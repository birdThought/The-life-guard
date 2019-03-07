var cycleMaxData = {
	step: 1000000,
	lung: 10000,
	sugar: 10,
	temp: 40
}

$(document).ready(function(){
	
	$('#xinhuan').hide();
	$('#tizhicheng').hide();
	$('#xueyaji').hide();
	$('#feihuoyi').hide();
	$('#xuetangyi').hide();
	$('#xueyangyi').hide();
	$('#mainbar_2').hide();
	$('#mainbar_3').hide();
	$('#temperature').hide();
	//初始化菜单
	//initMenu();
	
	
});

//加载设备正常值
//jQuery(function() {
//	$.ajax({ 
//		async : false,
//		cache : false,
//		type: 'POST',
//		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
//		url: 'userHomeControl.do?getHealthStandardValue',
//		dataType: 'json',
//		success: function(result) {
//			var healthStandardArea = result.healthStandardValues;
//			//填充设备正常范围值
//			$('#heartRateArea').html(healthStandardArea.心率);
//			$('#bodyfatscaleArea').html(
//				"体重正常范围: " + "没有值" + " kg<br/>" +
//				"体脂率正常范围: " + healthStandardArea.体脂率+" %<br/>" +
//				"体年龄正常范围: " + healthStandardArea.体年龄+" 岁<br/>" +
//				"腰臀比正常范围: " + healthStandardArea.腰臀比+" %<br/>" +
//				"BMI正常范围: " + healthStandardArea.BMI+" kg/m<br/>" +
//				"去脂体重正常范围: " + healthStandardArea.去脂体重+" kg<br/>" +
//				"人体水份正常范围: " + healthStandardArea.人体水份+" %<br/>" +
//				"人体肌肉正常范围: " + healthStandardArea.肌肉+" %<br/>" +
//				"骨骼重量正常范围: " + healthStandardArea.骨量+" kg<br/>" +
//				"基础代谢正常范围: " + healthStandardArea.基础代谢+" kcal<br/>"	
//			);
//			$('#bloodPressureArea').html();
//			$('#lunginstrumentArea').html();
//			$('#bloodSugarArea').html();
//			$('#oxygenArea').html();
//			$('#temperatureArea').html();
//		}
//	});
//});
	



//圆环进度
function initMenu(){
	var menuText = "";
	$(".menu div").each(function(e){
		var divObj = this;
		console.log($(divObj).find("ul").length);
		$(divObj).find("ul").each(function(index,element){ 
			if($(element).css("display") == "block"){
				$(divObj).find("p").each(function(ind,ele){ 
					menuText = $(ele).text();
				});
			}
		});
	});
}

function _inforCenter(){
	layer.alert("消息中心");
}

function _help(){
	layer.alert("帮助");
}

