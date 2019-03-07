<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>饮食记录</title>
<t:base type="jquery,layer,vue"></t:base>
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" type="text/css"
	href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css" href="/static/css/healthFiles.css">
<script type="text/javascript"
	src="/static/plugins/Highmaps-4.2.5/highcharts.js"></script>
<script type="text/javascript"
	src="/static/plugins/Highmaps-4.2.5/modules/data.js"></script>
<script type="text/javascript"
	src="/static/plugins/Highmaps-4.2.5/modules/exporting.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript"
	src="/static/plugins/echarts-2.2.7/echarts.js"></script>
<!-- <script src="/static/officialwebsite/js/healthFile.js"></script> -->
<script src="/static/com/lifeshs/member/record/recordDiet.js"></script>
<!-- <script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script> -->
<style>
	[v-cloak] {
    	display: none;
	}
</style>
</head>
<body>
	<div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
			<div class="right-wrap">
				<div class="title fl">
					<a href="/login" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a
						href="#" class="top_cite">健康档案</a>&nbsp;&nbsp;>&nbsp;&nbsp;健康日记
				</div>
				<div class="container fr">
					<ul class="recordTitle">
						<li>饮食运动</li>
						<li><span><a
								href="recordDietControl.do?RecordDietFoodEnter">饮食运动记录</a></span></li>
					</ul>
					<div id="dietData"
						style="width: 800px; height: 400px; margin: 0 auto; border-bottom: 2px solid #ddd"></div>
					<div style="margin-top: 20px; text-align: center;">
						<a href="javascript:void(0);" class = "prePage">
						<img src="/static/images/previous.png" height="18" width="8">
						</a> 
						<span class = "selectDate" style="margin: 0 20px;"><!-- 2017-01-03 --></span> 
						<a href="javascript:void(0);" class = "nextPage">
						<img src="/static/images/previous.png" style="transform: rotate(-180deg)" height="18" width="8">
						</a>
					</div>
					<ul class="addDiet">
						<li>
							<div style="line-height: 40px">
								<img alt="" src="/static/images/food.png"> <span>摄入食物</span>
							</div>
							<p>
								<em class = "dietEnergy">0</em>kcal
							</p>
						</li>
						<li>
							<div id="energyChart" style="height: 200px; width: 200px"></div>
						</li>
						<li>
							<div style="line-height: 40px">
								<img alt="" src="/static/images/sports.png"> <span>运动消耗</span>
							</div>
							<p>
								<em class = "sportEnergy">0</em>kcal
							</p>
						</li>
					</ul>
					<div class = 'sportsModel'>
						<div class="addDiet">
							<h3 class="subtitle sportsTitle">运动
							<span v-cloak v-if = "results != ''" @click = "addSports()" class = "sports" style = "cursor:pointer; margin-left: 695px;color: green; margin-top: -41px; ">添加运动</span>
							</h3>
							<div class = "sportsList">
							<div class = "sportsListItem" v-for = "(r,index) in results" style = "margin-top: 30px; margin-bottom: 50px;">
								<div class = "sportsCnt">
									<ul class = "sportsEdit">
										<li class = "content" style = "width: 60%; padding-left: 15px;">
											<span>
												<template v-cloak v-for = "(d, index) in r.details">
													
													{{d.sportName}} {{d.duration}} 分钟         
													<span v-if = "index < r.details.length - 1" >、</span>
												</template>
											</span>
										</li>
										<li class = "operation" style = "width: 40%; padding-right: 40px;">
											<span><a v-cloak @click = "editSport(index)" style = "cursor:pointer">编辑</a></span>
											<span><a v-cloak @click = "deleteSport(index)" style = "cursor:pointer">删除</a></span>
										</li>
									</ul>
									<br/>
									<br/>
								</div>
								<div class = "sportsCnt">
									<ul class = "sportsEdit">
										<li class = "content" style = "width: 50%; padding-left: 15px;">
											<span v-cloak class = "energy">能量</span>
											<span v-cloak>{{r.energy}}kcal</span>
										</li>
										<li class = "operation" style = "width: 50%; padding-right: 40px;">
											<span v-cloak>{{r.startTime | formatDate('hh:mm:ss')}}</span>
										</li>
									</ul>
								</div>
								<hr v-cloak v-if = "index < results.length - 1" style="height:1px;border:none;border-top:1px dashed #0066CC; margin: 30px; margin-top: 85px;">
							</div>
							</div>
							<div v-cloak v-if = "results == ''" @click = "addSports()" class = "addSportsDiv"
								style="position: absolute; left: 50%; top: 40%; transform: translate(-50%);	">
								<button class="sports">添加运动</button>
								<p class="introduce">快来添加运动记录</p>
							</div>
						</div>
						<!-- 运动弹出窗口 -->
						<div class="dialog_container_sport"
						style="display: none; overflow-y: auto; max-height: 500px">
						<div>
							<div class="item_control">
								<label>运动时间</label>
								<select class="select" id="sportTime">
									<option value = "09:00:00">09:00:00</option>
									<option value = "12:00:00">12:00:00</option>
									<option value = "18:00:00">18:00:00</option>
								</select>
							</div>
							
							<div class="item_control">
								<label>运动类别</label> 
								<select name="sportType" id="sportOne" class="select" v-model = "selectedSportKind">
									<option v-for = "k in sportKindList">{{k.name}}</option>
								</select> 
								<select name="sportName" id="sportTwo" class="select"
									style="margin-left: 15px" v-model = "selectedSportName">
									<option v-for = "n in sportList" :value = "n.name">{{n.name}}</option>
								</select> 
								<select id="duration" class="select"
									style="margin-left: 15px" v-model = "selectedDurationTime">
									<option value="100">100分钟</option>
									<option value="80">80分钟</option>
									<option value="60">60分钟</option>
									<option value="40">40分钟</option>
									<option value="20">20分钟</option>

								</select> <span class="control_btn" @click = "editToAddSport()">
									添加运动 </span>
							</div>
							<div>
								<ul id="sport" class="sport">
									<li v-for = "(d, index) in details">
										<div class = 'addSportItem'>
											<span>{{d.sportName}}</span>
											<span>{{d.duration}}分钟</span>
											<a @click = "deleteSportItem(index)" style = "cursor:pointer">删除</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					</div>
					
					<div class = "dietListModel">
					<div class="addDiet">
						<h3 class="subtitle dietTitle">饮食
							<span v-cloak v-if = "results != ''" @click = "addDiet()" style = "cursor:pointer; margin-left: 700px;color: green; margin-top: -41px;">添加饮食</span>
						</h3>
						<div class = "foodList" v-for = "(r, index) in results">
							<li >
								<div class="foodCnt">
									<ul class="foodEdit">
										<li>
											<strong v-cloak>{{r.dietType}}</strong>
										</li>
										<li>
											<span value="'+i+'" :id = "r.id" :kcal = "r.energy">
												<a v-cloak @click = "editDiet(r.details, r.dietTime, r.dietType, r.id)" style="cursor:pointer">编辑</a>
											</span>
											<span :value = "r.id">
												<a v-cloak onclick="removeDiet(this)" style="cursor:pointer">删除</a>
											</span>
										</li>
									</ul>
									<div class="foodList_content">
										<dl>
											<dt>
												<span v-cloak>能量</span>
												<span v-cloak style = "color: gray; background-color: white;">{{r.energy}}kcal</span>
											</dt>
										</dl>
										<dl v-cloak>
											<template v-for = "(n,index) in r.details">
											{{n.name}}&nbsp;{{n.foodWeight}}g
											<span v-if = "index < r.details.length - 1" style = "margin:0px; padding:0px; background-color:white;color: black;">、</span>
											</template>
										</dl>
										<ul class="imgList">
											<li v-cloak>
												<template v-for = "m in r.details">
													<img  :src="m.image" alt="">
												</template>
											</li>
										</ul>
									</div>
							  </div> 
							</li>
							
							<hr v-cloak v-if = "index < results.length - 1" style="height:1px;border:none;border-top:1px dashed #0066CC;"/>
							
						</div>
						<div v-cloak v-if = "results == ''" class = "addDietDiv" style="position: absolute; left: 50%; top: 40%; transform: translate(-50%);">
							<button class="foods" @click = "addDiet()">添加饮食</button>
							<p class="" style = "color: #888;font-size: 16px;line-height: 40px;">快来添加饮食记录</p>
						</div>
						
					
					</div>
					<!-- 食物添加窗口 -->
					<div class="dialog_container"
						style="display: none; overflow-y: auto; max-height: 500px">
						<div>
							<div class="item_control">
								<label>进餐时间</label>
								<select class="select" id="selectTime" name="dietTime"
									onchange="FoodAddObject.selectTime()" v-model="selectedDietTime">
									<option>09:00:00</option>
									<option>12:00:00</option>
									<option>18:00:00</option>
								</select>
							</div>
							<div class="item_control">
								<label>时间段</label> 
								<select class="select" id="selectType" v-model="selectedDietType">
									<option>早餐</option>
									<option>早餐加餐</option>
								</select>
							</div>
							<div class="item_control">
								<label>食物类别</label> 
								<select name="foodType" id="foodOne" class="select" v-model="selectedFoodKind">
									<option v-cloak v-for="k in foodKindList">{{k.name}}</option>
								</select> 
								<select name="foodName" id="foodTwo" class="select"
									style="margin-left: 15px" v-model = "selectedFoodName"><!-- {id: k.ID, image: k.image, kcal: k.KCAL, name: k.NAME} -->
									<option v-cloak v-for="k in foodList" :value = "k.NAME">{{k.NAME}}</option>
								</select> 
								<select id="foodWeight" class="select"
									style="margin-left: 15px" v-model = "selectedWeight">
									<option value="100">100g</option>
									<option value="80">80g</option>
									<option value="60">60g</option>
									<option value="40">40g</option>
									<option value="20">20g</option>

								</select> <span class="control_btn" @click = "addToAddFood()">
									添加食物 </span>
							</div>
							<div>
								<!--食物-->
								<ul id="food" class="food">
									<li v-for = "(d, index) in details">
										<div class = "food_item">
											<span v-cloak>{{d.kind}}</span>
											<span v-cloak value = "" kcal = "">{{d.name}}</span>
											<span v-cloak id = "">{{d.foodWeight}}</span>
											<a @click = "deleteFoodItem(index)">删除</a>
										</div>
									</li>
								</ul>
							</div>
							<!--图片的显示位置-->
							<div class="image_contain">
								<ul id="img_list_add">
									<li v-for = "d in details">
										<img style = "height:90px; width:120px;" :src = "d.image">
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 食物编辑窗口 -->
					<div class="dialog_container_modify"
						style="display: none; overflow-y: auto; max-height: 500px">
						<div>
							<div class="item_control">
								<label>进餐时间</label> <select class="select" disabled="disabled"
									name="dietTime">
									<option v-cloak>{{dietTime}}</option>
								</select>
							</div>
							<div class="item_control">
								<label>时间段</label> 
								<select class="select" disabled="disabled" name="dietType">
									<option v-cloak>{{dietType}}</option>
								</select>
							</div>
								<div class="item_control">
									<label>食物类别</label> 
									<select name="foodType_modify"
										id="foodType_modify" class="select" v-model="selectedFoodKind">
										<option v-cloak v-for="k in foodKindList">{{k.name}}</option>
									</select> 
									<select v-model="selectedFoodName" name="foodName_modify"
										id="foodName_modify" class="select" style="margin-left: 15px">
										<option v-cloak v-for="k in foodList" :value = "k.NAME">{{k.NAME}}</option>
									</select> 
									<select v-model = "selectedWeight" id="foodWeight_modify" class="select"
										style="margin-left: 15px">
										<option value="100">100g</option>
										<option value="80">80g</option>
										<option value="60">60g</option>
										<option value="40">40g</option>
										<option value="20">20g</option>

									</select> 
									<span class="control_btn" @click = "editToAddFood()">
									添加食物 
									</span>
								</div>
								<div>
								<!--食物-->
								<ul id="food_modify" class="food_modify">
									<li v-for = "(d, index) in details">
										<div class = "food_item_modify">
											<span v-cloak>{{d.kind}}</span>
											<span v-cloak value = "" kcal = "">{{d.name}}</span>
											<span v-cloak id = "">{{d.foodWeight}}</span>
											<a @click = "deleteFoodItem(index)">删除</a>
										</div>
									</li>
								</ul>
							</div>
							<!--图片的显示位置-->
							<div class="image_contain">
								<ul id="img_list_modify">
									<li v-for = "d in details">
										<img style = "height:90px; width:120px;" :src = "d.image">
									</li>
								</ul>
							</div>
							<!--上传图片的操作-->
						</div>
					</div>
					
					
					</div>
					
					<div class="addDiet">
						<h3 class="subtitle">能量分析</h3>
						<div class = "energyAnalyze" style="position: absolute; left: 50%; top: 40%; transform: translate(-50%);">
							<ul>
								<li>
									<div style = "width: 400px; margin-top: -40px; transform: translateX(-70%); height: 180px; text-align: left; padding: 20px;">
										<span>日基础代谢:</span>
										<span style = "color: green;"><!-- 400kcal --></span>
										<span style = "margin-left: 50px;">偏差:</span>
										<span style = "color: orange;"><!-- 100kcal --></span>
										<br><br>
										<span style = "color: green;">建议:</span>
										<span><!-- 建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议 --></span>
									</div>
								</li>
								<li>
									<div style = "width: 400px; border-left: 1px dashed #ccc; margin-top: -40px; height: 180px; text-align: left; padding: 20px;">
										<span>日所需能量:</span>
										<span style = "color: green;"><!-- 400kcal --></span>
										<span style = "margin-left: 50px;">偏差:</span>
										<span style = "color: orange;"><!-- 100kcal --></span>
										<br><br>
										<span style = "color: green;">建议:</span>
										<span><!-- 建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议建议 --></span>
									</div>
								</li>
							</ul>
							<p class="introduce" style = "display: none;">您还没有添加饮食运动记录，暂无能量分析</p>
						</div>
					</div>
					
					
			</div>
		</div>
	</div>
	<script>
	$(function(){
		menuSetting({
			parent : 2,
			child : 2
		});
		
	});
	/* jeDate({
		dateCell : "#date",
		format:"hh:mm",
	    isinitVal:true,
	    initAddVal:[0],
	    minDate:"00:00",
	    maxDate: jeDate.now(0),
	    startMin:"00:00",
	    startMax:jeDate.now(0),
	    choosefun:function(elem, val) {
	    	//val为获取到的时间值
	    	// 选择时间后更新饮食记录 
	    }
	}); */
	</script>

</body>
</html>