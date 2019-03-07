<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11"></t:base>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/animate.css"> 
    <link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
    <link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
    <link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
   
    <title>生命守护官网  健康管理系统</title>
    <style>
    	.device_1 > ul > li:hover {
    		box-shadow:3px 3px 15px #aaa;
    		transition:all 0.5s;
    		transition:scale(1.03)
    	}
    	#popup {
    	 	width:500px;
    	 	height:200px;
    	 	color:#11c660;
            position: fixed;
            opacity: 0;
            letter-spacing:1.5px;
            text-align: center;
            line-height: 200px;
            top: 50%;
            left: -250px;;
            margin-left: -250px;
            margin-top: -100px;
            font-size: 25px;
            font-weight: bold;
          	z-index:0;
          	font-family:cursive;
          	background-image:url('/static/images/tip_icon.png');
          	background-size:contain;
    	}
    </style>
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-device-content">
    <div class="banner">
        <img src="/static/images/index/appImg.png" alt="">
    </div>
    <div class="banxin">
        <div class="device_test">
            <div>
                <img src="/static/images/index/bodyTest.png" alt="">
            </div>
            <div>
                <h4>健康设备/体征测量</h4>
                <p>科学测量 &nbsp;掌握健康</p>
            </div>
        </div>
        <!-- 智能设备 -->
        <div class="device_1">
            <div class="device-title">
                <h3>智能设备</h3>
            </div>
           
            <ul id="equipment_list" class="clearfix">
                    <li>
                        <div><img src="static/images/device/no_circle/BloodPressure.png" alt=""></div>
                        <h4>血压计</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1MzMyNzkyNA==.html?spm=a2hzp.8253869.0.0" title="点击查看血压计操作视频" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545196778619.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                    <li>
                        <div><img src="static/images/device/no_circle/Oxygen.png" alt=""></div>
                        <h4>血氧仪</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1NDI1MDI2MA==.html?spm=a2hzp.8253869.0.0" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545183845714.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                    <li>
                        <div><img style='width:160px;height:180px' src="static/images/device/no_circle/newBodyFatScale.jpg" alt=""></div>
                        <h4>体脂秤</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1NTE3MTE4OA==.html?spm=a2hzp.8253869.0.0" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545350752443.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
           
                    <li>
                        <div><img style='width:160px;height:180px' src="static/images/device/no_circle/newband.png" alt=""></div>
                        <h4>手环</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1MzMxMDQ2MA==.html?spm=a2hzp.8253869.0.0" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545196906704.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                
                    <li>
                        <div><img src="static/images/device/no_circle/Glucometer.png" alt=""></div>
                        <h4>血糖仪</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1MzMyMDYyOA==.html?spm=a2hzp.8253869.0.0" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545183625717.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                
                    <li>
                        <div><img src="static/images/device/no_circle/Lunginstrument.png" alt=""></div>
                        <h4>肺功能康复仪</h4>
                        <a  class="equipment-list" href="https://v.youku.com/v_show/id_XMjg1MzI5OTA1Mg==.html?spm=a2hzp.8253869.0.0" target="_blank"><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/545152796265.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                
                    <li>
                        <div><img src="static/images/device/no_circle/Temperature.png" alt=""></div>
                        <h4>体温计</h4>
                        <a  class="equipment-list" href="javascript:;"><span id="thermometer">操作视频</span></a>
                        <a href="#" target="_blank"><span>查看详情</span></a>
                    </li>
                    <li>
                        <div><img  style='width:160px;height:180px' src="static/images/device/no_circle/bloodFat.png" alt=""></div>
                        <h4>血脂仪</h4>
                        <a  class="equipment-list" href="javascript:;"><span class="equipment-list" id="bloodFat">操作视频</span></a>
                        <a href="https://detail.1688.com/offer/558954733504.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                     <li>
                        <div><img style='width:160px;height:180px' src="static/images/device/no_circle/xindianyi.png" alt=""></div>
                        <h4>心电仪</h4>
                        <a  class="equipment-list" href="javascript:;"><span id="electrocardiograph">操作视频</span></a>
                        <a href="https://detail.1688.com/offer/558864976638.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                    <li>
                        <div><img style='width:160px;height:180px' src="static/images/device/no_circle/UricAcid.jpg" alt=""></div>
                        <h4>尿酸分析仪</h4>
                        <a  class="equipment-list" href="javascript:;"><span id="uricAcid">操作视频</span></a>
                        <a href="https://detail.1688.com/offer/558953113449.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
                     <li>
                        <div><img style='width:160px;height:180px' src="static/images/device/no_circle/urine.png" alt=""></div>
                        <h4>尿液分析仪</h4>
                        <a  class="equipment-list" href="javascript:;"id='urine'><span>操作视频</span></a>
                        <a href="https://detail.1688.com/offer/558953113449.html?spm=a2615.7691456.0.0.1085a046EG22wp" target="_blank"><span>查看详情</span></a>
                    </li>
            </ul>
        </div>
        <div class="device_1 device_2">
            <div class="device-title">
                <h3>设备套装</h3>
            </div>
            <ul class="clearfix">
                <li>
                    <div><img src="/static/images/index/device_package.png" alt=""></div>
                    <h4>便携式健康包</h4>
                    <a href="https://shop1416912965904.1688.com/page/offerlist.htm?spm=a2615.2177701.0.0.105c238fywzET1&smToken=f2f9a43c17514928a555981f11c1491a&smSign=NDeV02dKpNKdzpcl4qizsA%3D%3D" target="_blank"><span>查看详情</span></a>
                </li>
            </ul>
        </div>
        <!-- 智能设备 -->
    </div>
</section>
<section>
    <div id="popup">
        <p>对不起，该设备暂时没有操作视频!!!!</p>
    </div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
</body>
</html>
<script>
$(".tz-navbar-nav > li:eq(2) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
var elements = [$('#thermometer'),$('#bloodFat'),$("#electrocardiograph"),$('#uricAcid'),$('#urine')]
$.each(elements,function(index,domEle){
	domEle.on('click',function(){
		$('#popup').animate({
			opacity:'1',
			left:"50%"
		},"fast").animate({
			opacity:"1",
			},2000).animate({
				"opacity":"0",
				"left":"-20%"
			})
	})
})
</script>
<script>
    $(document).ready(function(){
            $('.equipment-list').on('click',function(){
                $(this).addClass('animated flipInY').children('span').css({
                    background:'#44c660',
                    color:"#FFF"
                })
            })
            var buttonArr=$("#equipment_list span");
            buttonArr.on("click",function(){
                buttonArr.css({
                    backgroundColor:'#369239',
                    color:'#fff'
                });
                $(this).css({
                    backgroundColor:"#44c660",
                    color:'#fff',
                    fontWeight:'bold'
                })
            })

    })
</script>