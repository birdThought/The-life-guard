<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<footer id="footer" class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12">
                <ul>
                    <li><h5>广州通众电气实业有限公司&copy;版权所有</h5></li>
                    <li><a href="http://www.miitbeian.gov.cn/" target="_blank">粤ICP备13019866号-3</a></li>
                    <li>400-026-1003</li>
                </ul>
            </div>
            <div class="col-md-5 col-sm-4">
                <div class="row clearMargin">
                    <div class="col-xs-6">
                        <h4>关于我们</h4>
                        <ul>
                            <li><a href="informationControl.do?inforLook&amp;id=91" target="_blank">关于健康管理平台</a></li>
                            <li><a href="http://netfield.cn">官方网站</a></li>
                            <li><a href="informationControl.do?inforLook&id=92">加入我们</a></li>
                            <li><a href="informationControl.do?inforLook&id=93">联系我们</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-6">
                        <h4>免费下载APP</h4>
                        <ul>
                            <li><a id="" onclick="onclickD('tanchuanAndroid',this)">应用APP下载</a></li>
                            <li><a onclick="onclickD('tanchuanIos',this)">管理APP下载</a></li>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="col-md-3 col-sm-4">
                <a href="#" class="item-code"> <img
                    src="/static/images/index/QR_Code.png" alt="">
                    <h4 class="text-center">生命守护公众号</h4>
                </a>
            </div>
        </div>
    </div>
</footer>
<div id="tanchuanIos" style="left: 887.5px; top: 1130px; display: none;">
    <div style="padding: 15px 23px">
        <h3>生命守护管理APP下载</h3>
        <input type="image" src="/static/images/iconfont-2.png" onclick="removeElement('tanchuanIos')">
        <a href="http://app.qq.com/#id=detail&amp;appid=1105884260" style="float: left"> <img src="/static/images/android_manage.jpg" alt="android版下载" width="95" height="95">
            <p class="p2">Andriod版下载</p>
        </a>
        <a href="https://itunes.apple.com/us/app/shou-hu-shi/id1185206076?mt=8" style="float: left">
            <img src="/static/images/ios_manage.jpg" width="95" height="95" alt="IOS版下载">
            <p class="p2">IOS版下载</p>
        </a>
    </div>
</div>
<div id="tanchuanAndroid" style="left: 887.5px; top: 1100px; display: none;">
    <div style="padding: 15px 23px">
        <h3>生命守护应用APP下载</h3>
        <input type="image" src="/static/images/iconfont-2.png" onclick="removeElement('tanchuanAndroid')">
        <a href="http://app.qq.com/#id=detail&amp;appid=1105104088" style="float: left">
            <img src="/static/images/android_use.jpg" alt="Andriod版下载" width="95" height="95">
            <p class="p2">Andriod版下载</p>
        </a>
        <a href="https://itunes.apple.com/us/app/sheng-ming-shou-hu/id1114425655?l=zh&amp;ls=1&amp;mt=8" style="float: left">
            <img src="/static/images/ios_use.jpg" width="95" height="95" alt="IOS版下载">
            <p class="p2">IOS版下载</p>
        </a>
    </div>
</div>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script>
    function removeElement(id) {
        document.getElementById(id).style.display = "none";
    }
    function onclickD(type, obj) {
        if ("tanchuanIos" == type) {
            $("#tanchuanAndroid").fadeOut();
            document.getElementById("tanchuanAndroid").style.display = "none";

            $("#tanchuanIos")[0].style.left = obj.getBoundingClientRect().left
                -40+ "px";
            $("#tanchuanIos")[0].style.top = obj.getBoundingClientRect().top
                - 273 + $(document).scrollTop() + "px";

            $("#tanchuanIos").fadeToggle(300);
        } else {
            $("#tanchuanIos").fadeOut();
            $("#tanchuanAndroid")[0].style.left = obj
                    .getBoundingClientRect().left
                -40+ "px";
            $("#tanchuanAndroid")[0].style.top = obj
                    .getBoundingClientRect().top
                - 273 + $(document).scrollTop() + "px";
            $("#tanchuanAndroid").fadeToggle(300);
        }
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

                    var $exitLi = jQuery("ul.nav>li.showExit");
                    $exitLi.children("a").text(attributes.name);

                    var $childLi = $exitLi.find("ul>li");
                    $childLi.each(function() {
                        var $this = jQuery(this);
                        var userType = $this.data("usertype");
                        if ((userType & b) == b) {
                            $this.removeClass("none");
                        }
                    });

                    $exitLi.removeClass("none");
                } else {
                    var $signUpParentLi = jQuery("#register");
                    var $signInParentLi = jQuery("#login");
                    $signUpParentLi.removeClass("none");
                    $signInParentLi.removeClass("none");
                }
            }
        });
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
//          console.log(result);
                layer.closeAll('loading');
                if(result.success){
                    cookieClean("isManageOrg");
                    setTimeout(function() {
                        window.location.href = "/login";
                    }, 300);
                }
            }
        });
    }

    function quitLogin(){
        layer.confirm('您确定想退出系统登录吗？', {
            scrollbar: false,
            btn: ['确定','取消'] //按钮
        }, function(){
            _logout();
        }, function(){
        });
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
            value = unescape(cookies.substring(cookie_index, cookie_end));
        }
        return value;
    }

    $(window).scroll(function(){
        var sc=$(window).scrollTop();
        if(sc>200){
            $("#goHome").css("display","block");

        }else{
            $("#goHome").css("display","none");
        }
    });
    $("#goHome").click(function(){
        $('body,html').animate({scrollTop:0},300);
    });
</script>