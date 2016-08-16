package lol.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import net.minidev.json.JSONObject;

/**
 *
 * @date 2016年6月7日 下午1:41:22
 * @author James Yang
 * @version 1.0
 * @since
 */
public class ControllerTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Test
	public void test() {
		JSONObject object = new JSONObject();
		object.put("hello", "world");
		System.out.println(object.get("hello"));
		DataSource source = jdbcTemplate.getDataSource();
//		source.
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
	@SuppressWarnings({ "resource", "unused" })
	@Test
	public void testResource() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:lol/controller/beans.xml");
//		Resource template = context.getResource("classpath:lol/controller/format.properties");
//		Resource template = context.getResource("file:///home/james/epel-6.repo");
		Resource template = context.getResource("http://localhost/test.html");

		String description = template.getDescription();
//		File file = template.getFile();
//		System.out.println(file.exists());
		int a = template.getInputStream().read();
//		FileReader reader = (FileReader) new InputStreamReader(template.getInputStream());
//		int a = reader.read();
		System.out.println(a);
		}
}
