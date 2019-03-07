<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/view/platform/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<t:base type="jquery2.11,layui,vue"></t:base>
<meta charset=utf-8>
<meta name=description content="">
<meta name=viewport content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css?v=2.2.0">
    <link rel="stylesheet" href="/static/plugins/jeDate/skin/jedate.css">
    <%--<link rel="stylesheet" href="/static/platform/v2.2.0/css/org/common-store.css?v=2.2.0">--%>
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/org/income-manage.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/layui/css/layui.css">
<title>收益管理</title>
</head>
<body>

<%@include file="/view/platform/org/context/header.jsp"%>
<!-- /header -->
<article>
    <%@include file="/view/platform/org/context/admin.jsp"%>
    <section class="banxin container">
        <%@include file="/view/platform/org/context/menu.jsp"%>
        <div class="main-content vue-content" v-cloak>
            <div class="income-notice">
                <label>收益须知：</label>
                <p style="color: red;">
                    结算方式：
                    <br>1.平台按月进行结算，每笔成交订单可在平台查到交易明细，“生命守护”每月最后一天为结算日，
                    由生命守护统计并提供“对账明细表”,供双方核对，双方核对一致后，生命守护平台应在 5 个工作日内付款至机构指定账户。
                    <br>2.结算金额：商家收益=平台收益总金额-平台收益总金额。
                    <br>*费率说明：费率按照不同项目变化。
                </p>
            </div>
            <div>
                <label style="line-height: 50px; padding-left: 20px; color: #666; font-size: 16px;">账单确认：</label>
                <div style="background-color: white; padding-top: 50px;">

                    <input id="yearDate" type="text" class="year-date-btn"  readonly>
                    <div class="layui-tab layui-tab-brief">
                        <ul class="layui-tab-title">
                            <%--<li class="layui-this">2017年1月</li>
                            <li>2017年2月</li>
                            <li>2017年3月</li>
                            <li>2017年4月</li>
                            <li>2017年5月</li>
                            <li>2017年2月</li>
                            <li>2017年3月</li>
                            <li>2017年4月</li>
                            <li>2017年5月</li>
                            <li>2017年2月</li>
                            <li>2017年3月</li>
                            <li>2017年4月</li>--%>
                            <li v-for = "(i, index) in billDateList" :value = "i.value" v-cloak
                                @click = "getOrgStatement(i.value)"       >{{i.date}}</li>
                        </ul>
                        <%--<div class="layui-tab-content">--%>

                        <%--</div>--%>
                        <table class="bill-table" v-if = "currentBill.length > 0">
                            <thead>
                            <tr>
                                <td>总单数</td>
                                <td>总消费金额</td>
                                <td>实际结算</td>
                                <td>结算单生成时间</td>
                                <td>状态</td>
                                <td>机构确认时间</td>
                                <td>转账操作人</td>
                                <td>转账时间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>{{currentBill[0].orderNumber}}</td>
                                <td>{{currentBill[0].orderCharge}}</td>
                                <td>{{currentBill[0].statementCharge}}</td>
                                <td>{{currentBill[0].createDate | date('yyyy-MM-dd')}}</td>
                                <td class="status" v-html = "billStatus(currentBill[0].status)"></td>
                                <td>{{currentBill[0].confirmTime | date('yyyy-MM-dd')}}</td>
                                <td>{{currentBill[0].transferUser | isNone(1)}}</td>
                                <td>{{currentBill[0].transferTime | date('yyyy-MM-dd')}}</td>
                                <td class="details"><span @click = "showBillDetails">查看详情</span></td>
                            </tr>
                            </tbody>
                        </table>
                        <h4 v-else class="bill-none">该月份没有账单</h4>
                    </div>
                    <div class="bill-details none">
                        <label style="margin: 20px; color: #40c98d; font-size: 14px; font-weight: 800;">账单流水详情</label>
                        <div class="orders">
                            <div class="order-lists">
                                <span>日期</span>
                                <span style="margin-left: -12px;">订单号</span>
                                <span style="margin-left: -10px;">服务项目</span>
                                <span>用户名称</span>
                                <span>消费</span>
                            </div>
                            <ul class="order-content" v-if = "orders != null && orders.length > 0">
                                <li v-for = "order in orders" v-cloak>
                                    <span style="min-width: 40px;">{{order.createDate | date('yyyy-MM-dd')}}</span>
                                    <span style="min-width: 40px;">{{order.orderNumber}}</span>
                                    <span style="min-width: 60px;">{{order.projectName}}</span>
                                    <span style="min-width: 40px;">{{order.userName}}</span>
                                    <span style="min-width: 40px;">￥{{order.charge}}</span>
                                </li>
                            </ul>
                            <p v-else style="padding: 30px; text-align: center; color: gray;">该月暂无账单流水记录</p>
                        </div>
                    </div>

                </div>
            </div>


            <div class="income-notice">
                <label>账户信息：</label>
                <div v-if="bankAccount == ''">
                    <span class="cursor-pointer bind-account" @click="bindBankInfo()">去绑定银行信息</span>
                </div>
                <table class="store-info" v-if="bankAccount != ''">
                    <tr>
                        <td>账户名</td>
                        <td>{{bankAccount}}</td>
                    </tr>
                    <tr>
                        <td>状态</td>
                        <td>公司账户</td>
                    </tr>
                    <tr>
                        <td>类型</td>
                        <td>已绑定</td>
                    </tr>
                    <tr>
                        <td>银行</td>
                        <td>{{bankName}}</td>
                    </tr>
                </table>
            </div>


           <%-- <div class="income-top" style="height: 428px;">
                &lt;%&ndash;<label>收益详细：</label>&ndash;%&gt;
                <div>
                    <div class="income-count">
                        <i></i>
                        <p>上月收益</p>
                    </div>
                    <p><em>{{weekProfitSum}}</em>元</p>
                    <br><hr style="width: 100%; border-bottom: 1px solid #f2f2f2;"><br><br>
                    <div class="income-count">
                        <i></i>
                        <p style="color: #10bb71">上月实际收入</p>
                    </div>
                    <p><em>{{monthIncome}}</em>元</p>

                </div>
                <div>
                    <div class="data-show clearfix">
                        &lt;%&ndash;<ul>
                            <li class="on" v-on:click="switchData(1)">近7天</li>
                            <li v-on:click="switchData(2)">14天</li>
                            <li v-on:click="switchData(3)">30天</li>
                        </ul>&ndash;%&gt;
                        <div>
                            <input id="s" type="text" :placeholder="startDate">
                            <em>至</em>
                            <input id="e" type="text" :placeholder="endDate">
                        </div>
                    </div>
                    <div id="container"></div>
                </div>
            </div>--%>






        </div>
    </section>
