<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<script type="text/javascript" src="/static/plugins/hx/strophe.js"></script>
<script type="text/javascript" src="/static/plugins/hx/websdk-1.1.2.js"></script>
<script type="text/javascript" src="/static/plugins/hx/websdk.shim.js"></script>
<script type="text/javascript" src="/static/plugins/hx/webim.config.js"></script>--%>

<script type="text/javascript" src="/static/plugins/hx/v1.4.13/webim.config.js"></script>
<script type="text/javascript" src="/static/plugins/hx/v1.4.13/strophe-1.2.8.js"></script>
<script type="text/javascript" src="/static/plugins/hx/v1.4.13/websdk-1.4.13.js"></script>
<script type="text/javascript" src="/static/plugins/hx/v1.4.13/websdk.shim.js"></script>

<script src = "/static/platform/v2.2.0/js/org/common/common.js?v=2.2.0"></script>
<style>
    #qrcode{background:url(/static/images/erweimabg.png) no-repeat;
        left: 532px;
        top: 73px;
        position: absolute;
    }
    #qrcode div input{
        position:absolute;
    }
    #qrcode a .p2{background:#44c660;padding:2px 4px;margin:15px -11px;border-radius:8px;-moz-border-radius:8px;color:white;text-align:center}

</style>
<shiro:hasRole name="user:1">    <%--服务师--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-org-home.css?v=2.2.0">
    <section class="main-top">
        <ul class="banxin clearfix">
            <li><img class="photo" src="" alt=""></li>
            <li>
                <span class="name"><%--习大大--%></span>
                <p >隶属机构：<label class="org-name"><%--广东省中医院--%></label></p>
                <p style="width: 500px;">
                    职称：<label class="userType"><%--三级营养师--%></label>
                    <img src="/static/platform/v2.2.0/images/qrcode-template.png"
                         onclick="common.popupQrCode()"
                         style="margin-left: 43px;">
                    <label class="userId" style="margin-left: 42px;"><%--编号: 135--%></label>
                </p>


            </li>
            <li>
                <a href="javascript:void(0)" class="btn btn-1"></a>
                <a href="javascript:void(0)" class="btn btn-2"></a>
                <a href="javascript:void(0)" class="btn btn-3"></a>
            </li>
            <li class="cursor-pointer" onclick = "javascript:window.location.href = '/message'">
                <div><span class="news hidden">0</span></div>
                <p>我的消息</p>
            </li>
            <input type="hidden" class="userCode">
        </ul>
    </section>
    <div id="qrcode" class="none" style="left: 648px;"></div>
    <script>
        common.checkOrgUserStatus($('.photo'), $('.name'), $('.org-name'), $('.userType'), $('.btn-1'), $('.btn-2'), $('.btn-3'), $('.news'), $('.userCode'), $('.userId'));
        var left = $('.userType').offset().left;
    </script>
</shiro:hasRole>
<shiro:hasRole name="user:0">    <%--门店--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">
    <section class="main-top">
        <ul class="banxin clearfix">
            <li>
                <img class="logo" src="" alt="">
            </li>
            <li>
                <span class="orgName"  title=""><%--果然健康养生馆--%></span>
                <div class="clearfix">
                    <label class="fl">安全等级：</label>
                    <div class="fl strength">
                        <div></div>
                    </div>
                    <div class="fl">中</div>
                </div>
                <img src="/static/platform/v2.2.0/images/qrcode-template.png"
                     onclick="common.popupQrCode()">
                <label class="userId" style="margin-left: 42px;"><%--编号: 135--%></label>
            </li>
            <li>
                <a href="javascript:void(0)" class="btn btn-1"></a>
                <a href="javascript:void(0)" class="btn btn-2"></a>
                <a href="javascript:void(0)" class="btn btn-3"></a>
            </li>
            
            <li class="cursor-pointer" onclick = "javascript:window.location.href = '/message'">
                <div><span class="news hidden">0</span></div>
                <p>我的消息</p>
            </li>
        </ul>
    </section>
    <div id="qrcode" class="none"></div>
    <script>
        common.getStoreInfo($('.logo'), $('.orgName'), $('.btn-1'), $('.btn-2'), $('.btn-3'), $('.news'), $('.userId'))
    </script>
</shiro:hasRole>
<shiro:hasRole name="user:2">    <%--个体门店--%>
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">
    <section class="main-top">
        <ul class="banxin clearfix">
            <li>
                <img class="logo" src="" alt="">
            </li>
            <li>
                <span class="orgName"><%--果然健康养生馆--%></span>

                <div class="clearfix">
                    <label class="fl">安全等级：</label>
                    <div class="fl strength">
                        <div></div>
                    </div>
                    <div class="fl">中</div>
                </div>
                <img src="/static/platform/v2.2.0/images/qrcode-template.png"
                      onclick="common.popupQrCode()">
                <label class="userId" style="margin-left: 42px;"><%--编号: 135--%></label>
            </li>
            <li>
                <a href="javascript:void(0)" class="btn btn-1"></a>
                <a href="javascript:void(0)" class="btn btn-2"></a>
                <a href="javascript:void(0)" class="btn btn-3"></a>
            </li>
            <li class="cursor-pointer" onclick = "javascript:window.location.href = '/message'">
                    <div><span class="news hidden">0</span></div>
                    <p>我的消息</p>
            </li>
        </ul>
    </section>
    <div id="qrcode" class="none"></div>
    <script>
        common.getStoreInfo($('.logo'), $('.orgName'), $('.btn-1'), $('.btn-2'), $('.btn-3'), $('.news'), $('.userId'))
    </script>
</shiro:hasRole>


<%--<img src="/static/platform/v2.2.0/images/loading.gif"
     style="position: relative; top: 60px; left: 40px;">--%>

