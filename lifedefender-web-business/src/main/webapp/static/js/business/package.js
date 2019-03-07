/** 申明参数 */
registerController.controller('codePackageController',['$scope', '$rootScope',function ($scope, $rootScope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 10,
        totalSize: 0
    }
    $scope.package =[]; //套餐列表
    $scope.datalist = null; //列表
    $scope.submit = {
        ageId : null,
        name : null

    };
    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(2).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(2).addClass('layui-this');
        $scope.initPage();
        $scope.getDataList();
        $scope.IntroduceForm();

    };
    console.log(typeof $scope.package);
    $scope.getDataList = function(){
        var url = "/code/data/"+$scope.page.pageIndex;
        // TODO DATA 待定
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.datalist = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }



    $scope.findByPackage =function(){
        var url = "/code/pack"
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
            $scope.$apply(function(){
                $scope.package = result.obj;
            })
        })
    };


    // 打开添加窗口
    $scope.openAddDialog = function(superior) {
        if(superior == 0){
            layer.msg("您无此权限操作！！")
            return;
        }
         layer.open({
            type: 1,
            title: ['添加推荐', 'text-align:center;font-size:20px;background:#0398fc;color:#FFF;'],
            area: ['1200px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            content: $('#addPackageDialog'),
            success: function() {
                if ($scope.package.length == 0) {
                    $scope.findByPackage(function (result) {
                        $scope.package = result.obj;
                    })
                }
            }
        });
    }

    $scope.addPackage = function(){
        var url = "/code/add"
        if($scope.submit.ageId == "" | $scope.submit.ageId == null){
            layer.msg("请选择要推荐的套餐");
            return;
        }
        var data = {
          ageId:$scope.submit.ageId,
    }
    if(!data){
        layer.msg("请选择要推荐的套餐");
        $("input[ng-model='data.submit.name']").focus();
        return;
    }
    http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function(result){
        $scope.$apply(function(){
            if(result.success){
                layer.msg("添加成功");
                location.reload();
                layer.closeAll('page');
            }
        })
    })
    }
    $scope.IntroduceForm=function(){
        layui.use(['form'], function(){
            var form = layui.form;
            // 得到选择的套餐值
            form.on('select(businessIdSelect)', function(data) {
                $scope.$apply(function() {
                    var value = data.value;
                    if (value.startsWith("number:")) {
                        $scope.submit.ageId = parseInt(value.split(":")[1]); // 得到被选中的值
                    }
                });
            });
        });
    }

    $scope.showCode = function(name,bsId,ageId,superior){
        layer.open({
            type: 1,
            title: ["查看二维码 (" + name + ")", 'text-align:center;font-size:16px;background:#fff;'],
            area: ['390px', '320px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            content: $('#qrCodeContent'),
            success: function () {
                if (ageId == undefined) {
                    ageId = null;
                }
                var qr = '{businessUserId:'+ bsId + ',comboId:' + ageId +',businessId:'+ superior + '}';
                var obj = document.getElementById("qrcode");
                jQuery("#qrcode").empty();
                var qrcode = new QRCode(obj, {
                    width : 240,//设置宽高
                    height : 240
                });
                qrcode.makeCode(qr);
            }
        });
    }

    $scope.delData = function(id){
        layer.confirm("确定要删除该条推荐信息吗？",function(index){
            var url = "/code/del/"+id;
            http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function(result){
                $scope.$apply(function(){
                    if(result.success){
                        layer.close(index);
                        layer.msg("删除成功", {offset: 450});
                        console.log($('#delete_' + id));
                      $('#delete_' + id).parent().parent().hide('noraml')
                    }
                })
            })
        })

    }

    $scope.update = function(){
        layer.msg("暂不支持修改！！");
    }

    /*初始化分页*/
    $scope.initPage = function () {
        if (typeof laypage == 'undefined') {
            setTimeout(function () {
                console.log('wait for laypage...');
                laypage.render({
                    elem: 'page'
                    , count: $scope.page.totalSize
                    , limit: $scope.page.pageSize
                    , theme: '#00bfff'
                    , layout: ['count', 'prev', 'page', 'next', 'skip']
                    , jump: function (obj, first) {
                        if (!first) {
                            $scope.page.pageIndex = obj.curr;
                            $scope.findByPackage();
                        }

                    }
                });
            }, 300);
        } else {
            laypage.render({
                elem: 'page'
                , count: $scope.page.totalSize
                , limit: $scope.page.pageSize
                , theme: '#00bfff'
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , jump: function (obj, first) {
                    if (!first) {
                        $scope.page.pageIndex = obj.curr;
                        $scope.findByPackage();
                    }
                }
            });
        }

    }
}]);