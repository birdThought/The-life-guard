
/** 用户列表*/
registerController.controller('memberHxController',['$scope', function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数

    };
    $scope.results = {};


    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findByHxdatalists();
    }


    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    , count: $scope.page.totalSize
                    , limit: $scope.page.pageSize
                    , theme: '#00bfff'
                    , layout: ['count', 'prev', 'page', 'next', 'skip']
                    , jump: function (obj, first) {
                        if (!first) {
                            $scope.page.pageIndex = obj.curr;
                            $scope.findByHxdatalists();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                , count: $scope.page.totalSize
                , limit: $scope.page.pageSize
                , theme: '#00bfff'
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                        $scope.page.pageIndex = obj.curr;
                        $scope.findByHxdatalists();
                    }
                }
            });
        }

    }

    $scope.findByHxdatalists = function () {
        var url = "/member/data/hx/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, $scope.search, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;

            })
        })
    }


    $scope.regist = function (code) {
        var url = "/member/data/hx/code/" + code;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {

        })
    }

    $scope.registAll = function () {
        url ="/member/data/hx/ids";
        http.ajax.post(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
        })
    }

}]);