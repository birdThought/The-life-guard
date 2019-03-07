
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
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/products.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/platforms.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/organization.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    <title>用户端App</title>
</head>
<body>
<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp"%>
<section class="Platformsandsystems container"><p>您现在的位置<i class="iconfont icon-cursor"></i>产品与平台 | <span style="color: #44c660">用户端App</span></p></section> 
<section class="systemlist container">
    <div class="content">
    	<h2>产品与平台</h2>
    <div class="container">
        <div class="row">
            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-default management" onclick="goModule('/product/platform')">健康管理平台</button>
                <button type="button" class="btn btn-default management" onclick="goModule('/product/store-app')">机构端APP</button>
                <button type="button" class="btn btn-default organization" onclick="goModule('/product/user-app')">用户端APP</button>
                <button type="button" class="btn btn-default health" onclick="goModule('/product/device')">智能健康设备</button>
            </div>
        </div>
    </div>
    </div>
</section>
<section class="systemlist-machine menu-list ">
    <div class="menu-list-one  menu-area-one text-center" id="organization">
        <div class="container">
            <div class="row">
                <ol>
                    <li class="col-lg-4">
                        <img class="showdata001 img-ressponsive center-block" src="/static/officialwebsite/v2.5.0/images/showdata001.png" alt="">
                    </li>
                    <li class="col-lg-4 platform-des">
                        <ul>
                            <li>
                                <h5 class="head-line"></h5>
                                <h1>健康测量与预警</h1>
                                <h2>7X24小时监测您的健康</h2>
                            </li>
                            <li>
                                <h3>健康测量</h3>
                                <p>APP连接十余款智能</p>
                                <p>健康检测设备</p>
                            </li>
                            <li>
                                <h3>健康分析</h3>
                                <p>APP智能化分析测量结果，</p>
                                <p>并给出康复建议</p>
                            </li>
                            <li>
                                <h3>数据异常预警</h3>
                                <p>健康数据异常，平台发送</p>
                                <p>预警短信到联系人手机</p>
                            </li>
                        </ul>
                    </li>
                    <li class="col-xs-12 col-lg-4">
                        <img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/machine-list.png" alt="">
                    </li>
                </ol>
            </div>

        </div>
    </div>
    <div class="menu-list-one  menu-area-two" id="">
        <div class="container">
            <div class="row">
                <ol class="clearfix">
                    <li class="col-lg-6">
                        <ul>
                            <li>
                                <h5 class="head-line head-line-two"></h5>
                                <h1 class="text-center">健康档案</h1>
                                <h2 class="text-center">您的健康生活管家</h2>
                                <img class="img-responsive center-block" src="/static/officialwebsite/v2.5.0/images/history001.png" alt="">
                            </li>
                        </ul>
                    </li>
                    <li id="history_002"  class="history002 col-lg-6"><img class="img-responsive center-block" src= "/static/officialwebsite/v2.5.0/images/history002.png" alt=""></li>
                </ol>
            </div>

        </div>
    </div>
    <div class="menu-list-one  menu-area-three " id="">
        <div class="container">
            <div class="row menu-area-three-div" style="">
                <ol class="clearfix">
                    <li class="history002 col-lg-4 "><img class="img-responsive center-block" src= "/static/officialwebsite/v2.5.0/images/history003.png" alt=""></li>
                    <li class="history003 col-lg-4">
                        <ul>
                            <li>
                                <h5 class="head-line"></h5>
                                <h1>健康咨询与健康课堂</h1>
                                <h2>家庭医生时刻在身边</h2>
                            </li>
                            <li>
                                <h3>健康咨询</h3>
                                <p>咨询在线医生、支持语音、</p>
                                <p>文字、图片、消息提醒</p>
                            </li>
                            <li>
                                <h3>健康课堂</h3>
                                <p>加入专家直播课堂、支持语音，</p>
                                <p>文字、图片、消息提醒</p>
                                <img class="healthclass" src="/static/officialwebsite/v2.5.0/images/healthclass.png" alt="">
                                <img class="broatfast" src="/static/officialwebsite/v2.5.0/images/broatfast.png" alt="">
                            </li>
                        </ul>
                    </li>
                    <li  class="doctor001 col-lg-4">
                        <img src="/static/officialwebsite/v2.5.0/images/doctor001.png" alt="">
                    </li>
                </ol>
            </div>

        </div>
    </div>
    <div class="menu-list-one  menu-area-four" id="">
        <div class="container">
            <div class="row">
                <ol class="clearfix">
                    <li class="col-lg-6 o2o-service">
                        <ul>
                            <li class="li-spac-one">
                                <h5 class="head-line "></h5>
                                <h1>O2O服务</h1>
                                <h2>全国知名健康机构为您服务</h2>
                                <p>线上优惠购买，线下享受服务</p>
                                <p>养生门店、家政中心、理疗馆、国医馆等的</p>
                                <p>优惠券和折扣券</p>
                            </li>
                            <li>
                                <div class="service-show"><i class="iconfont icon-xianshangxiaoshuaigongju"></i><span>线上</span></div>
                                <div class="service-show"><i class="iconfont icon-xianxia"></i><span>线下</span></div>
                            </li>
                        </ul>
                    </li>
                    <li class="history002 col-lg-6"><img class="img-responsive " src= "/static/officialwebsite/v2.5.0/images/myorder001.png" alt=""></li>
                </ol>
            </div>
        </div>
    </div>
    <div class="menu-list-one  menu-area-five " id="">
        <div class="container">
            <div class="row">
                <ol class="clearfix">
                    <li style="" class="history002 li-spec-four col-lg-6"><img class="img-responsive" src= "/static/officialwebsite/v2.5.0/images/service002.png" alt=""></li>
                    <li class="col-lg-6">
                        <ul>
                            <li>
                                <h5 style="margin:0" class="head-line"></h5>
                                <h1 style="margin-bottom: 3rem;">共享健康服务</h1>
                                <h2 style="color: #333;">共享设备测量点</h2>
                                <p>免费体检站点定位和健康类服务机构定位，</p>
                                <p style="margin-bottom: 4rem">总有一家在您附近</p>
                                <h2 style="color: #333;">多样化的健康服务</h2>
                                <p>康复理疗、健康咨询、名医预约、就医绿色</p>
                                <p style="margin-bottom: 4rem">通道、上门护理、居家服务</p>
                                <div class=" service-title-area row">
                                    <h6 class="col-lg-3 col-md-2 col-xs-3"><i class="iconfont icon-jiankang"></i><span>健康咨询</span></h6>
                                    <h6 class="col-lg-3 col-md-3 col-xs-3"><i class="iconfont icon-jiankangzhuanqu"></i><span>康复治疗</span></h6>
                                    <h6 class="col-lg-3 col-md-3 col-xs-3"><i class="iconfont icon-fangzi"></i><span>居家服务</span></h6>
                                    <h6 class="col-lg-3 col-md-3 col-xs-3"><i class="iconfont icon-yisheng"></i><span>名义预约</span></h6>
                                </div>
                            </li>
                        </ul>
                    </li>

                </ol>
            </div>
        </div>
    </div>
    <div class="menu-list-one  menu-area-six " id="">
        <div class="container">
            <div class="row">
                <ol class="clearfix">
                    <li class="col-lg-6 li-spec-two">
                        <ul>
                            <li>
                                <h5 style="margin: 0;" class="head-line"></h5>
                                <h1>更多服务</h1>
                                <div>
                                    <h2 class="feature">“家庭组”功能</h2>
                                    <p>可添加家庭成员，随时切换账号查看家属健康数据，对个人和家庭的健康状况进行管理。</p></div>
                                <div>
                                    <h2 class="feature">用药提醒服务</h2>
                                    <p>定时提醒用药，以防错过用药时间。</p>
                                </div>
                                <div>
                                    <h2 class="feature">自我诊断</h2>
                                    <p>中医辩证体质、中风风险、亚健康测试等自我诊断测试。</p>
                                </div>
                                <div>
                                    <h2 class="feature">健康指数</h2>
                                    <p>根据健康测量数据进行健康“评分”</p>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="history002 li-spec-three col-lg-6"><img class="img-responsive" src= "/static/officialwebsite/v2.5.0/images/data002.png" alt=""></li>
                </ol>
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
</section>

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
<script>
    common.activityMenu(2, 2);

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