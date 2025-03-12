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
        <dt><span>内容管理</span></dt>
        <dd><a href="/admin/navigation/list" target="mainFrame">导航管理</a></dd>
        <dd><a href="/admin/article/list" target="mainFrame">文章管理</a></dd>
        <dd><a href="/admin/article_category/list" target="mainFrame">文章分类</a></dd>
        <dd><a href="/admin/friend_link/list" target="mainFrame">友情链接</a></dd>
        <dd><a href="/admin/footer/edit" target="mainFrame">网页底部信息</a></dd>
        <dd><a href="/admin/agreement/edit" target="mainFrame">会员注册协议</a></dd>
    </dl>
    <dl>
        <dt><span>模板管理</span></dt>
        <dd><a href="/admin/template_dynamic/list" target="mainFrame">动态模板管理</a></dd>
        <dd><a href="/admin/template_html/list" target="mainFrame">静态模板管理</a></dd>
        <dd><a href="/admin/template_mail/list" target="mainFrame">邮件模板管理</a></dd>
    </dl>
    <dl>
        <dt><span>缓存管理</span></dt>
        <dd><a href="/admin/cache/flush" target="mainFrame">更新缓存</a></dd>
    </dl>
    <dl>
        <dt><span>网站静态管理</span></dt>
        <dd><a href="/admin/build_html/allInput" target="mainFrame">一键网站更新</a></dd>
        <dd><a href="/admin/build_html/articleInput" target="mainFrame">文章更新</a></dd>
        <dd><a href="/admin/build_html/productInput" target="mainFrame">商品更新</a></dd>
    </dl>
</div>
</body>
</html>