<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<script src="../../static/plugins/http.js"></script> 
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript" src="/static/js/vue.js"></script>
		<link rel="stylesheet" href="/static/semantic/semantic.css" />
		<link rel="stylesheet" href="/static/css/shop/css/login.css" />
		<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
		<script src="../../static/plugins/layui/v2.1.2/layui.all.js"></script>
		<script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
		
		<title></title>
		<style>
			body {
				margin: 0;
				padding: 0;
			}
		</style>
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/vue.js"></script>
	</head>

	<body>
	<!--头选择项-->
	<ul id="ul" class="header_shop" >
			<li class="header_icon" style="z-index: 100;" ><img @click="backOff()" src="/static/images/shop/img/left_white.png" style="width: 1rem;height: 1rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>登录</label>
			</li>
			<li class="header_icon" style="text-align: right; z-index:-1;"></li>
		</ul>
		<!--end 头选择项-->
		<ul class="header_shop_bg"></ul>
		<form id="block_login" class="block_login" style="z-index:-1;">
		
			<!-- <div  z-index = 20;><img src="/static/images/shop/img/icon_return.png" style="width: 0.6rem;height: 0.8rem;" /></div> -->
			<!--end 标题-->
			
			<div class="login_input_bar field">
				<div class="ui pointing below red basic label" v-if="erroPhone">手机号有误 </div>
				<input class="login_input" placeholder="手机号" @blur="testPhone($event)" />
			</div>

			<div class="login_input_send_bar inline field">
				<input class="login_input" placeholder="验证码" @blur="testCode($event)" />
				<div class="ui left pointing red basic label" v-if="erroPassword">验证码错误 </div>
			</div>
			<!--{{canSend?'发送验证码':'发送成功'}}-->
			<label class="label_send" @click="sendCode" v-if="!this.notFirstSend">发送验证码</label>
			<label class="label_send" @click="sendCode" v-if="this.notFirstSend" :style="!canSend?'pointer-events: none;color:#aee4ab;':'pointer-events:visible;color:#64be5e;'">{{canSend?'再次发送':'发送成功'}}<label v-if="!canSend" style="font-size: 0.8rem;">({{time}})</label></label>
			<div class="block_btn">
				<div class="block_btn_exit" @click="login" :style="canLogin?'pointer-events: visible;background-color: #64be5e;':'pointer-events:none;background-color: #aee4ab;'">登录</div>
			</div>
		</form>

	</body>

	<script>
		//测试的验证码数据
		var testCode = '1234';
		//倒数时间
		var outtime = 60;
		var ul = new Vue({
			el:'#ul',
			methods:{
				backOff : function(){
					history.go(-1);
				}
			}
		});
		var vue = new Vue({
			el: '#block_login',
			data: {
				time: outtime,
				canSend: true,
				canLogin: false,
				notFirstSend: false,
				erroPhone: false,
				erroPassword: false,
				//手机号和后端的验证码
				bean: {
					phone: '',
					code: ''
				}
			},
			mounted : function() {
				layer.msg("您还没登录，请先登录", {offset: 350}); 
				this.canLogin = true;
			},
			methods: {
				//校验手机号和验证码
				testPhone: function(event) {
					if(!(/^1[34578]\d{9}$/.test(event.target.value))) {
						//alert("手机号码有误，请重填");
						this.erroPhone = true;
					} else {
						this.erroPhone = false;
						this.bean.phone = event.target.value;
					}
				},
				testCode: function(event) {								
					this.bean.code = event.target.value;
				},
				//发送验证码
				sendCode: function(event) {
					var that = this;
					//1.手机号无误时和非空时，才能发送验证码
					if(!this.erroPhone&&this.bean.phone!='') {

						//第一次点击时，改变其初始状态
						if(!this.notFirstSend) {
							this.notFirstSend = true;
							that.canSend = false;
							//而且时间倒数
							var reduceTime = setInterval(function() {
								that.time--;
								if(that.time == 0) {
									clearInterval(reduceTime);
									that.canSend = true;
								}
							}, 1000);
						} else {
							//必须是时间为0，才能再次点击时，重新初始化时间，不改变初始状态
							if(that.time == 0) {
								that.time = outtime;
								that.canSend = false;
								//而且时间倒数
								var reduceTime = setInterval(function() {
									that.time--;
									if(that.time == 0) {
										clearInterval(reduceTime);
										that.canSend = true;
									}
								}, 1000);
							}
						}
						//成功点击发送验证码
						//ajax调用,获取验证码数据，成功保存验证码
						
						axios.get('/shop/shopLogin/sendValidCode?mobile='+ this.bean.phone +'&cache=login')
							.then(function(response) {
								if(response.success){								
									layer.msg("验证码已发送,请查收", {offset: [350,85]});
									
								}
							}) 
							.catch(function(error) {
								layer.msg(error.msg, {offset: [350,85]});
							}); 
						
					}

				},
				//登录
				login: function(event) {
					
					var url = '/shop/shopLogin/checkVerifyCode?mobile='+ this.bean.phone +'&type=login&verifyCode='+this.bean.code;
					http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){		            	               		                  
                	   if(result.success){
							layer.msg("登陆成功,正跳转页面", {offset: [350,85]});
							setTimeout(function () {                                              	 
                    			window.location = "/shop/index";
                           	},2000)	
						}else{
							layer.msg(response.Msg, {offset: [350,85]});
						}
		                   
		               
			        });
					
					
					
					
					
				}
			}
		});
	</script>

</html>