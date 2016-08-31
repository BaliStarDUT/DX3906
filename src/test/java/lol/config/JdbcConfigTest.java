package lol.config;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test JdbcConfig, it must get a valid JdbcTemplate and NamedParameterJdbcTemplate.
 * Depend on DataSourceConfig to run.
 * 2016年8月31日 上午10:06:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class,JdbcConfig.class,})
@WebAppConfiguration
public class JdbcConfigTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private DataSource dataSource;
		
    @Before
    public void setup() {
		assertNotNull("WebApplicationContext can't be null",wac);
		for(String bean :this.wac.getBeanDefinitionNames()){
			System.out.println(bean);
		}
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		assertTrue("Bean named dataSource must be defined", this.wac.containsBean("dataSource"));
		this.dataSource = (DataSource) this.wac.getBean("dataSource");
		assertNotNull("Bean named dataSource must not be null",dataSource);
    }
    
	@Test
	public void testJdbcTemplate() {
		assertTrue("Bean named jdbcTemplate must be defined", this.wac.containsBean("jdbcTemplate"));
		JdbcTemplate jdbcTemplate = (JdbcTemplate) this.wac.getBean("jdbcTemplate");
		assertNotNull("Bean named jdbcTemplate must not be null",jdbcTemplate);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate() {
		assertTrue("Bean named namedParameterJdbcTemplate must be defined", this.wac.containsBean("namedParameterJdbcTemplate"));
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) this.wac.getBean("namedParameterJdbcTemplate");
		assertNotNull("Bean named namedParameterJdbcTemplate must not be null",namedParameterJdbcTemplate);
//		namedParameterJdbcTemplate.
	}
	
}
