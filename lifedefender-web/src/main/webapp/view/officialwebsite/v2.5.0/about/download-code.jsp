
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
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/contactus.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/nav-setting.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    <style>.down:nth-child(3n){margin-right:0}.icon-xiazai{cursor:pointer}</style>
    <title>二维码下载</title>
</head>
<body>
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
<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp"%>
<div class="banner">
    <img src="/static/officialwebsite/v2.5.0/images/banner.jpg" alt="banner">
</div>
<div class="route container">
    <ol class="breadcrumb">
        <li><a href="#">关于我们</a></li>
        <li class="active">二维码下载</li>
    </ol>
</div>
<div class="content container">
	<div id="down"><a href="" id="downLoad"></a></div>
    <div class="row">
        <div class="col-xs-12 col-sm-4 col-md-2 tree-nav">
            <ul class="row">
                <li class="company col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/introduction">公司简介</a></li>
                <li class="partner col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/partner">招募合作伙伴</a></li>
                <li class="us col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/contact" >联系我们</a></li>
                <li class="recruit col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/recruitment">人才招聘</a></li>
                <li class="load col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/download-code" style="color: #f40">二维码下载</a></li>
            </ul>
        </div>
        <div class="col-xs-12 col-sm-8 col-md-10 qrcode">
            <ul class="QRcode-content">
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/shuomingshu.jpg" alt="">
                    <i id="icon_xiazai_1" class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/shuomingshu.rar')"> </i>
                </li>
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/chanpincaozuo.jpg" alt="">
                    <i class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/chanpincaozuo.rar')"> </i>
                </li>
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/appcaozuo.jpg" alt="">
                    <i class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/appcaozuo.rar')"> </i>
                </li>
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/gongzhonghao.jpg" alt="">
                    <i class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/gongzhonghao.rar')"> </i>
                </li>
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/userapp.jpg" alt="">
                    <i class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/userapp.rar')"> </i>
                </li>
                <li class="down">
                    <img src="/static/officialwebsite/v2.5.0/images/jigouapp.jpg" alt="">
                    <i class="iconfont icon-xiazai" onclick="common.downloadCode('/static/officialwebsite/v2.5.0/images/jigouapp.rar')"> </i>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="cooperation" style="margin-top: 5rem">
    <h2>合作伙伴</h2>
    <p>加入生命守护合作伙伴计划，开创新业务，获取资源，快速成长</p>
      <section class="linkfriends container">
        <ul class="row">
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaLife.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaMobile.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/beadhouse.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/oldRogue.png" alt=""></li>
        </ul>
    </section>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>

</body>
</html>
<script>
    common.activityMenu(6, 5);

    var lis = $('.service-main-content-bottom li');
    var $width = $(window).width();
    if($width<650){
    	 $('.banner > img').attr('src','/static/officialwebsite/v2.5.0/images/small-banner.png')
    }
    lis.click(function () {
        if($width<650){
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing')
            $(this).find('h3').css({'color':"blue"}).parent('li').siblings('li').find('h3').css({'background':'#333'})
        }else {
            $(this).css({'transform':'scale(1.1)','z-index':11,'transition':'all 0.5s'}).siblings('li').css({'transform':'scale(1)','z-index':10,'transition':'all 0.5s'})
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing')
            $(this).find('h3').css({'color':"#228de8"}).parent('li').siblings('li').find('h3').css({'color':'#333'})
        }
    })
</script>
