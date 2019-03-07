
registerController.controller('orgAddPageController',['$scope',function ($scope) {

    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数
    };
    $scope.results ={};
    $scope.province = [];
    $scope.city = [];
    $scope.dis = [];
    $scope.orgInfo =null;
    $scope.logo =null;
    $scope.licence =null;
    
    $scope.org = {
            lat : null,
            lng:null,
            map:null,
            address:null,
    }
    
    
    $scope.add={
            logo:null,
            orgName : null,
            type:null,
            orgType:null,
            contacts:null,
            contactInformation:null,
            businessLicense:null,
            address:null,
            /*详细地址:null,*/
            p : "",
            c : "",
            d : "",
        };
    $scope.user={
            userName:null,
            password:null,
            confirm_psw:null
        };

    $scope.init = function () {
        setTimeout(function () {
        }, 300);
        $scope.getProvinces();
    }

    $scope.findByOrgDataList = function () {

        if ($scope.list.p != '-1'){
            $scope.list.p = $scope.list.p.substr(0,2);
        }if ($scope.list.c != '-1'){
            $scope.list.c = $scope.list.c.substr(2,2);
        }if ($scope.list.d != '-1'){
            $scope.list.d = $scope.list.d.substr(4,2);
        }
        var url = "/org/data/list/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, $scope.list, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;

            })
        })
    }

    
    $scope.addOrg = function(){
        var data = {
                logo:$('#logoUrl').val(),
                orgName : $scope.add.orgName,
                type:$scope.add.type,
                orgType:$scope.add.orgType,
                contacts:$scope.add.contacts,
                contactInformation:$scope.add.contactInformation,
                businessLicense:$("#businessLicenseUrl").val(),
                /*详细地址:null,*/
                province : $scope.add.p,
                city : $scope.add.c,
                district : $scope.add.d,
                street:$scope.add.address,
                userName:$scope.user.userName,
                password:$scope.user.password
            };
    
        var url = "/org/addOrg";
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg(result.msg);
                $scope.$apply();
            }
        })
    }
    
    /**初始化地图*//*
    $scope.initMap = function (map, callBack) {
        map.centerAndZoom('北京',12);
        var point = new BMap.Point();
        map.centerAndZoom(point, 15);
        var marker = new BMap.Marker(point);  // 创建标注
        map.addOverlay(marker);               // 将标注添加到地图中
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        map.centerAndZoom(point,8);
        setTimeout(function(){
            map.setZoom(14);
        }, 2000);  //2秒后放大到14级
        map.enableScrollWheelZoom(true);

        this.getLngLat(map, function (lng, lat) {
            org.lng = lng;
            org.lat = lat;
            $scope.getAddressByLngLat(lng, lat, function (address) {
                org.address = address.province + address.city + address.district
                    + address.district + address.street + (address.street_number==''? '': address.street_number);
                $('input[name = "address"]').val(org.address);
            })
        });

    },
    *//**点击地图获取经纬度*//*
    $scope.getLngLat = function (map, callBack) {
        map.addEventListener("click",function(e){
            if (typeof callBack == 'function') {
                callBack(e.point.lng, e.point.lat);
            }
        });
    },
    *//**根据经纬度获取地址名称*//*
    $scope.getAddressByLngLat = function (lng, lat, callBack) {
        $.ajax({
            type : "get",
            async:false,
            url : 'http://api.map.baidu.com/geocoder/v2/?callback=showAddress&location='+ lat +','+ lng +
            '&output=json&pois=1&ak=i7QOOG81qeyTB5QvRmwqnipj',
            dataType : "jsonp",
            success : function(json){
                if (typeof callBack == 'function') {
                    if (json.status == 0) {
                        callBack(json.result.addressComponent);
                    }
                }
            },
            error:function(){
                console.log('fail')
            }
        });
    },
    $scope.searchMap = function (map, address) {
        if (address == null || address == undefined) {
            var province = $('[name="province"] option:selected').text();
            var city = $('[name="city"] option:selected').text();
            var district = $('[name="district"] option:selected').text();
            address = province + city + district;
        }
        map.addControl(new BMap.ScaleControl(), 13);// 添加比例尺控件
        map.addControl(new BMap.MapTypeControl());
        map.enableScrollWheelZoom(true);
        // map.getPoint();
        if (address != '') {
            map.centerAndZoom(address, 15); // 用城市名设置地图中心点
            // var localSearch = new BMap.LocalSearch(map);

            point = new BMap.Point(lng,lat);
             var icon = new BMap.Icon(
             'http://120.76.77.36/plug-in/com/tzcms/images/zd.png',
             new BMap.Size(20, 32), {
             anchor : new BMap.Size(10, 30)
             });
             var pointMarker = new BMap.Marker(point, {
             icon : icon
             });
             map.addOverlay(pointMarker);
             pointMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
        }
    }
    *//**根据地址获取经纬度*//*
    $scope.getLngLatByAddress = function (address) {
        $.ajax({
            type : "get",
            async:false,
            url : 'http://api.map.baidu.com/geocoder/v2/?address='+ address +'&output=json&ak=i7QOOG81qeyTB5QvRmwqnipj&' +
            'callback=showLocation',
            dataType : "jsonp",
            success : function(json){
                if (json.status == 0) {
                    org.lng = json.result.location.lng.toFixed(6);
                    org.lat = json.result.location.lat.toFixed(6);
                }
                if (typeof callBack == 'function') {
                    if (json.status == 0) {
                        callBack(json.result.location);
                    }
                }
            },
            error:function(){
                console.log('fail')
            }
        });
    }
*/

    $scope.getProvinces = function () {
        var url = "/datalist?getProvince";
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.province =result.obj;
                $scope.gerCity();
            })
        })
    };
    $scope.gerCity = function () {
        var code = $scope.add.p;
        var data = {
            provinceCode :  code.substr(0, 2)
        };
        $.ajax({
            async : true,
            cache : false,
            type : 'GET',
            url: "/datalist?getCity",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){

            },
            complete:function(){
            },
            success: function(result) {
                if(result.success){
                    if($scope.city != null){
                        $scope.dis = null;
                    }
                    $scope.city = result.obj;
//                    $scope.add.c = result.obj[0];
                    $scope.$apply();
                    $scope.gerArea();
                    /*$scope.searchMap(org.map)*/
                }
            }
        });
    };
    $scope.gerArea = function () {
        var code = $scope.add.c;
        if (typeof code == "undefined" || code =="" || code == null){
            return;
        }

        var data = {
            provinceCode :  code.substr(0, 4)
        }
        $.ajax({
            async : true,
            cache : false,
            type : 'GET',
            url: "/datalist?getArea",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){
            },
            complete:function(){
            },
            success: function(result) {
                if(result.success){
                    if($scope.dis != null){
                        $scope.dis = null;
                    }
                    $scope.dis = result.obj;
                    $scope.add.d = result.obj[0];
                    $scope.$apply();
                    
                    /*$scope.searchMap(org.map)*/
                }
            }
        });
    };

  /*  $scope.uploadPhoto = function(target){
        upload.uploadFile =function(url,elem,method,callback){
            layui.use('upload',function(){
                var upload = layui.upload;
                var uploadInst=upload.render({
                    elem:elem,
                    url:url,
                    unwrap:true,
                    done:function(res){
                        if(res.success==true){
                            if(typeof callback == "function"){
                                callback(res)
                            }
                        }

                    },
                    error:function(){
                        layer.msg('上传失败，请重新上传')
                    }
                })
            })

        }
    }*/
}]);
registerController.controller('orgRcmController',['$scope',function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数

    };
        $scope.results ={};

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findByOrgRcm();
    };
    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    , count: $scope.page.totalSize
                    , limit: $scope.page.pageSize
                    , theme: '#00bfff'
                    , layout: ['count', 'prev', 'page', 'next', 'skip']
                    , jump: function (obj, first) {
                        if (!first) {
                            $scope.page.pageIndex = obj.curr;
                            $scope.findByOrgRcm();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                , count: $scope.page.totalSize
                , limit: $scope.page.pageSize
                , theme: '#00bfff'
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                        $scope.page.pageIndex = obj.curr;
                        $scope.findByOrgRcm();
                    }
                }
            });
        }
    };
    $scope.findByOrgRcm = function () {
        var url = "/org/data/rcm/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj;
                console.log($scope.results);
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;

            })
        })
    }
}]);