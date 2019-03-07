


/**
 * 设备号码
 */
var changeNumber = {}

/**
 * 获取设备号码数据
 * @param terminalType 设备类型
 */
changeNumber.getData = function(terminalType){
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'GET',
		url : "terminalWebControl.do?getTerminalMobile&terminalType=" + terminalType,
		beforeSend : function() {

		},
		success : function(result) {
			if (result.success) {
				jQuery("input[name='terminalImei']").val(result.attributes.terminalImei);
				jQuery("input[name='terminalMobile']").val(result.attributes.terminalMobile);
			}
		},
		complete : function() {
			// 方法执行完毕，效果自己可以关闭，或者隐藏效果
		}
	});
	
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'GET',
		url : "terminalWebControl.do?getTerminalSosMobile&terminalType=" + terminalType,
		beforeSend : function() {

		},
		success : function(result) {
			if (result.success) {
				jQuery("input[name='SOSMobile']").val(result.attributes.sosMobile);
			}
		},
		complete : function() {
			// 方法执行完毕，效果自己可以关闭，或者隐藏效果
		}
	});
}

changeNumber.eventBind = function(terminalType){
	// 点击事件
	jQuery("#changeNumber").on('click', '.terminalMobile', function(){
		
		var $input = jQuery("ul.changeNumber_form input[name='changeNumber_number']");
		var $li = $input.parent("li");
		$input.val("");
		$li.siblings("li.li_error").remove();
		
		layer.open({
			type: 1,
			content: jQuery("ul.changeNumber_form"),
			title: ['手机号码修改','text-align:center;font-size:16px'],
			btn: ['确定','取消'],
			closeBtn: 1,
			moveType: 1,
			area: '320px',
			yes: function(){
				$li.siblings("li.li_error").remove();
				var mobile = jQuery("input[name='changeNumber_number']").val();
				var $li_new = jQuery("<li class='li_error'></li>");
				if(mobile == ""){
					$li_new.text("请输入手机号码");
					$li.before($li_new);
					return ;
				}
				var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
				if(!regex.test(mobile)){
					$li_new.text("手机号码格式错误");
					$li.before($li_new);
					return ;
				}
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					url: 'terminalWebControl.do?modifyTerminalNumber',
					data: 'mobile=' + mobile + '&terminalType=' + terminalType,
					beforeSend: function(){
						setTimeout(function(){
							layer.closeAll("page");
						}, 800);
					},
					success: function(result){
						layer.msg(result.msg);
					},
					complete: function(){
						var $this = jQuery("div.container>ul>li.action");
						setTimeout(function(){
							_clickNav($this);
						}, 1000);
					}
				});
			}
		});
	});
	//SOS号码设置
	jQuery("#changeNumber").on('click', '.SOSMobile', function(){
		
		var $input = jQuery("ul.changeNumber_form input[name='changeNumber_number']");
		var $li = $input.parent("li");
		$input.val("");
		$li.siblings("li.li_error").remove();
		
		layer.open({
			type: 1,
			content: jQuery("ul.changeNumber_form"),
			title: ['手机号码修改','text-align:center;font-size:16px'],
			btn: ['确定','取消'],
			closeBtn: 1,
			moveType: 1,
			area: '320px',
			yes: function(){
				$li.siblings("li.li_error").remove();
				var mobile = jQuery("input[name='changeNumber_number']").val();
				var $li_new = jQuery("<li class='li_error'></li>");
				if(mobile == ""){
					$li_new.text("请输入手机号码");
					$li.before($li_new);
					return ;
				}
				var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
				if(!regex.test(mobile)){
					$li_new.text("手机号码格式错误");
					$li.before($li_new);
					return ;
				}
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'GET',
					url: 'terminalWebControl.do?modifyTerminalSosMobile',
					data: 'contactNumber=' + mobile + '&terminalType=' + terminalType,
					beforeSend: function(){
						setTimeout(function(){
							layer.closeAll("page");
						}, 800);
					},
					success: function(result){
						layer.msg(result.msg);
					},
					complete: function(){
						var $this = jQuery("div.container>ul>li.action");
						setTimeout(function(){
							_clickNav($this);
						}, 1000);
					}
				});
			}
		});
	});
}
