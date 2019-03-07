<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div id="service_history_block" style="display:none">
    <div style="margin-bottom:15px;position:relative">
        <select id="serverGroup_choose" class="service_select">
            <option value="" selected="selected">服务群组</option>
            <c:forEach items="${groupList}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
        <select id="status" class="service_select">
            <option selected="selected" value="">状态</option>
            <option value="1">待付款</option>
            <option value="2">付款失败</option>
            <option value="3">有效</option>
            <option value="4">已完成</option>
            <option value="5">退款失效</option>
            <option value="6">已取消</option>
        </select> <span class="outdate"> <label>到期时间</label> <input
            id="out_of_date" type="text" readonly/>
		</span>
        <button class="search-btn" onclick="HistoryRecord.filterHistory()">搜索</button>
    </div>
    <table id="history_record" class="service_table" cellpadding="0"
           cellspacing="0">
        <tr>
            <td>姓名</td>
            <td>性别</td>
            <td>服务群组</td>
            <td>收费方式</td>
            <td>开始时间</td>
            <td>结束时间</td>
            <td>收费记录</td>
            <td>状态</td>
        </tr>
        <c:if test="${empty historyRecord}">
            <tr>
                <td colspan="7">没有相关数据</td>
            </tr>
        </c:if>
        <c:forEach items="${historyRecord}" var="item">
            <tr>
                <td>${item.realName}</td>
                <td><c:choose>
                    <c:when test="${item.sex}">男</c:when>
                    <c:otherwise>女</c:otherwise>
                </c:choose>
                </td>
                <td>${item.groupName}</td>
                <td><c:choose>
                    <c:when test="${item.chargeMode==0}">免费</c:when>
                    <c:when test="${item.chargeMode==1}">按次</c:when>
                    <c:when test="${item.chargeMode==2}">按月</c:when>
                    <c:when test="${item.chargeMode==3}">按年</c:when>
                </c:choose></td>
                <td><fmt:formatDate value="${item.startDate}"
                                    pattern="yyyy-MM-dd"/></td>
                <td>
                    <c:choose>
                        <c:when test="${item.chargeMode==0}">
                            <c:choose>
                                <c:when test="${item.endDate==null}">无限制</c:when>
                                <c:otherwise><fmt:formatDate value="${item.endDate}"
                                                             pattern="yyyy-MM-dd"/></c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${item.chargeMode==1}">剩余${item.timesRemaining}次</c:when>
                        <c:otherwise><fmt:formatDate value="${item.endDate}"
                                                     pattern="yyyy-MM-dd"/></c:otherwise>
                    </c:choose>
                </td>
                </td>
                <td><span class="money">￥${item.fair}</span></td>
                <td>
                    <c:choose>
                        <c:when test="${item.status==1}">待付款</c:when>
                        <c:when test="${item.status==2}">付款失败</c:when>
                        <c:when test="${item.status==3}">有效</c:when>
                        <c:when test="${item.status==4}">已完成</c:when>
                        <c:when test="${item.status==5}">退款失效</c:when>
                        <c:when test="${item.status==6}">已取消</c:when>
                    </c:choose>
                        <%-- <c:choose>
                             <c:when test="${item.remainDays<=0}">
                                 <span style="color:#6c6c6c">已过期</span>
                             </c:when>
                             <c:otherwise>
                                 <span style="color:#48c858">正常</span>
                             </c:otherwise>
                         </c:choose>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div id="historyPage" class="page_Container">
        <c:choose>
            <c:when test="${rCount==1}">
                <span class="page page_action">1</span>
            </c:when>
            <c:when test="${rCount>1}">
                <span class="page page_action">1</span>
                <c:choose>
                    <c:when test="${rCount>5}">
                        <c:forEach begin="2" end="5" var="p">
                            <span class="page">${p}</span>
                        </c:forEach>
                        <span class="page_dian">...</span>
                    </c:when>
                    <c:when test="${rCount<=5}">
                        <c:forEach begin="2" end="${rCount}" var="p">
                            <span class="page">${p}</span>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <span id="his_next" class="page page_next">下一页</span>
                <span style="margin-left:10px">共${rCount}页，到第</span>
                <input type="text" class="page_input" id="p_input"/>
                <span>页</span>
                <button class="page_input_enter">确定</button>
            </c:when>
        </c:choose>
    </div>
</div>