<%--
  Created by IntelliJ IDEA.
  User: wenxian.cai
  Date: 2017/4/20
  Time: 11:14
  用户基本资料页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layer,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common-home.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/user-info.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/jeDate/css/jedate.css">
    <script type="text/javascript" src="/static/plugins/jeDate/jedate.min.js"></script>
    <title>用户基本资料</title>
    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>
</head>
<body>
<%@include file="../context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="../context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="../context/menu.jsp"%>
        <%--右边页面start--%>
        <div class="main-content vue-content">
            <div class="main-content-top">
                <a href="#">首页></a><a href="testController.do?usercenter">个人主页</a>><span>基本资料</span>
            </div>
            <form class="form-info">
                <div class="head-layer">
                    <div class="yourself">
                        <img id="upload_img" :src = "photo" alt="">
                        <input type="hidden" name="img_hidden"/>
                    </div>
                    <div>
                        <a href="#">
                            <span onclick="path.click()">浏览</span>
                            <input type="file" name="path" id="path" style="display:none" @change = "uploadPic($event)"/>
                        </a>
                        <p>请选择一张大小不超过1M的JPG、GIF、JPEG、PNG、BMP格式的文件</p>
                    </div>
                </div>
                <ul class="user-infor">
                    <li class="set-head">
                        <h5>头像设置</h5>
                        <div>
                            <div class="photo"><img :src = "photo" alt=""></div>
                            <span id="head-btn" @click = "openHead();" class="cursor-pointer">自定义头像</span>

                        </div>
                    </li>
                    <li>
                        <h5>姓名</h5>
                        <input type="text" :value = "name" name="realName">
                    </li>
                    <li>
                        <h5>性别</h5>
                        <dl>
                            <dd>
                                <input type="radio" class="magic-radio" id="user_1" name="gender" :checked = "gender" value="true">
                                <label for="user_1" >先生</label>
                            </dd>
                            <dd>
                                <input type="radio" class="magic-radio" id="user_2" name="gender" :checked = "!gender" value="false">
                                <label for="user_2" >女士</label>
                            </dd>
                        </dl>
                    </li>
                    <li>
                        <h5>出生日期</h5>
                        <input type="text" class="date" placeholder="监测您健康的重要参数" :value = "birthday" name="birthday">
                        <div class="msg-lay"></div>
                    </li>
                    <li>
                        <h5>所在省份</h5>
                        <select  onchange="getCitys()" class="province" v-model = "selectedProvince" name = "province" v-cloak>
                            <%--<option :value = "565655">{{province}}</option>--%>
                            <option :value = "p.name" v-for = "p in allProvinces" v-cloak>{{p.name}}</option>

                        </select>
                    </li>
                    <li>
                        <h5>所在城市</h5>
                        <select  class="city" onchange="getCountrys()" v-model = "selectedCity" name = "city" v-cloak>
                            <%--<option :value="city">{{city}}</option>--%>
                            <option :value="c.name" v-for = "c in allCitys" v-cloak>{{c.name}}</option>
                        </select>
                    </li>
                    <li>
                        <h5>所在区县</h5>
                        <select  v-model = "selectedCountry" name = "country" v-cloak>
                            <%--<option :value="country">{{country}}</option>--%>
                            <option :value="c.name" v-for = "c in allCountrys" v-cloak>{{c.name}}</option>
                        </select>
                    </li>
                    <li>
                        <h5>身高(cm)</h5>
                        <input type="text" placeholder="输入数值" :value = "height" name = "height">
                    </li>
                    <li>
                        <h5>体重(kg)</h5>
                        <input type="text" placeholder="输入数值" :value = "weight" name = "weight">
                    </li>
                    <li class="sanwei">
                        <h5>三围(cm)</h5>
                        <input type="text" placeholder="输入臀围" :value = "hip" name = "hip">
                        <input type="text" placeholder="输入腰围" :value = "waist" name = "waist">
                        <input type="text" placeholder="输入胸围" :value = "bust" name = "bust">
                    </li>
                    <li>
                        <h5>工作单位</h5>
                        <input type="text" placeholder="工作单位不能超过60个字符" name = "workSpace">
                    </li>
                    <li>
                        <h5>联系方式</h5>
                        <input type="text" :value = "mobile" name = "mobile">
                    </li>
                    <li class="refer-to">
                        <a href="javascript:void(0)" class="btn" @click = "submitInfo()">确认提交</a>
                    </li>
                </ul>
            </form>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/platform/v2.2.0/js/member/usercenter/user-info.js"></script>
<script>
    //初始化vue
    model.getVm().results = '${user}' == '' ? '' : JSON.parse('${user}');
    model.getVm().allProvinces = '${province}' == '' ? '' : JSON.parse('${province}');
    model.getVm().allCitys = '${city}' == '' ? '' : JSON.parse('${city}');
    model.getVm().allCountrys = '${district}' == '' ? '' : JSON.parse('${district}');
    model.getVm().selectedProvince = model.getVm().results.province == null ? '选择省份' : model.getVm().results.province;
    model.getVm().selectedCity = model.getVm().results.city == null ? '选择城市' : model.getVm().results.city;
    model.getVm().selectedCountry = model.getVm().results.county == null ? '选择区县' : model.getVm().results.county;
    /*$('.province').val(model.getVm().results.province);
    $('.city').val(model.getVm().results.city);
    $('.country').val(model.getVm().results.county);*/

    /*$("#head-btn").on("click",function(){
        layer.open({
            type:1,
            title:['自定义头像','font-size:16px;text-align:center'],
            content:$('.head-layer'),
            btn:['确定','取消'],
            area: '500px',
            shade:0.1
        })
    })*/

    jeDate({
        dateCell : ".date",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        minDate:"1900-01-01",
        maxDate: jeDate.now(0),
        startMin:"1900-01-01",
        startMax:jeDate.now(0),
        zindex: 999,
    });
</script>

