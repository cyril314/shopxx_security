<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>管理菜单 - Powered By ${systemConfig.systemName}</title>
    <meta name="Author" content="SHOP++ Team"/>
    <meta name="Copyright" content="SHOP++"/>
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <#include "../include.ftl">
    <link href="${base!}/assets/admin/css/menu.css" rel="stylesheet" type="text/css"/>
</head>
<body class="menu">
<div class="menuContent">
    <dl>
        <dt><span>管理员&nbsp;</span></dt>
        <dd><a href="/admin/admin/list" target="mainFrame">管理员列表</a></dd>
        <dd><a href="/admin/role/list" target="mainFrame">角色管理</a></dd>
        <dd><a href="/admin/resource/list" target="mainFrame">资源管理</a></dd>
    </dl>
    <dl>
        <dt><span>站内消息&nbsp;</span></dt>
        <dd><a href="/admin/message/inbox" target="mainFrame">收件箱</a></dd>
        <dd><a href="/admin/message/outbox" target="mainFrame">发件箱</a></dd>
    </dl>
    <dl>
        <dt><span>操作日志管理&nbsp;</span></dt>
        <dd><a href="/admin/log/list" target="mainFrame">查看日志</a></dd>
        <dd><a href="/admin/log_config/list" target="mainFrame">日志配置</a></dd>
    </dl>
</div>
</body>
</html>