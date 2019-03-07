<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>员工管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link rel="stylesheet" type="text/css"
          href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/pageCss.css">
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/DateUtils.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script type="text/javascript"
            src="/static/com/QYPart/js/employManage.js"></script>
    <script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script>
    <script>
        window.onload = function () {
            var orgType = parseInt(_cookie("orgType"));
            orgType==0 ? menuSetting({
                parent : 0,
                child : 1
            }) : menuSetting({
                parent : 0,
                child : 2
            });
            staffControl.isManager =  orgType==0;
        }
    </script>
</head>

<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body">
            <div class="right_title">
                <label class="action"> 员工管理 </label> <a
                    href="orgUserControl.do?addEmployPage" class="search-btn">添加员工</a>
            </div>
            <div style="margin-top:20px">
                <div style="margin-bottom:15px;position:relative">
                    <c:if test="${orgType!=0}">
                        <select id="typeSelect" class="service_select">
                            <option selected="selected" value="all">类型</option>
                            <option value="0">管理员</option>
                            <option value="1">服务师</option>
                        </select>
                    </c:if>
                    <select id="status" class="service_select">
                        <option selected="selected" value="all">状态</option>
                        <option value="0">正常</option>
                        <option value="4">离职</option>
                    </select> <span class="outdate"> <label>出生日期</label> <input
                        id="joinDate" type="text" value="" readonly/>
						</span><span class="outdate" style="margin-left:10px"> <label>姓名</label>
							<input id="name" type="text" style="padding:0 8px"/>
						</span>
                    <button onclick="staffControl.filter()" class="search-btn">搜索</button>
                </div>

                <table class="service_table" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>用户名</td>
                        <td>姓名</td>
                        <td>手机号</td>
                        <c:if test="${orgType!=0}">
                            <td>类型</td>
                        </c:if>
                        <td>出生时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${emList}" var="item" begin="0" end="15">
                        <tr>
                            <td data-id="${item.id}">${item.userName}</td>
                            <td>${item.realName}</td>
                            <td>${item.mobile}</td>
                            <c:if test="${orgType!=0}">
                                <td><c:choose>
                                    <c:when test="${item.userType==0}">
                                        管理员
                                    </c:when>
                                    <c:when test="${item.userType==1}">
                                        服务师
                                    </c:when>
                                    <c:when test="${item.userType==2}">
                                        管理员&服务师
                                    </c:when>
                                </c:choose></td>
                            </c:if>
                            <td><fmt:formatDate value="${item.birthday}"
                                                pattern="yyyy-MM-dd"/></td>
                            <td><c:choose>
                                <c:when test="${item.status==0}">
                                    <span class="jobStatus">正常</span>
                                </c:when>
                                <c:when test="${item.status==4}">
                                    <span style="color:#6c6c6c">离职</span>
                                </c:when>
                            </c:choose></td>
                            <td><a onclick="staffControl.editClick(this,${item.id})" style="cursor: pointer;">编辑</a>
                                <a
                                        onclick="staffControl.showControl(this)"
                                        style="margin-left:10px;cursor:pointer">操作</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div id="allCount"
                     style="text-align:right;padding-right:15px;font-size:15px;height:50px;line-height:50px">
                    员工总数:${employCount}</div>
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
                            <button class="page_input_enter">确定</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jeDate({
        dateCell: "#joinDate",
        format: "YYYY-MM-DD",
        minDate: "1900-01-01",
        maxDate: jeDate.now(0),
        startMin: "1900-01-01",
        startMax: jeDate.now(0),
        zindex: 999,
        choosefun: function (elem, val) {
            //val为获取到的时间值
        }
    });
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: parseInt("${pageCount}"),
        pageChange: function (page) {
            staffControl.findEmploys(page);
        }
    });
</script>
</body>
</html>