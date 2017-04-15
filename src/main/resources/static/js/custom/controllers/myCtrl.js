app.controller("myCtrl", function($scope,$http) {
	$scope.firstName = "John";
    $scope.lastName= "Doe";
    
	$scope.getActuator = function() {
		$http.get("/actuator").then(function(response){
			if(response.status!="200"){
				allert("failed");
			}
			$scope.urls = response.data._links;
			$scope.keys = response.config;
		});
	};
	$scope.getHealth = function() {
		$http.get("/health").then(function(response){
			if(response.status!="200"){
				allert("failed");
			}
			$scope.health_status = response.data.status;
			$scope.health_diskSpace = response.data.diskSpace;
			$scope.health_db = response.data.db;
			$scope.keys2 = response.config;
		});
	};
	$scope.getMetrics = function() {
		$http.get("/metrics").then(function(response){
			if(response.status!="200"){
				allert("failed");
			}
			$scope.metrics_mem = response.data.mem;
			$scope.metrics_memfree = response.data['mem.free'];
			$scope.metrics_processors = response.data.processors;
			$scope.metrics_instanceuptime = response.data['instance.uptime'];
			$scope.metrics_uptime = response.data.uptime;
			$scope.metrics_heap = response.data.heap;
		});
	};
	$scope.getWeather = function(){
		$http.get("/weather/query.json?cityname=北京")
	    .success(function(response) {
	    	$scope.weather = response.records;
	    	});
	}
	 $scope.getActuator();
	 $scope.getHealth();
	 $scope.getMetrics();
}); 
