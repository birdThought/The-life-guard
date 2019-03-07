/**渠道商管理*/
registerController.controller('comboManageController',['$scope','$compile',function ($scope,$compile,$element) {

    /** 申明参数 */
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.combo = null;
    $scope.conditions =null;
    $scope.dataArr=[];
    $scope.selectComboItemId = null;
    $scope.edit={
    		id :            null,
    		name : 			null,
    		description :	null,
    		originalPrice :	null,
    		price :			null,
    		photo :			null,
    		validDay :		null,
    		detail :		null,
//    		type :			null,
    		l1   :          null,
    };
    $scope.add={
            name :          null,
            originalPrice :   0,
            price : 0,
            description :         null,
            detail :         null,
//            type :      0,
            validDay :        0,
            photo :          null,
            setContent: null,
            comboName: null,
            number:    0,
            l1    :    null,
    };
    
    
    $scope.del={
   		id :          	null,
   		vipComboId:     null,
    };
    $scope.valueArr=[];
    
    //新增、编辑套餐时，用户选择的套餐项id
    var flag = 0;
    var comboItemList = new Array(); //套餐项集合
    var serverUserList = new Array(); //服务师集合
    var comboItemPo = {
            id:0,
            name:null,
            number:0,
            orgUserList:null,
    }
    var serverUserPo = {
            id:0,
            name:null,
            orgName:null,
    }
    
    /**申明函数*/

   
     /*控制器初始化*/
    $scope.init = function (){
   /* setTimeout(function () {
            $('.content-left ul li').eq(3).click();
        }, 100)*/
        $scope.listCombo();
        $scope.initPage();
    }
    
     /*获取渠道商列表*/
    $scope.listCombo = function () {
        var url = '/combo/list/' + $scope.page.pageIndex;
        var data = $scope.conditions;
        flag = 0;
        
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
        
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.combo = result.obj.data;
                
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
               /* $scope.$watch( $scope.combo,function(){
                	$.each($('.td-special'),function(val,index){
                		console.log(index.innerHTML)
                	})
                })*/
            })
        });
        
