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
 * root
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
		databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.initLocation")));
		databasePopulator.addScript(new ClassPathResource(env.getProperty("jdbc.dataLocation")));
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
	}
}
