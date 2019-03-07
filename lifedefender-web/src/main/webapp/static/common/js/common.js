/**
 *  公共部分js
 *  1、设置后台登录名
 *  2、
 *  3、
 */
$(document).ready(function() {
    //在引入css的基础上配置skin参数，如下所示
/*  layer.config({
        extend:'skin/seaning/style.css'
    });*/
    
    // 设置jQuery Ajax全局的参数  
    $.ajaxSetup({
        async: true,
        cache: false,
        type: "POST",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        success:function () {
        },
        error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(500):  
                    console.log(jqXHR);
                    /*layer.alert(jqXHR.responseJSON.msg,{skin:'layer-ext-seaning',icon: 11});  */
                    break;  
                case(401):
                    layer.alert("未登录或者登录超时",{skin:'layer-ext-seaning',icon: 11}, function() {
                        window.location.href='/login';
                    });
                    break;  
                case(403):  
                    layer.alert("无权限执行此操作",{skin:'layer-ext-seaning',icon: 11});  
                    break;
                case(404):  
                    layer.alert("找不到页面",{skin:'layer-ext-seaning',icon: 11});
                    break;
                case(408, 0):  
                    layer.alert("请求超时",{skin:'layer-ext-seaning',icon: 11});  
                    break;
                default:
                    console.log('jqXHR:', jqXHR);
                    layer.alert("未知错误",{skin:'layer-ext-seaning',icon: 11}, function() {
                        window.location.href='/login';
                    });
            }
        }
    });
    $.extend({
        checkPswStrong:function (content) {//密码强度：0：弱，1：弱：2：中，3：强，4：超强
            if (content.length <= 4)
                return 0; // 密码太短
            var mode = 0;
            for (var i = 0; i < content.length; i++) {
                // 测试每一个字符的类别并统计一共有多少种模式.
                var c=content.charCodeAt(i);
                var temp=0;
                if(c>=48&&c<=57){//数字
                    temp=1;
                }else if(c>=65&&c<=90){//大写字母
                    temp=2;
                }else if(c>=97&&c<=122){//小写
                    temp=4;
                }else{
                    temp=8;//特殊字符
                }
                mode |= temp;
            }
            var strength = 0;
            for (var j = 0; j < 4; j++) {
                if (mode & 1)
                    strength++;
                mode >>>= 1;
            }
            return strength;
        },localSave: function(key, value) {
            localStorage.setItem(key, value);
        },
        localRemove: function(key) {
            localStorage.removeItem(key);
        },
        localGet: function(key) {
            return localStorage.getItem(key);
        },
        localClear: function() {
            localStorage.clear();
        },
        localIndex: function(index) {
            return localStorage.key(index);
        }
    })
});

jQuery(function() {
    reDefinedPageHeight();
});

function reDefinedPageHeight() {
    if($("body div").hasClass("container")){
     $("#menu").css("min-height",$(".container").height()+71+"px");
     $(".webPage").css("min-height",$("#menu").height()+62+"px");
   }else{
     var h = $("#contextIframe").height()+82;
     $("#menu").css("min-height",h+"px");
     $(".webPage").css("min-height",h+62+"px");
   }
}
function checkOrRadio(error, element) {
    var elementType = element.attr("type");
    if("checkbox" == elementType){
        $("#_checkbox").append( error );
    }else{
        var div = $("<div />").append(error);
        div.appendTo(element.parent());
    }
}

//退出登录
function _logout(){
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "/login/logout",
        data: "",
        dataType: 'json',
        beforeSend:function(){
            layer.load();
        },
        complete:function(){
        },
        success: function(result) {
            layer.closeAll('loading');
            if(result.success) {
                var redirectUrl = '/login';
                var loginTunnel = _cookie('loginTunnel');
                if ('hongyuantang' == loginTunnel) {
                    redirectUrl = '/hongyuantang'
                }
                cookieClean("isManageOrg");
                $.removeCookie('loginTunnel', {path: "/"});
                setTimeout(function() {
                    window.location.href = redirectUrl;
                }, 300);
            }
        }
    });
}

