<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>帮助中心</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/mainHeader.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/help.css">
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
    <script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
    <style>
        .question_ul{
            padding:15px 25px;font-size:15px;list-style: disc;color:#ccc;min-height: 320px;line-height: 28px
        }
        .question_ul li a:hover{
            text-decoration: underline;
        }
        .hwrap{
            width:900px
        }
        .main_content_nav{
            margin-right: 0;
        }
        .search .import input{
            border:3px solid #3598dc
        }
        .search .import div span{
            background-color:#3598dc
        }
        .search h3{
            border-left:3px solid #3598dc
        }
    </style>
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
    <script>
        $(function () {
            menuSetting({
                home:true
            })
        })
    </script>
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="main hwrap">
                <div class="search">
                    <h3>帮助中心</h3>
                    <div class="import">
                        <div style="overflow: hidden;margin-bottom: 10px">
                            <input type="text" name="" value="" id="searchCondition" style="padding: 0 8px">
                            <span id="search" style="color:#fff;text-align: center;padding-top: 7px;cursor: pointer;">搜索</span>
                        </div>
                        <p style="display: none;">
                            <label>热键关键词：</label>
                            <span>
		 					<a href="#">智能设备</a>
	        				<a href="#">健康服务</a>
	        				<a href="#">找回密码</a>
	        				<a href="#">生命守护</a>
		 				</span>
                        </p>
                    </div>
                </div>
                <div class="main_content">
                    <ul class="main_content_nav">
                        <c:forEach items="${columns}" var="item">
                            <c:choose>
                                <c:when test="${item.name eq columnName}">
                                    <li data-id="${item.id}"><a style="color:#3598dc" href="orgControl.do?orgHelp&f=${item.id}">${item.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li data-id="${item.id}"><a href="orgControl.do?orgHelp&f=${item.id}">${item.name}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <div style="display: inline-block;width: 75%;min-height: 320px;float: right">
                        <div style="font-size:16px;color: #6c6c6c;margin-bottom: 15px"><span>${columnName}</span> > <span style="color:#3598dc">${info.title}</span></div>
                        <div>${info.content}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    orgHelp.init({
        f: '',
        search: ''
    })
</script>
</body>
</html>
