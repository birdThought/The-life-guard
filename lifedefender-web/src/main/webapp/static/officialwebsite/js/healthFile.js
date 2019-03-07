/*
* @Author: Administrator
* @Date:   2016-08-04 11:34:39
* @Last Modified by:   Administrator
* @Last Modified time: 2016-08-06 11:56:20
*/

'use strict';
$(function(){
	$('.addDiet button.sports').on('click',function(){
		layer.open({
			type: 1,
			moveType: 1,
			title:['添加运动','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
			area:['770px','auto'],
			btn:['确定','取消'],
			offset:'100px',
			scrollbar: false,
  			content: $('.dialog_container_sport'),
		});
		$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
	});
		$('.addDiet button.foods').on('click',function(){
			layer.open({
				type: 1,
				moveType: 1,
				title:['添加饮食','text-align:center;font-size:16px;background:#fff;height:50px;line-height:50px'],
				area:['770px','auto'],
				btn:['确定','取消'],
				offset:'100px',
				scrollbar: false,
	  			content: $('.dialog_container'),
			});
		$('.layui-layer-content').attr("style","min-height:300px;");//使弹出层里的按钮增加节点不会显示滚动条
	});
	$('#dietData').highcharts({
        title: {
            text: null,
           
        },
        
        legend:{
       	 layout: 'horizontal',
           align: 'right',
           verticalAlign: 'top',
           x: -10,
           y: 30,
           fontSize:14
       },
       exporting: {
           enabled:false
},
       chart: {
           type: 'column'
       },
        xAxis: {
            categories: ['07/15', '07/16', '07/17', '07/18', '07/19', '07/20',
                '07/21', '07/22']
        },
        yAxis: {
            title: {
                text:null
            }
            
        },
         credits: {
            enabled: false
        },
        plotOptions: {
            spline: {
                marker: {
                    radius:4,
                    lineColor: '#666',
                    lineWidth: 1
                }
            }
        },
        colors: ['gold', '#48c858'],
        series: [{	
        			name: "饮食摄入",
        			data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6],
        		},{
        			 name: "运动消耗",
                     data: [9.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6],
                 }],
    });
});

require.config({
    paths: {
    	echarts: 'static/plugins/echarts-2.2.7'
    }
});
	require(
    [
        'echarts',
        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    ],
function (ec) {
            // 基于准备好的dom，初始化echarts图表
    var myChart = ec.init(document.getElementById('energy'));
    

// 为echarts对象加载数据 
    myChart.setOption(_initoOption(getParam("剩余能量","#f00","",65,"",35))); 
    
}
);

function getParam(dw,color,wname,wvalue,name,value){
	var param = new Object();
param.dw = dw;
param.color = color;
param.wname = wname;//
param.wvalue = wvalue;
param.name = name;
param.value = value;
return param;
}

function _initoOption(_param){

var labelTop = {
    normal : {
        label : {
            show : true,
            position : 'center',
            formatter : '{b}',
            textStyle: {
                baseline : 'bottom'
            }
        },
        labelLine : {
            show : false
        }
    }
};

var labelFromatter = {
    normal : {
        label : {
            formatter : function (params){
                return 100000 - params.value + _param.dw
            },
            textStyle: {
                baseline : 'top'
            }
        },
    color: _param.color
    },
}

var labelBottom = {
    normal : {
        color: '#ccc',
        label : {
            show : true,
            position : 'center'
        },
        labelLine : {
            show : false
        }
    },
    emphasis: {
        color: 'rgba(0,0,0,0)'
    }
};

var radius = [65, 50];
var option = {
    legend: {
        x : 'center',
        y : 'center',
        data:[
            ''
        ]
    },
    series : [
        {
            type : 'pie',
            center: ['50%', '50%'],
            radius : radius,
            x:'0%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:_param.wname, value:_param.wvalue, itemStyle : labelBottom},
                {name:_param.wname, value:_param.value,itemStyle : labelTop}
            ]
        }
    ]
};
return option
}
