/*用户名规则：6-18位字符，只能包含英文字母、数字、下划线*/
var $reg= /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,17}$/;
$(".username").blur(function() {
	var $username=$(".username").val();
    if($reg.test($username)==true){
        $(".username + .msg-lay").text("");
        if ($(".username + .msg-lay").children('span').length < 1) {
            console.log('inter')
            $(".username + .msg-lay").append("<span class=msg-lay-1></span>");
        }
        $(".msg-lay-1").css("display","block");

    }else if($username==""){
        isValid = false;
        $(".username + .msg-lay").text("用户名不能为空！");
        $(".username + .msg-lay").css({
            'lineHeight':"40px",
            'color':"#f00"
        });
    }else{
        isValid = false;
        $(".username + .msg-lay").text("用户名输入不正确");
        $(".username + .msg-lay").css({
            'lineHeight':"40px",
            'color':"#f00"
        });
    }

});


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

/*confirm password*/
$(".cf-pwd").blur(function(){
	var $pwd=$(".password").val();
	var $cf_pwd=$(".cf-pwd").val();
	if($cf_pwd!=$pwd){
		$(".cf-pwd + .msg-lay").text("两次密码不一致！").css({
			'lineHeight':"40px",
			'color':"#f00"
		});
	}
})
$(".phone").blur(function(){
	var $phone=$(".phone").val();
	if ($phone != "") {
		if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test($phone)) {
			$(".phone + .msg-lay").text("手机格式不对").css({
				'lineHeight':"40px",
				'color':"#f00"
			})
			return;
		}
	}
})

/**
 * 机构注册
 * @type {{}}
 */
var org = {};
org.picIsChange = false;
org.provinces = null;
org.cities = null;
org.district = null;
org.address = null;
org.lng = null;
org.lat = null;
org.map = null;

/**
 * 提交注册第一步信息
 */
org.register = function () {
    if (org.lng == null || org.lng  == undefined || org.lng == '') {
        org.getLngLatByAddress($('input[name="address"]').val())
    }
    var json = $('.register-form').serializeArray();

	for (var i in json) {
        if (json[i].name == 'photo') {
            if (json[i].value == '') {
                layer.msg('请选择公司logo')
                return;
            }
        }
		if (json[i].name == 'orgName') {
			if (json[i].value == '') {
				layer.msg('公司名称不能为空')
				return;
			}
		}
        if (json[i].name == 'businessLicenseNumber') {
            if (json[i].value == '') {
                layer.msg('营业执照号不能为空')
                return;
            }
        }
        if (json[i].name == 'businessLicenseNumber') {
            /*if (json[i].value.length != 15) {
                layer.msg('营业执照号不合法')
                return;
            }*/

        }
        if (json[i].name == 'address') {
            if (json[i].value == '') {
                layer.msg('公司详细地址不能为空')
                return;
            }
        }
        if (json[i].name == 'workField') {
            if (json[i].value == '') {
                layer.msg('公司从事领域不能为空')
                return;
            }
        }
        if (json[i].name == 'mobile') {
            if (json[i].value == '') {
                layer.msg('公司电话号码不能为空');
                return;
            }
            var myreg = /^[4,0]\d{2,3}-?\d{7,8}$/;;
            if (!myreg.test(json[i].value)) {
                layer.msg('公司电话号码格式不正确');
                return;
            }
        }
	}


    layer.confirm('确认注册门店？', function () {
        var longitude = {
            name: 'longitude',
            value: org.lng
        }
        var latitude = {
            name: 'latitude',
            value: org.lat
        }
        json.push(longitude);
        json.push(latitude);

        $.ajax({
            async : true,
            cache : false,
            type : 'POST',
            url: "/register/registerOrg",
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){
            },
            complete:function(){
            },
            success: function(result) {
                layer.msg(result.msg);
                if(result.success){
                    var attributes = result.attributes;
                    window.location.href = '/register/org/steptwo?id=' + attributes.id + '&token=' + attributes.token + '&orgName=' + attributes.orgName;
                }
            }
        });
    })

}

/**
 * 提交注册第二步信息
 */
org.registerStepTwo = function () {
    var json = $('.register-form-2').serializeArray();
    for (var i in json) {
        if (json[i].value == "") {
            layer.msg('请完善机构信息');
            return;
        }
        if (json[i].name == 'bankAccount') {
            if (json[i].value.length < 9 || json[i].value.length > 20) {
                layer.msg('银行账号不合法')
                return;
            }
        }

    }
    // json.id = $('.orgId').val();
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register/org/improve",
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                window.location.href = '/register/org/stepthree?id=' + result.attributes.id + '&token=' + result.attributes.token;
            }
        }
    });
}

/**
 * 提交注册第三步信息
 */
org.registerStepThree = function () {
    var json = $('.register-form-3').serializeArray();
    for (var i in json) {
        if (json[i].value == "") {
            layer.msg('请完善机构信息');
            return;
        }
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register/org/improve",
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
               window.location.href = '/register/org/stepfour?id=' + result.attributes.id + '&token=' + result.attributes.token;
            }
        }
    });
}

