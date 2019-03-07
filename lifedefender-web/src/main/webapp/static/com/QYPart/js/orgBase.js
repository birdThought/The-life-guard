var BaseSet = {
	createErrorLabel : function(text, objAfter) {
		var error = document.createElement("label");
		$(error).attr({
			"id" : "error",
			"style" : "margin-left:15px;color:#ff0000;"
		});
		$(error).text(text);
		$(error).insertAfter($("#" + objAfter));
	},
	changePsw : function() {
		$("body #error").each(function() {
			$(this).remove();
		});
		var oldPsw = $("#old_psw").val();
		var newPsw = $("#new_psw").val();
		var confirmPsw = $("#confirm_psw").val();
		if(oldPsw==''){
			this.createErrorLabel("请输入旧密码！", "old_psw");
			return;
		}
		if (!checkUtils.checkLegalPsw(newPsw)) {
			this.createErrorLabel("密码长度在6-16之间，只能包含字符、字母和数字", "new_psw");
			return;
		}
		if(newPsw==''){
			this.createErrorLabel("请输入新密码！", "new_psw");
			return;
		}
		if(confirmPsw==''){
			this.createErrorLabel("请确认新密码！", "confirm_psw");
			return;
		}
		if (newPsw != confirmPsw) {
			this.createErrorLabel("两次密码输入不正确，请重新输入！", "new_psw");
			return;
		}
		var json={oldKey:encodeStr(oldPsw),newKey:encodeStr(newPsw)};
		layer.load(1, {
			shade : [ 0.5, '#393D49' ]
		});
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : 'orgUserControl.do?changeOrgPsw',
			data : JSON.stringify(json),
			success : function(result) {
				layer.closeAll();
				if(result.success){
					layer.msg("修改成功！");
					$.cookie("pwdStrong",$.checkPswStrong(newPsw));
					setTimeout(function() {
						window.location.reload();
					}, 1000);
				}else{
					layer.msg("旧密码不正确，请重新输入");
					BaseSet.createErrorLabel("旧密码不正确，请重新输入","old_psw");
				}
			},
			error : function() {
				layer.closeAll();
			}
		});
	}
}

function pwStrength(pwd) {
	O_color = "#DDDDDD";
	L_color = "#FF0000";
	M_color = "#FF9900";
	H_color = "#33CC00";
	if (pwd == null || pwd == '') {
		Lcolor = Mcolor = Hcolor = O_color;
		strength_notice.style.display = 'none';
	} else {
		S_level = $.checkPswStrong(pwd);
		console.log(S_level);
		switch (S_level) {
			case 0:
				Lcolor = Mcolor = Hcolor = O_color;
			case 1:
				Lcolor = L_color;
				Mcolor = Hcolor = O_color;
				strength_notice.style.display = 'inline-block';
				strength_notice.style.color = 'red';
				strength_notice.innerText = "弱";
				break;
			case 2:
				Lcolor = Mcolor = M_color;
				Hcolor = O_color;
				strength_notice.style.display = 'inline-block';
				strength_notice.style.color = '#FF9900';
				strength_notice.innerText = "中";
				break;
			case 3:
				Lcolor = Mcolor = Hcolor = H_color;
				strength_notice.innerText = "强";
				strength_notice.style.color = '#48c858';
				break;
			default:
				Lcolor = Mcolor = Hcolor = H_color;
				strength_notice.style.display = 'none';
		}
	}
	document.getElementById("strength_L").style.background = Lcolor;
	document.getElementById("strength_M").style.background = Mcolor;
	document.getElementById("strength_H").style.background = Hcolor;
	return;
}