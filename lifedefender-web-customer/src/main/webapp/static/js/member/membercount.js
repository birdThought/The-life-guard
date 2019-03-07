registerController.controller('memberCountController',['$scope', function ($scope) {

    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数
    };
    $scope.results ={};
    $scope.sum =null;
    $scope.provinces =null;
    $scope.counts = {
        orgName: null,//门店
        province: null,//省
        avgAge: -1,//年龄段
        radioValue: 0,//选择年龄段排序或者省份排序
    };

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.getCountDataList();
        $scope.getProvinces();
        $scope.getGenderSum();
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
                            $scope.getCountDataList();
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
                        $scope.getCountDataList();
                    }
                }
            });
        }

    }
    $scope.getGenderSum = function () {
        var url = "/member/data/count/gender";
        http.ajax.get(true,false,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.sum = result.obj;
                console.log($scope.sum)
            })
        })
    }
    $scope.getCountDataList = function () {
         var url ="/member/data/count/"+ $scope.page.pageIndex;
        http.ajax.post(true, true, url, $scope.counts, http.ajax.CONTENT_TYPE_1, function (result) {
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

    $scope.search = function () {
        this.getCountDataList();
    }
    $scope.getProvinces = function () {
        var url = "/datalist?getProvince";
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.provinces =result.obj;
            })
        })
    };
}]);