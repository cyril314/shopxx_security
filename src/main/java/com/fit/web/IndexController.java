package com.fit.web;

import com.fit.base.BaseController;
import com.fit.bean.Pager;
import com.fit.entity.*;
import com.fit.service.*;
import com.fit.util.BeanUtils;
import com.fit.util.SystemConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private HtmlService htmlService;
    @Autowired
    private ProductService productService;

    @RequestMapping({"/login"})
    public String login(HttpServletRequest request) {
        return toFront(request, "login");
    }

    @RequestMapping({"", "/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        Map<String, Object> param = getRequestParamsMap(request);
        Pager pager = (Pager) param.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        request.setAttribute("pager", pager);
        request.setAttribute("priceCurrencyFormat", SystemConfigUtil.getPriceCurrencyFormat());
        param.clear();
        param.put("parentId", "0");
        List<Map<String, Object>> pcs = htmlService.getProductCategoryTree();
        request.setAttribute("rootProductCategoryList", pcs);
        param.clear();
        param.put("isMarketable", 1);
        param.put("isHot", 1);
        List<Product> HProducts = this.productService.findList(param);
        request.setAttribute("hotProductList", HProducts);
        param.clear();
        param.put("isMarketable", 1);
        param.put("isNew", 1);
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> p : pcs) {
            String id = p.get("id").toString();
            List<Map<String, Object>> newProductList = this.productService.getNewProductList(id);
            List<Product> products = BeanUtils.map2List(Product.class, newProductList);
            map.put(id, products);
        }
        request.setAttribute("newProductMap", map);
        param.clear();
        param.put("isPublication", 1);
        List<Article> articles = articleService.findList(param);
        request.setAttribute("hotArticleList", articles);
        param.clear();
        param.put("isMarketable", 1);
        param.put("isBest", 1);
        List<Product> BProducts = this.productService.findList(param);
        request.setAttribute("bestProductList", BProducts);
        return toFront(request, "index");
    }
}
