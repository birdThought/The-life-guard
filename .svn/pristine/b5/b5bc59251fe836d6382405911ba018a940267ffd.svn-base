var dataInit = function(){

};
var CreateLine = function(){
    var line = $('#line');
    var $height =$('.history').height();
    var HT = $('.measure-date');
    line.css({'position':'absolute','height':$height-50+'px','width':'0.02rem','background':'#ddd','left':'0.4rem','top':'0.3rem'});
    var round = $("<s></s>");var round_one = $("<s></s>");
    round.css({'position':'absolute','width':'0.1rem','height':"0.1rem",'border-radius':'50%','border':'0.01rem solid #ccc','left':'-0.65rem','background':'#fff','z-index':'9999','top':'6px'});
    HT.append(round);
};
var gettime = function(t){
    var _time = new Date(t);
    var year = _time.getFullYear();
    var month = _time.getMonth()+1;
    var date = _time.getDate();
    var hour = _time.getHours();
    var minute = _time.getMinutes();
    return   year+"-"+getzf(month)+"-"+getzf(date)+" "+getzf(hour)+":"+getzf(minute);
};
function getzf(num){
    return num>=10?num:'0'+num;
}
var measureResult = function(){
    var spans = $('.lastest-content-right li > span ');
    if(spans.hasClass('red')){
        $('.result-show').html('异常').css({'color':'#f40'});
        $('.left').css({'border-color':'#f40'});
        $('.right').css({'border-color':'#f40'});
    }else {
        $('.result-show').html('正常').css({'color':'#44c660'});
        $('.left').css({'border-color':'#44c660'});
        $('.right').css({'border-color':'#44c660'});
    }
};
var finish = function () {
    setTimeout(function () {
        obj.measureResult();
    },350);
    var obj = {
        measureResult:function () {
            var spans = $('.lastest-content-right li > span ');
            if(spans.hasClass('red')){
                $('.result-show').html('异常').css({'color':'#f40'});
                $('.left').css({'border':'0.1rem solid #f40'});
                $('.right').css({'border':'0.1rem solid #f40'});
            }else {
                $('.result-show').html('正常').css({'color':'#44c660'});
                $('.left').css({'border':'0.1rem solid #44c660'});
                $('.right').css({'border':'0.1rem solid #44c660'});
            }
        }
    };
};
var result_judge = function(val,valArea){
    var area = valArea.split('-');
    if(val>=area[0]&&val<=area[1]){
        return 'white';
    }else {
       return 'red';
    }
};
var panduan = function(str){
    if(str==0){
        return '正常'
    }else {
        return '异常'
    }
};