</article>
<!-- /article -->
</body>
</html>
<script src="/static/plugins/highcharts/highcharts.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/common/js/dateFormat.js"></script>
<script src="/static/platform/v2.2.0/js/org/finance/finance-manager.js?v=2.2.0"></script>
<script src = "/static/plugins/layer/layer.js"></script>
<script>
    layui.use(['layer', 'element']);


    //这里是日期联动的关键
    function endDates() {
        //将结束日期的事件改成 false 即可
        end.trigger = false;
        jeDate(end);
    }
    
    var start = {
        skinCell:"jedategreen",
        dateCell : "#s",
        format:"YYYY-MM",
//        isinitVal:true,
        initAddVal:[0],
//        startMin:"1900-01",
//        startMax: '2017-06',
//        minDate:"1900-01",
        maxDate: '2017-06-01',
        zindex: 999,
        choosefun: function(elem, val){
            manager.vm.startDate = val;
            end.minDate = val + '-01';
            endDates();
        }
    }
    
    var end = {
        skinCell:"jedategreen",
        dateCell : "#e",
        format:"YYYY-MM",
//        isinitVal:true,
        initAddVal:[0],
      /*  startMin:"1900-01",
        startMax:"2017-06",*/
      /*  minDate: jeDate.now(0),*/
        maxDate: "2017-06-01",
        zindex: 999,
        choosefun: function(elem, val){
            manager.vm.endDate = val;
            start.maxDate = val + '-01'; //将结束日的初始值设定为开始日的最大日期
        }
    }
    
    jeDate(start);
    jeDate(end);
    
    $(".items-content table").not("table:first").css("display","none");
    $(".items-nav li").on("click",function(){
        $(this).addClass('current').siblings().removeClass('current');
        $(".items-content table").css("display","none").eq($(this).index()).css("display","block");
    });


        jeDate({
            skinCell:"jedategreen",
            dateCell: "#yearDate",
            maxDate: '2017-06-01',
            isinitVal:true,
            format:"YYYY",
            choosefun: function(elem, val){
                manager.vm.getbillDateList(val)
            }
        });
    
    manager.vm.weekProfitSum = parseInt('${data.weekProfitSum}');
    var date = new Date(DateUtils.getPreMonth(new Date().Format("yyyy-MM-dd"))).Format('yyyy-MM');
    manager.vm.startDate = date;
    manager.vm.endDate = date;
    manager.vm.monthIncome = '${data.monthIncome}';
    manager.vm.bankAccount = '${data.bankAccount}';
    manager.vm.bankName = '${data.bankName}';

    manager.vm.getbillDateList((new Date()).getFullYear())
//    manager.vm.customSelectData();


    if ('${orgType}' == 1) {
        $('.main-nav li').eq(4).click();
    } else {
        $('.main-nav li').eq(2).click();
    }
</script>