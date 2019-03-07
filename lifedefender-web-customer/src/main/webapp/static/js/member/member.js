
/** 用户列表*/
registerController.controller('memberController',['$scope', function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 15,
        totalSize: 0,//总数

    };
    $scope.results = {};
    $scope.obj = null; //健康数据
    $scope.nowDate = null; //当前时间
    $scope.nowUserId = null; //选中的用户
    $scope.search = {
        userName: null,
        realName: null,
        orgName: null,
        mobile: null,
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
        var url = "/member/data/list/" + $scope.page.pageIndex;
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

    $scope.openHealthDialog = function (userId) {
        $scope.nowDate = new Date().Format("yyyy-MM-dd");
        $scope.nowUserId = userId;
        url ="/member/data/DeviceData";
       var  data ={
           nowDate : $scope.nowDate,
           userId : $scope.nowUserId
        }
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function () {
                $scope.obj = result.obj;
                console.log($scope.obj)
            })
        })
        layer.open({
            type: 1,
            title: ['健康数据', 'text-align:center;font-size:16px;background:#fff;'],
            area: ['600px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content')
        });
    }

    $scope.searchdata = function () {
        $scope.findBydatalists();
    }
    $scope.switchDate = function (isNext) {
        var s = isNext ? 1 : -1;
        var newDate = new Date(String($scope.nowDate));
        newDate.setDate(newDate.getDate() + s);
        if (newDate.isAfter(new Date())) {
            layer.msg("很抱歉，无法提供未来的数据");
            return;
        }
        $scope.nowDate = newDate.Format("yyyy-MM-dd");
        var  data ={
            nowDate : $scope.nowDate,
            userId : $scope.nowUserId
        }
        url ="/member/data/DeviceData";
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function () {
                $scope.obj = result.obj;
                console.log($scope.obj)
            })
        })
    }

}]);