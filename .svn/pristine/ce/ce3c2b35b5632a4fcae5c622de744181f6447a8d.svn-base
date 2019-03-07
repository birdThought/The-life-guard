define(function (require, exports, module) {
    require('QYcommon');
    require.async(['icheck', 'customRadio', 'commonCheck'], function () {
        customRadio.init({
            name: 'sexSelect',
            color: 'blue',
            onselect: function (result) {
                switch (result) {
                    case '0'://女
                        break;
                    case '1'://男
                        break;
                }
            }
        });
    });
});

var addUser = {
    url: 'orgUserControl.do?addUser',
    serviceListCache: null,
    initService: function (serviceListCache) {
        this.serviceListCache = serviceListCache;
        var str = '';
        var first = null;
        for (var o in serviceListCache) {
            var item = serviceListCache[o];
            str += '<option value="' + item["id"] + '" data-id="' + item["orgServeId"] + '" >' + item["serviceName"] + '</option>';
            if (first == null)
                first = o;
        }
        $(str).appendTo($("#serverSelect"));
        this.updateService(first);
        $("#countNumber").bind(
            "input propertychange", function () {
                addUser.pageInput($(this).val());
            });
    },pageInput: function (val) {
        if (val < 1) {
            $("#countNumber").val(1);
        } else if (!(/^[-\+]?\d+$/.test(val) && parseInt(val) >= 0)) {
            $("#countNumber").val(1);
        }
    },
    createErrorLabel: function (text, objAfter, notAfter) {
        var error = document.createElement("label");
        $(error).attr({
            "id": "error",
            "style": "margin-left:15px;color:#ff0000;"
        });
        $(error).text(text);
        if (notAfter) {
            $(error).appendTo($("#" + objAfter));
            return;
        }
        $(error).insertAfter($("#" + objAfter));

    },
    errorCode: {
        0: "该用户名已存在，请重新创建！"
    },
    getGroupList: function () {
        var server = $("#serverSelect option:selected").val();
        layer.load(1, {
            shade: [0.5, '#393D49']
        });
        this.updateService(server);
        $.ajax({
            type: 'GET',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            url: 'orgUserControl.do?getGroupListByService&server=' + server,
            success: function (result) {
                var b = result.success;
                addUser.updateGroupList(result.obj);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    },
    updateService: function (id) {
        var target = this.serviceListCache[id];
        $("#chargeMode").html('');
        var str = '';
        if (target["hasFree"] != null && target["hasFree"]) {
            var freeDays = target["freeDate"] == 0 ? '无限制' : target["freeDate"] + "个月";
            str += '<option value="0" data-price="0">免费   ' + freeDays + '</option>';
        }
        if (target["hasTime"] != null && target["hasTime"]) {
            str += '<option value="1" data-price="'+target["timePrice"]+'">按次   ' + ((target["timePrice"]) / 100).toFixed(2) + '元/次</option>';
        }
        if (target["hasMonth"] != null && target["hasMonth"]) {
            str += '<option value="2" data-price="'+target["monthPrice"]+'">按月   ' + ((target["monthPrice"]) / 100).toFixed(2) + '元/月</option>';
        }
        if (target["hasYear"] != null && target["hasYear"]) {
            str += '<option value="3" data-price="'+target["yearPrice"]+'">按年   ' + ((target["yearPrice"]) / 100).toFixed(2) + '元/年</option>';
        }
        $(str).appendTo($("#chargeMode"));

    },
    updateGroupList: function (data) {
        var str = '';
        for (var index in data) {
            var item = data[index];
            str += '<option value="' + item.id + '">' + item.name + '</option>'
        }
        $("#groupSelect").html(str);
    },
    submitForm: function () {
        $("body #error").each(function () {
            $(this).remove();
        });
        var isReturn = false;
        var userName = $("#account").val();
        if (!checkUtils.checkLegalUserName(userName)) {
            this.createErrorLabel("用户名只能包含字母、数字", "account");
            isReturn = true;
        }
        if (userName.length < 6) {
            this.createErrorLabel("用户名不能少于6位", "account");
            isReturn = true;
        } else if (userName.length > 16) {
            this.createErrorLabel("用户名不能超过16位", "account");
            isReturn = true;
        }
        var password = $("#psw").val();
        if (!checkUtils.checkLegalPsw(password)) {
            this.createErrorLabel("密码长度在6-16之间，只能包含字符、字母和数字以及部分特殊字符", "psw");
            isReturn = true;
        }
        var confirmPwd = $("#confirm_psw").val();
        if (password != confirmPwd) {
            this.createErrorLabel("2次输入密码不一样，请重新输入", "confirm_psw");
            isReturn = true;
        }
        var realName = $("#name").val();
        if (realName == '') {
            this.createErrorLabel("姓名不能为空", "name");
            isReturn = true;
        } else {
            if (realName.length > 10) {
                this.createErrorLabel("真实姓名不能超过10位", "name");
                isReturn = true;
            }
        }
        var sex = customRadio.getResult('sexSelect') == 0 ? sex = false
            : sex = true;
        var phone = $("#phone").val();
        if (!checkUtils.checkPhone(phone)) {
            this.createErrorLabel("请输入合法的手机号码", "phone");
            isReturn = true;
        }
        if (isReturn)
            return;
        var json = {
            userName: encodeStr(userName),
            password: encodeStr(password),
            realName: realName,
            sex: sex,
            mobile: phone
        };
        var serveId = $("#serverSelect option:selected").attr("data-id");
        if (serveId != undefined) {// 表示有开通过服务
            json.serveId = serveId;
            var t=$("#chargeMode option:selected");
            var chargeMode=parseInt(t.val());
            var price=t.attr("data-price");
            if(chargeMode!=undefined)
                json.chargeMode=chargeMode;
            if(price!=undefined)
                json.price=parseInt(price);
            var count=parseInt($("#countNumber").val());
            if(count!=undefined)
                json.count=count;
            var groupId = $("#groupSelect option:selected").val();
            if (groupId == undefined) {
                layer.msg("未知错误");
                return;
            }
            json.groupId = groupId;
        }
        layer.load(1, {
            shade: [0.5, '#393D49']
        });
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: addUser.url,
            data: JSON.stringify(json),
            success: function (result) {
                var b = result.success;
                if (!b) {
                    var message = addUser.errorCode[result.msg];
                    switch (result.msg) {
                        case "0":
                            addUser.createErrorLabel(message, "account");
                            break;
                    }
                    return;
                }
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = 'orgUserControl.do?userManage';
                }, 1000);
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }
}