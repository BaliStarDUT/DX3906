package lol.events;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 2016年8月5日 下午4:47:38
 */
public class EventsTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("lol/events/beans.xml");
		
		EmailService emailService = (EmailService)context.getBean("emailService");
		emailService.sendEmail("john.doe@example.org", "黑名单");
//		fail("Not yet implemented");
	}

}
