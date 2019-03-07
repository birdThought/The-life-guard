
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="banxin clearfix">
        <div class="user-logo fl">
            <a href="/indexControl.do?index">
                <img src="/static/platform/v2.2.0/images/logo.png" alt="">
            </a>
            <shiro:hasPermission name="user:0 or user:2">
                <span>机构中心</span>
            </shiro:hasPermission>
            <shiro:hasRole name="user:1">
                <span>服务中心</span>
            </shiro:hasRole>
        </div>
        <ul class="hd-nav fr">
            <li><a href="/indexControl.do?index">返回首页</a></li>
            <li><a href="javascript:quitLogin();">退出登录</a></li>
        </ul>
    </div>
</header>

<%--<script src="/static/plugins/vue/vue.min.js"></script>--%>
<script type="text/javascript" src="/static/jquery/jquery.cookie.js"></script>
<script src="/static/common/js/common.js?v=2.2.2"></script>
<script src="/static/plugins/qrcode/qrcode.min.js"></script>
