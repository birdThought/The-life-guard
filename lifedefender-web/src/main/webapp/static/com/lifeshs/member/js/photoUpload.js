var photoUpload = {}

/**
 * 单文件头像上传(未确认保存路径)
 * <p>
 * 使用ajax上传文件，然后返回相对路径，在img标签中展示图片，同时保存相对路径到relativeInput中
 * 
 * @param source 数据源
 * @param url 上传的url
 * @param fileInputId type=file 的id
 * @param img 需要展示图片的img对象
 * @param relativeInput 存放相对路径的值对象
 */
photoUpload.showPreview = function(source, url, fileInputId, img, relativeInput, callback) {
	if (photoUpload.validata(source)) {
		// 上传图片

		photoUpload.ajaxFileUpload(url, fileInputId, img, relativeInput, null,
				null, null, callback);
	}
}

/**
 * 确认上传图片
 * 
 * @param url 处理上传操作的url
 * @param relativePath 图片的相对地址
 * @param userId 用户的id
 * @param imgId 展示图片id
 */
photoUpload.upload = function(url, relativePath, userId, imgId) {
	jQuery.ajax({
		async : true,
		cache : false,
		type : 'POST',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		url : url,
		data : 'relativePath=' + relativePath + '&userId=' + userId,
		beforeSend : function() {

		},
		success : function(result) {
			layer.msg(result.msg);
			if (result.success) {
				jQuery("#" + imgId).attr("src", relativePath);
				_setUserMessage();
			}
		},
		complete : function() {
			// setTimeout(function(){
			// location.href = redirectUrl;
			// }, 1000);
		}
	});
}

/**
 * 上传头像（上传与保存路径）
 * 
 * @param source
 * @param uploadUrl
 * @param modifyUrl
 * @param fileInputId
 * @param imgElememtId
 * @param userId
 */
photoUpload.previewAndUpload = function(source, uploadUrl, modifyUrl,
		fileInputId, imgElementId, userId) {
	if (photoUpload.validata(source)) {
		// 上传图片
		photoUpload.ajaxFileUpload(uploadUrl, fileInputId, imgElementId, null,
				modifyUrl, userId);

	}
}

/**
 * 上传头像数据格式校验
 * 
 * @param source
 *            数据源
 */
photoUpload.validata = function(source) {
	// 判断文件类型与大小
	if (!/image\/\w+/.test(source.type)) {
		layer.msg("请确保上传文件类型为图片");
		return false;
	}
	if (source.size == 0) {
		layer.msg("图片大小不能是0KB");
		return false;
	}
	if (source.size > (1024 * 1024)) {
		layer.msg("图片大小不能超过1MB");
		return false;
	}
	return true;
}

/**
 * ajax上传文件
 * 
 * @param uploadUrl 上传操作处理的url
 * @param fileInput 文件对象
 * @param imgId 展示图片
 * @param relativeInput 保存相对路径的input对象，如果为null或空，就不保存
 * @param modifyUrl 修改操作处理的url
 * @param userId 用户id
 * @param redirectU重定向url
 */
photoUpload.ajaxFileUpload = function(uploadUrl, fileInput, imgId,
		relativeInput, modifyUrl, userId, redirectUrl, callback) {
	
	fileUpload({
		id: path,  //path
		url: uploadUrl,
		limit: String(1024*1),
		mutiple: false,
		error: function(errorCode) {
			switch(errorCode) {
			case 0:
				layer.msg("不支持上传的类型");
				break;
			case 1:
				layer.msg("超过了指定大小");
				break;
			}
		},
		success: function() {
			layer.msg("上传成功");
            if (typeof callback == 'function') {
                return callback(true);
            }
		},
		fail: function() {
			layer.msg("上传失败");
		},
		progress: function(evt) {
			console.log(evt + "%");
		},
		callback: function(result) {
			var obj = result.obj;
//			for(var i = 0; i < obj.length; i++) {
//				var img = obj[i];
//				// 将图片存储到imgs中
//				imgControl.pushWithMinIndex(img);
//			}
//			imgControl.printImg();
			jQuery(imgId).attr("src", obj);
			// 看情况赋值，如果不为null与空就赋值
			if (relativeInput != null && relativeInput != "") {
				jQuery(relativeInput).val(obj);
			} else {
				var userId = jQuery("#id").val();
				photoUpload.upload(modifyUrl, obj, userId, imgId);
			}

			/** 重新添加一个file类型的input标签 */
//			var $file_old = jQuery("[name='files']");
//			var $file_new = jQuery("<input name='files' id='files' type='file' class='file caseFile' multiple />");
//			$file_old.after($file_new);
//			$file_old.remove();
		}
	});
	
//	$.ajaxFileUpload({
//		type : 'POST',
//		url : uploadUrl, // 用于文件上传的服务器端请求地址
//		secureuri : false, // 是否需要安全协议，一般设置为false
//		fileElementId : fileInputId, // 文件上传域的ID
//		dataType : 'HTML', // 返回值类型 一般设置为json
//		success : function(data) { // 服务器成功响应处理函数
//
//			var start = data.indexOf(">");
//			var end = data.lastIndexOf("<");
//
//			if (start != -1 && end != -1) {
//				data = data.substring(start + 1, end);
//				data = eval("(" + data + ")");
//			}
//			if (data.success) {
//				jQuery(imgId).attr("src", data.obj);
//				// 看情况赋值，如果不为null与空就赋值
//				if (relativeInput != null && relativeInput != "") {
//					jQuery(relativeInput).val(data.obj);
//				} else {
//					var userId = jQuery("#id").val();
//					photoUpload.upload(modifyUrl, data.obj, userId, imgId);
//				}
//			}
//		}
//	});
}
