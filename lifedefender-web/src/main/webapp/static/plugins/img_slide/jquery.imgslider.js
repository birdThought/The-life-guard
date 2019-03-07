/**
 * ????????
 * @param op+options
 * @author walden.wang
 */
(function($){
	$.fn.extend({
		imgslider:function(options){
			//?????????
			var op=$.extend({defTarget:"_blank",imgShowClass:"imgShow",footerClass:"footer",titleShowClass:"titleShow",imgIndexClass:"imgIndex",selectedClass:"selected",duration:2000},options);
			return this.each(function(){
				initImgSlider(this);
			})
			function initImgSlider(item){
				var autoSlider={};
				var $this=$(item);
				var currentIndex=0;
				var divStr="<div></div>";
				var imgsCount=$this.children().size();
				$this.wrapInner(divStr);
				var $imgShow=$(">div:first",$this).addClass(op.imgShowClass);
				var $footer=$(divStr).appendTo($this).addClass(op.footerClass);
				var $titleShow=$(divStr).appendTo($this).addClass(op.titleShowClass);
				var imgIndexStr=generateImgIndexList(imgsCount);
				var $imgIndex=$(imgIndexStr).appendTo($this).addClass(op.imgIndexClass);
				var $imgIndexList=$(">li",$imgIndex);
				var $imgLinks=$(">a",$imgShow);
				var $imgs=$(">a>img",$imgShow);
				var $imgTitles=new Array();
				$imgs.each(function(index,item){
					$imgTitles[index]=$(item).attr("title");
				});
				$imgLinks.click(function(e){
					window.open($(this).attr("href"),$(this).attr("target")?$(this).attr("target"):op.defTarget);
					//????????????????
					e.preventDefault();
				});
				//?????????,????????
				$titleShow.text($imgTitles[currentIndex]);
				$imgIndexList.eq(currentIndex).addClass(op.selectedClass);
				$imgs.each(function(index,item){
					currentIndex==index?$(item).show():$(item).hide();
				});
				//?????????????
				$imgIndexList.click(function(){
					currentIndex=$(this).text()-1;
					$titleShow.text($imgTitles[currentIndex]);
					//???IE8????????????????BUG
					$imgIndex[0].style.background="";
					//???????sibling()???? ???????
					$imgIndexList.each(function(index,item){
						currentIndex==index?item.className=op.selectedClass:item.className="";
					});
					//???????? ???????
					$imgs.each(function(index,item){
						if(currentIndex==index){
							$(item).fadeIn(op.duration);
						}else if($this.is(":visible")){
							$(item).fadeOut(op.duration);
						}
					});
				});
				//???????????
				$titleShow.click(function(){
					var url=$imgLinks.eq(currentIndex).attr("href");
					window.open(url,$(this).attr("target")?$(this).attr("target"):op.defTarget);
				});
				//??????????????
				autoSlider=function(){
					currentIndex=currentIndex>=imgsCount-1?0:++currentIndex;
					$imgIndexList.eq(currentIndex).trigger("click");
				}
				var autoHandler=setInterval(autoSlider,2*op.duration);
				//?????????????????????,?????????????
				$this.hover(function(){
					clearInterval(autoHandler);
				},function(){
					autoHandler=setInterval(autoSlider,2*op.duration);
				});
			}
			//????ul??imgsCount??li
			function generateImgIndexList(imgsCount){
				var imgIndexStr="<ul>";
				for(var i=1;i<=imgsCount;i++){
					imgIndexStr+="<li>"+i+"</li>";
				}
				return imgIndexStr+="</ul>";
			}
		}
	});
})(jQuery);