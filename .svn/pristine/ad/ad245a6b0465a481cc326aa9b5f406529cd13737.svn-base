
registerController.controller('orgListController',['$scope',function ($scope,$filter) {

    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数
    };
    $scope.results ={};
    $scope.orgUserInfo = {};
    $scope.province = [];
    $scope.city = [];
    $scope.dis = [];
    $scope.orgInfo =null;
    $scope.list={
        orgName : null,
        p : "",
        c : "",
        d : ""
    };

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
       $scope.findByOrgDataList();
        $scope.getProvinces();
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
                            $scope.findByOrgDataList();
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
                        $scope.findByOrgDataList();
                    }
                }
            });
        }
    }

    $scope.findByOrgDataList = function () {

        if ($scope.list.p != '-1'){
            $scope.list.p = $scope.list.p.substr(0,2);
        }if ($scope.list.c != '-1'){
            $scope.list.c = $scope.list.c.substr(2,2);
        }if ($scope.list.d != '-1'){
            $scope.list.d = $scope.list.d.substr(4,2);
        }
        var url = "/org/data/list/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, $scope.list, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;

            })
        })
    }
    
    $scope.searchOrgUser = function (id){
        var url = "/orgUser/list";
        var data = {orgId:id};
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.orgUserInfo = result.obj;
                console.log($scope.orgUserInfo)
            })
        })
        
        layer.open({
            type: 1,
            title: ['服务师列表', 'text-align:center;font-size:16px;background:#fff;'],
            area: ['800px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.orgUserListDialog')
        });
    }
    
    $scope.restOrgUserPwd = function(id){
        var lay = layer.confirm('确认要重置密码吗?', function () {
            var url = "/orgUser/updatePwd";
            var data = {id:id,pwd: null};
            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                url: url,
                data: data,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend:function(){
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.msg(result.msg)
                  layer.closeAll(lay);
                }
            });
        })
    };

    $scope.getProvinces = function () {
        var url = "/datalist?getProvince";
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.province =result.obj;
            })
        })
    };
    $scope.gerCity = function () {
        var code = $scope.list.p;
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
                        $scope.dis = null;
                    }
                    $scope.city = result.obj;
                    $scope.$apply();
                }
            }
        });
    };
    $scope.gerArea = function () {
        var code = $scope.list.c;
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
                    if($scope.dis != null){
                        $scope.dis = null;
                    }
                    $scope.dis = result.obj;
                    $scope.$apply();
                }
            }
        });
    };

    $scope.search = function () {
        this.findByOrgDataList();
    }

    $scope.operateOrg = function (target,$event,id) {
        var obj = $event.target;
        var text = $(obj).html();
        switch (target) {
            case 0://推荐或取消推荐
                var isRecommend = false;
                var tip;
                if (text == '推荐') {
                    tip = "你确定要推荐该机构吗？";
                    isRecommend = true;
                } else {
                    tip = "你确定要取消推荐该机构吗？";
                    isRecommend = false;
                }
                var url = "/org/data/operate/recommend/"+id;
                var data ={
                    isRecommend : isRecommend
                }
                layer.confirm(tip, function (index) {
                    http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                        $scope.$apply(function () {
                            layer.close(index);
                            if (result.success) {
                                console.log(1);
                                layer.msg("操作成功", {offset: 450});
                                $(obj).parents("tr").find("td:eq(6) a:eq(0)").html()=='推荐'? $(obj).parents("tr").find("td:eq(6) a:eq(0)").html('取消')
                                    .css({'color':'#ff5168'}): $(obj).parents("tr").find("td:eq(6) a:eq(0)").html("推荐").css({'color':'#0093ff'})

                            } else {
                                layer.msg("操作失败！请重试", {offset: 450});
                            }
                        })
                    })
                })
                break;
            case 1://停用或恢复正常
                var status = 0;
                var tip;
                if (text == '停用') {
                    tip = "你确定要停用该机构吗？";
                    status = 1;
                } else {
                    tip = "你确定要恢复该机构吗？";
                }
                var url = "/org/data/operate/status/"+id;
                var data ={
                    status : status
                }
                layer.confirm(tip, function (index) {
                    http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                        $scope.$apply(function () {
                            layer.close(index);
                            if (result.success) {
                                layer.msg("操作成功", {offset: 450});
                                $(obj).parents("tr").find("td:eq(6) a:eq(1)").html()=='停用'? $(obj).parents("tr").find("td:eq(6) a:eq(1)").html('恢复')
                                    .css({'color':'#ff5168'}): $(obj).parents("tr").find("td:eq(6) a:eq(1)").html("推荐").css({'color':'#0093ff'})
                            } else {
                                layer.msg("操作失败！请重试", {offset: 450});
                            }
                        })
                    })
                })
                break;
        }

    }

    $scope.getOrgInfo = function (id) {
        var ct = "#details-org";
        layer.open({
            type: 1,
            title: ['门店信息',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['400px', '450px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $(ct)
        });
        url ="/org/data/getInfo/" + id;
        http.ajax.get(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.orgInfo =result.obj;
            })
        })
    }
}]);

