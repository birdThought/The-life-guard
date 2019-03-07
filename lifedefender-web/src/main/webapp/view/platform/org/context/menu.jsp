
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasRole name="orgType:1">  <%--服务机构--%>
    <shiro:hasRole name="user:1">  <%--服务师--%>
        <ul class="main-nav">
            <li>
                <a href="/org/services/home">主页<i></i></a>
            </li>
            <li>
                <a href="/message">消息中心<%--<i class="news">3</i>--%><i></i></a>
            </li>
            <li>
                <a href="/org/memberManage">会员管理<i></i></a>
            </li>
            <li>
                <a href="/orderManage/ordertodo">任务管理<i></i></a>
            </li>
            <li>
                <a href="/org/profile/services">个人资料<i></i></a>
            </li>
            <li>
                <a href="/org/profile/services/accountsecurity">账户安全<i></i></a>
            </li>
        </ul>
    </shiro:hasRole>
    <shiro:hasRole name="user:0">  <%--管理员--%>
        <ul class="main-nav">
            <li>
                <a href="/store/home"><i></i>门店主页</a>
            </li>
            <li>
                <a href="/orderManage/order"><i></i>任务提醒</a>
            </li>
            <li>
                <a href="/org/employee"><i></i>员工管理</a>
            </li>
            <li>
                <a href="/org/memberManage"><i></i>会员管理</a>
            </li>
            <li>
                <a href="/store/finance"><i></i>财务管理</a>
            </li>
            <li>
                <a href="/org/service"><i></i>服务管理</a>
            </li>
            <li >
                <a href="/org/data-statistics"><i></i>数据统计报表</a>
            </li>
            <li >
                <a href="/org/push"><i></i>推送管理</a>
            </li>
            <li>
                <a href="/org/offlineManage"><i></i>用户管理</a>
            </li>
            <li>
                <a href="/orderManage/orderListManage"><i></i>订单管理</a>
            </li>
            <li>
                <a href="/message"><i></i>消息中心</a>
            </li>
            <li>
                <a href="/org/profile/store"><i></i>门店信息</a>
            </li>
            <li>
                <a href="/org/profile/services/accountsecurity"><i></i>账户安全</a>
            </li>

        </ul>
    </shiro:hasRole>
</shiro:hasRole>
<shiro:hasRole name="orgType:2">  <%--个体机构--%>
    <ul class="main-nav">
        <li class="per-home">
            <a href="/store/home"><i></i>个体门店主页</a>
        </li>
        <li class="member">
            <a href="/org/memberManage"><i></i>会员管理</a>
        </li>
        <li class="finance">
            <a href="/store/finance"><i></i>财务管理</a>
        </li>
        <li class="service">
            <a href="/org/service"><i></i>服务管理</a>
        </li>
        <li class="task">
            <a href="/orderManage/ordertodo"><%--<i class="news">4</i>--%><i></i>任务管理</a>
        </li>
        <li >
            <a href="/org/data-statistics"><i></i>数据统计报表</a>
        </li>
        <li >
            <a href="/org/push"><i></i>推送管理</a>
        </li>
        <li class="offline">
            <a href="/org/offlineManage"><i></i>用户管理</a>
        </li>
        <li class="order-info">
            <a href="/orderManage/orderListManage"><i></i>订单管理</a>
        </li>
        <li class="message-center">
            <a href="/message"><i></i>消息中心</a>
        </li>
        <li class="per-info">
            <a href="/org/profile/services"><i></i>个人资料</a>
        </li>
        <li class="store-info">
            <a href="/org/profile/store"><i></i>门店信息</a>
        </li>
        <li class="store-security">
            <a href="/org/profile/services/accountsecurity"><i></i>账号安全</a>
        </li>
    </ul>
    <style>
        .main-nav li.member > a:before{
            background-position:left -336px;
        }
        .main-nav li.order-info > a:before{
            background-position:left -336px;
        }
        .main-nav li.offline > a:before{
            background-position:left -336px;
        }
        .main-nav li.finance > a:before{
            background-position:left -290px;
        }
        .main-nav li.service > a:before{
            background-position:left -384px;
        }
        .main-nav li.task > a:before{
            background-position:left -408px;
        }
        .main-nav li.message-center > a:before{
            background-position:left -192px;
        }
        .main-nav li.store-info > a:before{
            background-position:left -216px;
        }
        .main-nav li.per-info > a:before{
            background-position:left -360px;
        }
        .main-nav li.store-security > a:before{
            background-position:left -431px;
        }



        .main-nav li.order-info:hover > a:before{
            background-position:-26px -336px;
        }
        .main-nav li.offline:hover > a:before{
            background-position:-26px -336px;
        }
        
        .main-nav li.per-home:hover > a:before{
            background-position:-26px -311px;
        }
        .main-nav li.per-info:hover > a:before{
            background-position:-26px -360px;
        }
        .main-nav li.member:hover > a:before{
            background-position:-26px -336px;
        }
        .main-nav li.finance:hover > a:before{
            background-position:-26px -290px;
        }
        .main-nav li.service:hover > a:before{
            background-position:-26px -384px;
        }
        .main-nav li.task:hover > a:before{
            background-position:-26px -408px;
        }
        .main-nav li.message:hover > a:before{
            background-position:-26px -192px;
        }
        .main-nav li.store-info:hover > a:before{
            background-position:-26px -216px;
        }
        .main-nav li.store-security:hover > a:before{
            background-position:-26px -431px;
        }

    </style>
</shiro:hasRole>

<script>
   /*  $('.main-nav').on('click', 'li', function () {
        $(this).addClass('menu-current').siblings().removeClass('menu-current');
//        $(this).trigger('hover');
        $(this).children('a').css("color","#fff").parent().siblings('li').children('a').css("color","#333");
    }) */
     $('.main-nav li').on('click', function () {
    	
        $(this).addClass('menu-current').siblings().removeClass('menu-current');
         
        $(this).children('a').css("color","#fff").parent().siblings('li').children('a').css("color","#333");
        
        
        var yuansu = $(this).find('i').parent().parent();
       

       $(this).find('i').css('backgroundPositionX', '-26px').parent().parent().siblings().find('i').css('backgroundPositionX','left')
    })

</script>