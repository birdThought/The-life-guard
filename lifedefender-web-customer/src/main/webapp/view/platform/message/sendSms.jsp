<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<div class="right_body orderCenter" ng-controller="sendSmsController" ng-init = "">
    <div class="right_title right_top">
        <div class="titleShow">
            <label style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: block;font-size: 18px;color: #0093ff;" class="action">
                发送短信
            </label>
        </div>

    </div>

    <div class = "" style="">
        <form class="u-from-send-sms" style="padding-left: 0px;">
            <div class="param_set" style="margin-top: 20px;margin-bottom: 30px;">
                <label class="param" style="font-size: 16px"><span class="warn">*</span>接收号码：</label>
                <input placeholder="请输入要填写的手机号····" style="width: 340px;border: 1px solid #ddd;padding: 8px 5px;" id="mobile" type="text" name="mobile" style="width: 490px;"/>
            </div>
            <div class="param_set">
                <label class="param" style="float: left;font-size: 16px"><span class="warn">*</span>内容：</label>
                <textarea id="content" type="text" name="content" placeholder="请输入要填写的内容·····" > </textarea>
                <div style="width: 300px;font-size: 12px;color: #9a9a9a;">
                    <label style="font-size: 16px;">编码格式</label>
                    <br><br>
                    <label>(1)温馨提示：用户（?）?出现异常，?，请多加关注！祝您阖家幸福健康！（生命守护）回复TD拒收。</label>
                    <br><br>
                    <label>(2)尊敬的用户，您注册验证码为?，请在?分钟内使用</label>
                </div>
            </div>
        </form>
        <div>
            <button class="search-btn" ng-click = "sendSms()" style="margin-left: 60px;border: none; margin-top: -160px;right: 842px;bottom: 380px;position:absolute;background:#3a87fc;padding: 4px 10px;color: #fff;">发送</button>
        </div>
    </div>

</div>