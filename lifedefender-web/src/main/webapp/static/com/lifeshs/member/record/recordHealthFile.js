function selectInit() {
	var $parentSelect = jQuery("[name='parent']");
	$parentSelect.empty();
	$parentSelect.append('<option value="0">请选择</option>');
	
	jQuery.ajax({
		async: true,
		cache: false,
		type: 'GET',
		url: "recordHealthFileControl.do?getChildDepartments&parentId=0",
		success: function(result) {
			var departments = result.obj;
			for(var i = 0; i < departments.length; i++) {
				var department = departments[i];
				$parentSelect.append("<option value='"+department.id+"'>"+department.name+"</option>");
			}
		}
	});
}

function selectEventBind() {
	jQuery("[name='parent']").change(function() {
		var $childSelect = jQuery("[name='child']");
		var optionValue = jQuery(this).val();
		
		$childSelect.empty();
		$childSelect.append('<option value="0">请选择</option>');
		
		if(optionValue != 0) {
			jQuery.ajax({
				async: true,
				cache: false,
				type: 'GET',
				url: "recordHealthFileControl.do?getChildDepartments&parentId="+optionValue,
				success: function(result) {
					var departments = result.obj;
					for(var i = 0; i < departments.length; i++) {
						var department = departments[i];
						$childSelect.append("<option value='"+department.id+"'>"+department.name+"</option>");
					}
				}
			});
		}
	});
}

function userMessageInit() {
	var $table = jQuery("table.addCase");
	var $trs = $table.find("tr");
	jQuery.ajax({
		async: true,
		cache: false,
		type: 'GET',
		url: 'recordHealthFileControl.do?getUserMessage',
		success: function(result) {
			var userMessage = result.obj;
			$trs.eq(0).children("td:eq(1)").text(userMessage.name);
			$trs.eq(1).children("td:eq(1)").text(userMessage.sex);
			$trs.eq(2).children("td:eq(1)").text(userMessage.birthday);
			$trs.eq(3).children("td:eq(1)").text(userMessage.age+"岁");
		}
	});
}

function jeDateInit() {
	
	// default date for input value 
	jQuery("[name='visitingDate'], [name='visitingDate_case']").val(new Date().Format("yyyy-MM-dd"));
	
	jeDate({
	    dateCell:"[name='visitingDate'], [name='visitingDate_case']",
	    format:"YYYY-MM-DD",
	    isinitVal:true,
	    initAddVal:[0],
	    minDate:"1900-01-01",
	    maxDate: jeDate.now(0),
	    startMin:"1900-01-01",
	    startMax:jeDate.now(0),
	    zindex: 999,
	    choosefun:function(elem, val) {
	    	//val为获取到的时间值
	    }
	});
}

function addCaseEvent() {
	$('.add_btn span').on('click',function(){
		layer.open({
			type: 1,
			title:['添加病程','text-align:center;font-size:16px;background:#fff;'],
			area:['500px'],
			offset:'100px',
			btn:['确定','取消'],
			moveType: 1,
			zIndex: 888,
  			content: $('.reportPop'), //这里content是一个DOM
  			success: function() {
  				if(clearModalData) {
  					modalDataClean();
  				} else {
  					jQuery("div.layui-layer-title").html("修改病程");
  					/** 重定为默认清除模态框数据 */
  					resetClearModalData();
  				}
  				modalInputFileReset();
  			},
  			yes: function(index) {
  				
  				var visitingDate = jQuery("[name='visitingDate_case']").val();
  				var courseType = parseInt(jQuery("[name='courseType']").val());
  				var remark = jQuery("[name='remark']").val();
  				
  				if(visitingDate == "") {
  					layer.msg("病程日期不能为空");
  					jQuery("[name='visitingDate_case']").focus();
  					return ;
  				}
  				if(calculate.strLength(remark) > 200) {
  					layer.msg("字数会不会太多了啊，已经超过200啦!");
  					return ;
  				}
  				
  				/** 图片临时存储数组，用于保存在course中 */
  				var imgs = [];
  				for(var i = 0; i < filesSrc.length; i++) {
  					imgs.push(filesSrc[i]);
  				}
  				
  				/** 用于编辑 */
  				/** 将courseEditNumber对应的course抽空出来 */
  				var courseTmp = arrayGet(courses, courseEditNumber);
  				courses = arrayRemove(courses, courseEditNumber);
  				
  				/** 封装 */
  				var course = {'visitingDate':visitingDate,'courseType':courseType,'remark':remark,'imgs':imgs};
  				/** 存储 */
  				if(!courseDataPush(course)){
  					/** 用于编辑，为了避免添加的时候不通过调用该方法，设置了一个非0判断 */
  					/** 如果内容不通过，将原先的数据存储到courses中 */
  					/** 写这段抽空与重新保存的原因是，编辑内容不通过的话，需要将数据还原成原来的模样 */
  					if(courseEditNumber != 0) {
  						courseDataPush(courseTmp);
  					}
  					defaultEvent();
  					return ;
  				}
  				defaultEvent();
  				/** 关闭 */
  				layer.close(index);
  			}
		});
		
		function defaultEvent() {
			/** 排序 */
			courseDataSort(courses);
			/** 构图 */
			courseAppendToPage(courses);
			/** 排序存储 */
			dataStorage();
			/** 正常关闭需要归零courseEditNumber的值 */
			courseEditNumber = 0;
		}
	});
}

