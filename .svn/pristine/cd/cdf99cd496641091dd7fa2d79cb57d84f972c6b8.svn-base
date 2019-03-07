
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
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/questionnaire.css">
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    <title>体质分析</title>
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
<section class="item-content physicalModel">
    <div class="container">
        <div>
            <div class="item-title">
                <h3>中医体质评估问卷</h3>
            </div>
            <p class="item-content-border">
                中医体质辩证是根据中华中医药学会标准来通过测试题目来判断体质现象，然后为诊断和治疗疾病提供重要依据。虽然题目比较多，但是准确性高，请耐心完成。
            </p>
        </div>
        <div class="item-quetion">
            <h4 class="item-sub" v-cloak>第{{currentTopicNumber}}题,共{{totalTopic}}道题</h4>
            <form>
                <h4 v-cloak>{{currentTopicNumber}}. {{currentTopicName}}</h4>
                <ul>
                    <li v-cloak v-for = "(o, index) in paperOptions">
                        <input type="radio" name="10" :id="checkedCss(index)" @click = "nextTopic(o.score, currentTopicType, special)" class="magic-radio">
                        <label :for="checkedCss(index)">{{o.name}}</label>
                    </li>
                </ul>
            </form>
            <p class="text-center">
                <button v-cloak v-if = "gender" @click = "skipTopic()" class="btn" style = "background-color: chocolate;">跳过</button>
                <button v-cloak v-if = "(currentTopicNumber > 1)" @click = "preTopic()" class="btn">上一题</button>
            </p>
        </div>

    </div>
    <div class="cooperation" style="margin-top: 8rem;">
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
</section>
<section class="container">
    <section class="teamwork">
        <h4 class="text-center">生命守护合作企业</h4>
        <p class="text-center">加入生命守护合作伙伴计划，开创新业务，获取资源，快速成长</p>
    </section>
    <section class="linkfriends container">
        <ul class="row">
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaLife.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/chinaMobile.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/beadhouse.png" alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/oldRogue.png" alt=""></li>
        </ul>
    </section>
</section>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>

</body>
</html>
<script type="text/javascript" src="/static/plugins/highcharts/highcharts.js"></script>
<script src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/officialwebsite/v2.5.0/js/paper/physical-paper.js"></script>

<script>
    common.activityMenu(3, 1);

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

    var goDetails = function () {
        window.location.href = '/test/v2.5/serve/doctor-details';
    }
    vueModel.getPhysical().paper = '${paper}' == '[]' ? '' : JSON.parse('${paper}');
    vueModel.getPhysical().paperOptions = '${paperOption}' == '[]' ? '' : JSON.parse('${paperOption}');

</script>