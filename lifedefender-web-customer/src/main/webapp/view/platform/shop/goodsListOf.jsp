
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<div class="orderCenter" ng-controller="shopGoodsController" ng-init="init()" style="padding: 34px 0 0 20px;">

    <div class="titleShow">
        <div class="orderCenterTop clearfix" style="display: inline-block;width: auto;">
            <ol>
                <li class="orderDate">
                    <span class="outdate">
                       <label>商品名称</label>
                       <input type="text" placeholder="请输入商品名称" value="" name="goodsName" ng-model="conditions.goodsName"/>
                    </span>
                </li>
                <li ng-if="userInfo.agentId == 0" class="orderout"><!-- 客服人员 -->
                    <span class="outdate">
                       <label>商铺名称</label>
                       <input type="text" placeholder="请输入商铺名称" value="" name="shopName" ng-model="conditions.shopName"/>
                    </span>
                </li>
                <li class="orderStatus" style="margin-left:50px;">
                    <span class="small-tip">状态</span>
                    <select class="Select-Options" name="status" id="status" ng-model="conditions.status">
                        <option value>-全部-</option>
                        <option value="1">待上架</option>
                        <option value="2">已上架</option>
                        <option value="3">已下架</option>
                    </select>
                </li>
                <li>
                	<button class="button_blue" style="margin-left: 88px" ng-click="searchGoodsList()">
						<i class="layui-icon">&#xe615;</i> 查询
					</button>
                </li>
                <li><!--  ng-if="userInfo.agentId == 3" -->
                	<button class="button_blue" ng-if="userInfo.agentId == 3" style="margin-left: 88px" ng-click="dump('/commodity/toAdd','add')">
						+  添加商品
					</button>
				</li>
            </ol>
        </div>
    </div>
    <div class="orderCenterBottom">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td>商品名称</td>
                <td>市场价</td>
                <td>优惠价</td>
                <td>商铺</td>
                <td>销量</td>
                <td>状态</td>
                <td>最后调整状态时间</td>
                <td>操作</td>
            </tr>
            </thead>
            <fbody>
                
                <tr ng-repeat="o in results">
                    <td>{{o.goodsName}}</td>
                    <td>{{o.marketPrice}}</td>
                    <td>{{o.favorablePrice}}</td>
                    <td>{{o.shopName}}</td>
                    <td>{{o.salesVolume}}</td>
                    <td ng-if="o.status == 1">待上架</td>
                    <td ng-if="o.status == 2">已上架</td>
                    <td ng-if="o.status == 3">已下架</td>
                    <td>{{(o.stateTime || o.createTime) | date:"yyyy-MM-dd hh:mm:ss"}}</td>
                    <td>
                    	<span ng-click = "details(o)" title="查看">
                    		<i class="layui-icon" style="font-size:20px;color:#3D7F9E">&#xe63c;</i>
                    	</span>
                    	<span ng-if="userInfo.agentId == 3 && o.status == 1" ng-click ="dump('/commodity/toAdd','update',o.id)" title="修改">
                    		<i class="layui-icon" style="font-size:20px;color:#3cbaff">&#xe614;</i>
                    	</span>
                    	<span ng-if="userInfo.agentId == 3 && o.status == 2" ng-click = "down(o)" title="下架">
	                    	<i class="layui-icon" style="font-size:20px;color:red">&#xe640;</i>
                    	</span>
                    	<span ng-if="userInfo.agentId == 3 && (o.status == 1 || o.status == 3)" ng-click = "up(o)" title="上架">
	                    	<i class="layui-icon" style="font-size:20px;color:#304D35">&#xe604;</i>
                    	</span>
                    	
                    </td>
                </tr>
              
            </fbody>
        </table>
	<div id="page" style="text-align: center; margin-top: 20px"></div>
    
    <div id="goodsDetails" style="display: none;">
    	<table border="0" cellpadding="0" cellspacing="0" class="detail-table">
    		<tr>
    			<td class="attr_name">商品名称</td>
    			<td class="attr_value">{{goodsDetails.goodsName}}</td>
    		</tr>
    		<tr>
    			<td class="attr_name">商铺</td>
    			<td class="attr_value">{{goodsDetails.shopName}}</td>
    		</tr>
    		<tr>
    			<td class="attr_name">通用名</td>
    			<td class="attr_value">{{goodsDetails.commonTitle}}</td>
    		</tr>
    		<tr ng-repeat="attr in goodsDetails.attributes" style="color:red;">
    			<td class="attr_name">{{attr.aname}}</td>
    			<td class="attr_value">{{attr.vname}}</td>
    		</tr>
    		<tr>
    			<td class="attr_name">市场价</td>
    			<td class="attr_value">{{goodsDetails.market_price}}</td>
    		</tr>
    		<tr>
    			<td class="attr_name">优惠价</td>
    			<td class="attr_value">{{goodsDetails.favorable_price}}</td>
    		</tr><tr>
    			<td class="attr_name">说明书</td>
    			<td class="attr_value">
    				<textarea ng-trim="false" rows="6" cols="48">
						{{goodsDetails.Instructions}}
					</textarea>
				</td>
    		</tr>
    		<tr>
    			<td class="attr_name">图文详情</td>
    			<td class="attr_value">
    				<img onclick="showPhoto(this)" src="{{goodsDetails.details}}"
                                                          style="width:120px;height: 150px;cursor: pointer">
    			</td>
    		</tr>
    		
    	</table>
    	<style type="text/css">
    		.attr_name{width:35%;}
    		.attr_value{width:65%;}
    	</style>
    </div>
</div>
<script>
    function showPhoto(node) {
       /*  var src = $.baseUrl + "/" + $(node).attr("src"); */
       var src = $(node).attr("src");
        layer.photos({
            photos: {
                "status": 1,    //请求的状态，1表示成功，其它表示失败
                "msg": "",  //状态提示语
                "title": "",    //相册标题
                "id": 0,    //相册id
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "name": "身份证", //图片名
                        "pid": 0, //图片id
                        "src": src, //原图地址
                        "thumb": "", //缩略图地址
                        "area": [900, 700] //原图宽高
                    }
                ]
            }
        })
    }
</script>