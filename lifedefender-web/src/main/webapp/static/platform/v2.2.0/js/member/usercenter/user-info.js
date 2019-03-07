/*
* @Author: Administrator
* @Date:   2017-04-18 14:17:59
* @Last Modified by:   Administrator
* @Last Modified time: 2017-04-18 14:18:37
*/
$(function(){
	var height=$(".main-content").height();
	$(".main-nav").css({
		borderRight:'1px solid #ddd',
		height:height
	});
	$(".container").css({
		border:'1px solid #ddd',
		height:height
	})
	$(".subnav").css("display","none");
	$(".main-nav li").on("click",function(){
		$(".main-nav li .subnav").not($(this).children(".subnav")).css("display","none");
		$(this).children(".subnav").slideToggle(400);
		/*$(this).children(".subnav").slideToggle(400).parent("li").siblings('li').children(".subnav").slideUp();*/

		$(this).children("a").css("backgroundColor","#dedede").parent("li").siblings('li').children("a").css("backgroundColor","transparent");

	});
})

/**
 * 定义vue
 */
var model = {};
model.vm = null;
model.getVm = function () {
	if (model.vm == null) {
		model.vm = new Vue({
            el: '.vue-content',
            data: {
                results: null,
                allProvinces: null,
                allCitys: null,
				allCountrys: null,
                selectedProvince: null,
                selectedCity: null,
                selectedCountry: null,
            },
            methods: {
                /**
                 * 获取城市
                 * @param code
                 */
                    getCitys: function (code) {
                    var data = {
                        provinceCode :  code.substr(0, 2)
                    }
                    $.ajax({
                        async : true,
                        cache : false,
                        type : 'GET',
                        url: "commonControl.do?getCity",
                        data: data,
                        dataType: 'json',
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        beforeSend:function(){
                        },
                        complete:function(){
                        },
                        success: function(result) {
                            if(result.success){
                                model.getVm().allCitys = result.obj;
                            }
                        }
                    });
                },
                /**
                 * 弹出头像窗口
                 */
                openHead: function () {
                    layer.open({
                        type:1,
                        title:['自定义头像','font-size:16px;text-align:center'],
                        content:$('.head-layer'),
                        btn:['确定','取消'],
                        area: '500px',
                        shade:0.1,
                        yes: function(index){
                            var relativePath = $("[name='img_hidden']").val();
                            var userId = model.getVm().results.id;
                            if(relativePath != "" && userId != ""){
                                photoUpload.upload("member?modifyPhoto", relativePath, userId, "photo");
                                setTimeout(function(){
                                    layer.close(index);
                                }, 800);
                            }
                        }
                    })
                },
                /**
                 * 上传图片
                 */
                uploadPic: function () {
                    var $this = $(event.currentTarget);
                    photoUpload.showPreview($this[0].files[0],"commonControl/uploadFile/img.do", $("#path"),"#upload_img","[name='img_hidden']");
                },
                /**
                 * 提交个人信息
                 */
                submitInfo: function () {
                    var json = $('.form-info').serializeArray();

                    $.ajax({
                        async : true,
                        cache : false,
                        type : 'POST',
                        url: "member?updateUserInfo_new",
                        data: json,
                        dataType: 'json',
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        beforeSend:function(){
                            layer.load();
                        },
                        complete:function(){
                            layer.closeAll("loading");
                        },
                        success: function(result) {
                            layer.msg(result.msg);
                            if(result.success){
                                setTimeout(function(){
                                    window.location.href="testController.do?userinfo";
                                }, 1000);
                            }
                        }
                    });
                },
				test: function () {
					console.log('inter point_3')
                },
            },
            computed: {
                name: function () {
                    if (this.results != null) {
                        return this.results.realName;
                    }
                },
                photo: function () {
                    if (this.results != null) {
                        return this.results.photo == '' ? 'static/images/photo.png' : this.results.photo;
                    }
                },
                gender: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.gender;
                    }
                },
                birthday: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.birthday ;
                    }
                },
                province: function () {
                    if (this.results != null) {
                        return this.results.province == null ? '选择省份' : this.results.province;
                    }
                },
                city: function () {
                    if (this.results != null) {
                        return this.results.city == null ? '选择城市' : this.results.city;
                    }
                },
                country: function () {
                    if (this.results != null) {
                        return this.results.county == null ? '选择区县' : this.results.county;;
                    }
                },
                height: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.height;
                    }
                },
                weight: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.weight;
                    }
                },
                mobile: function () {
                    if (this.results != null) {
                        return this.results.mobile;
                    }
                },
                //腰围
                waist: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.waist;
                    }
                },
                //胸围
                bust: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.bust;
                    }
                },
                //臀围
                hip: function () {
                    if (this.results != null) {
                        return this.results.recordDTO.hip;
                    }
                },
                workSpace: function () {
                    if (this.results != null) {
						/*return this.results.workSpace;*/
                    }
                },
            },
            filters: {

            },
            watch: {
                /** 监控selectedFoodKind属性，渲染完成时执行方法 */
            }
        });
	}
	return model.vm;
}

