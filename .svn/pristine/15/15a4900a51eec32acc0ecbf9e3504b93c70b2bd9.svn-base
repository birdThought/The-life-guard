/**
 * <p>需要包含的js文件有
 * <p>iCheck、百度地图、jQuery、dateFormat
 * 
 * <p>需要包含的函数有
 * <p>_clickNav()   --选项栏切换函数
 * 
 * <p>应用的jsp页面
 * <p>当前定位、电子围栏、轨迹回放
 * 
 * <p>不足的地方
 * <p>在向页面填充数据时候，用到了iCheck插件，处理checkbox的勾选问题
 * <p>为了动态刷新数据，部分地方使用到_clickNav函数
 * <p>处理日期的时候，使用到dateFormat为Date对象添加的Format方法
 * <p>用于处理ajax请求的url、ajax的返回结果已经做了限制，扩展性有待加强...
 * 
 * <p>具体使用方法：
 * <p>1._location.eventBind()绑定方法
 * <p>2._location.setGps()方法
 */

/**
 * 地址
 */
var _location = {}
/**
 * 经度
 */
_location.lng = 0.0;
/**
 * 纬度
 */
_location.lat = 0.0;

/**
 * 设置经纬度
 */
_location.setGps = function(lng, lat){
	this.lng = lng;
	this.lat = lat;
}

/**
 * 当前定位
 */
_location.current = {}
/**
 * 电子围栏
 */
_location.fence = {}
/**
 * 轨迹回放
 */
_location.orbit = {}

/**
 * 输入框查询地址
 * @param input_id 输入框的ID
 * @param map_id 包裹地图div的ID
 */
function theLocation(input_id, map_id) {
	var map;
	var pointMarker;
	// 百度地图API功能
	map = new BMap.Map(map_id);
	map.addControl(new BMap.ScaleControl(), 13);// 添加比例尺控件
	map.addControl(new BMap.MapTypeControl());
	map.enableScrollWheelZoom(true);
	var city = document.getElementById(input_id).value;
	console.log(city);
	if (city != "") {
		map.centerAndZoom(city, 15); // 用城市名设置地图中心点

		var options = {
			onSearchComplete : function(results) {
				if (local.getStatus() == BMAP_STATUS_SUCCESS) {
					map.setCenter(results.getPoi(0).point);
					map.removeOverlay(pointMarker);
					var icon = new BMap.Icon(
							'http://120.76.77.36/plug-in/com/tzcms/images/zd.png',
							new BMap.Size(20, 32), {
								anchor : new BMap.Size(10, 30)
							});
					pointMarker = new BMap.Marker(results.getPoi(0).point, {
						icon : icon
					});
					map.addOverlay(pointMarker);
					pointMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
				}

			}
		};
		// 为map对象添加监听事件，获取坐标保存在全局参数中
		map.addEventListener("click", function(e){
			_location.setGps(e.point.lng, e.point.lat);
			if(old_divId == "electricFence"){
				_location.getAddress();
			}
		});
		var local = new BMap.LocalSearch(map, options);
		local.search(city);
	}
}

/**
 * 画地图
 * @param id 显示地图的容器id
 * @param lng 经度
 * @param lat 纬度
 * @param radius 半径长度(如果半径为0表示不画圈圈)
 * @param zoomSize 显示缩放比例
 * @param terminalType 设备类型 hl03、c3
 * @returns {BMap.Map}
 */
_location.createMap = function(id, lng, lat, radius, zoomSize, terminalType) {
	var zs = zoomSize == 0 ? 14 : zoomSize;	// 默认大小14
	
	var map = new BMap.Map(id);
	map.centerAndZoom(new BMap.Point(lng, lat), zs);
	map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
	map.addControl(new BMap.ScaleControl());// 添加比例尺控件
	map.enableScrollWheelZoom(true); // 地图可以拖拽
	var overView = new BMap.OverviewMapControl();// 添加缩略图控件
	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
		// LARGE类型
		type : BMAP_NAVIGATION_CONTROL_ZOOM,
		offset : new BMap.Size(30, 50),
		// 启用显示定位
		enableGeolocation : false
	});
	map.addControl(navigationControl);

	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl({
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
		offset : new BMap.Size(26, 120),
		showAddressBar : false
	});
	map.addControl(geolocationControl);
	map.addControl(overView);
	var marker = new BMap.Marker(new BMap.Point(lng, lat));// 创建标注
	map.addOverlay(marker);// 将标注添加到地图上
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); // 跳动的动画

	if(radius > 0){
		var circle = new BMap.Circle(new BMap.Point(lng, lat), radius, {
			fillColor : "blue",
			strokeWeight : 1,
			fillOpacity : 0.3,
			strokeOpacity : 0.3
		});
		map.addOverlay(circle);
	}
	
	// 为map对象添加监听事件，获取坐标保存在全局参数中
	map.addEventListener("click", function(e){
		_location.setGps(e.point.lng, e.point.lat);
		if(old_divId == "electricFence"){
			_location.getAddress();
		}
	});
	
	return map;
}

