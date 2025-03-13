package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.config.security.utils.I18nMessage;
import com.fit.entity.Product;
import com.fit.service.BrandService;
import com.fit.service.HtmlService;
import com.fit.service.ProductService;
import com.fit.service.ProductTypeService;
import com.fit.util.SystemConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/admin/product")
public class ProductController extends BaseController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private HtmlService htmlService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Product> products = this.productService.findList(map);
        pager.setList(products);
        pager.setTotalCount(products.size());
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        return "/admin/product_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, String id) {
        Product product = new Product();
        if (!StringUtils.isEmpty(id)) {
            product = this.productService.get(id);
        }

        request.setAttribute("id", id);
        request.setAttribute("product", product);
        request.setAttribute("allBrand", brandService.findList());
        request.setAttribute("allProductType", productTypeService.findList());
        request.setAttribute("allWeightUnit", I18nMessage.WeightUnit.values());
        request.setAttribute("productCategoryTreeList", htmlService.getProductCategoryTree());
        return "/admin/product_input";
    }
}
