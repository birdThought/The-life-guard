var physicalValidate = function(){
	
	var $physicalsDate = $('#physicalsDate').val();
	if($physicalsDate == ""){
		layer.msg("请填写体检日期");
		return false;
	}

	var $physicalsOrg = $('#physicalsOrg').val();
	if($physicalsOrg == ""){
		layer.msg("请填写体检机构");
		return false;
	}

	var $img1 = $('#img1').attr("src"); 
	var $img2 = $('#img2').attr("src");
	var $img3 = $('#img3').attr("src");	  				
	if(($img1 == "" ) && ($img2 == "") && ($img3 == "")){
		layer.msg("请选择至少一张图片");
		return false;
	}
};

