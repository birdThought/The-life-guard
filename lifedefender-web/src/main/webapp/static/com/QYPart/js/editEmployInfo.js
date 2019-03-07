define(function (require, exports, module) {
    require('QYcommon');
    require.async(['icheck', 'customRadio', 'jedate', 'commonCheck', 'fileUpload'], function () {
        jeDate({
            dateCell: "#birth",
            format: "YYYY-MM-DD",
            minDate: "1900-01-01",
            maxDate: jeDate.now(0),
            startMin: "1900-01-01",
            startMax: jeDate.now(0),
            zindex: 999,
            choosefun: function (elem, val) {
            }
        });
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
        customRadio.init({
            name : 'typeSelect',
            color : 'blue',
            onselect : function(result) {
                if (result == 'f') {
                    $("#introduce_div").animate({
                        "height": "show"
                    });
                }
            },
            onUnSelect : function(result) {
                if (result == 'f') {
                    $("#introduce_div").animate({
                        "height": "hide"
                    });
                }
            }
        });
    });
});

var editEmploy = {
    isServeManage: false,
    isServeUser: false,
    isSingleServeManage: false,
    headFileName: null,
    userId:null,
    userType: null,
    uploadFile: function () {
        photoUtil({
            targetId: 'headFile',
            displayId: 'avater',
            paramName: 'path',
            url: 'commonControl/uploadFile/img.do',
            limit: 3072,
            uploadFail: function () {
                editEmploy.headFileName = null;
            },
            uploadSuccess: function (data) {
                editEmploy.headFileName = data.obj;
            }
        });
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
    submitForm: function () {
        $("body #error").each(function () {
            $(this).remove();
        });
        var isReturn = false;
        var type = customRadio.getResult('typeSelect');
        type = type.length == 2 ? type = 2 : type == 'a' ? type = 0
            : type == 'f' ? type = 1 : type = null;
        if (this.isServeManage && !this.isServeUser) {
            if (type == null) {
                this.createErrorLabel("请选择员工类型", "selectType", true);
                isReturn = true;
            }
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
        var sex = customRadio.getResult('sexSelect') == 0 ? false : true;

        var phone = $("#phone").val();
        if (!checkUtils.checkPhone(phone)) {
            this.createErrorLabel("请输入合法的手机号码", "phone");
            isReturn = true;
        }
        var eMail = $.trim($("#e_mail").val());
        if (eMail != '' && !checkUtils.checkEmail(eMail)) {
            this.createErrorLabel("e-mail格式不正确", "e_mail");
            isReturn = true;
        }
        var workPhone = $("#workPhone").val();
        if (workPhone != '') {
            if (!checkUtils.checkPhone(workPhone)) {
                this.createErrorLabel("工作电话必须是手机号或固话", "workPhone");
                isReturn = true;
            }
        }
       /* var addr = $("#addr").val();
        if (addr != '' && addr.length > 80) {
            this.createErrorLabel("家庭地址不能超出80个字符", "addr");
            isReturn = true;
        }*/
        if (isReturn)
            return;
        var json = {
            id:this.userId,
            realName: realName,
            sex: sex,
            mobile: phone
        };
         var birthday = $("#birth").val();
         if (birthday != '') {
             json.birthday=parserDate(birthday);
         }
        if (this.isServeManage) {
            if (type != undefined && type != editEmploy.userType) {
                json.userType = type;
            }
            if (type == 1 || type == 2 || this.isSingleServeManage || this.userType == 1) {
                var about = $("#server_introduce").val();
                var detail = FilterUtils.filterScript(Editor.getContent());
                if (about.length > 100) {
                    layer.alert("服务师简介不能超过100字");
                    return;
                }
                if (about != '')
                    json.about = about;
                if (detail != '')
                    json.detail = detail;
            }
        }
        if (this.headFileName != null)
            json.photo = this.headFileName;
        if (eMail != '') {
            json.email = eMail;
        }
        if (workPhone != '') {
            json.tel = workPhone;
        }
        /* if (addr != '') {
            json.address = addr;
        }*/
        layer.load(1, {
            shade: [0.5, '#393D49']
        });
        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url: 'orgUserControl.do?editEmployPage',
            data: JSON.stringify(json),
            success: function (result) {
                var b = result.success;
                if (b) {
                    layer.msg("编辑成功！");
                    var redirect = result.attributes.redirect;
                    var href = "";
                    if (redirect == 1) {
                        href = 'orgUserControl.do?employManage';
                    }
                    setTimeout(function () {
                        window.location.href = href;
                    }, 1000);
                } else {
                    layer.msg(result.msg);
                }
            },
            complete: function () {
                layer.closeAll("loading");
            }
        });
    }
}

var FilterUtils = {
    filterScript: function (s) {
        return s.replace(/<script.*?>.*?<\/script>/ig, '');
    }
}

var Editor = {
    uEditor: null,
    init: function (data) {
        this.uEditor = UE.getEditor('server_details', {
            toolbars: [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'strikethrough',
                'horizontal', 'removeformat', '|', 'forecolor',
                'backcolor', 'insertorderedlist', 'insertunorderedlist',
                'selectall', '|', 'rowspacingtop', 'rowspacingbottom',
                'lineheight', '|', 'customstyle', 'paragraph',
                'fontfamily', 'fontsize', '|', 'directionalityltr',
                'directionalityrtl', 'indent', '|', 'justifyleft',
                'justifycenter', 'justifyright', 'justifyjustify', '|',
                'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload']],
            enableAutoSave: false,
            enableContextMenu: false,
            elementPathEnabled: false,
            maximumWords: 300,
            imageFieldName: 'path',
            imageActionName: 'uploadimage',
            initialFrameHeight: 200
        });
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function (action) {
            if (action == 'uploadimage') {
                return 'commonControl/uploadFile/img.do';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    },
    getContent: function () {
        return this.uEditor.getContent();
    },setHTMLContent:function(content){
        this.uEditor.ready(function () {
            Editor.uEditor.setContent(content, false);
        });
    }
}

var parserDate = function (date) {
    var t = Date.parse(date);
    if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
    } else {
        return new Date();
    }
};