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
    <title>生成订单</title>
    <link rel="stylesheet" href="/static/css/orderDetails.css">
    <script type="text/javascript" src="/static/com/lifeshs/healthService/myOrders.js"></script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
            <div class="container fr" style="margin-top: 0">
                <div class="title" style="width: 100%">
                    <h3>订单信息</h3>
                </div>
                <div>
                    <div class="liucheng">
                        <div class="line"></div>
                        <div class="step">
                            <p>提交订单</p>
                            <em>1</em>
                        </div>
                        <div class="step">
                            <p>支付订单</p>
                            <em>2</em>
                        </div>
                        <div class="step">
                            <p>使用订单</p>
                            <em>3</em>
                        </div>
                        <div class="step">
                            <p>完成订单</p>
                            <em>4</em>
                        </div>
                    </div>
                    <div class="orderInfor">
                        <h3>订单信息</h3>
                        <ul>
                            <li><label>订单编号：</label><span>${data.orderNumber}</span></li>
                            <li><label>下单时间：</label><fmt:formatDate value="${data.createDate}"
                                                                    pattern="yyyy-MM-dd HH:mm:ss"/></li>
                            <li><label>服务商家：</label>${data.orgName}</li>
                            <li><label>支付方式：</label>在线支付</li>
                        </ul>
                    </div>
                    <div class="orderInfor serviceInfor">
                        <h3>服务信息</h3>
                        <table class="bought-table">
                            <tbody>
                            <tr>
                                <td>订单详情</td>
                                <td>类型</td>
                                <td>规格</td>
                                <td>单价</td>
                                <td>数量</td>
                            </tr>
                            <tr class="table-content">
                                <td class="contentBg">
                                    <div style="float: left;">
                                        <a href="#"> <img src="${data.logo}" alt=""
                                                          width="80">
                                        </a>
                                    </div>
                                    <div style="margin-left: 100px;">
                                        <a href="#" class="service-center">
                                            <dl>
                                                <dt>${data.orgName}</dt>
                                                <dd>
                                                    <label>简介：</label> <span><c:choose>
                                                    <c:when test="${fn:length(data.orgAbout) > 27}">
                                                        <c:out value="${fn:substring(data.orgAbout, 0, 27)}..."/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${data.orgAbout}"/>
                                                    </c:otherwise>
                                                </c:choose></span>
                                                </dd>
                                            </dl>
                                        </a>
                                    </div>
                                </td>
                                <td><p>
                                    <a href="#">${data.name}</a>
                                </p></td>
                                <td>
                                    <div>
                                            <span><c:choose>
                                                <c:when test="${data.chargeMode==0}">
                                                    免费
                                                </c:when>
                                                <c:when test="${data.chargeMode==1}">
                                                    按次
                                                </c:when>
                                                <c:when test="${data.chargeMode==2}">
                                                    按月
                                                </c:when>
                                                <c:when test="${data.chargeMode==3}">
                                                    按年
                                                </c:when>
                                            </c:choose></span>
                                    </div>
                                </td>
                                <td><em id="shop_price">￥<fmt:formatNumber type="number" value="${data.price}" pattern="0.00" maxFractionDigits="2"/></em></td>
                                <td class="count">
                                    <c:choose>
                                        <c:when test="${data.chargeMode==0}">
                                            ${data.number}
                                        </c:when>
                                        <c:otherwise>
                                            <div class="item-amount ">
                                                <a href="javascript:orderControl.addOrDecrease(0)" class="decrease">-</a> <input id="countNumber" type="text" maxlength="3"
                                                                                                                                 value="${data.number}" class="text" autocomplete="off"> <a
                                                    href="javascript:orderControl.addOrDecrease(1)" class="add">+</a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="priceSum">
                        <ul>
                            <li>商品总计：<strong id="allMoney" style="margin-left: 105px;">￥<fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/>
                            </strong></li>
                            <li>优惠券：<strong style="margin-left: 105px;">￥0.00</strong></li>
                            <li>实际总额：<em id="realAllMoney">￥<fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/></em></li>
                        </ul>
                    </div>
                    <div class="commit-order">
                        <button onclick="orderControl.commitOrder(${data.id},'${data.orderNumber}',${data.chargeMode},'${data.price}','${data.name}','${data.groupId}')">提交订单</button>
                    </div>
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
    orderControl.init();
</script>
</body>
</html>