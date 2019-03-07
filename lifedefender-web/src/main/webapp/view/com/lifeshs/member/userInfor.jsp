<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>个人信息</title>
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<link rel="stylesheet" href="/static/common/css/comCss.css">
<link rel="stylesheet" type="text/css" href="/static/common/css/common.css">
<link rel="stylesheet" href="/static/css/userInfor.css">
<link rel="stylesheet" href="/static/plugins/jeDate/css/jedate.css">
<link type="image/x-icon" rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico"/>
<t:base type="jquery,layer"></t:base>
<script type="text/javascript" src="/static/officialwebsite/js/dateFormat.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/plugins/jeDate/jedate.min.js"></script>
<script type="text/javascript" src="/static/officialwebsite/js/userInfo.js"></script>
<script>
    $(function () {
        menuSetting({
            parent : 5,
            child : 0
        });
        // 生日
        var birthday = '${user.birthday}';
        if (birthday != "") {
            birthday = new Date(birthday).Format("yyyy-MM-dd");
            $("#testDate").val(birthday);
        }
    });
</script>
</head>
<body>
<div class="webPage wrap" style="border: 1px solid #ddd !important">
    <%@include file="/context/header.jsp" %>
    <div class="view-body wrap">
        <%@include file="/context/nav_left.jsp" %>
        <div class="right-wrap">
            <div class="title fl">主页</div>
            <div class="container fr">
                <h3>个人信息</h3>

                <div class="addMyself" style="display:none;">
                    <div class="imgId">
                        <img id="upload_img" width="110" height="110" src="<c:choose><c:when test='${empty user.photo}'>static/images/photo.png</c:when><c:otherwise>${user.photo}</c:otherwise></c:choose>">
                        <input type="hidden" name="img_hidden"/>
                    </div>
                    <div class="changeUrl">
                        <!-- <input type="text" size="20" name="upfile" id="upfile">   -->
                        <input type="button" value="浏览" onclick="path.click()" style="outline:none;font-family:'Microsoft Yahei';cursor:pointer"/>
                        <input type="file" name="path" id="path" style="display:none" />
                        <p>请选择一张大小不超过1MB的图片，限jpg、gif格式</p>
                    </div>
                </div>
                <form action="">
                    <ul class="setPhoto">
                        <li class="photo_1"><p>头像设置</p></li>
                        <li class="photo_2">
                            <img name="photo" id="photo" width=110 height=110 src="<c:choose><c:when test='${empty user.photo}'>static/images/photo.png</c:when><c:otherwise>${user.photo}</c:otherwise></c:choose>" alt="头像">
                            <p>自定义头像</p></li>
                    </ul>
                    <ul class="setInfor">
                        <li><label>姓名</label> <input type="text" class="userName"
                                                     name="realName" value="${user.realName}"></li>
                        <li><label for="">性别</label>
                            <c:choose>
                                <c:when test="${empty user.sex}">
                                    <select name="sex">
                                        <option value="0" selected="selected">-选择性别-</option>
                                        <option value="male">男</option>
                                        <option value="female">女</option>
                                    </select>
                                </c:when>
                                <c:when test="${user.sex == false}">
                                    <select name="sex">
                                        <option value="0">-选择性别-</option>
                                        <option value="male">男</option>
                                        <option value="female" selected="selected">女</option>
                                    </select>
                                </c:when>
                                <c:when test="${user.sex == true}">
                                    <select name="sex">
                                        <option value="0">-选择性别-</option>
                                        <option value="male" selected="selected">男</option>
                                        <option value="female">女</option>
                                    </select>
                                </c:when>
                            </c:choose></li>
                        <li><label>生日</label> <input type="text" name="birthday" id="testDate" value="" readonly/>
                        </li>
                        <li class="tel">
                            <label for="info_mobile">手机号码</label>
                            <input type="text" id="info_mobile" value="${user.mobile}" name="mobile" placeholder="请输入手机号码">
                            <input type="hidden" name="info_mobileVerify" value="${user.mobileVerified}"/>
                            <c:if test="${user.mobileVerified}">
                                <label style="color: #ff5500;margin-right: 15px">已绑定</label>
                            </c:if>
                            <a href="memberControl.do?enterModifyMobile"><span>号码修改</span></a>
                        </li>
                        <li class="email">
                            <label for="info_email">邮箱</label>
                            <input type="email" id="info_email" value="${user.email}" placeholder="请输入邮箱">
                            <input type="hidden" name="info_emailVerify" value="${user.emailVerified}"/>
                            <c:if test="${user.emailVerified}">
                                <label style="color: #ff5500;margin-right: 15px">已绑定</label>
                            </c:if>
                            <a href="memberControl.do?enterModifyEmail"><span>邮箱绑定</span></a>
                        </li>
                    </ul>
                    <button type="button" onclick="updateUserInfo()">确定</button>
                </form>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="id" value="${user.id}"/>
<script type="text/javascript">
jeDate({
    dateCell: "#testDate",
    format: "YYYY-MM-DD",
    minDate: "1900-01-01",
    maxDate: jeDate.now(0),
    startMin: "1900-01-01",
    startMax: jeDate.now(0),
    zindex: 999,
    choosefun: function (elem, val) {
        //val为获取到的时间值
    }
});
</script>
</body>
</html>