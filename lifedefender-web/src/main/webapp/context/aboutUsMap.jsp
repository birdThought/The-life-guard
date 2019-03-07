<style type="text/css">
       .baidu-map-address {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"Microsoft YaHei UI";}
</style>
<div id="allmap" class="baidu-map-address"></div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=D2b4558ebed15e52558c6a766c35ee73"></script>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(113.41873,23.193814);
    map.centerAndZoom(point,15);
//    map.addOverlay(new BMap.Marker(point));
    //单独设定图标的样式
    var icon = new BMap.Icon('/static/images/favicon.ico', new BMap.Size(32, 32), {
        anchor: new BMap.Size(10, 30)
    });
    //创建一个图像标注实例。point参数指定了图像标注所在的地理位置
    var mkr = new BMap.Marker(new BMap.Point(113.41873,23.193814), {
        icon: icon
    });
    //将覆盖物添加到地图中
    map.addOverlay(mkr);
</script>