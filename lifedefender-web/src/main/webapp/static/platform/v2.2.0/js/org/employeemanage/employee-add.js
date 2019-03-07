/**
 * 添加员工
 * @Author wenxian.cai
 * @Date 2017/6/7 10:23
 */

var employee = {};

/**初始化*/
employee.init = function () {

    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';

    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.employee-image-show-1').attr('src', results.obj);
        $('.employee-image-hidden-1').val(results.obj);
    });

    var element = '.path-2';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.employee-image-show-2').attr('src', results.obj);
        $('.employee-image-hidden-2').val(results.obj);
    });

    var element = '.path-3';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.employee-image-show-3').attr('src', results.obj);
        $('.employee-image-hidden-3').val(results.obj);
    });

    var element = '.path-4';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.employee-image-show-4').attr('src', results.obj);
        $('.employee-image-hidden-4').val(results.obj);
    });
}

/**vm实例*/
employee.vm = new Vue({
    el: '.vue-employee-add',
    data: {
        results: null,
    },
    methods: {
        /**上传新添员工数据*/
        addEmployee: function () {
            var json = $('.employee-add-form').serializeJSON();

            if (json.photo == '') {
                layer.msg('请选择头像', {offset: '50%'}
                );
                return;
            }
            if ($.trim(json.realName) == '') {
                layer.msg('请填写您的真实姓名', {offset: '50%'});
                return;
            }
            if ($.trim(json.idCard) == '') {
                layer.msg('请填写您的身份证号码', {offset: '50%'});
                return;
            }
            if (!/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test($.trim(json.idCard))) {
                layer.msg('身份证号码不合法', {offset: '50%'});
                return;
            }
            if ($.trim(json.gender) == '') {
                layer.msg('请选择您的性别', {offset: '50%'});
                return;
            }
            if ($.trim(json.birthday) == '') {
                layer.msg('请填选择您的出生日期', {offset: '50%'});
                return;
            }

            if (json.idCardPicOne == '') {
                layer.msg('请选择身份证照', {offset: '50%'});
                return;
            }

            if (json.idCardPicTwo == '') {
                layer.msg('请选择身份证照', {offset: '50%'});
                return;
            }

            if (json.userType == '') {
                layer.msg('请选择申请类型', {offset: '50%'});
                return;
            }
            if (json.professionalName == '') {
                layer.msg('请选择职称', {offset: '50%'});
                return;
            }
            if (json.professionalPic == '') {
                layer.msg('请选择职业资格证照', {offset: '50%'});
                return;
            }
            if ($.trim(json.userName) == '') {
                layer.msg('请填写登录名', {offset: '50%'});
                return;
            }
            if (!/^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,17}$/.test($.trim(json.userName))) {
                layer.msg('用户名不合法', {offset: '50%'});
                return;
            }
            if(json.initPassword.indexOf(" ") != -1 || json.password.indexOf(" ") != -1){
                layer.msg('密码不能含有空格', {offset: '50%'});
                return;
            }
            if ($.trim(json.initPassword) == '') {
                layer.msg('请填写登录密码', {offset: '50%'});
                return;
            }
            if ($.trim(json.initPassword).length < 6) {
                layer.msg('密码不能小于6位字符', {offset: '50%'});
                return;
            }
            if ($.trim(json.password) == '') {
                layer.msg('请确认登录密码', {offset: '50%'});
                return;
            }
            if (json.mobile == '') {
                layer.msg('请填写手机号码', {offset: '50%'});
                return;
            }
            if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(json.mobile)) {
                layer.msg('请填写有效的手机号码', {offset: '50%'});
                return;
            }
            if (json.initPassword != json.password) {
                layer.msg('两次填写的密码不一致', {offset: '50%'});
                return;
            }
            delete json.initPassword;
            layer.confirm("确认上传备案?", {'offset': '50%'}, function () {
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    url: '/org/employee?addemployee',
                    data: JSON.stringify(json),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        layer.msg(result.msg, {offset: '50%'});
                        if(result.success){
                            setTimeout(function () {
                                window.location.href = '/org/employee';
                            }, 1000);
                        }
                    }
                });
            })

        },

        /**点击图片触发事件*/
        popupImg: function (type) {
            switch (type) {
                case 1:
                    $('.path-1').click();
                    break;
                case 2:
                    $('.path-2').click();
                    break;
                case 3:
                    $('.path-3').click();
                    break;
                case 4:
                    $('.path-4').click();
                    break;
            }
        },
    },
    computed: {

    },
    watch: {

    }
});