require.config({
    paths: {
         echarts: 'static/plugins/echarts-2.2.7'
    }
});
	require(
    [
        'echarts',
        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    ],
function (ec) {
    // 基于准备好的dom，初始化echarts图表
    var myChart = ec.init(document.getElementById('main'));
    var myChartLung=ec.init(document.getElementById('mainLung')); 
    var myChartSugar=ec.init(document.getElementById('mainSugar'));
    var myChartSc=ec.init(document.getElementById('mainSc'));
    var myChartTp=ec.init(document.getElementById('mainTp'));
    var myChartHt=ec.init(document.getElementById('mainHt'));

    //圆环图初始化
	myChart.setOption(_initoOption(getParam("步","#48c858","", cycleMaxData.step,"", 0))); 
    myChartLung.setOption(_initoOption(getParam("ml","#48c858","",cycleMaxData.lung,"",0)));
    myChartSugar.setOption(_initoOption(getParam("mmoL/L","#fe7c6b","",cycleMaxData.sugar,"",0)));
    myChartSc.setOption(_initoOption(getParam("步","#48c858","",cycleMaxData.step,"",0)));
    myChartTp.setOption(_initoOption(getParam("℃","#2da9ff","",cycleMaxData.temp,"",0)));
    myChartHt.setOption(_initoOption(getParam("℃","#2da9ff","",cycleMaxData.temp,"",0)));

// 为echarts对象加载数据 
    $(function(){
    	
    	$.ajax({
    		async : true,
    		cache : false,
    		type: 'GET',
    		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
    		url: 'userHomeControl.do?getMeasureLastedData',
    		success: function(result){
    			if(result.obj == true){
    				$('#mainbar').hide();
    				layer.msg(result.msg);
    				return ;
    			}
    			/** 健康包数据 */
    			
    			//显示心率手环数据
    			if(result.attributes.showHeartRate == "true"){
    				fill_app_heartRate(result);
        			fill_app_sportBand(result,myChart);
        			$('#xinhuan').show();
    			}
    			
    			//显示体脂秤数据
    			if(result.attributes.showBodyfatscale == "true"){
    				fill_app_bodyfatscale(result);
    				$('#tizhicheng').show();
    			}
    				
    			//显示血压数据
    			if(result.attributes.showBloodPressure == "true"){
    				fill_app_bloodPressure(result);
    				$('#xueyaji').show();
    			}
    			
    			//显示肺活仪数据
    			if(result.attributes.showLunginstrument == "true"){
    				fill_app_lunginstrument(result,myChartLung);
    				$('#feihuoyi').show();
    			}
    			
    			//显示血糖数据
    			if(result.attributes.showGlucometer == "true"){
    				fill_app_glucometer(result,myChartSugar);
    				$('#xuetangyi').show();
    			}
    			
    			//显示血氧仪数据
    			if(result.attributes.showOxygen == "true"){
    				fill_app_oxygen(result);
    				$('#xueyangyi').show();
    			}
    			
    			//显示体温计数据
    			if(result.attributes.showTemperature == "true"){
    				fill_app_temperature(result, myChartHt);
    				$('#temperature').show();
    			}
    			
    			if(!((result.attributes.showHeartRate != null) || (result.attributes.showBodyfatscale != null) 
    					|| (result.attributes.showBloodPressure != null) || (result.attributes.showLunginstrument != null)
    					|| (result.attributes.showGlucometer != null) || (result.attributes.showOxygen != null)
    					|| (result.attributes.showtemperature != null))){
    				$('#mainbar').hide();
    			}
    			
    			/**HL 系列数据*/
    			if(result.attributes.HL031 == "true"){
    				//血压
        			fill_hl_bloodPressure(result);
        			//计步器
        			fill_hl_sportBand(result,myChartSc);
        			//体温
        			fill_hl_temperature(result,myChartTp);
        			//心率
        			fill_hl_heartRate(result);
        			//心电
        			fill_hl_ecg(result);
    				$('#mainbar_2').show();
    			}
    			
    			/** C系列数据 */   
    			
    			if(result.attributes.C3 != null){
    				fill_c3_location(result);
    				$('#mainbar_3').show();
    			}
    		}
    	});
    		
    });  

}
);

function getParam(dw,color,wname,wvalue,name,value){
	var param = new Object();
	param.dw = dw;
	param.color = color;
	param.wname = wname;
	param.wvalue = wvalue;
	param.name = name;
	param.value = value;
	return param;
}

