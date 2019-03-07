<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="stepCounter nav_none" id="stepCounter">
	<ul>
		<li><a href="#">计步</a></li>
	</ul>
	<div id="stepCount" style="min-width: 670px; height: 400px"></div>
	<ul class="period">
		<li onclick="stepCounterDataControl.drawChartByTime('week')" class="current"><a href="javascript:void(0)">周</a></li>
		<li onclick="stepCounterDataControl.drawChartByTime('month')"><a href="javascript:void(0)">一月</a></li>
		<li onclick="stepCounterDataControl.drawChartByTime('threeMonth')" class="border_none"><a href="javascript:void(0)">三月</a></li>
	</ul>
	<div class="detail" id="detail_stepCounter">
		<span>详细数据</span>  <button  onclick="tableToExcel('jb_table')">导出</button>
		<table id="jb_table">
			<thead></thead>
			<tbody></tbody>
		</table>
	</div>
	<!-- 分页 -->
	<div class="stepCounterPageCode"></div>
</div>