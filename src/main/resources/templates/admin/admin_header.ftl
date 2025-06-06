<#assign authentication="com.fit.config.FreemarkerAuthenticationDirective"?new()>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>管理中心 - Powered By ${systemConfig.systemName}</title>
    <meta name="Author" content="SHOP++ Team"/>
    <meta name="Copyright" content="SHOP++"/>
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <#include "../include.ftl">
    <link href="${base!}/assets/admin/css/header.css" rel="stylesheet" type="text/css"/>
    <!--[if lte IE 6]>
    <script type="text/javascript">
        DD_belatedPNG.fix(".headerLogo");
    </script>
    <![endif]-->
</head>
<body class="header">
<div class="body">
    <div class="headerLogo"></div>
    <div class="headerTop">
        <div class="headerLink">
            <span class="welcome"><strong><@authentication property="name" /></strong>&nbsp;您好!&nbsp;</span>
            <a href="/admin/" target="mainFrame">后台首页</a>|
            <a href="http://bbs.shopxx.net" target="_blank">技术支持</a>|
            <a href="http://www.shopxx.net" target="_blank">购买咨询</a>|
            <a href="http://about.shopxx.net" target="_blank">关于我们</a>|
            <a href="http://contact.shopxx.net" target="_blank">联系我们</a>
        </div>
    </div>
    <div class="headerBottom">
        <div class="headerMenu">
            <div class="menuLeft"></div>
            <ul>
                <li><a href="/menu/product" target="menuFrame" hidefocus="true">商品管理</a></li>
                <li><a href="/menu/order" target="menuFrame" hidefocus="true">订单处理</a></li>
                <li><a href="/menu/member" target="menuFrame" hidefocus="true">会员管理</a></li>
                <li><a href="/menu/content" target="menuFrame" hidefocus="true">页面内容</a></li>
                <li><a href="/menu/admin" target="menuFrame" hidefocus="true">管理员</a></li>
                <li><a href="/menu/setting" target="menuFrame" hidefocus="true">网站设置</a></li>
                <li class="home"><a href="${base!}/" target="_blank" hidefocus="true"></a></li>
            </ul>
            <div class="menuRight"></div>
        </div>
        <div class="userInfo">
            <a href="admin_profile/edit" target="mainFrame">
                <span class="profileIcon">&nbsp;</span>个人资料
            </a>
            <a href="${base!}/admin/logout" target="_top">
                <span class="logoutIcon">&nbsp;</span>退出&nbsp
            </a>
        </div>
    </div>
</div>
</body>
</html>