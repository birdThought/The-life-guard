<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="/static/css/reportanalysis/report-analysis.css">
<link rel="stylesheet" href="/static/css/shop/goodsForm.css">
<!-- 废弃 -->
<div class="orderCenter" ng-controller="editGoodsController" ng-init="init()" style="padding: 34px 0 0 20px;">

	<div class="main_bl">
		<h2 class="main_title">添加商品</h2>
	</div>
	<div class="main_bd">
		<h3 class="group_label">
			<i>●</i>基础信息
		</h3>
		<div class="frm_control_group">
			<label for="" class="frm_label"> 选择类目 </label>
			<div class="frm_controls">
				<div class="category_select">
					<span class="category_txt"></span> <a href="#"
						class="js_categorychange">修改</a>
				</div>
				<p class="frm_tips">商品上架后不可修改，请谨慎选择</p>
			</div>
		</div>
		<div class="frm_control_group">
			<label for="" class="frm_label"> 商品属性<br> <span
				class="gray">(选填)</span>
			</label>
			<div class="frm_controls">
				<div class="ext_box js_container dn" style="display: block;">
					<table>
						<tbody>
						<tr ng-repeat="arr in base.attrs2">
							<td ng-repeat-start="att in arr" class="property_label">
								{{att.attribute.name}}
							</td>
							<td ng-repeat-end class="property_value">
								<div class="dropdown_menu search"
									id="property_{{att.attribute.id}}">
									<select name="select_{{att.attribute.id}}">
										<option value="">选择</option>
										<option ng-repeat="(v_idx,attValue) in att.attributeValues" value="{{attValue.id}}">
											{{attValue.name}}
										</option>
									</select>
								</div>
							</td>
						</tr>
						</tbody>
					</table>

					<div class="add_property_wrp">
						<a class="btn_add_property" href="" ng-click="addDialog('dialog_add_property','添加属性')">添加属性</a>
						<div style="display:none;" id="dialog_add_property">
							<div class="grid_line">
								<div class="grid_item">
									<label class="label"> 属性名称 </label>
									<div class="content">
										<span class="frm_input_box"> <input name="name" type="text"
											ng-model="attribute.name">
										</span>
									</div>
								</div>
								<div class="grid_item">
									<label class="label" for=""> 属性值 </label>
									<div class="content">
										<span class="frm_input_box"> <input name="value" type="text"
											ng-model="attribute.value" class="frm_input js_value">
										</span>
									</div>
								</div>
								<div class="grid_item">
									<label class="label" for=""> &nbsp; </label>
									<div class="content">
										<a href="" ng-click="addProperty()" >添加</a>
										<a href="" ng-click="closeLayer()" >取消 </a>
									</div>
								</div>
							</div>
							<p class="frm_tips"></p>
						</div>
					</div>
				</div>
				<p class="frm_tips">商品上架后不可修改，请谨慎选择</p>
			</div>
		</div>
		<div class="frm_control_group"
			style="padding-bottom: 22px;">
			<label for="" class="frm_label"> 商品分组<br> <span
				class="gray">(必填)</span>
			</label>
			<div class="">
				<div ng-repeat="arr in base.labels2" class="js_checkbox_wrp">
					<label ng-repeat="label in arr" class="frm_checkbox_label">
						 <i class="icon_checkbox"></i> 
						 <input type="checkbox" class="frm_checkbox" ng-model="label.checked" ng-value="label.id"/>
						 <span class="lbl_content">{{label.labelName }}</span> 
					</label>
				</div>
				<button ng-click="test()">测试</button>
			</div>
		</div>
		<div class="frm_control_group" id="js_name">
			<label for="" class="frm_label"> 商品名称 </label>
			<div class="frm_controls">
				<span class="frm_input_box input_goods_name">
					<input type="text" ng-model="goods.goodsName" placeholder="限30个字" class="frm_input">
				</span>
			</div>
		</div>
		<div class="frm_control_group">
			<label class="frm_label">商品规格</label>
			<div class="frm_controls">
				<div class="frm_controls js_guige_setting">
					<label class="frm_radio_label">
					 	<i class="icon_radio"></i> 
						<input type="radio" name="skuType" ng-checked="goods.skuType == 0" ng-value=0 ng-model="goods.skuType">
						<span class="lbl_content">统一规格</span> 
					</label>
					 <label class="frm_radio_label">
						<i class="icon_radio"></i> 
						<input type="radio" name="skuType" ng-checked="goods.skuType == 1" ng-value=1  ng-model="goods.skuType">
						<span class="lbl_content">多规格</span> 
					</label>
				</div>
				
				<div ng-class="{true:'', false:'display_class'}[goods.skuType==1]">
					<div>
						<div name="列表区">
							<div class="spec_list" ng-repeat="(n_k,v) in specs">
								<p>
									<strong>{{v.name}}</strong>&nbsp;&nbsp;
									<a href="" ng-click="editSpecByKey(n_k)">编辑</a>&nbsp;&nbsp;
									<a href="" ng-click="removeSpecByKey(n_k)">删除</a>
								</p>
								<div class="checkbox_wrp js_level">
									<label ng-repeat="(v_k,val) in v.values">
										<i class="icon_checkbox"></i> 
										<input type="checkbox" name="spec_name_{{v.name}}" ng-change="checkSpecValue(n_k,v_k)"
											 ng-model="val.used" ng-value="val.value">
										<span class="lbl_content">{{val.value}}</span>
										<!-- <span class="frm_input_box"> 
											<input type="text" ng-value="val"/>
										</span>  -->
									</label>
								</div>
							</div>
						</div>
						
						<div name="添加区">
							<a href="" ng-click="editSpecByKey()">添加规格</a>
						</div>
						
						<!-- 编辑规格 -->
						<div id="dialog_edit_spec" style="display:none;">
							<div class="add_standard  js_sku_form">
								<div class="frm_control_group">
									<label class="frm_label"> 规格名称 </label>
									<div class="frm_controls">
										<span class="frm_input_box append limit_append">
											<input type="text" ng-model="spec.name"/> 
											<span>0/15</span>
										</span>
										<p class="frm_msg fail">
											<span class="frm_msg_content">请填写规格名称，不超过15个字</span>
										</p>
									</div>
								</div>
								<div class="frm_control_group">
									<label class="frm_label">规格值</label>
									<div class="frm_controls">
										<span class="frm_input_box append"> 
											<input name="" type="text" ng-model="specTempValue"> 
											<span class="frm_input_append js_addnum">0/15</span>
										</span>
											<a href="" ng-click="addSpecValueToList()">添加</a>
											<p class="frm_msg fail">
												<span class="frm_msg_content">请至少添加一个规格值</span>
											</p>
										<div class="js_sku_addlist">
											<span ng-repeat="(k0,val) in spec.values">
												<span class="standard_item_name">{{val.value}}</span>
												<a href="" ng-click="removeSpecValueFromList(k0)">X</a>
											</span> 
										</div>
										<div class="oper">
											<a href="" ng-click="saveToSpecArray(spec.k)" class="js_sure">确定</a>
											<a href="" ng-click="cancelEditSpec()" class="js_cancel">取消</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 编辑规格 -->						
						
					</div>
				</div>
			</div>
		</div>
		
		<div ng-class="{true:'', false:'display_class'}[goods.skuType == 1 && skus.length > 0]">
			<label for="" class="frm_label">价格&amp;库存</label>
			<div class="frm_controls js_container pt5">
				<p class="tips">微信价需低于原价，图片选传，单张图片大小不超过30kb。</p>
				<div class="table_wrp2">
					<table class="table table_complex" cellspacing="0">
						<tbody>
							<tr name="标题">
								<th ng-repeat="(index,spec) in specs" ng-if="spec.used">
									{{spec.name}}
								</th>
								<th class="table_cell_t wxprice">微信价</th>
								<th class="table_cell_t price">原价<span class="gray">(选填)</span></th>
								<th class="table_cell_t count">库存</th>
								<th class="table_cell_t no no_extra">商品条码<span
									class="gray">(选填)</span>&nbsp;
									<i class="icon_msg_mini"  data-x="-142" data-y="5"></i>
								</th>
							</tr>
							<tr>
								<td ng-repeat="(index,spec) in specs" ng-if="spec.used">
									<span class="label">{{spec.values[0].value}}</span>
								</td>
								
								<td>
									<span> 
										<input type="text" value=""/>
									</span>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div ng-class="">
			<label for="" class="frm_label"> 微信价<br>
			</label>
			<div class="frm_controls">
				<span class="frm_input_box frm_input_mid"> <input type="text"
					class="frm_input">
				</span>元
				<p class="frm_tips">销售价，需低于原价</p>
			</div>
		</div>
		<div ng-class="">
			<label for="" class="frm_label"> 原价<br> <span
				class="gray">(选填)</span>
			</label>
			<div class="frm_controls">
				<span class="frm_input_box frm_input_mid"> <input type="text"
					class="frm_input">
				</span>元
			</div>
		</div>
		<div ng-class="">
			<label for="" class="frm_label"> 商品库存<br>&nbsp;
			</label>
			<div class="frm_controls">
				<span class="frm_input_box frm_input_mid"> <input type="text"
					class="frm_input">
				</span>
			</div>
		</div>
		<div ng-class="">
			<label for="" class="frm_label"> 商品条码&nbsp;<i
				class="icon_msg_mini ask js_tts"
				data-tips="填写商品条码后，用户可通过扫码查找到你的商品，建议填写" data-x="-142" data-y="5"></i><br>
				<span class="gray">(选填)</span>
			</label>
			<div class="frm_controls">
				<span class="frm_input_box frm_input_mid"> <input type="text"
					class="frm_input">
				</span>
			</div>
		</div>
		
		
		<!-- 
		<div ng-class="">
			<label for="" class="frm_label">价格&amp;库存</label>
			<div class="frm_controls js_container pt5">
				<div class="js_guige_price_all">
					<p class="tips">微信价需低于原价，图片选传，单张图片大小不超过30kb。</p>
					<div class="table_wrp2">
						<table class="table table_complex upload_tips_block"
							cellspacing="0">
							<tbody>
								<tr>
									<th class="table_cell_one" colspan="2">颜色 <i
										class="icon_msg_mini ask js_tts_naxx"
										data-tips="你可选择是否上传颜色对应图片，上传后将在用户选择颜色的时候出现在对应的位置，方便用户选择。"
										data-x="-142" data-y="5"></i></th>

									<th class=" table_cell_t ">尺寸</th>


									<th class="table_cell_t wxprice">微信价</th>
									<th class="table_cell_t price">原价<span class="gray">(选填)</span></th>
									<th class="table_cell_t count">库存</th>
									<th class="table_cell_t no no_extra">商品条码<span
										class="gray">(选填)</span>&nbsp;<i
										class="icon_msg_mini ask js_tts_naxx"
										data-tips="填写商品条码后，用户可通过扫码查找到你的商品，建议填写" data-x="-142"
										data-y="5"></i></th>
								</tr>

								<tr data-id="$颜色:$白色;$尺寸:$11">

									<td class="table_cell_one" rowspan="3"><span class="label">白色</span>
									</td>
									<td class="table_cell_t table_cell_pic" rowspan="3">
										<div class="upload_wrp">
											<span class="upload_area"> <a
												class="btn btn_upload js_guige_naxx_img"
												id="js_guige_naxx_img颜色白色尺寸11">添加图片</a>
											</span>
										</div>
										<div class="pics_wrp upload_preview"></div>
									</td>
									<td class="table_cell_t table_cell_l" rowspan="1"><span
										class="table_cell_label">11</span></td>

									<td class=" table_cell_t wxprice"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_wxp"
											data-id="$颜色:$白色;$尺寸:$11" value="" data-index="-1">
									</span></td>
									<td class="no_extra table_cell_t price"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_yp"
											data-id="$颜色:$白色;$尺寸:$11" value="" data-index="-1">
									</span></td>
									<td class="table_cell_t count"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_stock_input"
											data-id="$颜色:$白色;$尺寸:$11" value="" data-index="-1">
									</span></td>
									<td class="table_cell_t no"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_code_input"
											data-id="$颜色:$白色;$尺寸:$11" value="" data-index="-1">
									</span></td>
								</tr>

								<tr data-id="$颜色:$白色;$尺寸:$12">
									<td class=" table_cell_l" rowspan="1"><span
										class="table_cell_label">12</span></td>

									<td class="  wxprice"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_wxp"
											data-id="$颜色:$白色;$尺寸:$12" value="" data-index="-1">
									</span></td>
									<td class="no_extra  price"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_yp"
											data-id="$颜色:$白色;$尺寸:$12" value="" data-index="-1">
									</span></td>
									<td class=" count"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_stock_input"
											data-id="$颜色:$白色;$尺寸:$12" value="" data-index="-1">
									</span></td>
									<td class=" no"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_code_input"
											data-id="$颜色:$白色;$尺寸:$12" value="" data-index="-1">
									</span></td>
								</tr>

								<tr data-id="$颜色:$白色;$尺寸:$13">

									<td class=" table_cell_l" rowspan="1"><span
										class="table_cell_label">13</span></td>

									<td class="  wxprice"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_wxp"
											data-id="$颜色:$白色;$尺寸:$13" value="" data-index="-1">
									</span></td>
									<td class="no_extra  price"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_price_input_yp"
											data-id="$颜色:$白色;$尺寸:$13" value="" data-index="-1">
									</span></td>
									<td class=" count"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_stock_input"
											data-id="$颜色:$白色;$尺寸:$13" value="" data-index="-1">
									</span></td>
									<td class=" no"><span
										class="frm_input_box frm_input_box_mini"> <input
											type="text" class="frm_input js_guige_code_input"
											data-id="$颜色:$白色;$尺寸:$13" value="" data-index="-1">
									</span></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		 -->
		
		
		<div class="frm_control_group" id="js_guige_ty_images">
			<label for="" class="frm_label"> 商品图片 </label>
			<div class="frm_controls pt5">
				<ul class="upload_shop_img_list">
					<li class="upload_shop_img_item">
						<h4 class="title">
							主图<span class="desc">(建议尺寸为640像素*640像素，大小不超过500kb)</span> <i
								class="icon_msg_mini ask js_tts"
								data-tips="商品主图将会作为商品的默认图片出现在货架及商品详情页。" data-x="-142" data-y="5"></i>
						</h4>
						<div class="upload_wrp">
							<div class="upload_preview">
								<div class="pics_wrp js_guige_images_unitymain"></div>
							</div>
							<div class="upload_box">
								<div class="upload_area">
									<a class="create_access_primary" id="js_guige_images_unitymain"
										href="javascript:"> <i class="icon20_common add_gray">上传</i>
									</a>
								</div>
							</div>
						</div>
					</li>
					<li class="upload_shop_img_item">
						<h4 class="title">
							其它图片<span class="desc">(选传，单张图片大小不超过500kb，最多10张)</span> <i
								class="icon_msg_mini ask js_tts"
								data-tips="将出现在商品图片库，方便用户更好的了解您的商品。" data-x="-142" data-y="5"></i>
						</h4>
						<div class="upload_wrp">
							<div class="upload_preview">
								<div class="pics_wrp js_guige_images_unitysub"></div>
							</div>
							<div class="upload_box">
								<div class="upload_area">
									<a class="create_access_primary" id="js_guige_images_unitysub"
										href="javascript:"> <i class="icon20_common add_gray">上传</i>
									</a>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="frm_control_group" id="js_xiangqing">
			<label for="" class="frm_label"> 详情描述 </label>
			<div class="frm_controls pt5">
				<label class="frm_radio_label selected" for="checkbox18"> <i
					class="icon_radio"></i> <span class="lbl_content">不设置</span> <input
					type="radio" value="0" class="frm_radio" checked="" id="checkbox18">
				</label> <label class="frm_radio_label" for="checkbox19"> <i
					class="icon_radio"></i> <span class="lbl_content">设置</span> <input
					type="radio" value="1" class="frm_radio" id="checkbox19">
				</label>
				<div class="goods_desc_wrp" id="js_xiangqing_desc"
					style="display: none;">
					<p class="frm_tips">
						<span class="js_txtlimit">图片大小不超过300kb，还可以输入<span>5000</span>字
						</span>
					</p>
					<div class="goods_desc">
						<div class="goods_desc_content js_goods_desc_content"></div>
						<span class="create_access"> <i
							class="icon36_common add_gray"> </i> <a href="javascript:;"
							class="btn_add_pic"> <i class="icon_add_pic"></i> <strong>添加图片</strong>
						</a> <a href="javascript:;" class="btn_add_txt"> <i
								class="icon_add_txt multi"></i> <strong>添加文字</strong>
						</a>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="frm_control_group frm_region" style="line-height: 30px;">
			<label class="frm_label" for="">是否限购</label>
			<div class="frm_controls pt5" id="js_xiangou">
				<label class="frm_radio_label selected" for="checkbox22"> <i
					class="icon_radio"></i> <span class="lbl_content">不限购</span> <input
					type="radio" value="0" class="frm_radio" checked="" id="checkbox22">
				</label> <label class="frm_radio_label" for="checkbox23"> <i
					class="icon_radio"></i> <span class="lbl_content">限购</span> <input
					type="radio" value="1" class="frm_radio" id="checkbox23">
				</label> <span class="frm_input_box frm_input_box_mini js_xiangou_txt"
					style="display: none;"> <input type="text" class="frm_input">
				</span>
			</div>
		</div>
		<h3>
			<i>●</i>物流信息
		</h3>
		<div class="frm_control_group frm_region">
			<label class="frm_label" for="">所在地</label>
			<div style="" id="js_region_edit" class="frm_controls pt5">
				<span class="js_country">中国</span> <span class="js_province_wrap">&gt;&gt;</span>
				<span class="js_province">广东</span> <span class="js_city_wrap">&gt;&gt;</span>
				<span class="js_city">广州</span>&nbsp;&nbsp;<a href="javascript:;"
					class="js_edit">编辑</a>
			</div>
			<div class="frm_controls pt5" id="js_region" style="display: none">
				<div id="js_country264" style="" class="dropdown_menu">
					<a href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label
						class="jsBtLabel">国家</label><i class="arrow"></i></a>
					<div class="dropdown_data_container jsDropdownList"
						style="display: none;">
						<ul class="dropdown_data_list">

							<li class="dropdown_data_item "><a onclick="return false;"
								href="javascript:;" class="jsDropdownItem" data-value="1017"
								data-index="0" data-name="中国">中国</a></li>

							<li class="dropdown_data_item "><a onclick="return false;"
								href="javascript:;" class="jsDropdownItem" data-value="1031"
								data-index="1" data-name="中国香港">中国香港</a></li>

						</ul>
					</div>
				</div>
				<div id="js_province6391" style="display: none"></div>
				<div id="js_city3644" style="display: none"></div>
			</div>
		</div>
		<h3>
			<i>●</i>售后信息
		</h3>
		<div class="frm_control_group" id="js_fapiao">
			<label for="" class="frm_label"> 发票 </label>
			<div class="frm_controls pt5">
				<label class="frm_radio_label selected" for="checkbox24"> <i
					class="icon_radio"></i> <span class="lbl_content">无</span> <input
					type="radio" value="0" class="frm_radio" checked="" id="checkbox24">
				</label> <label class="frm_radio_label" for="checkbox25"> <i
					class="icon_radio"></i> <span class="lbl_content">有</span> <input
					type="radio" value="1" class="frm_radio" id="checkbox25">
				</label>
			</div>
		</div>
		<div class="frm_control_group" id="js_baoxiu">
			<label for="" class="frm_label">保修</label>
			<div class="frm_controls pt5">
				<label class="frm_radio_label selected" for="checkbox26"> <i
					class="icon_radio"></i> <span class="lbl_content">无</span> <input
					type="radio" value="0" class="frm_radio" checked="" id="checkbox26">
				</label> <label class="frm_radio_label" for="checkbox27"> <i
					class="icon_radio"></i> <span class="lbl_content">有</span> <input
					type="radio" value="1" class="frm_radio" id="checkbox27">
				</label>
			</div>
		</div>
		<h3>
			<i>●</i>上架设置
		</h3>
		<div class="frm_control_group" id="js_shangjia">
			<label for="" class="frm_label">上架时间</label>
			<div class="frm_controls pt5">
				<label class="frm_radio_label" for="checkbox28"> <i
					class="icon_radio"></i> <span class="lbl_content">暂不上架，放入<a
						href="/merchant/goods?type=1&amp;t=shop/list&amp;offset=0&amp;count=20&amp;token=933976996&amp;lang=zh_CN"
						target="_blank" class="url_mine">我的商品</a>中
				</span> <input type="radio" value="0" class="frm_radio" id="checkbox28">
				</label> <label class="frm_radio_label selected" for="checkbox29"> <i
					class="icon_radio"></i> <span class="lbl_content">立即上架</span> <span
					class="error_tips">（上架后商品类目、商品属性及商品名称将不能再修改，请确认已填写无误）</span> <input
					type="radio" value="1" class="frm_radio" checked="" id="checkbox29">
				</label>
			</div>
		</div>
		<div class="tool_bar tc border">
			<a href="javascript:;" class="btn btn_primary" id="js_addgood">确定</a>
		</div>
	</div>
</div>