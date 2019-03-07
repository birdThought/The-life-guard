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
    <title>服务机构</title>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/serviceDoctor.css">
    <t:base type="jquery,layer"></t:base>
    <script>
        window.onload = function () {
            menuSetting({
                parent: 3,
                child: 0
            });
        }
    </script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">
                <a href="/login" class="top_cite">主页</a> &nbsp;&nbsp;>&nbsp;&nbsp; <a
                    href="serviceControl.do?healthConsultPage" class="top_cite">服务</a> &nbsp;&nbsp;>&nbsp;&nbsp;服务机构
            </div>
            <div class="container fr">
                <ul class="serviceDoctor organize">
                    <li><img src="${org.logo}" alt=""></li>
                    <li>
                        <p>${org.orgName}</p>
                        <dl>
                            <dt class="mainServe">
                                <label>主要服务：</label>${org.classify}
                            </dt>
                            <dd>
                                <label>机构简介：</label>${org.about}<%--...<a>更多</a>--%>
                            </dd>
                        </dl>
                        <span class="doctor-bg">预约人数：${org.memberCount}</span> <span class="doctor-bg">服务指数：90%</span>
                    </li>
                </ul>
                <div class="service-title organize-introduce">
                    <div>
                        <h3>服务机构详细介绍</h3>
                       <%-- <span class="btn">展开</span>--%>
                    </div>
                    <div class="introduce">${org.detail}</div>
                </div>
                <div class="service-title organize-introduce">
                    <div>
                        <h3>服务</h3>
                        <span>服务费由机构自行设定，平台不收取任何额外费用</span>
                    </div>
                    <ul class="service-list">
                        <c:forEach items="${org.serve}" var="s">
                            <li>
                                <img id="${s.serveId}" src="${s.image}" alt="">
                                <p>${s.name}</p>
                                <p><a href="serviceControl.do?buyServicePage&orgServeId=${s.orgServeId}" class="org-buy">购买服务</a></p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>