package lol.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
@Component(value="dataSource")
public class DataSourceConfig extends BasicDataSource{
	public DataSourceConfig(){
		this.setDriverClassName("org.h2.Driver");
		this.setUrl("jdbc:h2:tcp://localhost/~/test");
		this.setUsername("sa");
		this.setPassword("");
	}
	DataSource datasource ;
	BasicDataSource basicDataSource;
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public BasicDataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(BasicDataSource basicDataSource) {
		basicDataSource.setDriverClassName("org.h2.Driver");
		basicDataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		basicDataSource.setUsername("sa");
		basicDataSource.setPassword("");
		try {
			basicDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.basicDataSource = basicDataSource;
	}
}
