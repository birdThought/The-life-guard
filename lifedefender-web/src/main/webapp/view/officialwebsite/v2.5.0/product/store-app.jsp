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
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/organization.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    
    <title>机构端App</title>
    <style>
    	
    </style>
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
<section class="Platformsandsystems container"><p>您现在的位置<i class="iconfont icon-cursor"></i>产品与平台 | <span style="color: #44c660">机构端App</span></p></section> 
<div class="content">
    <h2>产品与平台</h2>
    <div class="container">
        <div class="row">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-default management" onclick="goModule('/product/platform')">健康管理平台</button>
                <button type="button" class="btn btn-default organization" onclick="goModule('/product/store-app')">机构端APP</button>
                <button type="button" class="btn btn-default user" onclick="goModule('/product/user-app')">用户端APP</button>
                <button type="button" class="btn btn-default health" onclick="goModule('/product/device')">智能健康设备</button>
                <!-- col-xs-6 col-sm-6 col-md-3 col-lg-3 -->
            </div>
        </div>
    </div>

    <div class="part container">
        <div class="row">
            <div class="phone col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <img src="/static/officialwebsite/v2.5.0/images/phone-1.jpg" alt="">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
                <p>
                    <strong>员工管理：</strong>
                    员工入职/离职管理、员工任务服务考核、员工业绩分析
                    <strong>会员管理系统：</strong>会员健康预警提示、会员病
                    种分类、会员异常前置、查看会员健康数据
                    （血压、血糖、心率、睡眠、体脂率、）、
                    查看会员电子病历、查看会员体检报告、查
                    看会员饮食记录、推送消息、批量导入会员
                </p>
                <div class="icon">
                    <h3>对会员实现“主动式”干预，提高粘性</h3>
                    <ul class="row">
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/getdata.jpg" alt=""><span>实时接收会员数据</span></li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/remind.jpg" alt=""><span>数据异常前置提醒</span></li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/warn.jpg" alt=""><span>数据异常短信预警</span></li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/file.jpg" alt=""><span>查看会员健康档案</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="part grey ">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-9 col-lg-6">
                    <div class="icon">
                        <h3>“互动式”会员营销，提高忠诚度</h3>
                        <ul class="row">
                            <li class="col-xs-6 col-sm-6"><img src="/static/officialwebsite/v2.5.0/images/consult.jpg" alt=""><span>在线咨询</span>
                                <small>支持语音、文字、图片<br>主动发起对话、消息提醒</small>
                            </li>
                            <li class="col-xs-6 col-sm-6"><img src="/static/officialwebsite/v2.5.0/images/class.jpg" alt=""><span>健康课堂</span>
                                <small>实时开课支持语音、文字<br>图片、容纳500人以上
                                </small>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="phone col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <img src="/static/officialwebsite/v2.5.0/images/phone-2.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
    <div class="part container">
        <div class="row">
            <div class="phone col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <img src="/static/officialwebsite/v2.5.0/images/phone-3.jpg" alt="">
            </div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
                <div class="icon">
                    <h3>“020”服务，线上集客，线下消费</h3>
                    <ul class="row online">
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/paycard.jpg" alt=""><span>移动支付功能</span>
                            <small>支持第三方平台支付</small>
                        </li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/service.jpg" alt=""><span>服务管理</span>
                            <small>含待办任务、已完成任务、待回复
                            </small>
                        </li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/order.jpg" alt=""><span>订单管理</span>
                            <small>查看或者查找订单明细
                            </small>
                        </li>
                        <li class="col-xs-6 col-sm-5"><img src="/static/officialwebsite/v2.5.0/images/profit.jpg" alt=""><span>收益管理</span>
                            <small>查看月度总收益、日收益及每位服务师收益</small>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="part grey">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
                    <div class="icon">
                        <h3>“互动式”会员营销，提高忠诚度</h3>
                        <ul class="row">
                            <li class="col-xs-6 col-sm-6"><img src="/static/officialwebsite/v2.5.0/images/vip.jpg" alt=""><span>会员管理</span>
                                <small class="add">
                                    添加会员、查找会员<br>
                                    查看会员健康档案<br>
                                    查看会员交易记录<br>
                                    向会员发送消息
                                </small>
                            </li>
                            <li class="col-xs-6 col-sm-6"><img src="/static/officialwebsite/v2.5.0/images/worker.jpg" alt=""><span>员工管理</span>
                                <small class="add">
                                    查找员工<br>
                                    查看员工档案<br>
                                    查看员工收益
                                </small>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="phone col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <img src="/static/officialwebsite/v2.5.0/images/phone-4.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
    <section class="content-bottom">
        <h4 class="products-des">携手共赢，与全球合作伙伴共建云端生态</h4>
        <p>加入生命守护合作伙伴计划，开创新业务，获取资源，快速成长</p>
    </section>
    <section class="friendlinks row container">
        <div class="hidden-xs"><img src="/static/officialwebsite/v2.5.0/images/chinaLife.png" alt=""></div>
        <div class="hidden-xs"><img src="/static/officialwebsite/v2.5.0/images/chinaMobile.png" alt=""></div>
        <div class="hidden-xs"><img src="/static/officialwebsite/v2.5.0/images/oldRogue.png" alt=""></div>
        <div class="hidden-xs"><img src="/static/officialwebsite/v2.5.0/images/beadhouse.png" alt=""></div>
    </section>
</div>

<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp"%>
</body>
</html>
<script>
    common.activityMenu(2, 3);

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
    function goModule(value) {
        window.location.href = '/index/v2.5' + value;
    }
</script>