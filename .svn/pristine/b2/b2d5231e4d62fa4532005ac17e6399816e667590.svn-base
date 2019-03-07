registerController.controller('settleController',['$scope','$rootScope',function($scope,$rootScope){
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.record ={
        moons:null
    }
    /** 结算数据 */
    $scope.recordList = null;
    /** 详情数据*/
    $scope.spreadList = null;
    $scope.init =function () {
        $scope.initPage();
        $scope.dateSelect();
        $scope.getRecordDataList();
    }

    $scope.getRecordDataList = function () {
        var url = "/settle/data/"+$scope.page.pageIndex;
        var data = {
            moons:$scope.record.moons
        }
        http.ajax.post(true,false,url,data,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.recordList = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }

    $scope.openRecord =function (date) {
        layer.open({
            type: 1,
            title: ["查看明细", 'text-align:center;font-size:16px;background:#fff;'],
            area: ['760px', '360px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            content: $('#settle_record'),
            success:function () {
                if($scope.spreadList == null){
                    $scope.getSpreadComboData(date,function (result) {
                        $scope.spreadList = result.obj;
                        console.log($scope.spreadList);
                    })
                }
            }
        });
    };

    $scope.getSpreadComboData = function (date,callBack) {
        var url = "/settle/obtain";
        var data ={
            moons:date
        }
        http.ajax.post(true,false,url,data,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function () {
                callBack(result);
            })
        })
    };

    $scope.del = function () {
        layer.confirm("暂不支持删除操作！！！");
    }

    $scope.show = function () {
        this.getRecordDataList();
    }

    $scope.dateSelect=()=>{
        layui.use(['form','laydate'],()=>{
            const laydate = layui.laydate;
        laydate.render({
            elem: '#dateSelect',type: 'month',
            value: new Date()
            ,done: function(value, date, endDate){
                $scope.record.moons = value;
                 }
             })
        });
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
                            $scope.getRecordDataList();
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
                        $scope.getRecordDataList();
                    }
                }
            });
        }
    }
}])