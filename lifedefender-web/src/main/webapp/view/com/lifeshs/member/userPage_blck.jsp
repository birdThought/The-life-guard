<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
  <head>
    <title>用户主页</title>
	<t:base type="jquery,layer"></t:base>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" type="text/css" href="/static/css/userPage.css">
  </head>
  
 <body>
<div class="webPage wrap">
	<div class="topbar wrap">
		<div class="console-topbar">
			<div class="topbar-head topbar-left">
				<a href="#" target="_blank" class="topbar-logo">
					<img src="/static/images/logo.png" alt="">
				</a>
			</div>
			<div class="topbar-infor topbar-right topbar-clearfix">
				<ul>
					<li class="infor-center topbar-left">
						<a href="javascript:_inforCenter();">
							<span>消息中心</span>
						</a>
					</li>
					<li class="help topbar-left">
						<a href="javascript:_help();">
							<span>帮助</span>
						</a>
					</li>
					<li class="username topbar-left">
						<a href="javascript:_help();">
							<span>地主</span>
						</a>
					</li>
					<li class="exit topbar-left">
						<a href="javascript:quitLogin();">
							<span>退出</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="view-body wrap">
		<div class="menu fl" id="menu">
			<div>
				<p>健康包</p>
				<ul style="display:block">
					<li><a href="terminalCommandControl.do?showHealthPackageDevices">设备管理</a></li>
					<li><a href="#">健康数据</a></li>
				</ul>
			</div>
			<div>
				<p>穿戴设备</p>
				<ul>
					<li><a>设备管理</a></li>
					<li><a>HL-03手环</a></li>
					<li><a>C3手环</a></li>
				</ul>
			</div>
			<div>
				<p>个人中心</p>
				<ul>
					<li><a href="memberControl.do?getUserInfor">个人信息</a></li>
					<li><a href="memberControl.do?showUserRecord">个人档案</a></li>
					<li><a href="memberControl.do?showWarn">预警&联系人</a></li>
					<li><a href="memberControl.do?showUserPwd">修改密码</a></li>
					<li><a>邀请好友</a></li>
				</ul>
			</div>
			<ol>
			    <li><a href="#">健康档案</a></li>
			    <li><a href="#">健康日记</a></li>
			    <li><a href="#">家庭成员</a></li>
			    <li><a href="#">服务</a></li>
			</ol>
		</div>
		<div class="title fl">主页</div>
		<div class="infor fr">
			<div class="mainbar">
				<div class="healthy-package">
					<h3>健康包数据</h3>
					<span class="flesh-icon"></span>
				</div>
				<div class="myEquipment">
					<ul class="myEquip_add">
						<li>我的设备</li>
					    <li>
					    	<img src="/static/images/IMG_1646.png" alt="">
					    </li>
					    <li>
					    	<img src="/static/images/IMG_1646.png" alt="">
					    </li>
					    <li>
					    	<img src="/static/images/IMG_1646.png" alt="">
					    </li>
					    <li>
					    	<img src="/static/images/IMG_1646.png" alt="">
					    </li>
					</ul>
					<div class="myEquip_bind">
						<span onclick="_bindDevice();" style="cursor: pointer;">设备绑定</span>
					</div>
				</div>
			</div>	
			<div style="clear: both"></div>
			<div class="xinhuan">
				<section class="xinhuan_top">
					<h3>心率手环</h3>
					<a href="javascript:_xlshxq();">详情>></a>
				</section>
				<section class="content">
					<ul class="level_1">
					    <li class="run">
					    	 <div id="main" style="height:200px;width:200px"></div>
					    </li>
					    <li class="percent_1">		
			    	    	<span>卡路里</span>
			    	    	<div class="progressBar">
								<div class="finish_1"></div>
    							<p class="ing">0千卡</p>
							</div> 
				    	    <span>里程</span>
				    	    <div class="progressBar_2">
					    	    <div class="finish_2"></div>
	    						<p class="ing_2">0公里</p>
        					</div>
					    </li>
					    <li class="percent_1">
					    	<img src="/static/images/quxian.png" alt="">
					    	<p>85 bpm</p>
					    	<p>心率：85bpm</p>
					    </li>
					    <li class="percent_1 percent">
					    	<div class="percentAnto">
						    	<div class="progressBar progressBarAnto_1">
						    	    <div class="finish_1"></div>
	        					</div>
	        					<div class="progressBar_2 progressBarAnto_2">
						    	    <div class="finish_2"></div>
	        					</div>
        					</div>
					    </li>
					    <li class="percent_1 ">
					    	<span>深睡</span>
					    	<span>浅睡</span>
					    	<div>深睡2.5h</div>
					    	<div>浅睡3.5h</div>
					    </li>
					</ul>
					<section class="testResult">
						<p>检查结果 
							<span>正常</span>
						</p>
						<a href="#">正常范围值</a>
					</section>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div class="tizhicheng">
				<section class="tizhicheng_top">
					<h3>体脂秤</h3>
					<a href="javascript:_xzcxq();">详情>></a>
				</section>
				<section class="content">
					<ul>
					    <li>
					    	<p><strong>45</strong><i>kg</i></p>
					    	<p>体重</p>
					    </li>
					    <li>
					    	<p><strong>22.5</strong><i>%</i></p>
					    	<p>体脂肪率</p>
					    </li>
					    <li>
					    	<p><strong>22</strong><i>岁</i></p>
					    	<p>体年龄</p>
					    </li>
					    <li>
					    	<p><strong>22.5</strong><i>%</i></p>
					    	<p>腰臀比</p>
					    </li>
					    <li>
					    	<p><strong>15</strong><i>kg/m</i></p>
					    	<p>BMI</p>
					    </li>
					    
					</ul>
					<ul>
					    <li>
					    	<p><strong>6</strong><i>kg</i></p>
					    	<p>去脂体重</p>
					    </li>
					    <li>
					    	<p><strong>15</strong><i>%</i></p>
					    	<p>人体水分</p>
					    </li>
					    <li>
					    	<p><strong>46</strong><i>%</i></p>
					    	<p>人体肌肉</p>
					    </li>
					    <li>
					    	<p><strong>26</strong><i>kg</i></p>
					    	<p>骨骼重量</p>
					    </li>
					    <li>
					    	<p><strong>22.5</strong><i>kcal</i></p>
					    	<p>基础代谢</p>
					    </li>
					</ul>
					<section class="testResult">
						<p>测试结果 
							<span>正常</span>
						</p>
						<a href="#">正常范围值</a>
					</section>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div class="xueyaji">
				<section class="xueyaji_top">
					<h3>血压计</h3>
					<a href="javascript:_xyjxq();">详情>></a>
				</section>
				<section class="content">
					<ol class="unorder">
					    <li>偏低</li>
					    <li>正常</li>
					    <li>异常</li>
					</ol>
					<div class="contentMid">
						<ul>
							<li>收缩压 140mmHg</li>
							<li>舒张压 100mmHg</li>
							<li>心率 90次/分</li>
						</ul>
						<div class="percent_1">
					    	<div class="progressBar progressBar_1">
					    	    <div class="finish_1"></div>
	    					</div>
	    					<div class="progressBar_2 progressBar_2">
					    	    <div class="finish_2"></div>
	    					</div>
	    					<div class="progressBar_2 progressBar_2">
					    	    <div class="finish_2"></div>
	    					</div>
						</div>
					</div>	
				</section>
				<section class="testResult">
					<p>测试结果 
						<span>正常</span>
					</p>
					<a href="#">正常范围值</a>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div class="feihuoyi">
				<section class="feihuoyi_top">
					<h3>肺活仪</h3>
					<a href="javascript:_fhyxq();">详情>></a>
				</section>
				<section class="content">
					<ul class="contentLs">
					    <li>
					    	<div id="mainLung" style="height:150px;width:150px"></div>
					    </li>
					    <li class="ctSummarize">
					    	<p>肺活量</p>
					    	<p>肺活仪：2400ml</p>
					    </li>
					</ul>
				</section>
				<section class="testResult">
					<p>测试结果 
						<span>正常</span>
					</p>
					<a href="#">正常范围值</a>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div style="clear:both"></div>
			<div class="xuetangyi">
				<section class="xuetangyi_top">
					<h3>血糖仪</h3>
					<a href="javascript:_xtyxq();">详情>></a>
				</section>
				<section class="content">
					<ul class="contentLs">
					    <li>
					    	<div id="mainSugar" style="height:150px;width:150px"></div>
					    </li>
					    <li class="ctSummarize">
					    	<p>血糖含量</p>
					    	<p>血糖含量：4.5mmol/L</p>
					    </li>
					</ul>
				</section>
				<section class="testResult">
					<p>测试结果 
						<span>正常</span>
					</p>
					<a href="#">正常范围值</a>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div class="xueyangyi">
				<section class="xueyangyi_top">
					<h3>血氧仪</h3>
					<a href="javascript:_xyyxq();">详情>></a>
				</section>
				<section class="content">
					<ol class="unorder">
					    <li>偏低</li>
					    <li>正常</li>
					    <li>异常</li>
					</ol>
					<div class="contentMid">
						<ul>
							<li>血氧饱和度&nbsp;&nbsp;95%</li>
							<li>心率 90次/分</li>
						</ul>
						<div class="percent_1">
					    	<div class="progressBar progressBar_1">
					    	    <div class="finish_1"></div>
	    					</div>
	    					<div class="progressBar_2 progressBar_2">
					    	    <div class="finish_2"></div>
	    					</div>
						</div>
					</div>	
				</section>
				<section class="testResult">
					<p>测试结果 
						<span>正常</span>
					</p>
					<a href="#">正常范围值</a>
				</section>
				<section class="date">
					<p>2016年05月11日 16:30</p>
				</section>
			</div>
			<div style="clear: both"></div>
			<div class="mainbar_2">
				<div class="healthy-function">
					<h3>HL系列数据</h3>
					<span class="flesh-icon"></span>
				</div>
				<div class="small-nav">
					<ul>
						<li><a href="#">健康功能</a></li>
						<li><a href="#">近期定位</a></li>
					</ul>
					<div><a href="javascript:_hlMore();">更多功能</a></div>
				</div>
				<ul class="sort">
				    <li class="blood">
				    	<section class="title_top">
				    		<h3>血压</h3>
				    	</section>
				    	<section class="content">
				    		<ol>
				    		    <li>收缩压</li>
				    		    <li>舒张压</li>
				    		</ol>
				    		<div class="pgBars">
				    			<span>收缩压&nbsp;&nbsp;140mmHg</span>
						    	<div class="pgBar pg_1 ">
						    	    <div class="ok_1"></div>
		    					</div>
		    					<span>舒张压&nbsp;&nbsp;100mmHg</span>
		    					<div class="pgBar pg_2">
						    	    <div class="ok_2"></div>
		    					</div>
							</div>
				    	</section>
				    	<section class="testResult">
							<p>测试结果 
								<span>正常</span>
							</p>
							<a href="#">正常范围值</a>
						</section>
				    </li>
				    <li class="stepCount">
						<section class="title_top">
				    		<h3>计步器</h3>
				    	</section>
				    	<section class="content">
				    		<div id="mainSc" style="height:150px;width:242px"></div>
				    	</section>
				    	<section class="testResult">
							<ol>
							    <li>
							    	<p>卡路里</p>
							    	<p>1.44千卡</p>
							    </li>
							    <li>
							    	<p>里程</p>
							    	<p>2.5公里</p>
							    </li> 
							</ol>
						</section>
				    </li>
				    <li class="temper">
				    	<section class="title_top">
							<h3>体温</h3>
				    	</section>
				    	<section class="content">
				    		<div id="mainTp" style="height:150px;width:242px"></div>
				    	</section>
				    	<section class="testResult">
							<p>测试结果 
								<span>正常</span>
							</p>
							<a href="#">正常范围值</a>
						</section>
				    </li>
				    <li class="heartRate">
				    	<section class="title_top">
				    		<h3>心率</h3>
				    	</section>
				    	<section class="content">
				    		<img src="/static/images/quxian.png" alt="">
					    	<p>85 bpm</p>
					    	<p>心率：85bpm</p>
				    	</section>
				    	<section class="testResult">
							<p>测试结果 
								<span>正常</span>
							</p>
							<a href="#">正常范围值</a>
						</section>
				    </li>
				    <li class="sleep">
				    	<section class="title_top">
				    		<h3>睡眠</h3>
				    	</section>
				    	<section class="content">
				    		<ul>

				    			<li class="sleepy">
							    	<div class="repgBars">
								    	<div class="repgBar_1">
								    	    <div class="reok_1"></div>
			        					</div>
			        					<div class="repgBar_2 ">
								    	    <div class="reok_2"></div>
			        					</div>
		        					</div>
							    </li>
							    <li class="slep">
							    	<span>深睡</span>
							    	<span>浅睡</span>
							    	<div>深睡2.5h</div>
							    	<div>浅睡3.5h</div>
							    </li>
				    		</ul>
				    	</section>
				    	<section class="testResult">
							<p>测试结果 
								<span>正常</span>
							</p>
							<a href="#">正常范围值</a>
						</section>
				    </li>
				    <li class="elect">
				    	<section class="title_top">
				    		<h3>心电</h3>
				    	</section>
				    	<section class="content">
				    		<img src="/static/images/elect.png" alt="">
					    	<p>90bpm</p>
				    	</section>
				    	<section class="testResult">
							<p>测试结果 
								<span>正常</span>
							</p>
							<a href="#">正常范围值</a>
						</section>
				    </li>
				</ul>
			</div>
			<div class="mainbar_3">
				<div class="healthy-function">
					<h3>C系列数据</h3>
					<span class="flesh-icon"></span>
				</div>
				<div class="latelyLocate">
					<ul>
						<li><a href="#">近期定位</a></li>
						<li class="func"><a href="javascript:_cMore();">更多功能</a></li>
					</ul>
				</div>
				<div class="last-nav">
					<ul class="ulArray">
					    <li>时间</li>
					    <li>位置</li>
					    <li>IMEI</li>
					    <li>操作</li>
					</ul>
					<ul class="ulAdd">
					    <li>				    	
					    	<ol>
					    	    <li>2016年05月11日 11:20</li>
					    	    <li>广州市海珠区仑头路53号4栋3楼</li>
					    	    <li>4566561676687</li>
					    	    <li>
					    	    	<img src="/static/images/look.png" alt="">
					    	    </li>
					    	</ol>
					    </li>
					    <li>				    	
					    	<ol>
					    	    <li>2016年05月11日 11:20</li>
					    	    <li>广州市海珠区仑头路53号4栋3楼</li>
					    	    <li>4566561676687</li>
					    	    <li>
					    	    	<img src="/static/images/look.png" alt="">
					    	    </li>
					    	</ol>
					    </li>
					</ul>
				</div>
			</div>
			<div class="addEquipment">
				<img src="/static/images/roundb.png" width="40" height="40" alt="">
				<strong>添加设备</strong>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/static/plugins/echarts-2.2.7/echarts.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/userPage.js"></script>
</body>
</html>