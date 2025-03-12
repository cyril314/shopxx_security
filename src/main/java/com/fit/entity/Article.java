package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 文章
 * @Author AIM
 * @DATE 2025-03-10 14:06:17
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Article extends BaseEntity<Article> {
    /** 作者 (无默认值) */
    private String author;

    /** 标题 (无默认值) */
    private String title;

    /** 内容 (无默认值) */
    private String content;

    /** 点击数 (无默认值) */
    private Integer hits;

    /** HTML静态文件路径（首页） (无默认值) */
    private String htmlFilePath;

    /** 页面关键词 (无默认值) */
    private String metaKeywords;

    /** 页面描述 (无默认值) */
    private String metaDescription;

    /** 是否发布 (无默认值) */
    private Boolean isPublication;

    /** 是否为推荐文章 (无默认值) */
    private Boolean isRecommend;

    /** 是否置顶 (无默认值) */
    private Boolean isTop;

    /** 文章页数 (无默认值) */
    private Integer pageCount;

    /** 文章分类ID (无默认值) */
    private String articleCategoryId;
}