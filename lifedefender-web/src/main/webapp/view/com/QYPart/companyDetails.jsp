<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>公司信息</title>
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
<link rel="stylesheet" type="text/css"
	href="/static/plugins/jeDate/css/jedate.css">
<link rel="stylesheet" href="/static/com/QYPart/css/paramSubmit.css">
<script type="text/javascript"
	src="/static/plugins/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="/static/plugins/uedit/editor_api.js"></script>
<script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
<script type="text/javascript">
	seajs.config({
		base : './static/',
		alias : {
			"QYcommon" : "com/QYPart/js/QYcommon",
			"commonCheck" : "com/QYPart/js/commonCheck",
			"jedate" : "plugins/jeDate/jedate.min",
			"fileUpload" : "com/lifeshs/member/record/fileUpload"
		}
	});
	seajs.use([ "./static/com/QYPart/js/companyDetails" ], function() {
		menuSetting({
			parent: 1,
			child: 0
		});
		jQuery(function() {
			Editor.init();
			Editor.setHTMLContent(new String('${org.detail}'));
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
					<h3>机构信息</h3>
				</div>
				<!-- 公司信息 -->
				<div class="item_contain">
					<div class="item_title">
						<label class="title">机构信息 </label>
					</div>
					<div style="padding-top:20px;padding-left:20px">
						<div class="param_set">
							<label class="param" style="float:left;">机构Logo：</label>
							<div
								style="background-color:#f0f0f0;width:150px;height:150px;display:inline-block;margin-bottom: 15px;cursor:pointer"
								onclick="orgMsgControl.uploadFile(0)">
								<img id="logo" src="${org.logo}"
									style="width:150px;height:150px;" /> <input id="logoFile"
									type="file" style="display:inline-block;visibility: hidden;" />
							</div>
						</div>
						<div class="param_set">
							<label class="param">机构名称：</label><input
								id="orgName" type="text" placeholder="请输入机构名称"
								value="${org.orgName}" disabled />
						</div>

						<div class="param_set" style="clear:both;">
							<label class="param"><span class="warn">*</span>机构性质：</label><select
								id="orgXZ" class="select-btn" style="width:250px;">
								<script>
									var target = "${org.orgType}";
									var orgTypes = ["慢病康复","健康养生","减肥塑体","居家养老","癌症康复"];
									for (var i = 0; i < orgTypes.length; i++) {
										var s = "";
										if (target == orgTypes[i]) {
											s = 'selected="selected"';
										}
										document.write('<option value="'+orgTypes[i]+'" '+s+'>'+orgTypes[i]+'</option>');
									}
								</script>
							</select>
						</div>
						<div class="param_set">
							<label class="param" style="float:left;">营业执照：</label>
							<div
								style="background-color:#f0f0f0;width:150px;height:150px;float:left;display:inline-block;margin-bottom:15px;">
								<img id="card" src="${org.businessLicense}"
									style="width:150px;height:150px;" />
							</div>
							<input id="licenceFile" type="file"
								style="display:inline-block;visibility: hidden;" />
						</div>
						<div class="param_set" style="clear: both;">
							<label class="param">联系人：</label><input type="text" id="contact"
								value="${org.contacts}" placeholder="请输入联系人" />
						</div>
						<div class="param_set">
							<label class="param">联系号码：</label><input type="text" id="phone"
								value="${org.contactInformation}" placeholder="请输入联系号码" />
						</div>
						<div class="param_set">
							<label class="param">地区：</label> <select
								onchange="areaUtils.provinceChange()" class="select-btn"
								id="province"><c:forEach items="${province}" var="item">
									<c:choose>
										<c:when test="${org.province!=null&&fn:startsWith(item.code, org.province)}">
											<option value="${item.code}" selected="selected">${item.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.code}">${item.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach></select> <select onchange="areaUtils.cityChange()" id="city"
								class="select-btn">
								<c:forEach items="${city}" var="item">
									<c:choose>
										<c:when test="${org.city!=null&&fn:substring(item.code, 2, 4) eq org.city}">
											<option value="${item.code}" selected="selected">${item.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.code}">${item.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select> <select class="select-btn" id="district">

								<c:forEach items="${district}" var="item">
									<c:choose>
										<c:when test="${org.district!=null&&fn:substring(item.code, 4, 6) eq org.district}">
											<option value="${item.code}" selected="selected">${item.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.code}">${item.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="param_set">
							<label class="param" style="float:left;">详细地址：</label>
							<textarea id="road" class="big-txt-area" rows="3"
								placeholder="不能超过70字">${org.street}</textarea>
						</div>
						<div class="param_set">
							<label class="param" style="float:left;">机构简介：</label>
							<textarea id="org_introduce" class="big-txt-area" rows="5"
								placeholder="不能超过300字">${org.about}</textarea>
						</div>
						<div class="param_set">
							<label class="param" style="float:left;">详细介绍：</label>
							<div style="width:650px;display: inline-block;">
								<script type="text/plain" id="org_details"
									name="org_details">
           								 </script>
							</div>
						</div>
						<div style="padding:10px 110px 20px">
							<input class="save" value="保存"
								onclick="orgMsgControl.submitForm()" type="button" /><input
								class="back" value="返回" type="button" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
