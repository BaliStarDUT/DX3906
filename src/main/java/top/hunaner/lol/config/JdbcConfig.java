package top.hunaner.lol.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/**
 * 从Spring中自动配置dataSource，并获取JdbcTemplate和NamedParameterJdbcTemplate，
 * 注入到Spring中等待使用。
 * @author Administrator
 *
 */
@Configuration(value="jdbcConfig")
//@Profile("jdbc")
//@ComponentScan("lol.entity")
public class JdbcConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
}

