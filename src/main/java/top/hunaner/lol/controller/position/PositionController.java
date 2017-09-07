package top.hunaner.lol.controller.position;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.hunaner.lol.config.ApplicationKeyConfig;
import top.hunaner.lol.controller.weather.WeatherQueryController;
import top.hunaner.lol.dao.position.PositionRepository;
import top.hunaner.lol.entity.position.Position;
import top.hunaner.lol.service.positon.PositionService;

/**
 * 
 * 2016年11月7日 下午1:57:29
 */
@Controller
@RequestMapping("/position")
public class PositionController {
	private static final Logger log = LoggerFactory.getLogger(PositionController.class);
    
    @Resource(name="ApplicationKey")
    private ApplicationKeyConfig applicationKeyConfig;
    
    @Autowired
    private PositionService positionService;

	@ApiOperation(value="上传地理位置接口", notes="上传地理位置接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "Float"),
			@ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "Float")
	})
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<Integer> uploadPosition(Model model,
										 @RequestParam(required=true) Float latitude,
										 @RequestParam(required=true) Float longitude,
										 @RequestParam(required=true) Float accuracy,
										 @RequestParam(required=false,defaultValue="0") Float altitude,
										 @RequestParam(required=false,defaultValue="0") Float altitudeAccuracy,
										 @RequestParam(required=false,defaultValue="0") float heading,
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
		int count = positionService.save(position);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
//		List<Map<String, Object>> positionList = positionService.findAll();
//    	model.addAttribute("lolheroForm",new LolheroForm());
//        return applicationKeyConfig.googleMapAppKey;
    }

	@ApiOperation(value="getAllPosition", notes="getAllPosition")
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Collection> getAll(){
		List<Map<String, Object>> list = positionService.findAll();
		return new ResponseEntity<Collection>(list,HttpStatus.OK);
	}

	@RequestMapping(value = "/count",method = RequestMethod.GET)
	public ResponseEntity<Integer> count(){
		long count = positionService.getCount();
		return new ResponseEntity<Integer>((int)count,HttpStatus.OK);
	}

	@RequestMapping(value = "/find",method = RequestMethod.GET)
	public ResponseEntity<Position> find(@RequestParam(required=true) int id){
		Position position = positionService.findOne(id);
		return new ResponseEntity<Position>(position,HttpStatus.OK);
	}
}
