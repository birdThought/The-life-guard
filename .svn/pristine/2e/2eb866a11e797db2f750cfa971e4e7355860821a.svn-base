/**
 * @Author wenxian.cai
 * @Date 2017/5/22 15:56
 */

var publishService = {};

/**初始化线上服务*/
publishService.initOnlineService = function () {
    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';
    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.serve-image-show-1').attr('src', results.obj);
        $('.serve-image-hidden-1').val(results.obj);
    });
};
/**初始化上门服务*/
publishService.initVisitService = function () {
    $(".lg-img img").not("img:first").css("display","none");
    $(".sm-btn li").click(function(event) {
        $(this).addClass("on").siblings().removeClass('on');
        $(".lg-img img").css("display","none").eq($(this).index()).css("display","block");
    });

    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';

    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.serve-image-show-1').attr('src', results.obj);
        $('.serve-image-hidden-1').val(results.obj);
        $('.serve-image-hidden-1').change();
    });
    //绑定图片2
    element = '.path-2';
    lay.uploadFile(url, method, element, function (results) {
        $('.serve-image-show-2').attr('src', results.obj);
        $('.serve-image-hidden-2').val(results.obj);
        $('.serve-image-hidden-2').change();
    });
    //绑定图片3
    element = '.path-3';
    lay.uploadFile(url, method, element, function (results) {
        $('.serve-image-show-3').attr('src', results.obj);
        $('.serve-image-hidden-3').val(results.obj);
        $('.serve-image-hidden-3').change();
    });
    //绑定图片4
    element = '.path-4';
    lay.uploadFile(url, method, element, function (results) {
        $('.serve-image-show-4').attr('src', results.obj);
        $('.serve-image-hidden-4').val(results.obj);
        $('.serve-image-hidden-4').change();
    });
}

