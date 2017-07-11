package top.hunaner.lol.dao.position;

import org.springframework.data.repository.CrudRepository;
import top.hunaner.lol.entity.position.Position;

import java.util.List;

/**
 * Created by James Yang on 2017/5/26 0026 下午 3:10.
 */
public interface PositionRepository extends CrudRepository<Position,Integer>{
    List<Position> findBySid(Integer sid);
}
