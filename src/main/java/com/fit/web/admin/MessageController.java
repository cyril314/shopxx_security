package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.Member;
import com.fit.entity.Message;
import com.fit.service.MemberService;
import com.fit.service.MessageService;
import com.fit.util.SystemConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 退款
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/inbox")
    public String inbox(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Message> lists = this.messageService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/message_inbox";
    }

    @RequestMapping("/outbox")
    public String outbox(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Message> lists = this.messageService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/message_outbox";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            messageService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
