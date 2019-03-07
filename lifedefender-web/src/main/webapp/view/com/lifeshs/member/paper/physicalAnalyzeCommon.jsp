<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="item-content-border">
	<div class="comment">
		<h4 style="color:#3db74d;">评论结果:</h4>
		<p style="text-indent: 2em" v-cloak> 您的体质是{{physicalName}}
			<template v-cloak v-if = "physicalNames != null && physicalNames.length > 1">，兼有
				<template v-for = "(p, index) in physicalNames">
					<template v-if = "p != physicalName">{{p}}
						<span v-if = "index < physicalNames.length - 1">、</span>
					</template>
				</template>等多种体质
			</template>
		</p>
		<div v-cloak id="container" style="width:100%;margin:0 auto;border-bottom: 2px solid #ddd"></div>
	</div>
	<div class="comment-content">
		<h4 style="color:#fe933d;" v-cloak>{{physicalName}}病因</h4>
		<ul v-cloak>
		    <li>
		    	{{cause}}
		    </li>
		</ul>
	</div>
	<div class="comment-content">
		<h4 style="color:#fe933d;" v-cloak>{{physicalName}}症状</h4>
		<ul v-cloak>
		    <li>
		    	{{symptoms}}
		    </li>
		</ul>
	</div>
	<div class="comment-content">
		<h4 style="color:#fe933d;" v-cloak>{{physicalName}}临床表现</h4>
		<ul v-cloak>
		    <li>
		    	{{manifestations}}
		    </li>
		</ul>
	</div>
	<div class="comment-content">
		<h4 style="color:#fe933d;" v-cloak>{{physicalName}}易感疾病</h4>
		<ul v-cloak>
		    <li>
		    	{{susceptibleDisease}}
		    </li>
		</ul>
	</div>
	<div class="comment-content">
		<h4 style="color:#fe933d;" v-cloak>{{physicalName}}建议</h4>
		<ul v-cloak>
		    <li>
		    	{{suggestion}}
		    </li>
		</ul>
	</div>
	<p class="text-center"><button class="btn" @click = 'again' v-cloak>重新评估</button></p>
</div>