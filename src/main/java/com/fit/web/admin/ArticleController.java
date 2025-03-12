package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.R;
import com.fit.bean.Pager;
import com.fit.entity.Article;
import com.fit.service.ArticleService;
import com.fit.util.BeanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 文章
 * @Author AIM
 * @DATE 2025/1/9
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CacheManager cacheManager;
    @Value("${spring.cache.cache-names}")
    private String cacheName;

    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        Pager pager = (Pager) map.get("pager");
        if (pager == null) {
            pager = new Pager();
        }
        List<Article> articles = this.articleService.findList(map);
        pager.setList(articles);
        request.setAttribute("pager", pager);

        return "/admin/article_list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest req) {
        Map<String, Object> paramsMap = getRequestParamsMap(req);
        Article article = BeanUtils.map2Bean(Article.class, paramsMap);
        String id = paramsMap.get("id").toString();
        if (Strings.isEmpty(id)) {
            article.setHits(0);
            articleService.save(article);
            flushCache();
        } else {
            Article persistent = articleService.get(id);
            BeanUtils.copyProperties(persistent, article);
            articleService.update(persistent);
            flushCache();
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            articleService.batchDelete(ids);
            return R.success("删除成功！");
        } else {
            return R.error("删除失败,请检查!");
        }
    }

    // 更新页面缓存
    private void flushCache() {
        // 获取应用级别的缓存
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            // 清空缓存
            cache.clear();
        }
    }
}
