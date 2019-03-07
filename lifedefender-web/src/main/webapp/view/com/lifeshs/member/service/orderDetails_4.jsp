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
    <script>
        window.onload = function () {
            menuSetting({
                parent: 5,
                child: 3
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
                <div class="current-pay-state stateAlign">
                    <div class="stateLeft">
                        <div class="stateBtn">
                            <div class="alignLeft">
                                <label>当前状态：</label>
                                <c:choose>
                                    <c:when test="${data.status==1}">
                                        您的订单还未支付，如需正常使用服务，请前去支付
                                    </c:when>
                                    <c:when test="${data.status==2}">
                                        您的订单付款失败
                                    </c:when>
                                    <c:when test="${data.status==3}">
                                        您的订单有效
                                    </c:when>
                                    <c:when test="${data.status==4}">
                                        <strong>已完成</strong>
                                        <label>再次购买</label>
                                    </c:when>
                                    <c:when test="${data.status==5}">
                                        您的订单退款失效
                                    </c:when>
                                    <c:when test="${data.status==6}">
                                        您的订单已取消
                                    </c:when>
                                </c:choose>
                            </div>
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
                            <dt>${data.serveName}</dt>
                            <dd>您的订单已完成咨询</dd>
                        </dl>
                    </div>
                    <c:choose>
                        <c:when test="${data.chargeMode==1&&data.status==3}">
                            <div class="stateRight">
                                <p>
                                    <em>${data.timesRemaining}</em><br> 剩余次数
                                </p>
                                <a href="serviceControl.do?myServiceDetail&orgServeId=${data.orgServeId}"><span>立即咨询</span></a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${data.status==3}">
                                <div class="stateRight">
                                    <p>
                                        <em>${data.remain}</em><br> 剩余天数
                                    </p>
                                    <a href="serviceControl.do?myServiceDetail&orgServeId=${data.orgServeId}"><span>立即咨询</span></a>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
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
                <div class="priceSum">
                    <ul>
                        <li>商品总计：<strong style="margin-left: 105px;">￥<fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/></strong></li>
                        <li>优惠券：<strong style="margin-left: 106px;">￥0.00</strong></li>
                        <li>实际总额：<em>￥<fmt:formatNumber type="number" value="${data.price*data.number}" pattern="0.00" maxFractionDigits="2"/></em></li>
                    </ul>
                </div>
                <div class="focusService">
                    <h3>服务须知</h3>
                    <ul>
                        <li>支付完成后，可任意选择服务机构的服务师进行咨询</li>
                        <li>可通过文字和图片的形式与服务师沟通您的问题,也可与离线状态的服务师发问，在服务师上线后将回复您的问题</li>
                        <li>服务开始后将无法退款，如您遇到任何问题，可联系平台在线客服或拨打客服电话进行反馈</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>