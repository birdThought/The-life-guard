/**
 * 会员管理-会员列表
 * author: wenxian.cai
 * date: 2017/10/25 11:31
 */

registerController.controller('vipMemberController',['$scope',function ($scope) {

    /** 声明参数*/

    $scope.vipMembers = null;
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0,
    }
    $scope.conditions = {
        isEndTime: null,
        gender: null,
        startAge: null,
        endAge: null,
        vipComboId: null,
        status: "0",
        keyword: null,
        age: null,
    }
    $scope.vipCombo = null;

    /** 声明函数 */

    /*初始化*/
    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(0).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(0).addClass('layui-this');

        $scope.listVipMembers();
        $scope.initPage();
        $scope.listVipCombo();
    }

    /*获取用户列表*/
    $scope.listVipMembers = function () {
        var url = '/vip-member/vip-members/' + $scope.page.pageIndex;
        var data = Object.assign({}, data, $scope.conditions);
        data.isEndTime = data.isEndTime == false ? null : data.isEndTime;
        try {
            if (data.age != null && data.age != '') {
                data.startAge = data.age.split('-')[0];
                data.endAge = data.age.split('-')[1];
            }
        } catch (e){

        }
        delete data.age;

        http.ajax.get(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
                $scope.initPage();
            }
            $scope.userList = result.obj.data;
            $scope.page.totalSize = result.obj.totalSize;
            $scope.page.pageIndex = result.obj.nowPage;
            $scope.$apply();

        });
    }

    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    ,count: $scope.page.totalSize
                    ,limit: $scope.page.pageSize
                    ,theme: '#00bfff'
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump: function(obj, first){
                        if(!first){
                            $scope.page.pageIndex = obj.curr;
                            $scope.listVipMembers();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                ,count: $scope.page.totalSize
                ,limit: $scope.page.pageSize
                ,theme: '#00bfff'
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        $scope.page.pageIndex = obj.curr;
                        $scope.listVipMembers();
                    }
                }
            });
        }

    }

    /*查询*/
    $scope.search = function () {
        $scope.page.pageIndex = 1;
        $scope.listVipMembers();
    }

    /*获取套餐列表*/
    $scope.listVipCombo = function () {
        var url = '/vip-card/vip-combo';
        http.ajax.get_no_loading(true, true, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.vipCombo = result.obj;
            $scope.$apply();
        });
    }


    /** 参数监听 */
    var watch = $scope.$watch('conditions.gender', function (newValue,oldValue, scope) {
        console.log('new:', newValue);
        console.log('old:', oldValue);
    })

}]);