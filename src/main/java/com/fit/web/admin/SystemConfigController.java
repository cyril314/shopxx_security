package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.bean.SystemConfig;
import com.fit.entity.LogAction;
import com.fit.entity.PaymentConfig;
import com.fit.entity.SysRole;
import com.fit.service.PaymentConfigService;
import com.fit.util.SystemConfigUtil;
import com.fit.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/11
 */
@Controller
@RequestMapping("/admin")
public class SystemConfigController extends BaseController {

    @Autowired
    private PaymentConfigService paymentConfigService;

    @RequestMapping("/system_config/edit")
    public String system_config(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        request.setAttribute("systemConfig", SystemConfigUtil.getSystemConfig());
        request.setAttribute("allCurrencyType", SystemConfig.CurrencyType.values());
        request.setAttribute("allPointType", SystemConfig.PointType.values());
        request.setAttribute("allRoundType", SystemConfig.RoundType.values());
        request.setAttribute("allStoreFreezeTime", SystemConfig.StoreFreezeTime.values());
        request.setAttribute("allWatermarkPosition", SystemConfig.WatermarkPosition.values());
        return "/admin/system_config_input";
    }

    @RequestMapping("/payment_config/list")
    public String payment_config_list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<PaymentConfig> lists = this.paymentConfigService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        return "/admin/payment_config_list";
    }
}
