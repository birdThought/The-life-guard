/**
 * 路由配置
 * @type {angular.Module}
 */
var app = angular.module('myApp',['ngRoute','Controllers']);



app.config(['$routeProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide){
    var loadJs = function (dependencies) {
        return function ($q) {
            var deferred = $q.defer();
            $script(dependencies, function () {
                deferred.resolve();
            })
            return deferred.promise;
        }
    }

    $routeProvider
        .when('/',{ /*vip会员列表*/
            templateUrl:'/vip-member/page',
            controller:'vipMemberController',
        })
        .when('/vip-card',{ /*vip邀请码管理 */
            templateUrl:'/vip-card/page',
            controller:'vipCardController'
        })
        .when('/vip-member/financial',{    /*会员结算*/
             templateUrl:'/vip-member/financial/page',
             controller:'vipMemberFinancialController'
        })
        .when('/search-member',{    /*分析报告搜索用户*/
            templateUrl:'/report-analysis/search-member-page',
            controller:'searchMemberController'
        })
        .when('/report-analysis-order',{    /*分析报告订单中心*/
            templateUrl:'/report-analysis/order-page',
            controller:'reportAnalysisOrderController'
        })
        .when('/report-analysis-financial',{    /*分析报告结算*/
            templateUrl:'/report-analysis/page',
            // controller:'reportAnalysisController'
        })
        .when('/message',{   /*消息中心*/
            templateUrl:'/message/page'
        })
        .when('/security',{    /*账户安全*/
            templateUrl:'/account-security/page',
            controller:'accountSecurityController'
        })
        .when('/user-manager',{    /*用户管理*/
        	templateUrl:'/business/toUserManager',
        	controller:'userManagerController'
        })
          .when('/check-detail',{    /*用户管理>查看*/
            templateUrl:'/business/toCheckDetail',
        })
        .when('/user-statistic',{    /*用户统计*/
            templateUrl:'/business/toUserStatistic',
        })
        .when('/month-cost',{    /*本月费用*/
            templateUrl:'/business/toMonthCost',
        })
         .when('/detail-list',{    /*本月费用>消费清单*/
            templateUrl:'/business/toDetailList',
        })
        .when('/account-list',{    /*结算记录*/
            templateUrl:'/business/toAccountList',
        })
        .when('/check-account',{    /*结算记录>查看*/
            templateUrl:'/business/toCheckAccount',
        })
        .when('/code',{    /*推销管理 > 二维码列表*/
            templateUrl:'/code/list',
            controller:'codePackageController'
        }) .when('/sell',{    /*推销管理 > 推销员列表*/
            templateUrl:'/sell/page',
            controller:'sellManageController'
        }).when('/temp',{    /*推销管理 > 推广数据*/
            templateUrl:'/temp/page',
            controller:'temporaryController'
        }).when('/spread',{    /*推销管理 > 结算列表*/
            templateUrl:'/settle/page',
            controller:'settleController'
        })
        .otherwise({redirectTo:'/'});
}]);

/*客服基本信息*/
app.factory('userInfo', function () {
    return {
        getUserInfo: function (callback) {
            var url = '/user/info';
            http.ajax.get_no_loading(true, false, url, null, http.ajax.CONTENT_TYPE_1, function (result) {
                if (result.success) {
                    if (typeof callback == 'function') {
                        callback(result.obj);
                    }
                }
            })
        }
    }
});



