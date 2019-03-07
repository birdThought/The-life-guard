/**项目套餐管理*/
registerController.controller('comboItemManageController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.arr = [];
    $scope.customerorders = null;
    $scope.conditions =null;
    $scope.edit={
            id:          null,
            name:        null,
            createDate:  null,
            icon:        null,
            itemDetail:  null,
            itemType:        null,
    };
    
    $scope.del={
            id:          null
    };

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listComBoItem();
        $scope.initPage();
        for (var i =0;i < 100; i++){
            $scope.arr.push(i);
        }
    };

     /*获取工单列表*/
    $scope.listComBoItem = function () {
        var url = '/combo/item/work-list/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.comboItemList = result.obj.data;
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
                            $scope.listComBoItem();
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
                        $scope.listComBoItem();
                    }
                }
            });
        }
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
    
    $scope.replyOrder = function () {
        $scope.edit.icon = $("#divEditPhoto").val();
        var url = '/combo/item/add';
        if('edit'==$scope.edit.itemType){
            url = '/combo/item/edit';
        }
        
        var data ={
                id              : $scope.edit.id,
                name            : $scope.edit.name,
                remark          : $scope.edit.remark,
                icon            : $scope.edit.icon,
                itemDetail      : $scope.edit.itemDetail,
                /*newImg:           newImg,*/
        };
        
        
        if ($.trim(data.name) == '' || $.trim(data.name) == null) {
            layer.msg('请输入名称');
            return;
        }
        if ($.trim(data.remark) == '') {
            layer.msg('请输入备注');
            return;
        }
        if ($.trim(data.icon) == '') {
            layer.msg('请上传图标');
            return;
        }
        if ($.trim(data.itemDetail) == ''||$.trim(data.itemDetail) == null 
                || !(/(^[1-9]\d*$)/.test(data.itemDetail))) {
            layer.msg('请输入正确的详情编号');
            return;
        }
         
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listComBoItem();
                layer.close($scope.lay);
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    
 // 弹出删除框
    $scope.DeleteDialog = function (comboItem) {
        $scope.del.id = comboItem.id;
        $scope.lay=layer.open({
          type:1,
          title: '删除',
          area:['300px','200px'],
          content: $('#deleteContent')
        }); 
    };
    
    $scope.deleteItemCombo = function () {
        var url = '/combo/item/delete';
        var data = {
                comboItemId:$scope.del.id,
        };
        
         http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
             
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listComBoItem();
                layer.close($scope.lay);
                return;
            }
        })                 
     };
 
}]);


