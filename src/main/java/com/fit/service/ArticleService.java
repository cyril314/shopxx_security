package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.ArticleDao;
import com.fit.entity.Article;
import org.springframework.stereotype.Service;

/**
 * @AUTO 文章服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class ArticleService extends BaseCrudService<ArticleDao, Article> {
}