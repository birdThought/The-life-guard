/**
 * 个体门店注册
 * @Author wenxian.cai
 * @Date 2017/6/20 11:57
 */

var store = {};

/**初始化*/
store.init = function () {
    layui.use('layer', function () {
        // layer = layui.layer;
    });

    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';
    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-1').attr('src', results.obj);
        $('.hidden-img-1').val(results.obj);
    });

    //绑定图片2
    element = '.path-2';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-2').attr('src', results.obj);
        $('.hidden-img-2').val(results.obj);
    });

    //绑定图片2
    element = '.path-3';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-3').attr('src', results.obj);
        $('.hidden-img-3').val(results.obj);
    });
}

/**初始化第一步页面*/
store.initStepOne = function () {

}
/**初始化第二步页面*/
store.initStepTwo = function () {
    store.vm.listProvince();
}
store.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        key: 'lifekeeper.reg',
        countOfProfession: 0,
        countOfIntroduction: 0,
        profession: null,
        introduction: null,
        allProvince: null,
        allCity: null,
        allDistrict: null,
        province: 0,
        city: 0,
        district: 0,
        map: null,
        street: '',
        countOfOrgAbout: 0,
        orgAbout: null,
        lng: null, //经度
        lat: null  //纬度

    },
    methods: {
        /**点击图片触发事件*/
        popupImg: function (type) {
            switch (type) {
                case 1:
                    $('.path-1').click();
                    break;
                case 2:
                    $('.path-2').click();
                    break;
                case 3:
                    $('.path-3').click();
                    break;
                case 4:
                    $('.path-4').click();
                    break;
            }
        },
        /**注册协议*/
        popupAgreement: function () {
            var href = "/register?showOrgAgreement";
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                moveType: 1,
                area: ["1000px", "800px"],
                content: href
            });
        },
        /**开始申请*/
        apply: function () {
            layer.confirm('确定开始申请？', function () {
                var json = $('.apply').serializeJSON();
                if ($.trim(json.photo) == '') {
                    layer.msg('请选择个人头像');
                    return;
                }
                if ($.trim(json.realName) == '') {
                    layer.msg('请填写个人姓名');
                    return;
                }
                if ($.trim(json.idCard) == '') {
                    layer.msg('请请填写个人身份证号码');
                    return;
                }
                if ($.trim(json.idCardPicOne) == '' || $.trim(json.idCardPicTwo) == '') {
                    layer.msg('请选择个人身份证件照');
                    return;
                }
                var isVerified = store.vm.verifyIdCard(json.idCard);
                if (!isVerified.isSuccess) {
                    layer.msg(isVerified.error);
                    return;
                }
                var info = store.vm.getInfoByIdCard(json.idCard);
                json.sex = info.gender;
                json.birthday = info.birthday;

                $.ajax({
                    async : true,
                    cache : false,
                    type : 'POST',
                    url: "/register/individual/store/apply",
                    data: JSON.stringify(json),
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load()
                    },
                    complete:function(){
                        layer.closeAll("loading")
                    },
                    success: function(result) {
                        layer.msg(result.msg);
                        if(result.success){
                            //将临时数据存储在浏览器缓存
                            var obj = {};
                            obj.user = json;
                            setLocalStorage(store.vm.key, obj);
                            window.location.href = '/register/individual/store/steptwo';
                        }
                    }
                });
            });
        },
        /**提交第二步数据*/
        submitTwo: function() {
            store.getLngLatByAddress($('input[name="address"]').val());
            layer.confirm('确定保存？', function () {
                var json = $('.form-two-step').serializeJSON();
                if (json.province == null) {
                    layer.msg('请选择省份');
                    return;
                }
                if (json.city == null) {
                    layer.msg('请选择城市');
                    return;
                }
                if (json.district == null) {
                    layer.msg('请选择地区');
                    return;
                }
                if ($.trim(json.address) == '') {
                    layer.msg('请填写详细地址');
                    return;
                }
                if ($.trim(json.professionalName) == 'undefined' || $.trim(json.professionalName) == '') {
                    layer.msg('请选择职称');
                    return;
                }
                if ($.trim(json.professionalPic) == '') {
                    layer.msg('请上传营业资格证照');
                    return;
                }
                if ($.trim(json.expertise) == '') {
                    layer.msg('请填写专业擅长');
                    return;
                }
                if ($.trim(json.about) == '') {
                    layer.msg('请填写个人简介');
                    return;
                }

                $.ajax({
                    async: true,
                    cache: false,
                    type: 'POST',
                    url: "/register/individual/store/apply/two",
                    data: JSON.stringify(json),
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    beforeSend: function () {
                        layer.load()
                    },
                    complete: function () {
                        layer.closeAll("loading")
                    },
                    success: function (result) {
                        layer.msg(result.msg);
                        if (result.success) {
                            //将临时数据存储在浏览器缓存
                            var obj = getLocalStorage(store.vm.key);
                            obj.user.address = json.address;
                            obj.user.professionalName = json.professionalName;
                            obj.user.professionalPic = json.professionalPic;
                            obj.user.expertise = json.expertise;
                            obj.user.about = json.about;
                            var org = {};
                            org.province = json.province.substr(0, 2);
                            org.city = json.city.substr(2, 2);
                            org.district = json.district.substr(4, 2);
                            org.address = json.address;
                            org.longitude = store.vm.lng;
                            org.latitude = store.vm.lat;
                            obj.org = org;
                            setLocalStorage(store.vm.key, obj);
                            window.location.href = '/register/individual/store/stepthree';
                        }
                    }
                });
            });
        },
        /**提交第三步数据*/
        submitThree: function() {
            layer.confirm('确定保存？', function () {
                var json = $('.form-three-step').serializeJSON();
                if ($.trim(json.orgName) == '') {
                    layer.msg('请填写店铺名称');
                    return;
                }
                if ($.trim(json.orgType) == 'undefined' || $.trim(json.orgType) == '') {
                    layer.msg('请选择店铺分类');
                    return;
                }
                if ($.trim(json.workField) == 'undefined' || $.trim(json.workField) == '') {
                    layer.msg('请选择店铺从事领域');
                    return;
                }
                if ($.trim(json.about) == '') {
                    layer.msg('请填写店铺简介');
                    return;
                }
                //将临时数据存储在浏览器缓存
                var obj = getLocalStorage(store.vm.key);
                var org = obj.org;
                org.orgName = json.orgName;
                org.orgType = json.orgType;
                org.workField = json.workField;
                org.about = json.about;
                obj.org = org;

                /*json.province = org.province;
                json.city = org.city;
                json.district = org.district;*/
                $.ajax({
                    async: true,
                    cache: false,
                    type: 'POST',
                    url: "/register/individual/store/apply/three",
                    data: JSON.stringify(org),
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    beforeSend: function () {
                        layer.load()
                    },
                    complete: function () {
                        layer.closeAll("loading")
                    },
                    success: function (result) {
                        layer.msg(result.msg);
                        if (result.success) {
                            //将临时数据存储在浏览器缓存
                            var orgId = result.obj;
                            obj.user.orgId = orgId;
                            setLocalStorage(store.vm.key, obj);
                            window.location.href = '/register/individual/store/stepfour';
                        }
                    }
                });
            });
        },
        /**提交第四步数据*/
        submitFour: function() {
            var $reg= /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,17}$/;
            var mobileReg = /^[1][3,4,5,7,8][0-9]{9}$/;
            layer.confirm('确定提交？', function () {
                var json = $('.form-four-step').serializeJSON();
                if (!$reg.test($.trim(json.userName))) {
                    layer.msg('登录名不合法');
                    return;
                }
                if ($.trim(json.password)  == '') {
                    layer.msg('请填写登录密码');
                    return;
                }
                if ($.trim(json.confirmPassword)  == '') {
                    layer.msg('请确认登录密码');
                    return;
                }
                if ($.trim(json.password)  != $.trim(json.confirmPassword)) {
                    layer.msg('两次填写的密码不一致');
                    return;
                }
                if (!mobileReg.test($.trim(json.mobile))) {
                    layer.msg('手机号码不合法');
                    return;
                }
                if ($.trim(json.verifyCode) == '') {
                    layer.msg('请填写验证码');
                    return;
                }
                if ($.trim(json.userType) == '') {
                    layer.msg('请选择申请类型');
                    return;
                }
                var obj = getLocalStorage(store.vm.key).user;
                if (obj == null) {
                    return;
                }
                json.photo = obj.photo;
                json.realName = obj.realName;
                json.idCard = obj.idCard;
                json.idCardPicOne = obj.idCardPicOne;
                json.idCardPicTwo = obj.idCardPicTwo;
                json.address = obj.address;
                json.professionalName = obj.professionalName;
                json.professionalPic = obj.professionalPic;
                json.expertise = obj.expertise;
                json.about = obj.about;
                json.orgId = obj.orgId;
                json.sex = obj.sex;
                json.birthday = obj.birthday;
                $.ajax({
                    async: true,
                    cache: false,
                    type: 'POST',
                    url: "/register/individual/store/apply/four",
                    data: JSON.stringify(json),
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    beforeSend: function () {
                        layer.load()
                    },
                    complete: function () {
                        layer.closeAll("loading")
                    },
                    success: function (result) {
                        layer.msg(result.msg);
                        if (result.success) {
                            setLocalStorage(store.vm.key, null);
                            window.location.href = '/register/individual/store/stepfive';
                        }
                    }
                });
            });
        },
        /**获取省份*/
        listProvince: function () {
            $.ajax({
                async : true,
                cache : false,
                type : 'GET',
                url: "/commonControl?getProvince",
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    // layer.load()
                },
                complete:function(){
                    // layer.closeAll("loading")
                },
                success: function(result) {
                    if(result.success){
                        store.vm.allProvince = result.obj;
                    }
                }
            });
        },
        /**获取城市*/
        listCity: function (code) {
            var data = {
                provinceCode: code.substr(0, 2)
            }
            $.ajax({
                async : true,
                cache : false,
                type : 'GET',
                url: "/commonControl?getCity",
                data: (data),
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                beforeSend:function(){
                    // layer.load()
                },
                complete:function(){
                    // layer.closeAll("loading")
                },
                success: function(result) {
                    if(result.success){
                        store.vm.allCity = result.attributes.city;
                        store.vm.city = result.attributes.city[0].code;
                    }
                }
            });
        },
        /**获取地区*/
        listDistrict: function (code) {
            var data = {
                cityCode: code.substr(0, 4)
            }
            $.ajax({
                async : true,
                cache : false,
                type : 'GET',
                url: "/commonControl?getDistrict",
                data: data,
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                beforeSend:function(){
                    // layer.load()
                },
                complete:function(){
                    // layer.closeAll("loading")
                },
                success: function(result) {
                    store.vm.allDistrict = result;
                    store.vm.district = result[0].code;
//                    if (result.success) {
//                        store.vm.allDistrict = result.obj;
//                        store.vm.district = result.obj[0].code;
//                    }
                }
            });
        },
        /**初始化地图*/
        initMap: function (map, callBack) {
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
                store.vm.lng = lng;
                store.vm.lat = lat;
                store.vm.getAddressByLngLat(lng, lat, function (address) {
                    store.vm.street = address.province + address.city + address.district
                        + address.district + address.street + (address.street_number==''? '': address.street_number);
                })
            });

        },
        /**点击地图获取经纬度*/
        getLngLat: function (map, callBack) {
            map.addEventListener("click",function(e){
                if (typeof callBack == 'function') {
                    callBack(e.point.lng, e.point.lat);
                    }
            });
        },
        /**根据经纬度获取地址名称*/
        getAddressByLngLat: function (lng, lat, callBack) {
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
        searchMap: function (map, address) {
            map.addControl(new BMap.ScaleControl(), 13);// 添加比例尺控件
            map.addControl(new BMap.MapTypeControl());
            map.enableScrollWheelZoom(true);
            if (address != '') {
                map.centerAndZoom(address, 15); // 用城市名设置地图中心点
                /*point = new BMap.Point(lng,lat);
                 var icon = new BMap.Icon(
                 'http://120.76.77.36/plug-in/com/tzcms/images/zd.png',
                 new BMap.Size(20, 32), {
                 anchor : new BMap.Size(10, 30)
                 });
                 var pointMarker = new BMap.Marker(point, {
                 icon : icon
                 });
                 map.addOverlay(pointMarker);
                 pointMarker.setAnimation(BMAP_ANIMATION_BOUNCE);*/
            }
        },

        /**获取验证码*/
        getCode: function () {
            var mobile = $('input[name="mobile"]').val();
            var cacheType = 'register';
            var userType = 'org';
            if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
                layer.msg('手机号码格式不合法')
                return;
            }
            var data = "mobile="+mobile+'&temp='+new Date()+'&cache='+cacheType + '&userType=' + userType;
            jQuery.ajax({
                async : true,
                cache : false,
                type : 'POST',
                url: "/register?sendValidCode",
                data: data,
                dataType: 'json',
                beforeSend:function(){
                    layer.load()
                },
                complete:function(){
                    layer.closeAll("loading")
                },
                success: function(result) {
                    layer.closeAll()
                    layer.msg(result.msg);
                    if(result.success){
                        sendMessage($('.get-code'));
                    }
                }
            });
        },
        /**根据省份证获取个人信息*/
        getInfoByIdCard: function (card) {
            //获取出生日期
            var birthday=card.substring(6, 10) + "-" + card.substring(10, 12) + "-" + card.substring(12, 14);
            var gender = null;
            //获取性别
            if (parseInt(card.substr(16, 1)) % 2 == 1) {
                gender = 1;
            } else {
                gender = 0;
            }
            //获取年龄
            var myDate = new Date();
            var month = myDate.getMonth() + 1;
            var day = myDate.getDate();
            var age = myDate.getFullYear() - card.substring(6, 10) - 1;
            if (card.substring(10, 12) < month || card.substring(10, 12) == month && card.substring(12, 14) <= day) {
                age++;
            }
            //返回对象
            var obj = {
                birthday: birthday,
                gender: gender,
                age: age
            }
            return obj;
        },
        /**验证生份证*/
        verifyIdCard: function(sId){
            var obj = {
                isSuccess: false
            }
            var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
                33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",
                50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",
                81:"香港",82:"澳门",91:"国外"}
            var iSum=0 ;
            var info="" ;
            if(!/^\d{17}(\d|x)$/i.test(sId)) {
                obj.error = "你输入的身份证长度或格式错误";
                return obj;
            }
            sId=sId.replace(/x$/i,"a");
            if(aCity[parseInt(sId.substr(0,2))]==null) {
                obj.error = "你的身份证地区非法";
                return obj;
            }
            sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
            var d=new Date(sBirthday.replace(/-/g,"/")) ;
            if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())) {
                obj.error = "身份证上的出生日期非法";
                return obj;
            }
            for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
            if(iSum%11!=1) {
                obj.error = "你输入的身份证号非法";
                return obj;
            }
            //aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女");//此次还可以判断出输入的身份证号的人性别
            obj.isSuccess = true;
            return obj;
        },
    },
    computed: {

    },
    watch: {
        profession: function () {
            this.countOfProfession = this.profession.length;
        },
        introduction: function () {
            this.countOfIntroduction = this.introduction.length;
        },
        province: function () {
            this.listCity(this.province);
        },
        city: function () {
            this.listDistrict(this.city);
        },
        district: function () {
            this.$nextTick(function () {
                this.searchMap(this.map, $('[name="province"] option:selected').text()
                    + $('[name="city"] option:selected').text()
                    + $('[name="district"] option:selected').text());
                store.vm.street = '';
            })

        },
        street: function () {
            /*if (this.street != '') {
                return;
            }*/
            this.searchMap(this.map, this.street)
        },
        orgAbout: function () {
            this.countOfOrgAbout = this.orgAbout.length;
        }

    }
});

