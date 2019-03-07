<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.1">
<div class="orderCenter" ng-controller="reportAnalysisController" ng-init="init()" style="">
    <div class="orderCenterTop clearfix">
        <ol class="titleShow clearfix">
            <li class="orderDate">
                 <span class="small-tip">订单日期</span>
                <select class="Select-Options" ng-model="conditions.orderDate">
                    <option ng-repeat="m in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]" ng-value="m">{{m}}月</option>
                </select>
            </li>
            <li class="orderStatus" style="margin-left:50px;">
               <span class="small-tip"> 订单状态 </span>
                <select class="Select-Options" name="" id="" ng-model="conditions.orderStatus">
                    <option value="">全部</option>
                    <option value="3">未处理</option>
                    <option value="4">已处理</option>
                </select>
            </li>
            <li class="search clearfix">
                <input type="text" placeholder="请输入姓名/渠道商" style="color: #9a9a9a;" ng-model="conditions.keyword" ng-keypress="enterSearch($event)">
                <span ng-click="search()"><i></i>搜索</span>
            </li>
        </ol>
    </div>
    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>姓名</td>
                <td>手机号码</td>
                <td>订单号</td>
                <td>订单日期</td>
                <td>渠道商来源</td>
                <td>订单状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <fbody>
                <tr ng-repeat="o in orders">
                    <td>{{o.userPO.realName || o.userPO.userName}}</td>
                    <td>{{o.userPO.mobile}}</td>
                    <td>{{o.orderNumber}}</td>
                    <td>{{o.createDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{o.businessName}}</td>
                    <td ng-class="o.status == 3 ? 'red' : 'color_10bb71'">{{o.status == 3 ? '未处理' : '已处理'}}</td>
                    <td><span class="ny" ng-click="popupApplyDialog(o)">{{o.reportAnalysisPO.healthProduct | orderProduct }}</span></td>
                </tr>
            </fbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <div id="orderCenterPopup" class="orderPopup " style="display: none">
        <section><p ng-class="currentOrder.reportAnalysisPO.status == 0 ? '' : 'unusual'">{{currentOrder.createDate | date:'yyyy-MM-dd'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>比重: {{currentOrder.reportAnalysisPO.content.SG}}</li>
                <li>白细胞：{{currentOrder.reportAnalysisPO.content.LEU}}</li>
                <li>尿胆原：{{currentOrder.reportAnalysisPO.content.UBG}}</li>
                <li>胆红素：{{currentOrder.reportAnalysisPO.content.BIL}}</li>
            </ul>
            <ul>
                <li>PH值: {{currentOrder.reportAnalysisPO.content.PH}}</li>
                <li>蛋白质：{{currentOrder.reportAnalysisPO.content.pRO}}</li>
                <li>潜血：{{currentOrder.reportAnalysisPO.content.BLD}}</li>
                <li>葡萄糖：{{currentOrder.reportAnalysisPO.content.GLU}}</li>
            </ul>
            <ul>
                <li>酮体: {{currentOrder.reportAnalysisPO.content.KET}}</li>
                <li>亚硝酸盐：{{currentOrder.reportAnalysisPO.content.NIT}}</li>
                <li>VC维生素C：{{currentOrder.reportAnalysisPO.content.VC}}</li>
            </ul>
        </section>
        <section style="margin-top: 15px;">
            <span class="color_deepskyblue">
                医生：
            </span>
            <input class="doctor-sign" placeholder="请输入医生姓名" ng-model="reply.doctorSign">
        </section>
        <section class="reply">
            <span>
                分析：
            </span>
            <textarea id="Textarea" cols="30" rows="10" placeholder="请填写回复内容" ng-model="reply.content"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>

    <div id="orderCenterPopup1" class="orderPopup " style="display: none">
        <section><p ng-class="currentOrder.reportAnalysisPO.status == 0 ? '' : 'unusual'">{{currentOrder.createDate  | date:'yyyy-MM-dd'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>高密度脂蛋白胆固醇：{{currentOrder.reportAnalysisPO.content.HDL}}</li>
                <li>低密度脂蛋白胆固醇：{{currentOrder.reportAnalysisPO.content.LDL}}</li>
                <li>甘油三酯：{{currentOrder.reportAnalysisPO.content.TG}}</li>
                <li>总胆固醇：{{currentOrder.reportAnalysisPO.content.TC}}</li>
            </ul>
        </section>
        <section style="margin-top: 15px;">
            <span class="color_deepskyblue">
                医生：
            </span>
            <input class="doctor-sign" placeholder="请输入医生姓名" ng-model="reply.doctorSign">
        </section>
        <section class="reply">
            <span>
                分析：
            </span>
            <textarea id="Textarea1" cols="30" rows="10" placeholder="请填写回复内容" ng-model="reply.content"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>

    <div id="orderCenterPopup2" class="orderPopup " style="display: none">
        <section><p ng-class="currentOrder.reportAnalysisPO.status == 0 ? '' : 'unusual'">{{currentOrder.createDate | date:'yyyy-MM-dd'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>尿酸:{{currentOrder.reportAnalysisPO.content.UA}}</li>
            </ul>
        </section>
        <section style="margin-top: 15px;">
            <span class="color_deepskyblue">
                医生：
            </span>
            <input class="doctor-sign" placeholder="请输入医生姓名" ng-model="reply.doctorSign">
        </section>
        <section class="reply">
            <span>
                分析：
            </span>
            <textarea id="Textarea2" cols="25" rows="10" placeholder="请填写回复内容" ng-model="reply.content"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>
    <div id="orderCenterPopup3" class="orderPopup " style="display: none">
        <section><p ng-class="currentOrder.reportAnalysisPO.status == 0 ? '' : 'unusual'">{{currentOrder.createDate | date:'yyyy-MM-dd'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>心电:{{currentOrder.reportAnalysisPO.content.HEARRATE}}</li>
                <li>心电图:<img src="{{currentOrder.reportAnalysisPO.content.IMAGE}}"></li>

            </ul>
        </section>
        <section style="margin-top: 15px;">
            <span class="color_deepskyblue">
                医生：
            </span>
            <input class="doctor-sign" placeholder="请输入医生姓名" ng-model="reply.doctorSign">
        </section>
        <section class="reply">
            <span>
                分析：
            </span>
            <textarea id="Textarea3" cols="25" rows="10" placeholder="请填写回复内容" ng-model="reply.content"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>
    <div id="orderCenterPopup4" class="orderPopup " style="display: none">
        <section><p ng-class="currentOrder.reportAnalysisPO.status == 0 ? '' : 'unusual'">{{currentOrder.createDate | date:'yyyy-MM-dd'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>血糖:{{currentOrder.reportAnalysisPO.content.BLOODSUGAR}}</li>
                <li>血糖正常范围值:{{currentOrder.reportAnalysisPO.content.BLOODSUGARAREA}}</li>
                <li></li>
            </ul>
        </section>
        <section style="margin-top: 15px;">
            <span class="color_deepskyblue">
                医生：
            </span>
            <input class="doctor-sign" placeholder="请输入医生姓名" ng-model="reply.doctorSign">
        </section>
        <section class="reply">
            <span>
                分析：
            </span>
            <textarea id="Textarea4" cols="25" rows="10" placeholder="请填写回复内容" ng-model="reply.content"></textarea>
        </section>

        <section style="text-align: center; margin-top: 15px;">
            <input type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>
</div>
<%--<script src="/static/js/reportanalysis/reportanalysis.js"></script>--%>
