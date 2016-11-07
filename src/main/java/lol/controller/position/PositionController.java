package lol.controller.position;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import lol.entity.LolheroForm;
import lol.entity.position.Position;
import lol.service.positon.PositionService;

/**
 * 
 * 2016年11月7日 下午1:57:29
 */
@Controller
public class PositionController {
	private PositionService positionService;
	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@RequestMapping(value="/position/upload", method=RequestMethod.GET)
	@ResponseBody
    public String showNewHeroForm(Model model,
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
		position.setTimestamp(new Date(timestamp));
		positionService.save(position);
//		List<Map<String, Object>> positionList = positionService.findAll();
    	model.addAttribute("lolheroForm",new LolheroForm());
        return "form";
    }
	
}
