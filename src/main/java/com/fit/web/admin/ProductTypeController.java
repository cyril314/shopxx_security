package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.entity.Article;
import com.fit.entity.ProductType;
import com.fit.service.ProductTypeService;
import com.fit.util.BeanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @AUTO 商品类型
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/productType")
public class ProductTypeController extends BaseController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        ProductType productType = BeanUtils.map2Bean(ProductType.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            productTypeService.save(productType);
        } else {
            ProductType persistent = productTypeService.get(id);
            BeanUtils.copyProperties(persistent, productType);
            productTypeService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            productTypeService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
