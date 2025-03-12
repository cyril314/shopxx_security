package com.fit.service;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fit.bean.HtmlConfig;
import com.fit.bean.SystemConfig;
import com.fit.entity.*;
import com.fit.util.BeanUtils;
import com.fit.util.CommonUtil;
import com.fit.util.SystemConfigUtil;
import com.fit.util.TemplateConfigUtil;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.BulletList;
import org.htmlparser.tags.DefinitionList;
import org.htmlparser.tags.DefinitionListBullet;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Service
public class HtmlService {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private FriendLinkService friendLinkService;
    @Autowired
    private FooterService footerService;
    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private ArticleService articleDao;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getRealPath(String htmlFilePath) throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:" + htmlFilePath).getAbsolutePath();
    }

    public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data) {
        try {
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate(templateFilePath);
            File htmlFile = new File(this.getRealPath(htmlFilePath));
            File htmlDirectory = htmlFile.getParentFile();
            if (!htmlDirectory.exists()) {
                htmlDirectory.mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取公共数据
    public Map<String, Object> getCommonData() {
        Map<String, Object> commonData = new HashMap<String, Object>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n");
        ResourceBundleModel resourceBundleModel = new ResourceBundleModel(resourceBundle, new BeansWrapper());
        SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();

        String priceCurrencyFormat = SystemConfigUtil.getPriceCurrencyFormat();
        String priceUnitCurrencyFormat = SystemConfigUtil.getPriceUnitCurrencyFormat();

        String orderCurrencyFormat = SystemConfigUtil.getOrderCurrencyFormat();
        String orderUnitCurrencyFormat = SystemConfigUtil.getOrderUnitCurrencyFormat();

//        commonData.put("base", servletContext.getContextPath());
        commonData.put("bundle", resourceBundleModel);
        commonData.put("systemConfig", systemConfig);
        commonData.put("priceCurrencyFormat", priceCurrencyFormat);
        commonData.put("priceUnitCurrencyFormat", priceUnitCurrencyFormat);
        commonData.put("orderCurrencyFormat", orderCurrencyFormat);
        commonData.put("orderUnitCurrencyFormat", orderUnitCurrencyFormat);
        Map<String, Object> param = new HashMap<>();
        param.put("position", 0);
        param.put("isVisible", 1);
        List<Navigation> tops = this.navigationService.findList(param);
        commonData.put("topNavigationList", tops);
        param.put("position", 1);
        List<Navigation> middles = this.navigationService.findList(param);
        commonData.put("middleNavigationList", middles);
        param.put("position", 2);
        List<Navigation> bottoms = this.navigationService.findList(param);
        commonData.put("bottomNavigationList", bottoms);
        commonData.put("friendLinkList", friendLinkService.findList());
        List<FriendLink> tlist = new ArrayList();
        List<FriendLink> plist = this.friendLinkService.findList(param);
        for (FriendLink friendLink : plist) {
            if (ObjectUtils.isEmpty(friendLink.getLogo())) {
                tlist.add(friendLink);
            }
        }
        commonData.put("pictureFriendLinkList", plist);
        commonData.put("textFriendLinkList", tlist);
        commonData.put("footer", footerService.get("1"));
        return commonData;
    }

    public void baseJavascriptBuildHtml() {
        Map<String, Object> data = getCommonData();
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.BASE_JAVASCRIPT);
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public List<Map<String, Object>> getProductCategoryTree() {
        List<Map<String, Object>> pcs = new ArrayList<>();
        List<ProductCategory> list = productCategoryService.findList();
        // 首先找到所有顶级分类（parentId为null的分类）
        for (ProductCategory pc : list) {
            if (pc.getParentId() == null) {
                Map<String, Object> map = BeanUtils.bean2Map(pc);
                // 递归获取子分类
                map.put("children", getProductCategoryChildren(pc.getId(), list));
                pcs.add(map);
            }
        }
        return pcs;
    }

    // 递归方法，获取某个分类的所有子分类
    private List<Map<String, Object>> getProductCategoryChildren(String parentId, List<ProductCategory> list) {
        List<Map<String, Object>> children = new ArrayList<>();
        for (ProductCategory pc : list) {
            if (parentId.equals(pc.getParentId())) {
                Map<String, Object> map = BeanUtils.bean2Map(pc);
                // 递归获取子分类的子分类
                map.put("children", getProductCategoryChildren(pc.getId(), list));
                children.add(map);
            }
        }

        return children;
    }

    public List<Map<String, Object>> getArticleCategoryTree() {
        List<Map<String, Object>> pcs = new ArrayList<>();
        List<ArticleCategory> list = articleCategoryService.findList();
        // 首先找到所有顶级分类（parentId为null的分类）
        for (ArticleCategory ac : list) {
            if (ac.getParentId() == null) {
                Map<String, Object> map = BeanUtils.bean2Map(ac);
                // 递归获取子分类
                map.put("children", getArticleCategoryChildren(ac.getId(), list));
                pcs.add(map);
            }
        }
        return pcs;
    }

    // 递归方法，获取某个分类的所有子分类
    private List<Map<String, Object>> getArticleCategoryChildren(String parentId, List<ArticleCategory> list) {
        List<Map<String, Object>> children = new ArrayList<>();
        for (ArticleCategory ac : list) {
            if (parentId.equals(ac.getParentId())) {
                Map<String, Object> map = BeanUtils.bean2Map(ac);
                // 递归获取子分类的子分类
                map.put("children", getArticleCategoryChildren(ac.getId(), list));
                children.add(map);
            }
        }

        return children;
    }

    public void loginBuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.LOGIN);
        Map<String, Object> data = getCommonData();
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void productContentBuildHtml(Product product) {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.PRODUCT_CONTENT);
        ProductCategory productCategory = this.productCategoryService.get(product.getProductCategoryId());
        Map<String, Object> data = getCommonData();
        data.put("product", product);
        List<ProductCategory> pathList = jdbcTemplate.queryForList("select * from `product_category` where `id`!=? and `id` in (?) order by `sort` asc", ProductCategory.class, new Object[]{productCategory.getId(), productCategory.getPath()});
        pathList.add(productCategory);
        data.put("pathList", pathList);

        data.put("rootProductCategoryList", jdbcTemplate.queryForList("SELECT * FROM `product_category` WHERE `parent_id` IS NULL ORDER BY `sort` ASC", ProductCategory.class));
        Object[] obpcs = {productCategory.getId(), productCategory.getPath() + "%"};
        String bpl = "SELECT p.* FROM `product` p LEFT JOIN `product_category` c ON p.`product_category_id`=c.`id` WHERE p.`is_marketable`=1 AND p.`is_best`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY p.`create_time` DESC LIMIT 20";
        data.put("bestProductList", jdbcTemplate.queryForList(bpl, Product.class, obpcs));
        String hpl = "SELECT p.* FROM `product` p LEFT JOIN `product_category` c ON p.`product_category_id`=c.`id` WHERE p" + ".`is_marketable`=1 AND p.`is_hot`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY p.`create_time` DESC LIMIT 20";
        data.put("hotProductList", jdbcTemplate.queryForList(bpl, Product.class, obpcs));
        String npl = "SELECT p.* FROM `product` p LEFT JOIN `product_category` c ON p.`product_category_id`=c.`id` WHERE p.`is_marketable`=1 AND p.`is_new`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY p.`create_time` DESC LIMIT 20";
        data.put("newProductList", jdbcTemplate.queryForList(bpl, Product.class, obpcs));
        String htmlFilePath = product.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void indexBuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.INDEX);
        Map<String, Object> data = getCommonData();

        List<ProductCategory> allProductCategory = productCategoryService.findList();
        data.put("allProductCategoryList", allProductCategory);
        String tcs = "SELECT * FROM `product_category` WHERE `id` !=? AND `path` LIKE ? ORDER BY `sort` ASC";
        Map<String, List<ProductCategory>> productCategoryMap = new HashMap<String, List<ProductCategory>>();
        String bps = "select t.* from `product` t left join `product_category` c on t.`product_category_id`=c.`id` where t" + ".`is_marketable`=1 and t.`is_best`=1 and (t.`product_category_id`=? or c.`path` like ? ) order by t.`create_time` desc";
        Map<String, List<Product>> bestProductMap = new HashMap<String, List<Product>>();
        String hps = "select t.* from `product` t left join `product_category` c on t.`product_category_id`=c.`id` where t.`is_marketable`=1 and t.`is_hot`=1 and (t.`product_category_id`=? or c.`path` like ? ) order by t.`create_time` desc";
        Map<String, List<Product>> hotProductMap = new HashMap<String, List<Product>>();
        String nps = "select t.* from `product` t left join `product_category` c on t.`product_category_id`=c.`id` where t.`is_marketable`=1 and t.`is_new`=1 and (t.`product_category_id`=? or c.`path` like ? ) order by t.`create_time` desc";
        Map<String, List<Product>> newProductMap = new HashMap<String, List<Product>>();
        for (ProductCategory productCategory : allProductCategory) {
            Object[] objects = {productCategory.getId(), productCategory.getPath() + "%"};
            productCategoryMap.put(productCategory.getId(), jdbcTemplate.queryForList(tcs, ProductCategory.class, objects));
            bestProductMap.put(productCategory.getId(), jdbcTemplate.queryForList(bps, Product.class, objects));
            hotProductMap.put(productCategory.getId(), jdbcTemplate.queryForList(hps, Product.class, objects));
            newProductMap.put(productCategory.getId(), jdbcTemplate.queryForList(nps, Product.class, objects));
        }
        data.put("productCategoryMap", productCategoryMap);
        data.put("bestProductMap", bestProductMap);
        data.put("hotProductMap", hotProductMap);
        data.put("newProductMap", newProductMap);

        this.getArticleAndCategory(data);

        List<ArticleCategory> allArticleCategory = articleCategoryService.findList();
        data.put("allArticleCategoryList", allArticleCategory);
        String acs = "SELECT * FROM `article_category` WHERE `id` != ? AND `path` LIKE ? ORDER BY `sort` ASC";
        Map<String, List<ArticleCategory>> articleCategoryMap = new HashMap<String, List<ArticleCategory>>();
        String rls = "SELECT a.* FROM `article` a LEFT JOIN `article_category` c ON a.`article_category_id`=c.`id` WHERE a" + ".`is_publication`=1 AND a.`is_recommend`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY a.`is_top` DESC,a.`create_time` DESC LIMIT 20";
        Map<String, List<Article>> recommendArticleMap = new HashMap<String, List<Article>>();
        String hls = "SELECT a.* FROM `article` a LEFT JOIN `article_category` c ON a.`article_category_id`=c.`id` WHERE a.`is_publication`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY a.`hits` DESC,a.`is_top` DESC,a.`create_time` DESC LIMIT 20";
        Map<String, List<Article>> hotArticleMap = new HashMap<String, List<Article>>();
        String nls = "SELECT a.* FROM `article` a LEFT JOIN `article_category` c ON a.`article_category_id`=c.`id` WHERE a.`is_publication`=1 AND (c.`id`=? OR c.`path` LIKE ?) ORDER BY a.`create_time` DESC LIMIT 20";
        Map<String, List<Article>> newArticleMap = new HashMap<String, List<Article>>();
        for (ArticleCategory articleCategory : allArticleCategory) {
            Object[] alcs = {articleCategory.getId(), articleCategory.getPath() + "%"};
            articleCategoryMap.put(articleCategory.getId(), jdbcTemplate.queryForList(acs, ArticleCategory.class, alcs));
            recommendArticleMap.put(articleCategory.getId(), jdbcTemplate.queryForList(rls, Article.class, alcs));
            hotArticleMap.put(articleCategory.getId(), jdbcTemplate.queryForList(hls, Article.class, alcs));
            newArticleMap.put(articleCategory.getId(), jdbcTemplate.queryForList(nls, Article.class, alcs));
        }
        data.put("articleCategoryMap", articleCategoryMap);
        data.put("recommendArticleMap", recommendArticleMap);
        data.put("hotArticleMap", hotArticleMap);
        data.put("newArticleMap", newArticleMap);

        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    private void getArticleAndCategory(Map<String, Object> data) {
        data.put("rootArticleCategoryList", jdbcTemplate.queryForList("SELECT * FROM `article_category` WHERE `parent_id` IS NULL ORDER BY `sort` ASC", ArticleCategory.class));
        String ras = "select * from `article` where `is_publication`=1 and `is_recommend`=1 order by `is_top` desc,`create_time` desc limit 20";
        data.put("recommendArticleList", jdbcTemplate.queryForList(ras, ArticleCategory.class));
        String has = "SELECT * FROM `article` WHERE `is_publication`=1 ORDER BY `hits` DESC,`is_top` DESC,`create_time` DESC LIMIT 20";
        data.put("hotArticleList", jdbcTemplate.queryForList(has, ArticleCategory.class));
        String nas = "SELECT * FROM `article` WHERE `is_publication`=1 ORDER BY `create_time` DESC LIMIT 20";
        data.put("newArticleList", jdbcTemplate.queryForList(nas, ArticleCategory.class));
    }

    public static String substringBeforeLast(String str, String separator) {
        if ((StringUtils.isEmpty(str)) || (StringUtils.isEmpty(separator))) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringAfterLast(String str, String separator) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (StringUtils.isEmpty(separator)) {
            return "";
        }
        int pos = str.lastIndexOf(separator);
        if ((pos == -1) || (pos == str.length() - separator.length())) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    // 获取文章分页内容
    public List<String> getPageContentList(Article article) {
        Integer MAX_PAGE_CONTENT_COUNT = 2000;
        List<String> pageContentList = new ArrayList<String>();
        String content = article.getContent();
        // 如果文章内容长度小于等于每页最大字符长度则直接返回所有字符
        if (content.length() <= 2000) {
            pageContentList.add(content);
            return pageContentList;
        }
        NodeFilter tableFilter = new NodeClassFilter(TableTag.class);// TABLE
        NodeFilter divFilter = new NodeClassFilter(Div.class);// DIV
        NodeFilter paragraphFilter = new NodeClassFilter(ParagraphTag.class);// P
        NodeFilter bulletListFilter = new NodeClassFilter(BulletList.class);// UL
        NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);// LI
        NodeFilter definitionListFilter = new NodeClassFilter(DefinitionList.class);// DL
        NodeFilter DefinitionListBulletFilter = new NodeClassFilter(DefinitionListBullet.class);// DD

        OrFilter orFilter = new OrFilter();
        orFilter.setPredicates(new NodeFilter[]{paragraphFilter, divFilter, tableFilter, bulletListFilter, bulletFilter, definitionListFilter, DefinitionListBulletFilter});
        List<Integer> indexList = new ArrayList<Integer>();
        // 按每页最大字符长度分割文章内容
        List<String> contentList = CommonUtil.splitString(content, MAX_PAGE_CONTENT_COUNT);
        for (int i = 0; i < contentList.size() - 1; i++) {
            try {
                String currentContent = contentList.get(i);
                Parser parser = Parser.createParser(currentContent, "UTF-8");
                NodeList nodeList = parser.parse(orFilter);
                if (nodeList.size() > 0) {
                    // 若在此段内容中查找到相关标签，则记录最后一个标签的索引位置
                    Node node = nodeList.elementAt(nodeList.size() - 1);
                    indexList.add(i * MAX_PAGE_CONTENT_COUNT + node.getStartPosition());
                } else {
                    // 若在此段内容中未找到任何相关标签，则查找相关标点符号，并记录最后一个标点符号的索引位置
                    Pattern pattern = Pattern.compile("\\.|。|\\!|！|\\?|？");
                    Matcher matcher = pattern.matcher(currentContent);
                    if (matcher.find()) {
                        int endIndex = 0;
                        while (matcher.find()) {
                            endIndex = matcher.end();
                        }
                        indexList.add(i * MAX_PAGE_CONTENT_COUNT + endIndex);
                    } else {
                        indexList.add((i + 1) * MAX_PAGE_CONTENT_COUNT);
                    }
                }
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <= indexList.size(); i++) {
            String pageContent = "";
            if (i == 0) {
                pageContent = content.substring(0, indexList.get(0));
            } else if (i == indexList.size()) {
                pageContent = content.substring(indexList.get(i - 1));
            } else {
                pageContent = content.substring(indexList.get(i - 1), indexList.get(i));
            }
            try {
                // 对分割出的分页内容进行HTML标签补全
                Parser parser = Parser.createParser(pageContent, "UTF-8");
                NodeList nodeList = parser.parse(orFilter);
                String contentResult = nodeList.toHtml();
                if (StringUtils.isEmpty(contentResult)) {
                    contentResult = pageContent;
                }
                pageContentList.add(contentResult);
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }
        return pageContentList;
    }

    @Transactional
    public void articleContentBuildHtml(Article article) {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ARTICLE_CONTENT);
        ArticleCategory articleCategory = this.articleCategoryService.get(article.getId());
        Map<String, Object> data = getCommonData();
        data.put("article", article);
        List<ArticleCategory> pathList = jdbcTemplate.queryForList("SELECT * FROM `article_category` WHERE `id`!=? AND `id` IN (?) ORDER " + "BY `sort` ASC", ArticleCategory.class, new Object[]{articleCategory.getId(), articleCategory.getPath()});
        pathList.add(articleCategory);
        data.put("pathList", pathList);

        this.getArticleAndCategory(data);

        String htmlFilePath = article.getHtmlFilePath();
        String prefix = this.substringBeforeLast(htmlFilePath, ".");
        String extension = this.substringAfterLast(htmlFilePath, ".");
        List<String> pageContentList = this.getPageContentList(article);
        article.setPageCount(pageContentList.size());
        articleDao.update(article);
        for (int i = 0; i < pageContentList.size(); i++) {
            data.put("content", pageContentList.get(i));
            data.put("pageNumber", i + 1);
            data.put("pageCount", pageContentList.size());
            String templateFilePath = htmlConfig.getTemplateFilePath();
            String currentHtmlFilePath = null;
            if (i == 0) {
                currentHtmlFilePath = htmlFilePath;
            } else {
                currentHtmlFilePath = prefix + "_" + (i + 1) + "." + extension;
            }
            buildHtml(templateFilePath, currentHtmlFilePath, data);
        }
    }

    public void errorPageBuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
        Map<String, Object> data = getCommonData();
        data.put("errorContent", "系统出现异常，请与管理员联系！");
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void errorPageAccessDeniedBuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
        Map<String, Object> data = getCommonData();
        data.put("errorContent", "您无此访问权限！");
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void errorPage500BuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_500);
        Map<String, Object> data = getCommonData();
        data.put("errorContent", "系统出现异常，请与管理员联系！");
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void errorPage404BuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_404);
        Map<String, Object> data = getCommonData();
        data.put("errorContent", "您访问的页面不存在！");
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public void errorPage403BuildHtml() {
        HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_403);
        Map<String, Object> data = getCommonData();
        data.put("errorContent", "系统出现异常，请与管理员联系！");
        String htmlFilePath = htmlConfig.getHtmlFilePath();
        String templateFilePath = htmlConfig.getTemplateFilePath();
        buildHtml(templateFilePath, htmlFilePath, data);
    }

    public List<Product> getProductList(Date beginDate, Date endDate, Integer firstResult, Integer maxResults) {
        Object[] params;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from product WHERE is_marketable=1 ");
        if (beginDate != null) {
            beginDate.setHours(0);
            beginDate.setMinutes(0);
            beginDate.setSeconds(0);
        }
        if (endDate != null) {
            endDate.setHours(23);
            endDate.setMinutes(59);
            endDate.setSeconds(59);
        }
        if (beginDate != null && endDate == null) {
            sb.append(" AND create_date > ? ");
            params = new Object[]{beginDate, firstResult, maxResults};
        } else if (endDate != null && beginDate == null) {
            sb.append(" AND create_date < ? ");
            params = new Object[]{endDate, firstResult, maxResults};
        } else if (endDate != null && beginDate != null) {
            sb.append(" AND create_date > ? AND create_date < ? ");
            params = new Object[]{beginDate, endDate, firstResult, maxResults};
        } else {
            params = new Object[]{firstResult, maxResults};
        }
        sb.append(" ORDER BY create_date DESC LIMIT ?, ?");
        // 执行查询并映射结果
        return jdbcTemplate.queryForList(sb.toString(), Product.class, params);
    }

    public List<Article> getArticleList(Date beginTime, Date endTime, Integer firstResult, Integer maxResults) {
        Object[] params;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from article WHERE is_marketable=1 ");
        if (beginTime != null) {
            beginTime.setHours(0);
            beginTime.setMinutes(0);
            beginTime.setSeconds(0);
        }
        if (endTime != null) {
            endTime.setHours(23);
            endTime.setMinutes(59);
            endTime.setSeconds(59);
        }
        if (beginTime != null && endTime == null) {
            sb.append(" AND create_date > ? ");
            params = new Object[]{beginTime, firstResult, maxResults};
        } else if (endTime != null && beginTime == null) {
            sb.append(" AND create_date < ? ");
            params = new Object[]{endTime, firstResult, maxResults};
        } else if (endTime != null && beginTime != null) {
            sb.append(" AND create_date > ? AND create_date < ? ");
            params = new Object[]{beginTime, endTime, firstResult, maxResults};
        } else {
            params = new Object[]{firstResult, maxResults};
        }
        sb.append(" ORDER BY `is_top` DESC, `create_time` DESC LIMIT ?, ?");
        // 执行查询并映射结果
        return jdbcTemplate.queryForList(sb.toString(), Article.class, params);
    }
}