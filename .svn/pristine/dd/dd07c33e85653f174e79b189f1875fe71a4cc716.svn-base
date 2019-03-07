$(document).ready(function(){	 
	//切换用户
	jQuery("table.familyMem").on("click", "a.switch", function(event){
		event.preventDefault();
		var url = jQuery(this).attr("href");
		
		jQuery.ajax({
    		type: 'GET',
    		url: url,
    		success: function(result){
    			if(result.success){
//    				_setUserMessage();
    				setTimeout(function(){
    					location.href = 'familyControl.do?getFamilyMember';
    				}, 800);
    			}
    		}
		});
		
	});
	
	//添加成员页面
	jQuery('#searchForm').submit(function(event) {
		event.preventDefault();
		
		var registedUserName = $('#searchCondition').val();
		var _data = 'registedUserName=' + registedUserName;
		
		if(registedUserName == "") {
			layer.msg("请输入用户名再进行查询");
			return false;
		}
		
		$.ajax({
    		async : true,
    		cache : false,
    		type: 'POST',
    		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
    		url: 'familyControl.do?findRegistedUser',
    		data: _data,
    		success: function(result){
    			
    			if(result.success){
    				$("tbody").empty();
    				var user = result.attributes.userData;
    				
    				var tr = "<tr>" +
    					"<td>" + user.realName + "</td>" +
    					"<td>" + user.age + "</td>" +
    					"<td>" + user.mobile + "</td>" +
    					"<td>";
    				if(user.ownerMember) {
    					tr += "<a href='javascript:void(0);'><span id='" + user.userName + "'>已添加</span></a></td>" + "</tr>";
    				} else {
    					tr += "<a href='javascript:void(0);'><span id='" + user.userName + "'>添加</span></a></td>";
    				}
    				
    				$('tbody').append(tr);
    				
    				if(!user.ownerMember) {
    					jQuery("#"+user.userName).click(function() {
        					var userName = $(this).attr("id");
    						$('#userName').html(userName);
    						
    						layer.open({
    							type: 1,
    							title:['确认窗口', 'font-size:18px;text-align:center'],
    							content: $('.layUp'),
    							area:'380px',
    							moveType: 1,
    							btn:['确定','取消'],
    							closeBtn : 0,
    							yes:function(index){
    								// 可能不是点击确认提交的请求
    								addNewMember();
    					  		}
    						});
        				});
    				}
    			} else {
    				layer.msg(result.msg);
    			}
    		}
		});
	});
	
	jQuery("#addForm").submit(function(event) {
		event.preventDefault(event);
		addNewMember();
	});

	jQuery("form.memberSet").on("change", "#path", function(){
		var source = jQuery(this)[0];
		var userId = jQuery("#id").val();
		if(userId != ""){
			photoUpload.previewAndUpload(source.files[0],"memberControl.do?uploadPhoto", "memberControl.do?modifyPhoto", jQuery("#path"), "upload_img", userId);
		}
	});
	
//	$('#sure').click(function(){
//		$('#member').submit();
//	});
	$("[name='member']").validate({
		errorPlacement: function(error, element){
			error.appendTo(element.parent());
		},
		submitHandler: function(){
			var realName = jQuery("[name='userName']").val();
			if(realName.length>18){
				layer.msg("姓名过长，请重新输入");
				return;
			}
			var mobile = jQuery("[name='mobile']").val();
			var birthday = jQuery("#testDate").val();
			var sex = parseInt(customRadio.getResult("sex"));
			if(mobile == "") {
				layer.msg("请填写手机号码");
				jQuery("[name='mobile']").focus();
				return ;
			}
			var regex_mobile = /^[1][3,4,5,7,8][0-9]{9}$/;
			if(!regex_mobile.test(mobile)) {
				layer.msg("请填写正确的手机号码");
				jQuery("[name='mobile']").focus();
				return ;
			}
			if(birthday == "") {
				layer.msg("请填写出生日期");
				jQuery("#testDate").focus();
				return ;
			}
			
			var height = $.trim($("[name='height']").val());
			var weight = parseFloat($.trim($("[name='weight']").val())).toFixed(2);
			var waist = $.trim($("[name='waist']").val());
			var bust = $.trim($("[name='bust']").val());
			var hip = $.trim($("[name='hip']").val());
			var id = jQuery("#id").val();
			
			if(height==''||weight==''||waist==''||bust==''||hip==''){
				layer.msg("请填写数据，以便获取更准确的测试结果");
				return;
			}
			if(height>998){
				layer.msg("请输入正常人的身高");
				$("[name='height']").focus();
				return;
			}
			if(weight>998){
				layer.msg("请输入正常人的体重");
				$("[name='weight']").focus();
				return;
			}
			if(hip>998){
				layer.msg("请输入正常人的臀围");
				$("[name='hip']").focus();
				return;
			}
			if(waist>998){
				layer.msg("请输入正常人的腰围");
				$("[name='waist']").focus();
				return;
			}
			if(bust>998){
				layer.msg("请输入正常人的胸围");
				$("[name='bust']").focus();
				return;
			}
			var data = {
				id: id,
				mobile: mobile,
				birthday: birthday,
				sex: sex,
				height: height,
				weight: weight
			}
			
			if(realName != "") {
				data.realName = realName;
			}
			if(hip != "") {
				data.hip = hip;
			}
			if(waist != "") {
				data.waist = waist;
			}
			if(bust != "") {
				data.bust = bust;
			}
			
			jQuery.ajax({
				type: 'POST',
				data: JSON.stringify(data),
				contentType: 'application/json; charset=utf-8',
				url: 'familyControl.do?insertNewMemberInfo',
				beforeSend: function() {
					layer.load(2);
				},
				success: function(result) {
					if(result.success) {
						window.location.href = 'familyControl.do?getFamilyMember';
					} else {
						layer.msg(result.msg);
					}
				},
				complete: function() {
					layer.closeAll("loading");
				}
			});
			
		},
		rules:{
			height:{
				digits:true
			},
			weight:{
				isFloatGtZero:true
			},
			waist:{
				isFloatGtZero:true
			},
			bust:{
				isFloatGtZero:true
			},
			hip:{
				isFloatGtZero:true
			}
		},
		messages:{
			height:{
				digits:"请填写整数"
			},
			weight:{
				isFloatGtZero:"请填写正确的数值"
			},
			waist:{
				isFloatGtZero:"请填写正确的数值"
			},
			bust:{
				isFloatGtZero:"请填写正确的数值"
			},
			hip:{
				isFloatGtZero:"请填写正确的数值"
			}
		}
	});
	
});

function addNewMember() {
	//获取参数
	var userName = $('#userName').text();
	var password = $('#password').val();
	
	userName=encodeStr(userName);
	password=encodeStr(password);
	
	var _data = 'userName=' + userName + '&password=' + password;
	//提交数据
	$.ajax({
		type: 'POST',
		url: 'familyControl.do?insertRegistedMember',
		data: _data,
		success: function(result){
			layer.msg(result.msg);
			if(result.success){
				setTimeout(function(){
					window.location.href = "familyControl.do?getFamilyMember";
				},800);
			}
		},
		complete: function() {
			
		}
	});
}

//服务协议弹窗
function clickHere() {
	layer.open({
		type: 2,
		title: false,
		shadeClose: true,
		moveType: 1,
		area: ["1000px", "800px"],
		content:"/releaseControl.do?showAgreement"
	});
}