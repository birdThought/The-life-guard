/*Cookie的工具类*/
var Cookie = {
    add : function(key, value, hours) {
        value = encodeURI(value);
        if(hours==undefined)
            hours=999;
        var dismissTime = new Date().getTime();
        dismissTime += hours * 1000 * 60;
        var cookieStr = key + "=" + escape(value) + ";expires="
            + new Date(dismissTime).toGMTString();
        document.cookie = cookieStr;
    },
    get : function(key) {
        var arrStr = document.cookie.split(";");
        for ( var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == key)
                return decodeURI(temp[1]);
        }
        return null;
    },
    remove : function(key) {
        this.add(key, '', 0);
    },
    clearAll : function() {
        var arrStr = document.cookie.split(";");
        for ( var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            this.add(temp[0], '', 0);
        }
    }
}