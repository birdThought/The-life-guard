(function() {
    angular.module('Controllers', [])
    .controller('serveController', ['$scope', function($scope) {
        
        $scope.data = {
            serveList: [],  // 服务列表
            serveTypeList: [  // 服务类型
                {v:1,n:"健康养生"},
                {v:2,n:"慢病康复"},
                {v:3,n:"减肥塑体"},
                {v:4,n:"居家养老"},
                {v:5,n:"癌症康复"},
            ],
            serve: {
                id: null,
                name: null,
                code: null,
                profitShare: null,
                serveType: null,
                classify: null,
                chargeMode: [],
                about: null,
                projectType: null
            },
            layer: {
                form: null,
                dialogIndex: null
            }
        }
        
        $scope.fn = {
            init: function() {
                layui.use(['form'], function() {
                    var form = layui.form;
                    
                    // 监听服务类型
                    form.on('select(serveType)', function(data) {
                        var value = data.value;
                        var serveType = null;
                        if (value.startsWith("number:")) {
                            serveType = value.split(":")[1];
                        }
                        $scope.$evalAsync(function() {
                            $scope.data.serve.serveType = parseInt(serveType);
                        });
                    });
                    
                    // 监听付款方式
                    form.on('checkbox(chargeMode)', function(data) {
                        var value = parseInt(data.value);
                        var checked = data.elem.checked;
                        if (checked) {
                            $scope.data.serve.chargeMode.push(value);
                        } else {
                            var newArr = new Array();
                            for (var i = 0; i <$scope.data.serve.chargeMode.length; i++) {
                                if ($scope.data.serve.chargeMode[i] != value) {
                                    newArr.push($scope.data.serve.chargeMode[i]);
                                }
                            }
                            $scope.data.serve.chargeMode = newArr;
                        }
                    });
                    
                    // 监听服务类型
                    form.on('radio(projectType)', function(data) {
                        $scope.data.serve.projectType = parseInt(data.value);
                    });
                    
                    form.render();
                    $scope.data.layer.form = form;
                    $scope.service.listServe();
                })
            },
            addNew: function() {
                $scope.fn.openDialog("添加服务", function() {
                    
                });
            },
            edit: function(serve) {
                $scope.fn.openDialog("编辑服务", function() {
                    $scope.data.serve = serve;
                    $('select[ng-model="data.serve.serveType"]').next("div.layui-form-select").find("dd[lay-value='number:" + $scope.data.serve.serveType + "']").click();
                    var chargeMode = $scope.data.serve.chargeMode.split(",");
                    for (var i = 0; i < chargeMode.length; i++) {
                        $("#serveDialog input[type='checkbox'][value='" + parseInt(chargeMode[i]) + "']").prop("checked", true);
                    }
                    $("input[name='projectType'][value='" + parseInt($scope.data.serve.projectType) + "']").prop("checked", true);
                    $scope.data.layer.form.render();
                });
            },
            openDialog: function(title, before) {
                $scope.data.layer.dialogIndex = layer.open({
                    type: 1,
                    title: [title, 'text-align:center;font-size:20px;background:#0398fc;color:#FFF;'],
                    area: ['750px', '700px'],
                    offset: 150,
                    moveType: 1,
                    scrollbar: false,
                    zIndex: 99,
                    content: $('#serveDialog'),
                    success: function() {
                        before();
                    },
                    end: function() {
                        // 重置
                        $scope.data.serve = {
                            id: null,
                            name: null,
                            code: null,
                            profitShare: null,
                            serveType: null,
                            classify: null,
                            chargeMode: [],
                            about: null,
                            projectType: null
                        }
                        $('select[ng-model="data.serve.serveType"]').next("div.layui-form-select").find("dd[lay-value='']").click();
                        $('#serveDialog input[type="checkbox"]').prop("checked", false);
                        $("input[name='projectType']").prop("checked", false);
                        $scope.data.layer.form.render();
                    }
                });
            },
            save: function() {
                var name = $scope.data.serve.name;
                var code = $scope.data.serve.code;
                var serveType = $scope.data.serve.serveType;
                var profitShare = $scope.data.serve.profitShare;
                var classify = $scope.data.serve.classify;
                var chargeModeArr = $scope.data.serve.chargeMode;
                var about = $scope.data.serve.about;
                var projectType = $scope.data.serve.projectType;
                
                if (name == null || name.length == 0) {
                    layer.msg("请填写服务名称");
                    return;
                }
                if (name.length > 20) {
                    layer.msg("服务名称长度不能超过20个字符");
                    return;
                }
                
                if (code == null || code.length == 0) {
                    layer.msg("请填写服务代码");
                    return;
                }
                if (code.length > 3) {
                    layer.msg("服务代码长度不能超过3个字符");
                    return;
                }
                
                if (serveType == null || serveType.length == 0) {
                    layer.msg("请选择服务类型");
                    return;
                }
                
                if (profitShare == null || profitShare.length == 0) {
                    layer.msg("请填写平台分成");
                    return;
                }
                
                if (classify == null || classify.length == 0) {
                    layer.msg("请填写下级分类");
                    return;
                }
                
                if (chargeModeArr.length == 0) {
                    layer.msg("请选择收费方式");
                    return;
                }
                
                if (about == null || about.length == 0) {
                    layer.msg("请填写服务介绍");
                    return;
                }
                if (about.length > 70) {
                    layer.msg("服务介绍长度不能超过70个字符");
                    return;
                }
                
                var chargeMode = "";
                for (var i = 0; i < chargeModeArr.length; i++) {
                    chargeMode += chargeModeArr[i] + ","
                }
                chargeMode = chargeMode.substring(0, chargeMode.length - 1);
                
                if (projectType == null) {
                    layer.msg("请选择项目类型");
                    return;
                }
                
                var data = {
                    name: name,
                    code: code,
                    profitShare: profitShare,
                    serveType: serveType,
                    classify: classify,
                    chargeMode: chargeMode,
                    about: about,
                    projectType: projectType
                }
                
                var id = $scope.data.serve.id;
                if (id == null) {
                    $scope.service.addServe(JSON.stringify(data));
                } else {
                    $scope.service.modifyServe(id, JSON.stringify(data));
                }
            },
            delConfirm: function(data) {
                layer.confirm("你确定删除 [" + data.name + "] 服务?", {icon:2, title:"警告"}, function(index) {
                    $scope.service.deleteServe(data.id);
                    layer.close(index);
                });
            }
        }
        
        $scope.service = {
            listServe: function() {
                var url = "/serve/serves";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        $scope.data.serveList = result.data;
                    });
                });
            },
            addServe: function(data) {
                var url = "/serve";
                http.ajax.post(true, false, url, data, http.ajax.CONTENT_TYPE_2, function(result) {
                    $scope.service.listServe();
                    layer.close($scope.data.layer.dialogIndex);
                });
            },
            modifyServe: function(id, data) {
                var url = "/serve/" + id;
                http.ajax.put(url, data, http.ajax.CONTENT_TYPE_2, function(result) {
                    $scope.service.listServe();
                    layer.close($scope.data.layer.dialogIndex);
                });
            },
            deleteServe: function(id) {
                var url = "/serve/" + id;
                http.ajax.del(url, function() {
                    $scope.service.listServe();
                });
            }
        }
        
        $scope.clearNoDigit = function(obj, attr){
            obj[attr] = obj[attr].replace(/\D/g,"");
        }
    }]);
}());