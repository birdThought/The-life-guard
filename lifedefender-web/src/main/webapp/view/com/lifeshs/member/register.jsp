<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>注册账号</title>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/register.css"/>
    <link rel="stylesheet" type="text/css" href="/static/common/css/mainHeader.css"/>
    <link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
    <t:base type="jquery,layer"></t:base>
    <script type="text/javascript" src="/static/login/js/icheck.js"></script>
    <script type="text/javascript"
            src="/static/common/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/static/common/js/messages_zh.min.js"></script>
    <script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
    <script type="text/javascript" src="/static/login/js/register.js"></script>

    <script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
          mce_href="favicon.ico"/>
    <script type="text/javascript" src="/static/com/QYPart/js/customRadio.js"></script>
    <style type="text/css">
        html {
            overflow-x: hidden;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<%@include file="/context/mainHeader.jsp" %>
<div class="content">
    <form action="" id="signupForm" method="" class="hwrap">
        <div class="title">
            <h1>注册账号</h1>
        </div>
        <div class="control_contain">
            <div class="control_item">
                <label>用户类型 </label>
                <div class="control">
                    <input name="radio1" type="radio" class="user" value="m" checked/><label
                        id="userCustom" style="color:#48c859">个人用户</label><span
                        style="margin-left:8px"> <input name="radio1" type="radio"
                                                        class="user1" value="o"/><label id="businessCustom"
                >机构</label></span>
                </div>
            </div>
            <section id="registType">
                <div id="businessType" style="display:none">
                    <div class="control_item">
                        <label>机构类型</label>
                        <div class="control">
                            <input type="radio" name="orgType" value="0" checked/><label>多门店&nbsp;&nbsp;</label>
                            <input type="radio" name="orgType" value="1"/><label>单门店&nbsp;&nbsp;</label>
                            <input type="radio" name="orgType" value="2"/><label>个体店</label>
                        </div>
                    </div>
                    <div  class="control_item" >
                        <label>机构性质</label>
                        <div class="control">
                            <select id="orgXZ" style="width: 90%;padding: 3px 8px;font-size: 15px;border: 1px solid #ddd;font-family: 'Microsoft Yahei'">
                                <option value="慢病康复">慢病康复</option>
                                <option value="健康养生">健康养生</option>
                                <option value="减肥塑体">减肥塑体</option>
                                <option value="居家养老">居家养老</option>
                                <option value="癌症康复">癌症康复</option>
                                <option value="妇婴">妇婴</option>
                                <option value="家政">家政</option>
                                <option value="生殖医学">生殖医学</option>
                            </select>
                        </div>
                    </div>
                    <div  class="control_item" >
                        <label>机构名称</label>
                        <div class="control">
                            <input id="businessName" name="business" type="text" placeholder="请输入名称"
                                   class="input_edit"/>
                        </div>
                    </div>
                </div>
                <div class="control_item">
                    <label>用户名</label>
                    <div class="control">
                        <input id="userName" type="text" name="userName" placeholder="请输入用户名"
                               class="input_edit"/>
                    </div>
                </div>
                <!-- <div class="control_item">
                    <label>验证码</label>
                    <div class="control">
                        <input type="text" maxlength="6" placeholder="请输入手机短信验证码"
                            class="input_edit" style="width:60%" /><input
                            id="signupBtnCode" type="button" name="idcode" value="获取验证码" class="getVerify"
                            onclick="sendRandCode()">
                    </div>
                </div> -->
                <div class="control_item" style="margin-bottom:5px">
                    <label>密码</label>
                    <div class="control">
                        <input type="password" name="password" id="password" placeholder="请输入密码"
                               onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)"
                               class="input_edit"/>
                    </div>
                    <div class="psw_strenth">
                        <div id="strength_L"></div>
                        <div id="strength_M"></div>
                        <div id="strength_H"></div>
                        <span id="strength_notice"></span>
                    </div>
                </div>
                <div class="control_item" style="clear:both">
                    <label>确认密码</label>
                    <div class="control">
                        <input type="password" placeholder="请再次输入密码" name="conform_password" class="input_edit"/>
                    </div>
                </div>
            </section>
            <div class="control_item">
                <label for="agreement" id="_checkbox"> <input
                        type="checkbox" id="agreement" name="agreement" class="keep"
                        checked="checked" required><span
                        style="margin-left:8px;position:relative;top:2px">我已阅读并接受 <a
                        href="javascript:void(0);" class="moument" onclick="clickHere();">《生命守护用户服务协议》</a>
					</span>
                </label>
            </div>
            <div><input type="submit" value="注册" class="regist_btn"></div>
            <p style="text-align:right">
                已经有生命守护账号？<a href="/login" class="moument">立即登录</a>
            </p>
        </div>
    </form>
</div>
<%@include file="/context/mainFooter.jsp" %>
</body>
</html>