function _initoOption(_param){

var labelTop = {
    normal : {
        label : {
            show : true,
            position : 'center',
            formatter : '{b}',
            textStyle: {
                baseline : 'bottom'
            }
        },
        labelLine : {
            show : false
        }
    }
};

var labelFromatter = {
    normal : {
        label : {
        	
            formatter : function (params){
            	
                return _param.value + _param.dw;
            },
            textStyle: {
                baseline : 'top'
            }
        },
    color: _param.color
    },
};

var labelBottom = {
    normal : {
        color: '#ccc',
        label : {
            show : true,
            position : 'center'
        },
        labelLine : {
            show : false
        }
    },
    emphasis: {
        color: 'rgba(0,0,0,0)'
    }
};

var radius = [65, 50];
var option = {
    legend: {
        x : 'center',
        y : 'center',
        data:[
            ''
        ]
    },
    series : [
        {
            type : 'pie',
            center: ['50%', '50%'],
            radius : radius,
            x:'0%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:_param.wname, value:_param.wvalue, itemStyle : labelBottom},
                {name:_param.wname, value:_param.value,itemStyle : labelTop}
            ]
        }
    ]
};
return option;
}
function _bindDevice(){
	//iframe窗
	layer.open({
	      type: 2,
	      title: '添加设备绑定',
	      moveType: 1,
	      shadeClose: false,
	      shade: false,
	      maxmin: true, //开启最大化最小化按钮
	      shade: 0.5,
	      area: ['893px', '600px'],
	      scrollbar: false,
	      content: 'http://fly.layui.com/'
	    });
}    	
   
// 心率手环详情
function _xlshxq(){
	layer.alert("心率手环详情");
}

// 体脂秤详情
function _xzcxq(){
	layer.alert("体脂秤详情");
}	

//血压计详情
function _xyjxq(){
	layer.alert("血压计详情");
}

// 肺活仪详情
function _fhyxq(){
	layer.alert("肺活仪详情");
}	
  
//血糖仪详情
function _xtyxq(){
	layer.alert("血糖仪详情");
}

// 血氧仪详情
function _xyyxq(){
	layer.alert("血氧仪详情");
}	
  
// HL 更多功能
function _hlMore(){
	layer.alert("HL 更多功能");
}	

//C 更多功能
function _cMore(){
	layer.alert("C 更多功能");
}	

//填充健康包心率内容
function fill_app_heartRate(result){
	
	var _xl = result.attributes.healthStandard.heartRate == undefined ? "暂时没有心率数据" : "心率正常范围值: " + result.attributes.healthStandard.heartRate + " bpm";
	$('#heartRateArea').text(_xl);
	
	var heartRate = result.attributes.APP.HeartRate;
	if(heartRate != null){
		$('#heartrate_1').text(heartRate.heartRate + " bpm");
		$('#heartrate_2').text("心率：" + heartRate.heartRate + " bpm");
		$('#heartrate_date').text(renderTime(heartRate.measureDate));

		if(result.attributes.heartRate.status!=0){
			$('#heartRateStatus').text("不正常");
			$('#heartRateStatus').css({"color":"red","border-color":"red"});
		}
	}
	
	if(heartRate == null || result.attributes.sportBand == null){
		$('#heartrate_date').css({"color":"red","fontWeight":"bold"});
	}
}

//填充健康包计步器内容
function fill_app_sportBand(result,myChart){
	var sportBand = result.attributes.APP.Band;
	if(sportBand != null){
		$(".ing").text(sportBand.kcal + "卡路里");
		$(".ing_2").text(sportBand.mileage + "公里");
		//睡眠数据
		$('#deepDuration_1').css("width",sportBand.deepDuration);
		$('#shallowDuration_1').css("width",sportBand.shallowDuration);
		var _step = (cycleMaxData.step - sportBand.steps) < 0 ? 0 : (cycleMaxData.step - sportBand.steps);
		myChart.setOption(_initoOption(getParam("BPM","#48c858","计步",_step,"",sportBand.steps))); 
		
		$('#shallowDuration').text("浅睡 " + sportBand.shallowDuration + " min");
		$('#deepDuration').text("深睡 " + sportBand.deepDuration + " min");
		//计步器
		$("#kcal").css("width",sportBand.kcal);
		$("#mileage").css("width",sportBand.mileage);
	}
}

