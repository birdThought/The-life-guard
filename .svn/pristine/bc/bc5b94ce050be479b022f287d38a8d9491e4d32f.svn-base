initJeDate();

jQuery(function(){
	$('li .addTest').on('click',function(){
		layer.open({
			type: 1,
			title:['添加体检','text-align:center;font-size:16px;background:#fff;'],
			area:'520px',
			btn:['确定','取消'],
			zIndex: 9999,
			scrolling:'no',
			scrollbar:false,
  			content: $('.reportPop'), //这里content是一个DOM 	
			success : function() {
				cleanHtml();
				picture.aTips();
			},
  			yes:function(index){
  				var isSuccess = physical.addReport();
  				if(!isSuccess){
  					return ;
  				}
  				layer.close(index);
  			}
		});
	});
	
	//为文件域绑定change事件
	$('.upload').on('change', $('#pictures') , function(){		
		picture.upload();
	});
	//清空图片
	$('.upload').children().eq(1).click(function(){
		picture.cleanPicture();
	});
});
/*
 * 静态模板
 */
var reportModel = {
		model: null,
		getModel: function(){
			if (this.model == null) {
				this.model = new Vue({
					el: '.reportDetail',
					data: {
						results: null,
						curPage: 1,
						totalSize: null,
						totalPage: null,
					},
					methods: {
						//绑定"编辑体检"
						editReport: function (e, id) {
							physical.getReport(id, this.curPage);
						},
						//绑定"删除体检"
						deleteReport: function (e, id) {
							if (this.totalSize % 5 == 1) {
								physical.deleteReport(id, this.curPage -1);
							} else {
								physical.deleteReport(id, this.curPage);
							}
							
						},
						//判断是否有图片
						isImg: function (value) {
							if (value == undefined || value == '') {
								return false;
							}
							return true;
						},
						popurImg: function () {
							$('.image-container img').bigic();
						}
					},
					computed: {
						
					}
				});
			}
			return this.model;
		}
}

/*
 * 分页工具
 */
var pageUtil = {
		reportPage: null,
		getPageUtil: function () {
			if (this.reportPage == null) {
				this.reportPage = new PageUtil();
				this.reportPage.getPageControl().init({
					container: 'physicalPage',
					preBtn: 'pre_rec',
					nextBtn: 'next_rec',
					totalPage: reportModel.getModel().totalPage,
					pageChange: function(page) {
						physical.getReportsDate(page);
					}
				});
			}
			this.reportPage.getPageControl().totalPage = reportModel.getModel().totalPage;
			this.reportPage.getPageControl().selectPage(reportModel.getModel().curPage, true);
			
		}
}

