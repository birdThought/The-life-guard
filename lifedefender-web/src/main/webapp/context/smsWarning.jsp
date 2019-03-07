<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="sms nav_none" id="sms">
    <ul>
        <li><span>短信预警</span></li>
    </ul>
    <br/>
    <ul>
        <li><span>说明:&nbsp;设备测量参数出现异常信息时可向亲情联系人发送预警信息</span></li>
    </ul>
    <br/>
    <p>预警短信接收者</p>
    <div class="smsWarning_add item-fr"><span>添加号码</span></div>
    <table id="receiveLists">
        <thead>
            <tr>
                <th>姓名</th>
                <th>手机号码</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        
        </tbody>
    </table>
    <br/>
    <div class="receive_dialog_container" style = "padding: 30px; display: none;">
    <p>
        <select id = "allContact" style = "width: 200px; height:35px; border: solid 1px #ccc;">
        </select>
        <a href="memberControl.do?showWarn"><span class = "addContact" id = "addContact" style = "padding-left: 5px;">添加</span></a>
    </p>
    </div>
    <div id = "receiveList">
    
    <!-- <div style = "border:1px solid gray; padding: 7px; border-radius: 10px; width: 100px;">
        <center><label>张三</label></center>
    </div>
    <div style = "border:1px solid gray; padding: 7px; border-radius: 10px; width: 100px; ">
        <center><label>张三</label></center>
    </div>
    <div style = "border:1px solid gray; padding: 7px; border-radius: 10px; width: 100px; ">
        <center><label>张三</label></center>
    </div>
    <div style = "border:1px solid gray; padding: 7px; border-radius: 10px; width: 100px; ">
        <center><label>张三</label></center>
    </div>
    <div style = "border:1px solid gray; padding: 7px; border-radius: 10px; width: 100px; ">
        <center><label>张三</label></center>
    </div> -->
    </div>
    <br/>
    <p>预警项目</p>
    <hr/>
    <div id = "warningList">
    <div>
        <label><input id = "heartRate" name="smsDevice" type="checkbox" value="heartRate" />心率</label>
    </div>
    <div >
        <label><input id = "tolic" name="smsDevice" type="checkbox" value="tolic" />血压</label>
    </div>
    <div>
        <label><input id = "bloodSugar" name="smsDevice" type="checkbox" value="bloodSugar" />血糖</label>
    </div>
    <div>
        <label><input id = "saturation" name="smsDevice" type="checkbox" value="saturation" />血氧</label>
    </div>
    <div>
        <label><input id = "temperatureBox" name="smsDevice" type="checkbox" value="temperature" />体温</label>
    </div>
    <div>
        <label><input id = "ECG" name="smsDevice" type="checkbox" value="ECG" />心电</label>
    </div>
    </div>
</div>
<style>
    /* input{-webkit-appearance:block;} */
    .setNotice .checkbox_line .icheckbox_minimal-green{
        margin-right:8px
    }
</style>
<!-- <script src = "../static/officialwebsite/js/smsWarning.js"></script> -->
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

 
</script>