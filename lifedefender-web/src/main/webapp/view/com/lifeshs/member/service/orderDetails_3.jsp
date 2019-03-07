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
    <link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/login/js/icheck.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/customRadio.js"></script>
    <script type="text/javascript" src="/static/com/lifeshs/healthService/myOrders.js"></script>
    <script type="text/javascript" src="/static/common/js/CookieUtil.js"></script>
    <script>
        window.onload =function () {
            menuSetting({
                parent : 5,
                child : 3
            });
            customRadio.init({
                name: 'pay'
            });
        }
    </script>
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
                            <a id="serveName" href="#">${data.serveName}</a>
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
                        <td><em>￥<fmt:formatNumber type="number" value="${data.price}" pattern="0.00" maxFractionDigits="2"/></em></td>
                        <td class="count">
                            <div class="item-amount ">
                                <span>${data.number}</span>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <ul class="partPay">
                <%--<li><input type="radio" name="pay" value="0"> <span><img
                        src="/static/images/bank.png" width=24 alt="银行支付">银行支付</span></li>--%>
                <li><input type="radio" name="pay" value="1"> <span><img
                        src="/static/images/alipay.png" width=24 alt="支付宝支付">支付宝支付</span></li>
                <%--<li><input type="radio" name="pay" value="2"> <span><img
                        src="/static/images/wechat.png" width=30 alt="微信支付">微信支付</span></li>--%>
            </ul>
            <div class="sum">
                <p>
                    <i>提交订单总额：</i>￥<em id="pay_cash"><fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/></em><span style="cursor: pointer;" onclick="orderControl.pay('${data.orderNumber}',${data.orderId},${data.chargeMode})">立即支付</span>
                </p>
            </div>
        </div>
            </div>
    </div>
</div>
</body>
</html>