var physical = {
	getReport : function(id, curPage) {
		$.ajax({
			async : true,
			cache : false,
			type: 'GET',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: 'physicalControl.do?getPhysicalReport',
			data: {"reportId": id},
			dataType: 'json',
			success: function(result){
				if(result.success){	
					layer.open({
						type: 1,
						title:['编辑体检','text-align:center;font-size:16px;background:#fff;'],
						area:'520px',
						btn:['确定','取消'],
						scrolling:'no',
						zIndex: 9999,
						content: $(".reportPop"),
						success : function(){
							fillHtml(result);
							picture.aTips();
						},
			  			yes:function(index){
			  				physical.updateReport(result.obj.id, curPage);
			  				layer.close(index);
			  			}
					});	
				} else {
					layer.msg('体检报告不存在,请刷新页面');
				}
			}
		});
	},
	addReport : function() {
		var url = 'physicalControl.do?addPhysicalReport';
		//初始化参数
		var param = new Parameter();
		//如果检验失败，直接返回
		if(!param.valid()){
			return false;
		}
		
		$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			url : url,
			data : {
				'physicalsDate' : param.physicalsDate,
				'physicalsOrg' : param.physicalsOrg,
				'description' : param.description,
				'img1' : param.img1,
				'img2' : param.img2,
				'img3' : param.img3
			},
			dataType : 'json',
			success : function(result) {
				layer.msg(result.msg);
				if(result.success){
					window.location.href = "physicalControl.do?enterPhysicalReport";
				}		
			}
		});
		
	},
	updateReport : function(reportId, curPage) {
		var url = 'physicalControl.do?updatePhysicalReport';
		//初始化参数
		var param = new Parameter();
		//如果检验失败，直接返回
		if(!param.valid()){
			return false;
		}
		
		$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
			url: url,
			data: {
				id : reportId,
				physicalsDate : param.physicalsDate,
				physicalsOrg :	param.physicalsOrg,
				description : param.description,
				img1 : param.img1,
				img2 : param.img2,
				img3 : param.img3
			},
			dataType: 'json',
			success: function(result){
				layer.msg(result.msg);
				if(result.success){
				/*	window.location.href = 'physicalControl.do?enterPhysicalReport';*/
					physical.getReportsDate(curPage);
				}
			}
			
		});
	},
	deleteReport : function(_id, curPage) {
		/*var _id = url.substring(url.lastIndexOf("=")+1);*/
		layer.open({
			type: 1,
			title:['删除体检报告','text-align:center;font-size:16px;background:#fff;'],
			area: '520px',
			btn:['确定','取消'],
			scrolling:'no',
			zIndex: 9999,
			content: $('.deleteTip'),
  			yes:function(index){
  				jQuery.ajax({
  					type: 'POST',
  					data: 'reportId='+_id/*+'&curPage='+curPage+'&pageSize='+pageSize*/,
  					url: 'physicalControl.do?deletePhysicalReport',
  					beforeSend: function() {
  						layer.load(2);
  					},
  					success: function(result) {
  						layer.msg(result.msg);
  						if(result.success) {
  							physical.getReportsDate(curPage);
  						}
  					},
  					complete: function() {
  						layer.closeAll("loading");
  						layer.closeAll('page');
  					}
  				});
  			}
		});
	},
	getReportsDate: function(page, physicalsDate, title) {
		var url = 'physicalControl.do?getPhysicalReportByPage&nowPage='+page+'&pageSize=5';
		if(physicalsDate != null) {
			url += '&physicalsDate='+physicalsDate;
		}
		if(title != null) {
			url += '&title='+title;
		}
		
		jQuery.ajax({
			type: 'GET',
			url: url,
			beforeSend: function() {
				layer.load(2);
			},
			success: function(result) {
				/*var obj = result.obj;*/
				/* 模板赋值 (自动更新界面数据)*/
				reportModel.getModel().results = result.obj.data;
				reportModel.getModel().curPage = result.obj.nowPage;
				reportModel.getModel().totalSize = result.obj.totalSize;
				reportModel.getModel().totalPage = result.obj.totalPage;
				pageUtil.getPageUtil();
			},
			complete: function() {
				layer.closeAll("loading");
			}
		});
	},
	
};