function resetClearModalData() {
	clearModalData = true;
}

var fileNumber = 0;
var filesSrc = [];

var courses = new Array();
var typeValue = ["首诊", "复诊", "出院", "晚期"];
var colorValue = ["#48C858", "#F50", "#48C858", "RED"];
/** 总图片数量限制为3 */
var MAX_FILE_LENGTH = 3;
/** 有效上传文件数量 */
var validFile = 0;
function uploadBtnEventBind() {
	fileUploadTip();
	
	jQuery('div.upload').on('change', '[name="files"]', function() {
		var filesTmp = jQuery(this)[0].files;
		
		
		if(filesTmp.length > MAX_FILE_LENGTH) {
			layer.msg("上传图片数量不能超过"+MAX_FILE_LENGTH+"张");
			return ;
		}
		
		var isAddFile = imgClickedNumber == 0 ? true : false;
		
		validFile = 0;
		for(var i= 0; i < filesTmp.length; i ++) {
			
			if(!isFileTypeValid(filesTmp[i])) {
				return ;
			}
			if(!isFileSizeValid(filesTmp[i])) {
				return ;
			}
			/** 如果是添加图片操作，需要判断图片数量 */
			if(!isFilesLengthValid(isAddFile)) {
				return ; 
			}
			/** 这里判断imgClickedNumber是因为如果本次上传图片操作是修改，不需要再修改files里面的元素 */
			if(isAddFile) {
				/** 用于文件数量(如果中途出现无效文件就会中断本次记录操作) */
				validFile ++;
			}
		}
		// 将文件数量填充到fileNumber中
		fileNumber += validFile;
		
		// 更新下tip提示
		fileUploadTip();
		/** 这里判断上传文件的有效长度 */
		if(filesTmp.length > 0) {
			fileAjaxSubmit(jQuery(this));
		}
	});
	
	jQuery("button[name='courseClear']").click(function() {
		jQuery("div.upload>div.tmpDiv").empty();
		// 清空数据
		fileNumber = 0;
		filesSrc = [];
		fileUploadTip();
	});
	
}
function isFilesLengthValid(isAddFile) {
	// 这里fileNumber的长度要加1
	// 预先判断fileNumber里面的数量
	var fileLength = fileNumber + validFile + 1;
	// 如果是修改图片，数量不需要加1
	if(!isAddFile) {
		fileLength -= 1;
	}
	
	if(fileLength > MAX_FILE_LENGTH) {
		layer.msg("上传图片数量不能超过"+MAX_FILE_LENGTH+"个");
		return false;
	}
	return true;
}

function isFileTypeValid(source) {
	// 判断文件类型与大小
	if(!/image\/\w+/.test(source.type)){
		layer.msg("请确保上传文件类型为图片");
		return false;
	}
	return true;
}

function isFileSizeValid(source) {
	if(source.size > (1024*200)){
		layer.msg("图片大小应该控制在200KB以内");
		return false;
	}
	if(source.size == 0) {
		layer.msg("图片大小不能是0KB");
		return false;
	}
	return true;
}

function fileUploadTip() {
	jQuery("div.upload>span").text("您还可以上传"+(MAX_FILE_LENGTH - fileNumber)+"张图片");
}

