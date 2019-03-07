/*实例化一个模块用来管理所有的控制器*/

var registerController = angular.module("Controllers",[])
/*angular依赖注入*/
.controller('indexController',["$location", "$scope", "userInfo", "$rootScope", function ($location, $scope, $userInfo, $rootScope) {

    $scope.userInfo = {};

    /*初始化*/
    $scope.init = function () {

        $userInfo.getUserInfo(function (user) {
            $scope.userInfo = user;
            $rootScope.userInfo = {
                realName: user.name,
                userName: user.userName,
                head: user.photo,
                id: user.id,
                userCode: user.userCode,
                status: user.status,
                roles: user.roles,
                perms: user.perms
                ,agentId:user.agentId
                ,agentNum:user.agentNum
            };
            //登录环信
            common.initHuanXin({userCode: user.userCode, photo: user.photo, nick: user.name});
        });
        
        $scope.includeChatDialog = true;
    }
    /*菜单链接*/
    $scope.go = function (type) {
        $location.url(type);
    }
    /** 显示聊天窗口 */
    $scope.showChatDialog = function() {
        chat.initChatDialog([], $rootScope.userInfo, null, null);
    }
}]);
