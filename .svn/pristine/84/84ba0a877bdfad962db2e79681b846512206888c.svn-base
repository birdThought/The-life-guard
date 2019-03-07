<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>C系列手环</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" href="/static/common/css/common.css">
    <link rel="stylesheet" href="/static/common/css/comCss.css">
    <link rel="stylesheet" href="/static/css/Cseries.css">
    <link href="/static/login/css/green.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=U2glmFNDRvYgft0vED17VpNj"></script>
    <t:base type="jquery,layer"></t:base>
    <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/com/lifeshs/member/js/loginCheck.js"></script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl"><a href="#" class="top_cite">主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;C3手环</div>
            <div class="container fr">
                <section class="introduce">
                    <h3>C系列手环</h3>
                </section>
                <ul class="Cseries_nav fl">
                    <li class="CsTitle">
                        <a href="javascript:void(0);">位置功能</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('currentLocate');">当前定位</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('electricFence');">电子围栏</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('orbitRun');">轨迹回放</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('monitor');">实时监听</a>
                    </li>
                    <li class="CsTitle">
                        <a href="javascript:void(0);">设备设置</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('changeNumber');">设备号码修改</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('monitorRate');">监控频率</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('remind');">提醒设置</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('blackList');">黑名单</a>
                    </li>
                    <li>
                        <a href="javascript:_clickNav('runMode');">运行模式</a>
                    </li>
                </ul>
                <div class="CsContent fr">
                    <div class="currentLocate" id="currentLocate">
                        <div class="searchLocate">
                            广州市: <input id="cityName" type="text" placeholder="请输入要搜索的地址"/>
                            <input class="searchBtn" type="button" value="搜索" onclick="theLocation('cityName','allmap')"/>
                        </div>
                        <div id="allmap">
                        </div>
                    </div>
                    <div class="electricFence nav_none" id="electricFence">
                        <div class="searchLocate">
                            广州市: <input id="cityAddr" type="text" placeholder="请输入要搜索的地址"/>
                            <input class="searchBtn" type="button" value="搜索" onclick="theLocation('cityAddr','allmap2')"/>
                        </div>
                        <ul class="createFance">
                            <li class="createOne"><a href="javascript:void(0)">创建围栏</a></li>
                        </ul>
                        <div class="setFance">
                            <h3>围栏设置</h3>
                            <ul>
                                <li>
                                    <div>
                                        <em>当前地址:</em>
                                        <p id="fence_addr">无</p>
                                    </div>
                                </li>
                                <li>
                                    <label for="">触发方式:</label><br/>
                                    <em>进围栏警报</em><input name="fence_check_in" type="checkbox">
                                    <em>出围栏警报</em><input name="fence_check_ou" type="checkbox">
                                </li>
                                <li>
                                    <em>围栏范围:</em>
                                    <div class="Cradius">
                                        <p id="fence_radi">当前半径:<em>0</em>米</p>
                                        <img src="" alt="">
                                    </div>
                                </li>
                                <li>
                                    <label for="">预警号码:</label><br/>
                                    <input id="fence_phone" type="text" placeholder="请输入电话号码">
                                </li>
                                <li>
                                    <table>
                                        <tr>
                                            <td>围栏监控时间段:</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="fence_check_1" type="checkbox"><em>第一时间段</em><br/>
                                                <input type="text" value="00:00" id="start_one" name="start_one" onclick="selectDate(1)"/><em>至</em><input type="text" value="00:00" id="end_one" name="end_one" onclick="selectDate(1)"/></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="fence_check_2" type="checkbox"><em>第二时间段</em><br/>
                                                <input type="text" value="00:00" id="start_two" name="start_two" onclick="selectDate(2)"/><em>至</em><input type="text" value="00:00" id="end_two" name="end_two" onclick="selectDate(2)"/></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input name="fence_check_3" type="checkbox"><em>第三时间段</em><br/>
                                                <input type="text" value="00:00" id="start_three" name="start_three" onclick="selectDate(2)"/><em>至</em><input type="text" value="00:00" id="end_three" name="end_three" onclick="selectDate(3)"/></td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <span id="fence_save">保存</span>
                                    <span id="fence_cancel">取消</span>
                                    <input type="hidden" name="fence_number">
                                </li>
                            </ul>
                        </div>
                        <div id="allmap2"></div>
                    </div>
                    <div class="orbitRun nav_none" id="orbitRun">
                        <div class="searchLocate">
                            广州市: <input id="cityTwo" type="text" placeholder="请输入要搜索的地址"/>
                            <input class="searchBtn" type="button" value="搜索" onclick="theLocation('cityTwo','allmap3')"/>
                            <div class="fr"><input type="date" id="orbit_date"/></div>
                        </div>
                        <ul class="createFance">
                            <li class="createOne"><a href="javascript:void(0)">创建轨迹</a></li>
                        </ul>
                        <div class="setFance nav_none">
                            <h3>轨迹回放</h3>
                            <dl>
                                <dt>监控时间段：</dt>
                                <dd>第一时间段</dd>
                                <dd><span>06:30</span><em>至</em><span>11:20</span></dd>
                            </dl>
                            <div class="changeMonitor">
                                <span>修改</span>
                                <span>取消</span>
                                <input id="orbit_number" value="1" type="hidden"/>
                            </div>
                        </div>
                        <div class="setFance nav_none">
                            <h3>轨迹回放设置</h3>
                            <ul>
                                <li>
                                    <table>
                                        <tr>
                                            <td>监控时间段:</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="checkbox"><em>第一时间段</em><br/>
                                                <input type="text" value="06:30"><em>至</em><input type="text" value="11:30"></td>
                                        </tr>
                                    </table>
                                </li>
                                <li>
                                    <span>删除该围栏</span>
                                </li>
                                <li>
                                    <span>保存</span>
                                    <span>取消</span>
                                </li>
                            </ul>
                        </div>
                        <div id="allmap3"></div>
                        <div id="result"></div>
                        <button id="run">开始</button>
                        <button id="stop">停止</button>
                        <button id="pause">暂停</button>
                    </div>
                    <div class="monitor nav_none" id="monitor">
                        <dl>
                            <dt>实时监听</dt>
                            <dd>实时监听，通过平台发送至领导手环，手环在根据所选择的联系人自动回拨号码(每次只可以U型安泽一个号码监听)</dd>
                        </dl>
                        <table>
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>号码</th>
                                <th style="position: relative;">类型<img src="/static/images/up.png" alt="">
                                    <ul style="display: none;">
                                        <li>全部</li>
                                        <li>预警号码</li>
                                        <li>亲情号码</li>
                                    </ul>
                                    <script>
                                        $('th img').click(function () {
                                            $('th ul').toggle();
                                        })
                                    </script>
                                </th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>程子欣</td>
                                <td>15917053564</td>
                                <td>预警号码</td>
                                <td>
                                    <span>启动监听</span>
                                </td>
                            </tr>
                            <tr>
                                <td>陈小浩</td>
                                <td>18020362378</td>
                                <td>预警号码</td>
                                <td>
                                    <span>启动监听</span>
                                </td>
                            </tr>
                            <tr>
                                <td>李冰</td>
                                <td>15920364628</td>
                                <td>亲情号码</td>
                                <td>
                                    <span>启动监听</span>
                                </td>
                            </tr>
                            <tr>
                                <td>王明</td>
                                <td>13723783964</td>
                                <td>亲情号码</td>
                                <td>
                                    <span>启动监听</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="changeNumber nav_none" id="changeNumber">
                        <dl>
                            <dt>设备号码修改</dt>
                            <dd>手环号码是获取定位信息和拨号功能的唯一途径，如果手环更换了号码卡，平台或APP需做相应的修改</dd>
                        </dl>
                        <p class="firstAlign">
                            <label>imei号</label>
                            <input type="text" name="terminalImei" readonly="readonly" placeholder="">
                        </p>
                        <p>
                            <label>手机号码</label>
                            <input type="text" name="terminalMobile" readonly="readonly" placeholder="">
                            <span>号码修改</span>
                        </p>
                        <button type="button">提交</button>
                    </div>
                    <div class="monitorRate nav_none" id="monitorRate">
                        <dl>
                            <dt>监控频率</dt>
                            <dd>“轨迹回放”或“电子围栏”的试用状态下，手环发送位置信息的频率，须知位置信息发送频率越高，手环的耗电量就好相对的增加(单位：秒)</dd>
                        </dl>
                        <ul>
                            <li>
                                <span>手表默认信息同步上传频率</span>
                                <select name="heartFrequency" id="heartFrequency">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                                <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                                <span>手表默认上传位置信息频率</span>
                                <select name="locationFrequency">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                                <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                                <span>当电量低于70%时，降频至</span>
                                <select name="autoFrequency70">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                                <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                                <span>当电量低于50%时，降频至</span>
                                <select name="autoFrequency50">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                                <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                                <span>当电量低于30%时，降频至</span>
                                <select name="autoFrequency30">
                                    <option value="0">0</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                                <img src="/static/images/question.png" alt="help">
                            </li>
                            <li>
                                <a href="javascript:_clickNav('resetMonitorRate');">
                                    <button type="reset">恢复默认</button>
                                </a>
                                <button type="submit" onclick="setMonitorRate()">提交</button>
                            </li>
                        </ul>
                    </div>
                    <div class="remind nav_none" id="remind">
                        <ul>
                            <li>
                                <span>提醒设置</span>
                            </li>
                            <li>
                                <span>添加提醒</span>
                            </li>
                        </ul>
                        <table id="notice">
                            <thead>
                            <tr>
                                <th>启动</th>
                                <th>时间</th>
                                <th>提醒时间</th>
                                <th>提醒内容</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>


                            </tbody>
                        </table>
                    </div>
                    <div class="blackList nav_none" id="blackList">
                        <dl>
                            <dt>黑白名单</dt>
                            <dd>加入黑名单的号码不能正常进行通话和收发短信，会受到限制</dd>
                        </dl>
                        <table id="blackLists">
                            <thead>
                            <tr>
                                <th>手机号码</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                    <div class="runMode nav_none" id="runMode">
                        <dl>
                            <dt>运行模式</dt>
                            <dd>在不同的运行模式下，根据系统设置项对手环用户的使用环境和人群惊醒模式切换</dd>
                        </dl>
                        <ul>
                            <li>
                                <input type="radio" name="radMode" id="runMode0" value="0">
                                <dl>
                                    <dt>默认模式</dt>
                                    <dd>对设备无任何限制</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode7" value="7" placeholder="">
                                <dl>
                                    <dt>家长模式</dt>
                                    <dd>可以一键拨通设置的监护人电话/可以接听黑名单之外的任意来电，来电铃声</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode1" value="1" placeholder="">
                                <dl>
                                    <dt>飞行模式</dt>
                                    <dd>不能拨打接听任意电话，无语音提示，无振动，GPS可被动开启</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode5" value="5" placeholder="">
                                <dl>
                                    <dt>儿童模式</dt>
                                    <dd>可以一键拨通设置的监护人电话/可以接听黑名单之外的任意来电，来电铃声</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode4" value="4" placeholder="">
                                <dl>
                                    <dt>校园模式</dt>
                                    <dd>可以一键拨通设置的监护人电话/可以接听黑名单之外的任意来电，来电铃声</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode3" value="3" class="goToClass">
                                <dl>
                                    <dt>上课隐身</dt>
                                    <dd>不能拨打接听任意电话，无语音提示，无振动，GPS可被动开启</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode2" value="2" class="goToClass">
                                <dl>
                                    <dt>会议模式</dt>
                                    <dd>不能拨打接听任意电话，无语音提示，无振动，GPS可被动开启</dd>
                                </dl>
                            </li>
                            <li>
                                <input type="radio" name="radMode" id="runMode6" value="6" class="goToClass">
                                <dl>
                                    <dt>GPS模式</dt>
                                    <dd>不能拨打接听任意电话，无语音提示，无振动，GPS可被动开启</dd>
                                </dl>
                            </li>
                        </ul>
                        <div class="remindDate">
                            <h4>选择日期</h4>
                            <ul class="remindFirst">
                                <li>
                                    <input type="checkbox" name="week" value="一">星期一
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="二">星期二
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="三">星期三
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="四">星期四
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="五">星期五
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="六">星期六
                                </li>
                                <li>
                                    <input type="checkbox" name="week" value="日">星期日
                                </li>
                            </ul>
                            <ul class="remindSecond">
                                <li>
                                    <span>提醒时间</span>
                                    <select name="remindHour1">
                                        <option value="6">6</option>
                                        <option value="5">5</option>
                                    </select>
                                    <span>时</span>
                                    <select name="remindMin1">
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                    </select>
                                    <span>分</span>
                                </li>
                                <li>
                                    <span>提醒时间</span>
                                    <select name="remindHour2">
                                        <option value="6">6</option>
                                        <option value="5">5</option>
                                    </select>
                                    <span>时</span>
                                    <select name="remindMin2">
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                    </select>
                                    <span>分</span>
                                </li>
                                <li>
                                    <span>提醒时间</span>
                                    <select name="remindHour3">
                                        <option value="6">6</option>
                                        <option value="5">5</option>
                                    </select>
                                    <span>时</span>
                                    <select name="remindMin3">
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                    </select>
                                    <span>分</span>
                                </li>
                            </ul>
                            <dl>
                                <dt>上课隐身</dt>
                                <dd>1、请在时段设置栏内修改时间</dd>
                                <dd>2、如果要关闭某一时段，请把时段相应的启用取消钩选</dd>
                                <dd>3、在时间段内终端处于GPS模式</dd>
                                <dd>4、在时间段外终端处于校园模式</dd>
                            </dl>
                        </div>
                        <button type="submit" onclick="setRunMode()">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/login/js/icheck.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/loginCheck.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/location.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/Cseries.js"></script>
