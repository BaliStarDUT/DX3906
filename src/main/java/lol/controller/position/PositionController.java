package lol.controller.position;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lol.config.ApplicationKeyConfig;
import lol.entity.LolheroForm;
import lol.entity.position.Position;
import lol.service.positon.PositionService;

/**
 * 
 * 2016年11月7日 下午1:57:29
 */
@Controller
@RequestMapping("/position")
public class PositionController {
    private static final Logger log = Logger.getLogger(PositionController.class);
    
    @Resource(name="ApplicationKey")
    private ApplicationKeyConfig applicationKeyConfig;
    
    @Autowired
    private PositionService positionService;
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	@ResponseBody
    public String uploadPosition(Model model,
    		@RequestParam(required=true) Float latitude,
    		@RequestParam(required=true) Float longitude,
    		@RequestParam(required=true) Float accuracy,
    		@RequestParam(required=false,defaultValue="0") Float altitude,
    		@RequestParam(required=false,defaultValue="0") Float altitudeAccuracy,
    		@RequestParam(required=false,defaultValue="0") Float heading,
    		@RequestParam(required=false,defaultValue="0") Float speed,
    		@RequestParam(required=true) Long timestamp) {
		Position position = new Position();
		position.setLatitude(latitude);
		position.setLongitude(longitude);
		position.setAccuracy(accuracy);
		position.setAltitude(altitude);
		position.setAltitudeAccuracy(altitudeAccuracy);
		position.setHeading(heading);
		position.setSpeed(speed);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		position.setTimestamp(calendar.getTime());
		positionService.save(position);
//		List<Map<String, Object>> positionList = positionService.findAll();
		log.info("uploadPosition:"+position.toString());
//    	model.addAttribute("lolheroForm",new LolheroForm());
        return applicationKeyConfig.googleMapAppKey;
    }
	
	@RequestMapping(value="/uploadposition", method=RequestMethod.GET)
	@ResponseBody
	public String uploadPositionObject(){
		return "success";
	}
	
}