/**
 * 获取详细地址
 */
_location.getAddress = function(){
	var result;
	jQuery.ajax({
		type: 'GET',
		url: 'terminalWebControl.do?address&lng=' + _location.lng + '&lat=' + _location.lat,
		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		success: function(json){
			result = json;
		},
		complete: function(){
			if(result.success){
				jQuery("#fence_addr").text(result.attributes.address);
			}
		}
	});
}

/**
 * 当前定位 -- 绘制地图
 * @param terminalType 设备类型
 * @param Dom 地图id
 */
_location.current.drawMap = function(Dom, terminalType, callback){
	var bool = false;
	var lng = 0.0;
	var lat = 0.0;
	var address = '';
	var measureDate;
	jQuery.ajax({
		type: 'GET',
		url: 'terminalWebControl.do?gps&terminalType=' + terminalType,
		success: function(result){
			bool = result.success
			if(bool){
				lng = result.obj.lng;
				lat = result.obj.lat;
				address = result.obj.address;
				measureDate = result.obj.measureDate;
			}
			if(address == '' || address == '天安门'){
				$('.address').text("暂未有定位记录!");
			}else{
				measureDate = new Date(measureDate);
				var now = new Date();
				var time_diff = now.getTime()-measureDate.getTime();  //时间差的毫秒数
				console.log(time_diff);
				if(time_diff < 86400000){	//一天
					//计算出相差天数
					var days=Math.floor(time_diff/(24*3600*1000));
					//计算出小时数
					var leave1=time_diff%(24*3600*1000);    //计算天数后剩余的毫秒数
					var hours=Math.floor(leave1/(3600*1000));
					//计算相差分钟数
					var leave2=leave1%(3600*1000);       //计算小时数后剩余的毫秒数
					var minutes=Math.floor(leave2/(60*1000));
					measureDate = (hours > 0 ? hours + '小时' : '') + minutes + '分钟前';
				}else{
					measureDate = measureDate.Format("yyyy-MM-dd hh:mm:ss");
				}
				$('.currentLocate div span').text('上一定位点:('+ measureDate +')');
				$('.currentLocate div span').attr('measureDate', measureDate);
				$('.currentLocate div span').css('display', 'block');
				$('.address').text(address);
			}
		},
		complete: function(){
			if(bool){
				_location.createMap(Dom, lng, lat, 0, 0, terminalType);
				if(typeof callback == "function"){
					  callback(measureDate);
					 }
			}
		}
	});
}
/*
 * 获取实时位,发送短信指令
 */
_location.current.getCurrentLocation = function(Dom, terminalType){
	var bool = false;
	var measureTime;
	var oldTime = $('.currentLocate div span').attr('measuredate');
	console.log('oldTime:' + oldTime);
	layer.confirm('确定获取实时位置?', {icon: 3, title:'提示'}, function(index){
		//loading层
		jQuery.ajax({
			type: 'GET',
			url: 'terminalWebControl.do?getCurrentLocation&terminalType=' + terminalType,
			success: function(result){
				layer.msg(result.msg);
				if(result.success){
					sAlert('正在获取实时位置...');
					var index1 = layer.load(1, {
						  shade: [0.1,'#fff'] //0.1透明度的白色背景
						});
					var s = window.setInterval(function(){
						
						refeshLocation(Dom, terminalType, function(measureDate){
							measureTime = measureDate;
							if(measureTime != oldTime){
								window.clearInterval(s);
								$('#bgDiv').remove();
								layer.close(index1);
							}
						});
						
					},10000); 
				}
			},
			complete: function(){
			}
		});
		  layer.close(index);
		});
}
function refeshLocation(Dom, terminalType, callback){
	_location.current.drawMap(Dom, terminalType, function(measureDate){
	if(typeof callback == "function"){
		  callback(measureDate);
		 }
	});
}
/**
 * 电子围栏 -- 绘制地图
 * @param terminalType 设备类型
 * @param Dom 地图id
 */
