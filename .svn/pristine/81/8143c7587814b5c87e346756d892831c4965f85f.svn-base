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
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <title>服务详情</title>
    <link rel="stylesheet" href="/static/css/serviceDetails.css">
    <script type="text/javascript" src="/static/com/lifeshs/healthService/serviceDetails.js"></script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
            <div class="container fr" style="margin-top: 0">
                <ul class="healthCon">
                    <li><img src="${data.image}" alt=""></li>
                    <li><span>${data.name}</span>
                        <P>${data.about}</P>
                    </li>
                    <li>
                        <div>
                            <span>服务时间</span>
                            <p>09:00-17:00</p>
                        </div>
                    </li>
                </ul>
                <div class="servicePrice">
                    <h3>服务费用</h3>
                    <ul class="priceBtn">
                        <c:if test="${data.hasFree}">
                            <li data-mode="0"><span>免费</span></li>
                        </c:if>
                        <c:if test="${data.hasTime}">
                            <li data-mode="1">
                            <span>${data.timePrice}</span>元/次
                            </li>
                        </c:if>
                        <c:if test="${data.hasMonth}">
                            <li data-mode="2">
                            <span>${data.monthPrice}</span>元/月
                            </li>
                        </c:if>
                        <c:if test="${data.hasYear}">
                            <li data-mode="3">
                            <span>${data.yearPrice}</span>元/年
                            </li>
                        </c:if>
                    </ul>
                    <h3>数量</h3>
                    <div class="countBtn">
                        <div class="item-amount">
                            <a href="javascript:ServiceControl.addOrDecrease(0)" class="decrease">-</a> <input id="countNumber" type="text" value="1" maxlength="3"
                                                                                                               class="text" autocomplete="off"> <a href="javascript:ServiceControl.addOrDecrease(1)" class="add">+</a>
                        </div>
                        <button onclick="ServiceControl.buy(${data.id})">立即购买</button>
                    </div>
                </div>
                <div class="service-team">
                    <h3>服务师团队</h3>
                    <c:if test="${empty data.servers}">
                        <div style="text-align: center;font-size: 16px;padding-top: 30px">该机构服务还未给该服务分配服务师团队
                        </div>
                    </c:if>
                    <c:forEach items="${data.servers}" var="item">
                        <ul class="serviceDoctor">
                            <li><img src="${item.photo}" alt=""></li>
                            <li>
                                <p>
                                    <span>${item.realName}</span>
                                    <small>营养师</small>
                                </p>
                                <span class="doctor-bg">糖尿病</span> <span class="doctor-bg">高血压</span>
                                <dl>
                                    <dt>
                                        <label>简介：</label>${item.about}<a style="display: none">更多</a>
                                        <%-- <label>所属机构：</label>${item.orgName} --%>
                                    </dt>
                                    <%-- <dd>
                                        <label>简介：</label>${item.about}<a style="display: none">更多</a>
                                    </dd> --%>
                                </dl>
                            </li>
                            <li style="display: none;">
                                <div>
                                    <div>
                                        <img src="/static/images/star.png" alt="star"> <img
                                            src="/static/images/star.png" alt="star"> <img
                                            src="/static/images/star.png" alt="star"> <img
                                            src="/static/images/star.png" alt="star"> <img
                                            src="/static/images/star2.png" alt="star">
                                    </div>
                                    <p>
                                        问诊数：<span>2131</span>
                                    </p>
                                    <p>
                                        综合评分：<span>4.0</span>
                                    </p>
                                </div>
                            </li>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    window.onload = function () {
        menuSetting({
            parent: 3,
            child: 0
        });
    }
    ServiceControl.init();
</script>
</body>
</html>