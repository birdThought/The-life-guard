<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="heartRate nav_none" id="heartRate">
	<ul>
		<li><a href="#">心率</a></li>
		<!-- <li><input type="date"></li> -->
	</ul>
	<div id="heartRat" style="min-width: 670px; height: 400px"></div>
	<ul class="period">
		<li onclick="heartRateDataControl.drawChartByTime('week')" class="current"><a href="javascript:void(0)">周</a></li>
		<li onclick="heartRateDataControl.drawChartByTime('month')"><a href="javascript:void(0)">一月</a></li>
		<li onclick="heartRateDataControl.drawChartByTime('threeMonth')" class="border_none"><a href="javascript:void(0)">三月</a></li>
	</ul>
	<div class="detail" id="detail_heartRate">
		<span>详细数据</span>  <button>导出</button>
		<table>
			<thead>
				<tr>
					<th>测试时间</th>
					<th>心率(bpm)</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<!-- 分页 -->
	<div class="heartRatePageCode"></div>
</div>