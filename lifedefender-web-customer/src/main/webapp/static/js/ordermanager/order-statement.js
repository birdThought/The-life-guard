(function() {
    angular.module('Controllers', [])
    .controller('statementController', ['$scope', function($scope) {
        $scope.init = function() {
            layui.use(['laypage'], function() {
                $scope.data.layer.laypage = layui.laypage;
                $scope.listData();
            });
        }
        
        $scope.data = {
            layer: {
                form: null
            }
        }
        
        $scope.listData = function() {
            var url = '/order/statement/data/list/' + $scope.page.pageIndex;
            http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                $scope.$apply(function() {
                    var totalSize = result.totalSize;
                    var pageIndex = result.nowPage;
                    // 如果查询结果是有数据，并且总数据量发生改变，就初始化分页
                    if (($scope.page.totalSize != 0 || totalSize > 0) && ($scope.page.totalSize != totalSize)) {
                        $scope.page.totalSize = totalSize;
                        $scope.page.pageIndex = pageIndex;
                        $scope.page.initPage();  // 初始化分页
                    }
                    // 更新门店服务列表的数据
                    $scope.dataList = result.data;
                });
            });
        }
        
        /** 分页相关 */
        $scope.page = {
            // 参数
            pageIndex: 1,
            pageSize: 10,
            totalSize: 0,
            // 初始化分页工具
            initPage: function() {
                $scope.data.layer.laypage.render({
                    elem: 'page',
                    count: $scope.page.totalSize,
                    limit: $scope.page.pageSize,
                    theme: '#00bfff',
                    layout: ['count', 'prev', 'page', 'next', 'skip'],
                    jump: function(obj, first) {
                        if(!first) {
                            $scope.$apply(function () {
                                $scope.page.pageIndex = obj.curr;
                                $scope.service.listOrder();
                            });
                        }
                    }
                });
            }
        }
    }]);
}());