//        url = '/combo-manage/findL1All';
//        data = null;
//        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//            $scope.$apply(function () {
//                $scope.l1List = result.obj;
//            })
//        })
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
                            $scope.listCombo();
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
                        $scope.listCombo();
                    }
                }
            });
        }
    }
    /*弹出修改框*/
   	$scope.select=function(val){
  		switch(val)

  		{

  		    case undefined:

  		    	return '未选择';

  		           break;

  		    case 1:

  		    	return '妇婴照护';
  		           break;
  		    case 2:

		    	return '健康管理';
		           break;
  		    case 3:

  		    	return "慢病管理";

  		           break;
  		    case 4:

		    	return "术后康复";

		           break;
  		    case 5:

		    	return "长期护理";

		           break;

		           

  		}
  	}
 	$scope.transition=function(val){
  		switch(val)

  		{

  		    case '未选择':

  		    	return 0;

  		           break;
  		   case '妇婴照护':

 		    	return 1;
 		           break;

  		    case '健康管理':

  		    	return 2;
  		           break;

  		    case "慢病管理":

  		    	return 3;

  		           break;
  		    case "术后康复":

		    	return 4;

		           break;
  		    case "长期护理":

		    	return 5;

		           break;

  		    default:

  		        return false;

  		}
  	}
 
    $scope.popupEditDialog = function (combo) {
       $scope.edit.id=combo.id;
       $scope.currentcombo=combo;
       $scope.edit.name  = combo.name;
       $scope.edit.description = combo.description;
       $scope.edit.originalPrice = combo.originalPrice/100;
       $scope.edit.price = combo.price/100;
       $scope.edit.photo = combo.photo;
       $scope.edit.validDay = combo.validDay;
       $scope.edit.detail = combo.detail.substring(combo.detail.indexOf("=")+1,combo.detail.length);
       $scope.comboItemList();
       $scope.edit.l1 = combo.l1;
       
       flag = 0;
       var setMealdetails = $('#setMealdetails');
       var updSetOrgUserInfo = $('#updSetOrgUserInfo');
//       var updSetOrgUserInfo1 = $('#updSetOrgUserInfo1');
       var url = '/combo/findComboItemList';
       var data = {vipComboId:combo.id};
       http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
       
           $scope.$apply(function () {
               var ul=$('<ul class="lisBox"></ul>');
               var str='';
               //套餐项
               $.each(result.obj.vipComboItemRelation,function(val,val){
                   str+='<li id=div'+val.id+' style="display:inline-block;line-height: 34px;font-size: 13px;"><a ng-click="setOrgUser(\''+val.id+'\',\''+'edit'+'\')">'+val.name+'</a><input name="divEditComboId" type="hidden" value='+val.id+' /><input name="divEditNumber" value='+val.number+' type="text" style="border:none;border-bottom:1px solid skyblue;width:30px;text-align:center;border-radius: 0">'+'次 &nbsp; <a ng-click="removeItemNum(\''+val.id+'\',\''+'edit'+'\')"><font color=skyblue >移除</font></a></li>'
               });
               
               ul.append(str);
               var $e = $compile(ul)($scope);
               setMealdetails.html($e);
               
               //服务师
               str='';
               ul=$('<ul class="lisBox"></ul>');
               $.each(result.obj.vipComboOrgUserRelation,function(val,val){
                   str+='<li id=div'+val.id+' style="display:inline-block;line-height: 34px;font-size: 13px;">'+val.realName+'-'+val.userName +'<input name="orgUserId" type="hidden" value='+val.id+' /> &nbsp; <a ng-click="deleteOrgUserNum(\''+val.id+'\')"><font color=skyblue >移除</font></a></li>'
               });
               ul.append(str);
               var $e = $compile(ul)($scope);
               updSetOrgUserInfo.html($e);
               
           })
       });
       
       
       
       
       
       $scope.lay = layer.open({
           type:1,
           title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>套餐编辑</p>",
           area:['750px','auto'],
           content:$('#orderCenterPopup')
       })
   };
   
    $scope.editcombo = function () {
    	//$scope.edit.type =$scope.transition(combo.type);
        var orgUserIds = ""; //拼接服务师id
       var comboStr = "";
       var isBol = false;
       var editImg = $("#divEditPhoto").val();
       var url = '/combo/edit';
       if(typeof(editImg)=='undefined' || editImg== ''|| editImg == null){
           editImg = $scope.edit.photo;
       }
     //获取套餐项目子项信息
       var cId = document.getElementsByName("divEditComboId");
       var cNumber = document.getElementsByName("divEditNumber");
       var orgUserId = document.getElementsByName("orgUserId");
       for(var i=0;i<cId.length;i++){
           $.each($scope.comboItemInfo,function(val,index){
               if(typeof(cNumber[i].value)=="undefined" 
                   || cNumber[i].value == null
                   || cNumber[i].value=="" 
                   || !(/(^[1-9]\d*$)/.test(cNumber[i].value))) {
                   isBol = true;
                   return false;
               }
               if (cId[i].value == index.id){
                   comboStr += index.id+":" + cNumber[i].value +",";
               }
           });
       }
       for(var i=0;i<orgUserId.length;i++){
           orgUserIds += orgUserId[i].value+",";
           if(i == orgUserId.length-1){
               orgUserIds = orgUserIds.substring(0,orgUserIds.length-1);
           }
       }
       var data ={
               id              : $scope.edit.id,
               name            : $scope.edit.name,
               originalPrice   : $scope.edit.originalPrice,
               price           : $scope.edit.price,
               description     : $scope.edit.description,
               detail          : $scope.edit.detail,
               validDay        : $scope.edit.validDay,
               photo           : editImg, //$scope.edit.photo,
//               setContent      : $scope.edit.setContent,
               l1              : $scope.edit.l1,
               vipComboItem    : comboStr,
               orgUserIds      : orgUserIds,
       };
       
       if ($.trim(data.name) == ''||$.trim(data.name) == null) {
           layer.msg('请输入套餐名');
           return false;
       }
       if ( !(/(^[1-9]\d*$)/.test(data.originalPrice))) {
           layer.msg('原价不小于1的正整数');
           return false;
       }
       if ($.trim(data.price) == '' ||$.trim(data.price) == null
               || !(/(^[1-9]\d*$)/.test(data.price))) {
           layer.msg('实价不小于1的正整数');
           return false;
       }
       if ($.trim(data.description) == ''||$.trim(data.description) == null) {
           layer.msg('请输入描述');
           return false;
       }
       if ($.trim(data.detail) == ''||$.trim(data.detail) == null 
               || !(/(^[1-9]\d*$)/.test(data.detail))) {
           layer.msg('请输入正确的文章编号');
           return false;
       }
       if ($.trim(data.validDay) == '' ||$.trim(data.validDay) == null 
               || !(/(^[1-9]\d*$)/.test(data.validDay))) {
           layer.msg('请输入正确的有效期');
           return false;
       }
       if (($.trim($scope.edit.photo) == '' ||$.trim($scope.edit.photo) == null) 
               && ($.trim($scope.edit.photo) == '' ||$.trim($scope.edit.photo) == null)) {
           layer.msg('请输入图片路径');
           return false;
       }
       if ($.trim(data.l1) == '' ||$.trim(data.l1) == null) {
           layer.msg('请输入套餐类型');
           return false;
       }
       if(isBol){
           layer.msg('套餐次数为不小于1的正整数！');
           return false;
       }
       
       if(comboStr!=""){
           comboStr = comboStr.substring(0,comboStr.length-1);
       }
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
           if (result.success) {
        	   console.log(result)
               layer.msg('操作成功!');
               $scope.listCombo();
               layer.close($scope.lay);
               return;
           }
           
       })       		  
    };
    
    // 弹出删除框
    $scope.DeleteDialog = function (combo) {
	$scope.del.id=combo.id;
	$scope.del.vipComboId=combo.id;
	$scope.lay=layer.open({
		  type:1,
		  title: '删除',
		  area:['300px','200px'],
		  content: $('#deleteContent')
		}); 
    };
    
	$scope.deleteCombo = function () {
       var url = '/combo/delete';
       var data = $scope.del;
       
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
        	
           if (result.success) {
               layer.msg('操作成功!');
               $scope.listCombo();
               layer.close($scope.lay);
               return;
           }
       })       		  
	};	   

    //弹出添加套餐框
    $scope.addDialog = function(){
    	var index =layer.open({
            type:1,
            title:"<p class='layer-report' style='line-height: 60px;font-size: 16px; letter-spacing: 1.5px'>添加套餐</p>",
            area:['946px','880px'],
            content:$('#addPopup')
        })
    };
    
    $scope.addCombo = function(){
        var comboStr = "";//拼接选中的套餐子项
        var orgUserIds = ""; //拼接服务师id
        var isBol = false;
        var url = '/combo/add';
        
        //获取套餐项目子项信息
        var cId = document.getElementsByName("divComboName");
        var cNumber = document.getElementsByName("divNumber");
        var orgUserId = document.getElementsByName("orgUserId");
        for(var i=0;i<cId.length;i++){
            $.each($scope.comboItem,function(val,index){
                if(typeof(cNumber[i].value)=="undefined" 
                    || cNumber[i].value == null
                    || cNumber[i].value=="" 
                    || !(/(^[1-9]\d*$)/.test(cNumber[i].value))) {
                    isBol = true;
                    return false;
                }
                console.log(cId[i].value+"===="+index.id);
                
                if (cId[i].value == index.id){
                    comboStr += index.id+":" + cNumber[i].value +",";
                }
            });
        }
        for(var i=0;i<orgUserId.length;i++){
            orgUserIds += orgUserId[i].value+",";
            if(i == orgUserId.length-1){
                orgUserIds = orgUserIds.substring(0,orgUserIds.length-1);
            }
        }
        
    	var data ={
    			name 			: $scope.add.name,
    			originalPrice 	: $scope.add.originalPrice,
    			price 	        : $scope.add.price,
    			description 	: $scope.add.description,
    			detail 			: $scope.add.detail,
    			/*type 		    : $scope.add.type,*/
    			validDay 		: $scope.add.validDay,
    			photo 			: $("#divPhoto").val(),
    			setContent      : $scope.add.setContent,
        		vipComboItem 	: comboStr,
        		l1              : $scope.add.l1,
        		orgUserIds      : orgUserIds, 
    	};
    	if ($.trim(data.name) == ''||$.trim(data.name) == null) {
            layer.msg('请输入套餐名');
            return false;
        }
    	if ($.trim(data.originalPrice) == '' ||$.trim(data.originalPrice) == null 
    	        || !(/(^[1-9]\d*$)/.test(data.originalPrice))) {
            layer.msg('原价不小于1的正整数');
            return false;
        }
    	if ($.trim(data.price) == '' ||$.trim(data.price) == null 
    	        || !(/(^[1-9]\d*$)/.test(data.price))) {
            layer.msg('实价不小于1的正整数');
            return false;
        }
    	if ($.trim(data.description) == ''||$.trim(data.description) == null) {
            layer.msg('请输入描述');
            return false;
        }
    	if ($.trim(data.detail) == ''||$.trim(data.detail) == null 
    	        || !(/(^[1-9]\d*$)/.test(data.detail))) {
            layer.msg('请输入正确的文章编号');
            return false;
        }
    	if ($.trim(data.l1) == '' ||$.trim(data.l1) == null) {
            layer.msg('请输入套餐类型');
            return false;
    	}
    	if ($.trim(data.validDay) == '' ||$.trim(data.validDay) == null 
    	        || !(/(^[1-9]\d*$)/.test(data.validDay))) {
            layer.msg('请输入正确的有效期');
            return false;
        }
    	if ($.trim(data.photo) == '' ||$.trim(data.photo) == null) {
            layer.msg('请输入图片路径');
            return false;
        }
    	
    	if ($.trim(data.vipComboItem) == '' ||$.trim(data.vipComboItem) == null) {
            layer.msg('请选择并正确输入有效的套餐内容.');
            return false;
        }
    	if(isBol){
            layer.msg('套餐次数为不小于1的正整数！');
            return false;
        }
    	if(comboStr!=""){
            comboStr = comboStr.substring(0,comboStr.length-1);
        }
    	
       http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            console.log(result)
        	if (result.success) {
                layer.msg('操作成功!');
                $scope.listCombo();
                layer.closeAll('page');
                return;
            }
            layer.msg('操作失败，请重新尝试!')
        })
    };
    
    /*编辑 */
    /*获取套擦项目列表*/
    $scope.comboItemList =function(){
        $('#checkedMeal').slideToggle('fast');
        var url ='/combo/item/list';
        var data = null;
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.comboItemInfo = result.obj;
            })
        })
    };
