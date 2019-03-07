app.filter('orderStatus', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                value = '待付款';
                break;
            case 2:
                value = '付款失败';
                break;
            case 3:
                value = '有效';
                break;
            case 4:
                value = '已完成';
                break;
            case 5:
                value = '退款失效';
                break;
            case 6:
                value = "已取消";
                break;
            case 7:
                value = "退款中";
                break;
            case 8:
                value = "退款成功";
                break;
        }
        return value;
    }
})
app.filter('Combotype', function () {
    return function (value) {
    	if (!value) {
    		return "未选择";
    	}
        switch (parseInt(value)) {
            case 1:
                value = '妇婴照护';
                break;
            case 2:
                value = '健康管理';
                break;
            case 3:
                value = '慢病管理';
                break;
            case 4:
                value = '术后康复';
                break;
            case 5:
                value = '长期护理';
                break;
            case 6:
                value = '肿瘤管理';
                break;
        }
        return value;
    }
})

app.filter('orderProduct', function () {
    return function (Data) {
        switch (Data) {
            case 16384:
                Data = '血脂分析';
                break;
            case 8192:
                Data = '尿酸分析';
                break;
            case 4096:
                Data = '尿液分析';
                break;
            case 256:
                Data = '心电分析';
                break;
            case 64:
                Data = '血糖分析';
                break;
        }
        return Data;
    }
});

app.filter('payType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                value = "支付宝";
                break;
            case 2:
                value = "微信";
                break;
            case 3:
                value = "网银在线";
                break;
        }
        return value;
    }
})

app.filter('recommend', function ($sce) {
    return function (value) {
        if (value == undefined || value == '' || !value)
            return $sce.trustAsHtml("推荐");
        return $sce.trustAsHtml("<span style='color:#ff5168'>取消</span>");
    }
})
app.filter('orgStatus', function ($sce) {
    return function (value) {
        switch (parseInt(value)) {
            case 0:
                return $sce.trustAsHtml("停用");
            case 1:
                return $sce.trustAsHtml("<span style='color:#48c858'>恢复</span>");
        }
    }
})

app.filter('flowType', function () {
    return function (value) {
        return parseInt(value) == 1 ? "收款" : "退款"
    }
})

app.filter('serveType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "健康养生";
            case 2:
                return "慢病恢复";
            case 3:
                return "减肥塑体";
            case 4:
                return "居家养老";
            case 5:
                return "癌症康复";
        }
    }
})
app.filter('selectVal', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "未选择";
            case 2:
                return "推广渠道";
            case 4:
                return "会员销售渠道";
            case 8:
                return "分析服务渠道";
        }
    }
})
app.filter('sex', function () {
    return function (value) {
        if (value == undefined)
            return '';
        if (typeof value == 'number') {
            switch (value) {
                case 0:
                    return "女";
                case 1:
                    return "男";
                case 2:
                    return "不限";
            }
        }
        return value ? '男' : '女';
    }
})

app.filter('userType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "会员";
            case 2:
                return "机构员工";
        }
    }
})
app.filter('adminType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "超级管理员";
            case 2:
                return "管理员";
            case 3:
                return "待定";
            case 4:
                return "超级运维";
            case 5:
                return "小管理员";
        }
    }
})

app.filter('dbType', function () {
    return function (val) {
        switch (parseInt(val)) {
            case 0:
                return "添加";
            case 1:
                return "修改";
            case 2:
                return "删除";
        }
    }
})

app.filter('measureType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "早餐前";
            case 2:
                return "早餐后";
            case 3:
                return "午餐前";
            case 4:
                return "午餐后";
            case 5:
                return "晚餐前";
            case 6:
                return "晚餐后";
            case 7:
                return "凌晨";
        }
    }
})

app.filter('orgType',function () {
    return function (value) {
        switch (parseInt(value)) {
            case 0:
                return "管理机构";
            case 1:
                return "服务机构";
            case 2:
                return "个人机构";
        }
    }
})

app.filter('terminalStatus',function () {
    return function (value) {
        switch (parseInt(value)) {
            case 0:
                return "离线";
            case 1:
                return "在线";
        }
    }
})

app.filter('textLength',function () {
    return function (value) {
        if(value.length>21){
            value=value.substr(0,21)+"...";
        }
        return value;
    }
})
app.filter('reportUserType',function () {
    return function (value) {

        return parseInt(value)==0?"会员":"机构用户";
    }
})

app.filter('Type',function () {
    return function (value) {

        return parseInt(value)==0?"机构用户":"会员";
    }
})
app.filter('nullToEmpty',function () {
    return function (value) {
        var s=value.split(" ");
        var temp="";
        for (var index in s){
            var m=s[index];
            if(m=="null"||m==undefined||m==null){
                m="";
            }
            temp+=m+" ";
        }

        return temp;
    }
})

/**
 * 性别转换
 */
app.filter('gender',function () {
    return function (value) {
        console.log(value + '  ' + typeof value);
       if (typeof value == "undefined") {
           return '无限制';
       }
       return value == false ? '女' : '男';
    }
})

/**
 * 性别反转换
 */
app.filter('reverseGender',function () {
    return function (value) {
        if (value == '无限制') {
            return null;
        }
        return value == '男' ? true : false;
    }
})

/**
 * 剪切字符串
 */
app.filter('subStr',function () {
    return function (value, len) {
        if (value.length > len) {
            return value.substr(0, len) + '...';
        }
        return value;
    }
})

/**
 * 健康状态转换
 */
