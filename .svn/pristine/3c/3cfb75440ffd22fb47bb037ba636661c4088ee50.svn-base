/*
 * 门店数据统计js
 */

/*数据统计类*/
var dataCountObject = {
		dateType: "WEEK",
		url: "/orgControl.do?getTradeData",
		old_buttonId: "week",
		old_buttonId_traffic: "yestoday",
		result:"",
		getDataCount: function() {
			$.ajax({
				type : 'POST',
				contentType : 'application/json; charset=utf-8',
				url : dataCountObject.url,
				data : '{"dateType":"'+dataCountObject.dateType+'"}',
				success : function(result) {
					dataCountObject.result = result;
					dataCountObject.showDate(result);
				}
			});				
			
		},
//		填充全部数据
		showDate: function(result) {
			$('.info').find('h1').text(result.orgMap.org.orgName);
			$('.admin').text(result.orgMap.userType);
			$('.msg').eq(0).find('h2').text(result.tradeMap.tradeCount);
			$('.msg').eq(1).find('h2').text(result.tradeMap.charge);
			$('.count').text(result.traffic);
			$('.msg_data_list').eq(0).find('span').eq(0).text(result.memberMap.TodayCount);
			$('.msg_data_list').eq(0).find('span').eq(1).text(result.memberMap.YestodayCount);
			$('.msg_data_list').eq(0).find('span').eq(2).text(result.memberMap.MonthCount);
			$('.msg_data_list').eq(0).find('span').eq(3).text(result.memberMap.AllCount);
		},
//		单独填充交易数据
		showTradeDate: function(result) {
			$('.msg').eq(0).find('h2').text(result.tradeMap.tradeCount);
			$('.msg').eq(1).find('h2').text(result.tradeMap.charge);
		},
//		单独填充流量数据
		showTrafficDate: function(result) {
			$('.count').text(result.traffic);
		},
//		选择交易时间类型后触发事件
		selectTradeTime: function(obj) {
			var buttonId=$(obj).attr('id');
			switch (buttonId) {
			case "week":
				dataCountObject.showTradeDate(dataCountObject.result);
				break;
			case "month":
				$.ajax({
					type : 'POST',
					contentType : 'application/json; charset=utf-8',
					url : dataCountObject.url,
					data : '{"dateType":"MONTH"}',
					success : function(result) {
						dataCountObject.showTradeDate(result);
					}
				});
				break;
			default:
				break;
			}
//			样式设置
			
			if(this.old_buttonId != buttonId){
			$("#"+buttonId).addClass("action");
			$("#"+this.old_buttonId).removeClass("action");
			this.old_buttonId = buttonId;
			}
		},
//		选择流量数据时间类型后出发事件
		selectTrafficTime: function(obj) {
			var buttonId=$(obj).attr('id');
			if(this.old_buttonId_traffic != buttonId){
			$("#"+buttonId).addClass("action");
			switch (buttonId) {
			case "yestoday":
				dataCountObject.showTrafficDate(dataCountObject.result);
				break;
			case "servenday":
				$.ajax({
					type : 'POST',
					contentType : 'application/json; charset=utf-8',
					url : dataCountObject.url,
					data : '{"dateType":"SERVENDAY"}',
					success : function(result) {
						dataCountObject.showTrafficDate(result);
					}
				});
				break;
			default:
				break;
			}
//			设置样式
			$("#"+this.old_buttonId_traffic).removeClass("action");
			this.old_buttonId_traffic = buttonId;
			}
		}
		
}

$(function() {
	var dataCount = Object.create(dataCountObject);
	dataCount.getDataCount();
});