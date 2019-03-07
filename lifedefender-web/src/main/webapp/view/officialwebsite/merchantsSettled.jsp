<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<t:base type="jquery2.11"></t:base>
	<meta charset="UTF-8">
	<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/reset.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/headerAndFooter.css">
	<link rel="stylesheet" href="/static/officialwebsite/index/css/style.css">
	<link rel="stylesheet" type="text/css" href="/static/officialwebsite/index/css/banner.css">
	<title>生命守护官网  健康管理系统</title>
</head>
<body>
<%@include file="/context/mainHeader_1.jsp"%>
<section class="item-business-content">
	<div class="banner">
		<img src="/static/images/index/businessSettled.png" alt="">
	</div>
	<div class="moreAdvan">
		<h3 class="banxin">为什么要加入生命守护</h3>
		<div id="advantage">
			<section class="clearfix banxin adv-point">
				<div class="fl">
					<img src="/static/images/index/advan_1.png" alt="">
				</div>
				<dl class="fr">
					<dt class="merchants_title">优势一 ：用户精准</dt>
					<dd>
						<label class="setColor">用户群体：</label>海量健康行业用户,专注慢病管理，合作机构已达上百家</dd>
					<dd>用户分类：慢病康复、健康养生、家庭医生、家庭健康、减肥塑体、癌症康复</dd>
				</dl>
			</section>
			<section class="add_bg adv-point">
				<div class="clearfix banxin">
					<dl class="fl">
						<dt class="merchants_title">优势二 ：超强的技术实力</dt>
						<dd><label class="setColor">取得成绩 ：</label>荣获国家高新技术企业，9项国家技术专利证书。已通过十余款健康设备，可提供SDK支持等</dd>
						<dd>技术实力 ：生命守护通过三年研发，平台功能齐全，从产品的整天体验，慢病数据库的用户等在业界领先</dd>
					</dl>
					<div class="fr adv-point-img adv-point-img-2">
						<img src="/static/images/index/advan_2.png" alt="">
					</div>
				</div>
			</section>
			<section class="clearfix banxin adv-point">
				<div class="fl adv-point-img-3">
					<img src="/static/images/index/advan_3.png" alt="">
				</div>
				<dl class="fr">
					<dt class="merchants_title">优势三 ：专业的团队</dt>
					<dd><label class="setColor">专业团队 ：</label>公司有专门的研发部门，开发人数占公司总人数50%以上，包含软件研发、硬件开发等模块</dd>
					<dd>团队建设 ：公司健全的部门建设，软件开发和市场部门占够公司比例重。技术和销售实力雄厚</dd>
				</dl>
			</section>
			<section class="add_bg merchants-content">
				<ol class="cooper_content banxin">
					<li class="merchants-content-title">
						<h3 class="merchants_title">合作内容</h3>
						<p class="subheading">COOPERATION CONTENT</p>
					</li>
					<li class="merchants-content-main">
						<label class="setColor">设备合作 ：</label>生命守护平台的设备合作
					</li>
					<li class="merchants-content-main">
						<label class="setColor">平台合作 ：</label>进驻平台，为平台用户提供服务
					</li>
					<li class="merchants-content-main">
						<label class="setColor">SDK包支持 ：</label>生命守护SDK包对接客户自有平台
					</li>
				</ol>
			</section>
			<section>
				<div class="banxin">
					<h3 class="merchants_title">合作客户</h3>
					<p class="subheading">COOPERATION CUSTOMER</p>
				</div>
				<ol class="cooper_customer banxin">
					<li>
						<div><img src="/static/images/index/service_1.png" alt=""></div>
						<h4>慢病康复机构</h4>
					</li>
					<li>
						<div><img src="/static/images/index/service_2.png" alt=""></div>
						<h4>健康养生机构</h4>
					</li>
					<li>
						<div><img src="/static/images/index/service_3.png" alt=""></div>
						<h4>家庭医生机构</h4>
					</li>
					<li>
						<div><img src="/static/images/index/service_4.png" alt=""></div>
						<h4>家庭健康机构</h4>
					</li>
					<li>
						<div><img src="/static/images/index/service_5.png" alt=""></div>
						<h4>减肥塑体机构</h4>
					</li>
					<li>
						<div><img src="/static/images/index/service_6.png" alt=""></div>
						<h4>癌症康复机构</h4>
					</li>
				</ol>
			</section>
			<section>
				<div class="banxin">
					<h3 class="merchants_title">联系我们</h3>
					<p class="subheading">CONNECT US</p>
				</div>
				<ol class="cooper_hotLine banxin">
					<li>
						<img src="/static/images/index/contact.png">
						<em>招商热线 : </em>15603051308  邹小姐 </li>
					<li>13500029623  蔡小姐</li>
					<li>QQ咨询：1732745304</li>
				</ol>
			</section>
		</div>
	</div>
</section>
<%@include file="/context/mainFooter_1.jsp"%>
</body>
</html>
<script>
$(".tz-navbar-nav > li:eq(3) a").addClass("on").parent("li").siblings("li").children("a").removeClass("on");
</script>