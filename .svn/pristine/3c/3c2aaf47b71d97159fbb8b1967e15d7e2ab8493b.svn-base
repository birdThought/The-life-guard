
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>
    <meta charset="utf-8">
    <%--<meta name="flexible" content="initial-dpr=1" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="apple-touch-icon" href="favicon.png">
    <link rel="Shortcut Icon" href="favicon.png" type="image/x-icon">
    <title>健康档案</title>
    <style type="text/css">
        .none {
            display: none;
        }
        .file div{
            width: 50%;
            display: inline-block;
            text-align: center;
        }
        .file div:hover {
            opacity: 0.5;
        }
        .file div:active {
            opacity: 0.5;
        }
        .file img{
            width: 2.2rem;
            height: 1.8rem;
            border-radius: 1rem
        }
        .file p {
            color: #64CC77;
            font-size: 14px;
        }
        .remark p{
            line-height: 0.8rem;
            font-size: 14px;
        }
        p {
            font-size: 14px;
        }
        .tabs li {
            text-align: center;
            /*line-height: 2rem;*/
            width: 50%;
            display: inline-block;
            border-top: 1px solid #9a9a9a;
            font-size: 16px;
        }
        .tabs li.on {
            border-top-color: #64cc77;
            color: #64CC77;
        }
        .tabs li span {
            display: inline-block;
            width: 0.8rem;
            height: 0.8rem;
            margin-top: 0.2rem;
        }
        .tabs li:nth-of-type(1).on span {
            background: url(/static/mobile/wechat/images/health_records_sel.png) no-repeat;
            background-size: contain;
        }
        .tabs li:nth-of-type(1) span {
            background: url(/static/mobile/wechat/images/health_records_nor.png) no-repeat;
            background-size: contain;
        }
        .tabs li:nth-of-type(2).on span {
            background: url(/static/mobile/wechat/images/my_sel.png) no-repeat;
            background-size: contain;
        }
        .tabs li:nth-of-type(2) span {
            background: url(/static/mobile/wechat/images/my_nor.png) no-repeat;
            background-size: contain;
        }
        .my div {
            padding: 0.2rem;
        }
        .my p {
            height: 6%;
            padding: 0.5rem 0.6rem 0.5rem 0.4rem;
            border-bottom: 1px solid #F5F5F5;

        }
        .my .right-icon {
            position: absolute;
            right: 0px;
            width: 0.6rem;
            height: 0.6rem;
        }
      /*  .my p:nth-of-type(7) {
            border-bottom: none;
        }*/

    </style>