//    $scope.serviceUser =function(v){
//        var orgUserName = "";
//        var str = "";
//        var setOrgUserInfo = "";
//        if(v=='add'){
//            str = $('#setOrgUserInfo').html();
//            setOrgUserInfo = $('#setOrgUserInfo');
//            orgUserName = $('#orgUserName').val();
//        }else{
//            str = $('#updSetOrgUserInfo').html();
//            setOrgUserInfo = $('#updSetOrgUserInfo');
//            orgUserName = $('#updOrgUserName').val();
//        }
//        
//        if ($.trim(orgUserName) == '' ||$.trim(orgUserName) == null ) {
//            layer.msg('服务师名称不能为空!');
//            return false;
//        }
//        var resultValue = null;
//        var url ='/orgUser/getOrgUserByrealName';
//        var data = {
//                rName:orgUserName
//        };
//        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
//            
//                var ul=$('<ul class="lisBox"></ul>');
//                $.each(result.obj,function(val,val){
//                    str+='<li id=div'+val.id+' style="display:inline-block;line-height: 34px;font-size: 13px;">'+val.realName+'-'+val.userName+'<input name="orgUserId" type="hidden" value='+val.id+' /> &nbsp; <a ng-click="deleteOrgUserNum(\''+val.id+'\')"><font color=skyblue >移除</font></a></li>'
//                });
//                ul.append(str);
//                var $e = $compile(ul)($scope);
//                setOrgUserInfo.html($e);
//        })
//    };
    
    $scope.addCheckMeal = function(){
      var msg = "";
      $('#mealdetails').html("");
      $('#checkedMeal').css({'display':'none'});
        var obj=$('#checkedMeal .cName');
        var objId=$('#checkedMeal .cId');
        var setMealdetails = $('#setMealdetails');
        var str = $('#setMealdetails').html();
        var ul=$('<ul class="lisBox"></ul>');
        
        for(var i=0; i<obj.length; i++){ 
            if(obj[i].checked) {
                if($("#div"+objId[i].value).length > 0){
                    msg += " "+ obj[i].value
                    continue;
                }
                str +='<li id=div'+objId[i].value+' style="display:inline-block;line-height: 34px;font-size: 14px;"><a ng-click="setOrgUser(\''+objId[i].value+'\',\''+'edit'+'\')">'+obj[i].value+'</a><input name="divEditComboId" type="hidden" value='+objId[i].value+' /><input value="" name="divEditNumber" onchange="this.defaultValue=this.value" type="text" style="border:none;border-bottom:1px solid skyblue;width:30px;text-align:center;border-radius: 0;">'+'次 &nbsp; &nbsp; <a ng-click="removeItemNum(\''+objId[i].value+'\',\''+'add'+'\')"><font color=skyblue >移除</font></a></li>'
            }
        }
        ul.append(str);
        var $e = $compile(ul)($scope);
        setMealdetails.html($e);
        
        if(msg!=""){
            layer.msg(msg+'已存在.')
        }
    };
    
    $scope.setOrgUser = function (index,vType) {
        flag = index;
        debugger;
        var str ="";
        var updSetOrgUserInfo = "";
        var url ='/combo/findOrgUserRelation';
        var data = {
                vipComboItemId:flag,
                vipComboId:$scope.edit.id
        };
        
        
        if(vType == 'edit'){
            updSetOrgUserInfo = $('#updSetOrgUserInfo');
            updSetOrgUserInfo.html();
            
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                var ul=$('<ul class="lisBox"></ul>');
                
                $.each(result.obj,function(val,val){
                    str+='<li id=div'+val.id+' style="display:inline-block;line-height: 34px;font-size: 13px;">'+val.realName+'-'+val.userName+'<input name="orgUserId" type="hidden" value='+val.id+' /> &nbsp; <a ng-click="deleteOrgUserNum(\''+val.id+'\')"><font color=skyblue >移除</font></a></li>'
                });
                
                ul.append(str);
                var $e = $compile(ul)($scope);
                updSetOrgUserInfo.html($e);
            });
        }else{
//            updSetOrgUserInfo = $('#setOrgUserInfo');
//            //处理遍历 后续实现
        }
    };
    
    $scope.serviceUser =function(v){

        if(flag==''){
            layer.msg('请选择套餐项!');
            return false;
        }
        
        var orgUserName = "";
        var str = "";
        var setOrgUserInfo = "";
        if(v=='add'){
            str = $('#setOrgUserInfo').html();
            setOrgUserInfo = $('#setOrgUserInfo');
            orgUserName = $('#orgUserName').val();
        }else{
            str = $('#updSetOrgUserInfo').html();
            setOrgUserInfo = $('#updSetOrgUserInfo');
            orgUserName = $('#updOrgUserName').val();
        }
        
        if ($.trim(orgUserName) == '' ||$.trim(orgUserName) == null ) {
            layer.msg('服务师名称不能为空!');
            return false;
        }
        var resultValue = null;
        var vipComboId = $scope.edit.id;
        var url ='/combo/addOrgUserRelation';
        var data = {
                rName:orgUserName,
                vipComboItemId:flag,
                vipComboId:vipComboId
        };
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                var ul=$('<ul class="lisBox"></ul>');
                $.each(result.obj,function(val,val){
                    str+='<li id=div'+val.id+' style="display:inline-block;line-height: 34px;font-size: 13px;">'+val.realName+'-'+val.userName+'<input name="orgUserId" type="hidden" value='+val.id+' /> &nbsp; <a ng-click="deleteOrgUserNum(\''+val.id+'\')"><font color=skyblue >移除</font></a></li>'
                })
                ul.append(str);
                var $e = $compile(ul)($scope);
                setOrgUserInfo.html($e);
                
//                //设值 后续实现
//                if(v=='add'){
//                    comboItemList = new Array(); //套餐项集合
//                    for(var i=0;i<comboItemList.length;i++){
//                        serverUserList = new Array(); //服务师集合
//                        if(flag==comboItemList[i]){
//                            for(var j=0;j<comboItemList.length;j++){
//                                serverUserPo = {};
//                                serverUserPo.id = val.id;
//                                serverUserPo.name = val.realName;
//                                serverUserPo.orgName = val.userName;
//                                serverUserList.push(serverUserPo);
//                            }
//                            comboItemList[i].orgUserList = serverUserList;
//                        }
//                        
//                    }
//                }
        })
    };
    
    $scope.removeItemNum = function (index,v) {
        if('edit' == v){
            var url ='/combo/delOrgUserRelationByVipComboId';
            var data = {
                    vipComboItemId:index,
                    vipComboId:$scope.edit.id,
                    userId:null,
            };
            http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            })
        }
        $("li").remove("#div"+index);
        
    };  
    
    $scope.deleteOrgUserNum = function (index) {
        var vipComboId = $scope.edit.id;
        var url = '/combo/delOrgUserRelationByVipComboId';
        var data = {
                vipComboItemId:flag,
                vipComboId:vipComboId,
                userId:index
                };
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
        })     
        $("li").remove("#div"+index);
    };
    
   
    /*新增 */
    /*获取套擦项目列表*/
    $scope.listComboItem =function(){
    	$('#notarizeAdd').slideToggle('fast');
    	var url ='/combo/item/list';
    	var data = null;
    	http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.comboItem = result.obj;
            })
        })
        
    };
    $scope.addmeal=function(){
      var msg = "";
      $('#setMealdetails').html("");
      $('#notarizeAdd').css({'display':'none'});
      var obj=$('#notarizeAdd .addName');
      var str = $('#mealdetails').html();
      var objId=$('#notarizeAdd .addId');
      var mealdetails = $('#mealdetails');
      var ul=$('<ul class="lisBox"></ul>');
    	for(var i=0; i<obj.length; i++){
    	    if(obj[i].checked) {
    	        if($("#div"+objId[i].value).length > 0){
    	            msg += " "+ obj[i].value
                    continue;
    	        }
    	        str+='<li id=div'+objId[i].value+' style="display:inline-block;line-height: 34px;font-size: 14px;"><a ng-click="setOrgUser(\''+objId[i].value+'\',\''+'add'+'\')">'+obj[i].value+'</a><input name="divComboName" type="hidden" value='+objId[i].value+' /><input name="divNumber" id="divNumber'+objId[i].value+'" onchange="this.defaultValue=this.value" ng-blur=settingValue(\''+objId[i].value+'\') type="text" style="border:none;border-bottom:1px solid skyblue;width:30px;text-align:center;border-radius: 0">'+'次 &nbsp; &nbsp; <a ng-click="removeAddItemNum(\''+objId[i].value+'\')"><font color=skyblue >移除</font></a></li>'
    	        
    	        comboItemPo = {};
    	        comboItemPo.id = objId[i].value;
                comboItemPo.name = obj[i].value;
                comboItemList.push(comboItemPo);
                if(i==0){
                    flag = objId[i].value;
                }
    	    
    	    }
    	}
    	
    	ul.append(str);
        var $e = $compile(ul)($scope);
        mealdetails.html($e);
    	
        if(msg!=""){
            layer.msg(msg+'已存在.')
        }
    };
    
    $scope.settingValue= function (num) {
        var number = $("#divNumber"+num).val();
        
        for(var i=0;i< comboItemList.length;i++){
            if(comboItemList[i].id==num){
                comboItemList[i].number = number;
            } 
        }
    }
    
    $scope.removeAddItemNum = function (index) {
        $("li").remove("#div"+index);
    };
}]);