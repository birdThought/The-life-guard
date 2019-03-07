/**
 * 项目预览
 * @Author wenxian.cai
 * @Date 2017/6/2 10:58
 */

var preview = {};
/**初始化*/
preview.init = function () {
    //图片切换
    $(".lg-img img").not("img:first").css("display","none");
    $(".sm-btn li").click(function(event) {
        $(this).addClass("on").siblings().removeClass('on');
        $(".lg-img img").css("display","none").eq($(this).index()).css("display","block");
    });

    //数量增减
    $('.combo-number-sub').click(function () {
        if (preview.vm.comboNumber >= 2) {
            preview.vm.comboNumber -= 1;
        }
    });
    $('.combo-number-add').click(function () {
        preview.vm.comboNumber += 1;
    });

}

preview.vm = new Vue({
    el: '.vue-preview',
    data: {
        result: null,
        service: null,
        media: {},
        combo: null,
        currentCombo: {},
        comboNumber: 1,
        org: null,
    },
    methods: {

        /**返回修改*/
        back: function () {

        },
        /**初始化地图*/
        initMap: function (map, callBack) {
            // map.centerAndZoom(this.org.street,12);
            var point = new BMap.Point(preview.vm.org.longitude, preview.vm.org.latitude);
            map.centerAndZoom(point, 15);
            var marker = new BMap.Marker(point);  // 创建标注
            map.addOverlay(marker);               // 将标注添加到地图中
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            map.centerAndZoom(point,8);
            setTimeout(function(){
                map.setZoom(14);
            }, 2000);  //2秒后放大到14级
            map.enableScrollWheelZoom(true);
        },
        /**显示套餐具体信息tip*/
        showComboTip: function () {
           /* var tip_index = 0;
            $(document).on('mouseenter', '#mytest', function(){
                tip_index = layer.tips('我出来啦~| ू•ૅω•́)ᵎᵎᵎ', '#mytest', {time: 0});
            }).on('mouseleave', '#mytest', function(){
                layer.close(tip_index);
            })*/
        },
        /**上线、下线项目*/
        changeProjectStatus: function (code) {
            var msg = '确定上线该项目？';
            layer.confirm(msg, {'offset': '50%', 'anim': '0'}, function(index){
                var data = {
                    type: '课堂服务',
                    code: code,
                    status: 2
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    url: '/org/service/updatestatus',
                    data: data,
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if(result.success){
                            setTimeout(function () {
                                window.location.href = '/org/service';
                            }, 1000)
                        }
                    }
                });
                layer.close(index);
            });

        },
    },
    computed: {

    },
    watch: {
        service: function () {
            if (this.service != null) {
                this.media = this.service.media;
            }
        },
        combo: function () {
            if (this.combo != null) {
                if (typeof this.combo == 'string') {
                    this.combo = this.combo.replace(/"\[/g, '[');
                    this.combo = this.combo.replace(/\]"/g, ']')
                    this.combo = JSON.parse(this.combo);
                }
            }
            this.$nextTick(function () {
                //套餐切换
                $('.meal span').on('click', function () {
                    $(this).addClass("on").siblings().removeClass('on');
                    preview.vm.currentCombo = preview.vm.combo[$(this).index() - 1]
                })
                $('.meal span:first').click();


            })

        },
        currentCombo: function () {
            if (this.combo != null) {
                return this.combo[0];
            }
        }
    },
    filters: {


    }
});