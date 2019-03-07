define(function (require, exports, module) {
    require('QYcommon');
    // require('pageUtils');
    /*require('strophe');
     require('websdk2');
     require('websdk');
     require('webim');*/
    require('chat');
    require.async(['icheck', 'customRadio', 'commonCheck', 'jedate', 'dateFormat', 'qrcode'], function () {
        jeDate({
            dateCell: "#out_of_date",
            format: "YYYY-MM-DD",
            minDate: "1900-01-01",
            maxDate: "2099-01-01",
            startMin: "1900-01-01",
            startMax: "2099-01-01",
            zindex: 999,
            choosefun: function (elem, val) {
            }
        });
    });
});

var serveTarget = null;
function initUserControl(serve) {
    serveTarget = serve;
    var time = null;
    $(".conversation").hover(function () {
        $(this).attr("src", "static/images/conversation_h.png");
        var obj = $(this);
        var tip = new TipControl();
        time = setTimeout(function () {
            tip.showTips("健康会话", obj);
        }, 1000);
    }, function () {
        $(this).attr("src", "static/images/conversation.png");
        clearTimeout(time);
    });
    $(".person_info").hover(function () {
        $(this).attr("src", "static/images/person_info_h.png");
        var tip = new TipControl();
        var obj = $(this);
        var tip = new TipControl();
        time = setTimeout(function () {
            tip.showTips("个人信息", obj);
        }, 1000);
    }, function () {
        $(this).attr("src", "static/images/person_info.png");
        clearTimeout(time);
    });
    $(".health_data").hover(function () {
        $(this).attr("src", "static/images/health_data_h.png");
        var tip = new TipControl();
        var obj = $(this);
        var tip = new TipControl();
        time = setTimeout(function () {
            tip.showTips("健康数据", obj);
        }, 1000);
    }, function () {
        $(this).attr("src", "static/images/health_data.png");
        clearTimeout(time);
    });
    $(".relation").hover(function () {
        $(this).attr("src", "static/images/relation_h.png");
        var tip = new TipControl();
        var obj = $(this);
        var tip = new TipControl();
        time = setTimeout(function () {
            tip.showTips("移动群组", obj);
        }, 1000);
    }, function () {
        $(this).attr("src", "static/images/relation.png");
        clearTimeout(time);
    });
}
/*小tip显示功能*/
var TipControl = function () {
    var createTips = function (text) {
        var tip = document.createElement("div");
        $(tip).addClass("tip-contain");
        $(tip).text(text);
        return tip;
    }
    this.showTips = function (text, obj) {
        var control = createTips(text);
        obj = $(obj).get(0);
        control.style.left = obj.getBoundingClientRect().left - 30 + "px";
        control.style.top = obj.getBoundingClientRect().top
            + -(obj.offsetHeight + 25) + $(document).scrollTop() + "px";
        document.body.appendChild(control);

        $(control).fadeIn(300);
        setTimeout(function () {
            $(control).fadeOut(300, function () {
                $(control).remove();
            });
        }, 2000);
    }
}

function showBlock(target) {
    switch (target) {
        case 0:// 群组管理
            $("#group_control").addClass("action");
            $("#service_history").removeClass("action");
            $("#service_history_block").fadeOut(200, function () {
                $("#group_control_block").fadeIn(200);
            });
            break;
        case 1:// 服务历史记录
            $("#service_history").addClass("action");
            $("#group_control").removeClass("action");
            $("#group_control_block").fadeOut(200, function () {
                $("#service_history_block").fadeIn(200);
            });
            break;
    }
}

var HistoryRecord = {
    filterCache: null,
    filterHistory: function () {
        var groupId = $("#serverGroup_choose option:selected").val();
        var status = $("#status option:selected").val();
        var endDate = $("#out_of_date").val();
        var condition = {};
        if (groupId != '')
            condition.serveGroupId = groupId;
        if (endDate != '')
            condition.endDate = endDate;
        if (status != '') {
            condition.status = parseInt(status);
            /*switch (parseInt(status)) {
             case 0:
             condition.normal = 0;
             break;
             case 1:
             condition.outDate = 0;
             break;
             }*/
        }
        $("#historyPage").remove();
        $.isEmptyObject(condition) ? this.filterCache = null
            : this.filterCache = condition;
        this.getDatas(1, true);
    },
    getDatas: function (page, isFilter) {
        var json = {
            serverId: serveTarget,
            page: page
        };
        if (this.filterCache != null)
            json.condition = this.filterCache;
        ajaxSend(
            json,
            'orgServeControl.do?getServiceHistoryRecord',
            true,
            function (result) {
                var data = result.attributes.data;
                HistoryRecord.createDataNodes(data);
                if (isFilter != undefined && isFilter) {
                    var pageCount = result.attributes.count;
                    if (pageCount != 0) {
                        pageCount = pageCount % 15 == 0 ? pageCount / 15
                            : Math.floor(pageCount / 15) + 1;
                        var pageDiv = document.createElement("div");
                        $(pageDiv).addClass("page_Container");
                        $(pageDiv).attr({
                            "id": "historyPage"
                        });
                        $(pageDiv).appendTo($("#service_history_block"));
                        if (pageCount > 1) {
                            var historyPage = new PageUtil();
                            historyPage.getPageControl().init({
                                container: "historyPage",
                                preBtn: "his_pre",
                                nextBtn: "his_next",
                                totalPage: pageCount,
                                pageChange: function (page) {
                                    HistoryRecord.getDatas(page);
                                }
                            });
                            historyPage.getPageControl()
                                .selectPage(1, true);
                        } else {
                            $("#historyPage")
                                .html(
                                    '<span class="page page_action">1</span>');
                        }
                    }

                }
            });
    },
    createDataNodes: function (dataList) {
        var str = '<tr><td>姓名</td><td>性别</td><td>服务群组</td><td>收费方式</td><td>开始时间</td><td>结束时间</td><td>收费记录</td><td>状态</td></tr>';
        if (dataList.length == 0) {
            str += '<tr><td colspan="8">没有相关数据</td></tr>';
            $("#history_record").html(str);
            return;
        }
        for (var index in dataList) {
            var item = dataList[index];
            item.startDate = DateUtils.formatDate(new Date(item.startDate));
            item.sex == true ? item.sex = '男' : item.sex = '女';
            item.realName == undefined ? item.realName = "" : item.realName;
            item.fair = parseFloat(item.fair).toFixed(2);
            if (item.endDate != undefined)
                item.endDate = DateUtils.formatDate(new Date(item.endDate));
            switch (item.chargeMode) {
                case 0:
                    item.chargeMode = '免费';
                    if (item.endDate == undefined)
                        item.endDate = "无限制";
                    break;
                case 1:
                    item.chargeMode = '按次';
                    item.endDate = "剩余" + item.timesRemaining + "次";
                    break;
                case 2:
                    item.chargeMode = '按月';
                    break;
                case 3:
                    item.chargeMode = '按年';
                    break;
            }

            /* item.status = DateUtils.getInterval(item.endDate) > 0 ? "已过期"
             : "正常";*/
            str += '<tr><td>' + item.realName + '</td><td>' + item.sex
                + '</td><td>' + item.groupName + '</td><td>' + item.chargeMode
                + '</td><td>' + item.startDate + '</td><td>' + item.endDate
                + '</td><td><span class="money">￥' + item.fair
                + '</span></td>';

            switch (item.status) {
                case 1:
                    item.status = '待付款';
                    break;
                case 2:
                    item.status = '付款失败';
                    break;
                case 3:
                    item.status = '有效';
                    break;
                case 4:
                    item.status = '已完成';
                    break;
                case 5:
                    item.status = '退款失效';
                    break;
                case 6:
                    item.status = '已取消';
                    break;
            }
            console.log(item.status);
            str += '<td>' + item.status + '</td></tr>';
            /*switch (item.status) {
             case "已过期":
             str += '<td><span style="color:#6c6c6c">已过期</span></td></tr>';
             break;
             case "正常":
             str += '<td><span style="color:#48c858">正常</span></td></tr>';
             break;
             }*/
        }
        $("#history_record").html(str);
    }
}

