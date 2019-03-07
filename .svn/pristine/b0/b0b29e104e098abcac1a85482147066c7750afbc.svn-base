(function() {
    angular.module('Controllers', [])
    .controller('orderCountController', ['$scope', function($scope) {

        $scope.data = {
            orderCountList: [],
            serveList: [],
            condition: {
                serveCode: null,
                start: null,
                end: null,
            },
            layer: {
                laypage: null,
                form: null,
                laydate: null,
            }
        }
        
        $scope.fn = {
            init: function() {
                layui.use(['laypage', 'form', 'laydate'], function() {
                    var laypage = layui.laypage;
                    $scope.data.layer.laypage = laypage;
                    $scope.page.initPage();
                    
                    var form = layui.form;
                    form.on('select(serveList)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("string:")) {
                                var serveCode = value.split(":")[1];  // 得到被选中的值
                                $scope.data.condition.serveCode = serveCode;
                            }
                            if ("" == value) {
                                $scope.data.condition.serveCode = null;
                            }
                        });
                    });
                    
                    $scope.data.layer.form = form;
                    
                    $scope.data.layer.laydate = layui.laydate;
                    $scope.date.render();
                    
                    $scope.service.listServe();
                    $scope.service.listOrderCount();
                });
            },
            search: function() {
                $scope.page.pageIndex = 1;  // 重置为获取第一页的数据
                $scope.service.listOrderCount();
            }
        }
        
        $scope.service = {
            listOrderCount: function() {
                var url = '/order/count/data/' + $scope.page.pageIndex;
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
                        $scope.data.orderCountList = result.data;
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
                                $scope.service.listOrderCount();
                            });
                        }
                    }
                });
            }
        }
        
        $scope.date = {
            render: function() {
                //执行一个laydate实例
                $scope.data.layer.laydate.render({
                    elem: "input[name='date']", //指定元素
                    format: "yyyy年MM月dd日",
                    range: true,
                    done: function(value, start, end) {
                        var sy = start.year;
                        var sm = start.month > 10 ? start.month : "0" + start.month;
                        var sd = start.date > 10 ? start.date : "0" + start.date;
                        
                        var ey = end.year;
                        var em = end.month > 10 ? end.month : "0" + end.month;
                        var ed = end.date > 10 ? end.date : "0" + end.date;
                        $scope.$apply(function() {
                            $scope.data.condition.start = sy + "-" + sm + "-" + sd;
                            $scope.data.condition.end = ey + "-" + em + "-" + ed;
                        });
                    }
                });
            }
        }
    }]);
}());