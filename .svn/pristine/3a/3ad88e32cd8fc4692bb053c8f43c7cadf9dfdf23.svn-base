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
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/com/QYPart/css/sercurity.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/pageCss.css">
    <script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script>
        window.onload = function () {
            menuSetting({
                parent: 5,
                child: 5
            })
        }

    </script>
    <style>
        .container{
            width: 918px;
            min-height: 840px;
            border: 1px solid #ddd;
            padding: 0 20px;
        }
        .container h3{
            height: 60px;
            line-height: 60px;
            font-size: 18px;
            font-weight: 400;
            margin: 0 30px 20px;
            border-bottom: 1px dashed #ddd;
        }
    </style>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">主页</div>
            <div class="container fr">
                <h3>意见反馈</h3>
                <div class="base_content" style="padding-left: 20px;padding-right: 20px;position:relative;">
                    <div class="param_set">
                        <label class="param" style="vertical-align: top;font-size: 15px;font-weight: 400">反馈内容：</label>
                        <textarea name="" id="content" style="width:600px" rows="6"></textarea>
                    </div>
                    <span class="summit" onclick="commit()" style="cursor: pointer;background: #48c858">提交</span>
                </div>
                <table id="reportTable" class="service_table" cellpadding="0" cellspacing="0" style="margin-top: 80px;">
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
                <%@include file="/context/page.jsp" %>
            </div>
        </div>
    </div>
</div>
<script>
    Vue.filter('date', function (value, fmt) {
        return new Date(value).Format(fmt);
    });
    var v = new Vue({
        el: '#reportTable',
        data: {
            results: '${data}' == '' ? '' : JSON.parse('${data}')
        }, filters: {
            reply: function (value) {
                if (value == undefined)
                    return "无"
                return value
            }
        }
    });
    var cloak=false;
    function commit() {
    	if(cloak)
    		return;
        var text = $("#content").val();
        if ($.trim(text) == '') {
            layer.msg("反馈内容不能为空");
            return;
        }
        cloak=true;
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
                cloak=false;
            }
        });
    }
    var pageManager = new PageUtil();
    pageManager.getPageControl().init({
        container: "pageManager",
        preBtn: "btn_pre",
        nextBtn: "btn_next",
        totalPage: parseInt("${totalPage}"),
        pageChange: function (page) {
            $.ajax({
                type: 'GET',
                contentType: 'application/json; charset=utf-8',
                url: 'commonControl.do?reportSet&action=get&page=' + page,
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
</body>
</html>
