package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.Shipping;
import com.fit.service.ShippingService;
import com.fit.util.SystemConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 发货
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/shipping")
public class ShippingController extends BaseController {

    @Autowired
    private ShippingService shippingService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Shipping> lists = this.shippingService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("orderUnitCurrencyFormat", SystemConfigUtil.getOrderUnitCurrencyFormat());
        return "/admin/shipping_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            shippingService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