_location.fence.drawMap = function(Dom, terminalType){
	var bool = false;
	var lng = 0.0;
	var lat = 0.0;
	jQuery.ajax({
		type: 'GET',
		url: 'terminalWebControl.do?electricFences&terminalType=' + terminalType,
		success: function(result){
			bool = result.success;
			if(bool){
				var obj = result.obj;
				/**
				 * 清空element，再创建新的element
				 */
				jQuery("#electricFence ul.createFance").children("li:not('.createOne')").remove();
				if(obj.size == 0){
					lng = obj.gps.lng;
					lat = obj.gps.lat;
					radius = 0;
				} else {
					lng = obj.fence.longitude;
					lat = obj.fence.latitude;
					radius = obj.fence.radius;
					for(var i= 0; i< obj.size; i++){
						var $li = jQuery('<li><a value="' + obj.numbers[i] + '" href="javascript:void(0)">围栏' + obj.numbers[i] + '</a></li>');
						jQuery("#electricFence ul.createFance").append($li);
					}
				}
			}
		},
		complete: function(){
			if(bool){
				_location.createMap(Dom, lng, lat, radius, 16, terminalType);
			}
		}
	});
}

/**
 * 电子围栏--表单隐藏与显示切换
 */
_location.fence.formToggle = function(isCreate){
	var $form = jQuery("#electricFence div.setFance");
	if ($form.is(":hidden")) {
		if(isCreate!=undefined&&isCreate){
			$form.attr("style","right:0");
		}else{
			$form.attr("style","left:0");
		}
		$form.animate({height:"show"});
	} else {
		$form.animate({height:"hide"});
		/** 数据清空 */
		jQuery("#fence_addr").text("无");
		jQuery("#fence_radi>em").text("0");
		jQuery("#fence_phone").val("");
		jQuery("[name='fence_check_in']").iCheck("uncheck");
		jQuery("[name='fence_check_ou']").iCheck("uncheck");
		jQuery("[name='fence_check_1']").iCheck("uncheck");
		jQuery("[name='fence_check_2']").iCheck("uncheck");
		jQuery("[name='fence_check_3']").iCheck("uncheck");
		jQuery("#start_one").val("00:00");
		jQuery("#start_two").val("00:00");
		jQuery("#start_three").val("00:00");
		jQuery("#end_one").val("00:00");
		jQuery("#end_two").val("00:00");
		jQuery("#end_three").val("00:00");
		jQuery("[name='fence_number']").val("");
	}
}

/**
 * <p>电子围栏事件绑定
 * <p>1_选项栏切换、2_保存(添加)与修改电子围栏信息
 * <p>3_取消按钮、4_查看围栏详细信息、5_删除电子围栏
 * @param mapId 地图id  
 * @param terminalType 设备类型 hl03、c3
 */
