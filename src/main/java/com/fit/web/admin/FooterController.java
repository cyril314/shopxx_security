package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.Footer;
import com.fit.entity.FriendLink;
import com.fit.service.FooterService;
import com.fit.service.FriendLinkService;
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
@RequestMapping("/admin/footer")
public class FooterController extends BaseController {

    @Autowired
    private FooterService footerService;

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request) {
        Footer footer = this.footerService.get("1");
        request.setAttribute("footer", footer);
        return "/admin/footer_input";
    }
}