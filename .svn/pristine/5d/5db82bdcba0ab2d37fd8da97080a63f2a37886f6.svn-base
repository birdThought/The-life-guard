/**预约记录*/
registerController.controller('agentController',['$scope',function ($scope) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.agent = null;
    $scope.search= null;
    /*$scope.search={
            userName: null,
            realName: null,
            orgName: null,
            mobile: null,  
    };*/
    
//    $scope.City =[];

    /** 申明函数 */

    /*控制器初始化*/
    $scope.init = function (){
        $scope.listAgent();
        $scope.initPage();
//        $scope.listProvince();
    }
//    /*验证手机号码*/
//    $scope.verifyNumber=function(val){
//		 var sMobile = val 
//		 if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))){ 
//		  return false; 
//		 }else {
//			 return true;
//		 } 
//    }
//    
//    /*验证邮箱*/
//    $scope.verifyEmail=function(str){
//		 var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
//		 if (re.test(str)) {
//		      return true
//		 } else{
//		    	return false
//		 }
//    }
    
    $scope.searchdata = function () {
        $scope.listAgent();
    }

     /*获取工单列表*/
    $scope.listAgent = function () {
        var url = '/agent/list/' + $scope.page.pageIndex;
        var data = $scope.search;
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.agent = result.obj.data;
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
                            $scope.listAgent();
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
                        $scope.listAgent();
                    }
                }
            });
        }
    }
    
//    /*获取省列表*/
//    $scope.listProvince = function(){
//    	var url='/datalist?getProvince';
//    	var data =null;
//    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//            $scope.$apply(function () {
//                $scope.Province = result.obj.Province;
//             
//            })
//        })
//    }
//    
//    //弹出添加代理商框
//    $scope.addDialog = function(){
//    	var index =layer.open({
//    		type:1,
//    		title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加代理商</p>",
//            area:['946px','650px'],
//            content:$('#orderCenterPopup')
//    	})
//    }
//    $scope.addAgent = function(){
//    	var url ="/agent/add";
//    	var data={
//    			userName		: $('#agent_userName').val(),
//    			name 		 	: $('#agent_name').val(),
//    			contactMan  	: $('#PersonName').val(),
//    			phone			: $('#agent_phone').val(),
//    			email			: $('#agent_email').val(),
//    			provinceCode	: $('#agent_province').val().substr(7,9).substr(0,2),
//    			cityCode		: $('#agent_city').val().substr(2,2),
//    			password		: $('#agent_password').val(),
//    
//    	};
//    
//    	if ($.trim(data.userName) == ''||$.trim(data.userName) == null){
//    		layer.msg('请输入代理商帐号名');
//            return false;
//    	}
//    	if ($.trim(data.name) == ''||$.trim(data.name) == null){
//    		layer.msg('请输入代理商名');
//            return false;
//    	}
//    	if ($.trim(data.contactMan) == ''||$.trim(data.contactMan) == null){
//    		layer.msg('请输入联系人名');
//    		return false;
//    	}
//    	if ($.trim(data.phone) == ''){
//    		layer.msg('请输入联系电话');
//    		return false;
//    	}
//    	if($scope.verifyNumber($('#agent_phone').val())==false){
//       	 layer.msg("不是完整的11位手机号或者正确的手机号前七位"); 
//       	 return false;
//        } 
//    	if ($.trim(data.password) == '') {
//            layer.msg('请输入密码');
//            return false;
//        }
//    	if($.trim(data.password).length<6){
//    		layer.msg('密码长度不能小于6位');
//    		return false;
//    	}
//    	if ($.trim($('#verfityPassword').val()) == '') {
//            layer.msg('请重复输入密码');
//            return false;
//        }
//      
//        if ($.trim($('#agent_password').val())!==$.trim($('#verfityPassword').val())) {
//            layer.msg('两次密码不一致,请重新确认');
//            return false;
//        }
//    	if ($.trim(data.email) == ''){
//       	 layer.msg('请输入邮箱');
//         return false;
//     }
//     
//    	if ($scope.verifyEmail($.trim($('#agent_email').val()))==false) {
//         layer.msg('请输入正确邮箱');
//         return false;
//    	}
//    	
//        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//            if (result.success) {
//                layer.msg('操作成功!');
//                $scope.listAgent();
//                layer.closeAll('page');
//                return;
//            }
//            layer.msg('操作失败，请重新尝试!')
//           
//        })
//    };
//    
////    获取市列表
//    $scope.getCitys = function(){
//        var province=$('#agent_province').val().substr(7,9);
//        province=province.substr(0,2);
//        var url='/datalist?getCity';
//        var data = {
//            provinceCode : province,
//        }
//        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//            $scope.$apply(function () {
//                $scope.City = result.obj.City;
//
//            })
//        })
//
//    	
//    }
    	
}]);
