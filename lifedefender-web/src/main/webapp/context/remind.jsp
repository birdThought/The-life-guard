<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="remind nav_none" id="remind">
	<ul>
		<li><span>提醒设置</span></li>
		<li><span class="addNotice">添加提醒</span></li>
	</ul>
	<table id="notice">
		<thead>
			<tr>
				<th>启动</th>
				<th>时间</th>
				<th>重复间隔</th>
				<th>提醒时间</th>
				<th>提醒内容</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
<style>
	.setNotice .checkbox_line .icheckbox_minimal-green{
		margin-right:8px
	}
</style>
<ul class="setNotice" style="display: none;">
	<li><label>提醒时间</label> <input id="timer" name="time" type="text" value="00:00" onclick="TimePicker.showTimePicker(this)" readonly/></li>
	<li class="ui-slide">
		<label>重复间隔</label>
		<input type="hidden" class="slider-input" /> 
		<!-- <input id="sliderInp" type="text" value="0" oninput="timeSlider.sliderListen(this)" maxlength="2" />
		<div class="slider-track">
			<span class="slider-thumb" style="position: relative; text-align: center"> 
				<span id="progress" style="position: absolute; top: -25px; right: 10px; cursor: pointer">0</span>
			</span>
		</div> -->
	</li>
	<li><label>选择日期</label>
		<div class="checkbox_line">
			<input type="checkbox" value="1">星期一&nbsp;&nbsp;&nbsp;<input type="checkbox" value="2">星期二&nbsp;&nbsp;&nbsp;<input type="checkbox" value="3">星期三
		</div>
		<div class="checkbox_line" style="margin-top:8px">
			<input type="checkbox" value="4">星期四&nbsp;&nbsp;&nbsp;<input type="checkbox" value="5">星期五&nbsp;&nbsp;&nbsp;<input type="checkbox" value="6">星期六
		</div>
		<div class="checkbox_line" style="margin-top:8px">
			<input type="checkbox" value="7">星期日
		</div>
	</li>
	<li><label>提醒内容<input name="content" type="text" placeholder="请输入提醒内容，10个字符以内" maxlength="10" /></label></li>
</ul>
<script>
var jRangeSilder;
seajs.use(['/static/plugins/Jrange/js/jquery.range-min.js'], function() {
	jQuery(function() {
		jRangeSilder = $('.slider-input').jRange({ 
		    from: 0, 
		    to: 100, 
		    step: 0.5, 
		    scale: [0,25,50,75,100], 
		    format: '%s', 
		    width: 290, 
		    showLabels: true, 
		    showScale: true 
		});
	});
});

/* window.onload = function() {
	timeSlider.init({
		slider : document.getElementsByClassName("slider-thumb")[0],//slider的圆形按钮
		text : progress,//slider上面的文字
		input : sliderInp,//文本输入框
		length : 240
	//slider的长度
	});
}
var timeSlider = {
	sliderThumb : null,
	sliderText : null,
	sliderInput : null,
	slider_length : 0,
	init : function(data) {
		this.sliderThumb = data['slider'];
		this.sliderText = data['text'];
		this.sliderInput = data['input'];
		this.slider_length = parseInt(data['length']);
		this.sliderThumb.style.left = "0px";
		this.sliderThumb.onmousedown = function(evt) {
			var that = this;
			var oldX = evt.clientX;
			var left = parseInt(that.style.left);
			document.onmousemove = function(evt) {
				var x = evt.clientX - oldX;
				that.style.left = left + x + "px";
				if (parseInt(that.style.left) < 0) {
					that.style.left = 0;
				}
				if (parseInt(that.style.left) > timeSlider.slider_length) {
					that.style.left = timeSlider.slider_length + "px";
				}
				timeSlider.sliderInput.value = Math
						.ceil(parseInt(that.style.left)
								/ (timeSlider.slider_length) * 60);
				timeSlider.sliderText.innerText = timeSlider.sliderInput.value;
			}
			document.onmouseup = function(evt) {
				document.onmouseup = null;
				document.onmousemove = null;
			}
		}
	},
	sliderListen : function(obj) {
		var str = obj.value;
		if (isNaN(str) || str.indexOf(".") != -1) {
			alert("请输入从0~60范围的整数");
			return;
		}
		if (parseInt(str) > 60) {
			alert("请输入从0~60范围的整数");
			return;
		}
		if (parseInt(str) < 0) {
			alert("请输入从0~60范围的整数");
			return;
		}

		this.sliderThumb.style.left = obj.value
				* (this.slider_length / 60) + 'px';
		this.sliderText.innerText = obj.value;
	}
} */
</script>