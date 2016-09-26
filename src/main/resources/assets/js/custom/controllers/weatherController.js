angular.module('weatherApp', []).controller('weatherController', function($scope, $http) {
	$http.get("/weather/query.json?cityname=北京")
    .success(function(response) {
    	$scope.weather = response.records;
    	});
}); 