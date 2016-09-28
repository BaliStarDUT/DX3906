app.controller('weatherController', function($scope, $http) {
	$scope.weather ;

	$scope.getWeather = function(){
		$http.get("/weather/query.json?cityname=北京")
	    .success(function(response) {
	    	if(response.reason.indexOf("successed")!=-1){
		    	var result = response.result.data;
		    	$scope.weather = result;
		    	var realTimeDate = result.realtime.date;
		    	var realTimetime = result.realtime.time;
		    	$scope.realTime = realTimeDate +" "+realTimetime;
	    	}else{
	    		alert("获取天气信息失败")
	    	}
	    });
	}
	$scope.skyCons = function(){
		 var icons = new Skycons({
	            "color": "#73879C"
	          }),
	          list = [
	            "clear-day", "clear-night", "partly-cloudy-day",
	            "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
	            "fog"
	          ],
	          i;

	        for (i = list.length; i--;)
	          icons.set(list[i], list[i]);
	        icons.play();
	}
	$scope.getWeather();
	$scope.skyCons();
}); 