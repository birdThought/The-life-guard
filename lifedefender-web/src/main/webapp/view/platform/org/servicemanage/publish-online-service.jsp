<%--
  @Description: 线上服务
  @Author: wenxian.cai
  @Date: 2017/7/17 9:57
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <t:base type="jquery2.11,jquery.serializejson,layer,layui,vue"></t:base>
        <meta charset=utf-8>
        <meta name=description content="">
        <meta name=viewport content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
        <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
        <link rel="stylesheet" href="/static/platform/v2.2.0/css/magiccheck.css?v=2.2.0">
        <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
        <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
        <link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
        <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/publish-service.css?v=2.2.0">
        <title>发布项目</title>
    </head>
    <body >
    <%@include file="/view/platform/org/context/header.jsp"%>
    <!-- /header -->
        <article>
            <%@include file="/view/platform/org/context/admin.jsp"%>
            <section class="banxin container vue-content">
                <%@include file="/view/platform/org/context/menu.jsp"%>
                    <div class="main-content" style="background-color: white;">
                        <div class="items-link">
                            <a href="/org/service">服务管理</a><em>></em>
                            <a href="#">发布项目</a><em>></em>
                            <span v-cloak v-if = "projectType == 'consult'">咨询服务</span>
                            <span v-cloak v-else>课堂服务</span>
                        </div>
                        <div class="online-service">
                            <section class="online-top">
                                <ul class="clearfix">
                                    <li class="on fl">线上服务</li>
                                    <li class="btn fr">上单新手指南</li>
                                </ul>
                                <p>
                                    <label>温馨提示：</label>
                                    “生命守护平台”将收取入驻机构与客户单笔交易额的10%费率作为平台服务费。
                                </p>
                            </section>
                            <section class="chose-type">
                                <div class="explain">选择项目类别</div>
                                <div class="items-type">
                                    <ul class="grade">
                                        <li>选择一级品类</li>
                                        <li>选择二级品类</li>

                                    </ul>
                                    <div class="items-content" v-cloak>
                                        <dl class="tab-nav cursor-pointer">
                                            <dd v-for = "s in serveType" :value = "s.id" @click = "clickServe(s.id, s.name)">
                                                {{s.name}}<em>></em>
                                            </dd>

                                        </dl>
                                        <div class="tab-content cursor-pointer">
                                            <dl v-for = "s in serveType">
                                                <dd v-for = "t in s.secondaryServe" :value = "t.id" @click = "clickSecondaryServe(t.id, t.name)">{{t.name}}</dd>
                                            </dl>
                                        </div>
                                    </div>
                                    <p v-cloak>
                                        <span>当前选择：</span>
                                        <span v-if = "currentServe!= null">{{currentServe.name}}</span>
                                        <em>></em>
                                        <span v-if = "currentSecondaryServe!= null">{{currentSecondaryServe.name}}</span>
                                    </p>
                                </div>
                            </section>
                            <section class="product">
                                <div class="explain">产品图片</div>
                                <div class="product-pic">
                                    <div>
                                        <template v-if = "project.image == null">
                                            <img  class="serve-image-show-1 cursor-pointer" @click = "clickPic(1)"
                                                  src="/static/platform/v2.2.0/images/register/businessPic.png"
                                                  style="width: 100%;height: 100%;" alt="">
                                            <input class="serve-image-hidden-1" type="hidden">
                                        </template>
                                        <template v-else>
                                            <img class="serve-image-show-1 cursor-pointer" @click = "clickPic(1)"
                                                 :src="project.image"
                                                 style="width: 100%;height: 100%;" alt="" >
                                            <input class="serve-image-hidden-1" type="hidden" :value = "project.image">
                                        </template>
                                        <input type="file" name = "path" class="path-1 none">


                                    </div>
                                    <%--<p class="clearfix">
                                        <span class="fl">-删除图片</span>
                                        <span class="fr">+编辑图片</span>
                                    </p>--%>
                                </div>
                            </section>
                            <section class="class-pro none">
                                <div class="explain">课堂属性</div>
                                <form class="lesson-form">
                                <table>

                                        <tbody>
                                        <tr>
                                            <td>课堂名称：</td>
                                            <td>
                                                <input v-if = "project.name == null" type="text" name = "serviceName" placeholder="请输入课堂名称(最多29个字符)"
                                                       style="height: 40px; width: 100%; border: 0px; margin-left: -26px; font-size: 14px;"
                                                       >
                                                <input v-else type="text" name = "serviceName" placeholder="请输入课堂名称(最多29个字符)"
                                                       style="height: 40px; width: 100%; border: 0px; margin-left: -26px; font-size: 14px;"
                                                       :value = "project.name | subStr(1)">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>开课时间：</td>
                                            <td>
                                                <div class="time-content" style="">
                                                    <span v-for = "(t, index) in lessonTime" class="lesson-time-item cursor-pointer"
                                                          @dblclick = "removeLessonTime($event, index)"
                                                          @mouseover = "showLessonTimeTip($event)">
                                                        <template v-for = "d in t.date">{{d | changeWeek}} | </template>
                                                        {{t.time}}
                                                    </span>
                                                </div>
                                                <div class="time-btn" @click = "popupLessonTime()">添加</div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>课堂有效期：</td>
                                            <td style="padding-left: 10px;">
                                                <input v-if = "project.startDate == null" class="date lesson-date-start" name="startDate" id="date"
                                                       style="width: 110px; height: 40px; border: 1px solid #cacaca;
                                                       text-indent: 16px; outline-color: #369239;
                                                       margin-right: 18px; font-size: 14px;" readonly >
                                                <input v-else class="date lesson-date-start" name="startDate" id="date"
                                                       style="width: 110px; height: 40px; border: 1px solid #cacaca;
                                                       text-indent: 16px; outline-color: #369239;
                                                       margin-right: 18px; font-size: 14px;" readonly :value = "project.startDate | date('yyyy-MM-dd')">
                                                至
                                                <input v-if = "project.endDate == null" class="date-2 lesson-date-end" name="endDate" id="date-2"
                                                       style="width: 110px; height: 40px; border: 1px solid #cacaca;
                                                       text-indent: 16px; outline-color: #369239;
                                                       margin-left: 18px; font-size: 14px;" readonly>
                                                <input v-else class="date-2 lesson-date-end" name="endDate" id="date-2"
                                                       style="width: 110px; height: 40px; border: 1px solid #cacaca;
                                                       text-indent: 16px; outline-color: #369239;
                                                       margin-left: 18px; font-size: 14px;" readonly :value = "project.endDate | date('yyyy-MM-dd')">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>课堂简介：</td>
                                            <td>
                                                <textarea class="lesson-introduce" name = "introduce"
                                                          placeholder="请输入课堂简介(最多250个字符)" v-model = "project.introduce">
                                                </textarea>
                                            </td>
                                        </tr>
                                        <tr class="model">
                                            <td>课堂收费模式：</td>
                                            <td>
                                                <span class="free-btn" @click = "addFree($event)">免费</span>
                                                <span class="charge" >课程收费
                                                    <input v-if = "isModify" type="text" name = "price"
                                                        onkeyup="publishService.vm.filterComboPrice(this)"
                                                        :value = "project.price">
                                                    <input v-else type="text" value="0" name = "price"
                                                           onkeyup="publishService.vm.filterComboPrice(this)"
                                                           >
                                                    <i>元</i>
                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>

                                </table>
                                </form>
                            </section>
                            <section class="dependent">
                                <div class="clearfix">
                                    <div class="explain fl">选择添加相关服务师</div>
                                    <span class="fr add-btn cursor-pointer" @click = "popupOrgUserDialog">
                                    <em>+</em>
                                    添加服务师
                                    </span>
                                </div>
                                <div class="look-service">
                                    <template v-if = "selectOrgUserInfo.length == 0">
                                        <div class="service-add">
                                            <button class="service-add-org-user" @click = "popupOrgUserDialog">+ 添加服务师</button>
                                        </div>
                                    </template>
                                    <template v-else>
                                        <ul class="service-enginneer clearfix" id="ser-con" v-cloak>
                                            <li class="add-border" v-for = "(s, index) in selectOrgUserInfo">
                                                    <img v-if = "s.photo != '' && s.photo != null" :src = "s.photo" alt="">
                                                    <img v-else src = "/static/images/index/nopic.jpg" alt="">
                                                </div>
                                                <p>{{s.realName}}</p>
                                                <p v-if = "projectType!= null && projectType == 'consult'" style="width:60%;heigth:80%" align="right">
                                                       图文咨询
                                                       <input v-if = "isModify" class="org-user-price" :id="'org-user-price-' + s.id" type="text" :value="s.price"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />
                                                       <input v-else class="org-user-price" :id="'org-user-price-' + s.id" type="text" value="0"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />元/次
                                                       <!-- <select style="border: 0px;" v-model = "chargeMode">
                                                           <option value="1">元/次</option>
                                                           <option value="2">元/月</option>
                                                           <option value="3">元/年</option>
                                                       </select> -->
                                                    </br>
                                                       <input v-if = "isModify" class="org-user-price" :id="'org-user-monthPrice-' + s.id" type="text" :value="s.monthPrice"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />
                                                       <input v-else class="org-user-price" :id="'org-user-monthPrice-' + s.id" type="text" value="0"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />元/月
                                                       <!-- <select style="border: 0px;" v-model = "chargeMode">
                                                           <option value="1">元/次</option>
                                                           <option value="2">元/月</option>
                                                           <option value="3">元/年</option>
                                                       </select> -->
                                                     </br> 
                                                       <input v-if = "isModify" class="org-user-price" :id="'org-user-yearPrice-' + s.id" type="text" :value="s.yearPrice"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />
                                                       <input v-else class="org-user-price" :id="'org-user-yearPrice-' + s.id" type="text" value="0"
                                                              onkeyup="publishService.vm.filterComboPrice(this)" />元/年
                                                       <!-- <select style="border: 0px;" v-model = "chargeMode">
                                                           <option value="1">元/次</option>
                                                           <option value="2">元/月</option>
                                                           <option value="3">元/年</option>
                                                       </select> -->
                                                </p>
                                                
                                                <i class="cursor-pointer" @click = "deleteSelectOrgUser(s.id)">X</i>
                                                <div v-if = "index < selectOrgUserInfo.length - 1"
                                                     class="org-user-border"
                                                     style="height: 127px; width: 1px; margin-left:309px;
                                                     border-right: 1px solid #9a9a9a; margin-top: -142px;"></div>
                                            </li>


                                        </ul>
                                        <div  id="clickBtn">
                                            <a href="javascript:void(0)" class="pre" id = "pre"> < </a>
                                            <a href="javascript:void(0)" class="next" id = "next"> > </a>
                                        </div>
                                    </template>

                                </div>
                                <div class="items-btn">
                                    <template v-if = "!isModify">
                                        <span class="cursor-pointer" @click = "saveService(true)">发布项目</span>
                                        <span class="cursor-pointer" @click = "saveService(false)">预览/保存</span>
                                    </template>
                                    <template v-else>
                                        <span class="cursor-pointer" @click = "modifyService(project.status)">保存修改</span>
                                    </template>

                                </div>
                            </section>
                        </div>
                    </div>
                    <%--添加课堂窗口--%>
                    <div class="class-dialog lesson-dialog none">
                        <p>添加课堂时间</p>
                        <ul>
                            <li class="clearfix">
                                <h5 class="fl">开课日期</h5>
                                <dl class="fl">
                                    <dd v-for = "w in 7">
                                        <input class="magic-checkbox cursor-pointer" type="checkbox" :id = "w" :value = "w" v-model = "lessonWeek">
                                        <label :for = "w" class="cursor-pointer">{{w | changeWeek}}</label>
                                    </dd>
                                </dl>
                            </li>
                            <li class="clearfix">
                                <h5 class="fl">开课时间</h5>
                                <input type="text" class="fl add-bg cursor-pointer" name = "lesson-date"
                                       id = "lesson-date" placeholder="00:00" onclick="TimePicker.showTimePicker(this)"
                                       readonly >
                                <%-- <option value="1">09:30</option>
                                 <option value="2">16:30</option>--%>
                                </input>

                            </li>
                            <li class="set">
                                <span class="add-bg cursor-pointer" @click = "addLessonTime()">添加</span>
                            </li>
                        </ul>
                    </div>
                    <%--添加服务师窗口--%>
                    <div class="data-export org-user-list none">
                        <ul>
                            <li>
                                <input type="text" placeholder="搜索服务师" v-model = "searchOfOrgUser">
                            </li>
                            <li v-if = "projectType != null && projectType != 'lesson'">
                                <input type="checkbox" class="magic-checkbox" id="all" @click = "selectAllOrgUser($event)">
                                <label for="all">全选</label>
                            </li>
                        </ul>
                        <dl class="export-tell">
                            <dd class="org-user-item" v-for = "u in orgUsers" v-if = "u.display != false">
                                <img :src = "u.photo" style="width: 56px; height: 56px; border-radius: 3px;">
                                <a href="#">{{u.realName}}</a>
                                <span>{{u.gender == true ? '男' : '女'}}</span>
                                <div>
                                    <input type="checkbox" class="magic-checkbox" :value = "u.id" :id = "u.id"
                                           style="width: 37px; height: 20px;" v-model = "selectOrgUserId">
                                    <label :for = "u.id"></label>
                                </div>
                            </dd>
                        </dl>
                        <div style="text-align: center;">
                            <button class="service-add-org-user-btn" @click = "addOrgUser">添加</button>
                        </div>
                    </div>
            </section>

        </article>

    </body>
