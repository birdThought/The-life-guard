<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>.doctor-sign{border:1px solid #ddd;width:350px;padding:10px}#business_type{width:100px}#orderCenterPopup{padding-left:20px}#orderCenterContent ul li span{display:inline-block;width:84px;text-align:center;line-height:36px}</style>
<div class="orderCenter" ng-controller="customerManagerController" ng-init="init()" style="padding: 16px 0 0 20px;"
     ng-cloak>
    <div class="titleShow">
        <label style="border-left:2px solid #0093ff;padding-left: 6px;margin-bottom: 10px;display: inline-block;font-size: 18px;color: #0093ff;"
               class="action">
            客服管理
        </label>
        <div style="display:inline-block;">
            <button style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;"
                    class="search search-btn cursor-pointer" ng-click="addCustomerPopup(userInfo.agentId)">添加客服
            </button>
        </div>
    </div>
    <div class="orderCenterBottom">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>姓名</td>
                <td>登陆账号</td>
                <td>创建时间</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="o in data">
                <td>{{$index+1}}</td>
                <td>{{o.name}}</td>
                <td>{{o.userName}}</td>
                <td>{{o.createDate | date:'yyyy-MM-dd'}}</td>
            </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <div id="orderCenterPopup" style="display:none;height:auto">
        <div id="orderCenterContent">
            <ul>

                <li class="clearfix" style="margin-top: 15px; text-align:center">
                    <img id="portrait" style="width: 110px;
    height: 110px;
    border-radius: 50%;" src="/static/images/template-head.png">
                    <p style="color:#999;font-size:16px;line-height:30px">头像为审核条件之一，请上传您真实照片作为头像</p>
                    <input id="upload_img" type="button" value="选择头像" style="width: 150px;border-radius: 2px;height: 38px;
    border: 1px solid #369239;
    background-color: #369239;
    line-height: 38px;
    text-align: center;
    color: #fff;
    outline: none;
    cursor: pointer;
    margin-right: 0 auto;">
                </li>
                <li  class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                姓名
            </span>
                    <input type="text" id="name" class="doctor-sign Select-Options" placeholder="请输入姓名">
                </li>
                <li class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                登陆账号
            </span>
                    <input type="text" id="username" class="doctor-sign Select-Options"  placeholder="请输入帐号名称">
                </li>
                <li class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                密码
            </span>
                    <input type="password" id="password" class="doctor-sign Select-Options" placeholder="请输入密码">
                </li>
                <li class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                确认密码
            </span>
                    <input type="password" id="rePassword" class="doctor-sign Select-Options" placeholder="请再次输入密码">
                </li>
                <li class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                用户角色
            </span>
                    <select class="doctor-sign Select-Options" lay-search ng-model="select.reloId" ng-options="c.id as c.name for c in customer" >
                        <option value="">直接选择或搜索</option>
                    </select>

                </li>

                <li class="clearfix" style="text-align: center; margin-top: 50px;">
                    <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px"
                           type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                           value="提交数据" ng-click="submitData()">
                </li>
            </ul>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        upload.uploadFile('/common/uploadFile/img'
            , $('#upload_img'), 'POST', function (res) {
                $('#portrait').attr('src', res.obj)
            });
    });
    var upload = {};
    upload.uploadFile = function (url, elem, method, callback) {
        layui.use('upload', function () {
            var upload = layui.upload;
            var uploadInst = upload.render({
                elem: elem,
                url: url,
                unwrap: true,
                done: function (res) {
                    if (res.success == true) {
                        if (typeof callback == "function") {
                            callback(res)
                        }
                    }

                },
                error: function () {
                    layer.msg('上传失败，请重新上传')
                }
            })
        })

    }
</script>