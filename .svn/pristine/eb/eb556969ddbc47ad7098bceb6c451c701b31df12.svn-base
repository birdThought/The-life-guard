
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <t:base type="jquery2.11,layui,layer"></t:base>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="/static/platform/v2.2.0/css/magiccheck.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/reset.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/common.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/register_v2_0.css">

    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/static/platform/v2.2.0/css/proregister.css">

    <%--<script src="js/jquery-2.1.1.min.js"></script>--%>
    <title>机构注册</title>
</head>
<body>
<%@include file="../../context/header.jsp"%>
<section class="register_content banxin">
    <p class="introduce">提示：请正确填写以下资料，您提交信息后的3个工作日内进行审核，审核结果会以手机或邮件方式通知。</p>
    <div class="step-line clearfix">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <ul class="clearfix">
            <li>
                <div class="step-1"></div>
                <p class="on">1.公司信息认证</p>
            </li>
            <li>
                <div class="step-2"></div>
                <p >2.银行信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>3.法人信息认证</p>
            </li>
            <li>
                <div  class="step-2"></div>
                <p>4.准备入驻</p>
            </li><li>
                <div  class="step-2"></div>
                <p>5.等待审核</p>
            </li>
        </ul>
    </div>
    <form class="wrap register-form">
        <ul>
            <li class="set-head">
                <div><img class="upload-img-3" src="/static/platform/v2.2.0/images/template-head.png" alt=""></div>
                <p>logo为审核条件之一，请上传公司logo</p>
                <div>
                    <input type="button" value="选择文件" onclick="org.picClick(3)">

                </div>
                <input type="file" name="path" class="path-3"/>
                <input type="hidden" name = "photo" class="hidden-img-3"/>
            </li>
            <li>
                <h5>公司名称</h5>
                <input type="text" class="org-name" name = "orgName" placeholder="请按照营业执照上的公司名称填写，不可多写或少字">
                <div class="msg-lay">
                    <span class="msg-lay-1"></span>
                </div>
            </li>
            <li>
                <h5>营业执照号</h5>
                <input type="text" name="businessLicenseNumber" placeholder="请按营业执照副本格式填写">
            </li>
            <li class="up-licence">
                <h5>营业执照副(加盖公章)</h5>
                <div class="img-container fl">
                    <div class="up-btn">
                        <%--<span onclick="path.click()" class="register-upload-file" >上传文件</span>--%>
                        <%--<input type="file" name="path" id="path" style="display:none" onchange = "org.uploadPic(this)"/>--%>
                        文件大小1Mb以内
                    </div>
                    <div class="img-list clearfix">
                        <div class="fl">
                            <img class="upload-img-1 cursor-pointer" src = "/static/platform/v2.2.0/images/register/businessPic.png" onclick="org.picClick(1)"  alt=""/>
                            <input type="file" name="path" class="path-1"  />
                            <input type="hidden" name = "businessPic1" class="hidden-img-1"/>
                        </div>
                        <%--<div class="fl">
                            <img class="upload-img-2 cursor-pointer" src="/static/platform/v2.2.0/images/upload-2.png" onclick="org.picClick(2)" alt="">
                            <input type="file" name="path" class="path-2" />
                            <input type="hidden" name="businessPic2" class="hidden-img-2" />
                        </div>--%>
                    </div>
                </div>
            </li>
            <%--<li>
                <h5>公司类型</h5>
                <select name="orgType">
                    <option value="1">机构</option>
                    <option value="2">门店</option>
                </select>
            </li>--%>
            <li>
                <h5>公司性质</h5>
                <select name="orgCharacter">
                    <option value="健康服务机构">健康服务机构</option>
                    <option value="医疗服务机构">医疗服务机构</option>
                    <option value="体检机构">体检机构</option>
                    <option value="健康养生会所">健康养生会所</option>
                    <option value="瑜伽馆">瑜伽馆</option>
                    <option value="健身俱乐部">健身俱乐部</option>
                    <option value="国医馆">国医馆</option>
                    <option value="中医理疗馆">中医理疗馆</option>
                    <option value="中医馆">中医馆</option>
                    <option value="药店">药店</option>
                    <option value="足疗店">足疗店</option>
                    <option value="理疗护理中心">理疗护理中心</option>
                    <option value="家政服务机构">家政服务机构</option>
                    <option value="养老护老行业">养老护老行业</option>
                    <option value="教育/学校/培训机构">教育/学校/培训机构</option>
                    <option value="三甲医院">三甲医院</option>
                    <option value="社区医院">社区医院</option>
                    <option value="心理辅导机构">心理辅导机构</option>
                    <option value="私人诊所">私人诊所</option>
                </select>
            </li>
            <li class="chose-area">
                <h5>所在地区</h5>
                <div class="fl">
                    <select name="province" class="province" onchange="getCitys();">
                        <c:forEach var = "p" items="${province}">
                            <option value="${p.code}">${p.name}</option>
                        </c:forEach >
                    </select>
                    <select name="city" class="city" onchange="getCountrys()" >
                        <c:forEach var = "p" items="${city}">
                            <option value="${p.code}">${p.name}</option>
                        </c:forEach >
                    </select>
                    <select name="district" class="district" onchange="org.searchMap(org.map)">
                        <c:forEach var = "p" items="${district}">
                            <option value="${p.code}">${p.name}</option>
                        </c:forEach >
                    </select>
                </div>
            </li>
            <li>
                <h5>公司详细地址</h5>
                <input type="text" name="address" placeholder="请填写公司详细地址(或点击地图选择地址)" onkeyup="org.searchMap(org.map, $(this).val())">
            </li>
            <div class="auto-area" id = "map"
                 style="width: 492px; height: 240px; margin-left: 239px; margin-top: -40px; margin-bottom: 40px;">
            </div>
            <li>
                <h5>公司从事领域</h5>
               <%-- <input type="text" name="workField" placeholder="请填写公司从事领域">--%>
                <select name="workField" >
                    <option value="体检">体检</option>
                    <option value="健康管理">健康管理</option>
                    <option value="诊疗">诊疗</option>
                    <option value="养生理疗">养生理疗</option>
                    <option value="居家健康">居家健康</option>
                    <option value="锻炼健身">锻炼健身</option>
                    <option value="教育文化">教育文化</option>
                </select>
            </li>
            <li>
                <h5>公司电话</h5>
                <input type="text" name="mobile" placeholder="请填写公司电话，格式：020-88888888">
            </li>
            <li class="argument">
                <p>点击【注册】按钮，表示您同意<a class="btn-yellow btn-agreement" onclick="org.agreement()">《门店注册协议》</a></p>
            </li>
            <li class="argument">
                <input type="button" class="submit" value="注册" onclick="org.register()">
            </li>
        </ul>
    </form>
