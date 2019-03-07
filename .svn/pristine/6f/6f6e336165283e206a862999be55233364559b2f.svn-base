<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
 <script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<link rel="stylesheet" type="text/css" href="/static/common/css/pageCss.css">
<head>
    <t:base type="jquery,layer,vue"></t:base>
</head>
 <style>
    .container{
        width: 918px;
        min-height: 840px;
        border: 1px solid #ddd;
        padding: 0 20px;
    }
    .container h3{
        height: 60px;
        line-height: 60px;
        font-size: 18px;
        font-weight: 400;
        margin: 0 30px 20px;
        border-bottom: 1px dashed #ddd;
    }
</style>
<div class="content5 nav_none" id="xyj" >
    <div>
        <span>血压计</span> <!-- <span class="date"><input type="date" id="date" name="date"></span> -->
    </div>
    <ul class="nav_tab">
        <li class="current"><a href="javascript:void(0)">收缩压</a></li>
        <li><a href="javascript:void(0)">舒张压</a></li>
        <li class="border_none"><a href="javascript:void(0)">心率</a></li>
    </ul>
    <div class="nav_content">
        <div id="container1" style="width: 700px; height: 400px"></div>
        <div id="relax" class="nav_none" style="width: 700px; height: 400px"></div>
        <div id="heartStep" class="nav_none" style="width: 700px; height: 400px"></div>
    </div>
    <ul class="period">
        <li id = "week" class="current"  onclick = "xyjSelectDateControl.selectDataByTime(this)"><a href="javascript:void(0)">周</a></li>
        <li id = "month" onclick = "xyjSelectDateControl.selectDataByTime(this)"><a href="javascript:void(0)">一月</a></li>
        <li id = "threeMonth" class="border_none" onclick = "xyjSelectDateControl.selectDataByTime(this)"><a href="javascript:void(0)">三月</a></li>
    </ul>
    <span>详细数据</span> <button  onclick="tableToExcel('xyj_table')">导出</button>
    <table id="xyj_table" class="service_table" cellpadding="0" cellspacing="0" style="margin-top: 20px;">
       <tr style="background-color: #CCCCCC;">
           <th>测量时间</th>
           <th>收缩压(mmHg)</th>
           <th>舒张压(mmHg)</th>
           <th>心率(bpm)</th>
           <th>状态</th>
       </tr>
       <tr v-if="results==''">
           <td colspan="5">暂无记录</td>
       </tr>
       <tr v-for='r in results'>
           <td v-cloak>{{r.measureDate | date("yyyy-MM-dd hh:mm:ss")}}</td>
           <td class="customTip" :title="r.systolicStatusDescription" v-cloak>{{r.systolic | formatData(r.systolicArea)}}</td>
           <td class="customTip" :title="r.diastolicStatusDescription" v-cloak>{{r.diastolic | formatData(r.diastolicArea)}}</td>
           <td class="customTip" :title="r.heartRateStatusDescription" v-cloak>{{r.heartRate | formatData(r.heartRateArea)}}</td>
           <td v-cloak class = "tdAdvice" v-bind:class = "{'changeNormalColor': r.status}">{{r.status > 0 ? "异常" : "正常"}}</td>
       </tr>
   </table>
    <div id="xyjPageManager" class="page_Container">
    </div>
</div>