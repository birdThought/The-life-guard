<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>业务员&分销商注册</title>
    <meta charset="utf-8"/>
    <!-- <link rel="stylesheet" href="/static/plugin/login/css/style.css" media="screen" type="text/css"/>-->
    <link rel="stylesheet" href="/static/plugin/layui/v2.1.2/css/layui.css" media="screen" type="text/css"/>
    <link rel="stylesheet" href="/static/platform/register/register.css">
    <link rel="stylesheet" href="/static/platform/register/reset.css">
</head>
<body style="overflow: hidden;">
<div id="window" >
    <div class="page page-front">
        <div id="login_form">
                <div class="register-font">
                    <label class="user-reginster-font">业务员&分销商注册</label>
                </div>
            <div class="page-content">
            
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>登录账户：</label>
                    <input id="userName" name="userName" placeholder="请输入6~16位登录账户" type=text data-fyll="hackmeplease" class="input fadeIn delay3 userName"/>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>密码：</label>
                    <input id="pwd" name="pwd" placeholder="请输入6~16位包含大小写英文和数字！" type="password" data-fyll="hackmeplease" class="input fadeIn delay3 pwd"/>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>确认密码：</label>
                    <input id="password" name="password" placeholder="确认您的密码" type="password" data-fyll="hackmeplease" class="input fadeIn delay3 password"/>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-guanliyuan"></i>
                    <label class="param"><span class="warn">*</span>真实姓名：</label>
                    <input id="name" name="name" type="text" placeholder="请输入2~6位的真实姓名!" data-fyll="pineapple" class="input fadeIn delay3 name"/>
                </div>
                <!-- <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>联系人：</label>
                    <input id="contactMan" name="contactMan" placeholder="请输入联系人" type=text data-fyll="hackmeplease" class="input fadeIn delay3 contactMan"/>
                </div>
                
               <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>邮箱：</label>
                    <input id="email" name="email" placeholder="请输入邮箱" type=text data-fyll="hackmeplease" class="input fadeIn delay3 email"/>
                </div> -->
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>联系号码：</label>
                    <input id="mobile" name="mobile" placeholder="请输入联系号码" type=text data-fyll="hackmeplease" class="input fadeIn delay3 mobile"/>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <input id="verifyCode" name="verifyCode" type="text" class="verifyCode" style="width:240px;margin-left: 140px">
                    <input  type="button" class="get-code" value="获取验证码" onclick="register.getCode()">
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>省：</label>
                    <select id="province" name="province" class="select-btn" onchange="register.provinceChange()">
                        <c:forEach items="${province}" var="item">
                            <option value="${item.code}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>市：</label>
                    <select id="city" name="city" class="select-btn" onchange="register.cityChange()">
                        <c:forEach items="${city}" var="item">
                            <option value="${item.code}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param"><span class="warn">*</span>区：</label>
                    <select id="area" name="area" class="select-btn">
                        <c:forEach items="${area}" var="item">
                            <option value="${item.code}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param">详细地址：</label>
                    <input id="address" name="address" placeholder="请输入详细地址" type=text data-fyll="hackmeplease" class="input fadeIn delay3 address"/>
                </div>
                <!-- <div class="input-row">
                    <i class="fadeIn iconfont icon-mima"></i>
                    <label class="param">引荐人编号：</label>
                    <input id="parentId" name="parentId" placeholder="请输入引荐人编号" type=text data-fyll="hackmeplease" class="input fadeIn delay3 parentId"/>
                </div> -->
                
                <div class="input-row" style="padding: 50px 0px 50px 80px">
                    <button class="button load-btn fadeIn delay4" onclick="register.addSumit()" style="width:230px;float: left;margin-left: 80px">
                        <span class="default"><i class="ion-arrow-right-b"></i>保存</span>
                        <div class="load-state">
                            <div class="ball"></div>
                            <div class="ball"></div>
                            <div class="ball"></div>
                        </div>
                    </button>
                    <!-- <button class="button load-btn fadeIn delay4" onclick="history.go(-1)" style="width:130px;float: left;margin-left: 50px">
                        <span class="default"><i class="ion-arrow-right-b"></i>返回</span>
                        <div class="load-state">
                            <div class="ball"></div>
                            <div class="ball"></div>
                            <div class="ball"></div>
                        </div>
                    </button> -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- <script src="/static/js/login/register.js"></script> -->
<script type="text/javascript" src="/static/plugin/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/static/plugin/layui/v2.1.2/layui.js"></script>
<!--  <script type="text/javascript" src="/static/plugin/login/js/cav.js"></script> 
<script type="text/javascript" src='/static/plugin/login/js/fyll.js'></script> 
<script type="text/javascript" src="/static/plugin/login/js/index.js"></script> -->
<script type="text/javascript" src="/static/js/common/http.js?v=1.1.0"></script>
<!-- <script type="text/javascript" src="/static/js/login/login.js?v=1.1.0"></script> -->
<script type="text/javascript" src="/static/js/register/register.js"></script>
<script>
    register.init();
</script>
</body>
</html>
