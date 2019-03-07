var checkUtils = {
	checkPhone : function(phone) {// 手机号码检测
		var length = phone.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var tel = /^([0|4|8][0-9]{2,3}-)?([2-9][0-9]{6,7})$/;
		
        var isTel = tel.test(phone);
        
        if (isTel) {
        	return true;
        }
        
        if (phone.length == 11) {
        	var isMobile = mobile.test(phone);
        	if (isMobile) {
        		return true;
        	}
        }
        
        return false;
        
//		if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
//			return false;
//		}
//		return true;
	},
	checkLegalUserName : function(str) {// 用户名检测
		if (!/^[a-zA-Z0-9]{6,16}$/.test(str)) {
			return false;
		}
		return true;
	},
	checkLegalPsw : function(str) {// 检查是否合法的密码
		if (!/[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,16}$/.test(str)) {
			return false;
		}
		return true;
	},
	checkEmail : function(mail) {// 检查是否合法的email
		if (!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
				.test(mail))
			return false;
		return true;
	},
	checkMoney:function(money){
		var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
		if(exp.test(money))
			return true;
		return false;
	},
	checkOrgName: function(orgName) {
		var orgNameRegex = /^([a-zA-Z0-9]|[\u4e00-\u9fa5]){1,20}$/;
		if (orgNameRegex.test(orgName)) {
			return true;
		}
		return false;
	}
}