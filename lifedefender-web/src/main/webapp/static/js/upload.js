$(function() {
	$("#input").change(function() {

		var path = document.getElementById("input").files[0]; // js 获取文件对象
		/*if(typeof(fileObj) == "undefined" || fileObj.size <= 0) {
			return;
		}*/

		/*var URL = window.URL || window.webkitURL;
		// 通过 file 生成目标 url
		var imgURL = URL.createObjectURL(fileObj);
		$('#img').attr('src', imgURL);*/

		var formFile = new FormData();
		/*formFile.append("action", "UploadVMKImagePath");*/
		formFile.append("path", path); //加入文件对象

		//第二种 ajax 提交

		/*var data = path;*/
		$.ajax({
			url: "/commonControl/uploadFile/img.do",
			data: formFile,
			type: "POST",
			dataType: "json",
			cache: false, //上传文件无需缓存
			processData: false, //用于对data参数进行序列化处理 这里必须false
			contentType: false, //必须
			success: function(result) {
				if(result.success){
					$('#img').attr('src', result.obj);
				}
								
			},
			error: function(event) {
				alert('上传失败');
			}
		})
	})
})