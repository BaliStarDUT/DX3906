package top.hunaner.lol.controller.position;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.hunaner.lol.config.ApplicationKeyConfig;
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
    private static final Logger log = Logger.getLogger(PositionController.class);
    
    @Resource(name="ApplicationKey")
    private ApplicationKeyConfig applicationKeyConfig;
    
    @Autowired
    private PositionService positionService;

	@Autowired
	PositionRepository positionRepository;

	@ApiOperation(value="上传地理位置接口", notes="上传地理位置接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "Float"),
			@ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "Float")
	})
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	@ResponseBody
    public String uploadPosition(Model model,
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
		positionService.save(position);
//		List<Map<String, Object>> positionList = positionService.findAll();
		log.info("uploadPosition:"+position.toString());
//    	model.addAttribute("lolheroForm",new LolheroForm());
        return applicationKeyConfig.googleMapAppKey;
    }

	@ApiOperation(value="上传地理位置响应", notes="上传地理位置响应接口")
	@RequestMapping(value="/uploadposition", method=RequestMethod.GET)
	@ResponseBody
	public String uploadPositionObject(){
		List<Map<String, Object>> list = positionService.findAll();
		return "success";
	}

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public void position(){
		long count = positionRepository.count();
		log.debug("positon count:"+count);
		log.debug(positionRepository.findOne(Integer.valueOf(1)).toString());
		for (Position positon:positionRepository.findAll()) {
			log.debug(positon.toString());
		}
	}
}
