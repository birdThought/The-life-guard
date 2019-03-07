/**
 * 用户搜索
 * author: wenxian.cai
 * date: 2017/10/25 10:33
 */

registerController.controller('searchMemberController',['$scope', function ($scope) {
    /** 申明参数 */

    $scope.members = null;  //用户集合
    $scope.conditions = {
        keyword: null,
    }
    $scope.ruanData = null;  //尿检数据
    $scope.ruanData1 = null;  //血脂数据
    $scope.ruanData2 = null;  //尿酸数据
    $scope.ruanData3 = null;  //心电数据
    $scope.ruanData4 = null;  //血糖数据

    /** 声明函数 */

    /*初始化*/
    $scope.init = function () {
        $('.layui-nav').find('.layui-nav-item').eq(1).addClass('layui-nav-itemed')
        $('.layui-nav-itemed .layui-nav-child .layui-this').removeClass('layui-this');
        $('.layui-nav-itemed .layui-nav-child').find('dd').eq(0).addClass('layui-this');

    }

    /*获取用户列表*/
    $scope.listMembers = function () {
        var data = $scope.conditions;
        var url = '/report-analysis/members';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.members = result.obj;
            });
        })
    }

    /*搜索用户*/
    $scope.searchMember = function () {
        if ($.trim($scope.conditions.keyword) == '') {
            layer.msg('请输入搜索内容');
            return;
        }
        $scope.listMembers();
    }

    $scope.enterSearchMember = function($event) {
        if ($event.keyCode == 13) {
            $scope.searchMember();
        }
    }
    
    /*弹出尿检数据提交框*/
    $scope.popupRuanDialog = function (userId) {
        layer.open({
            type:1,
            content:$("#submitData"),
            title:"<p class='layer-report' style='line-height: 56px;font-size: 16px; letter-spacing: 1.5px'>尿检分析</p>",
            area:['550px','auto'],
            success: function () {
                $scope.getRuanData(userId);

            }
        })
    }
    /*弹出血脂数据提交框*/
    $scope.popupRuanDialog1 = function (userId) {
        layer.open({
            type:1,
            content:$("#submitData1"),
            title:"<p class='layer-report' style='line-height: 56px;font-size: 16px; letter-spacing: 1.5px'>血脂分析</p>",
            area:['550px','auto'],
            success: function () {
                $scope.getRuanData1(userId);

            }
        })
    }

    /*弹出尿酸数据提交框*/
    $scope.popupRuanDialog2 = function (userId) {
        layer.open({
            type:1,
            content:$("#submitData2"),
            title:"<p class='layer-report' style='line-height: 56px;font-size: 16px; letter-spacing: 1.5px'>尿酸分析</p>",
            area:['550px','auto'],
            success: function () {
                $scope.getRuanData2(userId);

            }
        })
    }
    /*弹出心电数据提交框*/
    $scope.popupRuanDialog3 = function (userId) {
        layer.open({
            type:1,
            content:$("#submitData3"),
            title:"<p class='layer-report' style='line-height: 56px;font-size: 16px; letter-spacing: 1.5px'>心电分析</p>",
            area:['550px','auto'],
            success: function () {
                $scope.getRuanData3(userId);

            }
        })
    }
    /*弹出血糖数据提交框*/
    $scope.popupRuanDialog4 = function (userId) {
        layer.open({
            type:1,
            content:$("#submitData4"),
            title:"<p class='layer-report' style='line-height: 56px;font-size: 16px; letter-spacing: 1.5px'>血糖分析</p>",
            area:['550px','auto'],
            success: function () {
                $scope.getRuanData4(userId);

            }
        })
    }

    /*获取尿检数据*/
    $scope.getRuanData = function (userId) {
        var data = {
            userId: userId
        };
        var url = '/report-analysis/uran/lastest';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.ruanData = result.obj;
            });
        })
    }

    /*获取血脂数据*/
    $scope.getRuanData1 = function (userId) {
        var data = {
            userId: userId
        };
        var url = '/report-analysis/blood/lastest';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.ruanData1 = result.obj;
            });
        })
    }

    /*获取尿酸数据*/
    $scope.getRuanData2 = function (userId) {
        var data = {
            userId: userId
        };
        var url = '/report-analysis/ua/lastest';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.ruanData2 = result.obj;
            });
        })
    }

    /*获取心电数据*/
    $scope.getRuanData3 = function (userId) {
        var data = {
            userId: userId
        };
        var url = '/report-analysis/ecg/lastest';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.ruanData3 = result.obj;
            });
        })
    }

    /*获取血糖数据*/
    $scope.getRuanData4 = function (userId) {
        var data = {
            userId: userId
        };
        var url = '/report-analysis/glu/lastest';
        http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            $scope.$apply(function () {
                $scope.ruanData4 = result.obj;
            });
        })
    }

    /*提交尿检分析报告订单*/
    $scope.submitOrder = function () {
        var userId = $scope.ruanData.userId;
        var content = {
            BIL: $scope.ruanData.bIL,
            BLD: $scope.ruanData.bLD,
            GLU: $scope.ruanData.gLU,
            KET: $scope.ruanData.kET,
            LEU: $scope.ruanData.lEU,
            NIT: $scope.ruanData.nIT,
            PH: $scope.ruanData.pH,
            pRO: $scope.ruanData.pRO,
            SG: $scope.ruanData.sG,
            UBG: $scope.ruanData.uBG,
            VC: $scope.ruanData.vC
        }
        var status = $scope.ruanData.status == 0 ? 0 : 1;
        var data = {
            userId: userId,
            content: JSON.stringify(content),
            status: status
        };
        var url = '/report-analysis/order/uran';
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('创建订单成功，正跳转到支付界面...');
                setTimeout(function () {
                    var orderNumber = result.obj.orderNumber;
                    var cash = result.obj.price;
                    var orderId = null;
                    var payType = 2;
                    var subject = '分析报告';
                    //跳转支付
                    window.location.href = '/common/alipay?order=' + orderNumber + '&cash='
                    + cash + '&payType=' + payType + '&subject=' + subject;
                }, 300);

                return;
            }
            layer.msg('操作失败，请重新尝试!');
        })
    }

    /*提交血脂分析报告订单*/
    $scope.submitOrder1 = function () {
        var userId = $scope.ruanData1.userId;
        var content = {
            HDL: $scope.ruanData1.hDL,
            LDL: $scope.ruanData1.lDL,
            TG : $scope.ruanData1.tG,
            TC : $scope.ruanData1.tC

        }
        var status = $scope.ruanData1.status == 0 ? 0 : 1;
        var data = {
            userId: userId,
            content: JSON.stringify(content),
            status: status
        };
        var url = '/report-analysis/order/blood';
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('创建订单成功，正跳转到支付界面...');
                setTimeout(function () {
                    var orderNumber = result.obj.orderNumber;
                    var cash = result.obj.price;
                    var orderId = null;
                    var payType = 2;
                    var subject = '血脂分析';
                    //跳转支付
                    window.location.href = '/common/alipay?order=' + orderNumber + '&cash='
                        + cash + '&payType=' + payType + '&subject=' + subject;
                }, 300);

                return;
            }
            layer.msg('操作失败，请重新尝试!');
        })
    };

    /*提交尿酸分析报告订单*/
    $scope.submitOrder2 = function () {
        var userId = $scope.ruanData2.userId;
        var content = {
            UA: $scope.ruanData2.uA,
        }
        var status = $scope.ruanData2.status == 0 ? 0 : 1;
        var data = {
            userId: userId,
            content: JSON.stringify(content),
            status: status
        };
        var url = '/report-analysis/order/ua';
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('创建订单成功，正跳转到支付界面...');
                setTimeout(function () {
                    var orderNumber = result.obj.orderNumber;
                    var cash = result.obj.price;
                    var orderId = null;
                    var payType = 2;
                    var subject = '尿酸分析';
                    //跳转支付
                    window.location.href = '/common/alipay?order=' + orderNumber + '&cash='
                        + cash + '&payType=' + payType + '&subject=' + subject;
                }, 300);

                return;
            }
            layer.msg('操作失败，请重新尝试!');
        })
    }
    /*提交心电分析报告订单*/

   var limitOrder=  $scope.submitOrder3 = function () {
        var userId = $scope.ruanData3[0].userId;
        var content = {
            HEARRATE: $scope.ruanData3[0].detailList[0].heartRate,
            IMAGE: $scope.ruanData3[0].detailList[0].image
        }
        var status = $scope.ruanData3[0].detailList[0].status == 0 ? 0 : 1;
        var data = {
            userId: userId,
            content: JSON.stringify(content),
            status: status
        };
        var url = '/report-analysis/order/ecg';
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('创建订单成功，正跳转到支付界面...');
                setTimeout(function () {
                    var orderNumber = result.obj.orderNumber;
                    var cash = result.obj.price;
                    var orderId = null;
                    var payType = 2;
                    var subject = '心电分析';
                    //跳转支付
                    window.location.href = '/common/alipay?order=' + orderNumber + '&cash='
                        + cash + '&payType=' + payType + '&subject=' + subject;
                }, 500);

                return;
            }
            layer.msg('操作失败，请重新尝试!');
        })
    }

    $scope.testOne=function(){
        setTimeout(function(){
            limitOrder();
        },300)
    };
    /*提交血糖分析报告订单*/
    $scope.submitOrder4 = function () {
        var userId = $scope.ruanData4.userId;
        var content = {
            BLOODSUGAR: $scope.ruanData4.bloodSugar,
            BLOODSUGARAREA: $scope.ruanData4.bloodSugarArea
        }
        var status = $scope.ruanData4.bloodSugar.status == 0 ? 0 : 1;
        var data = {
            userId: userId,
            content: JSON.stringify(content),
            status: status
        };
        var url = '/report-analysis/order/glu';
        http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
            if (result.success) {
                layer.msg('创建订单成功，正跳转到支付界面...');
                setTimeout(function () {
                    var orderNumber = result.obj.orderNumber;
                    var cash = result.obj.price;
                    var orderId = null;
                    var payType = 2;
                    var subject = '血糖分析';
                    //跳转支付
                    window.location.href = '/common/alipay?order=' + orderNumber + '&cash='
                        + cash + '&payType=' + payType + '&subject=' + subject;
                }, 300);

                return;
            }
            layer.msg('操作失败，请重新尝试!');
        })
    }

    /** 参数监听 */
}]);