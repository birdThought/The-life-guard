jQuery(function() {
	pageDataInit();
	pageEventBind();
});

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

var update_index = -1;

function pageDataInit() {
	
	getDepartmentsData();
	coursesInit();	// 将数据填充到courses
	
	/**
	 * 获取部门信息
	 */
	function getDepartmentsData() {
		var childDepartmentId = parseInt(jQuery("[name='childeDepartmentId']").val());
		jQuery.ajax({
			async: true,
			cache: false,
			type: 'GET',
			url: 'recordHealthFileControl.do?getParentsDepartment&childDepartmentId='+childDepartmentId,
			success: function(result) {
				var departments = result.obj;
				/** 目前确定为两级，如果需要多级，使用for循环遍历结果逐层触发change事件 */
				var departmentId = departments[0].id;
				jQuery("#parent").val(departmentId);
				jQuery("#parent").change();
				/** 400毫秒后选择child的值，child内容未构建就对其进行赋值 */
				setTimeout(function() {
					jQuery("#child").val(childDepartmentId);
				}, 400);
			}
		});
	}
	
	function coursesInit() {
		
		var medicalId = jQuery("[name='medicalId']").val();
		
		jQuery.ajax({
			async: true,
			cache: false,
			type: 'GET',
			url: 'recordHealthFileControl.do?getCoursesData&medicalId='+medicalId,
			beforeSend: function() {
				layer.load();
			},
			success: function(result) {
				courseControl.courses = result.obj;
				courseControl.sort();
			},
			complete: function() {
				layer.closeAll('loading');
				var $root = jQuery("ul.timeline");
				courseControl.appendToPage($root);
			}
		});
	}
}

