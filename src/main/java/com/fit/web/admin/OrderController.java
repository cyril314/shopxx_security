package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.Orders;
import com.fit.service.OrdersService;
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
@RequestMapping("/admin/order")
public class OrderController extends BaseController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Orders> orders = this.ordersService.findList(map);
        pager.setList(orders);
        request.setAttribute("pager", pager);
        request.setAttribute("orderUnitCurrencyFormat", SystemConfigUtil.getOrderUnitCurrencyFormat());
        return "/admin/order_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }
}
