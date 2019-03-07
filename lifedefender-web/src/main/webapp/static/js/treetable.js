layui.define(['layer', 'table'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;
    var eleParam='';
    var dataArr=[]
    var addOrgID=''
    var Code=''
    var arr=[]
    var empData=[]
    var treetable = {
        // 渲染树形表格
        render: function (param) {
            // 检查参数
            if (!treetable.checkParam(param)) {
                return;
            }
            // 获取数据
            if (param.data) {
                treetable.init(param, param.data);
            } else {
             $.getJSON(param.url, param.where, function (res) {
             	dataArr=res.obj.data
		        treetable.init(param, dataArr);
             });

                
            }
        },
        // 渲染表格
        init: function (param, data) {
            var mData = [];
            var doneCallback = param.done;
            var tNodes = data;
            // 补上id和pid字段
            for (var i = 0; i < tNodes.length; i++) {
                var tt = tNodes[i];
                if (!tt.id) {
                    if (!param.treeIdName) {
                        layer.msg('参数treeIdName不能为空', {icon: 5});
                        return;
                    }
                    tt.id = tt[param.treeIdName];
                }
                if (!tt.pid) {
                    if (!param.treePidName) {
                        layer.msg('参数treePidName不能为空', {icon: 5});
                        return;
                    }
                    tt.pid = tt[param.treePidName];
                }
            }
			
            // 对数据进行排序
            var sort = function (s_pid, data) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].pid == s_pid) {
                        var len = mData.length;
                        if (len > 0 && mData[len - 1].id == s_pid) {
                            mData[len - 1].isParent = true;
                        }
                        mData.push(data[i]);
                        sort(data[i].id, data);
                    }
                }
                
            };
            sort(param.treeSpid, tNodes);
            // 重写参数
            param.url = undefined;
            param.data = mData;
            param.page = {
                count: param.data.length,
                limit: param.data.length
            };
            param.cols[0][param.treeColIndex].templet = function (d) {
                var mId = d.id;
                var mPid = d.pid;
                var isDir = d.isParent;
                var emptyNum = treetable.getEmptyNum(mPid, mData);
                var iconHtml = '';
                for (var i = 0; i < emptyNum; i++) {
                    iconHtml += '<span class="treeTable-empty"></span>';
                }
                if (isDir) {
                    iconHtml += '<i class="layui-icon layui-icon-triangle-d"></i> <i class="layui-icon layui-icon-layer"></i>';
                } else {
                    iconHtml += '<i class="layui-icon layui-icon-file"></i>';
                }
                iconHtml += '&nbsp;&nbsp;';
                var ttype = isDir ? 'dir' : 'file';
                var vg = '<span class="treeTable-icon open" lay-tid="' + mId + '" lay-tpid="' + mPid + '" lay-ttype="' + ttype + '">';
                return vg + iconHtml + d[param.cols[0][param.treeColIndex].field] + '</span>'
            };
			
            param.done = function (res, curr, count) {
                $(param.elem).next().addClass('treeTable');
                $('.treeTable .layui-table-page').css('display', 'none');
                $(param.elem).next().attr('treeLinkage', param.treeLinkage);
                // 绑定事件换成对body绑定
                /*$('.treeTable .treeTable-icon').click(function () {
                    treetable.toggleRows($(this), param.treeLinkage);
                });*/
                if (param.treeDefaultClose) {
                    treetable.foldAll(param.elem);
                }
                if (doneCallback) {
                    doneCallback(res, curr, count);
                }
            };

            // 渲染表格
            table.render(param);
            eleParam=param
        },
        // 计算缩进的数量
        getEmptyNum: function (pid, data) {
            var num = 0;
            if (!pid) {
                return num;
            }
            var tPid;
            for (var i = 0; i < data.length; i++) {
                if (pid == data[i].id) {
                    num += 1;
                    tPid = data[i].pid;
                    break;
                }
            }
            return num + treetable.getEmptyNum(tPid, data);
        },
        // 展开/折叠行
        toggleRows: function ($dom, linkage) {
            var type = $dom.attr('lay-ttype');
            if ('file' == type) {
                return;
            }
            var mId = $dom.attr('lay-tid');
            var isOpen = $dom.hasClass('open');
            if (isOpen) {
                $dom.removeClass('open');
            } else {
                $dom.addClass('open');
            }
            $dom.closest('tbody').find('tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var pid = $ti.attr('lay-tpid');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if (mId == pid) {
                    if (isOpen) {
                        $(this).hide();
                        if ('dir' == ttype && tOpen == isOpen) {
                            $ti.trigger('click');
                        }
                    } else {
                        $(this).show();
                        if (linkage && 'dir' == ttype && tOpen == isOpen) {
                            $ti.trigger('click');
                        }
                    }
                }
            });
        },
        // 检查参数
        checkParam: function (param) {
            if (!param.treeSpid && param.treeSpid != 0) {
                layer.msg('参数treeSpid不能为空', {icon: 5});
                return false;
            }

            if (!param.treeColIndex && param.treeColIndex != 0) {
                layer.msg('参数treeColIndex不能为空', {icon: 5});
                return false;
            }
            return true;
        },
