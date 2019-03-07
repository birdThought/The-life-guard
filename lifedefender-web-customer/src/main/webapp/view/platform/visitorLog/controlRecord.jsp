<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="right_body" ng-controller="operaController" ng-init="init()">
    <div class="right_title">
        <label class="action">
            操作记录
        </label>
    </div>
    <style>
        td {
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
    </style>
    <div style="margin-top:20px">
        <div style="margin-bottom:15px;position:relative">
            <span class="outdate">
                            <label>姓名</label>
                            <input type="text" placeholder="请输入姓名" ng-model="realName"/>
                        </span>
            <span class="outdate">
                        <label>操作时间</label>
                        <input id="promiseDate" type="text" placeholder="操作时间">
                    </span>
            <select class="service_select" ng-model="type" ng-init="type='-1'" style="width: auto">
                <option value="-1" selected>--操作类型--</option>
                <option value="0">添加</option>
                <option value="1">修改</option>
                <option value="2">删除</option>
            </select>
            <button class="search-btn" ng-click="search()">
                搜索
            </button>
        </div>
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>IP</td>
                <td>角色</td>
                <td>姓名</td>
                <td>联系方式</td>
                <td>数据库表名</td>
                <td>参数</td>
                <td width="10%">操作类型</td>
                <td>时间</td>
                <td width="10%">参数详情</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="9">暂时无任何记录</td>
            </tr>
            <tr ng-repeat='r in results'>
                <td>{{r.host}}</td>
                <td>{{r.adminId | adminType}}</td>
                <td>{{r.realName}}</td>
                <td>{{r.mobile}}</td>
                <td>{{r.tableName}}</td>
                <td>{{r.paramData}}</td>
                <td>{{r.type | dbType}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
                <td><img style="cursor: pointer" ng-click="lookDetails(r.paramData)" src="/static/images/chectDetails.png" alt=""></td>
            </tr>
        </table>
        <div class="content-right-bottom">
            <div id="page" style="text-align: center; margin-top: 30px"></div>
        </div>
    </div>
</div>
<script>
    layui.use('laydate',function(){
        var laydate = layui.laydate;
        laydate.render({
            elem:'#promiseDate',
            done:function(val,date){
                $scope.datas = val;
            }
        })
    })
</script>
