/**
 *@author ouyang
 * @Date  2018年4月4日 14:03:10
 *
 */

(function(){
    angular.module('Controllers', []).controller("agentAddController",['$scope',function($scope){

        /** 申明参数 */
        $scope.data = {
            layer: {
                form: null
            },
            // 省份
            Province:[],
            customer:[],
            city:[],
            submit:{
                agentName:null,
                name:null,
//                contactMan:null,
                phone:null,
                email:null,
                provinceCode:null,
                cityCode:null,
                areaCode:null,
                reloId:3,//角色id
                userName:null,
                pwd:null,
                password:null,
                address:null
            }
        }
        $scope.add={
                p : "",
                c : "",
                d : "",
            };
        
        /*$scope.listProvince = function () {
            var url = "/datalist?getProvince";
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
                $scope.$apply(function () {
                    $scope.province =result.obj;
                    $scope.getCitys();
                })
            })
        };
        $scope.getCitys = function () {
            var code =$('#province').val();
            var data = {
                provinceCode :  code.substr(0, 2)
            };
            $.ajax({
                async : true,
                cache : false,
                type : 'GET',
                url: "/datalist?getCity",
                data: data,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                beforeSend:function(){

                },
                complete:function(){
                },
                success: function(result) {
                    if(result.success){
                        if($scope.city != null){
                            $scope.area = null;
                        }
                        $scope.city = result.obj;
                        $scope.add.c = result.obj[0];
                        $scope.$apply();
                        $scope.gerArea();
                        $scope.searchMap(org.map)
                    }
                }
            });
        };
        $scope.gerArea = function () {
            var code = $scope.add.c;
            if (code =="" && code == null){
                return;
            }

            var data = {
                provinceCode :  code.substr(0, 4)
            }
            $.ajax({
                async : true,
                cache : false,
                type : 'GET',
                url: "/datalist?getArea",
                data: data,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                beforeSend:function(){
                },
                complete:function(){
                },
                success: function(result) {
                    if(result.success){
                        if($scope.area != null){
                            $scope.area = null;
                        }
                        $scope.area = result.obj;
                        $scope.add.d = result.obj[0];
                        $scope.$apply();
                        
                        $scope.searchMap(org.map)
                    }
                }
            });
        };*/

        /** 申明函数*/
        $scope.ag ={
            init:function(){
                $scope.service.listProvince();
                $scope.service.getCostomerRole();
            },

            addSumit:function(){
                var agentName = $scope.data.submit.agentName;
                var name = $scope.data.submit.name;
//                var contactMan = $scope.data.submit.contactMan;
                var phone = $scope.data.submit.phone;
                var email = $scope.data.submit.email;
                var provinceCode = $('#province').val().substr(7,9).substr(0,2);
                var city = $('#city').val().substr(7,9).substr(2,2);
                var area = $('#area').val().substr(7,9).substr(4,2);
                var userName = $scope.data.submit.userName;
                var password = $scope.data.submit.password;
                var roleId = $scope.data.submit.reloId;
                var address = $scope.data.submit.address;
                if(agentName == null || agentName.length < 2 || agentName.length > 21){
                    layer.msg("请输入2~20位的代理商名称！",{offset: '50%'});
                    return;
                }
                if(name == null || name.length < 2 || name.length > 7){
                    layer.msg("请输入2~6位的真实名称！",{offset: '50%'});
                    return;
                }
                if(userName == null || userName.length < 6 || userName.length > 16){
                    layer.msg("请输入6~16位的登录名称！",{offset: '50%'});
                    return;
                }
//                if(contactMan == null || contactMan.length == 0){
//                    layer.msg("请输入联系人！！",{offset: '50%'});
//                    return;
//                }
                if(phone == null || phone.length == 0){
                    layer.msg("请输入手机号！！",{offset:'50%'});
                    return;
                }
                /*if(email == null || email.length == 0){
                    layer.msg("请输入邮箱！！",{offset:'50%'});
                    return;
                }*/
                if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(phone)){
                    layer.msg("手机号码不合法",{offset:'50%'});
                    return;
                }
                if(provinceCode == null || provinceCode.length == 0){
                    layer.msg("请选择省份",{offset:'50%'});
                    return;
                }
                if(city == null || city.length == 0){
                    layer.msg("请选择城市。。",{offset:'50%'});
                    return;
                }
                if(area == null || area.length == 0){
                    layer.msg("请选择区域。。",{offset:'50%'});
                    return;
                }
                
//                if(roleId == null || roleId.length == 0){
//                    layer.msg("请选择用户角色。。",{offset:'50%'});
//                    return;
//                }
                if(password == null){
                    layer.msg('请输入密码。。', {offset: '50%'});
                    return;
                }
                if(password.indexOf(" ") != -1){
                    layer.msg('密码不能含有空格', {offset: '50%'});
                    return;
                }
                if(password != $.trim($scope.data.submit.pwd)){
                    layer.msg("两次输入的密码不一致。。",{offset:'50%'});
                    return;
                }
                if(password.length < 6 || password.length > 16){
                    layer.msg("请输入6~16位包含大小写英文和数字！");
                    return;
                }

                var json ={
                    agentName:agentName,
                    name:name,
//                    contactMan:contactMan,
                    phone:phone,
                    email:email,
                    provinceCode:provinceCode,
                    cityCode:city,
                    areaCode:area,
                    userName:userName,
                    password:password,
                    address:address
                }
                $scope.service.addAgent(json,function (result) {
                    window.history.back(-1);
                })
            },
            
            getArea : function () {
                var city=$('#city').val().substr(7,13);
                city = city.substr(0,4);
                var url='/datalist?getArea';
                var data = {
                    provinceCode : city,
                }
                http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                    $scope.$apply(function () {
                        $scope.data.area = result.obj;

                    })
                })
            },

            /** 获取市 */
            getCitys : function(){
                var province=$('#province').val().substr(7,9);
                province=province.substr(0,2);
                var url='/datalist?getCity';
                var data = {
                    provinceCode : province,
                }
                http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                    $scope.$apply(function () {
                        $scope.data.city = result.obj;
                    })
                })
            }
        }

        $scope.service = {
            /*获取省列表*/
            listProvince:function(){
            var url='/datalist?getProvince';
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    $scope.data.Province = result.obj;
                })
            })
        },
            /** 获取角色列表*/
        getCostomerRole:function () {
            var url = "/role/data/"+1;
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
                $scope.$apply(function(){
                    $scope.data.customer = result.obj;
                })
            })
        },

            /** 提交 */
           addAgent:function (data, callBack) {
               var uri ="/agent/addAgent"
               http.ajax.post(true,false,uri,data,http.ajax.CONTENT_TYPE_1,function(result){
                   $scope.$apply(function () {
                       callBack(result);
                   })
               })
           }
       }
    }]);
}())