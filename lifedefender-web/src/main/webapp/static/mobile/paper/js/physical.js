/**
 * 中医体质
 * @Author wenxian.cai
 * @Date 2017/7/18 10:14
 */

'use strict';

var physical = {};

physical.init = function () {
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

physical.vm = new Vue({
    el: '.vue-content',
    data: {
        paper: null,    //题目集合
        paperOptions: null,   //选项集合
        currentTopicNumber: 1,		//当前题码
        totalTopic: null,		//总题码
        currentTopicName: null,		//当前题目名称
        paperOptionScore: null,     //题目所得分数集合--[{'number': 1, 'score': 2}]
        paperScore: null,    //所得分数
        special: null,	//特殊题目
        gender: null,	//性别区分

        results: null,
        physicalName: null,
        cause: null,
        suggestion: null,
        physicalNames: null,
    },
    methods: {
        /** 下一道题 */
        nextTopic: function (score, type, special) {
            var vue = this;
            //特殊题目需逆向计分，即1→5,2→4,3→3,4→2,5→1
            if (special) {
                switch (score) {
                    case 1 :
                        score = 5;
                        break;
                    case 2 :
                        score = 4;
                        break;
                    case 3 :
                        break;
                    case 4 :
                        score = 2;
                        break;
                    case 5 :
                        score = 1;
                        break;
                }
            }
            var scoreObj = {
                number: vue.currentTopicNumber,
                score: score,
                type: type,
            }
            if (vue.paperOptionScore == null) {
                vue.paperOptionScore = new Array();
            }
            var bool = true;
            for (var i = 0; i < vue.paperOptionScore.length; i ++) {
                //该题分数以存在
                if (vue.paperOptionScore[i].number == vue.currentTopicNumber) {
                    vue.paperOptionScore[i].score = score;
                    bool = false;
                    break;
                }
            }
            if (bool) {
                vue.paperOptionScore.push(scoreObj);
            }
            if (vue.currentTopicNumber == vue.totalTopic) {	//全部题目做完
                var scoreArr = [0, 0, 0, 0, 0, 0, 0, 0, 0];
                var numberArr = [0, 0, 0, 0, 0, 0, 0, 0, 0];
                for (var i = 0; i < vue.paperOptionScore.length; i ++) {
                    switch (vue.paperOptionScore[i].type) {
                        case '阳虚质':
                            scoreArr[0] += vue.paperOptionScore[i].score;
                            numberArr[0] ++;
                            break;
                        case '阴虚质':
                            scoreArr[1] += vue.paperOptionScore[i].score;
                            numberArr[1] ++;
                            break;
                        case '气虚质':
                            scoreArr[2] += vue.paperOptionScore[i].score;
                            numberArr[2] ++;
                            break;
                        case '痰湿质':
                            scoreArr[3] += vue.paperOptionScore[i].score;
                            numberArr[3] ++;
                            break;
                        case '湿热质':
                            scoreArr[4] += vue.paperOptionScore[i].score;
                            numberArr[4] ++;
                            break;
                        case '血瘀质':
                            scoreArr[5] += vue.paperOptionScore[i].score;
                            numberArr[5] ++;
                            break;
                        case '特禀质':
                            scoreArr[6] += vue.paperOptionScore[i].score;
                            numberArr[6] ++;
                            break;
                        case '气郁质':
                            scoreArr[7] += vue.paperOptionScore[i].score;
                            numberArr[7] ++;
                            break;
                        case '平和质':
                            scoreArr[8] += vue.paperOptionScore[i].score;
                            numberArr[8] ++;
                            break;
                    }

                }

                //转化分数＝［（原始分-条目数）/（条目数×4）］×100
                for (var i = 0; i < scoreArr.length; i ++) {
                    scoreArr[i] = Math.round((scoreArr[i] - numberArr[i])/(numberArr[i] * 4)*100);
                }
                var isbool = false;
                for (var i = 0; i < scoreArr.length - 1; i ++) {
                    if (scoreArr[i] >= 40) {
                        isbool = true;
                    }
                }

                if (!isbool) {
                    if (scoreArr[8] >= 60) {
                        vue.paperScore = "平和质";
                    } else {
                        var max = 0;
                        for (var i = 0; i < scoreArr.length - 1; i ++) {
                            if (max < scoreArr[i]) {
                                max = scoreArr[i];
                            }
                        }
                        switch (max) {
                            case scoreArr[0]:
                                vue.paperScore = "阳虚质";
                                break;
                            case scoreArr[1]:
                                vue.paperScore = "阴虚质";
                                break;
                            case scoreArr[2]:
                                vue.paperScore = "气虚质";
                                break;
                            case scoreArr[3]:
                                vue.paperScore = "痰湿质";
                                break;
                            case scoreArr[4]:
                                vue.paperScore = "湿热质";
                                break;
                            case scoreArr[5]:
                                vue.paperScore = "血瘀质";
                                break;
                            case scoreArr[6]:
                                vue.paperScore = "特禀质";
                                break;
                            case scoreArr[7]:
                                vue.paperScore = "气郁质";
                                break;
                        }
                    }
                } else {
                    var max = 0;
                    for (var i = 0; i < scoreArr.length - 1; i ++) {
                        if (max < scoreArr[i]) {
                            max = scoreArr[i];
                        }
                    }
                    switch (max) {
                        case scoreArr[0]:
                            vue.paperScore = "阳虚质";
                            break;
                        case scoreArr[1]:
                            vue.paperScore = "阴虚质";
                            break;
                        case scoreArr[2]:
                            vue.paperScore = "气虚质";
                            break;
                        case scoreArr[3]:
                            vue.paperScore = "痰湿质";
                            break;
                        case scoreArr[4]:
                            vue.paperScore = "湿热质";
                            break;
                        case scoreArr[5]:
                            vue.paperScore = "血瘀质";
                            break;
                        case scoreArr[6]:
                            vue.paperScore = "特禀质";
                            break;
                        case scoreArr[7]:
                            vue.paperScore = "气郁质";
                            break;
                    }
                }
                setTimeout(function () {
                    vue.submitResult(vue.paperScore, scoreArr);
                    vue.paperScore = null;
                }, 1000);
            } else {
                setTimeout(function () {
                    vue.currentTopicNumber ++;
                    bool = true;
                    //判断此题是否已经答过
                    /*for (var i = 0; i < vue.paperOptionScore.length; i ++) {
                     if (vue.paperOptionScore[i].number == vue.currentTopicNumber) {
                     bool = false;
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
                    if (bool) {
                        $('.magic-radio').removeAttr("checked");
                    }
                }, 300);
            }
        },
        /** 上一道题 */
        preTopic: function () {
            var vue = this;
            setTimeout(function () {
                vue.currentTopicNumber --;
                //判断此题是否已经答过
                for (var i = 0; i < vue.paperOptionScore.length; i ++) {
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
                }
            }, 300);
        },
        /** 跳过题目 */
        skipTopic: function () {
            setTimeout(function () {
                physical.vm.currentTopicNumber ++;
                // $('.magic-radio').removeAttr("checked");
            }, 100);
        },
        /** 提交测试结果 */
        submitResult: function (physicalName, scoreArr) {
            var paperTypeName = "体质";
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
                    if (result.success == true) {
                        for (var i = 0; i < scoreArr.length; i ++) {
                            if (isNaN(scoreArr[i])) {
                                scoreArr[i] = 0;
                            }
                        }
                        window.location.href = "/app/appweb/physical-analyze?result=" + physicalName
                            +"&scoreArr=" + scoreArr;
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
            window.location.href = "/app/appweb/physical-paper";
        },
        /** 画柱状图 */
        makeChart: function () {
            var arr = this.physicalScore;
            var nameArr = new Array();
            var vueArr = new Array();
            for (var i = 0; i < arr.length; i ++) {
                if (arr[i] != 0) {
                    switch (i) {
                        case 0 :
                            nameArr.push("阳虚质");
                            vueArr.push({'color': '#F6BD0F', 'y': arr[i]});
                            break;
                        case 1 :
                            nameArr.push("阴虚质");
                            vueArr.push({'color': '#AFD8F8', 'y': arr[i]});
                            break;
                        case 2 :
                            nameArr.push("气虚质");
                            vueArr.push({'color': '#8BBA00', 'y': arr[i]});
                            break;
                        case 3 :
                            nameArr.push("痰湿质");
                            vueArr.push({'color': '#FF8E46', 'y': arr[i]});
                            break;
                        case 4 :
                            nameArr.push("湿热质");
                            vueArr.push({'color': '#008E8E', 'y': arr[i]});
                            break;
                        case 5 :
                            nameArr.push("血瘀质");
                            vueArr.push({'color': '#D64646', 'y': arr[i]});
                            break;
                        case 6 :
                            nameArr.push("特禀质");
                            vueArr.push({'color': '#8E468E', 'y': arr[i]});
                            break;
                        case 7 :
                            nameArr.push("气郁质");
                            vueArr.push({'color': '#30BE85', 'y': arr[i]});
                            break;
                        case 8 :
                            nameArr.push("平和质");
                            vueArr.push({'color': '#E708BA', 'y': arr[i]});
                            break;
                    }

                }
            }
           this.physicalNames = nameArr;
            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '体质分析图表',
                    align:'center'
                },
                legend: {
                    enabled: false
                },
                xAxis: {
                    categories: nameArr
                },
                yAxis: {
                    title: {
                        text:null
                    }

                },
                credits: {
                    enabled: false
                },
                plotOptions: {
                    spline: {
                        marker: {
                            radius:4,
                            lineColor: '#666',
                            lineWidth: 1
                        }
                    }
                },
                series: [{
                    name:'体质',
                    marker: {
                        symbol: 'diamond'
                    },
                    data: vueArr

                }]
            });
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
        /** 是否为特殊题目 */
        special: function () {
            if (this.paper != null) {
                return this.paper[this.currentTopicNumber - 1].special;
            }
        },
        /** 男女特殊题目 */
        gender: function () {
            if (this.paper != null) {
                if (this.paper[this.currentTopicNumber -1].gender == null) {
                    return false;
                }
                return true;
            }
        },
        /**体质名称*/
        physicalName: function () {
            if (this.results != null) {
                return this.results.physicalName;
            }
        },
        /**病因*/
        cause: function () {
            if (this.results != null) {
                return this.results.cause;
            }
        },
        /**症状*/
        symptoms: function () {
            if (this.results != null) {
                if (this.results.symptoms == null || this.results.symptoms == '') {
                    return null;
                }
                var temp = this.results.symptoms.split('&');
                return temp;
            }
        },
        /**临床表现*/
        manifestations: function () {
            if (this.results != null) {
                return this.results.manifestations;
            }
        },
        /**易感疾病*/
        susceptibleDisease: function () {
            if (this.results != null) {
                return this.results.susceptibleDisease;
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
                    var width = 1/(physical.vm.totalTopic/6) * (physical.vm.currentTopicNumber + 1);
                    $('.progress .step').css('width', width + 'rem');
                })
            })
        }
    }
});