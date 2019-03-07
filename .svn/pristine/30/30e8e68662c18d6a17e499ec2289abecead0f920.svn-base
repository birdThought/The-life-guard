<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>添加成员</title>
<title>用户主页</title>
<t:base type="jquery,layer"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico"
	mce_href="favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css" href="/static/login/css/green.css">
<link rel="stylesheet" type="text/css" href="/static/css/familyInfor.css">
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
	<%@include file="/context/header.jsp"%>
	<div class="view-body wrap">
		<%@include file="/context/nav_left.jsp"%>
		<div class="right-wrap">
		<div class="title fl">
			<a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a
				href="#" class="top_cite">家庭组</a>&nbsp;&nbsp;>&nbsp;&nbsp;新增成员
		</div>
		<div class="container fr">
			<h3>新增成员</h3>
			<form class="memberRegister" id="memberRegister">
				<h3>新家庭成员添加</h3>
				<ul>
					<li class="newInfor"><label for="">&nbsp;&nbsp;&nbsp;用户名</label>
						<input type="text" placeholder="请输入用户名" name="userName"
						id="userName"></li>
					<li class="newInfor"><label for="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码</label>
						<input type="password" placeholder="请输入密码" name="pwd" id="pwd">
					</li>
					<li class="newInfor"><label for="">确认密码</label> <input
						type="password" placeholder="请输入确认密码" name="confirmPwd"
						id="confirmPwd"></li>
					<li style="margin-top: 15px"><label for="checkbox"
						id="_checkbox"> <input type="checkbox" id="agreement"
							name="agreement" class="keep" checked="checked" required><span
							style="margin-left: 10px">我已阅读并接受 <a
								href="javascript:void(0);" class="moument"
								onclick="clickHere();">《生命守护用户服务协议》</a></span>
					</label> <!-- <input type="checkbox" class="keep" name="agreement">
              		   我已阅读并接受 
                 <a href="#" class="moument">《生命守护用户服务协议》</a> --></li>
				</ul>
				<button type="button" id="next">下一步</button>
			</form>
		</div>
			</div>
	</div>
</div>
<script type="text/javascript"
	src="/static/common/js/validate.expand.js"></script>
<script type="text/javascript"
	src="/static/common/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="/static/common/js/messages_zh.min.js"></script>
<script type="text/javascript"
	src="/static/officialwebsite/js/register_validate.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/family.js"></script>
<script type="text/javascript" src="/static/login/js/icheck.js"></script>
<script type="text/javascript">
$(function() {
	menuSetting({
		parent : 4,
		child : 2
	});
	
	$('#member').validate({
		rules:{
			mobile:{
				required:true,
				mobile:true
			},
			birthday:{
				required:true,
				date:true 				
			}
	
		},
		
		messages: {
			mobile:{
				required:"手机号码不能空"
			},
			birthday:{
				required:"日期不能为空",
				date: "请输入合法的日期 ."
			}
	
		},
		
		errorPlacement: function(error, element) {
			var div = $("<div />").append(error);
			div.css("color","red").css("margin","0 150px");
			div.appendTo(element.parent());
		},
		invalidHandler : function(){
			return false;
		},
		submitHandler: function(form){
			var id = $("input[name='id']").val();
			var userName = $("input[name='userName']").val();
			var mobile = $("input[name='mobile']").val();
			var birthday = $("input[name='birthday']").val();
			var sex = $("input[name='sex']").val();
			var height = $("input[name='height']").val();
			var weight = $("input[name='weight']").val();
			var hip = $("input[name='hip']").val();
			var waist = $("input[name='waist']").val();
			var bust = $("input[name='bust']").val();
			
			var _data = 'id=' + id + '&userName=' + userName + '&mobile=' + mobile +
				'&birthday=' + birthday + '&sex=' + sex + '&height=' + height +
				'&weight=' + weight + '&hip=' + hip + '&waist=' + waist + '&bust=' + bust;
				
			$.ajax({
	    		async : true,
	    		cache : false,
	    		type: 'POST',
	    		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
	    		url: 'familyControl.do?insertNewMemberInfo',
	    		data: _data,
	    		success: function(result){
	    			if(result.success){
	    				layer.open({
	    					type: 1,
	    					title:['确认窗口', 'font-size:18px;text-align:center'],
	    					content: '成功添加"' + userName + '"到家庭组',
	    					area:'380px',
	    					moveType: 1,
	    					btn:['确定','取消'],
	    					yes:function(index){
	    						parent.layer.close(index);
	    						layer.msg(result.msg);
	    						window.location.href = "familyControl.do?getFamilyMember";
	    					}
	    				});
	    			}else{
	    				layer.msg(result.msg);
	    			}
	    		}
	    	});
		}
	});
});
$('input').iCheck({
	checkboxClass : 'icheckbox_minimal-green',
	radioClass : 'iradio_minimal-green'
});
</script>
</body>
</html>