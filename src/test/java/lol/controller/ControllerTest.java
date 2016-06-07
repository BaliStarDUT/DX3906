package lol.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import net.minidev.json.JSONObject;

/**
 *
 * @date 2016年6月7日 下午1:41:22
 * @author James Yang
 * @version 1.0
 * @since
 */
public class ControllerTest {

	@Test
	public void test() {
		JSONObject object = new JSONObject();
		object.put("hello", "world");
		System.out.println(object.get("hello"));
	}

}
