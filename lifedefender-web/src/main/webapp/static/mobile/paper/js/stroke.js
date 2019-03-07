/*
 * 中风风险测试
 * date: 2017/8/7 15:36
 */

var stroke = {};

stroke.init = function () {
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
}

stroke.vm = new Vue({
    el: '.vue-content',
    data: {
        paper: null,    //题目集合
        paperOptions: null,   //选项集合
        currentTopicNumber: 1,		//当前题码
        totalTopic: null,		//总题码
        currentTopicName: null,		//当前题目名称
        paperOptionScore: null,     //题目所得分数集合--[{'number': 1, 'score': 2}]
        paperScore: null,    //所得分数
        currentId: null,

        result: null,   //结果分析
        score: null,


    },
    methods: {
        /** 下一道题 */
        nextTopic: function (score, type) {
            var scoreObj = {
                number: this.currentTopicNumber,
                score: score,
                type: type,
            }
            if (this.paperOptionScore == null) {   //改为map方式存储
                this.paperOptionScore = {};
            }
            this.paperOptionScore[this.currentTopicNumber] = scoreObj;
            /** 全部做完*/
            if (this.currentTopicNumber == this.totalTopic) {
                this.paperScore = 0;
                for (var i in this.paperOptionScore) {
                    this.paperScore += this.paperOptionScore[i].score;
                }
                var basic = null;
                if (this.paperScore > 0) {
                    basic = '中危'
                    if (this.paperScore > 2) {
                        basic = '高危';
                    }
                } else {
                    basic = '低危';
                }
                setTimeout(function () {
                    stroke.vm.submitResult(basic, stroke.vm.paperScore);
                    stroke.vm.paperScore = null;
                }, 1000);

            } else {
                setTimeout(function () {
                    stroke.vm.currentTopicNumber ++;
                }, 300);
            }
        },
        /** 上一道题 */
        preTopic: function () {
            var vue = this;
            setTimeout(function () {
                vue.currentTopicNumber --;
                //判断此题是否已经答过
                /*for (var i = 0; i < vue.paperOptionScore.length; i ++) {
                    if (vue.paperOptionScore[i].number == vue.currentTopicNumber) {
                        var temp = 0;
                        switch (vue.paperOptionScore[i].score) {
                            case 0 :
                                temp = 0;
                                break;
                            case 3 :
                                temp = 1;
                                break;
                            case 5 :
                                temp = 2;
                                break;
                            default :
                                temp = 0;
                                break;
                        }
                        $('.options').find('.magic-radio').eq(temp).attr('checked', 'true');
                    }
                }*/
            }, 300);
        },
        /** 提交测试结果 */
        submitResult: function (physicalName, score) {
            var paperTypeName = "中风";
            var paperResult = {
                'paperTypeName': paperTypeName,
                'result': physicalName,
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
                    if (result.success) {
						/*var data = {
							physicalName:physicalName
						}*/
                        window.location.href = "/app/appweb/stroke-analyze?result=" + physicalName
                            +"&score=" + score;
                    }
                }
            });
        },
        getOptionNumber: function (index) {
            var msg = '';
            switch (index) {
                case 0:
                    msg = 'A';
                    break;
                case 1:
                    msg = 'B';
                    break;
                case 2:
                    msg = 'C';
                    break;
                case 3:
                    msg = 'D';
                    break;
                case 4:
                    msg = 'E';
                    break;
                default:
                    break;
            }
            return msg;
        },
        /** 重新测试 */
        again: function () {
            window.location.href = "/app/appweb/stroke-paper";
        },
        getOption: function() {

        }

    },
    computed: {
        /** 计算总页数 */
        totalTopic: function () {
            if (this.paper != null) {
                return this.paper.length;
            }
        },
        /** 获取当前题目名称 */
        currentTopicName: function () {
            if (this.paper != null) {
                return this.paper[this.currentTopicNumber - 1].name;
            }
        },
        /** 返回当前题目类型 */
        currentTopicType: function () {
            if (this.paper != null) {
                return this.paper[this.currentTopicNumber - 1].topicType;
            }
        },

        /**建议*/
        suggestion: function () {
            if (this.results != null) {
                if (this.results.suggestion == null || this.results.suggestion == '') {
                    return null;
                }
//								var temp = this.results.suggestion.split('&');
                return this.results.suggestion;
            }
        },
        paperOptions: function () {
            if (this.paper == null) {
                return;
            }
            for (var i in this.paper) {
                if (this.paper[i].id == this.currentId) {
                    return this.paper[i].options;
                }
            }
        },
        currentId: function () {
            if (this.paper != null) {
                return this.paper[this.currentTopicNumber - 1].id;
            }
        }

    },
    watch: {
        paperOptions: function () {
            this.$nextTick(function () {

                $('.answer').on('click', 'li', function () {
                    $(this).addClass("on").siblings().removeClass('on');
                    var $obj = $(this);
                    setTimeout(function () {
                        $obj.removeClass('on');
                    }, 300);
                    var width = 1/(stroke.vm.totalTopic/6) * (stroke.vm.currentTopicNumber + 1);
                    $('.progress .step').css('width', width + 'rem');
                })
            })
        },
        result: function () {
            this.$nextTick(function () {
                var width = stroke.vm.score;

                switch (stroke.vm.score) {
                    case '0':
                        width = 10;
                        break;
                    case '1':
                        width = 35;
                        break;
                    case '2':
                        width = 45;
                        break;
                    case '3':
                        width = 70;
                        break;
                    case '4':
                        width = 73;
                        break;
                    case '5':
                        width = 75;
                        break;
                    case '6':
                        width = 78;
                        break;
                    case '7':
                        width = 85;
                        break;
                    case '8':
                        width = 90;
                        break;
                    case '9':
                        width = 95;
                        break;
                }
                width = width + '%';
                $('.point').css('left', 'calc('+ width +')');
            });
        }
    }
});