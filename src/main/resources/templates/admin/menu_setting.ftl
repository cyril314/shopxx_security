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
        <dt><span>网站设置</span></dt>
        <dd><a href="/admin/system_config/edit" target="mainFrame">系统设置</a></dd>
    </dl>
    <dl>
        <dt><span>支付管理</span></dt>
        <dd><a href="/admin/payment_config/list" target="mainFrame">支付方式</a></dd>
    </dl>
    <dl>
        <dt><span>配送管理</span></dt>
        <dd><a href="/admin/delivery_type/list" target="mainFrame">配送方式</a></dd>
        <dd><a href="/admin/area/list" target="mainFrame">地区管理</a></dd>
        <dd><a href="/admin/delivery_corp/list" target="mainFrame">物流公司</a></dd>
    </dl>
</div>
</body>
</html>