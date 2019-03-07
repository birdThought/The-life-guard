
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="createCard"  ng-controller="vipMemberFinancialController" ng-init='init()'>
    <div class="createTop">
        <span></span>
        <select name="" id="select_change" ng-model="date">
            <option ng-repeat="m in month" ng-value="m">{{m}}月份分成结算清单</option>
        </select>
        <s class="option-icon"></s>
    </div>
    <div class="createContent">
        <div class="cardContent">
            <div class="activated"  style="display: block;">
                <table id="settle_accounts" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <td>序号</td>
                        <td>会员姓名</td>
                        <td>消费金额</td>
                        <td>收款方式</td>
                        <td>应付服务码金额</td>
                        <td>分成</td>
                    </tr>
                    </thead>
                    <tfooter>
                        <tr ng-repeat="(index, o) in orders">
                            <td>{{index}}</td>
                            <td>{{o.realName || o.userName}}</td>
                            <td>{{o.price | fenToYuan2}}</td>
                            <td>{{o.type == '1' ? '生命守护平台自收' : '渠道商代收'}}</td>
                            <td ng-class="o.type == '1' ? '' : 'red'">{{o.type == '1' ? '0元' : '-' + (o.price | fenToYuan2)}}</td>
                            <td class="color_deepskyblue">{{'+' + (o.businessIncome | fenToYuan2)}}</td>
                        </tr>
                    </tfooter>
                </table>

                <div id="page" style="text-align: center"></div>
            </div>
        </div>
    </div>

    <div id="shared">
        <p>渠道商获得总分成：<span>+{{bill.income | fenToYuan3}}</span>元</p>
        <p>渠道商支付服务码总金额：<span>-{{bill.payForCard | fenToYuan3}}</span>元</p>
        <p>应结算：<span ng-class="bill.learning >= 0 ? 'color_deepskyblue' : 'red'">
            {{(bill.learning >= 0 ? '+' : '-') + (bill.learning | fenToYuan3)}}
        </span>元</p>
    </div>
</div>