//填充健康包体脂秤内容
function fill_app_bodyfatscale(result){
	var bodyfatscaleArea = result.attributes.healthStandard;
	
	var _tz = bodyfatscaleArea.weight == undefined ? "暂时没有体重数据<br/>" : "体重正常范围: " + bodyfatscaleArea.weight + " kg<br/>";
	var _tzfl = bodyfatscaleArea.axungeRatio == undefined ? "暂时没有体脂肪率数据<br/>" : "体脂肪率正常范围: " + bodyfatscaleArea.axungeRatio + " %<br/>";
	var _tnl = bodyfatscaleArea.bodyage == undefined ? "暂时没有体年龄数据<br/>" : "体年龄正常范围: " + bodyfatscaleArea.bodyage + " 岁<br/>";
	var _ytb = bodyfatscaleArea.WHR == undefined ? "暂时没有腰臀比数据<br/>" : "腰臀比正常范围: " + bodyfatscaleArea.WHR + " %<br/>";
	var _bmi = bodyfatscaleArea.BMI == undefined ? "暂时没有BMI数据<br/>" : "BMI正常范围: " + bodyfatscaleArea.BMI + " kg/m<br/>";
	var _qztz = bodyfatscaleArea.fatFreeWeight == undefined ? "暂时没有去脂体重数据<br/>" : "去脂体重正常范围: " + bodyfatscaleArea.fatFreeWeight + " kg<br/>";
	var _rtsf = bodyfatscaleArea.moisture == undefined ? "暂时没有人体水份数据<br/>" : "人体水份正常范围: " + bodyfatscaleArea.moisture + " %<br/>";
	var _rtjr = bodyfatscaleArea.muscle == undefined ? "暂时没有人体肌肉数据<br/>" : "人体肌肉正常范围: " + bodyfatscaleArea.muscle + " %<br/>";
	var _ggzl = bodyfatscaleArea.boneWeight == undefined ? "暂时没有骨骼重量数据<br/>" : "骨骼重量正常范围: " + bodyfatscaleArea.boneWeight + " kg<br/>";
	var _jcdx = bodyfatscaleArea.baseMetabolism == undefined ? "暂时没有基础代谢数据<br/>" : "基础代谢正常范围: " + bodyfatscaleArea.baseMetabolism + " kcal<br/>";
//	var _dbz = bodyfatscaleArea.蛋白质 == undefined ? "暂时没有蛋白质数据<br/>" : "蛋白质正常范围值: " + bodyfatscaleArea.蛋白质 + " %<br/>";
	var _nzzf = bodyfatscaleArea.visceralFat == undefined ? "暂时没有内脏脂肪数据" : "内脏脂肪正常范围: " + bodyfatscaleArea.visceralFat + "";
	
	$('#bodyfatscaleArea').html(_tz + _tzfl + _tnl + _ytb + _bmi + _qztz + _rtsf + _rtjr + _ggzl + _jcdx + _nzzf);

	var bodyfatscale = result.attributes.APP.Bodyfatscale;
	if(bodyfatscale != null){
		$('#bodyfatscaleWeight').text(bodyfatscale.weight);
		$('#bodyfatscaleAxungeRatio').text(bodyfatscale.axungeRatio);
		$('#bodyfatscaleBodyage').text(bodyfatscale.bodyage);
		$('#bodyfatscaleWHR').text(bodyfatscale.WHR);
		$('#bodyfatscaleBMI').text(bodyfatscale.BMI);
		$('#bodyfatscaleFatFreeWeight').text(bodyfatscale.fatFreeWeight);
		$('#bodyfatscaleMoisture').text(bodyfatscale.moisture);
		$('#bodyfatscaleMuscle').text(bodyfatscale.muscle);
		$('#bodyfatscaleBoneWeight').text(bodyfatscale.boneWeight);
		$('#bodyfatscaleBaseMetabolism').text(bodyfatscale.baseMetabolism);
		$('#bodyfatscaleProteide').text("0");
		$('#bodyfatscaleVisceralFat').text(bodyfatscale.visceralFat);
		$('#bodyfatscale_date').text(renderTime(bodyfatscale.measureDate));
		
		if(bodyfatscale.status!=0){
			$('#bodyfatscaleStatus').text("不正常");
			$('#bodyfatscaleStatus').css({"color":"red","border-color":"red"});
		}			
	}else{
		$('#bodyfatscale_date').css("color","red").css("fontWeight","bold");
	}
}