</section>
<%@include file="../../context/footer.jsp"%>
</body>
</html>
<script type="text/javascript" src="/static/com/lifeshs/member/js/photoUpload.js"></script>
<script type="text/javascript" src="/static/com/lifeshs/member/record/fileUpload.js"></script>
<script src="/static/platform/v2.2.0/js/register/org/orgregister.js"></script>
<script src="/static/common/js/uploadfile.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap"></script>
<script>
    /*初始化地区*/
    org.provinces = '${province}' == '' ? '' : JSON.parse('${province}');
    org.cities = '${city}' == '' ? '' : JSON.parse('${city}');
    org.district = '${district}' == '' ? '' : JSON.parse('${district}');

    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';
    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {

        $('.upload-img-1').attr('src', results.obj);
        $('.hidden-img-1').val(results.obj);
    });

    //绑定图片2
    element = '.path-2';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-2').attr('src', results.obj);
        $('.hidden-img-2').val(results.obj);
    });

    //绑定图片2
    element = '.path-3';
    lay.uploadFile(url, method, element, function (results) {
        $('.upload-img-3').attr('src', results.obj);
        $('.hidden-img-3').val(results.obj);
    });

    var initMap = function () {
        var map = new BMap.Map("map");
        org.initMap(map);
        org.map = map;
    }
   /* setTimeout(function () {
        org.searchMap(org.map, $('[name="province"] option:selected').text()
            + $('[name="city"] option:selected').text()
            + $('[name="district"] option:selected').text());
    }, 3000)*/


</script>

