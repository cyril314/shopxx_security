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
@RequestMapping("/admin/template_html")
public class TemplateHtmlController extends BaseController {

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.setAttribute("htmlConfigList", TemplateConfigUtil.getHtmlConfigList());
        return "/admin/template_html_list";
    }
}
