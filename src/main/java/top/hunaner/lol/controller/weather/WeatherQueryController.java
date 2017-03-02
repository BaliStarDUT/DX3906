package top.hunaner.lol.controller.weather;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * root
 * 2016年9月21日 下午11:04:30
 */

@Controller
@RequestMapping("/weather")
public class WeatherQueryController {
	
    private static final Logger log = LoggerFactory.getLogger(WeatherQueryController.class);
	
    private static final String APPKEY = "7c3913df657c1d30a9d284305f395e05";

	@RequestMapping(value="/query",method=RequestMethod.GET)
	@JsonView(String.class)
	public ResponseEntity<Map> queryWeather(@RequestParam(name="cityname",required=true,defaultValue="北京") String cityname, 
			Model model) {
		log.info("queryWeather:cityname = {}.", cityname);
		RestTemplate restTemplate = new RestTemplate();
		Map<String ,String> params = new HashMap<String ,String>();
		final String DEFAULT_DTYPE = "json";
		final String WEATHER_URL = "http://op.juhe.cn/onebox/weather/query?"
				+ "cityname={cityname}&key={key}&dtype={dtype}";
		params.put("cityname",cityname);
		params.put("key",APPKEY );
        params.put("dtype",DEFAULT_DTYPE);
		ResponseEntity<Map> weather = 	restTemplate.getForEntity(WEATHER_URL, Map.class, params);
		log.info("queryWeather:weather= {}.", weather.toString());
	    return weather;
	}
	
}
