<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<t:base type="jquery2.11,layer,vue,layui"></t:base>
<meta charset=utf-8>
<meta name=description content="">
<meta name=viewport content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
<link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
<link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">
<%--<link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">--%>
<link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/income-bind.css">
<title>绑定银行信息</title>
</head>
<body>

<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white">
            <div class="store-href">
                <a href="#">账户安全</a><em>></em><span>绑定银行信息</span>
            </div>
            <form class="layui-form">
                <ul class="store-infor">
                    <li>
                        <label>公司名称</label>
                        <input name="bankName" type="text" placeholder="请正确填写公司名称(如：广州通众电气实业有限公司)" lay-verify="required" value="${orgName}" disabled>
                    </li>
                    
                    <li>
                        <label>公司对公账号</label>
                        <input name="bankAccount" type="text" placeholder="输入内容必须介于12-19之间" lay-verify="required|accountLength">
                    </li>
                    <li>
                        <label>开会所在地区</label>
                        <input name="bankDistrict" type="text" placeholder="请正确填写开户所在地区" lay-verify="required">
                    </li>
                    <li>
                        <label>开户行支行名称</label>
                        <input name="bankBranch" type="text" placeholder="请正确填写开户支行名称" lay-verify="required">
                    </li>
                    <li class="save">
                        <a href="#" lay-submit lay-filter="*">保存</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/platform/v2.2.0/js/org/finance/store-bank-info.js"></script>
<script>
    common.addBorder();
    bank.init();

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(4).click();
    } else {
        $('.main-nav li').eq(2).click();
    }
</script>