<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${(pager.keyword)!} 文章搜索结果 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="${pager.keyword}" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "../include.ftl">
<link href="${base!}/assets/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base!}/assets/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base!}/assets/shop/css/article.css" rel="stylesheet" type="text/css" />
<link href="${base!}/assets/shop/css/article_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base!}/assets/shop/js/login.js"></script>
<script type="text/javascript" src="${base!}/assets/shop/js/register.js"></script>
<script type="text/javascript" src="${base!}/assets/shop/js/article.js"></script>
</head>
<body class="articleList">
	<#include "./header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="recommendArticle">
				<div class="top">推荐文章</div>
				<div class="middle clearfix">
					<ul>
						<#list (recommendArticleList)! as list>
							<li>
								<#if (list.title?length < 15)>
									<span class="icon">&nbsp;</span><a href="${base!}${list.htmlFilePath}" title="${list.title}">${list.title}</a>
								<#else>
									<span class="icon">&nbsp;</span><a href="${base!}${list.htmlFilePath}" title="${list.title}">${list.title[0..12]}...</a>
								</#if>
							</li>
							<#if list_index + 1 == 10>
								<#break/>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="hotArticle">
				<div class="top">热点文章</div>
				<div class="middle clearfix">
					<ul>
						<#list (hotArticleList)! as list>
							<li class="number${list_index + 1}">
								<#if (list.title?length < 15)>
									<span class="icon">&nbsp;</span><a href="${base!}${list.htmlFilePath}" title="${list.title}">${list.title}</a>
								<#else>
									<span class="icon">&nbsp;</span><a href="${base!}${list.htmlFilePath}" title="${list.title}">${list.title[0..12]}...</a>
								</#if>
							</li>
							<#if list_index + 1 == 10>
								<#break/>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base!}/" class="home"><span class="icon">&nbsp;</span>首页</a>>&nbsp;搜索 "${(pager.keyword)!}" 结果列表 [${(pager.list?size)!0}条]
					</div>
					<div class="articleSearch">
						<form id="articleSearchForm" action="${base!}/shop/article!search.action" method="get">
							<input type="text" name="pager.keyword" id="articleSearchKeyword" class="keyword" value="${(pager.keyword)!}" />
							<input type="submit" class="searchButton" value="" />
						</form>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="articleList">
				<div class="articleListTop"></div>
				<div class="articleListMiddle">
					<ul class="articleListDetail">
						<#list pager.list as list>
                			<li>
                            	<a href="${base!}${list.htmlFilePath}" class="title">
                            		<#if list.title?length lt 40>
										${list.title}
									<#else>
										${list.title[0..40]}...
									</#if>
								</a>
                                <span class="author">
                                	作者:<#if list.author == "">未知<#else>${list.author}</#if>
                                </span>
                                <span class="createDate">
                                	${list.createDate?string("yyyy-MM-dd")}
                                </span>
                                <div class="contentText">
									<#if list.contentText?length lt 100>
										${list.contentText}
									<#else>
										${list.contentText[0..100]}...
									</#if>
									<a href="${base!}${list.htmlFilePath}">[阅读全文]</a>
								</div>
      		        		</li>
                		</#list>
                		<#if (pager.list?size == 0)!>
                			<li class="noRecord">非常抱歉，没有找到相关文章！</li>
                		</#if>
					</ul>
					<div class="blank"></div>
         			<link href="${base!}/assets/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "./shop/pager.ftl" as p>
         			<@p.pager pager = pager baseUrl = "/shop/article!search.action" />
				</div>
				<div class="articleListBottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "./friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "./footer.ftl">
</body>
</html>