<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="bloodPressure" id="bloodPressure">
	<ul>
		<li><a href="#">血压</a></li>
		<!-- <li><input type="date"></li> -->
	</ul>
	<ul class="nav_tab nav_bloodPressure">
		<li class="current"><a href="javascript:void(0)">收缩压</a></li>
		<li class="border_none"><a href="javascript:void(0)">舒张压</a></li>
	</ul>
	<div class="nav_content">
		<div id="bloodPressure_systolic" style="min-width: 670px; height: 400px"></div>
		<div id="bloodPressure_diastolic" class="nav_none" style="min-width: 670px; height: 400px"></div>
	</div>
	<ul class="period">
		<li class="current" onclick="bloodPressureDataControl.drawChartByTime('week')"><a href="javascript:void(0)">周</a></li>
		<li onclick="bloodPressureDataControl.drawChartByTime('month')"><a href="javascript:void(0)">一月</a></li>
		<li onclick="bloodPressureDataControl.drawChartByTime('threeMonth')" class="border_none"><a href="javascript:void(0)">三月</a></li>
	</ul>
	<div class="detail" id="detail_bloodPressure">
		<span>详细数据</span> <button onclick="tableToExcel('bloodPressure_table')">导出</button>
		<table id="bloodPressure_table">
			<thead>
				<tr>
					<th>测试时间</th>
					<th>收缩压(mmHg)</th>
					<th>舒张压(mmHg)</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="bloodPressurePageCode"></div>
</div>