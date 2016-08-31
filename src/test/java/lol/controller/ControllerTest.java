package lol.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.minidev.json.JSONObject;

/**
 *
 * @date 2016年6月7日 下午1:41:22
 * @author James Yang
 * @version 1.0
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/lol/controller/beans.xml")
public class ControllerTest extends AbstractJUnit4SpringContextTests {
	@Test
	public void test() {
		JSONObject object = new JSONObject();
		object.put("hello", "world");
		System.out.println(object.get("hello"));
//		DataSource source = jdbcTemplate.getDataSource();
		
	}
	@Test
	public void testFileTree(){
		final String ROOT = "/media/james/home/yangz/code/hello-world/";
		try {
			List<Path> pathList = Files.walk(Paths.get(ROOT)).collect(Collectors.toList());
			for(Path path:pathList){
				System.out.println(path.toUri());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@SuppressWarnings("resource")
	@Test
	public void testMessage(){
		MessageSource source = new ClassPathXmlApplicationContext("/lol/controller/beans.xml");
		String message = source.getMessage("message", null, "Default",null);
		System.out.println(message);
	}
	@SuppressWarnings("resource")
	@Test
	public void testMessage2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/lol/controller/beans.xml");
//		MessageSource source = new ClassPathXmlApplicationContext("/lol/controller/beans.xml");
		Example example = (Example)context.getBean("example");;
		example.excute();
	}
}
