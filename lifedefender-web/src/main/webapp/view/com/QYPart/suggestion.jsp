<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>意见反馈</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer,vue"></t:base>
    <link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
          mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link rel="stylesheet" type="text/css" href="/static/com/QYPart/css/sercurity.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/pageCss.css">
    <script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script>
        window.onload = function () {
            var child = 2;
            <shiro:hasPermission name="user:0 or user:2">
            child = 3;
            </shiro:hasPermission>
            menuSetting({
                parent: 1,
                child: child
            })
        }

    </script>
</head>
<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body" style="min-height: 822px">
            <div class="right_title" style="padding-left: 30px;">
                <label class="action">意见反馈</label>
            </div>
            <div class="base_content" style="padding-left: 20px;padding-right: 20px;position:relative;">
                <div class="param_set">
                    <label class="param" style="vertical-align: top;">反馈内容：</label>
                    <textarea name="" id="content" cols="30" rows="6"></textarea>
                </div>
                <span class="summit" onclick="commit()" style="cursor: pointer;">提交</span>
            </div>
            <table id="reportTable" class="service_table" cellpadding="0" cellspacing="0" style="margin-top: 80px">
                <tr>
                    <td>时间</td>
                    <td>反馈内容</td>
                    <td>回复</td>
                </tr>
                <tr v-if="results==''">
                    <td colspan="3">暂无记录</td>
                </tr>
                <tr v-for='r in results'>
                    <td v-cloak>{{r.createDate | date("yyyy-MM-dd")}}</td>
                    <td v-cloak>{{r.message}}</td>
                    <td v-cloak>{{r.reply | reply}}</td>
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
                        <button class="page_input_enter">确定</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script>
    Vue.filter('date', function (value, fmt) {
        return new Date(value).Format(fmt);
    });
    var v=new Vue({
        el:'#reportTable',
        data: {
            results:'${data}'==''?'':JSON.parse('${data}')
        },filters:{
            reply:function (value) {
                if(value==undefined)
                    return "无"
                return value
            }
        }
    });
    function commit() {
        var text = $("#content").val();
        if ($.trim(text) == '') {
            layer.msg("反馈内容不能为空");
            return;
        }
        layer.load();
        var json = {
            text: text
        }
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'commonControl.do?reportSet&action=add',
            data: JSON.stringify(text),
            success: function (result) {
                if (result.success) {
                    layer.msg("提交反馈成功!");
                    setTimeout(function () {
                        window.location.reload();
                    }, 500);
                }
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: parseInt("${pageCount}"),
        pageChange: function (page) {
            $.ajax({
                type: 'GET',
                contentType: 'application/json; charset=utf-8',
                url: 'commonControl.do?reportSet&action=get&page='+page,
                success: function (result) {
                    v.results=result.obj
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
        }
    });

</script>
</body>
</html>