function fileAjaxSubmit($fileElement) {
	
	$fileElement = fileAjaxWrap($fileElement);
	$("#updateImg").ajaxSubmit({
		async: true,
		cache: false,
		dataType: 'json',
		success: function(result) {
			layer.msg(result.msg);
			if(result.success) {
				var imgs = result.obj;
				var $root = jQuery("div.upload>div.tmpDiv");
				if(imgClickedNumber == 0) {
					/** 保存到filesSrc中 */
					for(var i= 0; i < imgs.length; i++) {
						addImgSrcToFilesSrc(imgs[i]);
					}
				} else {
					addImgSrcWithIndex(imgs, imgClickedNumber-1);
				}
				/** 清空root中的图片 */
				$root.empty();
				/** 遍历filesSrc输出图片 */
				for(var i = 0; i < filesSrc.length; i++) {
					/** 以imgN的形式取值，如果不为空，就添加到root当中 */
					var $img = jQuery("<img name='img"+(i + 1)+"' class='tmpImg' src='"+filesSrc[i]+"' />");
					$root.append($img);
				}
			}
		},
		complete: function() {
			$fileElement.unwrap();
			modalInputFileReset();
		}
	});
}

function fileAjaxWrap($fileElement) {
	$fileElement.wrap('<form id="updateImg" action="recordHealthFileControl.do?uploadImgs" method="post" enctype="multipart/form-data"></form>');
	return $fileElement;
}

function addImgSrcToFilesSrc(src) {
	filesSrc.push(src);
}

function addImgSrcWithIndex(src, index) {
	filesSrc[index] = src;
}

function modalDataClean() {
	jQuery("[name='visitingDate_case']").val(new Date().Format("yyyy-MM-dd"));
	jQuery("select[name='courseType']").val(1);
	jQuery("[name='remark']").val("");
	jQuery("[name='courseClear']").click();
}

function modalInputFileReset() {
	/** 清零 */
	imgClickedNumber = 0;
	/** 重新添加一个file类型的input标签 */
	var $file_old = jQuery("[name='files']");
	var $file_new = jQuery("<input name='files' type='file' class='file caseFile' multiple />")
	$file_old.after($file_new);
	$file_old.remove();
}

function courseDataPush(course) {
	
	if(courses.length > 0) {
		if(!isSingleCourse(course)) {
			return false;
		}
		if(!isDateValid(course)) {
			return false;
		}
	}
	
	courses.push(course);
	return true;
	
	function isSingleCourse(course) {
		var courseType = course.courseType;
		var courseTypeName = getCourseTypeName(course);
		for(var i = 0; i < courses.length; i++) {
			if(courseType == courses[i].courseType) {
				layer.msg(courseTypeName+"已经存在，请重新选择病程类型");
				return false;
			}
		}
		return true;
	}
	function isDateValid(course) {
		var courseType = course.courseType;
		var courseTypeName = getCourseTypeName(course);
		var visitingDate = course.visitingDate;
		
		for(var i = 0; i < courses.length; i++) {
			if(courseType < courses[i].courseType) {
				if(new Date(visitingDate).isAfter(new Date(courses[i].visitingDate))) {
					layer.msg("请确保"+courseTypeName+"日期在"+getCourseTypeName(courses[i])+"之前"); // "已经有病程的日期在"+courseTypeName+"之前"
					return false;
				}
			} else {
				if(new Date(courses[i].visitingDate).isAfter(new Date(visitingDate))) {
					layer.msg("请确保"+courseTypeName+"日期在"+getCourseTypeName(courses[i])+"之后");
					return false;
				}
			}
		}
		return true;
	}
	function getCourseTypeName(course) {
		var courseType = parseInt(course.courseType);
		return typeValue[courseType-1];
	}
}

var imgClickedNumber = 0;

function imgEventBind() {
	
	jQuery("div.reportPop").on("click", "div.tmpDiv>img", function() {
		var $this = jQuery(this);
		var name = $this.attr("name");
		var imgNumber = parseInt(name.substring(3));
		imgClickedNumber = imgNumber;
		/** 在获取到img的编号之后，将filesSrc中的数据重新构建 */
		
		var $file = jQuery("[name='files']");
		$file.removeAttr("multiple");
		$file.click();
	});
}

function sortBtnEventBind() {
	jQuery("div.sort_button").click(function() {
		if(jQuery(this).hasClass("asc")) {
			visitingDateSort(true);
			jQuery(this).removeClass("asc");
			jQuery(this).find("span").addClass("trangleR");
			jQuery(this).find("span").removeClass("trangle");
		} else {
			visitingDateSort(false);
			jQuery(this).addClass("asc");
			jQuery(this).find("span").removeClass("trangleR");
			jQuery(this).find("span").addClass("trangle");
		}
	});
}

var li_storage = [];

function dataStorage() {
	/** 清空数据 */
	li_storage = [];
	li_storage.length = 0;
	var $lis = jQuery("ul.info_list>li");
	jQuery.each($lis, function(index) {
		var $this = jQuery(this);
		li_storage.push($this);
	});
}

