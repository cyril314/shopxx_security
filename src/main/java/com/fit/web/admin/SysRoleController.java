package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.SysRole;
import com.fit.service.SysRoleService;
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
 * @AUTO 角色
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService roleService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<SysRole> lists = this.roleService.findList(map);
        pager.setList(lists);
        pager.setTotalCount(lists.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/role_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        String id = paramsMap.get("id").toString();
        SysRole sysRole = BeanUtils.map2Bean(SysRole.class, paramsMap);
        if (Strings.isEmpty(id)) {
            roleService.save(sysRole);
        } else {
            SysRole persistent = roleService.get(id);
            BeanUtils.copyProperties(persistent, sysRole);
            roleService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            roleService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
