jQuery(document).ready(function ($) {
    // If firefox
    if (navigator.userAgent.toLowerCase().match(/firefox/)) {
        $('.browser-warning').removeClass('hidden');
        setTimeout(function () {
            $('.browser-warning').addClass('hidden');
        }, 6 * 1000);
    }
    $('#window').attr('style', '');
    
    initAnimation();
    function initAnimation() {
        setTimeout(function () {
            fyll.go('', function () {
                $("#loginTitle").fadeIn(300);
            });

        }, 2 * 1000);
    }
    createCode();
});
function createCode(){
    code="";
    var codeLength = 5;
    var checkCode = document.getElementById('checkCode')
    var random =  new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    for(var i = 0;i<codeLength;i++){
        var charIndex = Math.floor(Math.random()*36);
        code+=random[charIndex]
    }
    checkCode.value = code;
}
