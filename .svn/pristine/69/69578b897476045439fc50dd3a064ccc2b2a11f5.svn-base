<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head></head>
<body>

	<div id = "content">
	</div>
	<script id="test" type="text/html">  
	{{if admin}}  
    	{{each list as value index}}  
     	   <div>{{index}}:{{value}}</div>  
  	    {{/each}}  
	{{/if}}  
	</script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/artTemplate/template.js"></script>
	<script>
	var data = {  
            list : ["第一个元素","第二个元素"],
            admin : true,
        };  
	var html = template('test',data);  
	document.getElementById("content").innerHTML = html;  
	</script>
</body>
</html>