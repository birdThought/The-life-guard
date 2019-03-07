$(function(){	
	var $ = jQuery,
    $list = $('#fileList'),
    // 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,

    // 缩略图大小
    thumbnailWidth = 100 * ratio,
    thumbnailHeight = 100 * ratio,

    // Web Uploader实例
    uploader;
	
	var imgId = 1;

    // 初始化Web Uploader
    uploader = WebUploader.create({

	    // 自动上传。
	    auto: true,
	
	    // swf文件路径
	    swf: getRootPath() + '/static/plugins/webupload/Uploader.swf',
	
	    // 文件接收服务端。
	    server: getRootPath() + '/physicalControl.do?uploadPhysicalPhoto',
	
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#uploader-demo #filePicker',
	
	    // 只允许选择文件，可选。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});

	// 当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail" style="float:left;">' +
	                '<img>' +
	                '<div class="info">' + file.name + '</div>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
	
	    $list.append( $li );
	
	    // 创建缩略图
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }
	
	        $img.attr( 'src', src );
	    }, thumbnailWidth, thumbnailHeight );
	});

	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');
	
	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<p class="progress"><span></span></p>')
	                .appendTo( $li )
	                .find('span');
	    }
	
	    $percent.css( 'width', percentage * 100 + '%' );
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file,response ) {
		//alert($('#uploader-demo'));
		$('<input type="hidden" id="img' + (imgId++) + '" value="'+ response.obj[0] + '">').appendTo(parent.$('.upload'));
	   // $( '#'+file.id ).addClass('upload-state-done');
		var $li = $( '#'+file.id );
		$('<div class="success">文件上传成功</div>').appendTo($li);
		
	});

	// 文件上传失败，现实上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');
	
	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }
	
	    $error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').remove();
	});
});

function getRootPath(){
	
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    projectName = "/lifeshs.web";
    return(localhostPaht+projectName);
    
}