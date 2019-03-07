(function(){
	angular.module("Controllers", [])
	.controller('informationViewController',['$scope','$location',function ($scope, $location) {
		$scope.init = function() {
			$scope.id = $location.search().id;
			var url = '/news/get';
			var data = {
					id:$scope.id
			}
			http.ajax.get(true, false, url, data, http.ajax.CONTENT_TYPE_1, function (result) {
				$scope.$apply(function () {
					$scope.data = result.obj;
					$("#content").html($scope.data.content);
				});
			})
		}
	}]);
}());