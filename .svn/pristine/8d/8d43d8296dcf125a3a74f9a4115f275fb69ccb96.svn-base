/*var dd = {// 传入的数据格式
	data : [ {
		name : "车圃门店",
		hasNext : true,// true表示图片用文件夹的icon，否则显示用文件的icon
		downJgCount : 10,// 下级机构的数量
		//staffCount : 10,// 员工的数量
		memberCount : 10,// 会员的数量
		downStair : {
			name : "车圃门店",
			hasNext : true,// true表示图片用文件夹的icon，否则显示用文件的icon
			downJgCount : 10,// 下级机构的数量
			memberCount : 10,// 会员的数量
			downStair : {
				name : "车圃门店",
				hasNext : false,// true表示图片用文件夹的icon，否则显示用文件的icon
				downJgCount : 10,// 下级机构的数量
				memberCount : 10,// 会员的数量
			}
		}
	}, {
		name : "车圃门店",
		hasNext : true,// true表示图片用文件夹的icon，否则显示用文件的icon
		downJgCount : 10,// 下级机构的数量
		//staffCount : 10,// 员工的数量
		memberCount : 10,// 会员的数量
		downStair : {
			name : "车圃门店",
			hasNext : false,// true表示图片用文件夹的icon，否则显示用文件的icon
			downJgCount : 10,// 下级机构的数量
			memberCount : 10,// 会员的数量
		}
	}, {
		name : "车圃门店",
		hasNext : true,// true表示图片用文件夹的icon，否则显示用文件的icon
		downJgCount : 10,// 下级机构的数量
		memberCount : 10,// 会员的数量
	}, {
		name : "问宇航门店",
		hasNext : true,// true表示图片用文件夹的icon，否则显示用文件的icon
		downJgCount : 10,// 下级机构的数量
		memberCount : 10,// 会员的数量
	} ]
}*/

