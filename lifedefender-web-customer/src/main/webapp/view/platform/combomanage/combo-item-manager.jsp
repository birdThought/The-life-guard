<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
 <style>
 	#orderCenterPopup {padding-left:72px}#orderCenterPopup input {border:none;border-bottom:1px solid #cee;width:346px}
 	#orderCenterPopup textarea {width:350px;border:none; border-bottom:1px solid #cde;height:80px;vertical-align:top}
 	#orderCenterPopup section:nth-of-type(2){margin-left:0}#orderCenterPopup span {width:74px;display:inline-block;text-align:right;}
 	.finsh{margin-top:34px;}
 </style>
<div  class="orderCenter" ng-controller="comboItemManageController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow" style="padding-bottom: 20px;">
        <shiro:hasPermission name="combo_item:add"><a style="padding: 4px 10px;background:#3cbaff;text-align: center;color: #fff;" ng-click="popupEditDialog('','add')">
    	新增</a></div></shiro:hasPermission>
    <div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td style="width:35px;">序号</td>
                <td style="width:130px;">名称</td>
                <td style="width:100px;">创建时间</td>
                <td style="width:350px;">描述</td>
                <td style="width:70px;">套餐图标</td>
                <td style="width:250px;">详细说明</td>
                <td style="width:35px;">编辑</td>
                <td style="width:35px;">删除</td>
            </tr>
            </thead>
           
             <tbody>
                <tr ng-repeat="o in comboItemList">
                    <td>{{o.id}}</td>
                    <td>{{o.name}}</td>
                    <td>{{o.createDate | date:'yyyy-MM-dd'}}</td>
                    <td>{{o.remark}}</td>
                    <td><img src="{{o.icon}}" /></td>
                    <td>{{o.itemDetail}}</td>
               		<td><span class="ny" ng-click="popupEditDialog(o,'edit')"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></td>
                    <td><span class="ny" ng-click="DeleteDialog(o)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
    <div id="deleteContent" style="display: none;text-align: center;">
        <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
        <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
            ng-click="deleteItemCombo()">确认</button>
    </div>
    <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section style="margin-top: 15px;" ng>
            <span class="#999">
                名称：
            </span>
            <input class="doctor-sign" ng-model="edit.name">
        </section>
        
        <section class="finsh">
            <span>
                备注：
            </span>
            <textarea id="Textarea" cols="30" rows="10" placeholder="请输入备注" ng-model="edit.remark"></textarea>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
                图标：
            </span>
            <div class="layui-upload" style="display: inline-block;vertical-align: top">
              <button type="button" style="width: 60px;height: 60px;display: inline-block;border: 2px dashed #ccc;background: #fff;border-radius: 6px;" class="layui-btn" id="uploadEditImg">
                  <img id="upload_editImg" src="{{edit.icon}}" alt="">
              </button>
              <input type="hidden" ng-model="edit.icon" id="divEditPhoto" name="divEditPhoto" />
            </div>
        </section>
        
        <section style="margin-top: 15px;">
            <span class="#999">
                详情：
            </span>
            <input class="doctor-sign" placeholder="请输入机构地点" ng-model="edit.itemDetail">
        </section>
        

        <section style="text-align: center; margin-top: 15px;">
        <input type="hidden" ng-model="edit.type" />
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="button_blue_2"
                   value="提交数据" ng-click="replyOrder()">
        </section>
    </div>
</div>



<script type="text/javascript">
        
    layui.use('upload',function(){
        var $ = layui.jquery,
        upload=layui.upload;
        var uploadInst = upload.render({
            elem:'#uploadEditImg',
            url:'/combo/item/uploadFile/img',
            before:function(){
                layer.load(2)
            },
            done:function(res){
                layer.closeAll("loading");
                if (res.success == true) {
                    $('#upload_editImg').attr('src',res.obj);
                     $('#divEditPhoto').val(res.obj);
                    $("input[name='divEditPhoto']")[0].dispatchEvent(new Event('input'));
                }else{
                    layer.msg(res.msg);
                }
            }
        })
    })
</script>