function quitLogin(){
    layer.confirm('您确定想退出系统登录吗？', {
            'offset': '50%',
            scrollbar: false,
            btn: ['确定','取消'] //按钮
        }, function(){
            _logout();
        }, function(){
    });
}

function checkLoginStatus() {
    jQuery.ajax({
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        url: '/login/checkloginstatus',
        success: function(result) {
            if (result.success) {
                var attributes = result.attributes;
                var isOrg = attributes.isOrg;
                
                var b = 1;
                if (isOrg) {
                    b = 2;
                }
                
                var $exitLi = jQuery(".inner-header li.showExit");
                $exitLi.find("img").attr("src",attributes.photo);
                $exitLi.find("a>span").text(attributes.name);
                
                var $childLi = $exitLi.find("ol>li");
                $childLi.each(function() {
                    var $this = jQuery(this);
                    var userType = $this.attr("data-userType");
                    if ((userType & b) == b) {
                        $this.show();
                    }
                });
                $exitLi.show();

            } else {
                var $signUpParentLi = jQuery(".inner-header span.signUp").parents("li");
                var $signInParentLi = jQuery(".inner-header span.signIn").parents("li");
                $signUpParentLi.show();
                $signInParentLi.show();
            }
        }
    });
}

/**
 * <p>使用说明：
 * <p>ajax请求获取数据的时候
 * <p>如果能正常获取数据，success的值为boolean类型
 * <p>如果success的值为undefined，说明登录失效
 * <p>如果success的值为function，说明服务器关闭
 * <p>在这里，只需要判断success的值是否为boolean，将type不是boolean的ajax请求处理变成刷新页面
 * @param result Ajax获取的返回结果
 */
/*function loginCheck(result){
    if(typeof(result.success) != 'boolean'){
        layer.msg("登录已超时，请重新登陆");
//      setTimeout(function() {
//          window.location.href = '/login';
//      }, 1000);
        return false;
//      location.reload();
    }
}*/

/**
 * 按名称查找cookie，如果找不到就返回空
 * @param name cookie名称
 * @returns {String}
 */
function _cookie(name){

    var cookies = document.cookie;
    var cookie_index = cookies.indexOf(name);
    var value = "";
    if(cookie_index != -1){
        cookie_index += name.length + 1;
        var cookie_end = cookies.indexOf(";", cookie_index);
        if(cookie_end == -1){
            cookie_end = cookies.length;
        }
        value = decodeURI(cookies.substring(cookie_index, cookie_end));
    }
    // value = decodeURI(value);
    return value;
}

function cookieClean(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=_cookie(name);
    if(cval != "") {
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
}

/**
 * 设置用户名
 * @param reCheck 重新检测，如果是true就重新获取结果
 */
function _setUserMessage(){
    var $userName = jQuery("#_userName");
    var $userHead = jQuery("img.userhead");
    
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'GET',
        url: '/memberControl.do?user',
        success: function(result){
            if(result.success){
                var obj = result.obj;
                
                $userName.text(obj.userName);
                $userHead.attr("src", obj.userHead);
            }
        }
    });
    
    /*var userName = _cookie("userName");
    var userHead = _cookie("userHead");
    var $userName = jQuery("#_userName");
    var $userHead = jQuery("img.userhead");
    if(userName == "" || userHead == "" || reCheck){
        jQuery.ajax({
            async: true,
            cache: false,
            type: 'GET',
            url: 'memberControl.do?user',
            success: function(result){
                if(result.success){
                    var obj = result.obj;
                    document.cookie = "userName=" + escape(obj.userName);
                    document.cookie = "userHead=" + escape(obj.userHead);
                    
                    $userName.text(_cookie("userName"));
                    $userHead.attr("src", _cookie("userHead"));
                }
            }
        });
        return ;
    }
    $userName.text(_cookie("userName"));
    $userHead.attr("src", _cookie("userHead"));
    return ;*/
    
    /*var userName = _cookie("userName");
    var userHead = _cookie("userHead");
    var $userName = jQuery("#_userName");
    var $userHead = jQuery("img.userhead");
    if(userName == "" || userHead == "" || reCheck){
        _userMessage();
        $userName.text(_cookie("userName"));
        $userHead.attr("src", _cookie("userHead"));
        return ;
    }
    $userName.text(_cookie("userName"));
    $userHead.attr("src", _cookie("userHead"));
    return ;*/
}

