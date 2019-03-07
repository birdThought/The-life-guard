<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="electricFence nav_none" id="electricFence" >
	<div class="searchLocate">
		广州市: <input id="cityAddr" type="text" placeholder="请输入要搜索的地址" /> <input
			class="greenBtn" type="button" value="搜索"
			onclick="theLocation('cityAddr','allmap2')" />
	</div>
	<ul class="createFance">
		<li class="createOne" style="float: right;"><a href="javascript:void(0)">围栏设置</a></li>
	</ul>
	<div class="setFance" style="">
		<ul>
			<li style="padding-bottom: 5px">
				<div>
					<p>当前地址:</p>
					<p id="fence_addr" style="font-size: 12px">无</p>
				</div>
			</li>
			
			<li style="padding-bottom: 15px"><em>围栏范围(m):</em>
				<div class="Cradius">
					<p id="fence_radi" style="margin-top:20px;">
						<input type="hidden" class="slider-fence" value="23" />
					</p>
					<img src="" alt="">
				</div></li>
			<li><label for="">预警号码:</label><br /> 
			<input id="fence_phone" type="text" placeholder="请输入电话号码"></li>
			<li style="padding-bottom: 5px">
				<label for="">触发方式:</label><br/> 
				<em>进栏警报</em>
				<input name="fence_check" class = "in" type="radio" > &nbsp;&nbsp;&nbsp;&nbsp;
				<em>出栏警报</em><input name="fence_check" class = "out" type="radio" checked>
			</li>
			<li>
				<table style="font-size: 13px;line-height: 30px">
					<tr>
						<td>围栏监控时间段:</td>
					</tr>
					<tr>
						<td><input name="fence_check_1" type="checkbox"><em>第一时间段</em><br />
							<input type="text" value="00:00" id="start_one" name="start_one" onclick="TimePicker.showTimePicker(this)"
								   readonly/><em>至</em><input type="text"
															  value="00:00" id="end_one" name="end_one" onclick="TimePicker.showTimePicker(this)" readonly/></td>
					</tr>
					<tr>
						<td><input name="fence_check_2" type="checkbox"><em>第二时间段</em><br />
							<input type="text" value="00:00" id="start_two" name="start_two"
								   onclick="TimePicker.showTimePicker(this)" readonly/><em>至</em><input type="text"
																										value="00:00" id="end_two" name="end_two" onclick="TimePicker.showTimePicker(this)" readonly/></td>
					</tr>
					<tr>
						<td><input name="fence_check_3" type="checkbox"><em>第三时间段</em><br />
							<input type="text" value="00:00" id="start_three"
								   name="start_three" onclick="TimePicker.showTimePicker(this)" readonly /><em>至</em><input
									type="text" value="00:00" id="end_three" name="end_three"
									onclick="TimePicker.showTimePicker(this)" readonly/></td>
					</tr>
				</table>
			</li>
			<li style="border-bottom: none;padding-bottom: 0;margin-top: 13px;margin-bottom: 0"><span id="fence_save">保存</span> <span id="fence_cancel">取消</span>
				<input type="hidden" name="fence_number"></li>
		</ul>
	</div>
	
	<div id="allmap2"></div>
</div>

<script>
var jRangeFenceSilder;
seajs.use(['/static/plugins/Jrange/js/jquery.range-min.js'], function() {
	jQuery(function(){
		jRangeFenceSilder = $('.slider-fence').jRange({ 
		    from: 0, 
		    to: 1000, 
		    step: 50, 
		    scale: [0,250,500,750,1000], 
		    format: '%s', 
		    width: 190, 
		    showLabels: true, 
		    showScale: true 
		});
	});
});
</script>