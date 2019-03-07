var newsControl = {
    filter: {
        f: null,
        search: null
    },
    init: function (data) {
        if (!$.isEmptyObject(data['f'])){
            this.filter.f = data['f'];
            $(".news-nav li[data-id="+this.filter.f+"]").addClass("fAction");
        }else{
            $(".news-nav li:eq(0)").addClass("fAction");
        }
        if (!$.isEmptyObject(data['search']))
            this.filter.search = data['search'];
        $("#search").click(function () {
            var search=$.trim($("#searchCondition").val());
            if($.isEmptyObject(search))
                return;
            window.location.href="informationControl.do?newsIndex&search="+search;
        })
    },nextPage:function (page) {
        var params="&page="+page;
        if(!$.isEmptyObject(this.filter.f)){
            params+='&f='+this.filter.f;
        }
        if(!$.isEmptyObject(this.filter.search)) {
            params+='&search='+this.filter.search;
        }
        window.location.href="informationControl.do?newsIndex"+params;
    },lookNews:function (id) {
        window.open("informationControl.do?inforLook&id="+id,"_blank");
    }
}

var helpControl = {
    filter: {
        f: null,
        search: null
    },
    init: function (data) {
        if (!$.isEmptyObject(data['search'])){
            this.filter.search = data['search'];
            $("#question").text("搜索结果如下：");
        }else{
            if (!$.isEmptyObject(data['f'])){
                this.filter.f = data['f'];
                $(".main_content_nav li[data-id="+this.filter.f+"] a").css("color","#48c858");
                $("#question").text($(".main_content_nav li[data-id="+this.filter.f+"] a").text())
            }else{
                if (data['f']=='')
                    return;
                $(".main_content_nav li:eq(0) a").css("color","#48c858");
                $("#question").text($(".main_content_nav li:eq(0) a").text())
            }
        }
        $("#search").click(function () {
            var search=$.trim($("#searchCondition").val());
            if($.isEmptyObject(search))
                return;
            window.location.href="informationControl.do?helpCenterIndex&search="+search;
        })
    },nextPage:function (page) {
        var params="&page="+page;
        if(!$.isEmptyObject(this.filter.f)){
            params+='&f='+this.filter.f;
        }
        if(!$.isEmptyObject(this.filter.search)) {
            params+='&search='+this.filter.search;
        }
        window.location.href="informationControl.do?helpCenterIndex"+params;
    },lookNews:function (id) {
        window.location.href="informationControl.do?helpLook&id="+id;
    }
}


var orgHelp = {
    filter: {
        f: null,
        search: null
    },
    init: function (data) {
        if (!$.isEmptyObject(data['search'])){
            this.filter.search = data['search'];
            $("#question").text("搜索结果如下：");
        }else{
            if (!$.isEmptyObject(data['f'])){
                this.filter.f = data['f'];
                $(".main_content_nav li[data-id="+this.filter.f+"] a").css("color","#3598dc");
                $("#question").text($(".main_content_nav li[data-id="+this.filter.f+"] a").text())
            }else{
                if (data['f']=='')
                    return;
                $(".main_content_nav li:eq(0) a").css("color","#3598dc");
                $("#question").text($(".main_content_nav li:eq(0) a").text())
            }
        }
        $("#search").click(function () {
            var search=$.trim($("#searchCondition").val());
            if($.isEmptyObject(search))
                return;
            window.location.href="orgControl.do?orgHelp&search="+search;
        })
    },nextPage:function (page) {
        var params="&page="+page;
        if(!$.isEmptyObject(this.filter.f)){
            params+='&f='+this.filter.f;
        }
        if(!$.isEmptyObject(this.filter.search)) {
            params+='&search='+this.filter.search;
        }
        window.location.href="orgControl.do?orgHelp"+params;
    },lookNews:function (id) {
        window.location.href="orgControl.do?orgHelpLook&id="+id;
    }
}