<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="topbar wrap">
    <div class="console-topbar">
        <div
            style="float: left; width: 200px; border-right: 1px solid #ddd;">
            <a href="#" target="_top"
                class="topbar-logo"> <img
                src="/static/images/logo.png" alt="">
            </a>
        </div>
        <div class="topbar-infor topbar-right topbar-clearfix">
            <ul>
                <li class="infor-center topbar-left"
                    style="position: relative"><img
                    src="/static/images/message.png" height="16" /> <a
                    href="#"> <span>消息中心</span><span class="unread"
                        style="display: none">99+</span>
                </a></li>
                <li class="help topbar-left"><img
                    src="/static/images/hel.png" width="16" /> <a
                    href="javascript:_help();"> <span>帮助</span>
                </a></li>
                <li class="username topbar-left"><a
                    href="#" > <img
                        src="/static/images/photo.png" class="userhead" />
                        <span id="_userName"></span>
                </a></li>
                <li class="exit topbar-left"><img
                    src="/static/images/exist.png" width="16" /> <a
                    href="javascript:quitLogin();"> <span>退出</span>
                </a></li>
            </ul>
        </div>
    </div>
</div>
<script>
    _setUserMessage();
</script>