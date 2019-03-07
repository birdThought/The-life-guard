<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>编辑资料</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
    mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
    href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
    href="/static/common/css/QYcomCss.css">
<link href="/static/login/css/blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
    href="/static/plugins/jeDate/css/jedate.css">
<link rel="stylesheet" href="/static/com/QYPart/css/paramSubmit.css">
<script type="text/javascript"
    src="/static/plugins/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="/static/plugins/uedit/editor_api.js"></script>
<script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
<script type="text/javascript">
    seajs.config({
        base : './static/',
        alias : {
            "icheck" : "login/js/icheck",
            "QYcommon" : "com/QYPart/js/QYcommon",
            "commonCheck" : "com/QYPart/js/commonCheck",
            "customRadio" : "com/QYPart/js/customRadio",
            "jedate" : "plugins/jeDate/jedate.min",
            "fileUpload" : "com/lifeshs/member/record/fileUpload",
        }
    });
    seajs.use([ "./static/com/QYPart/js/editEmployInfo" ], function() {
        var orgType = parseInt(_cookie("orgType"));
        var orgUserType = parseInt("${employ.userType}");

        var showMenuDetail = parseInt("${showMenuDetail}");
        if (showMenuDetail == 0) {
            var child = 0;
            <shiro:hasPermission name="user:0 or user:2">
            child = 1;
            </shiro:hasPermission>
            menuSetting({
                parent : 1,
                child : child
            });
        }
        if (showMenuDetail == 1) {
            orgType == 0 ? menuSetting({
                parent : 0,
                child : 1
            }) : menuSetting({
                parent : 0,
                child : 2
            });
        }

        if (orgType == 2) {
            editEmploy.isSingleServeManage = true;
        }
        editEmploy.isServeUser = ((orgUserType == 1) || (orgUserType == 2));
        editEmploy.userId = parseInt('${employ.id}');
        editEmploy.isServeManage = ((orgType == 1) || (orgType == 2));
        editEmploy.userType = orgUserType;
        if (orgType != 0) {
            Editor.init();
            Editor.setHTMLContent('${employ.detail}');
        }
    });
</script>
</head>

