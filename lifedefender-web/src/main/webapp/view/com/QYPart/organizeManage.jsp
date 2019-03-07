<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>机构管理</title>
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
<link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
<link rel="stylesheet" href="/static/com/QYPart/css/organize.css">
<script type="text/javascript" src="/static/com/QYPart/js/QYcommon.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
<script type="text/javascript" src="/static/com/QYPart/js/organize.js"></script>
<script type="text/javascript">
	window.onload = function() {
		menuSetting({
			parent : 0,//父级菜单的索引
			child : 0
		//子级菜单的索引
		});
		
		/** 为tr绑定click事件 */
		var $trs = jQuery("#jigou tr");
		jQuery.each($trs, function(index) {
			if(index != 0) {
				jQuery($trs[index]).find("td:eq(0)>a.hasNext").click(function() {
					var $tr = jQuery(this).parents("tr");
					var $next = $tr.next("tr");
					var id = $tr.attr("id");
					
					if($next.hasClass("child"+id)) {
						$next.slideUp(300);
						$next.remove();
					} else {
						treeSet.getChildData(id, 0);
					}
				});	
			}
		});
		
		
		jQuery.ajax({
			type: 'GET',
			url: 'orgControl.do?getOrgChildServiceData',
			beforeSend: function() {
				layer.load(2);
			},
			success: function(result) {
				var data = result.obj;
				var $tbody = jQuery("tbody.service_tbody");
				$tbody.empty();
				
				if(result.success && data.length > 0) {
					jQuery.each(data, function(index) {
						var $tr = jQuery("<tr></tr>");
						var $td1 = jQuery("<td>"+data[index].orgName+"</td>");
						var $td2 = jQuery("<td>"+data[index].contact+"</td>");
						var $td3 = jQuery("<td>"+data[index].contactNumber+"</td>");
						var $td4 = jQuery("<td>"+data[index].amountOfMemeber+"</td>");
						var $td5 = jQuery("<td><a href='javascript:treeSet.lookJigou("+data[index].id+")'>查看</a></td>");
						$tr.append($td1);
						$tr.append($td2);
						$tr.append($td3);
						$tr.append($td4);
						$tr.append($td5);
						$tbody.append($tr);
					});
				} else {
					var $tr = jQuery("<tr><td colspan='5'>暂时没有数据</td></tr>");
					$tbody.append($tr);
				}
			},
			complete: function() {
				layer.closeAll("loading");
			}
		});
		
		// 添加一个非空校验
		if(jQuery("#jigou>tbody").children("tr").length == 1) {
			var $noData = jQuery('<tr><td colspan="6">暂时没有数据</td></tr>');
			jQuery("#jigou>tbody").append($noData);
		}
		
	}
</script>
</head>

<body>
	<%@include file="/context/QYHeader.jsp"%>
	<div class="container">
		<%@include file="/context/QYMenu.jsp"%>
		<div class="right_content">
			<div class="right_body">
				<div class="right_title">
					<label class="action"> 机构管理 </label> <a href="orgControl.do?addChildOrgPage" class="search-btn">机构添加</a>
				</div>
				<div class="main_contain">
					<!--管理机构-->
					<div class="bigTitle">
						<h3>所属管理机构</h3>
						<p>所属管理机构指的是该机构的下级管理机构（例如:分部--区域管理--代理商）</p>
					</div>
					<table id="jigou" class="service_table" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>机构</td>
							<td>联系人</td>
							<td>联系号码</td>
							<td>下级管理机构</td>
							<td>下级服务机构</td>
							<td>操作</td>
						</tr>
						<c:forEach items="#{datas}" var="data" varStatus="status">
						<tr id="${data.id}">
							<td style="text-align: left; padding-left: 15px;">
								<a href="#" style="color:#000" class="<c:if test="${data.hasNext}">hasNext</c:if>">
								<img
									src="<c:choose><c:when test="${data.type==0}">static/images/folder.png</c:when><c:otherwise>static/images/file.png</c:otherwise></c:choose>"
									style="margin-right: 10px" />${data.orgName}</a>
							</td>
							<td>${data.contact}</td>
							<td>${data.contactNumber}</td>
							<td>${data.amountOfManagement}</td>
							<td>${data.amountOfService}</td>
							<td><a href="javascript:treeSet.lookJigou(${data.id})">查看</a></td>
						</tr>
						</c:forEach>
					</table>
					<!--服务机构-->
					<div class="bigTitle" style="margin-top:20px">
						<h3>直属服务机构</h3>
						<p>直属服务机构指的是该机构下的直接向用户提供服务的机构（例如:门店--健身房--家政服务点）</p>
					</div>
					<table class="service_table" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>机构</th>
								<th>联系人</th>
								<th>联系号码</th>
								<th>会员数</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="service_tbody"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="dialog_contain" style="display: none;">
		<div class="user_head">
			<img src="/static/images/head.png">
		</div>
		<div class="msg_contain">
			<ul>
				<li>用户名：用户名</li>
				<li>联系人：联系人</li>
				<li>联系号码：联系号码</li>
				<li>机构名称：机构名称</li>
				<li>地址：地址</li>
			</ul><!-- 
			<div class="dialog_btn_group">
				<button class="edit">编辑</button>
				<button class="details">详情</button>
			</div> -->
		</div>
	</div>
</body>
</html>
