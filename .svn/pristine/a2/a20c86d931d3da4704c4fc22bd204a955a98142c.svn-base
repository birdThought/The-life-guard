<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
 <style>
 	#orderCenterPopup {padding-left:72px}#orderCenterPopup input {border:none;border-bottom:1px solid #cee;width:346px}
 	#orderCenterPopup textarea {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top}
 	#orderCenterPopup section:nth-of-type(2){margin-left:0}#orderCenterPopup span {width:74px;display:inline-block;text-align:right;}
 	.finsh{margin-top:34px;}
 </style>
<div class="orderCenter" ng-controller="customerOrderController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow" style="padding-bottom: 20px;">
    <a  style="padding: 4px 10px;background:#3cbaff;text-align: center;color: #fff;" href="#!/combo/member/worklist/finish">
    	预约记录</a></div>
    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>用户名</td>
                <td>性别</td>
                <td>手机号码</td>
                <td>服务名称</td>
                <td>预约时间</td>
                <td>备注</td>
                <td>使用状态</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="o in customerorders">
                	<td>{{$index+1}}</td>
                    <td>{{o.userName}}</td>
                    <td>{{o.gender | gender}}</td>
                    <td>{{o.mobile}}</td>
                    <td>{{o.serveName}}</td>
                    <td>{{o.appoinDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{o.userRemark}}</td>
<!--                		<td ng-class="o.status == 3 ? 'red' : 'color_10bb71'"></td> -->
               		<td><shiro:hasPermission name="combo_member_worklist:finish"><span class="ny" ng-click="popupApplyDialog(o)">提交</span>
                        <span class="ny" ng-if="o.serveName == '家政'? true:false" ng-click="popupApplyDialog1()">下单</span></shiro:hasPermission>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section style="margin-top: 15px;">
            <span class="#999">
                机构名称：
            </span>
            <input class="doctor-sign" placeholder="请输入机构名称" ng-model="finsh.orgName">
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
                约定时间：
            </span>
            <input id="promiseDate" class="doctor-sign" placeholder="请输入约定时间" ng-model="finsh.sureDate">
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
                地点：
            </span>
            <input class="doctor-sign" placeholder="请输入机构地点" ng-model="finsh.address">
        </section>
        
        <section class="finsh">
            <span>
                备注：
            </span>
            <textarea id="Textarea" cols="30" rows="10" placeholder="请输入备注" ng-model="finsh.customerRemark"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>

    <div id="orderCenterPopup1" class="orderPopup">
        <section class="clearfix">
            <select style="width: 130px">
                <option value>广东省</option>
            </select>
            <select style="width: 130px" name="city">
                <option value>广州市</option>
            </select>
            <select style="width: 130px" name="area" ng-model="switch.workdistrict">
                <option value>地区</option>
                <option value="天河区">天河区</option>
                <option value="越秀区">越秀区</option>
                <option value="海珠区">海珠区</option>
                <option value="番禺区">番禺区</option>
                <option value="白云区">白云区</option>
                <option value="荔湾区">荔湾区</option>
                <option value="花都区">花都区</option>
                <option value="黄埔区">黄埔区</option>
                <option value="南沙区">南沙区</option>
                <option value="增城区">增城区</option>
                <option value="从化区">从化区</option>
            </select>
        </section>
        <div style="margin-top: 15px;">
            <span class="#999">
                客户姓名：
            </span>
            <input class="doctor-sign" placeholder="请输入客户姓名" ng-model="switch.customername"><br/>
            <span class="#999">
                联系方式：
            </span>
            <input class="doctor-sign" placeholder="请输入联系方式" ng-model="switch.customermobile"><br/>
            <span>
                详细地址：
                <input class="doctor-sign" placeholder="请输入详细地址" ng-model="switch.workaddress"><br/>
            </span>
        </div>
        <div style="margin-top: 15px;">
            <span class="#999">
                服务时间：
            </span>
            <input id="promiseDate1" class="doctor-sign" placeholder="请选择服务时间" ng-model="switch.yonggongshijian">
        </div>
        <div style="margin-top: 15px;">
            <span class="#999">
                有效时间：
            </span>
            <input id="promiseDate2" class="doctor-sign" placeholder="请选择有效时间" ng-model="switch.youxiqi">
        </div>

        <div style="margin-top: 15px;">
                <select style="width: 130px" ng-model="switch.gender">
                    <option value>性别</option>
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>
                <select style="width: 130px" ng-model="switch.language">
                    <option value>语言</option>
                    <option value="0">普通话</option>
                    <option value="1">粤语</option>
                </select>
            <select style="width: 130px" ng-model="switch.age">
                <option value>年龄</option>
                <option ng-repeat="i in arr" ng-value="i">{{i}}</option>
            </select>
        </div>
        <section style="margin-top: 15px;">
            <span class="#999">
                服务内容：
            </span>
            <label>
            <input type="checkbox" ng-model="switch.clean" class="magic-checkbox check-all">
                <label style="font-weight: bold;">家政</label>
            <input type="checkbox" class="magic-checkbox check-all"ng-model="switch.home">
                <label style="font-weight: bold;">上门护理</label>
            </label>
        </section>
        <section class="finsh">
            <select style="width: 130px" ng-model="switch.area">
                <option value>家庭面积</option>
                <option ng-repeat="i in arr" ng-value="i">{{i}}m²</option>
            </select>
            <select style="width: 130px" ng-model="switch.pulation">
                <option value>家庭人口</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
            </select>
        </section>
        <section>
            家政  -- 上门清洁  一次三小时<br/>
            上门护理  -- 80 一天
        </section>
        <div class="finsh">
            <span>
                其他需求：
            </span>
            <input class="doctor-sign" placeholder="请输入其他需求" ng-model="switch.details"><br/>
        </div>
        <section style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button"
                   value="提交数据" ng-click="submit()">
        </section>
    </div>
</div>


