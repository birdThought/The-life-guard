<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 引入JSTL表达式 -->
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>HOME</title>
	</head>
	<body>
		登录成功<br/>
		<%=path%><br/>
		<%=basePath%><br/>
		<button onclick="logout();">退出登录</button>
	</body>
	<script src="/static/adminX/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		function logout(){
			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url: "/login/logout",
				data: "temp="+new Date(),
				dataType: 'json',
				beforeSend:function(){
					//这里是开始执行方法，显示效果，效果自己写
					console.log("这里是开始执行方法，显示效果，效果自己写");
				},
				complete:function(){
					//方法执行完毕，效果自己可以关闭，或者隐藏效果
					console.log("方法执行完毕，效果自己可以关闭，或者隐藏效果");
				},
				error : function(e) {// 请求失败处理函数
					console.log(e);
				},
				success: function(result) {
					if(!result.success){
						alert(result.msg);
					}else{
						alert(result.msg);
						window.location.href = "/login";
					}
				}
			});
		}
	</script>
</html>
