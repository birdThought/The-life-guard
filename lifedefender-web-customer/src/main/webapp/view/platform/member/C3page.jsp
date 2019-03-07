<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="right_body" ng-controller="memberC3Controller" ng-init='init()'>
    <div class="titleShow">
        <div class="right_title">
            <label class="action">
                C3设备绑定记录
            </label>
        </div>
        <div style="margin-top:15px;position:relative">
                       <span class="outdate  out-contain">
                            <select class="service_select" ng-model="search.condition" ng-init="search.condition='-1'">
                                <option value="-1">条件</option>
                                <option value="0">用户名</option>
                                <option value="1">姓名</option>
                            </select>
                            <input type="text" placeholder="请输入" ng-model="search.userName"/>
                        </span>
            <span class="outdate">
                            <label>imei</label>
                            <input type="text" placeholder="请输入imei号" ng-model="search.imei"/>
                        </span>
            <span class="outdate" ng-click="findByDate()">
                        <label>绑定时间</label>
                        <input id="endDate" ng-model="search.createDate" type="text" placeholder="绑定时间" readonly>
                    </span>
            <select class="service_select" ng-model="search.status" style="width: 90px" ng-init="search.status='-1'">
                <option value="-1" selected>状态</option>
                <option value="1">在线</option>
                <option value="0" selected>离线</option>
            </select>
            <button class="search-btn" ng-click="searchs()">
                搜索
            </button>
        </div>
    </div>

    <div style="margin-top: 20px">
        <table class="service_table" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户名</td>
                <td>姓名</td>
                <td>imei</td>
                <td>手机号码</td>
                <td>状态</td>
                <td>绑定时间</td>
            </tr>
            <tr ng-if="results==''">
                <td colspan="6">
                    无任何记录
                </td>
            </tr>
            <tr ng-repeat="r in results">
                <td>{{r.userName}}</td>
                <td>{{r.realName}}</td>
                <td>{{r.imei}}</td>
                <td>{{r.mobile}}</td>
                <td>{{r.status | terminalStatus}}</td>
                <td>{{r.createDate | date:"yyyy-MM-dd"}}</td>
            </tr>
        </table>
    </div>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
</div>
<script>
    jeDate({
        dateCell: "#endDate",
        format: "YYYY-MM-DD",
        minDate: "1900-01-01",
        maxDate: jeDate.now(0),
        startMin: "1900-01-01",
        startMax: jeDate.now(0),
        zindex: 999,
        choosefun: function (elem, val) {
            //val为获取到的时间值
        }
    });
</script>
