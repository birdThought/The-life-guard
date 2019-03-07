<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="main-top">
    <ul class="banxin clearfix">
        <li><img class="photo" src="/static/platform/v2.2.0/images/xida.jpg" alt=""></li>
        <li>
            <span class="name"><%--习大大--%></span>
            <span class="age"><%--年龄： 23--%></span>
            <p>上次登录时间：<em class="last-login"><%--2017-3-31--%></em></p>
        </li>
        <li>
            <a href="javascript:void(0)" class="btn btn-1 mobile"></a>
            <a href="javascript:void(0)" class="btn btn-2 email"></a>
        </li>
        <li>
            <div><span class="news">8</span></div>
            <p>我的消息</p>
        </li>
    </ul>
</section>

<script>
    getUserInfo($('.photo'), $('.name'), $('.age'), $('.mobile'), $('.email'), $('.news'), $('.last-login'));
</script>
