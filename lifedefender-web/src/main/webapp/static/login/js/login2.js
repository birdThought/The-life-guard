$(document).ready(function() {
	$('#sub_login').click(function(){
		login();
	});
});


function _keyDown(){
	 if (event.keyCode == 13){
	    login();
	 }
}

function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	if(username == ""){
		layer.msg("请输入用户名");
		return;
	}
	if(password == ""){
		layer.msg("请输入密码");
		return;
	}
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "loginControl.do?checkUser",
		data: "userName="+username+'&pwd='+password+'&temp='+new Date(),
		dataType: 'json',
		beforeSend:function(){
			//这里是开始执行方法，显示效果，效果自己写
			console.log("这里是开始执行方法，显示效果，效果自己写");
//			//加载层-默认风格
			layer.load();
////			//此处演示关闭
//			setTimeout(function(){
//			    layer.closeAll('loading');
//			}, 2000);
			//加载层-风格4
//			layer.load(1, {shade: [0.8, '#393D49']})
//			layer.msg('正在努力的登录...', {icon: 16});
		},
		complete:function(){
			//方法执行完毕，效果自己可以关闭，或者隐藏效果
			console.log("方法执行完毕，效果自己可以关闭，或者隐藏效果");
		},
		success: function(result) {
//			layer.msg(result.msg);
//			layer.alert(result.msg);
			console.log(result);
			layer.closeAll('loading');
			if(!result.success){
				layer.alert(result.msg,{skin:'layer-ext-seaning',icon: 11});
			}else{
				layer.msg(result.msg);
				window.location.href = "/login";
			}
		}
	});
}


function testBut(){
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "testControl/test.do",
		data: 'temp='+new Date(),
		dataType: 'json',
		headers: {
            "Accept": "application/json; odata=verbose",
            "Content-Type": "application/json; odata=verbose"
        },
		beforeSend:function(){
			//这里是开始执行方法，显示效果，效果自己写
		},
		complete:function(){
			//方法执行完毕，效果自己可以关闭，或者隐藏效果
			//console.log("方法执行完毕，效果自己可以关闭，或者隐藏效果");
		},
		success: function(result) {
			layer.alert(result.msg);
		}
//		error : function(e) {// 请求失败处理函数
//			console.log("请求失败：");
//			console.log(e);
////			alert("no good "+JSON.stringify(e));
//		}
		
	});
}