/**vm实例*/
publishService.vm = new Vue({
    el: '.vue-content',
    data: {
        result: null,
        currentServe: null,
        orgUsers: [],
        selectOrgUserId: [],
        selectOrgUserInfo: [],
        currentSecondaryServe: null,
        serveType: null,
        lessonWeek: [],
        lessonTime: [],
        combo: {},  //套餐
        combos: [], //套餐集合
        comboStatus: null, //状态 'add/delete'
        currentCombo: {},
        serveImage: null,
        searchOfOrgUser: null, //服务师搜索框
        comboPrice: null,
        priceReg: /^([0-9][{0-9}|.]*)$/,
        unitReg: /^([1-9]*)$/,
        projectData: null,  //传输到后台的服务data
        projectType: null,
        chargeMode: 1,   //服务师收费模式
        isModify: false ,    //是否为编辑模式
        type: null,
        project: {},

    },
    methods: {
        /**点击一级服务*/
        clickServe: function (id, name) {
            this.currentServe = {
                id: id,
                name: name
            }
        },
        /**点击二级服务*/
        clickSecondaryServe: function (id, name) {
            this.currentSecondaryServe = {
                id: id,
                name: name
            }
        },
        /**获取服务师列表*/
        listOrgUser: function () {
            if ( publishService.vm.orgUsers.length != 0) {
                return;
            }
            $.ajax({
                async: true,
                cache: false,
                type: 'GET',
                url: '/org/employee/list-employee', /*/org/service/listOrgUser*/
                /*data: data,*/
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                beforeSend: function () {
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    if (result.success) {

                        publishService.vm.orgUsers = result.obj.data;

                    }
                }
            })
        },
        /**弹出添加服务师窗口*/
        popupOrgUserDialog: function () {
            this.listOrgUser();
            layer.open({
                closeBtn: 1,
                area: ['840px', '700'],
                shadeClose: true,
                // offset: ['200px', '200px'],
                type: 1,
                title: "添加服务师",
                offset: ['20%', '30%'],
                zIndex: 999,
                resize : false,
                content: $('.org-user-list'),
                cancel: function () {
                    console.log('inter cancel')
                }
            });

        },
        /**全选服务师*/
        selectAllOrgUser: function (event) {
            var bool = $(event.currentTarget).hasClass('on');
            this.selectOrgUserId = [];
            if (bool) {
                $(event.currentTarget).removeClass('on');
                return;
            }
            for (var i in this.orgUsers) {
                if (this.orgUsers[i].display || this.orgUsers[i].display == null) {
                    this.selectOrgUserId.push(this.orgUsers[i].id);
                }
            }
            $(event.currentTarget).addClass('on');
        },
        /**添加服务师*/
        addOrgUser: function () {
            var temp = [];
            for (var i in publishService.vm.selectOrgUserId) {
                for (var j in publishService.vm.orgUsers) {
                    if (publishService.vm.selectOrgUserId[i] == publishService.vm.orgUsers[j].id) {
                        temp.push(publishService.vm.orgUsers[j]);
                    }
                }
            }
            publishService.vm.selectOrgUserInfo = [];
            publishService.vm.selectOrgUserInfo = Object.assign([], publishService.vm.selectOrgUserInfo, temp)
            layer.closeAll('page');
        },
        /**移除服务师*/
        deleteSelectOrgUser: function (orgUserId) {
            for (var i in this.selectOrgUserId) {
                if (this.selectOrgUserId[i] == orgUserId) {
                    this.selectOrgUserId.splice(i, 1);
                }
            }
            for (var i in this.selectOrgUserInfo) {
                if (this.selectOrgUserInfo[i].id == orgUserId) {
                    this.selectOrgUserInfo.splice(i, 1);
                }
            }
        },
        /**预览项目*/
        previewService: function () {
            window.location.href = '/org/service/preview';
        },
        /**保存项目*/
        saveService: function (isPublish) {
            var bool = true;
            publishService.vm.addServiceItem(isPublish, function (message) {
                layer.msg(message, {'offset': '50%'});
                bool = false;
                return;
            });
            if (bool) {
                var msg = isPublish == true ? '确定发布该项目?' : '确定保存该项目?';
                layer.confirm(msg,{'offset': '50%'}, function () {
                    publishService.vm.addService();
                });

            }
        },
        /** 修改项目*/
        modifyService: function () {

            var bool = true;
            publishService.vm.addServiceItem(null, function (message) {
                layer.msg(message, {'offset': '50%'});
                bool = false;
                return;
            });
            
            if (bool) {
                var msg = '确定保存修改该项目?';
                layer.confirm(msg,{'offset': '50%'}, function () {
                    publishService.vm.updateService();
                });

            }
        },
        addServiceItem: function (isPublish, callback) {
            var data = {};
            var error = null;
            switch (this.projectType) {
                case 'consult':
                    var service = {};
                    service.name = this.currentServe.name + '[' + this.currentSecondaryServe.name + ']';
                    service.image = $('.serve-image-hidden-1').val();
                    service.serveSecondaryId = this.currentSecondaryServe.id;
                    service.serveId = this.currentSecondaryServe.id;
                    var orgUsers = [];
                    var orgUser = {};
                    for (var i in this.selectOrgUserInfo) {
                        orgUser.orgUserId = this.selectOrgUserInfo[i].id;
                        orgUser.realName = this.selectOrgUserInfo[i].realName;
                        orgUser.price = $('#org-user-price-' + this.selectOrgUserInfo[i].id).val();
                        orgUser.monthPrice = $('#org-user-monthPrice-' + this.selectOrgUserInfo[i].id).val();
                        orgUser.yearPrice = $('#org-user-yearPrice-' + this.selectOrgUserInfo[i].id).val();
                        if (orgUser.price == '' || orgUser.price == null) {
                            layer.msg('请填写服务师咨询按次价格')
                            return;
                        }
                        if (orgUser.monthPrice == '' || orgUser.monthPrice == null) {
                            layer.msg('请填写服务师咨询按月价格')
                            return;
                        }
                        if (orgUser.yearPrice == '' || orgUser.yearPrice == null) {
                            layer.msg('请填写服务师咨询按年价格')
                            return;
                        }
                        if (!this.priceReg.test(orgUser.price)) {
                            layer.msg('服务师咨询价格不合法')
                            return;
                        }
                        orgUsers.push(orgUser);
                        orgUser = {};
                    }
                    service.orgUser = orgUsers;
                    service.status = isPublish == true ? 2 : 1;
                    if (service.image == '') {
                        error = '请添加产品图片';
                        break;
                    }
                    if (service.orgUser.length < 1) {
                        error = '至少添加一个服务师';
                        break;
                    }
                    service.chargeMode = this.chargeMode;
                    data = service;
                    break;
                case 'lesson':
                    var lesson = $('.lesson-form').serializeJSON();

                    /*lesson.name = this.currentServe.name + '[' + $('input[name = "serviceName"]').val() + ']';*/
                    lesson.name = $('input[name = "serviceName"]').val() + "[" + this.currentSecondaryServe.name + ']';
                    lesson.photo = $('.serve-image-hidden-1').val();
                    debugger;
                    var courseTime = this.changeLessonTime(this.lessonTime);
                    var orgUsers = [];
                    var orgUser = {};
                    for (var i in this.selectOrgUserInfo) {
                        orgUser.orgUserId = this.selectOrgUserInfo[i].id;
                        orgUser.realName = this.selectOrgUserInfo[i].realName;
                        orgUser.userCode = this.selectOrgUserInfo[i].userCode;
                        orgUsers.push(orgUser);
                        orgUser = {};
                    }
                    var service = {
                        name: lesson.name,
                        serveId: this.currentSecondaryServe.id,
                        serveSecondaryId: this.currentSecondaryServe.id,
                        price: lesson.price,
                        introduce: lesson.introduce,
                        startDate: lesson.startDate,
                        endDate: lesson.endDate,
                        orgUser: orgUsers,
                        lessonTime: courseTime,
                        image: lesson.photo
                    };
                    service.status = isPublish == true ? 2 : 1;
                    data = service;
                    // data.lesson = lesson;
                    // data.courseTime = courseTime;
                    // data.service = service;
                    // data.orgUsers = orgUsers;
                    if (data.image == '') {
                        error = '请添加产品图片';
                        break;
                    }
                    if ($.trim(lesson.serviceName) == '') {
                        error = '请输入课堂名称';
                        break;
                    }
                    if ($.trim(lesson.serviceName).length > 19) {
                        error = '课堂名称超出限制';
                        break;
                    }
                    if (data.lessonTime.length < 1) {
                        error = '请添加开课时间';
                        break;
                    }
                    if ($.trim(data.introduce) == '') {
                        error = '请添加课堂简介';
                        break;
                    }
                    if ($.trim(data.introduce).length > 250) {
                        error = '课堂简介字数超出限制';
                        break;
                    }
                    if (data.orgUser.length < 1) {
                        error = '至少添加一个服务师';
                        break;
                    }
                    break;
                case 'offline':
                    var media = {
                        pictureOne: $('.serve-image-hidden-1').val() == '' ? null : $('.serve-image-hidden-1').val(),
                        pictureTwo: $('.serve-image-hidden-2').val() == '' ? null : $('.serve-image-hidden-2').val(),
                        pictureThree: $('.serve-image-hidden-3').val() == '' ? null : $('.serve-image-hidden-3').val(),
                        pictureFour: $('.serve-image-hidden-4').val() == '' ? null : $('.serve-image-hidden-4').val()
                    };
                    var orgUsers = [];
                    var orgUser = {};
                    for (var i in this.selectOrgUserInfo) {
                        orgUser.orgUserId = this.selectOrgUserInfo[i].id;
                        orgUsers.push(orgUser);
                        orgUser = {};
                    }
                    var combo = [];
                    var item = {};
                    for (var i in this.combos) {
                        var marketPrice = 0;
                        for (var j in this.combos[i].introduce) {
                            marketPrice += Number(this.combos[i].introduce[j].price);
                            this.combos[i].introduce[j].unit = this.combos[i].introduce[j].unit + "次";
                        }
                        item.introduce = (JSON.stringify(this.combos[i].introduce));
                        item.introduce = item.introduce.toString().replace("次次","次");
                        item.name = this.combos[i].name;
                        item.price = this.combos[i].price;
                        item.marketPrice = marketPrice;

                        combo.push(item);
                        item = {};
                    }

                    var service = {
                        /*name: this.currentServe.name + '[' + $('.service-name').val() + ']',*/
                        name: $('.service-name').val() + "[" + this.currentSecondaryServe.name + ']',
                        userType: $('.user-type').val(),
                        appointment: $('.appointment').val(),
                        introduce: Editor.filterScript(Editor.getContent()),
                        serveId: this.currentSecondaryServe.id,
                        serveSecondaryId: this.currentSecondaryServe.id,
                        media: media,
                        orgUser: orgUsers,
                        combo: combo,
                        status: isPublish == true ? 2 : 1
                    };
                    if (service.media.pictureOne == '' && service.media.pictureTwo == '' &&
                        service.media.pictureThree == '' && service.media.pictureFour == '') {
                        error = '至少添加一张产品图片';
                        break;
                    }
                    if ($.trim(service.userType) == '') {
                        error = '请填写服务适用人群';
                        break;
                    }
                    if ($.trim(service.appointment) == '') {
                        error = '请填写服务预约信息';
                        break;
                    }
                    if ($.trim($('.service-name').val()) == '') {
                        error = '请填写服务名称';
                        break;
                    }
                    if (service.combo.length < 1) {
                        error = '至少添加一个套餐';
                        break;
                    }
                    if (service.orgUser.length < 1) {
                        error = '至少添加一个服务师';
                        break;
                    }
                    if (service.introduce == '' || service.introduce == null) {
                        error = '请填写服务详细介绍';
                        break;
                    }
                    if (service.introduce.length > 1000) {
                        error = '详细描述字数不可超过1000个';
                        break;
                    }
                    data = service;
                    break;
                case 'visit':
                    debugger;
                    var media = {
                        pictureOne: $('.serve-image-hidden-1').val() == '' ? null : $('.serve-image-hidden-1').val(),
                        pictureTwo: $('.serve-image-hidden-2').val() == '' ? null : $('.serve-image-hidden-2').val(),
                        pictureThree: $('.serve-image-hidden-3').val() == '' ? null : $('.serve-image-hidden-3').val(),
                        pictureFour: $('.serve-image-hidden-4').val() == '' ? null : $('.serve-image-hidden-4').val()
                    };
                    var orgUsers = [];
                    var orgUser = {};
                    for (var i in this.selectOrgUserInfo) {
                        orgUser.orgUserId = this.selectOrgUserInfo[i].id;
                        orgUsers.push(orgUser);
                        orgUser = {};
                    }
                    var combo = [];
                    var item = {};
                    for (var i in this.combos) {
                        var marketPrice = 0;
                        for (var j in this.combos[i].introduce) {
                            marketPrice += Number(this.combos[i].introduce[j].price);
                            this.combos[i].introduce[j].unit = this.combos[i].introduce[j].unit + "次";
                        }
                        item.introduce = (JSON.stringify(this.combos[i].introduce));
                        item.introduce = item.introduce.toString().replace("次次","次");
                        item.name = this.combos[i].name;
                        item.price = this.combos[i].price;
                        item.marketPrice = marketPrice;
                        combo.push(item);
                        item = {};
                    }

                    var service = {
                        /*name: this.currentServe.name + '[' + $('.service-name').val() + ']',*/
                        name: $('.service-name').val() + "[" + this.currentSecondaryServe.name + ']',
                        userType: $('.user-type').val(),
                        appointment: $('.appointment').val(),
                        introduce: Editor.filterScript(Editor.getContent()),
                        serveId: this.currentSecondaryServe.id,
                        serveSecondaryId: this.currentSecondaryServe.id,
                        media: media,
                        orgUser: orgUsers,
                        combo: combo,
                        status: isPublish == true ? 2 : 1
                    };
                    if (service.media.pictureOne == '' && service.media.pictureTwo == '' &&
                        service.media.pictureThree == '' && service.media.pictureFour == '') {
                        error = '至少添加一张产品图片';
                        break;
                    }
                    if ($.trim(service.userType) == '') {
                        error = '请填写服务适用人群';
                        break;
                    }
                    if ($.trim(service.appointment) == '') {
                        error = '请填写服务预约信息';
                        break;
                    }
                    if ($.trim($('.service-name').val()) == '') {
                        error = '请填写服务名称';
                        break;
                    }
                    if (service.combo.length < 1) {
                        error = '至少添加一个套餐';
                        break;
                    }
                    if (service.orgUser.length < 1) {
                        error = '至少添加一个服务师';
                        break;
                    }
                    if (service.introduce == '' || service.introduce == null) {
                        error = '请填写服务详细介绍';
                        break;
                    }
                    if (service.introduce.length > 1000) {
                        error = '详细描述字数不可超过1000个';
                        break;
                    }
                    data = service;
                    break;
            }
            if (error != null) {
                if (typeof callback == 'function') {
                    return callback(error);

                }
            }
            this.projectData = data;
        },
        /**添加服务*/
        addService: function () {
            var data = this.projectData;
            var type = this.projectType;
            $.ajax({
                async: true,
                cache: false,
                type: 'POST',
                url: '/org/service/addservice/' + type,
                data: JSON.stringify(data),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend: function () {
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.open({
                        title: ['提示', 'text-align: left'],
                        content: "<p style='text-align:center;'>"+ result.msg +", 2秒后跳回服务项目管理界面...</p>",
                        btn: 0,
                        time: 2000,
                        offset: '200px',
                        shadeClose: true
                    });
                    if (result.success) {
                        setTimeout(function () {
                            window.location.href = '/org/service';
                        }, 2000)
                    }
                }
            })
        },
        /**修改服务*/
        updateService: function () {
            var data = this.projectData;
            var type = this.projectType;
            if (data.image == this.project.image) {
                data.image = null;
            }
            data.status = null;
            data.code = this.project.code;
            $.ajax({
                async: true,
                cache: false,
                type: 'POST',
                url: '/org/service/update-service/' + type,
                data: JSON.stringify(data),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                beforeSend: function () {
                    layer.load();
                },
                success: function (result) {
                    layer.closeAll('loading');
                    layer.open({
                        title: ['提示', 'text-align: left'],
                        content: "<p style='text-align:center;'>"+ result.msg +", 2秒后跳回服务项目管理界面...</p>",
                        btn: 0,
                        time: 2000,
                        offset: '200px',
                        shadeClose: true
                    });
                    if (result.success) {
                        setTimeout(function () {
                            window.location.href = '/org/service';
                        }, 2000)
                    }
                }
            })
        },
        /**点击图片触发事件*/
        clickPic: function (type) {

            switch (type) {
                case 1:
                    $('.path-1').click();

                    break;
                case 2:
                    $('.path-2').click();
                    break;
                case 3:
                    $('.path-3').click();
                    break;
                case 4:
                    $('.path-4').click();
                    break;
            }
        },
        /**弹出添加课堂时间*/
        popupLessonTime: function () {
            layer.open({
                closeBtn: 0,
                shadeClose: true,
                anim: 0,
                type: 1,
                title: " ",
                zIndex: 999,
                offset: ['50%', '50%'],
                content: $('.lesson-dialog'),
            });
        },
        /**添加课堂时间*/
        addLessonTime: function () {

            if (this.lessonWeek.length < 1) {
                layer.msg('请选择开课日期!');
                return;
            }
            var time = {
                time: $('#lesson-date').val() == '' ? '00:00' : $('#lesson-date').val(),
                date: this.lessonWeek
            };
            this.lessonTime.push(time);
            layer.closeAll('page')
        },
        /*删除课堂时间*/
        removeLessonTime: function (event, index) {
            this.lessonTime.splice(index, 1);
        },
        /**显示课堂时间tip*/
        showLessonTimeTip: function (event) {
            layer.tips('双击删除时间', $(event.currentTarget), {
                tips: [1, 'orange'],
                time:2000
            });
        },
        /**转换课堂时间*/
        changeLessonTime: function (value) {
            console.log("origin:" + value);
            debugger;
            var times = new Array();
            for (var i in value) {
                var time = {};
                time.startTime = value[i].time;
                time.weeks = [0, 0, 0, 0, 0, 0, 0];
                for (var j in value[i].date) {
                    time.weeks.splice(value[i].date[j] - 1, 1, 1);
                }
                time.weeks = time.weeks.join('');
                times.push(time);
            }
            return times;
        },
        /**添加免费样式*/
        addFree: function (event) {
            if ($(event.currentTarget).hasClass('on')) {
                $(event.currentTarget).removeClass('on');
                $('.charge input').prop('readonly', false);
                return;
            }
            $(event.currentTarget).addClass('on');
            $('.charge input').val('0');
            $('.charge input').prop('readonly',true);
        },
        /**弹出套餐窗口*/
        popupCombo: function () {
            this.combo = {};
            this.comboStatus = 'add';
            this.comboPrice = '';
            layer.open({
                area:['810px', '500px'],
                closeBtn: 1,
                shadeClose: false,
                anim: 0,
                type: 1,
                title: " ",
                zIndex: 999,
                offset: '20%',
                content: $('.combo-dialog'),
                cancel: function () {
                    publishService.vm.comboPrice = '';
                    $('.set-meal table tr:not(tr:first-child)').remove();
                }
            });
        },
        /**修改套餐*/
        updateCombo: function (type, event) {
            switch (type) {
                case 'add':
                    var remove = 'remove';
                    var html = '<tr class="combo-add-item add">\
                                <td>\
                                <input class = "name" type="text" placeholder="请输入项目名称" >\
                                </td>\
                                <td>\
                                <span>\
                                <input class = "unit" type="text" placeholder="请输入数字" >\
                                </span>\
                                </td>\
                                <td>\
                                <input class = "price" type="text" placeholder="请输入价格" \
                                onkeyup="publishService.vm.filterComboPrice(this)">\
                                </td>\
                                <td>\
                                <img class="cursor-pointer" onclick = "publishService.vm.updateCombo(\'remove\', this)" src="/static/platform/v2.2.0/images/org/org-combo-del.png" alt="">\
                                </td>\
                                </tr>';
                    $('.combo-dialog').find('tbody').append(html);
                    break;
                case 'delete':
                    $(event.currentTarget).parent().parent().addClass('add none');
                    this.combo.introduce.splice($(event.currentTarget).parent().parent().index() - 1, 1);
                    // this.combo.description = Object.assign([], this.combo.description, temp);
                    $(event.currentTarget).parent().parent().remove();
                    break;
                case 'remove':
                    $(event).parent().parent().addClass('add none');
                    $(event).parent().parent().remove();
                    $(event).parent().parent().remove();
                    break;
                default:
                    break;
            }

        },
        /**添加套餐*/
        addCombo: function () {
            var obj = $('.combo-dialog').find('tbody');
            var len = $('.combo-dialog').find('tbody').children().length;

            var temp = [];
            for (var i = 1; i < len; i ++) {
                var comboItem = {
                    name: obj.children().eq(i).find('.name').val(),
                    unit: obj.children().eq(i).find('.unit').val(),
                    price: obj.children().eq(i).find('.price').val(),
                    // marketPrice: obj.children().eq(i).find('.marketPrice').val(),
                }

                if (comboItem.name == '' || comboItem.name == null || comboItem.name == undefined) {
                   layer.msg('请完善套餐信息', {offset: '300px'});
                   return;
                }
                if (comboItem.unit == '' || comboItem.unit == null || comboItem.unit == undefined) {
                    layer.msg('请完善套餐信息', {offset: '300px'});
                    return;
                }
                if (!publishService.vm.unitReg.test(comboItem.unit)) {
                    layer.msg("套餐单位不合法", {offset: '300px'});
                    return;
                }
                if (comboItem.price == '' || comboItem.price == null || comboItem.price == undefined) {
                    layer.msg('请完善套餐信息', {offset: '300px'});
                    return;
                }

                if (!publishService.vm.priceReg.test(comboItem.price)) {
                    layer.msg('套餐价格不合法', {offset: '300px'});
                    return;
                }
                if (parseInt(comboItem.price) == 0) {
                    layer.msg('套餐价格不能免费', {offset: '300px'});
                    return;
                }

                /*if (comboItem.marketPrice == '' || comboItem.marketPrice == null || comboItem.marketPrice == undefined) {
                    layer.msg('请完善套餐信息', {offset: '300px'});
                    return;
                }*/

                temp.push(comboItem);
            }
            if ($('.total-combo-price').val() == '') {
                layer.msg('请输入套餐总价格', {offset: '300px'});
                return;
            }
            if (!publishService.vm.priceReg.test($('.total-combo-price').val())) {
                layer.msg('套餐价格不合法', {offset: '300px'});
                return;
            }

            if (temp.length == 0) {
                layer.msg('至少添加一个项目', {offset: '300px'});
                return;
            }
            debugger;
            this.combo = [];
            this.combo.introduce = Object.assign([], this.combo.introduce, temp);
            this.combo.price = this.comboPrice;
            this.$nextTick(function () {
                var obj = $('.combo-dialog').find('tbody');
                var len = $('.combo-dialog').find('tbody').children().length;
                for (var i = 0; i < len; i ++) {
                    if (obj.children().eq(i).hasClass('add')) {
                        obj.children().eq(i).remove();
                        i --;
                    }
                }
            })
            if (this.comboStatus == 'add') {
                this.combo.name = '套餐' + (Number(this.combos.length) + 1);
                this.combos.push(this.combo);
            } else {
                for (var i in this.combos) {
                    if(this.combos[i].name == this.currentCombo.name) {
                        this.combos[i].introduce = this.combo.introduce;
                        this.combos[i].price = this.combo.price;
                        break;
                    }
                }
            }

            this.combo = {};
            this.comboPrice = '';
            layer.closeAll('page');
        },
        /**编辑套餐窗口*/
        editCombo: function (index) {
            // $('.combo-dialog table tr:not(:first-child)').empty();
            // this.combo = this.combos[index];
            var introduce = [];
            for (var i in this.combos[index].introduce) {
                var de = {
                    name: this.combos[index].introduce[i].name,
                    price: this.combos[index].introduce[i].price,
                    unit: this.combos[index].introduce[i].unit
                }
                introduce.push(de);
            }
            this.combo = {
                name: this.combos[index].name,
                price: this.combos[index].price,
                introduce: introduce
            }
            this.currentCombo = this.combos[index];
            this.comboStatus = 'update';
            this.comboPrice = this.combo.price;
            layer.open({
                area:['810px', '500px'],
                closeBtn: 1,
                shadeClose: false,
                anim: 0,
                type: 1,
                title: " ",
                zIndex: 999,
                offset: '20%',
                content: $('.combo-dialog'),
                cancel: function () {
                     $('.set-meal table tr.add').remove();
                }
            });
        },
        /**删除套餐*/
        deleteCombo: function (event, $this) {
            (event).stopPropagation();  //阻止冒泡
            layer.confirm('确认删除套餐？', {'offset': '50%'}, function () {
                publishService.vm.combos.splice($($this).parent().index(), 1);
                layer.closeAll();
            });
        },
        /**显示产品图片tip*/
        showPicTip: function (event, type) {
            if ($('.serve-image-hidden-' + type).val() == null ||
                $('.serve-image-hidden-' + type).val() == '') {
                return;
            }
            layer.tips('单击修改图片，双击删除图片', $(event.currentTarget), {
                tips: [1, 'orange'],
                time:2000
            });
        },
        /**显示移除图片图标*/
        showDeleteTip: function (event, type) {
            $('.sm-btn li:nth-of-type('+type+')').find('img:nth-of-type(2)').css('display', 'block');
        },
        /**删除产品图片*/
        deleteImage: function (event, type) {

            var $obj = $(event.currentTarget);
            $obj.css('display', 'none');
            $('.serve-image-show-' + type).attr('src', '/static/platform/v2.2.0/images/register/businessPic.png');
            $('.serve-image-hidden-' + type).val('');

        },
        filterComboPrice: function ($this) {
            if ($($this).val() == '') {
                return;
            }
            var reg = /^([0-9][{0-9}|.]*)$/;
            if (!reg.test($($this).val())) {
                $($this).val('');
            }
        },


    },
    computed: {
        
    },
    watch: {
        /**监听服务类型列表渲染完毕*/
        serveType: function () {
            this.$nextTick(function () {
                /**服务选择事件*/
                $(".tab-content dl").not("dl:first").css("display","none");
                $(".tab-content dl dd").on("click",function() {
                    $(this).addClass('current').siblings().removeClass('current');
                });
                $(".tab-nav dd").on("click",function(){
                    $(this).addClass('current').siblings().removeClass('current');
                    $(".tab-content dl").css("display","none").eq($(this).index()).css("display","block");
                    $(".tab-content").find("dl").eq($(this).index()).find('dd:first').click();
                });

                $(".tab-nav").find('dd').eq(0).click();

            });


        },
        /**监控当前服务类型*/
        projectType: function () {
            if (this.projectType == 'consult' || this.projectType == 'lesson') {
                if (this.projectType == 'lesson') {
                    $('.class-pro').css('display', 'block');
                } else {
                    $('.class-pro').css('display', 'none');
                }
            }
            this.$nextTick(function () {
                if (publishService.vm.projectType == 'visit' || publishService.vm.projectType == 'offline') {
                    $(".main-content").css('height', '2500px')
                    Editor.init();
                }
                common.addBorder();

            });
        },
        /**监听选择星期*/
        lessonWeek: function () {
            // console.log('week:' + $('#lesson-date').val(), this.lessonWeek);
        },
        selectOrgUserId: function () {
            // console.log('selectOrgUserId:', this.selectOrgUserId)
            if (this.projectType == 'lesson') {
                if (this.selectOrgUserId.length > 1) {
                    this.selectOrgUserId.splice(0, 1);
                }

            }
        },
        selectOrgUserInfo: function () {
            this.$nextTick(function () {
                if (publishService.vm.selectOrgUserInfo.length < 1) {
                    return;
                }
                var ul=document.getElementById("ser-con");
                var lis=ul.getElementsByTagName("li");
                var clickBtn=document.getElementById("clickBtn");
                var pre=document.getElementById("pre");
                var next=document.getElementById("next");
                var flag=0;
                var i = 2;
                $('.org-user-border').eq(i).css('border', '0')
                if(lis.length>3){
                    clickBtn.style.display="block";
                    next.onclick=function(){
                        flag -=1;
                        if(Math.abs(flag)>lis.length-3){
                            flag +=1;
                            return false;
                        }else{
                            ul.style.left=flag*(312)+"px";
                        }
                        $('.org-user-border').eq(i).css('border-right', '1px solid #9a9a9a')
                        i += 1;
                        $('.org-user-border').eq(i).css('border', '0')
                    }
                    pre.onclick=function(){
                        flag+=1;
                        if(flag>0){
                            flag -=1;
                            return false;
                        }else{
                            ul.style.left=flag*(312)+"px";
                        }
                        i -= 1;
                        $('.org-user-border').eq(i).css('border', '0')
                    }
                }else{
                    clickBtn.style.display="none";
                }



            })
        },
        searchOfOrgUser: function () {

            for (var m in this.orgUsers) {
                if (this.orgUsers[m].realName.indexOf(this.searchOfOrgUser)<0) {
                    this.orgUsers[m].display = false;
                } else {
                    this.orgUsers[m].display = true;
                }
            }
        },
        /**套餐价格过滤*/
        comboPrice: function () {
            console.log('inter')
        },
        project: function () {
            var vm = publishService.vm;
            this.$nextTick(function () {
                if ($.isEmptyObject(vm.project)) {
                    return;
                }
                //一级服务处理
                // if (publishService.vm.isModify) {
                //     switch (publishService.vm.projectType.type) {
                //         case '1':
                //             break;
                //         case '2':
                //             break;
                //         case '4':
                //             $(".tab-nav dd[value='4']").click();
                //             $(".tab-nav dd").unbind();  //移除一级服务事件
                //             break;
                //         case '5':
                //             break;
                //     }
                // }
                //二级服务处理
                var arr = vm.project.name.split('[');
                var type = (arr[arr.length - 1]).replace(']', '');
                $('.tab-content dl').each(function (index) {
                   $(this).children().each(function () {
                            if ($(this).attr("value") == vm.project.serveId) {
                                $(".tab-nav").find('dd').eq(index).click();
                                $(this).click();
                            }
                   });
                });
                //课堂时间处理
                for (var i in vm.project.lessonTime) {
                    vm.lessonTime[i] = {};
                    var temp = new Array();
                    var arr = vm.project.lessonTime[i].weeks.split('');
                    for (var j = 0; j < arr.length; j ++) {
                        arr[j] = Number(arr[j]);
                        if (arr[j] == 1) {
                            temp.push(j);
                        }
                    }
                    vm.lessonTime[i].date = temp;
                    vm.lessonTime[i].time = vm.project.lessonTime[i].startTime;
                }
                //价格处理
                if (vm.project.price == 0) {
                    $('.free-btn').click();
                }
                //服务师处理
                for (var i in vm.selectOrgUserInfo) {
                    vm.selectOrgUserId.push(vm.selectOrgUserInfo[i].orgUserId);
                }
                //价格处理
                if (vm.project.chargeMode != null) {
                    vm.chargeMode = vm.project.chargeMode;
                }
                //套餐处理
                if (vm.project.combo != null) {
                    vm.combos = vm.project.combo;
                }
                //详细介绍处理
                Editor.init();
                Editor.setHTMLContent(vm.project.serviceDescription);

                common.addBorder();
            })
        }
    },
    filters: {
        changeWeek: function (value) {
            var msg = null;
            switch (value) {
                case 1:
                    msg = '星期一';
                    break;
                case 2:
                    msg = '星期二';
                    break;
                case 3:
                    msg = '星期三';
                    break;
                case 4:
                    msg = '星期四';
                    break;
                case 5:
                    msg = '星期五';
                    break;
                case 6:
                    msg = '星期六';
                    break;
                case 7:
                    msg = '星期日';
                    break;
            }
            return msg;
        },
        filterPrice: function (value) {
            var reg = /^([1-9][0-9]*)$/;
            if (!reg.test(value)) {
               return 0;
            }
        },
        subStr: function (value, type) {
            switch (type) {
                case 1: //服务名称截取
                    var arr = value.split('[');
                    arr.splice(arr.length - 1, 1);
                    return arr.join('[');
                case 2: //套餐次数截取
                    var arr = value.split('');
                    if (arr.length > 1) {
                        arr.splice(arr.length - 1, 1);
                        return arr.join('');
                    }
                    return value;
            }
        },
        date: function (value, fmt) {
            return (new Date(value)).Format(fmt);
        }

    }
});

