/**
 *  course => {visitingDate:xxxx,courseType:xx,remark:xxxx,imgs:[xxxx,xxxx,....],id:xx}
 */
function Course(visitingDate, courseType, remark, imgs, id){
	return {
		visitingDate: visitingDate,
		courseType: courseType,
		remark: remark,
		imgs: imgs,
		id: id,
		order: 0
	};
}

var courseControl = {
	courses: new Array(),
	courseTypeValue: ["首诊","复诊","出院","晚期"],
	add: function(course) {
		if(this.rules(course)) {
			this.courses.push(course);
			return true;
		}
		return false;
	},
	remove: function(index) {
		this.courses[index] = null;
//		var courses_new = new Array();
//		for(var i = 0; i < this.courses.length; i++) {
//			if(i != index) {
//				courses_new.push(this.courses[i]);
//			}
//		}
//		this.courses = courses_new;
	},
	insert: function(index, course) {
		this.courses[index] = course;
	},
	cover: function(index, course) {
		if(!this.rules(course)) {
			return false;
		}
		this.courses[index] = course;
		return true;
	},/*
	getCourses: function() {
		return courses;
	},*/
	rules: function(course) {
		if(this.courses.length > 0) {
			for(var i = 0; i < this.courses.length; i++) {
				var courseTypeName = this.courseTypeValue[course.courseType - 1];
				var courseTypeNameTmp = this.courseTypeValue[this.courses[i].courseType - 1];
				var visitingDate = course.visitingDate;

				// 复诊可以多次添加
				if(course.courseType != 2) {
					if(course.courseType == this.courses[i].courseType) {
						layer.msg(courseTypeName + "已经存在，请重新选择病程类型");
						return false;
					}
				}
				if(course.courseType < this.courses[i].courseType) {
					// 之前
					if(new Date(visitingDate).isAfter(new Date(this.courses[i].visitingDate))) {
						layer.msg("请确保" + courseTypeName + "日期在" + courseTypeNameTmp + "之前");
						return false;
					}
				} else if (course.courseType == this.courses[i].courseType) {
					// 复诊可以并存
				} else {
					// 之后
					if(new Date(this.courses[i].visitingDate).isAfter(new Date(visitingDate))) {
						layer.msg("请确保" + courseTypeName + "日期在" + courseTypeNameTmp + "之后");
						return false;
					}
				}
			}
		}
		return true;
	},
	sort: function() {
		this.courses.sort(function(a, b) {
			
			if(a.courseType == 2 && b.courseType == 2) {
				return new Date(a.visitingDate).isAfter(new Date(b.visitingDate));
			} else {
				return a.courseType - b.courseType;
			}
			
		});
		/** 排序结束后为course添加order序号 */
		for(var i = 0; i < this.courses.length; i++) {
			this.courses[i].order = i;
		}
		
	},
	getCourseByCourseType: function(courseType) {
		if(isNaN(courseType)) {
			return false;
		}
		for(var i = 0; i < this.courses.length; i++) {
			if(this.courses[i].courseType == courseType) {
				return this.courses[i];
			}
		}
		return false;
	},
	getCourseByIndex: function(index) {
		return this.courses[index];
	},
	getCourseByOrder: function(order) {
		return this.courses[order];
	},
	getCourseIndexByCourse: function(course) {
		for(var i = 0; i < this.courses.length; i++) {
			if(course.courseType == this.courses[i].courseType) {
				return i;
			}
		}
		return -1;
	},
	getCourseTypeNameByNumber: function(courseType) {
		return this.courseTypeValue[courseType - 1];
	},
	appendToPage: function($root) {
		$root.empty();
		

		for(var i = 0; i < this.courses.length; i ++) {
			var $li = jQuery('<li></li>');
			$li.attr("data-id",this.courses[i].id);
			var $div_left = jQuery('<div class="left_contain"></div>');
			var $div_left_wrap = jQuery('<div class="left_wrap" style="font-size: 16px;"></div>');
			var $div_courseType = jQuery('<div></div>');
			var $div_time = jQuery('<div class="time">'+this.courses[i].visitingDate+'</div>');
			$div_left_wrap.append($div_courseType);
			$div_left_wrap.append($div_time);
			$div_left.append($div_left_wrap);
			
			var $div_right = jQuery('<div class="right_contain" style="width: 91%; left: 92px"></div>');
			var $div_outCircle = jQuery('<div class="out_circle"></div>');
			var $div_container = jQuery('<div class="border_contain"></div>');
			var $div_remark = jQuery('<div class="remark"><div style="overflow:hidden;"><div style="float:left">备注:</div><div style="float:right;"><span class="courseEditSpan">编辑</span><span class="courseDeleteSpan">删除</span></div></div><p style="font-size: 15px; word-break: break-word;">'+this.courses[i].remark+'</p></div>');
			var $div_healthImg = jQuery('<div class="health_image_contain" style="padding: 15px"></div>');
			var $ul = jQuery('<ul></ul>');
			var $li_img = jQuery('<li></li>');
			
			/** 这里把图片添加到ul标签中 */
			for(var j = 0; j < this.courses[i].imgs.length; j++) {
				if(typeof(this.courses[i].imgs[j]) != 'undefined') {
					var $img = jQuery('<img name="img'+(j + 1)+'" src="'+this.courses[i].imgs[j]+'" />');
					$li_img.append($img);
				}
			}
			$ul.append($li_img);
			$div_healthImg.append($ul);
			$div_container.append($div_remark);
			$div_container.append($div_healthImg);
			$div_right.append($div_outCircle);
			$div_right.append($div_container);
			
			var $inputHiddenWithTypeNumber = jQuery('<input type="hidden" name="case_typeNumber" value="'+this.courses[i].courseType+'" />');
			var $inputHiddenWithOrder = jQuery('<input type="hidden" name="case_order" value="'+this.courses[i].order+'" />');
			
			$li.append($div_left);
			$li.append($div_right);
			$li.append($inputHiddenWithTypeNumber);
			$li.append($inputHiddenWithOrder);
			jQuery("div.record>div.record_default").not(":hidden").hide();
			$root.append($li);
		}
		$root.addClass("info_list");
		/** 将typeNumber转换为页面内容 */
		typeNumberUpdateHtml();
		avoidRemoveLiEmptyUl();
		/** 重新定义页面高度 */
		reDefinedPageHeight();
		
		function typeNumberUpdateHtml() {
			var colorValue = ["#48C858", "#F50", "#48C858", "RED"];
			var $typeNumber = jQuery("input[name='case_typeNumber']");
			
			jQuery.each($typeNumber, function(index) {
				var $this = jQuery(this);
				var value = parseInt($this.val());
				
				var $li = $this.parents("li");
				
				var $textFiled = $li.find("div.left_wrap").children("div:eq(0)");
				$textFiled.text(courseControl.getCourseTypeNameByNumber(value));
				
				var $outCircle = $li.find("div.out_circle");
				$outCircle.css({"background-color":colorValue[value - 1]});
			});
		}
		
		/**
		 * 避免移除li的同时将ul的内容全部清空了，需要将隐藏着的div展示出来
		 */
		function avoidRemoveLiEmptyUl() {
			var $ul = jQuery("ul.timeline");
			if($ul.children("li").length == 0) {
				jQuery("div.record_default:hidden").show();
			}
		}
	}
};

