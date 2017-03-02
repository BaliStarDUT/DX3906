package lol.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import top.hunaner.weixin.Application;


/**
 * 
 * 2016年12月6日 下午2:05:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class LoginControllerTest {
	@Autowired
    private WebApplicationContext wac;
	private MockMvc mockMvc;	
	
	@Before
    public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	@Test
	public void welcomeTest() throws Exception {
		for(String names :this.wac.getBeanDefinitionNames()){
			System.out.println(names);
		}
        this.mockMvc.perform(get("/welcome"))
//        	.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"))
            .andDo(print());
	}

}