</head>
<body style="height: 100%;" ontouchstart>
<!-- 页面结构写在这里 -->
<div style="width: 100%; height: 100%;">
    <div class="content" style="height: 88%;">
        <div class="record" style="height: 100%;">
            <div style="text-align: center; /*padding: 0.8rem;*/ height: 30%">
                <img class="photo" src="/static/mobile/wechat/images/we-head.png"
                     style="width: 1.5rem; height: 1.5rem; border-radius: 1rem; margin-top: 10%;">
                <p class="username"></p>
            </div>
            <div class="file" style="/*height: 7rem;*/ height: 40%">
                <div onclick="goDetails('/medical/page')">
                    <img src="/static/mobile/wechat/images/we-record-1.png"
                         >
                    <p>病历记录</p>
                </div>
                <div style="float: right" onclick="goDetails('/physical/page')">
                    <img src="/static/mobile/wechat/images/we-record-2.png"
                         >
                    <p>体检报告</p>
                </div>
                <br><br><br>
                <div onclick="goDetails('/health-diary/page')">
                    <img src="/static/mobile/wechat/images/we-record-3.png"
                        >
                    <p>健康日记</p>
                </div>
                <div style="float: right" onclick="goDetails('/all-device/page')">
                    <img src="/static/mobile/wechat/images/we-record-4.png"
                         >
                    <p>健康数据</p>
                </div>
            </div>
            <div class="remark" style="text-align: center; /*padding: 1rem;*/height: 30%">
                <p style="color: #64CC77; margin-top: 10%;">
                    健康档案有什么作用？
                </p>
                <p>
                    您的隐私会得到严格的保护
                </p>

            </div>
        </div>
        <div class="my none" style="height: 100%;">
            <div style="height: 80%">
                <p style="height: 10%;">
                    <span style="vertical-align: middle; line-height: 1.2rem;">头像</span>
                    <img class="photo" src="/static/mobile/wechat/images/we-head.png"
                         style="width: 1.3rem; height: 1.3rem; border-radius: 1rem;
                         float: right; vertical-align: middle;">
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%--style="margin-top: 0.3rem;">--%>
                </p>
                <p>
                    <span>昵称</span>
                    <span class="username" style="float: right; color: #9a9a9a;"></span>
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%-->--%>
                </p>
                <p>
                    <span>性别</span>
                    <span class="gender" style="float: right; color: #9a9a9a;"></span>
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%-->--%>
                </p>
                <p>
                    <span>年龄</span>
                    <span class="age" style="float: right; color: #9a9a9a;"></span>
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%-->--%>
                </p>
                <p>
                    <span>身高</span>
                    <span class="height" style="float: right; color: #9a9a9a;"></span>
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%-->--%>
                </p>
                <p>
                    <span>体重</span>
                    <span class="weight" style="float: right; color: #9a9a9a;"></span>
                    <%--<img class="right-icon" src="/static/mobile/wechat/images/we-more.png"--%>
                         <%-->--%>
                </p>
                <p>
                    <span>三围</span>
                    <span class="measurements" style="float: right; color: #9a9a9a;"></span>
                   <%-- <img class="right-icon" src="/static/mobile/wechat/images/we-more.png"
                         >--%>
                </p>
                <p>
                    <!-- <span style="background-color: yellow;" align="center" onclick=""> -->
                     <span align="center" style="margin-left: 35%">
                     <img  style="height: 90%" src="/static/mobile/wechat/images/bright-shutdown.png" onclick="cancel()">
                     </span> 
                    <!-- </span> -->
                </p>
            </div>
            <div style="height: 20%"></div>
        </div>
    </div>
    <div class="tabs" style="height: 12%">
        <li class="on">
            <%--<img src="/static/mobile/wechat/images/health_records_sel.png" style="width: 0.8rem; margin-top: 0.3rem">--%>
            <span></span>
            <p>健康记录</p>
        </li>
        <li style="float: right">
            <%--<img src="/static/mobile/wechat/images/my_nor.png" style="width: 0.8rem; margin-top: 0.2rem">--%>
            <span style="margin-left: 0.1rem;"></span>
            <p>我</p>
        </li>
    </div>
</div>
</body>
</html>

<script src="/static/mobile/wechat/jquery-2.1.1.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
$(function () {
    $('.tabs li').on('click', function () {
        if ($(this).hasClass('on')) {
            return;
        }
        $(this).addClass('on').siblings().removeClass('on');
        $('.content').children('div').addClass('none');
        $('.content').children('div').eq($(this).index()).removeClass('none')
    })

//    document.body.addEventListener('touchstart', function () { //...空函数即可});

    getUserInfo();
})

    var getUserInfo = function () {
        $.ajax({
            async : true,
            cache : false,
            type: 'GET',
            contentType: 'application/json;charset=utf-8',
            url: '/wechat/user',
            data: null,
            beforeSend:function(){
                layer.load(2);
            },
            success: function(result){
//                layer.msg('获取用户信息成功')
                layer.closeAll('loading');
                if (result.success) {
                    $('.photo').attr('src', result.obj.photo);
                    $('.username').text(result.obj.realName);
                    $('.gender').text(result.obj.gender);
                    $('.age').text(result.obj.age);
                    $('.height').text(result.obj.height);
                    $('.weight').text(result.obj.weight);
                    $('.measurements').text(result.obj.bust + ' ' + result.obj.waist + ' ' + result.obj.hip);
                }
            }
        });
    }

    var goDetails = function (value) {
        window.location.href = '/wechat-record' + value;
    }
    
    var cancel = function(){
        window.location.href = '/wechat/unbind-account';
       /*  $.ajax({
            async : false,
            cache : false,
            type: 'GET',
            url: '/wechat/unbind-account',
            data: {
                
            },
            beforeSend:function(){
                layer.load(2);
            },
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            dataType: 'json',
            success: function (result) {
                layer.closeAll('loading');
                if(result.success){
                    
                }
            }
        }); */
    }
</script>
