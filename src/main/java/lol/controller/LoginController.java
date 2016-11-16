package lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

}
