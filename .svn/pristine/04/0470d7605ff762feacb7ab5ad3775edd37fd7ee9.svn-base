var BindMobile = {};

BindMobile.init = function() {
    layui.use('form', function() {
        var form = layui.form();
        form.on('submit(*)', function(data) {
            var data = JSON.stringify(data.field);
            layer.msg("1");
            BindMobile.vm.commit(data);
            return false;
        });
    });
}

BindMobile.vm = new Vue({
    el: '.vue-content',
    data: {
        mobile: "",
        password: "",
        code: "",
        oldMobile: ""
    },
    watch: {
        
    },
    methods: {
        commit: function(data) {
            $.ajax({
                type: 'PUT',
                data: data,
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                url: '/org/profile/mobile',
                beforeSend: function() {
                    layer.load();
                },
                success: function(result) {
                    layer.closeAll('loading');
                    layer.msg(result.msg);
                    if (result.success) {
                        layer.msg("修改成功");
                        BindMobile.vm.oldMobile = result.obj;
                    }
                }
            });
        },
        sendCode: function() {
            if (this.mobile == "") {
                layer.msg("手机号码不能为空", {icon: 5}, function() {
                    $("[name='mobile']").focus();
                });
                return;
            }
            if (!/^1\d{10}$/.test(this.mobile)) {
                layer.msg("请输入正确的手机号", {icon: 5}, function() {
                    $("[name='mobile']").focus();
                });
                return;
            }
            $.ajax({
                type: 'POST',
                data: 'mobile=' + BindMobile.vm.mobile + "&cache=mobile",
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                url: '/register?sendValidCode',
                beforeSend: function() {
                    layer.load();
                },
                success: function(result) {
                    layer.closeAll("loading");
                    layer.msg(result.msg);
                }
            });
        }
    }
});