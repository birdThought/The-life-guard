/*
 * 数据统计报表
 * author: wenxian.cai
 * date: 2017/8/16 11:58
 */

var statistics = {};

statistics.init = function () {
    statistics.vm.listStatistics();
};

statistics.initDetails = function () {
    statistics.vm.listStatisticsDetails();
};

statistics.vm = new Vue({
    el: '.vue-content',
    data: {
        project: [],
        diseases: [],
        results: [],
        page: 1,
        pageObj: null, //分页对象
        ageSegment: null,
        search: {
            projectCode: -1,
            diseasesId: -1,
            gender: -1,
            startAge: -1,
            endAge: -1
        },

    },
    methods: {
        /**获取数据统计列表*/
        listStatistics: function () {

            var url = '/org/data-statistics/list-data-statistics/' + this.page;
            http.ajax.get(true, true, url, this.search, http.ajax.CONTENT_TYPE_1, function (results) {
                statistics.vm.results = results.obj;

                if (statistics.vm.search.startAge == -1 || statistics.vm.search.endAge == -1) {
                    statistics.vm.ageSegment = '不限';
                } else {
                    statistics.vm.ageSegment = statistics.vm.search.startAge + '-' + statistics.vm.search.endAge;
                }
                //初始化分页
                statistics.vm.initPage(1);
            })

        },
        /**获取详细数据统计列表*/
        listStatisticsDetails: function () {

            var url = '/org/data-statistics/list-data-statistics-details/' + this.page;
            http.ajax.get(true, true, url, this.search, http.ajax.CONTENT_TYPE_1, function (results) {
                statistics.vm.results = results.obj;

                if (statistics.vm.search.startAge == -1 || statistics.vm.search.endAge == -1) {
                    statistics.vm.ageSegment = '不限';
                } else {
                    statistics.vm.ageSegment = statistics.vm.search.startAge + '-' + statistics.vm.search.endAge;
                }
                //初始化分页
                statistics.vm.initPage(2);
            })

        },

        /**搜索*/
        searchStatistics: function (type) {
            var search = this.search;
            if ((search.startAge == -1 && search.endAge != -1) ||
                (search.startAge != -1 && search.endAge == -1)) {
                layer.msg('请选择年龄段', {icon: 5});
                return;
            }
            switch (type) {
                case 1:
                    this.listStatistics();
                    break;
                case 2:
                    this.listStatisticsDetails();
                    break;
            }

        },
        /**前往数据统计详情界面*/
        goStatisticsDetails: function (projectCode, diseasesId, gender, ageSegment) {
            diseasesId = diseasesId == null ? -1 : diseasesId;
            if (gender == null) {
                gender = -1;
            } else {
                gender = gender ? '1' : '0';
            }
            var startAge = -1;
            var endAge = -1;
            if (ageSegment != '不限') {
                var arr = ageSegment.split('-');
                startAge = arr[0];
                endAge = arr[1];
            }
            window.location.href = '/org/data-statistics/details?projectCode=' + projectCode +
                                    '&diseasesId=' + diseasesId + '&gender=' + gender +
                                    '&startAge=' + startAge + '&endAge=' + endAge;
        },
        /**导出统计数据*/
        exportStatistics: function (type) {
            switch (type) {
                case 1:
                    excel.exportToExcel('table-id', '', '服务项目统计数据.xls');
                    break;
                case 2:
                    excel.exportToExcel('table-id', '', '服务项目详细统计数据.xls');
                    break;
            }

        },
        /**初始化分对象*/
        initPage: function (type) {
            if (this.pageObj == null) {
                this.pageObj = new PageUtil();
                this.pageObj.getPageControl().init({
                    container: 'page-container',
                    preBtn: 'pre_rec',
                    nextBtn: 'next_rec',
                    totalPage: statistics.vm.results.totalPage,
                    pageChange: function (page) {
                        statistics.vm.page = page;
                        switch (type) {
                            case 1:
                                statistics.vm.listStatistics();
                                break;
                            case 2:
                                statistics.vm.listStatisticsDetails();
                                break;
                        }
                    }
                });
            }
            this.pageObj.getPageControl().totalPage = this.results.totalPage;
            this.pageObj.getPageControl().selectPage(this.page, true);
            if (statistics.vm.results.totalSize == 0) {
                $('#page-container').css('display', 'none');
                return;
            }
            $('#page-container').css('display', 'block');

        },

        beforeEnter: function (el) {
            el.style.opacity = 0
            el.style.height = 0
        },
        enter: function (el, done) {
            var delay = el.dataset.index * 50
            setTimeout(function () {
                Velocity(
                    el,
                    { opacity: 1, height: '1.6em' },
                    { complete: done }
                )
            }, delay)
        },
        leave: function (el, done) {
            var delay = el.dataset.index * 50
            setTimeout(function () {
                Velocity(
                    el,
                    { opacity: 0, height: 0 },
                    { complete: done }
                )
            }, delay)
        }
    },
    computed: {
    },
    filters: {

    }
});


