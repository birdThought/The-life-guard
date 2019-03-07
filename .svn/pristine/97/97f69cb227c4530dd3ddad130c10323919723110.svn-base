(function() {
    angular.module("Controllers", []).controller('orderFlowController', ['$scope', function ($scope) {
        
        $scope.data = {
            layer: {
                form: null,
                laypage: null,
                laydate: null
            },
            condition: {
                orderNumber: null,
                orgName: null,
                realName: null,
                serveName: null,
                startDate: null,
                endDate: null
            },
            serveList: [],
            orderFlowList: []
        }
        
        $scope.page = {
            pageIndex: 1,
            pageSize: 10,
            totalSize: 0
        }
        
        $scope.init = function() {
            layui.use(['laypage', 'form', 'laydate'], function() {
                $scope.data.layer.laypage = layui.laypage;
                $scope.data.layer.form = layui.form;
                $scope.data.layer.laydate = layui.laydate;
                
                $scope.data.layer.form.on('select(serveList)', function(data) {
                    $scope.$apply(function() {
                        var value = data.value;
                        if (value.startsWith("string:")) {
                            var serveName = value.split(":")[1];  // 得到被选中的值
                            $scope.data.condition.serveName = serveName;
                        }
                        if ("" == value) {
                            $scope.data.condition.serveName = null;
                        }
                    });
                });
                
                $scope.initPage();
                $scope.dateRender();
                $scope.listServe();
                $scope.findOrderFlowList();
                
                $scope.data.layer.form.render();
            });
        }
        
        $scope.findOrderFlowList = function() {
            var url = '/order/flow/data/list/' + $scope.page.pageIndex;
            var data = $scope.data.condition;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                        $scope.page.totalSize = result.obj.totalSize;
                        $scope.page.pageIndex = result.obj.nowPage;
                        $scope.initPage();
                    }
                    $scope.data.orderFlowList = result.obj.data;
                })
            })
        }
        
        $scope.listServe = function() {
            var url = "/serve/serves";
            http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                $scope.$apply(function() {
                    $scope.data.serveList = result.data;
                });
                // layui-form 渲染
                $scope.data.layer.form.render('select');
            });
        }
        
        /*初始化分页*/
        $scope.initPage = function () {
            $scope.data.layer.laypage.render({
                elem: 'page'
                ,count: $scope.page.totalSize
                ,limit: $scope.page.pageSize
                ,theme: '#00bfff'
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,jump: function(obj, first){
                    if(!first){
                        $scope.page.pageIndex = obj.curr;
                        $scope.findOrderFlowList();
                    }
                }
            });
        }
        
        $scope.dateRender = function(){
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
                        $scope.data.condition.startDate = sy + "-" + sm + "-" + sd;
                        $scope.data.condition.endDate = ey + "-" + em + "-" + ed;
                    });
                }
            });
        }
    
        //查询
        $scope.search = function() {
            $scope.page.pageIndex = 1;
            $scope.findOrderFlowList();
        }
        
        //查看明细
        $scope.detailsView = function (id) {
            $scope.getFlowDetails(id);
            layer.open({
                type: 1,
                title: ['交易流水明细',
                    'text-align:center;font-size:16px;background:#fff;'],
                area: ['700px'],
                offset: 120,
                moveType: 1,
                scrollbar: false,
                zIndex: 9999,
                scrolling: 'no',
                content: $('#details')
            });
        }
        
        $scope.getFlowDetails = function(id) {
            var url = '/order/flow/details';
            var data = {
                    id:id
            }
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    $scope.flowDetails = result.obj;
                })
            })
        }
        
    }]);
}());