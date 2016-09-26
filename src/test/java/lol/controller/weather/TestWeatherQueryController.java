package lol.controller.weather;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * root
 * 2016年9月21日 下午11:07:39
 */
public class TestWeatherQueryController {
    private static final Logger log = Logger.getLogger(TestWeatherQueryController.class);

	@Test
	public void testRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		String responseType = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
		System.out.println(responseType);
        log.info(responseType);
	}
	@Test
	public void testWeatherQuery() {
		final String WEATHER_QUERY_HOST = "www.op.juhe.cn/onebox/weather/query";
		RestTemplate restTemplate = new RestTemplate();
		try {
			URL url = new URL("http", WEATHER_QUERY_HOST, 80, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlString = "http://op.juhe.cn/onebox/weather/query?cityname={cityname}&key={key}&dtype={dtype}";
		Map<String ,String> params = new HashMap<String ,String>();
        String APPKEY = "7c3913df657c1d30a9d284305f395e05";
		params.put("cityname","北京");
		params.put("key",APPKEY );
        params.put("dtype","json");
		ResponseEntity<String> weather = restTemplate.getForEntity(urlString, String.class, params);
		String weather2 = 	restTemplate.getForObject(urlString, String.class, params);
		log.info(weather2);
//		weather.getBody()
		Map responseType = restTemplate.getForObject("http://op.juhe.cn/onebox/weather/query?cityname=北京&dtype=&key=7c3913df657c1d30a9d284305f395e05", Map.class);
		System.out.println(responseType);
        log.info(responseType.toString());
	}
	@Test
	public void testQueryWeather() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> weather = 	restTemplate.getForEntity("http://localhost:81/weather/query?cityname=北京",
				Map.class);
//		ResponseEntity<Map> weather = 	restTemplate.getForEntity("http://op.juhe.cn/onebox/weather/query?cityname=北京&dtype=&key=7c3913df657c1d30a9d284305f395e05",
//				Map.class);
		log.info(weather.toString());
	}
}
