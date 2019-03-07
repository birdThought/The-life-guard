/*
 * 短信预警
 */

var smsWarning = {
    /* 短信预警初始化 */
    bindEvent: function(){
        //确定是否已有预警短信接收人
        smsWarning.getSmsReceive(function(bool){
            if(bool){
                $('.smsWarning_add span').text('更换号码');
            }
        });
        //获取预警项目
        smsWarning.getWarningDevice();
        // 绑定添加短信预警人
        jQuery("#sms").on('click', 'div.smsWarning_add>span', function(){
            smsWarning.getWarningReceive();
            layer.open({
                type: 1,
                content: jQuery(".receive_dialog_container"),
                title: ['添加亲情号码','text-align:center;font-size:16px'],
                btn: ['确定','取消'],
                closeBtn: 1,
                moveType: 1,
                area: '320px',
                yes: function(index){
                    var contactId = $("#allContact option:selected").attr('id');
                    $.ajax({
                        async : true,
                        cache : false,
                        type: 'POST',
                        contentType: 'application/json; charset=utf-8',
                        url : 'healthDataControl.do?modifyContactreceiveSms',
                        data :'{"id":"'+ contactId +'"}',
                        success : function(data) {
                            if(data.success == true){
                                layer.msg("更换成功");
                                layer.close(index);
                                smsWarning.getSmsReceive();
                            }
                            
                        }
                    });
                }
            });
        });
        //绑定预警联系人删除事件
        $('#receiveLists').on('click', 'tbody tr td:nth-child(3)', function(){
            var temp = $(this);
            layer.confirm('确定删除预警联系人?', {icon: 2, title:'提示'}, function(index){
                smsWarning.deleteSmsReceive(temp.parent().attr('id'));
                layer.close(index);
            });
        });
        //绑定修改预警项目事件
         $("#warningList").on("ifClicked", "input[name='smsDevice']", function(event){
             alert("aaaa");
             if($(this).attr('check') == "true"){
                 var name = $(this).attr("value");
                 var obj = {
                         healthTypeName : name,
                         cancel : false,
                 }
                 smsWarning.modifyWarningDevice(obj);
             }else{
                 var name = $(this).attr("value");
                 var obj = {
                         healthTypeName : name,
                         cancel : true,
                 }
                 smsWarning.modifyWarningDevice(obj);
             }
         });
    },
    getSmsReceive: function(callback){
        $.ajax({
            async : true,
            cache : false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url : 'healthDataControl.do?getWarningReceive',
            data :'{"extra":"extra"}',
            success : function(data) {
                var bool = false;
                $("#receiveLists").find('tbody').empty();
                var len = data.obj.length;
                var str = '';
                for (var i = 0; i < len; i++) {
                    if(data.obj[i].receiveSMS == true){
                        str += '<tr id = "'+ data.obj[i].id +'"><td>'+ data.obj[i].name +'</td><td>'+ data.obj[i].contactNumber +'</td><td><span style="cursor:pointer">删除</span></td></tr>';
                        bool = true;
                        break;
                    }
                }
                $("#receiveLists").find('tbody').append(str);
                if(typeof(callback) == 'function'){
                    callback(bool);
                }
            }
        });
    },
    deleteSmsReceive: function(id){
        $.ajax({
            async : true,
            cache : false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url : 'healthDataControl.do?deleteContactreceiveSms',
            data :'{"id":"'+ id +'"}',
            success : function(data) {
                if(data.success == true){
                    layer.msg("删除成功");
                    smsWarning.getSmsReceive();
                }
                
            }
        });
    },
    getWarningReceive: function(){
        $.ajax({
            async : true,
            cache : false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url : 'healthDataControl.do?getWarningReceive',
            data :'{"extra":"extra"}',
            success : function(data) {
                $("#allContact").empty();
                var len = data.obj.length;
                var str = '';
                for (var i = 0; i < len; i++) {
                    if(data.obj[i].receiveSMS != true && !data.obj[i].isSOS){
                        str += '<option id = '+ data.obj[i].id +' value = "">'+ data.obj[i].name +'&nbsp;&nbsp;&nbsp;&nbsp;'+ data.obj[i].contactNumber+' </option>';
                    }
                }
                $("#allContact").append(str);
                
            }
        });
    },
    getWarningDevice: function(){
        $.ajax({
            async : true,
            cache : false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url : 'healthDataControl.do?getUserHealthWarningList',
            data :'{"extra":"extra"}',
            success : function(data) {
                if(data.obj != null){
                    var len = data.obj.length;
                    for (var i = 0; i < len; i++) {
                        if((data.obj[i].name_en == "heartRate")){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='heartRate']").iCheck('check');
                                $("#heartRate").attr("check", true);
                            }else{
//                                  $("#heartRate").parent().attr("class","");
                                $("#heartRate").attr("check", false);
                            }
                            
                        }
                        if(((data.obj[i].name_en == "systolic")) || ((data.obj[i].name_en == "diastolic"))){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='tolic']").iCheck('check');
                                $("#tolic").attr("check", true);
                            }else{
//                                  $("#tolic").parent().attr("class","");
                                $("#tolic").attr("check", false);
                            }
                            
                        }
                        if((data.obj[i].name_en == "saturation")){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='saturation']").iCheck('check');
                                $("#saturation").attr("check", true);
                            }else{
//                                  $("#saturation").parent().attr("class","");
                                $("#saturation").attr("check", false);
                            }
                            
                        }
                        if((data.obj[i].name_en == "bloodSugar") ){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='bloodSugar']").iCheck('check');
                                $("#bloodSugar").attr("check", true);
                            }else{
//                                  $("#bloodSugar").parent().attr("class","");
                                $("#bloodSugar").attr("check", false);
                            }
                            
                        }
                        if((data.obj[i].name_en == "temperature")){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='temperature']").iCheck('check');
                                $("#temperatureBox").attr("check", true);
                            }else{
//                                  $("#temperature").parent().attr("class","");
                                $("#temperature").attr("check", false);
                            }
                            
                        }
                        if((data.obj[i].name_en == "ECG")){
                            if(data.obj[i].status == 1){
                                jQuery(":checkbox[value='ECG']").iCheck('check');
                                $("#ECG").attr("check", true);
                            }else{
//                                  $("#ECG").parent().attr("class","");
                                $("#ECG").attr("check", false);
                            }
                            
                        }
                    }
                }
                
            }
        });
    },
    modifyWarningDevice: function(obj){
        $.ajax({
            async : true,
            cache : false,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            url : 'healthDataControl.do?modifyWarningDevice',
            data :'{"healthTypeName":"'+ obj.healthTypeName +'","cancel":'+ obj.cancel +'}',
            success : function(data) {
                if(data.success == true){
                    layer.msg("更换成功");
                    smsWarning.getWarningDevice();
                }
                
            }
        });
    }
}