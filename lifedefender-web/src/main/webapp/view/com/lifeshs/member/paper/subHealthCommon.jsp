<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="item-quetion">
	<h4 class="item-sub" v-cloak>第{{currentTopicNumber}}题,共{{totalTopic}}道题</h4>
	<form>
		<h4 v-cloak>{{currentTopicNumber}}. {{currentTopicName}}</h4>
		<ul class = "options">
		    <li v-cloak v-for = "(o, index) in questionnaireOptions">
		    	<input type="radio" name="2" :id="checkedCss(index)" @click = "nextTopic(o.score, o.topicType)" class="magic-radio">
		    	<label :for="checkedCss(index)">{{o.name}}</label>
		    </li>
		</ul>
	</form>
	<p class="text-center"><button v-if = "(currentTopicNumber > 1)" @click = "preTopic()" class="btn" v-cloak>上一题</button></p>
</div>