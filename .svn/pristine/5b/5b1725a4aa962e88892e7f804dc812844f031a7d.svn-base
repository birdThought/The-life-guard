<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>编辑病历</title>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<t:base type="jquery,layer"></t:base>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">
				<a href="/login" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href="recordHealthFileControl.do?enter" class="top_cite">健康档案</a>&nbsp;&nbsp;>&nbsp;&nbsp;编辑病历
			</div>
			<div class="container fr">
				<h3>编辑病历</h3>
				<ul class="saveOrback">
					<li><span>保存</span></li>
					<li><span>返回</span></li>
				</ul>
				<ul class="editCase">
					<li>
						<label>病历标题</label>
						<input type="text" name="case_title" value="${data.title}" placeholder="请输入疾病名称">
					</li>
					<li>
						<label>就诊医院</label>
						<input type="text" name="case_hospital" value="${data.hospital}" placeholder="请输入就诊医院名称">
					</li>
					<li>
						<label>科室</label>
						<select name="parent" id="parent">
						<c:forEach var="parent" items="${parents}">
						<option value="${parent.id}">${parent.name}</option>
						</c:forEach>
						</select>
						<select name="child" id="child">
							<option value="0">请选择</option>
						</select>
					</li>
					<li>
						<label>就诊时间</label> <input type="text" name="visitingDate" value="${data.date}" readonly>
					</li>
					<li>
						<table class="addCase" style="word-break:break-all;table-layout:fixed">
							<tbody>
								<tr>
									<td>姓名</td>
									<td>${data.name}</td>
								</tr>
								<tr>
									<td>性别</td>
									<td>${data.sex}</td>
								</tr>
								<tr>
									<td>出生年月</td>
									<td>${data.birthday}</td>
								</tr>
								<tr>
									<td>年龄</td>
									<td>${data.age}</td>
								</tr>
							</tbody>
						</table>
					</li>
					<li>
						<label>医生诊断</label>
						<input type="text" name="case_doctorDiagnosis" placeholder="比如疾病名称" value="${data.diagnosis}">
					</li>
					<li>
						<label>基础病情</label>
						<textarea name="case_basicCondition" placeholder="就医情况与处理方案">${data.basicCondition}</textarea>
					</li>
				</ul>
				<div class="record" style="display: block;">
					<div class="record_top">
						<div class="record_sort">
							<div class="sortBtn asc">病程时间<span class="trangle"></span></div>
						</div>
						<div class="record_course">
							<a href="javascript:void(0)" class="green_btn add_btn"><span>添加病程</span></a>
						</div>
					</div>
					<div class="record_default" style="display: none;">
						<p>提示信息：您可以按时间记录病程，添加相片等附件信息</p>
					</div>
					<ul class="timeline info_list"><%-- 
						<c:forEach var="course" items="${data.courses}">
						<li>
							<div class="left_contain">
								<div class="left_wrap" style="font-size: 16px;">
									<div></div>
									<div class="time">${course.visitingDate}</div>
								</div>
							</div>
							<div class="right_contain" style="width: 91%; left: 92px">
								<div class="out_circle"></div>
								<div class="border_contain">
									<div class="remark">
										<div style="overflow:hidden">
											<div style="float:left">备注:</div>
											<div style="float:right">
												<span class="courseEditSpan">编辑</span>
												<span class="courseDeleteSpan">删除</span>
											</div>
										</div>
										<p style="font-size: 15px">
											${course.remark}
										</p>
									</div>
									<div class="health_image_contain" style="padding: 15px">
										<ul>
											<li>
												<c:if test="${!empty course.img1}"><img name="img1" src="${course.img1}" /></c:if>
												<c:if test="${!empty course.img2}"><img name="img2" src="${course.img2}" /></c:if>
												<c:if test="${!empty course.img3}"><img name="img3" src="${course.img3}" /></c:if>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<input type="hidden" name="case_typeNumber" value="${course.typeNumber}" />
							<input type="hidden" name="case_orderNumber" value="${course.orderNumber}" />
						</li>
						</c:forEach>
					 --%></ul>
				</div>

				<div class="reportPop" style="display: none;">
					<p>
						<label for="">病程时间</label>
						<input type="text" name="visitingDate_case" readonly>
						<br />
						<label>病程类型</label>
						<select name="courseType">
							<option value="1">首诊</option>
							<option value="2">复诊</option>
							<option value="3">出院</option>
						</select>
						<br />
						<label class="alignTop">备注</label>
						<textarea name="remark" placeholder="就医情况与处理方案"></textarea>
					</p>
					<div class="upload">
						<a href="javascript:void(0)" class="file a-upload">上传图片<input name="files" id="files" type="file" class="file caseFile" multiple></a>
						<button name="courseClear" class="file">清空</button>
						<span></span>
					</div>
					<div class="tmpDiv"></div>
				</div>
			</div>
				</div>
		</div>
	</div>
	<input type="hidden" name="childeDepartmentId" value="${data.departmentId}" />
	<input type="hidden" name="medicalId" value="${data.id}" />
</body>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script src="/static/officialwebsite/js/dateFormat.js"></script>
<script src="/static/officialwebsite/js/calculate.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/healthFileControl.js"></script>
<script src="/static/com/lifeshs/member/record/editCase.js"></script>
<script type="text/javascript">
$(function(){
	menuSetting({
		parent : 2,
		child : 0
	});
});
</script>
</html>