package top.hunaner.lol.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 * 配置数据库，从classpath:spring/data-access.properties中获取数据库配置
 * 并将数据库配置的bean注入到Spring中
 * @author Administrator
 *
 */
@Configuration(value="dataSourceConfig")
@PropertySource("classpath:spring/data-access.properties")
public class DataSourceConfig {
	
	@Autowired
	private Environment env;
	@Bean(name="dataSource")
	@Description("DataSource configuration for the tomcat jdbc connection pool")
	public DataSource dataSource(){
		// See here for more details on commons-dbcp versus tomcat-jdbc:
		// http://blog.ippon.fr/2013/03/13/improving-the-performance-of-the-spring-petclinic-sample-application-part-3-of-5/-->
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
}
