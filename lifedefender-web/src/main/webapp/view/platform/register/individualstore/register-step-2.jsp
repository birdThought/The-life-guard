
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,jquery.serializejson,layui,vue"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">
    <%--<link rel="stylesheet" href="/static/plugins/layui/css/layui.css">--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <title>个体门店注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div class="step-1"></div>
        <div class="step-2"></div>
        <div></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-3"></div>
                <p class="pass">1.实名信息认证</p>
            </li>
            <li>
                <div class="step-1"></div>
                <p class="on">2.个人资格认证</p>
            </li>
            <li>
                <div class="step-2"></div>
                <p>3.店铺简介</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.账号申请</p>
            </li><li>
            <div  class="step-2"></div>
            <p>5.等待审核</p>
        </li>
        </ul>
    </div>
    <form class="wrap vue-content form-two-step">
        <ul>
            <li class="chose-area">
                <div class="clearfix">
                    <h5>工作地区</h5>
                    <div class="fl">
                        <select name="province" v-model = "province">
                            <option value="0" selected disabled>选择省份</option>
                            <option v-for = "p in allProvince" :value="p.code">{{p.name}}</option>
                        </select>
                        <select name="city" v-model = "city">
                            <option value="0" selected disabled>选择城市</option>
                            <option v-for = "p in allCity" :value="p.code">{{p.name}}</option>
                        </select>
                        <select name="district" v-model = "district">
                            <option value="0" selected disabled>选择区县</option>
                            <option v-for = "p in allDistrict" :value="p.code">{{p.name}}</option>
                        </select>
                    </div>
                </div>
                <div style="margin-top: 10px;">
                    <h5>详细地址</h5>
                    <input type="text" name = "address" placeholder="请填写详细地址(或点击地图选择地址)" v-model = "street">
                </div>
                <div class="auto-area" id = "map" style="width: 492px; height: 240px; margin-left: 239px">
                    <%--<img src="images/store-map.png" alt="">--%>
                </div>
            </li>
            <%--<li>
                <h5>从事工作类型</h5>
                <select name="2">
                    <option value="选择科室类别">选择科室类别</option>
                    <option value=""></option>
                </select>
            </li>--%>
            <li>
                <h5>职称</h5>
                <select name="professionalName" >
                    <option value="选择您的职称"  disabled selected>选择您的职称</option>
                    <option value="医生">医生</option>
                    <option value="护士">护士</option>
                    <option value="健康管理师">健康管理师</option>
                    <option value="膳食健康师">膳食健康师</option>
                    <option value="公共健康咨询师">公共健康咨询师</option>
                    <option value="理疗师">理疗师</option>
                    <option value="中医师">中医师</option>
                    <option value="健身教练">健身教练</option>
                    <option value="健康管理师">健康管理师</option>
                    <option value="慢病诊疗专家(中医、西医、养生师)">慢病诊疗专家(中医、西医、养生师)</option>
                    <option value="瑜伽教练">瑜伽教练</option>
                    <option value="药剂师">药剂师</option>
                    <option value="全科医生保姆">全科医生保姆</option>
                    <option value="特级看护">特级看护</option>
                    <option value="营养师">营养师</option>
                </select>
            </li>
            <li class="up-licence">
                <h5>营业资格证照</h5>
                <div class="img-container fl">
                    <div class="up-btn">
                        <%--<input type="file">
                        <input type="button" value="选择文件">--%>
                        文件大小1Mb以内,最多只能传2张图片
                    </div>
                    <div class="img-list clearfix">
                        <div class="fl">
                            <img class="upload-img-1 cursor-pointer" @click = "popupImg(1)" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                            <input type="file" name="path" class="path-1"/>
                            <input type="hidden" name = "professionalPic" class="hidden-img-1"/>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <h5>专业擅长</h5>
                <textarea name="expertise" v-model = "profession"></textarea>
                <p class="count ">最多200字，已输入<em style="color: orange">{{countOfProfession}}</em>个字</p>
            </li>
            <li>
                <h5>个人简介</h5>
                <textarea name="about" v-model = "introduction"></textarea>
                <p  class="count">最多200字，已输入<em style="color: orange">{{countOfIntroduction}}</em>个字</p>
            </li>
            <li class="step-2-btn">
                <a href="javascript:void(0)" class="btn-1">返回修改</a>
                <a href="javascript:void(0)" class="btn-1 btn-2" @click = "submitTwo">保存下一步</a>
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script src="/static/platform/v2.2.0/js/register/individualstore/individual-store-register.js?v=2.2.1"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/common/js/common.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap"></script>
<script>
//    layui.use('layer', function () {
////        store.layer = layui.layer;
//    });
    store.init();
    store.initStepTwo();
    var initMap = function () {
        var map = new BMap.Map("map");
        store.vm.initMap(map);
        store.vm.map = map;
    }
</script>

