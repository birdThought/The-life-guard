<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<div id="pageManager" class="page_Container">
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