/**
 * 体检报告js
 * author: wenxian.cai
 * date: 2017/11/13 16:52
 */

var physical = {};

physical.init = function () {
    physical.vm.listPhysical();
}

physical.initDetails = function () {
    physical.vm.getPhysical();
}

physical.vm = new Vue({
    el: '.vue-content',
    data: {
        list: [],
        page: 1,
        details: {},
        physicalId: null,

    },
    /*created: function () {
        this.listPhysical();
    },*/
    methods: {
        /**获取体检报告列表*/
        listPhysical: function () {
            var url = '/wechat-record/physicals/' + this.page;
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                physical.vm.list = result.obj;
            });
        },
        /**跳转体检详细页面*/
        goDetails: function (id) {
            window.location.href = '/wechat-record/physical-details/page?physicalId=' + id;
        },
        /**获取体检报告详细*/
        getPhysical: function () {
            var url = '/wechat-record/physical-details';
            var data = {
                physicalId: this.physicalId
            };
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                physical.vm.details = result.obj;
            });
        },
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        }
    }
});