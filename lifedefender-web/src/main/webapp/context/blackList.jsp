<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="blackList nav_none" id="blackList">
	<dl>
		<dt>黑名单</dt>
		<dd>加入黑名单的号码不能正常进行通话和收发短信，会受到限制</dd>
	</dl>
	<div class="blackList_add item-fr"><span>添加号码</span></div>
	<table id="blackLists">
		<thead>
			<tr>
				<th>手机号码</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
<ul class="blackList_form" style="display: none">
	<li><input type="text" name="blackList_number" placeholder="请输入手机号码"></li>
</ul>