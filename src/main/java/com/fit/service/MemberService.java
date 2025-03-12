package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.MemberDao;
import com.fit.entity.Member;
import org.springframework.stereotype.Service;

/**
 * @AUTO 会员信息服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class MemberService extends BaseCrudService<MemberDao, Member> {
}