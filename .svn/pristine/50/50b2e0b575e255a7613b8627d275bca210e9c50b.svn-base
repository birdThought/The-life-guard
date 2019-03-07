<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="content1" id="xl" style="display: none;">
    <div>
        <span>心率手环</span>
        <!-- <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <ul class="nav_tab">
        <li id="xlheartRate" class="current"><a href="javascript:void(0)">心率</a></li>
        <li id="xlStep"><a href="javascript:void(0)">计步</a></li>
        <li id="xlSleep" class="border_none"><a href="javascript:void(0)">睡眠</a></li>
    </ul>
    <div class="nav_content">
        <div id="heartStep_3" style="width: 700px; height: 400px"></div>
        <div id="container5" class="nav_none"
            style="width: 700px; height: 400px"></div>
        <div id="slep" class="nav_none" style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period">
        <li id="week" class="current"
            onclick="xlSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="xlSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="xlSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>
    <span>详细数据</span>
    <button onclick="tableToExcel('xl_table')">导出</button>
    <table id="xl_table" class="service_table" cellpadding="0"
        cellspacing="0" style="margin-top: 20px;">
        <tr v-show="type == 'heartRate'" style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>心率(bpm)</th>
            <th>状态</th>
        </tr>
        <tr v-show="type == 'step'" style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>步数(步)</th>
            <th>里程(公里)</th>
            <th>卡路里(Kcal)</th>
        </tr>
        <tr v-show="type == 'sleep'" style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>总睡眠(分钟)</th>
            <th>深睡眠(分钟)</th>
            <th>浅睡眠(分钟)</th>
            <th>苏醒(分钟)</th>
        </tr>

        <tr v-if="results==''" v-show="type == 'heartRate'">
            <td colspan="3">暂无记录</td>
        </tr>
        <tr v-if="results==''" v-show="type == 'step'">
            <td colspan="4">暂无记录</td>
        </tr>
        <tr v-if="results==''" v-show="type == 'sleep'">
            <td colspan="5">暂无记录</td>
        </tr>
        <tr v-for='r in results' v-if = "type == 'heartRate'" v-show="type == 'heartRate'">
            <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
            <td class="customTip" :title="r.heartRateStatusDescription"  v-cloak>{{r.heartRate | formatData(r.heartRateArea)}}</td>
            <td v-cloak class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
        </tr>
        <tr v-for='r in results' v-if="type == 'step'" v-show="type == 'step'">
            <td v-cloak>{{r.date | date("yyyy-MM-dd")}}</td>
            <td v-cloak>{{r.steps}}</td>
            <td v-cloak>{{r.mileage}}</td>
            <td v-cloak>{{r.kcal}}</td>
        </tr>
        <tr v-for='r in results' v-if="type == 'sleep'" v-show="type == 'sleep'">
            <td v-cloak>{{r.date | date("yyyy-MM-dd")}}</td>
            <td v-cloak>{{r.sleepDuration}}</td>
            <td v-cloak>{{r.deepDuration}}</td>
            <td v-cloak>{{r.shallowDuration}}</td>
            <td v-cloak>{{r.wakeupDuration}}</td>
        </tr>
        <tr v-show="type == 'heartRate'">
            <td colspan="3" style = "border: 0px;">
                <div id="xlHeartRatePageManager" class="page_Container"></div>
            </td>
        </tr>
        <tr v-show="type == 'step'">
            <td colspan="4" style = "border: 0px;">
                <div id="xlStepPageManager" class="page_Container"></div>
            </td>
        </tr>
        <tr v-show="type == 'sleep'">
            <td colspan="5" style = "border: 0px;">
                <div id="xlSleepPageManager" class="page_Container"></div>
            </td>
        </tr>
    </table>
</div>