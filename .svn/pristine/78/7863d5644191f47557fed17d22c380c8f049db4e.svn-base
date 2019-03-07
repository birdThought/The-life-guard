<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/base.css">
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/iconfont.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/common.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/contactus.css">
    <link rel="stylesheet" href="/static/officialwebsite/v2.5.0/css/map.css">
    <script src="/static/officialwebsite/v2.5.0/js/jquery.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/common.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/static/officialwebsite/v2.5.0/js/returnTop.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=TtbA6Q5hD6ZmU9FtP5g5kuHA7PTwhGwb"></script>
    <title>联系我们</title>
    <style>
        .cooperation {
            text-align: center;
            margin-top: 20px;
        }

        .cooperation h2 {
            font-size: 24px;
            margin-bottom: 24px;
        }

        .cooperation p {
            font-size: 12px;
        }

        .area2 {
            display: none;
        }

        .cooperation .roll > ul li {
            display: inline-block;
            margin: 50px 140px 44px 0;
        }

        #map label {
            max-width: none !important;
        }
    </style>
</head>
<body>
<div id="CfloatBtn" class="CfloatBtn">
    <ul>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"
               class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/zxzx1.gif" alt=""><b
                    style="display:block;font-size:1.2rem;font-weight:normal;color:#fff">在线咨询</b></a>
        </li>
        <li>
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"
               class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/qqlite.gif" alt=""><b
                    style="display:block;font-size:1.2rem;font-weight:normal;color:#000">QQ咨询</b></a>
        </li>
        <li class="li-list-three">
            <a rel="nofllow" href="tencent://Message/?Uin=1732745304&websiteName=sc.chinaz.com=&Menu=yes"
               class="wjdc"><img src="/static/officialwebsite/v2.5.0/images/dhzx.gif" alt=""><b
                    style="display:block;font-size:1.2rem;font-weight:normal;color:#000">联系方式</b></a>
            <div id="connact_method"><p>400-026-1003</p></div>
        </li>
        <li class="li-list-four">
            <a rel="nofllow" href="/index/v2.5/help/feedback" class="wjdc advice"><img
                    src="/static/officialwebsite/v2.5.0/images/advice.png" alt=""><b
                    style="display:block;font-size:1.2rem;font-weight:normal;color:#000">意见反馈</b></a>
        </li>
        <li>
            <span id="scroll_Top" class="scroll_Top" onclick="$.$w.windowScroll(0);">
                <img src="/static/officialwebsite/v2.5.0/images/returnTop.png" alt="">
                <b style="display: block;color: #fff;padding-bottom: 6px;">返回顶部</b>
            </span>
        </li>
    </ul>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/header.jsp" %>
<div class="banner" style="text-align: center">
    <img class="img-responsive" src="/static/officialwebsite/v2.5.0/images/banner.jpg" alt="banner">
</div>
<div class="route container">
    <ol class="breadcrumb">
        <li><a href="#">关于我们</a></li>
        <li class="active">联系我们</li>
    </ol>
