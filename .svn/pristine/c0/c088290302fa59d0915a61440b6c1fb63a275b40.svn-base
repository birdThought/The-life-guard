<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">		
		<title></title>
		<link rel="stylesheet" href="/static/css/shop/css/title_bar.css" />
		<link rel="stylesheet" href="/static/semantic/semantic.css" />
		<!--<link rel="stylesheet" href="css/bootstrap.css" />-->
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript" src="/static/js/address_data.js"></script>
		<script type="text/javascript" src="/static/js/vue.js"></script>
		<script src="../../static/plugins/http.js"></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
		<!-- 引入样式 -->
		<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
		<!-- 引入组件库 -->
		<script src="https://unpkg.com/element-ui/lib/index.js"></script>

		<style>
			body {
				margin: 0;
				padding: 0;
			}
			/*头部*/
			
			.header_shop {
				position: fixed;
				width: 100%;
				list-style-type: none;
				top: 0;
				margin: 0;
				padding: 0;
				height: 3.5rem;
				color: black;
				padding: 1rem 0 1rem 0;
				border-bottom: 1px solid #f4f4f4;
				background-color: white;
			}
			
			.header_shop_bg {
				position: relative;
				height: 1.5rem;
				/*padding: 1rem 0 1rem 0;*/
			}
			
			.header_shop>li {
				float: left;
			}
			
			.header_shop .header_icon {
				width: 20%;
				text-align: left;
			}
			
			.header_shop .header_title {
				width: 60%;
				text-align: center;
			}
			select{
				width:32%;
			}
		</style>

		<style>
			body {
				background-color: #f5f5f5;
			}
			
			input {
				border: 0;
				background-color: transparent;
			}
			/*内容块*/
			
			.div_content {
				margin-top: 0.5rem;
				background-color: white;
			}
			/*选项条*/
			
			.drawback_bar_img {
				display: flex;
				justify-content: space-between;
				padding: 1rem 1rem 1rem 1rem;
				border-bottom: 1px solid #e6e6e6;
			}
			
			.drawback_bar_img>div {
				display: flex;
				align-items: center;
			}
			
			.drawback_bar {
				border-bottom: 1px solid #f8f8f8;
			}
			/*提示*/
			
			.drawback_tip {
				color: red;
				padding: 1rem 1rem 1rem 1rem;
			}
			/*确定提交*/
			
			.btn_submit {
				position: fixed;
				width: 100%;
				bottom: 0;
				font-size: 1.2rem;
				padding: 1.5rem 0 1.5rem 0;
				text-align: center;
				color: white;
				background-color: #44c05e;
			}
		</style>
	</head>

	<body>
		<div id="address">
		
			<!--头选择项-->
			<ul class="header_shop" style="z-index: 1000;">
				<li class="header_icon" @click="backOff()"><img src="/static/images/shop/img/icon_return.png" style="width: 0.7rem;height: 1.2rem;margin-left: 1rem;" /></li>
				<li class="header_title" style="height: 1.2rem;">
					<label>{{isEidt?'编辑地址':'添加新地址'}}</label>
				</li>
				<li class="header_icon" style="text-align: right;z-index: 100;" @click="clickSave($event)">{{!isSave?'保存':'编辑'}}&nbsp;&nbsp;</li>
			</ul>
			
			<!--end 头选择项-->
			<ul class="header_shop_bg"></ul>

			<div class="div_content">			
				<!--联系人-->
				<div class="drawback_bar_img drawback_bar">
					<div>联系人&nbsp;&nbsp;&nbsp;&nbsp;<input placeholder="请输入联系人" v-model="user.receiverName" :disabled="disabled" /></div>
				</div>
				<!--end 联系人-->

				<!--联系电话-->
				<div class="drawback_bar_img">
					<div>联系电话&nbsp;&nbsp;&nbsp;&nbsp;<input placeholder="请填写联系电话" v-model="user.contactNumber" :disabled="disabled" /></div>
					<div><img src="/static/images/shopimg/address_add.png" style="width: 1.2rem;height: 1.2rem;" /></div>
				</div>
				<!--end 联系电话-->

				<!--处理方式-->
				<div class="drawback_bar_img">
					<div>所在区域&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div id="distpicker2" data-toggle="distpicker" style="display: flex;width: 20rem;" >
						<select @change="clickPro($event)" v-model="user.pro"></select>
						<select @change="clickCity($event)" v-model="user.city"></select>
						<select @change="clickArea($event)" v-model="user.area"></select>
					</div>
				</div>
				<!--end 处理方式-->
				<!--联系电话-->
				<div class="drawback_bar_img">
					<div>详细地址&nbsp;&nbsp;&nbsp;&nbsp;<input placeholder="请填写详细地址" v-model="user.street" :disabled="disabled" /></div>
				</div>
				<!--end 联系电话-->
			</div>
			<!--联系电话-->
			<!-- <div class="drawback_bar_img" style="margin-top: 1rem;background-color: white;" v-if="!isEidt">
				<div>设为默认&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="inline field">
					<div class="ui toggle checkbox">
						<input type="checkbox">
						<label></label>
					</div>
				</div>
			</div> -->
			<!--end 联系电话-->
			<!--删除-->
			<!-- <div class="drawback_bar_img" style="margin-top: 1rem;background-color: white;" v-if="isEidt">
				<div style="color: red;" @click="clickDelete($event)">删除</div>
			</div> -->
			<!--end 删除-->
		</div>
		<script type="text/javascript" src="/static/semantic/semantic.js"></script>

		<script src="https://unpkg.com/element-ui/lib/index.js"></script>
		<script src="/static/js/distpicker.data.js"></script>
		<script src="/static/js/distpicker.js"></script>
		<script src="/static/js/main.js"></script>		
		<script>
			//注：数据传入
			//1.是新建地址，还是编辑地址isEidt
			//2.用户数据
			var address = new Vue({
				el: '#address',
				data: {
					//区别是删除编辑还是添加新地址
					isEidt: ${judge},
					//1.编辑时，要传入用户数据,
					//2.添加新地址时，用户数据为空

					//是否保存，保存时，数据不可修改
					isSave: false,
					disabled: false,
					//用户数据
					user: {
						id:'${address.id}',
						receiverName: '${address.receiverName}',
						contactNumber: '${address.contactNumber}',
						pro: '',
						city: '',
						area: '',						
						address:'${address.address}',
						street:'${address.street}'
					}
					
				},
				watch: {
					 '$data.user.pro': function(newVal,oldVal){
			              address.$data.user.address = address.$data.user.pro + address.$data.user.city + address.$data.user.area
			          },
			          '$data.user.city': function(newVal,oldVal){
			        	  address.$data.user.address = address.$data.user.pro + address.$data.user.city + address.$data.user.area
			          },
			          '$data.user.area': function(newVal,oldVal){
			        	  address.$data.user.address = address.$data.user.pro + address.$data.user.city + address.$data.user.area
			          }
			          			          										
				},
				
				methods: {
					//点击保存时
					clickSave: function(e) {
						this.isSave = !this.isSave;
						this.disabled = !this.disabled;
						var url = "/shop/addAddress";
						var data = JSON.stringify(address.$data.user);
						http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_2,function(result){		            				                
			                    if(result.success){
			                    	layer.msg("添加地址成功", {offset: 350});
			                    	window.location = "/shop/showAddress";
			                    }
			                
				        }); 
					},
					//删除后要ajax提交数据
					clickDelete: function(e) {
						this.user = null;
					},
					clickPro: function(e) {
						this.pro = e.target.value;
						this.address = this.pro+this.city+this.area;
						console.log(this.address);
					},
					clickCity: function(e) {
						this.city = e.target.value;
						this.address = this.pro+this.city+this.area;
						console.log(this.address);
					},
					clickArea: function(e) {
						this.area = e.target.value;
						this.address = this.pro+this.city+this.area;
						console.log(this.address);
					},
					
					
					backOff :function(){
						history.go(-1);
					}
				}

			});
		</script>
	</body>

</html>