<script type="text/javascript" src="/static/login/js/logout.js"></script>
<script type="text/javascript" src="/static/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function () {
        menuSetting({
            parent : 1,
            child : 2
        });
    });
    function selectDate(val) {
        WdatePicker({
            mixDate: '#F{$dp.$D(\'rqq\')}', isShowClear: true, readOnly: true,
            dateFmt: 'HH:mm', onpicked: function (dp) {
                onpicked(dp, val)
            }, oncleared: function () {
                clearedFunc(val)
            }
        });
    }
    function onpicked(dp, val) {
        //	alert('你选择的日期是:'+dp.cal.getDateStr()+",val="+val);
        if (val == 1) {
            must("start_one", true);
            must("end_one", true);
        } else if (val == 2) {
            must("start_two", true);
            must("end_two", true);
        } else if (val == 3) {
            must("start_three", true);
            must("end_three", true);
        }
    }
    //当日期被清空
    function clearedFunc(val) {
        if (val == 1) {
            var start_one = $("#start_one").val();
            var end_one = $("#end_one").val();
            if (start_one == "" && end_one == "") {
                must("start_one", false);
                must("end_one", false);
            } else {
                must("start_one", true);
                must("end_one", true);
            }
        } else if (val == 2) {
            var start_two = $("#start_two").val();
            var end_two = $("#end_two").val();
            if (start_two == "" && end_two == "") {
                must("start_two", false);
                must("end_two", false);
            } else {
                must("start_two", true);
                must("end_two", true);
            }
        } else if (val == 3) {
            var start_three = $("#start_three").val();
            var end_three = $("#end_three").val();
            if (start_three == "" && end_three == "") {
                must("start_one", false);
                must("end_three", false);
            } else {
                must("start_one", true);
                must("end_three", true);
            }
        }
    }
    function must(elementId) {
        $('#' + elementId).val();
    }
</script>

</body>
</html>