//填充健康包血压内容
function fill_app_bloodPressure(result){
	var bloodpressureArea = result.attributes.healthStandard;
	
	var _ssy = bloodpressureArea.systolic == undefined ? "暂时没有收缩压数据<br/>" : "收缩压正常范围：" + bloodpressureArea.systolic + " mmHg<br/>";
	var _szy = bloodpressureArea.diastolic == undefined ? "暂时没有舒张压数据<br/>" : "舒张压正常范围 :" + bloodpressureArea.diastolic + " mmHg<br/>";
	var _xl = bloodpressureArea.heartRate == undefined ? "暂时没有心率数据" : "心率正常范围 :" + bloodpressureArea.heartRate + " bpm";
	
	$('#bloodPressureArea').html(_ssy + _szy + _xl);
	
	var bloodPressure = result.attributes.APP.Bloodpressure;
	if(bloodPressure != null){
		//图片数据
		$('#systolic_1').css("width",bloodPressure.systolic);
		$('#diastolic_1').css("width",bloodPressure.diastolic);
		$('#heartRate_1').css("width",bloodPressure.heartRate);
		//文本数据
		$('#systolic').text("收缩压  " + bloodPressure.systolic + " mmHg");
		$('#diastolic').text("舒张压 " + bloodPressure.diastolic + " mmHg");
		$('#bloodPressure_heartRate').text("心率 " + bloodPressure.heartRate + " bpm");
		$('#bloodPressure_status').text(renderTime(bloodPressure.measureDate));
		
		if(bloodPressure.status != 0){
			$('#bloodPressureStatus').text("不正常");
			$('#bloodPressureStatus').css({"color":"red","border-color":"red"});
		}
	}else{
		$('#bloodPressure_status').css("color","red").css("fontWeight","bold");
	}	
}

//填充健康包肺活仪内容
function fill_app_lunginstrument(result,myChartLung){
	
	var _fhl = result.attributes.healthStandard.vitalCapacity == undefined ? "暂时没有肺活量数据" : "肺活量正常范围：" + result.attributes.healthStandard.vitalCapacity + " ml";
	
	$('#lunginstrumentArea').html(_fhl);
	
	var lunginstrument = result.attributes.APP.Lunginstrument;
	if(lunginstrument != null){
		var _lung = (cycleMaxData.lung - lunginstrument.vitalCapacity) < 0 ? 0 : (cycleMaxData.lung - lunginstrument.vitalCapacity);
		myChartLung.setOption(_initoOption(getParam("ml","#48c858","", _lung,"", lunginstrument.vitalCapacity)));
		
		$('#vitalCapacity').text("肺活仪：" + lunginstrument.vitalCapacity + " ml");
		$('#lunginstrument_date').text(renderTime(lunginstrument.measureDate));
		
		if(lunginstrument.status != 0){
			$('#lunginstrumentStatus').text("不正常");
			$('#lunginstrumentStatus').css({"color":"red","border-color":"red"});
		}
	}else{
		$('#lunginstrument_date').css("color","red").css("fontWeight","bold");
	}	
}

