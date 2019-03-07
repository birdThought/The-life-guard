<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap"></script> -->
<div class="right_body" ng-controller="orgAddPageController" ng-init='init()'>
    <div class="right_title">
        <label class="action">
            门店添加
        </label>
    </div>
    <div class="item_contain" style="margin-top: 15px;">
        <form method="post" id="orgForm">
            <div style="padding-top:20px;padding-left:20px">
                <input id="logoUrl" ng-model="add.logo" type="hidden" />
                <input id="businessLicenseUrl" ng-model="add.businessLicense" type="hidden" />
                <div class="param_set">
                    <label class="param" style="float:left;">门店logo：</label>
                    <input id="logo" type="file" style="display:inline-block;visibility: hidden;"/>
                    <div  style="background-color:#f0f0f0;width:150px;height:150px;display:inline-block;float:left;margin-bottom:15px;cursor:pointer">
                        <img id="upload_img" style="width:150px;height:150px;"/>
                    </div>
                </div>
                <div class="param_set" style="clear:both;">
                    <label class="param"><span class="warn">*</span>门店名称：</label>
                    <input ng-model="add.orgName" type="text" placeholder="请输入门店名称"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>门店类型：</label>
                    <select name="type" ng-model="add.type" class="select-btn" style="width:250px;">
                        <option value="1">门店</option>
                        <option value="2">个体门店</option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>门店性质：</label>
                    <select name="orgType" ng-model="add.orgType" class="select-btn" style="width:250px;">
                        <option value="慢病康复">慢病康复</option>
                        <option value="健康养生">健康养生</option>
                        <option value="减肥塑体">减肥塑体</option>
                        <option value="居家养老">居家养老</option>
                        <option value="癌症康复">癌症康复</option>
                    </select>
                </div>
                <%--<div class="param_set">
                    <label class="param"><span class="warn">*</span>证件类型：</label>
                    <select class="select-btn">
                        <option>身份证</option>
                    </select>
                    <input type="text" id="contact" placeholder="请输入身份证"/>
                </div>--%>
                <!-- <div class="param_set">
                    <label class="param">注册时间：</label>
                    <input type="text" ng-model="add.createDate" readonly="readonly" name="createDate" id="createDate"/>
                </div> -->

                <div class="param_set">
                    <label class="param"><span class="warn">*</span>法人代表：</label>
                    <input type="text" placeholder="请输入联系人" ng-model="add.contacts" name="contacts"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>联系号码：</label>
                    <input type="text" id="phone" placeholder="请输入联系号码" ng-model="add.contactInformation" name="contactInformation"/>
                </div>
                <div class="param_set">
                    <label class="param">地区：</label>
                    <select class="select-btn" id="province" name="province" ng-change="gerCity()" ng-model="add.p" ng-init="add.p='110000'">
                        <option ng-repeat='r in province' value="{{r.code}}">{{r.name}}</option>
                    </select>
                    <select id="city" class="select-btn" name="city" ng-change="gerArea()" ng-model="add.c" > <!-- ng-init="add.c='-1'" -->
                        <option value="-1">--请选择--</option>
                        <option ng-repeat='r in city' value="{{r.code}}">{{r.name}}</option>
                    </select>
                    <select class="select-btn" name="district" ng-model="add.d" >  <!-- ng-init="add.d='-1'" -->
                        <option value="-1">--请选择--</option>
                        <option ng-repeat='r in dis' value="{{r.code}}">{{r.name}}</option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param" style="float:left;">详细地址：</label>
                    <!-- <textarea name="street" class = "street" readonly="readonly" class="big-txt-area" rows="3"  style="width:255px;background-color: #b0b0b0;"></textarea>
                    <label class = "map-address" style="color: blue; font-size: 14px; cursor: pointer">根据地图获取</label>
                    <div class="div-map" style="display: none">
                    <div>
                        <input type="text" name="address" class="search-content-org"  placeholder="请输入要搜索的地址" style="width: 180px; margin-left: 112px;">
                        <input type="button" class="save search" value="搜索" style>
                    </div>
                    <div id="map" style="width: 400px; height: 200px; margin-left: 112px;"></div>
                    </div>
                    <input type="hidden" name="longitude" class="map-lng">
                    <input type="hidden" name="latitude" class="map-lat"> -->
                    <input type="text" id="address" placeholder="请输入详细地址" ng-model="add.address" name="address"/>
                </div>

                <%--<div class="param_set">
                    <label class="param"><span class="warn">*</span>运营方式：</label>
                    <select class="select-btn">
                        <option>非独立性运营</option>
                    </select>
                </div>--%>
                <div class="param_set" style="clear: both;">
                    <label class="param" style="float:left;">营业执照：</label>
                    <input id="businessLicense" type="file" style="display:inline-block;visibility: hidden;"/>
                    <div  style="background-color:#f0f0f0;width:150px;height:150px;display:inline-block;float:left;margin-bottom:15px;cursor:pointer">
                        <img id="upload_img1" style="width:150px;height:150px;"/>
                    </div>
                </div>

                <!--账户信息-->
                <div class="item_contain" style="clear: both">
                    <div class="item_title">
                        <label class="title"> 账户信息 </label>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>登录账户：</label><input
                            id="userName" ng-model="user.userName" name="userName"  type="text" placeholder="输入账户"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>密码：</label><input
                            id="password" ng-model="user.password" name="password" type="password" placeholder="输入密码"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>确认密码：</label><input
                            id="confirm_psw" ng-model="user.confirm_psw" name="confirm_psw" type="password" placeholder="确认一下密码"/>
                    </div>
                </div>

                <div style="padding:10px 110px 20px;clear: both;">
                    <input class="save" value="保存" type="button" ng-click="addOrg()" /><input
                        class="back" value="返回" type="button" onclick="history.go(-1)"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>

   /* province = '${province}' == '' ? '' : JSON.parse('${province}');
   city = '${city}' == '' ? '' : JSON.parse('${city}');
   district = '${district}' == '' ? '' : JSON.parse('${district}'); 
   
   var initMap = function () {
       var map = new BMap.Map("map");
       initMap(map);
       org.map = map;
   }
   */

    $(document).ready(function(){
        upload.uploadFile('/common/uploadFile/img'
            ,$('#upload_img'),'POST',function(res){
                $('#upload_img').attr('src',res.obj)
                $("#logoUrl").val(res.obj);
            }),
        upload.uploadFile('/common/uploadFile/img'
                ,$('#upload_img1'),'POST',function(res){
                $('#upload_img1').attr('src',res.obj)
                $('#businessLicenseUrl').val(res.obj);
            });
    });
    var upload = {};
    upload.uploadFile =function(url,elem,method,callback){
        layui.use('upload',function(){
            var upload = layui.upload;
            var uploadInst=upload.render({
                elem:elem,
                url:url,
                field:'uploadFile',
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
