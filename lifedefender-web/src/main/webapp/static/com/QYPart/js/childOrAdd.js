define(function (require, exports, module) {
    require('QYcommon');
    require.async(['commonCheck', 'fileUpload']);
});

var jigouType = {
    seletType: "management",
    select: function (target) {
        this.seletType = $(target).parent().parent().attr("id");
        var obj = $("#" + this.seletType);
        obj.removeClass("select_box");
        obj.addClass("select_box_action");
        obj.find($(".checkBox")).html(
            '<img src="static/images/selected.png" />');
        var other = $("#" + this.seletType).siblings("div");
        other.removeClass("select_box_action");
        other.addClass("select_box");
        other.find($(".checkBox")).html("");
    },
    getType: function () {// 获取返回的机构选择类型
        return this.seletType;
    }
}

var areaUtils = {// 地区变化类
    provinceChange: function () {
        var provinceCode = $("#province option:selected").val();
        provinceCode = provinceCode.substr(0, 2);
        $.ajax({
            type: 'GET',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            url: 'commonControl.do?getCity',
            data: 'provinceCode=' + provinceCode,
            success: function (result) {
                areaUtils.setCityDatas(result);
            }
        });
    },
    cityChange: function () {
        var cityCode = $("#city option:selected").val();
        cityCode = cityCode.substr(0, 4);
        $.ajax({
            type: 'GET',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            url: 'commonControl.do?getDistrict',
            data: 'cityCode=' + cityCode,
            success: function (result) {
                areaUtils.setDistrictDatas(result);
            }
        });
    },
    setCityDatas: function (data) {
        var dis = data.obj;
        var cits = data.attributes['city'];
        if (cits == '') {
            $("#city").html(
                '<option selected="selected" value="'
                + $("#province option:selected").val() + '">'
                + $("#province option:selected").text()
                + '</option>');
        } else {
            var str = "";
            for (var key in cits) {
                if (key == 0) {
                    str += '<option selected="selected" value="'
                        + cits[key].code + '">' + cits[key].name
                        + '</option>';
                    continue;
                }
                str += '<option value="' + cits[key].code + '">'
                    + cits[key].name + '</option>';
            }
            $("#city").html(str);
        }
        this.setDistrictDatas(dis);
    },
    setDistrictDatas: function (data) {
        if (data == '') {
            $("#district").html(
                '<option selected="selected" value="'
                + $("#city option:selected").val() + '">'
                + $("#city option:selected").text() + '</option>');
        } else {
            var str = "";
            for (var key in data) {
                if (key == 0) {
                    str += '<option selected="selected" value="'
                        + data[key].code + '">' + data[key].name
                        + '</option>';
                    continue;
                }
                str += '<option value="' + data[key].code + '">'
                    + data[key].name + '</option>';
            }
            $("#district").html(str);
        }
    }
}

var licenceFileName = null;

var fileUtils = {
    openFile: function (obj) {
        obj = document.getElementById(obj);
        obj.click();
    },
    fileChange: function (objId, target) {
        this.upload(objId, target);
    },
    upload: function (objId, target) {
        objId = document.getElementById(objId);
        fileUpload({
            id: objId,
            paramName: 'path',
            url: 'commonControl/uploadFile/img.do',
            limit: 3072,// 3MB
            error: function (result) {
                switch (result) {
                    case 0:
                        layer.msg("请上传正确的图片");
                        break;

                    case 1:
                        layer.msg("图片大小不能超过3MB");
                        break;
                }
            },
            fail: function () {
                layer.msg("上传失败");
                switch (target) {
                    case 1:
                        licenceFileName = null;
                        card.src = '';
                        break;
                }
            },
            progress: function (pro) {

            },
            callback: function (json) {
                var file = objId.files[0];
                var reader = new FileReader();
                reader.readAsDataURL(file);
                switch (target) {
                    case 1:// 执照
                        licenceFileName = json.obj;
                        reader.onload = function (e) {
                            card.src = this.result;
                        }
                        break;
                }
            }
        });
    }
}

