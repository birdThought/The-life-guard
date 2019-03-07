/*
 * 
 * 饮食记录列表
 * 
 * */
'use strict';
/*获取当前系统时间*/
var date=new Date();
var year = date.getFullYear();
var month = date.getMonth()+1;    //js从0开始取 
var day = date.getDate();
var hours=date.getHours();
var minutes=date.getMinutes();
if(day<10){
	day="0"+day;
}
if(month<10){
	month="0"+month;
}
if(hours<10){
	hours="0"+hours;
}
if(minutes<10){
	minutes="0"+minutes;
}
var date=year+'-'+month+'-'+day;
var date_1=stringToDate(date,-1);
//var date_record=year+'年'+month+'月'+day+'日';
//json时间格式转换
function renderTime_1(date){
	var da = new Date(parseInt(date));
	var hours=da.getHours();
	var Minutes=da.getMinutes();
	if (hours<10) {
		hours='0'+hours;
	}
	if (Minutes<10) {
		Minutes='0'+Minutes;
	}
	return (hours)+":"+(Minutes);
}
function renderTime(date){
	var da = new Date(parseInt(date));
	var hours=da.getHours();
	var Minutes=da.getMinutes();
	
	return (da.getFullYear())+"年"+(da.getMonth()+1)+"月"+(da.getDate())+"日";
}
//将日期的天数加一或者减一天
function stringToDate(dateStr,dayNumber) {
	var date_new = new Date(dateStr);
	date_new.setDate(date_new.getDate()+dayNumber);
	var day=date_new.getDate();
	var month=date_new.getMonth()+1;
	if(day<10){
		day="0"+day;
	}
	if(month<10){
		month="0"+month;
	}
	return (date_new.getFullYear()+'-'+month+'-'+day);
}
/*获取页面的查询时间*/
function _getDeviceTime(){
	var val;
	val = $("input[name='date']").eq(0).val();
	if(val!=''){
    	date=val;
    	date_1=stringToDate(val,-1);
    }
}
/*饮食记录控制类*/
var recordControl = {
	dietResultDataLength: 0,
	initialRecord: function(){
		//获取运动列表
		recordControl.getSportWithDate(date,0);
		recordControl.getSportWithDate(date_1,1);
		//获取饮食记录列表
		recordControl.getDietList(date,0);
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(0).html(html);
		}
		recordControl.dietResultDataLength='';
		recordControl.getDietList(date_1,1);
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(1).html(html);
		}
		recordControl.dietResultDataLength='';
		// 点击页面切换
		$('[name="prePage"]').on('click',function(){
			selectPage('prePage');
		});
		$('[name="nextPage"]').on('click',function(){
			selectPage('nextPage');
		});

		suitPage();
	},
	addFood : function(dietTime,dietType,energy,foodName,foodImage,number) {
		this.createFoodItem(dietTime,dietType,energy, foodName,foodImage,number);
	},
	createFoodItem : function(dietTime,dietType,energy,foodName,foodImage,number) {//创建列表节点
//		左边节点
//		var left_contain_div=$('.left_contain').eq(right_);
		var left_contain_div=document.createElement("div");
		left_contain_div.setAttribute("class", "left_contain");
		var div2_1=document.createElement("div");
		div2_1.setAttribute("class", "left_wrap");
		var div3_1=document.createElement("div");
		div3_1.innerText=dietType;
		var div4_1=document.createElement("div");
		div4_1.setAttribute("class", "time");
		div4_1.innerText=dietTime;
		left_contain_div.appendChild(div2_1);
		div2_1.appendChild(div3_1);
		div2_1.appendChild(div4_1);
//		右边节点
//		var foodList = $(".right_contain").eq(right_);
		
		var right_contain_div=document.createElement("div");
		right_contain_div.setAttribute("class", "right_contain");
		var div1=document.createElement("div");
		div1.setAttribute("class", "out_circle");
		var div2=document.createElement("div");
		var div3=document.createElement("div");
		var span1=document.createElement("span");
		span1.setAttribute("class", "energy");
		span1.innerText="能量";
		var span2=document.createElement("span");
		span2.setAttribute("class", "energy_value");
		span2.innerText=energy+"Kcal";
		var div4=document.createElement("div");
		div4.setAttribute("class", "health_msg");
		var div4_text='';
		for (var i = 0; i < (foodName.length-1); i++) {
			div4_text+=foodName[i]+'--';
		}
		div4_text+=foodName[foodName.length-1];
		div4.innerText=div4_text;
		var div5=document.createElement("div");
		div5.setAttribute("class", "health_image_contain");
		var ul1=document.createElement("ul");
		var li1=document.createElement("li");
		for (var i = 0; i < foodImage.length; i++) {
			var img1=document.createElement("img");
			img1.setAttribute("src", foodImage[i]);
			li1.appendChild(img1);
		}
		div1.appendChild(div2);
		div3.appendChild(span1);
		div3.appendChild(span2);
		div5.appendChild(ul1);
		ul1.appendChild(li1);
		
		
		right_contain_div.appendChild(div1);
		right_contain_div.appendChild(div3);
		right_contain_div.appendChild(div4);
		right_contain_div.appendChild(div5);
		
		var outside=document.createElement("div");
		outside.setAttribute("class", "outside");
		outside.appendChild(left_contain_div);
		outside.appendChild(right_contain_div);
		var li=document.createElement("li");
		li.appendChild(outside);
		
		$('.info_list').eq(number).append(li);
		console.log('number:' + number)
		
	},
	getDietList: function(recordDate,number){
		$.ajax({
				async : false,
				cache : false,
				type: 'POST',
				contentType: 'application/x-www-form-urlencoded',
				url: 'recordDietControl.do?selectDietByUserIdWithDate',
				data:{"date": recordDate},
				before: function(){
					layer.load(1);
				},
				success: function(result){
						if (result.success == true) {
							recordControl.dietResultDataLength=result.obj[0].length;
							var dietTime=new Array();
							var dietType=new Array();
							var energy=new Array();
							var foodName=new Array();
							var foodImage=new Array();
							for (var i = 0; i < result.obj.length; i++) {
									dietTime.push(renderTime_1(result.obj[i].dietTime));
									dietType.push(result.obj[i].dietType);
									energy.push(result.obj[i].energy);
									for (var j = 0; j < result.obj[i].details.length; j++) {
										foodName.push(result.obj[i].details[j].name);
										foodImage.push(result.obj[i].details[j].image);
									}
									recordControl.addFood(dietTime,dietType,energy[i],foodName,foodImage,number);
									foodName=[];
									dietTime=[];
									dietType=[];
									foodImage=[];
									suitPage();
								}
						$('.recordTime').eq(0).html(date);
						$('.recordTime').eq(1).html(date_1);
						}
				}
			});
	},
	/** 获取运动 */
	getSportWithDate: function(recordDate,number){
		$.ajax({
				async : false,
				cache : false,
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				url: 'recordDietControl.do?getSportWithDate',
				data:'{"recordDate":"'+recordDate+'"}',
				before: function(){
					layer.load(1);
				},
				success: function(result){
					if(result.success == true){
						var content = '';
						var separateMark = '';
						var sport = '';
						for(var j = 0; j < result.obj.length; j++){
							if(j == 0){
								sport = "运动";
							}
							for (var i = 0; i < result.obj[j].details.length; i++) {
								if(result.obj[j].details.length > 1 && i < result.obj[j].details.length - 1){
									separateMark = '、';	
									}
								content += result.obj[j].details[i].sportName+ '&nbsp;' + result.obj[j].details[i].duration +'分钟'+ separateMark;
								separateMark = '';
							}
							var html = '<li><div class = "outside"><div class = "left_contain"><div class = "left_wrap"><div>'+ sport +'</div>'
								   +'<div class = "time">'+ new Date(result.obj[j].startTime).Format("hh:mm") +'</div></div></div><div class = "right_contain"><div class = "out_circle">'
								   +'<div></div></div><div class = "health_msg">'+ content +'</div><div><span style = "background-color:'
								   +' #48c858;" class = "energy">能量</span><span class = "energy_value">'+ result.obj[j].energy +'kcal</span></div></div></div></li>';
							$('.info_list').eq(number).append(html);
							content = '';
							sport = '';
						}
						
					}
				}
			});
	},
}

