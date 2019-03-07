

var monitor = {}

monitor.getData = function(terminalType){
	var $tbody = jQuery("#monitor tbody");
	var bool = false;
	var obj = null;
	// 数据清空
	$tbody.empty();
	
	jQuery.ajax({
		async: true,
		cache: false,
		type: 'GET',
		url: 'terminalWebControl.do?monitors&terminalType='+terminalType,
		beforeSend: function(){
			
		},
		success: function(result){
			bool = result.success;
			obj = result.obj;
			// 创建tr
			if(bool){
				jQuery.each(obj, function(i){
					var $tr = "";
					if(obj[i].selected){
						$tr = "<tr class='monitor_show'><td>" + obj[i].name + "</td><td>" + obj[i].number + "</td><td>" + obj[i].type + "</td><td class='selected monitor_td'><span style = 'display: block;'>启动监听</span></td></tr>";
					}else{
						$tr = "<tr><td>" + obj[i].name + "</td><td>" + obj[i].number + "</td><td>" + obj[i].type + "</td><td class='monitor_td'><span style = 'display: block;'>启动监听</span></td></tr>";
					}
					$tbody.append($tr);
				});
			}
		},
		complete: function(){
			if(bool){
				// 添加事件
				jQuery("#monitor tbody").on('mouseover mouseout click', 'tr', function(event){
					var $this = jQuery(this);
					if(event.type == "mouseover"){
						// 清除所有monitor_show类
						jQuery("#monitor tbody tr").removeClass("monitor_show");
						// 为现在hover的tr添加monitor_show类
						$this.addClass("monitor_show");
					}else if(event.type == "mouseout"){
						if(!$this.hasClass("monitor_click")){
							// 移除monitor_show类
							$this.removeClass("monitor_show");
							// 还原
							// 查找包含有selected类的tr,如果找到了就为其添加monitor_show类
							if(!$this.siblings("tr").is(".monitor_show")){
								jQuery("#monitor tbody").find(".selected").parents("tr").addClass("monitor_show");
							}
							jQuery("#monitor tbody tr").removeClass("monitor_click");
						}
					}else if(event.type == "click"){
						$this.addClass("monitor_click");
					}
				});
			}
		}
	});
}

monitor.eventBind = function(terminalType){
	
	// 点击事件
	jQuery("#monitor tbody").on('click', 'td.monitor_td span', function(){
		// 只处理不包含selected类的
		if(!jQuery(this).parent("td").hasClass("selected")){
			var number = jQuery(this).parents("tr").find("td:eq(1)").text();
			layer.confirm("使用此号码进行监听？",function(index){
				jQuery.ajax({
					async: true,
					cache: false,
					type: 'POST',
					url: 'terminalWebControl.do?modifyMonitor',
					data: 'number=' + number + '&terminalType=' + terminalType,
					beforeSend: function(){
						setTimeout(function(){
							layer.close(index);
						}, 800);
					},
					success: function(result){
						layer.msg('监听成功请等待设备回拨该号码！');
					},
					complete: function(){
						var $this = jQuery("div.container>ul>li.action");
						setTimeout(function(){
							_clickNav($this);
						}, 1000);
					}
				});
				layer.close(index);
			});
			return;
		}
		layer.msg("该号码已经在监听中");
	});
	
}
