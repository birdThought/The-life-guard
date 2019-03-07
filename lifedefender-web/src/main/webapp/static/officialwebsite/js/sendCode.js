/**
 * 发送验证码
 * <p>需要添加layer插件作为消息提示
 * <p>jQuery插件
 */

var sendCode = {}

sendCode.interValObj;	// timer变量，控制时间
sendCode.count = 60;	// 间隔函数，1秒执行
sendCode.curCount;		// 当前剩余描述
sendCode.codeLength = 4;// 验证码长度

/**
 * 发送邮箱验证码
 * @param email 邮箱
 * @param $element 点击发送的按钮对象
 * @param cacheType email reset
 * @param $saveUserIdElement 保存userId的对象
 */
sendCode.sendRandCodeToEmail = function(email, $element, cacheType, $saveUserIdElement){
	var _data = "email="+email+'&temp='+new Date()+'&cache='+cacheType;
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "releaseControl.do?sendValidCodeToEmail",
		data: _data,
		dataType: 'json',
		beforeSend:function(){
			layer.load()
		},
		complete:function(){
			//方法执行完毕，效果自己可以关闭，或者隐藏效果
			layer.closeAll("loading")
		},
		success: function(result) {
			layer.msg(result.msg);
			if(result.success){
				if($saveUserIdElement != null) {
					$saveUserIdElement.val(result.obj);
				}
				sendCode.sendMessage($element);
			}
		}
	});
}

/**
 * 发送手机验证码
 * @param mobile 手机
 * @param $element 点击发送的按钮对象
 * @param cacheType mobile reset
 * @param $saveUserIdElement 保存userId的对象
 */
sendCode.sendRandCodeToMobile = function(mobile, $element, cacheType, $saveUserIdElement){
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "/register?sendValidCode",
		data: "mobile="+mobile+'&temp='+new Date()+'&cache='+cacheType,
		dataType: 'json',
		beforeSend:function(){
			layer.load();
		},
		complete:function(){
			layer.closeAll("loading");
			//方法执行完毕，效果自己可以关闭，或者隐藏效果
		},
		success: function(result) {
			layer.msg(result.msg);
			if(result.success){
				if($saveUserIdElement != null) {
					$saveUserIdElement.val(result.obj);
				}
				sendCode.sendMessage($element);
			}
		}
	});
}

/**
 * 状态修改为已发送
 * @param $element 点击发送的按钮对象
 */
sendCode.sendMessage = function($element){
	sendCode.curCount = sendCode.count;  
    //设置button效果，开始计时 
	$element.attr("disabled", "true");  
	$element.val("重新发送（" + sendCode.curCount + "）秒");  
	sendCode.interValObj = window.setInterval(function(){
		/**
		 * timer处理函数
		 */
		if (sendCode.curCount == 0) {                  
			window.clearInterval(sendCode.interValObj);//停止计时器  
			$element.removeAttr("disabled");//启用按钮  
			$element.val("重新发送验证码");  
			code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效      
	    }
	    else {
	    	sendCode.curCount--;
	        $("#btnCode").val("重新发送（" + sendCode.curCount + "）秒");
	    }
	}, 1000);
}