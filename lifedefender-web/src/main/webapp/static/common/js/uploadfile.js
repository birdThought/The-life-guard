/**
 * @Description: 结合layui上传文件
 * @author: wenxian.cai
 * @create: 2017/4/25 15:24
 */

var lay = {};
/**
 * @Description: 上传文件 （上传图片专用）
 * @author: wenxian.cai
 * @create: 2017/4/25 15:28
 * @param: url 文件上传路径
 * @param: method 服务器方法(post, get)
 * @element: 上传文件元素
 */
lay.uploadFile = function (url, method, element, callback) {
    layui.use('upload', function () {
        layui.upload({
            url: url,
            elem: element,
            method: method,
            unwrap: true,
            // ext: 'jpg|png',
            before: function () {
                /*var size = $(element)[0].files[0].size/1000;
                console.log('size:', size);
                if (size > 410) {
                    layer.msg('文件大小超出限制!', {icon: 3, offset: '50%'});
                    return;
                }*/
                /*var photoExt = $(element).value.substr($(element).value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
                if (photoExt != 'jpg' && photoExt != 'png') {
                    layer.msg('文件格式不合法', {icon: 2, offset: '50%'});
                }*/
            },
            success: function (res) {
                if (res.success) {
                    if (typeof callback == 'function') {
                        return callback(res);
                    }
                }
                layer.msg(res.msg, {icon: 2, offset: '50%'});

            }
        });
    });
}