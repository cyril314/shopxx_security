package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.Article;
import com.fit.entity.ArticleCategory;
import com.fit.service.ArticleCategoryService;
import com.fit.util.BeanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 文章分类
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/article_category")
public class ArticleCategoryController extends BaseController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        List<ArticleCategory> articles = this.articleCategoryService.findList();
        request.setAttribute("articleCategoryTreeList", recursivArticleCategoryTreeList(articles, null, null));

        return "/admin/article_category_list";
    }

    // 递归父类排序分类树
    private List<ArticleCategory> recursivArticleCategoryTreeList(List<ArticleCategory> allArticleCategoryList, ArticleCategory p, List<ArticleCategory> temp) {
        if (temp == null) {
            temp = new ArrayList<ArticleCategory>();
        }
        for (ArticleCategory articleCategory : allArticleCategoryList) {
            String parent = articleCategory.getParentId();
            if ((p == null && parent == null) || (articleCategory != null && parent == p.getId())) {
                temp.add(articleCategory);
//                if (articleCategory.getChildren() != null && articleCategory.getChildren().size() > 0) {
//                    recursivArticleCategoryTreeList(allArticleCategoryList, articleCategory, temp);
//                }
            }
        }
        return temp;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        ArticleCategory article = BeanUtils.map2Bean(ArticleCategory.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            if (Strings.isEmpty(article.getParentId())) {
                article.setParentId(null);
            }
            articleCategoryService.save(article);
        } else {
            ArticleCategory persistent = articleCategoryService.get(id);
            BeanUtils.copyProperties(persistent, article);
            articleCategoryService.update(persistent);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            articleCategoryService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }
}
