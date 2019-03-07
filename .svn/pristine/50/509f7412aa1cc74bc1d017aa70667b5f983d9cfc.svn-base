<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>Sign In</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    
    <t:base type="jquery"></t:base>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/adminX/js/html5shiv.js"></script>
    <script src="/static/adminX/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body" onkeyup="_keyDown()">
<center>
	<div class="container">
	    <form class="form-signin" action="">
	        <table>
	        	<tr>
	        		<td colspan="2" align="center">
	        			<h1>终端测试</h1>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>IMEI</td>
	        		<td>
						<input type="text" id="imei" value="12345678966666">
					</td>
	        	</tr>
	        	<tr>
	        		<td>终端类型</td>
	        		<td>
						<select id="TerminalType">
							<option value="1">LCH-B</option>
							<option value="2">HL-031</option>
						</select>
					</td>
	        	</tr>
	        	<tr>
	        		<td>内容</td>
	        		<td>
	        			<textarea rows="6" cols="20" style="width: 700px;" id="content"></textarea>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td colspan="2" align="center">
	        			<input type="button" value="send" onclick="testBut()">
	        		</td>
	        	</tr>
	        </table>
	
	    </form>
	</div>
</center>
<button class="btn">click</button>
<script type="text/javascript" src="/static/plugins/layer/layer.js"></script>
<script type="text/javascript" src="/static/common/js/common.js"></script>

<script type="text/javascript">
$("button.btn").click(function() {
	$.ajax({
		url: 'http://127.0.0.1:8080/lifeshs.web/testControl.do?testException',
		type: 'GET',
		success: function(){
			alert("success");
		}
	});
});
function testBut(){
	var content = $("#content").val();
	var imei = $("#imei").val();
	var TerminalType = $("#TerminalType").val();
	if(content == "" || imei == ""){
		layer.alert("请输入值");
		return ;
	}
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "testControl.do?mian",
		data: 'content='+content+"&imei="+imei+"&TerminalType="+TerminalType,
		dataType: 'json',
		success: function(result) {
			layer.alert(result.msg);
		}
	});
}
</script>
</body>
</html>
