package lol;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import groovy.lang.Grab;
import lol.entity.LolheroForm;

/**
 *
 * @date 2016年6月1日 下午6:58:12
 * @author James Yang
 * @version 1.0
 * @since
 */
@SpringBootApplication
@Grab("org.webjars:jquery:2.0.3-1") 
public class Application implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	 public static void main(String[] args) throws Exception {
	        ApplicationContext ctx =  SpringApplication.run(Application.class, args);
	        System.out.println("Lets inspect the beans provided by Spring boot:");
//	        String[] beanNames = ctx.getBeanDefinitionNames();
//	        Arrays.sort(beanNames);
//	        for(String beanName:beanNames){
//	        	System.out.println(beanName);
//	        }
	        
	    }
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public void run(String... arg0) throws Exception {
		log.info("Creating tables");
//		jdbcTemplate.execute("DROP TABLE heros IF EXISTS");
//		jdbcTemplate.execute("CREATE TABLE heros(" +
//                "id SERIAL, nameCn VARCHAR(255), nameEn VARCHAR(255))");
//		jdbcTemplate.
        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting heros record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO heros(nameCn, nameEn) VALUES (?,?)", splitUpNames);
//
//        log.info("Querying for hero records where nameCn = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT id, nameCn, nameEn FROM heros WHERE nameCn = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new LolheroForm(rs.getLong("id"), rs.getString("nameCn"), rs.getString("nameEn"))
//        ).forEach(customer -> log.info(customer.toString()));
		
	}
}
