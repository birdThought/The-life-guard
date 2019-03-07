<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/vipmember/vip-card.css?v=1.0.0">
<style>
    .layui-layer-content{
        height: 100% !important;
    }
#submitData3 > section:nth-of-type(2) > ul{
    width:85%;}
</style>
<div ng-controller="searchMemberController" ng-init='init()'>
    <div class="c-r-t">
        <p> <i class="iconfont icon-cursor"></i>报告分析处理 ><span class="orderCenter">用户搜索</span></p>
    </div>
    <div class="c-r-m clearfix"><p><input type="text" placeholder="请输入手机号/用户名" ng-model="conditions.keyword" ng-keypress="enterSearchMember($event)"><button ng-click="searchMember()"><i class="layui-icon">&#xe615;</i>搜索</button></p></div>
    <div class="c-r-b">
        <table class="MemberSearchTable" id="informationList" border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>性别</th>
                <th>出生日期</th>
                <th>身高cm/<br>体重kg</th>
                <th>三围mm</th>
                <th>手机号码</th>
                <th>尿液分析</th>
                <th>血脂分析</th>
                <th>尿酸分析</th>
                <th>心电分析</th>
                <th>血糖分析</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="(index, m) in members">
                <td>{{index + 1}}</td>
                <td>{{m.realName || m.userName}}</td>
                <td>{{m.recordDTO.gender | gender}}</td>
                <td>{{m.recordDTO.birthday}}</td>
                <td>{{m.recordDTO.height}}cm/{{m.recordDTO.height}}kg</td>
                <td ng-if="m.recordDTO.waist != null && m.recordDTO.bust != null && m.recordDTO.hip != null">
                    {{m.recordDTO.waist}}-{{m.recordDTO.bust}}-{{m.recordDTO.hip}}
                </td>
                <td ng-if="m.recordDTO.waist == null || m.recordDTO.bust == null || m.recordDTO.hip == null">
                    无
                </td>
                <td>{{m.mobile}}</td>
                <td><span class="submit-report" ng-click="popupRuanDialog(m.id)">提交数据</span></td>
                <td><span class="submit-report" ng-click="popupRuanDialog1(m.id)">提交数据</span></td>
                <td><span class="submit-report" ng-click="popupRuanDialog2(m.id)">提交数据</span></td>
                <td><span class="submit-report" ng-click="popupRuanDialog3(m.id)">提交数据</span></td>
                <td><span class="submit-report" ng-click="popupRuanDialog4(m.id)">提交数据</span></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div id="submitData1" style="display: none" >
        <section><p ng-class="ruanData1.status == 0 ? '' : 'unusual'">{{ruanData1.measureDate | date:'yyyy-MM-dd hh:mm'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>高密度脂蛋白胆固醇: {{ruanData1.hDL}}</li>
                <li>低密度脂蛋白胆固醇:{{ruanData1.lDL}}</li>
                <li>甘油三酯:{{ruanData1.tG}}</li>
                <li>总胆固醇:{{ruanData1.tC}}</li>
            </ul>
        </section>
        <section>
            <button ng-click="submitOrder1()">提交数据</button>
        </section>
    </div>
    <div id="submitData2" style="display: none" >
        <section><p ng-class="ruanData2.status == 0 ? '' : 'unusual'">{{ruanData2.measureDate | date:'yyyy-MM-dd hh:mm'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>尿酸: {{ruanData2.uA}}</li>
            </ul>
        </section>
        <section>
            <button ng-click="submitOrder2()">提交数据</button>
        </section>
    </div>

    <div id="submitData3" style="display: none">
        <section><p>{{ruanData3[0].detailList[0].measureDate | date:'yyyy-MM-dd hh:mm'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>心电:{{ruanData3[0].detailList[0].heartRate}}</li>
                <li>心电图:<img src="{{ruanData3[0].detailList[0].image}}" alt=""></li>
            </ul>
        </section>
        <section>
            <button ng-click="testOne()">提交数据</button>
        </section>
    </div>

    <div id="submitData4" style="display: none" >
        <section><p ng-class="ruanData4.status == 0 ? '' : 'unusual'">{{ruanData4.measureDate | date:'yyyy-MM-dd hh:mm'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>血糖: {{ruanData4.bloodSugar}}</li>
                <li>血糖正常值范围: {{ruanData4.bloodSugarArea}}</li>
            </ul>
        </section>
        <section>
            <button ng-click="submitOrder4()">提交数据</button>
        </section>
    </div>

   <div id="submitData" >
        <section><p ng-class="ruanData.status == 0 ? '' : 'unusual'">{{ruanData.measureDate | date:'yyyy-MM-dd hh:mm'}}</p></section>
        <section class="clearfix">
            <ul>
                <li>比重: {{ruanData.sG}}</li>
                <li>白细胞：{{ruanData.lEU}}</li>
                <li>尿胆原：{{ruanData.uBG}}</li>
                <li>胆红素：{{ruanData.bIL}}</li>
            </ul>
            <ul>
                <li>PH值: {{ruanData.pH}}</li>
                <li>蛋白质：{{ruanData.pRO}}</li>
                <li>潜血：{{ruanData.bLD}}</li>
                <li>葡萄糖：{{ruanData.gLU}}</li>
            </ul>
            <ul>
                <li>酮体: {{ruanData.kET}}</li>
                <li>亚硝酸盐：{{ruanData.nIT}}</li>
                <li>VC维生素C：{{ruanData.vC}}</li>
            </ul>
        </section>
        <section>
            <button ng-click="submitOrder()">提交数据</button>
        </section>
    </div>
</div>


<style>
    .submit-report {
        color: #3cbaff;
        cursor: pointer;
    }
    .submit-report:hover {
        border-bottom: 2px dashed #3cbaff;
    }

</style>