/* 群组的管理类 */
var groupControl = {
    userType:0,
    groupCache: {},// 群组信息缓存
    changeGroup: function (obj, id, count) {// 切换群组
        if ($(obj).hasClass("action"))
            return;
        $(obj).addClass("action");
        $(obj).siblings("li").removeClass("action");
        if (!this.groupCache.hasOwnProperty(id)) {
            ajaxSend({
                groupId: id,
                serveTarget: serveTarget
            }, 'orgServeControl.do?getGroupDatas', false, function (result) {
                groupControl.groupCache[id] = result;
                groupControl.changeGroupData(result, count);
            });
        } else {
            this.changeGroupData(this.groupCache[id], count);
        }
    },
    changeGroupData: function (result, count) {//
        $(".group_details").html('');
        var groupInfo = groupControl.createGroupInfo(
            result.attributes.groupInfo, count);

        var memberList = groupControl
            .createMemberList(result.attributes.memberList);
        $(groupInfo).appendTo($(".group_details"));
        $(memberList).appendTo($(".group_details"));
        $("#groupPageContain").remove();

        if (result.attributes.memberList.length != 0) {
            var pageDiv = document.createElement("div");
            $(pageDiv).addClass("page_Container");
            $(pageDiv).attr({
                "id": "groupPageContain",
                "style": "margin-top:30px;text-align:right;padding-right:10px"
            });
            $(pageDiv).appendTo($("#group_control_block"));
            var pageCount = count % 10 == 0 ? count / 10 : Math
                    .floor(count / 10) + 1;
            if (pageCount > 1) {
                var groupPage = new PageUtil();
                groupPage.getPageControl().init({
                    container: "groupPageContain",
                    preBtn: "groupPre",
                    nextBtn: "groupNext",
                    totalPage: pageCount,
                    pageChange: function (page) {
                        groupControl.getGroupDataByPage(page);
                    }
                });
                groupPage.getPageControl().selectPage(1, true);
            } else {
                $("#groupPageContain").html(
                    '<span class="page page_action">1</span>');
            }
            initUserControl(serveTarget);
        }
    },
    addGroup: function (groupName) {// 添加群组
        if (groupName == '') {
            layer.msg('群组名字不能为空');
            return;
        }
        layer.load();
        var json = {
            name: groupName
        };
        var servers = customRadio.getResult('groupSelect');
        if (servers.length != 0) {
            json.servers = servers;
        }
        ajaxSend(json, 'orgServeControl.do?addGroup&serveId=' + serveTarget,
            true, function (result) {
                var groupItem = document.createElement("li");
                $(groupItem).attr({"data-id": result.obj, "onclick": "groupControl.changeGroup(this," + result.obj + ",0)"});
                if (groupName.length > 8)
                    groupName = groupName.substr(0, 8) + '...';
                $(groupItem).text(groupName + " (0)");
                if ($(".group_list").children().length == 0) {
                    $(groupItem).appendTo($(".group_list"));
                    return;
                }
                $(groupItem).insertBefore($(".group_list li:first"));
                layer.closeAll();
                groupManagerCache = null;
            });
    },
    editGroup: function (oldData) {// 编辑群组
        var nowGroupName = $.trim(groupName_input.value);
        if (nowGroupName == '') {
            layer.msg('群组名字不能为空');
            return;
        }
        var oldSelect = oldData['selectServers'];// 之前选择过的服务师
        var oldArray = null;
        if (!$.isEmptyObject(oldSelect)) {
            oldArray = [];
            for (var index in oldSelect) {
                oldArray.push(oldSelect[index].id);
            }
        }
        var groupId = oldData['groupId'];
        var groupName = oldData['groupName'];
        var nowSelect = customRadio.getResult('groupSelect');
        var needDel = [];// 需要进行删除的操作
        var needInsert = [];// 需要进行插入的操作
        if (oldArray != null) {
            for (var index in nowSelect) {
                var n = parseInt(nowSelect[index]);
                if ($.inArray(n, oldArray) == -1) {
                    needInsert.push(n.toString());
                }
            }
            for (var index in oldArray) {
                var n = oldArray[index].toString();
                if ($.inArray(n, nowSelect) == -1) {
                    for (var index in oldSelect) {
                        var temp = oldSelect[index];
                        if (temp.id == n) {
                            needDel.push(temp.goId);
                            break;
                        }
                    }
                }
            }
        } else {
            needInsert = nowSelect;
        }
        var json = {
            groupId: groupId
        };
        if (nowGroupName != groupName)
            json.groupName = nowGroupName;
        if (needDel.length > 0)
            json.needDel = needDel;
        if (needInsert.length > 0)
            json.needInsert = needInsert;
        if (!json.hasOwnProperty("groupName")
            && !json.hasOwnProperty("needDel")
            && !json.hasOwnProperty("needInsert")) {// 表示当前没做任何修改
            layer.closeAll();
            return;
        }
        ajaxSend(json, 'orgServeControl.do?editGroup', true, function (result) {
            if (result.success)
                window.location.reload();

        });
    },
    getGroupDataByPage: function (page) {
        var groupId = parseInt($("#groupNumber").text());
        ajaxSend({
            groupId: groupId,
            page: page
        }, 'orgServeControl.do?getMemberList', false, function (result) {
            $(".groupMember").html('');
            var tableDatas = groupControl.setDataList(result);
            $(tableDatas).appendTo($(".groupMember"));
            initUserControl(serveTarget);
        });
    },
    createGroupInfo: function (data, count) {
        var detailsDiv = document.createElement("div");
        $(detailsDiv).addClass("details_contain");
        $(detailsDiv)
            .html(
                '<div class="details_title"><h2>群组信息</h2></div><div class="details_img"><img src="static/images/head.png" width="100" height="100" /></div>');
        var rightContent = document.createElement("div");
        $(rightContent).addClass("right_details");
        data.createDate = DateUtils.formatDate(new Date(data.createDate));
        var displayServer=null;
        if (data.server == undefined) {
            data.server = null;
            displayServer="无"
        } else {
            if (data.server.length > 24) {
                displayServer = data.server.substr(0, 24) + '..';
            }else{
                displayServer=data.server;
            }

        }
        $(rightContent)
            .html(
                '<div class="group_name"><img src="static/images/qrcode.png" onclick="dialogUtils.showDialog(3,\''
                + data.id
                + '\',\''
                + data.name
                + '\')" /><h2>'
                + data.name
                + '</h2></div><div>群编号：<span id="groupNumber">'
                + data.id
                + '</span>&nbsp;&nbsp;&nbsp;&nbsp;本群创建时间：<span>'
                + data.createDate
                + '</span></div><div>本群人数：<span id="memberCount">'
                + count
                + '</span>&nbsp;&nbsp;&nbsp;&nbsp;服务师：<span class="manager" onclick="dialogUtils.showDialog(0,\''
                + data.server
                + '\')">'
                + displayServer
                + '</span></div>');
        $(rightContent).appendTo($(detailsDiv));
        return detailsDiv;
    },
    createMemberList: function (data) {
        var containDiv = document.createElement("div");
        $(containDiv).addClass("groupMember");
        var containTable = this.setDataList(data);
        $(containTable).appendTo($(containDiv));
        return containDiv;
    },
    setDataList: function (data) {
        var containTable = document.createElement("table");
        $(containTable).attr({
            "class": "service_table",
            "cellpadding": "0",
            "cellspacing": "0"
        });
        var str = '<tr><td>姓名</td><td>性别</td><td>剩余</td><td>到期时间</td><td>操作</td></tr>';
        if (data.length == 0) {
            str += '<tr><td colspan="5">该群组还未有成员</td></tr>';
            $(containTable).html(str);
            return containTable;
        }
        for (var index in data) {
            var item = data[index];
            var remainingDay = null;
            var end = DateUtils.formatDate(new Date(item.endDate));
            switch (parseInt(item.chargeMode)) {
                case 0:
                    if (item.endDate == undefined) {
                        remainingDay = "无限制";
                        end = "无限制";
                    }
                    break;
                case 1:
                    remainingDay = item.timesRemaining + "次";
                    end = '';
                    break;
            }

            if (remainingDay == null) {
                remainingDay = -DateUtils.getInterval(end);
                remainingDay = remainingDay < 0 ? "<span class='money'>0天</span>"
                    : "<span style='color:#48c858;'>" + remainingDay
                    + "天</span>";
            }

            item.sex == true ? item.sex = '男' : item.sex = '女';
            if (item.realName == undefined)
                item.realName = '';
            str += '<tr><td>'
                + item.realName
                + '</td><td>'
                + item.sex
                + '</td><td>'
                + remainingDay
                + '</td><td>'
                + end
                + '</td><td><a href="javascript:dialogUtils.showDialog(4,'
                + item.id
                + ','+item.chargeMode+')"><img class="person_info" src="static/images/person_info.png" /></a> <a href="javascript:dialogUtils.showDialog(1,'
                + item.id
                + ')"><img class="health_data" src="static/images/health_data.png" /></a>';
            switch (this.userType){
                case 0:
                    str+=' <a href="javascript:dialogUtils.showDialog(6,' + item.id + ')"><img class="relation"src="static/images/relation.png" /></a></td></tr>';
                    break;
                case 1:
                    str+=' <a href="javascript:dialogUtils.showDialog(5,\'' + item.realName + '\',' + item.hxId + ')"><img class="conversation" src="static/images/conversation.png" /></a></td></tr>';
                    break;
            }

            item.sex == '男' ? item.sex = true : item.sex = false;// 还原，防止数据总是女的
        }
        $(containTable).html(str);
        return containTable;
    }, showMoveDialog: function (escape) {
        layer.confirm("你确定要删除该群组吗？",function (index) {
            layer.close(index);
            var c=parseInt($("#memberCount").text());
            if($(".group_list").children().length==1){
                layer.msg("最后一个群组不能删除");
                return;
            }
            if(c==0){//群成员数量为0直接删除
                var oldGroup = $("#groupNumber").text();
                ajaxSend({
                        oldGroup: oldGroup
                    }, 'orgServeControl.do?delGroup', true,
                    function (result) {
                        if(!result.obj.success){
                            if(result.obj.message!=undefined&&result.obj.message!=''){
                                layer.msg(result.obj.message);
                            }else{
                                layer.msg("删除失败");
                            }
                            return;
                        }
                        window.location.reload();
                    });
                return;
            }

            var dialog = dialogUtils.createMoveGroupContainer(escape);
            layer.open({
                type: 1,
                area: ['250px'],
                title: ['移动群组',
                    'text-align:center;font-size:16px;background:#fff;'],
                moveType: 1,
                scrollbar: false,
                zIndex: 9999,
                content: $(dialog),
                success: function () {

                },
                end: function () {
                    $(dialog).remove();
                }, btn: ['确定', '取消'],
                yes: function (index) {
                    var v = $(dialog).find($("select option:selected")).val();
                    var oldGroup = $("#groupNumber").text();
                    ajaxSend({
                            newGroup: v,  serveId: serveTarget, oldGroup: oldGroup
                        }, 'orgServeControl.do?delGroup', true,
                        function (result) {
                            if(!result.obj.success){
                                if(result.obj.message!=undefined&&result.obj.message!=''){
                                    layer.msg(result.obj.message);
                                }else{
                                    layer.msg("删除失败");
                                }
                                return;
                            }
                            window.location.reload();
                        });
                }
            });
        })

    }
}

