<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="changeNumber nav_none" id="changeNumber">
	<dl>
		<dt>设备号码修改</dt>
		<dd>手环号码是设备电话卡的号码，如果手环更换了号码卡，平台或APP需做相应的修改</dd>
	</dl>
	<p class="firstAlign">
		<label>imei号</label>
		<input type="text" name="terminalImei" disabled="disabled" readonly="readonly" placeholder="IMEI号">
	</p>
	<p>
		<label>手机卡号</label>
		<input type="text" name="terminalMobile" disabled="disabled" readonly="readonly" placeholder="手机号码">
		<span class = "terminalMobile">号码修改</span>
		<span class = "" style="color: #9a9a9a; margin-left: 20px; border: none;">（手环内的电话卡号码）</span>
	</p>
	<p>
		<label>C3预警号码</label>	
		<input type="text" name="SOSMobile" disabled="disabled" readonly="readonly" placeholder="手机号码" style = "margin-left: 12px;">
		<span class = "SOSMobile">号码修改</span>
		<span class = "" style="color: #9a9a9a; margin-left: 20px; border: none;">（手环设置的预警号码）</span>
	</p>
</div>
<ul class="changeNumber_form" style="display: none">
	<li><input type="text" name="changeNumber_number" placeholder="请输入手机号码"></li>
</ul>