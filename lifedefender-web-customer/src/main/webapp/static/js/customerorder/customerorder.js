/**预约管理*/
registerController.controller('customerOrderController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.arr = [];
    $scope.customerorders = null;
    $scope.conditions =null;
    $scope.switch={
        totalprice : null, //总金额
        customername : null,
        customermobile : null,
        workdistrict : null, //区
        workaddress : null, //详细
        details : null, //其他需求
        youxiqi : null,
        yonggongshijian : null, //用工时间
        gender : null,
        language : null,//语言
        age : null,
        clean : null, //次
        home : null, //天
        area : null, //面积
        pulation : null,//人口
        yonggongshichang : null,//服务时长
    }

    $scope.finsh={
        id:     		null,
        orgName:        null,
        sureDate:       null,
        address:        null,
        customerRemark: null,
    }
    currentOrder: null;

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
   /* setTimeout(function () {
            $('.content-left ul li').eq(3).click();
        }, 100)*/
        $scope.listOrder();
        $scope.initPage();
        for (var i =0;i < 100; i++){
            $scope.arr.push(i);
        }
    }

     /*获取工单列表*/
    $scope.listOrder = function () {
        var url = '/combo/member/worklist/data/list/untreated/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.customerorders = result.obj.data;
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
    
    /*弹出客服回复框*/
    $scope.popupApplyDialog = function (order) {
    	 var test= order.UpdateOrderPo={};
    	test.orgName=null;test.sureDate=null;test.address=null;
        $scope.finsh.id=order.id;
        $scope.currentOrder=order;
        $scope.finsh.orgName  = test.orgName;
        $scope.finsh.sureDate = test.sureDate;
        $scope.finsh.address = test.address;
        $scope.finsh.customerRemark = order.UpdateOrderPo.customerRemark;
        layui.use('laydate',function(){
        	var laydate = layui.laydate;
        	laydate.render({
        		elem:'#promiseDate',
        		done:function(val,date){
        			 $scope.finsh.sureDate = val;
        			 console.log($scope.finsh.sureDate)
        		}
        	})
        })
        
        $scope.lay = layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>提交</p>",
            area:['550px','auto'],
            content:$('#orderCenterPopup')
        })
    }

    /*弹出客服回复框*/
    $scope.popupApplyDialog1 = function () {
        layui.use('laydate',function(){
            var laydate = layui.laydate;
            laydate.render({
                elem:'#promiseDate1',
                done:function(val,date){
                    $scope.switch.yonggongshijian =val;
                }
            })
            laydate.render({
                elem:'#promiseDate2',
                done:function(val,date){
                    $scope.switch.youxiqi = val;
                }
            })
        })

        $scope.lay = layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>提交</p>",
            area:['550px','auto'],
            content:$('#orderCenterPopup1')
        })
    }
     $scope.replyOrder = function () {
        if ($scope.currentOrder.status == 1) {
            layer.msg('已提交成功，不可重复提交!');
            return;
        }
        var url = '/combo/member/worklist/finish';
        var data = $scope.finsh;
        if ($.trim(data.orgName) == ''||$.trim(data.orgName) == null) {
            layer.msg('请输入机构名称');
            return;
        }
         if ($.trim(data.sureDate) == ''||$.trim(data.sureDate) == null) {
            layer.msg('请输入约定时间');
            return;
        }
         if ($.trim(data.address) == '') {
            layer.msg('请输入机构地点');
            return;
        }
         if ($.trim(data.customerRemark) == '') {
            layer.msg('请输入备注');
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
    $scope.submit = function () {
        var url = '/home/world';
        var data = $scope.switch;
        if ($.trim(data.customername) == ''||$.trim(data.customername) == null) {
            layer.msg('请输入客户姓名');
            return;
        }
        if ($.trim(data.customermobile) == ''||$.trim(data.customermobile) == null) {
            layer.msg('请输入客户手机号码');
            return;
        }
        if ($.trim(data.workdistrict) == '' || $.trim(data.workdistrict) ==null) {
            layer.msg('请选择行政区');
            return;
        }
        if ($.trim(data.yonggongshijian) == '' || $.trim(data.yonggongshijian) ==null) {
            layer.msg('请选择上门时间段');
            return;
        }
        if ($.trim(data.yonggongshijian) == '' || $.trim(data.yonggongshijian) ==null) {
            layer.msg('请选择上门时间段');
            return;
        }

        if ($.trim(data.workaddress) == '' || $.trim(data.workaddress) ==null) {
            layer.msg('请输入详细地址');
            return;
        }
        if ($.trim(data.details) == '' || $.trim(data.details) ==null) {
            layer.msg('请输入其它要求或者需求');
            return;
        }
        if ($scope.switch.clean == true) {
            $scope.switch.totalprice = "60";
            $scope.switch.yonggongshichang = 3;
            console.log($scope.switch.totalprice)
            //console.log($scope.switch.yonggongshichang)

        }
        if ($scope.switch.home == true) {
            $scope.switch.totalprice = "80";
            console.log($scope.switch.totalprice)

        }
        if ( ($scope.switch.home == true ? true:false) == ($scope.switch.clean == true ? true:false)) {
            layer.msg('只能选取一种服务、如有需要请重新预约！！');
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
    $scope.$watch('conditions', function (newValue, oldValue, scope) {
         if (newValue === oldValue) {
             return;
         }
        $scope.page.pageIndex = 1;
       $scope.listOrder();
    })


}]);


