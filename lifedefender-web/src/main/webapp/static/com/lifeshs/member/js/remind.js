
/**
 * 调用方法如下：
 * remind.gerData("c3");
 * remind.eventBind("c3"); 
 */

/**
 * 提醒设置
 */
var remind = {}

/**
 * 添加提醒事件绑定
 * @param terminalType 设备类型 设备类型
 */

remind.eventBind = function(terminalType){
	
	function pageRedirect() {
		old_divId = "remind";
		
		remind.getData(terminalType);
		$(".addData").hide();
		$("#remind").removeClass("nav_none");
		var $contents = $("div.container>div.content>div[class$=content]");
		$contents.each(function() {
			var contentId = $(this).attr("id");
			if (contentId != "remind") {
				$(this).addClass("nav_none");
			}
		});
//		if (old_divId != divId) {
//			$("#" + old_divId).addClass("nav_none");
//			old_divId = divId;
//			$("#" + divId).removeClass("nav_none");
//			/** 如果点击的是设置按钮或者心率手环，需要把.addData隐藏 */
//			if ((divId == 'remind') || (divId == 'xl')) {
//				
//			} else {
//				$(".addData").show();
//			}
//		}
		$("#remind").addClass("action");
		$("#remind").siblings("li").removeClass("action");
	}
	
	// 添加提醒
	jQuery("#remind").on("click", ".addNotice", function(){
		var $root = jQuery(".setNotice");
		var $time = $root.find("input[name='time']");
		var $content = $root.find("input[name='content']");
		var $checkbox = $root.find("input[type='checkbox']");
		var $sliderInput = $root.find(".slider-input");
		
		/** 打开模态框 */
		layer.open({
			type: 1,
			content: jQuery('.setNotice'), //这里content是一个DOM
			title:['添加提醒','text-align:center;font-size:16px'],
			btn:['确定','取消'],
			closeBtn:1,	// 显示最上层的关闭按钮
			scrollbar: false,
			moveType: 1,
			scrolling: "no",
			area:'470px',
			success: function() {
				/** 清除数据 */
				$time.val("00:00");
				$content.val("");
//				$sliderInput.val("0");
				jRangeSilder.jRange("setValue", 0);
				$checkbox.iCheck("uncheck");
			},
			yes: function(index){
				// 组织参数
				var _weeks = '';
				var _checkbox = $(".setNotice input[type='checkbox']:checked");
				var bool = false;
				for(var i=1; i<= 7; i++){
					if($(".setNotice input[type='checkbox'][value='" + i + "']").is(":checked")){
						_weeks += '1';
					}else{
						_weeks += '0';
					}
				}
				var _time = $(".setNotice [name='time']").val();
				if(_time == '' || _time == null){
					layer.msg("请设置提醒时间");
					return ;
				}
				var _content = $(".setNotice [name='content']").val();
				if(_content.length > 10){
					layer.msg("提醒内容长度不能超过10");
					return ;
				}
				var _data = 'weeks=' + _weeks + '&time=' + _time
					+ '&content=' + $(".setNotice [name='content']").val()
					+ '&intervalM=' + parseFloat(jQuery('.slider-input').val())
					+ "&terminalType=" + terminalType;
				/** 添加提醒数据提交 */
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					url: 'terminalWebControl.do?addNotice',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',
					data: _data,
					beforeSend: function(){
						
					},
					success: function(result){
						layer.msg(result.msg);
						bool = result.success;
					},
					complete: function(){
						if(bool){
							var $this = jQuery("div.container>ul>li.action");
							setTimeout(function(){
								layer.close(index);
								pageRedirect();
							}, 1000);
						}
					}
				});
			}
		});
	});
	
	// checkbox选中状态切换事件(勾选->取消勾选)
	jQuery("#notice").on("ifClicked", "input[name='notice_check']", function(event){
		var id = jQuery(this).parent().next("input[name='id']").val();
		var bool = false;
		layer.msg("id="+id);
		jQuery.ajax({
			async: true,
			cache: false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: 'terminalWebControl.do?toggleCheckNotice',
			data: 'noticeId='+id+"&terminalType="+terminalType,
			beforeSend: function(){
				
			},
			success: function(result){
				layer.msg(result.msg);
				bool = result.success;
			},
			complete: function(){
				// 如果操作失败
				if(!bool){
					jQuery(this).iCheck("toggle");
				}
			}
		});
	});
	
	// 删除提醒
	jQuery("#notice").on("click", "span.del_notice", function(){
		var $tr = jQuery(this).parents("tr");
		var id = $tr.find("td:eq(0)").children("input[name='id']").val();
		var bool = false;
		
		layer.confirm("确定删除该提醒？",
			function(){
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',
					url: "terminalWebControl.do?delNotice",
					data: "noticeId=" + id + "&terminalType=" + terminalType,
					beforeSend: function(){
						
					},
					success: function(result){
						bool = result.success;
						layer.msg(result.msg);
					},
					complete: function(){
						// 减少页面刷新次数，使用jq移除tr标签
						if(bool){
							setTimeout(function(){
								$tr.remove();
							}, 1000);
						}
					}
				});
			}
		);
	});
	
	// 设置提醒
	jQuery("#notice").on("click", "span.set_notice", function(){
		
		var $root = jQuery(".setNotice");
		var $time = $root.find("input[name='time']");
		var $content = $root.find("input[name='content']");
		var $checkbox = $root.find("input[type='checkbox']");
		var $sliderInput = $root.find("input.slider-input");
		
		/** 清除数据 */
		$time.val("");
		$content.val("");
		$checkbox.iCheck("uncheck");
		
		var id = jQuery(this).parents("tr").find("td:eq(0)").children("input[name='id']").val();
		
		/** 填充数据 */
		jQuery.ajax({
			async: true,
			cache: false,
			type: 'GET',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: "terminalWebControl.do?notice&noticeId="+id+"&terminalType="+terminalType,
			beforeSend: function(){
				
			},
			success: function(result){
				if(result.success){
					var obj = result.obj;
					var weeks = obj.weeks;
					$time.val(obj.time);
					$content.val(obj.content);
					jQuery.each($checkbox, function(i){
						if(String(weeks).charAt(i) == '1'){
							$(this).iCheck("check");
						}
					});
//					$sliderInput.val(obj.intervalM);
					jRangeSilder.jRange("setValue", obj.intervalM);
				} else {
					layer.msg(result.msg);
				}
			},
			complete: function(){
				
			}
		});
		/** 打开模态框 */
		layer.open({
			type: 1,
			content: $root, //这里content是一个DOM
			title:['添加提醒','text-align:center;font-size:16px'],
			btn:['确定','取消'],
			moveType: 1,
			closeBtn:1,	// 显示最上层的关闭按钮
			area:'470px',
			yes: function(index){
				// 组织参数
				var _weeks = '';
				var _checkbox = $(".setNotice input[type='checkbox']:checked");
				var bool = false;
				for(var i=1; i<= 7; i++){
					if($(".setNotice input[type='checkbox'][value='" + i + "']").is(":checked")){
						_weeks += '1';
					}else{
						_weeks += '0';
					}
				}
				var _time = $(".setNotice [name='time']").val();
				if(_time == '' || _time == null){
					layer.msg("请设置提醒时间");
					return ;
				}
				var _content = $(".setNotice [name='content']").val();
				if(_content.length > 10){
					layer.msg("提醒内容长度不能超过10");
					return ;
				}
				var _data = 'weeks=' + _weeks + '&time=' + _time
					+ '&content=' + $(".setNotice [name='content']").val()
					+ "&noticeId=" + id + "&intervalM=" + jQuery(".slider-input").val()
					+ "&terminalType=" + terminalType;
				/** 添加提醒数据提交 */
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					url: 'terminalWebControl.do?modifyNotice',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',
					data: _data,
					beforeSend: function(){
						
					},
					success: function(result){
						layer.msg(result.msg);
						bool = result.success;
					},
					complete: function(){
						if(bool){
							var $this = jQuery("div.container>ul>li.action");
							setTimeout(function(){
								layer.close(index);
								pageRedirect();
							}, 1000);
						}
					}
				});
			}
		});
	});
}

