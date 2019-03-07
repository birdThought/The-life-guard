$(function(){
	var verified = $("[name='Bemail_verified']").val();
	if(verified === "false"){
		bindEmail.firstBind("memberControl.do?modifyEmailFirstTime", $("form.findPsw"));
	}
	// 发送验证码
	$("form.findPsw").on("click", "#btnCode", function(){
		var email = $("#email").val();
		if(email != ""){
			var $element = $("#btnCode");
			sendCode.sendRandCodeToEmail(email, $element, "email", null);
		}
	});
});