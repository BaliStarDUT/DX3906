package top.hunaner.lol.dao.position;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.hunaner.lol.entity.position.Position;

/**
 * 
 * 2016年11月7日 下午5:55:22
 */
@Repository
public class PositionDao {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public void insert(Position position){
    	this.jdbcTemplate.update("INSERT INTO position "
    			+ "(sid, latitude, longitude, accuracy, altitude, "
    			+ "altitudeAccuracy, heading, speed, timestamp) "
    			+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)",position.getLatitude(),
    			position.getLongitude(),position.getAccuracy(),position.getAltitude(),
    			position.getAltitudeAccuracy(),position.getHeading(),position.getSpeed(),
    			position.getTimestamp());
    }

	public List<Map<String, Object>> findAll() {
		return this.jdbcTemplate.queryForList("select * from position");
	}
}
