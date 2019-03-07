<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生命守护</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keyword" content="">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/help.css">
    <t:base type="jquery,layer"></t:base>
    <script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
    <style type="text/css">
        html {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .class_name {
            font-size: 18px;
        }

        .page:hover {
            background-color: #48c858;
            color: #fff;
        }

        .page_action {
            background-color: #48c858;
            color: #fff;
        }

        .page_input {
            margin: 0 8px;
            width: 40px;
            border: 1px solid #ddd;
            outline: #48c858;
            padding: 3px 6px
        }

        .page_input_enter:hover {
            color: #48c858
        }
    </style>
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
</head>
<body>
<div class="container">
    <%@include file="/context/mainHeader_1.jsp" %>
    <div class="main help-wrap">
        <div class="search">
            <h3>帮助中心</h3>
            <%--<div class="import">--%>
                <%--<div style="overflow: hidden;margin-bottom: 10px">--%>
                    <%--<input type="text" name="" value="" id="searchCondition" style="padding: 0 8px">--%>
                    <%--<span id="search" style="color:#fff;text-align: center;padding-top: 7px;cursor: pointer;">搜索</span>--%>
                <%--</div>--%>
                <%--<p style="display: none;">--%>
                    <%--<label>热键关键词：</label>--%>
                    <%--<span>--%>
		 					<%--<a href="#">智能设备</a>--%>
	        				<%--<a href="#">健康服务</a>--%>
	        				<%--<a href="#">找回密码</a>--%>
	        				<%--<a href="#">生命守护</a>--%>
		 				<%--</span>--%>
                <%--</p>--%>
            <%--</div>--%>
        </div>
        <div class="main_content">
            <ul class="main_content_nav">
                <c:forEach items="${columns}" var="item">
                    <c:choose>
                        <c:when test="${item.name eq columnName}">
                            <li data-id="${item.id}"><a style="color:#48c858" href="informationControl.do?helpCenterIndex&f=${item.id}">${item.name}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li data-id="${item.id}"><a href="informationControl.do?helpCenterIndex&f=${item.id}">${item.name}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
            <div style="display: inline-block;width: 75%;min-height: 320px;float: left">
                <div style="font-size:16px;color: #6c6c6c;margin-bottom: 15px"><span>${columnName}</span> > <span style="color:#48c858">${info.title}</span></div>
                <div>${info.content}</div>
            </div>
        </div>
    </div>
    <%@include file="/context/mainFooter_1.jsp" %>
</div>
<script>
    helpControl.init({
        f: '',
        search: ''
    })
</script>
</body>
</html>