/**编辑套餐--移除项目*/
/*function updateCombo(obj) {
    $(obj).parent().parent().addClass('add none');
}*/
/**富文本编辑器*/
var Editor = {
    uEditor: null,
    init: function (data) {
        this.uEditor = UE.getEditor('editor', {
            toolbars: [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'strikethrough',
                'horizontal', 'removeformat', '|', 'forecolor',
                'backcolor', 'insertorderedlist', 'insertunorderedlist',
                'selectall', '|', 'rowspacingtop', 'rowspacingbottom',
                'lineheight', '|', 'customstyle', 'paragraph',
                'fontfamily', 'fontsize', '|', 'directionalityltr',
                'directionalityrtl', 'indent', '|', 'justifyleft',
                'justifycenter', 'justifyright', 'justifyjustify', '|',
                'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload']],
            enableAutoSave: false,
            enableContextMenu: false,
            elementPathEnabled: false,
            maximumWords: 1000,
            imageFieldName: 'path',
            imageActionName: 'uploadimage',
            initialFrameHeight: 200
        });
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function (action) {
            if (action == 'uploadimage') {
                return '/commonControl/uploadFile/img.do';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    },
    getContent: function () {
        return this.uEditor.getContent();
    },
    setHTMLContent:function(content){
        this.uEditor.ready(function () {
            Editor.uEditor.setContent(content, false);
        });
    },
    filterScript: function (s) {
        return s.replace(/<script.*?>.*?<\/script>/ig, '');
    }
}