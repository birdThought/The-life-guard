<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<div id="group_control_block">
    <!--群组-->
    <div class="qunzu">
        <shiro:hasPermission name="user:0 or user:2">
            <div class="btn_group">
                <button class="add" onclick="dialogUtils.showDialog(2)">添加群组</button>
                <button class="edit" onclick="dialogUtils.showDialog(2,1)">编辑群组</button>
            </div>
        </shiro:hasPermission>
        <div class="list">
            <div class="qunzu_text">群组</div>
            <ul class="group_list">
                <c:forEach items="${groupList}" var="item" varStatus="status">
                    <c:choose>
                        <c:when test="${status.first}">
                            <li data-id="${item.id}" onclick="groupControl.changeGroup(this,${item.id},${item.memberCount})"
                                class="action"><c:choose>
                                <c:when test="${fn:length(item.name) > 7}">
                                    <c:out value="${fn:substring(item.name, 0, 7)}..."/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${item.name}"/>
                                </c:otherwise>
                            </c:choose> (${item.memberCount})
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li data-id="${item.id}" onclick="groupControl.changeGroup(this,${item.id},${item.memberCount})"><c:choose>
                                <c:when test="${fn:length(item.name) > 7}">
                                    <c:out value="${fn:substring(item.name, 0, 7)}..."/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${item.name}"/>
                                </c:otherwise>
                            </c:choose> (${item.memberCount})
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
    <c:choose>
    <c:when test="${groupInfo==null}">
    <div class="group_details">
        <div
                style="text-align:center;min-height:600px;line-height:600px;font-size:20px;">
            未有群组信息
        </div>
    </div>
    </c:when>
    <c:otherwise>
    <!--群组信息-->
    <div class="group_details">
        <div class="details_contain">
            <div class="details_title">
                <h2>群组信息</h2>
            </div>
            <div class="details_img">
                <img src="/static/images/head.png" width="100" height="100"/>
            </div>
            <div class="right_details">
                <div class="group_name">
                    <img src="/static/images/qrcode.png"
                         onclick="dialogUtils.showDialog(3,'${groupInfo.id}','${groupInfo.name}')"/>
                    <h2>${groupInfo.name}</h2>
                </div>
                <div>
                    群编号：<span id="groupNumber">${groupInfo.id}</span>&nbsp;&nbsp;&nbsp;&nbsp;本群创建时间：<span><fmt:formatDate
                        value="${groupInfo.createDate}" pattern="yyyy-MM-dd"/></span>
                </div>
                <div>
                    本群人数：<span id="memberCount">${(fn:length(groupList))>0?groupList[0].memberCount:"0"}
                </span>&nbsp;&nbsp;&nbsp;&nbsp;服务师：<span class="manager"
                                                         onclick="dialogUtils.showDialog(0,'${groupInfo.server}')">
                                <c:choose>
                                    <c:when test="${fn:length(groupInfo.server) > 24}">
                                        <c:out value="${fn:substring(groupInfo.server, 0, 24)}.."/>
                                    </c:when>
                                    <c:when test="${empty groupInfo.server}">
                                        无
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${groupInfo.server}"/>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                </div>
            </div>
        </div>

        <div class="groupMember">
            <table class="service_table" cellpadding="0" cellspacing="0">
                <tr>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>剩余</td>
                    <td>到期时间</td>
                    <td>操作</td>
                </tr>
                <c:if test="${empty memberList}">
                    <tr>
                        <td colspan="5">该群组还未有成员</td>
                    </tr>
                </c:if>
                <c:forEach items="${memberList}" var="item">
                    <tr>
                        <td>${item.realName}</td>
                        <td><c:choose>
                            <c:when test="${item.sex}">男</c:when>
                            <c:otherwise>女</c:otherwise>
                        </c:choose></td>
                        <td><span class="money">
                            <c:choose>
                                <c:when test="${item.chargeMode==0}">
                                    <c:choose>
                                        <c:when test="${item.endDate==null}">无限制</c:when>
                                        <c:otherwise>
                                            <script type="text/javascript">
                        var status = -(DateUtils.getInterval('<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd" />'));
                        status < 0 ? document.write('0天') : document.write('<span style="color:#48c858">' + status + '天</span>');
                    </script>

                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${item.chargeMode==1}">${item.timesRemaining}次</c:when>
                                <c:otherwise> <script type="text/javascript">
                        var status = -(DateUtils.getInterval('<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd" />'));
                        status < 0 ? document.write('0天') : document.write('<span style="color:#48c858">' + status + '天</span>');
                    </script>
                                </c:otherwise>
                            </c:choose>


                                    <%--<c:choose>
                                        <c:when test="${item.remainDays<=0}">
                                            0
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color:#48c858">
                                                    ${item.remainDays}
                                            </span>
                                        </c:otherwise>
                                    </c:choose>--%>

                                    </span></td>
                        <td>
                            <c:choose>
                                <c:when test="${item.chargeMode==0}">
                                    <c:choose>
                                        <c:when test="${item.endDate==null}">无限制</c:when>

                                        <c:otherwise><fmt:formatDate value="${item.endDate}"
                                                                     pattern="yyyy-MM-dd"/></c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${item.chargeMode==1}">

                                </c:when>
                                <c:otherwise><fmt:formatDate value="${item.endDate}"
                                                             pattern="yyyy-MM-dd"/></c:otherwise>
                            </c:choose>
                        </td>
                        </td>
                        <td><a
                                href="javascript:dialogUtils.showDialog(4,${item.id},${item.chargeMode})"><img
                                class="person_info" src="/static/images/person_info.png"/></a> <a
                                href="javascript:dialogUtils.showDialog(1,${item.id})"><img
                                class="health_data" src="/static/images/health_data.png"/></a>
                            <shiro:hasPermission name="user:0 or user:2">
                            <a
                                href="javascript:dialogUtils.showDialog(6,${item.id})"><img class="relation"
                                                                                            src="/static/images/relation.png"/></a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="user:1 or user:2">
                            <a href="javascript:dialogUtils.showDialog(5,'${item.realName}',${item.hxId})"><img
                                class="conversation" src="/static/images/conversation.png"/>
                            </a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    </c:otherwise>
    </c:choose>


    <div id="groupPageContain" class="page_Container"
         style="margin-top:30px;text-align:right;padding-right:10px">
        <c:choose>
            <c:when test="${groupPageCount==1}">
                <span class="page page_action">1</span>
            </c:when>
            <c:when test="${groupPageCount>1}">
                <span class="page page_action">1</span>
                <c:choose>
                    <c:when test="${groupPageCount>5}">
                        <c:forEach begin="2" end="5" var="p">
                            <span class="page">${p}</span>
                        </c:forEach>
                        <span class="page_dian">...</span>
                    </c:when>
                    <c:when test="${groupPageCount<=5}">
                        <c:forEach begin="2" end="${groupPageCount}" var="p">
                            <span class="page">${p}</span>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <span id="groupNext" class="page page_next">下一页</span>
                <span style="margin-left:10px">共${groupPageCount}页，到第</span>
                <input type="text" class="page_input" id="p_input"/>
                <span>页</span>
                <button class="page_input_enter">确定</button>
            </c:when>
        </c:choose>
    </div>