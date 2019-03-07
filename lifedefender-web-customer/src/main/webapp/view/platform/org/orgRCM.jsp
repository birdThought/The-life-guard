<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<div class="right_body" ng-controller="orgRcmController" ng-init='init()'>
    <div class="right_title">
        <div class="titleShow">
            <label class="action">
                推荐门店
            </label>
            <button class="search-btn" style="display: none;float: left;">
                搜索
            </button>
            <input style="display: none" type="text" name="" class="search-content" placeholder="输入服务机构名称">
        </div>
    </div>

    <ul>
        <li ng-if="results==''" style="font-size: 25px;text-align: center;padding: 30px;">暂无推荐门店</li>
        <li ng-repeat="r in results" style="display: inline-block;border-style: groove;padding: 10px;
    margin: 10px;">
            <a href="">
                <div  style="display: inline-block;">
                    <img src="{{r.logo}}" width="160" height="120">
                    <div class="result-message-contain">
                        <h3>{{r.orgName}}</h3>
                        <p>主要服务：{{r.mainServes}}</p>
                        <p>地址：{{r.area}}</p>
                    </div>
                </div>
            </a>
        </li>
    </ul>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
</div>