/**
 * 门店信息
 * @Author wenxian.cai
 * @Date 2017/6/8 16:24
 */

var store = {};

/**初始化*/
store.init = function () {

    /*图片上传*/
    var url = '/commonControl/uploadFile/img.do';
    var method = 'post';

    var element = '.path-1';
    //绑定图片1
    lay.uploadFile(url, method, element, function (results) {
        $('.store-image-show-1').attr('src', results.obj);
        $('.store-image-hidden-1').val(results.obj);
    });
}
/**vm实例*/
store.vm = new Vue({
    el: '.vue-store',
    data: {
        results: null,
        orgType: null,
    },
    methods: {

        /**初始化地图*/
        initMap: function (map, callBack) {
            // map.centerAndZoom(this.org.address,12);
            var point = new BMap.Point(store.vm.results.longitude, store.vm.results.latitude);
            if (store.vm.results.longitude == null || store.vm.results.latitude == null) {
                map.centerAndZoom(this.org.street,12);
            }
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
        /**点击图片触发事件*/
        popupImg: function (type) {
            switch (type) {
                case 1:
                    $('.path-1').click();
                    break;
                case 2:
                    $('.path-2').click();
                    break;
                case 3:
                    $('.path-3').click();
                    break;
                case 4:
                    $('.path-4').click();
                    break;
            }

        },
        /**修改门店信息*/
        updateInfo: function () {

            layer.confirm('确定保存门店信息？', {'offset': '400px'}, function (index) {
                var json = $('.store-form').serializeJSON();
                json.detail = Editor.filterScript(Editor.getContent());
                if (json.logo == '') {
                    delete json.logo;
                }
                delete  json.editor;
                $.ajax({
                    async : true,
                    cache : false,
                    type: 'POST',
                    url: '/org/profile?updatestore',
                    data: JSON.stringify(json),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    beforeSend:function(){
                        layer.load();
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        layer.msg(result.msg, {offset: '50%'});
                        if(result.success){
                        }
                    }
                });
                layer.close(index);
            });
        }


    },
    computed: {

    },
    watch: {
        results: function () {
            this.$nextTick(function () {
                Editor.setHTMLContent(this.results.detail);
            })
        }

    },
    filters: {

    }
});

/**富文本编辑器*/
var Editor = {
    uEditor: null,
    init: function (data) {
        this.uEditor = UE.getEditor('editor', {
            toolbars: [['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'strikethrough',
                'horizontal', 'removeformat', '|', 'forecolor',
                'backcolor', 'insertorderedlist', 'insertunorderedlist',
                'selectall', '|', 'rowspacingtop', 'rowspacingbottom',
                'lineheight', '|', 'customstyle', 'paragraph',
                'fontfamily', 'fontsize', '|', 'directionalityltr',
                'directionalityrtl', 'indent', '|', 'justifyleft',
                'justifycenter', 'justifyright', 'justifyjustify', '|',
                'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload']],
            enableAutoSave: false,
            enableContextMenu: false,
            elementPathEnabled: false,
            maximumWords: 300,
            imageFieldName: 'path',
            imageActionName: 'uploadimage',
            initialFrameHeight: 200
        });
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function (action) {
            if (action == 'uploadimage') {
                return '/commonControl/uploadFile/img.do';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    },
    getContent: function () {
        return this.uEditor.getContent();
    },
    setHTMLContent:function(content){
        this.uEditor.ready(function () {
            Editor.uEditor.setContent(content, false);
        });
    },
    filterScript: function (s) {
        return s.replace(/<script.*?>.*?<\/script>/ig, '');
    }
}