package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.SysAdmin;
import com.fit.service.SysAdminService;
import com.fit.util.BeanUtils;
import com.fit.util.SystemConfigUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * @AUTO 后台管理、管理员
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/admin")
public class SysAdminController extends BaseController {

    @Autowired
    private SysAdminService adminService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<SysAdmin> lists = this.adminService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/admin_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        String id = paramsMap.get("id").toString();
        SysAdmin admin = BeanUtils.map2Bean(SysAdmin.class, paramsMap);
        if (Strings.isEmpty(id)) {
            admin.setUsername(admin.getUsername().toLowerCase());
            admin.setLoginFailureCount(0);
            admin.setIsLocked(false);
            admin.setIsExpired(false);
            admin.setIsCredentialsExpired(false);
            admin.setPassword(md5Hex(admin.getPassword()));
            adminService.save(admin);
        } else {
            SysAdmin persistent = adminService.get(id);
            if (Strings.isNotEmpty(admin.getPassword())) {
                persistent.setPassword(md5Hex(admin.getPassword()));
            }
            BeanUtils.copyProperties(persistent, admin);
            adminService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            adminService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }

    @RequestMapping("/checkpwd")
    @ResponseBody
    public R checkCurrentPassword(String currentPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SysAdmin admin = (SysAdmin) authentication.getPrincipal();
            if (admin.getPassword().equals(md5Hex(currentPassword))) {
                return R.success();
            }
        }
        return R.error();
    }
}
