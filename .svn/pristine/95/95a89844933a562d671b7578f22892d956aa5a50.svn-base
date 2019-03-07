<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
		<title>首页</title>
		<link rel="stylesheet" href="/static/css/bootstrap.css" />
		<link rel="stylesheet" href="/static/css/layui.css" />
    	<link rel="stylesheet" href="/static/css/common.css"/>

		<!-- <script type="text/javascript" src="/static/common/js/common.js"></script> -->
		<script type="text/javascript" src="/static/js/jq.js"></script>
		<script type="text/javascript" src="/static/js/mode.js"></script>
		<script type="text/javascript" src="/static/js/assets/layui/layui.js"></script>

	</head>

	<body>
		<!--固定头部-->
	<div class="header">
		<ul class="home_header">
			<li><img src="/static/platform/v2.2.0/images/logo.png" style="width: 5rem;height: 5rem;" /></li> 
			<li>
				<a href="/indexControl.do?index">返回首页</a>
				<a href="javascript:quitLogin();">退出登录</a>
			</li>
		</ul>
	</div>
		<!--导航栏-->
		<div class="Nav">
			<ul>
				<li>
					<img src="${org.logo}" style="width: 5rem;height: 5rem;" />
				</li>
				<li>
					<p>${org.orgName}</p>
				</li>
			</ul>
		</div>
		<!--主要内容-->
		<div id="box-content">
		<div class="content">
			<ul class="content-left">
				<li class="select">主页</li>
				<li >门店管理</li>
				<li >任务提醒</li>
				<li >员工管理</li>
				<li >会员管理</li>
				<li >财务管理</li>
				<li >门店管理</li>
				<li >门店管理</li>
				<li >门店管理</li>
				<li >门店管理</li>
				<li >门店管理</li>
				<li >门店管理</li>
			</ul>
			<ul class="content-right">
				<li style="display: block;"><img src="/static/images/home_index.png" style="width: 100%;height: 100%;" /></li>
				<li>
					<div class="layui-container">
					    <table id="auth-table" class="layui-table" lay-filter="auth-table">
					    	
					    </table>
					</div>
				</li>
			</ul>
		</div>
			<div class="addSub">
				<ul>
					<li><span>机构id：</span><input class="addOrgId" placeholder="输入机构id"/></li>
					<li><span>手机号：</span><input class="mobile" placeholder="请输入手机号"/></li>
					<li><span>验证码：</span><input class="code" placeholder="输入验证码"/><button class="codeBtn">获取验证码</button></li>
					<li><button class="firm">确认添加</button><button class="cancel">取消</button></li>
				</ul>
			</div>
			<div class="empInfo">
				<div class="closeEmpInfo">
					<span>关闭</span>
				</div>
				<table id="demo" lay-filter="test"></table>
			</div>
		</div>
		<script>
		
			//记录点击下标，hover背景色不变
			var i=0;
			$('.content-left li').hover(function() {
	            $(this).addClass('select')
	        },function(){
	        	if(i!=$(this).index()){
	        		$(this).removeClass('select')
	        	}
	        });
			$('.content-left li').click(function() {
	            i = $(this).index();//下标第一种写法
	            $(this).addClass('select').siblings().removeClass('select');
	            $('.content-right li').eq(i).show().siblings().hide();
	       });
	       
//				       新增
			layui.config({
		        base: '../../../../static/js/module/'
			    }).extend({
			        treetable: 'treetable-lay/treetable'
			    })
				.use(['table', 'treetable'], function () {
		        var $ = layui.jquery;
		        var table = layui.table;
		        var treetable = layui.treetable;
		
		        // 渲染表格
		        layer.load(2);
		        treetable.render({
		            treeColIndex: 1,
		            treeSpid: 68,
		            treeIdName: 'id',
		            treePidName: 'parent',
		            treeDefaultClose:false,
		            elem: '#auth-table',
					url:"http://192.168.0.104/org/manageOrg/list/1",
					where:{
						"orgId":"68",
						"orgName":"广州玫瑰园门店",
						"page":"1"
					},
//					url:"json/data66.json",
		            page: false,
		            cols: [[
		                {type: 'numbers'},
		                {field: 'id', width:150, title: '门店编号'},
		                {field: 'logo', width: 80, height:50,align: 'center',templet: function (d) {
		                	return '<img src="'+d.logo+'"/>'
		                },title:"Logo"},
		                {field: 'orgName', width: 200, title: '门店名称'},
		                {field: 'orgType',width: 150, title: '门店类型'},
		                {field: 'street',width: 256, title: '门店地址'},
		                {templet: '#auth-state', width: 115, align: 'center',templet: function (d) {
		                            return '<span class="layui-badge layui-bg-green add">添加下级门店</span>';
		                        },title: '添加'},
		                {templet: '#auth-state', width: 115, align: 'center',templet: function (d) {
		                            return '<span class="layui-badge layui-bg-add emp">查询员工信息</span>';
		                        },title: '查询'}
		            ]],
		            
		            
		            done: function (res) {
		            	console.log(res)
		                layer.closeAll('loading');
				}
				})
				
				});
				

			
				
		</script>
		<script type="text/javascript">
			var empinfo=function(data){
				console.log(data)
			layui.use('table', function(){
			  var table = layui.table;
			  
			  //第一个实例
			  table.render({
			    elem: '#demo'
			    ,height: 500
//			    ,url: 'http://192.168.0.104/org/manageOrg/list/findOrgUserByOrgId' ,//数据接口
////			    ,url:'json/888.json'
//			    where:{"orgId": addOrgID}
				,data:data
			    ,page: true //开启分页
			    ,response: {
				    dataName: 'obj' //规定数据列表的字段名称，默认：data
				  } 
			    ,cols: [[ //表头
			      {field: 'id', title: 'ID', width:80, sort: true,align:'center'}
			      ,{field: 'realName', title: '真实姓名', width:230,align:'center'}
			      ,{field: 'sex', title: '性别', width:83, sort: true,align:'center'}
			      ,{field: 'mobile', title: '手机', width:200,align:'center'} 
			      ,{field: 'email', title: '邮箱', width: 219,align:'center'}
			      ,{field: 'userType', title: '用户类型', width: 150,align:'center'}
			      ,{field: 'userName', title: '登录名称', width: 230,align:'center'}
			    ]],
			    done:function(res,curr,count){
			    	console.log(res)
			    	console.log(curr)
			    	console.log(count)
			    }
			    
			  });
  
})
			}
			
			function quitLogin(){
				 
				layer.confirm('您确定想退出系统登录吗？', {
			            'offset': '50%',
			            scrollbar: false,
			            btn: ['确定','取消'] //按钮
			        }, function(){
			            _logout();
			        }, function(){
			    });
				
				
			}
			
			function _logout(){
				$.ajax({
			        async : true,
			        cache : false,
			        type : 'POST',
			        url: "/login/logout",
			        data: "",
			        dataType: 'json',
			        beforeSend:function(){
			            layer.load();
			        },			       
			        success: function(result) {
			        	 setTimeout(function() {
			                    window.location.href = '/login';
			                }, 300);
			        }
				})
			}
			
		</script>

	</body>

</html>