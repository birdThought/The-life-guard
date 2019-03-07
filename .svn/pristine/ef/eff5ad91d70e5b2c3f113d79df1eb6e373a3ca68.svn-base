<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<div class="dialog_contain" style="display: none">
    <div
            style="height:40px;line-height:40px;font-size:16px;margin-bottom:10px">
        <label>服务类型：</label><span id="free_serve"><input name="serviceSelect" type="radio" value="0"
                                   checked/>免费服务&nbsp;&nbsp;&nbsp;</span><span id="notfree_serve"><input name="serviceSelect"
                                                                         type="radio" value="1"/>收费服务</span>
    </div>

    <div id="not_free_service" style="display:none">
        <div class="block_item" data-once>
            <div class="top_msg">
                <input name="serviceFair" type="checkBox" value="count"/> <label>按次</label>
            </div>
            <div class="dialog_contain_price">
                <label>价格：</label><input id="timeCash" type="text" value="10"
                                         class="dialog_input_text"/>元/次
            </div>
        </div>
        <div class="block_item" data-month>
            <div class="top_msg">
                <input name="serviceFair" type="checkBox" value="month"/> <label>按月</label>
            </div>
            <div class="dialog_contain_price">
                <label>价格：</label><input id="monthCash" type="text" value="10"
                                         class="dialog_input_text"/>元/月
            </div>
        </div>
        <div class="block_item" data-year>
            <div class="top_msg">
                <input name="serviceFair" type="checkBox" value="year"/> <label>按年</label>
            </div>
            <div class="dialog_contain_price">
                <label>价格：</label><input id="yearCash" type="text" value="10"
                                         class="dialog_input_text"/>元/年
            </div>
        </div>
    </div>
    <div id="free-item">
        <div class="divide">
            <label style="float:left">服务时长：</label>
            <input id="freeDate" type="text" class="dialog_input_text" style="width:120px" maxlength="3"/><label style="margin-right:15px;">月</label>
            <input name="noLimit" type="checkBox" value="nl"/><label>无限制</label>
        </div>
    </div>
    <div id="classify_divide" class="divide">
    </div>
    <div class="divide">
        <label style="float:left">服务描述：</label>
        <textarea id="service_about"
                  style="resize:none;padding:8px;width:450px;border:1px solid #ccc;margin-left:10px;font-family: 'Microsoft YaHei';"
                  rows="5" placeholder="输入对该服务的介绍"></textarea>
    </div>
    <div class="divide">
        <label style="float:left;font-weight:normal">说明：</label>
        <div style="padding-left:60px">价格区间设置后，用户选取了如1个月的包月套餐，平台会从中收取该服务平台分成费用，用户如果无消费，系统不会收取任何的费用。</div>
    </div>
    <div class="btn_group" style="padding:10px 60px">
        <button id="open_btn" class="open"
                onclick="serviceControl.openService()">确定开通
        </button>
    </div>
</div>