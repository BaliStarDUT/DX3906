package weixin;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @date 2016年6月1日 下午6:58:12
 * @author James Yang
 * @version 1.0
 * @since
 */
@Configurable
@EnableAutoConfiguration
@ComponentScan
public class Application {
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(Application.class, args);
	    }
}
