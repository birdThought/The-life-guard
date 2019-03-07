<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="topbar wrap"
	style="border:1px solid #ccc;border-top:none;border-bottom:none;border-right:1px solid #ddd;">
	<div class="console-topbar">
		<div style="float:left;width:200px;border-right:1px solid #ddd;">
			<a href="indexControl.do?index" target="_top" class="topbar-logo">
				<img src="/static/images/logo.png" alt="">
			</a>
		</div>
		<div class="topbar-infor topbar-right topbar-clearfix"
			style="display:inline-block;width:995px;float:left;">
			<ul style="float:right">
				<li class="infor-center topbar-left" style="position:relative">
					<img src="/static/images/message.png" height="16" /> <a
					href="javascript:_inforCenter();"> <span>消息中心</span><span
						class="unread" style="background-color:#3598dc;display: none">99+</span>
				</a>
				</li>
				<li class="help topbar-left"><img src="/static/images/hel.png"
					width="16" /> <a href="orgControl.do?orgHelp"> <span>帮助</span>
				</a></li>
				<li class="username topbar-left">
				<a href="orgUserControl.do?userInfo">
					<img src="/static/images/photo.png" class="userhead" /> <span
						id="_userName"></span>
				</a></li>
				<li class="exit topbar-left"><img src="/static/images/exist.png"
					width="16" /> <a href="javascript:quitLogin();"> <span>退出</span>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function() {
	_setOrgUserInfo();
});

function _setOrgUserInfo() {
	var $userName = jQuery("#_userName");
	var $userHead = jQuery("li.username>a>img.userhead");
	
	jQuery.ajax({
		async: true,
		cache: false,
		type: 'GET',
		url: '/orgUserControl.do?orgUser',
		success: function(result){
			if(result.success){
				var obj = result.attributes;
				$userName.text(obj.userName);
				$userHead.attr("src", obj.userHead);
			}
		}
	});
}
</script>