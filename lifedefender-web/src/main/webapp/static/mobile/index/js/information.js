var infomationControl = {
    init: function () {
        var first_col = $("#col_list li").eq(0).data("id");//第一个分类id
        $("#col_list li").eq(0).addClass("fAction");
        this.getInforListDatas(first_col, 1);
        $("#col_list li").each(function () {
            $(this).click(function () {
                $("#info_list").empty();
                $(this).addClass("fAction");
                $(this).siblings("li").removeClass("fAction");
                infomationControl.getInforListDatas($(this).data("id"), 1)
            })
        })
    },
    getInforListDatas: function (col, page) {
        $.ajax({
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            url: 'appweb.do?getInformationDatas&f=' + parseInt(col) + '&page=' + page,
            beforeSend: function () {
            },
            success: function (result) {
                infomationControl.createInfoDomList(result.obj);
            },
            complete: function () {
            }
        });
    }, createInfoDomList: function (data) {
        var totalPage = data.totalPage;
        var infoList = data.dataObject;
        var data_html = "";
        $.each(infoList, function (index, item) {
            if (item.title.length > 12) {
                item.title = item.title.substr(0, 12) + "...";
            }
            if (item.content.length > 25) {
                item.content = item.content.substr(0, 25) + "...";
            }
            if (item.source == undefined)
                item.source = ""
            if ((item.image != undefined) && (item.image != "")) {
                if (item.image.startsWith("lifekeepers_files")) {
                    item.image = "../" + item.image;
                }
            }
            data_html += ' <li><a href="appweb.do?informationInfo&id=' + item.id + '"> <div class="item-img"> ' +
                '<img src="' + item.image + '" alt=""> ' +
                '</div> ' +
                '<div class="item-news">' +
                ' <h3>' + item.title + '</h3> ' +
                '<p>' + item.content + '</p>' +
                /** TODO 暂时修改为上级目录.. */
                ' <a href="#"><img src="../static/images/sADtor.png" alt="">' + item.source + '</a> ' +
                '<span>' + DateUtils.formatDate(new Date(item.createDate)) + '</span> </div> ' +
                '<span class="spread"><a href="#">推广</a></span> </a></li>';
        })
        $(data_html).appendTo($("#info_list"));
        if (data.nowPage == totalPage) {
            var html = '<li><div style="text-align: center;">别扯了，到底啦～</div></li>'
            $(html).appendTo($("#info_list"))
        } else {
            slidePageUtils({
                dataDom: "#info_list",
                nowPage: data.nowPage,
                totalPage: totalPage,
                callBack: function (page) {
                    infomationControl.getInforListDatas(parseInt($(".fAction").data("id")), page)
                }
            })
        }
    }
}

/**
 * 手机分页
 * @param data
 */
var slidePageUtils = function (data) {
    var nowPage = data['nowPage'];//当前页数
    var totalPage = data['totalPage'];//总页数
    var callBack = data['callBack'];//回调
    // var contentHeight=data['contentHeight'];//数据累计的高度
    //var refreshPercent=data['refreshPercent'];//滑动到百分比进行分页回调
    var dataDom = data['dataDom'];//装数据的标签，如：div、ul

    this.getRefreshHeight = function () {
        return Math.round(parseInt($(dataDom).height()) * 0.7 - Math.round($(window).height() / 2));//数据高度的百分之70-屏幕高度一半就调用回调方法
    }
    var refreshHeight = this.getRefreshHeight();
    if (typeof callBack == "function") {
        var top = $(dataDom)[0].getBoundingClientRect().top;//当前滚动条应处于顶部，不然该值有误
        var flag = false;
        var listen = function () {
            if ((document.body.scrollTop - top) > refreshHeight) {
                if (!flag){
                    flag=!flag;
                    callBack((parseInt(nowPage) + 1));
                    $(dataDom)[0].removeEventListener("touchmove", listen, false);
                }
            }
        }
        $(dataDom)[0].addEventListener("touchmove", listen, false);
    }
    this.updateCurrentPage = function (page) {//更新当前页数
        nowPage = page;
        refreshHeight = this.getRefreshHeight();//刷新当前数据撑下来的高度
    }
}