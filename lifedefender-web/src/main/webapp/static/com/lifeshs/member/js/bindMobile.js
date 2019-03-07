/**
* 绑定手机
* <p>使用说明：自行使用函数
*/
var bindMobile = {}

/**
 * 第一次验证手机(不需要验证旧手机)
 * <p> 需要将流程修改为两步，并且修改表单提交的url
 * @param url 处理操作的url
 * @param $form form表单
 */
bindMobile.firstBind = function(url, $form){
	// 流程处理
	var $root = $(".for-liucheng").eq(0);
	$root.css("width", "auto");
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.eq(1).addClass("for-cur");
	$liutext.eq(1).html("<em>1</em><br/><strong>新手机验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>完成</strong>");
	$liutext.eq(0).remove();
	// 表单处理
	$form.attr("action", url);
	var $btn = $form.find("input[type='submit']");
	var $btn_new = $("<button class='tijiao'>提交</button>");
	$btn.after($btn_new);
	$btn.remove();
	// 提交数据
	$($form).on("click", "button.tijiao", function(event){
		event.preventDefault();
		var idcode = $form.find("[name='idcode']").val();
		if(idcode==''){
			layer.msg("请填写手机验证码");
			return;
		}
		$.ajax({
			async: true,
			cache: false,
			type: 'POST',
			url: 'memberControl.do?modifyMobileFirstTime',
			data: 'idcode='+idcode,
			beforeSend: function(){
				layer.load();
			},
			success: function(result){
				var obj = result.obj;
				if(result.success){
					setTimeout(function(){
						location.href="memberControl.do?modifyMobileSuccess&result="+obj;
					}, 800);
				}else{
					if(obj == 0){
						layer.msg(result.msg);
					}
				}
			},
			complete: function(){
				layer.closeAll("loading")
			}
		});
	});
}
/**
 * 第一次验证手机--成功
 */
bindMobile.firstBindSuccess = function(){
	// 流程处理
	var $root = $(".for-liucheng").eq(0);
	$root.css("width", "600px");
	$root.find(".liulist,.for-cur").css({"width":"50%"});
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.eq(1).html("<em>1</em><br/><strong>新邮箱验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>完成</strong>");
	$liutext.eq(0).remove();
	// 文本框
	$("div.success ul li span").text("已完成验证");
}
/**
 * 第一次验证手机--失败
 * @param msg 错误提示信息
 */
bindMobile.firstBindFail = function(msg){
	var $root = $(".for-liucheng").eq(0);
	$root.css("width", "600px");
	var $div = $root.find("div.liulist");
	$.each($div, function(){
		if($(this).hasClass("for-cur")){
			$(this).css({"background-color":"#F0AD4E","width":"50%"});
		}
	});
	var $div = $root.find(".liutext");
	$.each($div, function(){
		if($(this).hasClass("for-cur")){
			$(this).css("width", "50%");
		}
	});
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.addClass("faild");
	$liutext.eq(1).html("<em>1</em><br/><strong>新手机验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>失败</strong>");
	$liutext.eq(0).remove();
	// 文本框
	$("div.success img").remove();
	$("div.success ul li span").text(msg);
}