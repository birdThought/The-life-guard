function initDialogInputStyle() {
    customRadio.init({
        name: 'serviceFair',
        color: 'blue',
    });

    customRadio.init({
        name: 'classify',
        color: 'blue'
    });
    customRadio.init({
        name: 'serviceSelect',
        color: 'blue',
        onselect: function (result) {
            switch (result) {
                case '0':// 免费
                    $("#not_free_service").animate({
                        "height": "hide"
                    });
                    $("#free-item").animate({
                        "height": "show"
                    });
                    break;
                case '1':// 收费
                    $("#not_free_service").animate({
                        "height": "show"
                    });
                    $("#free-item").animate({
                        "height": "hide"
                    });
                    break;
            }
        }
    });
    customRadio.init({
        name: 'noLimit',
        color: 'blue',
        onselect: function (result) {
            $("#freeDate").attr("disabled", "disabled");
        }, onUnSelect: function (value) {
            $("#freeDate").removeAttr("disabled");
        }
    });
    $("#freeDate").bind(
        "input propertychange", function () {
            pageInput($(this).val());
        });
}

function pageInput(val) {
    if (val < 1) {
        $("#freeDate").val(1);
    } else if (!(/^[-\+]?\d+$/.test(val) && parseInt(val) >= 0)) {
        $("#freeDate").val(1);
    }
}

