package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 文章接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface ArticleDao extends BaseCrudDao<Article> {
}