function pageEventBind() {
	
	selectEventBind();
	addCaseEvent();
	uploadBtnEventBind();	// 上传按钮事件绑定
	sortBtnEventBind();
	courseEditEventBind();
	courseDeleteEventBind();
	clearBtnEventBind();
	
	caseSaveEventBind();
	caseBackEventBind();
	
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
	
	function addCaseEvent() {
		$('.add_btn span').parents('div.record_course').on('click',function(){
			layer.open({
				type: 1,
				title:['添加病程','text-align:center;font-size:16px;background:#fff;'],
				area:'500px',
				offset:'100px',
				btn:['确定','取消'],
				moveType: 1,
				zIndex: 888,
				scrollbar : false,
	  			content: $('.reportPop'), //这里content是一个DOM
	  			success: function() {
	  				if(update_index == -1) {
	  					modalDataClean();
	  				} else {
	  					jQuery("div.layui-layer-title").html("修改病程");
	  				}
	  			},
	  			cancel: function() {
	  				
	  			},
	  			end: function() {
	  				// 一定会执行的方法
	  				/** 排序 */
	  				courseControl.sort();
	  				/** 构图 */
	  				courseControl.appendToPage(jQuery('div.record>ul.timeline'));
	  				imgControl.reset();
	  				/** 排序存储 */
	  				timelineControl.storage(jQuery('ul.info_list'));
	  				/** 正常关闭需要归零courseEditNumber的值 */
	  				update_index = -1;
	  				/** 正向排序 */
	  				var $sortBtn = jQuery("div.sortBtn");
	  				$sortBtn.removeClass("asc");
	  				$sortBtn.click();
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
	  				
	  				var id = update_index == -1 ? 0 : courseControl.courses[update_index].id;
	  				
	  				/** 封装 */
	  				var course = Course(visitingDate, courseType, remark, imgControl.imgs, id);
	  				
	  				if(update_index != -1) {
	  					// 这里用一个tmp对象暂时保存原数据对象，避免因为新数据不符合规则而将原数据误删
	  					var courseTmp = courseControl.getCourseByOrder(update_index);
	  					courseControl.courses[update_index] = "";
	  					if(!courseControl.cover(update_index, course)) {
	  						courseControl.insert(update_index, courseTmp);
	  	  					return ;
	  	  				}
	  				} else {
	  					if(!courseControl.add(course)) {
	  	  					return ;
	  	  				}
	  				}

	  				/** 关闭 */
	  				layer.close(index);
	  			}
			});
			
			function modalDataClean() {
				
				jQuery("[name='visitingDate_case']").val(new Date().Format("yyyy-MM-dd"));
				jQuery("select[name='courseType']").val(1);
				jQuery("[name='remark']").val("");
				jQuery("div.tmpDiv").empty();
				jQuery("div.upload>span").text("");
			}
		});
	}
	
	function uploadBtnEventBind() {
		
		jQuery('div.reportPop').on('change', '[name="files"]', function() {
			imgControl.uploadImg(jQuery(this)[0].files);
		});
		
		jQuery('div.reportPop').on('click', 'div.delete>span', function() {
			var $obj = jQuery(this).parent('div.delete').prev('img');
			var _index = parseInt($obj.attr("name").substring(3)) - 1;
			
			imgControl.deleteWithIndex(_index);
			imgControl.printImg();
		});
	}
	
	function sortBtnEventBind() {
		jQuery("div.sortBtn").click(function() {
			var $root = jQuery("ul.info_list");
			
			if(jQuery(this).hasClass("asc")) {
				timelineControl.sort(true, $root);
				jQuery(this).removeClass("asc");
				jQuery(this).find("span").addClass("trangleR");
				jQuery(this).find("span").removeClass("trangle");
			} else {
				timelineControl.sort(false, $root);
				jQuery(this).addClass("asc");
				jQuery(this).find("span").removeClass("trangleR");
				jQuery(this).find("span").addClass("trangle");
			}
		});
	}
	
	function caseSaveEventBind() {
		jQuery("ul.saveOrback").on('click', 'li:eq(0) span', function() {
			var $title = jQuery("[name='case_title']");
			var $child = jQuery("#child");
			var $visitingDate = jQuery("[name='visitingDate']");
			var $doctorDiagnosis = jQuery("[name='case_doctorDiagnosis']");
			var $basicCondition = jQuery("[name='case_basicCondition']");
			var medicalId = jQuery("[name='medicalId']").val();
			var $hospital = jQuery("[name='case_hospital']");
			
			if(isBlank($title) || isBlank($hospital) || isBlank($visitingDate) || isBlank($doctorDiagnosis) || isBlank($basicCondition)) {
				return ;
			}
			if($child.val() == 0) {
				layer.msg("请选择科室");
				$child.focus();
				return ;
			}
			
			if ($hospital.val().length > 30) {
				layer.msg("医院名称不能超过30个字符");
				$hospital.focus();
				return ;
			}
			
			if($title.val().length > 30) {
				layer.msg("病历标题长度不能超过30个字符");
				$title.focus();
				return ;
			}
			if($doctorDiagnosis.val().length > 30) {
				layer.msg("医生诊断长度不能超过30个字符");
				$doctorDiagnosis.focus();
				return ;
			}
			if($basicCondition.val().length > 200) {
				layer.msg("基础病情内容长度不能超过200个字符");
				$basicCondition.focus();
				return ;
			}
			
			var data = {};
			
			var medical = {};
			
			medical.id = medicalId;
			medical.title = $title.val();
			medical.visitingDate = $visitingDate.val();
			medical.departmentId = $child.val();
			medical.doctorDiagnosis = $doctorDiagnosis.val();
			medical.basicCondition = $basicCondition.val();
			medical.hospital = $hospital.val();
			
			data.medical = medical;
			
			var courseList = new Array();
			var courses = courseControl.courses;
			
			for(var i = 0; i < courses.length; i++) {
				if (courses[i] == undefined) {
					continue;
				}
				var courseType = courseControl.getCourseTypeNameByNumber(courses[i].courseType);
				var courseMap = {"id": courses[i].id,"courseType":courseType,"remark":courses[i].remark,"visitingDate":courses[i].visitingDate,"img1":"","img2":"","img3":""};
				var imgs = courses[i].imgs;
				for(var j = 0; j < imgs.length; j++) {
					
					if("img"+(j+1) == "img1") {
						courseMap.img1 = typeof(imgs[j]) == 'undefined' ? "" : imgs[j];
					}
					if("img"+(j+1) == "img2") {
						courseMap.img2 = typeof(imgs[j]) == 'undefined' ? "" : imgs[j];
					}
					if("img"+(j+1) == "img3") {
						courseMap.img3 = typeof(imgs[j]) == 'undefined' ? "" : imgs[j];
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
				url: 'recordHealthFileControl.do?updateMedical',
				beforeSend: function() {
					layer.load();
				},
				success: function(result) {
					layer.msg(result.msg);
					setTimeout(function() {
						var medicalId = jQuery("[name='medicalId']").val();
						window.location.href = 'recordHealthFileControl.do?healthFileDetail&id='+medicalId;
					}, 800);
				},
				complete: function() {
					layer.closeAll('loading');
				}
			});
		}
		
	}
	
	function caseBackEventBind() {
		jQuery("ul.saveOrback").on('click', 'li:eq(1) span', function() {
			var medicalId = jQuery("[name='medicalId']").val();
			window.location.href = 'recordHealthFileControl.do?healthFileDetail&id='+medicalId;
		});
	}
	
	function clearBtnEventBind() {
		jQuery('div.reportPop').on('click', 'button[name="courseClear"]', function() {
			imgControl.clearImg();
		});
	}
}

function courseEditEventBind() {
	/**
	 * 取消模态框自动重置数据
	 * 把li中的数据重新填写到模态框中
	 * fileNumber
	 * filesSrc
	 * 保存时courses需要查找是否有替换下标指示
	 */
	jQuery("ul.timeline").on('click', 'span.courseEditSpan', function() {
		var $li = jQuery(this).parents('li');
		
		update_index = parseInt($li.children('input[name="case_order"]').val());
		
		var course = courseControl.getCourseByOrder(update_index);
		
		jQuery("[name='visitingDate_case']").val(course.visitingDate);
		jQuery("[name='courseType']").val(course.courseType);
		jQuery("[name='remark']").val(course.remark);
		
		imgControl.imgs = course.imgs.slice();
		imgControl.printImg();
		
		jQuery(".add_btn span").parents("div.record_course").click();
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
		
		var $this = jQuery(this);
		
		layer.confirm("确认删除该病程?", function(index) {
			var $li = $this.parents("li");
			var id=$li.attr("data-id");
			var removeIndex = parseInt($li.children('input[name="case_order"]').val());
			$li.remove();
			avoidRemoveLiEmptyUl();
			reDefinedPageHeight();
			// data
			courseControl.remove(removeIndex);
			timelineControl.storage(jQuery('ul.info_list'));
			
			layer.close(index);
		});
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