/**
 * 体检报告js
 * author: wenxian.cai
 * date: 2017/11/13 16:52
 */

var medical = {};

medical.init = function () {
    medical.vm.listMedical();
}

medical.initDetails = function () {
    medical.vm.getMedical();
}

medical.vm = new Vue({
    el: '.vue-content',
    data: {
        list: [],
        page: 1,
        details: {},
        medicalId: null,
        user: {},

    },
    /*created: function () {
     this.listPhysical();
     },*/
    methods: {
        /**获取体检报告列表*/
        listMedical: function () {
            var url = '/wechat-record/medicals/' + this.page;
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                medical.vm.list = result.obj;
            });
        },
        /**跳转体检详细页面*/
        goDetails: function (id) {
            window.location.href = '/wechat-record/medical-details/page?medicalId=' + id;
        },
        /**获取体检报告详细*/
        getMedical: function () {
            var url = '/wechat-record/medical-details';
            var data = {
                medicalId: this.medicalId
            };
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                medical.vm.details = result.obj;
                medical.vm.user = result.attributes;
            });
        },
    },
    filters: {
        date: function (value, format) {
            return new Date(value).Format(format);
        },
        removeSpace: function (value) {
            return $.trim(value);
        }
    }
});