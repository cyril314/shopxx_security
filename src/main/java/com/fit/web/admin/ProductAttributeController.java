package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.entity.ProductAttribute;
import com.fit.entity.ProductCategory;
import com.fit.service.ProductAttributeService;
import com.fit.service.ProductCategoryService;
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
@RequestMapping("/admin/productAttribute")
public class ProductAttributeController extends BaseController {

    @Autowired
    private ProductAttributeService productAttributeService;

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
        ProductAttribute productAttribute = BeanUtils.map2Bean(ProductAttribute.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            productAttributeService.save(productAttribute);
        } else {
            ProductAttribute persistent = productAttributeService.get(id);
            BeanUtils.copyProperties(persistent, productAttribute);
            productAttributeService.update(persistent);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            productAttributeService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
