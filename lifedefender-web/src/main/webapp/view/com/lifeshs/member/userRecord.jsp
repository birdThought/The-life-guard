<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>个人信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/css/userInfor.css">
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <t:base type="jquery,layer"></t:base>
    <script type="text/javascript" src="/static/login/js/icheck.js"></script>
    <script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
    <script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
    <script type="text/javascript" src="/static/com/lifeshs/member/js/userRecord.js"></script>
    <script>
        $(function () {
            menuSetting({
                parent: 5,
                child: 1
            });
        });
    </script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">主页</div>
            <div class="container fr">
                <h3>个人档案</h3>
                <form name="userRecord">
                    <ul class="setInfor">
                        <li><label>身高( CM )</label> <input type="text" id="height"
                                                           name="height" value="<fmt:formatNumber type="number" value="${user.height}" />" class="height" maxlength="3"
                                                           placeholder="请输入身高"></li>
                        <li><label>体重( KG )</label> <input type="text" id="weight" maxlength="6"
                                                           name="weight" value="${user.weight}" placeholder="请输入体重">
                        </li>
                        <li><label>臀围( CM )</label> <input type="text" name="hip" maxlength="5"
                                                           value="${user.hip}" placeholder="请输入臀围"></li>
                        <li><label>腰围( CM )</label> <input type="text" name="waist" maxlength="5"
                                                           value="${user.waist}" placeholder="请输入腰围"></li>
                        <li><label>胸围( CM )</label> <input type="text" name="bust" maxlength="5"
                                                           value="${user.bust}" placeholder="请输入胸围"></li>
                    </ul>
                    <dl class="instruction">
                        <dt>说明：</dt>
                        <dd>
                            <small>请完善个人信息，以便获取更准确的测试结果</small>
                        </dd>
                    </dl>
                    <button type="submit" class="btn">确定</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>