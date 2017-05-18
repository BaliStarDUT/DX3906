package top.hunaner.lol.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 2016年11月12日上午10:59:22
 */
@Controller
public class LoginController{
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 从属性文件application.yml中获取的h2的控制台路径
     */
	@Value(value = "${spring.h2.console.path}")
	private String h2_console;

	/**
	 * 本地服务实例的信息
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}

	@ApiOperation(value="网站首页", notes="index页面")
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String index(ModelMap map) {
        map.addAttribute("title","Legue Of Legends");
		map.addAttribute("h2console",h2_console);
		return "index";
	}

	@ApiOperation(value="用户登录", notes="用户登录调用的接口")
	@ApiImplicitParam(name = "body", value = "用户登录传递的参数", required = false, dataType = "Object")
	@RequestMapping(value = "/login",method = RequestMethod.POST)
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

	@ApiOperation(value="hello页面", notes="用来返回问候页面")
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public ModelAndView hello() {
        ModelAndView mv = new ModelAndView();  
        mv.addObject("message", "Hello World!");
		mv.addObject("name", "James");
		mv.setViewName("greeting");
        return mv;  
	}

	@ApiOperation(value="welcome接口", notes="用来分析用户登录过程")
	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	@ResponseBody
	public String welcome() {
//		try {
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if(principal instanceof UserDetails){
//				log.info(((UserDetails)principal).getUsername());
//			}
//			if(principal instanceof Principal){
//				log.info(((Principal)principal).getName());
//			}
//			log.info(String.valueOf(principal));
//			log.info(SecurityContextHolder.getContext().getAuthentication().getName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "welcome";
	}
	@ApiOperation(value="主机信息接口", notes="用来获取主机信息")
	@RequestMapping(value = "/hostinfo/getjson",method = RequestMethod.GET)
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
