<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>帮助中心</title>
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
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/mainHeader.css">
    <link rel="stylesheet" href="/static/common/css/pageCss.css">
    <link rel="stylesheet" type="text/css" href="/static/css/portal/help.css">
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>

    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script type="text/javascript" src="/static/officialwebsite/index/js/news_index.js"></script>
    <style>
        .question_ul{
            padding:15px 25px;font-size:15px;list-style: disc;color:#ccc;min-height: 320px;line-height: 28px
        }
        .question_ul li a:hover{
            text-decoration: underline;
        }
        .search .import input{
            border:3px solid #3598dc
        }
        .search .import div span{
            background-color:#3598dc
        }
        .search h3{
            border-left:3px solid #3598dc
        }
    </style>
    <c:set var="totalPage" value="${data.attr.informations.totalPage}"></c:set>
    <c:set var="nowPage" value="${data.attr.informations.nowPage}"></c:set>
    <script>
        $(function () {
            menuSetting({
                home:true
            })
        })
    </script>
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="main hwrap">
                <div class="search">
                    <h3>帮助中心</h3>
                    <div class="import">
                        <div style="overflow: hidden;margin-bottom: 10px">
                            <input type="text" name="" value="" id="searchCondition" style="padding: 0 8px">
                            <span id="search" style="color:#fff;text-align: center;padding-top: 7px;cursor: pointer;">搜索</span>
                        </div>
                        <p style="display: none;">
                            <label>热键关键词：</label>
                            <span>
		 					<a href="#">智能设备</a>
	        				<a href="#">健康服务</a>
	        				<a href="#">找回密码</a>
	        				<a href="#">生命守护</a>
		 				</span>
                        </p>
                    </div>
                </div>
                <div class="main_content">
                    <ul class="main_content_nav">
                        <c:forEach items="${data.attr.columns}" var="item">
                            <li data-id="${item.id}"><a href="orgControl.do?orgHelp&f=${item.id}">${item.name}</a></li>
                        </c:forEach>
                    </ul>
                    <div style="display: inline-block;width: 75%;min-height: 320px;float: left">
                        <label id="question" style="color:#3598dc;font-size:16px"></label>
                        <c:if test="${empty data.attr.informations.dataObject}">
                            <div style="font-size: 17px;padding:15px 25px;">暂无结果</div>
                        </c:if>
                        <ul class="question_ul">
                            <c:forEach items="${data.attr.informations.dataObject}" var="item">
                                <li><a href="javascript:orgHelp.lookNews(${item.id})">${item.title}</a></li>
                            </c:forEach>
                        </ul>
                        <div id="pageManager" class="page_Container" style="float: left">
                            <c:choose>
                                <c:when test="${totalPage==1}">
                                    <span class="page page_action">1</span>
                                </c:when>
                                <c:when test="${totalPage>1}">
                                    <span class="page page_action">1</span>
                                    <c:choose>
                                        <c:when test="${totalPage>5}">
                                            <c:forEach begin="2" end="5" var="p">
                                                <span class="page">${p}</span>
                                            </c:forEach>
                                            <span class="page_dian">...</span>
                                        </c:when>
                                        <c:when test="${totalPage<=5}">
                                            <c:forEach begin="2" end="${totalPage}" var="p">
                                                <span class="page">${p}</span>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                    <span class="page page_next" id="btn_next">下一页</span>
                                    <span style="margin-left:10px">共${totalPage}页，到第</span>
                                    <input type="text" class="page_input" id="p_input"/>
                                    <span>页</span>
                                    <button class="page_input_enter">确定</button>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <%--<script>
                    $("#clickChange >li:gt(0)").css("display","none");
                    $(".main_content_nav li").click(function(event) {
                        $(this).addClass('class_name').siblings().removeClass('class_name');
                        $("#clickChange >li").hide().eq($(this).index()).show();
                    });
                </script>--%>
            </div>
        </div>
    </div>
</div>
<script>
    orgHelp.init({
        f: '${f}',
        search: '${search}'
    })
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: ${totalPage},
        initPage: '${nowPage}',
        pageChange: function (page) {
            orgHelp.nextPage(page)
        }
    });
</script>
</body>
</html>
