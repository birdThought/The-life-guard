window.onload=function () {
	setTimeout(function(){
		 var percent=0;
		    var sum = parseInt(document.getElementById('num').innerHTML);
		    var loading=setInterval(function(){
		        if(percent>100){
		            percent=0;
		            $('.circle').removeClass('clip-auto');
		            $('.right').addClass('wth0');
		        }else if(percent>50){
		            $('.circle').addClass('clip-auto');
		            $('.right').removeClass('wth0');
		        }

		        $('.left').css("-webkit-transform","rotate("+(18/5)*percent+"deg)");
		        percent++;
		        if(percent==sum){
		            clearInterval(loading)
		        }
		    },30);

    },500)
   
    var btns = $('.content-middle button')
    btns.click(function () {
        $(this).css({'background':'#44c660','color':'#fff'}).siblings('button').css({'background':'#fff','color':'#000'})
    })

}