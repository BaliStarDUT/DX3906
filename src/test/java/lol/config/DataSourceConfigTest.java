package lol.config;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

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
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void testDataSource() {
		assertNotNull("WebApplicationContext can't be null",wac);
		System.out.println(this.wac.getBeanDefinitionCount());
		for(String bean :this.wac.getBeanDefinitionNames()){
			System.out.println(bean);
		}
		System.out.println(this.wac.getDisplayName());
		System.out.println(this.wac.getApplicationName());
		assertTrue("Bean named dataSource must be defined", this.wac.containsBean("dataSource"));
		if(this.wac.containsBean("dataSource")){
			DataSource dataSource = (DataSource) this.wac.getBean("dataSource");
			assertNotNull("Bean named dataSource must not be null",dataSource);
			System.out.println(dataSource.getClass().getName());
			try {
				Connection connection = dataSource.getConnection();
				assertNotNull("dataSource must can get a connection", connection);
//				System.out.println(connection.getSchema());
				Properties properties = connection.getClientInfo();
				for(Object ob :properties.keySet()){
					System.out.println(ob);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
