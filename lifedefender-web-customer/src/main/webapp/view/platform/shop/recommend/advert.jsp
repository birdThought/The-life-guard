
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/static/css/reportanalysis/report-analysis.css">
<div class="orderCenter" ng-controller="recommendController" ng-init="init('advert')" style="padding: 34px 0 0 20px;">

    <div class="titleShow">
        <div class="orderCenterTop clearfix" style="display: inline-block;width: auto;padding-left:0;">
            <ol>
                <li class="orderout">
                    <span class="outdate">
                       <label>商品名称</label>
                       <input type="text" placeholder="请输入商品名称" name="goodsName" ng-model="advert.conditions.goodsName"/>
                    </span>
                </li>
                <li class="orderout">
                    <span class="outdate">
                       <label>商铺名称</label>
                       <input type="text" placeholder="请输入商铺名称" name="shopName" ng-model="advert.conditions.shopName"/>
                    </span>
                </li>
                <li class="orderStatus">
                    <span class="small-tip">广告类型</span>
                    <select class="Select-Options" name="advertType" id="advertType" ng-model="advert.conditions.advertType">
                    	<option value>-全部-</option>
                        <option value="1">商铺广告</option>
                        <option value="2">商品广告</option>
                    </select>
                </li>
                <li class="orderout">
                    <span class="outdate" style="margin-left:10px;">
                    	<label>日期</label>
                    	<input type="text" name="date" readonly/>
                    </span>
                </li>
                <li>
                	<button class="button_blue" style="margin-left: 50px;" ng-click="advert.searchAdvertList()">
						<i class="layui-icon">&#xe615;</i> 查询
					</button>
                </li>
                <li>
                	<button class="btn-add-advert" ng-click="advert.addAdvert()">
						+  添加广告
					</button>
                </li>
            </ol>
        </div>
    </div>
    <div class="orderCenterBottom" >
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td width="100">广告类型</td>
                <td>商铺名称</td>
                <td>商品名称</td>
                <td style="width:97px;">
                	图片
                </td>
                <td width="140">投放时间</td>
                <td width="120">链接</td>
                <td width="120">用户</td>
                <td width="180">操作</td>
            </tr>
            </thead>
            <fbody>
                <tr ng-repeat="(k,o) in advert.results">
                    <td>
                    	{{o.advertType == 1?'商铺广告':o.advertType == 2?'商品广告':''}}
                    </td>
                    <td>{{o.shopName}}</td>
                    <td>{{o.goodsName}}</td>
                    <td>
                    	<img onclick="showPhoto(this)" src="{{o.pictureUrl || o.image}}"
                                                          style="width:96px;height: 120px;cursor: pointer">
                    </td>
                    <td width="140">{{o.showTime}}</td>
                    <td><a ng-if="o.url" href="{{o.url}}" style="color:blue;">点我链接</a></td>
                    <td>{{o.userName}}</td>
                    <td>
	                    <button class="btn-oper" ng-click="advert.remove(k,o.id)" name="remove" ng-if="o.status == 1">
				        	<!-- <i class="layui-icon">&#xe640;</i>&nbsp; -->删除
				       	</button>
                    </td>
                </tr>
              
            </fbody>
        </table>
        <div id="page" style="text-align: center; margin-top: 20px"></div>
        
        <div id="add-advert-choice" style="display: none;">
        	<div class="search-block" name="查询">
        		<ol>
        			<li>
        				<span>
		        			<label>广告类型</label>
		                    <select  name="advertType" id="add_advertType" ng-model="advert.add.conditions.advertType">
		                        <option value="1">商铺广告</option>
		                        <option value="2">商品广告</option>
		                    </select>
	                    </span>
        			</li>
        			<li>
                       <span>
		        			<label>商铺名称</label>
	                       <input type="text"  name="shopName" ng-model="advert.add.conditions.shopName"/>
                       </span>
        			</li>
        			<li ng-if="advert.add.conditions.advertType == 2">
        				<span>
		        			<label>商品名称</label>
	                       <input type="text"  name="goodsName" ng-model="advert.add.conditions.goodsName"/>
                       </span>
        			</li>
        			<li>
        				<span>
		        			<label>用户</label>
	                        <input type="text"  name="userName" ng-model="advert.add.conditions.userName"/>
                        </span>
        			</li>
        			<li>
	                	<button class="button_blue" style="margin-left: 20px;height:32px;" ng-click="advert.add.screen()">
							<i class="layui-icon">&#xe615;</i>筛选
						</button>
                	</li>
                	<!-- <li>
	                	<button class="btn-add-advert" ng-click="advert.add.commit()">
							+  确认添加
						</button>
                	</li> -->
        		</ol>
        	</div>
        	
        	<div class="table-list">
        		<table border="0" cellpadding="0" cellspacing="0" ng-if="advert.add.conditions.advertType == 1" name="商铺">
        			<thead>
        				<tr>
        					<th>商铺名</th>
        					<th>联系人</th>
        					<th>广告投放日期</th>
        					<th>操作</th>
        				</tr>
        			</thead>
        			<tbody>
        				<tr ng-repeat="(k,s) in advert.add.shopList">
        					<td>
        						{{s.shopName}}
        					</td>
        					<td>
        						{{s.userName}}
        					</td>
        					<td class="layui-input-block">
			                    <input type="text" class="layui-input" name="choice-date" readonly
			                      index="{{k}}" advert-type="shop"/>
                    		</td>
                    		<td>
                    			<button class="btn-add-advert" ng-if="s.showTime" ng-click="advert.add.commit(k,'shop')">
									+  确认添加
								</button>
                    		</td>
        				</tr>
        			</tbody>
        		</table>
        		<!-- 商品广告 -->
        		<table ng-if="advert.add.conditions.advertType == 2" name="商品">
        			<thead>
        				<tr>
        					<th>商品名</th>
        					<th>商铺</th>
        					<th>广告投放日期</th>
        					<th>操作</th>
        				</tr>
        			</thead>
        			<tbody>
        				<tr ng-repeat="(k,g) in advert.add.goodsList">
        					<td>
        						{{g.goodsName}}
        					</td>
        					<td>
        						{{g.shopName}}
        					</td>
        					<td class="layui-input-block">
		                    	<input type="text" class="layui-input" name="choice-date" readonly
		                    	 index="{{k}}" advert-type="goods"/>
		                    </td>
		                    <td>
		                    	<button class="btn-add-advert" ng-if="g.showTime" ng-click="advert.add.commit(k,'goods')">
									+  确认添加
								</button>
							</td>
        				</tr>
        			</tbody>
        		</table>
        	</div>
        </div>
