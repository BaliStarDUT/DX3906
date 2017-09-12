package top.hunaner.lol.controller.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.hunaner.lol.controller.ResultBean;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    @Value("${juhe.cn.API.key}")
    private String juheApiKey;

    public ResponseEntity<Map>  getWeather(String cityname){
        log.info("queryWeather:cityname = {}.", cityname);
        RestTemplate restTemplate = new RestTemplate();
        Map<String ,String> params = new HashMap<String ,String>();
        final String DEFAULT_DTYPE = "json";
        final String WEATHER_URL = "http://op.juhe.cn/onebox/weather/query?"
                + "cityname={cityname}&key={key}&dtype={dtype}";
        params.put("cityname",cityname);
        params.put("key",juheApiKey);
        params.put("dtype",DEFAULT_DTYPE);
        ResponseEntity<Map> weather = restTemplate.getForEntity(WEATHER_URL, Map.class, params);
		log.info("queryWeather:weather= {}.", weather.toString());
	    return weather;
    }
}
