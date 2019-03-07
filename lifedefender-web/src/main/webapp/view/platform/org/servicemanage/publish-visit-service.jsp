<%--
  @Description: 线下与上门服务
  @Author: wenxian.cai
  @Date: 2017/7/17 9:49
 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/view/platform/context/mytags.jsp"%>
<html>
<head>
    <t:base type="jquery2.11,jquery.serializejson,layer,layui,vue"></t:base>
    <meta charset=utf-8>
    <meta name=description content="">
    <meta name=viewport content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/css/page.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/publish-service.css?v=2.2.0">
    <title>发布项目</title>
</head>
<body>
<%@include file="/view/platform/org/context/header.jsp"%>
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container vue-content">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content" style="background-color: white;">
            <div class="items-link" >
                <a href="/org/service">服务管理</a><em>></em>
                <a href="#">发布项目</a><em>></em>
                <span v-cloak v-if = "projectType == 'visit'">上门服务</span>
                <span v-cloak v-else>到店服务</span>
            </div>
            <div class="online-service">
                <section class="online-top">
                    <ul class="clearfix">
                        <li class="on fl" v-cloak v-if = "projectType == 'visit'">上门服务</li>
                        <li v-else  class="on fl">线下服务</li>
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
                        <div class="items-content">
                            <dl class="tab-nav cursor-pointer">
                                <dd v-for = "s in serveType" :value = "s.id" @click = "clickServe(s.id, s.name)" v-cloak>{{s.name}}<em>></em></dd>
                            </dl>
                            <div class="tab-content cursor-pointer">
                                <dl v-for = "s in serveType">
                                    <dd v-for = "t in s.secondaryServe" :value = "t.id" @click = "clickSecondaryServe(t.id, t.name)" v-cloak>{{t.name}}</dd>
                                </dl>
                            </div>
                        </div>
                        <p v-cloak>
                            <span>当前选择：</span>
                            <span v-if = "currentServe!= null" v-cloak>{{currentServe.name}}</span>
                            <em>></em>
                            <span v-if = "currentSecondaryServe!= null" v-cloak>{{currentSecondaryServe.name}}</span>
                        </p>
                    </div>
                </section>
                <section class="product">
                    <div class="explain">产品图片</div>
                    <div class="product-pic">
                        <section class="clearfix">
                            <div class="fl lg-img">
                                <template v-if = "!isModify">
                                    <img @click = "clickPic(1)" class="cursor-pointer serve-image-show-1" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-1 none" >
                                    <input class="serve-image-hidden-1" type="hidden"  onchange = "publishService.vm.showDeleteTip(this, 1)">
                                    <img @click = "clickPic(2)" class="cursor-pointer serve-image-show-2 none" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-2 none">
                                    <input class="serve-image-hidden-2" type="hidden" onchange = "publishService.vm.showDeleteTip(this, 2)">
                                    <img @click = "clickPic(3)" class="cursor-pointer serve-image-show-3 none" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-3 none">
                                    <input class="serve-image-hidden-3" type="hidden" onchange = "publishService.vm.showDeleteTip(this, 3)">
                                    <img @click = "clickPic(4)" class="cursor-pointer serve-image-show-4 none" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-4 none">
                                    <input class="serve-image-hidden-4" type="hidden" onchange = "publishService.vm.showDeleteTip(this, 4)">
                                </template>
                                <template v-else>
                                    <img v-if = "project.media.pictureOne != null" @click = "clickPic(1)" class="cursor-pointer serve-image-show-1"
                                         :src="project.media.pictureOne" alt="">
                                    <img v-else @click = "clickPic(1)" class="cursor-pointer serve-image-show-1"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-1 none" >
                                    <input class="serve-image-hidden-1" type="hidden" :value="project.media.pictureOne" onchange = "publishService.vm.showDeleteTip(this, 1)">

                                    <img v-if = "project.media.pictureTwo != null" @click = "clickPic(2)" class="cursor-pointer serve-image-show-2 none"
                                         :src="project.media.pictureTwo" alt="">
                                    <img v-else @click = "clickPic(2)" class="cursor-pointer serve-image-show-2 none"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-2 none">
                                    <input class="serve-image-hidden-2" type="hidden" :value="project.media.pictureTwo" onchange = "publishService.vm.showDeleteTip(this, 2)">

                                    <img v-if = "project.media.pictureThree != null" @click = "clickPic(3)" class="cursor-pointer serve-image-show-3 none"
                                         :src="project.media.pictureThree" alt="">
                                    <img v-else @click = "clickPic(3)" class="cursor-pointer serve-image-show-3 none"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-3 none">
                                    <input class="serve-image-hidden-3" type="hidden" :value="project.media.pictureThree" onchange = "publishService.vm.showDeleteTip(this, 3)">

                                    <img v-if = "project.media.pictureFour != null" @click = "clickPic(4)" class="cursor-pointer serve-image-show-4 none"
                                         :src="project.media.pictureFour" alt="">
                                    <img v-else @click = "clickPic(4)" class="cursor-pointer serve-image-show-4 none"
                                         src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                    <input type="file" name = "path" class="path-4 none">
                                    <input class="serve-image-hidden-4" type="hidden" :value="project.media.pictureFour" onchange = "publishService.vm.showDeleteTip(this, 4)">
                                </template>

                            </div>
                            <ul class="fl sm-btn">
                                <template v-if = "!isModify">
                                    <li class="on">
                                        <img class="serve-image-show-1" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer none" @click = "deleteImage($event, 1)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                        style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img class="serve-image-show-2" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer none" @click = "deleteImage($event, 2)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img class="serve-image-show-3" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer none" @click = "deleteImage($event, 3)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img class="serve-image-show-4" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer none" @click = "deleteImage($event, 4)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                </template>
                                <template v-else>
                                    <li class="on">
                                        <img v-if = "project.media.pictureOne != null" class="serve-image-show-1" :src="project.media.pictureOne" alt="">
                                        <img v-else class="serve-image-show-1" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer" :class = "project.media.pictureOne == null ? 'none' : 'block'"  @click = "deleteImage($event, 1)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img v-if = "project.media.pictureTwo != null" class="serve-image-show-2" :src="project.media.pictureTwo" alt="">
                                        <img v-else class="serve-image-show-2" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer " :class = "project.media.pictureTwo == null ? 'none' : 'block'" @click = "deleteImage($event, 2)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img v-if = "project.media.pictureThree != null" class="serve-image-show-3" :src="project.media.pictureThree" alt="">
                                        <img v-else class="serve-image-show-3" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer " :class = "project.media.pictureThree == null ? 'none' : 'block'" @click = "deleteImage($event, 3)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                    <li>
                                        <img v-if = "project.media.pictureFour != null" class="serve-image-show-4" :src="project.media.pictureFour" alt="">
                                        <img v-else class="serve-image-show-4" src="/static/platform/v2.2.0/images/register/businessPic.png" alt="">
                                        <img class="cursor-pointer " :class = "project.media.pictureFour == null ? 'none' : 'block'" @click = "deleteImage($event, 4)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt=""
                                             style="margin-left: 70px; margin-top: -43px; width: 15px; height: 15px;">
                                    </li>
                                </template>
                            </ul>
                        </section>
                        <%--<p class="clearfix">
                            <span class="fl">-删除图片</span>
                            <span class="fr">+编辑图片</span>
                        </p>--%>
                    </div>
                </section>
                <section class="class-description">
                    <div class="explain">服务属性</div>
                    <p style="font-size: 16px; color: #333; margin-bottom: 30px;">购买须知</p>
                    <ul class="service-pro" v-cloak="true">
                        <li class="clearfix">
                            <p class="fl note">温馨提示：</p>
                            <dl class="fl">
                                <dd>如发现有问题，请您在消费时间向商户咨询;</dd>
                                <dd>为了保障您的权益，建议使用线上支付。若使用其他的支付方式导致纠纷，</dd>
                                <dd>本商家不承担任何责任，感谢您的理解和支持</dd>
                                <dd style="color: red;">下单之后可以快速联系服务师进行上门时间安排</dd>
                                <dd style="color: red;">7天之内可退款</dd>
                            </dl>
                        </li>
                        <li class="clearfix">
                            <p class="fl"><em>*</em>适用人群：</p>
                            <input  v-if = "!isModify" type="text" class="fl user-type" placeholder="半自理">
                            <input v-else type="text" class="fl user-type" placeholder="半自理1" :value = 'project.userType'>
                        </li>
                        <li class="clearfix">
                            <p class="fl"><em>*</em>预约信息：</p>
                            <input v-if = "!isModify" type="text" class="fl appointment" placeholder="请您提前30分钟预约">
                            <input v-else type="text" class="fl appointment" placeholder="请您提前30分钟预约1" :value = 'project.appointment'>
                        </li>
                        <li class="clearfix" v-cloak>
                            <p class="fl"><em>*</em>服务名称：</p>
                            <input v-if = "!isModify" type="text" class="fl service-name" placeholder="上门注射">
                            <input v-else type="text" class="fl service-name" placeholder="上门注射1" :value = 'project.name | subStr(1)'>
                        </li>
                        <li class="meal clearfix">
                            <p class="fl">服务收费：</p>
                            <div class="fl clearfix" v-cloak>
                                <span v-cloak class="meal-1" v-for = "(c, index) in combos" @click = "editCombo(index)">
                                    {{c.name}}
                                    <i onclick = "publishService.vm.deleteCombo(event, this)"></i>
                                </span>
                                <span class="fr meal-2" @click = "popupCombo">+添加套餐</span>
                            </div>
                        </li>
                    </ul>
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
                                    <div>
                                        <img :src = "s.photo" alt="">
                                    </div>
                                    <p>{{s.realName}}</p>
                                    <%--<p v-if = "currentServe!= null && currentServe.name == '咨询服务'">
                                        图文咨询
                                        <input class="org-user-price" :id="'org-user-price-' + s.id" type="text" value="0">
                                        元/次
                                    </p>--%>
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
                </section>
                <section class="edit-service">
                    <div class="explain">服务详细描述</div>
                    <div class="clearfix">
                        <p class="fl">详细介绍：</p>
                        <div class="edit-text fl">
                            <script id="editor" type="text/plain" name="editor"></script>
                        </div>
                    </div>
                </section>
                <div class="items-btn" style="margin-top: 130px">
                    <template v-if = "!isModify">
                        <span class="cursor-pointer" @click = "saveService(true)">发布项目</span>
                        <span class="cursor-pointer" @click = "saveService(false)">预览/保存</span>
                    </template>
                    <template v-else>
                        <span class="cursor-pointer" @click = "modifyService(project.status)">保存修改</span>
                    </template>
                </div>
            </div>
        </div>
        <%--添加服务师窗口--%>
        <div class="data-export org-user-list none">
            <ul>
                <li>
                    <input type="text" placeholder="搜索服务师" v-model = "searchOfOrgUser">
                </li>
                <li>
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
        <%--添加套餐窗口--%>
        <div class="set-meal combo-dialog none">
            <div class="tit">
                <h3 v-if = "combo.introduce != undefined">{{combo.name}}</h3>
                <h3 v-else>新增套餐</h3>
            </div>
            <p class="cue">
                <span>套餐项目：</span>
                <%--<a>编辑</a>--%>
            </p>
            <table>
                <tbody>
                <tr>
                    <td>项目名称</td>
                    <td>单位（次）</td>
                    <td>价格</td>
                    <%--<td>门市价</td>--%>
                    <td></td>
                </tr>
                <tr v-for = "(c, index) in combo.introduce"  >
                    <td>
                        <input class = "name" type="text" :value = "c.name">
                    </td>
                    <td>
                    <span>
                        <input v-if = "isModify" class = "unit" type="text" :value = "c.unit | subStr(2)">
                        <input v-else class = "unit" type="text" :value = "c.unit">
                    </span>
                    </td>
                    <td>
                        <input class = "price" type="text" :value = "c.price"
                               onkeyup="publishService.vm.filterComboPrice(this)">
                    </td>
                    <%--<td>
                        <input class = "marketPrice" type="text" :value = "c.marketPrice">
                    </td>--%>
                    <td>
                        <img class="cursor-pointer" @click = "updateCombo('delete', $event)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt="">
                    </td>
                </tr>

                </tbody>

            </table>
            <div class="add-meal cursor-pointer"><span @click = "updateCombo('add')">+添加项目</span></div>
            <div class="total-price">
                <span>总价格：</span>
                <input type="text" class="total-combo-price" placeholder="请输入总价格"
                       v-model = "comboPrice" onkeyup="publishService.vm.filterComboPrice(this)"></div>
            <div class="confirm-meal cursor-pointer">
                <span @click = "addCombo('add')">保存</span>
            </div>
        </div>
    </section>



</article>
</body>
</html>
<script src="/static/plugins/uedit/ueditor.config.js"></script>
<script src="/static/plugins/uedit/editor_api.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script src="/static/platform/v2.2.0/js/org/servicemanage/publish-service.js?v=2.2.0"></script>
<script>
</script>
<script>
    $('.main-nav').find('li').eq(5).click();

    <%--publishService.vm.project = '${project}' == '' ? {} : JSON.parse('${project}'.replace(/\\/g, '/'));--%>
    try {
        publishService.vm.project = ${project};
    } catch (e){

    }
    if (!$.isEmptyObject(publishService.vm.project)) {
        publishService.vm.project.serviceDescription = '${serviceDescription}';
        publishService.vm.project.combo = '${combo}' == '' ? null : JSON.parse('${combo}'.replace(/"\[/g, '[').replace(/\]"/g, ']'));
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
    publishService.initVisitService();



    if ('${orgType}' == 1) {
        $('.main-nav li').eq(5).addClass('menu-current');
    } else {
        $('.main-nav li').eq(3).addClass('menu-current');
    }
</script>
