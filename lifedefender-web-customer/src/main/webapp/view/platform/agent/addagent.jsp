<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">

<div class="right_body" ng-controller="agentAddController" ng-init="ag.init()">
    <div class="right_title">
        <label class="action">
            代理商添加
        </label>
    </div>
    <div class="item_contain" style="margin-top: 15px;">
        <form method="post" id="orgForm">
            <div style="padding-top:20px;padding-left:20px">
                <div class="param_set" style="clear:both;">
                    <label class="param"><span class="warn">*</span>代理商名称：</label>
                    <input name="agentName" ng-model="data.submit.agentName" type="text"  placeholder="请输入代理商名称"/>
                </div>
                <div class="param_set" style="clear:both;">
                    <label class="param"><span class="warn">*</span>真实姓名：</label>
                    <input name="name" ng-model="data.submit.name" type="text"  placeholder="请输入2~6位的真实姓名!"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>电话号码：</label>
                    <input type="text"  ng-model="data.submit.phone" placeholder="请输入联系号码" name=phone/>
                </div>
                <div class="param_set">
                    <label class="param">邮箱：</label>
                    <input type="text" ng-model="data.submit.email" placeholder="请输入邮箱地址" name="email"/>
                </div>

                <div class="param_set">
                    <label class="param"><span class="warn">*</span>省：</label>
                    <select ng-change="ag.getCitys()" ng-model="add.p" name="type" class="select-btn" id="province" style="width:250px;" 
                     ng-options="p.code as p.name for p in data.Province">
                    </select>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>市：</label>
                    <select name="type" ng-change="ag.getArea()" class="select-btn" id="city" style="width:250px;" ng-model="add.c">
                        <option ng-repeat="c in data.city" ng-value="c.code">{{c.name}}</option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>区：</label>
                    <select name="type" class="select-btn" id="area" style="width:250px;" ng-model="add.d">
                        <option ng-repeat="d in data.area" ng-value="d.code">{{d.name}}</option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param">详细地址：</label>
                    <input type="text" ng-model="data.submit.address" placeholder="请输入详细地址" name="address"/>
                </div>

                </div>

                <!--账户信息-->
                <div class="item_contain" style="clear: both">
                    <div class="item_title">
                        <label class="title"> 账户信息 </label>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>登录账户：</label><input
                            ng-model="data.submit.userName" type="text" placeholder="请输入账户、英文数字组合"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>密码：</label><input
                            ng-model="data.submit.pwd" type="password" placeholder="请输入6~16位包含大小写英文和数字！"/>
                    </div>
                    <div class="param_set">
                        <label class="param"><span class="warn">*</span>确认密码：</label><input
                            name="confirm_psw" ng-model="data.submit.password" type="password" placeholder="确认一下密码"/>
                    </div>
                    <%--<div class="param_set">
                        <label class="param"><span class="warn">*</span>用户角色：</label>
                         <select lay-search ng-model="data.submit.reloId" ng-options="c.id as c.name for c in data.customer">
                            <option value="">直接选择或搜索</option>
                        </select>
                    </div>--%>
                </div>

                <div style="padding:10px 110px 20px;clear: both;">
                    <input class="save" value="保存" ng-click="ag.addSumit()" type="button"/><input
                        class="back" value="返回" type="button" onclick="history.go(-1)"/>
                </div>
            </div>
        </form>
    </div>
</div>
