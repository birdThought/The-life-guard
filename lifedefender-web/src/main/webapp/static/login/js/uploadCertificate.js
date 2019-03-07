var orgImprove = {
	orgId : null,
	licenceFileName:null,
	uploadFile:function(){
		photoUtil({
			targetId:'licenceFile',
			displayId:'card',
			paramName : 'path',
			url : 'commonControl/uploadFile/img.do',
			limit : 3072,
			uploadFail:function(){
				orgImprove.licenceFileName=null;
			},
			uploadSuccess:function(data){
				orgImprove.licenceFileName=data.obj;	
			}
		});
	},
	initForm : function(id) {
		console.log('进入')
		this.orgId=id;

		$("#item-register").validate({
			errorPlacement : function(error, element) {
				var elementType = element.attr("type");
				if ("checkbox" == elementType) {
					$("#_checkbox").append(error);
				} else {
					var div = $("<div />").append(error);
					div.appendTo(element.parent());
					div.css("color", "red");
				}
			},
			submitHandler : function(form) {
				if (orgImprove.licenceFileName == null) {
					layer.msg("请上传营业执照");
					return;
				}
				var street = $("#street").val();
				var realName = $("#realName").val();
				var mobile = $("#mobile").val();

				var json = {
					id : orgImprove.orgId,
					street : street,
					contacts : realName,
					contactInformation : mobile,
					businessLicense : orgImprove.licenceFileName
				}
				layer.load();
				$.ajax({
					type : 'POST',
					contentType : 'application/json; charset=utf-8',
					url : 'orgControl.do?improveOrgMsg',
					data : JSON.stringify(json),
					success : function(result) {
						layer.closeAll();
						var b = result.success;
						if (b){
							layer.msg("完善成功！");
							setTimeout(function() {
								window.location.href = "/login";
							}, 1000)
						}
					},
					complete : function() {
						layer.closeAll("loading");
					}
				});
			},
			rules : {
				street : {
					required : true,
					maxlength : 80
				},
				realName : {
					required : true,
					maxlength : 10
				},
				mobile : {
					required : true,
					isTel : true
				}
			},
			messages : {
				street : {
					required : "请填写公司地址",
					maxlength : "地址不能超过80个字"
				},
				realName : {
					required : "请填写法定代表人",
					maxlength : "名字不能超过10个字"
				},
				mobile : {
					required : "请输入正确的联系号码",
					isTel : "请输入正确的联系号码"
				}
			}
		});
	}
}
