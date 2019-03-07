jQuery(function(){
	dataStorage();
	
	checkDataLengthMoreThanOne();
	
	btnEventBind();
	typeNumberUpdateHtml();
});

var li_storage = [];

function checkDataLengthMoreThanOne() {
	if(li_storage.length == 0) {
		var $div = jQuery("<div class='record_dataNull'><br><br>提示信息：没有搜索到相关信息</div>");
		jQuery("ul.info_list").after($div);
	}
}

function btnEventBind() {
	jQuery("span.case_back").click(function() {
		window.location.href = "recordHealthFileControl.do?enter";
	});
	
	jQuery("span.case_edit").click(function() {
		var medicalId = parseInt(jQuery("[name='medicalId']").val());
		if(!isNaN(medicalId)) {
			window.location.href = "recordHealthFileControl.do?editMedical&medicalId="+medicalId;
		} else {
			layer.msg('病历不存在');
		}
	});
	
	jQuery("span.case_delete").click(function() {
		var medicalId = parseInt(jQuery("[name='medicalId']").val());
		console.log('meidical:' + medicalId)
		if(!isNaN(medicalId)) {
			layer.confirm("确定删除该病历",
					function() {
						jQuery.ajax({
							async: true,
							cache: false,
							type: 'POST',
							data: "medicalId="+medicalId,
							url: "recordHealthFileControl.do?deleteMedical",
							beforeSend: function() {
								layer.load();
							},
							success: function(result) {
								layer.msg(result.msg);
								if(result.success) {
									setTimeout(function() {
										jQuery("span.case_back").click();
									}, 800);
								}
							},
							complete: function() {
								layer.closeAll("loading");
							}
						});
					}
				);
		} else {
			layer.msg('病历不存在');
		}
		
	});
	
	jQuery("select.sort_button").change(function() {
		var value = parseInt(jQuery(this).val());
		
		if(hasSelectTheValidOption(value)) {
			courseTypeShow(value);
		} else {
			courseShowAll();
		}
		/** 重新定义页面高度 */
		reDefinedPageHeight();
		
		function hasSelectTheValidOption(value) {
			if(value == 1 || value == 2 || value == 3) {
				return true;
			}
			return false;
		}
	});
	
	jQuery("div.sortBtn").click(function() {
		if(jQuery(this).hasClass("asc")) {
			visitingDateSort(true);
			jQuery(this).removeClass("asc");
			jQuery(this).find("span").addClass("trangleR");
			jQuery(this).find("span").removeClass("trangle");
		} else {
			visitingDateSort(false);
			jQuery(this).addClass("asc");
			jQuery(this).find("span").removeClass("trangleR");
			jQuery(this).find("span").addClass("trangle");
		}
	});
}

function typeNumberUpdateHtml() {
	var $typeNumber = jQuery("input[name='case_typeNumber']");
	jQuery.each($typeNumber, function(index) {
		var $this = jQuery(this);
		var value = parseInt($this.val()) - 1;
		
		var $li = $this.parents("li");
		
		var typeValue = ["首诊", "复诊", "出院", "晚期"];
		var $textFiled = $li.find("div.left_wrap").children("div:eq(0)");
		$textFiled.text(typeValue[value]);
		
		var colorValue = ["#48C858", "#F50", "#48C858", "RED"];
		var $outCircle = $li.find("div.out_circle");
		$outCircle.css({"background-color":colorValue[value]});
	});
}

function dataStorage() {
	var $lis = jQuery("ul.info_list>li");
	jQuery.each($lis, function(index) {
		var $this = jQuery(this);
		li_storage.push($this);
	});
}

function visitingDateSort(isASC) {
	
	var $root = jQuery("ul.info_list");
	$root.empty();
	
	if(isASC) {
		for(var i=li_storage.length; i >= 0; i--) {
			$root.append(li_storage[i]);
		}
		return true;
	} else {
		for(var i=0; i < li_storage.length; i++) {
			$root.append(li_storage[i]);
		}
		return true;
	}
	return false;
}

function courseTypeShow(value) {
	var $typeNumber = jQuery("input[name='case_typeNumber']");
	
	jQuery.each($typeNumber, function(index) {
		var $this = jQuery(this);
		var $li = $this.parents("li");
		
		var typeNumber = parseInt($this.val());
		
		if(typeNumber == value) {
			$li.removeClass("courseHide");
		} else {
			$li.addClass("courseHide");
		}
		
	});
}

function courseShowAll() {
	var $lis = jQuery("ul.info_list>li");
	$lis.removeClass("courseHide");
}
