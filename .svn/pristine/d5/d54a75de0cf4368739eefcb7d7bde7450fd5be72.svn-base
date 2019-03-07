/**
 * 健康日记js
 * author: wenxian.cai
 * date: 2017/11/13 16:52
 */

var diary = {};

diary.init = function () {
    diary.vm.listDiary();
}

/*diary.initDetails = function () {
    diary.vm.getDiary();
}*/

diary.vm = new Vue({
    el: '.vue-content',
    data: {
        list: [],
        page: 1,

    },
    methods: {
        /**获取体检报告列表*/
        listDiary: function () {
            var url = '/wechat-record/health-diary/' + this.page;
            var data = null;
            http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
                diary.vm.list = result.obj;
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