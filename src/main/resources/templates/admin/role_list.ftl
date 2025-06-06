<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>角色列表 - Powered By ${systemConfig.systemName}</title>
    <meta name="Author" content="SHOP++ Team"/>
    <meta name="Copyright" content="SHOP++"/>
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <#include "../include.ftl">
    <link href="${base!}/assets/admin/css/list.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base!}/assets/admin/js/list.js"></script>
</head>
<body class="list">
<div class="body">
    <div class="listBar">
        <h1><span class="icon">&nbsp;</span>角色管理&nbsp;<span
                    class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
    </div>
    <form id="listForm" action="role!list.action" method="post">
        <div class="operateBar">
            <input type="button" class="addButton" onclick="location.href='role!add.action'" value="添加角色"/>
            <label>查找:</label>
            <select name="pager.property">
                <option value="name" <#if pager.property! == "name">selected="selected" </#if>>角色名称</option>
            </select>
            <label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}"/></label><input type="button"
                                                                                                                        id="searchButton"
                                                                                                                        class="searchButton"
                                                                                                                        value=""/>
            <label>每页显示:</label>
            <select name="pager.pageSize" id="pageSize">
                <option value="10" <#if pager.pageSize == 10>selected="selected" </#if>> 10</option>
                <option value="20" <#if pager.pageSize == 20>selected="selected" </#if>> 20</option>
                <option value="50" <#if pager.pageSize == 50>selected="selected" </#if>> 50</option>
                <option value="100" <#if pager.pageSize == 100>selected="selected" </#if>> 100</option>
            </select>
        </div>
        <table class="listTable">
            <tr>
                <th class="check"><input type="checkbox" class="allCheck"/></th>
                <th><span class="sort" name="name">角色名称</span></th>
                <th><span class="sort" name="isSystem">系统内置</span></th>
                <th><span class="sort" name="description">描述</span></th>
                <th><span class="sort" name="createDate">创建日期</span></th>
                <th> 操作</th>
            </tr>
            <#list pager.list as list>
                <tr>
                    <td>
                        <input type="checkbox" <#if list.iSys??>disabled title="系统内置权限不允许删除!" <#else>name="ids" value="${list.id}"</#if> />
                    </td>
                    <td>${list.name}</td>
                    <td>
                        <#if list.iSys??>
                            <img src="${base!}/assets/admin/images/list_true_icon.gif"/>
                        <#else>
                            <img src="${base!}/assets/admin/images/list_false_icon.gif"/>
                        </#if>
                    </td>
                    <td>${list.description} &nbsp;</td>
                    <td>
                        <span title="${list.createTime?string("yyyy-MM-dd HH:mm:ss")}">${list.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                    </td>
                    <td><a href="role!edit.action?id=${list.id}" title="编辑">[编辑]</a></td>
                </tr>
            </#list>
        </table>
        <#if (pager.list?size > 0)>
            <div class="pagerBar">
                <input type="button" class="deleteButton" url="role!delete.action" value="删 除" disabled hidefocus="true"/>
                <#include "./pager.ftl" />
            </div>
        <#else>
            <div class="noRecord">
                没有找到任何记录!
            </div>
        </#if>
    </form>
</div>
</body>
</html>