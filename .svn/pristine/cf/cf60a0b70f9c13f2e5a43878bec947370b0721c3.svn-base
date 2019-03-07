<%@page import="com.lifeshs.entity.org.TServe" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>服务</title>
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
    <link href="/static/login/css/blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/com/QYPart/css/service.css">
    <script type="text/javascript" src="/static/login/js/icheck.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/customRadio.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/service.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/commonCheck.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>

    <script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var orgType = _cookie("orgType");
            menuSetting({
                parent: 0,//父级菜单的索引
                child: 0
            });
        }
    </script>
</head>

<body>
<%@include file="/context/QYHeader.jsp" %>
<%@include file="/view/com/QYPart/dialog/service_dialog.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body">
            <div class="right_title">
                <label class="action"> 定制服务 </label>
            </div>
            <c:forEach items="${serList}" var="item">
                <div class="service_order">
                    <div class="left_img">
                        <center>
                            <div>
                                <c:choose>
                                    <c:when test="${item.id==1}">
                                        <img src="/static/images/diagnose.png"/>
                                    </c:when>
                                    <c:when test="${item.id==2}">
                                        <div style="background:#fb8f1c">
                                            <img src="/static/images/treatment.png"/>
                                        </div>
                                    </c:when>
                                    <c:when test="${item.id==3}">
                                        <div style="background:#7ccc13">
                                            <img src="/static/images/diet.png"/>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </center>
                    </div>
                    <div class="service_order_right_content">
                        <div class="top_title">
                            <h2>${item.name}</h2>
                        </div>
                        <div class="content_body">
                            <label>平台分成：</label><span class="divide_percent">${item.profitShare}%</span>
                            <p class="info">${item.about}</p>
                            <div class="btn_group">
                                <script type="text/javascript">
                                    var s_id = ${item.id};
                                    var s_classify = '${item.classify}';
                                    serviceControl.serviceClassify[s_id] = s_classify;
                                    var array = new Array();
                                    var s = ('${item.hasOrder}').toString();
                                    array = s.split(",");
                                    if ($.inArray('${item.id}', array) != -1) {
                                        document
                                                .write('<button class="hasOrder">已开通</button><button class="priceOrder" onclick="serviceControl.showServiceDialog('
                                                        + 1
                                                        + ','
                                                        + ${item.id} +')">服务设置</button>');
                                    } else {
                                        document
                                                .write('<button class="open" onclick="serviceControl.showServiceDialog('
                                                        + 0
                                                        + ','
                                                        + ${item.id} +')">开通服务</button>');
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    serviceControl.serveChargeMode['${item.id}']='${item.chargeMode}'
                </script>
            </c:forEach>
            <div class="kaitong_explain">
                <div class="top_wrap">
                    <img src="/static/images/explain.png"/>开通说明
                </div>
                <ul>
                    <li><span></span>以上产品开通后均以扩展模块显现，有效期：永久有效。</li>
                    <li><span></span>开通后，如商户通过开通的扩展服务获得收益，平台将收取些许分成。</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
