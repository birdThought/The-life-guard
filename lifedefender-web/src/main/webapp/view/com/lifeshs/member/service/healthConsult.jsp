<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="keywords">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/healthDepart.css">
    <link rel="stylesheet" href="/static/common/css/pageCss.css">
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script type="text/javascript" src="/static/common/js/CookieUtil.js"></script>
    <script src="/static/com/lifeshs/healthService/healthConsult.js" type="text/javascript"></script>
    <title>健康问诊</title>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
            <div class="container fr" style="margin-top: 0">
                <div class="top-contain">
                    <h3>健康问诊</h3>
                    <div style="float: right;">
                        <input id="searchInp" type="text" class="search-inp" placeholder="输入服务机构名称、服务分类">
                        <button class="search-btn" onclick="searchControl.search()">搜索</button>
                    </div>
                </div>
                <div class="filter-contain">
                    <div id="search_action_group" class="search-filter-group" style="display: none;">
                        <ul class="search-filter">
                        </ul>
                        <span class="result-text">找到<span>${count}</span>个相关项目
						</span>
                    </div>
                    <div style="padding: 0 10px">
                        <div class="search-divide-group">
                            <label>开通服务</label>
                            <div class="divide-contain">
                                <ul id="service_filter" class="divide-list">
                                    <c:forEach items="${serviceTag}" var="item">
                                        <li>${item.name}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="search-divide-group" style="border-bottom: none;padding-bottom: 0;">
                            <label>全部分类</label>
                            <div class="divide-contain">
                                <ul id="classify_filter" class="divide-list">
                                    <c:forEach items="${classifyTag}" var="item">
                                        <c:if test="${item.name != '全部分类'}">
                                            <li>${item.name}</li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="search-divide-group"
                             style="border-bottom: none; padding-bottom: 0;display: none;">
                            <label>排序方式</label>
                            <div class="divide-contain">
                                <ul id="sort_filter" class="divide-list">
                                    <li>智能排序</li>
                                    <li>评价最高</li>
                                    <li>回复速度优先</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <ul>
                        <c:if test="${empty itemList}">
                            <div style="text-align: center;font-size: 18px;">没有相关数据</div>
                        </c:if>
                        <c:forEach items="${itemList}" var="item">
                            <li>
                                <a href="serviceControl.do?serviceOrganize&orgId=${item.orgId}" style="display: block">
                                    <div class="result-item-contain">
                                        <img src="${item.logo}" width="160" height="120">
                                        <div class="result-message-contain">
                                            <h3 style="">${item.orgName}</h3>
                                            <p>主要服务：${item.serviceList}</p>
                                            <p>地址：${item.street}</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <c:if test="${!empty itemList}">
                    <%@include file="/context/page.jsp" %>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    window.onload = function () {
        menuSetting({
            parent: 3,
            child: 0
        });
    }
    searchControl.init({
        fil1ter: {
            service: '${service}',
            classify: '${classify}',
            sort: '${sort}'
        }, pageCount:${totalPage}
    });
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: ${totalPage},
        initPage:${initPage},
        pageChange: function (page) {
            searchControl.pageChange(page);
        }
    });
</script>
</html>