<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<footer>
	<!-- <img src="/static/images/police.png" alt=""> <span><a
		href="#">粤ICP备13019866号-3</a></span>
 -->
	<div class="footer_content hwrap">
		<div class="col-md-8" style="overflow:hidden;">
			<div class="col-md-4">
				<h3>关于我们</h3>
				<ul>
					<li><a href="#">关于我们</a></li>
					<li><a href="#">联系我们</a></li>
					<li><a href="#">加入我们</a></li>
				</ul>
			</div>
			<div class="col-md-4">
				<h3>健康产品</h3>
				<ul>
					<li><a href="#">产品系列</a></li>
					<li><a href="#">产品信息</a></li>
				</ul>
			</div>
			<div class="col-md-4 dl" style="position:relative;">
				<h3>APP下载</h3>
				<ul>
					<li><a href="#" onclick="onclickD('tanchuanAndroid',this)">应用APP下载</a></li>
					<li><a href="#" onclick="onclickD('tanchuanIos',this)">管理APP下载</a></li>
				</ul>
				
				
			</div>
			<div class="col-md-4">
				<h3>关注生命守护平台</h3>
				<div class="erweima">
					<div style="display:inline-block;">
						<img src="/static/images/publicNumber.jpg" width="95" height="95"/>
					</div>
					<div style="display:inline-block;">
						<p>扫描二维码关注</p>
						<p>生命守护公众号</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	
		function removeElement(id) {
			document.getElementById(id).style.display = "none";
		}

		function onclickD(type,obj) {
			if ("tanchuanIos" == type) {
				$("#tanchuanAndroid").fadeOut();
				document.getElementById("tanchuanAndroid").style.display = "none";
				
				$("#tanchuanIos")[0].style.left = obj.getBoundingClientRect().left - 20+ "px";
				$("#tanchuanIos")[0].style.top = obj.getBoundingClientRect().top-273
				+ $(document).scrollTop() + "px";
				
				$("#tanchuanIos").fadeToggle(300);
			} else {
				$("#tanchuanIos").fadeOut();
				$("#tanchuanAndroid")[0].style.left = obj.getBoundingClientRect().left - 20 + "px";
				$("#tanchuanAndroid")[0].style.top = obj.getBoundingClientRect().top-273
				+ $(document).scrollTop() + "px";
				$("#tanchuanAndroid").fadeToggle(300);
			}
		}
	</script>
	
</footer>
<div id="tanchuanIos" style="display: none">
	<div style="padding: 15px 23px">
		<h3>生命守护管理APP下载</h3>
		<input type="image" src="/static/images/iconfont-2.png"
			onclick="removeElement('tanchuanIos')"> <a href="#"
			style="float: left"> <img src="/static/images/APPdownload_2.png"
			alt="android版下载">
			<p class="p2">Andriod版下载</p>
		</a>

	</div>
</div>
<div id="tanchuanAndroid" style="display: none">
	<div style="padding: 15px 23px">
		<h3>生命守护应用APP下载</h3>
		<input type="image" src="/static/images/iconfont-2.png"
			onclick="removeElement('tanchuanAndroid')"> <a
			href="http://www.lifekeepers.cn/tz_res/download/apk/GuardLife.apk"
			style="float: left"> <img src="/static/images/APPdownload.png"
			alt="Andriod版下载">
			<p class="p2">Andriod版下载</p>
		</a> <a
			href="https://itunes.apple.com/us/app/sheng-ming-shou-hu/id1114425655?l=zh&ls=1&mt=8"
			style="float: left"> <img src="/static/images/APPdown.png"
			width="95" height="95" alt="IOS版下载">
			<p class="p2">IOS版下载</p>
		</a>
	</div>
</div>