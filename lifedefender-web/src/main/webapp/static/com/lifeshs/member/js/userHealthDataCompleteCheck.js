var checkRecordData = {
	HEALTH_PACKAGE: 1,
	WEARABLE_DEVICE: 2,
	complete: function(pageType) {
		jQuery.ajax({
			type: 'GET',
			url: 'healthDeviceManagerControl.do?isUserHealthDataComplete&pageType='+pageType,
			success: function(result) {
				if(!result.success) {
					
					var href = result.attributes.href;
					
					layer.alert(result.msg, {skin:'layer-ext-seaning',icon: 11}, function() {
						window.location.href = href;
	            	});
				}
			}
		});
	}
}