package com.fit.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.Area;
import com.fit.service.AreaService;
import com.fit.util.BeanUtils;
import com.fit.util.SystemConfigUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 地区
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/area")
public class AreaController extends BaseController {

    public static final String PATH_SEPARATOR = ",";// 树路径分隔符

    @Autowired
    private AreaService areaService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        List<Area> lists = this.areaService.findList(map);
        request.setAttribute("areaList", lists);
        return "/admin/area_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public R save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        String id = paramsMap.get("id").toString();
        String parentId = paramsMap.get("parentId").toString();
        Area area = BeanUtils.map2Bean(Area.class, paramsMap);
        if (Strings.isEmpty(id)) {
            Area parent = areaService.get(area.getParentId());
            if (area.getName().equalsIgnoreCase(parent.getName())) {
                return R.error("地区名称已存在!");
            } else {
                if (Strings.isEmpty(parentId)) {
                    area.setParentId(null);
                }
                areaService.save(area);
            }
        } else {
            Area persistent = areaService.get(id);
            Area parent = areaService.get(persistent.getParentId());
            if (persistent.getName().equalsIgnoreCase(parent.getName())) {
                return R.error("地区名称已存在!");
            } else {
                BeanUtils.copyProperties(persistent, area);
                areaService.update(persistent);
            }
        }
        return R.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            areaService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }

    @RequestMapping("/ajaxChildrenArea.action")
    @ResponseBody
    public String ajaxChildrenArea(String path) {
        String id = path;
        if (path.contains(PATH_SEPARATOR)) {
            id = substringAfterLast(path, PATH_SEPARATOR);
        }
        Map<String, Object> params = new HashMap<>();
        if (Strings.isEmpty(id)) {
            params.put("parentId", null);
        } else {
            params.put("parentId", areaService.get(id).getId());
        }
        List<Area> childrenAreaList = areaService.findList(params);
        JSONArray jsonArray = new JSONArray();
        for (Area area : childrenAreaList) {
            JSONObject map = new JSONObject();
            map.put("title", area.getName());
            map.put("value", area.getPath());
            jsonArray.add(map);
        }
        return toJSONString(jsonArray.toString());
    }
}