function visitingDateSort(isASC) {
	
	var $root = jQuery("ul.info_list");
	$root.empty();
	
	if(isASC) {
		for(var i=li_storage.length; i >= 0; i--) {
			$root.append(li_storage[i]);
		}
		return true;
	} else {
		for(var i=0; i < li_storage.length; i++) {
			$root.append(li_storage[i]);
		}
		return true;
	}
	return false;
}

function courseDataSort(courses) {
	courses.sort(function(a, b) {
		return a.courseType - b.courseType;
	});
}

function courseAppendToPage(courses) {
	
	/** 数据清除 */
	jQuery("ul.info_list").empty();
	
	for(var i = 0; i < courses.length; i ++) {
		var $li = jQuery('<li></li>');
		
		var $div_left = jQuery('<div class="left_contain"></div>')
		var $div_left_wrap = jQuery('<div class="left_wrap" style="font-size: 16px;"></div>');
		var $div_courseType = jQuery('<div></div>');
		var $div_time = jQuery('<div class="time">'+courses[i].visitingDate+'</div>');
		$div_left_wrap.append($div_courseType);
		$div_left_wrap.append($div_time);
		$div_left.append($div_left_wrap);
		
		var $div_right = jQuery('<div class="right_contain" style="width: 91%; left: 92px"></div>');
		var $div_outCircle = jQuery('<div class="out_circle"></div>');
		var $div_container = jQuery('<div class="border_contain"></div>');
		var $div_remark = jQuery('<div class="remark"><div style="overflow:hidden;"><div style="float:left">备注:</div><div style="float:right;"><span class="courseEditSpan">编辑</span><span class="courseDeleteSpan">删除</span></div></div><p style="font-size: 15px">'+courses[i].remark+'</p></div>');
		var $div_healthImg = jQuery('<div class="health_image_contain" style="padding: 15px"></div>');
		var $ul = jQuery('<ul></ul>');
		var $li_img = jQuery('<li></li>');
		
		/** 这里把图片添加到ul标签中 */
		for(var j = 0; j < courses[i].imgs.length; j++) {
			var $img = jQuery('<img name="img'+(j + 1)+'" src="'+courses[i].imgs[j]+'" />');
			$li_img.append($img);
		}
		$div_healthImg.append($li_img);
		$div_container.append($div_remark);
		$div_container.append($div_healthImg);
		$div_right.append($div_outCircle);
		$div_right.append($div_container);
		
		var $inputHiddenWithTypeNumber = jQuery('<input type="hidden" name="case_typeNumber" value="'+courses[i].courseType+'" />');
		
		$li.append($div_left);
		$li.append($div_right);
		$li.append($inputHiddenWithTypeNumber);
		
		jQuery("div.record>div.record_default").not(":hidden").hide();
		jQuery("div.record>ul").addClass("info_list").append($li);
	}
	/** 将typeNumber转换为页面内容 */
	typeNumberUpdateHtml();
	/** 重新定义页面高度 */
	reDefinedPageHeight();
	
	function typeNumberUpdateHtml() {
		var $typeNumber = jQuery("input[name='case_typeNumber']");
		jQuery.each($typeNumber, function(index) {
			var $this = jQuery(this);
			var value = parseInt($this.val()) - 1;
			
			var $li = $this.parents("li");
			
			var $textFiled = $li.find("div.left_wrap").children("div:eq(0)");
			$textFiled.text(typeValue[value]);
			
			var $outCircle = $li.find("div.out_circle");
			$outCircle.css({"background-color":colorValue[value]});
		});
	}
}

var clearModalData = true;
var courseEditNumber = 0;

function courseEditEventBind() {
	/**
	 * 
	 */
	jQuery("ul.timeline").on('click', 'span.courseEditSpan', function() {
		/**
		 * 取消模态框自动重置数据
		 * 把li中的数据重新填写到模态框中
		 * fileNumber
		 * filesSrc
		 * 保存时courses需要查找是否有替换下标指示
		 */
		clearModalData = false;
		var $li = jQuery(this).parents('li');
		var courseTypeNumber = parseInt($li.children('input[name="case_typeNumber"]').val());
		
		var course = arrayGet(courses, courseTypeNumber);
		
		
		jQuery("[name='visitingDate_case']").val(course.visitingDate);
		jQuery("[name='courseType']").val(course.courseType);
		jQuery("[name='remark']").val(course.remark);
		
		var $root = jQuery("div.upload>div.tmpDiv");
		$root.empty();
		/** fileNumber清空 */
		fileNumber = 0;
		/** 清空filesSrc中的图片，然后将图片路径重新放入 */
		filesSrc = [];
		filesSrc.length = 0;
		for(var i = 0; i < course.imgs.length; i++) {
			var $img = jQuery('<img name="img'+(i+1)+'" class="tmpImg" src="'+course.imgs[i]+'" />');
			$root.append($img);
			/** 每有一张图，fileNumber就自增1 */
			fileNumber++;
			addImgSrcToFilesSrc(course.imgs[i]);
		}
		courseEditNumber = courseTypeNumber;
		
		jQuery(".add_btn span").click();
	});
}

