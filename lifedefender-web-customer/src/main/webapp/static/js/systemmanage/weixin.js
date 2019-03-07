(function() {
    angular.module('Controllers', [])
    .controller('weixinController', ['$scope', '$timeout', function($scope, $timeout) {
        
        $scope.data = {
            parentOption: null,     // 上级菜单
            menuName: null,         // 菜单名称
            includeAction: true,    // 设置动作
            actionType: 'view',     // 动作类型
            actionUrl: null,        // 响应url
            actionKey: null,        // 响应动作
            menuBtn: [],    // 按钮
            layer: {
                form: null
            }
        }
        
        $scope.fn = {
            /** 初始化方法 */
            init: function() {
                layui.use(['form'], function() {
                    var form = layui.form;
                    // 监听上级菜单select
                    form.on('select(pMenu)', function(data) {
                        $scope.$evalAsync(function() {
                            var dValue = data.value;
                            var $includeAction = $("input[name=includeAction]");
                            if (dValue == "") {
                                // 选择无设置为null
                                $scope.data.parentOption = null;
                                // 鉴于edit一级菜单有时候disabled不能移除，加一个判断是否为edit状态
                                if ($("#menuFormHidden").data("pindex") == -1) {
                                    $includeAction.prop("disabled", false);
                                }
                            } else {
                                for(var i = 0; i < $scope.data.menuBtn.length; i++) {
                                    var value = $scope.data.menuBtn[i];
                                    if (value.$$hashKey == dValue) {
                                        $scope.data.parentOption = value;
                                        // 二级菜单
                                        $scope.fn.setIncludeAction(true);
                                        $includeAction.prop("disabled", true);
                                        break;
                                    }
                                }
                            }
                            $timeout(function() {
                                form.render('checkbox');
                            });
                        });
                    });
                    
                    // 监听switch开关
                    form.on('switch(action)', function(data) {
                        $scope.$evalAsync(function() {
                            var checked = data.elem.checked;
                            if (checked) {
                                $scope.data.includeAction = true;
                                // 需要把actionType设置为view 才会显示 动作类型 选项
                                $scope.data.actionType = 'view';
                            } else {
                                $scope.data.includeAction = false;
                                // 需要把actionType设置为null，表示 没有动作类型
                                $scope.data.actionType = null;
                            }
                            $timeout(function() {
                                form.render('select');
                            });
                        });
                    });
                    
                    // 监听actionType
                    form.on('select(actionType)', function(data) {
                        $scope.$evalAsync(function() {
                            $scope.data.actionType = data.value;
                        });
                    });

                    $scope.service.listMenuBtn(true);
                    form.render();
                    
                    $scope.data.layer.form = form;
                });
            },
            /** 显示子菜单 */
            showChild: function($event, index) {
                var data = $scope.data.menuBtn[index].sub_button;
                if (!$.isEmptyObject(data)) {
                    var dom = $event.target;
                    // 已经点击过展开的按钮，说明这一次点击是收放请求，需要把图标修改为'+'，同时销毁掉子栏目
                    if ($("tr[data-child='" + index + "']").hasClass("hidden")) {
                        $(dom).parent().find("img")[0].src = '/static/images/close.png';
                        $("tr[data-child='" + index + "']").removeClass("hidden");
                    } else {
                        $(dom).parent().find("img")[0].src = '/static/images/open.png';
                        $("tr[data-child='" + index + "']").addClass("hidden");
                    }
                } else {
                    layer.msg("该栏目下没有子栏目");
                }
            },
            /** 菜单新增 */
            addNew: function(parentIndex) {
                $scope.fn.openDialog("添加菜单", function() {
                    
                    var parentOptionValue = "";
                    // 判断父菜单的index是否存在
                    if (parentIndex != undefined) {
                        // 将缩放隐藏
                        $("img.toggleBtn").attr("src", '/static/images/open.png');
                        $("tr[data-child]").addClass("hidden");
                        
                        parentOptionValue = $scope.data.menuBtn[parentIndex].$$hashKey;
                    }
                    $scope.fn.setParentOption(parentOptionValue);
                    $scope.data.menuName = null;
                    $scope.data.actionType = "view";
                    $scope.fn.setIncludeAction(true);
                    $scope.data.actionUrl = null;
                    $scope.data.actionKey = null;
                    $timeout(function() {
                        $scope.data.layer.form.render();
                    });
                });
            },
            /** 编辑菜单 */
            editDialog: function(parentIndex, childIndex) {
                $scope.fn.openDialog("编辑菜单", function() {
                    $("#menuFormHidden").data("pindex", parentIndex);
                    // 判断edit内容是否为二级菜单
                    if (childIndex == undefined) {
                        // 编辑一级菜单默认上级菜单为空
                        $scope.fn.setParentOption("");
                        // 菜单名称为默认名称
                        $scope.data.menuName = $scope.data.menuBtn[parentIndex].name;
                        // 判断是否含有二级菜单
                        if ($scope.data.menuBtn[parentIndex].sub_button.length == 0) {
                            $scope.data.actionType = $scope.data.menuBtn[parentIndex].type;
                            $scope.data.actionUrl = $scope.data.menuBtn[parentIndex].url;
                            $scope.data.actionKey = $scope.data.menuBtn[parentIndex].key;
                            $scope.fn.setIncludeAction(true);
                            $scope.fn.setActionType("view");
                            // 可以修改设置动作按钮
                            $("input[name='includeAction']").prop("disabled", false);
                        } else {
                            $scope.fn.setIncludeAction(false);
                            // 不允许修改设置动作按钮
                            $("input[name='includeAction']").prop("disabled", true);
                        }
                    } else {
                        $("#menuFormHidden").data("cindex", childIndex);
                        // 菜单名称为默认名称
                        $scope.data.menuName = $scope.data.menuBtn[parentIndex].sub_button[childIndex].name;
                        $scope.data.actionType = $scope.data.menuBtn[parentIndex].sub_button[childIndex].type;
                        $scope.data.actionUrl = $scope.data.menuBtn[parentIndex].sub_button[childIndex].url;
                        $scope.data.actionKey = $scope.data.menuBtn[parentIndex].sub_button[childIndex].key;
                        $scope.fn.setIncludeAction(true);
                        $scope.fn.setParentOption($scope.data.menuBtn[parentIndex].$$hashKey);
                        // 不允许修改设置动作按钮
                        $("input[name='includeAction']").prop("disabled", true);
                    }
                    // 编辑菜单统一上菜单不允许修改
                    $("select[name='parentMenu']").prop("disabled", true);
                    // 渲染
                    $scope.data.layer.form.render();
                });
            },
            /** 打开对话框 */
            openDialog: function(name, before) {
                layer.open({
                    type: 1,
                    title: [name,
                        'text-align:center;font-size:16px;background:#fff;'],
                    area: ['700px'],
                    offset: 150,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 99,
                    scrolling: 'no',
                    content: $('div[name="dialog-content"]'),
                    closeBtn: 1,
                    success: function() {
                        before();
                    },
                    end: function() {
                        // 还原默认值
                        $("#menuFormHidden").data('pindex', -1);
                        $("#menuFormHidden").data('cindex', -1);
                        
                        // 消除disabled
                        $("select[name='parentMenu']").prop("disabled", false);
                        $("input[name='includeAction']").prop("disabled", false);
                        $scope.data.layer.form.render();    // 渲染作用到form
                    }
                });
            },
            /** 右上角保存按钮 */
            saveMenu: function() {
                layer.confirm("是否确定对菜单进行修改？", {icon: 7, title:'提示'}, function(index) {
                    $scope.service.updateMenuBtn(JSON.stringify($scope.data.menuBtn), function() {
                        layer.closeAll();
                    });
                });
            },
            /** 保存按钮 */
            saveOrEditMenu: function() {
                var name = $scope.data.menuName;    // 名字
                var type = $scope.data.actionType;  // 类型
                var url = $scope.data.actionUrl;   // 网页跳转
                var key = $scope.data.actionKey;   // 点击事件
                
                // 如果没有includeAction就取消url与key的值
                if (!$scope.data.includeAction) {
                    type = undefined;
                    url = undefined;
                    key = undefined;
                }
                // 数据校验
                if (name == undefined || name.length == 0) {
                    layer.msg("菜单名称不能为空");
                    $("input[ng-model='menuName']").focus();
                    return;
                }
                if (type == undefined && name.length > 4) {
                    layer.msg("一级菜单名称不能超过4个字符");
                    $("input[ng-model='menuName']").focus();
                    return;
                }
                if (type != undefined && name.length > 7) {
                    layer.msg("二级菜单名称超过7个字符的会以省略号显示");
                    // 不禁止用户继续操作，只做提示
                }
                if ("view" == type && (url == undefined || url.length == 0)) {
                    layer.msg("请填写网页跳转");
                    $("input[ng-model='actionUrl']").focus();
                    return;
                }
                if ("click" == type && (key == undefined || key.length == 0)) {
                    layer.msg("请填写点击事件");
                    $("input[ng-model='actionKey']").focus();
                    return;
                }
                
                var pindex = $("#menuFormHidden").data('pindex');
                var cindex = $("#menuFormHidden").data('cindex');
                if (pindex != -1) {
                    // 进行编辑操作
                    if (cindex == -1) {
                        $scope.data.menuBtn[pindex].name = name;
                        $scope.data.menuBtn[pindex].type = type;
                        $scope.data.menuBtn[pindex].url = url;
                        $scope.data.menuBtn[pindex].key = key;
                    } else {
                        $scope.data.menuBtn[pindex].sub_button[cindex].name = name;
                        $scope.data.menuBtn[pindex].sub_button[cindex].type = type;
                        $scope.data.menuBtn[pindex].sub_button[cindex].url = url;
                        $scope.data.menuBtn[pindex].sub_button[cindex].key = key;
                    }
                    layer.closeAll();
                    return;
                }
                
                var subBtn = {
                    type : type,
                    name : name,
                    sub_button : []
                }
                if ("view" == type) {
                    subBtn.url = url;
                }
                if ("click" == type) {
                    subBtn.key = key;
                }
                var menuBtn = $scope.data.menuBtn;
                var parentBtn = $scope.data.parentOption;
                
                if (parentBtn) {
                    if (parentBtn.type) {
                        layer.msg("该菜单已设置了动作，不能继续添加子菜单");
                        return;
                    }
                    if (parentBtn.sub_button.length >= 5) {
                        layer.msg("该菜单最多含有5个子菜单，不能继续添加");
                        return;
                    }
                    // 二级菜单
                    parentBtn.sub_button.push(subBtn);
                    for(var i = 0; i < menuBtn.length; i++) {
                        if (menuBtn[i] == $scope.data.parentOption) {
                            $scope.data.menuBtn[i] = parentBtn;
                            break;
                        }
                    }
                } else {
                    if (menuBtn.length >= 3) {
                        layer.msg("顶级菜单最多只能有3个");
                        return;
                    }
                    $scope.data.menuBtn.push(subBtn);
                }
                layer.closeAll();
            },
            /** 删除菜单 */
            deletMenu: function(parentIndex, childElement) {
                var menuBtn = $scope.data.menuBtn;
                if (childElement == undefined) {
                    // 删除一级菜单
                    var newArr = [];
                    for(var i = 0; i < menuBtn.length; i++) {
                        if (i != parentIndex) {
                            newArr.push(menuBtn[i]);
                        }
                    }
                    $scope.data.menuBtn = newArr;
                } else {
                    // 删除二级菜单
                    var newArr = [];
                    var sub_button = menuBtn[parentIndex].sub_button;
                    for(var i = 0; i < sub_button.length; i++) {
                        if (sub_button[i] != childElement) {
                            newArr.push(sub_button[i]);
                        }
                    }
                    $scope.data.menuBtn[parentIndex].sub_button = newArr;
                    // 如果length == 0，就把加号修改为减号
                    if (newArr.length == 0) {
                        $("img.toggleBtn").eq(parentIndex).attr("src", '/static/images/open.png');
                    }
                }
            },
            /** 动作类型actionType值，value可以缺省默认为view */
            setActionType: function(value) {
                if (value == undefined) {value = 'view'}    // 默认view
                $scope.data.actionType = value;
            },
            /** 设置动作 */
            setIncludeAction: function(value) {
                var $includeAction = $("input[name=includeAction]");
                
                // 用两位数表示开关状态，第一位表示即将变更的状态，第二位表示目前的状态
                // 列举为 00 01 10 11
                // 为了状态同调，需要纠正的状态有 01 10
                if (value != $includeAction.is(":checked")) {
                    // 模拟点击事件触发监听
                    $includeAction.next("div.layui-form-switch")[0].click();
                }
                $scope.data.includeAction = value;
            },
            /** 设置上级菜单 */
            setParentOption: function(value) {
                var $ddList = $("select[name='parentMenu']").next("div.layui-form-select").find("dd");
                // 遍历上级菜单标签 触发点击事件
                for (var i = 0; i < $ddList.length; i++) {
                    var $this = $($ddList[i]);
                    if ($this.attr("lay-value") == value) {
                        $this.click();
                        break;
                    }
                }
            },
        }
        
        $scope.service = {
            listMenuBtn: function(forceCheck) {
                if (forceCheck || $scope.data.menuBtn.length == 0) {
                    var url = "/weixin/menu";
                    http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                        $scope.$apply(function() {
                            $scope.data.menuBtn = result.menu.button;
                        });
                        $scope.data.layer.form.render();
                    });
                }
            },
            updateMenuBtn: function(json, callBack) {
                var url = "/weixin/menu";
                http.ajax.post(true, false, url, json, http.ajax.CONTENT_TYPE_2, callBack);
            }
        }
    }]);
}());