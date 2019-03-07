<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>体检报告</title>
<t:base type="vue"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
<link rel="stylesheet" type="text/css"
	href="/static/css/physicalReport.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<link rel="stylesheet" href="/static/css/page.css">
<t:base type="jquery,layer"></t:base>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
			<div class="title fl">
				<a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a
					href="#" class="top_cite">健康记录</a>&nbsp;&nbsp;>&nbsp;&nbsp;添加病例
			</div>
			<div class="container fr">
				<ul class="report">
					<li><span>体检报告</span></li>
					<li><span class="addTest">添加体检</span></li>
				</ul>
				<ol class="reportDetail">
					<li v-if = "results != ''"  v-for = 'r in results'>
						<div class="content">
							<dl>
								<dt v-cloak>{{r.title}}</dt>
								<dd class = "image-container">
										<img v-if = "isImg(r.img1)" v-cloak :src= "r.img1" id="content_img1" alt="">
										<img v-if = "isImg(r.img2)" v-cloak :src= "r.img2" id="content_img2" alt="">
										<img v-if = "isImg(r.img3)" v-cloak :src= "r.img3 " id="content_img3" alt="">
								</dd>
								<dd>{{r.description}}</dd>
							</dl>
							<div class="editBtn">
								<span>
									<a v-on:click = "editReport($event, r.id)" class="editReport">编辑</a>
								</span>
								<span>
									<a v-on:click = "deleteReport($event, r.id)" class="deleReport">删除</a>
									<input :value = "r.id" class = "reportId" type = "hidden" >
								</span>
							</div>
						</div>
					</li>
					<li v-if = "results == ''" class = "noData">
						<span style = "line-height: 50px;">暂无记录</span>
					</li>
				</ol>
				<div class="deleteTip"><p style="font-size:16px;padding-top:20px;">删除操作不可恢复，你确定要删除？</p></div>
				<div id="physicalPage" class="page_Container">
				</div>
				<form id="physical">
					<div class="reportPop" style="display: none">
						<p>
							<label for="">体检日期</label> <input type="text" value=""
								id="physicalsDate" readonly><br /> <label>体检机构</label> <input
								type="text" placeholder="广东省人民医院" id="physicalsOrg">
						</p>
						<div class="description">
							<textarea placeholder="体检描述" id="description"></textarea>
						</div>
						<ul class="imgSet"></ul>
						<div class="upload">
							<a href="javascript:void(0)" class="file a-upload"> 上传图片 <input
								type="file" class="file" name="pictures" id="pictures"
								multiple="multiple" />
							</a>
							<a href="javascript:void(0)" class="file">清空</a>
							<span class="ATips">您可上传3张</span>
						</div>
					</div>
				</form>
			</div>
				</div>
		</div>
	</div>
</body>

<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/plugins/json/json2.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/physical_validate.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/physicalReport.js"></script>
<script type="text/javascript" src="/static/plugins/image/jquey-bigic.js"></script>
<script type="text/javascript">  

jQuery(function() {
	menuSetting({
		parent : 2,
		child : 1
	});
	//监听模型数据，渲染完成后执行事件
	reportModel.getModel().$watch('results',function(){
		$('.image-container img').bigic();
	});
	//初始化模型
	reportModel.getModel().results = '${data}' == '[]' ? '' : JSON.parse('${data}');
	reportModel.getModel().totalPage = '${totalPage}' || 0;
	reportModel.getModel().curPage = '${curPage}' || 1;
	//绑定弹出图片
	reportModel.getModel().popurImg();
	//初始化分页
	if ('${data}' != '[]') {
		pageUtil.getPageUtil();
	}
});
</script>
</html>