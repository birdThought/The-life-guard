var ServiceControl={
    isFree:false,
    init:function () {
        $(".priceBtn li").click(function () {
            $(this).siblings("li").removeClass("choose-action");
            $(this).addClass("choose-action");
            if(parseInt($(this).attr("data-mode"))=='0'){
                $("#countNumber").attr("disabled","disabled");
                ServiceControl.isFree=true;
            }else{
                $("#countNumber").removeAttr("disabled");
                ServiceControl.isFree=false;
            }
        });
        $("#countNumber").bind(
            "input propertychange", function () {
                ServiceControl.pageInput($(this).val());});
    },pageInput: function (val) {
        if (val < 1) {
            $("#countNumber").val(1);
        } else if (!(/^[-\+]?\d+$/.test(val) && parseInt(val) >= 0)) {
            $("#countNumber").val(1);
        }
    },addOrDecrease: function (target) {//增加或者减少数量
        if(this.isFree)
            return;
        var count = parseInt($("#countNumber").val());
        switch (target) {
            case 0://减少
                if (count <= 1) {
                    layer.msg("数量不能少于1");
                    return;
                }
                count -= 1;
                break;
            case 1://增加
                count += 1;
                break;
        }
        $("#countNumber").val(count);
    },buy:function (id) {
        var target=$(".priceBtn li.choose-action");
        var mode=parseInt(target.attr("data-mode"));
        if(isNaN(mode)||target.length==0){
            layer.msg("请选择付款模式");
            return;
        }
        var group = $(".groupBtn li.choose-action");
        var groupId = parseInt(group.data("groupid"));
        if (isNaN(groupId) || group.length == 0) {
        	layer.msg("请选择群组");
        	return;
        }
        
        var count=parseInt($("#countNumber").val());
        window.location.href="serviceControl.do?createOrder&orgServeId="+id+"&mode="+mode+"&count="+count+"&groupId="+groupId;
    }
}

jQuery(function() {
    $(".serviceGroup").on('click', '.groupBtn>li', function() {
        var groupid_show = $(this).data("groupid");
        
        $("div.serviceGroup>ul.groupBtn>li").each(function() {
            var groupid = $(this).data("groupid");
            if (groupid_show == groupid) {
                $(this).addClass("choose-action");
            } else {
                $(this).removeClass("choose-action");
            }
        });
        
        $("div.serviceGroup>ul.serviceDoctor").each(function() {
            var groupid = $(this).data("groupid");
            if (groupid_show == groupid) {
                $(this).fadeIn();
            } else {
                $(this).hide();
            }
        });
    })
});