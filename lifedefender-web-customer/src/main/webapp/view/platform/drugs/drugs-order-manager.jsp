
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
 <style>
 	#orderCenterPopup {padding-left:72px}#orderCenterPopup input {border:none;border-bottom:1px solid #cee;width:346px}
 	#orderCenterPopup textarea {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top}
 	#orderCenterPopup section:nth-of-type(2){margin-left:0}#orderCenterPopup span {width:74px;display:inline-block;text-align:right;}
 	.finsh{margin-top:34px;}
 </style>
<div  class="orderCenter" ng-controller="drugsOrderManageController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="layui-row">
        <div class="layui-col-md11" style="float: left">
            <div class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <label class="layui-form-label">订单编号</label>
                    <div class="layui-input-block">
                        <input ng-model="search.orderNo" type="text" class="layui-input" placeholder="请输入订单编号">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md1 layui-col-md-offset-11" style="width:100%;text-align:right;">
            <button class="layui-btn layui-btn-normal" ng-click="search()">搜索</button>
        </div>
    </div>
    
    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td style="width:35px;">序号</td>
                <td style="width:100px;">订单标号编码</td>
                <td style="width:100px;">订单状态</td>
                <td style="width:100px;">支付类型</td>
                <td style="width:70px;">收件人</td>
                <td style="width:50px;">下单时间</td>
                <td style="width:50px;">订单金额</td>
                <td style="width:250px;">订单备注</td>
                <td style="width:100px;">快递单号</td>
                
            </tr>
            </thead>
           
             <tbody>
                <tr ng-repeat="o in drugsOrderList">
                    <td>{{o.id}}</td>
                    <td>{{o.orderNo}}</td>
                    <td>{{o.status}}</td>
                    <td>{{o.paymentType}}</td>
                    <td>{{o.consignee}}</td>
                    <td>{{o.orderTime}}</td>
                    <td>{{o.money}}</td>
                    <td>{{o.orderNotes}}</td>
                    <td>{{o.shippingNo}}</td>
                    
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section style="margin-top: 15px;" ng>
            <span class="#999">
               订单编号：
            </span>
            <label>{{show.orderNo}}</label>
            <input class="doctor-sign" ng-model="">
        </section>
        <section class="finsh">
            <span>
                健客订单编号：
            </span>
            <label>{{show.externalOrderNo}}</label>
        </section>
        
        <section class="finsh">
            <span>
               发票：
            </span>
            <label>{{show.invoice}}</label>
        </section>
        
        <section class="finsh">
            <span>
                订单备注：
            </span>
            <label>{{show.orderNotes}}</label>
        </section>
        
        <section class="finsh">
            <span>
                订单金额：
            </span>
            <label>{{show.money}}</label>
        </section>
        
        <section class="finsh">
            <span>
               下单时间：
            </span>
            <label>{{show.orderTime}}</label>
        </section>
        
        <section class="finsh">
            <span>
              用户id：
            </span>
            <label>{{show.userId}}</label>
        </section>
        
        <section class="finsh">
            <span>
                收件人：
            </span>
            <label>{{show.consignee}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               创建时间：
            </span>
             <label>{{show.createDate}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               医师编号：
            </span>
             <label>{{show.physCode}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               支付类型：
            </span>
             <label>{{show.paymentType}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               订单状态：
            </span>
             <label>{{show.status}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
              快递单号：
            </span>
             <label>{{show.shippingNo}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               付款帐户支付金额：
            </span>
             <label>{{show.payCost}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               付款帐户：
            </span>
             <label>{{show.payAccount}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               收款帐户：
            </span>
             <label>{{show.sellerAccount}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               临床诊断：
            </span>
             <label>{{show.clinicalDiagnosis}}</label>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
               医嘱：
            </span>
             <label>{{show.doctorAdvice}}</label>
        </section>
    </div>
</div>