var serviceControl = {
    serveChargeMode: {},
    openId: null,
    serviceClassify: {},//服务的分类
    initFreeContain: function (id) {
        $("#not_free_service").hide();
        $("#free-item").show();
        $("#open_btn").text("开通服务");
        $("#open_btn").attr("onclick",
            "serviceControl.openService()");

        var chargeMode = this.serveChargeMode[id].split(",")
        $.each(chargeMode, function (index, item) {
            chargeMode[index] = parseInt(item)
        })
        if ($.inArray(0, chargeMode) != -1) {
            if ($.inArray(1, chargeMode) != -1 || $.inArray(2, chargeMode) != -1 || $.inArray(3, chargeMode) != -1)
                $("input[name='serviceSelect']").eq(1).iCheck('uncheck');
            else
                $("#notfree_serve").hide()
            $("input[name='serviceSelect']").eq(0).iCheck('check');
            $("input[name='noLimit']").iCheck('uncheck');
            $("#freeDate").removeAttr("disabled");
            $("#free_serve").show()
        } else {
            $("#not_free_service").show()
            $("input[name='serviceSelect']").eq(1).iCheck('check');
            $("input[name='serviceSelect']").eq(0).iCheck('uncheck');
            $("#free_serve").hide()
            $("input[name='noLimit']").iCheck('uncheck');
            $("#freeDate").removeAttr("disabled");
            $("#free-item").hide()
            $("#notfree_serve").show()
        }
        $.inArray(1, chargeMode) != -1 ? $("[data-once]").show() : $("[data-once]").hide()
        $.inArray(2, chargeMode) != -1 ? $("[data-month]").show() : $("[data-month]").hide()
        $.inArray(3, chargeMode) != -1 ? $("[data-year]").show() : $("[data-year]").hide()

        $("#service_about").text("");
        $("input[name='classify']").each(function () {
            $(this).iCheck('uncheck');
        });
        $("input[name='serviceFair']").each(function () {
            $(this).iCheck('uncheck');
        });
        $("#timeCash").val(0);
        $("#monthCash").val(0);
        $("#yearCash").val(0);
        initDialogInputStyle();
    },
    showServiceDialog: function (target, id) {// 显示服务开通对话框
        this.openId = id;
        this.setupClassify(id);
        switch (target) {
            case 0:// 开通服务
                this.initFreeContain(id);
                layer.open({
                    type: 1,
                    title: ['开通服务设置',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: '670px',
                    offset: 80,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    scrolling: 'no',
                    content: $('.dialog_contain')
                });
                break;
            case 1:// 价格定制
                layer.load(1, {
                    shade: [0.5, '#393D49']
                });
                $
                    .ajax({
                        type: 'POST',
                        url: 'orgServeControl.do?getOrdeServiceMsg&serId='
                        + id,
                        success: function (result) {
                            serviceControl.setOrgServiceData(result.obj, id);
                            layer
                                .open({
                                    type: 1,
                                    title: ['价格定制',
                                        'text-align:center;font-size:16px;background:#fff;'],
                                    area: '670px',
                                    offset: 80,
                                    moveType: 1,
                                    scrollbar: false,
                                    zIndex: 9999,
                                    scrolling: 'no',
                                    content: $('.dialog_contain')
                                });
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
                break;
        }
    },
    setOrgServiceData: function (data, id) {
        $("#open_btn").text("确认修改");
        $("#freeDate").val('');
        $("#open_btn").attr("onclick",
            "serviceControl.updateOrderService(" + data.id + ")");

        var chargeMode = this.serveChargeMode[id].split(",")
        $.each(chargeMode, function (index, item) {
            chargeMode[index] = parseInt(item)
        })
        if($.inArray(0, chargeMode) != -1){
            if ($.inArray(1, chargeMode) != -1 || $.inArray(2, chargeMode) != -1 || $.inArray(3, chargeMode) != -1)
               ;// $("input[name='serviceSelect']").eq(1).iCheck('uncheck');
            else
                $("#notfree_serve").hide()
            //$("input[name='serviceSelect']").eq(0).iCheck('check');
            $("#free_serve").show()
        }else{
            $("#not_free_service").show()
            $("input[name='serviceSelect']").eq(1).iCheck('check');
            $("input[name='serviceSelect']").eq(0).iCheck('uncheck');
            $("#free_serve").hide()
            $("input[name='noLimit']").iCheck('uncheck');
            $("#freeDate").removeAttr("disabled");
            $("#free-item").hide()
            $("#notfree_serve").show()
        }
        $.inArray(1, chargeMode) != -1 ? $("[data-once]").show() : $("[data-once]").hide()
        $.inArray(2, chargeMode) != -1 ? $("[data-month]").show() : $("[data-month]").hide()
        $.inArray(3, chargeMode) != -1 ? $("[data-year]").show() : $("[data-year]").hide()

        if (data.hasFree) {
            $("#not_free_service").hide();
            $("#free-item").show();
            $("input[name='serviceSelect']").eq(0).iCheck('check');
            $("input[name='serviceSelect']").eq(1).iCheck('uncheck');
            var months = data['freeDate'];
            if (months == 0) {
                $("#freeDate").attr("disabled", "disabled");
                $("input[name='noLimit']").iCheck('check');
            } else {
                $("#freeDate").removeAttr("disabled");
                $("#freeDate").val(months);
                $("input[name='noLimit']").iCheck('uncheck');
            }
            $("input[name='serviceFair']").each(function () {
                $(this).iCheck('uncheck');
            })
            $("#timeCash").val(0);
            $("#monthCash").val(0);
            $("#yearCash").val(0);
        } else {
            $("#free-item").hide();
            $("#not_free_service").show();
            $("input[name='serviceSelect']").eq(0).iCheck('uncheck');
            $("input[name='serviceSelect']").eq(1).iCheck('check');
            if (data.hasTime) {
                $("[data-once] input[name='serviceFair']").iCheck('check');
                $("#timeCash").val(parseFloat(data.timePrice / 100));
            } else {
                $("[data-once] input[name='serviceFair']").iCheck('uncheck');
                $("#timeCash").val(0);
            }
            if (data.hasMonth) {
                $("[data-month] input[name='serviceFair']").iCheck('check');
                $("#monthCash").val(parseFloat(data.monthPrice / 100));
            } else {
                $("[data-month] input[name='serviceFair']").iCheck('uncheck');
                $("#monthCash").val(0);
            }
            if (data.hasYear) {
                $("[data-year] input[name='serviceFair']").iCheck('check');
                $("#yearCash").val(parseFloat(data.yearPrice / 100));
            } else {
                $("[data-year] input[name='serviceFair']").iCheck('uncheck');
                $("#yearCash").val(0);
            }
        }

        if (data.classify != undefined) {
            var str = data.classify.split(",");
            var h = '<label>服务分类：</label>';
            for (var index in str) {
                var c = str[index];
                $("input[name='classify']").each(function () {
                    var v = $(this).val();
                    if (v == c)
                        $(this).iCheck('check');
                });
            }
        }
        $("#service_about").text(data.about);

        initDialogInputStyle();
    },
    openService: function () {// 开通服务
        var json = this.getJson();
        if (json == undefined)
            return;
        layer.confirm("您确定要开通该服务吗？", function () {
            layer.load(1, {
                shade: [0.5, '#393D49']
            });
            $.ajax({
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                url: 'orgServeControl.do?orderService',
                data: JSON.stringify(json),
                success: function (result) {
                    var b = result.success;
                    if (!b) {
                        layer.msg("开通失败！");
                        return;
                    }
                    layer.msg("开通成功！");
                    window.location.reload();
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
        });
    },
    updateOrderService: function () {
        var json = this.getJson();
        if (json == undefined)
            return;
        layer.confirm("您确定要修改吗？", function () {
            layer.load(1, {
                shade: [0.5, '#393D49']
            });
            $.ajax({
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                url: 'orgServeControl.do?updateOrgSerivce',
                data: JSON.stringify(json),
                success: function (result) {
                    if (result.success) {
                        layer.msg("修改成功！");
                        window.location.reload();
                    }
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
        });
    },
    getJson: function () {
        var json = {
            serveId: this.openId
        };
        var classifyArray = customRadio.getResult('classify');
        var str = '';
        for (var index in classifyArray) {
            str += classifyArray[index] + ',';
        }
        if (str == '') {
            layer.msg("至少要选择一个服务分类");
            json = undefined;
            return json;
        }
        str = str.substr(0, str.length - 1);
        json.classify = str;
        var cs = parseInt(customRadio.getResult('serviceSelect'));
        switch (cs) {
            case 0:// 免费
                json.hasFree = true;
                var mode = customRadio.getResult('noLimit');
                if ($.trim(mode) != '') {
                    json.freeDate = 0;
                } else {
                    var days = parseInt($("#freeDate").val());
                    if (isNaN(days)) {
                        layer.msg("请输入免费时长");
                        json = undefined;
                        return json;
                    }
                    json.freeDate = days;
                }
                break;
            case 1:// 收费
                json.hasFree = false;
                var fairArray = customRadio.getResult('serviceFair');// 可获得按次、按月、按年是否被选中
                if ($.trim(fairArray) == '') {
                    layer.msg("请选择收费的方式");
                    return;
                }
                for (var index in fairArray) {
                    switch (fairArray[index]) {
                        case 'count':
                            json.hasTime = true;
                            var timeMoney = $("#timeCash").val();
                            if (!checkUtils.checkMoney(timeMoney)) {
                                layer.msg("请输入正确的金额,小数点不能超出2位");
                                return;
                            }
                            timeMoney = (parseFloat(timeMoney) * 100) + "";
                            if (timeMoney == '0') {
                                layer.msg("收费项价格不能为0元");
                                return;
                            }
                            json.timePrice = timeMoney;
                            break;
                        case 'month':
                            json.hasMonth = true;
                            var monthMoney = $("#monthCash").val();
                            if (!checkUtils.checkMoney(monthMoney)) {
                                layer.msg("请输入正确的金额,小数点不能超出2位");
                                return;
                            }
                            monthMoney = (parseFloat(monthMoney) * 100) + "";
                            if (monthMoney == '0') {
                                layer.msg("收费项价格不能为0元");
                                return;
                            }
                            json.monthPrice = monthMoney;
                            break;
                        case 'year':
                            json.hasYear = true;
                            var yearMoney = $("#yearCash").val();
                            if (!checkUtils.checkMoney(yearMoney)) {
                                layer.msg("请输入正确的金额,小数点不能超出2位");
                                return;
                            }
                            yearMoney = (parseFloat(yearMoney) * 100) + "";
                            if (yearMoney == '0') {
                                layer.msg("收费项价格不能为0元");
                                return;
                            }
                            json.yearPrice = yearMoney;
                            break;
                    }
                }
                break;
        }

        var about = $("#service_about").val();
        if (about != '')
            json.about = about;
        return json;
    }, setupClassify: function (id) {
        var classify = this.serviceClassify[id].split(',');
        var str = '<label>服务分类：&nbsp;&nbsp;</label>';
        for (var index in classify) {
            var c = classify[index];
            str += '<input name="classify" type="checkBox" value="' + c + '" />' + c + '&nbsp;&nbsp;'
        }
        $("#classify_divide").html(str);
    }
}