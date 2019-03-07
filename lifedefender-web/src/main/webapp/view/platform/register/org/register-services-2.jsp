<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/27
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery,layer,layui"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>专业人士注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.准备申请</p>
            </li>
            <li>
                <div class="step-1"></div>
                <p class="on">2.实名信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>3.资格认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.专业特长</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap register-form-2">
        <ul>
            <li class="set-head">
                <div><img src="/static/platform/v2.2.0/images/userhead-templete.jpg" class="upload-img-3" alt=""></div>
                <p>头像为审核条件之一，请上传您真实照片作为头像</p>
                <div>
                    <input type="button" value="选择文件" onclick="org.picClick(3)">
                    <div class="none"><input type="file" name="path" class="path-3"/></div>
                    <input type="hidden" name = "photo" class="hidden-img-3"/>
                </div>
            </li>
            <li>
                <h5>真实姓名</h5>
                <input type="text" class="realname" placeholder="请输入真实姓名">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>身份证号</h5>
                <input type="text" name="idCard" placeholder="请如实填写您的身份证号码">
                <div class="msg-lay"></div>
            </li>
            <li class="up-licence">
                <h5>身份证照</h5>
                <div class="img-container fl">
                    <div class="up-btn">
                        <%--<input type="file">
                        <input type="button" value="选择文件">--%>
                        文件大小1Mb以内,最多只能传2张图片
                    </div>
                    <div class="img-list clearfix">
                        <div class="fl">
                            <img class="upload-img-1" src = "/static/platform/v2.2.0/images/register/businessPic.png" onclick="org.picClick(1)"  alt=""/>
                            <input type="file" name="path" class="path-1"  />
                            <input type="hidden" name = "idPic1" class="hidden-img-1"/>
                        </div>
                        <div class="fl">
                            <img class="upload-img-2" src = "/static/platform/v2.2.0/images/register/businessPic.png" onclick="org.picClick(2)"  alt=""/>
                            <input type="file" name="path" class="path-2"  />
                            <input type="hidden" name = "idPic2" class="hidden-img-2"/>
                        </div>
                    </div>
                </div>
            </li>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2">保存下一步</a>
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
<script>
    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';
    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {

        $('.upload-img-1').attr('src', '/' + results.obj);
        $('.hidden-img-1').val(results.obj);
    });

    //绑定图片2
    element = '.path-2';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-2').attr('src', '/' + results.obj);
        $('.hidden-img-2').val(results.obj);
    });

    //绑定图片3
    element = '.path-3';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-3').attr('src', '/' + results.obj);
        $('.hidden-img-3').val(results.obj);
    });
</script>
</body>
</html>