var addOrganization = {
    url: 'orgControl.do?addChildOrg',
    errorCode: {
        0: "该机构名称已存在，请重新创建！",
        1: "该用户名已存在，请重新创建！"
    },
    createErrorLabel: function (text, objAfter) {
        var error = document.createElement("label");
        $(error).attr({"id": "error", "style": "margin-left:15px;color:#ff0000;"});
        $(error).text(text);
        $(error).insertAfter($("#" + objAfter));
    },
    submitForm: function () {// 提交信息
        $("body #error").each(function () {
            $(this).remove();
        });
        var isReturn = false;
        var selectType = jigouType.getType() == "management" ? 0 : 1;

        var name = $.trim($("#orgName").val());
        if (name == '') {
            this.createErrorLabel("机构名称不能为空！", "orgName");
            isReturn = true;
        }
        if (name.length > 20) {
            this.createErrorLabel("机构名称不能大于20个字！", "orgName");
            isReturn = true;
        }
		if (!checkUtils.checkOrgName(name)) {
			this.createErrorLabel("机构名称只能包含字母，数字与中文", "orgName");
			isReturn = true;
		}
        var xingzhi = $("#orgXZ option:selected").val();
        if (xingzhi == 0) {
            this.createErrorLabel("请选择机构性质", "orgXZ");
            isReturn = true;
        }
        var contact = $("#contact").val();
        if (contact != '' && contact.length > 10) {
            this.createErrorLabel("联系人姓名不能超过10个字", "contact");
            isReturn = true;
        }
        var phone = $("#phone").val();
        var district = $("#district option:selected").val();
        var p = district.substr(0, 2);
        var c = district.substr(2, 2);
        var d = district.substr(4, 2);
        var road = $("#road").val();
        if (road != '') {
            if (road.length > 70) {
                this.createErrorLabel("详细地址不能超出70个字", "road");
                return;
            }
        }
        var userName = $.trim($("#account").val());
        var password = $("#psw").val();
        var confirmPassword = $("#confirm_psw").val();
        if (password != confirmPassword) {
            this.createErrorLabel("2次输入的密码不一样", "confirm_psw");
            isReturn = true;
        }
        if (isReturn)
            return;
        var json = {};
        json.type = selectType;

        if (licenceFileName != null)
            json.businessLicense = licenceFileName;
        if (name != '')
            json.orgName = name;
        json.orgType = xingzhi;
        if (contact != '')
            json.contact = contact;
        if (phone != '') {
            if (!checkUtils.checkPhone(phone)) {
                layer.msg("请输入正确的手机号码");
                this.createErrorLabel("请输入正确的手机号码", "phone");
                return;
            }
            json.contactInformation = phone;
        }

        json.province = p;
        json.city = c;
        json.district = d;
        if (road != '')
            json.street = road;
        if (!checkUtils.checkLegalUserName(userName)) {
            layer.msg("用户名只能包含字母、数字");
            this.createErrorLabel("用户账户只能包含字母或数字", "account");
            return;
        }
        if (userName.length < 4) {
            this.createErrorLabel("用户名不能少于4位", "account");
            return;
        } else if (userName.length > 30) {
            this.createErrorLabel("用户名不能超过30位", "account");
            return;
        }

        if (!checkUtils.checkLegalPsw(password)) {
            layer.msg("密码长度在6-16之间，只能包含字符、字母和数字");
            this.createErrorLabel("密码长度在6-16之间，只能包含字符、字母和数字", "psw");
            return;
        }
        json.userName = encodeStr(userName);
        json.password = encodeStr(password);
        layer.load(1, {shade: [0.5, '#393D49']});
        $.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            url: addOrganization.url,
            data: json,
            success: function (result) {
                var b = result.success;
                if (!b) {
                    var message = addOrganization.errorCode[result.msg];
                    layer.msg(message);
                    switch (result.msg) {
                        case "0":
                            addOrganization.createErrorLabel(message, "orgName");
                            break;
                        case "1":
                            addOrganization.createErrorLabel(message, "account");
                            break;
                    }
                    return;
                }

                layer.msg("创建成功！");
                setTimeout(function () {
                    window.location.href = "orgControl.do?orgManage";
                }, 1000);
            }, complete: function () {
                layer.closeAll("loading");
            }
        });
    }
}