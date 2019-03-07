$(function(){
	$('#memberRegister').validate({
		rules:{
			userName:{
				required: true,
        		minlength: 6,
				maxlength: 16,
				checkUserName: true,
        		remote:{              //验证用户名是否存在
                    type:"POST",
                    url:"releaseControl.do?userIsExist",
                    data:{
                    	val:function(){
                    		return $("#userName").val();
                    	}
                    } 
               }
              
			},
			pwd:{
				required: true,
                minlength: 6,
                maxlength: 16,
                checkPassword: true
			},
			confirmPwd:{
				equalTo : "#pwd"
			},
			agreement:{
				required: true
			}
		},
		messages:{
			userName:{
				required:"请填写用户名",
				remote:"该用户名已经被注册"
			},
			pwd:{
				required:"请填写密码",
				rangelength:"密码应该在6-16位"
			},
			confirmPwd:{
				equalTo:"输入的密码的不一致"
			},
			agreement:{
				required: "这是必选项"
			}
		},

		errorPlacement: function(error, element) {
			var elementType = element.attr("type");
			if("checkbox" == elementType){
				console.log("1");
				var $span = $("<span />").append(error);
				$span.css("color","red");
				$span.appendTo(element.parent().parent().parent());
			}else{
				console.log("2");
				var $span = $("<span />").append(error);
				$span.css("color","red").css("margin-left","27px");
				$span.appendTo(element.parent());
			}
		},
		invalidHandler : function(){
			return false;
		},
		submitHandler: function(form){
			var userName = $("input[name='userName']").val();
			var pwd = $("input[name='pwd']").val();
			var confirmPwd = $("input[name='confirmPwd']").val();
			
			userName=encodeStr(userName);
			pwd=encodeStr(pwd);
			var _data = 'userName=' + userName + '&pwd=' + pwd + '&confirmPwd=' + pwd;
			$.ajax({
	    		async : true,
	    		cache : false,
	    		type: 'POST',
	    		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
	    		url: 'familyControl.do?insertNewMember',
	    		data: _data,
	    		success: function(result){
	    			layer.msg(result.msg);
	    			if(result.success){   				
	    				var url = "familyControl.do?insertNewMemberInfoPage&userName=" + userName;
	    				window.location.href = url;
	    			}
	    			
	    		}
	    	});
		},
	});
	
	$('#next').click(function(){
		$('#memberRegister').submit();
	});
});
