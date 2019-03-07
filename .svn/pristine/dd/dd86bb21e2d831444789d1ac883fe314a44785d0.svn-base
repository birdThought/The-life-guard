(function set(layui, $) {
//angular.module("Controllers",[]).controller('goodsEditController',['$scope','$rootScope', function ($scope,$rootScope) {
//		$scope.init = function() {
			layui.config({
				base: '/static/js/shop/goods-edit-modules/' //模块存放的目录
			}).use(["layer", "form", "upload", "picker", "interact", "inputTags", "laytpl"], function () {
		        var layer = layui.layer;
		        var form = layui.form;
		        var upload = layui.upload;
		        var picker = layui.picker;
		        var interact = layui.interact;
		        var inputTags = layui.inputTags;
		        var laytpl = layui.laytpl;
		        const maxSpecNum = 5, maxChooseNum = 4;
		        function Page(param, paramF, paramS) {
		            this.param = param;
		            this.paramF = paramF;
		            this.paramS = paramS;
		            this.message = null;
		            this.goods = {
		            		skus:[], 
		            		skus_ns:[],
		            		willRemoveAttrs: ''
		            };
		        }
		
		        Page.prototype = {
		            init() {
		            	console.log('debugger');
		            	this.init_data();
		                this.add_attr();
		                this.area_set();
		                this.edit_area();
		                this.upload();
		                this.specification();
		                this.set_details();
		                this.input_tags();
		                this.validate_input($('div.must-number-validate input'));
		                this.submit();
		            },
		            init_data() {
		            	var type = $('#outest-div').attr("type");
		            	if(type == 'add'){
		            		this.add_init();
		            		this.change_items();
		            	} else {
		            		$('.content-header > h1').text('修改商品');
		            		this.update_init(parseInt($('#outest-div').attr("goods-id")));
		            	}
		            },
		            add_init(){
		            	var _this = this;
		            	 var init_url = '/commodity/goods/addInit';
		                 http.ajax.get(false, false, init_url, null, http.ajax.CONTENT_TYPE_1, function (result) {
		                 	if(result.success){
		                 		_this.data = result.obj;
		                 		_this.goods.goods = {}
		                 		_this.goods.specs = _this.data.fixSpecs;
		                 		_this.laytpl_renders();
		                 		form.render();
		                 	}
		                 });
		            },
		            update_init(goodsId){
		            	var _this = this;
		            	 var init_url = '/commodity/goods/getGoodsById';
		                 http.ajax.get(false, false, init_url, {goodsId:goodsId}, http.ajax.CONTENT_TYPE_1, function (result) {
		                 	if(result.success){
		                 		_this.data = result.obj;
		                 		_this.goods.goods = {id:goodsId}
		                 		_this.echoed();
		                 	}
		                 });
		            },
		            echoed() { // 回显
		            	var _this = this;
		            	// 类目
		            	$('.select-items span:nth-child(1)').html(_this.data.goods.category.cName);
	                    $('.select-items span:nth-child(1)').attr('idpath', _this.data.goods.category.idPath);
	                    this.param.attr('disabled', true);
	                    this.param.parent().next().text('修改页面不能修改类目');
	                    
		            	_this.data.attrs = _this.data.attributes;
	             		_this.laypl_render_attrs();
	             		var labelIds = _this.data.goods.labelIds.split(',');
	             		for(var lb = 0; lb < _this.data.labels.length; lb++){
	             			if(labelIds.indexOf(_this.data.labels[lb].id + '') >= 0){
	             				_this.data.labels[lb].checked = true;
	             			}
	             		}
	             		_this.laypl_render_labels();
	             		
	             		// 商品名称
	             		$('div.layui-form-item').find('input[name="goodsName"]').val(_this.data.goods.goodsName);
	             		// 规格类型
	             		$('#goods-specType div.radio').children('input[value=' + _this.data.goods.specType + ']').prop('checked',true);
	             		_this.data.fixSpecs = _this.data.specs;
	             		_this.goods.specs = _this.data.specs;
	             		_this.laypl_render_specs();
	             		if (_this.data.goods.specType == 1) {
	                        $('.specification-box').slideUp('fast')
	                        $('.single-spec-attr').show('normal');
	                        $.each($('div.single-spec-attr').find('input'), function(){
	                        	$(this).val(_this.data.goods[$(this).attr('name')]);
	                        });
	                    } else if (_this.data.goods.specType == 2) {
	                    	 $('.specification-box').slideDown('fast')
	                         $('.single-spec-attr').css({display: 'none'});
	                    }
	             		_this.echoed_skus();
	             		
	             		// 图片
	             		if(_this.data.goods.mainPic && _this.data.goods.mainPic != ''){
	             			$('#upload_drap').children('img').show('normal');
	                		$('#upload_drap').children(':not(img)').css({display: 'none'});
	                		$('#upload_drap').children('img').attr('src', _this.data.goods.mainPic);
	             		}
	             		if(_this.data.goods.otherPics && _this.data.goods.otherPics != ''){
	             			let arr = _this.data.goods.otherPics.split('|');
	             			for(var p = 0; p < arr.length;p++){
	             				_this.append_img(arr[p], p);
	             			}
	             		}
	             		// 详情
	             		var isDetails = (typeof(_this.data.goods.details) != 'undefined' && _this.data.goods.details != '')?1:0;
	             		$('#goods-detail input[value="' + isDetails + '"]').prop('checked',true);
	             		if(isDetails == 1) {
	             			$('#details-image').show('normal');
	             			$('#details-image img').show('normal');
	             			$('#details-image').children(':not(img)').css({display: 'none'});
	             			$('#details-image img').attr('src', _this.data.goods.details);
		            	}
	             		// 限购
	             		$('#goods-limitBuy input[value="' + _this.data.goods.limitBuy + '"]').prop('checked',true);
	             		// 物流信息
	             		
	             		// 发票
	             		$('#goods-invoice input[value="' + _this.data.goods.invoice + '"]').prop('checked',true);
	             		// 保修
	             		$('#goods-warranty input[value="' + _this.data.goods.warranty + '"]').prop('checked',true);
	             		form.render();
	             		_this.add_attr_value();
	             		_this.remove_attr();
		            },
		            echoed_skus(){ //价格&规格
		            	var _this = this;
		            	this.goods.skus = this.data.skus;
//		            	console.log(this.goods.goods)
		            	if(_this.data.goods.specType == 1 || this.goods.skus == null || this.goods.skus.length == 0){
		            		this.goods.skus = [];
		            		return;
		            	}
		            	/* set sku.sort start... */
		            	var idx = -1;
		            	this.goods.specs = this.data.fixSpecs;
		            	var exps = '', sort = '[', end = '';
		            	for(var s = 0; s < this.goods.specs.length; s++){
		            		var spec = this.goods.specs[s];
		            		if(spec.usedNum > 0){
		            			exps += 'for(var s' + s + ' = 0 ; s' + s + ' < this.goods.specs[' + s + '].specValues.length; s' + s + '++){\n';
		            			exps += 'if(!this.goods.specs[' + s + '].specValues[s' + s + '].used){continue;}\n'
		            			sort += 's' + s + ',';
		            			end += '}\n';
		            		}
		            	}
		            	exps += 'idx++;\n';
		            	exps += 'this.goods.skus[idx].sort = ' + sort.substr(0,sort.length - 1) + '];\n';
		            	exps += end;
		            	eval(exps);
		            	/* set sku.sort end... */
		            	
		            	this.goods.skus_ns = [];// be used spec names
		            	for(var s = 0 ; s < this.goods.specs.length; s++){
		            		if(this.goods.specs[s].usedNum > 0){
		            			this.goods.skus_ns.push(this.goods.specs[s].name);
		            		}
		            	}
		            	
		            	for(var k = 0 ; k < this.goods.skus.length; k++){
		            		var vs = [];
		            		var nv = this.goods.skus[k].groupSpec.split(',');
		            		for(var n = 0 ; n < nv.length; n++) {
		            			var vname = nv[n].substring(nv[n].indexOf(':') + 1, nv[n].length);
		            			vs.push(vname);
		            		}
		            		this.goods.skus[k].vs = vs; // be used spec values
		            	}
		            	
		            	var rowspan = 1; // 表格合并单元格的rowspan
		            	for(var s = this.goods.specs.length - 1 ; s >= 0; s--){
		            		if(this.goods.specs[s].usedNum > 0){
		            			this.goods.specs[s].rowspan = rowspan;
		            			rowspan = this.goods.specs[s].usedNum * rowspan;
		            		}
		            	}
		            	if(this.goods.skus.length > 0){
		                	$('.standard-area').css({'display': 'block'});
		                	$('p.standard-tips').css({'display': 'none'});
		                } else {
		                	$('.standard-area').css({'display': 'none'});
		                	$('p.standard-tips').css({'display': 'block'});
		                	return;
		                }
		            	this.gen_table(); // 生成skus表格
		            },
		            laytpl_renders() { 
		            	this.laypl_render_labels();
		            	this.laypl_render_specs();
		            },
		            laypl_render_specs(){ // 规格渲染
		            	var fixSpecs_laytpl = document.getElementById('fixSpecs-laytpl').innerHTML;
		            	laytpl(fixSpecs_laytpl).render(this.data.fixSpecs, function(html){
		            		$('#specs-view').prepend(html);
		            	});
		            },
		            laypl_render_labels(){ // 分组渲染
		            	var labels_laytpl = document.getElementById('labels-laytpl').innerHTML;
		            	laytpl(labels_laytpl).render(this.data.labels, function(html){
		            		$('#labels-view').html(html);
		            	});
		            },
		            laypl_render_attrs(){ // 属性渲染
		            	var _this = this;
		            	var attrs_tpl = document.getElementById('attrs-laytpl').innerHTML;
	            		laytpl(attrs_tpl).render(_this.data.attrs, function(html){
	            			$('#attrs-view').prepend(html);
	            			$('#new-attr').attr('length', _this.data.attrs.length);
	            		});
		            },
		            specNameIndexOf(name) {
		            	for(var i = 0; i < this.goods.specs.length; i++){
		            		if(this.goods.specs[i].name == name){
		            			return i;
		            		}
		            	}
		            	return -1;
		            },
		            input_tags() {
		            	var _this = this;
		                inputTags.render({
		                    elem: '#inputTags',
		                    content: [],
		                    aldaBtn: true,
		                    done: function (value) {
		                    }
		                });
		                $('.add-specification').click(function () {
		                	if(_this.goods.specs.length == maxSpecNum){
		                    	layer.msg('现版本最多添加' + maxSpecNum + '种规格');
		                    	return;
		                    }
		                	inputTags.Class.config.content = [];
		                    $('.add-specification').css({'display': 'none'});
		                    $('#multi_name').css({'display': 'block'});
		                    $('#multi_name').attr('state', 'add');
		                });
		                // 取消添加
		                $('#confirm_add_cancel').click(function(e) {
		                	e.preventDefault();
		                	$('#input_tag_name').val('');
		                    $('#tags span').remove();
		                	$('.add-specification').css({'display': 'block'});
		                    $('#multi_name').css({'display': 'none'});
						});
		                $('#confirm_add_finish').click(function (e) {
		                    e.preventDefault();
		                    var name = $('#input_tag_name').val();
		                    if(name == null || (name = name.trim()) == ''){
		                    	layer.msg('请输入规格名');
		                    	return;
		                    } 
		                    var spans = $('#tags span');
		                    if(spans.length < 1){
		                    	layer.msg('请至少添加一个规格值');
		                    	return;
		                    }
		                    var state = $('#multi_name').attr('state');
		                    if('add' == state) { // 添加
		                    	if(_this.specNameIndexOf(name) == 1) {
		                        	layer.msg('已存在此规格');
		                        	return;
		                        }
		                    	_this.add_spec(name, spans);
		                    	_this.add_attr();
		                    } else { // 修改
		                    	var index = parseInt(state);
		                    	var spec = _this.goods.specs[index];
		                    	if(spec.usedNum > 0) {
			                    	for(let s = 0; s < spec.specValues.length; s++){
			                    		if(spec.specValues[s].used) {
			                    			_this.cross(index, s);
			                    		}
			                    	}
			                    	_this.change_rowspan(index);
			                    	_this.gen_table(); // 生成skus表格
		                    	}
		                    	
		                    	spec.name = name;
		                    	spec.usedNum = 0;
		                    	spec.rowspan = 1;
		                    	spec.specValues = [];
		                    	var specDiv = $('div.specs-list-' + index);
		                    	specDiv.find('.name-multi').text(spec.name);
		                    	var divValues = specDiv.find('div.spec-values');
		                    	divValues.empty();
		                    	$.each(spans, function (index, val) {
		                    		var vname = $(this).find('em').text();
		                    		var value = {used:false, name:vname}
		                    		spec.specValues.push(value);
		                    		var inputStr = '<input type="checkbox" value="' + index
		                    			+ '" name="infinite" lay-skin="primary" lay-filter="inputTags" title="'
		                    			+ vname + '">';
		                    		divValues.append(inputStr);
		                    	});
		                    	
		                    }
		                    inputTags.Class.config.content = [];
		                    // 清空输入框
		                    $('#input_tag_name').val('');
		                    $('#tags span').remove();
		                    
		                    $('.add-specification').css({'display': 'block'});
		                    $('#multi_name').css({'display': 'none'});
		                    form.render();
		                });
		
		            },
		            add_spec(name, spans) {
		            	var _this = this;
		            	var newSpec = null;
		                var values = [];
		                $.each(spans, function (index, val) {
		                	var value = {used:false, name:$(this).find('em').text()}
		                	values.push(value);
		                    return values;
		                });
		                newSpec = {
		                		name:name,
		                		usedNum:0,
		                    	rowspan:1,
		                		specValues:values
		                }
		                _this.goods.specs.push(newSpec);
		                
		                var inputStr = '';
		                inputStr += '<div class="layui-form-item specs-list-' + (_this.goods.specs.length - 1) + '">';
		                inputStr += '<div class="attr-select pl20">';
		                inputStr += '<p class="multi-specification-title" value="'
		        						+ (_this.goods.specs.length - 1) + '"><u class="name-multi">' 
		        						+ newSpec.name 
		                		+ '</u><span class="edit-multi">&nbsp;编辑</span>'
		                		+ '<span class="delete-multi">删除</span></p>';
		                inputStr += '<div class="CheckBox spec-values">';
		                for (var i = 0; i < newSpec.specValues.length; i++) {
		                    inputStr += '<input type="checkbox" value="' + i
		                    		+ '" name="infinite" lay-skin="primary" lay-filter="inputTags" title="'
		                    		+ newSpec.specValues[i].name + '">';
		                }
		                inputStr += '</div></div></div>';
		                $('.none').css({display: 'none'})
		                $('.shop-attr-first-child').append(inputStr);
		            },
		            change_items() {
		                var _this = this;
		                _this.message = '';
		                this.param.click(function () {
		                    interact.render({
		                        elem: '.set-items',
		                        title: '选择类别',
		                        data: _this.data.allCategory,
		                        name: 'region1',
		                        hint: ['请在此选择类别名称', '请在此选择类别名称', '请在此选择类别名称', '请在此选择类别名称']
		                    });
		                    var str = '';
		                    interact.on('interact(item_select)', function (data) {
		                        _this.message = data.text;
		                        _this.idPath = data.elem.attr('idpath');
		                    });
		                    str = _this.message + "<span class='select-tips'>&nbsp;(商品上架后不可修改，请谨慎选择)</span>";
		                    layer.open({
		                        type: 1,
		                        title: str,
		                        area: ['800px', '400px'],
		                        skin: 'layui-layer-lan',
		                        shadeClose: true,
		                        content: $('#popup_f'),
		                        closeBtn: 1,
		                        btn:['确定选择','取消'],
		                        yes:function () {
		                            $('.select-items span:nth-child(1)').html(_this.message)
		                            $('.select-items span:nth-child(1)').attr('idpath', _this.idPath)
		                            var idpath = _this.idPath;
		                            if(idpath && idpath.length > 0) {
			    	                	idpath = idpath.split(',');
			    	                    var categoryId = idpath[idpath.length - 2];
			    	                    var changeAttrs_url = '/commodity/goods/changeAttrs';
			    	                    http.ajax.get(true, false, changeAttrs_url, {categoryId: categoryId}, http.ajax.CONTENT_TYPE_1, function (result) {
			    	                    	if(result.success){
			    	                    		$('#attrs-view').children('div.old-attributes').remove();
			    	                    		_this.data.attrs = result.obj.attrs;
			    	                    		_this.laypl_render_attrs();
			    	                    		form.render();
			    	                    		_this.add_attr_value();
			    	                    		_this.remove_attr();
			    	                    	}
			    	                    });
		                            }
		                            layer.closeAll();
		                        },
		                        btn2:function () {
		                            layer.msg('cancel')
		                        }
		                    })
		                })
		            },
		            add_attr() {
		            	var _this = this;
		                this.paramF.click(function () {
		                    $('#add_attr').css({display: 'none'});
		                    $('.add-attr-box').show('normal');
		                });
		                $('.edit-multi').click(function (e) {
		                    e.preventDefault();
		                    var index = parseInt($(this).parent().attr('value'));
		                    var spec = _this.goods.specs[index];
		                    $('#input_tag_name').val(spec.name);
		                    inputTags.Class.config.content = [];
		                    for(var i = 0; i < spec.specValues.length; i++){
		                    	inputTags.Class.addSpecValue(spec.specValues[i].name);
		                    }
		                    
		                    $('.add-specification').css({'display': 'none'});
		                    $('#multi_name').css({'display': 'block'});
		                    $('#multi_name').attr('state', index);
		                })
		                $('.delete-multi').click(function (e) {
		                    e.preventDefault();
		                    var $del = $(this);
		                    layer.confirm('您确定要删除？', {
		                        btn: ['确定', '取消'] //按钮
		                    }, function () {
		                    	var index = parseInt($del.parent().attr('value'));
		                    	let spec = _this.goods.specs[index];
		                    	if(spec.usedNum > 0) {
			                    	for(let s = 0; s < spec.specValues.length; s++){
			                    		if(spec.specValues[s].used) {
			                    			_this.cross(index, s);
			                    		}
			                    	}
			                    	_this.change_rowspan(index);
			                    	_this.gen_table();// 生成skus表格
		                    	}
		                    	_this.goods.specs.splice(index, 1);
		                        var parents = $del.parents('.specs-list-' + index);
		                        if (parents) {
		                            parents.empty();
		                            layer.closeAll()
		                        }
		                    });
		                })
		            },
		            edit_area() {
		                this.paramS.click(function () {
		                    layer.open({
		                        type: 1,
		                        title: "编辑地区",
		                        skin: 'layui-layer-lan',
		                        area: ['600px', '160px'],
		                        shadeClose: true,
		                        closeBtn: 1,
		                        content: $('#popup'),
		                        success: function () {
		                        }
		                    })
		
		                })
		            },
		            area_set() {
		            	var _this = this;
		                let Set = new picker();
		                var code = 110101;
		                if(_this.data.goods && typeof(_this.data.goods.areaCode) != 'undefined'){
		                	code = _this.data.goods.areaCode;
		                }
		                Set.set({
		                    elem: '#area_show',
		                    data: Areas,
		                    codeConfig: {
		                        code: code,
		                        type: 3
		                    }
		                }).render();
		                $('#area_change').click(function () {
		                    var area_data_f = $('#area_data_hide li:nth-child(1)').text();
		                    var area_data_s = $('#area_data_hide li:nth-child(2)').text();
		                    var area_data_t = $('#area_data_hide li:nth-child(3)').text();
		                    var code = $('#area_data_hide li:nth-child(3)').attr('code');
		                    if (area_data_f == '') {
		                        layer.msg('请选择省份')
		                    } else if (area_data_s == '') {
		                        layer.msg('请选择城市')
		                    } else if (area_data_t == '') {
		                        layer.msg('请选择地区')
		                    } else {
		                        layer.msg('选择成功');
		                        layer.closeAll();
		                        $('.province-item b').text(area_data_f);
		                        $('.city-item b').text(area_data_s);
		                        $('.area-item b').text(area_data_t);
		                        $('.area-item b').attr('code', code);
		                    }
		                })
		            },
		            set_details() {
		            	form.on('radio(set)', function (data) {
		                    let value = data.value;
		                    if (value == 1) {
		                        $('#details-image').show('normal');
		                    } else if (value == 0) {
		                        $('#details-image').css({display: 'none'});
		                    }
		                });
		            },
		            upload() {
		            	var _this = this;
		                upload.render({
		                    elem: '.layui-upload-drag.single',
		                    url: '/combo/uploadFile/img',
		                    auto: true,
		                    multiple: false,
		                    before: function () {
		                    	var type = 'cut';
		                    	console.log($(this.item));
		                    	if($(this.item).attr('id') == 'details-image'){ // 如果是商品详情不压缩图片
		                    		type = 'original'; //原图
		                    	}
		                    	this.data = {type: type}
		                        layer.load(2)
		                    },
		                    done: function (data) {
		                    	if(data.success) {
		                    		$(this.item).children('img').attr('src', data.obj);
		                    		$(this.item).children('img').attr('upload-state', 'success');
		                    		$(this.item).children('img').show('normal');
		                    		$(this.item).children(':not(img)').css({display: 'none'});
		                    	} else {
		                    		$(this.item).children('img').attr('upload-state', 'failed');
		                    		$(this.item).children('img').css({display: 'none'});
		                    		$(this.item).children(':not(img)').show('normal');
		                    		layer.msg(data.msg);
		                    	}
		                    	layer.closeAll('loading');
		                    },
		                    error: function () {
		                        layer.closeAll('loading');
		                    }
		                });
		                var upload_view = $('#upload_pic_list')
		                    , uploadListIns = upload.render({
		                    elem: '#upload_list'
		                    , url: '/combo/uploadFile/img'
		                    , accept: 'file'
		                    , multiple: true
		                    , auto: false
		                    , bindAction: '#upload_list_action'
		                    , choose: function (obj) {
		                        var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		                        //读取本地文件
		                        obj.preview(function (index, file, result) {
		                            var tr = $(['<tr id="upload-' + index + '">'
		                                , '<td><span>' + file.name + '</span>'
		                                 + '</td>'
		                                , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
		                                , '<td>等待上传</td>'
		                                , '<td>'
		                                , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
		                                , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
		                                , '</td>'
		                                , '</tr>'].join(''));
		
		                            //单个重传
		                            tr.find('.demo-reload').on('click', function () {
		                                obj.upload(index, file);
		                            });
		
		                            //删除
		                            tr.find('.demo-delete').on('click', function () {
		                                delete files[index]; //删除对应的文件
		                                tr.remove();
		                                uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		                            });
		
		                            upload_view.append(tr);
		                        });
		                    }
		                    , done: function (res, index, upload) {
		                    	var tr = upload_view.find('tr#upload-' + index)
		                    	, tds = tr.children();
		                        if (res.success) { //上传成功
		                            _this.append_img(res.obj, index);
		                            tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		                            tds.eq(3).html(''); //清空操作
		                            return delete this.files[index]; //删除文件队列已经上传成功的文件
		                        }
		                        img.css({display: 'none'});
		                        img.attr('upload-state', 'failed');
		                        this.error(index, upload, res.msg);
		                    }
		                    , error: function (index, upload, msg) {
		                        var tr = upload_view.find('tr#upload-' + index)
		                            , tds = tr.children();
		                        tds.eq(2).html('<span style="color: #FF5722;">' + msg + '</span>');
		                        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		                    }
		                });
		            },
		            append_img(src, index){
	                	var span = $('<span title="双击删除" file-index="' + index + '">'
	                		+ '<img  src="' + src + '" alt="" />'
	                		+ '</span>');
	                	$('#other-pictures div.uploaded-imgs').append(span);
	                	span.dblclick(function(){ // 双击删除
	                		$(this).remove();
	                		$('#upload_pic_list tr#upload-' + $(this).attr('file-index')).remove();
	                	});
	                },
	                validate_input(inputs){
	                	inputs.on('input',function(e){
                        	let val = $(this).val();
                        	if(isNaN(val)){
                        		$(this).val('');
                        		layer.msg('只能是数字');
                        	}
                        });
	                },
		            specification() {
		                var _this = this;
		                form.on('radio(specification)', function (data) {
		                    let value = data.value;
		                    if (value == 1) {
		                        $('.specification-box').slideUp('fast')
		                        $('.single-spec-attr').show('normal');
		                    } else if (value == 2) {
		                        $('.specification-box').slideDown('fast')
		                        $('.single-spec-attr').css({display: 'none'});
		                    }
		                });
		                form.on('checkbox(inputTags)', function (data) {
		                    var elem = data.elem;
		                    var n_index = parseInt($(elem).parent().siblings('p.multi-specification-title').attr('value'));
		                    var v_index = parseInt($(elem).attr('value'));
		                    if(elem.checked){ // 选中事件
		                    	if(_this.goods.skus_ns.length == maxChooseNum){
		                    		layer.msg('现版本最多选择' + maxChooseNum + '种规格');
		                        	return;
		                    	}
		                    	_this.checked(n_index, v_index);
		                    } else { // 取消选中
		                    	_this.cross(n_index, v_index);
		                    }
		                    _this.change_rowspan(n_index);
		                    _this.gen_table();
		                })
		            },
		            change_rowspan(n_index) {
		            	var _this = this;
		            	if(_this.goods.skus_ns.length > 0){
		                	$('.standard-area').css({'display': 'block'});
		                	$('p.standard-tips').css({'display': 'none'});
		                } else {
		                	$('.standard-area').css({'display': 'none'});
		                	$('p.standard-tips').css({'display': 'block'});
		                	return;
		                }
		                
		                // rowspan && _this.goods.specs[i].usedNum > 0
		        		for(var i = n_index; i >= 0; i--) {
		        			var rowspan = 1;
		        			for(var j = i + 1; j < _this.goods.specs.length ; j++) {
		        				if(_this.goods.specs[j].usedNum > 0) {
		        					rowspan = rowspan * _this.goods.specs[j].usedNum;
		        				}
		        			}
		        			_this.goods.specs[i].rowspan = rowspan;
		        		}
		                
		            },
		            gen_table(){ // 生成skus表格
		            	var _this = this;
		            	var sort_index = [];
		                var rowspan_arr = [];
		                for(var i = 0; i < _this.goods.specs.length; i++){
		                	if(_this.goods.specs[i].usedNum < 1){
		                		continue;
		                	}
		                	var num = _this.goods.skus_ns.indexOf(_this.goods.specs[i].name);
							sort_index.push(num);
		                	rowspan_arr.push(_this.goods.specs[i].rowspan);
		                }
		                // 生成表格
		                var tbody = $('#skus-table').children('tbody');
		                // 标题skus-title
		                tbody.children('tr.skus-title').children('th.table_cell_spec_title').remove();
		                var title = '';
		                for(var si = 0; si < sort_index.length; si++){
		                	title += '<th class="table_cell_t table_cell_spec_title">' + _this.goods.skus_ns[sort_index[si]] + '</th>'
		                }
		                tbody.children('tr.skus-title').prepend(title);
		                
		                var skus_data_tr = tbody.children('tr.skus-data');
		                if(skus_data_tr){
		                	skus_data_tr.remove();
		                }
		                let skus = _this.goods.skus;
		                
		            	for(let j = 0; j < skus.length; j++){
							var cur_sku = skus[j];
								
							var td = '', groupSpec = '';
							for(var si = 0; si < sort_index.length; si++){
								groupSpec += '$' + _this.goods.skus_ns[sort_index[si]] + ':' + cur_sku.vs[sort_index[si]] + ',';
								if(rowspan_arr[si] == 1 || (j + 1) % rowspan_arr[si] == 1){
									td +=  '<td class="table_cell_data" rowspan="' + rowspan_arr[si] + '"><span class="">'
									+ 	cur_sku.vs[sort_index[si]] + '</span>'
									+ '</td>';
								}
								
							}
							var tr = '<tr class="skus-data" groupSpec="' + groupSpec.substr(0, groupSpec.length - 1)
									+ '" sku-index="' + j + '">';
							tr += td;
							var placeholder = '';
							if(j == 0){
								placeholder = '回车实现数据同步';
							}
							// 市场价
							tr += '<td class="table_cell_data must-number-validate">'
								+ 	'<span>'
								+ 		'<input name="marketPrice" placeholder="' + placeholder + '" type="text" value="' + (cur_sku.marketPrice || '') + '"/>'
								+ 	'</span>'
								+ '</td>';
							// 优惠价
							tr += '<td class="table_cell_data must-number-validate">'
								+ 	'<span>'
								+ 		'<input name="favorablePrice" placeholder="' + placeholder + '" type="text" value="' + (cur_sku.favorablePrice || '') + '"/>'
								+ 	'</span>'
								+ '</td>';
							// 库存
							tr += '<td class="table_cell_data must-number-validate">'
								+ 	'<span>'
								+ 		'<input name="inventory" placeholder="' + placeholder + '" type="text" value="' + (cur_sku.inventory || '') + '"/>'
								+ 	'</span>'
								+ '</td>';
							// 条码
							tr += '<td class="table_cell_data">'
								+ 	'<span>'
								+ 		'<input name="braCode" placeholder="' + placeholder + '" type="text" value="' + (cur_sku.braCode || '') + '"/>'
								+ 	'</span>'
								+ '</td>'; 
							tr += '</tr>';
							tbody.append(tr)
		            	}
		            	_this.auto_input();
		            	_this.validate_input($('td.must-number-validate input'));
		            },
		            auto_input() {
		            	var _this = this;
		            	$('tr[sku-index="0"].skus-data input').bind('keypress', function(event) {
		            		if(event.keyCode == "13"){
		            			event.preventDefault()
		            			var value = $(this).val();
		            			if(value && value != '') {
		            				$('tr.skus-data').find('input[name="' + $(this).attr('name') + '"]').val(value);
		            			}
		            		}
		            	});
		            },
		            merge_array(prev, next) { // 合并两个数组
		            	for(let i=0;i < next.length;i++) {
		            		prev.push(next[i]);
		        	    }
		        		return prev;
		            },
		            insertSort(old, New, compare){ // 插入排序sku
		            	for(var n = 0 ; n < New.length; n++){
		            		var flag = false;
		            		for(var o = old.length - 1; o >= 0; o--){
		            			if(compare(old[o], New[n]) == 1){
		            				if(flag) {
		            					old[o + 1] = old[o];
		            					old[o] = New[n];
		            				} else {
		            					old.splice(o, 0, New[n]);
		            				}
		            				
		            				flag = true;
		            			} else {
		            				break;
		            			}
		            		}
		            		if(!flag){
		            			old.push(New[n]);
		            		}
		            	}
		            },
		            cross(ni, vi) { // checked = false
		            	var _this = this;
		            	var cur_spec = _this.goods.specs[ni];
		            	cur_spec.usedNum--;
		            	cur_spec.specValues[vi].used = false;
		            	var n = _this.goods.skus_ns.indexOf(cur_spec.name); // 应该存放的位置,规格和规格值下标对应
		        		if(cur_spec.usedNum > 0) { // 如果还有此规格的某个值被选中
		        			var len = _this.goods.skus.length;
		        			// 删除所有与此规格值有关的sku
		        			while(--len >= 0) {
		        				let sku = _this.goods.skus[len];
		        				if(n != -1 && sku.vs[n] == cur_spec.specValues[vi].name){
		        					_this.goods.skus.splice(len, 1);
		        				}
		        			}
		        		} else { // 如果已经没有此规格的某个值被选中,则缩短
		        			_this.goods.skus_ns.splice(n, 1); //
		        			for(let b = _this.goods.skus.length - 1; b >= 0; b--) {
		        				let sku = _this.goods.skus[b];
		        				if(_this.goods.skus_ns.length == 0) { // 
		        					_this.goods.skus.splice(b, 1);
		        				} else {
		        					sku.vs.splice(n, 1);
		        					sku.sort.splice(n, 1);
		        				}
		        			}
		        		}
		        		return n;
		            },
		            checked(ni, vi) { // 选中
		            	var _this = this;
		            	var cur_spec = _this.goods.specs[ni];
		            	cur_spec.usedNum++;
		            	cur_spec.specValues[vi].used = true;
		            	var n = _this.goods.skus_ns.indexOf(cur_spec.name); // 应该存放的位置,规格和规格值下标对应
		            	if(_this.goods.skus.length < 1) {//第1个选中
		            		_this.goods.skus_ns.push(cur_spec.name); // 记录选中的规格
		        			var sku = {
		        					vs: [cur_spec.specValues[vi].name],
		        					sort: [vi]
		        				}
		        			_this.goods.skus.push(sku);
		        		} else { 
		        			if(cur_spec.usedNum > 1) { // 已选过此规格的某个值
		        				let vn = null;
		        				for(let a = 0; a < cur_spec.specValues.length; a++) {
		        					if(cur_spec.specValues[a].used && a != vi) {
		        						vn = cur_spec.specValues[a].name; // 随便找'1'个选中的规格值
		        						break;
		        					}
		        				}
		        				var newSkus = [];
		        				// 复制部分,比例: 1/此规格中选中的规格值数量
		        				for(let b = 0; b < _this.goods.skus.length; b++) {
		        					let sku = _this.goods.skus[b];
		        					if(n != -1 && sku.vs[n] == vn){
		        						var copy_sku_vs = [];
		        						var copy_sku_sort = [];
		        						_this.merge_array(copy_sku_vs, sku.vs);// 合并数组
		        						_this.merge_array(copy_sku_sort, sku.sort);
		        						copy_sku_vs[n] = cur_spec.specValues[vi].name; // 替换
		        						copy_sku_sort[n] = vi;
		        						var newSku = {vs:copy_sku_vs,sort: copy_sku_sort}
		        						newSkus.push(newSku);
		        					}
		        				}
		        				// 排序
		        				var sort_index = [];
		        				for(var i = 0; i < _this.goods.specs.length; i++){
		        					if(_this.goods.specs[i].usedNum < 1){
		                        		continue;
		                        	}
		        					var num = _this.goods.skus_ns.indexOf(_this.goods.specs[i].name);
		        					sort_index.push(num);
		        				}
		        				_this.goods.sort_index = sort_index;
		        				
		        				//插入排序
		        				_this.insertSort(_this.goods.skus, newSkus, function(o, n){
		        					for(var si = 0; si < _this.goods.sort_index.length; si++){
		    		    				var a = o.sort[_this.goods.sort_index[si]], b = n.sort[_this.goods.sort_index[si]];
		    		            		if(a > b){
		    		            			return 1;
		    		            		} else if(a < b) {
		    		            			return -1;
		    		            		} else {
		    		            			continue;
		    		            		}
		    		            	}
		    		            	return -1;
		        				});
		        			} else { // 没有选中过此规格
		        				_this.goods.skus_ns.push(cur_spec.name); // 记录选中的规格
		        				// 在所有sku_vs后面追加此规格值
		        				for(let b = 0; b < _this.goods.skus.length; b++) {
		        					let sku = _this.goods.skus[b];
		        					sku.vs.push(cur_spec.specValues[vi].name);
		        					sku.sort.push(vi);
		        				}
		        			}
		        		}
		            	return n;
		            },
		            set_goods_data() {
		            	var _this = this;
		            	// 类目
		                var idpath = $('.select-items span:nth-child(1)').attr('idpath');
		                if(idpath && idpath.length > 0) {
		                	idpath = idpath.split(',');
		                    _this.goods.goods.categoryId = idpath[idpath.length - 2];
		                } else {
		                	return '请选择类目';
		                }
		                
		                // 属性
		                // 1.从后台获取的属性 
		                var $attrs = $('#attr-list').find('div.old-attributes');
		            	var relations = [];
		                $.each($attrs, function () {
		                	var select_val = $(this).find('dd.layui-this');
		                	if(select_val.length && select_val.length > 0) {
		                    	var rela = {
		                    		attributeId: parseInt($(this).find('select').attr('attr-id')),
		                    		attributeVId: parseInt(select_val.attr('lay-value')) // 选中的值
		                    	}
		                    	relations.push(rela);
		                	}
		                });
		                _this.goods.relations = relations;
		                // 2.新添加的属性
		                var newAttrs = [];
		                $.each($('div.new-attributes'), function () {
		                	var lab = $(this).children('label.layui-form-label').clone();
		                	lab.find(':nth-child(n)').remove();
		                	let name = lab.text();
//		                	console.log(name);
		                	var attr = {
		                		name: name,
		                		attributeValues: []
		                	}
		                	
		                	$.each($(this).find('dd'), function(){
		                		if($(this).attr('lay-value') && !($(this).attr('lay-value') == '-1' && $(this).text() == '添加选项') ){
		                			var attributeValue = {
		                				name:$(this).text()
		                			}
		                			attributeValue.selected = ($(this).attr('class') == 'layui-this')?1:0;
		                			attr.attributeValues.push(attributeValue);
		                		}
		                	});
		                	newAttrs.push(attr);
		                })
		                _this.goods.attributes = newAttrs;
		                // 商品分组
		                var labelIds = '';
		                $.each($('#goods-labelIds').find('div.layui-form-checked'), function(){
		                	labelIds += $(this).prev().val() + ',';
		                })
		                if(labelIds != ''){
		                	labelIds = labelIds.substr(0, labelIds.length - 1);
			                _this.goods.goods.labelIds = labelIds;
		                } else {
		                	return '至少选择一个分组';
		                }
		                
		                // 商品名称
		                var goodsName = $('div.layui-form-item').find('input[name="goodsName"]').val();
		                _this.goods.goods.goodsName = goodsName;
		                if(typeof(goodsName) == 'undefined' || goodsName == ''){
		                	return '请输入商品名称';
		                }
		                // 规格类型
		                var specType = $('#goods-specType').find('input:checked').val();
		                _this.goods.goods.specType = specType;
		                // 价格库存
		                if(specType == 1){ //统一规格
		                	var marketPrice = $('div.single-spec-attr').find('input[name="marketPrice"]').val(); // 市场价
		                	_this.goods.goods.marketPrice = marketPrice;
		                	if(typeof(marketPrice) == 'undefined' || marketPrice == ''){
		                		return '请输入市场价';
		                	}
		                	var favorablePrice = $('div.single-spec-attr').find('input[name="favorablePrice"]').val(); //优惠价
		                	_this.goods.goods.favorablePrice = favorablePrice;
		                	var inventory = $('div.single-spec-attr').find('input[name="inventory"]').val(); // 库存
		                	_this.goods.goods.inventory = inventory;
		                	var braCode = $('div.single-spec-attr').find('input[name="braCode"]').val(); // 条码
		                	_this.goods.goods.braCode = braCode;
		                	_this.goods.skus = null; // 统一规格不保存skus
		                	var specs = []; // 统一规格不保存新添加的规格
		                	specs.push(_this.goods.specs[0]);
		                	specs[0].usedNum = 0;
		                	specs[0].rowspan = 1;
		                	for(var s = 0; s < specs[0].specValues.length; s++){
		                		specs[0].specValues[s].used = false;
		                	}
		                	_this.goods.specs = specs;
		                }
		                
		                else if(specType == 2){
		                	$.each($('#skus-table tr.skus-data'), function(){
		                		var index = parseInt($(this).attr("sku-index"));
		                		var sku = _this.goods.skus[index];
		                		sku.groupSpec = $(this).attr('groupSpec');
		                		$.each($(this).find('input'), function(){
		                			sku[$(this).attr('name')] = $(this).val();
		                		})
		                	});
		                	for(let s = 0; s < _this.goods.skus.length; s++){
		                		if(typeof(_this.goods.skus[s].marketPrice) == 'undefined' || _this.goods.skus[s].marketPrice == ''){
		                			return '市场价不能为空';
		                		}
		                	}
		                }
		                // 图片
		                // 1 主图
		                var mainPic = '', mainImg = $('#upload_drap').children('img');
		                if(mainImg.attr('upload-state') == 'success') {
		                	mainPic = mainImg.attr('src');
		                }
		                _this.goods.goods.mainPic = mainPic;
		                if(mainPic == ''){
		                	return '请上传主图';
		                }
		                // 2 其它图片
		                var otherPics = '', otherImgs = $('#other-pictures div.uploaded-imgs').find('img');
		                $.each(otherImgs, function(){
		                	otherPics += $(this).attr('src') + '|';
		                });
		                _this.goods.goods.otherPics = otherPics.substr(0, otherPics.length - 1);
		                // 详情
		                var set = $('#goods-detail').find('input:checked').val();
		                var details = null;
		                if(set == 1){
		                	details = $("#details-image > img").attr('src');
		                	_this.goods.goods.details = details;
		                }
		                // 限购
		                var limitBuy = $('#goods-limitBuy').find('input:checked').val();
		                _this.goods.goods.limitBuy = limitBuy;
		                
		                // 物流信息-所在地
		                var areaCode = $('.area-show.area-item b').attr('code');
		                _this.goods.goods.areaCode = areaCode;
		                if(typeof(areaCode) == 'undefined' || areaCode == ''){
		                	return '请选择物流所在地';
		                }
		                // 发票 
		                var invoice = $('#goods-invoice').find('input:checked').val();
		                _this.goods.goods.invoice = invoice;
		                // 保修 
		                var warranty = $('#goods-warranty').find('input:checked').val();
		                _this.goods.goods.warranty = warranty;
		                // 上架状态
		                var status = $('#goods-status').find('input:checked').val();
		                _this.goods.goods.status = status;
		                
		                return null;
		            },
		            submit(){
		                var _this = this;
		                $('#submit_form').click(function (e) {
		                    e.preventDefault();
		                    var msg = _this.set_goods_data();
	                    	if(msg != null){ // 是否通过验证
	                    		layer.msg(msg);
	                    		return;
	                    	}
		                    layer.confirm("确定提交吗?", {icon: 3, title:'提示'}, function(index) {
		                    	layer.close(index);
		                    	 // 提交
		                    	var type = $('#outest-div').attr("type");
		                        var url = '/commodity/goods/';
		                        if('add' == type){ // 添加或修改
		                        	url += 'doAdd';
		                        } else {
		                        	url += 'doUpdate';
		                        }
		                        if(_this.goods.willRemoveAttrs != ''){
		                        	_this.goods.willRemoveAttrs = _this.goods.willRemoveAttrs.substr(0, _this.goods.willRemoveAttrs.length - 1);
		                        }
//		                        console.log(_this.goods);
		                        http.ajax.post(true, false, url, JSON.stringify(_this.goods), http.ajax.CONTENT_TYPE_2, function (result) {
		                			if(result.success){
		                				layer.msg('已保存');
		                				history.back(); 
		                			} else {
		                				if(_this.goods.willRemoveAttrs != ''){
		                					_this.goods.willRemoveAttrs = _this.goods.willRemoveAttrs + ',';
		                				}
		                			}
		                    	});
		                    });
		                    
		                });
		                $('#confirm_add').click(function (e) {
		                    e.preventDefault();
		                    var val = $('#add_attr_name').val();
		                    var value = $('#add_attr_val').val();
		                    if(val==''){
		                        layer.tips('必填项！', '#add_attr_name', {
		                            tips: [1, '#459eff'],
		                            time: 1500
		                        });
		                        return;
		                    }
		                    if(value==''){
		                        layer.tips('必填项！', '#add_attr_val', {
		                            tips: [1, '#459eff'],
		                            time: 1500
		                        });
		                        return;
		                    }
		                    // 添加属性
		                    var len = parseInt($('#new-attr').attr('length')) + 1;
		                    var c1 = c2 = '';
		                    var c3 = 'specially';
		                    if(len % 4 == 1){
		                    	c1 = 'spec';
		                    	c2 = 'specially';
		                    	c3 = 'spec';
		                    }
		                    var newAttr = '<div class="layui-form-item new-attributes dib ' + c1 + '">'
		                    		+ 	'<label class="layui-form-label ' + c2 + '">' + val 
		                    		+  '<span style="color:blue;" class="delete-attribute fresh">&nbsp;&nbsp;删除</span>'
		                    		+ '</label>'
		                    		+ 	'<div class="layui-input-block ' + c3 + '">'
		                    		+ 		'<select name="interest" class="new-attr" lay-filter="attrs-select" attr-index="' + len +'">'
		                    		+ 			'<option value=""></option>'
		                    		+ 			'<option value="' + value + '">' + value + '</option>'
		                    		+			'<option value="-1">添加选项</option>'
		                    		+ 		'</select>'
		                    		+ 	'</div>'
		                    		+ '</div>';
		                    $('#new-attr').before(newAttr);
		                    form.render();
		                    _this.remove_attr();
		                    _this.add_attr_value();
		                    $('#new-attr').attr('length', len);
		                    $('.add-attr-box').css({'display':'none'})
		                    $('#add_attr').css({'display':'block'})
		                });
		                $('#confirm_cancel').click(function (e) {
		                    e.preventDefault();
		                    $('.add-attr-box').css({'display':'none'})
		                    $('#add_attr').css({'display':'block'})
		                });
		            },
		            remove_attr(){
		            	var _this = this;
		            	$('span.delete-attribute.old,span.delete-attribute.fresh').off("click").click(function(){
//		            		layer.confirm("确定删除吗?", {icon: 3, title:'提示'}, function(index) {
//			            		if($(this).hasClass('old')){
//			            			_this.goods.willRemoveAttrs.push($(this).attr('attr-id'))
//			            		}
//			            		$(this).parent().parent().remove();
//			            		layer.close(index);
//		            		});
//		            		console.log('delete');
		            		if($(this).hasClass('old')){
		            			_this.goods.willRemoveAttrs += $(this).attr('attr-id') + ',';
		            			layer.msg('提交商品后才会真正删除',{
		            				  icon: 1,
		            				  time: 1500 //（如果不配置，默认是3秒）
		            			});
		            		} 
		            		$(this).parent().parent().remove();
		            	});
		            },
		            add_attr_value() {
		            	var _this = this;
		            	form.on('select(attrs-select)', function(data){
	            			if(data.elem.selectedIndex == data.elem.length - 1){
	            				var custom = $(data.elem[data.elem.selectedIndex]);
//	            				console.log($(data.elem).attr('attr-index'))
	            				custom.remove();
	            				layer.open({
	            					title: false,
	            					btn:[],
	            					cancel: function(){ 
	            						$(data.elem).append('<option value="-1" class="add-option">添加选项</option>');
	            						form.render('select');
	            					},
	            					content: '<div id="custom_attr_block">'
	                                  	+ '<p><input style="padding:4px 0 4px 0;" placeholder="输入属性值" type="text" class="custom_attr_value"/>'
	                                  	+'<span class="confirm-add-attr-value" style="display:inline-block;margin-left:3px;padding:2px 6px 2px 6px;background-color:#9DCAB5;border-radius:4px;">确定</span></p>'
	                                  	+ '</div>'
	            					}); 
	            				$('span.confirm-add-attr-value').attr('attr-index', $(data.elem).attr('attr-index'));
	            				_this.confirm_add_attr_value();
	            				form.render('select');
	            			}
	            		});
		            },
		            confirm_add_attr_value(){
		            	$('span.confirm-add-attr-value').off("click").click(function(){
		            		let value = $(this).prev().val();
		            		if(typeof(value) != 'undefined' && value != ''){
		            			var option = '<option value="' + value + '">' + value + '</option>';
		            			let select = $('#attrs-view select[attr-index="' + $(this).attr('attr-index') + '"]');
		            			if(select.attr('class') == 'new-attr'){
		            			} else {
		            				var attr_id = select.attr('attr-id');
		            				var attrValue = {attributeId: attr_id, name: value};
		            				var url = '/commodity/goods/addAttrValue';
		            				http.ajax.post(false, false, url, JSON.stringify(attrValue), http.ajax.CONTENT_TYPE_2, function (result) {
			                			if(result.success){
			                				option = '<option value="' + result.obj.id + '">' + result.obj.name + '</option>';
			                			}
			                    	});
		            			}
		            			select.append(option);
		            			select.append('<option value="-1" class="add-option">添加选项</option>');
		            			form.render('select');
		            			layer.closeAll();
		            		} else {
		            			layer.msg('不能为空',{
		            				  icon: 1,
		            				  time: 1000 //（如果不配置，默认是3秒）
		            			});
		            		}
		            	});
		            },
		            regix(a) {
		                return /(\x0f[^\x0f]+)\x0f[\s\S]*\1/.test("\x0f" + a.join("\x0f\x0f") + "\x0f");
		            }
		        };
		        let change_items = $('#change_items');
		        let add_attr = $('#add_attr');
		        let edit_area = $('.edit-area');
		        let page = new Page(change_items, add_attr, edit_area);
		        page.init();
			})
//		}
})(layui, jQuery)
//}]);