</div>
<style>
	button{border:1px;border-radius: 2px;}
	.btn-oper{text-align:center;width:50px;height:30px;}
	button.btn-oper[name='remove']{background-color:#5678DF;}
	button.btn-oper[name='remove']:hover{background-color:#8DB9D7;}
	.btn-add-advert{margin-left: 50px;text-align:center;width:80px;height:29px;background-color:#6196B5;}
	.btn-add-advert:hover{background-color:#8AABBE;}
	
	/* 添加广告 */
	#add-advert-choice{margin: 0 5px;}
	#add-advert-choice .search-block{display: inline-block;}
	#add-advert-choice .search-block ol li{float:left}
	#add-advert-choice .search-block ol li span{
		border: 1px solid #E0BCBC;margin-left:5px;
		height:32px;line-height: 30px;
		display: inline-block;
	}
	.search-block ol li span label{display: inline-block;padding: 0px 5px;border-right:1px solid #E0BCBC;background-color:#CAE8DF;}
	.search-block ol li span input{
		border: none;
	}
	.search-block select{
		border: none;
	}
	.search-block ol li span input{width:125px;}
	#add-advert-choice .table-list{height:620px;overflow-y:scroll;margin:15px 10px 0 5px;}
	#add-advert-choice .table-list table{width:98%;}
</style>
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
                        "area": [638, 851] //原图宽高
                    }
                ]
            }
        })
    }
</script>