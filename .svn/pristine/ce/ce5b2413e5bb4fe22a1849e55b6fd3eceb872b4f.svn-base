<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<style>
    *{ margin: 0; padding: 0}
    ul,li{ list-style: none}
    .tab-container {
        width: 980px;
        height:auto;
        background: #f5f5f5;
    }
    .tab-box {
        margin-left: 30px;
        padding-top: 20px;
        background: #fff;
    }
    .tabClick{overflow: hidden}
    .tabClick li{ height:46px; line-height: 46px; width: 150px; float: left; text-align: center}
    .tabClick li.active{ color: #fff; transition: 0.6s; background: #3BBAFF;
        width: 150px;
        height: 46px;
        border-radius:0 60px 2px 0;}
    .tabCon{ overflow: hidden}
    .tabBox{ position: relative}
    .tabList{word-break: break-all; width:950px;float:left; text-align:center; color:#333;}
    .tabList table {
        height: auto;
        width: 100%;
        border-collapse: collapse;
    }
    .tabList table th {
        background: #eee;
    }
    .tabList table th,
    .tabList table td {
        height: 60px;
        border: 1px solid #ccc;
    }
    .select-cur {
        background: #3BBAFF;
        width: 150px;
        height: 40px;
        border-radius:0 0 0 20px;
    }
</style>
<body>

<div class="tab-container" ng-controller="userStatisticController" ng-init="init()">
    <div class="tab-box">
        <div class="wrap" id="wrap">
            <ul class="tabClick">
                <li class="active" ng-click="init()">本月</li>
                <li ng-click="getRecentlyThreeMonths()">近三月</li>
                <li ng-click="getHalfYear()">近半年</li>
                <li ng-click="getAlmostYear()">近一年</li>
            </ul>
            <div class="lineBorder">
            </div>
            <div class="tabCon">
                <div class="tabBox"  >
                    <div class="tabList">
                        <table   border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <th>月份</th>
                                <th>新用户数量</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in items">
                                <td>{{item.months | date:'yyyy-MM'}}</td>
                                <td>{{item.userNumber}}人</td>
                            </tr>
                            </tbody>
                        </table>
                     </div>
                    <div class="tabList">
                        <table   border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <th>月份</th>
                                <th>新用户数量</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in items">
                                <td>{{item.months | date:'yyyy-MM'}}</td>
                                <td>{{item.userNumber}}人</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tabList">
                        <table   border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <th>月份</th>
                                <th>新用户数量</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in items">
                                <td>{{item.months | date:'yyyy-MM'}}</td>
                                <td>{{item.userNumber}}人</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tabList">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <th>月份</th>
                                <th>新用户数量</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in items">
                                <td>{{item.months | date:'yyyy-MM'}}</td>
                                <td>{{item.userNumber}}人</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script charset="utf-8">
	 var windowWidth = document.body.clientWidth; //window 宽度;
		var wrap = document.getElementById('wrap');
     var tabClick = wrap.querySelectorAll('.tabClick')[0];
     var tabLi = tabClick.getElementsByTagName('li');
     
     var tabBox =  wrap.querySelectorAll('.tabBox')[0];
     var tabList = tabBox.querySelectorAll('.tabList');
     
     var lineBorder = wrap.querySelectorAll('.lineBorder')[0];
     //var lineDiv = lineBorder.querySelectorAll('.lineDiv')[0];
     
     var tar = 0;
     var endX = 0;
     var dist = 0;
     
     tabBox.style.overflow='hidden';
     tabBox.style.position='relative';
  tabBox.style.width=windowWidth*tabList.length+"px";
     
     for(var i = 0 ;i<tabLi.length; i++ ){
           tabList[i].style.width=950+"px";
           tabList[i].style.float='left';
           tabList[i].style.float='left';
           tabList[i].style.padding='0';
           tabList[i].style.margin='0';
           tabList[i].style.verticalAlign='top';
           tabList[i].style.display='table-cell';
     }
     
     for(var i = 0 ;i<tabLi.length; i++ ){
         tabLi[i].start = i;
         tabLi[i].onclick = function(){
             var star = this.start;
             for(var i = 0 ;i<tabLi.length; i++ ){
                 tabLi[i].className='';
             }
             tabLi[star].className='active';
             init.translate(tabBox,950,star);
             endX= -star*950;
         }
     }
     
     function OnTab(star){
         if(star<0){
             star=0;
         }else if(star>=tabLi.length){
             star=tabLi.length-1
         }
         for(var i = 0 ;i<tabLi.length; i++ ){
             tabLi[i].className='';
         }
          tabLi[star].className='active';
         init.translate(tabBox,windowWidth,star);
         endX= -star*windowWidth;
     };
     
     tabBox.addEventListener('touchstart',chstart,false);
     tabBox.addEventListener('touchmove',chmove,false);
     tabBox.addEventListener('touchend',chend,false);
     //按下
     function chstart(ev){
         ev.preventDefault;
         var touch = ev.touches[0];
         tar=touch.pageX;
         tabBox.style.webkitTransition='all 0s ease-in-out';
         tabBox.style.transition='all 0s ease-in-out';
     };
     //滑动
     function chmove(ev){
         var stars = wrap.querySelector('.active').start;
         ev.preventDefault;
         var touch = ev.touches[0];
         var distance = touch.pageX-tar;
         dist = distance;
         init.touchs(tabBox,windowWidth,tar,distance,endX);
     };
     //离开
     function chend(ev){
         var str= tabBox.style.transform;
         var strs = JSON.stringify(str.split(",",1));  
         endX = Number(strs.substr(14,strs.length-18)); 
         
         if(endX>0){
             init.back(tabBox,windowWidth,tar,0,0,0.3);
             endX=0
         }else if(endX<-windowWidth*tabList.length+windowWidth){
             endX=-windowWidth*tabList.length+windowWidth
             init.back(tabBox,windowWidth,tar,0,endX,0.3);
         }else if(dist<-windowWidth/3){
              OnTab(tabClick.querySelector('.active').start+1);
              init.back(tabBox,windowWidth,tar,0,endX,0.3);
         }else if(dist>windowWidth/3){
              OnTab(tabClick.querySelector('.active').start-1);
         }else{
              OnTab(tabClick.querySelector('.active').start);
         }
         var stars = wrap.querySelector('.active').start;
         
     };
	
	
 var init={
     translate:function(obj,windowWidth,star){
         obj.style.webkitTransform='translate3d('+-star*windowWidth+'px,0,0)';
         obj.style.transform='translate3d('+-950+',0,0)px';
         obj.style.webkitTransition='all 0.3s ease-in-out';
         obj.style.transition='all 0.3s ease-in-out';
     },
     touchs:function(obj,windowWidth,tar,distance,endX){
         obj.style.webkitTransform='translate3d('+(950)+'px,0,0)';
         obj.style.transform='translate3d('+(distance+endX)+',0,0)px';
     },
     lineAnme:function(obj,stance){
         obj.style.webkitTransform='translate3d('+stance+'px,0,0)';
         obj.style.transform='translate3d('+stance+'px,0,0)';
         obj.style.webkitTransition='all 0.1s ease-in-out';
         obj.style.transition='all 0.1s ease-in-out';
     },
     back:function(obj,windowWidth,tar,distance,endX,time){
         obj.style.webkitTransform='translate3d('+(950)+'px,0,0)';
         obj.style.transform='translate3d('+(950)+',0,0)px';
         obj.style.webkitTransition='all '+time+'s ease-in-out';
         obj.style.transition='all '+time+'s ease-in-out';
     }
 }
</script>
<!-- 代码部分end -->

</body>
