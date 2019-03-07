<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设备数据</title>
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" href="/static/common/css/comCss.css">
	<link rel="stylesheet" type="text/css" href="/static/css/userInfor.css">
	<link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
	<t:base type="jquery,layer"></t:base>
	<script type="text/javascript" src="/static/login/js/icheck.js"></script>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap" >
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>消息中心</h3>
				<ul class="tab_news">
					<li><a href="#">预警消息</a></li>
					<li><a href="#">系统消息</a></li>
					<li><a href="#">服务消息</a></li>
				</ul>
				<div class="contentNews">
					<div class="warnNews">
						<div class="newsTop">
							<input type="checkbox" name="checkbox_name" id="checkAll">&nbsp;勾选所有信息
							<span>删除</span>
						</div>
						<ul>
							<li>
								<p>
									<input type="checkbox" name="checkbox_name">&nbsp;预警信息
									<small>2016-05-14</small>
								</p>
								<p class="warnInfor">
									在<em>2016-05-14 15:30</em>使用血压计，检测出<em>异常</em>。详细数值(收缩压：180、舒张压:120、心率：120次/分)
								</p>
							</li>
							<li>
								<p>
									<input type="checkbox" name="checkbox_name">&nbsp;预警信息
									<small>2016-05-14</small>
								</p>
								<p class="warnInfor">
									在<em>2016-05-14 15:30</em>使用血压计，检测出<em>异常</em>。详细数值(收缩压：180、舒张压:120、心率：120次/分)
								</p>
							</li>
						</ul>
					</div>
					<div class="warnNews sysNews" style="display: none">
						<div class="newsTop">
							<input type="checkbox">&nbsp;勾选所有信息 <span>删除</span>
						</div>
					</div>
					<div class="warnNews sysNews" style="display: none">
						<div class="newsTop">
							<input type="checkbox">&nbsp;勾选所有信息 <span>删除</span>
						</div>
					</div>
				</div>
			</div>
				</div>
		</div>
	</div>
	<script>
		$('#checkAll').click(function() {
			if (this.checked) {
				$(":checkbox").prop("checked", true);
			} else {
				$(":checkbox").prop("checked", false);
			}
		});

		$('.tab_news li').click(
				function() {

					$(this).children('a').css('color', '#fff').parent()
							.siblings().children('a').css('color', '');
					$(this).addClass('current').siblings('li').removeClass(
							'current');
					$('.contentNews > .warnNews').eq($(this).index()).show()
							.siblings('div').hide();

				});
		$('input').iCheck({
			checkboxClass : 'icheckbox_minimal-green'
		});
	</script>
</body>
</html>