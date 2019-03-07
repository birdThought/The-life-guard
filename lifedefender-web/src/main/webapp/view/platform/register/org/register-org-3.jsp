
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,layui"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>机构注册</title>
</head>
<body>
<%@include file="/view/platform/context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.公司信息认证</p>
            </li>
            <li>
                <div class="step-3"></div>
                <p class="pass">2.银行信息认证</p>
            </li>
            <li>
                <div  class="step-1"></div>
                <p class="on">3.法人信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.准备入驻</p>
            </li><li>
                <div  class="step-2"></div>
                <p>5.等待审核</p>
            </li>
        </ul>
    </div>
    <form class="wrap register-form-3">
        <ul>
            <li>
                <h5>法人姓名</h5>
                <input type="text" name="legalPerson" placeholder="请输入法人姓名">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>性别</h5>
                <dl>
                    <dd>
                        <input type="radio" class="magic-radio" id="user_1" name="legalPersonGender" checked="true" value="true">
                        <label for="user_1">先生</label>
                    </dd>
                    <dd>
                        <input type="radio" class="magic-radio" id="user_2" name="legalPersonGender" value="false">
                        <label for="user_2">女士</label>
                    </dd>
                </dl>
            </li>
            <li>
                <h5>身份证号码</h5>
                <input type="text" name="legalPersonIdCard" placeholder="请输入您的身份证号码">
                <div class="msg-lay"></div>
            </li>
            <li class="up-licence">
                <h5>法人身份证电子版</h5>
                <div class="img-container fl">
                    <div class="up-btn">
                        <%--<input type="file">
                        <input type="button" value="选择文件">--%>
                        文件大小1Mb以内,最多只能传2张图片
                    </div>
                    <div class="img-list clearfix">
                        <div class="fl">
                            <img class="upload-img-1" src="/static/platform/v2.2.0/images/upload-1.png" alt="" onclick="org.picClick(1)">
                            <input type="file" name="path" class="path-1"  />
                            <input type="hidden" name = "legalPersonPic1" class="hidden-img-1"/>
                        </div>
                        <div class="fl">
                            <img class="upload-img-2" src="/static/platform/v2.2.0/images/upload-1.png" alt="" onclick="org.picClick(2)">
                            <input type="file" name="path" class="path-2"  />
                            <input type="hidden" name = "legalPersonPic2" class="hidden-img-2"/>
                        </div>
                    </div>
                </div>
                <br>
                <div style=" width: 500px; margin-left: 236px; color: gray; font-size: 14px;">
                    (PS:请将身份证正面放于胸前，拍摄正面半身照，确保身份证信息清晰可见，照片不得进行任何修改)
                </div>
            </li>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2" onclick="org.registerStepThree()">保存下一步</a>
            </li>
            <input type="hidden" name="id" class="orgId" value="${id}">
            <input type="hidden" name="token" class="token" value="${token}">
        </ul>
    </form>
</section>
<%@include file="/view/platform/context/footer.jsp"%>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script>
    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';
    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {

        $('.upload-img-1').attr('src', results.obj);
        $('.hidden-img-1').val(results.obj);
    });

    //绑定图片2
    element = '.path-2';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-2').attr('src', results.obj);
        $('.hidden-img-2').val(results.obj);
    });

</script>
</body>
</html>
