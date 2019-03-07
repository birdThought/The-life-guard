/**项目套餐管理*/
registerController.controller('drugsManageController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.arr = [];
    $scope.customerorders = null;
    $scope.search={
            productName:          null,
            productAttribute:        null,
            prescriptionType:  null,
    };
    $scope.show={
            id:          null,
            productName:        null,
            productCode:  null,
            channelPrice:        null,
            manufacturer:  null,
            prescriptionType:        null,
            productInventory:          null,
            productAttribute:        null,
            thumbnailUrl:  null,
            introduction:        null,
    };
    /*控制器初始化*/
    $scope.init = function (){
        $scope.listDrugs();
        $scope.initPage();
        for (var i =0;i < 100; i++){
            $scope.arr.push(i);
        }
    };
    
    $scope.search = function() {
        $scope.listDrugs();
    };
    

     /*获取药品列表*/
    $scope.listDrugs = function () {
        var url = '/drugs/data/list/' + $scope.page.pageIndex;
        var data = $scope.search;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.drugsList = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
        
        url = '/drugs/data/getPrescriptionType';
        http.ajax.post(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                $scope.search.prescriptionType =result.obj;
            }
        })
        
        url = '/drugs/data/getProductAttribute';
        http.ajax.post(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                $scope.search.productAttribute =result.obj;
            }
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
                            $scope.listDrugs();
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
                        $scope.listDrugs();
                    }
                }
            });
        }
    };
    
    $scope.synchro = function () {
        var data = null;
        debugger;
        var url = '/drugs/synchro';
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDrugs();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    
    
    $scope.popupEditDialog = function(comboItem,itemType){
        var msg = "新增";
        $scope.edit.itemType = itemType;
        $scope.edit.id = null;
        $scope.edit.name = null;
        $scope.edit.createDate = null;
        $scope.edit.remark = null;
        $scope.edit.icon = null;
        $scope.edit.itemDetail = null;
        
        if(itemType=='edit'){
            msg = "编辑";
            $scope.edit.id = comboItem.id;
            $scope.edit.name = comboItem.name;
            $scope.edit.createDate = comboItem.createDate;
            $scope.edit.remark = comboItem.remark;
            $scope.edit.icon = comboItem.icon;
            if(comboItem.itemDetail!='' && typeof(comboItem.itemDetail)!="undefined"){
                $scope.edit.itemDetail = comboItem.itemDetail.substring(comboItem.itemDetail.indexOf("=")+1,comboItem.itemDetail.length);
            }
            $("#divEditPhoto").val(comboItem.icon);
        }
        
        $scope.lay = layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>"+msg+"套餐</p>",
            area:['650px','auto'],
            content:$('#orderCenterPopup')
        })
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    $scope.findOrder = function () {
        var data = null;
        debugger;
        var url = '/drugs/findOrder';
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
//                $scope.listDrugs();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    $scope.findOrderList = function () {
        var data = null;
        debugger;
        var url = '/drugs/findOrderList';
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDrugs();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    $scope.orderPush = function () {
        var data = null;
        debugger;
        var url = '/drugs/orderPush';
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDrugs();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    $scope.logisticsInfo = function () {
        var data = null;
        debugger;
        var url = '/drugs/logisticsInfo';
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listDrugs();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    
    
}]);


