<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
 <style>
 	#orderCenterPopup {padding-left:72px}#orderCenterPopup input {border:none;border-bottom:1px solid #cee;width:346px}
 	#orderCenterPopup textarea {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top}
 	#orderCenterPopup section:nth-of-type(2){margin-left:0}#orderCenterPopup span {width:74px;display:inline-block;text-align:right;}
 	.finsh{margin-top:34px;}
 </style>
<div  class="orderCenter" ng-controller="drugsManageController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="layui-row">
        <div class="layui-col-md11" style="float: left">
            <div class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <label class="layui-form-label">药品名称</label>
                    <div class="layui-input-block">
                        <input ng-model="search.productName" type="text" class="layui-input" placeholder="请输入药品名称">
                    </div>
                </div>
                 
                <!-- <div class="layui-input-block" style="margin-left:75px";>
                <label class="layui-form-label">产品属性</label>
                    <select class='Select-Options' style="width: 130px" name="attribute" ng-model="search.productAttribute" ng-options="x.prescriptionName for x in productAttribute">
                        <option>全部</option>
                        <option value="1">新药特药</option>
                        <option value="2">进口药品</option>
                        <option value="3">基本药品</option>
                        <option value="4">麻黄碱类</option>
                        <option value="5">电子监管</option>
                        <option value="6">委托加工</option>
                        <option value="7">外用</option>
                        <option value="8">药品</option>
                        <option value="9">中药保护品种</option>
                        <option value="10">特殊管理药品</option>
                        <option value="11">运动员慎用药品</option>
                    </select>
                </div>  -->
                 <!-- <label class="layui-form-label" style="margin-left:85px;">处方类型</label> 
                   <select style="width: 120px;height: 50px" ng-model="search.prescriptionType">
                        <option value="0">全部</option>
                        <option value="1">其它</option>
                        <option value="2">红OTC</option>
                        <option value="3">绿OTC</option>
                        <option value="4">处方药</option>
                        <option value="5">管制处方药</option>
                        <option value="9">非药品</option>
                    </select> -->
                </div>
            </div>
        <div class="layui-col-md1 layui-col-md-offset-11" style="width:100%;text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="search()">搜索</button>
            <shiro:hasPermission name="drugs:sync"><button class="layui-btn layui-btn-normal" ng-click="synchro()">药品同步</button></shiro:hasPermission>
            
            <!-- <button class="layui-btn layui-btn-normal" ng-click="findOrder()">findOrder()</button>
            <button class="layui-btn layui-btn-normal" ng-click="findOrderList()">findOrderList()</button>
            <button class="layui-btn layui-btn-normal" ng-click="orderPush()">orderPush()</button>
            <button class="layui-btn layui-btn-normal" ng-click="logisticsInfo()">logisticsInfo()</button> -->
            
            
            
        </div>
    </div>
    
<!--     <div class="titleShow" style="padding-bottom: 20px;">
    <a  style="padding: 4px 10px;background:#3cbaff;text-align: center;color: #fff;" ng-click="popupEditDialog('','add')">新增</a></div> -->
    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td style="width:35px;">序号</td>
                <td style="width:100px;">名称</td>
                <td style="width:100px;">产品编码</td>
                <td style="width:100px;">渠道价</td>
                <td style="width:100px;">制造商</td>
                <td style="width:70px;">处方类型</td>
                <td style="width:50px;">库存</td>
                <td style="width:50px;">药品属性</td>
                <td style="width:70px;">商品照片</td>
                <td style="width:250px;">产品详情</td>
                <!-- <td style="width:35px;">编辑</td>
                <td style="width:35px;">删除</td> -->
            </tr>
            </thead>
           
             <tbody>
                <tr ng-repeat="o in drugsList">
                    <td>{{o.id}}</td>
                    <td>{{o.productName}}</td>
                    <td>{{o.productCode}}</td>
                    <td>{{o.channelPrice}}</td>
                    <td>{{o.manufacturer}}</td>
                    <td>{{o.prescriptionType}}</td>
                    <td>{{o.productInventory}}</td>
                    <td>{{o.productAttribute}}</td>
                    <td><img src="{{o.thumbnailUrl}}" /></td>
                    <td>{{o.introduction}}</td>
               		<!-- <td><span class="ny" ng-click="popupEditDialog(o,'edit')"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
                    <td><span class="ny" ng-click="DeleteDialog(o)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td> -->
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <!-- <div id="deleteContent" style="display: none;text-align: center;">
        <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
        <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
            ng-click="deleteItemCombo()">确认</button>
    </div> -->
    <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section style="margin-top: 15px;" ng>
            <span class="#999">
               名称：
            </span>
            <input class="doctor-sign" ng-model="show.productName">
        </section>
        
        <section class="finsh">
            <span>
                产品编码：
            </span>
            <input id="doctor-sign"  ng-model="show.productCode"></input>
        </section>
        
        <section class="finsh">
            <span>
               渠道价：
            </span>
            <input id="doctor-sign"  ng-model="show.channelPrice"></input>
        </section>
        <section class="finsh">
            <span>
                制造商：
            </span>
            <input id="doctor-sign"  ng-model="show.manufacturer"></input>
        </section>
        <section class="finsh">
            <span>
                处方类型：
            </span>
            <input id="doctor-sign"  ng-model="show.prescriptionType"></input>
        </section>
        <section class="finsh">
            <span>
                库存：
            </span>
            <input id="doctor-sign"  ng-model="show.productInventory"></input>
        </section>
        <section class="finsh">
            <span>
              药品属性：
            </span>
            <input id="doctor-sign"  ng-model="show.productAttribute"></input>
        </section>
        <section class="finsh">
            <span>
                商品照片：
            </span>
            <img id="upload_editImg" src="{{show.thumbnailUrl}}" alt="">
        </section>
        <section style="margin-top: 15px;">
            <span class="#999">
               产品详情：
            </span>
             <textarea id="Textarea" cols="30" rows="10" ng-model="show.introduction"></textarea>
        </section>

        <!-- <section style="text-align: center; margin-top: 15px;">
        <input type="hidden" ng-model="edit.type" />
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="button_blue_2"
                   value="提交数据" ng-click="replyOrder()">
        </section> -->
    </div>
</div>

