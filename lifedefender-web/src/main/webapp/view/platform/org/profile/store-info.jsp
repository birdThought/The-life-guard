<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11,jquery.serializejson,layer,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-store-info.css?v=2.2.0">
    <title>门店信息</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article >
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-store" style="background-color: white;">
            <div class="store-infor">
                <form class="store-form" v-if = "results != null" v-cloak>
                    <div class="head-img">
                        <div class="photo">
                            <img class="store-image-show-1" v-if = "results.logo != null" :src="results.logo" alt="" style="width: 120px; height: 120px; border-radius: 50%;">
                            <img class="store-image-show-1" v-else src="/static/platform/v2.2.0/images/template-head.png" alt="" style="width: 120px; height: 120px; border-radius: 50%;">
                        </div>
                        <div>
				    		<span @click = "popupImg(1)" class="chose-id cursor-pointer store-image-show-1">
					    		选择文件
					    	</span>
                            <span>选择您需要上传的图片</span>
                            <input type="file" name = "path" class="path-1 none">
                            <input name = "logo" class="store-image-hidden-1" type="hidden">
                        </div>
                    </div>
                    <ul class="user-infor">
                        <li>
                            <p>门店名称:</p>
                            <input type="text" :value = "results.orgName" style="cursor:not-allowed;" readonly>
                        </li>
                        <li v-if = "orgType == 1">
                            <p >门店法人代表:</p>
                            <input type="text" :value = "results.legalPerson" style="cursor:not-allowed;" readonly>
                            <div class="msg-lay"></div>
                        </li>
                        <li>
                            <p>门店联系方式:</p>
                            <input type="text" name = "contactInformation" :value = "results.contactInformation" style="border: 1px solid #10bb71;">
                            <div class="msg-lay">可修改</div>
                        </li>
                        <li>
                            <p>门店联系人:</p>
                            <input type="text" name = "contacts" :value = "results.contacts" style="border: 1px solid #10bb71;">
                            <div class="msg-lay">可修改</div>
                        </li>
                        <li>
                            <p>门店分类:</p>
                            <input type="text" :value = "results.orgType" style="cursor:not-allowed;" readonly>
                        </li>
                        <li>
                            <p>门店简介:</p>
                            <%--<div class="store-intro">--%>
                                <textarea name = "about" class="store-intro" style="border: 1px solid #10bb71;">{{results.about}}</textarea>
                            <%--</div>--%>
                        </li>
                        <li class="chose-area">
                            <p>门店地址:</p>
                            <div class="fl">
                                <select >
                                    <option >{{results.province}}</option>
                                </select>
                                <select >
                                    <option >{{results.city}}</option>
                                </select>
                                <select >
                                    <option >{{results.district}}</option>
                                </select>
                            </div>
                        </li>
                        <li>
                            <p>详细地址:</p>
                            <div class="fl" style="margin-bottom: 10px;">
                                <div id="r-result">
                                    <input type="text" id="suggestId" size="30" :value = "results.street" style="cursor:not-allowed;" readonly/>
                                </div>
                                <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                            </div>
                            <div class="store-map">
                                <div id="map"></div>
                            </div>
                        </li>
                        <li>
                            <p>详细介绍:</p>
                            <div class="store-intro">
                                <script id="editor" type="text/plain" name="editor"></script>
                            </div>
                        </li>
                        <li class="refer-to" style="margin-left: 170px;">
                            <a href="javascript:void(0)" class="btn" @click = "updateInfo">保存修改</a>
                            <a href="#" class="retest">重新审核</a>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/plugins/uedit/ueditor.config.js"></script>
<script src="/static/plugins/uedit/editor_api.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap"></script>
<script src="/static/platform/v2.2.0/js/org/profile/store-info.js?v=2.2.0"></script>


<script>
    store.init();
    store.vm.results = '${org}' == '' ? '' : ${org};
    store.vm.orgType = '${orgType}';
    var initMap = function () {
        var map = new BMap.Map("map");
        store.vm.initMap(map);
    }
    Editor.init();
</script>
<script>
    $(".main-content").css("minHeight",1930);
    var height=$(".main-content").height();
    $(".main-nav").css({
        borderRight:'1px solid #ddd',
        height:height
    });
    $(".container").css({
        borderLeft:'1px solid #ddd',
        borderRight:'1px solid #ddd',
        borderBottom:'1px solid #ddd',
        height:height
    })

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(9).click();
    } else {
        $('.main-nav li').eq(9).click();
    }
//    common.addBorder();

</script>