_location.fence.eventBind = function(mapId, terminalType){
	
	// 创建围栏-选项栏展示
	jQuery("#electricFence ul.createFance").on('click', 'li.createOne>a', function(event){
		event.preventDefault();
		jQuery("#electricFence div.setFance>ul>li:last").prev("li.fence_remove").remove();
		jQuery("[name='fence_number']").val("");
		// jRange修改
		jRangeFenceSilder.jRange("setValue", 0);
		_location.fence.formToggle(true);
	});
	
	// 保存(添加)与修改电子围栏信息
	// 保存(添加)与修改电子围栏的terminalType参数包含在_data中
	jQuery("#fence_save").click(function(e){
		var _number = jQuery("[name='fence_number']").val();
		var _action = 0;	// 0表示创建 1表示修改
		var _url = "";
		var _bool = false;	// false 表示
		/**
		 * 查询隐藏域的fence_number的值
		 * 如果是空，表示此次行为是添加一个新的电子围栏
		 * 如果不为空，表示此次行为是修改电子围栏的信息
		 * 
		 * 在这里，利用数数的方式，从1数到100，
		 * 估算了新生成的围栏编号
		 * 
		 */
		if(_number == ""){
			_url = "terminalWebControl.do?electricFence";
			var numbers = "";
			jQuery("#electricFence ul.createFance li:not('.createOne') a").each(function(){
				numbers += jQuery(this).attr("value");
			});
			for(var i=0; i< 100; i++){
				if((i+1) != numbers.charAt(i)){
					_number = (i+1);
					break;
				}
			}
		}else{
			_action = 1;
			_url = "terminalWebControl.do?modifyElectronicFence";
		}
		
		var _enable = "";
		var _warningType = 0;	// 默认0表示进、出栏都不预警
		for(var i= 1; i <= 3; i++){
			if(jQuery("[name='fence_check_" + i + "']").is(":checked"))
				_enable += 1;
			else
				_enable += 0;
		}
		if(jQuery(".in").is(":checked"))
			_warningType = 1;
		if(jQuery(".out").is(":checked"))
			_warningType = 2;
		if(jQuery(".in").is(":checked") && jQuery(".out").is(":checked"))
			_warningType = 3;
		
		var _data = "{'number':" + _number + ",'radius':" + jQuery("#fence_radi>input.slider-fence").val() + "," +
		"'latitude':'" + _location.lat + "','longitude':'" + _location.lng + "','warningType':" + _warningType + "," +
		"'address':'" + jQuery("#fence_addr").text() + "','startTime1':'" + jQuery("#start_one").val()+":00" + 
		"','endTime1':'" + jQuery("#end_one").val()+":00" + "','startTime2':'" + jQuery("#start_two").val()+":00" + 
		"','endTime2':'" + jQuery("#end_two").val()+":00" + "','startTime3':'" + jQuery("#start_three").val()+":00" + "'," +
		"'endTime3':'" + jQuery("#end_three").val()+":00" + "','enabled':'" + _enable + "'," +
		"'phone':'" + jQuery("#fence_phone").val() + "','terminalType':'" + terminalType + "'}";
		var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
		if(_location.lng != 0.0 && _location.lat != 0.0 && jQuery("#fence_addr").text() != "无"){
			if((regex.test(jQuery("#fence_phone").val()))){
			$.ajax({
				url: _url,
				data: _data,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				beforeSend: function(){
					// 模拟click事件，将工具栏缩回去
					jQuery("#electricFence li.createOne>a").trigger('click');
				},
				success: function(result){
					layer.msg(result.msg);
					_bool = result.success;
					// 成功添加或修改电子围栏信息，就刷新内容
					if(_bool){
						var $this = jQuery("div.container>ul>li.action");
						setTimeout(function(){
							_clickNav($this);
						}, 1000);
					}
				}
			});
			}else{
				layer.msg("手机号码格式不正确");
			}
		} else {
			layer.msg("请点击地图创建电子围栏区域");
		}
	});
	
	// 取消按钮
	jQuery("#fence_cancel").click(function(){
		var $_a = jQuery("#electricFence li.createOne>a");
		$_a.trigger('click');
	});
	
	// 查看围栏详细信息
	jQuery("#electricFence ul.createFance").on('click', 'li:not(".createOne")>a', function(event){
		event.preventDefault();
		var $form = jQuery("#electricFence div.setFance");
		var bool = false;
		var $li = jQuery("#electricFence div.setFance>ul>li:last");
		var value = jQuery(this).attr("value");
		if ($form.is(":hidden")) {
			$.ajax({
				type: 'GET',
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				url: 'terminalWebControl.do?electricFence&number=' + jQuery(this).attr("value") + "&terminalType=" + terminalType,
				success: function(result){
					bool = result.success;
					if(bool){
						// 数据赋值
						var obj = result.obj;
						jQuery("#fence_addr").text(obj.address);
						jQuery("#fence_radi>input.slider-fence").val(obj.radius);
						jQuery("#fence_phone").val(obj.warningPhone);
						
						_location.lng = obj.longitude;
						_location.lat = obj.latitude;
						
						var enabled = String(obj.enabled);
						for(var i= 0; i< enabled.length; i++){
							if(enabled.charAt(i) == '1'){
								jQuery("[name='fence_check_" + (i+1) + "']").iCheck('check');
							}
						}
						var in_out = obj.warningType;
						switch(in_out){
						case 1:
							jQuery("[name='fence_check_in']").iCheck("check"); break;
						case 2:
							jQuery("[name='fence_check_ou']").iCheck("check"); break;
						case 3:
							jQuery("[name='fence_check_in']").iCheck("check");
							jQuery("[name='fence_check_ou']").iCheck("check");
							break;
						}
						var start1 = new Date(obj.startTime1).Format("hh:mm");
						jQuery("#start_one").val(start1);
						var end1 = new Date(obj.endTime1).Format("hh:mm");
						jQuery("#end_one").val(end1);
						
						var start2 = new Date(obj.startTime2).Format("hh:mm");
						jQuery("#start_two").val(start2);
						var end2 = new Date(obj.endTime2).Format("hh:mm");
						jQuery("#end_two").val(end2);
						
						var start3 = new Date(obj.startTime3).Format("hh:mm");
						jQuery("#start_three").val(start3);
						var end3 = new Date(obj.endTime3).Format("hh:mm");
						jQuery("#end_three").val(end3);
						// jRange修改
						jRangeFenceSilder.jRange("setValue", obj.radius);
						// 地图修改
						_location.createMap(mapId, obj.longitude, obj.latitude, obj.radius, 16, terminalType);
					}
				},
				complete: function(){
					if(bool){
						if(!$li.prev().hasClass("fence_remove")){
							jQuery("<li class='fence_remove' style='margin-top:13px'><span>删除该围栏</span></li>").insertBefore($li);
						}
						// fence_number
						jQuery("[name='fence_number']").val(value);
					}
				}
			});
			
		}
		_location.fence.formToggle();
	});
	
	// 删除电子围栏
	jQuery("#electricFence div.setFance").on('click', 'li.fence_remove>span', function(){
		$.ajax({
			type: 'POST',
			url: 'terminalWebControl.do?delElectronicFence',
			data: 'number=' + jQuery("[name='fence_number']").val() + "&terminalType=" + terminalType,
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			beforeSend: function(){
				// 模拟click事件，将工具栏缩回去
				jQuery("#electricFence li.createOne>a").trigger('click');
			},
			success: function(result){
				layer.msg(result.msg);
				if(result.success){
					var $this = jQuery("div.container>ul>li.action");
					setTimeout(function(){
						// 刷新数据
						_clickNav($this);
					}, 1000);
				}
			}
		});
	});
}

