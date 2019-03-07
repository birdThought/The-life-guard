
/** 用户列表*/
registerController.controller('memberOfflineController',['$scope', function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 15,
        totalSize: 0,//总数

    };
    $scope.results = {};
    $scope.search = {
        realName: null,
        mobile: null
    };
    
    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findBydatalists();
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
                            $scope.findBydatalists();
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
                        $scope.findBydatalists();
                    }
                }
            });
        }

    }

    $scope.findBydatalists = function () {
        var url = "/member/data/offline/list/" + $scope.page.pageIndex;
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
    
    $scope.addOffline = function() {
        window.open("/member/offline/register");  
    }
    
    $scope.searchdata = function(){
        $scope.findBydatalists();
    }
    
}]);