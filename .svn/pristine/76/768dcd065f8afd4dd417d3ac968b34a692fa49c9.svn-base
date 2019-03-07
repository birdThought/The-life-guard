(function($){
	$.xes={};
	$.xes.template=function(){
		var templateScript=$("script[xesType=temp]");
		var templates={};
		for(var i=0,l=templateScript.length;i<l;i+=1){
			templates[$(templateScript[i]).attr("forObj")]=$(templateScript[i]).html().trim();
		}

		return templates;
	};
    $.xes.require=function(names,fun){
        var head=document.getElementsByTagName("head")[0];
        var script=null;
        var scripts=[];
        var name;
        var total=0;
        var num=0;

        if(typeof names ==="string"){
            name= names;
            script=document.createElement("script");
            script.type="text/javascript";

            head.appendChild(script);

            script.onload=function(){
                fun();
            };
            script.onreadystatechange = function(){
                if (script.readyState == "loaded" || script.readyState == "complete"){
                    script.onreadystatechange = null;
                    fun();
                }
            };
        }else if(typeof names ==="object"){
            total=names.length;
            for(var i= 0;i<total;i+=1){
                scripts[i]=document.createElement("script");
                scripts[i].type="text/javascript";
                scripts[i].src = "http://pic.speiyou.com/haoweilai/js/xes."+names[i]+".js";

                head.appendChild(scripts[i]);
                scripts[i].onload=function(){
                    num+=1;
                    if(num===total){
                        fun();
                    }
                };
                scripts[i].onreadystatechange=(function(index){
                    var _i=index;
                    return function(){
                        if (scripts[_i].readyState == "loaded" || scripts[_i].readyState == "complete"){
                            scripts[_i].onreadystatechange = null;
                            num+=1;
                            if(num===total){
                                fun();
                            }
                        }
                    };
                })(i);
            }
        }
    };

    $.xes.rePosDialog=function(pop){
        var pos= $.xes.getDialogPosition(pop);
        pop.css("top",pos.top+"px");
        pop.css("left",pos.left+"px");
    };

    $.xes.getDialogPosition=function(pop){
        var width=$(window).width();
        var height=$(window).height();
        var boundingClientRect=pop[0].getBoundingClientRect();
        var scrollTop= document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
        var scrollLeft= document.documentElement.scrollLeft || window.pageXOffset || document.body.scrollLeft;
        var popWidth=boundingClientRect.right-boundingClientRect.left;
        var popHeight=boundingClientRect.bottom-boundingClientRect.top;
        return {
            left:(width-popWidth)/2+scrollLeft,
            top:(height-popHeight)/2+scrollTop
        };
    };
})(jQuery);
