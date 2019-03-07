<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    #orderCenterPopup span.small-tip,#orderCenterPopup1 span.small-tip {line-height: 36px;text-align: center}
	#orderCenterPopup2 section span {
		display:inline-block;
		width:118px;
		text-align:right;
	}
	.layui-layer-page{width:630px!important}
	#orderCenterPopup section:nth-of-type(2){margin-left:0}
	#orderCenterPopup2 section:nth-of-type(2){margin-left:0}
</style>
 <div class="orderCenter" ng-init="init()" style="padding: 16px 0 0 20px;" ng-clock>
     <div class="titleShow clearfix">
         <label style="border-left:2px solid #0093ff;padding-left: 10px;margin-bottom: 10px;display: inline-block;font-size: 18px;color: #0093ff;" class="action">
             版本管理
         </label>
         <div style="display: inline-block;">
             <button  style="margin: 14px 0 8px 100px;float: none;position:static;background: #3a87fc;color: #fff;padding: 4px 10px;text-align: center;border:none;" class="search search-btn cursor-pointer" ng-click = "addDialog()">添加版本</button>
         </div>
     </div>
 	<div class="orderCenterBottom" ng-cloak>
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td style="width: 242px;">应用名称</td>
                <td>发布版本号</td>
                <td>公开版本号</td>
                <td style="width: 640px;">版本说明</td>
                <td>操作</td>
            </tr>
            </thead>
           
            <tbody>
                <tr ng-repeat="v in version">
                	<td>{{v.appname}}</td>
                	<td>{{v.version}}</td>
                	<td>{{v.publicVersion}}</td>
                	<td>{{v.comment}}</td>
                    <td><span class="ny" ng-click="EditDialog(v)"><i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe642;</i></span>
  					<span class="ny" ng-click="DeleteDialog(v)"><i class="layui-icon" style="font-size:20px;color:blue">&#xe640;</i></span></td>
                </tr>
            </tbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
    </div>
	<!-- 删除 -->
 	   <div id="deleteContent" style="display: none;text-align: center;">
            <p style="color:#222;line-height: 36px;font-size: 16px;text-align: center">确认删除?</p>
            <button style="border: none;font-size: 14px;text-align: center;margin-top: 30px;color:#FFF;width: 80px;height: 30px;line-height: 30px;background: #3cbaff"
                ng-click="deleteversion()"
            >确认</button>
        </div>
 
	<!-- 添加版本 -->
     <div id="orderCenterPopup" class="orderPopup ">
         <section class="clearfix" style="margin-top: 15px;">
            <span  class="small-tip">
               应用名称：
            </span>
            <input id="version_appname" class="doctor-sign Select-Options" placeholder="请输入应用名称" >
        </section>
        
           <section class="clearfix" style="margin-top: 15px;">
            <span class="small-tip">
                发布版本号：
            </span>
            <input id="version_version" class="doctor-sign Select-Options" placeholder="请输入发布版本号" >
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                公开版本号：
            </span>
            <input id="version_publicVersion" class="doctor-sign Select-Options" placeholder="请输入公开版本号" >
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip" style="vertical-align: top">
                版本说明：
            </span>
            <textarea style="resize: none" id="version_comment" cols="30" rows="5" class="doctor-sign Select-Options" placeholder="请输入版本说明" ></textarea>
        </section>
        
          <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                上传文件：
            </span>
		<button type="button" class="layui-btn" id="test100"><i class="layui-icon"></i>上传文件</button> 
        </section>
        <section class="clearfix"  style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="addversion()">
        </section>
    </div> 
    
	<!--编辑种类 -->
      <div id="orderCenterPopup2" class="orderPopup ">
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                应用名称
            </span>
            <input id="" class="doctor-sign Select-Options" placeholder="" ng-model="edit.appname">
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                发布版本号
            </span>
            <input id="" class="doctor-sign Select-Options" placeholder="" ng-model="edit.version">
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                公开版本号
            </span>
            <input id="" class="doctor-sign Select-Options" placeholder="" ng-model="edit.publicVersion">
        </section>
        
           <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                版本说明
            </span>
            <textarea style="resize: none" id="" class="doctor-sign Select-Options" cols="30" rows="5" placeholder="" ng-model="edit.comment"></textarea>
        </section>
        
               <section class="clearfix"  style="margin-top: 15px;">
            <span class="small-tip">
                上传文件
            </span>
            <button type="button" class="layui-btn" id="test101"><i class="layui-icon" ng-model="edit.path"></i>上传文件</button> 
        </section>
   
        <section class="clearfix"  style="text-align: center; margin-top: 15px;">
            <input style="background-color:#3cbaff;color:#fff;width:120px;height:40px;border-raidus:6px" type="button" ng-class="currentOrder.status == 3 ? 'button_blue_1' : 'button_blue_2'"
                   value="提交数据" ng-click="editversion()">
        </section>
    </div>
    
  </div>
  <script>
  $(document).ready(function(){
	  upload.uploadFile('#test100',
		'/app-version/uploadFile/apk'
		/* function(res){
		  console.log(1)
	  } */
	  )
	   upload.uploadFile2('#test101',
		'/app-version/uploadFile/apk'
		/* function(res){
		  console.log(1)
	  } */
	  )
  })
  var upload={};
  upload.uploadFile=function(elem,url,callback){
	  layui.use('upload',function(){
	  		var $ = layui.juery;
	  		var upload = layui.upload;
	  		upload.render({
	  			elem:elem,
	  			url:url,
	  			unwrap:true,
	  			accept: 'file', 
	  			before:function(){
	  				console.warn('正在上传')
	  			},
	  			done:function(res){
	  					layer.msg('上传成功')
	  				 if (res.code>0){
	  					layer.msg('上传失败')
	  				}
	  				
	  			}
	  		})
	  		
	  	})
	  	
  }
  upload.uploadFile2=function(elem,url,callback){
	  layui.use('upload',function(){
	  		var $ = layui.juery;
	  		var upload = layui.upload;
	  		upload.render({
	  			elem:elem,
	  			url:url,
	  			unwrap:true,
	  			accept: 'file', 
	  			before:function(){
	  				console.warn('正在上传')
	  			},
	  			done:function(res){
	  					layer.msg('上传成功')
	  				 if (res.code>0){
	  					layer.msg('上传失败')
	  				}
	  				
	  			}
	  		})
	  		
	  	})
	  	
  }
  </script>