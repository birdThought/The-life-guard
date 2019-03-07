
/** 用户列表*/
registerController.controller('memberC3Controller',['$scope', function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数

    };
    $scope.results = {};

    $scope.search = {
        userName: null,
        status: -1,
        imei: null,
        createDate: null,
        condition:-1,
    };

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findByC3datalists();
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
                            $scope.findByC3datalists();
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
                        $scope.findByC3datalists();
                    }
                }
            });
        }

    }

    $scope.findByC3datalists = function () {
        var url = "/member/data/c3/" + $scope.page.pageIndex;
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

    $scope.findByDate = function () {
        layui.use('laydate',function(){
            var laydate = layui.laydate;
            laydate.render({
                elem:'#endDate',
                done:function(val,date){
                    $scope.search.createDate =val;
                }
            })
        })
    }

    $scope.searchs = function () {
        $scope.findByC3datalists();
    }

}]);