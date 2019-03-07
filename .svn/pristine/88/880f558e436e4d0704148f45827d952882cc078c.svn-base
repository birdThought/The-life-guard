
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11"></t:base>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Title</title>
</head>
<style>
    *{ margin:0; padding:0;}html,body {width:100%;height:100%;font-size:12px;font-family:Microsoft Yahei;}.cb {clear: both;}
    #mapSquare {margin: 0 auto;width:100%;}input,button{outline: none}
    .search_area {z-index:999;width:99%;padding-left:1%;}
    #lonlat,#lat {display: inline-block;margin-top:5PX;color:purple;width:60%;padding-left:1%;border:none;float:left;}
    #address {color:purple;}
    #search {height:30px;position:relative;border-radius:0px;width:24.5%;float: left;}
    #search{color:#fff;font-weight:bold;background-color:#99BED1;outline:none;border:0px solid #99BED1}
    #where{padding-left:1%;width:73%;height:30px; border-radius:4px;border:0px solid #ddd;float: left}
    .BMap_noprint {margin-top:50px;z-index:999!important;}
    .BMap_noprint>div{float:none!important;margin-top:5%;}
    .BMap_noprint>div:first-child{margin-top:0;}
</style>
<body>
<div id="mapSquare">
    <form action="" method="get" id="InputSite">
        <div class="search_area">
            <input id="where" class="address" name="where" type="text" placeholder="请输入公司的地址"/>
            <input id="search" type="button" value="点击查询" onClick="org.searchMap(org.map, document.getElementById('where').value);" />
            <%--<span>经度：</span>
            <input id="lonlat" name="lonlat" type="text" class="lng"><br>
            <span>纬度：</span>
            <input type="text" id="lat" class="lat">--%>
        </div>
        <div class="cb"></div>
        <p style="position: fixed; right: 0; width: 25%;z-index: 999;">
            <span style="float: left; width: 36%;margin-top: 1%">经度:</span>
            <input id="lonlat" name="lonlat" type="text" class="lng"><br>
            <span style="float: left; width: 36%;margin-top: 1%">纬度:</span>
            <input type="text" id="lat" class="lat"><br>
        </p>
        <%--<div class="cb"></div>--%>
    </form>
</div>
<div style="height:100%;border:1px solid gray" id="container"></div>
</body>
</html>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj"></script>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
<script>
    var map = new BMap.Map("container");
    org.map = map;
    map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
    map.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用。
    org.initMap(map);

    //建立一个自动完成的队形
    var ac = new BMap.Autocomplete(
        {
            "input":"where",
            "location":map
        }
    );
    ac.addEventListener('onhighlight',function (e) {
        var str =""; var value = "";
        var _value = e.fromitem.value;
        if(e.fromitem.index>-1){
            value = _value.province+_value.city+_value.distrit+_value.street+_value.business
        }
        str ="FromItem</br>index="+e.fromitem.index+"</br>value"+value;
        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        where.innerHTML = str;
    });
    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        where.innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
        setPlace();
    });

    function setPlace(){
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }

    setTimeout(function () {
        org.searchMap(map, '${address}');
    }, 500);

    org.getLngLat(map, function (lng, lat) {
        org.lng = lng;
        org.lat = lat;
        org.getAddressByLngLat(lng, lat, function (address) {
            org.address = address.province + address.city + address.district
                + address.district + address.street + (address.street_number==''? '': address.street_number);
            $('#where').val(org.address);
            $('.lng').val(lng);
            $('.lat').val(lat);
        })
    })
</script>