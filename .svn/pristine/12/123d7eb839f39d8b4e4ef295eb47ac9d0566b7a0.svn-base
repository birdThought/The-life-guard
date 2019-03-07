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