<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <t:base type="jquery2.11,layer"></t:base>
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">

    <meta http-equiv="Cache-Control" content="no-transform " /> 

    <meta http-equiv="Cache-Control" content="no-transform " />
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css?v=1.0.0">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css?v=1.0.0">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
    <title>生命守护官网  健康管理系统</title>
    <style>
       .news-list a {
            margin-right: 50px;
            color:#566;
            font-size:13px;
            line-height:1;
            height:30px;
            
       }
       .news-list a > i {
       		margin-left:20px;
       		color:#abb;
       		font-size:12px;
       }
       .scroll-news {
       		height:242px;
       }
    </style>
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-index-content">
    <div class="banner banner_wrapper" style="height: 450px; position: relative;">
        <div id="slider" class="slider">
            <div class="conbox" style="height: 450px;">
                <c:forEach items="${banners}" var="b">
                <div style="position: absolute; z-index: 1; opacity:1; width: 100%">
                 <a href="javascript:void(0)" style="width: 100%; margin: 0 auto; display: block; background: url('${b}') no-repeat center top; height: 450px;">
                    </a>
                </div>
                </c:forEach>
             
            </div>
            <div class="list_banner_box">
                <div class="switcher switcher_dot clearfix">
                    <span class="dot cur">1</span>
                    <span class="dot">2</span>
                    <span class="dot">3</span>
                </div>
            </div>
        </div>
        <!--  -->
        <div id="newsList">
        <i class="information">资讯：</i>
        <i class="newsLogo"></i>
   			<div id=demo style="overflow:hidden;" align=center>
                <table height=50 border=0 align=center cellpadding=0 cellspacing=0 cellspace=0 >
                    <tr class="news-list">
                        <td valign=top bgcolor=ffffff  id=marquePic1 >
                            <pre>
                               <a href="http://www.lifekeepers.cn/informationControl.do?inforLook&id=223" target="_blank">2030年北京平均人均寿命82岁，但超过70%死于此病<i>[2017-9-25]</i></a><a href="http://www.lifekeepers.cn/informationControl.do?inforLook&id=219" target="_blank">看懂血常规化验单先记住这六项<i>[2017-9-13]</i></a><a href="http://www.toutiao.com/i6460280104479097357/?tt_from=mobile_qq&utm_campaign=client_share&app=news_article&utm_source=mobile_qq&iid=15669016157&utm_medium=toutiao_ios" target="_blank">糖尿病友喝水技巧，看了不吃亏<i>[2017-9-26]</i></a><a href="http://www.lifekeepers.cn/informationControl.do?inforLook&id=206" target="_blank">慢病疾病呈现全球化，中国慢病管理从何开始？<i>[2017-8-22]</i></a><a href="https://m.zjurl.cn/item/6429578994328273409/?iid=15669016157&app=news_article&tt_from=mobile_qq&utm_source=mobile_qq&utm_medium=toutiao_ios&utm_campaign=client_share" target="_blank">智能健康包能为居民解决什么问题？”<i>[2017-6-09]</i></a><a href="http://www.lifekeepers.cn/informationControl.do?inforLook&id=181" target="_blank">你以往的血压测量方法可能错了！看正确的操作方法<i>[2017-7-10]</i></a>
                            </pre>
                        </td>
                        <td valign=top bgcolor=ffffff id=marquePic2>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
       	
       		
   		<!--  -->
