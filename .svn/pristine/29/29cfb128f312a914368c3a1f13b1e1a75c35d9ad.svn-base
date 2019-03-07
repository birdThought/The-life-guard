<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="sleep nav_none" id="sleep">
	<ul>
		<li><a href="#">睡眠</a></li>
	</ul>
	<div id="slep" style="min-width: 670px; height: 400px"></div>
	<ul class="period">
		<li onclick="sleepDataControl.drawChartByTime('week')" class="current"><a href="javascript:void(0)">周</a></li>
		<li onclick="sleepDataControl.drawChartByTime('month')"><a href="javascript:void(0)">一月</a></li>
		<li onclick="sleepDataControl.drawChartByTime('threeMonth')" class="border_none"><a href="javascript:void(0)">三月</a></li>
	</ul>
	<div class="detail" id="detail_sleep">
		<span>详细数据</span>  <button  onclick="tableToExcel('sleep_table')">导出</button>
		<table id="sleep_table">
			<thead></thead>
			<tbody></tbody>
		</table>
	</div>
	<!-- 分页 -->
	<div class="sleepPageCode"></div>
</div>