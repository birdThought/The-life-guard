/**预约管理*/
registerController.controller('customerManagerController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.customerorders = null;
    $scope.conditions =null;
    $scope.customer = [];
    $scope.select={
        reloId : null
    }
    $scope.finsh={
        id:     		null,
        orgName:        null,
        sureDate:       null,
        address:        null,
        customerRemark: null,
    }
    $scope.boolean =null;
    currentOrder: null;

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
   /* setTimeout(function () {
            $('.content-left ul li').eq(3).click();
        }, 100)*/
    	$scope.listCustomer();
        $scope.initPage();
    }
    
    /* 获取客服列表 */
  $scope.listCustomer = function() {
    	var url = '/user/list/' + $scope.page.pageIndex;
    	var data = $scope.conditions;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.data = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }
    
    var index = null;
   
    /*添加客服弹框*/
    $scope.addCustomerPopup = function (Authority) {
//        if(Authority != 0 & Authority != 2){
       if(Authority != 0){
            layer.msg('对不起、您无此权限进行操作');
            return;
        }
        $scope.getCostomerRole();
    	index =layer.open({
                type:1,
                title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加客服</p>",
                area:['550px','auto'],
                content:$('#orderCenterPopup'),
         })
       
    }
  
    $scope.getCostomerRole = function () {
        var url = "/role/data/"+2;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                $scope.customer = result.obj;
            })
        })
    }
    /*初始化分页*/
   $scope.initPage = function () {
       if (typeof laypage == 'undefined') {
           setTimeout(function () {
              // console.log('wait for laypage...');
               laypage.render({
                   elem: 'page'
                   ,count: $scope.page.totalSize
                   ,limit: $scope.page.pageSize
                   ,theme: '#00bfff'
                   ,layout: ['count', 'prev', 'page', 'next', 'skip']
                   ,jump: function(obj, first){
                       if(!first){
                           $scope.page.pageIndex = obj.curr;
                           $scope.listCustomer();
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
                       $scope.listCustomer();
                   }
               }
           });
       }
   }
   
   $scope.submitData=function(){
   	var data={
   			name:$('#name').val(),
   	        userName:$('#username').val(),
   			password:$('#password').val(),
   			rePassword:$('#rePassword').val(),
            Authority:$scope.select.reloId,
   			photo:$('#portrait').attr('src')
   	}
   	if($.trim(data.photo)=='/static/images/template-head.png'){
   		layer.msg('请选择头像')
   		return false;
   	}
   	if ($.trim(data.name)=='') {
        layer.msg('请输入姓名')
         return false;
         
     }
   	if ($.trim(data.userName)=='') {
        layer.msg('请输入账号')
         return false;
         
     }
   	if ($.trim(data.userName).length<6) {
        layer.msg('账号至少六位')
         return false;
         
     }
   	 if ($.trim(data.password)=='') {
            layer.msg('请输入密码');
            return false;
            
        }
   	if ($.trim(data.password).length<6) {
           layer.msg('密码至少六位')
            return false;
            
        }
   	
	if ($.trim(data.rePassword)=='') {
        layer.msg('请确认密码')
         return false;
         
     }
   	if ($.trim(data.password)!==$.trim(data.rePassword)) {
        layer.msg('两次密码输入不一样，请重新确认')
         return false;
         
     }
   	
   	delete data.rePassword;
   	
   	var url = '/user/add';
   	http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function (result) {
        $scope.$apply(function () {
            if (!result.success) {
                layer.msg(result.msg)
                 return false;
                 
             } else {
            	 layer.msg('添加成功')
            	 layer.close(index);
            	 $scope.init();
             }
            
        })
    })
   	
   }

}]);


