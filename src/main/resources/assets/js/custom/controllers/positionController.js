app.controller("positionController", function($scope,$http) {
	//定义位置类，赋初始值
	$scope.position = {
			timestamp:"2016-11-07",
			coords:{
				latitude : 10.1212,
				longitude : 12.1212,
				accuracy : 0,
				altitude : 0,
				altitudeAccuracy : 0,
				heading : 0,
				speed : 0
			},
			message:""
	}
	//调用HTML5的地理定位API，需要用户同意
	$scope.getPostion = function(){
		if (navigator.geolocation){
			//第一个参数为获取成功的操作，第二个参数为获取失败的操作
			navigator.geolocation.getCurrentPosition(showPosition,showError);
		}else{
			$scope.position.message="该浏览器不支持获取地理位置。";
		}
	};
	//地理位置获取成功调用，用来显示位置
	function showPosition(position){
		$scope.position.timestamp =position.timestamp;
		$scope.position.coords.latitude = position.coords.latitude;
		$scope.position.coords.longitude =position.coords.longitude;
		$scope.position.coords.accuracy =position.coords.accuracy;
		$scope.position.coords.altitude =position.coords.altitude;
		$scope.position.coords.altitudeAccuracy =position.coords.altitudeAccuracy;
		$scope.position.coords.heading =position.coords.heading;
		$scope.position.coords.speed =position.coords.speed;
		$scope.uploadPosition();
	}
	//地理位置获取失败调用，显示错误信息
	function showError(error)
	{
		switch(error.code) 
		{
			case error.PERMISSION_DENIED:
				$scope.position.message="用户拒绝对获取地理位置的请求。"
				break;
			case error.POSITION_UNAVAILABLE:
				$scope.position.message="位置信息是不可用的。"
				break;
			case error.TIMEOUT:
				$scope.position.message="请求用户地理位置超时。"
				break;
			case error.UNKNOWN_ERROR:
				$scope.position.message="未知错误。"
				break;
		}
	}
	$scope.uploadPosition = function(){
		var url = "/position/upload";
		$http.get(url,{params:{latitude:$scope.position.coords.latitude,
			longitude:$scope.position.coords.longitude,
			accuracy:$scope.position.coords.accuracy,
			altitude:$scope.position.coords.altitude,
			altitudeAccuracy:$scope.position.coords.altitudeAccuracy,
			heading:$scope.position.coords.heading,
			speed:$scope.position.coords.speed,
			timestamp:$scope.position.timestamp}}).success(
					function(response) {
						$scope.position.message = "上传成功";
					});
	}

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
//	 $scope.getActuator();
//	 $scope.getHealth();
//	 $scope.getMetrics();
}); 
