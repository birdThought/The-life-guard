/*员工操作管理类*/
var staffControl = {
	isManager:false,//是否管理机构
	isShowing : false,
	objIndex : null,
	isFilter : false,// 是否点击过了搜索筛选
	cacheJson : null,// 缓存的json搜索串
	showControl : function(obj) {
		if (this.isShowing) {
			this.onHidden();
			return;
		}
		var control = this.createControlList(obj);
		control.style.left = obj.getBoundingClientRect().left - 10 + "px";
		control.style.top = obj.getBoundingClientRect().top + obj.offsetHeight
				+ $(document).scrollTop() + "px";
		document.body.appendChild(control);
		this.objIndex = obj;
		$(obj).attr("status", "1");
		this.isShowing = true;
		$(document).bind(
				"click",
				function(event) {
					if ($(event.target).parent().attr("class") == "dialog_btn"
							|| ($(event.target).text() == "操作" && $(
									event.target).attr("status") == "1"))
						return;
					staffControl.onHidden();
				});
	},
	createControlList : function(obj) {
		var ul = document.createElement("ul");
		$(ul).addClass("dialog_btn");
		var abandon = document.createElement("li");
		$(abandon).text("离职");
		$(abandon).click(function() {
			staffControl.btnClick(0, obj);
		});
		ul.appendChild(abandon);
		return ul;
	},editClick:function(obj,id){
		var check=$(obj).parent().parent().find($("td")).eq(this.isManager?4:5).find($("span")).text();
		if(check=='离职'){
			layer.msg("离职的员工无法编辑哟~");
			return;
		}
		window.location.href='orgUserControl.do?editEmployPage&userId='+id;
	},
	btnClick : function(target, obj) {
		var id = $(obj).parent().parent().find($("td:first-child")).attr(
				"data-id");
		var json = {
			column : {
				id : id
			}
		}
		switch (target) {
		case 0:// 离职
			var check=$(obj).parent().parent().find($("td")).eq(this.isManager?4:5).find($("span")).text();
			if(check=='离职'){
				layer.msg("该员工已经是离职状态了哦亲~");
				staffControl.onHidden();
				return;//减少交互
			}
			layer.confirm("你确定要让该员工离职吗？", function(index) {
				json.target = 0;
				json.column.status = 4;
				staffControl.controlEmploy(json,obj);
				staffControl.onHidden();
				layer.close(index);
			});
			break;
		}
	},
	controlEmploy : function(json,obj) {
		layer.load();
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : 'orgUserControl.do?controlEmploy',
			data : JSON.stringify(json),
			success : function(result) {
				layer.closeAll();
				if (!result.success)
					return;
				switch (json.target) {
				case 0:// 离职
					$(obj).parent().parent().find($("td")).eq(staffControl.isManager?4:5).html('<span style="color:#6c6c6c">离职</span>');
					break;
				}
			},
			error : function() {
				layer.closeAll();
			}
		});
	},
	onHidden : function() {
		this.isShowing = false;
		$(this.objIndex).removeAttr("status");
		$(document).unbind("click");
		$(".dialog_btn").remove();
	},
	filter : function() {
		var json = {
			begin : 1
		};
		
		var status = $("#status option:selected").val();
		var date = $("#joinDate").val();
		var realName = $.trim($("#name").val());
		if(!this.isManager){
			var type = $("#typeSelect option:selected").val();
			if (type != 'all') {
				json.userType = type;
			}
		}
		if (status != 'all') {
			json.status = status;
		}
		if (date != '') {
			json.birthday = date;
		}
		if (realName != '') {
			json.realName = realName;
		}
		this.isFilter = true;
		this.cacheJson = json;
		this.getDataList(this.cacheJson, true);
	},
	findEmploys : function(page) {
		if (this.isFilter) {
			this.cacheJson.begin = page;
			this.getDataList(this.cacheJson);
		} else {
			var json = {
				begin : page
			};
			this.getDataList(json);
		}
	},
	getDataList : function(json, isCreatePage) {
		layer.load();
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : 'orgUserControl.do?filterEmploy',
			data : JSON.stringify(json),
			success : function(result) {
				var dataList = result.attributes.emList;
				var count = parseInt(result.attributes.count);
				staffControl.setList(dataList);
				if (dataList != '' && isCreatePage) {
					$("#pageManager").remove();
					var pageDiv = document.createElement("div");
					$(pageDiv).addClass("page_Container");
					$(pageDiv).attr("id","pageManager");
					$(pageDiv).insertAfter($("#allCount"));
					if (count > 1) {
						var pageManager = new PageUtil();
						pageManager.getPageControl().init({
							container : "pageManager",
							preBtn : "btn_pre",
							nextBtn : "btn_next",
							totalPage :count,
							pageChange : function(page) {
								staffControl.findEmploys(page);
							}
						});
						pageManager.getPageControl().selectPage(1,true);
					} else {
						$(".page_Container").html(
								'<span class="page page_action">1</span>');
					}
				}
			},
			complete : function() {
				layer.closeAll("loading");
			}
		});
	},
	setList : function(data) {
		var str='<tr><td>用户名</td><td>姓名</td><td>手机号</td>';
		if(!this.isManager){
			str+='<td>类型</td>';
		}
		str += '<td>出生时间</td><td>状态</td><td>操作</td></tr>';
		if (data == '') {
			str += this.isManager?"<tr><td colspan=\"6\">无记录</td></tr>":"<tr><td colspan=\"7\">无记录</td></tr>";
			$(".service_table").html(str);
			$(".page_Container").remove();
		}
		for (var key in data) {
			var obj = data[key];
			if (obj.mobile == undefined)
				obj.mobile = '';
			if (obj.realName == undefined)
				obj.realName = '';
			str += '<tr><td data-id="' + obj.id + '">' + obj.userName
					+ '</td><td>' + obj.realName + '</td><td>' + obj.mobile
					+ '</td>';
			if(!this.isManager){
				switch (obj.userType) {
				case 0:
					str += '<td>管理员</td>';
					break;
				case 1:
					str += '<td>服务师</td>';
					break;
				case 2:
					str += '<td>管理员&服务师</td>';
					break;
				}
			}
			if(obj.birthday!=undefined)
				obj.birthday=DateUtils.formatDate(new Date(obj.birthday));
			str += '<td>' + obj.birthday + '</td>';
			switch (obj.status) {
			case 0:
				str += '<td><span class="jobStatus">正常</span></td>';
				break;
			case 4:
				str += '<td><span style="color:#6c6c6c">离职</span></td>';
				break;
			}
			str += '<td><a onclick="staffControl.editClick(this,'+obj.id+')" style="cursor: pointer;">编辑</a>&nbsp;<a onclick="staffControl.showControl(this)" style="margin-left:10px;cursor:pointer">操作</a></td>';
		}
		str=str.replace(/undefined/g,"")
		$(".service_table").html(str);
	}
}

/*
 * var ajaxControl = function(data) { this.method = data['method']; this.success =
 * data['success']; this.error = data['error']; this.url = data['url'];
 * this.json = data['json']; $.ajax({ type : this.method, contentType :
 * 'application/json; charset=utf-8', url : this.url, data :
 * JSON.stringify(this.json), success : function(result) { success(result); },
 * error : function() { error(); } }); }
 */

