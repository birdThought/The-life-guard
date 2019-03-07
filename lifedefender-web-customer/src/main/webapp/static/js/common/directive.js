// app.directive('finishecreate',['$timeout','$compile',function($timeout,$compile){
//     return {
//         restrict:'ECMA',
//         replace:true,
//         link:function(scope,element,attr){
//             if(scope.$last==true){
//                 $timeout(function(){
//                     var btn = '<button type="button" id="queren" ng-click="addmeal()" style="position: absolute;left: 50%;bottom:-520px;margin-left: -50px">确认添加</button>'
//                     $('#notarizeAdd').append(btn)
//                 },0)
//             }
//         }
//     }
// }]);