/**
 * 亚健康
 * @Author wenxian.cai
 * @Date 2017/7/18 14:34
 */

var health = {};

health.init = function () {
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if(clientWidth>=640){
                    docEl.style.fontSize = '100px';
                }else{
                    docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);

    $('.lists-test').on('click', 'li', function (index, ele) {
        if ($(this).hasClass('on')) {
            $(this).removeClass('on');
            $(this).find('.tip').eq(0).remove();
            return;
        }
        $(this).find('div').append('<i class = "tip"></i>');
        $(this).addClass('on');

    })
};

health.vm = new Vue({
    el: '.vue-content',
    data: {
        results: null,
        score: null,
        basicDes: null,
        suggestion: null,
    },
    methods: {
        /** 提交测试结果 */
        submitResult: function () {
            var score = 0;
            $('.lists-test li').each(function () {
                if ($(this).hasClass('on')) {
                    score ++;
                }
            });
            score = 24 - score;

            var paperTypeName = "亚健康";
            var topicTypeName = "亚健康";
            var paperResult = {
                'paperTypeName': paperTypeName,
                'topicTypeName': topicTypeName,
                'result': score
            }
            $.ajax({
                async : true,
                cache : false,
                type: 'POST',
                contentType: 'application/x-www-form-urlencoded',
                url: '/indexControl.do?addSubHealthResult',
                data: (paperResult),
                before: function(){
                    layer.load(1);
                },
                success: function(result){
                    if (result.success == true) {
                        window.location.href = "/app/appweb/subhealth-analyze?score=" + score;
                    }
                }
            });
        },
        again: function () {
            window.location.href = "/app/appweb/subhealth-paper";
        }
    },
    computed: {
        basicDes: function () {
            if (this.results != null) {
                return this.results.basicDescription;
            }
        },
        suggestion: function () {
            if (this.results != null) {
                if (this.results.suggestion == null || this.results.suggestion == '') {
                    return null;
                }
                var temp = this.results.suggestion.split('&');
                return temp;
            }
        }

    }
});