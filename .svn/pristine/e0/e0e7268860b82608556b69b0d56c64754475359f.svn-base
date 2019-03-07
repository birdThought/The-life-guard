
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/base.css">
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/iconfont.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/common.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/supportcenter.css">
     
    <title>问题留言板</title>
</head>
<body>

<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp"%>
<article class="main-content container">
    <section class="content-top"><p>您现在的位置   |  帮助中心  |  <span>问题留言板</span></p></section>
    <section class="content-body clearfix">
        <div class="content-list-left">
            <ul>
                <li><a href="/index/v2.5/help/product-instruction">产品说明书</a></li>
                <li><a href="/index/v2.5/help/product-video">产品视频介绍</a></li>
                <li><a href="javascript:;">常见问题</a></li>
                <li><a href="/register">机构注册</a></li>
                <li><a href="javascript:;" class="active">问题留言板</a></li>
            </ul>
        </div>
        <div class="content-list-right">
            <form id="feed-back">
            <ol class="questionsubmit">
                <li><label for="">姓名：</label><input  placeholder="请输入您的姓名"  type="text" name="name"></li>
                <li><label for="">电话：</label><input placeholder="请输入您的电话"  type="number" name="mobile"></li>
                <li><label for="">E-mail：</label><input  placeholder="请输入您的邮箱" type="email" name="email"></li>
                <li class="clearfix">
                    <textarea class="input content" id="saytext" placeholder="请输入想要反馈的问题" name="content"></textarea>
                    <p class="clearfix">
                        <span class="emotion"><img src="/static/officialwebsite/v2.5.0/images/emoji/1.gif" alt=""></span>
                        <b>(输入200字以内)</b>
                        <input id="submit" type="button" class="sub_btn" value="提交" onclick="submitFeedBack()">
                    </p>
                </li>
            </ol>
            </form>
        </div>
    </section>
</article>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>
<div id="CfloatBtn" class="CfloatBtn">
    <ul>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/zxzx1.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#fff">在线咨询</b></a>
        </li>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/qqlite.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">QQ咨询</b></a>
        </li>
        <li class="li-list-three">
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"  class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/dhzx.gif" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">联系方式</b></a>
            <div id="connact_method"><p>400-026-1003</p></div>
        </li>
        <li class="li-list-four">
            <a rel="nofllow" href="/index/v2.5/help/feedback" class="wjdc advice"><img src="/static/officialwebsite/v2.5.0/images/advice.png" alt=""><b style="display:block;font-size:1.2rem;font-weight:normal;color:#000">意见反馈</b></a>
        </li>
        <li>
            <span id="scroll_Top" class="scroll_Top" onclick="$.$w.windowScroll(0);">
                <img src="/static/officialwebsite/v2.5.0/images/returnTop.png" alt="">
                <b style="display: block;color: #fff;padding-bottom: 6px;">返回顶部</b>
            </span>
        </li>
    </ul>
</div>
</body>
</html>
<script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/jquery.qqFace.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
<script>
    common.activityMenu(5, 4);
    $('#navbar_nav > li:nth-of-type(5) > a').addClass('active')
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
        $('.content-list-right').css({'width':'100%','border':'none','padding-left':'0'})
        $('.content-list-left').css({'float':'none'})
        $('.content-list-left li').css({'text-align':'center'})
    }
    $(function(){

        $('.emotion').qqFace({

            id : 'facebox',

            assign:'saytext',

            path:'/static/officialwebsite/v2.5.0/images/emoji/'	//表情存放的路径

        });

        $(".sub_btn").click(function(){

            var str = $("#saytext").val();

            $("#show").html(replace_em(str));

        });

    });
    function replace_em(str){

        str = str.replace(/\</g,'&lt;');

        str = str.replace(/\>/g,'&gt;');

        str = str.replace(/\n/g,'<br/>');

        str = str.replace(/\[em_([0-9]*)\]/g,'<img src="/static/officialwebsite/v2.5.0/images/emoji/$1.gif" border="0" />');

        return str;

    }
    
    function submitFeedBack() {
        var name = $('input[name="name"]').val();
        var mobile = $('input[name="mobile"]').val();
        var email = $('input[name="email"]').val();
        var content = $('.content').val();
        if(name == '' || mobile == '' || email == '' || content == '') {
            layer.msg('请完善信息')
            return;
        }
        var data = {
            name: name,
            mobile: mobile,
            email: email,
            content: content
        }
        var url = '/index/v2.5/help/feedback';
        $.ajax({
            type : 'POST',
            contentType : 'application/json; charset=utf-8',
            url : url,
            data : JSON.stringify(data),
            success : function(result) {
                if (result.success) {
                    layer.msg('提交成功！');
                    return;
                }
                layer.msg('提交失敗')
            }
        });
    }
</script>
<body>

</body>
</html>
