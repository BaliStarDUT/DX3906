app.controller('weatherController', function($scope, $http) {
	$scope.weather ;
	$scope.weatherInfo ;
	$scope.getWeather = function(){
		$http.get("/weather/query.json?cityname=北京")
	    .success(function(response) {
	    	if(response.reason.indexOf("successed")!=-1){
		    	var result = response.result.data;
		    	$scope.weather = result;
		    	var realTimeDate = result.realtime.date;
		    	var realTimetime = result.realtime.time;
		    	$scope.realTime = realTimeDate +" "+realTimetime;
		    	var weekday=new Array(7);
		    	weekday[0]="Sunday";
		    	weekday[1]="Monday";
		    	weekday[2]="Tuesday";
		    	weekday[3]="Wednesday";
		    	weekday[4]="Thursday";
		    	weekday[5]="Firday";
		    	weekday[6]="Saturday";
		    	$scope.currentWeek = weekday[(result.realtime.week+1)];
		    	$scope.currentDateTime = $scope.realTime;
		    	$scope.cityName = result.realtime['city_name'];
		    	$scope.weatherInfo = result.realtime.weather.info;
		    	$scope.temperature = result.realtime.weather.temperature;
		    	$scope.humidity = result.realtime.weather.humidity;
		    	$scope.windDirect = result.realtime.wind.direct;
		    	$scope.windPower = result.realtime.wind.power;
		    	$scope.windSpeed = result.realtime.wind.windspeed;
		    	$scope.dateMoon = result.realtime.moon;
		    	$scope.airQuality = result.pm25.pm25.quality;
		    	$scope.airDes = result.pm25.pm25.des;
		    	$scope.days5Weather = result.weather;
		    	if($scope.weatherInfo.indexOf("多云")!=-1){
		    		$scope.weatherInfoIcon = "partly-cloudy-day";
		    	}
	    	}else{
	    		 console.log("获取天气信息失败");
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