//填充健康包血糖仪内容
function fill_app_glucometer(result,myChartSugar){
	
	var glucometerArea = result.attributes.healthStandard.bloodSugar;
	
	var _bM = glucometerArea.beforeMeal == undefined ? "<div>暂时没有餐前数据</div>" : "<div>餐前正常范围：" + glucometerArea.beforeMeal + " mmoL/L";
	var _aM = glucometerArea.afterMeal == undefined ? "<div>暂时没有餐后数据</div>" : "<div>餐后正常范围：" + glucometerArea.afterMeal + " mmoL/L";
	
	$('#bloodSugarArea').empty();
	$('#bloodSugarArea').append(_bM);
	$('#bloodSugarArea').append(_aM);
	
	var glucometer = result.attributes.APP.Glucometer;
	if(glucometer != null){
		var _sugar = (cycleMaxData.sugar - glucometer.bloodSugar) < 0 ? 0 : (cycleMaxData.sugar - glucometer.bloodSugar);
		myChartSugar.setOption(_initoOption(getParam("mmoL/L","#fe7c6b","", _sugar,"",glucometer.bloodSugar)));
		$('#bloodSugar').text("血糖含量：" + glucometer.bloodSugar + " mmoL/L");
		$('#glucometer_date').text(renderTime(glucometer.measureDate));
		
		if(glucometer.status != 0){
			$('#glucometerStatus').text("不正常");
			$('#glucometerStatus').css({"color":"red","border-color":"red"});
		}
	}else{
		$('#glucometer_date').css("color","red").css("fontWeight","bold");
	}
}

//填充健康包血氧仪内容
function fill_app_oxygen(result){
	
	var _xybhd = result.attributes.healthStandard.saturation == undefined ? "暂时没有血氧饱和度数据<br/>" : "血氧饱和度正常范围：" + result.attributes.healthStandard.saturation + " %" + "<br/>";
	var _xl = result.attributes.healthStandard.heartRate == undefined ? "暂时没有心率数据" : "心率正常范围：" + result.attributes.healthStandard.heartRate + " bpm";
	
	$('#oxygenArea').html(_xybhd + _xl);
	
	var oxygen = result.attributes.APP.Oxygen;
	if(oxygen != null){
		$('#saturation_1').css("width",oxygen.saturation);
		$('#oxygen_heartRate_1').css("width",oxygen.heartRate);
		
		$('#saturation').text("血氧饱和度    " + oxygen.saturation + " %");
		$('#oxygen_heartRate').text("心率 " + oxygen.heartRate + " bpm");
		$('#oxygen_date').text(renderTime(oxygen.measureDate));
		
		if(oxygen.status != 0){
			$('#oxygen_status').text("不正常");
			$('#oxygen_status').css({"color":"red","border-color":"red"});
		}
	}else{
		$('#oxygen_date').css({"color":"red","fontWeight":"bold"});
	}
}

//填充体温数据内容
function fill_app_temperature(result, myChartHt){
	
	var _tw = result.attributes.healthStandard.temperature == undefined ? "暂时没有体温数据 " : "体温的正常范围：" + result.attributes.healthStandard.temperature + " ℃";
	
	$('#temperatureArea').html(_tw);
	
	var temperature = result.attributes.APP.Temperature;
	if(temperature != null){
		var _temp = (cycleMaxData.temp - temperature.temperature) < 0 ? 0 : (cycleMaxData.temp - temperature.temperature);
		myChartHt.setOption(_initoOption(getParam("℃","#fe7c6b","", _temp,"",temperature.temperature)));
		$('#temperature_date').html(renderTime(temperature.measureDate));
		$('#temperature_p').html("体温：" + temperature.temperature + " ℃");
		if(temperature.status != 0){
			$('#temperatureStatus').text("不正常");
			$('#temperatureStatus').css({"color":"red","border-color":"red"});
		}
	}else{
		$('#temperature_date').css({"color":"red","fontWeight":"bold"});
	}
}


