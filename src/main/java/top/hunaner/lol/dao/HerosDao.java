package top.hunaner.lol.dao;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 *
 * @date 2016年7月6日 下午4:08:59
 * @author James Yang
 * @version 1.0
 * @since
 */
@Repository
public class HerosDao {
	private JdbcTemplate jdbcTemplate;
	//@Autowired
    public void setDataSource() {
    	DataSource dataSource = new DataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	public List<Map<String,Object>>  getDate(){
		List<Map<String,Object>>  heroList = jdbcTemplate.queryForList("SELECT * FROM heros ");//query( "SELECT * FROM heros ");
		 return heroList;

//	    	log.info("Creating tables");
////	    	jdbcTemplate.execute("DROP TABLE heros IF EXISTS");
////	    	jdbcTemplate.execute("CREATE TABLE heros(" +
////	    	              "id SERIAL, nameCn VARCHAR(255), nameEn VARCHAR(255))");
//	    	       // Split up the array of whole names into an array of first/last names
//	    	       List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//	    	               .map(name -> name.split(" "))
//	    	               .collect(Collectors.toList());
//	    	        // Use a Java 8 stream to print out each tuple of the list
//	    	       splitUpNames.forEach(name -> log.info(String.format("Inserting heros record for %s %s", name[0], name[1])));
//	    	       // Uses JdbcTemplate's batchUpdate operation to bulk load data
//	    	       jdbcTemplate.batchUpdate("INSERT INTO heros(nameCn, nameEn) VALUES (?,?)", splitUpNames);
//	    	
//	    	       log.info("Querying for hero records where nameCn = 'Josh':");
//	    	       jdbcTemplate.query(
//	    	               "SELECT id, nameCn, nameEn FROM heros WHERE nameCn = ?", new Object[] { "Josh" },
//	    	               (rs, rowNum) -> new LolheroForm(rs.getLong("id"), rs.getString("nameCn"), rs.getString("nameEn"))
//	    	        ).forEach(customer -> log.info(customer.toString()));
	}
}
