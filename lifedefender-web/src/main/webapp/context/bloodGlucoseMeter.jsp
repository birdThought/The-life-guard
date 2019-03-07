<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="content3 nav_none" id="xty">
    <div>
        <span>血糖仪</span>
        <!-- <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <ul class="period" id="measureType" style="width: 425px;">
        <li class="current" style="width: 70px;"><a
            href="javascript:void(0)">早餐前</a></li>
        <li style="width: 70px;"><a href="javascript:void(0)">早餐后</a></li>
        <li style="width: 70px;"><a href="javascript:void(0)">午餐前</a></li>
        <li style="width: 70px;"><a href="javascript:void(0)">午餐后</a></li>
        <li style="width: 70px;"><a href="javascript:void(0)">晚餐前</a></li>
        <li class="border_none" style="width: 70px;"><a
            href="javascript:void(0)">晚餐后</a></li>
    </ul>

    <div class="nav_content">
        <div id="container3" style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period" id="dateType">
        <li id="week" class="current"
            onclick="xtySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="xtySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="xtySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>
    <span>详细数据</span>
    <button onclick="tableToExcel('xty_table')">导出</button>
    <table id="xty_table" class="service_table" cellpadding="0"
        cellspacing="0" style="margin-top: 20px;">
        <tr style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>测量类型</th>
            <th>血糖(mmol/L)</th>
            <th>状态</th>
        </tr>
        <tr v-if="results==''">
            <td colspan="4">暂无记录</td>
        </tr>
        <tr v-for='r in results'>
            <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
            <td v-cloak>{{r.measureType | formatMeasureType}}</td>
            <td class="customTip" :title="r.bloodSugarStatusDescription" v-cloak>{{r.bloodSugar | formatData(r.bloodSugarArea)}}</td>
            <td v-cloak class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
        </tr>
    </table>
    <div id="xtyPageManager" class="page_Container"></div>
</div>
