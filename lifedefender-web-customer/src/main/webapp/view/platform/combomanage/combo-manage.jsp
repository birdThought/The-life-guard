<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css?v=1.0.0">
<style>
.layui-layer-content {
	height:auto!important;
	padding-bottom:34px;
}
	#orderCenterPopup section span {
		display:inline-block;
		width:118px;
		text-align:right;
	}
	.layui-form-label {
        border: none !important;
    }
    #addPopup input {
        border-radius: 6px;
    }

</style>
 <div class="orderCenter" ng-controller="comboManageController" ng-init="init()" style="padding: 34px 0 0 20px;">
    <div class="titleShow">
        <shiro:hasPermission name="combo:add"><div style="width: 100px;background: #3cbaff;color: #fff;border: none;padding: 4px 14px;text-align: center" ng-click="addDialog()">
            添加套餐
        </div></shiro:hasPermission>
    </div>

 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td style="width:40px">序号</td>
                <td style="width: 180px;">套餐名称</td>
                <td style="width: 420px;">描述</td>
                <td style="width:70px;">原价</td>
                <td style="width:70px;">实际价格</td>
                <td style="width:150px;">图片</td>
                <td style="width:60px;">有效期</td>
                <td style="width: 360px">详细描述</td>
                <td style="width:80px;">类型</td>
                <td style="width:40px">编辑</td>
                 <td style="width:40px">删除</td> 
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="c in combo">
                	<td>{{$index+1}}</td>
                    <td>{{c.name}}</td>
                    <td>{{c.description}}</td>
                    <td>{{c.originalPrice | fenToYuan}}</td>
                    <td>{{c.price | fenToYuan}}</td>
                    <td><img src="{{c.photo}}" /></td>
                    <td>{{c.validDay}}天</td>
                    <td>{{c.detail}}</td>
  					<!-- <td class="td-special">{{c.type|Combotype}}</td>  -->
                    <td class="td-special">{{c.l1}}</td>
  					<td><shiro:hasPermission name="combo:edit"><span class="ny" ng-click="popupEditDialog(c)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span></shiro:hasPermission></td>
  					<td><shiro:hasPermission name="combo:del"><span class="ny" ng-click="DeleteDialog(c)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></shiro:hasPermission></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
     <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deleteCombo()"
            >确认</button>
        </div>
        
     <!-- 编辑套餐 -->
     <div id="orderCenterPopup" class="orderPopup ">
        <section class="clearfix">

        </section>
        <section style="margin-top: 15px;">
            <span class="#999" style="width:85px">
                套餐名称：
            </span>
            <input id=" " class="doctor-sign" placeholder="修改名称" ng-model="edit.name" style="width: 150px">
            
            <span class="#999" style="width:57px">
                原价：
            </span>
            <input id=" " class="doctor-sign" placeholder="修改原价" ng-model="edit.originalPrice" style="width: 65px">
            
            <span class="#999" style="width:87px">
                实际价格：
            </span>
            <input id='' class="doctor-sign" placeholder="修改实际价格" ng-model="edit.price" style="width: 65px">
            
        </section>
        
         <section style="margin-top: 15px;">
            <span class="#999">
                描述：
            </span>
            <input id=" " class="doctor-sign" placeholder="修改描述" ng-model="edit.description">
        </section>
        
         <section style="margin-top: 15px;">
            <span class="#999">
                文章编号：
            </span>
            <input id='' class="doctor-sign" ng-model="edit.detail" style="width: 65px">
            
            <span class="#999" style="width:65px;">
                类型：
            </span>
           <select class="doctor-sign" id="combo_type" name="select" ng-model="edit.l1" id="select_k1" class="xla_k" style="width:150px" > 
    			<option>{{edit.l1}}</option> 
    			<option value="未选择">未选择</option> 
                <option value="中医养生">中医养生</option>
                <option value="健康管理">健康管理</option> 
                <option value="减肥塑体">减肥塑体</option> 
    			<option value="妇婴照护">妇婴照护</option> 
				<option value="居家护理">居家护理</option> 
                <option value="慢病康复">慢病康复</option> 
				<option value="术后康复">术后康复</option> 
 				<option value="美容美体">美容美体</option>  
                <option value="肿瘤康复">肿瘤康复</option>  
   		 </select>
        <span class="#999" style="width: 65px;">
                         有效期：
        </span>
            <input id='' class="doctor-sign" placeholder="修改有效期" ng-model="edit.validDay" style="width: 60px;">
            <span style="width:30px;height:38px;line-height:38px; text-align: left">天</span>
            
        </section>
        <section style="margin-top: 15px;">
            <span class="#999">
                图片：
            </span>
           <div class="layui-upload" style="display: inline-block;vertical-align: top">
              <button type="button" style="width: 372px;height: 150px;display: inline-block;border: 2px dashed #ccc;background: #fff;border-radius: 6px;" class="layui-btn" id="uploadEditImg">
                  <img id="upload_editImg" src="{{edit.photo}}" alt="">
              </button>
              <input type="hidden" ng-model="edit.photo" id="divEditPhoto" name="divEditPhoto" />
            </div>
        </section>
        <div class="setMealContent" style="position: relative;height: 150px;margin-top: 22px;margin-left: 36px;">
            <span class="">选择套餐内容: </span>
            <div ng-click="comboItemList()" class="" style="text-align:center;width: 220px;line-height:40px;height: 40px;border-radius: 6px;border: 1px solid #ddd;display: inline-block;vertical-align: middle;cursor: pointer">
                <img style="width:16px;height: 16px;vertical-align: middle;" src="/static/images/add-img2.png">
            </div>
            <button type='button' id="addCheckMeal" ng-click="addCheckMeal()" >确认添加</button>
            <div id="checkedMeal" style="width:218px;overflow: scroll;position: absolute;left: 94px;display: none">
                <ul style="height: 120px">
                    <li name="setMeal" ng-repeat="c in comboItemInfo">
                    <input class="cId" ng-value="c.id" type="hidden" />
                    <input class="cName" style="display: inline-block;line-height: 36px" type="checkbox" name="checkbox"  ng-value="c.name">{{c.name}}</li>
                </ul> 
            </div>
            <div  style="border:1px solid #006600;OVERFLOW-Y: auto;margin-left:55%; width:210px; height: 140px;" id="setMealdetails">
                
            </div>
       </div>
       
        <!-- <div class="setUpdOrgUserContent1" style="position: relative;height: 80px;margin-top: 40px;margin-left: 36px;">
            <label for="" class="layui-form-label" >服务师: </label>
            <input style="width: 150px;" id="updOrgUserName1" type="text" class="doctor-sign">
            <button type="button" style="width: 60px;" ng-click = "serviceUser1('update')">查询</button>
            <div  style="border:1px solid #006600;OVERFLOW-Y: auto;margin-left:25%; width:210px; height: 85px;" id="updSetOrgUserInfo1">
            </div>
       </div>  -->
       
       <div class="setUpdOrgUserContent" style="position: relative;height: 80px;margin-top: 40px;margin-left: 36px;">
            <label for="" class="layui-form-label" >服务师: </label>
            <input style="width: 150px;" id="updOrgUserName" type="text" class="doctor-sign">
            <button type="button" style="width: 60px;" ng-click = "serviceUser('update')">查询</button>
            <div  style="border:1px solid #006600;OVERFLOW-Y: auto;margin-left:55%; width:210px; height: 85px;" id="updSetOrgUserInfo">
            </div>
       </div>

        <section style="text-align: center; margin-top: 55px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="修改数据" ng-click="editcombo()">
        </section>
       
    </div> 
	<!-- 添加套餐 -->
	
	<div id="addPopup" class="orderPopup" style="display:none;width: 90%;margin: 0 auto;" >
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item" style="margin-bottom: 22px">
                <label for="" class="layui-form-label" >套餐名称: </label>
                <div class="layui-input-inline">
                    <input ng-model="add.name" style="width: 189px;height: 38px;border: 1px solid #ccc;outline: none;color: #0398fc" type="text" id="" lay-verify="required" value="慢病管理" autocomplete="off" class="layui-input">
                </div>
                 <label for="" class="layui-form-label" >套餐原价: </label>
                <div class="layui-input-inline" style="width: 110px">
                    <input ng-model="add.originalPrice" style="width: 110px;height: 38px;border: 1px solid #ccc;outline: none;color: #0398fc" type="text" id="" lay-verify="required" value="8000" autocomplete="off" class="layui-input">
                </div>
                 <label for="" class="layui-form-label" >实价: </label>
                <div class="layui-input-inline" style="width: 110px">
                    <input ng-model="add.price" style="width: 110px;height: 38px;border: 1px solid #ccc;outline: none;color: #0398fc" type="text" id="" lay-verify="required" value="500" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="">
              <label class="layui-form-label">套餐简介: </label>
              <div class="layui-input-block" style="margin-right: 94px;margin-bottom: 26px">
                <input ng-model="add.description" style="height: 38px;border: 1px solid #ccc;outline: none;color: #0398fc" type="text" autocomplete="off" value="慢病监看管理(三个月)" class="layui-input">
            </div>
            
            
             <div class="layui-form-item" style="margin-bottom: 22px">
              <label class="layui-form-label">文章编号: </label>
              <div class="layui-input-inline">
                <input ng-model="add.detail" style="height: 38px;width:130px; border: 1px solid #ccc;outline: none;color: #0398fc" type="text" lay-verify="required" autocomplete="off" class="layui-input">
              </div>
              
              
              <label class="layui-form-label">类型: </label>
                <div class="layui-input-inline" style="width: 110px;">
                <select ng-model="add.l1" style="width: 110px;height: 40px;border: 1px solid #ddd;border-radius: 4px; display:inline-block">
        
                      <option value="未选择">未选择</option> 
                      <option value="中医养生">中医养生</option>
                      <option value="健康管理">健康管理</option> 
                      <option value="减肥塑体">减肥塑体</option> 
                      <option value="妇婴照护">妇婴照护</option> 
                      <option value="居家护理">居家护理</option> 
                      <option value="慢病康复">慢病康复</option> 
                      <option value="术后康复">术后康复</option> 
                      <option value="美容美体">美容美体</option>  
                      <option value="肿瘤康复">肿瘤康复</option>  
              </select>
              </div>
              
              <label class="layui-form-label">有效期: </label>
              <div class="layui-input-inline" style="width: 70px;">
                <input ng-model="add.validDay" style="width: 70px;height: 38px;border: 1px solid #ccc;outline: none;color: #0398fc" type="text" lay-verify="required" value="100" autocomplete="off" class="layui-input">
              </div><span style="float: left;width:30px;height:38px;line-height:38px" class="">天</span>
          </div>
              
          <fieldset class="layui-elem-field layui-field-title" style="display: inline-block; margin-top: 0px;">
            <legend style="margin-top: 0">套餐图片:</legend> 
            </fieldset>
            <div class="layui-upload" style="display: inline-block;vertical-align: top">
              <button type="button" style="width: 372px;height: 150px;display: inline-block;border: 2px dashed #ccc;background: #fff;border-radius: 6px;" class="layui-btn" id="uploadImg">
                  <img id="upload_img" src="/static/images/add-img.png" alt="">
              </button>
              <input type="hidden" ng-model="add.photo" id="divPhoto" />
            </div>
             <div class="selectmeal" style="position: relative;height: 150px;margin-top: 22px;margin-left: 36px;">
                <span class="">选择套餐内容: </span>
                <div ng-click="listComboItem()" class="" style="text-align:center;width: 220px;line-height:40px;height: 40px;border-radius: 6px;border: 1px solid #ddd;display: inline-block;vertical-align: middle;cursor: pointer">
                    <img style="width:16px;height: 16px;vertical-align: middle" src="/static/images/add-img2.png">
                </div>
                <button type='button' id="queren" ng-click="addmeal()">确认添加</button>
                <div  id="notarizeAdd" style="width:218px;overflow: scroll;position: absolute;left: 94px;display: none">
                    <ul style="height: 120px">
                        <li name="selectSame" ng-repeat="u in comboItem">
                        <input class="addId" ng-value="u.id" type="hidden" />
                        <input class="addName" style="display: inline-block;line-height: 36px" type="checkbox" name="checkbox"  ng-value="u.name">{{u.name}}</li>
                    </ul> 
                </div>
                <div  style="border:1px solid #006600;OVERFLOW-Y: auto;margin-left:55%; width:210px; height: 140px;" id="mealdetails">
                
                </div>
            </div>
            <!-- <div id="mealdetails" >

            </div> -->
            
            <!-- <div class="setOrgUserContent" style="position: relative;height: 80px;margin-top: 40px;margin-left: 36px;">
            
            <label for="" class="layui-form-label" >服务师: </label>
            <input style="width: 150px;" id="orgUserName" type="text" class="doctor-sign">
            <button type="button" style="width: 60px;" ng-click = "serviceUser('add')">查询</button>

               <div  style="border:1px solid #006600;OVERFLOW-Y: auto;margin-left:55%; width:210px; height: 85px;" id="setOrgUserInfo">
                   
               </div>
            </div> -->
        </form>
        
  </div>
  

  
        <section style="text-align: center; margin-top: 45px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-radius:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="确认添加" ng-click="addCombo()">
        </section>
    </div>

    <script type="text/javascript">
        layui.use('upload',function(){
            var $ = layui.jquery,
            upload=layui.upload;
            var uploadInst = upload.render({
                elem:'#uploadImg',
                url:'/combo/uploadFile/img',
                before:function(){
                    layer.load(2)
                },
                done:function(res){
                    layer.closeAll("loading");
                    if (res.success == true) {
                        $('#upload_img').attr('src',res.obj);
                        $('#divPhoto').val(res.obj);
                    }else{
                        layer.msg(res.msg);
                    }
                }
            })
        })
        
        layui.use('upload',function(){
            var $ = layui.jquery,
            upload=layui.upload;
            var uploadInst = upload.render({
                elem:'#uploadEditImg',
                url:'/combo/uploadFile/img',
                before:function(){
                    layer.load(2)
                },
                done:function(res){
                    layer.closeAll("loading");
                    if (res.success == true) {
                        $('#upload_editImg').attr('src',res.obj);
                        $('#divEditPhoto').val(res.obj);
                        $("input[name='divEditPhoto']")[0].dispatchEvent(new Event('input'))
                    }else{
                        layer.msg(res.msg);
                    }
                }
            })
        })
    </script>