</div>
<div class="content container">
    <div class="row">
        <div class="col-xs-12 col-sm-4 col-md-3 tree-nav">
            <ul class="row">
                <li class="company col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/introduction">公司简介</a></li>
                <li class="partner col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/partner">招募合作伙伴</a></li>
                <li class="us col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/contact"
                                                               style="color: #f40">联系我们</a></li>
                <li class="recruit col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/recruitment">人才招聘</a></li>
                <li class="load col-xs-6 col-sm-12 col-md-12"><a href="/test/v2.5/about/download-code">二维码下载</a></li>
            </ul>
        </div>
        <div class="col-xs-12 col-sm-8 col-md-9 message">
            <h3>联系我们</h3>
            <ul>
                <li>联系电话：400-0261-003</li>
                <li>公司座机：020-61067620</li>
                <li>传真：020-87237650</li>
                <li>地址：广州市天河区柯木塱园区10栋302</li>
            </ul>
            <div class="map-area">
                <div class="map-container">
                    <div id="allmap"></div>
                    <div class="tip" style="position: relative;z-index:1;">
                        <p style="position:absolute; bottom: 0;z-index: 500;line-height: 1!important;font-size: 14px;font-weight:bold;background: #fff;color: purple;margin: 0 0 10px 0;">
                            点击地图中建筑标识可以获取&lt;省内、省外>路线</p>
                    </div>
                    <div class="lineSearch">
                        <p style="font-size:14px;height:50px;padding-top: 10px;" class="clearfix">
                            <span class="btn-one"
                                  style="display: inline-block;width: auto;border: 1px solid #ddd;background: #ddd;padding: 5px 10px;line-height: 1;cursor: pointer;color: purple;float: left;">搜索广州市内路线：<i
                                    style="font-size: 1.6rem;color: skyblue;" class="iconfont icon-luxian"></i></span>
                            <span class="btn-two"
                                  style="display: inline-block;width: auto;border: 1px solid #ddd;background: #ddd;padding: 5px 10px;line-height: 1;cursor: pointer;color: purple;float: right;">搜索省外驾驶路线：<i
                                    style="font-size: 1.6rem;color: skyblue;" class="iconfont icon-luxian"></i></span>
                        </p>
                        <div class="content-area">
                            <div class="area1">
                                <p style="position: relative">
                                    <i style="position: absolute;color: purple; font-size: 1.7rem;top: 0.56rem;left: 0.56rem;"
                                       class="iconfont icon-qidian"></i>
                                    <input class="startPlace" type="text" placeholder="请输入广州市内出发起点">
                                </p>
                                <div style="height: 1.2rem;"></div>
                                <p style="position: relative;">
                                    <i style="position:absolute;color: purple; font-size: 1.7rem;top: 0.56rem;left: 0.56rem;"
                                       class="iconfont icon-zhongdian"></i>
                                    <input class="endPlace" type="text" value="广州通众电气实业有限公司">
                                </p>
                                <div style="height: 1.2rem;"></div>
                                <p style="margin: 0 auto;width: 20%;margin-bottom: 24px">
                                    <input class="busSearch" type="button"
                                           style="letter-spacing: 0.5rem;padding-left: 0;text-align:center;height: 3rem;background: skyblue;color: #fff;"
                                           value="搜索">
                                </p>
                            </div>
                        </div>
                        <div class="area2">
                            <p style="position: relative">
                                <i style="position: absolute;color: purple; font-size: 21px;top: 5px;left: 5px;"
                                   class="iconfont icon-qidian"></i>
                                <input class="CountryStartPlace" type="text" placeholder="请输入驾车出发地点">
                            </p>
                            <div style="height: 1.2rem;"></div>
                            <p style="position: relative;">
                                <i style="position:absolute;color: purple; font-size: 21px;top: 5px;left: 5px;"
                                   class="iconfont icon-zhongdian"></i>
                                <input class="CountryEndPlace" type="text" value="广州通众电气实业有限公司">
                            </p>
                            <div style="height: 1.2rem;"></div>
                            <p style="margin: 0 auto;width: 26%;margin-bottom: 24px">
                                <input class="PathSearch" type="button"
                                       style="letter-spacing: 1rem;height: 3rem;background: skyblue;color: #fff;"
                                       value="搜索">
                            </p>
                        </div>
                    </div>
                </div>
                <div class="showResult">
                    <div id="r-result"></div>
                </div>
            </div>
        </div>
        <script>
            // 百度地图API功能
            var map = new BMap.Map("allmap");
            map.centerAndZoom(new BMap.Point(113.4197935741, 23.1967139449), 18);  // 初始化地图,设置中心点坐标和地图级别
            $('.PathSearch').click(function(){
                $('#r-result').html();
                var CountryStart = $('.CountryStartPlace').val();
                var CountryEnd = $('.CountryEndPlace').val();
               pathSearch(CountryStart,CountryEnd)
            });
            var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, panel: "r-result", autoViewport: true}});
            function pathSearch(start,end){
                driving.search(start,end)
            }
            map.addControl(new BMap.MapTypeControl({
                mapTypes: [
                    BMAP_NORMAL_MAP,
                    BMAP_HYBRID_MAP
                ]
            }));
            map.setCurrentCity("广州");
            map.enableScrollWheelZoom(true);
            var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]});
            var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});
            var overView = new BMap.OverviewMapControl();
            var overViewOpen = new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});
            map.addControl(mapType1);
            var marker = new BMap.Marker(new BMap.Point(113.4197935741, 23.1967139449));
            map.addOverlay(marker);
            var label = new BMap.Label("广州通众公司", {offset: new BMap.Size(30, -3)});
            label.setStyle({
                width: '88px',
                color: '#fff',
                background: 'skyblue',
                border: '1px solid skyblue',
                height: '26px',
                fontSize: '13px',
                maxWidth: 'none'
            });
            marker.setLabel(label);
            var transit = new BMap.TransitRoute(map, {
                renderOptions: {map: map, panel: "r-result"}
            });
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    transit.search($('.startPlace').val(), $('.endPlace').val());
                }
            });
            $('.busSearch').on('click', function () {
                $('#r-result').html()
                transit.search($('.startPlace').val(), $('.endPlace').val());
            })
        </script>
    </div>
