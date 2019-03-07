
/** 用户列表*/
registerController.controller('memberReportController',['$scope', function ($scope) {
    $scope.page = {
        pageIndex: 1,
        pageSize: 14,
        totalSize: 0,//总数

    };
    $scope.results = {};
    $scope.details =null;
    $scope.target = {
        reply : null,
        id : null,
    }

    $scope.init = function () {
        setTimeout(function () {
            $scope.initPage();
        }, 300);
        $scope.findByReportdatalists();
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
                            $scope.findByReportdatalists();
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
                        $scope.findByReportdatalists();
                    }
                }
            });
        }

    }

    $scope.findByReportdatalists = function () {
        var url = "/member/data/report/" + $scope.page.pageIndex;
        http.ajax.post(true, true, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
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
    
    $scope.delete = function (id) {
        var temp = $(".service_table tr#" + id );
        var msg = "您真的确定要删除吗？\r\n请确认！";
        if (confirm(msg)==true){
            var url = "/member/data/report/del/"+ id;
            http.ajax.post(true,true,url,null,http.ajax.CONTENT_TYPE_1,function (result) {
                $scope.$apply(function () {
                    if (result.success) {
                        //提示
                        layer.msg("删除成功", {offset: 450});
                        // location.reload();
                        //事件传播
                        $scope.$emit('$reportCountChange');

                        //隐藏被删除的项目
                         temp.css({"display":"none"});
                        return;
                    }else {
                        layer.msg("删除失败！请重试", {offset: 450});
                    }
                })
            })
             return true;
        }else{
            return false;
        }
}
    
    $scope.replyReport = function (id, msg, reply) {
        $scope.target = {id: id, message: msg, reply: reply};
        layer.open({
            type: 1,
            title: ['回复',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['600px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $('.dialog-content')
        });
        
    }
    
    $scope.replyMsg = function () {
      var data = {
          reply : $scope.target.reply,
          id : $scope.target.id
      }
        var url = "/member/data/report/update"
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
               if(true == result.success) {
                   layer.msg("操作成功", {offset: 450});
                   setTimeout(function () {
                       window.location.reload();
                   }, 500);
               }else {
                   layer.msg("操作失败！请重试", {offset: 450});
               }
            })
        })
    }
    $scope.getReportUser = function (userType, userId) {
        url ="/member/data/report/findId/";
        var data ={
            userType :userType,
            userId :userId
        }
        http.ajax.post(true,true,url,data,http.ajax.CONTENT_TYPE_1,function (result) {
            $scope.$apply(function () {
                $scope.details = result.obj;
            })
        })
        var ct = "#details-member";

        if (userType == 1){
            ct = "#details-orgUser";
        }
        layer.open({
            type: 1,
            title: ['用户信息',
                'text-align:center;font-size:16px;background:#fff;'],
            area: ['700px'],
            offset: 150,
            moveType: 1,
            scrollbar: false,
            zIndex: 99,
            scrolling: 'no',
            content: $(ct)
        });
    }
}]);