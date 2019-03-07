(function() {
    angular.module('Controllers', [])
    .controller('orderListController', ['$scope', function($scope) {
        
        /** 申明参数 */
        $scope.data = {
            orderList: [],  // 订单列表
            serveList: [],  // 服务列表
            orderStatusList: [],// 订单状态
            orderFlag : "", //区分显示字段
            findServeList1:[],
            toalMoeny:0,
            introduceTotal:0,
            serviceTotal:0,
            moeny:0,
            userNo:"",
            condition: {
                userName: null,     // 用户名
                realName: null,     // 用户姓名
                orgName: null,      // 机构名称
                projectType: null,    // 服务code
                orderStatus: null,       // 订单状态
                orderType:null     //订单类型
            },
            layer: {
                laypage: null,  // 分页
                form: null, // 表单
            },
            temp: {
                os: [{n:'用户名',v:'userName'},{n:'姓名',v:'realName'}],
                nr: 'userName',
                urValue: null
            }
        }
        
        $scope.fn = {
            init: function() {
                layui.use(['laypage', 'form'], function() {
                    $scope.data.layer.laypage = layui.laypage;
                    
                    var form = layui.form;
                    $scope.data.layer.form = form;
                    
                    // 监听select
                    form.on('select(os)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("string:")) {
                                var nr = value.split(":")[1];  // 得到被选中的值
                                $scope.data.temp.nr = nr;
                            }
                        });
                    });
                    form.on('select(serveList)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            // if (value.startsWith("string:")) {
                            //     var serveCode = value.split(":")[1];  // 得到被选中的值
                            //     $scope.data.condition.serveCode = serveCode;
                            // }
                            if ("" == value) {
                                $scope.data.condition.projectType = null;
                            }else{
                                $scope.data.condition.projectType = value;
                            }
                        });
                    });
                    form.on('select(orderStatusList)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("number:")) {
                                var status = value.split(":")[1];  // 得到被选中的值
                                $scope.data.condition.orderStatus = status;
                            }
                            if ("" == value) {
                                $scope.data.condition.orderStatus = null;
                            }
                        });
                    });

                    $scope.service.listServe();
                    $scope.service.listOrderStatus();
                    $scope.service.listOrder();  // 初始化数据
                });
            },
            search: function() {
                $scope.data.condition.userName = null;
                $scope.data.condition.realName = null;
                $scope.page.pageIndex = 1;
                
                if ('userName' == $scope.data.temp.nr) {
                    $scope.data.condition.userName = $scope.data.temp.nrValue;
                }
                if ('realName' == $scope.data.temp.nr) {
                    $scope.data.condition.realName = $scope.data.temp.nrValue;
                }
                $scope.service.listOrder();
            },
            findServeList: function(){
                var url = '/order/data/findServeList';
                var data = {
                        type:'Vip'
                }
                http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                    
                    if (result.success) {
                        
                        $scope.data.findServeList1 = result.comboList;
                    }
                })
            },
            testAgentIncome: function() {
                var url = '/order/data/testAgentIncome';
                
                 http.ajax.post(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
                     
                    if (result.success) {
                        layer.msg('操作成功!');
                        layer.close($scope.lay);
                        return;
                    }
                })               
            }
        }
        
        $scope.service = {
            // 获取订单列表
            listOrder: function() {
                var url = "/order/data/list/" + $scope.page.pageIndex;
                $scope.data.condition.orderType = $("#oType").val();
                
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
                        // 更新订单列表的数据
                        $scope.data.orderList = result.data;
                        $scope.data.orderFlag = result.flag;
                        $scope.data.userNo = result.userNo;
                        $scope.data.toalMoeny = result.toalMoeny;
                        $scope.data.introduceTotal = result.introduceTotal;
                        $scope.data.serviceTotal = result.serviceTotal;
                        $scope.data.moeny = result.moeny;
                        $scope.page.totalSize = totalSize;
                        $scope.page.pageIndex = pageIndex;
                    });
                });
            },
            // 获取serveList
            listServe: function() {
                var url = "/serve/serves";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        $scope.data.serveList = result.data;
                    });
                    // layui-form 渲染
                    $scope.data.layer.form.render('select');
                });
            },
            listOrderStatus: function() {
                var url = "/order/data/status";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        $scope.data.orderStatusList = result.data;
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
                                $scope.service.listOrder();
                            });
                        }
                    }
                });
            }
        }
    }]);
}());