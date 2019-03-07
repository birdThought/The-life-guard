<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/vipmember/vip-member.css">
<link rel="stylesheet" href="/static/css/vipmember/member-health-data.css">
<div ng-controller="vipMemberController" ng-init='init()'>
    <div class="Main-content">
        <div class="content-right-top" style="border: 1px dashed #ddd;border-top: none;box-shadow: 0px 0 20px #ddd">
            <p>会员名称：
                <input type="text" placeholder="请输入会员名字" ng-model="conditions.keyword">
                <button class="button_blue" style="margin-left: 50%" ng-click="search()">
                    <i class="layui-icon">&#xe615;</i> 查询
                </button>
            </p>


            <div class="pretty success">
                <input type="checkbox" ng-model="conditions.isEndTime">
                <label for=""><i class="mdi mdi-check"></i>服务即将过期会员</label>
            </div>
            <form class="WellForm">
                <div class="item">
                    <label>性别：</label>
                    <select name="sex" class="WellSelect" ng-model="conditions.gender">
                        <option value>不限</option>
                        <option value="true">男</option>
                        <option value="false">女</option>
                    </select>
                </div>
            </form>
            <form class="WellForm special" style="width:280px;">
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
            <p></p>
            <div class="pretty success">
                <input id="checkbox1" type="checkbox" ng-model="conditions.todayAbnormal">
                <label for="checkbox1" ><i class="mdi mdi-check"></i>今日异常数据会员</label>
            </div>
            <div class="pretty success">
                <input type="checkbox" ng-model="conditions.todayNotMeasure">
                <label for=""><i class="mdi mdi-check"></i>今日未测量数据</label>
            </div>
            <div class="pretty success">
                <input type="checkbox" ng-model="conditions.monthNotMeasure">
                <label for=""><i class="mdi mdi-check"></i>月未测量数据会员</label>
            </div>
            <form class="radio WellForm" style="margin-right: 50px;">
                <div class="item">
                    <label>套餐：</label>
                    <select name="year" class="WellSelect" ng-model="conditions.vipComboId" style="width:135px;">
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
                    <td style="width: 200px;">会员套餐</td>
                    <td>会员购买时间</td>
                    <td>会员到期时间</td>
                    <td>使用状态</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in userList" ng-cloak>
                    <td>{{($index + 1) + ((page.pageIndex - 1) * page.pageSize)}}</td>
                    <td>{{x.realName || x.userName}}</td>
                    <td>{{x.gender | gender}}</td>
                    <td>{{x.birthday | date:'yyyy-MM-dd'}}</td>
                    <td>{{x.height}}</td>
                    <td ng-if="x.waist != null && x.bust != null && x.hip != null">
                        {{x.waist}}-{{x.bust}}-{{x.hip}}
                    </td>
                    <td ng-if="x.waist == null || x.bust == null || x.hip == null">无</td>
                    <td>{{x.mobile}}</td>
                    <td>{{x.vipComboPO.name}}</td>
                    <td>{{x.createDate | date:'yyyy-MM-dd hh:mm'}}</td>
                    <td>{{x.vipUserPO.endTime | date:'yyyy-MM-dd'}}</td>
                    <td ng-class="x.vipUserPO.status == 0 ? 'color_deepskyblue' : 'color_red'">{{x.vipUserPO.status | comboStatus}}</td>
                    <td>
                        <a class="healthBtn"  ng-click="popupMemberDialog(x);" href="javascript:void(0)">健康数据</a>
                        <a  href="javascript:void(0)" ng-click="popupChatDialog(x.userName, x.realName, x.userCode, x.photo)">聊天</a>
                        <a class="ServiceBtn" href="javascript:void(0)" ng-click="popupServeRecord(x.userId)">服务记录</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <%--<div class="scroll"><span class="scrollBar"></span></div>--%>
        </div>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>

        <%--会员信息窗口start--%>
        <div class="member-dialog none" >
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <div class="layui-tab-title  clearfix " >
                    <ul class="fl">
                        <li class="layui-this health-data-health" ng-click = "clickTab($event, 1)">健康数据</li>
                        <li ng-click = "clickTab($event, 2)">体检报告</li>
                        <li ng-click = "clickTab($event, 3)">病历记录</li>
                        <li ng-click = "clickTab($event, 4)">饮食记录</li>
                    </ul>
                    <div class="date-time fr">
                        <input type="text" class="health-date" ng-model = "date"
                               style="background:url('/static/images/green-date-img.png') no-repeat right center; font-size: small;" readonly>
                    </div>
                </div>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <div class="health-data">
                            <div class="test-date clearfix">
                                <p>
                                    <span>{{date}}</span>
                                    <span ng-if="analysisList == null" class="health-analysis" ng-click="popupHealthAnalysisDialog()">添加批注</span>
                                    <span ng-if="analysisList != null" style="color: orange;">批注：</span>
                                    <input class="health-analysis-content" ng-if="analysisList != null" ng-value="analysisList[0].reply" onmouseover="showTip('.health-analysis-content', $(this).val())" onmouseout="closeTip()" readonly style="width: 78%; border: none; color: orange; font-size: 14px; cursor: pointer">
                                </p>

                            </div>
                            <div ng-if = "healthData != null && healthData.length != 1" class="data-head">
                                <span>类型</span>
                                <span>测量值</span>
                                <span>检查时间</span>
                            </div>
                            <ul class="date-details" ng-if = "healthData != null">
                                <li ng-if = "healthData.Glucometer != null">
                                    <dl>
                                        <dd>血糖仪</dd>
                                        <dd>
                                            <p>血糖值：{{healthData.Glucometer.dataType | measureType}}
                                                <span ng-class="healthData.Glucometer.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Glucometer.bloodSugar}}</span> mmol/L
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Glucometer.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('xty')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Glucometer == null">
                                    <dl>
                                        <dd>血糖仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('xty')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bloodpressure != null">
                                    <dl>
                                        <dd>血压计</dd>
                                        <dd>
                                            <p>收缩压：
                                                <span ng-class="healthData.Bloodpressure.systolicStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.systolic}}</span> mmHg
                                            </p>
                                            <p>舒张压：
                                                <span ng-class="healthData.Bloodpressure.diastolic != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.diastolic}}</span> mmHg
                                            </p>
                                            <p>心率：
                                                <span ng-class="healthData.Bloodpressure.heartRateStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bloodpressure.heartRate}}</span> 次/分
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Bloodpressure.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('xyj')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bloodpressure == null">
                                    <dl>
                                        <dd>血压计</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('xyj')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Oxygen != null">
                                    <dl>
                                        <dd>血氧仪</dd>
                                        <dd>
                                            <p>血氧饱和度：
                                                <span ng-class="healthData.Oxygen.saturationStatus != false ? 'color_10bb71' : 'red'">{{healthData.Oxygen.saturation}}</span> %
                                            </p>
                                            <p>心率：
                                                <span ng-class="healthData.Oxygen.heartRateStatus != false ? 'color_10bb71' : 'red'">{{healthData.Oxygen.heartRate}} </span>
                                                次/分</p>
                                        </dd>
                                        <dd>{{healthData.Oxygen.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('xyy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Oxygen == null">
                                    <dl>
                                        <dd>血氧仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('xyy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Lunginstrument != null">
                                    <dl>
                                        <dd>肺活仪</dd>
                                        <dd>
                                            <p>肺活仪：<span ng-class="healthData.Lunginstrument.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Lunginstrument.vitalCapacity}}</span> ml
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Lunginstrument.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('fhy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Lunginstrument == null">
                                    <dl>
                                        <dd>肺活仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('fhy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Band != null || healthData.Heartrate != null">
                                    <dl >
                                        <dd>心率手环</dd>
                                        <dd ng-if = "healthData.Band != null">
                                            <p>步数：<span class="color_10bb71">{{healthData.Band.steps}}</span> 步</p>
                                            <p>卡路里：<span class="color_10bb71">{{healthData.Band.kcal}}</span> Kcal</p>
                                            <p>深睡：<span class="color_10bb71">{{healthData.Band.deepDuration | sleepTime}}</span></p>
                                            <p>浅睡：<span class="color_10bb71">{{healthData.Band.shallowDuration | sleepTime}}</span></p>
                                            <p class="xinlv-dialog" ng-if = "healthData.Heartrate != null">
                                                心率：<span ng-class="healthData.Heartrate[0].status == 0 ? 'color_10bb71' : 'red'">{{healthData.Heartrate[0].heartRate}}</span>次/分
                                                <i class="heart-item-btn cursor-pointer" ng-click = "showHreatRate()"></i>
                                            </p>
                                        </dd>
                                        <%--<dd>心率</dd>--%>
                                        <dd ng-if = "healthData.Band == null" style="margin-left: -100px; margin-right: -50px;">
                                            <div ng-repeat = "(index, i) in healthData.Heartrate">
                                                <span class="heart-item-time" style="margin-left: 20px">{{i.measureDate | date:'hh:mm'}}</span>
                                                <span class="heart-item-unit"><span ng-class="i.status == 0 ? 'color_10bb71' : 'red'">{{i.heartRate}}</span> 次/分</span>
                                                <br ng-if = "(index + 1)%3 == 0">

                                            </div>
                                        </dd>

                                        <dd ng-if = "healthData.Band != null">{{healthData.Band.date | date:'yyyy-MM-dd'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('band')">历史详情</span>
                                        </dd>
                                        <dd ng-if = "healthData.Band == null">{{healthData.Heartrate[0].measureDate | date:'yyyy-MM-dd'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('band')">历史详情</span>
                                        </dd>
                                    </dl>

                                    <div class="heart-item none"  ng-if = "healthData.Heartrate != null"
                                         style="border: 1px solid #f5eeee; box-shadow: 1px 1px 3px #bdbcbc; padding: 5px;">
                                    <span ng-repeat = "(index, i) in healthData.Heartrate">
                                        <span class="heart-item-time">{{i.measureDate | date:'hh:mm'}}</span>
                                        <span class="heart-item-unit">
                                            <span ng-class="i.status == 0 ? 'color_10bb71' : 'red'">{{i.heartRate}}</span> 次/分
                                        </span>
                                        <br ng-if = "(index + 1)%5 == 0"/>
                                        <br ng-if = "(index + 1)%5 == 0"/>
                                    </span>
                                    </div>
                                </li>
                                <li ng-if = "healthData.Band == null && healthData.Heartrate == null">
                                    <dl>
                                        <dd>心率手环</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('band')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bodyfatscale != null">
                                    <dl>
                                        <dd>体脂秤</dd>
                                        <dd>
                                            <p>体重：
                                                <span ng-class="healthData.Bodyfatscale.weightStatus != false ? 'color_10bb71' : 'red'">{{healthData.Bodyfatscale.weight}}</span> kg
                                            </p>
                                            <%--<p>BMI：{{healthData.Bodyfatscale.deepDuration}} kg/m</p>--%>
                                        </dd>
                                        <dd>{{healthData.Bodyfatscale.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('tzc')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bodyfatscale == null">
                                    <dl>
                                        <dd>体脂秤</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('tzc')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Temperature != null">
                                    <dl>
                                        <dd>体温计</dd>
                                        <dd>
                                            <p>体温：
                                                <span ng-class="healthData.Temperature.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Temperature.temperature}}</span>℃
                                            </p>
                                            <%--<p>BMI：{{healthData.Bodyfatscale.deepDuration}} kg/m</p>--%>
                                        </dd>
                                        <dd>{{healthData.Temperature.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('twj')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Temperature == null">
                                    <dl>
                                        <dd>体温计</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('twj')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bloodlipid != null">
                                    <dl>
                                        <dd>血脂仪</dd>
                                        <dd>
                                            <p>总胆固醇：
                                                <span ng-class="healthData.Bloodlipid.tC != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.TC}}</span> mmol
                                            </p>
                                            <p>
                                                甘油三酯: <span ng-class="healthData.Bloodlipid.tG != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.TG}}</span> mmHg
                                            </p>
                                            <p>
                                                高密度脂蛋白: <span ng-class="healthData.Bloodlipid.hDL != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.HDL}}</span> mmol
                                            </p>
                                            <p>
                                                低密度脂蛋白: <span ng-class="healthData.Bloodlipid.lDL != false ? 'color_10bb71' : 'red'">{{healthData.Bloodlipid.LDL}}</span> mmol
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Bloodlipid.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('xzy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Bloodlipid == null">
                                    <dl>
                                        <dd>血脂仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('xzy')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Uran != null">
                                    <dl class="urine">
                                        <dd>尿液分析仪</dd>
                                        <dd style="text-align: left">
                                            <p>
                                                <span>比重: <label ng-class="healthData.Uran.sG != false ? 'color_10bb71' : 'red'">{{healthData.Uran.SG}} </label> </span>
                                                <span>PH值：<label ng-class="healthData.Uran.pH != false ? 'color_10bb71' : 'red'">{{healthData.Uran.pH}}</label></span>
                                            </p>
                                            <p>
                                                <span>白细胞:  <label ng-class="healthData.Uran.lEU != false ? 'color_10bb71' : 'red'">{{healthData.Uran.LEU}}</label>  </span>
                                                <span>蛋白质:  <label ng-class="healthData.Uran.pRO != false ? 'color_10bb71' : 'red'">{{healthData.Uran.PRO}}</label>  </span>
                                            </p>
                                            <p>
                                                <span >尿胆原:  <label ng-class="healthData.Uran.uBG != false ? 'color_10bb71' : 'red'">{{healthData.Uran.UBG}}</label> </span>
                                                <span>潜血:  <label ng-class="healthData.Uran.bLD != false ? 'color_10bb71' : 'red'">{{healthData.Uran.BLD}}</label> </span>
                                            </p>
                                            <p>
                                                <span>胆红素:  <label ng-class="healthData.Uran.bIL != false ? 'color_10bb71' : 'red'">{{healthData.Uran.BIL}}</label>  </span>
                                                <span>葡萄糖:  <label ng-class="healthData.Uran.gLU != false ? 'color_10bb71' : 'red'">{{healthData.Uran.GLU}}</label> </span>
                                            </p>
                                            <p>
                                                <span>酮体:  <label ng-class="healthData.Uran.kET != false ? 'color_10bb71' : 'red'">{{healthData.Uran.KET}}</label>  </span>
                                                <span>亚硝酸盐:  <label ng-class="healthData.Uran.nIT != false ? 'color_10bb71' : 'red'">{{healthData.Uran.NIT}}</label> </span>
                                            </p>
                                            <p>
                                                <span >VC维生素C:  <label ng-class="healthData.Uran.vC != false ? 'color_10bb71' : 'red'">{{healthData.Uran.VC}} </label></span>
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Uran.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('ny')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Uran == null">
                                    <dl>
                                        <dd>尿液分析仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('ny')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Ua != null">
                                    <dl>
                                        <dd>尿酸分析仪</dd>
                                        <dd>
                                            <p>尿酸：
                                                <span ng-class="healthData.Ua.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Ua.UA}}</span> mmol
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Ua.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('ns')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Ua == null">
                                    <dl>
                                        <dd>尿酸分析仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('ns')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>

                                <li ng-if = "healthData.Ecg != null">
                                    <dl>
                                        <dd>心电仪</dd>
                                        <dd>
                                            <p>心率：
                                                <span ng-class="healthData.Ecg.status == 0 ? 'color_10bb71' : 'red'">{{healthData.Ecg.heartRate}}</span> bpm
                                            </p>
                                        </dd>
                                        <dd>{{healthData.Ecg.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <br><span class="history-details" ng-click = "getDeviceHistory('ecg')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li ng-if = "healthData.Ecg == null">
                                    <dl>
                                        <dd>心电仪</dd>
                                        <dd>
                                            <p>暂无数据</p>
                                        </dd>
                                        <dd>
                                            <span class="history-details" ng-click = "getDeviceHistory('ecg')">历史详情</span>
                                        </dd>
                                    </dl>
                                </li>
                            </ul>
                        </div>
                        <div class = "device-history-data" ng-if="history.showDevice != null">
                            <br>
                            <p>
                                <i class="return-health-icon" ng-click = "showHealthData()">返回</i>
                            </p>
                            <br>
                            <p>
                                <span class = "return-health-data cursor-pointer" ng-click = "showHealthData()">健康数据</span>&gt;
                                <span class = "color_10bb71">{{history.showDevice | deviceName}}</span>
                            </p>
                            <div ng-if = "history.showDevice == 'xyj'" class="popup-bottom clearfix"><%--血压计--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'diastolic')" >舒张压</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'systolic')" >收缩压</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'heartRate')" >心率</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">mmHg</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>收缩压 &nbsp;:&nbsp;<span ng-class="d.systolicStatus == 'abnormal' ? 'red' : ''">{{d.systolic}}</span> mmHg</p>
                                        <p>舒张压 &nbsp;:&nbsp;<span ng-class="d.diastolicStatus == 'abnormal' ? 'red' : ''">{{d.diastolic}}</span> mmHg</p>
                                        <p>心率 : <span ng-class="d.heartRateStatus == 'abnormal' ? 'red' : ''">{{d.heartRate}}</span>次/分</p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'xty'" class="popup-bottom clearfix"><%--血糖仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, '1')" style="margin-left: 5px">早餐前</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, '2')" style="margin-left: 5px">早餐后</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, '3')" style="margin-left: 5px">午餐前</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, '4')" style="margin-left: 5px">午餐后</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, '5')" style="margin-left: 5px">晚餐前</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, '6')" style="margin-left: 5px">晚餐后</button>
                                </div>
                                <div ng-if="history.data.length > 0 && history.xtyHasData">
                                    <div class="bloodPressure">mmol</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0 || !history.xtyHasData" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="d in history.pageData">
                                        <div ng-repeat="p in d.paramData">
                                            <div ng-repeat="(index, t) in p">
                                                <div class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                                    <p>
                                                        <label style="color: #11bb71; font-weight: bold;">|</label>
                                                        {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                                        <span ng-if="t.status != 0" class="health-data-abnormal">异常</span>
                                                        <span ng-if="t.status == 0" class="health-data-normal">正常</span>
                                                    </p>
                                                    <p>血糖浓度 &nbsp;:&nbsp;<span ng-class="t.status == 0 ? '' : 'red'">{{t.bloodSugar}}</span> mmol/L</p>
                                                    <p>类型：<span style="color: grey; font-size: 14px;">{{index | measureType}}</span></p>
                                                    <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'xyy'" class="popup-bottom clearfix"><%--血氧仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'saturation')" >血氧饱和度</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'heartRate')" >心率</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure"></div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>血氧饱和度 &nbsp;:&nbsp;<span ng-class="d.saturationStatus == 'abnormal' ? 'red' : ''">{{d.saturation}}</span> %</p>
                                        <p>心率 &nbsp;:&nbsp;<span ng-class="d.heartRatestatus == 'abnormal' ? 'red' : ''">{{d.heartRate}}</span> bpm</p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'fhy'" class="popup-bottom clearfix"> <%--肺活仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'vitalCapacity')" >肺活量</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">ml</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>肺活量 &nbsp;:&nbsp;<span ng-class="d.status == 0 ? '' : 'red'">{{d.vitalCapacity}}</span> ml</p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'band'" class="popup-bottom clearfix"><%--心率手环--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'steps')" >计步</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'heartRate')" >心率</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'sleepDuration')" >睡眠</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">%</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{(history.paramType == 'heartRate' ? d.measureDate : date) | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="history.paramType == 'heartRate'">
                                                <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                                <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                            </span>
                                        </p>
                                        <span ng-if="history.paramType == 'steps'">
                                            <p>步数 &nbsp;:&nbsp;<span>{{d.steps}}</span> 步</p>
                                            <p>卡路里 &nbsp;:&nbsp;<span>{{d.kcal }}</span> Kcal</p>
                                            <p>里程 &nbsp;:&nbsp;<span>{{d.mileage }}</span> 公里</p>
                                        </span>
                                        <span ng-if="history.paramType == 'heartRate'">
                                            <p>心率 &nbsp;:&nbsp;<span ng-class="d.status == 0 ? 'color_10bb71' : 'red'">{{d.heartRate}}</span> bpm</p>
                                        </span>
                                        <span ng-if="history.paramType == 'sleepDuration'">
                                            <p>睡眠时长 &nbsp;:&nbsp;<span>{{d.sleepDuration | sleepTime}}</span> </p>
                                            <p>深睡 &nbsp;:&nbsp;<span>{{d.deepDuration | sleepTime}}</span> </p>
                                            <p>浅睡 &nbsp;:&nbsp;<span>{{d.shallowDuration | sleepTime}}</span> </p>
                                            <p>苏醒 &nbsp;:&nbsp;<span>{{d.wakeupDuration | sleepTime}}</span> </p>

                                        </span>

                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'tzc'" class="popup-bottom clearfix"><%--体脂秤--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'weight')" >体重</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">kg</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <template ng-if="d.status != 0">
                                                <span class="health-data-abnormal">异常</span>
                                            </template>
                                            <template ng-if="d.status == 0">
                                                <span class="health-data-normal">正常</span>
                                            </template>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">体重 &nbsp;:&nbsp;<span ng-class="d.weightStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.weight}}</span>kg</div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale">体脂率 &nbsp;:&nbsp;<span ng-class="d.axungeRatioStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.axungeRatio}}</span>%</div>
                                        </p>
                                        <p>
                                        <div class="color_666">BMI &nbsp;:&nbsp;<span ng-class="d.BMIStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.BMI}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >内脏脂肪 &nbsp;:&nbsp;<span ng-class="d.visceralFatStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.visceralFat}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666">腰臀比 &nbsp;:&nbsp;<span ng-class="d.WHRStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.WHR}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >体年龄 &nbsp;:&nbsp;<span ng-class="d.bodyageStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.bodyage}}</span>岁</div>
                                        </p>
                                        <p>
                                        <div class="color_666">人体水分 &nbsp;:&nbsp;<span ng-class="d.moistureStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.moisture}}</span>%</div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >人体肌肉 &nbsp;:&nbsp;<span ng-class="d.muscleStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.muscle}}</span>%</div>
                                        </p>
                                        <p>
                                        <div class="color_666">骨骼重量 &nbsp;:&nbsp;<span ng-class="d.boneWeightStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.boneWeight}}</span>kg</div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >基础代谢 &nbsp;:&nbsp;<span ng-class="d.baseMetabolismStatus == 'abnormal' ? 'red' : 'color_10bb71'">{{d.baseMetabolism}}</span>kg</div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'twj'" class="popup-bottom clearfix"><%--体温计--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'temperature')" >体温计</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">℃</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">体温 &nbsp;:&nbsp;<span ng-class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.temperature}}</span>℃</div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'xzy'" class="popup-bottom clearfix"><%--血脂仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'tC')" >总胆固醇</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'tG')" >甘油三酯</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">mmol</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">总胆固醇 &nbsp;:&nbsp;<span ng-class="d.tCStatus == false ? 'red' : 'color_10bb71'">{{d.tC}}</span>mmol/L</div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">甘油三酯 &nbsp;:&nbsp;<span ng-class="d.tGStatus == false ? 'red' : 'color_10bb71'">{{d.tG}}</span>mmol/L</div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">高密度脂蛋白胆固醇 &nbsp;:&nbsp;<span ng-class="d.hDLStatus == false ? 'red' : 'color_10bb71'">{{d.hDL}}</span>mmol/L</div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">低密度脂蛋白胆固醇 &nbsp;:&nbsp;<span ng-class="d.lDLStatus == false ? 'red' : 'color_10bb71'">{{d.lDL}}</span>mmol/L</div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'ny'" class="popup-bottom clearfix"><%--尿液分析仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'sG')" >比重</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeParamType($event, 'pH')" >PH值</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure"></div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">比重 &nbsp;:&nbsp;<span ng-class="d.sGStatus == false ? 'red' : 'color_10bb71'">{{d.sG}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >PH值 &nbsp;:&nbsp;<span ng-class="d.pHStatus == false ? 'red' : 'color_10bb71'">{{d.pH}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">葡萄糖 &nbsp;:&nbsp;<span ng-class="d.gLUStatus == false ? 'red' : 'color_10bb71'">{{d.gLU}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >蛋白质 &nbsp;:&nbsp;<span ng-class="d.pROStatus == false ? 'red' : 'color_10bb71'">{{d.pRO}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">尿胆原 &nbsp;:&nbsp;<span ng-class="d.uBGStatus == false ? 'red' : 'color_10bb71'">{{d.uBG}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >潜血 &nbsp;:&nbsp;<span ng-class="d.bLDStatus == false ? 'red' : 'color_10bb71'">{{d.bLD}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">胆红素 &nbsp;:&nbsp;<span ng-class="d.bILStatus == false ? 'red' : 'color_10bb71'">{{d.bIL}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >亚硝酸盐 &nbsp;:&nbsp;<span ng-class="d.nITStatus == false ? 'red' : 'color_10bb71'">{{d.nIT}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">白细胞 &nbsp;:&nbsp;<span ng-class="d.lEUStatus == false ? 'red' : 'color_10bb71'">{{d.lEU}}</span></div>
                                        <div class="color_666 line-height_24 history-health-bodyfatscale" >酮体 &nbsp;:&nbsp;<span ng-class="d.kETStatus == false ? 'red' : 'color_10bb71'">{{d.kET}}</span></div>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">VC维生素C &nbsp;:&nbsp;<span ng-class="d.vCStatus == false ? 'red' : 'color_10bb71'">{{d.vC}}</span></div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'ns'" class="popup-bottom clearfix"><%--尿酸分析仪--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'uA')" >尿酸</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">mmol</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">尿酸 &nbsp;:&nbsp;<span ng-class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.uA}}</span>mmol/L</div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>

                            <div ng-if = "history.showDevice == 'ecg'" class="popup-bottom clearfix"><%--心电--%>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="on cursor-pointer" ng-click="changeParamType($event, 'heartRate')" >心率</button>
                                </div>
                                <div ng-if="history.data.length > 0">
                                    <div class="bloodPressure">bpm</div>
                                    <div style="width: 600px;height:254px; overflow-x: auto;">
                                        <div id="chart_container"  style="width: 600px; height:240px; overflow-x: hidden;">
                                            <p><s></s></p>
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="history.data.length == 0" style="width: 600px;height:315px; padding-top: 200px; color: gray; padding-left: 205px;">
                                    <p>暂未有数据</p>
                                </div>
                                <div class="popup-bottom-top list">
                                    <button type="button" class="cursor-pointer on" ng-click="changeDateType($event, 'WEEK')" >一周</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'MONTH')" >一月</button>
                                    <button type="button" class="cursor-pointer" ng-click="changeDateType($event, 'THREEMONTH')" >三月</button>
                                </div>
                                <div class="popup-bottom-bottom">
                                    <div ng-repeat="(index, d) in history.pageData" class="popup-bottom-bottom-left" <%--ng-class="index/2 == 0 ? 'border' : ''"--%>>
                                        <p>
                                            <label style="color: #11bb71; font-weight: bold;">|</label>
                                            {{d.detailList[0].measureDate | date:'yyyy-MM-dd hh:mm'}}
                                            <span ng-if="d.detailList[0].status != 0" class="health-data-abnormal">异常</span>
                                            <span ng-if="d.detailList[0].status == 0" class="health-data-normal">正常</span>
                                        </p>
                                        <p>
                                        <div class="color_666 line-height_24">心率 &nbsp;:&nbsp;<span ng-class="d.status != 0 ? 'red' : 'color_10bb71'">{{d.detailList[0].heartRate}}</span>bpm</div>
                                        </p>
                                        <%--<p>批注&nbsp; ：&nbsp;<input type="text" placeholder="."></p>--%>
                                    </div>
                                </div>
                                <div class="last"></div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-tab-item">
                        <div ng-cloak class="data-none" ng-if = "physical.totalSize == 0" style="text-align: center; padding-top: 100px;">
                            <img src="/static/images/org-no-news.png">
                            <p style="color: #acacac; font-size: 16px;">暂无数据</p>
                        </div>
                        <dl class="health-case" ng-repeat = "(index, p) in physical.data">
                            <dt>医院：{{p.physicalsOrg}}</dt>
                            <dd>
                                <p>类别：{{p.title}}</p>
                                <div class="physical-img" style="display: initial;">
                                    <img ng-repeat="i in [0, 1, 2, 3, 4, 5]" ng-if = "p.imgList[i-1] != null"
                                         style="width: 250px; height: 220px; margin-right: 10px; margin-bottom: 10px;"
                                         ng-src = "{{p.imgList[i-1].img}}" alt=""
                                         ng-click = "popurImg(event, p.imgList[i-1].img)"
                                    >
                                </div>
                            </dd>
                        </dl>
                        <div style="text-align: center" id="page-physical" ></div>
                    </div>
                    <div class="layui-tab-item">
                        <div ng-cloak class="data-none" ng-if = "medical.data != null && medical.data.length == 0" style="text-align: center; padding-top: 100px;">
                            <img src="/static/images/org-no-news.png">
                            <p style="color: #acacac; font-size: 16px;">暂无数据</p>
                        </div>
                        <div>
                            <ul>
                                <li ng-repeat = "(index, m) in medical.data">
                                    <dl>
                                        <dt>医院：</dt>
                                        <dd class="case-type">{{m.title}}</dd>
                                        <dd>
                                            <span>{{m.name}}</span>
                                            <span>{{m.sex}}</span>
                                            <span>{{m.age}}岁</span>
                                        </dd>
                                        <dd>
                                            {{m.basicCondition}}
                                        </dd>
                                        <dd>
                                            <span class="timed">{{m.visitingDate}}就诊</span>
                                        </dd>
                                    </dl>
                                    <div class="state-ill" style="height:200px;">
                                        <img ng-src = "m.photo" alt="" style="height: 100%;"
                                             ng-click = "popurImg(event, m.photo)"
                                             ng-if = "m.photo != null && m.photo != ''"
                                        >
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div style="text-align: center" id="page-medical" ></div>
                    </div>
                    <div class="layui-tab-item">

                        <div class="dinner-situation">
                            <div class="test-date clearfix">
                                <p>{{date}}</p>
                            </div>
                            <ul class="tab-1">
                                <li class="on">早餐</li>
                                <li>午餐</li>
                                <li>晚餐</li>
                            </ul>
                            <div ng-cloak class="data-none" ng-if = "dietData != null && dietData.length == 0" style="text-align: center; padding-top: 100px;">
                                <img src="/static/images/org-no-news.png">
                                <p style="color: #acacac; font-size: 16px;">暂无数据</p>
                            </div>
                            <div class="tab-content-1">
                                <div>
                                    <dl ng-repeat = "d in dietData" ng-if = "d.dietType == '早餐' || d.dietType == '早餐加餐'">
                                        <dt>{{d.dietType}}</dt>
                                        <dd>
                                            <template ng-repeat = "(index, detail) in d.dietDetails">{{detail}}
                                                <%--<template ng-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                            </template>

                                        </dd>
                                        <dd class="clearfix">
                                            <span class="fl">消耗能量:{{d.energy}}</span>
                                            <span class="fr">{{d.dietTime}}</span>
                                        </dd>
                                    </dl>

                                </div>
                                <div>
                                    <dl ng-repeat = "d in dietData" ng-if = "d.dietType == '午餐' || d.dietType == '午餐加餐'">
                                        <dt>{{d.dietType}}</dt>
                                        <dd>
                                            <template ng-repeat = "(detail, index) in d.dietDetails">{{detail}}
                                                <%--<template ng-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                            </template>

                                        </dd>
                                        <dd class="clearfix">
                                            <span class="fl">消耗能量:{{d.energy}}</span>
                                            <span class="fr">{{d.dietTime}}</span>
                                        </dd>
                                    </dl>

                                </div>
                                <div>
                                    <dl ng-repeat = "d in dietData" ng-if = "d.dietType == '晚餐' || d.dietType == '晚餐加餐'">
                                        <dt>{{d.dietType}}</dt>
                                        <dd>
                                            <template ng-repeat = "(index, detail) in d.dietDetails">{{detail}}
                                                <%--<template ng-if = "index < detail.length - 5">、{{detail.length}}</template>--%>
                                            </template>

                                        </dd>
                                        <dd class="clearfix">
                                            <span class="fl">消耗能量:{{d.energy}}</span>
                                            <span class="fr">{{d.dietTime}}</span>
                                        </dd>
                                    </dl>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%---会员信息窗口end--%>

        <%--批注窗口start--%>
        <div class="none health-analysis-dialog" style="">
            <span>批注:</span>
            <textarea placeholder="请输入批注内容..." ng-model="analysis.content"></textarea><br>
            <span>医生:</span>
            <input placeholder="请输入医生署名" ng-model="analysis.doctorSign">
        </div>
        <%--批注窗口end--%>

        <%--服务记录窗口start--%>
        <div id="ServeRecordDialog" style="display: none;">
            <h5 class="button_blue" ng-click="popupAddServeRecord()">+增加记录</h5>
            <table  padding="10px" cellspacing="0" cellpadding="1">
                <thead>
                <tr>
                    <td style="width: 20%">服务日期</td>
                    <td style="width: 40%">服务内容</td>
                    <td>备注</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="i in serveRecord.list">
                    <td>{{i.serveDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{i.serveContent}}</td>
                    <td>{{i.remark}}</td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="none add-serve-record-dialog" style="">
            <span>服务日期:</span>
            <input type="text" class="serve-record-date" style="background:url('/static/images/green-date-img.png')
        no-repeat right center; font-size: 16px; width: 120px; color: #757575;" readonly=""
                   ng-model="serveRecord.serveDate"><br>
            <span>服务内容:</span>
            <textarea placeholder="请输入服务内容..." ng-model="serveRecord.serveContent"></textarea><br>
            <span style="margin-left: 26px;">备注:</span>
            <textarea placeholder="请输入备注..." ng-model="serveRecord.remark"></textarea>
        </div>
        <%--服务记录窗口end--%>
    </div>
</div>
<%-- <%@include file="/view/platform/message/chat-dialog.jsp"%> --%>
<%--<script src="/static/js/vipmember/vip-member.js"></script>--%>