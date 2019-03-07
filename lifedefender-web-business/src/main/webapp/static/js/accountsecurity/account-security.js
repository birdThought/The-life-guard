/**
 * 账户安全
 * author: wenxian.cai
 * date: 2017/10/28 15:25
 */


registerController.controller('accountSecurityController',['$scope', '$rootScope', function ($scope, $rootScope) {
    /** 声明参数*/

    $scope.oldPassword = null;
    $scope.newPassword = null;
    $scope.confirmPassword = null;

    /** 申明函数 */
    /*初始化*/
    $scope.init = function () {
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav').find('.layui-nav-item').eq(3).addClass('layui-this');

        setTimeout(function () {
            $('.content-left ul li').eq(4).click();
        }, 100)
    }

    /*更新密码*/
    $scope.updatePassword = function () {
        if ($.trim($scope.oldPassword) == '') {
            layer.msg('请输入原密码!')
            return;
        }
        if ($.trim($scope.newPassword) == '') {
            layer.msg('请输入新密码!')
            return;
        }
        if ($.trim($scope.confirmPassword) == '') {
            layer.msg('请确认密码!')
            return;
        }
        if ($.trim($scope.newPassword) != $.trim($scope.confirmPassword)) {
            layer.msg('两次输入的密码不一致!')
            return;
        }
        if ($.trim($scope.newPassword).length < 6 || $.trim($scope.oldPassword).length < 6
        || $.trim($scope.confirmPassword).length < 6) {
            layer.msg('密码至少6位!')
            return;
        }
        layer.confirm('确认修改密码?', function () {
            var url = '/account-security/password';
            var data = {
                oldPassword: $scope.oldPassword,
                newPassword: $scope.newPassword
            }
            http.ajax.patch(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function (result) {
                if (result.success) {
                    layer.msg('修改密码成功')
                    layer.closeAll('page');
                }
            })
        })
    }

    /** 参数监听 */
}]);