<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-container" ng-controller="codePackageController" ng-init="init()">
    <!-- 标题 -->
    <div class="layui-row title">
        <div class="layui-col-md1">
            <label class="title">推荐套餐列表</label>
        </div>
    </div>
    <!-- 添加按钮 -->
    <div class="layui-row">
        <div class="layui-col-md1 layui-col-md-offset11 top">
            <button class="layui-btn layui-btn-normal" ng-click="openAddDialog(userInfo.type)" >添加推荐</button>
        </div>
    </div>
    <!-- 表单 -->
    <div class="layui-row">
        <div class="layui-col-md12">
            <table id="table" class="layui-table" cellspacing="0" cellpadding="0" lay-size="lg" lay-even>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>推荐二维码</th>
                        <th>推荐人名称</th>
                        <th>套餐名称</th>
                        <th>生成日期</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                   <%-- <tr ng-if="data.packageList.length == 0">
                        <td colspan="8" style="text-align: center;">无相关记录</td>
                    </tr>--%>
                    <tr ng-repeat="(index,p) in datalist">
                        <td>{{index + 1}}</td>
                        <td><a class="red" ng-click="showCode(p.name,p.bsId,p.ageId,p.superior)">查看</a></td>
                        <td>{{p.orgName}}</td>
                        <td>{{p.name}}</td>
                        <td>{{p.createDate | date:'yyyy-MM-dd'}}</td>
                        <td>
                            <a ng-click="update()"><i class="layui-icon" style="font-size: 25px;">&#xe63c;</i></a>
                            <a class="delete" ng-click="delData(p.id)"><i class="layui-icon" style="font-size: 25px;">&#xe640;</i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="content-right-bottom">
        <div id="page" style="text-align: center; margin-top: 30px"></div>
    </div>
    <div name="dialog">
    <!-- 二维码 -->
    <div id="qrCodeContent" class="layui-container" style="display:none;width: auto !important;">
        <div class="layui-row">
            <div class="layui-col-md8 layui-col-md-offset2">
                <div id="qrcode"></div>
            </div>
        </div>
    </div>
    <!-- 套餐 -->

        <div id="addPackageDialog" style="display:none;padding:20px;min-height:665px;">
             <label class="param">选择推荐套餐:</label>
            <select style="width: 320px;height: 28px;border: 1px solid #ccc;text-align: center;line-height: 36px;" name="" ng-model="submit.ageId" ng-init="submit.ageId=''">
                <<option value="">下拉选择</option>
                <option  ng-repeat="p in package" value="{{p.id}}">{{p.name}} （{{p.price | fenToYuan}}）</option>
            </select>

        <div class="layui-form layui-form-pane" >
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" ng-click="addPackage()">立即提交</button>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>


