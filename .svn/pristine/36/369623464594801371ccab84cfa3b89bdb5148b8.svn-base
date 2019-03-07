jQuery(function() {
	jQuery("div.item-service").on('click', 'button.goBuy', function() {
		var $ul = $(this).parents("ul.item-price");
		var name = $ul.find("li").eq(0).find("input[type='radio']").attr("name");
		
		var type = jQuery("[name='"+name+"']:checked").attr("value");
		var id = $(this).attr("data-id");
		if (id != undefined && type != undefined) {
			var redirectUrl = "serviceControl.do?createOrder&orgServeId="+id+"&mode="+type+"&count=1";
			jQuery.ajax({
				type: 'POST',
				contentType: 'application/x-www-form-urlencoded;charset=utf-8',
				url: 'loginControl.do?checkLoginStatus',
				data: {urlType: 'goBuy'},
				success: function(result) {
					var rUrl = "/login&redirectUrl="+encodeURIComponent(redirectUrl);
					if (result.success) {
						rUrl = redirectUrl;
					}
					location.href = rUrl;
				}
			});
		}
	});
});