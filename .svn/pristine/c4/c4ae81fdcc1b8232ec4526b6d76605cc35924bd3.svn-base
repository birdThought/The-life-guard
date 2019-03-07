<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<nav class="menu-left">
    <ul>
        <li style="cursor:pointer;width:100%;">
            <div class="item top">
                <a href="orgControl.do?home" style="color:#fff;display:block"><img
                        src="/static/images/home.png"/> <span>主页</span></a>
            </div>
        </li>
        <li class="menu-parent menu-orgManage">
            <div class="item">
                <img src="/static/images/jigou.png"/> <span>管理机构</span> <img
                    class="cursor" src="/static/images/caretDown.png"/>
            </div>
            <ul class="child-menu">
                <li><a href="orgControl.do?orgManage"> <img/> <span>机构管理</span>
                </a></li>
                <li><a href="orgUserControl.do?employManage"> <img/> <span>员工管理</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent menu-orgServe">
            <div class="item">
                <img src="/static/images/door_menu.png" width="23"/> <span>服务机构</span>
                <img class="cursor" src="/static/images/caretDown.png"/>
            </div>
            <ul class="child-menu">
                <shiro:hasPermission name="user:0 or user:2">
                    <li><a href="orgServeControl.do?service"> <img/> <span>服务</span>
                    </a></li>
                    <li><a href="orgServeControl.do?serviceManage"> <img/> <span>服务管理</span>
                    </a></li>
                    <li><a href="orgUserControl.do?employManage"> <img/> <span>员工管理</span>
                    </a></li>
                    <li><a href="orgUserControl.do?userManage"> <img/> <span>用户管理</span>
                    </a></li>
                </shiro:hasPermission>
                <shiro:hasRole name="user:1">
                    <li><a href="orgServeControl.do?serviceManage"> <img/> <span>服务管理</span>
                    </a></li>
                </shiro:hasRole>
            </ul>
        </li>

        <li class="menu-parent menu-personOrgServe" style="display: none;">
            <div class="item">
                <img src="/static/images/door_menu.png" width="23"/> <span>个人机构</span>
                <img class="cursor" src="/static/images/caretDown.png"/>
            </div>
            <ul class="child-menu">
                <li><a href="orgServeControl.do?service"> <img/> <span>服务</span>
                </a></li>
                <li><a href="orgServeControl.do?serviceManage"> <img/> <span>服务管理</span>
                </a></li>
                <li><a href="orgUserControl.do?userManage"> <img/> <span>用户管理</span>
                </a></li>
                <li><a href="orgControl.do?companyDetailsPage"> <img/> <span>机构信息</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/account.png"/> <span>系统设置</span> <img
                    class="cursor" src="/static/images/caretDown.png"/>
            </div>
            <ul class="child-menu">
                <shiro:hasPermission name="user:0 or user:2">
                    <li><a href="orgControl.do?companyDetailsPage"> <img/> <span>机构信息</span>
                    </a></li>
                </shiro:hasPermission>
                <li><a href="orgUserControl.do?userInfo"> <img/> <span>个人信息</span>
                </a></li>
                <li><a href="orgSetControl.do?security"> <img/> <span>账号安全</span>
                </a></li>
                <li><a href="orgSetControl.do?suggestionPage"> <img/> <span>意见反馈</span>
                </a></li>
            </ul>
        </li>
    </ul>
</nav>
