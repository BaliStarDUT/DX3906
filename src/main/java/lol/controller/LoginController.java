package lol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 2016年11月12日上午10:59:22
 */
@Controller
public class LoginController{
	@RequestMapping(value = "/")
//	@ResponseBody
	public String index() {
		return "redirect:/resources/view/index.html";
//		return "index,这应该是一个文本响应";
	}
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestBody(required= false) Object body ) {
		//1、收集参数  
        //2、绑定参数到命令对象  
        //3、调用业务对象  
        //4、选择下一个页面  
        ModelAndView mv = new ModelAndView();  
        //添加模型数据 可以是任意的POJO对象  
        mv.addObject("message", "Hello World!");  
        mv.addObject("_csrf.parameterName","yang");
        mv.addObject("_csrf.token", "zhen");
        mv.addObject("object",body);

        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
        mv.setViewName("login");  
        return mv;  
	}
	
	@RequestMapping(value = "/login.do",method=RequestMethod.POST)
	public ModelAndView loginDo() {
		//1、收集参数  
        //2、绑定参数到命令对象  
        //3、调用业务对象  
        //4、选择下一个页面  
        ModelAndView mv = new ModelAndView();  
        //添加模型数据 可以是任意的POJO对象  
        mv.addObject("message", "Hello World!"); 
        
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
        mv.setViewName("hello");  
        return mv;  
	}
	
	@RequestMapping(value = "/hello")
	public ModelAndView hello() {
		//1、收集参数  
        //2、绑定参数到命令对象  
        //3、调用业务对象  
        //4、选择下一个页面  
        ModelAndView mv = new ModelAndView();  
        //添加模型数据 可以是任意的POJO对象  
        mv.addObject("message", "Hello World!"); 
        
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
        mv.setViewName("hello");  
        return mv;  
	}
	@RequestMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "welcome";
	}
	@RequestMapping(value = "/hostinfo/getjson")
	@ResponseBody
	public void hostinfo(HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>(1);
		List<Object> groupList = new ArrayList<>(4);
		Map<String, Object> groupMap = new HashMap<>(4);
		Map<String, String> memberMap = new HashMap<>(4);
		memberMap.put("ip", "192.168.0.107");
		memberMap.put("hostname", "james-CW65");
		groupMap.put("groupname", "web");
		groupMap.put("members", memberMap);
		
		Map<String, Object> groupMap2 = new HashMap<>(4);
		Map<String, String> memberMap2 = new HashMap<>(4);
		memberMap2.put("ip", "192.168.0.105");
		memberMap2.put("hostname", "airs-MacBook-Air.local");
		groupMap2.put("groupname", "db");
		groupMap2.put("members", memberMap2);
		
		groupList.add(groupMap);
		groupList.add(groupMap2);
		result.put("data", groupList);
		JSONObject jsonObject = new JSONObject(result);
		String responseStr =  jsonObject.toJSONString();
		try {
			response.getWriter().write(responseStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
