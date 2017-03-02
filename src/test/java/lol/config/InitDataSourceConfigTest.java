package lol.config;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import top.hunaner.lol.config.DataSourceConfig;
import top.hunaner.lol.config.InitDataSourceConfig;

/**
 * 
 * 2016年8月31日 下午4:12:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class,InitDataSourceConfig.class,})
@WebAppConfiguration
public class InitDataSourceConfigTest {
	@Autowired
    private WebApplicationContext wac;
	@Autowired
    private DataSource dataSource;
    
	@Before
    public void setup() {
		assertNotNull("WebApplicationContext can't be null",wac);
		for(String bean :this.wac.getBeanDefinitionNames()){
			System.out.println(bean);
		}
		assertTrue("Bean named dataSource must be defined", this.wac.containsBean("dataSource"));
//		this.dataSource = (DataSource) this.wac.getBean("dataSource");
		assertNotNull("Bean named dataSource must not be null",dataSource);
    }
	
	@Test
	public void testInit(){
		assertTrue("Bean named initDataSourceConfig must be defined", this.wac.containsBean("initDataSourceConfig"));

	}
}
