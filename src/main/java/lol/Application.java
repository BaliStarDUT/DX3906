package lol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @date 2016年6月1日 下午6:58:12
 * @author James Yang
 * @version 1.0
 * @since
 */
@SpringBootApplication
@ComponentScan(basePackages="lol") //lol,weixin
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	 public static void main(String[] args) throws Exception {
	        ApplicationContext ctx =  SpringApplication.run(Application.class, args);
	 }
}
