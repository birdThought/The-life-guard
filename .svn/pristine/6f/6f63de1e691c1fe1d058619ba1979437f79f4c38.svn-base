jQuery(function() {
	addCaseEventBind();
	liLink();
});

var healthFileControl = {
	getDatas: function(page, visitingDate, title) {
		var url = 'recordHealthFileControl.do?healthFile&nowPage='+page+'&pageSize=5';
		if(visitingDate != null) {
			url += '&visitingDate='+visitingDate;
		}
		if(title != null) {
			url += '&title='+title;
		}
		
		jQuery.ajax({
			type: 'GET',
			url: url,
			beforeSend: function() {
				layer.load(2);
			},
			success: function(result) {
				var obj = result.obj;
				// 交给build来创建内容
				healthFileControl.buildContent(obj.data);
			},
			complete: function() {
				layer.closeAll("loading");
			}
		});
	},
	buildContent: function(data) {
		
		var $contentRoot = jQuery("ul.files");
		$contentRoot.empty();
		
		if(data.length > 0) {
			jQuery.each(data, function(index) {
				var $li = jQuery("<li class='panel' id='"+data[index].id+"'></li>");
				
				var $dl = jQuery("<dl></dl>");
				var $dt_title = jQuery("<dt>" + data[index].title + "</dt>");
				var $dd_departmentName = jQuery("<dd><span style='margin-right:15px;'>" + data[index].hospital + "</span>" + data[index].departmentName + "</dd>");
				var $dd_basicCondition = jQuery("<dd>" + data[index].basicCondition + "</dd>");
				var $dd_visitingDate = jQuery("<dd><small>" + new Date(parseInt(data[index].visitingDate)).Format("yyyy-MM-dd") + " 就诊</small></dd>");
				$dl.append($dt_title);
				$dl.append($dd_departmentName);
				$dl.append($dd_basicCondition);
				$dl.append($dd_visitingDate);
				
				var $div = jQuery("<div></div>");
				
				if(data[index].photoPath != "") {
					var $img_photoPath = jQuery("<img src='" + data[index].photoPath + "' />");
					$div.append($img_photoPath);
				}
				
				$li.append($dl);
				$li.append($div);
				
				$contentRoot.append($li);
			});
		} else {
			$contentRoot.append("<h3 style='text-align:center;'>暂时没有数据</h3>");
		}
	}
}

function addCaseEventBind() {
	jQuery("li.addMedicalCase span").click(function() {
		window.location.href = "recordHealthFileControl.do?add";
	});
}

function liLink() {
	jQuery("ul.files").on('click', 'li.panel', function() {
		window.location.href = "recordHealthFileControl.do?healthFileDetail&id="+this.id;
	});
}

