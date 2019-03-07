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
				//val为获取到的时间值
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
					$("#introduce_div").show(300);
				}
			},
			onUnSelect : function(result) {
				if (result == 'f') {
					$("#introduce_div").hide(300);
				}
			}
		});
		jQuery("input.back").click(function() {
			location.href = 'orgUserControl.do?employManage';
		});
	});
});

var addEmploy = {
	isManager : false,
	isUpdate : true,
	url : 'orgUserControl.do?editEmployPage',
	headFileName : null,
	uploadFile : function() {
		photoUtil({
			targetId : 'headFile',
			displayId : 'avater',
			paramName : 'path',
			url : 'commonControl/uploadFile/img.do',
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
		var id = jQuery("[name='userId']").val();
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
		var birthday = $("#birth").val();
		if (birthday == '') {
			this.createErrorLabel("请选择出生日期", "birth");
			isReturn = true;
		}
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
		var addr = $("#addr").val();
		if (addr != '' && addr.length > 80) {
			this.createErrorLabel("家庭地址不能超出80个字符", "addr");
			isReturn = true;
		}

		if (isReturn)
			return;
		//birthday = parserDate(birthday);
		var json = {
			id : id,
			realName : realName,
			sex : sex,
			birthday : birthday,
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
		if (this.headFileName != null)
			json.photo = this.headFileName;
		if (eMail != '') {
			json.email = eMail;
		}
		if (workPhone != '') {
			json.tel = workPhone;
		}
		if (addr != '') {
			json.address = addr;
		}
		layer.load(1, {
			shade : [ 0.5, '#393D49' ]
		});
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : addEmploy.url,
			data : JSON.stringify(json),
			success : function(result) {
				layer.closeAll("loading");
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
				layer.msg("修改成功！");
			}
		});
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
			imageFieldName : 'uploadFile',
			imageActionName : 'uploadimage',
			initialFrameHeight : 200
		});
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
			if (action == 'uploadimage') {
				return 'orgControl.do?uploadPhoto';
			} else {
				return this._bkGetActionUrl.call(this, action);
			}
		}
	},
	getContent : function() {
		return this.uEditor.getContent();
	},
	setInitContent : function(html) {
		this.uEditor.addListener("ready", function() {
			Editor.setContent(html);
		});
	},
	setContent : function(html) {
		this.uEditor.setContent(html);
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