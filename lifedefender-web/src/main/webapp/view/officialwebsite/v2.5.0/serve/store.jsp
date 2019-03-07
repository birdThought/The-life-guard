
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
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/serveice001.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    <title>专业服务-保健医生</title>
    <style>
        .content-left img{
            height:180px;
        }
        .content-right p {
        	text-indent:2em;
        }
    </style>
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
    <h2>健康机构服务</h2>
    <div class="row">
        <ul class="clearfix container store-list">
       </ul>
    </div>
</article>
<div class="cooperation" style="margin-top: 50px;">
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
    common.activityMenu(3, 4);
    listStore();

    var lis = $('.service-main-content-bottom li');
    var $width = $(window).width();
    lis.click(function () {
        if($width<650){
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing');
            $(this).find('h3').css({'color':"blue"}).parent('li').siblings('li').find('h3').css({'background':'#333'})
        }else {
            $(this).css({'transform':'scale(1.1)','z-index':11,'transition':'all 0.5s'}).siblings('li').css({'transform':'scale(1)','z-index':10,'transition':'all 0.5s'})
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing');
            $(this).find('h3').css({'color':"#228de8"}).parent('li').siblings('li').find('h3').css({'color':'#333'})
        }
    })
    // $(document).off('click.bs.dropdown.data-api');

    /*var goDetails = function (value) {
        window.location.href = '/test/v2.5/serve/store-details';
    }*/

    /**
     * 获取机构列表
     */
    function listStore() {
        var page = 1;
        var url = '/index/v2.5/serve/stores/' + page;
        $.ajax({
            type: 'GET',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            url: url,
            data: null,
            success: function (result) {
                if (result.success) {
                    renderStore(result.obj)
                }
            }
        });
    }

    /**
     * document 机构列表
     */
    var renderStore = function (list) {
        var str = '';
        for (var i in list) {
            var logo = list[i].logo;
            var orgName = list[i].orgName;
            var about = list[i].about;
            about = about == null ? '暂无介绍' : about.substr(0,60)+'....';
            var street = list[i].street;
            street = street == null ? '暂无地址' : street;
            var id = list[i].id;
            str += '<li class="col-lg-4 clearfix col-sm-6 col-md-6  col-xs-12"> ' +
                '<div class="content-left col-lg-6 col-sm-6 col-md-6  col-xs-12"> ' +
                '<img src="'+ logo +'" class="img-responsive center-block" alt=""> ' +
                '</div> ' +
                '<div class="content-right col-lg-6 col-sm-6 col-md-6  col-xs-12"> ' +
                '<h2>'+ orgName +'</h2> ' +
                '<p>'+ about +'<br> ' +
                ''+ street +'<a href="/test/v2.5/serve/store-details/'+ id +'">详情介绍</a> </p> </div> </li>';
        }
        $('.store-list').append(str);
    }

</script>