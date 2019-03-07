/**
 * Created by Administrator on 2017/11/14 0014.
 */
$(function () {
    $(".dropdown").mouseenter(function () {
        $(this).addClass("open");
        $(this).find('a.dropdown-toggle').addClass('hover').parent('li').siblings('li').find('a.dropdown-toggle').removeClass('hover');
    });

    $(".dropdown").mouseleave(function(){
        $(this).removeClass("open");
        $(this).find('a.dropdown-toggle').removeClass("hover");
    })
});

var common = {};

/**
 * 导航栏添加样式
 * author: wenxian.cai
 * date: 2017/11/24 15:12
 */
common.activityMenu = function (parent, child) {
    child = child * 2 - 1;
    $('.navbar-nav li:nth-of-type('+ parent +') .dropdown-toggle').addClass('active');
    $('.navbar-nav li:nth-of-type('+ parent +') .dropdown-menu li:nth-of-type('+ child +')>a').addClass('active');
};
common.icon=function(){
    var link = $('<link type="image/x-icon" rel="shortcut icon" href="/static/officialwebsite/v2.5.0/images/favicon.ico">')
    var head = $('head');
    head.append(link)
};
common.advice=function(){
  var adviceArea=$('.advice');
  var adviceImg = $('.advice img');
   adviceArea.hover(function(){
       console.log(adviceArea);
       adviceImg.attr('src','/static/officialwebsite/v2.5.0/images/advice1.png')
   },function(){
       adviceImg.attr('src','/static/officialwebsite/v2.5.0/images/advice.png')
   })
};
//downloadCode
common.downloadCode = function(url){
    window.location.href=url
};
common.setCase = function(){
    window.onresize=function(){
        var $width = $(window).width();
        var $height = $('.middle-main-content:nth-child(2)').height();
        if($width>650&&$width<1200){
            $('.middle-main-content div:nth-child(2)').css({'height':'auto'});
        }else{
            $('.middle-main-content div:nth-child(2)').css({'height':'100%'});
        }
    };
};
setTimeout(function(){
    common.icon();
    common.advice();
    common.setCase();

},100);

