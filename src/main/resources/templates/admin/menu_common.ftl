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
        <dt><span>商品管理</span></dt>
        <dd><a href="/admin/product/list" target="mainFrame">商品列表</a></dd>
        <dd><a href="/admin/product/add" target="mainFrame">添加商品</a></dd>
    </dl>
    <dl>
        <dt><span>文章管理</span></dt>
        <dd><a href="/admin/article/list" target="mainFrame">文章列表</a></dd>
        <dd><a href="/admin/article/add" target="mainFrame">添加文章</a></dd>
    </dl>
    <dl>
        <dt><span>订单管理</span></dt>
        <dd><a href="/admin/order/list" target="mainFrame">订单列表</a></dd>
    </dl>
</div>
</body>
</html>