function Img(index, src) {
	return {
		index: index,
		src: src
	};
} 

var imgControl = {
	imgs: new Array(),
	MAX_IMG_NUMBER: 3,
	remove: function(index) {
		var imgs_new = new Array();
		for(var i = 0; i < this.imgs.length; i++) {
			if(i != index) {
				imgs_new.push(this.imgs[i]);
			}
		}
		this.imgs = imgs_new;
	},
	removeImgs: function(indexs) {
		var imgs_new = new Array();
		for(var i = 0; i < indexs.length; i++) {
			this.imgs[i] = null;
		}
		for(var i = 0; i < this.imgs.length; i++) {
			if(this.imgs[i] != null) {
				imgs_new.push(this.imgs[i]);
			}
		}
		this.imgs = imgs_new;
	},
	deleteWithIndex: function(index) {
		this.imgs[index] = null;
		var imgs_new = new Array();
		for(var i = 0; i < this.imgs.length; i++) {
			if(this.imgs[i] != null) {
				imgs_new[i] = this.imgs[i];
			}
		}
		this.imgs = imgs_new;
	},
	update: function(index, img) {
		if(this.rules(img)) {
			this.imgs[index] = img;
		}
	},
	pushWithMinIndex: function(img) {
		var minIndex = -1;
		var i = 0;
		while(minIndex = -1) {
			if(this.imgs[i] == undefined) {
				minIndex = i;
				break;
			}
			i++;
		}
		if((i+1) > this.MAX_IMG_NUMBER ) {
			layer.msg("图片数量不能超过"+this.MAX_IMG_NUMBER);
			return false;
		}
		this.imgs[minIndex] = img;
		return true;
	},
	rules: function(sources) {
		/*
		// 判断图片类型
		if(!/image\/\w+/.test(img.type)) {
			layer.msg("请确保上传文件类型为图片");
			return false;
		}
		*/
		if(sources.length <= 0) {
			return false;
		}
		// 判断图片大小
		for(var i = 0; i < sources.length; i++) {
			if(sources[i].size > (1024*1024)) {
				layer.msg("图片大小请控制在1MB以内");
				return false;
			}
			if(sources[i].size <= 0) {
				layer.msg("图片大小不能小于0KB");
				return false;
			}
		}
		// 判断图片数量
		if(this.imgsLengthWithoutUndefined() + sources.length > this.MAX_IMG_NUMBER) {
			layer.msg("图片数量不能超过"+this.MAX_IMG_NUMBER+"张");
			return false;
		}
		return true;
	},
	deleteImgs: function(Imgs) {
		/*var src = $obj.attr("src");
		var index = $obj.attr("name").substring(3) - 1;*/
		var data = new Array();
		for(var i = 0; i < Imgs.length; i++) {
			data.push(Imgs[i].src);
		}
		if(data.length > 0) {
			/*jQuery.ajax({
				async: true,
				cache: false,
				type: 'POST',
				url: 'recordHealthFileControl.do?deleteImg',
				data: {srcs: data},
				beforeSend: function() {
					layer.load();
				},
				success: function(result) {
					loginCheck(result);
					layer.msg(result.msg);
					if(result.success) {
						var indexs = [];
						for(var i = 0; i < Imgs.length; i ++) {
							indexs.push(Imgs[i].index);
						}
						imgControl.removeImgs(indexs);
						imgControl.printImg();
					}
				},
				error: function(e) {
					console.log(e);
				},
				complete: function() {
					layer.closeAll("loading");
				}
			});*/
			var indexs = [];
			for(var i = 0; i < Imgs.length; i ++) {
				indexs.push(Imgs[i].index);
			}
			imgControl.removeImgs(indexs);
			imgControl.printImg();
		}
	},
	uploadImg: function(source) {
		if(this.rules(source)) {
			var url = 'recordHealthFileControl.do?uploadImgs';
			fileUpload({
				id: files,
				url: url,
				limit: String(1024*200*3),
				mutiple: true,
				error: function(errorCode) {
					switch(errorCode) {
					case 0:
						layer.msg("不支持上传的类型");
						break;
					case 1:
						layer.msg("超过了指定大小");
						break;
					}
				},
				success: function() {
					layer.msg("上传成功");
				},
				fail: function() {
					layer.msg("上传失败");
				},
				progress: function(evt) {
					console.log(evt + "%");
				},
				callback: function(result) {
					var obj = result.obj;
					for(var i = 0; i < obj.length; i++) {
						var img = obj[i];
						// 将图片存储到imgs中
						imgControl.pushWithMinIndex(img);
					}
					imgControl.printImg();
					/** 重新添加一个file类型的input标签 */
					var $file_old = jQuery("[name='files']");
					var $file_new = jQuery("<input name='files' id='files' type='file' class='file caseFile' multiple />");
					$file_old.after($file_new);
					$file_old.remove();
				}
			});
		}
	},
	uploadTips: function() {
		jQuery("div.upload>span").text("您还可以上传"+(this.MAX_IMG_NUMBER - this.imgsLengthWithoutUndefined())+"张图片");
	},
	printImg: function() {
		var $root = jQuery("div.tmpDiv");
		$root.empty();
		for(var i = 0; i < this.imgs.length; i++) {
			if(typeof(this.imgs[i]) != 'undefined') {
				var $div = jQuery('<div class="imgDivBlock"></div>');
				var $img = jQuery('<img name="img'+(i+1)+'" class="tmpImg" src="'+this.imgs[i]+'">');
				var $deleteDiv = jQuery('<div class="delete"><span></span></div>');
				$div.append($img).append($deleteDiv);
				$root.append($div);
			}
		}
		this.uploadTips();
	},
	clearImg: function() {
		this.imgs = new Array();
		this.printImg();
	},
	reset: function() {
		this.imgs = new Array();
	},
	imgsLengthWithoutUndefined: function() {
		var length = 0;
		for(var i = 0; i < this.imgs.length; i++) {
			if(typeof(this.imgs[i]) != 'undefined') {
				length ++;
			}
		}
		return length;
	},
	
};

var timelineControl = {
	$lis : new Array(),
	storage: function($root) {
		this.$lis = new Array();
		var $lis = $root.children("li");
		
		jQuery.each($lis, function(index) {
			timelineControl.$lis.push($lis[index]);
		});
	},
	remove: function(index) {
		var $lis_new = new Array();
		for(var i = 0; i < this.$lis.length; i++) {
			if(i != index) {
				$lis_new.push(this.$lis[i]);
			}
		}
		this.$lis = $lis_new;
	},
	sort: function(isASC, $root) {
		$root.empty();
		if(isASC) {
			for(var i = this.$lis.length; i >= 0; i--) {
				$root.append(this.$lis[i]);
			}
		} else {
			for(var i = 0; i < this.$lis.length; i++) {
				$root.append(this.$lis[i]);
			}
		}
	}
};