//         展开所有
		expandAll: function (dom) {
            $(dom).next('.treeTable').find('.layui-table-body tbody tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if ('dir' == ttype && !tOpen) {
                    $ti.trigger('click');
                }
            });
        },
        // 折叠所有
        foldAll: function (dom) {
            $(dom).next('.treeTable').find('.layui-table-body tbody tr').each(function () {
                var $ti = $(this).find('.treeTable-icon');
                var ttype = $ti.attr('lay-ttype');
                var tOpen = $ti.hasClass('open');
                if ('dir' == ttype && tOpen) {
                    $ti.trigger('click');
                }
            });
        }
    };

    layui.link(layui.cache.base + 'treetable-lay/treetable.css');
    //手机号码正则
    $('body').on('blur','.mobile',function(){
	    if(!(/^1[34578]\d{9}$/.test($('.mobile').val()))){ 
	        alert('手机号码格式不正确')  
	        $('.mobile').val('')
	        return false; 
	    } 
    })
    //获取验证码
    $('body').on('click','.codeBtn',function(){
    	console.log($('.mobile').val())
    	if(!$('.mobile').val()){
			alert("手机号码不能为空")
		}else{
	    	$.getJSON("http://192.168.0.104/org/manageOrg/sendValidCode", {
	        	"mobile": $('.mobile').val()
	        }, function (res) {
	        	console.log(res)
	        	Code=res.msg
	        })
    	}
    })

		//添加下级门店
		$('body').on('click', '.add', function () {
			addOrgID=$(this).parent().parent().parent().children().eq(1)[0].innerText.trim()
			$('.addSub').show()
			$('.empInfo').hide()
			$('.addOrgId').val(addOrgID)
		})
		$('body').on('click', '.cancel', function () {
			$('.addSub').hide()
		})
		$('body').on('click', '.firm', function () {
			if(Code!=$('.code').val()){
				alert('验证码错误')
			}else{
				$.getJSON("http://192.168.0.104/org/manageOrg/addLowerOrgOfSMS", {
					" orgId": $('.addOrgId').val(),
					" mobile":$('.mobile').val(),
					" verifyCode": "1216"
		        }, function (res) {
		        	console.log(res.msg)
		        })
			}
			
		})
//		查看员工信息
		
		$('body').on('click', '.emp', function () {
//			console.log($(this).parent().parent().parent().children().eq(1)[0].innerText.trim())
			addOrgID=$(this).parent().parent().parent().children().eq(1)[0].innerText.trim()
			$('.empInfo').show()
			$('.addSub').hide()
			$.getJSON("http://192.168.0.104/org/manageOrg/list/findOrgUserByOrgId", {
        		"orgId":addOrgID
	        }, function (res) {
	        	console.log(res)
	        	empinfo(res.obj)
	        })
			
		})
		//关闭员工详细
		$('body').on('click', '.closeEmpInfo', function () {
			$('.empInfo').hide()
			$('.addSub').hide()
		})
    // 给图标列绑定事件
    $('body').on('click', '.treeTable .treeTable-icon', function () {
        
        $.getJSON("http://192.168.0.104/org/manageOrg/list/findOrgListByOrgId", {
        	"orgId": $(this)[0].innerText.trim()
        }, function (res) {
        	console.log(res)
        	var flag=0
    			for(var i=0;i<res.obj.length;i++){
    				for(var j=0;j<dataArr.length;j++){
    					if(dataArr[j].id==res.obj[i].id){
	    					flag++
	    				}
    				}
    			}
    			if(flag==0){
    				for(var k=0;k<res.obj.length;k++){
    					dataArr.push(res.obj[k])
	    			}
    				treetable.init(eleParam, dataArr);
    			}
    		                
            });
             var treeLinkage = $(this).parents('.treeTable').attr('treeLinkage');
	        if ('true' == treeLinkage) {
	            treetable.toggleRows($(this), true);
	        } else {
	            treetable.toggleRows($(this), false);
	        }
    });
    

    exports('treetable', treetable);
});