var picture = {
	Max_File_Number : 3,
	//计算上传文件总数
	calculateTotalQuantity : function() {
		var numberOfExisting = $('.imgSet').children().length;
		var numberOfNew = $('#pictures').prop('files').length;
		var totalQuantity = numberOfExisting + numberOfNew;
		return totalQuantity;
	},
	isExceedMaxNumber : function() {
		var totalQuantity = this.calculateTotalQuantity();
		if(totalQuantity > this.Max_File_Number){
			return true;
		}
		return false;
	},
	upload : function() {
		if(this.isExceedMaxNumber()){
			layer.msg("只能上传3张图片!");
			return ;
		}
		var url = 'physicalControl.do?uploadPhysicalPhoto';
		
		if(jQuery("#pictures").length > 0) {
			fileUpload({
				id : pictures,//对应的input的id
				url : url,//上传的url
				limit : 1024,//最大上传大小的限制,以KB为单位（可选），默认为图片类型
				mutiple : true,//多选，默认为单选（可选）
				error : function(result) {//result:0表示不支持上传的类型,1表示超过大小限制
					switch(result) {
					case 0:
						layer.msg("不支持上传类型");
						break;
					case 1:
						layer.msg("图片超过了指定大小(最大1M)");
						break;	
					case 2:
						layer.msg("图片大小为0Kb,不能上传");
					}
				},
				success : function() {//上传成功（可选）
					layer.msg("上传成功");
				},
				fail : function() {//上传失败（可选）
					layer.msg("上传失败");
				},
				progress : function(evt) {//上传的进度监听（可选）
					console.log(evt + "%");
				},
				callback:function(result){//可选
					var obj = result.obj;
					for(var key in obj){
						if($('#img1').val() == undefined){
							picture.generatePicture(obj[key],"img1");
						}else if($('#img2').val() == undefined){
							picture.generatePicture(obj[key],"img2");
						}else if($('#img3').val() == undefined){
							picture.generatePicture(obj[key],"img3");
						}
					}
					picture.aTips();
				}
			});
		}
		/** 重新添加一个file类型的input标签 */
		var $file_old = jQuery("#pictures");
		var $file_new = jQuery("<input type='file' class='file' name='pictures' id='pictures' multiple>");
		$file_old.after($file_new);
		$file_old.remove();
	},
	cleanPicture : function() {
		$('.imgSet').empty();
		this.aTips();
	},
	removePicture : function(obj) {
		var $obj = $(obj);
		$obj.parents("li").remove();
		this.aTips();
	},
	aTips: function() {
		var numberOfExisting = $('.imgSet').children().length;
		jQuery("span.ATips").text("您还可以上传"+(3-numberOfExisting)+"张");
	},
	generatePicture : function(src,imgId) {
		var $img = jQuery('<img src="' + src + '" id="' + imgId + '" />');
		var $removeBtn = jQuery('<div><img src="static/images/delete.jpg" onclick="picture.removePicture(this)"/></div>');
		var $image_box = jQuery('<div class="image_box"></div>');
		var $image_outside = jQuery('<div class="image_outside"></div>');
		var $li = jQuery('<li></li>');
		
		$image_box.append($img).append($removeBtn);
		$image_outside.append($image_box);
		$li.append($image_outside);
		$('.imgSet').append($li);
	}
};

//校验
function Parameter() {
	this.physicalsDate = $('#physicalsDate').val();
	this.physicalsOrg = $('#physicalsOrg').val();
	this.description =  $('#description').val();
	this.img1 = $('#img1').attr('src');
	this.img2 = $('#img2').attr('src');
	this.img3 = $('#img3').attr('src');
	if(Parameter._initialized == undefined){
		//输入参数校验
		Parameter.prototype.valid = function(){
			if(this.physicalsDate == ""){
				layer.msg("请填写体检日期");
				return false;
			};
			if(this.physicalsOrg == ""){
				layer.msg("请填写体检机构");
				return false;
			}
			if((this.description == "") && (this.img1 == undefined) && (this.img2 == undefined) && (this.img3 == undefined)){
				layer.msg("请上传至少一张图片或填写体检描述信息");
				return false;
			}
			if(this.description != "" && this.description.length > 150) {
				layer.msg("体检描述信息长度不能超过150字");
				return false;
			}
			return true;
		};
	}
	return Parameter._initialized = true;
};

function initJeDate() {
	//为日期控件设置默认值为当前日期
	jQuery('#physicalsDate').val(new Date().Format("yyyy-MM-dd"));
	//初始化JeDate控件
	jeDate({
	    dateCell: "#physicalsDate",
	    format:"YYYY-MM-DD",
	    minDate:"1900-01-01 00:00:00",
	    maxDate: jeDate.now(0),
	    zIndex: 9999999,
	    startMin:"1900-01-01 00:00:00",	//清除日期后返回到预设的最小日期
	    startMax:jeDate.now(0),
	    choosefun:function(elem, val) {
	    	//val为获取到的时间值
	    }
	});
};

var fillHtml = function(result) {
	
	var physical = result.obj;
	
	$('#physicalsDate').val(new Date(physical.physicalsDate).Format("yyyy-MM-dd"));
	$('#physicalsOrg').val(physical.physicalsOrg);
	$('#description').val(physical.description);
	$('.imgSet').empty();
	
	if(physical.img1 != null && physical.img1 != ""){
		picture.generatePicture(physical.img1,"img1");
	}
	if(physical.img2 != null && physical.img2 != ""){
		picture.generatePicture(physical.img2,"img2");
	}
	if(physical.img3 != null && physical.img3 != ""){
		picture.generatePicture(physical.img3,"img3");
	}
};

var cleanHtml = function() {
	$('#physicalsDate').val(new Date().Format("yyyy-MM-dd"));
	$('#physicalsOrg').val("");
	$('#description').val("");
	$('.imgSet').empty();
}
