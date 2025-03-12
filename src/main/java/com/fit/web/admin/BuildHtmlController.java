package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.entity.Article;
import com.fit.entity.Product;
import com.fit.service.HtmlService;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/12
 */
@Controller
@RequestMapping("/admin/build_html")
public class BuildHtmlController extends BaseController {

    @Autowired
    private HtmlService htmlService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/allInput")
    public String allInput() {
        return "/admin/build_html_all_input";
    }

    @RequestMapping("/articleInput")
    public String articleInput(HttpServletRequest request) {
        request.setAttribute("articleCategoryTreeList", htmlService.getArticleCategoryTree());
        return "/admin/build_html_article_input";
    }

    @RequestMapping("/productInput")
    public String productInput(HttpServletRequest request) {
        request.setAttribute("productCategoryTreeList", htmlService.getProductCategoryTree());
        return "/admin/build_html_product_input";
    }

    @RequestMapping
    @ResponseBody
    public String all(HttpServletRequest request, HttpServletResponse response, String buildType, String buildContent, Integer maxResults, Integer firstResult, Date beginDate, Date endDate) {
        Cache cache = ServletCacheAdministrator.getInstance(request.getSession().getServletContext()).getCache(request, PageContext.APPLICATION_SCOPE);
        cache.flushAll(new Date());
        if (StringUtils.isEmpty(buildType)) {
            buildType = "all";
        }
        if (StringUtils.isEmpty(buildContent)) {
            buildContent = "baseJavascript";
        }
        if (maxResults == null || maxResults < 0) {
            maxResults = 50;
        }
        if (firstResult == null || firstResult < 0) {
            firstResult = 0;
        }
        Map<String, String> jsonMap = new HashMap<String, String>();
        if (buildContent.equalsIgnoreCase("baseJavascript")) {
            htmlService.baseJavascriptBuildHtml();
            jsonMap.put(STATUS, "baseJavascriptFinish");
            jsonMap.put("buildTotal", "1");
            return ajaxJson(response, jsonMap);
        }
        if (buildContent.equalsIgnoreCase("errorPage")) {
            htmlService.errorPageBuildHtml();
            htmlService.errorPageAccessDeniedBuildHtml();
            htmlService.errorPage500BuildHtml();
            htmlService.errorPage404BuildHtml();
            htmlService.errorPage403BuildHtml();
            jsonMap.put(STATUS, "errorPageFinish");
            jsonMap.put("buildTotal", "1");
            return ajaxJson(response, jsonMap);
        }
        if (buildContent.equalsIgnoreCase("index")) {
            htmlService.indexBuildHtml();
            jsonMap.put(STATUS, "indexFinish");
            jsonMap.put("buildTotal", "1");
            return ajaxJson(response, jsonMap);
        }
        if (buildContent.equalsIgnoreCase("login")) {
            htmlService.loginBuildHtml();
            jsonMap.put(STATUS, "loginFinish");
            jsonMap.put("buildTotal", "1");
            return ajaxJson(response, jsonMap);
        }
        if (buildContent.equalsIgnoreCase("article")) {
            List<Article> articleList = null;
            if (buildType.equalsIgnoreCase("all")) {
                String sql = "SELECT * FROM `article` WHERE `is_publication`=1 ORDER BY `is_top`,`create_time` DESC LIMIT ?,?";
                articleList = jdbcTemplate.queryForList(sql, Article.class, new Object[]{firstResult, maxResults});
            } else if (buildType.equalsIgnoreCase("date")) {
                articleList = htmlService.getArticleList(beginDate, endDate, firstResult, maxResults);
            }
            if (articleList != null && articleList.size() > 0) {
                for (Article article : articleList) {
                    htmlService.articleContentBuildHtml(article);
                }
            }
            if (articleList != null && articleList.size() == maxResults) {
                int nextFirstResult = firstResult + articleList.size();
                jsonMap.put(STATUS, "articleBuilding");
                jsonMap.put("firstResult", String.valueOf(nextFirstResult));
                return ajaxJson(response, jsonMap);
            } else {
                int buildTotal = firstResult + 1 + articleList.size();
                jsonMap.put(STATUS, "articleFinish");
                jsonMap.put("buildTotal", String.valueOf(buildTotal));
                return ajaxJson(response, jsonMap);
            }
        }
        if (buildContent.equalsIgnoreCase("product")) {
            List<Product> productList = null;
            if (buildType.equalsIgnoreCase("all")) {
                String sql = "SELECT * FROM `product` WHERE `is_marketable`=1 ORDER BY `create_time` DESC LIMIT ?,?";
                productList = jdbcTemplate.queryForList(sql, Product.class, new Object[]{firstResult, maxResults});
            } else if (buildType.equalsIgnoreCase("date")) {
                productList = htmlService.getProductList(beginDate, endDate, firstResult, maxResults);
            }
            if (productList != null && productList.size() > 0) {
                for (Product product : productList) {
                    htmlService.productContentBuildHtml(product);
                }
            }
            if (productList != null && productList.size() == maxResults) {
                int nextFirstResult = firstResult + productList.size();
                jsonMap.put(STATUS, "productBuilding");
                jsonMap.put("firstResult", String.valueOf(nextFirstResult));
                return ajaxJson(response, jsonMap);
            } else {
                int buildTotal = firstResult + 1 + productList.size();
                jsonMap.put(STATUS, "productFinish");
                jsonMap.put("buildTotal", String.valueOf(buildTotal));
                return ajaxJson(response, jsonMap);
            }
        }
        return null;
    }
}