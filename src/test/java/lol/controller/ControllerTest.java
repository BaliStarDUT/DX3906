package lol.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lol.Application;

/**
 *
 * @date 2016年6月7日 下午1:41:22
 * @author James Yang
 * @version 1.0
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class ControllerTest {
	@Autowired
    private WebApplicationContext wac;
	private MockMvc mockMvc;	
	
	@Before
    public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
    public void getAccount() throws Exception {
		for(String names :this.wac.getBeanDefinitionNames()){
			System.out.println(names);
		}
        this.mockMvc.perform(get("/lolheros/heros.json")
        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andDo(print());
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