//var recordControl.dietResultDataLength;
//获取列表数据

var index=date; 
var index_1=date_1;
function selectPage(page) {
	var index;
	switch (page) {
	case 'prePage':
		date=stringToDate(date, -1);
		date_1=stringToDate(date_1, -1);
		
		$('.info_list').empty();
		//获取运动列表
		recordControl.getSportWithDate(date,0);
		recordControl.getSportWithDate(date_1,1);
		
		recordControl.getDietList(date,0);
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(0).html(html);
		}
		recordControl.dietResultDataLength='';
		recordControl.getDietList(date_1,1);	
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(1).html(html);
		}
		recordControl.dietResultDataLength='';
		break;
	case 'nextPage':
		date=stringToDate(date, 1);
		date_1=stringToDate(date_1, 1);
		$('.info_list').empty();
		//获取运动列表
		recordControl.getSportWithDate(date,0);
		recordControl.getSportWithDate(date_1,1);
		
		recordControl.getDietList(date,0);
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(0).html(html);
		}
		recordControl.dietResultDataLength='';
		recordControl.getDietList(date_1,1);	
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(1).html(html);
		}
		recordControl.dietResultDataLength='';
		break;

	default:
		break;
	}
	suitPage();
}

/*适配页面*/
function suitPage(){
	 if($("body div").hasClass("container")){
    	 $("#menu").css("min-height",$(".container").height()+71+"px");
    	 $(".webPage").css("min-height",$("#menu").height()+62+"px");
    }else{
    	 var h = $("#contextIframe").height()+82;
    	 $("#menu").css("min-height",h+"px");
    	 $(".webPage").css("min-height",h+62+"px");
    }
}
function selectDeviceTime() {
//	点击事件选择器
		$('.info_list').empty();
		_getDeviceTime();
		//获取运动列表
		recordControl.getSportWithDate(date,0);
		recordControl.getSportWithDate(date_1,1);
		
		recordControl.getDietList(date,0);
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(0).html(html);
		}
		recordControl.dietResultDataLength=0;
		recordControl.getDietList(date_1,1);	
		if (recordControl.dietResultDataLength<1) {
			var html='<li ><div><p class="remain" style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;饮食暂未上传数据</p></div></li>';
			$('.info_list').eq(1).html(html);
		}
		recordControl.dietResultDataLength=0;
}
$(function(){
	//初始化记录列表
	recordControl.initialRecord();
});