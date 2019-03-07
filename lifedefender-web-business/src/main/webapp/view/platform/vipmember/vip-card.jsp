
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/vipmember/vip-card.css?v=1.0.0">
<div id="createCard" ng-controller="vipCardController" ng-init='init()'>
    <div class="createTop">
        <select name="" id="select_change" ng-model="conditions.cardStatus">
            <option selected value="0">未激活服务卡号</option>
            <option value="1">已激活服务卡号</option>
        </select>
        <s class="option-icon" <%--onclick="javascript:('$(\'#select_change\').click()')"--%>></s>
        <p class="batchCreate" ng-click="popupApplyVipCardDialog()">创建卡号</p>
    </div>
    <div class="createContent">
        <div class="cardTop clearfix" style="float: right; margin-top: -70px;">
            <button style="margin-right: 30px; cursor: pointer" ng-click="search()">搜索</button>
            <input type="text" id="acquire" placeholder="请输入服务卡号" value ng-model="conditions.keyword">
        </div>
        <div class="cardContent">
            <div class="activated"  style="display: block;">
                <table id="serviceList" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <td>序号</td>
                        <td>服务卡号</td>
                        <td>服务套餐</td>
                        <td>价格</td>
                        <td>服务卡有效期</td>
                        <td>激活状态</td>
                        <td ng-if="conditions.cardStatus == 1">会员账号</td>
                        <%--<td>渠道商</td>--%>
                    </tr>
                    </thead>
                    <tfooter>
                        <tr ng-repeat="(index, c) in cards">
                            <td>{{index + 1}}</td>
                            <td>{{c.code}}</td>
                            <td>{{c.vipComboPO.name}}</td>
                            <td>{{c.price}}元</td>
                            <td>{{c.vipComboPO.validDay}}天</td>
                            <td>{{c.status | vipCardStatus}}</td>
                            <td ng-if="conditions.cardStatus == 1">{{c.userPO.userName}}</td>
                            <%--<td>{{c.businessName}}</td>--%>
                        </tr>
                    </tfooter>
                </table>
                <div id="page" style="text-align: center; margin-top: 30px;"></div>
            </div>
            <div class="inactive" style="display: none;">
                <table id="newservicelist" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <td>序号</td>
                        <td>服务卡号</td>
                        <td>服务套餐</td>
                        <td>价格</td>
                        <td>服务卡有效期</td>
                        <td>激活状态</td>
                        <td ng-if="conditions.cardStatus == 1">会员账号</td>
                        <td>渠道商</td>
                    </tr>
                    </thead>
                    <tbody>
                    <%--<tr ng-repeat="(index, c) in cards">
                        <td>{{index + 1}}</td>
                        <td>{{c.code}}</td>
                        <td>{{c.vipComboPo.name}}</td>
                        <td>{{c.vipComboPo.validDay}}</td>
                        <td>{{c.price}}</td>
                        <td>{{c.status}}</td>
                        <td ng-if="conditions.cardStatus == 1">{{c.userPO.realName || c.userPO.userName}}</td>
                        <td>{{c}}</td>
                    </tr>--%>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <div id="serviceCardPopup" style="display: none;">
        <ul>
            <li class="pretty circle success" ng-repeat="c in vipCombos">
                <input type="radio" name="selectSame" ng-value="c.id" ng-model="selectVipComboId">
                <label>
                    <i class="default"></i>
                    <span>{{c.name}}</span>
                </label>
                <span>{{c.price | fenToYuan}}/{{c.validDay | comboUnit}}</span>
            </li>
        </ul>
        <%--<ol class="clearfix">
            <span>数量：</span>
            <li  class="minueNumber" ng-click="applyAmount > 0 ? (applyAmount = applyAmount - 1) : 0">
                <img style="width: 15px;" src="/static/images/sub-icon.png">
            </li>
            <li class="Number"><strong>{{applyAmount}}</strong></li>
            <li class="addNumber" ng-click="applyAmount = applyAmount + 1">
                <img style="width: 15px;" src="/static/images/add-icon.png">
            </li>
        </ol>--%>

        <button class="createBtn" ng-click="applyVipCard()">立即创建</button>
    </div>
    <div id="CardNumberPopup" style="display: none;">
        <ul class="serviceCardList clearfix">

        </ul>
        <button class="returnBtn">返回</button>
    </div>
</div>













