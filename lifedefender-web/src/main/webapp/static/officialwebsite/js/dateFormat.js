
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt){	//author: meizz 
	var o = {
    "M+": this.getMonth() + 1, //月份 
    "d+": this.getDate(), //日 
    "h+": this.getHours(), //小时 
    "m+": this.getMinutes(), //分 
    "s+": this.getSeconds(), //秒 
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    "S": this.getMilliseconds() //毫秒 
};
if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
for (var k in o)
if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
return fmt;
};

Date.prototype.isAfter = function (date) {
	return ((new Date(this.toString().replace(/-/g,"\/"))) > (new Date(date.toString().replace(/-/g,"\/"))));
};


var DateUtils = {
    getInterval : function(_date) {// 固定日期和当前日期的比较计算时差天数，例如：getInterval("2016-09-23")
        var date1 = new Date().getTime(), date2 = new Date(_date).getTime();
        return Math.ceil((date1 - date2) / (24 * 3600 * 1000));
    },
    parserDate:function(date){//把字符串日期格式转成Date对象
        var t = Date.parse(date);
        if (!isNaN(t)) {
            return new Date(Date.parse(date.replace(/-/g, "/")));
        } else {
            return new Date();
        }
    },formatDate:function(date){//把Date对象转成 2016-09-23格式
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    },printDateByRemain:function (remain) {//输出距离当前时间多少天后的日期
        var date2 = new Date();
        date2.setDate(date2.getDate()+remain);
        var times = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
        return times;
    }
}

