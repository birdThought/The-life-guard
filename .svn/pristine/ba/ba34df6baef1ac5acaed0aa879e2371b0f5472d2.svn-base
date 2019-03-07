<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title></title>
		<script type="text/javascript" src="js/mode.js"></script>
		<script type="text/javascript" src="js/jq.js"></script>
		<style>
			/*å¤´é¨*/
			
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
				/*border-bottom: 1px solid white;*/
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
			body {
				margin: 0;
				padding: 0;
			}
			
			.block_author_describe * {
				/*outline: 1px solid red;*/
				text-align: center;
				background-color: #63be5e;
			}
			
			.block_author_img {}
			
			.block_author_img img {
				width: 10rem;
				height: 10rem;
			}
			/*è´¦å·æè¿°*/
			
			.block_author_describe_bar {
				font-size: 0.9rem;
				text-align: center;
				padding: 0.1rem 1rem 0.1rem 1rem;
			}
			/*ä¸ªäººä¿¡æ¯è¡¨å*/
			
			.block_author_form_bar {
				font-size: 1rem;
				display: flex;
				justify-content: space-between;
				padding: 1rem 1rem 1rem 1rem;
				border-bottom: 1px solid #dddddd;
			}
			
			.block_author_form_bar_left {
				display: flex;
				align-items: center;
			}
			
			.block_author_form_bar_right {
				width: 0.7rem;
				height: 1.2rem;
			}
			
			.block_btn{
				position: fixed;
				bottom: 0.5rem;
				width: 100%;
			}
			.block_btn_exit{
				color: white;
				text-align: center;
				border-radius: 0.5rem;
				padding:1rem 0 1rem 0;
				margin-left: 1rem;
				margin-right: 1rem;
				background-color: #f3ad4d;
			}
		</style>
	</head>

	<body>
		<!--å¤´éæ©é¡¹-->
		<ul class="header_shop">
			<li class="header_icon"><img src="img/left_white.png" style="width: 1rem;height: 1rem;margin-left: 1rem;" /></li>
			<li class="header_title" style="height: 1.2rem;">
				<label>ä¸ªäººä¸­å¿</label>
			</li>
			<li class="header_icon" style="text-align: right;"></li>
		</ul>
		<!--end å¤´éæ©é¡¹-->
		<ul class="header_shop_bg"></ul>

		<div class="block_author_describe">
			<div class="block_author_img">
				<img src="img/author.png" />
			</div>
			<div class="block_author_describe_bar">
				<div>æµç§°</div>
			</div>
			<div class="block_author_describe_bar">
				<div>è´¦å·:</div>
			</div>
		</div>
		${mweb_url }
		<div class="block_author_form">
			<!--ä¸ªäººä¿¡æ¯-->
			<div id="mine_describe" class="block_author_form_bar">
				<div class="block_author_form_bar_left">
					<img src="img/mine_on.png" style="width: 1.2rem;height: 1.2rem;" />
					<div style="margin-left: 1rem;">ä¸ªäººä¿¡æ¯</div>
				</div>
				<img class="block_author_form_bar_right" src="img/right.png" />
			</div>
			<!--end ä¸ªäººä¿¡æ¯-->
			<!--å°åç®¡ç-->
			<div id="address" class="block_author_form_bar">
				<div class="block_author_form_bar_left">
					<img src="img/pos.png" style="width: 1.2rem;height: 1.2rem;" />
					<div style="margin-left: 1rem;">å°åç®¡ç</div>
				</div>
				<img class="block_author_form_bar_right" src="img/right.png" />
			</div>
			<!--end å°åç®¡ç-->
		</div>
		
		
		<div class="block_btn">
			<div class="block_btn_exit">éåºç»å½</div>
		</div>
	</body>
	<script>
		$('#mine_describe').click(function(){
			window.location = 'mine_desribe.html';
		});
		$('#address').click(function(){
			window.location = 'address.html';
		});
	</script>

</html>