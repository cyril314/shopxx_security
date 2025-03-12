package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.DeliveryType;
import com.fit.util.TemplateConfigUtil;
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
@RequestMapping("/admin/template_mail")
public class TemplateMailController extends BaseController {

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.setAttribute("mailConfigList", TemplateConfigUtil.getMailConfigList());
        return "/admin/template_mail_list";
    }
}
