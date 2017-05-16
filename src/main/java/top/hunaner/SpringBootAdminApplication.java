package top.hunaner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//import top.hunaner.lol.config.WebAppInitializer;
//import top.hunaner.lol.config.WebMvcConfig;
//import top.hunaner.lol.config.WebSecurityConfig;
import top.hunaner.lol.service.storage.impl.StorageProperties;


/**
 *
 * @date 2016年6月1日 下午6:58:12
 * @author James Yang
 * @version 1.0
 * @since
 */
@Configuration
@EnableAutoConfiguration
//		(exclude = {WebSecurityConfig.class, WebMvcConfig.class})
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan(basePackages="top.hunaner.lol") //lol,weixin
//@Import(value = WebAppInitializer.class)
@EnableDiscoveryClient
public class SpringBootAdminApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootAdminApplication.class);
	 public static void main(String[] args) {
	        ApplicationContext ctx =  SpringApplication.run(SpringBootAdminApplication.class, args);
	 }
}
