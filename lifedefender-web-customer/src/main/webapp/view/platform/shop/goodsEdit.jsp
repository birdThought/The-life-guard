<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/static/css/shop/goodsEdit.css"/>
<div class="article" id="outest-div" type="${type }" goods-id="${goodsId }"><!--  ng-controller="goodsEditController" ng-init="init()" -->
   <div class="content">
        <div class="content-header">
            <h1>添加商品</h1>
        </div>
        <div class="content-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend><i class="layui-icon">&#xe756;</i>基本信息</legend>
            </fieldset>
            <form class="layui-form" action="">
                <div class="layui-form-item ">
                    <label class="layui-form-label">选择类目<span class="db select-tips mt0">(必选)</span></label>
                    <div class="layui-input-inline select-options">
                        <p class="select-items"><span></span><span id="change_items" class=" cup">修改</span></p>
                        <p class="select-tips">商品上架后不可修改，情谨慎选择</p>
                    </div>
                </div>
                <div class="layui-form-item clearfix">
                    <label class="layui-form-label">商品属性 <span class="db select-tips mt0">(选填)</span></label>
                    <div class="shop-attr" id="attr-list">
                        <div class="attr-select" id="attrs-view">
                        
                        	<script id="attrs-laytpl" type="text/html">
								
								{{#  layui.each(d, function(index, attr){ }}

								<div class="layui-form-item old-attributes dib {{ index % 4==0?'spec':'' }}">
	                                <label class="layui-form-label {{ index % 4==0?'specially':'' }}">{{ attr.name }}
										{{#  if(attr.shopId != null && attr.shopId != -1){ }}
											<span style="color:blue;" class="delete-attribute old" attr-id="{{ attr.id }}">&nbsp;&nbsp;删除</span>
										{{#  } }}
									</label>
	                                <div class="layui-input-block {{ index % 4==0?'spec':'specially' }}">
	                                    <select name="interest" attr-id="{{ attr.id }}" 
	                                    		attr-index="{{ index }}" {{ attr.shopId==-1?'':'lay-filter="attrs-select"' }}>
	                                        <option value=""></option>
											{{#  layui.each(attr.attributeValues, function(idx, attrValue){ }}
		                                        <option value="{{ attrValue.id }}" {{ attrValue.selected==1?'selected':'' }}>
													{{ attrValue.name }}</option>
	                                        {{#  }); }}
											{{#  if(attr.shopId != null && attr.shopId != -1){ }}
												<option value="-1" class="add-option">添加选项</option>
											{{#  } }}
	                                    </select>
	                                </div>
	                            </div>

								{{#  }); }}
							</script>
							
							
                            <div class="add-attr-box" style="display: none;" id="new-attr">
                                <div class="layui-form-item dib mb0">
                                    <label class="layui-form-label" style="width: 60px;padding-left: 5px;">属性名</label>
                                    <div class="layui-input-inline" style="width: 90px;">
                                        <input type="text" name="name" id="add_attr_name" lay-verify="pass" placeholder="请输入"
                                               autocomplete="off" class="layui-input ">
                                    </div>
                                </div>
                                <div class="layui-form-item dib mb0">
                                    <label class="layui-form-label" style="width: 60px;padding-left: 5px">属性值</label>
                                    <div class="layui-input-inline w120">
                                        <input id="add_attr_val" type="text" name="name" lay-verify="pass" placeholder="请输入"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <button class="layui-btn layui-btn-small layui-btn-primary vt" id="confirm_add">确定</button>
                                <button class="layui-btn layui-btn-small layui-btn-primary vt" id="confirm_cancel">取消
                                </button>
                            </div>
                        </div>
                        <div class="add-attr">
                            <p><span id="add_attr" class="cup">添加属性</span></p>
                            <p class="select-tips spec">商品上架后不可修改，情谨慎选择</p>
                        </div>
                    </div>
                    
                </div>
                <div class="layui-form-item" id="goods-labelIds">
                    <label class="layui-form-label">商品分组<span class="db select-tips mt0">(多选)</span></label>
                    <div class="shop-attr">
                        <div class="attr-select pl20">
                            <div class="CheckBox" id="labels-view">
                            	<script id="labels-laytpl" type="text/html">
									{{#  layui.each(d, function(index, label){ }}
										<input type="checkbox" name="label" value="{{ label.id }}" lay-skin="primary" 
											title="{{ label.categoryName }}" {{ label.checked?'checked':'' }}>
									{{#  }); }}
								</script>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" >
                    <label class="layui-form-label">商品名称<span class="db select-tips mt0">(必填)</span></label>
                    <div class="layui-input-inline">
                        <input type="text" name="goodsName" lay-verify="pass" placeholder="请输入商品名称" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">不超过30个字</div>
                </div>
                <div class="layui-form-item" id="goods-specType">
                    <label class="layui-form-label">商品规格</label>
                    <div class="radio">
                        <input type="radio" name="specification" value="1" lay-filter="specification" title="统一规格" checked="">
                        <input type="radio" name="specification" value="2" lay-filter="specification" title="多规格">
                    </div>
                    <div class="specification-box" style="display: none;">
                        <div class=" shop-attr">
                            <div class="shop-attr-first-child" id="specs-view">
                                <script id="fixSpecs-laytpl" type="text/html">
									{{#  layui.each(d, function(index, spec){ }}
                                	<div class="layui-form-item specs-list-{{ index }}">
	                                    <div class=" pl20" >
	                                        <p class="multi-specification-title" value="{{ index }}" 
	                                        	data-id="{{ spec.id }}" >
	                                        	<u class="name-multi">{{ spec.name }}</u>
												{{#  if(spec.goodsId != -1){ }}
													<span class="edit-rules">编辑</span>
													<span class="edit-delete">删除</span>
												{{#  } }}
	                                         </p>
	                                        <div class="CheckBox spec-values">
												{{#  layui.each(spec.specValues, function(idx, val){ }}
	                                            	<input type="checkbox" lay-filter="inputTags" value="{{ idx }}"
	                                            		name="eleven" lay-skin="primary" title="{{ val.name }}" 
	                                            		data-id="{{ val.id }}" {{val.used?'checked':''}}>
												{{#  }); }}
	                                        </div>
	                                    </div>
	                                </div>
									{{#  }); }}

									{{#  if(d.length < 1){ }}
   										<span class='none'>暂无数据····</span>
  									{{#  } }}
                                </script>
                            </div>

                            <div class="layui-form-item multi-specification-item-spec">
                                <div class="pl20" id="multi_name" style="display: none;" state='add'>
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">规格名</label>
                                            <div class="layui-input-inline">
                                                <input id="input_tag_name" type="text" name="price_min" placeholder="输入规格名" autocomplete="off" class="layui-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">规格值</label>
                                            <div class="layui-input-inline">
                                                <div class="tags" id="tags">
                                                    <input id="inputTags" type="text" name="price_min" placeholder="输入规格值后按回车键添加" autocomplete="off" class="layui-input">
                                                </div>
                                            	
                                            </div>
                                            <div class="layui-input-inline" style="float: right;">
                                            	<button id="add_value_btn" class="layui-btn layui-btn-small">添加</button>
                                            </div>
                                        </div>
                                    </div>
                                    <button id="confirm_add_finish" class="layui-btn layui-btn-small mt12">填写完毕</button>
                                    <button id="confirm_add_cancel" class="layui-btn layui-btn-small mt12">取消</button>
                                </div>
                                <b class="add-specification">添加规格</b>
                            </div>

                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label layui-form-label-spec">价格&规格</label>
                                <div class="layui-input-block" style="margin-left: 84px;">
                                    <p class="select-tips db standard-tips" style="display: none;">
                                    	微信价需要低于原价，图片选传，单张图片大小不要超过30KB
                                    </p>
                                    <p class="select-tips db standard-tips">请选择规格</p>
                                    <table class="standard-area" id="skus-table" style="display: none;" 
                                    		border="0" cellpadding="0" cellspacing="0">
                                    	<tbody>
                                    		<tr class="skus-title">
												
												<th class="table_cell_t market-price">市场价</th>
												<th class="table_cell_t favorable-price">优惠价<span>(选填)</span></th>
												<th class="table_cell_t count">库存</th>
												<th class="table_cell_t braCode">商品条码<span>(选填)</span></th>
											</tr>
											
                                    	</tbody>
                                    </table>                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item single-spec-attr must-number-validate">
                    <div class="layui-inline">
                        <label class="layui-form-label">市场价<span class="dib select-tips mt0">(必填)</span></label>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" name="marketPrice" placeholder="￥" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item single-spec-attr must-number-validate">
                    <div class="layui-inline">
                        <label class="layui-form-label">优惠价<span class="dib select-tips mt0">(选填)</span></label>
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" name="favorablePrice" placeholder="￥" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">优惠价，不高于原价</div>
                    </div>
                </div>
                <div class="layui-form-item single-spec-attr must-number-validate">
                    <label class="layui-form-label">商品库存<span class="dib select-tips mt0">(必填)</span></label>
                    <div class="layui-input-inline">
                        <input type="text" name="inventory" lay-verify="pass" placeholder="请输入商品库存数量" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item single-spec-attr">
                    <label class="layui-form-label">商品条码<span class="dib select-tips mt0">(选填)</span></label>
                    <div class="layui-input-inline">
                        <input type="text" name="braCode" lay-verify="pass" placeholder="您可以选择输入商品条码" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item mb0">
                    <label class="layui-form-label">商品图片</label>
                    <div class="upload-pic">
                        <p class="upload-pic-des"><span>主图</span><span class="dib select-tips mt0">(必传，建议尺寸为604像素*640像素，大小不要超过500kb)</span>
                        </p>
                        <div class="layui-upload-drag single" id="upload_drap">
                            <i class="layui-icon"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                            <img alt="主图" src="" style="display: none;width: 200px;height: 150px;" upload-state="success"/>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" id="other-pictures">
                    <label class="layui-form-label"></label>
                    <div class="upload-pic">
                        <p class="upload-pic-des"><span>其他图片</span><span class="dib select-tips mt0">(选传，大小不要超过500kb，最多十张)</span>
                        </p>
                        <div class="uploaded-imgs layui-upload-drag">
                        </div>
                        <div class="layui-upload">
                            <button type="button" class="layui-btn layui-btn-small layui-btn-normal" id="upload_list"><i
                                    class="layui-icon">&#xe681;</i>选择多文件
                            </button>
                            <div class="layui-upload-list">
                                <table class="layui-table">
                                    <thead>
                                    <tr>
                                        <th>文件名</th>
                                        <th>大小</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="upload_pic_list"></tbody>
                                </table>
                            </div>
                            <button type="button" class="layui-btn layui-btn-small layui-btn-warm" id="upload_list_action">
                                <i class="layui-icon">&#xe650;</i>开始上传
                            </button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" id="goods-detail">
                    <label class="layui-form-label pt4">详情描述</label>
                    <div class="layui-input-block">
                        <input type="radio" name="set" value="0" lay-filter="set" title="不设置" checked="">
                        <input type="radio" name="set" value="1" lay-filter="set" title="设置">
                    </div>
                    <div class="layui-upload-drag single" style="display: none;margin-left: 80px;" id="details-image">
                        <i class="layui-icon"></i>
                        <p>点击上传，或将文件拖拽到此处</p>
                        <img alt="图片详情" src="" style="display: none;width: 200px;"/>
                    </div>
                </div>
                <div class="layui-form-item" id="goods-limitBuy">
                    <label class="layui-form-label pt4">是否限购</label>
                    <div class="layui-input-block">
                        <input type="radio" name="limitBuy" value="0" title="不限购" checked="">
                        <input type="radio" name="limitBuy" value="1" title="限购">
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                    <legend><i class="layui-icon">&#xe756;</i>物流信息</legend>
                </fieldset>
                <div class="layui-form-item">
                    <div class="layui-inline db">
                        <label class="layui-form-label">所在地</label>
                        <div class="layui-input-inline wauto">
                            <p class="mt8">
                            <span class="area-show">
	                            <b>中国</b>
	                            &nbsp;&nbsp;
	                            <i class="layui-icon">&#xe65b;&nbsp;</i>
	                        </span>
                            <span class="area-show province-item">
	                            <b></b>
	                            <i class="layui-icon">&nbsp;&#xe65b;&nbsp;</i>
                            </span>
                            <span class="area-show city-item">
                            	<b></b>
                            	<i class="layui-icon">&nbsp;&#xe65b;&nbsp;</i>
                            </span>
                            <span class="area-show area-item"><b></b></span>
                           	<u class="edit-area">编辑</u>
                            </p>
                        </div>
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                    <legend><i class="layui-icon">&#xe756;</i>售后信息</legend>
                </fieldset>
                <div class="layui-form-item" id="goods-invoice">
                    <label class="layui-form-label pt4">发票</label>
                    <div class="layui-input-block">
                        <input type="radio" name="invoice" value="0" title="无" checked="">
                        <input type="radio" name="invoice" value="1" title="有">
                    </div>
                </div>
                <div class="layui-form-item" id="goods-warranty">
                    <label class="layui-form-label">保修</label>
                    <div class="layui-input-block">
                        <input type="radio" name="warranty" value="0" title="无" checked="">
                        <input type="radio" name="warranty" value="1" title="有">
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                    <legend><i class="layui-icon">&#xe756;</i>上架设置</legend>
                </fieldset>
                <div class="layui-form-item" id="goods-status">
                    <label class="layui-form-label">上架时间</label>
                    <div class="layui-input-block">
                        <input type="radio" name="status" value="1" title="暂不上架，" checked="">
                        <a href="javascript:;" class="putaway-tips">放到<i>我的商品</i>中</a><br>
                        <input type="radio" name="status" value="2" title="立即上架">
                        <a href="javascript:;" class="putaway-tips spec">(上架后商品类目，商品属性以及商品名称将不能再修改，情确认已经填写无误)</a>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button id="submit_form" class="layui-btn" lay-submit="" lay-filter="submit_form">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="popup" style="display: none;">
        <div class="layui-field-box">
            <div id="area_show"></div>
            <div id="area_data_hide" style="display: none;">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <div class="area-change-set">
                <button id="area_change" class="layui-btn layui-btn-small layui-bg-blue">确认修改</button>
            </div>
        </div>
    </div>
    <div id="popup_f" style="display: none;">
        <form class="layui-form" action="">
            <span style="display: none;" class="set-items-data">选择类别</span>
            <div class="layui-form-item set-items" lay-filter="item_select"></div>
        </form>
    </div>
</div>
<script src='/static/js/shop/goods-edit.js?v=1.0.0'></script>
<!-- <script src='/static/js/shop/area_data.js'></script> -->