/**
 * 获取用户的userName
 */
function _userMessage(){
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'GET',
        url: '/memberControl.do?user',
        success: function(result){
            if(result.success){
                var obj = result.obj;
                document.cookie = "userName=" + escape(obj.userName);
                document.cookie = "userHead=" + escape(obj.userHead);
            }
        }
    });
}

function _register(){
    window.location.href = "/login/register";
}

function _forgotPwd(){
    window.location.href = "/pass/forgotpwd";
}

//对特殊字符进行转义
function encodeStr(str){
    return str.replace(new RegExp(/(&)/g),'＆');
}
//添加收藏夹
function addFavorite() {
    //捕获加入收藏过程中的异常
    try {
        //判断浏览器是否支持document.all
        if (document.all) {
            //如果支持则用external方式加入收藏夹
            window.external.addFavorite(location.href, document.title);
            layer.alert('添加收藏成功！');
        } else if (window.sidebar) {
            //如果支持window.sidebar，则用下列方式加入收藏夹
            window.sidebar.addPanel(document.title, location.href, '');
            layer.alert('添加收藏成功！');
        }
        else layer.alert("暂不支持您的浏览器，请使用Ctrl+D快捷键进行添加操作!");
    }
        //处理异常
    catch (e) {
        layer.alert("暂不支持您的浏览器，请使用Ctrl+D快捷键进行添加操作!");
    }
}

/**
 * 产生任意长度随机字母数字组合
 * @param randomFlag 是否任意长度
 * @param min 任意长度最小位[固定位数]
 * @param max 任意长度最大位
 * @returns {string}
 */
function random(randomFlag, min, max){
    var str = "",
        range = min,
        arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', '_'];

    // 随机产生
    if(randomFlag){
        range = Math.round(Math.random() * (max-min)) + min;
    }
    for(var i=0; i<range; i++){
        pos = Math.round(Math.random() * (arr.length-1));
        str += arr[pos];
    }
    return str;
}

/**
 * 初始化admin.jsp页面数据
 * @param photoSeletor
 * @param nameSelector
 * @param ageSelector
 * @param mobileVerifiedSelector
 * @param emailVerifiedSelector
 * @param newsSelector
 * @param lastLoginSelector
 */
function getUserInfo(photoSeletor, nameSelector, ageSelector, mobileVerifiedSelector, emailVerifiedSelector, newsSelector, lastLoginSelector) {
    jQuery.ajax({
        async: true,
        cache: false,
        type: 'GET',
        url: 'member?user',
        success: function (result) {
            if (result.success) {
                var obj = result.obj;
                photoSeletor.attr("src", obj.userHead == '' ? 'static/images/photo.png' : obj.userHead);
                nameSelector.text(obj.userName);
                ageSelector.text('年龄： ' + obj.age)
                if (obj.mobileVerified) {
                    mobileVerifiedSelector.css('background-position', 'left top');
                }
                if (obj.emailVerified) {
                    emailVerifiedSelector.css('background-position', 'left -68px');
                }
                newsSelector.text(obj.news);
                lastLoginSelector.text(new Date(obj.lastLogin).Format('yyyy-MM-dd'));


            }
        }
    });
}

/**localStorage*/

function setLocalStorage(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}
function getLocalStorage(key) {
    var value = JSON.parse(localStorage.getItem(key));
    return value;
}

