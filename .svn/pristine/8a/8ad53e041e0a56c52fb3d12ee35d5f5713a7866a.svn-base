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
http.ajax.get = function (async, cache, url, data, contentType, callback) {
    $.ajax({
        async : async,
        cache : cache,
        type: 'GET',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforeSend:function(){
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
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

};

http.ajax.get_no_loading = function (async, cache, url, data, contentType, callback) {
    $.ajax({
        async : async,
        cache : cache,
        type: 'GET',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
            }
        }
    });

};

/**POST请求*/
http.ajax.post = function (async, cache, url, data, contentType, callback) {

    $.ajax({
        async : async,
        cache : cache,
        type: 'POST',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforeSend:function(){
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
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
/**POST请求2*/
http.ajax.post_no_loading = function (async, cache, url, data, contentType, callback) {

    $.ajax({
        async : async,
        cache : cache,
        type: 'POST',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
                    return;
                }
            }
        }
    });

};


/**PATCH请求*/
http.ajax.patch = function (async, cache, url, data, contentType, callback) {
    $.ajax({
        async : async,
        cache : cache,
        type: 'PATCH',
        url: url,
        data: data,
        contentType: contentType,
        dataType: 'json',
        beforeSend:function(){
            layer.load(2, {offset: ['55%', '50%']});
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (result) {
            if (typeof callback == 'function') {
                if (result.success) {
                    callback(result);
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
