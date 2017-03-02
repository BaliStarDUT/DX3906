package lol.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import top.hunaner.lol.config.ApplicationKeyConfig;

/**
 * 
 * 2017年1月3日 下午7:01:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationKeyConfig.class)
@WebAppConfiguration
public class ApplicationKeyConfigTest {
	@Autowired
    private WebApplicationContext wac;
	
	@Test
	public void test() {
		if(this.wac.containsBean("ApplicationKey")){
			ApplicationKeyConfig applicationKeyConfig = (ApplicationKeyConfig) this.wac.getBean("ApplicationKey");
			System.out.println(applicationKeyConfig.googleMapAppKey);
			System.out.println(applicationKeyConfig.juheDataAppKey);
			applicationKeyConfig.googleMapAppKey = "";
			System.out.println(applicationKeyConfig.googleMapAppKey);

		}else{
			fail();
		}
	}

}
