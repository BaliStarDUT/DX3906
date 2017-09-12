package top.hunaner.lol.controller.weather;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonView;
import top.hunaner.lol.controller.ResultBean;

/**
 * root
 * 2016年9月21日 下午11:04:30
 */

@Controller
@RequestMapping("/weather")
@RefreshScope
public class WeatherQueryController {
	
    private static final Logger log = LoggerFactory.getLogger(WeatherQueryController.class);
	
	@Value("${spring.profiles}")
	private String profile;

	@Autowired
	WeatherService weatherService;

	@GetMapping("/hello")
	public String hello() {
		log.info(profile);
		return this.profile;
	}

	@RequestMapping(value="/query",method=RequestMethod.GET)
	@JsonView(String.class)
	public ResponseEntity<Map> queryWeather(@RequestParam(name="cityname",required=true,defaultValue="北京") String cityname,
			Model model) {
		return weatherService.getWeather(cityname);
	}
}
