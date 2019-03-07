/**
 * 公用方法调用
 * author: wenxian.cai
 * date: 2017/10/12 17:13
 */

var common = {};

/**退出登录*/
common.logout = function () {
    layer.confirm('是否退出登录', function () {
        var url = '/login/logout';
        http.ajax.post(true, false, url, null, http.ajax.CONTENT_TYPE_1, function () {
            window.location.href = '/login';
        })
    })
}

