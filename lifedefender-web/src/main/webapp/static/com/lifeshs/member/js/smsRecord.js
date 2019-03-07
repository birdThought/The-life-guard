/*
 * 短信记录
 * （已舍弃）
 */

$(function(){
	//初始化短息记录类
	smsRecord.smsRecordInitial();
	//初始化分页控件
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
});

/*
 * 短信记录类
 */
var smsRecord = {
	/*初始化*/
	smsRecordInitial: function(){
		smsRecord.getSmsRecordList(1, 8, function(totalpage, totalSize){
			if(totalSize > 0){
			//调用分页
		    $(".splitPage").createPage({
		        pageCount:totalpage,
		        current:1,
		        backFn:function(p){
		            smsRecord.getSmsRecordList(p, 8);
		        }
		    });
			}else{
				$('.errorMessage').css('display', 'block');
			}
		});
	},
	/*获取短信记录(分页)*/
	getSmsRecordList: function(nowPage, pageSize,callback){
		$.ajax({
			async : true,
			cache : false,
			type: 'POST',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			url: 'memberControl.do?getSmsRecordList',
			data: {nowPage: nowPage, pageSize: pageSize},
			dataType: 'json',
			success: function(result){				
				if(result.success){	
					$('.smsRecordMem tbody').empty();
					
					for(var i = 0; i < result.obj.length; i ++){
						var status = result.obj[i].status == '0' ? '已发送' : '未发送';
						var html = '<tr><td>'+ result.obj[i].userName +'</td><td>'+ result.obj[i].receiveMobile 
						+'</td><td style = "width: 40%; line-height:20px;"><span>'+ result.obj[i].content +'</span></td><td>'+ status
						+'</td><td>'+ new Date(result.obj[i].createDate).Format('yyyy-MM-dd') +'</td></tr>';
						$('.smsRecordMem tbody').append(html);
					}
					if(typeof(callback) == "function"){	//返回总页数
						callback(result.attributes.totalPage, result.attributes.totalSize);
					}
				}
			}
		});
	},
	
}

/*
 * 分页类
 */
var ms = {
	init:function(obj,args){
		return (function(){
			ms.fillHtml(obj,args);
			ms.bindEvent(obj,args);
		})();
	},
	//填充html
	fillHtml:function(obj,args){
		return (function(){
			obj.empty();
			//上一页
			if(args.current > 1){
				obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
			}else{
				obj.remove('.prevPage');
				obj.append('<span class="disabled">上一页</span>');
			}
			//中间页码
			if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
				obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
			}
			if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
				obj.append('<span>...</span>');
			}
			var start = args.current -2,end = args.current+2;
			if((start > 1 && args.current < 4)||args.current == 1){
				end++;
			}
			if(args.current > args.pageCount-4 && args.current >= args.pageCount){
				start--;
			}
			for (;start <= end; start++) {
				if(start <= args.pageCount && start >= 1){
					if(start != args.current){
						obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
					}else{
						obj.append('<span class="current">'+ start +'</span>');
					}
				}
			}
			if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
				obj.append('<span>...</span>');
			}
			if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
				obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
			}
			//下一页
			if(args.current < args.pageCount){
				obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
			}else{
				obj.remove('.nextPage');
				obj.append('<span class="disabled">下一页</span>');
			}
		})();
	},
	//绑定事件
	bindEvent:function(obj,args){
		return (function(){
			obj.on("click","a.tcdNumber",function(){
				var current = parseInt($(this).text());
				ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
				if(typeof(args.backFn)=="function"){
					args.backFn(current);
				}
			});
			//上一页
			obj.on("click","a.prevPage",function(){
				var current = parseInt(obj.children("span.current").text());
				ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
				if(typeof(args.backFn)=="function"){
					args.backFn(current-1);
				}
			});
			//下一页
			obj.on("click","a.nextPage",function(){
				var current = parseInt(obj.children("span.current").text());
				ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
				if(typeof(args.backFn)=="function"){
					args.backFn(current+1);
					
				}
			});
		})();
	}
}

	