/* 树形机构显示类 */
var treeSet = {
	createCol : function() {
		var col = document.createElement("td");
		$(col).attr({
			"style" : "text-align:left",
			"colspan" : "6"
		});
		return col;
	},
	createUl : function() {
		var list = document.createElement("ul");
		$(list).css({
			"padding-left" : "35px",
			"line-height" : "30px"
		});
		return list;
	},/*
	createList : function(data, node) {
		var row = document.createElement("tr");
		var col = this.createCol();// 表格的tr
		var list = this.createUl();// 创建ul
		data = data['data'];
		for ( var key in data) {
			var li = this.createChildNode(data[key]);// 创建li节点，一个li节点代表一级
			$(li).appendTo($(list));
		}
		$(col).appendTo($(row));
		$(list).appendTo($(col));
		$(row).css("display", "none");
		$(row).insertAfter($("#" + node));
		$("#" + node).bind("click", function() {
			$(row).slideToggle(300);
			treeSet.getChildData(node);
		});
	},
	createChildNode : function(data) {
		var name = data['name'];// 菜单名字
		var hasNext = data['hasNext'];// 是否还有下一级
		var downJgCount = data['downJgCount'];// 下级机构数量
		//var staffCount = data['staffCount'];// 员工数量
		var memberCount = data['memberCount'];// 会员数量
		var downStair = data['downStair'];// 还能下拉的json
		var li = document.createElement("li");
		var line = document.createElement("img");
		$(line).css({
			"position" : "relative",
			"top" : "-7px"
		});
		line.src = "static/images/tree_line.png";
		li.appendChild(line);
		var btn = document.createElement("a");
		$(btn).css({
			"color" : "#000",
			"cursor" : "pointer"
		});
		var door = document.createElement("span");
		door.innerText = name;
		var icon = document.createElement("img");
		$(icon).css({
			"margin" : "0 6px"
		});
		icon.src = hasNext ? "static/images/folder.png"
				: "static/images/file.png";
		btn.appendChild(icon);
		btn.appendChild(door);
		li.appendChild(btn);
		var downJigou = document.createElement("span");
		var downMember = document.createElement("span");
		var look = document.createElement("a");
		$(downJigou).css({
			"margin-left" : "30px",
			"color" : "#6c6c6c"
		});
		
		$(downMember).css({
			"margin-left" : "30px",
			"color" : "#6c6c6c"
		});
		look.href="javascript:treeSet.lookJigou()";
		look.innerText="查看";
		$(look).css({"float":"right","margin-right":"60px"});
		$(downJigou).text("下级机构数（" + downJgCount + "）");
		$(downMember).text("会员数（" + memberCount + "）");
		li.appendChild(look);
		li.appendChild(downJigou);
		li.appendChild(downMember);
		if (downStair != undefined) {
			var list = this.createUl();
			$(list).css({
				"display" : "none"
			});
			var child = this.createChildNode(downStair);
			list.appendChild(child);
			li.appendChild(list);
			$(btn).bind("click", function() {
				$(list).slideToggle(300);
			});
		}
		return li;
	}, */lookJigou: function (id) {//机构信息对话框
        
        jQuery.ajax({
    		type: 'GET',
    		url: 'orgControl.do?getOrgDetail&orgId='+id,
    		beforSend: function() {
    			layer.load(2);
    		},
    		success: function(result) {
    			var data = result.obj;
    			// 创建一个dom
    			var $dialog = jQuery(".dialog_contain");
    			// logo判断
    			if(data.logo != null && data.logo != "") {
    				$dialog.find("div.user_head>img").attr("src", data.logo);
    			}
    			var $ul = $dialog.find("div.msg_contain>ul");
    			
    			var userName = typeof(data.userName) == 'undefined' ? '暂未填写' : data.userName;
    			var contact = typeof(data.contact) == 'undefined' ? '暂未填写' : data.contact;
    			var mobile = typeof(data.mobile) == 'undefined' ? '暂未填写' : data.mobile;
    			var orgName = typeof(data.orgName) == 'undefined' ? '暂未填写' : data.orgName;
    			var address = typeof(data.address) == 'undefined' ? '暂未填写' : data.address;
    			
    	        $ul.find("li").eq(0).text("用户名："+userName);
    	        $ul.find("li").eq(1).text("联系人："+contact);
    	        $ul.find("li").eq(2).text("联系号码："+mobile);
    	        $ul.find("li").eq(3).text("机构名称："+orgName);
    	        $ul.find("li").eq(4).text("地址："+address);
    			
    			layer.open({
    				type : 1,
    				title : [ '机构信息',
    						'text-align:center;font-size:16px;background:#fff;' ],
    				area : '550px',
    				moveType : 1,
    				offset:150,
    				scrollbar: false,
    				zIndex : 9999,
    				scrolling : 'no',
    				content : $dialog //这里content是一个DOM 	
    			});
    		},
    		complete: function() {
    			layer.closeAll("loading");
    		}
    	});
        
    }, /*createJigouDialog: function () {
    	
        var dialog = document.createElement("div");
        $(dialog).addClass("dialog_contain");
        var head = document.createElement("div");
        $(head).addClass("user_head");
        var img = document.createElement("img");
        img.src = "static/images/head.png";
        $(img).appendTo($(head));
        var msg = document.createElement("div");
        $(msg).addClass("msg_contain");
        var list = document.createElement("ul");
        $(list).html('<li>用户名：939132650</li><li>上级机构：总部</li> <li>联系人：许湛司</li> <li>联系号码：12345678901</li><li>机构名称：湛司机构</li><li>地址：广州市天河区通众集团</li>');
        var btnGroup = document.createElement("div");
        $(btnGroup).addClass("dialog_btn_group");
        var edit = document.createElement("button");
        $(edit).text("编辑");
        $(edit).addClass("edit");
        var details = document.createElement("button");
        $(details).addClass("details");
        $(details).text("详情");
        btnGroup.appendChild(edit);
        btnGroup.appendChild(details);
        $(list).appendTo($(msg));
        $(btnGroup).appendTo($(msg));
        dialog.appendChild(head);
        dialog.appendChild(msg);
        return dialog;
    }, */getChildData: function(id, mode) {
    	jQuery.ajax({
    		type: 'GET',
    		url: 'orgControl.do?getOrgChildData&orgId='+id,
    		beforeSend: function() {
    			layer.load(2);
    		},
    		success: function(result) {
    			var dd = result.obj;
    			if(dd.data.length > 0) {
    				switch(mode) {
    				case 0: treeSet.createChild(dd, id); break;
    				case 1: treeSet.createList(dd, id); break;
    				}
    			}
    		},
    		complete: function() {
    			// 测试3毫秒关闭加载标签
    			setTimeout(function() {
    				layer.closeAll("loading");
    			}, 300);
    		}
    	});
    }, createChild: function(data, node) {
    	/** 创建子节点用一个tr包括起来 */
		var row = document.createElement("tr");
		var col = this.createCol();// 表格的tr
		var list = this.createUl();// 创建ul
		data = data['data'];
		for ( var key in data) {
			var li = this.createNode(data[key]);
			$(li).appendTo($(list));
		}
		
		$(col).appendTo($(row));
		$(list).appendTo($(col));
		$(row).css("display", "none");
		$(row).insertAfter($("#" + node));
		$(row).slideToggle(300);

		$(row).addClass("child"+node);
		
    }, createList: function(data, node) {
    	var root = document.createElement("li");
    	/** 创建子节点用一个ul包括起来 */
		var list = this.createUl();// 创建ul
		data = data['data'];
		for ( var key in data) {
			var li = this.createNode(data[key]);
			$(li).appendTo($(list));
		}
		$(root).addClass('child'+node);
		$(list).appendTo($(root));
		$(root).insertAfter($("#" + node));
		
    }, createNode: function(data) {
    	/** 获得一个节点li */
    	var name = data['name'];	// 机构名称
//    	var hasNext = data['hasNext'];// 是否还有下一级
    	var downJgCount = data['downJgCount'];// 下级机构数量
    	var memberCount = data['memberCount'];// 会员数量
    	var type = data['type'];	// 机构类型
    	var ii = data['id'];	// id
    	
    	var li = document.createElement("li");
		var line = document.createElement("img");
		$(line).css({
			"position" : "relative",
			"top" : "-7px"
		});
		line.src = "static/images/tree_line.png";
		li.appendChild(line);
		var btn = document.createElement("a");
		$(btn).css({
			"color" : "#000",
			"cursor" : "pointer"
		});
		var door = document.createElement("span");
		door.innerText = name;
		var icon = document.createElement("img");
		$(icon).css({
			"margin" : "0 6px"
		});
		icon.src = type==0 ? "static/images/folder.png"
				: "static/images/file.png";
		btn.appendChild(icon);
		btn.appendChild(door);
		li.appendChild(btn);
		var downJigou = document.createElement("span");
		var downMember = document.createElement("span");
		var look = document.createElement("a");
		$(downJigou).css({
			"margin-left" : "30px",
			"color" : "#6c6c6c"
		});
		
		$(downMember).css({
			"margin-left" : "30px",
			"color" : "#6c6c6c"
		});
		look.href="javascript:treeSet.lookJigou("+ii+")";
		look.innerText="查看";
		$(look).css({"float":"right","margin-right":"60px"});
		$(downJigou).text("下级机构数（" + downJgCount + "）");
		$(downMember).text("会员数（" + memberCount + "）");
		li.appendChild(look);
		li.appendChild(downJigou);
		li.appendChild(downMember);
		
		$(li).attr('id', ii);
		
		if(downJgCount > 0) {
			$(li).find("a").eq(0).click(function() {
				var $next = jQuery(this).parent("li").next("li");
				if($next.hasClass("child"+ii)) {
					$next.slideUp(300);
					$next.remove();
				} else {
					treeSet.getChildData(ii, 1);
				}
			});
		}
		
		return li;
    }
}