var userControl = {
		filterCache:null,
	filter : function() {// 筛选查询
		var service = $("#serve_select option:selected").text();
		var userName = $("#user_inp").val();
		var condition = {};
		if (service != '选择服务') {
			condition.curService = service;
		}
		if (userName != '') {
			condition.userName = '%' + userName + '%';
		}
		 $.isEmptyObject(condition) ?this.filterCache=null:this.filterCache=condition;
		this.getDatas(1, true);
	},
	getDatas : function(page,isFilter) {
		var json = {
			page : page
		};
		if (this.filterCache!=null)
			json.condition = this.filterCache;
		ajaxSend(json, 'orgUserControl.do?getMemberDatas', true, function(
				result) {
			layer.closeAll();
			var dataList = result.attributes.data;
			userControl.createTableNodes(dataList);
			if (isFilter != undefined && isFilter) {
				$("#pageContain").remove();
				var pageCount = result.attributes.count;
				if (pageCount != 0) {
					pageCount = pageCount % 15 == 0 ? pageCount / 15 : Math
							.floor(pageCount / 15) + 1;
					var pageDiv = document.createElement("div");
					$(pageDiv).addClass("page_Container");
					$(pageDiv).attr({
						"id" : "pageContain"
					});
					$(pageDiv).insertAfter($(".service_table"));
					if (pageCount > 1) {
						var mPage = new PageUtil();
						mPage.getPageControl().init({
							container : "pageContain",
							preBtn : "btn_pre",
							nextBtn : "btn_next",
							totalPage : pageCount,
							pageChange : function(page) {
								userControl.getDatas(page);
							}
						});
						mPage.getPageControl().selectPage(1, true);
					} else {
						$("#pageContain").html(
								'<span class="page page_action">1</span>');
					}
				}
			}
		})
	},
	createTableNodes : function(dataList) {
		var str = '<tr><td>用户名</td><td>姓名</td><td>手机号</td><td>当前服务</td><td>群组</td></tr>';
		if (dataList.length == 0) {
			str += '<tr><td colspan="5">没有相关数据</td></tr>';
			$(".service_table").html(str);
			return;
		}
		for ( var index in dataList) {
			var data = dataList[index];
			if (data.realName == undefined)
				data.realName = '';
			if (data.mobile == undefined)
				data.mobile = '';
			str += '<tr><td>' + data.userName + '</td><td>' + data.realName
					+ '</td><td>' + data.mobile + '</td><td>' + data.curService
					+ '</td><td>' + data.groupName
					+ '</td></tr>';
		}
		$(".service_table").html(str);
	}
}
var ajaxSend = function(json, url, isType, callBack) {
	layer.load();
	var type = 'application/json; charset=utf-8';
	isType ? json = JSON.stringify(json)
			: type = 'application/x-www-form-urlencoded; charset=utf-8';
	$.ajax({
		type : 'POST',
		contentType : type,
		url : url,
		data : json,
		success : function(result) {
			callBack(result);
		},complete:function () {
			layer.closeAll("loading");
		}
	});
}