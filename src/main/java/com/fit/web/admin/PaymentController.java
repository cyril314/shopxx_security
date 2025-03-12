package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.Payment;
import com.fit.service.PaymentService;
import com.fit.util.SystemConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/payment")
public class PaymentController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Payment> lists = this.paymentService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("orderUnitCurrencyFormat", SystemConfigUtil.getOrderUnitCurrencyFormat());
        return "/admin/payment_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }
}
