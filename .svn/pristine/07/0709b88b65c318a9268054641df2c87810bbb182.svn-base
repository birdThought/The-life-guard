/**
 * 绑定邮箱
 * <p>使用说明：自行使用函数
 */
var bindEmail = {}

/**
 * 第一次验证邮箱(不需要验证旧邮箱)
 * <p> 需要将流程修改为两步，并且修改表单提交的url
 * @param url 处理操作的url
 * @param $form form表单
 */
bindEmail.firstBind = function(url, $form){
	// 流程处理
	var $root = jQuery(".for-liucheng").eq(0);
	$root.css("width", "auto");
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.eq(1).addClass("for-cur");
	$liutext.eq(1).html("<em>1</em><br/><strong>新邮箱验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>完成</strong>");
	$liutext.eq(0).remove();
	// 表单处理
	$form.attr("action", url);
	var $btn = $form.find("input[type='submit']");
	var $btn_new = jQuery("<button class='tijiao'>提交</button>");
	$btn.after($btn_new);
	$btn.remove();
	// 提交数据
	jQuery($form).on("click", "button.tijiao", function(event){
		event.preventDefault();
		var idcode = $form.find("[name='idcode']").val();
		if(idcode==''){
			layer.msg("请填写邮箱验证码");
			return;
		}
		jQuery.ajax({
			async: true,
			cache: false,
			type: 'POST',
			url: 'memberControl.do?modifyEmailFirstTime',
			data: 'idcode='+idcode,
			beforeSend: function(){
				layer.load();
			},
			success: function(result){
				var obj = result.obj;
				if(result.success){
					setTimeout(function(){
						location.href="memberControl.do?modifyEmailSuccess&result="+obj;
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
 * 第一次验证邮箱--成功
 */
bindEmail.firstBindSuccess = function(){
	// 流程处理
	var $root = jQuery(".for-liucheng").eq(0);
	$root.css("width", "600px");
	$root.find(".liulist,.for-cur").css({"width":"50%"});
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.eq(1).html("<em>1</em><br/><strong>新邮箱验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>完成</strong>");
	$liutext.eq(0).remove();
	// 文本框
	jQuery("div.success ul li span").text("已完成验证");
}
/**
 * 第一次验证邮箱--失败
 * @param msg 错误提示信息
 */
bindEmail.firstBindFail = function(msg){
	var $root = jQuery(".for-liucheng").eq(0);
	$root.css("width", "600px");
	var $div = $root.find("div.liulist");
	jQuery.each($div, function(){
		if(jQuery(this).hasClass("for-cur")){
			jQuery(this).css({"background-color":"#F0AD4E","width":"50%"});
		}
	});
	var $div = $root.find(".liutext");
	jQuery.each($div, function(){
		if(jQuery(this).hasClass("for-cur")){
			jQuery(this).css("width", "50%");
		}
	});
	$root.children(".liulist").eq(2).remove();
	var $liutext = $root.children(".liutextbox").find(".liutext");
	$liutext.addClass("faild");
	$liutext.eq(1).html("<em>1</em><br/><strong>新邮箱验证</strong>");
	$liutext.eq(2).html("<em>2</em><br/><strong>失败</strong>");
	$liutext.eq(0).remove();
	// 文本框
	jQuery("div.success img").remove();
	jQuery("div.success ul li span").text(msg);
}