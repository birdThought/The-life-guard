
registerController.controller('backLogController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 15,
        totalSize: 0
    }
    $scope.results = null;


    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.findByBack();
        $scope.initPage();
    }


    $scope.findByBack = function () {
        var url = '/pv/back/login/' + $scope.page.pageIndex;
        http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
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
                            $scope.findByBack();
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
                        $scope.findByBack();
                    }
                }
            });
        }
    }

}]);

registerController.controller('operaController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 15,
        totalSize: 0
    }
    $scope.results = null;

    $scope.type = -1;
    $scope.realName =null;
    $scope.datas = null;

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.findByOpera();
        $scope.initPage();
    }


    $scope.findByOpera = function () {
        var url = '/pv/back/visit/' + $scope.page.pageIndex;
        var data ={
            type : $scope.type,
            realName : $scope.realName,
            date : $scope.datas
        }
        http.ajax.post(true, true, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
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
                            $scope.findByOpera();
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
                        $scope.findByOpera();
                    }
                }
            });
        }
    }

    $scope.search = function(){
        this.findByOpera();
    }

    $scope.lookDetails = function(data) {
        layer.alert(data)
    }

}]);