package lol.config;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * @date 2016年6月30日 下午5:05:18
 * @author James Yang
 * @version 1.0
 * @since
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true)
		.defaultContentType(MediaType.TEXT_HTML);
		configurer.mediaType("html", MediaType.TEXT_HTML);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
	}
	/**
	 * 配置自定义的视图解析器，能够解析Thymeleaf格式的html文件
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(ThymeleafViewResolver.class);
		bean.setOrder(1);
		Properties pro = new Properties();
		pro.put("templateEngine", getTemplateEngine());
		bean.setAttributes(pro);
//		bean.setViewNames("*.html");
//		bean.setPrefix("/WEB-INF/jsp/");
//		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
		super.configureViewResolvers(registry);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureHandlerExceptionResolvers(java.util.List)
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		// results into 'WEB-INF/jsp/exception.jsp'
		exceptionResolver.setDefaultErrorView("exception");
		// needed otherwise exceptions won't be logged anywhere
		exceptionResolver.setWarnLogCategory("warn");
		exceptionResolvers.add(exceptionResolver);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("classpath:/assets/").setCachePeriod(31556926)
		.resourceChain(true).addResolver(
				new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/form").setViewName("form");

	}
	
	@Bean(name="templateEngine")
	@Description("org.thymeleaf.spring4.SpringTemplateEngine:"
			+ "http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#integrating-thymeleaf-with-spring")
	public SpringTemplateEngine getTemplateEngine(){
		SpringTemplateEngine template = new SpringTemplateEngine();
		template.setTemplateResolver(getTemplateResolver());
		return template;
	}
	@Bean
	@Description("org.thymeleaf.templateresolver.ServletContextTemplateResolver")
	public ServletContextTemplateResolver getTemplateResolver(){
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}
}