app.filter('healthStatus',function () {
    return function (value) {
        var msg = null;
        switch (value) {
            case 1 :
                msg = '低';
                break;
            case 2 :
                msg = '偏低';
                break;
            case 3 :
                msg = '正常';
                break;
            case 4 :
                msg = '偏高';
                break;
            case 5 :
                msg = '高';
                break;
            case '低' :
                msg = 1;
                break;
            case '偏低' :
                msg = 2;
                break;
            case '正常' :
                msg = 3;
                break;
            case '偏高' :
                msg = 4;
                break;
            case '高' :
                msg = 5;
                break;
            default :
                break;
        }
        return msg;
    }
})

/**
 * 健康状态转换2
 */
app.filter('healthStatus2',function () {
    return function (value) {
        var msg = null;
        switch (value) {
            case '1' :
                msg = '低';
                break;
            case '2' :
                msg = '偏低';
                break;
            case '3' :
                msg = '正常';
                break;
            case '4':
                msg = '偏高';
                break;
            case '5' :
                msg = '高';
                break;
            case '低' :
                msg = '1';
                break;
            case '偏低' :
                msg = '2';
                break;
            case '正常' :
                msg = '3';
                break;
            case '偏高' :
                msg = '4';
                break;
            case '高' :
                msg = '5';
                break;
            default :
                break;
        }
        return msg;
    }
})

/**
 * 转换短信-发送者类型
 */
app.filter('sendType',function () {
    return function (value) {
        var msg = null;
        switch (value) {
            case 0:
                msg = "平台";
                break;
            case 1:
                msg = "用户";
                break;
            case 2:
                msg = "机构";
                break;
            default:
                msg = "平台";
                break;
        }
        return msg;
    }
})


app.filter('comboStatus', function () {
    return function (value) {
        switch (value) {
            case 0:
                return '使用中';
            case 1:
                return '暂停服务';
            default:
                return '无';
        }
    }
})

app.filter('date', function () {
    return function (value, format) {
        if (value == null) {
            return '无';
        }
        return new Date(value).Format(format);
    }
})

app.filter('gender', function () {
    return function (value) {
        switch (value) {
            case 0:
                return '女';
            case 1:
                return '男';
            case false:
                return '女';
            case true:
                return '男';
            default:
                return '无';
        }
    }
})

app.filter('sleepTime', function () {
    return function (value) {
        if (value <= 60) {
            return value + ' 分';
        }
        var hour =  Math.floor(value / 60);
        var min = value % 60;
        return hour + ' 时' + (min == 0 ? '' : min + ' 分');
    }
})

app.filter('deviceName', function () {
    return function (value) {
        var msg = null;
        switch (value) {
            case 'xty':
                msg = '血糖仪';
                break;
            case 'xyj':
                msg = '血压计';
                break;
            case 'xyy':
                msg = '血氧仪';
                break;
            case 'fhy':
                msg = '肺活仪';
                break;
            case 'band':
                msg = '心率手环';
                break;
            case 'tzc':
                msg = '体脂秤';
                break;
            case 'twj':
                msg = '体温计';
                break;
            case 'xzy':
                msg = '血脂仪';
                break;
            case 'ny':
                msg = '尿液分析仪';
                break;
            case 'ns':
                msg = '尿酸分析仪';
                break;
            case 'ecg':
                msg = '心电仪';
                break;
            default:
                break
        }
        return msg;
    }
})

app.filter('vipCardStatus', function () {
    return function (value) {
        return value == 0 ? '未激活' : '已激活';
    }
})

app.filter('fenToYuan', function () {
    return function (value) {
        if (value == 0) {
            return '免费';
        }
        var price = (value/100).toFixed(0) + '元';
        return price;
    }
})

app.filter('comboUnit', function () {
    return function (value) {
        switch (value){
            case 30:
                return '月';
            case 60:
                return '2月';
            case 90:
                return '3月';
            case 180:
                return '半年';
            case 365:
                return '年';
        }
    }
})

app.filter('customerStatus',function () {
    return function (value) {
        switch (parseInt(value)) {
            case 0:
                return "禁用";
            case 1:
                return "正常";
        }
    }
})
app.filter('businessStatus',function () {
    return function (value) {
        switch (parseInt(value)) {
            case 0:
                return "正常";
            case 1:
                return "禁用";
        }
    }
})
app.filter('businessType',function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                return "推广渠道";
            case 2:
                return "会员销售渠道";
            case 4:
                return "分析服务渠道";
        }
    }
})

app.filter('sendType', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                value = "用户";
                break;
            case 2:
                value = "机构";
                break;
            case 0:
                value = "平台";
                break;
        }
        return value;
    }
})

app.filter('sendStatus', function () {
    return function (value) {
        switch (parseInt(value)) {
            case 1:
                value = "未发送";
                break;
            case 0:
                value = "已发送";
                break;
        }
        return value;
    }


})

app.filter('display',function(){
	return function(value){
		switch(parseInt(value)){
			case 0:
				value = "隐藏";
				break;
			case 1:
				value = "显示";
				break;
		}
		return value;
	}
})

app.filter('professional',function(){
	return function(value){
		switch(parseInt(value)){
			case 0:
				value = "否";
				break;
			case 1:
				value = "是";
				break;
		}
		return value;
	}
})

app.filter('statementStatus', function() {
    return function(value) {
        switch(parseInt(value)){
        case 1: value = "未确认"; break;
        case 2: value = "未转帐"; break;
        case 0: value = "结算完成"; break;
        }
        return value;
    }
});