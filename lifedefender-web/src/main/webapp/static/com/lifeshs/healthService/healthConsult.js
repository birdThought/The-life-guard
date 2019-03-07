var searchControl = {
    cookieKey:"allCount",//查询总数，存入cookie减少查询总数次数
    init: function (data) {
        var filter = data['fil1ter'];
        var allCount=data['pageCount'];
        Cookie.add(this.cookieKey,allCount);
        this.initFilter(filter);
        reDefinedPageHeight();
    }, initFilter: function (filter) {//初始化过滤模块
        var service_f = filter['service'] == '' ? undefined : filter['service'];//开通服务
        var classify_f = filter['classify'] == '' ? undefined : filter['classify'];//分类
        var sort_f = filter['sort'] == '' ? undefined : filter['sort'];//排序
        if (service_f != undefined || classify_f != undefined || sort_f != undefined)
            $("#search_action_group").show();
        $("#service_filter li").each(function () {
            if (service_f != undefined && $(this).text() == service_f) {
                $(this).addClass("divide-action");
                searchControl.createSearchItem(0, service_f);
            }
            $(this).click(function () {
                searchControl.filterItemAction(0, $(this));
            });
        });
        $("#classify_filter li").each(function () {
            if (classify_f != undefined && $(this).text() == classify_f) {
                $(this).addClass("divide-action");
                searchControl.createSearchItem(1, classify_f);
            }
            $(this).click(function () {
                searchControl.filterItemAction(1, $(this));
            });
        });
        $("#sort_filter li").each(function () {
            if (sort_f != undefined && $(this).text() == sort_f) {
                $(this).addClass("divide-action");
                searchControl.createSearchItem(2, sort_f);
            }
            $(this).click(function () {
                searchControl.filterItemAction(2, $(this));
            });
        });
    },
    createSearchItem: function (target, str) {
        var item_li = $('<li><div class="filter-item">' + str + ' <a onclick="searchControl.removeFilter(' + target + ')"></a></div></li>');
        $(item_li).appendTo($("#search_action_group ul"));
    },
    filterItemAction: function (target, obj) {//点击筛选按钮
        var service = $("#service_filter li.divide-action").text();
        var classify = $("#classify_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
        var str = "";
        target == 0 ? service = $(obj).text() : target == 1 ? classify = $(obj).text() : target == 2 ? sort = $(obj).text() : true;
        if (service != '')
            str += "&service=" + service;
        if (classify != '')
            str += "&classify=" + classify;
        if (sort != '')
            str += "&sort=" + sort;
        Cookie.remove(this.cookieKey);
        window.location.href = "serviceControl.do?healthConsultPage" + str;
    }, removeFilter: function (target) {//移除一个过滤筛选
        var service = $("#service_filter li.divide-action").text();
        var classify = $("#classify_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
        target == 0 ? service = '' : target == 1 ? classify = '' : target == 2 ? sort = '' : true;
        var str = "";
        if (service != '')
            str += "&service=" + service;
        if (classify != '')
            str += "&classify=" + classify;
        if (sort != '')
            str += "&sort=" + sort;
        Cookie.remove(this.cookieKey);
        window.location.href = "serviceControl.do?healthConsultPage" + str;
    },pageChange:function (page) {//翻页
        var str=this.getFilterValue();
        str+="&page="+page;
        window.location.href = "serviceControl.do?healthConsultPage" + str;
    },getFilterValue:function () {
        var service = $("#service_filter li.divide-action").text();
        var classify = $("#classify_filter li.divide-action").text();
        var sort = $("#sort_filter li.divide-action").text();
        var str = "";
        if (service != '')
            str += "&service=" + service;
        if (classify != '')
            str += "&classify=" + classify;
        if (sort != '')
            str += "&sort=" + sort;
        return str;
    },search:function () {
        //filterName
        var str=$.trim($("#searchInp").val());
        if(str=='')
            return;
        Cookie.remove(this.cookieKey);

        window.location.href = "serviceControl.do?healthConsultPage&filterName=" + str;
    }
}
