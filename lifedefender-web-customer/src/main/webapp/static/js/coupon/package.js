
/**
 * @author yuhang.weng
 * @Date 2018年1月29日17:03:14
 *
 */
(function(){
    angular.module('Controllers', [])
    .controller('couponPackageController', ['$scope', function($scope) {
        
        /** 申明参数 */
        $scope.data = {
            layer: {
                laypage: null,
                form: null,
                openAddDialogIndex: null
            },
            packageList: [],    // 卡包列表
            storeList: [],      // 门店列表
            serveList: [],      // 服务列表
            businessList: [],   // 渠道商列表
            couponList: [],     // 卡券列表
            packageDetail: null,    // 详情对象
            temp: {
                storeId: null,  // 选择门店
                couponList: [], // 选中卡券列表
            },
            submit: {   // 提交数据
                name: null, // 礼包名称
                instructions: null, // 使用说明
                businessId: null,   // 渠道商id
                templetIdList: [],  // 卡券id列表
            }
        }
        
        /** 申明函数 */
        $scope.fn = {
            init: function() {
                layui.use(['laypage', 'form'], function() {
                    $scope.data.layer.laypage = layui.laypage;
                    
                    var form = layui.form;
                    // 监听选择渠道商
                    form.on('select(businessIdSelect)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("number:")) {
                                $scope.data.submit.businessId = parseInt(value.split(":")[1]); // 得到被选中的值
                            }
                        });
                    });
                    // 监听选择机构
                    form.on('select(storeIdSelect)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("number:")) {
                                $scope.data.temp.storeId = parseInt(value.split(":")[1]); // 得到被选中的值
                            }
                        })
                    });
                    // 监听享受服务
                    form.on('select(serveIdSelect)', function(data) {
                        $scope.$apply(function() {
                            var value = data.value;
                            if (value.startsWith("string:")) {
                                var projectCode = value.split(":")[1];  // 得到被选中的值
                                $scope.service.listCoupon($scope.data.temp.storeId, projectCode, function(result) {
                                    $scope.data.couponList = result.obj;
                                });
                            }
                        });
                    });
                    // 监听checkbox
                    form.on('checkbox(couponCheckbox)', function(data) {
                        $scope.$apply(function() {
                            var checked = data.elem.checked; // 开关是否开启，true或者false
                            var coupon = JSON.parse(data.value);    // 选中的值
                            $scope.fn.clickCoupon(checked, coupon);
                        });
                        $scope.data.layer.form.render('checkbox');
                    });
                    form.render();
                    $scope.data.layer.form = form;
                });
                $scope.page.initPage();  // 初始化分页
                $scope.service.listPackage();   // 获取卡包列表
            },
            // 查看二维码
            showCode: function(name, code, businessId) {
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
                        if (businessId == undefined) {
                            businessId = null;
                        }
                        var qr = '{code:'+ code + ',businessId:' + businessId + '}';
                        var obj = document.getElementById("qrcode");
                        jQuery("#qrcode").empty();
                        var qrcode = new QRCode(obj, {
                            width : 240,//设置宽高
                            height : 240
                        });
                        qrcode.makeCode(qr);
                    }
                });
            },
            // 查看详情
            look: function(data) {
                layer.open({
                    type: 1,
                    title: ['礼包详情', 'text-align:center;font-size:20px;background:#0398fc;color:#FFF;'],
                    area: ['1200px'],
                    offset: 150,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 99,
                    content: $('#packageDetail'),
                    success: function() {
                        $scope.data.packageDetail = data;
                    },
                    end: function() {
                        $scope.data.packageDetail = null;
                    }
                });
            },
            // 删除
            delConfirm: function(data) {
                layer.confirm("你确定删除 [" + data.name + "] 礼包?", {icon:2, title:"警告"}, function(index) {
                    $scope.service.delPackage(data.id, function(result) {
                        // 遍历卡券列表，删除相应的对象
                        var packageList = $scope.data.packageList;
                        var nwArr = [];
                        packageList.forEach(function(value) {
                            if (value != data) {
                                nwArr.push(value);
                            }
                        });
                        $scope.data.packageList = nwArr;
                        // 关闭对话框
                        layer.close(index);
                    });
                });
            },
            // 打开添加窗口
            openAddDialog: function() {
                $scope.data.layer.openAddDialogIndex = layer.open({
                    type: 1,
                    title: ['添加礼包', 'text-align:center;font-size:20px;background:#0398fc;color:#FFF;'],
                    area: ['1200px'],
                    offset: 150,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 99,
                    content: $('#addPackageDialog'),
                    success: function() {
                        // 重置submit数据
                        $scope.fn.resetAddData();
                        // 加载门店列表
                        // 避免多次加载数据
                        if ($scope.data.storeList.length == 0) {
                            // 获取数据
                            $scope.service.listStore(function(result) {
                                $scope.data.storeList = result.obj;
                            });
                        }
                        if ($scope.data.businessList.length == 0) {
                            // 获取数据
                            $scope.service.listBusiness(function(result) {
                                $scope.data.businessList = result.obj;
                            });
                        }
                    }
                });
            },
            // 点击卡券的checkbox事件
            clickCoupon: function(checked, data) {
                if (checked) {
                    for (var i = 0; i < $scope.data.temp.couponList.length; i ++) {
                        var value = $scope.data.temp.couponList[i];
                        // 禁止添加相同的卡券
                        if (value.id == data.id) {
                            layer.msg("请勿添加重复的卡券");
                            return;
                        }
                    }
                    $scope.data.temp.couponList.push(data);
                } else {
                    var nwArr = new Array();
                    $scope.data.temp.couponList.forEach(function(value) {
                        // 添加与data不同的数据
                        if (value.id != data.id) {
                            nwArr.push(value);
                        }
                    });
                    $scope.data.temp.couponList = nwArr;    // 重新赋值
                }
            },
            // 是否已勾选了该卡券
            hasCheckedCoupon: function(id) {
                $scope.data.temp.couponList.forEach(function(value) {
                    if (value.id == id) {
                        return true;
                    }
                });
                return false;
            },
            // 添加
            addPackage: function() {
                var name = $scope.data.submit.name;
                var instructions = $scope.data.submit.instructions;
                var businessId = $scope.data.submit.businessId;
                var couponList = $scope.data.temp.couponList;
                
                if (!name) {
                    layer.msg("请填写礼包名称");
                    $("input[ng-model='data.submit.name']").focus();
                    return;
                }
                if (!instructions) {
                    layer.msg("请填写使用说明");
                    $("input[ng-model='data.submit.instructions']").focus();
                    return;
                }
                if (!businessId) {
                    layer.msg("请选择渠道商");
                    return;
                }
                if (couponList.length == 0) {
                    layer.msg("请添加关联卡券");
                    return;
                }
                
                var nwArray = new Array();
                couponList.forEach(function(coupon) {
                    nwArray.push(coupon.id);
                });
                $scope.data.submit.templetIdList = nwArray;
                // 添加
                $scope.service.addPackage($scope.data.submit, function(result) {
                    $scope.service.listPackage();
                    // 关闭窗口
                    layer.close($scope.data.layer.openAddDialogIndex);
                });
            },
            // 重置添加礼包数据
            resetAddData: function() {
                // 提交数据
                $scope.data.submit = {
                    name: null, // 礼包名称
                    instructions: null, // 使用说明
                    businessId: null,   // 渠道商id
                    templetIdList: [],  // 卡券id列表
                };
                
                $scope.data.serveList = [];      // 服务列表
                $scope.data.businessList = [];   // 渠道商列表
                $scope.data.couponList = [];     // 卡券列表
                $scope.data.temp.storeId = null;    // 门店id
                $scope.data.temp.couponList = []; // 卡券列表
                
                // 重置layui的select下拉框
                $("select[name='businessSelect']").next("div.layui-form-select").find("input[type='text']").eq(0).val("");
                $("select[name='storeSelect']").next("div.layui-form-select").find("input[type='text']").eq(0).val("");
                $("select[name='serveSelect']").next("div.layui-form-select").find("input[type='text']").eq(0).val("");
            }
        }
        
        /** service */
        $scope.service = {
            // 获取卡包列表
            listPackage: function() {
                var url = '/coupon/package/list/' + $scope.page.pageIndex;
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        var totalSize = result.totalSize;
                        var pageIndex = result.nowPage;
                        // 如果查询结果是有数据，并且总数据量发生改变，就初始化分页
                        if (($scope.page.totalSize != 0 || totalSize > 0) && ($scope.page.totalSize != totalSize)) {
                            $scope.page.totalSize = totalSize;
                            $scope.page.pageIndex = pageIndex;
                            $scope.page.initPage();  // 初始化分页
                        }
                        // 更新卡券列表的数据
                        $scope.data.packageList = result.data;
                        $scope.page.totalSize = totalSize;
                        $scope.page.pageIndex = pageIndex;
                    });
                });
            },
            // 删除卡包
            delPackage: function(id, callBack) {
                var url = '/coupon/package/del/' + id;
                http.ajax.del(url, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                });
            },
            // 获取渠道商列表
            listBusiness: function(callBack) {
                var url = '/coupon/business/businesses';
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                    $scope.data.layer.form.render('select');    // 重新渲染
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
            // 获取卡券列表
            listCoupon: function(storeId, projectCode, callBack) {
                var url = "/coupon/orgId/" + storeId + "/projectCode/" + projectCode;
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                    $scope.data.layer.form.render('checkbox');
                });
            },
            // 添加礼包
            addPackage: function(data, callBack) {
                var url = '/coupon/package/add';
                http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function(result) {
                    $scope.$apply(function() {
                        callBack(result);
                    });
                });
            }
        }
        
        /** 监听 */
        $scope.$watch('data.temp.storeId', function(nValue, oValue) {
            if (nValue == null || nValue == oValue) {
                return;
            }
            $scope.service.listStoreServe(nValue, function(result) {
                $scope.data.serveList = result.obj;
            });
        });
        
        /** 分页相关 */
        $scope.page = {
            // 参数
            pageIndex: 1,
            pageSize: 10,
            totalSize: 0,
            // 初始化分页工具
            initPage: function() {
                if ($scope.data.layer.laypage == null) {
                    setTimeout(function () {
                        console.log('wait for laypage...');
                        $scope.page.render();
                    }, 300);
                } else {
                    $scope.page.render();
                }
            },
            render: function() {
                $scope.data.layer.laypage.render({
                    elem: 'page',
                    count: $scope.page.totalSize,
                    limit: $scope.page.pageSize,
                    theme: '#00bfff',
                    layout: ['count', 'prev', 'page', 'next', 'skip'],
                    jump: function(obj, first) {
                        if(!first) {
                            $scope.$apply(function () {
                                $scope.page.pageIndex = obj.curr;
                                $scope.service.listPackage();
                            });
                        }
                    }
                });
            }
        }
    }]);
}())