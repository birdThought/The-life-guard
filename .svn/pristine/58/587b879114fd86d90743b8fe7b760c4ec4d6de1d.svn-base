
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11,jquery.serializejson,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-employee-add.css?v=2.2.0">
    <title>添加员工</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article >
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-employee-add" style="background-color: white">
            <div class="main-content-top">
                <a href="#">员工管理</a>><span>添加员工</span>
            </div>
            <form class="employee-add-form">
                <div class="head-img">
                    <div class="photo">
                        <img style="width: 120px; height: 120px; border-radius: 50%;" class="employee-image-show-1" src="/static/platform/v2.2.0/images/template-head.png" alt=""></div>
                    <p>头像为审核条件之一，请上传您真实照片作为头像</p>
                    <div>
						<span class="head-btn cursor-pointer" @click = "popupImg(1)">
							选择文件
						</span>
                        <input type="file" name = "path" class="path-1 none">
                        <input name = "photo" class="employee-image-hidden-1" type="hidden">
                    </div>
                </div>
                <ul class="user-infor">
                    <li>
                        <p>真实姓名</p>
                        <input name = "realName" type="text" placeholder="请输入您的真实姓名">
                    </li>
                    <li>
                        <p>身份证号</p>
                        <input name = "idCard" type="text" placeholder="请如实填写您的身份证号码">
                        <div class="msg-lay"></div>
                    </li>
                    <li>
                        <p>性别</p>
                        <select name="gender" >
                            <option value="1" selected>男</option>
                            <option value="0">女</option>
                        </select>
                    </li>
                    <li>
                        <p>出生日期</p>
                        <input name = "birthday" id = "birthday" type="text"
                               style="background:url('/static/images/green_date_img.png') no-repeat right center;  width: 150px;" readonly>
                    </li>
                    <li>
                        <dl class="id-card">
                            <dt class="clearfix">
                            <p class="fl">身份证照</p>
                            <div class="fl">
						    		<%--<span class="chose-id">
							    		<input type="file">
							    		选择文件
							    	</span>--%>
                                <span style="color: #999; font-size: 14px; margin-left: 10px;">格式为JPG、PNG、GIF，文件大小1MB以内</span>
                            </div>
                            </dt>
                            <dd class="clearfix">
                                <div class="fl">
                                    <img @click = "popupImg(2)" class="employee-image-show-2 cursor-pointer"
                                         style="width: 240px; height: 178px;"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-2 none">
                                    <input name = "idCardPicOne" class="employee-image-hidden-2" type="hidden">
                                </div>
                                <div class="fl">
                                    <img @click = "popupImg(3)" class="employee-image-show-3 cursor-pointer"
                                         style="width: 240px; height: 178px;"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-3 none">
                                    <input name = "idCardPicTwo" class="employee-image-hidden-3" type="hidden">
                                </div>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <p>申请类型</p>
                        <select name="userType" >
                            <%--<option value="请选择您的专业背景">请选择您的专业背景</option>--%>
                            <option value="1">服务师</option>
                        </select>
                    </li>
                    <li>
                        <p>职称</p>
                        <select name="professionalName">
                            <%--<option value="请选择您的职称">请选择您的职称</option>--%>
                                <option value="医生">医生</option>
                                <option value="护士">护士</option>
                                <option value="健康管理师">健康管理师</option>
                                <option value="膳食健康师">膳食健康师</option>
                                <option value="公共健康咨询师">公共健康咨询师</option>
                                <option value="理疗师">理疗师</option>
                                <option value="中医师">中医师</option>
                                <option value="健身教练">健身教练</option>
                                <option value="健康管理师">健康管理师</option>
                                <option value="慢病诊疗专家(中医、西医、养生师)">慢病诊疗专家(中医、西医、养生师)</option>
                                <option value="瑜伽教练">瑜伽教练</option>
                                <option value="药剂师">药剂师</option>
                                <option value="全科医生保姆">全科医生保姆</option>
                                <option value="特级看护">特级看护</option>
                                <option value="营养师">营养师</option>
                        </select>
                    </li>
                    <li>
                        <p>职业资格证照</p>
                        <div >
				    		<%--<span class="chose-id cursor-pointer">
					    		选择文件
					    	</span>--%>
                            <span style="color: #999; font-size: 14px; margin-left: 10px;">格式为JPG、PNG、GIF，文件大小1MB以内</span>
                        </div>
                        <div style = "margin-left: 150px;">
                            <img @click = "popupImg(4)" class="employee-image-show-4 cursor-pointer"
                                 style="width: 240px; height: 178px;"
                                 src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                            <input type="file" name = "path" class="path-4 none">
                            <input name = "professionalPic" class="employee-image-hidden-4" type="hidden">
                        </div>
                    </li>
                    <li>
                        <p>登录名</p>
                        <input name = "userName" type="text" placeholder="6-19个字符组成，不能纯数字、特殊字符">
                    </li>
                    <li>
                        <p>登录密码</p>
                        <input name = "initPassword" type="password" placeholder="至少6位任意字符组成">
                    </li>
                    <li>
                        <p>确认密码</p>
                        <input name = "password" type="password" placeholder="请再次输入登陆密码">
                    </li>
                    <li>
                        <p>手机</p>
                        <input name = "mobile" type="text" placeholder="请输入手机号码">
                    </li>
                    <li class="refer-to">
                        <a href="javascript:void(0)" class="btn" @click = "addEmployee">上传备案</a>
                    </li>
                </ul>
            </form>
        </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/common/js/dateFormat.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script src="/static/platform/v2.2.0/js/org/employeemanage/employee-add.js?v=2.2.0"></script>

<script>
    $('.main-nav').find('li').eq(2).click();
    employee.init();

    jeDate({
        dateCell : "#birthday",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        startMin:"1900-01-01",
        startMax: jeDate.now(0),
        minDate:"1900-01-01",
        maxDate: jeDate.now(0),
        zindex: 999,
        choosefun: function(elem, val){
            /*home.vm.startDate = val;
            end.minDate = val;
            endDates();*/
        }
    });
</script>
<script>
    common.addBorder();

</script>

