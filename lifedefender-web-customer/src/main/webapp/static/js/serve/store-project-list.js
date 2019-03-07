(function() {
    angular.module('Controllers', [])
    .controller('storeProjectController', ['$scope', function($scope) {
        
        $scope.data = {
            storeProjectList: [], // 门店服务列表
            serveList: [],  // 服务列表
            condition: {
                orgName: null,
                classifyName: null,
                serveCode: null
            },
            layer: {
                form: null,
                laypage: null
            }
        }
        
        $scope.fn = {
            init: function() {
                layui.use(['form', 'laypage'], function() {
                    var form = layui.form;
                    var laypage = layui.laypage;
                    
                    form.on('select(serveList)', function(data) {
                        var value = data.value;
                        // if (value.startsWith("string:")) {
                        //     var serveCode = value.split(":")[1];  // 得到被选中的值
                        //     $scope.data.condition.serveCode = serveCode;
                        // }
                        if(value == "")
                            $scope.data.condition.serveCode = null;
                        else
                            $scope.data.condition.serveCode = value;
                    });
                    
                    $scope.data.layer.form = form;
                    $scope.data.layer.laypage = laypage;
                    
                    $scope.service.listServe();
                    $scope.service.listStoreProject();
                    form.render();
                });
            },
            search: function() {
                $scope.page.pageIndex = 1;  // 还原为第一页
                $scope.service.listStoreProject();
            }
        }
        
        $scope.service = {
            listStoreProject: function() {
                var url = "/serve/projectes/" + $scope.page.pageIndex;
                http.ajax.get_not_null(true, false, url, $scope.data.condition, http.ajax.CONTENT_TYPE_1, function(result) {
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
                        $scope.data.storeProjectList = result.data;
                    });
                });
            },
            listServe: function() {
                var url = "/serve/serves";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        $scope.data.serveList = result.data;
                    });
                    // layui-form 渲染
                    $scope.data.layer.form.render('select');
                });
            }
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
                                $scope.service.listStoreProject();
                            });
                        }
                    }
                });
            }
        }
        
    }]);
}());