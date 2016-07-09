package lol.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lol.entity.LolheroForm;

/**
 *
 * @date 2016年7月6日 下午4:08:59
 * @author James Yang
 * @version 1.0
 * @since
 */
//@Repository
public class HerosDao {
	private JdbcTemplate jdbcTemplate;
//	@Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
	public String getDate(){
		 jdbcTemplate.query(
	               "SELECT id, nameCn, nameEn FROM heros WHERE nameCn = ?", new Object[] { "Josh" },
	               (rs, rowNum) -> new LolheroForm(rs.getLong("id"), rs.getString("nameCn"), rs.getString("nameEn"))
	        );
		 return "true";
	}
}
