<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/vipmember/vip-member.css">
<div ng-controller="vipMemberController" ng-init='init()'>
    <%--<div class="content-right-top">
        <p>会员名称：
            <input type="text" placeholder="请输入会员名字" ng-model="conditions.keyword">
            <button class="button_blue" style="margin-left: 50%" ng-click="search()">
                <i class="layui-icon">&#xe615;</i> 查询
            </button>
        </p>
        <form class="WellForm">
            <div class="item">
                <label>性别：</label>
                <select name="sex" class="WellSelect" ng-model="conditions.gender">
                    <option value>不限</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </form>
        <form class="WellForm special" style="width:230px;">
            <div class="item">
                <label>年龄段：</label>
                <select name="year" class="WellSelect" ng-model="conditions.startAge">
                    <option value>不限</option>
                    <option ng-repeat="i in ageArr">{{i}}</option>
                </select><i class="WellForm-i">至</i>
                <select name="year" class="WellSelect" ng-model="conditions.endAge">
                    <option value>不限</option>
                    <option ng-repeat="i in ageArr" ng-value="i">{{i}}</option>
                </select>
            </div>
        </form>
        <form class="radio WellForm">
            <div class="item">
                <label>套餐：</label>
                <select name="year" class="WellSelect" ng-model="conditions.vipComboId">
                    <option value>不限</option>
                    <option ng-repeat="i in vipCombo" ng-value="i.id">{{i.name}}</option>
                </select>
            </div>
        </form>
        <form class="WellForm">
            <div class="item">
                <label>使用状态：</label>
                <select name="year" class="WellSelect" ng-model="conditions.status">
                    <option value='0'>使用中</option>
                    <option value='1'>暂停服务</option>
                </select>
            </div>
        </form>
    </div>
    <div class="content-right-middle">
        <table id="thetable"  padding="10px" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>用户名</td>
                <td>性别</td>
                <td>出生日期</td>
                <td>身高cm/<br>体重kg</td>
                <td>三围mm</td>
                <td>手机号码</td>
                <td>会员套餐</td>
                <td>会员到期时间</td>
                <td>使用状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="x in userList" ng-cloak>
                <td>{{page.pageIndex == 1 ? ($index + 1) * page.pageIndex : ($index + 1) + page.pageSize}}</td>
                <td>{{x.realName || x.userName}}</td>
                <td>{{x.gender | gender}}</td>
                <td>{{x.birthday | date:'yyyy-MM-dd'}}</td>
                <td>{{x.height}}</td>
                <td ng-if="x.waist != null && x.bust != null && x.hip != null">
                    {{x.waist}}-{{x.bust}}-{{x.hip}}
                </td>
                <td ng-if="x.waist == null || x.bust == null || x.hip == null">
                    无
                </td>
                <td>{{x.mobile}}</td>
                <td>{{x.vipComboPO.name}}</td>
                <td>{{x.vipUserPO.endTime | date:'yyyy-MM-dd'}}</td>
                <td ng-class="x.vipUserPO.status == 0 ? 'color_deepskyblue' : 'color_red'">{{x.vipUserPO.status | comboStatus}}</td>
                <td>
                    <a class="healthBtn"  ng-click="popupMemberDialog(x);" href="">健康数据</a>
                    <a  href="">聊天</a>
                    <a class="ServiceBtn" href="">服务记录</a>
                </td>
            </tr>
            </tbody>
        </table>
        &lt;%&ndash;<div class="scroll"><span class="scrollBar"></span></div>&ndash;%&gt;
    </div>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>--%>

        <div class="content-right-top">
            <div class="workspace">
                <p class="username">
                    用户名称：<input type="text" placeholder="请输入会员名字" ng-model="conditions.keyword">
                    <button class="button_blue" style="margin-left: 49%" ng-click="search()">
                        <i class="layui-icon">&#xe615;</i>查询
                    </button>
                </p>
            </div>
            <div class="userFilter  clearfix">
                <ul>
                    <li>
                        <!--  <p class="userService"><label for="service"><input type="checkbox" name="checkbxox" id="service">服务即将过期会员</label></p>-->
                        <div class="pretty success" style="padding: 5px;">
                            <input type="checkbox" ng-model="conditions.isEndTime">
                            <label for=""><i class="mdi mdi-check"></i>服务即将过期会员</label>
                        </div>
                    </li>
                    <li>性别：
                        <select name="" ng-model="conditions.gender">
                            <option value="" selected>不限</option>
                            <option value="true">男</option>
                            <option value="false">女</option>
                        </select>
                    </li>
                    <li>年龄段：
                        <select name="" ng-model="conditions.age">
                            <option value="" selected>不限</option>
                            <option value="20-25">20 - 25</option>
                            <option value="25-30">25 - 30</option>
                            <option value="30-35">30 - 35</option>
                            <option value="35-40">35 - 40</option>
                            <option value="40-45">40 - 45</option>
                            <option value="45-50">45 - 50</option>
                            <option value="50-55">50 - 55</option>
                            <option value="55-60">55 - 60</option>
                            <option value="60-65">60 - 65</option>
                            <option value="65-70">65 - 70</option>
                            <option value="70-75">70 - 75</option>
                        </select>
                    </li>
                    <li>套餐：
                        <select name="" id="" ng-model="conditions.vipComboId" style="width: 105px;">
                            <option value="">不限</option>
                            <option ng-repeat="i in vipCombo" ng-value="i.id">{{i.name}}</option>
                        </select>
                    </li>
                    <li>
                        使用状态：
                        <select name="" ng-model="conditions.status">
                            <option value='0'>使用中</option>
                            <option value='1'>停止使用</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>

        <div class="c-r-b">
            <table class="userListTable" id="informationList" border="0" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>性别</th>
                        <th>出生日期</th>
                        <th>身高cm/<br>体重kg</th>
                        <th>三围mm</th>
                        <th>手机号码</th>
                        <th>会员套餐</th>
                        <th>会员到期时间</th>
                        <th>使用状态</th>
                        <%--<th>操作</th>--%>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="x in userList" ng-cloak>
                        <td>{{page.pageIndex == 1 ? ($index + 1) * page.pageIndex : ($index + 1) + page.pageSize}}</td>
                        <td>{{x.realName || x.userName}}</td>
                        <td>{{x.gender | gender}}</td>
                        <td>{{x.birthday | date:'yyyy-MM-dd'}}</td>
                        <td>{{x.height}}/{{x.weight}}</td>
                        <td ng-if="x.waist != null && x.bust != null && x.hip != null">
                            {{x.waist}}-{{x.bust}}-{{x.hip}}
                        </td>
                        <td ng-if="x.waist == null || x.bust == null || x.hip == null">
                            无
                        </td>
                        <td>{{x.mobile}}</td>
                        <td>{{x.vipComboPO.name}}</td>
                        <td>{{x.vipUserPO.endTime | date:'yyyy-MM-dd'}}</td>
                        <td ng-class="x.vipUserPO.status == 0 ? 'color_deepskyblue' : 'color_red'">{{x.vipUserPO.status | comboStatus}}</td>
                    </tr>
                </tbody>
            </table>
            <div id="page" style="text-align: center"></div>

        </div>
</div>
<%--<script src="/static/js/vipmember/vip-member.js"></script>--%>
<script>
</script>


<style>
    #order_center {
        background: #3a4957;
    }

    #user_search {
        background: #3a4957;
    }
    .userList > li:nth-of-type(1) > a {
        background: #5f7487;
    }
    .content-left > ul > li:nth-of-type(2) {
        background: #4c5f70;
        color: #fff;
    }
    .user-manage .li-spec {
        background: #3a4957;
        color: #3cbaff;
    }
    .layui-layer-title {
        background: #fff!important;
        border-bottom: none !important;
    }
</style>

<script>
    $(".MemberSearchTable tr").each(function()
    {
        $(this).find("td").eq(7).css({'color':'deepskyblue','cursor':'pointer'}).click(function () {
            submitdata.submit()
        });
    });
    $(".userListTable tr").each(function () {
        $(this).find('td').eq(10).css({'color':'deepskyblue','cursor':'pointer'}).click(function () {
            submitdata.userList()
        })
    })
</script>