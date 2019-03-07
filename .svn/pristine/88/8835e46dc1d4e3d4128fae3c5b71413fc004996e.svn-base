<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="monitor nav_none" id="monitor">
	<dl>
		<dt>实时监听</dt>
		<dd>实时监听，通过平台发送短信至手环，手环在根据所选择的联系人自动回拨号码(每次只可以选择一个号码监听)</dd>
	</dl>
	<table>
		<thead>
			<tr>
				<th>姓名</th>
				<th>号码</th>
				<th style="position: relative;">类型<img
					src="/static/images/up.png" alt="">
					<ul style="display: none;">
						<li>全部</li>
						<li>预警号码</li>
						<li>亲情号码</li>
					</ul> <script>
						function changeType() {
							$('th ul').slideToggle();
							if ($('th ul').attr('data-hidden') == 0) {
								$('th img').css("transform", "rotate(-180deg)");
								$('th ul').attr('data-hidden', '1');
							} else {
								$('th img').css("transform", "rotate(0deg)");
								$('th ul').attr('data-hidden', '0');
							}
						}
						$('th img').click(function() {
							changeType();
						})
					</script>
				</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<!-- <tr>
				<td>程子欣</td>
				<td>15917053564</td>
				<td>预警号码</td>
				<td><span>启动监听</span></td>
			</tr>
			<tr>
				<td>陈小浩</td>
				<td>18020362378</td>
				<td>预警号码</td>
				<td><span>启动监听</span></td>
			</tr>
			<tr>
				<td>李冰</td>
				<td>15920364628</td>
				<td>亲情号码</td>
				<td><span>启动监听</span></td>
			</tr>
			<tr>
				<td>王明</td>
				<td>13723783964</td>
				<td>亲情号码</td>
				<td><span>启动监听</span></td>
			</tr> -->
		</tbody>
	</table>
</div>