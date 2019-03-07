<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<nav class="menu-left">
    <ul>
        <li style="cursor: pointer; width: 100%;">
            <div class="item top">
                <a href="/login"
                    style="color: #fff; display: block"><img
                    src="/static/images/home.png" /> <span>主页</span></a>
            </div>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/healthPac.png" /> <span>便携健康包</span>
                <img class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a
                    href="healthDeviceManagerControl.do?showHealthPackageDevices">
                        <img /> <span>设备管理</span>
                </a></li>
                <li><a href="healthDataControl.do?hLEnter"> <img />
                        <span>健康数据</span>
                </a></li>
                <li><a href="healthDataControl.do?smsWarning">
                        <img /> <span>短信预警</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/wear.png" /> <span>穿戴设备</span> <img
                    class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a href="memberControl.do?getDeviceInfo"> <img />
                        <span>设备管理</span>
                </a></li>
                <li><a href="hLSeriesWebControl.do?hLSeries"> <img />
                        <span>HL-03手环</span>
                </a></li>
                <li><a href="terminalWebControl.do?cseries"> <img />
                        <span>C3手环</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/files.png" /> <span>健康档案</span>
                <img class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a href="recordHealthFileControl.do?enter">
                        <img /> <span>个人病历</span>
                </a></li>
                <li><a
                    href="physicalControl.do?enterPhysicalReport"> <img />
                        <span>体检报告</span>
                </a></li>
                <li><a href="recordDietControl.do?RecordDietEnter">
                        <img /> <span>健康日记</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/service.png" /> <span>服务</span>
                <img class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a href="serviceControl.do?healthConsultPage">
                        <img /> <span>推荐服务</span>
                </a></li>
                <li><a href="serviceControl.do?myService"> <img />
                        <span>我的服务</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/pers.png" /> <span>家庭组</span> <img
                    class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a href="familyControl.do?getFamilyMember">
                        <img /> <span>家庭成员</span>
                </a></li>
                <li><a
                    href="familyControl.do?insertRegistedMemberPage">
                        <img /> <span>添加成员</span>
                </a></li>
                <li><a href="familyControl.do?insertNewMemberPage">
                        <img /> <span>新增成员</span>
                </a></li>
            </ul>
        </li>
        <li class="menu-parent">
            <div class="item">
                <img src="/static/images/fami.png" /> <span>个人中心</span> <img
                    class="cursor" src="/static/images/caretDown.png" />
            </div>
            <ul class="child-menu">
                <li><a href="memberControl.do?getUserInfor"> <img />
                        <span>个人信息</span>
                </a></li>
                <li><a href="memberControl.do?showUserRecord">
                        <img /> <span>个人档案</span>
                </a></li>
                <li><a href="memberControl.do?showWarn"> <img />
                        <span>联系人</span>
                </a></li>
                <li><a href="memberControl.do?myOrders"> <img />
                        <span>我的订单</span>
                </a></li>
                <li><a href="memberControl.do?showUserPwd"> <img />
                        <span>修改密码</span>
                </a></li>
                <li><a href="memberControl.do?suggestionPage">
                        <img /> <span>意见反馈</span>
                </a></li>
                <li><a href="memberControl.do?interSmsRecord">
                        <img /> <span>短信记录</span>
                </a></li>
            </ul>
        </li>
    </ul>
</nav>

<script type="text/javascript">
var menuSetting = function (data) {
    this.parentMenuIndex = data['parent'];
    this.childMenuIndex = data['child'];
    this.num = 0;
    this.goHome = data['home'] == undefined ? false : true;
    if (!goHome) {
        var p = $(".menu-parent:eq(" + this.parentMenuIndex + ")");
        p.find($(".item")).addClass("item-action");
        p.find($(".item")).find($(".cursor")).attr("src", "/static/images/caret.png");
        p.find($(".child-menu")).show();
        p.find($(".child-menu li")).eq(this.childMenuIndex).find($("a"))
                .addClass("action");
    }
    this.oldHeight = $(window).height() - 61;
    $(".container").css("min-height", this.oldHeight);
    $(".right_content .right_body").css("min-height", this.oldHeight - 65);
    $(".menu-parent .item").click(
            function () {
                $(this).parent().siblings("li").find($(".child-menu")).slideUp(
                        300);
                $(this).parent().find($(".child-menu")).slideToggle(300);
                if ($(this).find($(".cursor")).attr("src") == 'static/images/caret.png') {
                    $(this).find($(".cursor")).attr("src", "/static/images/caretDown.png");
                } else {
                    $(this).find($(".cursor")).attr("src", "/static/images/caret.png");
                }
                $(this).parent().siblings("li").find($(".item")).find($(".cursor")).attr("src", "/static/images/caretDown.png");
            });
}
</script>