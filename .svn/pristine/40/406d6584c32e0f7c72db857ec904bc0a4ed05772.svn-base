layui.define(['element','layer','upload'], function(exports) {
    var MOD_NAME = "fx",
        o = layui.jquery,
        upload = layui.upload,
        urls = {
            one_img : '/upload/image.html'
        },
        fx = function() {};

    // ajax异步请求
    fx.prototype.load = function(url, callback) {
        Pace.restart();
        o.ajax({
            type: 'GET',
            url: url,
            data: {},
            statusCode: {
                404: function() {
                    Pace.stop();
                    layer.msg('E400：紧急开发中，请耐心等待...',{icon: 5});
                },
                500: function() {
                    layer.msg('E500：页面加载失败，请稍后再试！',{icon: 5});
                    Pace.stop();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                Pace.stop();
            },
            success: function(res) {
                res.code == 0 && layer.msg(res.msg,{icon: 5},function(){res.url && (location.href = res.url)});
                callback(res);
            }
        });
    }

    fx.prototype.reload = function(url){
        location.href = location.origin + '#' + url.split(location.host)[1];
    }

    fx.prototype._get = function(name){
        var url = location.href,
            reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        if(url.indexOf('?') > -1){
            var r = url.split('?')[1].match(reg);
            return r != null ? unescape(r[2]) : '';
        }
        return '';
    }

    fx.prototype.result = function(res){
        if(res.code == 1){
            var t = this;
            layer.msg(res.msg ? res.msg : '数据处理成功，正在跳转！',{icon: 6,time: 1000},function(){
                layer.closeAll();
                res.url ? o.get(res.url,function(){location.reload()}) : location.reload();
            });
        }
        if(res.code == 0){
            layer.msg(res.msg ? res.msg : '数据处理失败，请稍后再试！',{icon: 5});
        }
    }

    fx.prototype.error = function(){

    }

    fx.prototype.upload = function(elem){
        upload.render({
            elem : elem,
            url : urls.one_img,
            done: function(res, index, upload){
                if(res.code == 1){
                    layer.msg(res.msg);
                    return false;
                }
                o(elem).prev('input').val(res.data.src);
            }
        })
    }

    fx.prototype.set_cookie = function(name, value, days) {
        var exp = new Date();
        exp.setTime(exp.getTime() + (days ? days : 30) * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString()
    };

    fx.prototype.get_cookie = function(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if(arr = document.cookie.match(reg)) {
            return unescape(arr[2])
        } else {
            return null
        }
    };

    fx.prototype.cache = function(name,value){
        var param = this.get_cookie('sys_frame_param') || '{}',obj = JSON.parse(param);
        if(value === undefined){
            return param == '{}' || !obj[name] ? 0 : obj[name];
        }
        obj[name] = value;
        this.set_cookie('sys_frame_param',JSON.stringify(obj),1);
    }

    exports(MOD_NAME, new fx());
});