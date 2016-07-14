package lol.controller;

import static org.junit.Assert.*;

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

}
