/**渠道商管理*/
registerController.controller('businessController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.business = null;
    $scope.conditions =null;
    $scope.edit={
        id :        null,
        name :		null,
        status :     0,
        contactMan :null,
        phone :	    null,
        email : 	null,
        address :	null,
        type :		null,
    }

    /*控制器初始化*/
    $scope.init = function (){
   /* setTimeout(function () {
            $('.content-left ul li').eq(3).click();
        }, 100)*/
        $scope.listBusiness();
        $scope.initPage();
    }
    
     /*获取渠道商列表*/
    $scope.listBusiness = function () {
        var url = '/business/list/' + $scope.page.pageIndex;
        var data = $scope.conditions;
       
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
        
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.business = result.obj.data;
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
                            $scope.listBusiness();
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
                        $scope.listBusiness();
                    }
                }
            });
        }
    }
    $scope.verifyNumber=function(val){
    		 var sMobile = val 
    		 if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
    		  return false; 
    		 }else {
    			 return true;
    		 } 
    }
    $scope.verifyPassword=function(string){
    		 if(string.length < 6) {
    			 layer.msg('密码至少六位')
    		 }
    }
    $scope.verifyEmail=function(str){
    		 var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
    		 if (re.test(str)) {
    		      return true
    		 } else{
    		    	return false
    		 }
    		       		 
    }
    /*弹出添加渠道商框*/
    $scope.popupApplyBusiness = function () {
    	var index =layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加渠道商</p>",
            area:['550px','auto'],
            content:$('#orderCenterPopup')
        })
    }

     $scope.addBusiness = function () {
        var url = '/business/add-business';
        if ($.trim($scope.edit.name) == null) {
            layer.msg('请输入渠道商名称');
            return false;
        }
        
        if ($.trim($scope.edit.contactMan) == null) {
        	layer.msg('请输入联系人');
        	return false;
        }
        
        if ($.trim($scope.edit.phone) == null){
        	layer.msg('请输入联系人电话...')
        	return false;
        }

         if ($scope.verifyEmail($scope.edit.email)==false) {
             layer.msg('请输入正确邮箱');
             return false;
             
         }

         http.ajax.post(true, false, url,$scope.edit, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                $scope.listBusiness();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
           
        })
}
     //弹出修改渠道商
     $scope.popupEditBusiness = function (business) {
    	 $scope.edit.id=business.id;
    	 $scope.edit.name=business.name;
    	 $scope.edit.contactMan = business.contactMan;
    	 $scope.edit.phone=business.phone;
    	 $scope.edit.email=business.email;
     	var index =layer.open({
             type:1,
             title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>修改渠道商</p>",
             area:['550px','auto'],
             content:$('#orderCenterPopup2')
         })
     }
     
     $scope.editBusiness = function (business) {

         var url = '/business/user/edit';
         var data =$scope.edit;
         
         if($scope.verifyNumber($('#buiness_phone2').val())==false){
        	 layer.msg("不是完整的11位手机号或者正确的手机号前七位"); 
        	 return false;
         }
         

          
          if ($scope.verifyEmail($.trim($('#business_email2').val()))==false) {
              layer.msg('请输入正确邮箱');
              return false;
              
          }

          http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
             if (result.success) {
                 layer.msg('操作成功!');
                 $scope.listBusiness();
                 layer.closeAll('page');
                 return;
             }
             layer.msg('操作失败，请重新尝试!')
            
         })
 }
}]);



