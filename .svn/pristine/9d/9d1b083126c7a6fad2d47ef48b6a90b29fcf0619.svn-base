
/**
 * 黑名单
 */
var blackList = {}

/**
 * 获取黑名单数据
 * @param terminalType 设备类型 hl03、c3
 */
blackList.getData = function(terminalType){
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'GET',
		url : "terminalWebControl.do?getBlackLists&terminalType=" + terminalType,
		beforeSend : function() {

		},
		complete : function() {
			// 方法执行完毕，效果自己可以关闭，或者隐藏效果
		},
		success : function(result) {
			if (result.success) {
				jQuery("#blackLists tbody").empty();
				var balckLists=result.attributes.balckLists;
				jQuery.each(balckLists, function(i, item){
					jQuery("#blackLists tbody").append('<tr><td>'+item.limited+'</td><td><a class="blackList_delete">删除</a><input type="hidden" name="blackList_id" value="' + item.id + '"></td></tr>');
				}); 
			}
		}
	});
}

/**
 * 事件绑定
 * @param terminalType 设备类型
 */
blackList.eventBind = function(terminalType){
	
	
	
	// 删除
	jQuery("#blackList").on('click', 'a.blackList_delete', function(event){
		event.preventDefault();
		var id = jQuery(this).next("input[name='blackList_id']").val();
		var bool = false;
		var $tr = jQuery(this).parents("tr");
		
		layer.confirm("确定要删除该黑名单",
			function(){
				// 删除黑名单
				jQuery.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : "terminalWebControl.do?deleteBlackList",
					data : "blackListId="+id+"&terminalType="+terminalType,
					beforeSend : function() {
	
					},
					success : function(result) {
						layer.msg(result.msg);
						bool = result.success;
					},
					complete : function() {
						if(bool){
							$tr.remove();
						}
					}
				});
			}
		);
		
		
	});
	// 添加
	jQuery("#blackList").on('click', 'div.blackList_add>span', function(){
		var $input = jQuery("ul.blackList_form input[name='blackList_number']");
		var $li = $input.parent("li");
		$input.val("");
		$li.siblings("li.li_error").remove();
		
		layer.open({
			type: 1,
			content: jQuery("ul.blackList_form"),
			title: ['添加黑名单','text-align:center;font-size:16px'],
			btn: ['确定','取消'],
			closeBtn: 1,
			moveType: 1,
			area: '320px',
			yes: function(){
				$li.siblings("li.li_error").remove();
				var mobile = jQuery("input[name='blackList_number']").val();
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
					url: 'terminalWebControl.do?addBlackList',
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
}