registerController.controller('orgCheckContriller',['$scope',function ($scope,$filter) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数
    };
    $scope.results ={};
    $scope.param = {
        orgOption : '-1',
        reason : null,
        orgVerified : 1
    };

    $scope.orgVeriData =null;

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findByOrgCheck();
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
                            $scope.findByOrgCheck();
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
                        $scope.findByOrgCheck();
                    }
                }
            });
        }
    }

    $scope.findByOrgCheck = function () {
        var url = "/org/data/check/list/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, $scope.param, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                    $scope.page.totalSize = result.obj.totalSize;
                    $scope.page.pageIndex = result.obj.nowPage;
                    $scope.initPage();
                }
                $scope.results = result.obj.data;
                $scope.page.totalSize = result.obj.totalSize;
                $scope.page.pageIndex = result.obj.nowPage;
            })
        })
    }

    $scope.search = function () {
        this.findByOrgCheck();
    }
    /**
     * 获取
     * @param id
     */
    $scope.openVerifyDialog = function (id) {
        var url = "/org/data/check/data/"+1;
        var data ={
            id : id
        }
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.orgVeriData = result.obj;
            })
                layer.open({
                    type: 1,
                    title: ['机构审核',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: ['800px'],
                    offset: 80,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    scrolling: 'no',
                    content: $('.dialog-content')
                });
        })
    }
    /**
     * 提交
     * @param id
     */
    $scope.commitVerifyResult = function (id) {
        console.log($scope.param.orgVerified)
        var url = "/org/data/operate/orgVerify/" +id;
        var data ={
            reason : $scope.param.reason,
            orgVerified : $scope.param.orgVerified
        }
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                layer.closeAll();
                if (result.success) {
                    layer.msg("提交成功", {offset: 450});
                    if($scope.param.orgVerified == 2) {
                        $("td#" + id).html("<span style=\"color: #ff0000;\">未通过</span>&nbsp;&nbsp;<a ng-click=\"removeVerifyOrg(" + id + ")\">删除</a>");
                    }else {
                        $scope.$emit("$orgCheckCountChange");
                        $("td#" + id).html("<span style=\"color: #ff0000;\">通过</span>&nbsp;&nbsp;<a ng-click=\"removeVerifyOrg(" + id + ")\">删除</a>");
                    }
                } else {
                    layer.msg("操作失败！请重试", {offset: 450});
                }
            })
        })
    }
    /**
     * 删除
     * @param id
     */
    $scope.removeVerifyOrg = function (id) {
        layer.confirm("你确认移除掉该记录吗？", function (index) {
            var url = "/org/data/operate/delVeryRecord/" +id;
            var data ={
                orgVerified : 3
            }
            http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    layer.close(index);
                    if (result.success) {
                        layer.msg("操作成功", {offset: 450});
                        $("tr." + id+"1").css("display", "none");
                    } else {
                        layer.msg("操作失败！请重试", {offset: 450});
                    }
                })
            })
        })
    }
}]);