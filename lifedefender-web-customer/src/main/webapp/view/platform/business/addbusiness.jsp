<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<div class="right_body" ng-controller="businessAddController" ng-init="init()">
    <div class="right_title">
        <label class="action">
            渠道商添加
        </label>
    </div>
    <div class="item_contain" style="margin-top: 15px;">
        <form method="post" id="orgForm">
            <div style="padding-top:20px;padding-left:20px">
                <div class="param_set">
                    <label class="param" style="float:left;">渠道logo：</label>
                    <input id="logo"  type="file" style="display:inline-block;visibility: hidden;"/>
                    <div  style="background-color:#f0f0f0;width:150px;height:150px;display:inline-block;float:left;margin-bottom:15px;cursor:pointer">
                        <img id="upload_img" style="width:150px;height:150px;"/>
                    </div>
                </div>
                <div class="param_set" style="clear:both;">
                    <label class="param"><span class="warn">*</span>渠道名称：</label>
                    <input name="orgName" type="text" ng-model="Display.name" placeholder="请输入渠道名称"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>渠道类型：</label>
                    <select name="type" class="select-btn" ng-model="Display.type" ng-init="Display.type='1'" style="width:250px;">
                        <option value="1">推广渠道</option>
                        <option value="2">会员销售渠道</option>
                        <option value="4">分析服务渠道</option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>状态：</label>
                    <select name="type" class="select-btn" ng-model="Display.status" ng-init="Display.status='0'" style="width:250px;">
                        <option value="0">正常</option>
                        <option value="1">禁用</option>
                    </select>
                </div>

                <div class="param_set">
                    <label class="param"><span class="warn">*</span>联系人：</label>
                    <input type="text" ng-model="Display.contactMan" placeholder="请输入联系人" name="contacts"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>联系号码：</label>
                    <input type="text" ng-model="Display.phone" placeholder="请输入联系号码" name="contactInformation"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>邮箱：</label>
                    <input type="email" ng-model="Display.email"  placeholder="请输入邮箱地址" name="contactInformation"/>
                </div>

                </div>

                <!--账户信息-->
                <div class="item_contain" style="clear: both">
                    <div class="item_title">
                        <label class="title"> 账户信息 </label>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>登录账户：</label><input
                            ng-model="Display.userName" name="userName" type="text" placeholder="请输入账户、英文数字组合"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>密码：</label><input
                            ng-model="Display.password" type="password" placeholder="输入密码"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>确认密码：</label><input
                            ng-model="Display.pwd" name="confirm_psw" type="password" placeholder="确认一下密码"/>
                    </div>
                </div>

                <div style="padding:10px 110px 20px;clear: both;">
                    <input class="save" value="保存" ng-click="addBusiness()" type="button"/><input
                        class="back" value="返回" type="button" onclick="history.go(-1)"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function(){
        upload.uploadFile('/common/uploadFile/img'
            ,$('#upload_img'),'POST',function(res){
                $('#upload_img').attr('src',res.obj)
            });
    });
    var upload = {};
    upload.uploadFile =function(url,elem,method,callback){
        layui.use('upload',function(){
            var upload = layui.upload;
            var uploadInst=upload.render({
                elem:elem,
                url:url,
                unwrap:true,
                done:function(res){
                        if(typeof callback == "function"){
                            callback(res)
                        }
                },
                error:function(){
                    layer.msg('上传失败，请重新上传')
                }
            })
        })
    }
</script>
