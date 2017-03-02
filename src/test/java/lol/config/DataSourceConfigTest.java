package lol.config;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import top.hunaner.lol.config.DataSourceConfig;

/**
 * Test DataSourceConfig, it must get a valid dataSource and connection
 * 2016年8月31日 上午10:06:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@WebAppConfiguration
public class DataSourceConfigTest {
    private static final Logger log = Logger.getLogger(DataSourceConfigTest.class);
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void testDataSource() {
		log.debug("Starting test class DataSourceConfig:");
		assertNotNull("WebApplicationContext can't be null",wac);
		log.debug("WebApplicationContext:"+this.wac.getDisplayName());
		log.debug("BeanDefinitionCount:"+this.wac.getBeanDefinitionCount());
		assertTrue("Bean named dataSource must be defined", this.wac.containsBean("dataSource"));
		if(this.wac.containsBean("dataSource")){
			DataSource dataSource = (DataSource) this.wac.getBean("dataSource");
			assertNotNull("Bean named dataSource must not be null",dataSource);
			log.debug("Bean named dataSource:"+dataSource.getClass().getName());
			try {
				Connection connection = dataSource.getConnection();
				assertNotNull("dataSource must can get a connection", connection);
				log.debug("Get a connection from dataSource:"+connection.toString());
				Properties properties = connection.getClientInfo();
				for(Object ob :properties.keySet()){
					log.debug("Properties in dataSource:"+ob.toString());
				}
				String sql ="show databases";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				log.debug(sql+":"+preparedStatement.toString());
				ResultSet resultSet = preparedStatement.executeQuery();
				for(int i =1 ;resultSet.next();i++){
					log.debug(resultSet.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Autowired DataSource dataSource;
	@Test
	public void testDatasource(){
		assertNotNull("Bean named dataSource must not be null",dataSource);
		log.debug("Bean named dataSource:"+dataSource.getClass().getName());
		try {
			Connection connection = dataSource.getConnection();
			assertNotNull("dataSource must can get a connection", connection);
			log.debug("Get a connection from dataSource:"+connection.toString());
			Properties properties = connection.getClientInfo();
			for(Object ob :properties.keySet()){
				log.debug("Properties in dataSource:"+ob.toString());
			}
			String sql ="show databases";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			log.debug(sql+":"+preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			for(int i =1 ;resultSet.next();i++){
				log.debug(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
