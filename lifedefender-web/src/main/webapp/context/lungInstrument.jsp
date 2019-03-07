<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content2 nav_none" id="fhy">
    <div>
        <span>肺活仪</span>
        <!--  <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <div class="nav_content">
        <div id="container2" style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period">
        <li id="week" class="current"
            onclick="fhySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">周</a></li>
        <li id="month" onclick="fhySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">一月</a></li>
        <li id="threeMonth" class="border_none"
            onclick="fhySelectDateControl.selectDataByTime(this)"><a
            href="javascript:void(0)">三月</a></li>
    </ul>
    <span>详细数据</span>
    <button onclick="tableToExcel('fhy_table')">导出</button>
    <table id="fhy_table" class="service_table" cellpadding="0"
        cellspacing="0" style="margin-top: 20px;">
        <tr style="background-color: #cccccc;">
            <th>测量时间</th>
            <th>肺活量(ml)</th>
            <th>状态</th>
        </tr>
        <tr v-if="results==''">
            <td colspan="3">暂无记录</td>
        </tr>
        <tr v-for='r in results'>
            <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
            <td class="customTip" :title="r.vitalCapacityStatusDescription" v-cloak>{{r.vitalCapacity | formatData(r.vitalCapacityArea)}}</td>
            <td v-cloak class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
        </tr>
    </table>
    <div id="fhyPageManager" class="page_Container"></div>
</div>