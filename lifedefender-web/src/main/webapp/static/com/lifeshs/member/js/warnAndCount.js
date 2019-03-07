$(function () {
    $("input").iCheck({
        checkboxClass: 'icheckbox_minimal-green',
        radioClass: 'iradio_minimal-green',
        increaseArea: '20%' // optional
    });
    $('#addNumber').click(
        function () {
            // 默认选中预警号码
            /*var $radio = $("[name='addInfor'] input[type='radio']:eq(0)");
             $radio.iCheck("check");*/
            infoForm.reset();
            layer
                .open({
                    type: 1,
                    content: $("[name='addInfor']"), //这里content是一个DOM
                    title: ['添加号码',
                        'text-align:center;font-size:16px'],
                    btn: ['确定', '取消'],
                    closeBtn: 0,
                    yes: function () {
                        addContact();
                    },
                });
        });

    var $tds = $("table td");
    $tds.each(function () {

        var $this = $(this);

        var sos = $this.data("sos");
        var fam = $this.data("family");
        var type = $this.data("type");
        var terminal = $(this).next().text();

        var text = "";

        if (sos == 1) {
            text += "预警号码 ";
        }
        if (fam == 1) {
            text += "亲情号码";
        }
        if (type != undefined) {
            if (text == "") {
                text += "无";
            }

        }

        if (text.length > 0) {
            $this.text(text);
        }
    });

    $("li[name='SOS']").click(function () {

        var $tds = $("table td");

        $("table tbody tr").hide();

        $tds.each(function (index) {

            var sos = $(this).data("sos");

            if (sos == 1) {
                $(this).parent().show();
            }
        })

        changeType();
    });
    $("li[name='family']").click(function () {
        var $tds = $("table td");

        $("table tbody tr").hide();

        $tds.each(function (index) {

            var family = $(this).data("family");

            if (family == 1) {
                $(this).parent().show();
            }
        })

        changeType();
    });
    $("li[name='allContact']").click(function () {

        $("table tbody tr").show();

        changeType();
    });

    $('th img').click(function() {
        changeType();
    })
});

function changeType() {
    $('th ul').slideToggle();
    if ($('th ul').attr('data-hidden') == 0) {
        $('th img').css("transform",
            "rotate(-180deg)");
        $('th ul').attr('data-hidden', '1');
    } else {
        $('th img').css("transform",
            "rotate(0deg)");
        $('th ul').attr('data-hidden', '0');
    }
}

function getContactDetail(id){
    $.ajax({
        async : true,
        cache : false,
        type : 'GET',
        url: "memberControl.do?getContactDetail&id="+id,
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        beforeSend:function(){
            layer.load();
        },
        success: function(result) {
            if(result.success){
                $("[name='name']").val(result.attributes.name);
                $("[name='contactNumber']").val(result.attributes.contactNumber);
                var name="";
                var contactNumber="";
                layer.open({
                    type : 1,
                    moveType: 1,
                    content : $("ul.addInfor"), //这里content是一个DOM
                    title : [ '号码设置', 'text-align:center;font-size:16px' ],
                    btn : [ '确定', '取消' ],
                    closeBtn : 0,
                    yes:function(index){
                        name=$("[name='name']").val();
                        contactNumber=$("[name='contactNumber']").val();
                        /*$("input[name='radio1']:radio").each(function(){
                         if(true == $(this).is(':checked')){
                         isSOS+=$(this).val();
                         }
                         });*/
                        updateContact(name,contactNumber,id);
                        parent.layer.close(index);
                    },no:function(){
                        location.href="memberControl.do?showWarn";
                    }
                });
                layer.closeAll('loading');
            }

        },
        complete:function(){
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        }
    });
}

function updateContact(name,contactNumber,id){
    /*var _data="id="+id+'&contactNumber='+contactNumber+'&name='+name+'&isSOS='+isSOS;*/
    var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(!regex.test(contactNumber)){
        layer.msg("手机号码格式不正确");
        return ;
    }
    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "memberControl.do?updateContact",
        data: {
            id: id,
            contactNumber: contactNumber,
            name: name
        },
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        beforeSend:function(){
            layer.load();
        },
        complete:function(){
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                window.location.href="memberControl.do?showWarn";
            }
        }
    });
}

function deleteContact(id){
    if(id  == 'disable'){
        layer.msg('不可删除');
    }else{
        layer.confirm("你确定要删除该联系人吗？",function(index){
            var _data="id="+id;
            $.ajax({
                async : true,
                cache : false,
                type : 'POST',
                url: "memberControl.do?deleteContact",
                data: _data,
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                beforeSend:function(){
                    layer.load();
                },
                complete:function(){
                    setTimeout(function(){
                        layer.closeAll('loading');
                    }, 1000);
                },
                success: function(result) {
                    layer.msg(result.msg);
                    if(result.success){
                        window.location.href="memberControl.do?showWarn";
                    }

                }
            });
            layer.close(index);
        });
    }
}

function addContact(){
    var name = $("input[name='name']").val();
    var contactNumber = $("input[name='contactNumber']").val();
    var isSOS = $("ul[name='addInfor'] input[name='radio1']:checked").val();
    var _data="name="+name+'&contactNumber='+contactNumber+'&isSOS='+isSOS;
    if(name==''){
        layer.msg("请输入姓名");
        return ;
    }
    var chReg = /^[\u4e00-\u9fa5 ]{2,20}$/;//中文正则表达
    var enReg=/^[a-z\/ ]{2,20}$/i ;//英文正则表达
    if(!chReg.test(name)&&!enReg.test(name)){
        layer.msg("请输入正确的姓名格式");
        return ;
    }
    var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(!regex.test(contactNumber)){
        layer.msg("手机号码格式不正确");
        return ;
    }

    $.ajax({
        async : true,
        cache : false,
        type : 'POST',
        url: "memberControl.do?addContact",
        data: _data,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        beforeSend:function(){
            layer.load();
            setTimeout(function(){
                layer.closeAll("page");
            }, 800);
        },
        complete:function(){
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        },
        success: function(result) {
            layer.msg(result.msg);
            if(result.success){
                window.location.href="memberControl.do?showWarn";
            }
        }
    });
}