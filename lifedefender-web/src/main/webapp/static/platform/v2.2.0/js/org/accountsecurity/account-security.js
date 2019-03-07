/**
 * 账户安全
 * @Author wenxian.cai
 * @Date 2017/6/14 11:25
 */

var security = {};

/**账户安全初始化*/
security.initSecurity = function () {

}

security.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        user: null,
        orgType: null,
        userType: null,
        pwdReg: /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,16}$/,
    },
    methods: {
        /**修改密码页面*/
        goModifyPassword: function () {
            window.location.href = '/org/profile/services/modifypassword';
        },
        /**更换手机号码*/
        goModifyMobile: function () {
            window.location.href = '/org/profile/services/mobile';
        },
        /**绑定手机号码*/
        goBindMobile: function () {

        },
        /**更换邮箱*/
        goModifyEmail: function () {

        },
        /**绑定邮箱*/
        goBindEmail: function () {

        },

        /**修改密码*/
        modifyPassword: function () {
            var json = $('.form-modify-password').serializeJSON();
            if (json.oldPassword == '') {
                layer.msg("请输入原密码", {icon: 5}, function() {
                    $(".oldPassword").focus();
                });
                return;
            }
            if (json.newPassword == '') {
                layer.msg("请输入新密码", {icon: 5}, function() {
                    $(".newPassword").focus();
                });
                return;
            }
            if (json.oldPassword.length > 20 || json.newPassword.length < 6) {
                layer.msg("密码长度不正确", {icon: 5}, function() {
                    $(".newPassword").focus();
                });
                return;
            }

            if (json.newPassword != json.confirmPassword) {
                layer.msg("两次输入的密码不一致", {icon: 5}, function() {
                    $(".confirmPassword").focus();
                });
                return;
            }
            if (!this.pwdReg.test(json.confirmPassword)) {
                layer.msg("输入密码不合法", {icon: 5}, function() {
                    $(".confirmPassword").focus();
                });
                return;
            }

            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                url: '/org/profile?modifypassword',
                data: JSON.stringify(json),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.msg(result.msg);
                    if (result.success) {
                        setTimeout(function () {
                            window.location.href = '/org/profile/services/accountsecurity';
                        }, 2000);
                    }
                }
            });
        }

    },
    computed: {
        mobile: function () {
            if (this.user != null) {
                return this.user.mobile;
            }
        },
        email: function () {
            if (this.user != null) {
                return this.user.email;
            }
        }
    },
    watch: {

    },
    filters: {

    }
});