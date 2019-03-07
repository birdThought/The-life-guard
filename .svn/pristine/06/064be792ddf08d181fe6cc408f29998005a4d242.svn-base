function unBindDevice(terminalType){
	var $imei = "";
	if(terminalType == "HL-031"){
		$imei = $('#hl_imei').val();
	}else{
		$imei = $('#c3_imei').val();
	}
	var _data = "imei=" + $imei + "&terminalType=" + terminalType;
	$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: 'memberControl.do?unBindDevice',
			data: _data,
			dataType: 'json',
			success: function(result){
				layer.msg(result.msg);
				if(result.success){
					if(result.success){
						if(terminalType=="HL-031"){
							$('#hl_mobile').text("未绑定");														
						}else{
							$('#c3_mobile').text("未绑定");
						}
					}			
				}			
			}
		});
}