</div>

        </div>
    </div>
    <!--banner end-->
    <div class="appAndNews">
        <div class="banxin clearfix">
            <div class="app clearfix">
                <ul class="fl download-link">
                    <li>
                        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.example.tzdq.lifeshsmanager" target="_blank">守护师（机构端）</a>
                        <div class="none ">
                            <!-- <img src="/static/images/index/serviceApp.jpg" alt=""> -->
                        </div>
                    </li>
                    <li>
                        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.mgc.lifeguardian" target="_blank">生命守护（用户端）</a>
                        <div class="none">
                      
                        </div>
                    </li>
                </ul>
                <div class="fr">
                    <img src="/static/images/index/QRCode.png" alt="">
                    <h4>关注微信公众号</h4>
                </div>
            </div>
            <div class="news">
                <div class="clearfix">
                    <h4 class="fl" style="font-weight:700;color:#369239;">生命守护官方宣传片</h4>
                    <span class="fr"><a href="informationControl.do?newsIndex" target="_blank" class="a-more">更多资讯</a></span>
                </div>
                <div id="scroll_news" class="scroll-news" style="position:relative">
             <!--    <video class="video" style="width:100%;height:100%;position:absolute" controls loop autoplay>
                      <source src="http://music-one.html81.com/lifes.mp4">
                      your browser does not support the video(你的浏览器不支持)
                </video>  -->   
            </div>
        </div>
    </div>
    <div class="platform">
        <div class="banxin clearfix">
            <div class="index-title">
                <h3>平台功能</h3>
                <p>PLATFORM  FUNCTION</p>
            </div>
            <div class="fl">
                <ul class="functions">
                    <li>
                        <img src="/static/images/index/part_1.png" alt="img-responsive">
                        <h4>测量设备</h4>
                        <p>科学测量 掌握健康</p>
                    </li>
                    <li>
                        <img src="/static/images/index/part_2.png" alt="img-responsive">
                        <h4>疾病预警</h4>
                        <p>健康异常 智能提醒</p>
                    </li>
                    <li>
                        <img src="/static/images/index/part_3.png" alt="img-responsive">
                        <h4>健康咨询</h4>
                        <p>身体不适 轻松咨询</p>
                    </li>
                    <li>
                        <img src="/static/images/index/part_4.png" alt="img-responsive">
                        <h4>健康课堂</h4>
                        <p>远程授课 健康群聊</p>
                    </li>
                    <li>
                        <img src="/static/images/index/part_5.png" alt="img-responsive">
                        <h4>健康档案</h4>
                        <p>健康记录 一查便知</p>
                    </li>
                    <li>
                        <img src="/static/images/index/part_6.png" alt="img-responsive">
                        <h4>健康评估</h4>
                        <p>问卷填写 给出建议</p>
                    </li>
                </ul>
            </div>
            <div class="fr">
                <img src="/static/images/index/phone.png" alt="">
            </div>
        </div>
    </div>
    <div class="device">
        <div class="banxin">
            <div class="index-title">
                <h3>多种智能设备连接</h3>
                <p>INTELLIGENT DEVICE CONNECTION</p>
            </div>
            <div>
                <ul class="item-device">
                    <li>
                        <img src="/static/images/index/link_device.png" alt="">
                    </li>
                    <li>
                        <img src="/static/images/index/linkImg.png" alt="">
                    </li>
                    <li>
                        <p class="device-introduce">APP蓝牙智能连接血压计、血糖仪、体脂秤、心率手环、肺活仪、血氧仪、尿液分析仪、24H体温监测仪等多种便携式设备。</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="cooperaters">
        <div class="tab-item banxin">
            <ul class="tab-nav">
                <li class="current">友情链接</li>
                <li>合作伙伴</li>
            </ul>
            <div class="tab-content">
                <ul>
                    <li><a href="http://netfield.cn" target="_blank">广州通众官网</a></li>
                    <li><a href="https://yushun1001.taobao.com/?spm=2013.1.1000126.d21.dfGWSY" target="_blank">生命守护特卖店</a></li>
                    <li><a href="http://www.arche.net.cn/" target="_blank">广州本和原环保科技有限公司</a></li>
                </ul>
                <ul class="cooper">
                    <li><a href="https://www.baidu.com"><img src="/static/images/index/chinaLife.png" alt=""></a></li>
                    <li><a href="#"><img src="/static/images/index/chinaMobile.png" alt=""></a></li>
                    <li><a href="#"><img src="/static/images/index/beadhouse.png" alt=""></a></li>
                    <li><a href="#"><img src="/static/images/index/oldRogue.png" alt=""></a></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>

<script type="text/javascript" src="/static/plugins/xslider/jquery.xslider.js"></script>
<script>
    $(function(){
        $('#slider').Xslider({ affect: 'fade', ctag: 'div', stag: 'span' });
        var $this = $(".scroll-news");
        var scrollTimer;
        $this.hover(function(){
            clearInterval(scrollTimer);
        },function(){
            scrollTimer = setInterval(function(){
                scrollNews( $this );
            }, 2000 );
        }).trigger("mouseout");
    });
    function scrollNews(obj){
        var $self = obj.find("ul:first");
        var lineHeight = $self.find("li:first").height();
        $self.animate({ "margin-top" : -lineHeight +"px" },600 , function(){
            $self.css({"margin-top":"0px"}).find("li:first").appendTo($self);
        })
    }
    $(".tab-content ul").not($(".tab-content ul").eq(0)).hide();
    $(".tab-item .tab-nav > li ").click(function(event) {
        $(this).attr("class","current").siblings().removeClass("current");
        $(".tab-content > ul").hide().eq($(this).index()).show();
    });
 
    
</script>
<script>
var speed=15;
function Marquee(){
    if(demo.scrollLeft>=marquePic1.scrollWidth)
    {
        demo.scrollLeft=0;
    }
    else
    {
        demo.scrollLeft++;
    }
}
 marquePic2.innerHTML=marquePic1.innerHTML; 
var MyMar=setInterval(Marquee,speed)
demo.onmouseover=function() { clearInterval(MyMar); }
demo.onmouseout=function() { MyMar=setInterval(Marquee,speed);
}
Marquee();
</script>
<script>
var str = '';
str+="<embed class='video' src='http://player.video.qiyi.com/61db02da6fb30db64ae14e975e168bde/0/0/w_19rvdsatgt.swf-albumId=9508566209-tvId=9508566209-isPurchase=0-cnId=27-isAutoPlay=true'  allowFullScreen='true' quality='high' width='100%' height='100%' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash'>"
str+="</embed>" 
str+="<embed class='video' src='http://player.video.qiyi.com/61db02da6fb30db64ae14e975e168bde/0/0/w_19rvdsatgt.swf-albumId=9508566209-tvId=9508566209-isPurchase=0-cnId=27-isAutoPlay=true'  allowFullScreen='true' quality='high' width='100%' height='100%' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash'>"
str+="</embed>" ;
var str2="";

str2+="<iframe class='video' height=100% width=100% src='http://player.youku.com/embed/XMjc0NDE3ODE2OA=='  frameborder=0 'allowfullscreen' '></iframe>";
    var scroll_news = document.getElementById("scroll_news");
      if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
       scroll_news.innerHTML=str2
} else {
   scroll_news.innerHTML=str;           
}
</script>
</body>
</html>
