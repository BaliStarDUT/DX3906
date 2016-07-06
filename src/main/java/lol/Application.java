package lol;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import groovy.lang.Grab;
import lol.entity.LolheroForm;

/**
 *
 * @date 2016年6月1日 下午6:58:12
 * @author James Yang
 * @version 1.0
 * @since
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application{
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	 public static void main(String[] args) throws Exception {
	        ApplicationContext ctx =  SpringApplication.run(Application.class, args);
	        
	    }
}
