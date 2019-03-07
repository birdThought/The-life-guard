<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="footer">
	<ul class="footer_content hwrap">
		<li style="width: 28%;">
			<p>广州通众电气实业有限公司&copy;版权所有2016-2017 </p>
			<p>
				<a href="http://www.miitbeian.gov.cn/" target="_blank">粤ICP备13019866号-3</a>
			</p>
			<p>400-026-1003</p>
		</li>
		<li>
			<h3>关于我们</h3>
			<ol>
				<li><a href="informationControl.do?inforLook&id=91" target="_blank">关于“生命守护”平台</a></li>
				<li><a href="http://netfield.cn" target="_blank">官方网站</a></li>
				<li><a href="informationControl.do?inforLook&id=92" target="_blank">加入我们</a></li>
				<li><a href="informationControl.do?inforLook&id=93" target="_blank">联系我们</a></li>
			</ol>
		</li>
		<li style="border-right:1px solid #f0f0f0;height:140px;">
			<h3 style="margin-bottom:10px;">免费下载APP</h3>
			<ol>
				<li><a href="javascript:void(0);" onclick="onclickD('tanchuanAndroid',this)">应用APP下载</a></li>
				<li><a href="javascript:void(0);" onclick="onclickD('tanchuanIos',this)">管理APP下载</a></li>
			</ol>
		</li>
		<li style="text-align:right;"><img src="/static/images/chatCode.png" alt="" width="100"
			height="100">
			<p style="margin-top:10px;">生命守护公众号</p></li>
	</ul>
</div>
<div id="tanchuanIos" style="display: none">
	<div style="padding: 15px 23px">
		<h3>生命守护管理APP下载</h3>
		<input type="image" src="/static/images/iconfont-2.png"
			onclick="removeElement('tanchuanIos')">
		<a href="http://app.qq.com/#id=detail&appid=1105884260"
			style="float: left"> <img src="/static/images/android_manage.jpg"
			alt="android版下载" width="95" height="95">
			<p class="p2">Andriod版下载</p>
		</a>
		<a
			href="https://itunes.apple.com/us/app/shou-hu-shi/id1185206076?mt=8" style="float: left"> 
			<img src="/static/images/ios_manage.jpg" width="95" height="95" alt="IOS版下载">
			<p class="p2">IOS版下载</p>
		</a>
	</div>
</div>
<script>
		function removeElement(id) {
			document.getElementById(id).style.display = "none";
		}

		function onclickD(type, obj) {
			if ("tanchuanIos" == type) {
				$("#tanchuanAndroid").fadeOut();
				document.getElementById("tanchuanAndroid").style.display = "none";

				$("#tanchuanIos")[0].style.left = obj.getBoundingClientRect().left
						-40+ "px";
				$("#tanchuanIos")[0].style.top = obj.getBoundingClientRect().top
						- 273 + $(document).scrollTop() + "px";

				$("#tanchuanIos").fadeToggle(300);
			} else {
				$("#tanchuanIos").fadeOut();
				$("#tanchuanAndroid")[0].style.left = obj
						.getBoundingClientRect().left
						-40+ "px";
				$("#tanchuanAndroid")[0].style.top = obj
						.getBoundingClientRect().top
						- 273 + $(document).scrollTop() + "px";
				$("#tanchuanAndroid").fadeToggle(300);
			}
		}
	</script>

<div id="tanchuanAndroid" style="display: none">
	<div style="padding: 15px 23px">
		<h3>生命守护应用APP下载</h3>
		<input type="image" src="/static/images/iconfont-2.png"
			onclick="removeElement('tanchuanAndroid')"> 
		<a
			href="http://app.qq.com/#id=detail&appid=1105104088"
			style="float: left"> 
			<img src="/static/images/android_use.jpg" alt="Andriod版下载" width="95" height="95" >
			<p class="p2">Andriod版下载</p>
		</a> 
		<a
			href="https://itunes.apple.com/us/app/sheng-ming-shou-hu/id1114425655?l=zh&ls=1&mt=8" style="float: left"> 
			<img src="/static/images/ios_use.jpg" width="95" height="95" alt="IOS版下载">
			<p class="p2">IOS版下载</p>
		</a>
	</div>
</div>