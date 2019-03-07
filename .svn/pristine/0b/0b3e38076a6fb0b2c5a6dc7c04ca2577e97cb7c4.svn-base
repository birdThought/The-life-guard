
/**
 * 亲情号码
 */
var familyList = {}

familyList.getAllContact = function(){
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url : "terminalWebControl.do?getAllContact",
		beforeSend : function() {

		},
		complete : function() {
			// 方法执行完毕，效果自己可以关闭，或者隐藏效果
		},
		success : function(result) {
			if (result.success) {
				console.log();
				$('#allContact').empty();
				for (var i = 0; i < result.obj.length; i++) {
					$('#allContact').append("<option id = "+ result.obj[i].id +" name = "+ result.obj[i].name +
							" contactNumber = "+ result.obj[i].contactNumber +"><span>"+ result.obj[i].name +
							"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+ result.obj[i].contactNumber +"	</span></option>");
				}
				
			}
		}
	});
}

/**
 * 获取亲情号码数据
 * @param terminalType 设备类型 hl03、c3
 */
familyList.getData = function(terminalType){
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'POST',
		url : "terminalWebControl.do?getFamilyList",
		beforeSend : function() {

		},
		complete : function() {
			// 方法执行完毕，效果自己可以关闭，或者隐藏效果
		},
		success : function(result) {
			if (result.success) {
				$('#familyLists').find('tbody').empty();
				for (var i = 0; i < result.obj.length; i++) {
					$('#familyLists').append("<tr contactId = '"+ result.obj[i].id +"'><td>"+ result.obj[i].name +"</td><td>"+ result.obj[i].contactNumber +
							"</td><td class = 'delete'>删除</td></tr>")
				}
				
			}
		}
	});
}

/**
 * 事件绑定
 * @param terminalType 设备类型
 */
familyList.eventBind = function(terminalType){
	
	
	
	// 删除
		jQuery("#familyLists").on('click', '.delete', function(event){
		event.preventDefault();
		var contactId = $(this).parent().attr("contactId");
		var name = $(this).parent().find(0).text();
		var contactNumber = $(this).parent().find(2).text();
		var ele = $(this);
		var _data = '{"contactId":"'+ contactId +'","name":"'+ name +'","contactNumber":"'+ contactNumber +'"}';
		layer.confirm("确定要删除该亲情号码",
			function(){
				// 删除黑名单
				jQuery.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : "terminalWebControl.do?deleteFamily",
					contentType: 'application/json; charset=utf-8',
					data : _data,
					beforeSend : function() {
	
					},
					success : function(result) {
						layer.msg(result.msg);
						ele.parent().remove();
					},
					complete : function() {
					}
				});
			}
		);
		
		
	});
	// 添加
	jQuery("#familyList").on('click', 'div.familyList_add>span', function(){
		var $input = jQuery("ul.familyList_form input[name='familyList_number']");
		var $li = $input.parent("li");
		$input.val("");
		$li.siblings("li.li_error").remove();
		familyList.getAllContact();
		layer.open({
			type: 1,
			content: jQuery(".dialog_container"),
			title: ['添加亲情号码','text-align:center;font-size:16px'],
			btn: ['确定','取消'],
			closeBtn: 1,
			moveType: 1,
			area: '320px',
			yes: function(){
				$li.siblings("li.li_error").remove();
				var mobile = jQuery("input[name='blackList_number']").val();
				var $li_new = jQuery("<li class='li_error'></li>");
				var contactId = $("#allContact option:selected").attr('id');
				var name = $("#allContact option:selected").attr('name');
				var contactNumber = $("#allContact option:selected").attr('contactNumber');
				var isSOS = "false";
				var terminalType = "C3";
				var _data = '{"contactId":"'+ contactId +'","name":"'+ name +'","contactNumber":"'+ contactNumber +'","isSOS":"'+ isSOS +'","terminalType":"'+ terminalType +'"}';
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					url: 'terminalWebControl.do?addFamily',
					contentType: 'application/json; charset=utf-8',
					data: _data,
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