package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.FriendLink;
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
@RequestMapping("/admin/friend_link")
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkService friendLinkService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<FriendLink> articles = this.friendLinkService.findList(map);
        pager.setList(articles);
        request.setAttribute("pager", pager);

        return "/admin/friend_link_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }
}
