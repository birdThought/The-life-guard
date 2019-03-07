<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>短信记录</title>
<t:base type="jquery,layer,vue"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/userInfor.css">
	<link rel="stylesheet" type="text/css" href="/static/common/css/pageCss.css">
<!-- <script type = "text/javascript" src = "/static/com/lifeshs/member/js/smsRecord.js"></script> -->
<script type = "text/javascript" src = "/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<style>
	.service_table th{
		background-color: #C4C4C4
	}
	.page_input_enter{
		border-radius: 0px;
		color: black; 
		height: 30px; 
		width: 50px; 
		padding: 0px;
	}
</style>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
	<%@include file="/context/header.jsp"%>
	<div class="view-body wrap">
		<%@include file="/context/nav_left.jsp"%>
		<div class="right-wrap">
			<div class="title fl"><a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;短信记录</div>
			<div class="container fr" >
			<div class="SMSRecord">
				<h3>短信记录</h3>
			</div>
				<table id="smsTable" class="service_table" style="color: black; word-break:break-all;table-layout:fixed">
				    <tr>
				        <th>发送者</th>
						<th>接受者</th>
						<th style = "width: 40%;">内容</th>
						<th>状态</th>
						<th>创建时间</th>
				    </tr>
				    <tr v-if="results==''">
				        <td colspan="5">暂无记录</td>
				    </tr>
				    <tr v-for='r in results'>
				        <td v-cloak>{{r.userName}}</td>
				        <td v-cloak>{{r.receiveMobile}}</td>
				        <td v-cloak style = "width: 40%; line-height:20px;">{{r.content}}</td>
				        <td v-cloak>{{r.status == 0 ? "已发送" : "未发送"}}</td>
				        <td v-cloak>{{r.createDate | date("yyyy-MM-dd")}}</td>
				    </tr>
				</table>
				<div id="pageManager" class="page_Container">
				    <c:choose>
				    <c:when test="${pageCount==1}">
				        <span class="page page_action">1</span>
				    </c:when>
				    <c:when test="${pageCount>1}">
				        <span class="page page_action">1</span>
				        <c:choose>
				            <c:when test="${pageCount>5}">
				                <c:forEach begin="2" end="5" var="p">
				                    <span class="page">${p}</span>
				                </c:forEach>
				                <span class="page_dian">...</span>
				            </c:when>
				            <c:when test="${pageCount<=5}">
				                <c:forEach begin="2" end="${pageCount}" var="p">
				                    <span class="page">${p}</span>
				                </c:forEach>
				
				            </c:when>
				        </c:choose>
				        <span class="page page_next" id="btn_next">下一页</span>
				        <span style="margin-left:10px">共${pageCount}页，到第</span>
				        <input type="text" class="page_input" id="p_input"/>
				        <span>页</span>
				        <button class="page_input_enter" >确定</button>
				    </c:when>
				    </c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
 <script>
        window.onload = function () {
            menuSetting({
                parent: 5,
                child: 6
            })
        }

</script>
<script>
    Vue.filter('date', function (value, fmt) {
        return new Date(value).Format(fmt);
    });
    var v = new Vue({
        el: '#smsTable',
        data: {
            results: '${data}' == '' ? '' : JSON.parse('${data}'),
            		
        },
        filters: {
            reply: function (value) {
                if (value == undefined)
                    return "无"
                return value
            }
        }
    });
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: parseInt("${pageCount}"),
        pageChange: function (page) {
            $.ajax({
                type: 'POST',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                url: 'memberControl.do?getSmsRecordList',
                data: {nowPage: page},
                dataType: 'json',
                success: function (result) {
                    v.results = result.obj
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
        }
    });

</script>
</html>