</html>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script src="/static/plugins/XzsTimePicker/TimerPicker.js"></script>
<script src="/static/platform/v2.2.0/js/org/servicemanage/publish-service.js"></script>
<script src="/static/common/js/dateFormat.js"></script>
<script>
    publishService.vm.project = '${project}' == '' ? {} : JSON.parse('${project}'.replace(/\\/g, '/'));
    if (!$.isEmptyObject(publishService.vm.project)) {
        publishService.vm.project.lessonTime = '${lessonTime}' == '' ? null : JSON.parse('${lessonTime}');
    }
    publishService.vm.isModify = Boolean('${isModify}');
    publishService.vm.type = '${type}';
    publishService.vm.selectOrgUserInfo = '${orgUsers}' == '' ? [] : JSON.parse('${orgUsers}'.replace(/\\/g, '/'))
    if (publishService.vm.selectOrgUserInfo.length > 0) {
        for (var i in publishService.vm.selectOrgUserInfo) {
            publishService.vm.selectOrgUserInfo[i].id = publishService.vm.selectOrgUserInfo[i].orgUserId;
        }

    }

    publishService.vm.projectType = '${projectType}';
    publishService.vm.serveType = '${serveType}' == '' ? '' : JSON.parse('${serveType}');
    publishService.initOnlineService();

    if ('${orgType}' == 1) {
        $('.main-nav li').eq(5).addClass('menu-current');
    } else {
        $('.main-nav li').eq(3).addClass('menu-current');
    }

</script>
<script>
    jeDate({
        dateCell : "#date",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        minDate:"1900-01-01",
        startMin:"1900-01-01",
        startMax:jeDate.now(0),
        zindex: 999,
    });
    jeDate({
        dateCell : "#date-2",
        format:"YYYY-MM-DD",
        isinitVal:true,
        initAddVal:[0],
        minDate:"1900-01-01",
        startMin:"1900-01-01",
        startMax:jeDate.now(0),
        zindex: 999,
    });
</script>