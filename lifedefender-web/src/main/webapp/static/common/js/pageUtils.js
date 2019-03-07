//分页控制器
var PageUtil = function () {
    var pageControl = {// 分页查询
        curPage: 1,// 当前页
        totalPage: 1,// 总页数
        pageChange: null,// 回调函数
        preBtn: null,// 上一页的按钮id，如"#preBtn"
        nextBtn: null,// 下一页的按钮id
        container: null,// 最外层id
        init: function (data) {
            this.totalPage = parseInt(data['totalPage']);
            this.pageChange = data['pageChange'];
            this.preBtn = data['preBtn'];
            this.nextBtn = data['nextBtn'];
            this.container = data['container'];
            var initPage = parseInt(data['initPage']);//初始化完毕后需要跳转的页数
            this.bindEvent();
            if (initPage != undefined&&!isNaN(initPage))
                this.selectPage(initPage, true);
        },
        bindEvent: function () {
            $("#" + this.container + " .page_input").bind(
                "input propertychange", function () {
                    pageControl.pageInput($(this).val());
                });
            $("body #" + this.container + " #" + this.preBtn).click(function () {
                pageControl.prePage();
            });
            $("body #" + this.container + " #" + this.nextBtn).click(
                function () {
                    pageControl.nextPage();
                });
            $("body #" + this.container + " .page").not(".page_next").not(
                ".page_pre").not(".page_action").each(function () {
                $(this).click(function () {
                    pageControl.selectPage($(this).text());
                });
            });
            $("#" + this.container + " .page_input_enter").click(
                function () {
                    pageControl.selectPage($(
                        "#" + pageControl.container + " .page_input")
                        .val());
                });
        },
        prePage: function () {
            this.pageOnchange(0);
        },
        nextPage: function () {
            this.pageOnchange(1);
        },
        pageOnchange: function (target) {
            this.curPage = $("#" + this.container).find($(".page_action"))
                .html();
            switch (target) {
                case 0:// 上一页
                    this.selectPage((this.curPage - 1) == 1 ? 1 : this.curPage - 1);
                    break;
                case 1:// 下一页
                    this
                        .selectPage((parseInt(this.curPage) + 1) == this.totalPage ? this.totalPage
                            : parseInt(this.curPage) + 1);
                    break;
            }
        },
        pageInput: function (val) {
            if (val < 1) {
                $("#" + this.container + " .page_input").val('');
            } else if (val > this.totalPage) {
                $("#" + this.container + " .page_input").val(this.totalPage);
            } else if (!(/^[-\+]?\d+$/.test(val) && parseInt(val) >= 0)) {
                $("#" + this.container + " .page_input").val('');
            }
        },
        selectPage: function (next, isInit) {
            next = parseInt(next);
            if (isNaN(next)) {
                // layer.msg("请输入数字");
                return;
            }
            /** 把pageChange事件调到函数体前执行 */
            if (isInit == undefined) {
                this.pageChange(next);
            }

            if (next == 1) {// 第一页
                if (this.totalPage > 5) {
                    $("#" + this.container)
                        .html(
                            '<span class="page page_action">1</span><span class="page">2</span><span class="page">3</span><span class="page">4</span><span class="page">5</span><span class="page_dian">...</span><span id="'
                            + this.nextBtn
                            + '" class="page page_next">下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>');
                } else {
                    var str = '<span class="page page_action">1</span>';
                    for (var i = 1; i < this.totalPage; i++) {
                        str += '<span class="page">' + (i + 1) + '</span>';
                    }
                    if (this.totalPage > 1) {
                        str += '<span class="page page_next" id='
                            + this.nextBtn
                            + '>下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';
                    }
                    $("#" + this.container).html(str);
                }
            } else if (next == this.totalPage) {// 最后一页
                if (this.totalPage > 7) {
                    $("#" + this.container)
                        .html(
                            '<span class="page page_pre" id='
                            + this.preBtn
                            + '>上一页</span><span class="page">1</span><span class="page">2</span><span class="page_dian">...</span><span class="page">'
                            + (this.totalPage - 4)
                            + '</span><span class="page">'
                            + (this.totalPage - 3)
                            + '</span><span class="page">'
                            + (this.totalPage - 2)
                            + '</span><span class="page">'
                            + (this.totalPage - 1)
                            + '</span><span class="page page_action">'
                            + this.totalPage
                            + '</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>');
                } else {
                    var str = '<span class="page page_pre" id="' + this.preBtn
                        + '">上一页</span>';
                    for (var i = 0; i < this.totalPage - 1; i++) {
                        str += '<span class="page">' + (i + 1) + '</span>';
                    }
                    str += '<span class="page page_action">'
                        + this.totalPage
                        + '</span><span style="margin-left:10px">共'
                        + this.totalPage
                        + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';
                    $("#" + this.container).html(str);
                }
            } else {
                if (next > 6) {
                    var str = '<span class="page page_pre" id="'
                        + this.preBtn
                        + '">上一页</span><span class="page">1</span><span class="page">2</span><span class="page_dian">...</span>';
                    if (next < this.totalPage - 3) {
                        str += '<span class="page">'
                            + (next - 2)
                            + '</span><span class="page">'
                            + (next - 1)
                            + '</span><span class="page page_action">'
                            + next
                            + '</span><span class="page">'
                            + (next + 1)
                            + '</span><span class="page">'
                            + (next + 2)
                            + '</span><span class="page_dian">...</span><span class="page page_next" id="'
                            + this.nextBtn
                            + '">下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';
                    } else {
                        for (var i = this.totalPage - 4; i <= this.totalPage; i++) {
                            if (i == next) {
                                str += '<span class="page page_action">' + i
                                    + '</span>';
                                continue;
                            }
                            str += '<span class="page">' + i + '</span>';
                        }
                        str += '<span class="page page_next" id="'
                            + this.nextBtn
                            + '">下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';
                    }
                    $("#" + this.container).html(str);
                } else {
                    if (this.totalPage > 7) {
                        var str = '<span class="page page_pre" id="'
                            + this.preBtn + '">上一页</span>';
                        for (var i = 0; i < (next < 5 ? 5 : 7); i++) {
                            if ((i + 1) == next) {
                                str += '<span class="page page_action">'
                                    + (i + 1) + '</span>';
                                continue;
                            }
                            str += '<span class="page">' + (i + 1) + '</span>';
                        }
                        str += '<span class="page_dian">...</span><span class="page page_next" id="'
                            + this.nextBtn
                            + '">下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';

                        $("#" + this.container).html(str);
                    } else {
                        var str = '<span class="page page_pre" id="'
                            + this.preBtn + '">上一页</span>';
                        for (var i = 0; i < this.totalPage; i++) {
                            if ((i + 1) == next) {
                                str += '<span class="page page_action">'
                                    + (i + 1) + '</span>';
                                continue;
                            }
                            str += '<span class="page">' + (i + 1) + '</span>';
                        }
                        str += '<span class="page page_next" id="'
                            + this.nextBtn
                            + '">下一页</span><span style="margin-left:10px">共'
                            + this.totalPage
                            + '页，到第</span><input type="text" class="page_input" /><span>页</span><button class="page_input_enter">确定</button>';
                        $("#" + this.container).html(str);
                    }
                }
            }
            this.curPage = next;
            this.bindEvent();
        }
    }
    this.getPageControl = function () {
        return pageControl;
    }
}
