package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.SysResource;
import com.fit.service.SysResourceService;
import com.fit.util.BeanUtils;
import com.fit.util.SystemConfigUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 资源
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/resource")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService resourceService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<SysResource> lists = this.resourceService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/resource_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        SysResource resource = BeanUtils.map2Bean(SysResource.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            resourceService.save(resource);
        } else {
            SysResource persistent = resourceService.get(id);
            BeanUtils.copyProperties(persistent, resource);
            resourceService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            resourceService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
