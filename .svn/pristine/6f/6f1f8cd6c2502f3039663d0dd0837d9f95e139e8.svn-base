/**
 * 登录
 * author: liaoguo
 * date: 2018/11/1 15:32
 */

var register = {};


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
};

    register.init = function () {
            layui.use('layer');
        }
    register.provinceChange=function () {
        var provinceCode = $("#province option:selected").val();
        provinceCode = provinceCode.substr(0, 2);
        var url = "/datalist?getCityByProvinceCode";
        var data = {
                provinceCode : provinceCode,
            };
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            console.log(result.obj);
                register.setCityDatas(result);
           
        });
    }
    
    register.cityChange=function () {
        var cityCode = $("#city option:selected").val();
        cityCode = cityCode.substr(0, 4);
        var url = "/datalist/?getAreaByCityCode";
        var data = {
                cityCode : cityCode,
            };
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            console.log(result.obj);
            register.setArea(result.obj);
           
        });
    }
    
    register.setCityDatas=function (data) {
        var dis = data.obj;
        var cits = data.attributes['city'];
        if (cits == '') {
            $("#city").html(
                '<option selected="selected" value="'
                + $("#province option:selected").val() + '">'
                + $("#province option:selected").text()
                + '</option>');
        } else {
            var str = "";
            for (var key in cits) {
                if (key == 0) {
                    str += '<option selected="selected" value="'
                        + cits[key].code + '">' + cits[key].name
                        + '</option>';
                    continue;
                }
                str += '<option value="' + cits[key].code + '">'
                    + cits[key].name + '</option>';
            }
            $("#city").html(str);
        }
        register.setArea(dis);
    },
    register.setArea = function (data) {
        if (data == '') {
            $("#district").html(
                '<option selected="selected" value="'
                + $("#city option:selected").val() + '">'
                + $("#city option:selected").text() + '</option>');
        } else {
            var str = "";
            for (var key in data) {
                if (key == 0) {
                    str += '<option selected="selected" value="'
                        + data[key].code + '">' + data[key].name
                        + '</option>';
                    continue;
                }
                str += '<option value="' + data[key].code + '">'
                    + data[key].name + '</option>';
            }
            $("#area").html(str);
        }
    }
    
    
    
    
    
    register.getCode =function () {
        var mobile = $('input[name="mobile"]').val();
        var cacheType = 'register';
        var userType = 'org';
        if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
            layer.msg('手机号码格式不合法')
            return;
        }
       
        var url = "/member/data/offline/sendValidCode";
        var data = {
            "mobile":mobile,
            "cache":cacheType,
            "userType":userType
        };
        
        $.ajax({
            async: true,
            cache: false,
            type: 'post',
            url: url,
            data: data,
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            dataType: 'json',
            beforSend: function() {
                layer.load(2, {offset: ['55%', '50%']});
            },
            complete: function() {
                layer.closeAll('loading');
            },
            success: function(result) {
//                if (typeof callBack == 'function') {
//                    if (result.success) {
//                        callBack(result);
//                        return;
//                    }
//                    if (result.msg != null && result != '') {
//                        layer.msg(result.msg);
//                        return;
//                    }
//                    layer.msg('服务器异常');
//                }
                if(result.success){
                    sendMessage($('.get-code'));
                }
            }
        });
    }
    
    register.addSumit = function(){
        var name = $('input[name="name"]').val(); 
//        var contactMan = $('input[name="contactMan"]').val(); 
//        var email = $('input[name="email"]').val(); 
        /*var parentId = $('input[name="parentId"]').val(); */
        var provinceCode = $("#province option:selected").val();
        var city = $("#city option:selected").val();
        var area = $("#area option:selected").val();
        var mobile = $('input[name="mobile"]').val(); 
        var address = $('input[name="address"]').val(); 
        var verifyCode = $('input[name="verifyCode"]').val(); 
        var userName = $('input[name="userName"]').val(); 
        var pwd = $('input[name="pwd"]').val(); 
        var password = $('input[name="password"]').val(); 
        if(isEmpty(userName)){
            layer.msg("登录账号不能为空!");
            return;
        }
        if(userName.length < 6 || userName.length > 16){
            layer.msg("请输入6~16位的登录账号！");
            return;
        }
        if(isEmpty(pwd)){
            layer.msg("密码不能为空!");
            return;
        }
        if(isEmpty(password)){
            layer.msg("确认密码不能为空!");
            return;
        }
        if(pwd!=password){
            layer.msg("两次输入的密码不一致!");
            return;
        }
        if(password.length < 6 || password.length > 16){
            layer.msg("请输入6~16位包含大小写英文和数字！");
            return;
        }
        if(isEmpty(name)){
            layer.msg("真实名称不能为空!");
            return;
        }
        if(name.length < 2 || name.length>7){
            layer.msg("请输入2~6位的真实名称！");
            return;
        }
        var msg = validateMobile(mobile);
        if(msg!="" && typeof(msg)!='undefined'){
            layer.msg(msg);
            return;
        }
//        if(isEmpty(contactMan)){
//            layer.msg("联系人不能为空!");
//            return;
//        }
//        if(isEmpty(email)){
//            layer.msg("邮箱不能为空!");
//            return;
//        }
        if(isEmpty(provinceCode)){
            layer.msg("省市不能为空!");
            return;
        }
        
        /*if(typeof parentId != "undefined" || parentId != null || parentId != ""){
            if(parentId.toUpperCase().indexOf("D") < 0){
                layer.msg("请正确输入引荐人编号!");
            }
        }*/
            
        
        var url = "/member/data/offline/addSalesman";
        var data = {
                "name":name, 
//                "contactMan":contactMan,
//                "email":email,
//                "parentId":parentId,
                "provinceCode":provinceCode,
                "cityCode":city,
                "areaCode":area,
                "address":address,
                "moblie":mobile,
                "userName":userName,
                "password":password,
                "verifyCode":verifyCode
        };
        
        http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            layer.msg(result.msg);
            layer.closeAll('page')
            return;
           
        })
    }
    
    function isEmpty(val){
        if(val=="" || val==null){
            return true;
        }
    }

    function validateMobile(mobile){
        var msg = "";
        if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
            msg = "手机号码格式不合法";
            return msg;
        }
    }



register.keyLogin = function (evt){
    evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象  
    var key = evt.keyCode?evt.keyCode:evt.which; //兼容IE和Firefox获得keyBoardEvent对象的键值
    if (key == 13) {
        document.getElementById("submit").click();
    }
};





