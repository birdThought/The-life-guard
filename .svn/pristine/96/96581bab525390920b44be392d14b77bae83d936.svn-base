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
    <link rel="stylesheet" href="/static/css/orders.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/pageCss.css">
    <title>我的订单</title>
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script type="text/javascript" src="/static/com/lifeshs/healthService/myOrders.js"></script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
        <div class="container fr" style="margin-top: 0">
            <div class="myOrders">
                <h3>我的订单</h3>
            </div>
            <ul id="order_nav_choose" class="order-nav">
                <li class="allOrder">全部订单</li>
                <li data-status="1">未完成</li>
                <li data-status="4">已完成</li>
            </ul>
            <ul class="order-details">
                <li style="width: 200px;">
                    <h3>近三个月的订单</h3>
                </li>
                <li style="width: 200px; text-align: center;">
                    <h3>订单详情</h3>
                </li>
                <li>
                    <h3>类型</h3>
                </li>
                <li>
                    <h3>单价</h3>
                </li>
                <li>
                    <h3>数量</h3>
                </li>
                <li>
                    <h3>交易状态</h3>
                </li>
                <li>
                    <h3>操作</h3>
                </li>
            </ul>
            <c:choose>
                <c:when test="${empty data.data}">
                    <div style="padding-top: 100px;font-size: 18px;text-align: center">
                        没有相关数据
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="order-container">
                        <div class="inner-order-container">
                            <c:forEach items="${data.data}" var="item">
                                <table class="bought-table">
                                    <tbody>
                                    <tr class="table-title">
                                        <td colspan="6" style="text-align: left;"><span>订单号：${item.orderNumber}</span>
                                            <span>下单时间：<fmt:formatDate value="${item.createDate}"
                                                                       pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
                                        <td style="text-align: center"><img
                                                src="/static/images/delete.png" alt="" style="cursor: pointer;display: none;" onclick="orderControl.delOrder(${item.id})"></td>
                                    </tr>
                                    <tr class="table-content">
                                        <td colspan="2" class="contentBg">
                                            <div style="float: left;">
                                                <a href="memberControl.do?orderDetailPage&id=${item.orderId}"> <img src="${item.logo}" alt=""
                                                                  width="80">
                                                </a>
                                            </div>
                                            <div style="margin-left: 100px;">
                                                <a href="memberControl.do?orderDetailPage&id=${item.orderId}" class="service-center">
                                                    <dl>
                                                        <dt>${item.orgName}</dt>
                                                        <dd>
                                                            <label>主要服务：</label> <span>
														<c:choose>
                                                            <c:when test="${fn:length(item.about) > 27}">
                                                                <c:out value="${fn:substring(item.about, 0, 27)}..."/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${item.about}"/>
                                                            </c:otherwise>
                                                        </c:choose>
													</span>
                                                        </dd>
                                                    </dl>
                                                </a>
                                            </div>
                                        </td>
                                        <td><span>${item.serveName}</span></td>
                                        <td><strong>￥${item.price}</strong></td>
                                        <td><em>${item.number}</em></td>
                                        <td>
                                            <p>
                                                <a>
                                                    <c:choose>
                                                        <c:when test="${item.status==1}">
                                                            待付款
                                                        </c:when>
                                                        <c:when test="${item.status==2}">
                                                            付款失败
                                                        </c:when>
                                                        <c:when test="${item.status==3}">
                                                            有效
                                                        </c:when>
                                                        <c:when test="${item.status==4}">
                                                            已完成
                                                        </c:when>
                                                        <c:when test="${item.status==5}">
                                                            退款失效
                                                        </c:when>
                                                    </c:choose>
                                                </a>
                                            </p>
                                            <p>
                                                <a href="memberControl.do?orderDetailPage&id=${item.orderId}">订单详情</a>
                                            </p>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status==1}">
                                                    <p>
                                                        <a href="serviceControl.do?orderPayPage&id=${item.orderId}">立即支付</a>
                                                    </p>
                                                    <p>
                                                        <a href="javascript:orderControl.delOrder('${item.orderId}',0)">取消订单</a>
                                                    </p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>
                                                        <a href="javascript:orderControl.delOrder('${item.orderId}',1)">删除订单</a>
                                                    </p>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </c:forEach>
                        </div>
                        <%@include file="/context/page.jsp" %>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
            </div>
    </div>
</div>
<script>
    window.onload=function () {
        menuSetting({
            parent : 5,
            child : 3
        });
    }
    orderControl.bindEvent();
    var status = '${status}';
    status != '' ? $("#order_nav_choose li[data-status=\"" + status + "\"]").addClass("order-action") : $("#order_nav_choose li:first-child").addClass("order-action");
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        initPage:'${data.nowPage}',
        totalPage: '${data.totalPage}',
        pageChange: function (page) {
            var status=$("#order_nav_choose li.order-action").attr("data-status");
            window.location.href = 'memberControl.do?myOrders&cur=' + page+(status==undefined?'':'&status='+status);
        }
    });

</script>
</body>
</html>