var searchControl = {
    cookieKey:"allCount_gaga",//查询总数，存入cookie减少查询总数次数
    init: function (data) {
        var filter = data['fil1ter'];
        var allCount=data['pageCount'];
        Cookie.add(this.cookieKey,allCount);
        this.initFilter(filter);
        reDefinedPageHeight();
    }, initFilter: function (filter) {//初始化过滤模块
    	var pcName_f = filter['pcName'] == '' ? undefined : filter['pcName'];
        var orgType_f = filter['orgType'] == '' ? undefined : filter['orgType'];// 机构分类
        var service_f = filter['service'] == '' ? undefined : filter['service'];//开通服务
        var sort_f = filter['sort'] == '' ? undefined : filter['sort'];//排序
        if (service_f != undefined || orgType_f != undefined || pcName_f != undefined || sort_f != undefined) {
        	$("#search_action_group").parent("li").show();
        }
        $("#pcName_filter li").each(function (index) {
        	if (index != 0) {
        		if (pcName_f != undefined && $(this).text() == pcName_f) {
        			$(this).addClass("divide-action");
        			searchControl.createSearchItem(0, pcName_f);
        		}
        		$(this).click(function () {
        			searchControl.filterItemAction(0, $(this));
        		});
        	} else {
        		$(this).addClass("notPointer");
        	}
        });
        $("#orgType_filter li").each(function (index) {
        	if (index != 0) {
	        	if (orgType_f != undefined && $(this).text() == orgType_f) {
	        		$(this).addClass("divide-action");
	        		searchControl.createSearchItem(1, orgType_f);
	        	}
	        	$(this).click(function () {
	        		searchControl.filterItemAction(1, $(this));
	        	});
        	} else {
        		$(this).addClass("notPointer");
        	}
        });
        $("#service_filter li").each(function (index) {
        	if (index != 0) {
        		if (service_f != undefined && $(this).text() == service_f) {
        			$(this).addClass("divide-action");
        			searchControl.createSearchItem(2, service_f);
        		}
        		$(this).click(function () {
        			searchControl.filterItemAction(2, $(this));
        		});
        	} else {
        		$(this).addClass("notPointer");
        	}
        });
        $("#sort_filter li").each(function (index) {
        	if (index != 0) {
        		if (sort_f != undefined && $(this).text() == sort_f) {
        			$(this).addClass("divide-action");
        			searchControl.createSearchItem(3, sort_f);
        		}
        		$(this).click(function () {
        			searchControl.filterItemAction(3, $(this));
        		});
        	} else {
        		$(this).addClass("notPointer");
        	}
        });
    },
    createSearchItem: function (target, str) {
        var item_li = $('<li><div class="filter-item">' + str + ' <a onclick="searchControl.removeFilter(' + target + ')"></a></div></li>');
        $(item_li).appendTo($("#search_action_group ul"));
    },
    filterItemAction: function (target, obj) {//点击筛选按钮
    	
    	var pcName = $("#pcName_filter li.divide-action").text();
        var orgType = $("#orgType_filter li.divide-action").text();
        var service = $("#service_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
    	
        var str = "";
        target == 0 ? pcName = $(obj).text() : target == 1 ? orgType = $(obj).text() : target == 2 ? service = $(obj).text() : target == 3 ? sort = $(obj).text() : true;
        if (service != '')
            str += "&service=" + service;
        if (pcName != '')
            str += "&pcName=" + pcName;
        if (orgType != '')
        	str += "&orgType=" + orgType;
        if (sort != '')
            str += "&sort=" + sort;
        Cookie.remove(this.cookieKey);
        window.location.href = "indexControl.do?healthServiceOrg" + str;
    }, removeFilter: function (target) {//移除一个过滤筛选
    	var pcName = $("#pcName_filter li.divide-action").text();
        var orgType = $("#orgType_filter li.divide-action").text();
        var service = $("#service_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
        target == 0 ? pcName = '' : target == 1 ? orgType = '' : target == 2 ? service = '' : target == 3 ? sort = '' : true;
        var str = "";
        if (service != '')
            str += "&service=" + service;
        if (pcName != '')
            str += "&pcName=" + pcName;
        if (orgType != '')
        	str += "&orgType=" + orgType;
        if (sort != '')
            str += "&sort=" + sort;
        Cookie.remove(this.cookieKey);
        window.location.href = "indexControl.do?healthServiceOrg" + str;
    },pageChange:function (page) {//翻页
        var str=this.getFilterValue();
        str+="&page="+page;
        window.location.href = "indexControl.do?healthServiceOrg" + str;
    },getFilterValue:function () {
    	var pcName = $("#pcName_filter li.divide-action").text();
        var orgType = $("#orgType_filter li.divide-action").text();
        var service = $("#service_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
        var str = "";
        if (service != '')
            str += "&service=" + service;
        if (pcName != '')
            str += "&pcName=" + pcName;
        if (orgType != '')
        	str += "&orgType=" + orgType;
        if (sort != '')
            str += "&sort=" + sort;
        return str;
    },search:function() {
        //filterName
        var str=$.trim($("#searchCondition").val());
        if(str=='')
            return;
        Cookie.remove(this.cookieKey);

        window.location.href = "indexControl.do?healthServiceOrg&filterName=" + str;
    }
}