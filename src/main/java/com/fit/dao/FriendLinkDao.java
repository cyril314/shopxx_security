package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 友情链接接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface FriendLinkDao extends BaseCrudDao<FriendLink> {
}