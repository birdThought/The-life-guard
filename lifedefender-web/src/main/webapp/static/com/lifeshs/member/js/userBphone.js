$(function(){
	
	var verified = $("[name='Bphone_verified']").val();
	if(verified === "false"){
		bindMobile.firstBind("memberControl.do?modifyMobileFirstTime", $("form.findPsw"));
	}
	
	// 发送验证码
	$("form.findPsw").on("click", "#btnCode", function(){
		var mobile = $("#mobile").val();
		if(mobile != ""){
			var $element = $("#btnCode");
			sendCode.sendRandCodeToMobile(mobile, $element, "mobile", null);
		}
	});
	
});