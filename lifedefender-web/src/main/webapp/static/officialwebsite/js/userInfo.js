$(function(){
	// 上传头像
	$("div.addMyself").on('change', '#path', function(){
		var $this = $(this);
		photoUpload.showPreview($this[0].files[0],"commonControl/uploadFile/img.do", $("#path"),"#upload_img","[name='img_hidden']");
	});
	$(".photo_2").on("click",function(){
		layer.open({
			type:1,
			title:['自定义头像','font-size:18px;text-align:center;'],
			content:$('.addMyself'),
			moveType: 1,
			btn:['确定','取消'],
			area: '330px',
			yes: function(index){
				var relativePath = $("[name='img_hidden']").val();
				var userId = $("#id").val();
				if(relativePath != "" && userId != ""){
					photoUpload.upload("memberControl.do?modifyPhoto", relativePath, userId, "photo");
					// 关闭layer
					setTimeout(function(){
						layer.close(index);
					}, 800);
				}
			}
		});
	});
	
	// 手机验证与邮箱验证
	var mobileVerified = $("[name='info_mobileVerify']").val();
	var emailVerified = $("[name='info_emailVerify']").val();
	var mobile = $("#info_mobile").val();
	mobileVerify(mobileVerified, mobile != "");
	var email = $("#info_email").val();
	emailVerify(emailVerified, email != "");
	
});

function mobileVerify(isBinding, isExist){
	var $a = $("li.tel a");
	if(isExist){
		if(isBinding === "true"){
			$a.text("号码修改");
			$("#info_mobile").attr("disabled", "disabled");
		}else{
			$a.text("前去验证");
		}
	}else{
		$a.remove();
	}
}
function emailVerify(isBinding, isExist){
	var $a = $("li.email a");
	if(isExist){
		if(isBinding === "true"){
			$a.text("号码修改");
			$("#info_email").attr("disabled", "disabled");
		}else{
			$a.text("前去验证");
		}
	}else{
		$a.remove();
	}
}

function containSpecial(s)      
{      
    var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);      
    return (containSpecial.test(s));      
}
function updateUserInfo(){
	var realName= $("input[name='realName']").val();
	var sex = $("select[name='sex']").val();
	var birthday = $("input[name='birthday']").val();
	var mobile = $("#info_mobile").val();
	var mobileVerify = $("[name='info_mobileVerify']").val() === "true";
	var email = $("#info_email").val();
	var emailVerify = $("[name='info_emailVerify']").val() === "true";
	
	// 数据校验
	if(realName == "") {
		layer.msg("请填写姓名");
		$("input[name='realName']").focus();
		return ;
	}
	if(containSpecial(realName)) {
		layer.msg("姓名存在特殊字符，请重写");
		$("input[name='realName']").focus();
		return ;
	}
	if(realName.length > 18) {
		layer.msg("姓名长度不能超过18个字符");
		$("input[name='realName']").focus();
		return ;
	}
	if(sex == 0) {
		layer.msg("请选择性别");
		$("select[name='sex']").focus();
		return ;
	}
	if(birthday == "") {
		layer.msg("请输入出生日期");
		$("input[name='birthday']").focus();
		return ;
	}
	
	var regex_mobile = /^[1][3,4,5,7,8][0-9]{9}$/;
	var regex_email = /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/;
	
	if(!regex_mobile.test(mobile) && mobile != ""){
		layer.msg("请输入正确的手机号码");
		$("#info_mobile").focus();
		return ;
	}
	if(!regex_email.test(email) && email != ""){
		layer.msg("请输入正确的邮箱号码");
		$("#info_email").focus();
		return ;
	}
	// 数据拼接
	var _data="realName="+realName+"&sex="+sex+"&birthday="+birthday
		+"&mobile="+mobile+"&mobileVerify="+mobileVerify+"&email="+email
		+"&emailVerify="+emailVerify;
	// 发送请求
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url: "memberControl.do?updateUserInfo",
		data: _data,
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		beforeSend:function(){
			layer.load();
		},
		complete:function(){
			layer.closeAll("loading");
		},
		success: function(result) {
			layer.msg(result.msg);
			if(result.success){
				setTimeout(function(){
					window.location.href="memberControl.do?getUserInfor";
				}, 1000);
			}
		}
	});
}




		


