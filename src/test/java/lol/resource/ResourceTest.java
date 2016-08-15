package lol.resource;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 2016年8月9日 下午3:21:54
 */
public class ResourceTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/lol/controller/beans.xml");
		context.getResource("assets/bootstarp/css/bootstrap.css");
//		fail("Not yet implemented");
	}

}
