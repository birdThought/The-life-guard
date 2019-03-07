define(function(require, exports, module) {
	require('QYcommon');
	require.async(['icheck','customRadio','jedate','commonCheck','fileUpload'], function() {
		jeDate({
			dateCell : "#birth",
			format : "YYYY-MM-DD",
			minDate : "1900-01-01",
			maxDate : jeDate.now(0),
			startMin : "1900-01-01",
			startMax : jeDate.now(0),
			zindex : 999,
			choosefun : function(elem, val) {
			}
		});
		customRadio.init({
			name : 'sexSelect',
			color : 'blue',
			onselect : function(result) {
				switch (result) {
				case '0'://女
					break;
				case '1'://男
					break;
				}
			}
		});
		customRadio.init({
			name : 'typeSelect',
			color : 'blue',
			onselect : function(result) {
				if (result == 'f') {
					$("#introduce_div").animate({
						"height": "show"
					});
				}
			},
			onUnSelect : function(result) {
				if (result == 'f') {
					$("#introduce_div").animate({
						"height": "hide"
					});
				}
			}
		});
		jQuery("input.back").click(function() {
			location.href = 'orgUserControl.do?employManage';
		});
	});
});



var addEmploy = {
	isManager : true,
	url : 'orgUserControl.do?addEmployee',
	headFileName:null,
	uploadFile : function() {
		photoUtil({
			targetId : 'headFile',
			displayId : 'avater',
			paramName : 'uploadFile',
			url : 'orgControl.do?uploadPhoto',
			limit : 3072,
			uploadFail : function() {
				addEmploy.headFileName = null;
			},
			uploadSuccess : function(data) {
				addEmploy.headFileName = data.obj;
			}
		});
	},
	createErrorLabel : function(text, objAfter, notAfter) {
		var error = document.createElement("label");
		$(error).attr({
			"id" : "error",
			"style" : "margin-left:15px;color:#ff0000;"
		});
		$(error).text(text);
		if (notAfter) {
			$(error).appendTo($("#" + objAfter));
			return;
		}
		$(error).insertAfter($("#" + objAfter));

	},
	errorCode : {
		0 : "该用户名已存在，请重新创建！"
	},
	submitForm : function() {
		$("body #error").each(function() {
			$(this).remove();
		});
		var isReturn = false;
		var userName = $("#account").val();
		if (!checkUtils.checkLegalUserName(userName)) {
			this.createErrorLabel("用户名只能包含字母、数字", "account");
			isReturn = true;
		}
		if(userName.length<6){
			this.createErrorLabel("用户名不能少于6位", "account");
			isReturn = true;
		}else if(userName.length>16){
			this.createErrorLabel("用户名不能超过16位", "account");
			isReturn = true;
		}

		var password = $("#psw").val();
		if (!checkUtils.checkLegalPsw(password)) {
			this.createErrorLabel("密码长度在6-16之间，只能包含字符、字母和数字以及部分特殊字符", "psw");
			isReturn = true;
		}
		var confirmPwd = $("#confirm_psw").val();
		if (password != confirmPwd) {
			this.createErrorLabel("2次输入密码不一样，请重新输入", "confirm_psw");
			isReturn = true;
		}
		var type = customRadio.getResult('typeSelect');
		type = type.length == 2 ? type = 2 : type == 'a' ? type = 0
				: type == 'f' ? type = 1 : type = null;
		if (!this.isManager) {
			if (type == null) {
				this.createErrorLabel("请选择员工类型", "selectType", true);
				isReturn = true;
			}
		}
		var realName = $("#name").val();
		if (realName == '') {
			this.createErrorLabel("姓名不能为空", "name");
			isReturn = true;
		} else {
			if (realName.length > 10) {
				this.createErrorLabel("真实姓名不能超过10位", "name");
				isReturn = true;
			}
		}
		var sex = customRadio.getResult('sexSelect') == 0 ? sex = false
				: sex = true;

		var phone = $("#phone").val();
		if (!checkUtils.checkPhone(phone)) {
			this.createErrorLabel("请输入合法的手机号码", "phone");
			isReturn = true;
		}
		var eMail = $.trim($("#e_mail").val());
		if (eMail != '' && !checkUtils.checkEmail(eMail)) {
			this.createErrorLabel("e-mail格式不正确", "e_mail");
			isReturn = true;
		}
		var workPhone = $("#workPhone").val();
		if (workPhone != '' && workPhone.length > 11) {
			this.createErrorLabel("工作电话不能超出11位", "workPhone");
			isReturn = true;
		}
		/*var addr = $("#addr").val();
		if (addr != '' && addr.length > 80) {
			this.createErrorLabel("家庭地址不能超出80个字符", "addr");
			isReturn = true;
		}*/

		if (isReturn)
			return;
		//birthday = parserDate(birthday);
		var json = {
			userName : encodeStr(userName),
			password : encodeStr(password),
			realName : realName,
			sex : sex,
			mobile : phone
		};
		if (!this.isManager) {
			json.userType = type;
			if (type == 1 || type == 2) {
				var about = $("#server_introduce").val();
				var detail = FilterUtils.filterScript(Editor.getContent());
				if (about.length > 100) {
					layer.alert("服务师简介不能超过100字");
					return;
				}
				if (about != '')
					json.about = about;
				if (detail != '')
					json.detail = detail;

			}
			var serveId = $("#serverSelect option:selected").val();
			if (serveId != undefined) {// 表示有开通过服务
				var groupId = $("#groupSelect option:selected").val();
				if (groupId == undefined) {
					layer.msg("未知错误");
					return;
				}
				json.groupId = groupId;
			}
		}
		var birthday = $("#birth").val();
		if (birthday != '') {
			json.birthday=parserDate(birthday);
		}
		if (this.headFileName != null)
			json.photo = this.headFileName;
		if (eMail != '') {
			json.email = eMail;
		}
		if (workPhone != '') {
			json.tel = workPhone;
		}
		/*if (addr != '') {
			json.address = addr;
		}*/
		layer.load(1, {
			shade : [ 0.5, '#393D49' ]
		});
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : addEmploy.url,
			data : JSON.stringify(json),
			success : function(result) {
				var b = result.success;
				if (!b) {
					var message = addEmploy.errorCode[result.msg];
					layer.msg(message);
					switch (result.msg) {
					case "0":
						addEmploy.createErrorLabel(message, "account");
						break;
					}
					return;
				}
				layer.msg("添加成功！");
				setTimeout(function () {
					window.location.href='orgUserControl.do?employManage';
				},1000);
			},
			complete : function() {
				layer.closeAll("loading");
			}
		});
	},
	getGroupList : function() {
		var server = $("#serverSelect option:selected").val();
		layer.load(1, {
			shade : [ 0.5, '#393D49' ]
		});
		$.ajax({
			type : 'GET',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			url : 'orgUserControl.do?getGroupListByService&server=' + server,
			success : function(result) {
				var b = result.success;
				addEmploy.updateGroupList(result.obj);
			},
			complete : function() {
				layer.closeAll("loading");
			}
		});
	},
	updateGroupList : function(data) {
		var str = '';
		for ( var index in data) {
			var item = data[index];
			str += '<option value="' + item.id + '">' + item.name + '</option>'
		}
		$("#groupSelect").html(str);
	}
}

