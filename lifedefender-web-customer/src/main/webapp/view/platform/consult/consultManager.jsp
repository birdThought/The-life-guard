<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="/static/css/consultManger/ConsultMangerSetting.css">
<style>
label.error{color:#e80000}
.small-parent-menu {
    width: 200px;
    min-height: 600px;
    background-color: #f1f1f1;
    padding: 14px 0 10px 0;
    float:left;
}
.small-parent-menu li p {
    text-align: left;
    line-height: 40px;
    cursor: pointer;
    font-size: 16px;
}
.small-parent-menu li p img {
    margin-right:8px;
    cursor: pointer;
}
.small-child-menu {
    padding-left: 19%;
    line-height: 30px;
    font-size: 15px;
}
.small-child-menu li {
    cursor: pointer;
}
.orderCenterBottom {
    width: 1272px;
    float: left;
    margin-left:2%;
}
.orderCenterBottom table {
    width: 100%;
}
.orderCenterBottom table thead th {
    border:1px solid #eceeef;
    background:#eceeef;
    height: 30px;
    padding:10px 0;
    font-size: 14px;
}
.small-parent-menu li:nth-of-type(1) ul.small-child-menu {
	display:block
}
</style>

<div class="" ng-init="init()">
    <div class="Main-content clearfix">
        <div>
            <shiro:hasPermission name="news:add"><button style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;border-radius: 4px" class="search search-btn cursor-pointer" ng-click = "addNew()">添加资讯</button></shiro:hasPermission>
        </div>
        <div>
            <ul name="column" class="small-parent-menu" ng-click="iconToggle($event)">
                <li  ng-repeat="column in columnList" data-id="{{column.id}}" >
                    <p ng-click="getInformationList(column.id, true)"><img src="static/images/open.png" alt="" style="margin-right:8px;cursor: pointer">{{column.name}}</p>
                    <ul  ng-repeat="(index,item) in column.itemList"   class="small-child-menu" style="display: none;">
                        <li data-id="{{item.id}}" ng-click="getSecondInformationList(item.id)">{{item.name}}</li>
                    </ul>
                </li>
            </ul>
        </div>

        <div class="orderCenterBottom" >
            <table border="0" cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th>资讯标题</th>
                    <th>来源</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody align="center">
                <tr ng-repeat="info in informationList" ng-cloak>
                    <td>{{info.title}}</td>
                    <td>{{info.source}}</td>
                    <td>{{info.createDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{info.modifyDate | date:'yyyy-MM-dd'}}</td>
                    <td>
                        <ul style="width:100%" >
                            <li style="cursor:pointer;float:left;width:33.3%;color: deepskyblue;" class="layui-icon" ng-click="goContent(info.id)">&#xe614;</li>
                            <shiro:hasPermission name="news:edit"><li style="cursor:pointer;float:left;width:33.3%;color: deepskyblue;" class="layui-icon" ng-click="editInformation(info.id)">&#xe642;</li></shiro:hasPermission>
                            <shiro:hasPermission name="news:del"><li style="cursor:pointer;float:left;width:33.3%;color: deepskyblue;" class="layui-icon" ng-click="delPopout(info.id,info.columnId)">&#xe640;</li></shiro:hasPermission>
                        </ul>
                    </td>
                </tr>
                </tbody>
            </table>
            <div id="page" style="text-align: center; margin-top: 20px"></div>
        </div>

        <!-- 编辑资讯 -->
        <div class="dialog-content" style="padding-left:30px;display: none;width: auto;min-height: 250px">
            <style>.param_set { margin-bottom: 8px }</style>
            <form id="consultForm" method="post" style="padding-top: 0;padding-left: 10px">
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>资讯标题：</label><input id="title"
                                                                                        name="title" type="text" placeholder="请输入资讯标题" style="width:300px" ng-model="informationObj.title"/>
                </div>
                <div class="param_set">
                    <label class="param"><span class="warn">*</span>主栏目：</label>
                    <select id="parentColumn" name="parent" class="select-btn" style="width:auto;" class="serveSelect" ng-model="informationObj.parentColumnId" ng-options="column.id as column.name for column in columnList">
                        <option value=""> -- 请选择 -- </option>
                    </select>
                </div>
                <div class="param_set" >
                    <label class="param"><span class="warn">*</span>子栏目：</label>
                    <select id="childColumn" name="child" class="select-btn" style="width:auto;" ng-options="column.id as column.name for column in childColumns" ng-model="informationObj.columnId">
                        <option value=""> -- 请选择 -- </option>
                    </select>
                </div>
                <div class="param_set">
                    <label class="param">发布来源：</label><input id="source"
                                                             type="text" placeholder="请输入发布来源" name="source" style="width:300px" ng-model="informationObj.source"/>
                </div>
                <div class="param_set">
                    <label class="param" style="float:left;">资讯内容：</label>
                    <div style="width:650px;display: inline-block;">
                        <script type="text/plain" id="server_details" name="content"></script>
                    </div>
                </div>
                <div style="padding:10px 110px 20px;clear: both;">
                    <input class="save" value="保存" type="button" ng-click="saveOrEdit()"/>
                    <input type="hidden" ng-model="informationObj.id">
                </div>
            </form>
        </div>

        <div id="deletenews" style="display: none">
            <p style="position: relative;
    padding: 20px;
    line-height: 24px;
    word-break: break-all;
    font-size: 14px;
    overflow: auto;
    text-align: center">确定要删除这条信息吗？</p>
            <p style="margin: 0 auto;width:56% ;">
                <button id="confrimdelete" class=" layui-btn layui-btn-danger" ng-click="delInformation()">确认</button>
                <button   class="layui-btn layui-btn-warm" ng-click="closeAll()">取消</button>
            </p>
        </div>
    </div>

</div>

<script>
$('.small-parent-menu > li:nth-of-type(1) > ul.small-child-menu ').css({'display':'block'})
</script>