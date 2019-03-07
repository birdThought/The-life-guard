<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<title>添加成员</title>
    <title>用户主页</title>
	<t:base type="jquery,layer"></t:base>
	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
	<link rel="stylesheet" type="text/css" href="/static/css/familyInfor.css">
  </head>
  <body>
	<div class="container">
		<div class="family_member">
			<h3>家庭成员</h3>
			<div class="search">
				<input type="text" placeholder="请输入已注册的手机号码">
				<input type="button" value="搜索">
			</div>
		</div>
		<ul class="nav_tab">
		    <li><span>返回</span></li>
		    <li><span>切换</span></li>
		    <li><span>退出</span></li>
		</ul>
		<ol class="masterInfor">
			<li>
				<img src="images/picture.png" alt="" width="90" height="90">
				<div>
					<strong>程小浩</strong>
					<span>35岁</span><br/>
					<small>13655654828</small>
				</div>
			</li>
		    <li>
		    	<em>性别</em>
		    	<p>男</p>
		    </li>
		    <li>
		    	<em>生日</em>
				<p>1991-08-18</p>
			</li>
		    <li>
		    	<em>身高</em>
				<p>175CM</p>
		    </li>
		    <li>
		    	<em>体重</em>
				<p>60KG</p>
		    </li>
		    <li>
		    	<em>臀围</em>
				<p>60CM</p>
		    </li>
		    <li>
		    	<em>腰围</em>
				<p>60CM</p>
		    </li>
		    <li>
		    	<em>胸围</em>
				<p>90CM</p>
		    </li>
		</ol>
	</div>
</body>
</html>