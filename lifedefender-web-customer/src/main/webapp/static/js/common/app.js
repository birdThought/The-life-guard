/**
* 路由配置
* @type {angular.Module}
*/
var app = angular.module('myApp',['ngRoute','Controllers','oc.lazyLoad']);
/*app.controller('permissionController',function($scope,$http,$routeParams){

});*/
app.config(['$routeProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide', '$ocLazyLoadProvider', function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $ocLazyLoadProvider){
    /*var loadJs = function (dependencies) {
        return function ($q) {
            var deferred = $q.defer();
            $script(dependencies, function () {
                deferred.resolve();
            })
            return deferred.promise;
        }
    }*/
    $routeProvider
        .when('/',{
            templateUrl:'/index/home',
            controller:''
         })
        .when('/member',{     /*用户列表*/
            templateUrl:'/member/page',
            controller:'memberController'
        })
        .when('/member/offline',{     /*业务员*/
            templateUrl:'/member/offline/page',
            controller:'memberOfflineController'/*,
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/member/memberOffline.js?v=1.0.0']);
                }]
            }*/
        })
        .when('/member/count',{     /*用户统计*/
            templateUrl:'/member/count/page',
            controller:'memberCountController' 
        })
        .when('/member/c3',{     /*C3用户*/
            templateUrl:'/member/c3/page',
            controller:'memberC3Controller'
        })
        .when('/member/hx',{     /*环信*/
            templateUrl:'/member/hx/page',
            controller:'memberHxController'
        })
        .when('/member/report',{     /*反馈*/
            templateUrl:'/member/report/page',
            controller:'memberReportController'
        })
        .when('/org',{     /*门店查看*/
            templateUrl:'/org/page',
            controller:'orgListController'
        })
        .when('/org/add',{     /*门店添加*/
            templateUrl:'/org/add/page',
            controller:'orgAddPageController'
        })
        .when('/org/check',{     /*门店审核*/
            templateUrl:'/org/check/page',
            controller:'orgCheckContriller'
        })
        .when('/org/recommend',{     /*推荐门店*/
            templateUrl:'/org/recommend/page',
            controller:'orgRcmController'
        })
        .when('/serve/list', {     /*服务查看*/
            templateUrl: '/serve/page',
            controller: 'storeProjectController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load(['/static/js/serve/store-project-list.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/serve/count', {     /*服务统计*/
            templateUrl: '/serve/statistics/page',
            controller: 'statisticsController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load(['/static/js/serve/statistics.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/serve', {
            templateUrl: '/serve/page',
            controller: 'serveController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load(['/static/js/serve/serve.js?v=0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/order', {
            templateUrl: '/order/page',
            controller: 'orderListController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/ordermanager/order-list.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/order/count', {
            templateUrl: '/order/count/page',
            controller: 'orderCountController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/ordermanager/order-count.js?v=0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0'
                    ]);
                }]
            }
        })
        .when('/order/flow',{     /*交易流水*/
            templateUrl:'/order/flow/page',
            controller:'orderFlowController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/order/order-flow.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/order/flow/count',{     /*交易流水统计*/
            templateUrl:'/order/flow/count/page',
            controller:'orderFlowController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/order/order-flow.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/order/statement', {
            templateUrl: '/order/statement/page',
            controller: 'statementController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load(['/static/js/ordermanager/order-statement.js?v=0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0']);
                }]
            }
        })
        .when('/news',{     /*资讯管理*/
            templateUrl:'/news/page',
            controller:'consultManagerController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/consult/consult-manager.js?v=1.0.0',
                        '/static/css/consultManger/consultResult.css?v=1.0.0']);
                }]
            }
        })
        .when('/news/column',{     /*栏目管理*/
            templateUrl:'/news/column/page',
            controller:'columnManagerController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/consult/column-manager.js?v=1.0.0']);
                }]
            }
        })
        .when('/news/detail',{     /*资讯内容*/
            templateUrl:'/news/details/page',
            controller:'informationViewController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/consult/information-view.js?v=1.0.0']);
                }]
            }
        })
        .when('/combo',{		/*套餐管理*/
            templateUrl:'/combo/page',
            controller:'comboManageController'
        })
        .when('/combo/item',{		/*套餐项管理*/
            templateUrl:'/combo/item/page',
            controller:'comboItemManageController'
        })
        .when('/combo/invite-code',{ /*邀请码管理 */
            templateUrl:'/combo/invite-code/page',
            controller:'vipCardController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/vipcard/vip-card.js?v=1.0.1',
                        '/static/css/vipcard/vip-card.css?v=1.0.0']);
                }]
            }
        })
        .when('/combo/member',{ /*套餐会员列表*/
            templateUrl:'/combo/member/page',
            controller:'vipMemberController',
        })
        .when('/combo/member/worklist',{ /*会员服务预约*/
            templateUrl:'/combo/member/worklist/page',
            controller:'customerOrderController'
        })
        .when('/combo/member/worklist/finish', {    /*登记预约信息*/
            templateUrl:'/combo/member/worklist/finish/page'
            /*controller: 'accountSecurityController'*/
        })
        .when('/report-analysis',{    /*分析报告订单中心*/
            templateUrl:'/report-analysis/page',
            controller:'reportAnalysisController'
        })
        .when('/drugs/order',{
            templateUrl:'/drugs/order/page',
            controller:'drugsOrderManageController'   
        })
        .when('/drugs',{
            templateUrl:'/drugs/page',           
            controller:'drugsManageController'  
        })
        .when('/sms',{     /*短信记录*/
            templateUrl:'/sms/page',
            controller:'smsRecordController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/message/sms-record.js?v=1.0.0']);
                }]
            }
        })
        .when('/sms/send',{     /*发送短信*/
            templateUrl:'/sms/send/page',
            controller:'sendSmsController',
            resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/message/send-sms.js?v=1.0.0']);
                }]
            }
        })
        .when('/push',{/*推送管理*/
            templateUrl:'/push/page',
            controller:'customerPushController'
        })
        .when('/coupon', {
            templateUrl:'/coupon/index',
            controller:'couponController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'static/js/coupon/coupon.js?v=1.0.1',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0',
                        '/static/css/coupon/coupon.css?v=1.0.0']);
                }]
            }
        })
        .when('/coupon/package', {
            templateUrl:'/coupon/package/index',
            controller:'couponPackageController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/coupon/package.js?v=1.0.0',
                        '/static/plugin/qrcode/qrcode.min.js',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0',
                        '/static/css/coupon/coupon.css?v=1.0.0']);
                }]
            }
        })
        .when('/business',{     /*渠道商管理*/
            templateUrl:'/business/page',
            controller:'businessController'
        })
        .when('/business/add',{     /*添加渠道商*/
            templateUrl:'/business/add/page',
            controller:'businessAddController'
        })
        .when('/agent',{     /*代理商管理*/
            templateUrl:'/agent/page',
            controller:'agentController'
        })
        .when('/agent/add',{     /*代理商添加*/
            templateUrl:'/agent/add/page',
            controller:'agentAddController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/agent/addAgent.js?v=1.0.0']);
                }]
            }
        })
        
        
        .when('/visit',{     /*用户登录记录*/
            templateUrl:'/pv/visit/page',
            controller:'visit-userLogController'
        })
        .when('/back/login',{     /*后台登录记录*/
            templateUrl:'/pv/back/login/page',
            controller:'backLogController'
        })
        .when('/back/visit',{     /*后台操作记录*/
            templateUrl:'/pv/back/visit/page',
            controller:'operaController'
        })
        .when('/user',{     /*后台用户管理*/
            templateUrl:'/user/page',
            controller:'customerManagerController'
        })
        
        
        
       /*
        * 商城管理    商品列表
        *  
        */
        /*商品列表*/
        .when('/commodity',{     /*商品列表*/
            templateUrl:'/commodity/goods/page',          /*   /drugs   跳转java页面           /page  查询       以id="customDiv2"的元素为模板        */
            controller:'shopGoodsController'   /*drugsOrderManageController   跳转jsp页面*/
            ,resolve: {
                deps:['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        '/static/js/shop/goodsListOf.js?v=1.0.0']);
                }]
            }
        })
        .when('/commodity/toAdd/:type/:goodsId',{
        	templateUrl:function($routeParams){
        		if($routeParams.goodsId == 'undefined'){
            		return '/commodity/goods/toAdd?type=' + $routeParams.type;
            	}
        		return '/commodity/goods/toAdd?type=' + $routeParams.type + '&goodsId=' + $routeParams.goodsId;
        	}
//        	,controller:'goodsEditController'
//    		,resolve: {
//                deps:['$ocLazyLoad', function($ocLazyLoad){
//                    return $ocLazyLoad.load([
//                        '/static/js/shop/goods-edit.js?v=1.0.0'
//                    	,'/static/js/shop/area_data.js']);
//                }]
//            }
        })
        /*
         * 商城管理  商品 分类
         */
        .when('/commodity/category/tree',{     
            templateUrl:'/commodity/classify/page',
            controller:'goodsClassifyController'
			,resolve: {
			    deps:['$ocLazyLoad', function($ocLazyLoad){
			        return $ocLazyLoad.load([
			            '/static/js/shop/category.js?v=1.0.0']);
	            }]
	        }
		})
        
        .when('/commodity/shop/list',{     /*商铺列表*/
            templateUrl:'/commodity/shop/page',
            controller:'shopController'
        	,resolve: {
			    deps:['$ocLazyLoad', function($ocLazyLoad){
			        return $ocLazyLoad.load([
			            '/static/js/shop/shop.js?v=1.0.0']);
	            }]
	        }
        })
        .when('/commodity/order/list',{     /*订单列表*/
            templateUrl:'/commodity/order/page',
            controller:'shopOrderController'
        	,resolve: {
			    deps:['$ocLazyLoad', function($ocLazyLoad){
			        return $ocLazyLoad.load([
			            '/static/js/shop/order.js?v=1.0.0']);
	            }]
	        }
        })
        .when('/commodity/recommend/category',{
            templateUrl:'/commodity/recommend/category',
            controller:'recommendController'
        	,resolve: {
			    deps:['$ocLazyLoad', function($ocLazyLoad){
			        return $ocLazyLoad.load([
			            '/static/js/shop/recommend.js?v=1.0.0']);
	            }]
	        }
        })
        .when('/commodity/recommend/advert',{
            templateUrl:'/commodity/recommend/advert',
            controller:'recommendController'
        	,resolve: {
			    deps:['$ocLazyLoad', function($ocLazyLoad){
			        return $ocLazyLoad.load([
			            '/static/js/shop/recommend.js?v=1.0.0']);
	            }]
	        }
        })
        
        
        .when('/account',{    /*账户安全*/
            templateUrl:'/account/security/page',
            controller: 'accountSecurityController'
        })
        .when('/role',{     /**角色管理*/
        templateUrl:'/role/page',
        controller:'characterController'
        })
        .when('/permission',{
            templateUrl:'/permission/page',
            controller:'adminPermissionController'
        })
        .when('/data/department',{     /*科室管理*/
            templateUrl:'/data/department/page',
            controller:'departmentManageController'
        })
        .when('/data/foodKind',{     /*食物种类*/
            templateUrl:'/data/foodKind/page',
            controller:'foodKindController'
        })
        .when('/data/food',{
            templateUrl:'/data/food/page',
            controller:'foodController'
        })
        .when('/data/sportKind',{        /*运动种类*/
            templateUrl:'/data/sportKind/page',
            controller:'sportKindController'
        })
        .when('/data/sport',{        /**运动管理*/
            templateUrl:'/data/sport/page',
            controller:'sportController'
        })
        .when('/data/measure-suggection',{       /**测量建议*/
            templateUrl:'/data/measure-suggection/page',
            controller:'suggestionController'
        })
        .when('/data/measure-reason',{       /**测量原因*/
            templateUrl:'/data/measure-reason/page',
            controller:'reasonController'
        })
        .when('/app-version',{      /**app版本控制*/
            templateUrl:'/app-version/page',
            controller:'appVersionController'
        })
        .when('/weixin', {
            templateUrl: '/weixin/index',
            controller: 'weixinController',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        '/static/js/systemmanage/weixin.js?v=1.0.0',
                        '/static/css/layuiedit/edit.css',
                        '/static/css/table/layui-table-edit.css?v=1.0.0',
                        '/static/css/table/layui-page-edit.css?v=1.0.0'
                    ]);
                }]
            }
        })
        /*.when('/permiss/:id',{     /!**角色管理*!/
            controller:'permissionController'
        })*/
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
