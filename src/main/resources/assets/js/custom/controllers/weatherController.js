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
		    	
		    	var skycons = new Skycons({ "color": "#73879C"});
		    	for(var we=0,len=result.weather.length; we<len; we++){
		    		switch(result.weather[we].info.day[1]){
		    			case "多云": 
		    		    	skycons.add("weatherIcon"+(we+1), Skycons.PARTLY_CLOUDY_DAY);break;
		    			case "阴":
		    				skycons.add("weatherIcon"+(we+1),Skycons.CLOUDY);break;
		    			case "晴":
		    				skycons.add("weatherIcon"+(we+1),Skycons.CLEAR_DAY);break;
		    			case "霾":
		    				skycons.add("weatherIcon"+(we+1),Skycons.FOG);break;
		    			case "雾":
		    				skycons.add("weatherIcon"+(we+1),Skycons.FOG);break;
		    			case "雨":
		    				skycons.add("weatherIcon"+(we+1),Skycons.RAIN);break;
		    			default:
		    				skycons.add("weatherIcon"+(we+1),Skycons.CLOUDY);break;

		    		}
		    	}
		    	
		    	skycons.add("weatherIcon6", Skycons.SNOW);
		    	skycons.add("weatherIcon7", Skycons.SNOW);
		    	if($scope.weatherInfo.indexOf("多云")!=-1){
			    	skycons.add("weatherIcon0", Skycons.PARTLY_CLOUDY_DAY);
		    		$scope.weatherInfoIcon = "partly-cloudy-day";
		    	}else if($scope.weatherInfo.indexOf("阴")!=-1){
			    	skycons.add("weatherIcon0", Skycons.CLOUDY);
		    		$scope.weatherInfoIcon = "cloudy";
		    	}	
		    	skycons.play();
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