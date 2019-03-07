var OrderComment = {};

OrderComment.init = function() {
    console.log("Hello, you entry the orderComment init method.");
    layui.use('form', function() {
        var form = layui.form();
        form.on('submit(*)', function(data) {
            if (!OrderComment.vm.replied) {
                console.log(data.field);
                OrderComment.vm.submitReply(JSON.stringify(data.field));
            }
            return false;
        });
        form.verify({
            reply: function(value) {
                if (value.length > 500) {
                    return '回复内容长度不能超过500个字符';
                }
            }
        });
    });
}

OrderComment.vm = new Vue({
    el: '.vue-content',
    data: {
        orderId: null,
        comment: null,
        reply: null,
        image: [],
        score: null,
        replied: true
    },
    methods: {
        submitReply: function(data) {
            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                url: '/orderManage/ordercomments/' + OrderComment.vm.orderId,
                data: data,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    var content = "";
                    if (result.success) {
                        content = "回复成功";
                        OrderComment.vm.comment = result.obj.comment;
                        OrderComment.vm.replied = result.obj.status;
                    } else {
                        content = "回复失败";
                    }
                    layer.open({
                        title: ['提示', 'text-align: left'],
                        content: content,
                        btn: 0,
                        time: 3000,
                        shadeClose: true
                    });
                }
            });
        }
    },
    watch: {
        score: function() {
            // 点亮星星
            var score = this.score;
            $('.star li:lt('+score+')').addClass('light');
        },
        replied: function() {
            if (!this.replied) {
                $("textarea[name='reply']").removeAttr("disabled");
                $("#spanBtn").text("回复");
            }
            if (this.replied) {
                $("textarea[name='reply']").attr("disabled", "disabled");
                $("#spanBtn").text("已回复");
            }
        }
    }
});