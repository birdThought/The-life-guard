<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加员工</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-ic

on" rel="shortcut icon" href="favicon.ico
"
          mce_href="favicon.ico"/>
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
            base: './static/',
            alias: {
                "icheck": "login/js/icheck",
                "QYcommon": "com/QYPart/js/QYcommon",
                "commonCheck": "com/QYPart/js/commonCheck",
                "customRadio": "com/QYPart/js/customRadio",
                "jedate": "plugins/jeDate/jedate.min",
                "fileUpload": "com/lifeshs/member/record/fileUpload"
            }
        });
        seajs.use(["./static/com/QYPart/js/addEmploy"], function () {
            var orgType = parseInt(_cookie("orgType"));
            orgType == 0 ? menuSetting({
                parent: 0,
                child: 1
            }) : menuSetting({
                parent: 0,
                child: 2
            });
            addEmploy.isManager = orgType == 0;
            if (orgType == 1) {
                Editor.init();
            }
        });
    </script>
</head>

<body>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body">
            <div class="item_title"
                 style="background-color:#f1f1f2;border-bottom:none;padding-left:20px;font-size:18px">
                <h3>添加员工</h3>
            </div>
            <!--账户信息-->
            <div class="item_contain">
                <div class="item_title">
                    <label class="title"> 账户信息 </label>
                </div>
                <form>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>登录账户：</label><input
                            id="account" type="text" placeholder="输入账户"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>密码：</label><input
                            id="psw" type="password" placeholder="输入密码"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>确认密码：</label><input
                            id="confirm_psw" type="password" placeholder="确认一下密码"/>
                    </div>
                </form>
            </div>
            <!--基本信息-->
            <div class="item_contain">
                <div class="item_title">
                    <label class="title"> 基本信息 </label>
                </div>
                <form>
                    <div class="param_set">
                        <label class="param" style="float:left;">员工头像：</label> <input
                            id="headFile" type="file"
                            style="display:inline-block;visibility: hidden;"/>
                        <div
                                style="background-color:#f0f0f0;width:100px;height:100px;display:inline-block;float:left;margin-bottom:15px;cursor:pointer"
                                onclick="addEmploy.uploadFile()">
                            <img id="avater" style="width:100px;height:100px;"
                                 src=""/>
                        </div>
                    </div>
                    <c:if test="${orgType==1}">
                        <div id="selectType" class="param_set" style="clear: both;">
                            <label class="param"><span class="warn">*</span>员工类型：</label>
                            <input name="typeSelect" type="checkBox" value="a"/>管理员
                            &nbsp;&nbsp;&nbsp;
                            <input name="typeSelect" type="checkBox" value="f"/>服务师

                        </div>
                    </c:if>
                    <div class="param_set" style="clear: both;">
                        <label class="param"><span class="warn">*</span>姓名：</label><input
                            id="name" type="text" placeholder="请输入姓名"
                            value="${employ.realName}"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>性别：</label>
                        <input name="sexSelect" type="radio" value="1" checked/>男&nbsp;&nbsp;&nbsp;
                        <input name="sexSelect" type="radio" value="0"/>女
                    </div>

                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>手机：</label><input
                            id="phone" type="text" placeholder="请输入手机号码"
                            value=""/>
                    </div>
                    <div class="param_set">
                        <label class="param">Email：</label><input id="e_mail" type="text"
                                                                  placeholder="请输入email" value=""/>
                    </div>
                    <div class="param_set">
                        <label class="param">工作电话：</label><input id="workPhone"
                                                                 type="text" placeholder="请输入工作电话" value=""/>
                    </div>
                    <div class="param_set">
                        <label class="param">出生日期：</label><input
                            id="birth" type="text" placeholder="请选择日期" readonly
                            value=""/>
                    </div>
                    <!--
                    <div class="param_set">
                        <label class="param">家庭住址：</label><input id="addr" type="text"
                                                                 placeholder="请输入家庭地址"/>
                    </div>
                    -->
                </form>
            </div>
            <c:if test="${orgType==1}">
                <div id="introduce_div" style="display: none">
                    <div class="item_contain">
                        <div class="item_title">
                            <label class="title">分配服务 </label>
                        </div>
                        <form>
                            <c:choose>
                                <c:when test="${empty serList}">
                                    <div style="padding:20px;font-size:17px;padding-top:0">此门店还没添加服务</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="param_set">
                                        <label class="param">服务绑定： </label><select id="serverSelect"
                                                                                   class="select-btn" style="width:250px;"
                                                                                   onchange="addEmploy.getGroupList()">
                                        <c:forEach items="${serList}" var="item">
                                            <option value="${item.id}">${item.serviceName}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                    <div class="param_set">
                                        <label class="param">选择群组： </label><select id="groupSelect"
                                                                                   class="select-btn" style="width:250px;">
                                        <c:forEach items="${groupList}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                    <div class="item_contain">
                        <div class="item_title">
                            <label class="title">简介资料 </label>
                        </div>
                        <form>
                            <div class="param_set">
                                <label class="param" style="float:left;">服务师简介：</label>
                                <textarea id="server_introduce" class="big-txt-area"
                                          style="width:650px" rows="3" placeholder="不能超过100字"></textarea>
                            </div>
                            <div class="param_set">
                                <label class="param" style="float:left;">详细介绍：</label>
                                <div style="width:650px;display: inline-block;">
                                    <script type="text/plain" id="server_details"
                                            name="server_details">
                                    </script>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
            <div style="padding:30px 110px 20px;clear: both;">
                <input onclick="addEmploy.submitForm()" class="save" value="保存"
                       type="button"/><input class="back" value="返回" type="button" onclick="history.go(-1)"/>
            </div>
        </div>
    </div>
</body>

</html>
