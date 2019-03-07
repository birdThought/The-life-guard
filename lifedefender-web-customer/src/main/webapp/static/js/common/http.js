/**
* 网络请求
* @type {{}}
*/
var http = {};

/**Ajax*/
http.ajax = {};
http.ajax.CONTENT_TYPE_1 = 'application/x-www-form-urlencoded;charset=utf-8';
http.ajax.CONTENT_TYPE_2 = 'application/json;charset=utf-8';

/**GET请求*/
http.ajax.get = function (async, cache, url, data, contentType, callBack) {
    http.ajax.request(async, cache, url, data, contentType, callBack, 'GET');
}
/** GET请求2 */
http.ajax.get_no_loading = function (async, cache, url, data, contentType, callBack) {
    http.ajax.request_no_loading(async, cache, url, data, contentType, callBack, 'GET');
}

http.ajax.get_not_null = function(async, cache, url, data, contentType, callBack) {
    if (contentType == http.ajax.CONTENT_TYPE_1 && data != null) {
        var nwObj = new Object();
        for (var key in data) {
            var value = data[key];
            if (value != null) {
                nwObj[key] = value;
            }
        }
        data = nwObj;
    }
    if (contentType == http.ajax.CONTENT_TYPE_2 && data != null) {
        var nwObj = new Object();
        var obj = JSON.parse(data);
        for (var key in obj) {
            var value = obj[key];
            if (value != null) {
                nwObj[key] = value;
            }
        }
        data = JSON.stringify(nwObj);
    }
    http.ajax.request(async, cache, url, data, contentType, callBack, 'GET');
}

/**POST请求*/
http.ajax.post = function (async, cache, url, data, contentType, callBack) {
    http.ajax.request(async, cache, url, data, contentType, callBack, 'POST');
}
/**POST请求2*/
http.ajax.post_no_loading = function (async, cache, url, data, contentType, callBack) {
    http.ajax.request_no_loading(async, cache, url, data, contentType, callBack, 'POST');
};

/**PATCH请求*/
http.ajax.patch = function (async, cache, url, data, contentType, callBack) {
    http.ajax.request(async, cache, url, data, contentType, callBack, 'PATCH');
}

/** PUT请求 */
http.ajax.put = function(url, data, contentType, callBack) {
    // 默认异步 + 不缓存
    http.ajax.request(true, false, url, data, contentType, callBack, 'PUT');
}

/** DELETE请求 */
http.ajax.del = function(url, callBack) {
    // 默认异步 + 不缓存
    http.ajax.request(true, false, url, null, http.ajax.CONTENT_TYPE_1, callBack, 'DELETE');
}

/** ajax底层请求 */
http.ajax.request = function(async, cache, url, data, contentType, callBack, requestMethod) {
    $.ajax({
        async: async,
        cache: cache,
        type: requestMethod,
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforSend: function() {
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function() {
            layer.closeAll('loading');
        },
        success: function(result) {
            if (typeof callBack == 'function') {
                if (result.success) {
                    callBack(result);
                    return;
                }
                if (result.msg != null && result != '') {
                    layer.msg(result.msg);
                    return;
                }
                layer.msg('服务器异常');
            }
        }
    });
}
/** ajax底层请求_不显示加载动画 */
http.ajax.request_no_loading = function(async, cache, url, data, contentType, callBack, requestMethod) {
    $.ajax({
        async : async,
        cache : cache,
        type: requestMethod,
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        success: function (result) {
            if (typeof callBack == 'function') {
                if (result.success) {
                    callBack(result);
                    return;
                }
            }
        }
    });
}