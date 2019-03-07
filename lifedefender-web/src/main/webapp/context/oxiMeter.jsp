<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="content4 nav_none" id="xyy">
    <div>
        <span>血氧仪</span>
        <!--  <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <ul class="nav_tab">
        <li class="current"><a href="javascript:void(0)">血氧</a></li>
        <li class="border_none"><a href="javascript:void(0)">心率</a></li>
    </ul>
    <div class="nav_content">
        <div id="container4" style="width: 700px; height: 400px"></div>
        <div id="heartStep_2" class="nav_none"
            style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period">
        <li id="week" class="current"
            onclick="xyySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="xyySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="xyySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>
    <div class="detail">
        <span>详细数据</span>
        <button onclick="tableToExcel('xyy_table')">导出</button>
        <table id="xyy_table" class="service_table" cellpadding="0"
            cellspacing="0" style="margin-top: 20px;">
            <tr style="background-color: #cccccc;">
                <th>测量时间</th>
                <th>血氧仪(%)</th>
                <th>心率(bpm)</th>
                <th>状态</th>
            </tr>
            <tr v-if="results==''">
                <td colspan="4">暂无记录</td>
            </tr>
            <tr v-for='r in results'>
                <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
                <td class="customTip" :title="r.saturationStatusDescription" v-cloak>{{r.saturation | formatData(r.saturationArea)}}</td>
                <td class="customTip" :title="r.heartRateStatusDescription" v-cloak>{{r.heartRate | formatData(r.heartRateArea)}}</td>
                <td v-cloak class = "tdAdvice"  v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
            </tr>
        </table>
        <div id="xyyPageManager" class="page_Container"></div>
    </div>
</div>