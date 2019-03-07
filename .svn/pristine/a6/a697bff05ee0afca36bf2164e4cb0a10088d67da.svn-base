define(function(require, exports, module) {
	require('QYcommon');
	require.async(['fileUpload','jedate','commonCheck']);
});

var areaUtils = {// 地区变化类
	provinceChange : function() {
		var provinceCode = $("#province option:selected").val();
		provinceCode = provinceCode.substr(0, 2);
		$.ajax({
			type : 'GET',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			url : 'commonControl.do?getCity',
			data : 'provinceCode=' + provinceCode,
			success : function(result) {
				areaUtils.setCityDatas(result);
			}
		});
	},
	cityChange : function() {
		var cityCode = $("#city option:selected").val();
		cityCode = cityCode.substr(0, 4);
		$.ajax({
			type : 'GET',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			url : 'commonControl.do?getDistrict',
			data : 'cityCode=' + cityCode,
			success : function(result) {
				areaUtils.setDistrictDatas(result);
			}
		});
	},
	setCityDatas : function(data) {
		var dis = data.obj;
		var cits = data.attributes['city'];
		if (cits == '') {
			$("#city").html(
					'<option selected="selected" value="'
							+ $("#province option:selected").val() + '">'
							+ $("#province option:selected").text()
							+ '</option>');
		} else {
			var str = "";
			for ( var key in cits) {
				if (key == 0) {
					str += '<option selected="selected" value="'
							+ cits[key].code + '">' + cits[key].name
							+ '</option>';
					continue;
				}
				str += '<option value="' + cits[key].code + '">'
						+ cits[key].name + '</option>';
			}
			$("#city").html(str);
		}
		this.setDistrictDatas(dis);
	},
	setDistrictDatas : function(data) {
		if (data == '') {
			$("#district").html(
					'<option selected="selected" value="'
							+ $("#city option:selected").val() + '">'
							+ $("#city option:selected").text() + '</option>');
		} else {
			var str = "";
			for ( var key in data) {
				if (key == 0) {
					str += '<option selected="selected" value="'
							+ data[key].code + '">' + data[key].name
							+ '</option>';
					continue;
				}
				str += '<option value="' + data[key].code + '">'
						+ data[key].name + '</option>';
			}
			$("#district").html(str);
		}
	}
}
var orgMsgControl = {
	url : 'orgControl.do?updateOrgDetails',
	logoFileName:null,
	licenceFileName:null,
	uploadFile:function(target){
		switch(target){
		case 0://logo的头像
			photoUtil({
				targetId:'logoFile',
				displayId:'logo',
				paramName : 'path',
				url : 'commonControl/uploadFile/img.do',
				limit : 3072,
				uploadFail:function(){
					orgMsgControl.logoFileName=null;
				},
				uploadSuccess:function(data){
					orgMsgControl.logoFileName=data.obj;	
				}
			});
			break;
		/*case 1://营业执照的头像
			photoUtil({
				targetId:'licenceFile',
				displayId:'card',
				paramName : 'uploadFile',
				url : 'orgControl.do?uploadPhoto',
				limit : 3072,
				uploadFail:function(){
					orgMsgControl.licenceFileName=null;
				},
				uploadSuccess:function(data){
					orgMsgControl.licenceFileName=data.obj;	
				}
			});
			break;*/
		}
	},
	createErrorLabel : function(text, objAfter) {
		var error = document.createElement("label");
		$(error).attr({
			"id" : "error",
			"style" : "margin-left:15px;color:#ff0000;"
		});
		$(error).text(text);
		$(error).insertAfter($("#" + objAfter));
	},
	submitForm : function() {
		$("body #error").each(function() {
			$(this).remove();
		});
		var isReturn = false;
		var json = {};
		if (this.logoFileName != null)
			json.logo = this.logoFileName;
		/*if (this.licenceFileName != null)
			json.businessLicense = this.licenceFileName;
		var name = $.trim($("#orgName").val());
		if (name == '') {
			this.createErrorLabel("机构名称不能为空！", "orgName");
			isReturn = true;
		}
		if (name.length > 20) {
			this.createErrorLabel("机构名称不能大于20个字！", "orgName");
			isReturn = true;
		}*/
		json.orgType = $("#orgXZ option:selected").val();
		var contact = $("#contact").val();
		if (contact != '' && contact.length > 10) {
			this.createErrorLabel("联系人姓名不能超过10个字", "contact");
			isReturn = true;
		}
		var phone = $("#phone").val();
		var district = $("#district option:selected").val();
		var p = district.substr(0, 2);
		var c = district.substr(2, 2);
		var d = district.substr(4, 2);
		var road = $("#road").val();
		var introduce = $("#org_introduce").val();
		if (introduce != '') {
			if (introduce.length > 300) {
				layer.msg("机构简介不能超出300个字");
				this.createErrorLabel("机构简介不能超出300个字", "org_introduce");
				isReturn = true;
			}
		}
		json.about = introduce;
		if (road != '') {
			if (road.length > 70) {
				layer.msg("街道地址不能超出70个字");
				this.createErrorLabel("街道地址不能超出70个字", "road");
				isReturn = true;
			}
		}
		json.street = road;
		if (isReturn)
			return;
//		json.orgName = name;
		if (contact != '')
			json.contacts = contact;
		if (phone != '') {
			if (!checkUtils.checkPhone(phone)) {
				layer.msg("请输入正确的手机号码");
				this.createErrorLabel("请输入正确的手机号码", "phone");
				return;
			}
			json.contactInformation = phone;
		}
		json.province = p;
		json.city = c;
		json.district = d;
		layer.load(1, {
			shade : [ 0.5, '#393D49' ]
		});
		
		var detail = FilterUtils.filterScript(Editor.getContent());
		if(detail != "") {
			json.detail = detail;
		}
		
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : this.url,
			data : JSON.stringify(json),
			success : function(result) {
				var b = result.success;
				if (b){
					layer.msg("更新成功！");
					setTimeout(function() {
						window.location.reload();
					}, 1000)
				}
			},
			complete : function() {
				layer.closeAll("loading");
			}
		});
	}
}

var Editor = {
	uEditor : null,
	init : function(data) {
		this.uEditor = UE.getEditor('org_details', {
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
			maximumWords : 1000,
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
	},
	setHTMLContent:function(content){
        this.uEditor.ready(function () {
            Editor.uEditor.setContent(content, false);
        });
    }
}

var FilterUtils = {
	filterScript : function(s) {
		return s.replace(/<script.*?>.*?<\/script>/ig, '');
	}
}
