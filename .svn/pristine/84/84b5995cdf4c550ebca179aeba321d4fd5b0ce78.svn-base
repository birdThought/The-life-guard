//健康包设备js通用类
var generalFunction = {
	deviceType: '',	// APP HL-031
	healthArea: [],
	renderTime: function(date) {
		var da = new Date(parseInt(date));
		return da.getFullYear()+"-"+(da.getMonth()+1)+"-"+da.getDate()+" "+da.getHours()+":"+da.getMinutes()+"";
	},
	 renderTime_1: function(date){
		var da = new Date(parseInt(date));
		return (da.getMonth()+1)+"-"+(da.getDate());
	},
	renderTime_2: function(date) {
		var da = new Date(parseInt(date));
		return da.getFullYear()+"-"+(da.getMonth()+1)+"-"+da.getDate();
	},
	//获取纵轴数据
	 getStandarArray: function(standar){
		var avg = (healthStandardValueMax - healthStandardValueMin) / 2;
		var equivalent = avg * standar;
		var midlePoint = avg + parseFloat(healthStandardValueMin);
		valArray = [
		  parseInt(midlePoint - 4 * equivalent),
		  parseInt(midlePoint - 3 * equivalent),
		  parseInt(midlePoint - 2 * equivalent),
		  parseInt(midlePoint - 1 * equivalent),
		  parseInt(midlePoint - 0 * equivalent),
		  parseInt(midlePoint + 1 * equivalent),
		  parseInt(midlePoint + 2 * equivalent),
		  parseInt(midlePoint + 3 * equivalent),
		  parseInt(midlePoint + 4 * equivalent),
		];
	},
	//获取设备数据最高最低值
	 getHealthStandardValue: function(area) {
		areaArray=[];
		areaArray=area.split("-");
		healthStandardValueMax=areaArray[1];
		healthStandardValueMin=areaArray[0];
	},
//	改变建议为异常的文本颜色
	/*getAbnormalColor: function() {
		$(document).ready(function(){
			$("td[class = 'tdAdvice']").each(function(i){
				if ($(this).text() == "异常") {
					$(this).css("color","red");
				}
			});
		});
	},*/
//	重新组装异常数据
	/*getAbnormalData: function(area, paramData) {
	 	var array = new Array();
	 	array = area.toString().split("-");
		if (paramData < parseFloat(array[0]) || paramData > parseFloat(array[1])) {
			paramData = "<span style = 'color:red'>" + paramData + "</span>" + "(" + area + ")";
		}
		return paramData;
	},*/
	// 获取健康标准值
	getHealthAreaData: function() {
		// 检测是否支持localStorage存储
		if (this.healthArea.length == 0) {
			this.getHealthAreaDataByAjax();
		}
		return this.healthArea;
	},
	// ajax获取后台数据
	getHealthAreaDataByAjax: function() {
		jQuery.ajax({
			type: 'GET',
			async: false,
			dataType : 'json',
			url: 'healthDataControl.do?getUserHealthArea',
			success: function(result) {
				var attributes = result.attributes;
				generalFunction.healthArea = attributes;
			}
		});
	}
}