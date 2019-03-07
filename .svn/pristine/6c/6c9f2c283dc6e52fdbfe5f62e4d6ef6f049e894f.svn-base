
/**
 * @author yuhang.weng
 * @Date 2018年1月29日17:03:14
 *
 */
(function() {
    angular.module('Controllers', [])
    .controller('couponController', ['$scope', function($scope) {
        
        /** 申明参数 */
        $scope.data = {
            layer: {
                form: null,
                addNewDialogIndex: null
            },
            couponList: [], // 卡券列表
            storeList: [],  // 门店列表
            serveList: [],  // 服务列表
            submit: {
                name: null, // 卡券名称
                price: null,    // 卡券金额
                storeId: null,  // 门店id
                projectCode: null,  // 项目code
                projectName: null,  // 项目名称
                serveItemId: null,  // 服务项目id
                serveItemName: null,// 服务项目名称
                serveCode: null,    // 服务code
            }
        }
        
        /** 申明函数 */
        $scope.fn = {
            // 初始化方法
            init: function() {
                $scope.page.initPage();  // 初始化分页
                $scope.service.listCoupon();    // 获取卡券列表
                
                layui.use('form', function () {
                    var form = layui.form;
                    // 监听选择机构
                    form.on('select(storeIdSelect)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("number:")) {
                                $scope.data.submit.storeId = parseInt(value.split(":")[1]); // 得到被选中的值
                            }
                        })
                    });
                    // 监听享受服务
                    form.on('select(serveIdSelect)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("string:")) {
                                var projectCode = value.split(":")[1];  // 得到被选中的值
                                for (var i = 0; i < $scope.data.serveList.length; i++) {
                                    var serve = $scope.data.serveList[i];
                                    if (serve.projectCode == projectCode) {
                                        $scope.data.submit.projectCode = serve.projectCode;
                                        $scope.data.submit.projectName = serve.projectName;
                                        $scope.data.submit.serveCode = serve.serveCode;
                                        break;
                                    }
                                }
                            }
                        });
                    });
                    form.render();
                    $scope.data.layer.form = form;
                });
            },
            // 打开添加窗口
            openDialog: function() {
                $scope.data.layer.addNewDialogIndex = layer.open({
                    type: 1,
                    title: ['添加卡券', 'text-align:center;font-size:20px;background:#0398fc;color:#FFF;'],
                    area: ['750px', '650px'],
                    offset: 150,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 99,
                    content: $('#addCouponDialog'),
                    success: function() {
                        // 重置submit数据
                        $scope.fn.resetAddNewDialog();
                        // 加载门店列表
                        // 避免多次加载数据
                        if ($scope.data.storeList.length != 0) {
                            return;
                        }
                        // 获取数据
                        $scope.service.listStore(function(result) {
                            if (result.success) {
                                $scope.data.storeList = result.obj;
                            }
                        });
                    }
                });
            },
            // 添加
            addNewSubmit: function() {
                var name = $scope.data.submit.name;
                var storeId = $scope.data.submit.storeId;
                var serveCode = $scope.data.submit.serveCode;
                var serveItemId = $scope.data.submit.serveItemId;
                var serveItemName = $scope.data.submit.serveItemName;
                var projectCode = $scope.data.submit.projectCode;
                var projectName = $scope.data.submit.projectName;
                var price = $scope.data.submit.price;
                var validDay = $scope.data.submit.validDay;
                
                // 数据校验
                if (name == null || name.length == 0) {
                    layer.msg("卡券名称不能为空");
                    $("input[ng-model='data.submit.name']").focus();
                    return;
                }
                if (price == null || price.length == 0) {
                    layer.msg("请输入卡券金额");
                    $("input[ng-model='data.submit.price']").focus();
                    return;
                }
                if (storeId == null || storeId.length == 0) {
                    layer.msg("请指定机构");
                    return;
                }
                if (projectCode == null || projectCode.length == 0) {
                    layer.msg("请选择服务");
                    return;
                }
                if (validDay == null || validDay.length == 0) {
                    layer.msg("请填写有效时间");
                    $("input[ng-model='data.submit.validDay']").focus();
                    return;
                }
                
                var json = {
                    name: name,
                    orgId: storeId,
                    serveCode: serveCode,
                    serveItemId: serveItemId,
                    serveItemName: serveItemName,
                    projectCode: projectCode,
                    projectName: projectName,
                    price: price,
                    validDay: validDay
                };
                $scope.service.addCoupon(JSON.stringify(json), function(result) {
                    // 在回调函数函数里更新List内容
                    $scope.service.listCoupon();
                });
                layer.close($scope.data.layer.addNewDialogIndex);
            },
            // 删除
            delConfirm: function(data) {
                layer.confirm("你确定删除 [" + data.name + "] 卡券?", {icon:2, title:"警告"}, function(index) {
                    $scope.service.delCoupon(data.id, function(result) {
                        // 遍历卡券列表，删除相应的对象
                        var couponList = $scope.data.couponList;
                        var nwArr = [];
                        couponList.forEach(function(value) {
                            if (value != data) {
                                nwArr.push(value);
                            }
                        });
                        $scope.data.couponList = nwArr;
                        // 关闭对话框
                        layer.close(index);
                    });
                });
            },
            // 重置添加数据对话框内容
            resetAddNewDialog: function() {
                $scope.data.submit = {
                    name: null,
                    price: null,
                    storeId: null,
                    projectCode: null,
                    projectName: null,
                    serveItemId: null,
                    serveItemName: null,
                    serveCode: null
                };
                // 重置layui的select下拉框
                $("select[name='storeSelect']").next("div.layui-form-select").find("input[type='text']").eq(0).val("");
                $("select[name='serveSelect']").next("div.layui-form-select").find("input[type='text']").eq(0).val("");
            }
        }
        
        /** service */
        $scope.service = {
             // 分页获取卡券列表
            listCoupon: function() {
                var url = '/coupon/list/' + $scope.page.pageIndex;
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        // 如果查询结果是有数据，并且总数据量发生改变，就初始化分页
                        if (($scope.page.totalSize != 0 || result.totalSize > 0) && ($scope.page.totalSize != result.totalSize)) {
                            $scope.page.totalSize = result.totalSize;
                            $scope.page.pageIndex = result.nowPage;
                            $scope.page.initPage();  // 初始化分页
                        }
                        // 更新卡券列表的数据
                        $scope.data.couponList = result.data;
                        $scope.page.totalSize = result.totalSize;
                        $scope.page.pageIndex = result.nowPage;
                    });
                });
            },
            // 删除
            delCoupon: function(id, callBack) {
                var url = '/coupon/del/' + id;
                http.ajax.del(url, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                });
            },
            // 获取门店列表
            listStore: function(callBack) {
                var url = '/coupon/store/stores';
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                    $scope.data.layer.form.render('select');    // 重新渲染
                });
            },
            // 获取门店下服务列表
            listStoreServe: function(storeId, callBack) {
                var url = '/coupon/store/' + storeId + "/serve";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                    $scope.data.layer.form.render('select');    // 重新渲染
                });
            },
            // 添加卡券
            addCoupon: function(data, callBack) {
                var url = '/coupon/add';
                http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_2, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                });
            }
        }
        
        /** 监听数据 */
        $scope.$watch("data.submit.storeId", function(nValue, oValue) {
            if (nValue == null || nValue == oValue) {
                return;
            }
            $scope.service.listStoreServe(nValue, function(result) {
                $scope.data.serveList = result.obj;
            });
        });
        
        /** 过滤器 */
        $scope.clearNoNum = function(obj, attr) {
            //先把非数字的都替换掉，除了数字和.
            obj[attr] = obj[attr].replace(/[^\d.]/g,"");
            //必须保证第一个为数字而不是.
            obj[attr] = obj[attr].replace(/^\./g,"");
            //保证只有出现一个.而没有多个.
            obj[attr] = obj[attr].replace(/\.{2,}/g,"");
            //保证.只出现一次，而不能出现两次以上
            //保证小数点后留两位
            obj[attr] = obj[attr].replace(".","$#$").replace(/\./g,"").replace("$#$",".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
        }
        
        $scope.clearNoDigit = function(obj, attr){
            obj[attr] = obj[attr].replace(/\D/g,"");
        }
        
        /** 分页相关 */
        $scope.page = {
            // 参数
            pageIndex: 1,
            pageSize: 10,
            totalSize: 0,
            // 初始化分页工具
            initPage: function() {
                if (typeof laypage == 'undefined') {
                    setTimeout(function () {
                        console.log('wait for laypage...');
                        $scope.page.render();
                    }, 300);
                } else {
                    $scope.page.render();
                }
            },
            render: function() {
                laypage.render({
                    elem: 'page',
                    count: $scope.page.totalSize,
                    limit: $scope.page.pageSize,
                    theme: '#00bfff',
                    layout: ['count', 'prev', 'page', 'next', 'skip'],
                    jump: function(obj, first) {
                        if(!first) {
                            $scope.$apply(function () {
                                $scope.page.pageIndex = obj.curr;
                                $scope.service.listCoupon();
                            });
                        }
                    }
                });
            }
        }
    }]);
}())