var ajaxSend = function (json, url, isJson, callBack) {
    layer.load();
    var type = 'application/json; charset=utf-8';
    isJson ? json = JSON.stringify(json)
        : type = 'application/x-www-form-urlencoded; charset=utf-8';
    $.ajax({
        type: 'POST',
        contentType: type,
        url: url,
        data: json,
        success: function (result) {
            callBack(result);
        },complete:function () {
            layer.closeAll("loading");
        }
    });
}

var groupManagerCache = null// 添加群组里的服务师缓存,添加或编辑完后此处必须设为null

/* 对话框创建的工具类 */
var dialogUtils = {
    /** 健康数据缓存 */
    healthDataCache: {},
    showDialog: function (target, data, d) {
        switch (target) {
            case 0:// 服务师信息的对话框
                if (data == null)
                    return;
                if (data==undefined|| data==null) {
                    layer.msg("该群组还没分配服务师，无法查看");
                    return;
                }
                var dialog = $('<div style="padding: 15px;">'+data+'</div>');
                document.body.appendChild($(dialog)[0]);
                layer
                    .open({
                        type: 1,
                        title: ['服务师信息',
                            'text-align:center;font-size:16px;background:#fff;'],
                        area: '650px',
                        offset: 100,
                        moveType: 1,
                        scrollbar: false,
                        zIndex: 9999,
                        scrolling: 'no',
                        content: $(dialog),
                        end: function () {// 销毁该弹出层
                            $(dialog).remove();
                        }
                    });
                /*var result = ajaxSend(
                 {
                 id: data
                 },
                 'orgServeControl.do?getEmployInfo',
                 false,
                 function (result) {
                 layer.closeAll();
                 var dialog = dialogUtils.createServicerInfo(result);
                 layer
                 .open({
                 type: 1,
                 title: ['服务师信息',
                 'text-align:center;font-size:16px;background:#fff;'],
                 area: '650px',
                 offset: 100,
                 moveType: 1,
                 scrollbar: false,
                 zIndex: 9999,
                 scrolling: 'no',
                 content: $(dialog),
                 end: function () {// 销毁该弹出层
                 $(dialog).remove();
                 }
                 });
                 });*/
                break;
            case 1:// 健康数据对话框
                if (data == null) {
                    return;
                }
                var userId = parseInt(data);
                var date = DateUtils.formatDate(new Date());

                var key = userId + "," + date;

                if (!this.healthDataCache.hasOwnProperty(key)) {
                    jQuery
                        .ajax({
                            type: 'GET',
                            url: 'orgServeControl.do?getMemberHealthDataByMeasureDate&userId='
                            + userId + '&measureDate=' + date,
                            beforeSend: function () {
                                layer.load(1);
                            },
                            success: function (result) {
                                openHealthDialog(result.attributes);
                                // 保存数据到缓存
                                dialogUtils.healthDataCache[key] = result.attributes;
                            },
                            complete: function (data) {
                                layer.closeAll("loading");
                            }
                        });
                } else {
                    openHealthDialog(this.healthDataCache[key]);
                }

            function openHealthDialog(data) {
                var dialog = dialogUtils.createHealthData(userId, date, data);
                layer
                    .open({
                        type: 1,
                        title: ['健康数据',
                            'text-align:center;font-size:16px;background:#fff;'],
                        area: '650px',
                        offset: 100,
                        moveType: 1,
                        scrollbar: false,
                        zIndex: 9999,
                        scrolling: 'no',
                        content: $(dialog),
                        end: function () {// 销毁该弹出层
                            jQuery("div.layui-layer-content").remove();
                        }
                    });
            }

                break;
            case 2:// 添加或编辑群组的对话框
                if (data != undefined && data == 1) {// 编辑
                    if ($(".group_list").children().length == 0) {
                        layer.msg("您目前没有群组可进行编辑");
                        return;
                    }
                    layer.load();
                    var groupId = $('#groupNumber').text();
                    var json = {
                        groupId: groupId
                    };
                    if (groupManagerCache == null)
                        json.noServerCache = true;
                    ajaxSend(json, 'orgServeControl.do?getEditGroupCondition',
                        true, function (result) {
                            layer.closeAll();
                            if (groupManagerCache == null)
                                groupManagerCache = result.attributes.emList;
                            var groupName = $.trim($(".group_name h2").text());
                            var oldData = {
                                selectServers: result.attributes.servers,
                                groupId: groupId,
                                groupName: groupName
                            };
                            dialogUtils.showAddGroupDiaglog(dialogUtils
                                    .createAddGroup(groupManagerCache, true,
                                        result.attributes.servers), true,
                                oldData);
                        });
                    return;
                }
                // 添加
                if (groupManagerCache != null) {
                    dialogUtils.showAddGroupDiaglog(dialogUtils
                        .createAddGroup(groupManagerCache));
                    return;
                }
                layer.load();
                ajaxSend(null, 'orgServeControl.do?getAddGroupCondition', false,
                    function (result) {
                        groupManagerCache = result.obj;
                        dialogUtils.showAddGroupDiaglog(dialogUtils
                            .createAddGroup(groupManagerCache));
                    });
                break;
            case 3:// 群组二维码对话框
                var dialog = this.createGroupQrcode(data, d);
                layer.open({
                    type: 1,
                    title: ['二维码',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: '350px',
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    scrolling: 'no',
                    content: $(dialog),
                    end: function () {// 销毁该弹出层
                        $(dialog).remove();
                    }
                });
                break;
            case 4:// 个人信息的对话框
                var dialog = this.peopleInfo.createPersonInfo(data,d);
                layer.open({
                    type: 1,
                    title: ['个人信息',
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: ['670px', '600px'],
                    offset: 100,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    content: $(dialog),
                    success: function () {
                        var obj = jQuery(
                            "div.person_dialog_contain>div.right_title>label")
                            .eq(0).click();
                    },
                    end: function () {
                        $(dialog).remove();
                    }
                });
                break;
            case 5://健康会话
                ChatControl.openChat({
                    to: d,
                    nick: data
                });
                break;
            case 6://移动群组
                if($(".group_list").children().length==1){
                    layer.msg("当前没有可以移动到的群组");
                    return;
                }
                var dialog = this.createMoveGroupContainer($("#groupNumber").text());
                layer.open({
                    type: 1,
                    area: ['250px'],
                    title: ['移动群组',
                        'text-align:center;font-size:16px;background:#fff;'],
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 9999,
                    content: $(dialog),
                    success: function () {

                    },
                    end: function () {
                        $(dialog).remove();
                    }, btn: ['确定', '取消'],
                    yes: function (index) {
                        var v = $(dialog).find($("select option:selected")).val();
                        var oldGroup = $("#groupNumber").text();
                        ajaxSend({
                                newGroup: v, userId: data, serveId: serveTarget, oldGroup: oldGroup
                            }, 'orgServeControl.do?moveGroup', true,
                            function (result) {
                                layer.closeAll();
                                window.location.reload();
                            });
                    }
                });
                break;
        }
    },
    createMoveGroupContainer: function (escape) {
        var str = '<div style="padding:10px 15px;background:#fff;text-align: center"><select style="padding: 0 8px;font-size: 15px">';
        $(".group_list li").each(function () {
            var name = $(this).text();
            var dataId = $(this).attr("data-id");
            if (dataId != escape)//防止将要删除的群组重名
                str += '<option value="' + dataId + '">' + name + '</option>';
        });
        str += '</select></div>';
        var dialog = $(str);
        document.body.appendChild($(dialog)[0]);
        return dialog;
    },
    showAddGroupDiaglog: function (dialog, isEdit, oldData) {
        var title = isEdit == undefined ? "添加群组" : "编辑群组";
        layer.open({
            type: 1,
            title: [title,
                'text-align:center;font-size:16px;background:#fff;'],
            area: '550px',
            moveType: 1,
            scrollbar: false,
            zIndex: 9999,
            btn: ['确定', '取消'],
            scrolling: 'no',
            content: $(dialog),
            end: function () {// 销毁该弹出层
                $(dialog).remove();
            },
            yes: function (index) {
                isEdit != undefined && isEdit ? groupControl.editGroup(oldData)
                    : groupControl.addGroup($.trim(groupName_input.value));
            }
        });
    },
    createServicerInfo: function (data) {// 创建服务师显示信息的对话框
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        var head = document.createElement("img");
        $(head).css({
            "position": "absolute",
            "width": "110px",
            "height": "110px",
            "margin": "15px"
        });
        head.src = data.photo == undefined ? "static/images/head.png"
            : data.photo;
        var msgContain = document.createElement("ul");
        $(msgContain)
            .attr("style",
                "margin-left:140px;font-size:16px;line-height:28px;padding:15px 10px");
        data.sex == true ? data.sex = '男' : data.sex = '女';
        $(msgContain).html(
            '<li><h2 style="font-size:20px;">' + data.realName
            + '（服务师）</h2></li><li>用户名：' + data.userName
            + '</li><li>性别：' + data.sex + '</li> <li>所属机构：'
            + data.orgName + '</li><li>服务的群组：' + data.serveGroup
            + '</li>');
        dialogContainer.appendChild(head);
        dialogContainer.appendChild(msgContain);
        document.body.appendChild(dialogContainer);
        return dialogContainer;
    },
    createHealthData: function (userId, date, data) {// 创建健康数据对话框

        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        $(dialogContainer).css("padding-top", "0");
        var dateContain = document.createElement("div");
        $(dateContain).addClass("date_contain");
        $(dateContain)
            .html(
                '<a href="javascript:dialogUtils.switchHealthData('
                + userId
                + ',\''
                + String(date)
                + '\',-1)" class="pre_v"><img src="static/images/pre_v.png">'
                + '</a><a href="javascript:dialogUtils.switchHealthData('
                + userId
                + ',\''
                + date
                + '\',1)" class="next_d"><img src="static/images/pre_v.png"></a><span>'
                + String(date) + '</span>');
        var healthTable = document.createElement("table");
        $(healthTable).attr({
            "cellpadding": "0",
            "cellspacing": "0",
            "class": "healthTable"
        });

        var $tbody = this.createHealthDataBody(data);
        $(healthTable).append($tbody);

        dialogContainer.appendChild(dateContain);
        dialogContainer.appendChild(healthTable);
        document.body.appendChild(dialogContainer);
        return dialogContainer;
    },
    createHealthDataBody: function (data) {

        var $tbody = jQuery("<tbody></tbody>");

        if (data.Glucometer != 'undefined' && data.Glucometer != null) {
            var $xueTang = jQuery('<tr class="healthdata-result"><td>血糖仪</td><td><p>血糖值：'
                + glucometerMeasureTypeToString(data.Glucometer.measureType)
                + ' '
                + data.Glucometer.bloodSugar
                + ' mmol/L</p></td><td>'
                + (new Date(data.Glucometer.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血糖仪
            $tbody.append($xueTang);

            function glucometerMeasureTypeToString(measureType) {
                var string = "";
                switch (measureType) {
                    case 1:
                        string = "早餐前";
                        break;
                    case 2:
                        string = "早餐后";
                        break;
                    case 3:
                        string = "午餐前";
                        break;
                    case 4:
                        string = "午餐后";
                        break;
                    case 5:
                        string = "晚餐前";
                        break;
                    case 6:
                        string = "晚餐后";
                        break;
                }
                return string;
            }
        }
        if (data.Bloodpressure != 'undefined' && data.Bloodpressure != null) {
            var $xueYa = jQuery('<tr class="healthdata-result"> <td>血压计</td><td><p>收缩压：'
                + data.Bloodpressure.diastolic
                + ' mmHg</p><p>舒张压：'
                + data.Bloodpressure.systolic
                + ' mmHg</p><p>心率：'
                + data.Bloodpressure.heartRate
                + ' bpm</p></td><td>'
                + (new Date(data.Bloodpressure.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血压计
            $tbody.append($xueYa);
        }
        if (data.Oxygen != 'undefined' && data.Oxygen != null) {
            var $xueYangYi = jQuery('<tr class="healthdata-result"><td>血氧仪</td><td><p>血氧饱和度：'
                + data.Oxygen.saturation
                + ' %</p><p>心率：'
                + data.Oxygen.heartRate
                + ' bpm</p></td><td>'
                + (new Date(data.Oxygen.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 血氧仪
            $tbody.append($xueYangYi);
        }
        if (data.Lunginstrument != 'undefined' && data.Lunginstrument != null) {
            var $feiHuoYi = jQuery('<tr class="healthdata-result"><td>肺活仪</td><td><p>肺活仪：'
                + data.Lunginstrument.vitalCapacity
                + ' ml</p></td><td>'
                + (new Date(data.Lunginstrument.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 肺活仪
            $tbody.append($feiHuoYi);
        }
        if (data.Heartrate != 'undefined' && data.Heartrate != null || data.Band != 'undefined' && data.Band != null) {
            var date = null;
            var heartRateData = "暂时没有该项数据";
            if (data.Heartrate != 'undefined' && data.Heartrate != null) {
                heartRateData = "";
                $.each(data.Heartrate,function(e){
                    if(data.Heartrate[e].heartRate){
                        heartRateData+=new Date(data.Heartrate[e].measureDate).Format("h点")+":"+data.Heartrate[e].heartRate+ " bpm";
                        if(e<data.Heartrate.length-1){
                            heartRateData+=";";
                        }
                    }
                });
                date = data.Heartrate[0].measureDate;
            }
            var bandData = '';

            if (data.Band != 'undefined' && data.Band != null) {
                bandData =  '<p>卡路里：'
                    + data.Band.kcal + ' Kcal</p><p>里程：' + (parseFloat(data.Band.mileage) / 1000)
                    + ' 公里</p><p>深睡：'
                    + data.Band.deepDuration + ' 分钟</p><p>浅睡：'
                    + data.Band.shallowDuration + ' 分钟</p>';
                date = data.Band.date;
            }
            var $xinLvSH = jQuery('<tr class="healthdata-result"><td>心率手环</td><td>' +
                bandData +
                '<p>心率：' + heartRateData + '</p></td><td>'
                + (new Date(date).Format("yyyy-MM-dd"))
                + '</td></tr>');// 心率手环

            $tbody.append($xinLvSH);
        }
        if (data.Bodyfatscale != 'undefined' && data.Bodyfatscale != null) {
            var $tiZhiCheng = jQuery('<tr class="healthdata-result"><td>体脂秤</td><td><p>体重：'
                + data.Bodyfatscale.weight
                + ' Kg</p><p>体脂肪率：'
                + data.Bodyfatscale.axungeRatio
                + ' %</p>'
                + '<p>体年龄：'
                + data.Bodyfatscale.bodyage
                + ' 岁</p>'
                + '<p>腰臀比：'
                + data.Bodyfatscale.WHR
                + '</p>'
                + '<p>BMI：'
                + data.Bodyfatscale.BMI
                + '</p>'
                // + '<p>去脂体重：'
                // + data.Bodyfatscale.fatFreeWeight
                // + ' Kg</p>'
                + '<p>人体水分：'
                + data.Bodyfatscale.moisture
                + ' %</p>'
                + '<p>人体肌肉：'
                + data.Bodyfatscale.muscle
                + ' %</p>'
                + '<p>骨骼重量：'
                + data.Bodyfatscale.boneWeight
                + ' Kg</p>'
                + '<p>基础代谢：'
                + data.Bodyfatscale.baseMetabolism
                + ' Kcal</p>'
                + '<p>内脏脂肪：'
                + data.Bodyfatscale.visceralFat
                + '</p>'
                + '</td><td>'
                + (new Date(data.Bodyfatscale.measureDate)
                    .Format("yyyy-MM-dd hh:mm:ss")) + '</td></tr>');// 体脂秤
            $tbody.append($tiZhiCheng);
        }

        // 判断数据是否为空
        if ($tbody.children().length == 0) {
            var $noData = jQuery("<tr><td colspan='3'>暂时没有数据</td></tr>");
            $tbody.append($noData);
        } else {
            var $title = jQuery('<tr><td width="20%">类型</td><td width="50%">测量值</td><td width="30%">检测时间</td></tr>');
            $tbody.prepend($title);
        }

        return $tbody;
    },
    switchHealthData: function (userId, date, variationValue) {
        // 切换日期
        var newDate = new Date(String(date));
        newDate.setDate(newDate.getDate() + variationValue);

        if (newDate.isAfter(new Date())) {
            layer.msg("很抱歉，无法提供未来的数据");
            return;
        }

        var newDateStr = newDate.Format("yyyy-MM-dd");

        var key = userId + "," + newDateStr;

        // 移除
        var $root = jQuery("div.layui-layer-content");
        $root.empty();

        if (!this.healthDataCache.hasOwnProperty(key)) {
            jQuery
                .ajax({
                    type: 'GET',
                    url: 'orgServeControl.do?getMemberHealthDataByMeasureDate&userId='
                    + userId + '&measureDate=' + newDateStr,
                    beforeSend: function () {
                        layer.load(1);
                    },
                    success: function (result) {
                        // 获取新的dialog
                        var $dialog = dialogUtils.createHealthData(userId,
                            newDateStr, result.attributes);

                        $root.append($dialog);

                        // 将数据存放到缓存中
                        dialogUtils.healthDataCache[key] = result.attributes;
                    },
                    complete: function () {
                        layer.closeAll("loading");
                    }
                });
        } else {
            // 获取新的dialog
            var $dialog = dialogUtils.createHealthData(userId, newDateStr,
                dialogUtils.healthDataCache[key]);

            $root.append($dialog);
        }
    },
    createAddGroup: function (data, isEdit, servers) {// 创建添加或编辑群组的对话框
        console.log(servers);
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        var groupNameSet = document.createElement("div");
        $(groupNameSet).addClass("groupName_set");
        isEdit != undefined && isEdit ? $(groupNameSet).html(
                '<label>群组名称：</label><input id="groupName_input" type="text" value="'
                + $(".group_name h2").text()
                + '" placeholder="请输入群组名称"/><a href="javascript:groupControl.showMoveDialog('+$("#groupNumber").text()+')" style="position: absolute;right: 50px">删除群组</a>')
            : $(groupNameSet)
                .html(
                    '<label>群组名称：</label><input id="groupName_input" type="text" placeholder="请输入群组名称"/>');

        var addTable = document.createElement("table");
        var tableDiv = document.createElement("div");
        $(tableDiv).attr("style", "max-height: 300px;overflow-y:auto");
        $(addTable).attr({
            "class": "service_table",
            "cellpadding": "0",
            "cellspacing": "0",
        });
        var groupStr = '<tr><td>选择框</td><td>服务师</td><td>管理群数</td></tr>';// 服务师拼接字符串
        if (data.length == 0) {
            groupStr += '<tr><td colspan="3">该门店还没有服务师</td></tr>'
        } else {
            for (var index in data) {
                var item = data[index];
                groupStr += '<tr><td style="position:relative">';
                if (servers != undefined) {
                    var has = false;
                    for (var i in servers) {
                        var s = servers[i];
                        if (s.id == item.id) {
                            has = true;
                            break;
                        }
                    }
                    has ? groupStr += '<input type="checkbox" value=' + item.id
                            + ' name="groupSelect" checked/>'
                        : groupStr += '<input type="checkbox" value='
                            + item.id + ' name="groupSelect"/>';
                } else {
                    groupStr += '<input type="checkbox" value=' + item.id
                        + ' name="groupSelect"/>';
                }
                groupStr += '</td><td>' + item.realName + '</td><td>'
                    + item.groupCount + '</td></tr>';
            }
        }
        $(addTable).html($(addTable).html() + groupStr);
        $(addTable).appendTo($(tableDiv));
        var notice = document.createElement("div");
        $(notice).attr("style",
            "padding:15px 15px 0;font-size:16px;color:#6c6c6c");
        $(notice).html('创建后方便管理用户，可调动服务师管理不同用户群');
        dialogContainer.appendChild(groupNameSet);
        dialogContainer.appendChild(tableDiv);
        dialogContainer.appendChild(notice);
        $(dialogContainer).width(550);
        document.body.appendChild(dialogContainer);
        customRadio.init({
            name: 'groupSelect',
            color: 'blue'
        });
        return dialogContainer;
    },
    createGroupQrcode: function (url, data) {// 创建群组二维码对话框,url为扫描二维码后的链接方向
        var dialogContainer = document.createElement("div");
        $(dialogContainer).addClass("dialog_contain");
        $(dialogContainer)
            .attr("style",
                "text-align:center;line-height:35px;font-size:16px;width:350px");
        var qrCodeDiv = document.createElement("div");
        $(qrCodeDiv).attr({
            "style": "display:inline-block;margin-top:10px",
            "id": "qrcode"
        });
        dialogContainer.appendChild(qrCodeDiv);
        $(dialogContainer)
            .html(
                $(dialogContainer).html()
                + '<p style="font-weight:bold;font-size:20px;margin-top:15px">'
                + data + '</p><p>扫一扫加入该群组</p>');
        document.body.appendChild(dialogContainer);
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width: 150,
            height: 150
        });
        qrcode.makeCode('{"serveGroupId":"'+url+'"}');
        return dialogContainer;
    },
    peopleInfo: {// 创建个人信息对话框
        basicMessageCache: {}, // 用户基本信息缓存
        sickRecordCache: {}, // 病历缓存
        testReportCache: {}, // 体检缓存
        dietRecordCache: {}, // 饮食缓存
        switchDialog: function (obj,chargeMode) {// 切换对应位置的内容
            $(obj).addClass("action");
            $(obj).siblings("label").removeClass("action");
            var target = $.trim($(obj).text());
            $(obj).parent().siblings("div").remove();

            var userId = jQuery("[name='labelUserId']").val();
            var date = new Date().Format("yyyy-MM-dd");

            switch (target) {
                case "基本信息":
                    var groupId = jQuery("#groupNumber").text();
                    this.createBaseInfo(userId, groupId,chargeMode);
                    break;
                case "体检报告":
                    this.createTestReport(userId, date);
                    break;
                case "病历记录":
                    this.createSickRecord(userId, date);
                    break;
                case "饮食记录":
                    this.createDietRecord(userId, date);
                    break;
            }
        },
        createPersonInfo: function (userId,mode) {
            var outContain = this.createNav(userId,mode);
            // var container = this.createBaseInfo();
            // outContain.appendChild(container);
            document.body.appendChild(outContain);
            return outContain;
        },
        createNav: function (userId,mode) {
            var outContain = document.createElement("div");
            $(outContain).addClass("person_dialog_contain");
            var title = document.createElement("div");
            $(title).addClass("right_title");
            $(title)
                .html(
                    '<label class="action"onclick="dialogUtils.peopleInfo.switchDialog(this,'+mode+')"> 基本信息 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 体检报告 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 病历记录 </label> <label onclick="dialogUtils.peopleInfo.switchDialog(this)"> 饮食记录 </label>');
            var $userId = jQuery("<input type='hidden' name='labelUserId' value='"
                + userId + "'>");
            $(title).append($userId);
            outContain.appendChild(title);
            return outContain;
        },
        createDietRecord: function (userId, recordDate) {// 创建饮食记录内容的菜单栏

            var key = userId + "," + recordDate;
            var $root = jQuery("div.person_dialog_contain");

            if (!this.dietRecordCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberDietDataByRecordDate&userId='
                        + userId + '&recordDate=' + recordDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {

                            var body = dialogUtils.peopleInfo
                                .createDietRecordElement(result.obj,
                                    recordDate, userId);
                            $(body).appendTo($root);

                            dialogUtils.peopleInfo.dietRecordCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = dialogUtils.peopleInfo.createDietRecordElement(
                    this.dietRecordCache[key], recordDate, userId);
                $(body).appendTo($root);
            }
        },
        createDietRecordElement: function (datas, recordDate, userId) {
            jQuery("div.diet_container").remove();
            var container = document.createElement("div");
            $(container).addClass("diet_container");
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchDietRecordDate("
                + userId + ", \'" + recordDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchDietRecordDate("
                + userId + ", \'" + recordDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(recordDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var dietList = document.createElement("ul");
            $(dietList).addClass("diet_record_list");
            var li = document.createElement("li");
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {// json数据添加在这里
                    var listr = '<p class="timeDiet"><span class="spot"></span>'
                        + datas[i].dietType
                        + '&nbsp;&nbsp;&nbsp;&nbsp;能量：'
                        + datas[i].energy + '</p><p class="food">';
                    if (datas[i].dietDetails.length > 0) {
                        for (var j = 0; j < datas[i].dietDetails.length; j++) {
                            listr += datas[i].dietDetails[j] + "、";
                        }
                        listr = listr.substring(0, listr.length - 1);
                    }
                    listr += "</p>";
                    $(li).html(listr);
                }
            } else {
                $(li)
                    .html(
                        '<p class="timeDiet" style="text-align:center;">暂时没有数据</p>');
            }
            dietList.appendChild(li);
            container.appendChild(dateContain);
            container.appendChild(dietList);
            return container;
        },
        switchDietRecordDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createDietRecord(userId, newDateStr);
        },
        createTestReport: function (userId, physicalsDate) {// 创建体检报告的菜单栏
            var key = userId + "," + physicalsDate;
            var $root = jQuery("div.person_dialog_contain");
            if (!this.testReportCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberPhysicalDataByPhysicalsDate&userId='
                        + userId
                        + '&physicalsDate='
                        + physicalsDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {
                            var body = dialogUtils.peopleInfo
                                .createTestReportElemet(result.obj,
                                    physicalsDate, userId);
                            $(body).appendTo($root);
                            // 保存到缓存中
                            dialogUtils.peopleInfo.testReportCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = this.createTestReportElemet(
                    this.testReportCache[key], physicalsDate, userId);
                $(body).appendTo($root);
            }
        },
        createTestReportElemet: function (datas, physicalsDate, userId) {
            jQuery("div.testReport_container").remove();
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchTestReportPhysicalsDate("
                + userId + ", \'" + physicalsDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchTestReportPhysicalsDate("
                + userId + ", \'" + physicalsDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(physicalsDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var container = document.createElement("div");
            $(container).addClass("testReport_container");
            var reportList = document.createElement("ul");
            $(reportList).addClass("test_report_list");

            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var item = document.createElement("li");
                    $(item).addClass("report_item");
                    $(item).html(
                        '<span class="report_date">' + datas[i].title
                        + '</span>' + datas[i].physicalsOrg
                        + '<p class="remark">备注：'
                        + datas[i].description + '</p>');
                    if (datas[i].photos.length > 0) {// 如果有图片
                        var imgSet = document.createElement("ul");
                        $(imgSet).addClass("img_set");
                        var imgStr = "";// 图片html拼接
                        for (var j = 0; j < datas[i].photos.length; j++) {// 如果有图片在这里进行添加
                            imgStr += '<li><img src="' + datas[i].photos[j]
                                + '" /></li>';
                        }
                        $(imgSet).html(imgStr);
                        item.appendChild(imgSet);
                    }
                    reportList.appendChild(item);
                }
            } else {
                var item = document.createElement("li");
                $(item).addClass("report_item");
                $(item).css({
                    "text-align": "center"
                });
                $(item).html('<span>暂时没有数据</span>');
                reportList.appendChild(item);
            }
            container.appendChild(dateContain);
            container.appendChild(reportList);
            return container;
        },
        switchTestReportPhysicalsDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createTestReport(userId, newDateStr);
        },
        createBaseInfo: function (userId, groupId,mode) {// 创建基本信息的内容

            var key = userId + "," + groupId;

            var $root = jQuery("div.person_dialog_contain");

            if (!this.basicMessageCache.hasOwnProperty(key+"-"+mode)) {
                jQuery.ajax({
                    type: 'GET',
                    url: 'orgServeControl.do?getMemberDetailMessage&userId='
                    + userId + "&groupId=" + groupId+"&mode="+mode,
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function (result) {
                        var data = result.obj;
                        var body = dialogUtils.peopleInfo.createbaseMsg(data);
                        $(body).appendTo($root);
                        // 保存到缓存
                        dialogUtils.peopleInfo.basicMessageCache[key+"-"+mode] = data;
                    },
                    complete: function () {
                        layer.closeAll("loading");
                    }
                });
            } else {
                var data = this.basicMessageCache[key+"-"+mode];
                var body = this.createbaseMsg(data);
                $(body).appendTo($root);
            }

        },
        createbaseMsg: function (data) {

            jQuery("div.baseMsg").remove();

            var container = document.createElement("div");
            $(container).addClass("baseMsg");
            var imgDiv = document.createElement("div");
            $(imgDiv).addClass("userImg");
            $(imgDiv)
                .html(
                    '<img width="110" height="110" src="' + data.photo
                    + '" />');
            container.appendChild(imgDiv);
            var baseContain = document.createElement("div");
            $(baseContain).addClass("baseContain");
            var remainingDay = null;
            switch (parseInt(data.chargeMode)) {
                case 0:
                    if (data.endDate == undefined)
                        remainingDay = "剩余天数：无限制";
                    break;
                case 1:
                    remainingDay = "剩余次数：" + data.timesRemaining + "次";
                    break;
            }
            if (remainingDay == null) {
                remainingDay = -DateUtils.getInterval(data.endDate);
                remainingDay = remainingDay < 0 ? "剩余天数：<span style='color:#d14242'>已过期</span>"
                    : "剩余天数：<span style='color:#48c858;'>" + remainingDay
                    + "天</span>";
            }
            var realName = data.realName == undefined ? "未填写" : data.realName;
            var birthday = data.birthday;
            birthday = birthday == undefined ? "未填写" : new Date(data.birthday)
                    .Format("yyyy-MM-dd");
            var end=data.endDate==undefined?"无":new Date(data.endDate).Format("yyyy-MM-dd");
            $(baseContain).html(
                ' <h2>' + realName
                + '</h2><table class="msg_table"><tr><td>性别：'
                + (data.sex ? '男' : '女') + '</td><td>当前服务：'
                + data.currentServeName + '</td></tr><tr><td>出生日期：'
                + birthday + '</td><td>服务费用：' + data.consumeMode
                + '</td></tr><tr><td>分组：' + data.groupName
                + '</td><td>' + remainingDay
                + '</td></tr><tr><td>到期时间：'
                + end
                + '</td></tr></table>');

            container.appendChild(imgDiv);
            container.appendChild(baseContain);
            return container;
        },
        createSickRecord: function (userId, visitingDate) {// 创建病历记录内容的对话框
            var key = userId + "," + visitingDate;
            var $root = jQuery("div.person_dialog_contain");
            if (!this.sickRecordCache.hasOwnProperty(key)) {
                jQuery
                    .ajax({
                        type: 'GET',
                        url: 'orgServeControl.do?getMemberMedicalDataByVisitingDate&userId='
                        + userId + "&visitingDate=" + visitingDate,
                        beforeSend: function () {
                            layer.load(2);
                        },
                        success: function (result) {
                            var body = dialogUtils.peopleInfo
                                .createSickRecordElement(result.obj,
                                    visitingDate, userId);
                            $(body).appendTo($root);

                            dialogUtils.peopleInfo.sickRecordCache[key] = result.obj;
                        },
                        complete: function () {
                            layer.closeAll("loading");
                        }
                    });
            } else {
                var body = dialogUtils.peopleInfo.createSickRecordElement(
                    this.sickRecordCache[key], visitingDate, userId);
                $(body).appendTo($root);
            }
        },
        createSickRecordElement: function (datas, visitingDate, userId) {
            jQuery("div.sick_container").remove();
            var dateContain = document.createElement("div");
            $(dateContain).addClass("date_contain");
            var pre = document.createElement("a");
            pre.href = "javascript:dialogUtils.peopleInfo.switchSickRecordVisitingDate("
                + userId + ", \'" + visitingDate + "\', -1)";
            $(pre).addClass("pre_v");
            $(pre).html('<img src="static/images/pre_v.png" />');
            var next = document.createElement("a");
            next.href = "javascript:dialogUtils.peopleInfo.switchSickRecordVisitingDate("
                + userId + ", \'" + visitingDate + "\', 1)";
            $(next).addClass("next_d");
            $(next).html('<img src="static/images/pre_v.png" />');
            var dateTime = document.createElement("span");
            $(dateTime).text(visitingDate);
            dateContain.appendChild(pre);
            dateContain.appendChild(next);
            dateContain.appendChild(dateTime);
            var container = document.createElement("div");
            $(container).addClass("sick_container");
            var sickList = document.createElement("ul");
            $(sickList).addClass("sick_record");
            var liStr = "";// 拼接html字符串用
            if (datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {// json数据添加在这里
                    // 添加一个图片是否为空的判断
                    liStr += '<li><img src="' + datas[i].photo
                        + '"/><div class="sick_data"><p>' + datas[i].title
                        + '</p><p>' + datas[i].name + '&nbsp;&nbsp;'
                        + datas[i].sex + '&nbsp;&nbsp;' + datas[i].age
                        + '岁</p><p>' + datas[i].basicCondition + '</p><p>'
                        + datas[i].visitingDate + '&nbsp;就诊</p></div></li>';
                }
            } else {
                liStr = '<li style="text-align:center;min-height:0px;">暂时没有数据</li>';
            }
            $(sickList).html(liStr);
            container.appendChild(dateContain);
            container.appendChild(sickList);
            return container;
        },
        switchSickRecordVisitingDate: function (userId, date, variationValue) {
            // 切换日期
            var newDate = new Date(String(date));
            newDate.setDate(newDate.getDate() + variationValue);
            if (newDate.isAfter(new Date())) {
                layer.msg("很抱歉，无法提供未来的数据");
                return;
            }
            var newDateStr = newDate.Format("yyyy-MM-dd");
            this.createSickRecord(userId, newDateStr);
        }
    }
}


/*var chatControl = {
 conn:null,//连接变量
 init:function(){
 this.conn = new WebIM.connection({
 https: WebIM.config.https,
 url: WebIM.config.xmppURL,
 isAutoLogin: WebIM.config.isAutoLogin,
 isMultiLoginSessions: WebIM.config.isMultiLoginSessions
 });
 this.conn.listen({
 onOpened: function (message) { //连接成功回调
 //如果isAutoLogin设置为false，那么必须手动设置上线，否则无法收消息
 conn.setPresence();
 alert("登陆成功");
 },
 onClosed: function (message) {
 console.log("连接关闭：" + message);
 }, //连接关闭回调
 onTextMessage: function (message) {
 console.log(message);
 var text = document.createElement("p");
 text.innerHTML = message.from + ":" + message.data;
 chat_content.appendChild(text);
 }, //收到文本消息
 onEmojiMessage: function (message) {
 }, //收到表情消息
 onPictureMessage: function (message) {
 }, //收到图片消息
 onCmdMessage: function (message) {
 }, //收到命令消息
 onAudioMessage: function (message) {
 }, //收到音频消息
 onLocationMessage: function (message) {
 },//收到位置消息
 onFileMessage: function (message) {
 }, //收到文件消息
 onVideoMessage: function (message) {
 }, //收到视频消息
 onPresence: function (message) {
 }, //收到联系人订阅请求、处理群组、聊天室被踢解散等消息
 onRoster: function (message) {
 }, //处理好友申请
 onInviteMessage: function (message) {
 }, //处理群组邀请
 onOnline: function () {
 }, //本机网络连接成功
 onOffline: function () {
 }, //本机网络掉线
 onError: function (message) {
 alert("发送失败");
 } //失败回调
 });
 },login:function(){
 var account = $("#account").val();
 var password = $("#pwd").val();
 var options = {
 apiUrl: WebIM.config.apiURL,
 user: account,
 pwd: password,
 appKey: WebIM.config.appkey
 };
 this.conn.open(options);
 },sendMsg:function () {
 var text = $("#chatMsg").val();
 var other = $("#receiver").val();
 var id = this.conn.getUniqueId();//生成本地消息id
 if ($.trim(other) == '') {
 alert("输入对方账号再发送");
 return;
 }
 var msg = new WebIM.message('txt', id);//创建文本消息
 msg.set({
 msg: text,
 to: other,
 success: function (id, serverMsgId) {
 var obj = document.createElement("p");
 obj.innerHTML = "我：" + text;
 chat_content.appendChild(obj);
 }//消息发送成功回调
 });
 this.conn.send(msg.body);
 }

 }*/
