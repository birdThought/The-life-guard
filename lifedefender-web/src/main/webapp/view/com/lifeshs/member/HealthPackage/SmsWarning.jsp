<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>短信预警</title>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
    mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
    href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/static/css/healthPackage/smsWarning.css">
<t:base type="jquery,layer"></t:base>
</head>
<body>
    <div class="webPage wrap" style="border: 1px solid #ddd !important">
        <%@include file="/context/header.jsp"%>
        <div class="view-body wrap">
            <%@include file="/context/nav_left.jsp"%>
            <div class="right-wrap">
                <div class="title fl">
                    <a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;短信预警
                </div>
                <div class="container fr">
                    <h3>短信预警</h3>
                    <dl class="instruction">
                        <dt>说明：</dt>
                        <dd>
                            <small>设备测量参数出现异常信息时可向亲情联系人发送预警信息</small>
                        </dd>
                    </dl>
                    <div class="sms nav_none" id="sms">
                        <p>预警短信接收者</p>
                        <div class="smsWarning_add item-fr">
                            <span>添加号码</span>
                        </div>
                        <table id="receiveLists">
                            <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>手机号码</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        <div class="receive_dialog_container"
                            style="padding: 30px; display: none;">
                            <p>
                                <select id="allContact"
                                    style="width: 200px; height: 35px; border: solid 1px #ccc;">
                                </select> <a href="memberControl.do?showWarn"><span
                                    class="addContact" id="addContact"
                                    style="padding-left: 5px;">添加</span></a>
                            </p>
                        </div>
                        <p class="bottom-border-dashed">预警项目</p>
                        <div id="warningList">
                            <div>
                                <label><input id="heartRate"
                                    name="smsDevice" type="checkbox"
                                    value="heartRate" />心率</label>
                            </div>
                            <div>
                                <label><input id="tolic"
                                    name="smsDevice" type="checkbox"
                                    value="tolic" />血压</label>
                            </div>
                            <div>
                                <label><input id="bloodSugar"
                                    name="smsDevice" type="checkbox"
                                    value="bloodSugar" />血糖</label>
                            </div>
                            <div>
                                <label><input id="saturation"
                                    name="smsDevice" type="checkbox"
                                    value="saturation" />血氧</label>
                            </div>
                            <div>
                                <label><input
                                    id="temperatureBox" name="smsDevice"
                                    type="checkbox" value="temperature" />体温</label>
                            </div>
                            <div>
                                <label><input id="ECG"
                                    name="smsDevice" type="checkbox"
                                    value="ECG" />心电</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="/static/login/js/icheck.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/smsWarning.js"></script>
<script>
$(function() {
    menuSetting({
        parent : 0,
        child : 2
    });
    
    $('input').iCheck({
        checkboxClass : 'icheckbox_minimal-green'
    });
    
    smsWarning.bindEvent();
});
</script>
</html>