var FilterUtils = {
	filterScript : function(s) {
		return s.replace(/<script.*?>.*?<\/script>/ig, '');
	}
}

var Editor = {
	uEditor : null,
	init : function(data) {
		this.uEditor = UE.getEditor('server_details', {
			toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'strikethrough',
					'horizontal', 'removeformat', '|', 'forecolor',
					'backcolor', 'insertorderedlist', 'insertunorderedlist',
					'selectall', '|', 'rowspacingtop', 'rowspacingbottom',
					'lineheight', '|', 'customstyle', 'paragraph',
					'fontfamily', 'fontsize', '|', 'directionalityltr',
					'directionalityrtl', 'indent', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyjustify', '|',
					'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
					'simpleupload' ] ],
			enableAutoSave : false,
			enableContextMenu : false,
			elementPathEnabled : false,
			maximumWords : 300,
			imageFieldName : 'path',
			imageActionName : 'uploadimage',
			initialFrameHeight : 200
		});
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
			if (action == 'uploadimage') {
				return 'commonControl/uploadFile/img.do';
			} else {
				return this._bkGetActionUrl.call(this, action);
			}
		}
	},
	getContent : function() {
		return this.uEditor.getContent();
	}
}

var parserDate = function(date) {
	var t = Date.parse(date);
	if (!isNaN(t)) {
		return new Date(Date.parse(date.replace(/-/g, "/")));
	} else {
		return new Date();
	}
};