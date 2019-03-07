/**
 * vip会员卡（邀请码）管理
 * @type {*}
 */
(function() {
    angular.module("Controllers", [])
    .controller('vipCardController', ['$scope',function ($scope) {

        /** 申明参数 */
        $scope.page = {
            pageIndex: 1,
            pageSize: 10,
            totalSize: 0
        }
        $scope.cards = null;    //会员卡列表
        $scope.conditions = {
            cardStatus: "0",
            keyword: null
        }
        $scope.applyAmount = 1; //申请会员卡数量
        $scope.vipCombos = null;
        $scope.selectVipComboId = 1;
        $scope.businessUsers = null; //渠道商用户列表
        $scope.selectBusinessId = "0";

        /** 申明函数 */
        
        /*控制器初始化*/
        $scope.init = function () {
//            setTimeout(function () {
//                $('.content-left ul li').eq(1).click();
//            }, 100)
            $scope.listVipCard();
            $scope.initPage();
        }
        /*获取会员卡列表*/
        $scope.listVipCard = function () {
            var url = '/combo/invite-code/list/' + $scope.page.pageIndex;
            var data = $scope.conditions;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                     if (($scope.page.totalSize != 0 || result.obj.totalSize > 0) && $scope.page.totalSize != result.obj.totalSize) {
                         $scope.page.totalSize = result.obj.totalSize;
                         $scope.page.pageIndex = result.obj.nowPage;
                         $scope.initPage();
                     }
                     $scope.cards = result.obj.data;
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
                                $scope.listVipCard();
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
                            $scope.$apply(function () {
                                $scope.page.pageIndex = obj.curr;
                                $scope.listVipCard();
                            })

                        }
                    }
                });
            }
        }
        
        /*搜索*/
        $scope.search = function () {
            $scope.page.pageIndex = 1;
            $scope.listVipCard();
        }

        /*弹出创建卡号窗口*/
        $scope.popupApplyVipCardDialog = function() {
            var index = layer.open({
                type:1,
                content:$("#serviceCardPopup"),
                title:'<p style="text-align: center;letter-spacing: 1.8px;font-weight: 700">批量创建卡号</p>',
                area:['700px','500px'],
                success: function () {
                    $scope.listVipCombo();
                    $scope.listBusinessUser();
                }
            })
        }
        
        /*申请会员卡*/
        $scope.applyVipCard = function () {
            var url = '/combo/invite-code/apply';
            var data = {
                vipComboId: $('input[name="selectSame"]:checked').val(),
                amount: $scope.applyAmount,
                businessId: $scope.selectBusinessId
            }
            if (data.vipComboId == undefined) {
                layer.msg('请选择套餐');
                return;
            }
            if (Number(data.amount) <= 0) {
                layer.msg('数量需大于0');
                return;
            }
            if (Number(data.businessId) == 0) {
                layer.msg('请选择渠道商');
                return;
            }
            http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    layer.msg('创建成功!')
                    setTimeout(function () {
                        layer.closeAll('page');
                    }, 200)
                    return;
                }
                layer.msg('创建失败，请重新尝试!')
            })
        }

        /*获取vip套餐*/
        $scope.listVipCombo = function () {
            var url = '/combo/list/id';
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    $scope.vipCombos = result.obj;
                })
            })
        }

        /*获取渠道商用户列表*/
        $scope.listBusinessUser = function () {
            var url = '/business/user/list';
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                $scope.$apply(function () {
                    $scope.businessUsers = result.obj.data;
                })
            })
        }
        
        /** 参数监听 */
        $scope.$watch('conditions.cardStatus', function (newValue, oldValue, scope) {
            if (newValue === oldValue) {
                return;
            }
            $scope.page.pageIndex = 1;
            $scope.listVipCard();
        })


    }]);
}());