<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="content7 nav_none" id="temperature">
    <ul>
        <li><a href="#">体温</a></li>
    </ul>
    <div id="temper" style="min-width: 670px; height: 400px"></div>
    <ul class="period">
        <li id="week" class="current"
            onclick="twjSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="twjSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="twjSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>
    <span>详细数据</span>
    <button onclick="tableToExcel('twj_table')">导出</button>
    <table id="twj_table" class="service_table" cellpadding="0"
        cellspacing="0" style="margin-top: 20px;">
        <tr style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>体温(°C)</th>
            <th>状态</th>
        </tr>
        <tr v-if="results==''" >
            <td colspan="3">暂无记录</td>
        </tr>
        <tr v-for='r in results' >
            <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
            <td class="customTip" :title="r.temperatureStatusDescription" v-cloak>{{r.temperature | formatData(r.temperatureArea)}}</td>
            <td class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}" v-cloak>{{r.status > 0 ? "异常" : "正常"}}</td>
        </tr>
    </table>
    <div id="twjPageManager" class="page_Container"></div>
</div>