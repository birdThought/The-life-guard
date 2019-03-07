
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<link rel="stylesheet" href="/static/css/member/tableLayout.css">
<div class="orderCenter right_body" ng-controller="shopController" ng-init="init()" style="padding: 34px 0 0 20px;">

    <div class="titleShow">
        <div class="orderCenterTop clearfix" style="display: inline-block;width: auto;">
            <ol>
                <li class="orderDate">
                    <span class="outdate">
                       <label>店铺名称</label>
                       <input type="text" placeholder="请输入店铺名称" name="shopName" ng-model="conditions.shopName"/>
                    </span>
                </li>
                <li class="orderout">
                    <span class="outdate">
                       <label>门店名称</label>
                       <input type="text" placeholder="请输入门店名称" name="orgName" ng-model="conditions.orgName"/>
                    </span>
                </li>
                <li class="orderStatus" style="margin-left:50px;">
                    <span class="small-tip">状态</span>
                    <select class="Select-Options" name="state" id="state" ng-model="conditions.state">
                        <option value>-全部-</option>
                        <option value="0">待审核</option>
                        <option value="1">审核不通过</option>
                        <option value="2">审核通过</option>
                        <option value="3">冻结</option>
                        <option value="4">已解冻</option>
                    </select>
                </li>
                <li>
                	<button class="button_blue" style="margin-left: 88px" ng-click="searchShopList()">
						<i class="layui-icon">&#xe615;</i> 查询
					</button>
                </li>
            </ol>
        </div>
    </div>
    <div class="orderCenterBottom">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>商铺名称</td>
                <td>所属门店</td>
                <td>注册时间</td>
                <td>开通时间</td>
                <td>联系人</td>
                <td>手机号</td>
                <td>电话</td>
                <td>地址</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <fbody>
                
                <tr ng-repeat="(k,o) in results">
                    <td>{{o.shopName}}</td>
                    <td>{{o.orgName}}</td>
                    <td>{{o.createTime | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                    <td>{{o.state == 2?(o.stateTime | date:"yyyy-MM-dd hh:mm:ss"):''}}</td>
                    <td>{{o.userName}}</td>
                    <td>{{o.mobile}}</td>
                    <td>{{o.telephone}}</td>
                    <td>{{o.address}}</td>
                    <!-- 0-待审核,1-审核不通过,2-审核通过,3-冻结,4-解冻 -->
                    <td ng-if="o.state == 0" style="color:blue;">待审核</td>
                    <td ng-if="o.state == 1" style="color:#DDB6A6;">审核不通过</td>
                    <td ng-if="o.state == 2" style="color:green;">审核通过</td>
                    <td ng-if="o.state == 3" style="color:#B44A1C;">冻结</td>
                    <td ng-if="o.state == 4" style="color:#304D35;">已解冻</td>
                    <td>
                    	<button class="layui-btn layui-btn-normal layui-btn-xs" ng-if="o.state == 0" 
                    		ng-click = "authit(o)">审核</button>
                    		<button class="layui-btn layui-btn-normal layui-btn-xs" ng-if="o.state == 1" 
                    		ng-click = "authit(o)">再审核</button>
                    	<button class="layui-btn layui-btn-xs" ng-if="o.state == 3" ng-click = "unfrozen(o)">解冻</button>
                    	<button class="layui-btn layui-btn-danger" ng-if="o.state == 4 || o.state == 2" ng-click = "frozen(o)">冻结</button>
                    </td>
                </tr>
              
            </fbody>
        </table>
     <div id="page" style="text-align: center; margin-top: 20px"></div>
        
    <div class="dialog-block" id="authit-content" style="display: none;">
        <!-- <img class="org-head" src="{{orgVeriData.logo}}"/> -->
        <div class="param-item">
            <label>商铺名称：</label><span>{{authitShop.shopName}}</span>
        </div>
        <div class="param-item">
            <label>所属门店：</label><span>{{authitShop.orgName}}</span>
        </div>
        <div class="param-item">
            <label>身份证号：</label><span>{{authitShop.identification}}</span>
        </div>
        <div class="param-item">
            <label>注册时间：</label><span>{{authitShop.createTime | date:"yyyy-MM-dd hh:mm:ss"}}</span>
        </div>
        <div class="param-item">
            <label>联系方式：</label><span>{{authitShop.mobile || authitShop.telephone}}</span>
        </div>
        <div class="param-item">
            <label>详细地址：</label><span>{{authitShop.address}}</span>
        </div>
        <div class="param-item">
            <label style="float: left;">身份证正反面：</label>
            <img class="show-image" name="正面" onclick="showPhoto(this)" src="{{authitShop.identificationFore}}"
                                                          style="width:180px;height: 120px;cursor: pointer">
            <img class="show-image" name="反面" onclick="showPhoto(this)" src="{{authitShop.identificationBack}}"
                                                          style="width:180px;height: 120px;cursor: pointer">
        </div>
        <!-- <div class="param-item" style="margin-top: 8px">
            <label>审核结果：</label>
            <input type="radio" name="pass" ng-value=2 ng-checked="authition.pass == 2"
            	 ng-model="authition.pass" ng-init="authition.pass='2'"/>通过&nbsp;&nbsp;
            <input type="radio" name="pass"
                   ng-model="authition.pass"
                   ng-value=1 ng-checked="authition.pass == 1"
                   style="margin-left: 15px"/>不通过
        </div> -->
        <div class="param-item" style="margin-top: 8px">
            <label style="float: left;">审核意见：</label>
            <textarea ng-model="authition.remarks" rows="3"
                         style="resize:none;padding:8px;width:350px;border:1px solid #ccc;font-family:Microsoft YaHei">
            </textarea>
        </div>
        <div class="commit-btn" style=" padding-left: 80px">
            <button class="save" ng-click="commitAuthit(2)">通过</button>
            <button id="no-pass" ng-click="commitAuthit(1)">不通过</button>
            <button class="back" onclick="layer.closeAll()">取消</button>
        </div>
    </div>
</div>
<style>
	.dialog-block {
	    width: 800px;
	    min-height: 500px;
	    background: #fff;
	    padding: 0 20px 0 80px;
	    font-size: 16px;
	    line-height: 35px;
	}
	.dialog-block label{font-weight:bold;padding-right:10px;}
	.dialog-block img{border:1px solid #ccc;}
	.dialog-block .commit-btn {
	    padding-left: 80px
	}
	
	.dialog-block .commit-btn #no-pass{
		border: none;
	    outline: none;
	    background-color: #F6947A;
    }
	
	.dialog-block .commit-btn button {
	    border-radius: 3px;
	    padding: 5px 20px;
	    cursor: pointer;
	    font-family: 'Microsoft YaHei';
	    margin-top: 15px;
	}
</style>
<script>
    function showPhoto(node) {
       /*  var src = $.baseUrl + "/" + $(node).attr("src"); */
       var start = 0;
       var data = [];
       $(".show-image").each(function(i, val){
    	   if(node == this){
    		   start = i;
    	   }
    	   var d = {
                   "name": $(this).attr('name'), // 图片名
                   "pid": 0, //图片id
                   "src": $(this).attr('src'), //原图地址
                   "thumb": "", //缩略图地址
                   "area": [638, 851] //原图宽高
               }
    	   data.push(d);
       });
        layer.photos({
            photos: {
                "status": 1,    //请求的状态，1表示成功，其它表示失败
                "msg": "",  //状态提示语
                "title": "身份证",    //相册标题
                "id": 0,    //相册id
                "start": start, //初始显示的图片序号，默认0
                "data": data
            }
        })
    }
</script>