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
    <title>订单详情</title>
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
                <h3>订单详情</h3>
            </div>
            <c:choose>
                <c:when test="${empty data}">
                    <div style="text-align: center;font-size: 18px;padding-top: 100px">
                        该订单不存在或已取消
                    </div>
                </c:when>
                <c:otherwise>
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
                        <div class="current-pay-state">
                            <div class="stateBtn">
                                <div class="alignLeft">
                                    <label>当前状态：</label>
                                    <c:choose>
                                        <c:when test="${data.status==1}">
                                            <strong>待支付</strong> <a href="javascript:orderControl.goToPay('${data.orderId}','${data.number}')"><span>立即支付</span></a>
                                        </c:when>
                                        <c:when test="${data.status==2}">
                                            <strong>付款失败</strong>
                                        </c:when>
                                        <c:when test="${data.status==3}">
                                            <strong>有效</strong>
                                        </c:when>
                                        <c:when test="${data.status==4}">
                                            <strong>已完成</strong>
                                        </c:when>
                                        <c:when test="${data.status==5}">
                                            <strong>退款失效</strong>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <c:choose>
                                    <c:when test="${data.status==1}">
                                        <div class="alignRight" onclick="orderControl.delOrder('${data.orderId}',0)" style="cursor:pointer;">取消订单</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alignRight" onclick="orderControl.delOrder('${data.orderId}',1)" style="cursor:pointer;">删除订单</div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <p>${data.orgName}</p>
                            <p>
                                <strong>主要服务：</strong>
                                <small><c:choose>
                                    <c:when test="${fn:length(data.about) > 27}">
                                        <c:out value="${fn:substring(data.about, 0, 27)}..."/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${data.about}"/>
                                    </c:otherwise>
                                </c:choose></small>
                            </p>
                            <dl>
                                <dt>服务名称：${data.serveName}</dt>
                                <c:choose>
                                    <c:when test="${data.status==1}">
                                        <dd>您的订单还未支付，如需正常使用服务，请前去支付</dd>
                                    </c:when>
                                    <c:when test="${data.status==2}">
                                        <dd>您的订单付款失败</dd>
                                    </c:when>
                                    <c:when test="${data.status==3}">
                                        <dd>您的订单有效</dd>
                                    </c:when>
                                    <c:when test="${data.status==4}">
                                        <dd>您的订单已完成</dd>
                                    </c:when>
                                    <c:when test="${data.status==5}">
                                        <dd>您的订单退款失效</dd>
                                    </c:when>
                                    <c:when test="${data.status==6}">
                                        <dd>您的订单已取消</dd>
                                    </c:when>
                                </c:choose>


                            </dl>
                        </div>
                        <div class="orderInfor">
                            <h3>订单信息</h3>
                            <ul>
                                <li><label>订单编号：</label>${data.orderNumber}</li>
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
                                                        <c:when test="${fn:length(data.about) > 27}">
                                                            <c:out value="${fn:substring(data.about, 0, 27)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${data.about}"/>
                                                        </c:otherwise>
                                                    </c:choose></span>
                                                    </dd>
                                                </dl>
                                            </a>
                                        </div>
                                    </td>
                                    <td><p>
                                        <a href="#">${data.serveName}</a>
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
                                            <c:when test="${data.chargeMode==0||data.status!=1}">
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
                                <li>优惠券：<strong style="margin-left: 106px;">￥0.00</strong></li>
                                <li>实际总额：<em id="realAllMoney">￥<fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/></em></li>
                            </ul>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
            </div>
    </div>
</div>
<script>
    window.onload =function () {
        menuSetting({
            parent : 5,
            child : 3
        });
    }
    orderControl.init();
</script>
</body>
</html>