function bindDevice(type){
	var $imei = $('#imei').val();
	var $mobile = $('#mobile').val();
	var $sosPhone = $('#sosPhone').val(); 
	
	var _data = "imei=" + $imei + "&mobile=" + $mobile + "&sosPhone=" + $sosPhone + "&terminalType=" + type;
	$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: 'memberControl.do?bindDevice',
			data: _data,
			dataType: 'json',
			success: function(result){				
				if(result.success){					
					if(type=="HL-031"){											
						insertContent($('#hl_mobile'),$('#hl_imei'),result);
					}else{
						insertContent($('#c3_mobile'),$('#c3_imei'),result);
					}	
				}
				layer.msg(result.msg);				
			}
		});
}

function insertContent($mobile,$imei,result){
	$mobile.text(result.attributes.mobile);
	$imei.val(result.attributes.imei);
}