/**
 * 获取提醒设置数据，并将数据填充到页面上
 * @param terminalType 设备类型
 */
remind.getData = function(terminalType){
	// 创建提醒table
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'GET',
		url : "terminalWebControl.do?getNotices&terminalType=" + terminalType,
		dataType : 'json',
		beforeSend : function() {
			
		},
		success : function(result) {
			if (result.success) {
				jQuery("#notice tbody").empty();
				var list=result.attributes.notices;
				if(typeof(list) != "undefined"){
					jQuery.each(list, function(i, item){  
						if(item.status==1){
							jQuery("#notice tbody").append('<tr><td><input name="notice_check" type="checkbox" checked><input type="hidden" name="id" value="' + item.id + '" /></td><td>'+item.time
									+'</td><td>'+item.intervalM+'分钟</td><td>'+item.weeks+'</td><td>'+item.content+'</td><td><span class="set_notice">设置</span>&nbsp;&nbsp;<span class="del_notice">删除</span></td></tr>');  
						}else{
							jQuery("#notice tbody").append('<tr><td><input name="notice_check" type="checkbox" ><input type="hidden" name="id" value="' + item.id + '" /></td><td>'+item.time
									+'</td><td>'+item.intervalM+'分钟</td><td>'+item.weeks+'</td><td>111'+item.content+'</td><td><span class="set_notice">设置</span>&nbsp;&nbsp;<span class="del_notice">删除</span></td></tr>');
						}
					});
				}
			}
			ic("#notice tbody input");
		},
		complete : function() {
			
		}
	});
}

function ic(att){
	jQuery(att).iCheck({
		checkboxClass : 'icheckbox_minimal-green',
		radioClass : 'iradio_minimal-green',
		increaseArea : '20%' // optional
	});
}
