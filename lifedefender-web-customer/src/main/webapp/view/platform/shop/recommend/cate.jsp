<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<style>
	[back-color='true']{background-color:#C7DEDB;}
	.checked-clor{background-color:#5AA4D3;}
	button.move{text-align:center;width:50px;height:30px;}
	button[name='move-save']{text-align:center;width:80px;height:30px;margin-left:10px;background-color:#A52C86;}
	button[name='move-save'][hov='false']:hover{background-color:#E38ECD;}
	button[name='up']{background-color:#5678DF;}
	button[name='up'][hov='false']:hover{background-color:#8DB9D7;}
	button[name='down']{background-color:#BFAE8F;}
	button[name='down'][hov='false']:hover{background-color:#E1D6BC;}
</style>
<div class="orderCenter" ng-controller="recommendController" ng-init="init('cate')" style="height: auto;
	    background: #fff;
	    padding: 20px;margin-top:10px;border: 1px solid #ddd;">
    <div class="header">
        <label style="border-left:2px solid #0093ff;font-size: 17px;color: #0093ff;padding: 0px 15px;" class="action">
           	 首页类目推荐配置
        </label>
    </div>
    
    <div class="button-line">
    	<button class="move" ng-click="cate.up()" title="上移" name="up" ng-disabled="cate.checkIndex <= 0" hov="{{cate.checkIndex <= 0}}">
        	上移
       	</button>
       	<button class="move" ng-click="cate.down()" title="下移" name="down" 
       			ng-disabled="cate.checkIndex < 0 || cate.checkIndex == (cate.allList.length - 1)" 
       			hov="{{cate.checkIndex < 0 || cate.checkIndex == (cate.allList.length - 1)}}">
        	下移
       	</button>
       	<button name="move-save" ng-click="cate.saveToServer()" ng-disabled="!cate.move" hov="{{!cate.move}}">
						<i class="layui-icon">&#xe605;</i>&nbsp;保存
		</button>
   </div>
    
    <div class="orderCenterBottom" style="width: 70%;">
    	<table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
            	<td width="5%"></td>
                <td>类目名称</td>
                <td width="30%">到期时间</td>
                <!-- <td width="20%">操作</td> -->
            </tr>
            </thead>
            <fbody>
                
                <tr ng-repeat="(k,o) in cate.allList">
                	<td ng-click="cate.change(k)" width="5%" ng-class="{true:'checked-clor',false:''}[o.checked]" back-color="{{(k < cate.recommendSize)}}">
                		<input type="checkbox" ng-checked="o.checked" name=""/>
                	</td>
                    <td ng-click="cate.change(k)" back-color="{{(k < cate.recommendSize)}}">{{o.categoryName}}</td>
                    
                    <td class="layui-input-block" back-color="{{(k < cate.recommendSize)}}">
                    	<input type="text" class="layui-input" value="{{o.endTime | date:'yyyy-MM-dd hh:mm:ss'}}" 
                    		data-id="{{o.id}}" index="{{k}}" name="datetime" placeholder="记得设置到期时间" readonly/>
                    </td>
                    
                    <!-- <td>
                    	
                    </td> -->
                </tr>
            </fbody>
        </table>
    </div>
</div>