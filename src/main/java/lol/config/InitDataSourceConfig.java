package lol.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * 初始化数据库，加入一些模拟数据
 * 2016年7月19日 下午11:41:27
 */
@Configuration
public class InitDataSourceConfig {
	@Autowired
	private Environment env;
	@Autowired
	private DataSource dataSource;
	@PostConstruct
	public void init() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		if(env.getProperty("jdbc.driverClassName").contains("mysql")){
			databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.mysql.initLocation")));
			databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.mysql.dataLocation")));
		}else{
			databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.h2.initLocation")));
			databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.h2.dataLocation")));
		}
		
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		
	}
}
