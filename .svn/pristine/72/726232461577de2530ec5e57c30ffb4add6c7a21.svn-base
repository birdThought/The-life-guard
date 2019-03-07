
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,jquery.serializejson,layui,vue"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <%--<link rel="stylesheet" href="/static/plugins/layui/css/layui.css">--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>个体门店注册注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-2"></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-1"></div>
                <p class="on">1.实名信息认证</p>
            </li>
            <li>
                <div class="step-2"></div>
                <p>2.个人资格认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>3.店铺简介</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.账号申请</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap vue-content apply" name = "content-form">
        <ul>
            <li class="set-head">
                <div><img class="upload-img-1" src="/static/platform/v2.2.0/images/template-head.png" alt=""></div>
                <p>头像为审核条件之一，请上传您真实照片作为头像</p>
                <div>
                    <input type="button" value="选择文件" @click = "popupImg(1)">

                </div>
                <input type="file" name="path" class="path-1"/>
                <input type="hidden" name = "photo" class="hidden-img-1"/>
            </li>
            <li>
                <h5>真实姓名</h5>
                <input type="text" class="username" name = "realName" placeholder="请输入真实姓名">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>身份证号</h5>
                <input type="text" name = "idCard" placeholder="请如实填写您的身份证号码">
                <div class="msg-lay"></div>
            </li>
            <li class="up-licence">
                <h5>身份证照</h5>
                <div class="img-container fl">
                    <div class="up-btn">
                        <%--<input type="file">--%>
                        <%--<input type="button" value="选择文件">--%>
                        文件大小1Mb以内,最多只能传2张图片
                    </div>
                    <div class="img-list clearfix">
                        <div class="fl">
                            <img class="upload-img-2 cursor-pointer" src="/static/platform/v2.2.0/images/upload-1.png" @click = "popupImg(2)" alt="">
                            <input type="file" name="path" class="path-2"/>
                            <input type="hidden" name = "idCardPicOne" class="hidden-img-2"/>
                        </div>
                        <div class="fl">
                            <img class="upload-img-3 cursor-pointer" src="/static/platform/v2.2.0/images/upload-2.png" @click = "popupImg(3)" alt="">
                            <input type="file" name="path" class="path-3"/>
                            <input type="hidden" name = "idCardPicTwo" class="hidden-img-3"/>
                        </div>
                    </div>
                </div>
            </li>
            <li class="argument">
                <p>点击【开始申请】按钮，表示您同意<a class="btn-yellow" @click = "popupAgreement">《个体门店注册协议》</a></p>
            </li>
            <li class="argument">
                <input type="button" value="开始申请" @click = "apply">
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script src="/static/platform/v2.2.0/js/register/individualstore/individual-store-register.js?v=2.2.0"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/common/js/common.js"></script>
<script>
    store.init();


</script>

