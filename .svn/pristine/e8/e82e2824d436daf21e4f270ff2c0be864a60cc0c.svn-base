/**渠道商管理*/
registerController.controller('businessAddController',['$scope',function ($scope) {

    $scope.Display={
        name :		null,
        status :       0,
        logo :      null,
        contactMan :null,
        phone :	    null,
        email : 	null,
        type : 		null,
        userName:   null, //设置登录用户
        password:   null,
        pwd:        null, //用于确认密码

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
        if(string == null){
            layer.msg('请输入渠道商名称...！');
            return false;
        }else
    		 if(string.length < 6) {
    			 layer.msg('用户名的长度不得少于6位！');
                 return false;
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


     $scope.addBusiness = function () {
        var logo = $('#upload_img')[0].src;
        $scope.Display.logo = logo;
        var url = '/business/add';
        if ($scope.verifyPassword($scope.Display.name) == false && $scope.verifyPassword($scope.Display.name) == false) {
            return ;
        }
        if ($scope.Display.contactMan == null) {
        	layer.msg('请输入联系人');
        	return;
        }
        if ($scope.Display.phone == null){
        	layer.msg('请输入联系人电话...')
        	return;
        }

         if ($scope.verifyEmail($scope.Display.email)==false) {
             layer.msg('请输入正确邮箱');
             return;
         }

         if($scope.verifyPassword($scope.Display.userName) == false){
            layer.msg("请输入登陆名...");
            return;
         }
         if($scope.Display.password == null && $scope.Display.pwd == null){
            layer.msg('请输入密码....！');
            return;
         }
         if($scope.Display.password != $scope.Display.pwd){
             layer.msg("密码输入不一致....");
             return;
         }
         http.ajax.post(true, false, url,$scope.Display, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('操作成功!');
                window.history.back(-1);
            }
        })
}

}]);



