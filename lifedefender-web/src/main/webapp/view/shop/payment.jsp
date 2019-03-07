<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript" src="/static/js/vue.js"></script>
		<style>
			body {
				margin: 0;
				padding: 0;
				background-color: white;
				font-size: 1rem;
			}
			/*头部*/
			
			.header_shop {
				position: fixed;
				width: 100%;
				list-style-type: none;
				margin: 0;
				padding: 0;
				top: 0;
				height: 1.5rem;
				color: white;
				padding: 1rem 0 1rem 0;
				border-bottom: 1px solid white;
				background-color: #44c05e;
			}
			
			.header_shop_bg {
				position: relative;
				width: 100%;
				list-style-type: none;
				margin: 0;
				padding: 0;
				height: 1.5rem;
				padding: 1rem 0 1rem 0;
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
		</style>
		<style>
			/*订单头*/
			
			.ticket_header {
				padding: 0.5rem 1rem 0.5rem 1rem;
			}
			/*内容块*/
			
			.ticket_header_content {
				font-size: 1.2rem;
				background-color: white;
			}
			
			.ticket_header_content>div {
				padding: 0.5rem 0 0.5rem 0;
			}
			
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
		<style>
			.payment_title {
				padding: 1rem 0.5rem 1rem 0.5rem;
				display: flex;
				justify-content: space-between;
				font-size:1.3rem;
			}
			
			.border_bottom {
				border-bottom: 1px solid #E5E5E5;
			}
			
			.payment_bar {
				display: flex;
				justify-content: space-between;
				padding: 0.8rem 2rem 0.8rem 1rem;
				border-bottom:1px solid #f1f1f1;
			}
			
			.payment_bar_left {
				display: flex;
				align-items: center;
			}
			
			.payment_bar_title {
				display: flex;
				flex-direction: column;
				/*justify-content: space-between;*/
				font-size: 1.2rem;
			}
			
			.payment_bar img {
				width: 2rem;
				height: 2rem;
				padding: 0 1rem 0 1rem;
			}
			
			.payment_bar_left img{
				width: 2.6rem;
				height: 2.6rem;
			}
		</style>
	</head>

	<body>
		<!--头选择项-->
		<ul class="header_shop">
			<li class="header_icon" onclick="backOff()"><img src="/static/images/shop/img/left_white.png" style="width: 1rem;height: 1rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>支付页面</label>
			</li>
			<li class="header_icon" style="text-align: right;"></li>
		</ul>
		<!--end 头选择项-->
		<ul class="header_shop_bg" style="z-index:-1;"></ul>
		<!-- <div class="payment_title">
			<div>订单类型:</div>
			<div>慢病康复</div>
		</div> -->
		<div style="background-color: #f5f5f5;height: 1rem;width: 100%;"></div>
		<div class="payment_title border_bottom">
			<div>订单编号</div>
			<div style="color:gray;">${order[0].order.orderNo }</div>
		</div>
		<div class="payment_title">
			<div>共需支付</div>
			<div style="color: orangered;">${order[0].order.money}元</div>
		</div>
		<div style="background-color: #f5f5f5;padding:0.2rem 0.5rem 0.2rem 0.5rem;font-size: 1.3rem;">选择支付方式</div>

		<div id="which_type">
			<!--支付宝支付-->
			<div class="payment_bar">
				<div class="payment_bar_left">
					<img src="/static/images/shop/img/aplay.png" />
					<div class="payment_bar_title">
						<div>支付宝支付</div>
						<div>推荐支付宝用户使用</div>
					</div>
				</div>
				
				<div style="display: flex;align-items: center;"><input type="radio" name="pay"  value="2" style="width:1.3rem;height:1.3rem;"/></div>
			</div>
			<!--支付宝支付-->
			<!--微信支付-->
			<div class="payment_bar border_bottom">
				<div class="payment_bar_left">
					<img src="/static/images/shop/img/wechat.png" />
					<div class="payment_bar_title">
						<div>微信支付</div>
						<div>推荐微信用户使用</div>
					</div>
				</div>
				<div style="display: flex;align-items: center;"><input type="radio" name="pay"  value="1" style="width:1.3rem;height:1.3rem;"/></div>
			</div>
			<!--微信支付-->
		</div>

		<div style="position: fixed;bottom: 0;width: 100%;">
			<div style="padding:0 1rem 1rem 1rem;">
				<div onclick="determinePay()" style="padding:1rem 0 1rem 0;background-color: orangered;color: white;text-align: center;border-radius: 0.5rem;">确定支付</div>
			</div>
		</div>

		<script>
			//			var whichType = true;
			//			$('#alipay').change(function() {
			//				whichType = true;
			//				$("#webchat").removeAttr("checked");
			//			});
			//			$('#webchat').change(function() {
			//				whichType = false;
			//				$("#alipay").removeAttr("checked");
			//			});
			var which_type = new Vue({
				el: '#which_type',
				data: {
					which: true
				},
				methods:{
						/* clickItem :function(id) {
							if(id == 'aplay'){
								this.which = true;
							}else if(id == 'webchat'){
								this.which = false;
							}
//							this.which = !this.which;
							console.log(this.which);
						} */
					}
			});
		</script>
		<script type="text/javascript">
			function determinePay(){
				var WIDtotal_fee = ${order[0].order.money};
				var orderNo =  ${order[0].order.orderNo};
				if($('input[name="pay"]:checked').val() == 1){
					
					window.location = "/shop/pay/wxPayH5?WIDtotal_fee="+WIDtotal_fee+"&orderNo="+orderNo;
				}else{
					window.location = "/shop/pay/AliPayH5?WIDtotal_fee="+WIDtotal_fee+"&orderNo="+orderNo;
				}
				
			};
			
			function backOff(){
				history.go(-1);
			}
		
		</script>

	</body>

</html>