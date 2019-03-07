<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>设备数据</title>
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css"
	href="/static/css/wearableDevice.css">
<link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="/static/com/lifeshs/member/js/bindDevice.js"></script>
<script type="text/javascript"
	src="/static/com/lifeshs/member/js/unBindDevice.js"></script>
<t:base type="jquery,layer"></t:base>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">主页</div>
			<div class="container fr">
				<h3>设备管理</h3>
				<section class="HLseries">
					<div class="top">
						<div class="left">
							<h3>HL系列手环</h3>
							<p>健康包通过蓝牙自动连接“生命守护”APP，数据自动上传到APP和平台，成系列，自动上传，操作简单，便于携带，提供全套的外接智能健康检测设备</p>
						</div>
						<div class="addE" id="HL-031">
							<img src="/static/images/roundb.png" alt="">
							<p>添加设备</p>
						</div>
					</div>
					<div class="down">
						<ul>
							<li><span>设备使用的手机号码:<em><span id="hl_mobile">${HL.mobile}</span></em></span></li>
							<li><span>参数设置</span></li>
						</ul>
						<input type="hidden" value="${HL.imei}" id="hl_imei">
					</div>
				</section>
				<section class="Cseries">
					<div class="top">
						<div class="left">
							<h3>C系列手环</h3>
							<p>健康包通过蓝牙自动连接“生命守护”APP，数据自动上传到APP和平台，成系列，自动上传，操作简单，便于携带，提供全套的外接智能健康检测设备</p>
						</div>
						<div class="addE" id="C3">
							<img src="/static/images/roundb.png" alt="">
							<p>添加设备</p>

						</div>
						<div class="Cs" style="display: none">
							<ul>
								<li><label for="">imei号</label><br> <input type="text"
									placeholder="请输入imei号" id="imei"></li>
								<li><label for="">设备使用的手机号码</label><br> <input
									type="text" placeholder="请输入设备号码" id="mobile"></li>
								<li><label for="">紧急求助号码</label><br> <input
									type="text" placeholder="请输入紧急求助号码" id="sosPhone"></li>
								<li><label for="">说明</label>
									<p>
										<small>需要进行设备绑定，输入imei号并提交，系统判断imei号是否存在并未被绑定，则可绑定成功。</small>
									</p></li>
							</ul>
						</div>
					</div>
					<div class="down">
						<ul>
							<li><span>设备使用的手机号码:<em><span id="c3_mobile">${C3.mobile}</span></em></span></li>
							<li><span>参数设置</span></li>
						</ul>
						<input type="hidden" value="${C3.imei}" id="c3_imei">
					</div>
				</section>
			</div>
				</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/com/lifeshs/member/js/userHealthDataCompleteCheck.js"></script>
	<script type="text/javascript">
		$(function() {
			menuSetting({
				parent : 1,
				child : 0
			});
			checkRecordData.complete(checkRecordData.WEARABLE_DEVICE);
		});
		$('#HL-031,#C3').click(function() {
			var $type = $(this).attr("id");
			layer.open({
				type : 1,
				content : $('.Cs'), //这里content是一个DOM
				title : [ '健康包参数设置', 'text-align:center;font-size:16px' ],
				btn : [ '确定', '取消' ],
				moveType: 1,
				closeBtn : 0,
				area : '460px',
				yes : function(index) {
					bindDevice($type);
					parent.layer.close(index);
				}
			});
		});
	</script>
	<script type="text/javascript">
		$('#c3_mobile,#hl_mobile').click(
				function() {
					var $type = $(this).attr("id");
					if ($type == "c3_mobile") {
						$type = 'C3';
					} else {
						$type = 'HL-031';
					}
					layer.confirm('你确定需要解绑' + $type + '系列手环？', function(index) {
						unBindDevice($type);
						parent.layer.close(index);
					});
					/* layer.open({
						type : 1,
						title : [ '你确定需要解绑' + $type + '系列手环？',
								'text-align:center;font-size:16px' ],
						btn : [ '确定', '取消' ],
						closeBtn : 0,
						area : '460px',
						yes : function(index) {
							unBindDevice($type);
							parent.layer.close(index);
						}
					}); */
				});
	</script>
</body>
</html>