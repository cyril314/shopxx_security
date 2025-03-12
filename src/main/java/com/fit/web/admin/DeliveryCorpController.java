package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.DeliveryCorp;
import com.fit.entity.LogAction;
import com.fit.service.DeliveryCorpService;
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
@RequestMapping("/admin/delivery_corp")
public class DeliveryCorpController extends BaseController {

    @Autowired
    private DeliveryCorpService deliveryCorpService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<DeliveryCorp> lists = this.deliveryCorpService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        return "/admin/delivery_corp_list";
    }
}
