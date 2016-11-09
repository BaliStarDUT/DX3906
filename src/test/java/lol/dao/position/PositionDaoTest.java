package lol.dao.position;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import lol.entity.position.Position;
import sun.util.logging.resources.logging;

/**
 * 
 * 2016年11月7日 下午5:09:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/resources/spring-datasource-config.xml")
public class PositionDaoTest {

	@Autowired
	private ApplicationContext applicationContext;
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@SuppressWarnings("unused")
	@Test
	public void test() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/resources/spring-datasource-config.xml");
		DataSource dataSource = (DataSource)this.applicationContext.getBean("dataSource");
		int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "position");
		Position position = new Position();
		Date date = new Date(System.currentTimeMillis());
		
		this.jdbcTemplate.update("INSERT INTO position "
    			+ "(sid, latitude, longitude, accuracy, altitude, "
    			+ "altitudeAccuracy, heading, speed, timestamp) "
    			+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)",position.getLatitude(),
    			position.getLongitude(),position.getAccuracy(),position.getAltitude(),
    			position.getAltitudeAccuracy(),position.getHeading(),position.getSpeed(),
    			position.getTimestamp());
	}

}