function courseDeleteEventBind() {
	/**
	 * 删除courses中相应下标的course
	 * 删除li标签
	 * 重新加载li_storage中存储的li标签
	 * 重新定义页面height
	*/
	
	jQuery("ul.timeline").on('click', 'span.courseDeleteSpan', function() {
		var $li = jQuery(this).parents("li");
		var courseTypeNumber = parseInt($li.children('input[name="case_typeNumber"]').val());
		layer.confirm("是否确认删除该病程内容",
			function() {
				courses = arrayRemove(courses, courseTypeNumber);
				$li.remove();
				avoidRemoveLiEmptyUl();
				dataStorage();
				reDefinedPageHeight();
				layer.closeAll('dialog');
			}
		);
	});
	
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

/**
 * 获取array数组中courseTypeName雨传入参数相同的内容
 */
function arrayGet(array, courseTypeNumber) {
	if(isNaN(courseTypeNumber)) {
		return false;
	}
	
	var course = "";
	for(var i = 0; i < array.length; i ++) {
		if(array[i].courseType == courseTypeNumber) {
			course = array[i];
			break;
		}
	}
	return course;
}

/**
 * 删除array数组中courseType与传入参数相同的内容
 */
function arrayRemove(array, courseTypeNumber) {
	if(isNaN(courseTypeNumber)) {
		return false;
	}
	var arrayTmp = new Array();
	
	for(var i = 0; i < array.length; i++) {
		if(array[i].courseType != courseTypeNumber) {
			arrayTmp.push(array[i]);
		}
	}
	return arrayTmp;
}

function caseSaveEventBind() {
	jQuery("ul.saveOrback").on('click', 'li:eq(0) span', function() {
		var $title = jQuery("[name='case_title']");
		var $child = jQuery("#child");
		var $visitingDate = jQuery("[name='visitingDate']");
		var $doctorDiagnosis = jQuery("[name='case_doctorDiagnosis']");
		var $basicCondition = jQuery("[name='case_basicCondition']");
		
		if(isBlank($title) || isBlank($visitingDate) || isBlank($doctorDiagnosis) || isBlank($basicCondition)) {
			return ;
		}
		if($child.val() == 0) {
			layer.msg("请选择科室");
			$child.focus();
			return ;
		}
		var data = {};
		
		var medical = {};
		
		medical.title = $title.val();
		medical.visitingDate = $visitingDate.val();
		medical.departmentId = $child.val();
		medical.doctorDiagnosis = $doctorDiagnosis.val();
		medical.basicCondition = $basicCondition.val();
		
		data.medical = medical;
		
		var courseList = new Array();
		
		
		for(var i = 0; i < courses.length; i++) {
			var courseType = typeValue[(courses[i].courseType)-1];
			var courseMap = {"courseType":courseType,"remark":courses[i].remark,"visitingDate":courses[i].visitingDate,"img1":"","img2":"","img3":""};
			var imgs = courses[i].imgs;
			for(var j = 0; j < imgs.length; j++) {
				
				if("img"+(j+1) == "img1") {
					courseMap.img1 = imgs[j];
				}
				if("img"+(j+1) == "img2") {
					courseMap.img2 = imgs[j];
				}
				if("img"+(j+1) == "img3") {
					courseMap.img3 = imgs[j];
				}
			}
			courseList.push(courseMap);
		}
		data.courses = courseList;
		
		dataSubmit(data);
	});
	
	function isBlank($element) {
		if($element.val() == "") {
			layer.msg("请把内容填写完整");
			$element.focus();
			return true;
		}
		return false;
	}
	
	function dataSubmit(data) {
		jQuery.ajax({
			async: true,
			cache: false,
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data),
			url: 'recordHealthFileControl.do?saveCase',
			beforeSend: function() {
				layer.load();
			},
			success: function(result) {
				layer.msg(result.msg);
			},
			complete: function() {
				layer.closeAll('loading');
			}
		});
	}
	
}

function caseBackEventBind() {
	jQuery("ul.saveOrback").on('click', 'li:eq(1) span', function() {
		window.location.href = "recordHealthFileControl.do?enter";
	});
}