/**根据地址获取经纬度*/
store.getLngLatByAddress = function (address) {
    $.ajax({
        type : "get",
        async:false,
        url : 'http://api.map.baidu.com/geocoder/v2/?address='+ address +'&output=json&ak=i7QOOG81qeyTB5QvRmwqnipj&' +
        'callback=showLocation',
        dataType : "jsonp",
        success : function(json){
            if (json.status == 0) {
                store.vm.lng = json.result.location.lng.toFixed(6);
                store.vm.lat = json.result.location.lat.toFixed(6);
            }
        },
        error:function(){
            console.log('fail')
        }
    });
},

/*密码强度验证*/
function CharMode(iN) {
    if (iN >= 48 && iN <= 57) // 数字
        return 1;
    if (iN >= 65 && iN <= 90) // 大写字母
        return 2;
    if (iN >= 97 && iN <= 122) // 小写
        return 4;
    else
        return 8; // 特殊字符
}
// bitTotal函数
// 计算出当前密码当中一共有多少种模式
function bitTotal(num) {
    modes = 0;
    for (i = 0; i < 4; i++) {
        if (num & 1)
            modes++;
        num >>>= 1;
    }
    return modes;
}
// checkStrong函数
// 返回密码的强度级别
function checkStrong(sPW) {
    if (sPW.length <= 4)
        return 0; // 密码太短
    Modes = 0;
    for (i = 0; i < sPW.length; i++) {
        // 测试每一个字符的类别并统计一共有多少种模式.
        Modes |= CharMode(sPW.charCodeAt(i));
    }
    return bitTotal(Modes);
}
// pwStrength函数
// 当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色
function pwStrength(pwd) {
    var O_color = "#999";
    var L_color = "#f00";
    var M_color = "#fe7800";
    var H_color = "#369239";
    if (pwd == null || pwd == '') {
        Lcolor = Mcolor = Hcolor = O_color;
    } else {
        var S_level = checkStrong(pwd);
        switch (S_level) {
            case 0:
                Lcolor = Mcolor = Hcolor = O_color;
            case 1:
                Lcolor = L_color;
                Mcolor = Hcolor = O_color;
                $(".psw_strenth span").text("安全等级低").css("color","#999");
                break;
            case 2:
                Lcolor = Mcolor = M_color;
                Hcolor = O_color;
                $(".psw_strenth span").text("安全等级中").css("color","#fe7800");
                break;
            case 3:
                Lcolor = Mcolor = Hcolor = H_color;
                $(".psw_strenth span").text("安全等级高").css("color","#369239");
                break;
            default:
                Lcolor = Mcolor = Hcolor = H_color;

        }
    }
    $("#strength_L").css("backgroundColor",Lcolor);
    $("#strength_M").css("backgroundColor",Mcolor);
    $("#strength_H").css("backgroundColor",Hcolor);
    return;
}


/**
 * 计时器
 */
var InterValObj; // timer变量，控制时间
var count = 60; // 间隔函数，1秒执行
var curCount;// 当前剩余秒数
var codeLength = 6;// 验证码长度
function sendMessage($obj) {
    curCount = count;
    // 设置button效果，开始计时
    $obj.attr("disabled", "true");
    $obj.val("重新获取（" + curCount + "）秒");
    $obj.css("background-color", "#ccc");
    $obj.css("border", "1px solid #ccc");
    InterValObj = window.setInterval(function () {
        if (curCount == 0) {
            window.clearInterval(InterValObj);// 停止计时器
            $obj.removeAttr("disabled");// 启用按钮
            $obj.val("重新发送验证码");
            $obj.css("background-color", "#369239");
            $obj.css("border", "1px solid #369239");
            // code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        } else {
            curCount--;
            $obj.val("重新获取（" + curCount + "）秒");
        }
    }, 1000); // 启动计时器，1秒执行一次
}