/**
 * 轨迹回放 -- 工具类绘制
 * <p>具体：
 * <p>1_时间段数据获取、2_时间段对应的按钮生成
 * @param terminalType 设备类型
 */
_location.orbit.drawToolBar = function(terminalType){
	var obj;
	jQuery.ajax({
		type: 'GET',
		url: 'terminalWebControl.do?orbitDetail&terminalType=' + terminalType,
		success: function(result){
			if(result.success){
				obj = result.obj;
				/**
				 * 清空element，再创建新的element
				 */
				jQuery("#orbitRun ul.createFance").children("li:not('.createOne')").remove();
				if(result.success){
					if (obj.length == 0) {
						
					} else {
						for(var i=0; i< obj.length; i++){
							if (obj[i].enabled) {
								var $li = jQuery('<li><a value="' + obj[i].number + '" href="javascript:void(0)">第' + obj[i].number + '时间段</a></li>');
								jQuery("#orbitRun ul.createFance").append($li);
							}
						}
					}
				}
			}
		},
		complete: function(){}
	});
}

/**
 * 轨迹回放表单切换状态
 * @param terminalType 设备类型 hl03、c3
 */
_location.orbit.formToggle = function(terminalType){
	var $form = jQuery("#orbitRun .setFance");
	if ($form.is(":hidden")) {
		jQuery.ajax({
			type: 'GET',
			contentType: 'application/json; charset=utf-8',
			url: 'terminalWebControl.do?orbitDetail&terminalType=' + terminalType,
			beforeSend: function(){
				$form.empty();
				var $h3 = $("<h3>轨迹回放</h3>");
				var $dl = $("<dl><dt>监控时间段：</dt></dl>");
				var $div = $("<div class='changeMonitor'><span id='orbit_update' class='orbitBtn'>修改</span>&nbsp;<span id='orbit_cancel' class='orbitBtn'>取消</span></div>");
				$form.append($h3);
				$form.append($dl);
				$form.append($div);
			},
			success: function(result){
				if(result.success){
					var obj = result.obj;
					var _dds = "";
					jQuery.each(obj, function(i){
						if (obj[i].enabled) {
							var s = new Date(obj[i].startTime).Format("hh:mm");
							var e = new Date(obj[i].endTime).Format("hh:mm");
							_dds += "<dd>第" + (i+1) + "时间段</dd><dd><span>" + s + "</span><em>至</em><span>" + e + "</span></dd>";
						}
					});
					if (_dds == ""){
						_dds = "<dd>无</dd>";
					}
					$form.children("dl").find("dd").remove();
					$form.children("dl").append(jQuery(_dds));
				}
			},
			complete: function(){
				$form.animate({height:"show"});
			}
		});
	} else {
		$form.animate({height:"hide"});
	}
}

