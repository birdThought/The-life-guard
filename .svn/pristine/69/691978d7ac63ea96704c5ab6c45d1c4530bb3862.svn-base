
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
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/hezuoanli.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>专业服务-保健医生</title>
</head>
<body>
<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp"%>
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
<article>
    <h2>合作案例</h2>
    <div class="row">
        <ul class="clearfix">
            <li onclick="goDetails('case-1')" class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo001.png" class="img-responsive " alt="">
                <p>生命守护健康管理系统</p>
                <s></s>
                <strong class="line-gradient">生命守护健康管理系统</strong>
            </li>

            <li onclick="goDetails('case-2')" class="col-lg-4 col-sm-6  col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo003.png" class="img-responsive " alt="">
                <p>康复机构用户健康管理系统</p>
                <s></s>
                <strong class="line-gradient">康复机构用户健康管理系统</strong>
            </li>
            <li onclick="goDetails('case-3')" class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo004.png" class="img-responsive " alt="">
                <p>养老机构安全健康监护管</p>
                <s></s>
                <strong class="line-gradient">养老机构安全健康监护管</strong>
            </li>
            <li onclick="goDetails('case-4')" class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo005.png" class="img-responsive " alt="">
                <p>中医养生用户健康管理系统</p>
                <s></s>
                <strong class="line-gradient">中医养生用户健康管理系统</strong>
            </li>
            <li onclick="goDetails('case-5')" class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo006.png" class="img-responsive " alt="">
                <p>减肥塑身用户健康量化管理系统</p>
                <s></s>
                <strong class="line-gradient">减肥塑身用户健康量化管理系统</strong>
            </li>
            <li onclick="goDetails('case-6')" class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
                <img src="/static/officialwebsite/v2.5.0/images/hezuo002.png" class="img-responsive " alt="">
                <p>家庭健康管理系统</p>
                <s></s>
                <strong class="line-gradient">家庭健康管理系统</strong>
            </li>
        </ul>
    </div>
</article>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>

</body>
</html>
<script>
    common.activityMenu(4, 0);

    var lis = $('.service-main-content-bottom li');
    var $width = $(window).width();
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
    // $(document).off('click.bs.dropdown.data-api');

    var goDetails = function (value) {
        window.location.href = '/test/v2.5/case/' + value;
    }
</script>