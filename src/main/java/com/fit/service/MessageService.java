package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.MessageDao;
import com.fit.entity.Message;
import org.springframework.stereotype.Service;

/**
 * @AUTO 消息服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class MessageService extends BaseCrudService<MessageDao, Message> {
}