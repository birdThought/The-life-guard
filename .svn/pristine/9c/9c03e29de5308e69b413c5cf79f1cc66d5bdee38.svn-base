$(function(){
	_validate();
});

function _validate(){
	$("[name='userRecord']").validate({
		errorPlacement: function(error, element){
			error.appendTo(element.parent());
		},
		submitHandler: function(){
			var _height = $.trim($("[name='height']").val());
			var _weight = $.trim($("[name='weight']").val());
			var _waist = $.trim($("[name='waist']").val());
			var _bust = $.trim($("[name='bust']").val());
			var _hip = $.trim($("[name='hip']").val());
			if(_height==''||_weight==''||_waist==''||_bust==''||_hip==''){
				layer.msg("请填写数据，以便获取更准确的测试结果");
				return;
			}
			_weight=parseFloat(_weight).toFixed(2);
			if(_height>998){
				layer.msg("请输入正常人的身高");
				$("[name='height']").focus();
				return;
			}
			if(_weight>998){
				layer.msg("请输入正常人的体重");
				$("[name='weight']").focus();
				return;
			}
			if(_hip>998){
				layer.msg("请输入正常人的臀围");
				$("[name='hip']").focus();
				return;
			}
			if(_waist>998){
				layer.msg("请输入正常人的腰围");
				$("[name='waist']").focus();
				return;
			}
			if(_bust>998){
				layer.msg("请输入正常人的胸围");
				$("[name='bust']").focus();
				return;
			}
			
			var _data = 'height_s=' + _height + '&weight_s=' + _weight + '&waist_s=' + _waist + '&bust_s=' + _bust + '&hip_s=' + _hip;
			$.ajax({
    			async : true,
    			cache : false,
    			type : 'POST',
    			url: 'memberControl.do?updateUserRecord',
    			data: _data,
    			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
    			dataType: 'json',
				beforeSend:function(){
					layer.load();
				},
				complete:function(){
					layer.closeAll("loading");
				},
    			success: function(result){
    				layer.msg(result.msg);
    				setTimeout(function(){
    					location.href = "memberControl.do?showUserRecord";	// 刷新页面
    				}, 1000);	// 缓冲1秒
    			}
    		});
		},
		rules:{
			height:{
				digits:true,required:true
			},
			weight:{
				isFloatGtZero:true,required:true
			},
			waist:{
				isFloatGtZero:true,required:true
			},
			bust:{
				isFloatGtZero:true,required:true
			},
			hip:{
				isFloatGtZero:true,required:true
			}
		},
		messages:{
			height:{
				digits:"请填写整数",
				required:"请填写身高"
			},
			weight:{
				isFloatGtZero:"请填写正确的数值",
				required:"请填写体重"
			},
			waist:{
				isFloatGtZero:"请填写正确的数值",
				required:"请填写腰围"
			},
			bust:{
				isFloatGtZero:"请填写正确的数值",
				required:"请填写胸围"
			},
			hip:{
				isFloatGtZero:"请填写正确的数值",
				required:"请填写臀围"
			}
		}
	});
}