/**
 * 提交注册第四步信息
 */
org.registerStepFour = function () {
    var json = $('.register-form-4').serializeArray();
    for (var i in json) {
        if (json[i].name!="parentId" && json[i].value == "") {
            layer.msg('请完善登录信息');
            return;
        }
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register/org/registerOrgUser",
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                 window.location.href = '/register/org/stepfive';
            }
        }
    });
}
/**
 * @Description: 发送注册验证码
 * @author: wenxian.cai
 * @create: 2017/4/26 19:56
 */
org.sendCode = function() {
    var mobile = $('.mobile').val();
    var cacheType = 'register';
    var userType = 'org';
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;

    if(mobile == '') {
        layer.msg('请输入手机号码');
        return;
    }
    if(!myreg.test(mobile)) {
        layer.msg('请输入有效的手机号码');
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
            layer.msg(result.msg);
            if(result.success){
                sendMessage($('.send-code'));
            }
        }
    });
}

/**初始化地图*/
org.initMap = function (map, callBack) {
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
        org.getAddressByLngLat(lng, lat, function (address) {
            org.address = address.province + address.city + address.district
                + address.district + address.street + (address.street_number==''? '': address.street_number);
            $('input[name = "address"]').val(org.address);
        })
    });

},
/**点击地图获取经纬度*/
org.getLngLat = function (map, callBack) {
    map.addEventListener("click",function(e){
        if (typeof callBack == 'function') {
            callBack(e.point.lng, e.point.lat);
        }
    });
},
/**根据经纬度获取地址名称*/
org.getAddressByLngLat = function (lng, lat, callBack) {
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
org.searchMap = function (map, address) {
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
}
/**根据地址获取经纬度*/
org.getLngLatByAddress = function (address) {
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
            /*if (typeof callBack == 'function') {
                if (json.status == 0) {
                    callBack(json.result.location);
                }
            }*/
        },
        error:function(){
            console.log('fail')
        }
    });
}

/**跳过第二步*/
org.skipTwoStep = function () {
    var orgId = $('.step-two-orgId').val();
    var token = $('.step-two-token').val();
    window.location.href = '/register/org/stepthree?id=' + orgId + '&token=' + token;
}

/**
 * 注册协议
 */
 org.agreement = function() {
    var href = "/register?showOrgAgreement";
    layer.open({
        type: 2,
        title: false,
        shadeClose: true,
        moveType: 1,
        area: ["1000px", "800px"],
        content: href
    });
}

/**
 * 专业人士注册
 * @type {{}}
 */
var services = {};
services.register = function () {
    var json = $('.register-form-1').serializeArray();
    for (var i in json) {
        if (json[i].value == "") {
            layer.msg('请完善登录信息');
            return;
        }
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register/org/services/register",
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                 window.location.href = '/register/org/services/steptwo?id=' + result.attributes.id + '&token=' + result.attributes.token;
            }
        }
    });
}
/**
 * 提交第二步注册信息
 */
services.registerStepTwo = function () {
    var json = $('.register-form-2').serializeArray();
    for (var i in json) {
        if (json[i].value == "") {
            layer.msg('请完善个人信息');
            return;
        }
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register/org/services/improve",
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                window.location.href = '/register/org/services/stepthree?id=' + result.attributes.id + '&token=' + result.attributes.token;
            }
        }
    });
}
/**
 * 协议
 */
services.agreement = function() {
    var href = "/register?showAgreement";
    layer.open({
        type: 2,
        title: false,
        shadeClose: true,
        moveType: 1,
        area: ["1000px", "800px"],
        content: href
    });
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

/**
 * 点击触发file按钮
 */
org.picClick = function (type) {
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
    }
}



/**
 * 获取城市
 */
function getCitys() {
    var provinceCode = $('.province').val().substr(0, 2);
    var data = {
        provinceCode : provinceCode
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'GET',
        url: "/commonControl.do?getCity",
        data: data,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            if(result.success){
            	var html = '';
            	var code;
            	var name;
            	for (var i in result.attributes.city) {
            		code = result.attributes.city[i].code;
                    name = result.attributes.city[i].name;
					html += "<option value = "+ code +">"+ name +"</option>";
				}
				$('.city').empty();
            	$('.city').append(html);
            	html = '';
                for (var i in result.obj) {
                    code = result.obj[i].code;
                    name = result.obj[i].name;
                    html += "<option value = "+ code +">"+ name +"</option>";
                }
                $('.district').empty();
                $('.district').append(html);

                org.searchMap(org.map)
            }
        }
    });
}

/**
 * 获取区县
 */
function getCountrys() {
    var cityCode = $('.city').val().substr(0, 4);
    var data = {
        cityCode : cityCode
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'GET',
        url: "/commonControl.do?getDistrict",
        data: data,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            var html = '';
            var code;
            var name;
            for (var i in result) {
                code = result[i].code;
                name = result[i].name;
                html += "<option value = "+ code +">"+ name +"</option>";
            }
            $('.district').empty();
            $('.district').append(html);

            org.searchMap(org.map)
        }
    });
}
