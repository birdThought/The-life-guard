$(function(){
	_validate();
});

function _validate(){
	$("[name='userPwd']").validate({
		errorPlacement: function(error, element){
			error.appendTo(element.parent());
		},
		submitHandler : function(){
			var _password_old = $("[name='password_old']").val();
			var _password_new = $("[name='password_new']").val();
			
			_password_old=encodeStr(_password_old);
			_password_new=encodeStr(_password_new);
			var _data = 'password_old=' + _password_old + '&password_new=' + _password_new;
			
			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url: 'memberControl.do?updateUserPwd',
				data: _data,
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				dataType: 'json',
				beforeSend:function(){
					layer.load()
				},
				complete:function(){
					layer.closeAll("loading");
				},
				success: function(result) {
					layer.msg(result.msg);
					setTimeout(function(){
						location.href = "memberControl.do?showUserPwd";	// 刷新页面
					},1000);	// 缓冲1秒
				}
			});
		},
		rules:{
			password_old:{
				required: true
			},
			password_new:{
				required:true,
				minlength: 6,
                maxlength: 16,
				checkPassword:true
			},
			password_comf:{
				equalTo : "#password_new"
			}
		},
		messages:{
			password_old:{
				required:"请填写原密码"
			},
			password_new:{
				required:"请填写密码",
				rangelength:"密码应该在6-16位"
			},
			password_comf:{
				equalTo:"输入的密码的不一致"
			}
		}
	});
}