package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.entity.Agreement;
import com.fit.entity.SysAdmin;
import com.fit.service.AgreementService;
import com.fit.util.BeanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @AUTO 会员注册协议
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/agreement")
public class AgreementController extends BaseController {

    @Autowired
    private AgreementService agreementService;

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request) {
        Agreement agreement = this.agreementService.get("1");
        request.setAttribute("agreement", agreement);
        return "/admin/agreement_input";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        String id = paramsMap.get("id").toString();
        SysAdmin admin = BeanUtils.map2Bean(SysAdmin.class, paramsMap);
        if (Strings.isNotEmpty(id)) {
            Agreement persistent = agreementService.get(id);
            BeanUtils.copyProperties(persistent, admin);
            agreementService.update(persistent);
        }
    }
}
