package top.hunaner.lol.web.robot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by James Yang on 2017/6/27 0027 下午 3:10.
 */
@RestController
@RequestMapping("/robot")
public class RobotController {
    private static final Logger log = LoggerFactory.getLogger(RobotController.class);

    @Value("${juhe.cn.API.key}")
    private String juheApiKey;

    @RequestMapping(value="/query",method= RequestMethod.GET)
    @JsonView(String.class)
    public ResponseEntity<Map> queryRobot(@RequestParam(name="userId",required=false,defaultValue="1") String userId,
                                          @RequestParam(name="location",required=false,defaultValue="1") String location,
                                          @RequestParam(name="info",required=true,defaultValue="你是谁") String info,
                                          Model model) {
        log.info("queryRobot:info = {}.", info);
        RestTemplate restTemplate = new RestTemplate();
        Map<String ,String> params = new HashMap<String ,String>();
        final String DEFAULT_DTYPE = "json";
        final String WEATHER_URL = "http://op.juhe.cn/robot/index?"
                + "info={info}&key={key}&dtype={dtype}";
        params.put("info",info);
        params.put("key",juheApiKey);
        params.put("dtype",DEFAULT_DTYPE);
        ResponseEntity<Map> weather = 	restTemplate.getForEntity(WEATHER_URL, Map.class, params);
        log.info("queryWeather:weather= {}.", weather.toString());
        return weather;
    }
}
