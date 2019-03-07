/**
 * Created by Administrator on 2017/4/28.
 */

var help = {};
help.url = 'http://localhost:3000/';

/**
 * 初始化
 */
help.init = function () {
   help.listHelpColumns();
}
/**
 * 获取帮助中心栏目
 */
help.listHelpColumns = function () {
    var json = {}
    $.ajax({
        async : false,  //同步获取，否则无法渲染样式
        cache : false,
        type : 'GET',
        url: help.url + 'help/index',
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            if(result.success){
                var html = '';
                var parent = '';
                var child = '';
                for (var i in result.obj.columns) {

                    parent += '<p><img src="/static/images/help/help-'+ (Number(i) + 1) +'.png" alt="">'+ result.obj.columns[i].name +'<i></i></p>';

                    for (var j in result.obj.informations) {
                        if (result.obj.columns[i].id == result.obj.informations[j].columnId) {
                            child += '<dd><a href="help-detail.html?id='+ result.obj.informations[j].id +'">'+ result.obj.informations[j].title +'</a></dd>';
                        }
                    }
                    html += '<li>'+ parent + '<dl class="none">' + child + '</dl></li>';
                    $('.columns').append(html);
                    html = '';
                    parent = '';
                    child = '';

                }


                /*$('.columns').append(html);*/
            }
        }
    });
}

/**
 * 具体帮助文档
 */
help.getHelpDetail = function () {
    var id = help.getUrlParams('id');
    $.ajax({
        async : true,  //同步获取，否则无法渲染样式
        cache : false,
        type : 'GET',
        url: help.url + 'help/index/' + id,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend:function(){
        },
        complete:function(){
        },
        success: function(result) {
            if(result.success){
                $('.help-info').append(result.obj.content);
                /*$('.columns').append(html);*/
            }
        }
    });
}

help.getUrlParams = function (name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null){
        return  unescape(r[2]);
    }
    return null;
}