/*菜单管理*/
var menuSetting = function (data) {
    showMenu();
    this.parentMenuIndex = data['parent'];
    this.childMenuIndex = data['child'];
    this.num = 0;
    this.goHome = data['home'] == undefined ? false : true;
    if (!goHome) {
        var p = $(".menu-parent:eq(" + this.parentMenuIndex + ")");
        p.find($(".item")).addClass("item-action");
        p.find($(".item")).find($(".cursor")).attr("src", "static/images/caret.png");
        p.find($(".child-menu")).show();
        p.find($(".child-menu li")).eq(this.childMenuIndex).find($("a"))
            .addClass("action");
    }else{
        var t=$(".menu-parent:eq(0)");
        t.find($(".item")).addClass("item-action");
        t.find($(".item")).find($(".cursor")).attr("src", "static/images/caret.png");
        t.find($(".child-menu")).show();
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
                $(this).find($(".cursor")).attr("src", "static/images/caretDown.png");
            } else {
                $(this).find($(".cursor")).attr("src", "static/images/caret.png");
            }

            $(this).parent().siblings("li").find($(".item")).find($(".cursor")).attr("src", "static/images/caretDown.png");
        });
    /*
     * $(".menu-parent .item").click(function () {
     * $(this).parent().find($(".child-menu")).slideToggle(300, function () {
     * var h = $(this).parent().parent().height(); if (h >
     * $(".container").height() && h > oldHeight) {
     * $(".container").css("min-height", h); $(".right_content
     * .right_body").css("min-height", h - 65); } else if
     * ($(".container").height() > oldHeight) {
     * $(".container").css("min-height", oldHeight); $(".right_content
     * .right_body").css("min-height", oldHeight - 65); } }); });
     */
}

/*jQuery(function () {
 var orgType = _cookie("orgType");
 if (orgType == "") {
 jQuery.ajax({
 type: 'GET',
 url: 'orgControl.do?orgType',
 success: function (result) {
 var orgType = result.attributes.orgType;
 showMenu(orgType);
 document.cookie = "orgType=" + orgType;
 }
 });
 } else {
 showMenu(orgType);
 }
 });*/

function getOrgType() {
    jQuery.ajax({
        type: 'GET',
        url: '/orgControl.do?orgType',
        success: function (result) {
            var orgType = result.attributes.orgType;
            showMenu();
            document.cookie = "orgType=" + encodeURI(orgType);
        }
    });
}

function showMenu() {
    var orgType = _cookie("orgType");
    if(orgType==''){
        getOrgType();
        return;
    }
    switch (parseInt(orgType)) {
        case 0:
            jQuery("li.menu-orgManage").css({"display": "list-item"});
            $("li.menu-orgServe").remove();
            $("li.menu-personOrgServe").remove();
            break;
        case 1:
            jQuery("li.menu-orgServe").css({"display": "list-item"});
            $("li.menu-orgManage").remove();
            $("li.menu-personOrgServe").remove();
            break;
        case 2:
            jQuery("li.menu-personOrgServe").css({"display": "list-item"});
            $("li.menu-orgManage").remove();
            $("li.menu-orgServe").remove();
            break;
    }
}