<body>
    <%@include file="/context/QYHeader.jsp"%>
    <div class="container">
        <%@include file="/context/QYMenu.jsp"%>
        <div class="right_content">
            <div class="right_body">
                <div class="item_title"
                    style="background-color: #f1f1f2; border-bottom: none; padding-left: 20px; font-size: 18px">
                    <h3>编辑资料</h3>
                </div>
                <!--基本信息-->
                <div class="item_contain">
                    <div class="item_title">
                        <label class="title"> 基本信息 </label>
                    </div>
                    <form>
                        <div class="param_set">
                            <label class="param" style="float: left;">员工头像：</label>
                            <input id="headFile" type="file"
                                style="display: inline-block; visibility: hidden;" />
                            <div
                                style="background-color: #f0f0f0; width: 100px; height: 100px; display: inline-block; float: left; margin-bottom: 15px; cursor: pointer"
                                onclick="editEmploy.uploadFile()">
                                <img id="avater"
                                    style="width: 100px; height: 100px;"
                                    src="${employ.photo}" />
                            </div>
                        </div>
                        <p></p>
                        <c:if test="${orgType==1 && orgUserType!=1}">
                            <div id="selectType" class="param_set"
                                style="clear: both;">
                                <label class="param"><span
                                    class="warn">*</span>员工类型：</label>
                                <c:choose>
                                    <c:when
                                        test="${employ.userType == 0}">
                                        <input name="typeSelect"
                                            type="checkBox" value="a"
                                            checked />管理员
                                    &nbsp;&nbsp;&nbsp;
                                    <input name="typeSelect"
                                            type="checkBox" value="f" />服务师
                                </c:when>
                                    <c:when
                                        test="${employ.userType == 1}">
                                        <input name="typeSelect"
                                            type="checkBox" value="a" />管理员
                                    &nbsp;&nbsp;&nbsp;
                                    <input name="typeSelect"
                                            type="checkBox" value="f"
                                            checked />服务师
                                </c:when>
                                    <c:when
                                        test="${employ.userType == 2}">
                                        <input name="typeSelect"
                                            type="checkBox" value="a"
                                            checked />管理员
                                    &nbsp;&nbsp;&nbsp;
                                    <input name="typeSelect"
                                            type="checkBox" value="f"
                                            checked />服务师
                                </c:when>
                                    <c:otherwise>
                                        <input name="typeSelect"
                                            type="checkBox" value="a" />管理员
                                    &nbsp;&nbsp;&nbsp;
                                    <input name="typeSelect"
                                            type="checkBox" value="f" />服务师
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                        <div class="param_set" style="clear: both;">
                            <label class="param"><span
                                class="warn">*</span>姓名：</label><input id="name"
                                type="text" placeholder="请输入姓名"
                                value="${employ.realName}" />
                        </div>
                        <div class="param_set">
                            <label class="param"><span
                                class="warn">*</span>性别：</label>
                            <c:choose>
                                <c:when
                                    test="${employ.sex || empty employ.sex}">
                                    <input name="sexSelect" type="radio"
                                        value="1" checked />男&nbsp;&nbsp;&nbsp;
                                <input name="sexSelect" type="radio"
                                        value="0" />女
                            </c:when>
                                <c:when test="${!employ.sex}">
                                    <input name="sexSelect" type="radio"
                                        value="1" />男&nbsp;&nbsp;&nbsp;
                                <input name="sexSelect" type="radio"
                                        value="0" checked />女
                            </c:when>
                            </c:choose>
                        </div>
                        <div class="param_set">
                            <label class="param"><span
                                class="warn">*</span>手机：</label><input
                                id="phone" type="text"
                                placeholder="请输入手机号码"
                                value="${employ.mobile}" />
                        </div>
                        <div class="param_set">
                            <label class="param">Email：</label><input
                                id="e_mail" type="text"
                                placeholder="请输入email"
                                value="${employ.email}" />
                        </div>
                        <div class="param_set">
                            <label class="param">工作电话：</label><input
                                id="workPhone" type="text"
                                placeholder="请输入工作电话"
                                value="${employ.tel}" />
                        </div>
                        <div class="param_set">
                            <label class="param">出生日期：</label><input
                                id="birth" type="text"
                                placeholder="请选择日期" readonly
                                value="${employ.birthday}" />
                        </div>
                        <!--
                        <div class="param_set">
                            <label class="param">家庭住址：</label><input id="addr" type="text"
                                placeholder="请输入家庭地址" value="${employ.address}" />
                        </div>
                        -->
                    </form>
                </div>
                <c:if test="${orgType!=0}">
                    <div class="item_contain" id="introduce_div"
                        style="<c:if test="${employ.userType==0||empty employ.userType}">display: none;</c:if>">
                        <div class="item_title">
                            <label class="title">简介资料 </label>
                        </div>
                        <form>
                            <div class="param_set">
                                <label class="param"
                                    style="float: left;">服务师简介：</label>
                                <textarea id="server_introduce"
                                    class="big-txt-area"
                                    style="width: 650px" rows="3"
                                    placeholder="不能超过100字">${employ.about}</textarea>
                            </div>
                            <div class="param_set">
                                <label class="param"
                                    style="float: left;">详细介绍：</label>
                                <div
                                    style="width: 650px; display: inline-block;">
                                    <script type="text/plain"
                                        id="server_details"
                                        name="server_details">
                                </script>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
                <div style="padding: 30px 110px 20px; clear: both;">
                    <input onclick="editEmploy.submitForm()"
                        class="save" value="保存" type="button" /><input
                        class="back" value="返回" type="button"
                        onclick="history.go(-1)" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
