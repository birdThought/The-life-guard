/**项目套餐管理*/
registerController.controller('drugsOrderManageController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
//    $scope.arr = [];
    $scope.results = null;
    $scope.search={
            orderNo:          null
    };
    $scope.show={
            orderNo               :null,
            externalOrderNo       :null,
            invoice               :null,
            orderNotes            :null,
            money                 :null,
            orderTime             :null,
            userId                :null,
            consignee             :null,
            createDate            :null,
            physCode              :null,
            paymentType           :null,
            status                :null,
            shippingNo            :null,
            payCost               :null,
            payAccount            :null,
            sellerAccount         :null,
            clinicalDiagnosis     :null,
            doctorAdvice          :null
    };
    /*控制器初始化*/
    $scope.init = function (){
        $scope.listDrugsOrder();
        $scope.initPage();
//        for (var i =0;i < 100; i++){
//            $scope.arr.push(i);
//        }
    };
    
    $scope.search = function() {
        $scope.listDrugsOrder();
    };
    

     /*获取药品列表*/
    $scope.listDrugsOrder = function () {
        debugger;
        var url = '/drugs/order/data/list/' + $scope.page.pageIndex;
        var data = $scope.search;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.drugsOrderList = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
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
                            $scope.listDrugsOrder();
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
                        $scope.listDrugsOrder();
                    }
                }
            });
        }
    };
    
    $scope.popupDialog = function(orderNo){
//        var msg = "新增";
//        $scope.edit.itemType = itemType;
//        $scope.edit.id = null;
//        $scope.edit.name = null;
//        $scope.edit.createDate = null;
//        $scope.edit.remark = null;
//        $scope.edit.icon = null;
//        $scope.edit.itemDetail = null;
//        
//        if(itemType=='edit'){
//            msg = "编辑";
//            $scope.edit.id = comboItem.id;
//            $scope.edit.name = comboItem.name;
//            $scope.edit.createDate = comboItem.createDate;
//            $scope.edit.remark = comboItem.remark;
//            $scope.edit.icon = comboItem.icon;
//            if(comboItem.itemDetail!='' && typeof(comboItem.itemDetail)!="undefined"){
//                $scope.edit.itemDetail = comboItem.itemDetail.substring(comboItem.itemDetail.indexOf("=")+1,comboItem.itemDetail.length);
//            }
//            $("#divEditPhoto").val(comboItem.icon);
//        }
        
        var url = '/drugs/order/data/get';
        var data = {
                orderNo:orderNo
                };
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.results = result.obj.data;
            });
        })
        
        $scope.lay = layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>订单详情</p>",
            area:['650px','auto'],
            content:$('#orderCenterPopup')
        })
    };
}]);


