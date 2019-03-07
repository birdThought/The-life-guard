<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="content6 nav_none" id="tzc">
    <div>
        <span>体脂秤</span>
        <!-- <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <div class="nav_content">
        <div id="container6" style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period">
        <li id="week" class="current"
            onclick="tzcSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="tzcSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="tzcSelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>

    <span>详细数据</span>
    <button onclick="tableToExcel('tzc_table')">导出</button>
    <table id="tzc_table" class="service_table" cellpadding="0"
        cellspacing="0" style="margin-top: 20px;">
        <tr style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>体重(kg)</th>
            <th>状态</th>
        </tr>
        <tr v-if="results==''">
            <td colspan="3">暂无记录</td>
        </tr>
        <tr v-for='r in results'>
            <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
            <td class="customTip" :title="r.weightStatusDescription" v-cloak>{{r.weight | formatData(r.weightArea)}}</td>
            <td v-cloak class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
        </tr>
    </table>
</div>