<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>基础信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/paramSubmit.css">
    <script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/commonCheck.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/orgBase.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            menuSetting({
                parent: 1,
                child: 1
            });
        }
    </script>
    <style>
        .psw_strenth{
            text-align:left;
            padding-left:14%;
            margin-top: 10px;
        }
        .psw_strenth div{
            width: 70px;
            height: 10px;
            margin-right:8px;
            background-color:#ddd;
            display:inline-block;
        }
    </style>
</head>

<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body">
            <div class="right_title" style="padding-left: 30px;margin-bottom: 25px">
                <label class="action">修改密码</label>
            </div>
            <div class="item_contain">
                <div style="padding-top:20px;padding-left:20px">
                    <div class="param_set" style="clear: both;">
                        <label class="param">旧密码：</label><input type="password"
                                                                id="old_psw" placeholder="请输入旧密码"/>
                    </div>
                    <div class="param_set" style="clear: both;">
                        <label class="param">新密码：</label><input type="password"
                                                                id="new_psw" placeholder="请输入密码" onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)"/>
                        <div class="psw_strenth">
                            <div id="strength_L"></div>
                            <div id="strength_M"></div>
                            <div id="strength_H"></div>
                            <span id="strength_notice"></span>
                        </div>
                    </div>
                    <div class="param_set" style="clear: both;">
                        <label class="param">确认密码：</label><input type="password"
                                                                 id="confirm_psw" placeholder="请确认新密码"/>
                    </div>
                    <div style="padding:10px 110px 20px">
                        <input class="save" value="确认" onclick="BaseSet.changePsw()"
                               type="button"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
