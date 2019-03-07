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
    <title>选择服务师</title>
    <link rel="stylesheet" href="/static/css/choseService.css">
    <script type="text/javascript">
        jQuery(function () {
            menuSetting({
                parent: 3,
                child: 1
            });
            var $uls = jQuery("ul.teams");
            jQuery.each($uls, function () {
                var isLeaveState = jQuery(this).find("button").hasClass("leaveState");
                var $span = jQuery(this).find("li").eq(1).find("p>span");
                var $small = null;
                if (isLeaveState) {
                    $small = jQuery("<small class='leaveState'>离线</small>");
                    //jQuery(this).find("button").attr("disabled", "disabled");
                } else {
                    $small = jQuery("<small>在线</small>");
                }
                $span.append($small);
            });
        });
    </script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap" style="padding-top: 30px">
            <div class="container fr" style="margin-top: 0">
                <div class="chose-title">
                    <h3>选择服务师</h3>
                </div>
                <ul class="community">
                    <li style="width: 50%">
                        <div>
                            <img src="${serve.logo}" alt="" width="90" height="64">
                        </div>
                        <a href="#" class="service-center">
                            <dl>
                                <dt>${serve.orgName}</dt>
                                <dd>
                                    <label>主要服务：</label> <span>${serve.classify}</span>
                                </dd>
                            </dl>
                        </a>
                    </li>
                    <li style="width: 25%; text-align: center;" class="setCenter">
                        <p>${serve.serveName}</p>
                    </li>
                    <li style="width: 20%; text-align: center;" class="setCenter">
                        <p>
                            剩余服务：<em>${serve.remaining}</em>
                        </p>
                    </li>
                </ul>
                <div class="service-team">
                    <div class="teams-title">
                        <h3>服务师团队</h3>
                    </div>
                    <c:if test="${empty serve.serveUsers}">
                        <div style="text-align: center;padding: 15px">
                            该服务机构下还没有服务师团队
                        </div>
                    </c:if>
                    <c:forEach items="${serve.serveUsers}" var="u">
                        <ul class="teams">
                            <li><img src="${u.photo}" height="74"/></li>
                            <li style="width: 70%">
                                <p><span>${u.realName}</span></p>
                                <dl>
                                    <dd>简介：${u.about}</dd>
                                </dl>
                            </li>
                            <li>
                                <button onclick="askServer(${serve.orgServeId},${u.userId})" class="<c:if test="${u.isOnline == 0}">leaveState</c:if>">咨询</button>
                            </li>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function askServer(orgServeId, id) {
        window.open("serviceControl.do?lookMessage&serverId=" + id + "&orgServeId=" + orgServeId, '_blank');
    }
</script>
</body>
</html>