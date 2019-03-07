<%--    舍弃

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<shiro:hasPermission name="user:0">
    <ul class="main-nav">
        <li>
            <a href="/store/home">门店主页</a>
        </li>
        <li>
            <a href="/orderManage/order">任务提醒</a>
        </li>
        <li>
            <a href="/org/employee">员工管理</a>
        </li>
        <li>
            <a href="/org/memberManage/store">会员管理</a>
        </li>
        <li>
            <a href="/store/finance">收益管理</a>
        </li>
        <li>
            <a href="/org/service">服务项目管理</a>
        </li>
        <li>
            <a href="#">账户安全</a>
        </li>
        <li>
            <a href="/message/store">消息中心</a>
        </li>

        <li>
            <a href="/org/profile/store">门店信息</a>
        </li>
    </ul>
</shiro:hasPermission>
<shiro:hasPermission name="user:2">
    <ul class="main-nav">
        <li>
            <a href="/store/home">个体门店主页</a>
        </li>
        <li>
            <a href="/org/memberManage/store">会员管理</a>
        </li>
        <li>
            <a href="/store/finance">收益管理</a>
        </li>
        <li>
            <a href="/org/service">产品项目管理</a>
        <li>
            <a href="/orderManage/ordertodo">&lt;%&ndash;<i class="news">4</i>&ndash;%&gt;服务管理</a>
        </li>
        </li>
        <li>
            <a href="/message/store">消息中心</a>
        </li>
        <li>
            <a href="/org/profile/services">个人资料</a>
        </li>
        <li>
            <a href="/org/profile/store">门店消息</a>
        </li>
    </ul>
</shiro:hasPermission>
<script>
    $('.main-nav').on('click', 'li', function () {
        if ($(this).hasClass('menu-current')) {
            return;
        }
        var $precurrent = $('.menu-current');
        $(this).addClass('menu-current');
        $precurrent.removeClass('menu-current');
    })
</script>--%>
