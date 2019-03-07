var UserInfo = {};

UserInfo.init = function() {
    console.log("Hello, you entry the userInfo init method.");
    layui.use('form', function() {
        var form = layui.form();
        form.on('submit(*)', function(data) {
            data = JSON.stringify(data.field);
            console.log(data);
            UserInfo.vm.modifyBaseUserInfo(data);
            return false;
        });
        form.verify({
            expertise: function(value) {
                if (value.length > UserInfo.vm.expertiseMaxLength) {
                    return '专业特长内容不能超过' + UserInfo.vm.expertiseMaxLength + '个字符';
                }
            },
            about: function(value) {
                if (value.length > UserInfo.vm.aboutMaxLength) {
                    return '个人简介内容不能超过' + UserInfo.vm.aboutMaxLength + '个字符';
                }
            }
        });
    });
}

UserInfo.vm = new Vue({
    el: '.vue-content',
    data: {
        userId:0,
        expertise: null,
        expertiseLength: 0,
        expertiseMaxLength: 200,
        about: null,
        aboutLength: 0,
        aboutMaxLength: 200,
        mobile: null,
        email: null,
        address: null,
        gender: null,
        male: false,
        birthday: null,
        cCode: null,
        city: null,
        dCode: null,
        district: null
    },
    methods: {
        modifyBaseUserInfo: function(data) {
            $.ajax({
                async : true,
                cache : false,
                type: 'PUT',
                url: '/org/profile',
                data: data,
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if (result.success) {
                        layer.open({
                            title: ['提示', 'text-align: left'],
                            content: "<p style='text-align:center;'>更新成功</p>",
                            btn: 0,
                            time: 3000,
                            shadeClose: true
                        });
                    }
                }
            });
        },
        bindMobile: function() {
            window.location.href = '/org/profile/services/mobile';
        }
    },
    watch: {
        expertise: function(curVal, oldVal) {
            if (this.expertise != "") {
                var s = this.expertise;
                this.expertiseLength = s.length;
                if (this.expertiseMaxLength - this.expertiseLength < 0) {
                    this.expertise = oldVal;
                }
            }
        },
        about: function(curVal, oldVal) {
            if (this.about != "") {
                var s = this.about;
                this.aboutLength = s.length;
                if (this.aboutMaxLength - this.aboutLength < 0) {
                    this.about = oldVal;
                }
            }
        },
        gender: function() {
            var gender = this.gender;
            if (gender == "false") {
                this.male = false ; //女的
            } else {
                this.male = true;
            }
        }
    }
});