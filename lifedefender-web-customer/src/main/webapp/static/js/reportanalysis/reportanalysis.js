/**
 * 分析报告
 * author: wenxian.cai
 * date: 2017/10/20 14:31
 */

registerController.controller('reportAnalysisController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.orders = null;
    $scope.conditions = {
        orderDate: Number(new Date().getMonth()) + 1,
        orderStatus: "",
        keyword: null
    }
    $scope.reply = {
        content: null,
        orderId: null,
        doctorSign: null,
    }
    currentOrder: null;

    /** 申明函数 */

    /*初始化*/
    $scope.init = function () {
        // setTimeout(function () {
        //     $('.content-left ul li').eq(2).click();
        // }, 100)
        $scope.listOrder();
        $scope.initPage();
    };
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
                            $scope.listOrder();
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
                        $scope.listOrder();
                    }
                }
            });
        }
    }

    /*获取订单列表*/
    $scope.listOrder = function () {
        var url = '/report-analysis/list-order/' + $scope.page.pageIndex;
        var data = Object.assign({}, data, $scope.conditions);
        var newDate = new Date(new Date().getFullYear() + '-' + data.orderDate).Format('yyyy-MM-dd');
        data.orderDate = newDate;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.orders = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }

    /*搜索*/
    $scope.search = function () {
        $scope.listOrder();
    }

    /** 按回车响应搜索 */
    $scope.enterSearch = function($event) {
        if($event.keyCode == 13){ //回车
            $scope.search();
        }
    }

    /*弹出尿液分析回复框*/
    $scope.popupApplyDialog = function (order) {
        $scope.reply.orderId = order.id;
        $scope.currentOrder = order;
        $scope.reply.doctorSign = order.reportAnalysisPO.doctorSign;
        $scope.reply.content = order.reportAnalysisPO.reply;
        try {
            $scope.currentOrder.reportAnalysisPO.content = JSON.parse($scope.currentOrder.reportAnalysisPO.content)
        } catch (e) {
            console.log('json转换失败:', e)
        }
        if($scope.currentOrder.reportAnalysisPO.healthProduct == 16384) {
            $scope.lay = layer.open({
                type: 1,
                content: $('#orderCenterPopup1'),
                title: "<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>血脂分析</p>",
                area: ['550px', 'auto']
            })
        }else if($scope.currentOrder.reportAnalysisPO.healthProduct == 8192){
            $scope.lay = layer.open({
                type: 1,
                content: $('#orderCenterPopup2'),
                title: "<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>尿酸分析</p>",
                area: ['550px', 'auto']
            })
        }else if($scope.currentOrder.reportAnalysisPO.healthProduct == 256){
            $scope.lay = layer.open({
                type: 1,
                content: $('#orderCenterPopup3'),
                title: "<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>心电分析</p>",
                area: ['550px', 'auto']
            })
        }else if($scope.currentOrder.reportAnalysisPO.healthProduct == 64){
            $scope.lay = layer.open({
                type: 1,
                content: $('#orderCenterPopup4'),
                title: "<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>血糖分析</p>",
                area: ['550px', 'auto']
            })
        }else{
            $scope.lay = layer.open({
                type: 1,
                content: $('#orderCenterPopup'),
                title: "<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>尿液分析</p>",
                area: ['550px', 'auto']
            })
        }
    }


    $scope.replyOrder = function () {
        if ($scope.currentOrder.status == 4) {
            layer.msg('订单已完成，不可重复提交!');
            return;
        }
        var url = '/report-analysis/reply-order';
        var data = $scope.reply;
        if ($.trim(data.doctorSign) == '') {
            layer.msg('请输入医生姓名');
            return;
        }
        if ($.trim(data.content) == '') {
            layer.msg('请输入回复内容');
            return;
        }
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listOrder();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    }

    /** 参数监听 */

    $scope.$watch('conditions.orderDate + conditions.orderStatus', function (newVal, oldVal, scope) {
        if (newVal === oldVal) {
            return;
        }
        $scope.search();
    })
    /*$scope.$watch('conditions.orderDate', function () {
        $scope.search()
    })
    $scope.$watch('conditions.orderStatus', function () {
        $scope.search()
    })*/

}]);