/**
 * 轨迹回放 -- 绘画运动轨迹
 * @param Dom 地图ID
 * @param number 轨迹编号
 * @param date 日期
 * @param terminalType 设备类型 hl03、c3
 */
_location.orbit.drawMap = function(Dom, number, date, terminalType){
	var obj, map, bool, lastPoint, firstPoint, date;
	
	var _url = 'terminalWebControl.do?orbit2&number=' + number + "&terminalType=" + terminalType;
	if(date != ""){
		_url = 'terminalWebControl.do?orbit2&number=' + number + '&date=' + date + "&terminalType=" + terminalType;
	}
	
	jQuery.ajax({
		type: 'GET',
		url: _url,
		success: function(result){
			obj = result.obj;
			bool = result.success;
		},
		complete: function(){
			if (bool) {
				var points = [];
		        for(var i=0;i<=obj.length-1;i++){
		            points[i] = new BMap.Point(obj[i].lng,obj[i].lat);
		        }

				map = _location.createMap(Dom, points[0].lng, points[0].lat, 0, 0, terminalType);
				map.enableScrollWheelZoom();
 
				var view = map.getViewport(eval(points));  
				var mapZoom = view.zoom;   
				var centerPoint = view.center;   
				map.centerAndZoom(centerPoint,mapZoom);  

				var polyline = new BMap.Polyline(points,{strokeColor:'#111'});
		        map.addOverlay(polyline);

			    for (i=0;i<points.length;i++){
		              map.addOverlay(new BMap.Marker(points[i],{strokeColor:'#111'}));
		        }
			    
				var lushu = new BMapLib.LuShu(map,points,{
			                defaultContent:"",// "描述性文字"
			                autoView:true,// 是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
			                icon  : new BMap.Icon('http://developer.baidu.com/map/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
			                speed: 1000,
			                enableRotation:true,//是否设置marker随着道路的走向进行旋转
			                landmarkPois: []
				});
		        
				// 绑定事件(轨迹回放页面的按钮的事件)
				jQuery("#run").click(function(){
					lushu.start();
				});
				jQuery("#stop").click(function(){
					lushu.stop();
				});
				jQuery("#pause").click(function(){
					lushu.pause();
				});
			} else {
				_location.createMap(Dom, obj.lng, obj.lat, 0, 0, terminalType);
			}
		}
	});
}

/**
 * <p>轨迹回放事件绑定
 * <p>1_日期栏失去焦点事件、2_选择时间段绘制地图、3_轨迹回放表单切换状态
 * <p>4_修改按钮事件
 * @param mapId 地图id
 * @param terminalType 设备类型 hl03、c3
 */
_location.orbit.eventBind = function(mapId, terminalType){
	
	// 日期栏失去焦点事件
	jQuery("#orbit_date").focusout(function(){
		var number = jQuery("#orbit_number").val();
		_location.orbit.drawMap(mapId, number, jQuery(this).val(), terminalType);
	});
	
	// 选择时间段绘制地图
	$("#orbitRun").on('click', "ul.createFance li:not('.createOne')", function(){
		$(this).siblings(":not('.createOne')").removeClass("noticeBorder");
		$(this).addClass("noticeBorder");
		var date = $("#orbit_date").val();
		var number = $(this).children("a").attr("value");
		$("#orbit_number").val(number);
		_location.orbit.drawMap(mapId, number, date, terminalType);
	});
	
	// 轨迹回放表单切换状态
	jQuery("#orbitRun li.createOne").on('click', function(){
		_location.orbit.formToggle(terminalType);
	});
	
	// 修改按钮事件
	jQuery("#orbitRun").on("click", "#orbit_update", function(){
		var checkList = "";
		var bool = false;
		
		jQuery.ajax({
			type: 'GET',
			contentType: 'application/json; charset=utf-8',
			url: 'terminalWebControl.do?orbitDetail&terminalType=' + terminalType,
			beforeSend: function(){
				
			},
			success: function(result){
				bool = result.success;
				if(bool){
					var $h3 = jQuery("<h3>轨迹回放设置</h3>");
					var $ul = jQuery("<ul></ul>");
					var $li1 = jQuery("<li></li>");
					var $li2 = jQuery("<li><div class='changeMonitor'><span id='orbit_save' class='orbitBtn'>保存</span>&nbsp;<span id='orbit_cancel' class='orbitBtn'>取消</span></div></li>");
					// table
					var $tr = jQuery("<tr><td>监控时间段:</td></tr>");
					var $table = jQuery("<table></table>");
					$table.append($tr);
					
					var obj = result.obj;
					$.each(obj, function(i){
						if (obj[i].enabled) {
							checkList += 1;
						} else {
							checkList += 0;
						}
						
						var s = new Date(obj[i].startTime).Format("hh:mm");
						var e = new Date(obj[i].endTime).Format("hh:mm");
						
						var $tr = jQuery("<tr></tr>");
						var $td = jQuery("<td></td>");
						var $element = jQuery("<input name='orbit_cb_" + obj[i].number + "' type='checkbox'><em>第" + obj[i].number + "时间段</em><br/><input id='start_" + (obj[i].number+3) + "' type='text' value='" + s + "' onclick='TimePicker.showTimePicker(this)' readonly/><em>至</em><input id='end_" + (obj[i].number+3) + "' type='text' value='" + e + "' onclick='TimePicker.showTimePicker(this)' readonly />");
						$td.append($element);
						$tr.append($td);
						$table.append($tr);
					});
					
					$li1.append($table);
					$ul.append($li1);
					$ul.append($li2);
					
					
					var $root = jQuery("#orbitRun div.setFance");
					// 清除数据
					$root.empty();
					
					$root.append($h3);
					$root.append($ul);
				}
			},
			complete: function(){
				if(bool){
					ic('#orbitRun div.setFance table input');
					var tmp = String(checkList);
					for(var i=0; i< tmp.length; i++){
						if (tmp.charAt(i) == "1") {
							jQuery("[name='orbit_cb_" + (i+1) + "']").iCheck('check');
						}
					}
				}
			}
		});
	});
	
	jQuery("#orbitRun").on('click', '#orbit_save', function(){
		var c1 = jQuery("[name='orbit_cb_1']").is(":checked") ? 1 : 0;
		var c2 = jQuery("[name='orbit_cb_2']").is(":checked") ? 1 : 0;
		var c3 = jQuery("[name='orbit_cb_3']").is(":checked") ? 1 : 0;
		var s1 = jQuery("#start_4").val() + ":00";
		var s2 = jQuery("#start_5").val() + ":00";
		var s3 = jQuery("#start_6").val() + ":00";
		var e1 = jQuery("#end_4").val() + ":00";
		var e2 = jQuery("#end_5").val() + ":00";
		var e3 = jQuery("#end_6").val() + ":00";
		var _data = "{'c1':"+c1+",'s1':'"+s1+"','e1':'"+e1+"',c2:"+c2+",'s2':'"+s2+"','e2':'"+e2+"',c3:"+c3+",'s3':'"+s3+"','e3':'"+e3+"','terminalType':'" + terminalType + "'}";
		
		jQuery.ajax({
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			url: 'terminalWebControl.do?modifyOrbit',
			data: _data,
			beforeSend: function(){
				// 将侧边栏隐藏
				jQuery("#orbit_cancel").trigger("click");
			},
			success: function(result){
				layer.msg(result.msg);
				if(result.success){
					var $this = jQuery("div.container>ul>li.action");
					setTimeout(function(){
						_clickNav($this);
					}, 1000);
				}
			},
			complete: function(){
				
			}
		});
	});
	
	// 取消按钮
	jQuery("#orbitRun").on('click', "#orbit_cancel", function(){
		var $form = jQuery("#orbitRun div.setFance");
		$form.animate({height:"hide"});
	});
}