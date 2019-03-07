<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>服务管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <t:base type="jquery,layer"></t:base>
    <link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/common.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/QYcomCss.css">
    <link href="/static/login/css/blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="/static/plugins/jeDate/css/jedate.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/tableLayout.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/healthAsk.css">
    <link rel="stylesheet" href="/static/com/QYPart/css/serviceManager.css">
    <link rel="stylesheet" href="/static/common/css/chatCss.css">
    <link rel="stylesheet" type="text/css"
          href="/static/common/css/pageCss.css">
    <script type="text/javascript" src="/static/com/QYPart/js/DateUtils.js"></script>
    <script type="text/javascript" src="/static/com/QYPart/js/pageUtils.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/strophe.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk-1.1.2.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/websdk.shim.js"></script>
    <script type="text/javascript" src="/static/plugins/hx/webim.config.js"></script>
    <script type="text/javascript" src="/static/plugins/seaJs/sea.js"></script>
    <script type="text/javascript">
        seajs.config({
            base: './static/',
            alias: {
                "icheck": "login/js/icheck",
                "QYcommon": "com/QYPart/js/QYcommon",
                "commonCheck": "com/QYPart/js/commonCheck",
                "customRadio": "com/QYPart/js/customRadio",
                "jedate": "plugins/jeDate/jedate.min",
                "dateFormat": "officialwebsite/js/dateFormat",
                //"pageUtils": "com/QYPart/js/pageUtils",
                "qrcode": "com/QYPart/js/qrcode.min",
                "chat": "common/js/ChatUtils"
            }
        });
        seajs.use(["./static/com/QYPart/js/health_ask"], function () {
            var child = 1;
            <shiro:hasPermission name="user:1">
            child = 0;
            groupControl.userType = 1;
            </shiro:hasPermission>
            menuSetting({
                parent: 0,
                child: child
            });
            initUserControl(parseInt('${serve}'));
            var groupPage = new PageUtil();
            groupPage.getPageControl().init({
                container: "groupPageContain",
                preBtn: "groupPre",
                nextBtn: "groupNext",
                totalPage: parseInt('${groupPageCount}'),
                pageChange: function (page) {
                    groupControl.getGroupDataByPage(page);
                }
            });
            var historyPage = new PageUtil();
            historyPage.getPageControl().init({
                container: "historyPage",
                preBtn: "his_pre",
                nextBtn: "his_next",
                totalPage: parseInt('${rCount}'),
                pageChange: function (page) {
                    HistoryRecord.getDatas(page);
                }
            });
            HuanxinControl.init({
                account: '${hxCode}',//测试用的账号和密码
                password: WebIM.config.password,
                headUrl: '${headUrl}',
                nick: '${nick}',
                orgServeId: '${orgServeId}'
            });
            ChatControl.init();
        });
    </script>
</head>

<body>
<div id="recent_chat" onclick="ChatControl.openChat(HuanxinControl.dataInit)">
</div>
<%@include file="/context/QYHeader.jsp" %>
<div class="container">
    <%@include file="/context/QYMenu.jsp" %>
    <div class="right_content">
        <div class="right_body">
            <div class="right_title">
                <label id="group_control" class="action" onclick="showBlock(0)">
                    群组管理 </label> <label id="service_history" onclick="showBlock(1)">
                服务历史记录 </label>
            </div>
            <div class="main_contain">
                <%@include file="/view/com/QYPart/fragment/serviceHistory.jsp" %>
                <%@include file="/view/com/QYPart/fragment/groupManage.jsp" %>
            </div>
        </div>
    </div>
</div>

</body>
</html>
