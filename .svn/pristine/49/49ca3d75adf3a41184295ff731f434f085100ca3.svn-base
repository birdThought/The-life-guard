<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>注册账号</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/reset.css">
<!-- <link rel="stylesheet" type="text/css" href="/static/plugins/bootstrap/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="static/officialwebsite/index/css/headerAndFooter.css">
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<script type="text/javascript" src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script type="text/javascript" src="/static/login/js/uploadCertificate.js"></script>
<link rel="stylesheet" type="text/css" href="/static/login/css/login.css">
<title>资质验证</title>
<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
	window.onload=function(){
		orgImprove.initForm(${orgId});
		console.log('id', ${orgId})
	}
</script>
</head>

<body>
	<%@include file="/context/mainHeader_1.jsp"%>
	<section class="item-content certificate-content">
		<form id="item-register" class="form-certificate" novalidate="novalidate">
			<div class="register-title">
				<h3>证件上传</h3>
			</div>
			<ul class="certificateContent">
				<li>
					<h5>公司地址</h5> <input type="text" placeholder="请输入公司地址">
				</li>
				<li>
					<h5>公司法定代表</h5> <input type="text" placeholder="请输入公司法人姓名">
				</li>
				<li>
					<h5>公司联系方式</h5> <input type="text" placeholder="请输入公司联系电话">
				</li>
				<li class="setFlex">
					<div>
						<img id="card" src="/static/images/licence.jpg" alt="">
					</div>
					<div>
						<input id="licenceFile" type="file" /> <input type="button"
							value="选择文件" class="regist_btn" onclick="orgImprove.uploadFile()">
						<p>请选择上传相关证件的复印件到平台，我们将尽快审核你的资料</p>
						<p>图片大小限制为3M以内，图片格式支持JPG、GIF、PNG</p>
					</div>
				</li>
				<li><input type="submit" value="完成" class="regist_btn"></li>
			</ul>
		</form>
	</section>
	<%@include file="/context/mainFooter_1.jsp"%>
</body>
</html>