/**
 * 获取城市
 */
function getCitys() {
    var province = $('.province').val();
    var provinces = model.getVm().allProvinces;
    var provinceCode;
    for (var i in provinces) {
        if (province == provinces[i].name) {

            provinceCode = provinces[i].code;
            provinceCode = provinceCode.substr(0, 2);
            break;
        }
    }
    var data = {
        provinceCode : provinceCode
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'GET',
        url: "commonControl.do?getCity",
        data: data,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            if(result.success){
                model.getVm().allCitys = result.attributes.city;
                model.getVm().selectedCity = result.attributes.city[0].name;
                model.getVm().allCountrys = result.obj;
                model.getVm().selectedCountry = result.obj[0].name;
            }
        }
    });
}

/**
 * 获取区县
 */
function getCountrys() {
    var city = $('.city').val();
    var cities = model.getVm().allCitys;
    var cityCode;
    for (var i in cities) {
        if (city == cities[i].name) {

            cityCode = cities[i].code;
            cityCode = cityCode.substr(0, 4);
            break;
        }
    }
    var data = {
        cityCode : cityCode
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'GET',
        url: "commonControl.do?getDistrict",
        data: data,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            model.getVm().allCountrys = result;
            model.getVm().selectedCountry = result[0].name;
        }
    });
}

/**
 * 提交修改邮箱
 */
function submitEmail() {
    var email = $('.m-email').val();
    var code = $('.m-code').val();
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

    if(email == '') {
        layer.msg('请输入邮箱地址');
        return;
    }
    if(!myreg.test(email)) {
        layer.msg('请输入有效的邮箱地址');
        return;
    }
    if(code == '') {
        layer.msg('请输入验证码');
        return;
    }
    var lay = layer.confirm('确定使用该邮箱地址?', {
        scrollbar: false,
        btn: ['确定','取消'] //按钮
    }, function(){
        var data = {
            email: email,
            code: code
        };
        $.ajax({
            async : true,
            cache : false,
            type : 'POST',
            url: "member?modifyEmail_new",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){
            },
            complete:function(){
            },
            success: function(result) {
                layer.msg(result.msg);
                layer.close(lay);
                if(result.success){
                    window.setTimeout(function () {
                        $('.modify-form').css('display', 'none');
                        $('.email-form').css('display', 'block');
                    }, 1000);
                }
            }
        });

    });
}

/**
 * 发送邮箱验证码
 */
function sendEmailValidateCode() {
    var email = $('.m-email').val();
    var cacheType = 'email';
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

    if(email == '') {
        layer.msg('请输入邮箱地址');
        return;
    }
    if(!myreg.test(email)) {
        layer.msg('请输入有效的邮箱地址');
        return;
    }
	var data = "email="+email+'&temp='+new Date()+'&cache='+cacheType;;
    jQuery.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/register?sendValidCodeToEmail",
        data: data,
        dataType: 'json',
        beforeSend:function(){
            layer.load()
        },
        complete:function(){
            //方法执行完毕，效果自己可以关闭，或者隐藏效果
            layer.closeAll("loading")
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                /*if($saveUserIdElement != null) {
                    $saveUserIdElement.val(result.obj);
                }*/
                sendMessage($('.send-email-code'));
            }
        }
    });
}


