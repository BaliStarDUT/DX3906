app.controller('weatherController', function($scope, $http,$interval,$timeout) {
	$scope.weather ;
	$scope.weatherInfo ;
	//调用http服务来获取weather对象
	$scope.getWeather = function(){
		$http.get("/weather/query.json?cityname=北京")
	    .then(function(response) {
	    	if(response.error_code==0){
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
		    	$scope.currentWeek = weekday[result.realtime.week];
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
		    	//调用动画显示。
		    	//注意：因为直接调用方法的时候，浏览器还没有绘制canvas，不能使匹配canvas的id，
		    	//所以这里延迟10ms来调用显示方法，暂时没有找到好的方法。
		    	$timeout(function(){
			    	$scope.showSkyCons();
				},10);
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
	$scope.showSkyCons = function(){
		var result = $scope.weather;
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
    			case "阵雨":
    				skycons.add("weatherIcon"+(we+1),Skycons.RAIN);break;
    			case "小雨":
    				skycons.add("weatherIcon"+(we+1),Skycons.RAIN);break;
    			case "小雪":
    				skycons.add("weatherIcon"+(we+1),Skycons.SNOW);break;
    			case "阵雪":
    				skycons.add("weatherIcon"+(we+1),Skycons.SNOW);break;
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
    	}else if($scope.weatherInfo.indexOf("晴")!=-1){
	    	skycons.add("weatherIcon0", Skycons.CLEAR_DAY);
    		$scope.weatherInfoIcon = "clear_day";
    	}else if($scope.weatherInfo.indexOf("小雨")!=-1){
	    	skycons.add("weatherIcon0", Skycons.RAIN);
    		$scope.weatherInfoIcon = "rain";
    	}else if($scope.weatherInfo.indexOf("阵雨")!=-1){
	    	skycons.add("weatherIcon0", Skycons.RAIN);
    		$scope.weatherInfoIcon = "rain";
    	}else if($scope.weatherInfo.indexOf("霾")!=-1){
	    	skycons.add("weatherIcon0", Skycons.FOG);
    		$scope.weatherInfoIcon = "fog";
    	}else if($scope.weatherInfo.indexOf("小雪")!=-1){
	    	skycons.add("weatherIcon0", Skycons.SNOW);
    		$scope.weatherInfoIcon = "snow";
    	}else if($scope.weatherInfo.indexOf("阵雪")!=-1){
	    	skycons.add("weatherIcon0", Skycons.SNOW);
    		$scope.weatherInfoIcon = "snow";
    	}else if($scope.weatherInfo.indexOf("雾")!=-1){
	    	skycons.add("weatherIcon0", Skycons.SNOW);
    		$scope.weatherInfoIcon = "fog";
    	}				
    	skycons.play();
	}
	$scope.getSchedulTime = function(){
		 $scope.theTime = new Date().toLocaleTimeString();
		  $interval(function () {
		      $scope.theTime = new Date().toLocaleTimeString();
		  }, 1000);
	}
	$scope.getWeather();
	$scope.skyCons();
	$scope.getSchedulTime();
	
}); 