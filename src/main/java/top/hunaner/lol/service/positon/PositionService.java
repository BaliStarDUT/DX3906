package top.hunaner.lol.service.positon;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.hunaner.lol.dao.position.PositionDao;
import top.hunaner.lol.dao.position.PositionRepository;
import top.hunaner.lol.entity.position.Position;

/**
 * 
 * 2016年11月7日 下午5:58:40
 */
@Service
public class PositionService {
	private static final Logger log = LoggerFactory.getLogger(PositionService.class);
	private PositionDao positionDao;
	
	@Autowired
	public PositionService(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Autowired
	PositionRepository positionRepository;

	public int save(Position position) {
		log.info("savePosition:"+position.toString());
		Position position1 = positionRepository.save(position);
		if(position1!=null){
			return 1;
		}
		return 0;
	}
	
	public List<Map<String, Object>> findAll() {
		return this.positionDao.findAll();
	}
	public long getCount(){
		long count = positionRepository.count();
		log.debug("positon count:"+count);
		return count;
	}
	public Position findOne(int id){
		Position position = positionRepository.findOne(id);
		log.debug(position.toString());
		return position;
	}

}
