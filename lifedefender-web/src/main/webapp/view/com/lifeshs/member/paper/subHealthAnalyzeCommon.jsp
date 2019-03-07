<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="item-content-border">
	<div class="comment">
		<h4 style="color:#3db74d;">评论结果:</h4>
		<p style="text-indent: 2em;">您此次的亚健康评估得分: <em v-cloak>{{score}}</em>分</p>
		<div class="progress">
		  <div class="progress-bar progress-bar-info" style="width: 25%">
		    <span class="sr-only">25% Complete (infor)</span>
		  </div>
		  <div v-if = "30 < score" class="progress-bar progress-bar-success" style="width: 25%">
		    <span class="sr-only">25% Complete (success)</span>
		  </div>
		  <div v-if = "60 < score" class="progress-bar progress-bar-warning" style="width: 25%">
		    <span class="sr-only">25% Complete (warning)</span>
		  </div>
		  <div v-if = "80 < score" class="progress-bar progress-bar-danger" style="width: 25%">
		    <span>25% Complete (danger)</span>
		  </div>
		</div>
		<p class="text-center" v-cloak>{{basicDes}}</p>
	</div>
	<div class="comment-content" v-if = "suggestion != null" v-cloak>
		<h4 style="color:#fe933d;">健康小贴士</h4>
		<ul v-for = "(s, index) in suggestion">
		    <li>
		    	{{index + 1}}.{{s}}
		    </li>
		</ul>
	</div>
	<p class="text-center"><button class="btn" @click = "again">重新评估</button></p>
</div>