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
    <title>我的服务</title>
    <script>
        window.onload=function () {
            menuSetting({
                parent : 3,
                child : 1
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
            <div class="myService">
                <h3>我的服务</h3>
            </div>
            <div class="item-service">
                <table class="bought-table">
                    <tr class="table-title">
                        <td colspan="5" style="text-align: center;"><span>服务信息</span>
                        </td>
                        <td style="text-align: center"><span>类型</span></td>
                        <td style="text-align: center"><span>剩余服务</span></td>
                        <td style="text-align: center"><span>操作</span></td>
                    </tr>
                    <c:if test="${empty data}">
                        <tr>
                            <td colspan="8"><div style="text-align: center;font-size: 18px;padding: 10px 0">您还没订购任何服务</div></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${data}" var="item">
                        <tr class="table-content">
                            <td colspan="5" class="contentBg">
                                <div style="float: left;">
                                    <a href="serviceControl.do?myServiceDetail&orgServeId=${item.orgServeId}"> <img src="${item.logo}" alt=""
                                                      width="80">
                                    </a>
                                </div>
                                <div style="margin-left: 100px;">
                                    <a href="serviceControl.do?myServiceDetail&orgServeId=${item.orgServeId}" class="service-center">
                                        <dl>
                                            <dt>${item.orgName}</dt>
                                            <dd>
                                                <label>主要服务：</label> <span>${item.classify}...</span>
                                            </dd>
                                        </dl>
                                    </a>
                                </div>
                            </td>
                            <td>
                                <p>
                                    <a href="serviceControl.do?myServiceDetail&orgServeId=${item.orgServeId}">${item.serveName}</a>
                                </p>

                            </td>
                            <td><em>${item.remaining}</em></td>
                            <td>
                                <p>
                                    <a href="serviceControl.do?myServiceDetail&orgServeId=${item.orgServeId}">咨询</a>
                                </p>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
            </div>
    </div>
</div>
</body>
</html>