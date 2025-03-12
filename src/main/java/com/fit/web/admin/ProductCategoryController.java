package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.entity.Product;
import com.fit.entity.ProductCategory;
import com.fit.entity.ProductType;
import com.fit.service.ProductCategoryService;
import com.fit.service.ProductService;
import com.fit.service.ProductTypeService;
import com.fit.util.BeanUtils;
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
 * @AUTO 商品类型
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/productCategory")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;

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
        ProductCategory productCategory = BeanUtils.map2Bean(ProductCategory.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            if (Strings.isEmpty(productCategory.getParentId())) {
                productCategory.setParentId(null);
            }
            productCategoryService.save(productCategory);
        } else {
            ProductCategory persistent = productCategoryService.get(id);
            BeanUtils.copyProperties(persistent, productCategory);
            productCategoryService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String id) throws Exception {
        String msg = "删除成功!";
        if (Strings.isEmpty(id)) {
            ProductCategory productCategory = productCategoryService.get(id);
            if (productCategory != null) {
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("parentId", productCategory.getId());
                List<ProductCategory> list = productCategoryService.findList(paramsMap);
                if (list != null && list.size() > 0) {
                    msg = "此商品分类存在下级分类，删除失败!";
                }
                List<Product> products = productService.findList(paramsMap);
                if (products != null && products.size() > 0) {
                    msg = "此商品分类下存在商品，删除失败!";
                }
                productCategoryService.delete(id);
            }
            return R.success(msg);
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
