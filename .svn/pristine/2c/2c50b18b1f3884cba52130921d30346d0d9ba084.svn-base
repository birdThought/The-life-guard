var bank = {};

bank.init = function() {
    layui.use('form', function() {
        var form = layui.form();
        form.on('submit(*)', function(data) {
            data = JSON.stringify(data.field);
            bank.vm.commit(data);
            return false;
        });
        form.verify({
            accountLength: function(value) {
                var length = value.length;
                if (length < 12 || length > 19) {
                    return "输入内容必须介于12-19之间";
                }
            }
        });
    });
}

bank.vm = new Vue({
    el: '.vue-content',
    data: {
        bankName: null,
        bankAccount: null,
        bankDistrict: null,
        bankBranch: null
    },
    methods: {
        commit: function(data) {
            $.ajax({
                async: true,
                cache: false,
                type: 'POST',
                data: data,
                dataType: 'json',
                url: '/store/finance/bankInfo',
                contentType: "application/json;charset=utf-8",
                beforeSend: function() {
                    layer.load(2);
                },
                success: function(result) {
                    layer.closeAll("loading");
                    if (!result.success) {
                        layer.msg(result.msg, {icon: 2, shade: 0.3});
                        return;
                    }
                    layer.msg(result.msg, {icon: 1, shade: 0.3});
                    setTimeout(function() {
                        location.href = '/store/finance'
                    }, 3000);
                }
            });
        }
    }
});