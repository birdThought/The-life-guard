$(function() {
	
	/* 初始化数据 */
	$(".tab_content").hide(); // 隐藏所有内容
	$("ul.tabs li:first").addClass("active").show(); // 第一个显示
	$("ul.tabs li:first").children('a').css('color', '#48c858');
	$(".tab_content:first").show(); // 第一个内容显示
	
	// 图片打勾
	$("div.tab_content img.check").each(function(){
		var cancel = $(this).nextAll("input[name='status']").val() == 0 ? true : false;
		changeImgShow($(this), cancel);
	});
	
	// 点击事件
	$("ul.tabs li").click(
		function() {	
			$(this).children('a').css('color', '#48c858').parent()
					.siblings().children('a').css('color', '');
			$("ul.tabs li").removeClass("active"); // 移除所有样式
			$(this).addClass("active"); // 当前元素加入.active样式
			$(".tab_content").hide(); // 隐藏所有内容
			var activeTab = $(this).find("a").attr("href"); // 给导航与内容建立联系
			$(activeTab).fadeIn(); // 出来的方式
			return false;
	});
	$('input').iCheck({
		radioClass : 'iradio_minimal-green'
	});
	
	/** 参数设置确定(数据提交)按钮事件 */
	_validate();
	
	/** 设备添加事件 */
	$("div.tab_content").on('click', 'p.clickBtn', function(){
		var $this = jQuery(this);
		var $status = $this.nextAll("input[name='status']");
		var typeName = $this.nextAll("input[name='nameEn']").val();
		var cancel = false;
		var message = "";
		if($status.val() == 0){
			message = "确认选择此设备?";
		}else{
			cancel = true;
			message = "确认解绑该健康包?";
		}
		layer.confirm(message, function(index){
			// 发送数据请求
			$.ajax({
				type: 'POST',
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				url: 'healthDeviceManagerControl.do?modifyHealthPackageDevices',
				data: 'typeName=' + typeName + "&cancel=" + cancel,
				success: function(result){
					// 默认刷新页面
					// var _href = 'healthDeviceManagerControl.do?showHealthPackageDevices';
					layer.msg(result.msg);
					if(result.success){
						changeImgShow($this.prevAll("img.check"), cancel);
						var _status = $status.val() == 0 ? 1 : 0;
						$status.val(_status);
					}/*else{
						if(result.obj == "uncomplete"){
							// 绑定的设备需要设置完整的健康参数，将跳转的页面修改为个人档案页面
							_href = 'memberControl.do?showUserRecord';
						}
					}
					setTimeout(function(){
						location.href = _href;
					}, 1000);*/
				}
			});
		});
	});
});

changeImgShow = function($imgShow, status){
	if (status){
		$imgShow.hide();
		$imgShow.parent().find("p:eq(1)").text("选择").addClass("cancelBtn");
	} else {
		$imgShow.show();
		$imgShow.parent().find("p:eq(1)").text("取消").removeClass("cancelBtn");
	}
		
};

function _validate(){
	$("[name='form_params']").validate({
		errorPlacement: function(error, element){
			error.appendTo(element.parent());
		},
		rules:{
			age:{
				required:true,
				digits:true,
				max: 199,
				min: 0
			},
			height:{
				digits:true
			},
			weight:{
				digits:true
			},
			waist:{
				digits:true
			},
			bust:{
				digits:true
			},
			hip:{
				digits:true
			}
		},
		messages:{
			age:{
				digits:"请填写整数"
			},
			height:{
				digits:"请填写整数"
			},
			weight:{
				digits:"请填写整数"
			},
			waist:{
				digits:"请填写整数"
			},
			bust:{
				digits:"请填写整数"
			},
			hip:{
				digits:"请填写整数"
			}
		}
	});
	
	$("button[name='submit']").click(function(event){
		event.preventDefault();	// 防止数据错误提交
		if($("[name='form_params']").valid()){
			var sex = $("[name='rd_1']:checked").val();
			var age = $("[name='age']").val();
			var height = $("[name='height']").val();
			var weight = $("[name='weight']").val();
			var waist = $("[name='waist']").val();
			var hip = $("[name='hip']").val();
			var bust = $("[name='bust']").val();
			var _data = '{"sex":' + sex + ',"age":' + age + ',"height":' + height
				+ ',"weight":' + weight + ',"waist":' + waist + ',"hip":'
				+ hip + ',"bust":' + bust + '}';
			console.log(_data);
			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url: 'healthDeviceManagerControl.do?setHealthPackageParams',
				data: _data,
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				beforeSend:function(){
					layer.load(1, {
						  shade: [0.1,'#fff'] //0.1透明度的白色背景
					});
				},
				complete:function(){
					layer.closeAll('loading');
				},
				success: function(result) {
					layer.msg(result.msg);
				}
			});
		}
	});
	
}