/**
 * 提交修改电话号码
 */
function submitMobile() {
    var mobile = $('.m-mobile').val();
    var code = $('.m-code').val();
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;

    if(mobile == '') {
        layer.msg('请输入手机号码');
        return;
    }
    if(!myreg.test(mobile)) {
        layer.msg('请输入有效的手机号码');
        return;
    }
    if(code == '') {
        layer.msg('请输入验证码');
        return;
    }
    var lay = layer.confirm('确定使用该电话号码?', {
        scrollbar: false,
        btn: ['确定','取消'] //按钮
    }, function(){
        var data = {
            mobile: mobile,
            code: code
        };
        $.ajax({
            async : true,
            cache : false,
            type : 'POST',
            url: "member?modifyMobile_new",
            data: data,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            beforeSend:function(){
            },
            complete:function(){
            },
            success: function(result) {
                layer.msg(result.msg);
                layer.close(lay);
                if(result.success){
                    window.setTimeout(function () {
                        $('.mobile-modify-form').css('display', 'none');
                        $('.mobile-form').css('display', 'block');
                    }, 1000);
                }
            }
        });

    });
}

/**
 * 发送手机验证码
 */
function sendMobileValidateCode() {
    var mobile = $('.m-mobile').val();
    var cacheType = 'mobile';
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;

    if(mobile == '') {
        layer.msg('请输入手机号码');
        return;
    }
    if(!myreg.test(mobile)) {
        layer.msg('请输入有效的手机号码');
        return;
    }
    var data = "mobile="+mobile+'&temp='+new Date()+'&cache='+cacheType;;
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
            //方法执行完毕，效果自己可以关闭，或者隐藏效果
            layer.closeAll("loading")
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                /*if($saveUserIdElement != null) {
                 $saveUserIdElement.val(result.obj);
                 }*/
                sendMessage($('.send-mobile-code'));
            }
        }
    });
}


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
            $obj.css("background-color", "#e0e0e0");
            $obj.css("border", "1px solid #cacaca");
            code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        } else {
            curCount--;
            $obj.val("重新获取（" + curCount + "）秒");
        }
    }, 1000); // 启动计时器，1秒执行一次
}

/**
 * 提交修改密码
 */
function submitPassword() {
    var oldPwd = $('.old-password').val();
    var newPwd = $('.new-password').val();
    var confirmPwd = $('.confirm-password').val();
    var $reg= /^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[_]){5,17}$/;
    if (oldPwd == '') {
        layer.msg('请输入旧密码');
        return;
    }
    if (newPwd == '') {
        layer.msg('请输入新密码');
        return;
    }
    if (confirmPwd == '') {
        layer.msg('请再次输入新密码');
        return;
    }
    if (!$reg.test(newPwd)) {
        layer.msg('密码限制6-18位字符，只能包含英文字母、数字、下划线');
        return;
    }
    if (newPwd != confirmPwd) {
        layer.msg('输入的密码不一致');
        return;
    }
    var lay = layer.confirm('确定使用该电话号码?', {
        scrollbar: false,
        btn: ['确定','取消'] //按钮
    }, function(){
        var data = {
            password_old: oldPwd,
            password_new: newPwd
        }
        jQuery.ajax({
            async : true,
            cache : false,
            type : 'POST',
            url: "member?updateUserPwd",
            data: data,
            dataType: 'json',
            beforeSend:function(){
                layer.load()
            },
            complete:function(){
                //方法执行完毕，效果自己可以关闭，或者隐藏效果
                layer.closeAll("loading")
            },
            success: function(result) {
                layer.msg(result.msg);
                if(result.success){
                }
            }
        });
    });

}