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
        <dt><span>会员管理</span></dt>
        <dd><a href="/admin/member/list" target="mainFrame">会员列表</a></dd>
        <dd><a href="/admin/member_rank/list" target="mainFrame">会员等级</a></dd>
        <dd><a href="/admin/member_attribute/list" target="mainFrame">会员注册项</a></dd>
    </dl>
</div>
</body>
</html>