/**填充HL系列手环数据*/
//填充HL血压数据内容
function fill_hl_bloodPressure(result){
	var _bloodpressureArea = result.attributes.healthStandard;
	
	var _ssy = _bloodpressureArea.systolic == undefined ? "暂时没有收缩压数据<br/>" : "收缩压正常范围：" + _bloodpressureArea.systolic + " mmHg<br/>";
	var _szy = _bloodpressureArea.diastolic == undefined ? "暂时没有舒张压数据<br/>" : "舒张压正常范围 :" + _bloodpressureArea.diastolic + " mmHg<br/>";
	var _xl = _bloodpressureArea.heartRate == undefined ? "暂时没有心率数据<br/>" : "心率正常范围 :" + _bloodpressureArea.heartRate + " bpm<br/>";
	
	$('#hlBloodPressureArea').html(_ssy + _szy + _xl);
	
	var _bloodPressure = result.attributes.HL.Bloodpressure;
	if(_bloodPressure != null){
		$('#_systolic_1').css("width",_bloodPressure.systolic);
		$('#_diastolic_1').css("width",_bloodPressure.diastolic);
		
		$('#_systolic').text(_bloodPressure.systolic + " mmHg");
		$('#_diastolic').text(_bloodPressure.diastolic + " mmHg");
		$('#blood_date').html(renderTime(_bloodPressure.measureDate));
		
		if(_bloodPressure.status != 0){
			$('#_bloodPressureStatus').text("不正常");
			$('#_bloodPressureStatus').css({"color":"red","border-color":"red"});
		}
	} else {
		$('#blood_date').css({"color":"red","fontWeight":"bold"});;
	}
}

//填充HL计步器数据内容
function fill_hl_sportBand(result,myChartSc){
	var band = result.attributes.HL.Band;
	if(band != null){
		var _step = (cycleMaxData.step - band.steps) < 0 ? 0 : (cycleMaxData.step - band.steps);
		myChartSc.setOption(_initoOption(getParam("步","#48c858","", _step,"",band.steps)));
			
		$('#_kcal').text(band.kcal + " 千卡");
		$('#_mileage').text(band.mileage + " 公里");
		$('#sleep_date').html(renderTime(band.measureDate));
		if(band.status != 0){
			$('#_sportBandStatus').text("不正常");
			$('#_sportBandStatus').css({"color":"red","border-color":"red"});
		}
		
		$('#_deepDuration_1').css("width", band.deepDuration);
		$('#_shallowDuration_1').css("width", band.shallowDuration);
		$('#_deepDuration').text("深睡 " + band.deepDuration + " min");
		$('#_shallowDuration').text("浅睡 " + band.shallowDuration + " min");
		$('#step_date').html(renderTime(band.measureDate));
//		if(band.status != 0){
//			$('#_sportBandStatus').text("不正常");
//			$('#_sportBandStatus').css({"color":"red","border-color":"red"});
//		}
	} else {
		$("#step_date").css({"color":"red","fontWeight":"bold"});;
		$('#sleep_date').css({"color":"red","fontWeight":"bold"});;
	}
}

//填充HL体温数据
function fill_hl_temperature(result,myChartTp){
	
	var _tw = result.attributes.healthStandard.temperature == undefined ? "暂时没有提问数据" : "正常体温范围：" + result.attributes.healthStandard.temperature + " ℃";
	
	$('#hlTemperature').html(_tw);
	var _temperature = result.attributes.HL.Temperature;
	if(_temperature != null){
		var _temp = (cycleMaxData.temp - _temperature.temperature) < 0 ? 0 : (cycleMaxData.temp - _temperature.temperature);
		myChartTp.setOption(_initoOption(getParam("℃","#2da9ff","", _temp,"", _temperature.temperature)));
		$('#temper_date').html(renderTime(_temperature.measureDate));
		 
		if(_temperature.status){
			$('#_temperatureStatus').text("不正常");
			$('#_temperatureStatus').css({"color":"red","border-color":"red"});
		}
	} else {
		$('#temper_date').css({"color":"red","fontWeight":"bold"});;
	}
}

