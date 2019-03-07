(function() {
    angular.module('Controllers', [])
    .controller('statisticsController', ['$scope', function($scope) {
        
        $scope.data = {
            statisticsList: [],
        }
        
        $scope.fn = {
            init: function() {
                $scope.service.listStatistics();
            }
        }
        
        $scope.service = {
            listStatistics: function() {
                var url = "/serve/statistics";
                http.ajax.get(true, false, url, null, http.ajax.CONTENT_TYPE_1, function(result) {
                    $scope.$apply(function() {
                        $scope.data.statisticsList = result.data;
                    });
                });
            }
        }
    }]);
}());