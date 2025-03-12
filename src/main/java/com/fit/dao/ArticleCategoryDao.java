package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 文章分类接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface ArticleCategoryDao extends BaseCrudDao<ArticleCategory> {
}