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

}
