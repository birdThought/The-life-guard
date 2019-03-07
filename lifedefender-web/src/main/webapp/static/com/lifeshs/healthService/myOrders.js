var orderControl = {
    init: function () {
        $("#countNumber").bind(
            "input propertychange", function () {
                orderControl.pageInput($(this).val());
            });
        $("#countNumber").onblur = function () {
            var onePrice = $("#shop_price").text();
            onePrice = parseFloat(onePrice.substr(1, onePrice.length));
            var count = parseInt($("#countNumber").val());
            $("#allMoney").html('￥' + (count * onePrice).toFixed(2));
            $("#realAllMoney").html('￥' + (count * onePrice).toFixed(2));
        };
    }, pageInput: function (val) {
        if (val < 1) {
            $("#countNumber").val(1);
        } else if (!(/^[-\+]?\d+$/.test(val) && parseInt(val) >= 0)) {
            $("#countNumber").val(1);
        }
    },
    delOrder: function (id, target) {//删除或者取消订单
        switch (target) {
            case 0://取消
                layer.confirm("你确定要取消该订单吗？", function () {
                    layer.load();
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                        url: 'memberControl.do?delOrder&id=' + id,
                        success: function (result) {
                            layer.closeAll();
                            window.location.reload();
                        },
                        error: function () {
                            layer.closeAll();
                        }
                    });
                });
                break;
            case 1://删除
                layer.confirm("你确定要删除该订单吗？", function () {
                    layer.msg("该功能暂未开放");
                });
                break;
        }
    }, addOrDecrease: function (target) {//增加或者减少数量
        var onePrice = $("#shop_price").text();
        onePrice = parseFloat(onePrice.substr(1, onePrice.length));
        var count = parseInt($("#countNumber").val());
        switch (target) {
            case 0://减少
                if (count <= 1) {
                    layer.msg("数量不能少于1");
                    return;
                }
                count -= 1;
                break;
            case 1://增加
                count += 1;
                break;
        }
        $("#countNumber").val(count);
        $("#allMoney").html('￥' + (count * onePrice).toFixed(2));
        $("#realAllMoney").html('￥' + (count * onePrice).toFixed(2));
    }, pay: function (ordNumber, id, charMode) {//立即支付
        var cash = $("#pay_cash").text();
        var subject = $("#serveName").text();
        if (cash == 0.00 || charMode == 0) {
            cash += '';
            var json = {
                cash: cash,
                orderNumer: ordNumber,
                charMode: charMode,
                action: 'pay'
            }
            layer.load();
            $.ajax({
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(json),
                url: 'memberControl.do?changeOrderData',
                success: function (result) {
                    layer.closeAll();
                    if (result.success) {
                        window.location.href = 'memberControl.do?finishOrde&order=' + id;
                        return;
                    }
                    layer.msg("未知错误，支付失败");
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
            return;
        }
        var target = $('input[name="pay"]:checked').val();
        if (target == undefined) {
            layer.msg("请选择支付方式");
            return;
        }
        target = parseInt(target);
        switch (target) {
            case 0://银行卡支付

                break;
            case 1://支付宝支付
                subject = encodeURI(encodeURI(subject));
                window.open("commonControl.do?alipay&cash=" + cash + "&subject=" + subject + "&order=" + ordNumber + "&orderId=" + id, "_blank");
                break;
            case 2://微信支付

                break;
        }
        layer.confirm("您完成支付了吗？", {
            btn: ['已完成', '未完成']
        }, function () {
            window.location.href = 'memberControl.do?finishOrde&order=' + id;
        }, function () {
            layer.msg("未完成");
        });
    }, bindEvent: function () {
        $("#order_nav_choose li").click(function () {
            var status = $(this).attr("data-status");
            window.location.href = status == undefined ? 'memberControl.do?myOrders' : 'memberControl.do?myOrders&status=' + status;
        });
    }, goToPay: function (id, oldCount) {
        var count = $("#countNumber").val();
        if (oldCount != count && count != undefined) {
            layer.load();
            var json = {
                orderId: parseInt(id),
                count: parseInt(count),
                action: 'count'
            }
            $.ajax({
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(json),
                url: 'memberControl.do?changeOrderData',
                success: function (result) {
                    layer.closeAll();
                    window.location.href = 'memberControl.do?orderPayPage&id=' + id;
                },
                complete: function () {
                    layer.closeAll("loading");
                }
            });
            return;
        }
        window.location.href = 'memberControl.do?orderPayPage&id=' + id;
    }, commitOrder: function (id, orderNumber, chargeMode, price, title, groupId) {
        var json = {
            orgServeId: id,
            orderNumber: orderNumber,
            chargeMode: chargeMode,
            price: price,
            title: title,
            body: 'web购买'
        }
        if (groupId != '' && groupId != undefined) {
            json.groupId = parseInt(groupId);
        }
        if (chargeMode == 0) {
            json.total = '0';
            json.count = 1;
        } else {
            var count = parseInt($("#countNumber").val());
            json.count = count;
            var total = (count * price).toFixed(2);
            json.total = total + "";
        }
        layer.load();
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            url: 'memberControl.do?addOrder',
            success: function (result) {
                layer.closeAll();
                if (result.success) {
                    window.location.href = chargeMode == 0 ? 'memberControl.do?finishOrde&order=' + result.obj : 'memberControl.do?orderPayPage&id=' + result.obj;
                    return;
                }
                layer.msg(result.msg);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });

    }
}