<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-employee-manage.css?v=2.2.0">
    <title>员工管理</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article >
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-employee" style="background-color: white">
            <div class="vip-top">
                <div>
                    <input type="text" placeholder="员工搜索" style="text-align: center;" v-model = "search">
                </div>
                <span class="cursor-pointer" @click = "addEmployee">添加员工</span>
            </div>
            <div class="member-content clearfix">
                <ul class="member-content-lists fl" style="max-height: 566px; overflow: hidden; overflow-y: auto;">
                    <li v-for = "e in employee" v-cloak>
                        <img v-if = "e.photo != '' && e.photo != null" :src = "e.photo">
                        <img v-else src = "/static/images/index/nopic.jpg">
                        <a href="#">{{e.realName}}</a>
                    </li>
                </ul>

                <div class="member-state fl" v-if = "currentEmployee != null && employee.length > 0" v-cloak>
                    <ul>
                        <li>
                            <p>{{currentEmployee.countOfMember}}<span>人</span></p>
                            <span>会员数量</span>
                        </li>
                        <li>
                            <p>{{currentEmployee.countOfOrder}}<span>单</span></p>
                            <span>订单数量</span>
                        </li>
                        <li>
                            <p>{{currentEmployee.earning}}<span>元</span></p>
                            <span>本月收益</span>
                        </li>
                    </ul>
                    <table>
                        <tbody>
                        <tr>
                            <td width="50%">
                                <span class="key">姓名</span>
                                <span class="value">{{currentEmployee.realName}}</span>
                            </td>
                            <td>
                                <span class="key">服务项目</span>
                                <span class="value">暂未提供</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="key">性别</span>
                                <span class="value">{{currentEmployee.gender == true ? '男' : '女'}}</span>
                            </td>
                            <td>
                                <span class="key">职称</span>
                                <span class="value">{{currentEmployee.professionalName}}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="key">出生日期</span>
                                <span v-if = "currentEmployee.birthday != null" class="value">{{currentEmployee.birthday | date('yyyy-MM-dd')}}</span>
                                <span v-else class="value">无</span>
                            </td>
                            <td>
                                <span class="key">账号属性</span>
                                <span class="value">{{currentEmployee.userType | userType}}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="key">联系方式</span>
                                <span v-if = "currentEmployee.mobile != null" class="value">{{currentEmployee.mobile}}</span>
                                <span v-else class="value">无</span>
                            </td>
                            <td>
                                <span class="key">工作状态</span>
                                <%--<span class="value">{{currentEmployee.status | employeeStatus}}</span>--%>
                                <select name="" class="value" v-model = "employeeStatus">
                                    <option value="0">正常</option>
                                    <option value="4">离职</option>
                                    <option value="1">停用</option>
                                    <option value="2">注销</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="key">账号</span>
                                <span class="value">{{currentEmployee.userName}}</span>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td colspan="2" class="value">地址：
                                <template v-if = "currentEmployee.address != null" class="value">{{currentEmployee.address}}
                                </template>
                                <template v-else>无</template>
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td colspan="2" class="value">
                            	<!-- 更新按钮 -->
               					<button name="updateBtn" v-bind:value="currentEmployee.id">修改信息</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div> 
            <div style = "margin-top: 100px; text-align: center;"
                v-if = "employee != null && employee.length < 1">
                <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                <br/><br/>
                <p style="font-size: 18px; color: gray;">暂无员工</p>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/common/js/dateFormat.js"></script>
<script src="/static/platform/v2.2.0/js/org/employeemanage/employee-manage.js?v=2.2.0"></script>

<script>
    layui.use('layer')
    $('.main-nav').find('li').eq(2).click();
    employee.init();
    employee.vm.result = '${employee}' == '' ? '' : ${employee};
    employee.vm.tempEmployee = '${employee.data}' == '' ? '' : ${employee.data};
</script>

<script>
	$(function() {
		/* 更改按钮点击事件  */
		$('button[name="updateBtn"]').click(function (){
			var value = $(this).attr("value");
			window.location.href = '${pageContext.request.contextPath}/org/employee/toupdateemployee?id='+value;
		});
	})
</script>
