<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span,#orderCenterPopup2 section span  {
		display:inline-block;
		width:90px;
		text-align:center;
        line-height: 36px;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-clock ng-controller="foodKindController" ng-init="init()" style="padding: 16px 0 0 20px;">
     <div class="titleShow">
         <label  style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: inline-block;font-size: 18px;color: #0093ff;"  class="action">
             食物种类
         </label>
         <div style="display: inline-block">
             <button style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none" class="search search-btn cursor-pointer" ng-click = "addDialog()">添加种类</button>
         </div>
     </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>编号</td>
                <td>种类名称</td>
                <td>编辑</td>
                <td>删除</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="f in foodKind">
                	<td>{{$index+1}}</td>
                	<td>{{f.name}}</td>
                    <td><span class="ny" ng-click="EditDialog(f)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
  					<td><span class="ny" ng-click="DeleteDialog(f)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deletefoodKind()"
            >确认</button>
        </div>
 
	<!-- 添加科种类 -->
     <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
          
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                种类名称
            </span>
            <input id="foodKind_name" class="doctor-sign Select-Options" placeholder="请输入种类名称" >
        </section>
       
   
        <section style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addfoodKind()">
        </section>
    </div> 
    
	<!--修改种类 -->
      <div id="orderCenterPopup2" class="orderPopup ">
        <section class="clearfix">

        </section>
        
         <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                种类名称
            </span>
            <input id="foodKind_name" class="doctor-sign Select-Options" placeholder="修改种类名称" ng-model="edit.name">
        </section>
       
   
        <section style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editfoodKind()">
        </section>
    </div>
    
  </div>