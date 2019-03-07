<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="banxin clearfix">
        <div class="user-logo fl">
            <a href="#">
                <img src="/static/platform/v2.2.0/images/logo.png" alt="">
            </a>
            <span>个人中心</span>
        </div>
        <ul class="hd-nav fr">
            <li><a href="indexControl.do?index">返回首页</a></li>
            <li><a href="javascript:quitLogin();">退出登录</a></li>
        </ul>
    </div>
</header>
<script src="/static/common/js/common.js"></script>
<script src="/static/common/js/dateFormat.js"></script>
<script>
    function quitLogin(){
        layer.confirm('您确定想退出系统登录吗？', {
            scrollbar: false,
            btn: ['确定','取消'] //按钮
        }, function(){
            _logout();
        }, function(){
        });
    }
</script>
