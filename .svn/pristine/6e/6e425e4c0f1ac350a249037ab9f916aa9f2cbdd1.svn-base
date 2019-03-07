(function() {
	angular.module("Controllers", []).controller('sendSmsController',
			[ '$scope', '$rootScope', function($scope, $rootScope) {

				/** 发送短信 */
				$scope.sendSms = function() {
					layer.confirm("你确定要发送该短信吗", function(index) {
						var data = {
							mobile : $('#mobile').val(),
							content : $('#content').val()
						}
						console.log(data);
						if ($.trim(data.mobile) == '') {
							layer.msg('请输入接收号码！');
							layer.close(index);
							return false;
						}
						var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
						if (!myreg.test(data.mobile)) {
		                    layer.msg("请输入正确的手机号码！");
		                    layer.close(index);
		                    return false;
		                }
						if ($.trim(data.content) == '') {
			                layer.msg("请输入内容！");
			                layer.close(index);
			                return false;
			            }
						var url = '/sms/data/send';
						http.ajax.post(true, false, url, JSON.stringify(data), http.ajax.CONTENT_TYPE_2, function(result) {
			        		$scope.$apply(function() {
			        			if(!result.success) {
			                		layer.msg('发送失败')
			                		layer.close(index);
			                	} else {
			                		layer.msg('发送成功');
			                    	layer.close(index);
			                	}
			        		})
			        	})
					})
				}
			} ]);
}());