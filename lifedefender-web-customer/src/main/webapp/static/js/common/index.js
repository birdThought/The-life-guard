(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // CommonJS
        factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    var pluses = /\+/g;
    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch(e) {}
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (value !== undefined && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setTime(+t + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path    ? '; path=' + options.path : '',
                options.domain  ? '; domain=' + options.domain : '',
                options.secure  ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {};

        // To prevent the for loop in the first place assign an empty array
        // in case there are no cookies at all. Also prevents odd result when
        // calling $.cookie().
        var cookies = document.cookie ? document.cookie.split('; ') : [];

        for (var i = 0, l = cookies.length; i < l; i++) {
            var parts = cookies[i].split('=');
            var name = decode(parts.shift());
            var cookie = parts.join('=');

            if (key && key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        if ($.cookie(key) === undefined) {
            return false;
        }

        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, { expires: -1 }));
        return !$.cookie(key);
    };

}));

var indexJs = {};
indexJs.rotateIcon = function (elem) {
    elem.click(function() {
     if(elem.find($('.layui-nav-more')).css('top')=='32px'){
         elem.find($('.layui-nav-more')).css({'top':'27px!important'})
     }else if(elem.find($('.layui-nav-more')).css('top')=='27px') {
         elem.find($('.layui-nav-more')).css({'top':'32px!important'})
     }
    });
};
indexJs.changeColor=function(elem1){
    elem1.click(function(){
        elem1.find($('.layui-nav-item-first')).css({
            'color':'#3cbaff'
        })
    })
};
indexJs.littleRound=function(elem){
    var line = $('<u style="width: 4px;height: 60px;background: red;position: absolute;left: 0"></u>');
    var round = $('<i class="round" style="position: absolute;width: 8px;height: 8px;background: #fff;-webkit-border-radius: 50%;-moz-border-radius: 50%;border-radius: 50%;top: 17px;left: 26px;"></i>')
    var elem = elem;
    elem.prepend(round);
    elem.hover(function(){
        $(this).find('i').css({'background':'deepskyblue'})
    },function(){
        $(this).find('i').css({'background':'#fff'})
    })
};
indexJs.addLine=function(elem1,elem2){
    var elem1 = elem1;
    var elem2 = elem2;

    var flag;
    elem1.hover(function(){
        $(this).prepend(elem2);
    },function(){
        $(this).find(elem2).remove();
    });
};
indexJs.getHref=function(){
    var Hash = window.location.hash;
    $.cookie('CurrentHash',Hash);
    Hash=$.cookie('CurrentHash')
};

indexJs.rotateIcon($('.layui-nav-item-first'));
indexJs.changeColor($('.deal-methods'));
indexJs.littleRound($('.layui-nav-child dd'));
