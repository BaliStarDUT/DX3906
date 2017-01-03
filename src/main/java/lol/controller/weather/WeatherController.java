package lol.controller.weather;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonView;

import lol.config.ApplicationKeyConfig;

/**
 * root
 * 2016年9月21日 下午11:04:30
 */

@Controller
@RequestMapping("/weather")
public class WeatherController {
	
    private static final Logger log = Logger.getLogger(WeatherController.class);
    
    @Resource(name="ApplicationKey")
    ApplicationKeyConfig applicationKeyConfig;

	@RequestMapping(value="/query",method=RequestMethod.GET)
	@JsonView(String.class)
	public ResponseEntity<Map> queryWeather(@RequestParam(name="cityname",required=true,defaultValue="北京") String cityname, 
			Model model) {
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.DEBUG);
		log.debug("queryWeather:cityname = "+cityname);
		log.info("queryWeather:cityname = "+cityname);
		RestTemplate restTemplate = new RestTemplate();
		Map<String ,String> params = new HashMap<String ,String>();
		final String DEFAULT_DTYPE = "json";
		final String WEATHER_URL = "http://op.juhe.cn/onebox/weather/query?"
				+ "cityname={cityname}&key={key}&dtype={dtype}";
		params.put("cityname",cityname);
		params.put("key",applicationKeyConfig.juheDataAppKey);
        params.put("dtype",DEFAULT_DTYPE);
		ResponseEntity<Map> weather = 	restTemplate.getForEntity(WEATHER_URL, Map.class, params);
//		if(weather.getStatusCode().value() == 200){
//			if(weather.getBody().containsKey("reason")){
				String reason = (String) weather.getBody().get("reason");
				if(reason.startsWith("successed")){
					log.debug("queryWeather:weather= "+weather.toString());
				}else {
					log.error("queryWeather:weather= "+weather.toString());
				}
//			}
//		}
		
	    return weather;
	}
	
}
