var fileUpload = function(data) {
	this.url = data['url'];
	this.fileId = data['id'];
	this.targetType = data['type'] == undefined ? 'jpg,bmp,png,jpeg,gif'
			: data['type'].toLowerCase();
	this.limit = parseInt(data['limit']);// 大小，以KB为单位
	this.success = data['success'];
	this.error = data['error'];// 0代表格式不对，1代表超出大小，2代表数量选择超过上限
	this.fail = data['fail'];
	this.progress = data['progress'];
	this.isMutiple = data['mutiple'] == undefined ? false : data['mutiple'];//是否多选
	this.nameValuePair = data['json'];
	this.callback = data['callback'];
	this.paramName = data['paramName'] == undefined ? this.fileId.id
			: data['paramName'];
	this.uploadProgress = function(evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100 / evt.total);
			if (progress != undefined) {
				progress(percentComplete);
			}
		}
	};
	this.uploadComplete = function(evt) {
		if (success != undefined)
			success();
	};
	this.uploadFailed = function(evt) {
		if (fail != undefined)
			fail();
	};
	this.uploadCanceled = function(evt) {
		if (fail != undefined)
			fail();
	};
	this.checkFile = function(file) {
		if (!file)
			return false;
		var fileSub = file.name.substr(file.name.lastIndexOf(".") + 1,
				file.name.length).toLowerCase();
		if (targetType == undefined) {
			if (fileSub != 'jpg' && fileSub != 'bmp' && fileSub != 'png'
					&& fileSub != 'jpeg' && fileSub != 'gif') {
				error(0);
				return false;
			}
		} else {
			var subLimit = targetType.split(",");
			var c = "";
			for ( var s in subLimit) {
				c += "fileSub!='" + subLimit[s] + "'&&";
			}
			c = eval(c.substr(0, c.length - 2));
			if (c) {
				error(0);
				return false;
			}
		}
		var fileSize = (Math.round(file.size * 100 / 1024) / 100);
		if (fileSize <= 0) {
			error(0);
			return false;
		}
		if (limit != undefined && fileSize > limit) {
			error(1);
			return false;
		}
		return true;
	};
	this.upload = function() {
		var fd = new FormData();
		if (isMutiple) {
			for (var i = 0; i < this.fileId.files.length; i++) {
				var file = this.fileId.files[i];
				if (!checkFile(file))
					return;
				fd.append(this.paramName, file);
			}
		} else {
			var file = this.fileId.files[0];
			if (!checkFile(file))
				return;
			fd.append(this.paramName, file);
		}
		if (nameValuePair != undefined) {
			for ( var key in nameValuePair) {
				fd.append(key, nameValuePair[key]);
			}
		}

		var xhr = new XMLHttpRequest();
		xhr.upload.addEventListener("progress", uploadProgress, false);
		xhr.addEventListener("load", uploadComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.addEventListener("abort", uploadCanceled, false);
		xhr.open("post", url);
		xhr.send(fd);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				if (callback != undefined) {
					callback(JSON.parse(xhr.responseText));
				}
			}
		};
	};
	upload();
};

// 上传并展示图片的方法
var photoUtil = function(data) {
	 this.targetId = document.getElementById(data['targetId']);// input为file类型的id
	 this.displayId = document.getElementById(data['displayId']);// 展示图片的img标签id
	 this.uploadFail = data['uploadFail'];// 上传失败回调
	 this.uploadSuccess = data['uploadSuccess'];// 上传成功的回调
	 this.paramName = data['paramName'];// 上传的字段名
	 this.url = data['url'];// 上传的路径
	 this.limit = data['limit'];// 限制的大小
	this.uploadFile = function() {
		var data = {
			id : targetId,
			url : url,
			limit : limit,
			error : function(result) {
				switch (result) {
				case 0:
					layer.msg("请上传正确的图片");
					break;
				case 1:
					layer.msg("图片大小不能超过3MB");
					break;
				}
			},
			fail : function() {
				layer.msg("上传失败");
				displayId.src = '';
				if (uploadFail != undefined)
					uploadFail();
			},
			progress : function(pro) {

			},
			callback : function(json) {
				var file = targetId.files[0];
				var reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onload = function(e) {
					displayId.src = this.result;
				}
				if (uploadSuccess != undefined)
					uploadSuccess(json);
			}
		}
		if (paramName != undefined)
			data.paramName = paramName;
		fileUpload(data);
	}
	this.fileChange = function() {
		uploadFile();
	};
	if (!targetId.hasAttribute("onchange")) {
		targetId.onchange = function() {
			fileChange();
		}
	}
	targetId.click();
}
