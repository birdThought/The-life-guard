<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>注册账号</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css" />
<link rel="stylesheet" type="text/css" href="/static/css/register.css" />
<link rel="stylesheet" href="/static/common/css/mainHeader.css">
<link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
<t:base type="jquery,layer"></t:base>

<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<script type="text/javascript"
	src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript"
	src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script type="text/javascript"
	src="/static/login/js/uploadCertificate.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			orgImprove.initForm(${orgId});
		}
	</script>
</head>

<body>
	<%@include file="/context/mainHeader.jsp"%>
	<div class="content">
		<form action="" id="signupForm" method="" class="hwrap"
			style="width:650px">
			<div class="title">
				<h1>证件上传</h1>
			</div>
			<div class="control_contain">
				<section style="margin-bottom:50px">
					<div class="control_item">
						<label>公司地址</label>
						<div class="control">
							<input id="street" type="text" name="street" placeholder="请输入公司地址" class="input_edit"
								style="width:95%" />
						</div>
					</div>
					<div class="control_item">
						<label>公司法定人代表</label>
						<div class="control">
							<input id="realName" name="realName" type="text" placeholder="请输入公司法人姓名" class="input_edit"
								style="width:95%" />
						</div>
					</div>


					<div class="control_item">
						<label>公司联系方式</label>
						<div class="control">
							<input id="mobile" name="mobile" type="text" placeholder="请输入公司联系电话" class="input_edit"
								style="width:95%" />
						</div>
					</div>

					<div class="control_item">
						<label>营业执照</label>
						<div class="control" style="padding-left:10px;">
							<img id="card" src="/static/images/licence.jpg"
								style="width:267px;height:178px;border-radius:6px 6px;border:1px solid #ccc;float:left;margin-right:20px" />
							<div>
								<input id="licenceFile" type="file" hidden="hidden"
									 /> <input
									type="button" class="regist_btn"
									style="width:150px;margin-top:0" value="选择文件"
									onclick="orgImprove.uploadFile()" />
								<p class="upload_notice">请选择上传相关证件的复印件到平台，我们将尽快审核你的资料</p>
								<p class="upload_notice">图片大小限制为3M以内，图片格式支持JPG、GIF、PNG</p>
							</div>
						</div>
					</div>
				</section>
				<div>
					<center>
						<input type="submit" value="完成" class="regist_btn"
							style="width:90%">
					</center>
				</div>
			</div>
		</form>
	</div>
	<%@include file="/context/mainFooter.jsp"%>
</body>

</html>
