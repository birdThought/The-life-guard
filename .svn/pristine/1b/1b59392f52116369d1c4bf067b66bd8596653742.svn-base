<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>健康档案</title>
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/page.css">
    <link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">
                <a href="/login" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a
                    href="#" class="top_cite">健康档案</a>&nbsp;&nbsp;>&nbsp;&nbsp;个人病历
            </div>
            <div class="container fr">
                <ul class="caseTitle">
                    <li><span>个人病历</span></li>
                    <li class="addMedicalCase item-fr"><span>添加病历</span></li>
                </ul>
                <ul class="files">
                    <c:if test="${empty p.data}">
                        <h3 style="text-align:center;">暂无记录</h3>
                    </c:if>
                    <c:forEach items="${p.data}" var="d">
                        <li class='panel' id="${d.id}">
                            <dl>
                                <dt>${d.title}</dt>
                                <dd><span style="margin-right:15px;">${d.hospital}</span>${d.departmentName}</dd>
                                <dd>${d.basicCondition}</dd>
                                <dd>
                                    <small><fmt:formatDate value="${d.visitingDate}" pattern="yyyy-MM-dd"/> 就诊</small>
                                </dd>
                            </dl>
                            <div>
                                <c:if test="${!empty d.photoPath}"><img src="${d.photoPath}"/></c:if>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div id="recordPage" class="page_Container">
                    <%-- <c:choose>
                        <c:when test="${p.totalPage==1}">
                            <!-- <span class="page page_action">1</span> -->
                        </c:when>
                        <c:when test="${p.totalPage>1}">
                            <span class="page page_action">1</span>
                            <c:choose>
                                <c:when test="${p.totalPage>5}">
                                    <c:forEach begin="2" end="5" var="pp">
                                        <span class="page">${pp}</span>
                                    </c:forEach>
                                    <span class="page_dian">...</span>
                                </c:when>
                                <c:when test="${p.totalPage<=5}">
                                    <c:forEach begin="2" end="${p.totalPage}" var="pp">
                                        <span class="page">${pp}</span>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                            <span id="next_rec" class="page page_next">下一页</span>
                            <span style="margin-left: 10px">共${p.totalPage}页，到第</span>
                            <input type="text" class="page_input" id="p_input" />
                            <span>页</span>
                            <button class="page_input_enter">确定</button>
                        </c:when>
                    </c:choose> --%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/healthFile.js"></script>

<script>
    $(function () {
        menuSetting({
            parent: 2,
            child: 0
        });
        <c:if test="${!empty p.data}">
        var recordPage = new PageUtil();
        recordPage.getPageControl().init({
            container: 'recordPage',
            preBtn: 'pre_rec',
            nextBtn: 'next_rec',
            pageChange: function (page) {
                healthFileControl.getDatas(page);
            }
        });
        recordPage.getPageControl().totalPage = '${p.totalPage}';
        recordPage.getPageControl().selectPage(1, true);
        </c:if>
    });

</script>
</html>