</div>
</div>
<div class="cooperation">
    <h2>合作伙伴</h2>
    <p>加入生命守护合作伙伴计划，开创新业务，获取资源，快速成长</p>
    <section class="linkfriends container">
        <ul class="row">
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block"
                                                                 src="/static/officialwebsite/v2.5.0/images/chinaLife.png"
                                                                 alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block"
                                                                 src="/static/officialwebsite/v2.5.0/images/chinaMobile.png"
                                                                 alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block"
                                                                 src="/static/officialwebsite/v2.5.0/images/beadhouse.png"
                                                                 alt=""></li>
            <li class="col-lg-3 col-md-3 col-sm-6 col-xs-6"><img class="img-responsive center-block"
                                                                 src="/static/officialwebsite/v2.5.0/images/oldRogue.png"
                                                                 alt=""></li>
        </ul>
    </section>
</div>
<%@include file="/view/officialwebsite/v2.5.0/common/footer.jsp" %>

</body>
</html>
<script>
    common.activityMenu(6, 3);
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
        $('.main-content').css({'width':'100%'}); $('#allmap').css({'width':'100%'});
        $('.BMap_mask').css({'width':'100%'}); $('.map-container').css({'width':'100%'});
        $('.btn-one').css({'padding':'5px 0'});$('.btn-two').css({'padding':'5px 0'});
        $('.message h3').css({'padding-left':'15px'}); $('.message ul').css({'padding-left':'15px'});
    }
    var lis = $('.service-main-content-bottom li');
    var $width = $(window).width();
    if ($width < 650) {
        $('.banner > img').attr('src', '/static/officialwebsite/v2.5.0/images/small-banner.png')
    }
    $('.btn-one').on('click', function () {
        $('.area1').fadeIn('300');
        $('.area2').fadeOut('fast')
    });
    $('.btn-two').on('click', function () {
        $('.area2').fadeIn('300');
        $('.area1').fadeOut('fast')
    });
    lis.click(function () {
        if ($width < 650) {
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing')
            $(this).find('h3').css({'color': "blue"}).parent('li').siblings('li').find('h3').css({'background': '#333'})
        } else {
            $(this).css({
                'transform': 'scale(1.1)',
                'z-index': 11,
                'transition': 'all 0.5s'
            }).siblings('li').css({'transform': 'scale(1)', 'z-index': 10, 'transition': 'all 0.5s'})
            $(this).find('i').addClass('xingxing').parent('li').siblings('li').find('i').removeClass('xingxing')
            $(this).find('h3').css({'color': "#228de8"}).parent('li').siblings('li').find('h3').css({'color': '#333'})
        }
    })
</script>