//填充HL心率数据
function fill_hl_heartRate(result){
	
	var _xl = result.attributes.healthStandard.heartRate == undefined ? "暂时没有心率数据" : "心率正常范围：" + result.attributes.healthStandard.heartRate + " bpm";
	
	$('#hlHeartRate').html(_xl);
	var _heartRate = result.attributes.HL.HeartRate;
	if(_heartRate != null){
		$('#_heartRate1').text(_heartRate.heartRate + " bpm");
		$('#_heartRate2').text("心率 " + _heartRate.heartRate + " bmp");
		
		$('#heartRate_date').html(renderTime(_heartRate.measureDate));
		if(_heartRate.status != 0){
			$('#_heartRateStatus').text("不正常");
			$('#_heartRateStatus').css({"color":"red","border-color":"red"});
		}
	} else {
		$('#_heartRate_date').css({"color":"red","fontWeight":"bold"});;
	}
}

//填充HL心电数据
function fill_hl_ecg(result){
	// TODO 暂无数据
	//$('#hlEcgArea').html("心电正常范围：" + result.attributes.healthStandard.心电 + "bpm");
	$('#hlEcgArea').html("暂时没有心率数据");
	var _ecg = result.attributes.HL.Ecg;
	if(_ecg != null){
		$('#_eCG').text(_ecg.eCG + " bpm");
		$('#ecg_date').html(renderTime(_ecg.measureDate));
		
		if(_ecg.status != 0){
			$('#_ecg').text("不正常");
			$('#_ecg').css({"color":"red","border-color":"red"});
		}
	} else {
		$('#ecg_date').css({"color":"red","fontWeight":"bold"});
	}
}

//C系列数据填充
function fill_c3_location(result){
	
	if(result.attributes.mobile != null){
		$('#c3_mobile').text(" "+ result.attributes.mobile);
	}
	
	var location = result.attributes.location;
	if(location != null){			
		for(var i = 0; i < location.length; i++){		
			$('.ulAdd').append(
				"<li><ol>" +
					"<li>" + renderTime(location[i].measureDate) + "</li>" + 
					"<li>" + cutstr(location[i].address,40) + "</li>" +
					"<li>" + result.attributes.mobile + "</li>" + 
					"<li>操作</li>" +
				"</ol></li>");
		}
	}
}

$('#show_heartRateArea,#show_bodyfatscaleArea,#show_bloodPressureArea,' +  
		'#show_bloodPressureArea,#show_bloodSugarArea,#show_lunginstrumentArea,' +
		'#show_oxygenArea,#show_hlBloodPressureArea,#show_hlTemperature,#show_hlHeartRate,' +
		'#show_hlEcgArea,#show_temperatureArea' ).click(function(){
	
	$idValue = $(this).attr("id");
	$type = $idValue.split("_");
	
	layer.open({
		type : 1,
		moveType: 1,
		content : $('#' + $type[1]), //这里content是一个DOM
		title : [ '正常范围值', 'text-align:center;font-size:16px' ],
		btn : ['确定'],
		closeBtn : 0,
		area : '460px',		
	});
});
	
/** 
 * js截取字符串，中英文都能用 
 * @param str：需要截取的字符串 
 * @param len: 需要截取的长度 
 */
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4  
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；  
    if (str_length < len) {
        return str;
    }
}

//日期时间格式转换
function renderTime(date){
	var da = new Date(parseInt(date));
	var year = da.getFullYear();
	var month = fillZero(da.getMonth() + 1);
	var date = fillZero(da.getDate());
	var hours = fillZero(da.getHours());
	var minutes = fillZero(da.getMinutes());
	var seconds = fillZero(da.getSeconds());
	return year+"年"+month+"月"+date+"日"+hours+":"+minutes+":" + seconds;
}

//日期格式转换
function renderDate(date){
	var da = new Date(parseInt(date));
	var hour = fillZeroda.getHours();
	var minutes = fillZeroda.getMinutes();
	return hour+":"+minutes;
}


function fillZero(type){
	if(type < 10){
		type = "0" + type;
	}
	return type;
}
