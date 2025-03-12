package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.SystemConfig;
import com.fit.entity.SysAdmin;
import com.fit.service.OrdersService;
import com.fit.service.SysAdminService;
import com.fit.util.SystemConfigUtil;
import freemarker.ext.beans.BeansWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/6
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    // Spring security 最后登录异常Session名称
    public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
    public static final String SHOP_XX_KEY = "SHOP_XX_KEY";

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SysAdminService adminService;

    @RequestMapping({"", "/", "/main"})
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/admin_main";
    }

    @RequestMapping({"/index", "/index.action"})
    public String index(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderStatus", 0);
        int count = this.ordersService.findCount(map);
        request.setAttribute("unprocessedOrderCount", count);
        request.setAttribute("paidUnshippedOrderCount", count);
        request.setAttribute("unreadMessageCount", count);
        request.setAttribute("storeAlertCount", count);
        request.setAttribute("marketableProductCount", count);
        request.setAttribute("unMarketableProductCount", count);
        request.setAttribute("memberTotalCount", count);
        request.setAttribute("articleTotalCount", count);
        request.setAttribute("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        return "/admin/admin_index";
    }

    @RequestMapping({"/header", "/header.action"})
    public String header(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("authentication", BeansWrapper.getDefaultInstance().getStaticModels());
        return "/admin/admin_header";
    }

    @RequestMapping("/middle")
    public String middle(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/admin_middle";
    }

    /**
     * @return 退出, 跳转到登陆页面
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/login";
    }

    // 登录页面
    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpSession session) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        if (paramsMap.containsKey("error")) {
            String error = paramsMap.get("error").toString();
            if (StringUtils.endsWithIgnoreCase(error, "captcha")) {
//            addActionError("验证码错误,请重新输入!");
                return "/admin/admin_login";
            }
        }
        String message = "您的用户名或密码错误!";
        Exception springSecurityLastException = (Exception) getValue2Session(req, SPRING_SECURITY_LAST_EXCEPTION);
        if (springSecurityLastException != null) {
            if (springSecurityLastException instanceof BadCredentialsException) {
                Object loginUsername = session.getAttribute("SPRING_SECURITY_LAST_USERNAME");
                if (loginUsername != null) {
                    SysAdmin admin = new SysAdmin();
                    admin.setUsername(loginUsername.toString().toLowerCase());
                    admin = adminService.get(admin);
                    if (admin != null) {
                        SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();
                        int loginFailureLockCount = systemConfig.getLoginFailureLockCount();
                        int loginFailureCount = admin.getLoginFailureCount();
                        if (systemConfig.getIsLoginFailureLock() && loginFailureLockCount - loginFailureCount <= 3) {
                            message = "若连续" + loginFailureLockCount + "次密码输入错误,您的账号将被锁定!";
                        }
                    }
                }
            } else if (springSecurityLastException instanceof DisabledException) {
                message = "您的账号已被禁用,无法登录!";
            } else if (springSecurityLastException instanceof LockedException) {
                message = "您的账号已被锁定,无法登录!";
            } else if (springSecurityLastException instanceof AccountExpiredException) {
                message = "您的账号已过期,无法登录!";
            } else {
                message = "出现未知错误,无法登录!";
            }
            delValue2Session(req, SPRING_SECURITY_LAST_EXCEPTION);
        }
        req.setAttribute("errorMessages", message);
        if (paramsMap.containsKey(SHOP_XX_KEY)) {
            String k = paramsMap.get(SHOP_XX_KEY).toString();
            if (!containsIgnoreCase(k, "shopxx")) {
                throw new ExceptionInInitializerError();
            }
        }
        return "/admin/admin_login";
    }
}