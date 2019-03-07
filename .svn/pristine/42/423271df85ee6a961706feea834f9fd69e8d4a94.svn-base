
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <%--<link rel="stylesheet" href="/static/plugins/layui/css/layui.css">--%>
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/org-service-manage.css?v=2.2.0">
    <title>项目管理</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" style="background-color: white;">
            <div class="items-nav">
                <ul>
                    <li class="current cursor-pointer" @click = "projectStatus = null">全部项目</li>
                    <li class="cursor-pointer" @click = "projectStatus = 1">待上线项目</li>
                    <li class="cursor-pointer" @click = "projectStatus = 2">已上线项目</li>
                    <li class="cursor-pointer" @click = "projectStatus = 3">已下线项目</li>
                </ul>
                <span class="cursor-pointer" @click = "publishProject()">发布项目</span>
            </div>
            <div class="clearfix type-search">
                <div class="fl">
                    <label>类型</label>
                    <select name="1" v-model = "projectType">
                        <option value="0">全部服务</option>
                        <option value="1">咨询服务</option>
                        <option value="4">课堂服务</option>
                        <option value="2">到店服务</option>
                        <option value="3">上门服务</option>
                    </select>
                </div>
                <div class="fr">
                    <input type="text" placeholder="搜索相关项目" v-model = "search">
                </div>
            </div>
            <div class="items-content" v-cloak>
                <table <%--v-if = "serviceData != null && serviceData.length > 0"--%>>
                    <thead>
                    <tr>
                        <th>项目名称</th>
                        <th>项目类型</th>
                        <th>销量</th>
                        <th>状态</th>
                        <th>最近变更时间</th>
                        <th>操作</th>
                        <th style="width: 150px">服务师</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-cloak v-for = "s in serviceData" :code = "s.code" :projectType = "s.projectType">
                        <td>
                            <div style="text-align: center;width: 100%">
                                <img v-if = "s.image != null" :src = "s.image" alt="">
                                <img v-else src = "/static/images/index/nopic.jpg" alt="">
                            </div>
                            <div style="text-align: center;width: 100%">{{s.name}}</div>
                        </td>
                        <td>{{s.projectType | projectType}}</td>
                        <td>{{s.sales}}</td>
                        <td>{{s.status | projectStatus}}</td>
                        <td>{{s.modifyDate | date('yyyy-MM-dd hh:mm')}}</td>
                        <td v-html = "getOperation(s.status, s.projectType, s.serveId, s.code)">
                            <template ></template>
                        </td>
                        <td>
                            <template v-for = "(u, index) in s.orgUser">
                                {{u.realName}}
                                <template v-if = "index < s.orgUser.length - 1">、</template>
                            </template>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7" style="border: 0px;">
                            <div id="servicePage" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <%--<div v-else style = "margin-top: 100px; text-align: center;">
                    <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                    <br/><br/>
                    <p style="font-size: 18px; color: gray;">暂无员工</p>
                </div>--%>

                <table <%--v-if = "preOnlineService != null && preOnlineService.length > 0"--%>>
                    <thead>
                    <tr>
                        <th>项目名称</th>
                        <th>销量</th>
                        <th>状态</th>
                        <th>最近变更时间</th>
                        <th>操作</th>
                        <th>服务师</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for = "s in preOnlineService" :code = "s.code" :projectType = "s.projectType">
                        <td>
                            <div><img :src = "s.image" alt=""></div>
                            <div>{{s.name}}</div>
                        </td>
                        <td>{{s.sales}}</td>
                        <td>{{s.status | projectStatus}}</td>
                        <td>{{s.modifyDate | date('yyyy-MM-dd hh:mm')}}</td>
                        <td v-html = "getOperation(s.status, s.projectType)">
                            <template ></template>
                        </td>
                        <td>
                            <template v-for = "(u, index) in s.orgUser">
                                {{u.realName}}
                                <template v-if = "index < s.orgUser.length - 1">、</template>
                            </template>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="border: 0px;">
                            <div id="preOnlinePage" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
                        </td>
                    </tr>
                    </tbody>
                </table >
                <%--<div v-else style = "margin-top: 100px; text-align: center;">
                    <img src="/static/platform/v2.2.0/images/org/org-no-news.png">
                    <br/><br/>
                    <p style="font-size: 18px; color: gray;">暂无员工</p>
                </div>--%>
                <table <%--v-if = "onlineService != null && onlineService.length > 0"--%>>
                    <thead>
                    <tr>
                        <th>项目名称</th>
                        <th>销量</th>
                        <th>状态</th>
                        <th>最近变更时间</th>
                        <th>操作</th>
                        <th>服务师</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for = "s in onlineService" :code = "s.code" :projectType = "s.projectType">
                        <td>
                            <div><img :src = "s.image" alt=""></div>
                            <div>{{s.name}}</div>
                        </td>
                        <td>{{s.sales}}</td>
                        <td>{{s.status | projectStatus}}</td>
                        <td>{{s.modifyDate | date('yyyy-MM-dd hh:mm')}}</td>
                        <td v-html = "getOperation(s.status, s.projectType)">
                            <template ></template>
                        </td>
                        <td>
                            <template v-for = "(u, index) in s.orgUser">
                                {{u.realName}}
                                <template v-if = "index < s.orgUser.length - 1">、</template>
                            </template>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="border: 0px;">
                            <div id="onlinePage" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <table <%--v-if = "offlineService != null && offlineService.length > 0"--%>>
                    <thead>
                    <tr>
                        <th>项目名称</th>
                        <th>销量</th>
                        <th>状态</th>
                        <th>最近变更时间</th>
                        <th>操作</th>
                        <th>服务师</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for = "s in offlineService" :code = "s.code" :projectType = "s.projectType">
                        <td>
                            <div><img :src = "s.image" alt=""></div>
                            <div>{{s.name}}</div>
                        </td>
                        <td>{{s.sales}}</td>
                        <td>{{s.status | projectStatus}}</td>
                        <td>{{s.modifyDate | date('yyyy-MM-dd hh:mm')}}</td>
                        <td v-html = "getOperation(s.status, s.projectType)">
                            <template ></template>
                        </td>
                        <td>
                            <template v-for = "(u, index) in s.orgUser">
                                {{u.realName}}
                                <template v-if = "index < s.orgUser.length - 1">、</template>
                            </template>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="border: 0px;">
                            <div id="offlinePage" class="page_Container" style = "background-color: white; width: 100%; text-align: center;"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <%--添加项目-选择类型窗口--%>
            <div class="chose-type none">
                <h3>请选择您的服务项目</h3>
                <ul>
                    <li>
                        <div>
                            <i class="cursor-pointer" @click = "goPublishProject('consult')"></i>
                            <span>咨询服务(线上)</span>
                        </div>
                    </li>
                    <li>
                        <div>
                             <i class="cursor-pointer" @click = "goPublishProject('lesson')"></i>
                             <span>课堂服务(线上)</span>
                        </div>
                    </li>
                    <li>
                        <div>
                          	<i class="cursor-pointer" @click = "goPublishProject('offline')"></i>
                            <span>到店服务(线下)</span>
                        </div>
                    </li>
                    <li>
                        <div>
                             <i class="cursor-pointer" @click = "goPublishProject('visit')"></i>
                            <span>上门服务(线下)</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src = "/static/platform/v2.2.0/js/org/servicemanage/service-manage.js?v=2.2.0"></script>
<script src = "/static/common/js/dateFormat.js"></script>
<script src = "/static/common/js/pageUtils.js?v=2.2.0"></script>
<script>
    layui.use('layer');
    service.init();
    service.vm.service = '${service}' == '' ? '' : ${service};

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(5).click();
    } else {
        $('.main-nav li').eq(3).click();
    }
//    pageUtil.getPageUtil();
</script>

