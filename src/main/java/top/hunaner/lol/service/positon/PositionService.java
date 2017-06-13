package top.hunaner.lol.service.positon;

import java.util.List;
import java.util.Map;

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
	private PositionDao positionDao;
	
	@Autowired
	public PositionService(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Autowired
	PositionRepository positionRepository;

	public void save(Position position) {
		this.positionDao.insert(position);;
	}
	
	public List<Map<String, Object>> findAll() {
		return this.positionDao.findAll();
	}
}
