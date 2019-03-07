<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="orgCheckContriller" ng-init='init()'>
    <div class="right_title">
        <div class="titleShow">
            <label class="action">
                门店审核
            </label>
            <select class="service_select" ng-model="param.orgOption" ng-init="param.orgOption='-1'">
                <option value="-1">所有门店</option>
                <option value="0">待审核</option>
                <option value="1">审核通过</option>
                <option value="2">审核未通过</option>
            </select>
            <button class="search-btn" style="float: left;left: 460px;" ng-click="search()">
                搜索
            </button>
        </div>

    </div>
    <div style="margin-top:20px">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>门店</td>
                <td>所属地区</td>
                <td>注册时间
                    <button class="time-sort" ng-click="sort()"></button>
                </td>
                <td>联系人</td>
                <td>绑定手机号</td>
                <td>操作</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="7">暂时没有待审核的门店</td>
            </tr>

            <tr ng-repeat="r in results" data-id="{{r.id}}" class="{{r.id+'1'}}">
                <td>{{r.orgName}}</td>
                <td>{{r.area}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <td>{{r.contacts}}</td>
                <td>{{r.contactInformation}}</td>
                <%--<td id="{{r.id}}" ng-if="r.orgVerified==0"><a ng-click="openVerifyDialog(r.id)">待审</a>&nbsp;&nbsp;<a
                        ng-click="removeVerifyOrg(r.id)" style="color:#ff2f05">删除</a></td>--%>
                <td id="{{r.id}}" ng-if="r.orgVerified==0"><a ng-click="openVerifyDialog(r.id)">待审</a></td>
                <td id="{{r.id}}" ng-if="r.orgVerified==1"><span style="color: #48c858;">通过</span>&nbsp;&nbsp;<a
                        ng-click="removeVerifyOrg(r.id)">删除</a></td>
                <td id="{{r.id}}" ng-if="r.orgVerified==2"><span style="color: #ff0000;">未通过</span>&nbsp;&nbsp;<a
                        ng-click="removeVerifyOrg(r.id)">删除</a></td>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>
    <div class="dialog-content" style="display: none;">
        <img class="org-head" src="{{orgVeriData.logo}}"/>
        <div class="param-item">
            <label>门店名称：</label><span>{{orgVeriData.orgName}}</span>
        </div>
        <div class="param-item">
            <label>注册时间：</label><span>{{orgVeriData.createDate | date:"yyyy-MM-dd"}}</span>
        </div>
        <div class="param-item">
            <label>法人代表：</label><span>{{orgVeriData.contacts}}</span>
        </div>
        <div class="param-item">
            <label>联系方式：</label><span>{{orgVeriData.contactInformation}}</span>
        </div>
        <div class="param-item">
            <label>公司地址：</label><span>{{orgVeriData.area}}</span>
        </div>
        <div class="param-item">
            <label style="float: left;">营业执照：</label>
            <img onclick="showPhoto(this)" src="{{orgVeriData.businessLicense}}" style="width:120px;height: 120px;cursor: pointer">
        </div>
        <div class="param-item" style="margin-top: 8px">
            <label>审核结果：</label>
            <input type="radio" ng-model="param.orgVerified" ng-init="param.orgVerified='1'" value="1">通过&nbsp;&nbsp;
            <input type="radio"
                   ng-model="param.orgVerified"
                   value="2"
                   style="margin-left: 15px"/>不通过
        </div>
        <div class="param-item" style="margin-top: 8px">
            <label style="float: left;">审核意见：</label><textarea ng-model="param.reason"
                                                               style="resize:none;padding:8px;width:350px;border:1px solid #ccc;font-family:Microsoft YaHei"
                                                               rows="3"></textarea>
        </div>
        <div class="commit-btn" style=" padding-left: 80px">
            <button class="save" ng-click="commitVerifyResult(orgVeriData.id)">提交</button>
            <button class="back" onclick="layer.closeAll()">取消</button>
        </div>
    </div>
</div>
<script>
    function showPhoto(node) {
        var src = $.baseUrl + "/" + $(node).attr("src");
        layer.photos({
            photos: {
                "status": 1,    //请求的状态，1表示成功，其它表示失败
                "msg": "",  //状态提示语
                "title": "",    //相册标题
                "id": 0,    //相册id
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "name": "测试", //图片名
                        "pid": 0, //图片id
                        "src": src, //原图地址
                        "thumb": "", //缩略图地址
                        "area": [638, 851] //原图宽高
                    }
                ]
            }
        })
    }
</script>