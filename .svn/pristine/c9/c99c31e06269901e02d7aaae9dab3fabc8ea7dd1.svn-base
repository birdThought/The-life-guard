var customRadio = {
    name:null,//覆盖组
    init: function (data) {
        this.name = data['name'];
        radioOrCheck(data);
    },
    getResult: function (target) {// 获取结果,target为目标name
        var result = [];
        target == undefined ? target = this.name : target = target;
        $("input[name=\"" + target + "\"]").each(function () {
            if ($(this).parent().hasClass("checked")) {
                result.push($(this).val());
            }
        });
        return result;
    }
}

var radioOrCheck = function (data) {
    var name = data['name'];// 组
    var onselect = data['onselect'];// 选择的回调
    var onUnSelect = data['onUnSelect'];//取消选择的回调
    var color = data['color'] == undefined ? 'green' : 'blue';// 默认为绿色
    $("input[name=\"" + name + "\"]").iCheck({
        checkboxClass: 'icheckbox_minimal-' + color,
        radioClass: 'iradio_minimal-' + color,
        increaseArea: '20%' // optional
    });
    $("input[name=\"" + name + "\"]").on('ifChecked', function (event) {
        if (event.type == 'ifChecked') {
            onSelect(event.currentTarget.value);
        }
    });
    $("input[name=\"" + name + "\"]").on('ifUnchecked', function (event) {

        if (event.type == 'ifUnchecked') {
            unSelect(event.currentTarget.value);
        }
    });
    var onSelect = function (value) {
        if (onselect != undefined) {
            onselect(value);
        }
    }
    var unSelect = function (value) {
        if (onUnSelect != undefined) {
            onUnSelect(value);
        }
    }
}