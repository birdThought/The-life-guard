<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>子级机构添加</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<t:base type="jquery,layer"></t:base>
<link type="image/x-ic
on" rel="shortcut icon" href="favicon.ico
"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/QYcomCss.css">
<link rel="stylesheet" href="/static/com/QYPart/css/paramSubmit.css">
<script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
<script type="text/javascript">
	seajs.config({
		base : './static/',
		alias : {
			"QYcommon" : "com/QYPart/js/QYcommon",
			"commonCheck" : "com/QYPart/js/commonCheck",
			"fileUpload" : "com/lifeshs/member/record/fileUpload"
		}
	});
	seajs.use([ "./static/com/QYPart/js/childOrAdd" ], function() {
		menuSetting({
			parent : 0,//父级菜单的索引
			child : 0
		//子级菜单的索引
		});
	});
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content">
			<div class="right_body">
				<div class="item_title"
					style="background-color:#f1f1f2;border-bottom:none;padding-left:20px;font-size:18px">
					<h3>子机构添加</h3>
				</div>
				<!--机构类型-->
				<div class="item_contain">
					<div class="item_title">
						<label class="title"> 机构类型 </label>
					</div>
					<div class="part_select">
						<div id="management" class="select_box_action">
							<div class="top">
								<label><b>类型：</b>管理机构</label>
								<div class="checkBox" onclick="jigouType.select(this)">
									<img src="/static/images/selected.png" />
								</div>
							</div>
							<div class="content">
								<p>管理机构：机构的下级管理组织</p>
								<p>（如：下级分部--下级代理商等）</p>
							</div>
						</div>
						<div id="service" class="select_box">
							<div class="top">
								<label><b>类型：</b>服务机构</label>
								<div class="checkBox" onclick="jigouType.select(this)"></div>
							</div>
							<div class="content">
								<p>服务机构：下属直接向用户提供服务的机构</p>
								<p>（如：直属门店--直属健身房--服务社区等）</p>
							</div>
						</div>
					</div>
				</div>
				<!--门店信息-->
				<div class="item_contain">
					<div class="item_title">
						<label class="title"> 机构信息 </label>
					</div>
					<div style="padding-top:20px;padding-left:20px">
						<div class="param_set">
							<label class="param" style="float:left;">营业执照：</label> <input
								id="licenceFile" type="file"
								style="display:inline-block;visibility: hidden;"
								onchange="fileUtils.fileChange('licenceFile',1)" />
							<div
								style="background-color:#f0f0f0;width:150px;height:150px;display:inline-block;float:left;margin-bottom:15px;cursor:pointer" onclick="fileUtils.openFile('licenceFile')">
								<img id="card" style="width:150px;height:150px;" />
							</div>
							<!-- <button id="selectImg" class="select_photo_btn" 
								style="margin-bottom:15px">
								<img src="/static/images/photo_select.png" /><br />+选择图片
							</button> -->
						</div>
						<div class="param_set" style="clear:both;">
							<label class="param"><span class="warn">*</span>机构名称：</label><input
								id="orgName" type="text" placeholder="请输入机构名称" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>机构性质：</label><select
								id="orgXZ" class="select-btn" style="width:250px;"><option
									value="0">请选择</option>
								<option value="慢病康复">慢病康复</option>
								<option value="健康养生">健康养生</option>
								<option value="减肥塑体">减肥塑体</option>
								<option value="居家养老">居家养老</option>
								<option value="癌症康复">癌症康复</option>
								<option value="妇婴">妇婴</option>
								<option value="家政">家政</option>
								<option value="生殖医学">生殖医学</option>
								</select>
						</div>
						<div class="param_set">
							<label class="param">联系人：</label><input type="text" id="contact"
								placeholder="请输入联系人" />
						</div>
						<div class="param_set">
							<label class="param">联系号码：</label><input type="text" id="phone"
								placeholder="请输入联系号码" />
						</div>
						<div class="param_set">
							<label class="param">地区：</label> <select onchange="areaUtils.provinceChange()" class="select-btn"
								id="province"><c:forEach items="${province}" var="item">
									<option value="${item.code}">${item.name}</option>
								</c:forEach></select> <select onchange="areaUtils.cityChange()" id="city" class="select-btn"><c:forEach
									items="${city}" var="item">
									<option value="${item.code}">${item.name}</option>
								</c:forEach></select> <select class="select-btn" id="district">
								<c:forEach items="${district}" var="item">
									<option value="${item.code}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="param_set">
							<label class="param" style="float:left;">详细地址：</label>
							<textarea id="road"
								style="resize:none;padding:8px;width:350px;border:1px solid #ccc"
								rows="3" placeholder="不能超过70字"></textarea>
						</div>
					</div>
				</div>
				<!--初始账户-->
				<div class="item_contain">
					<div class="item_title">
						<label class="title"> 初始账户 </label>
					</div>
					<form style="margin-top:20px;padding-left:25px">
						<div class="param_set">
							<label class="param"><span class="warn">*</span>登录账户：</label><input id="account" type="text"
								placeholder="输入账户" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>密码：</label><input id="psw" type="password"
								placeholder="输入密码" />
						</div>
						<div class="param_set">
							<label class="param"><span class="warn">*</span>确认密码：</label><input id="confirm_psw" type="password"
								placeholder="确认一下密码" />
						</div>
						<div style="padding:10px 110px 20px">
							<input class="save" value="保存"
								onclick="addOrganization.submitForm()" type="button" /><input
								class="back" value="返回" type="button" onclick="history.go(-1)"/>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
