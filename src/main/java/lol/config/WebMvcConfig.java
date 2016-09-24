package lol.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
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
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
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
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	/** ApplicationContext this object runs in */
	private ApplicationContext applicationContext;
	
	/** Default if no other location is supplied */
	public final static String ViEWS_LOCATION = "classpath:/spring/views.xml";
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
		.defaultContentType(MediaType.TEXT_HTML);
		configurer.mediaType("html", MediaType.TEXT_HTML);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
	}
	/**
	 * 配置自定义的视图解析器，能够解析FreeMarker文件
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver();
		servletContextTemplateResolver.setPrefix("/templates");
		servletContextTemplateResolver.setSuffix(".html");
		servletContextTemplateResolver.setTemplateMode("HTML5");
		templateEngine.setTemplateResolver(servletContextTemplateResolver);
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		thymeleafViewResolver.setOrder(1);
		registry.viewResolver(thymeleafViewResolver);
		
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/templates/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(3);
		registry.viewResolver(internalResourceViewResolver);;


	
		
//		XmlViewResolver xmlViewResolver = new XmlViewResolver();
//		Resource viewXmlConfigLocation =this.applicationContext.getResource(ViEWS_LOCATION);
//		xmlViewResolver.setLocation(viewXmlConfigLocation);
//		xmlViewResolver.setOrder(1);
		
		ResourceBundleViewResolver urlBasedViewResolver = new ResourceBundleViewResolver();
		urlBasedViewResolver.setOrder(2);
		registry.viewResolver(urlBasedViewResolver);
//		urlBasedViewResolver.setBasename("views");
//		urlBasedViewResolver.setDefaultParentView("parentView");;
//		registry.enableContentNegotiation(new MappingJackson2JsonView());
//		registry.jsp();
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
//		exceptionResolver.
		// results into 'WEB-INF/jsp/exception.jsp'
		exceptionResolver.setDefaultErrorView("exception");
		// needed otherwise exceptions won't be logged anywhere
		exceptionResolver.setWarnLogCategory("warn");
		exceptionResolvers.add(exceptionResolver);
//		exceptionResolver.setDefaultErrorView("404");
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
	}
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.xml().build()));
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
//	@Bean(name="templateEngine")
//	@Description("org.thymeleaf.spring4.SpringTemplateEngine:"
//			+ "http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#integrating-thymeleaf-with-spring")
//	public SpringTemplateEngine getTemplateEngine(){
//		SpringTemplateEngine template = new SpringTemplateEngine();
//		template.setTemplateResolver(getTemplateResolver());
//		return template;
//	}
//	@Bean
//	@Description("org.thymeleaf.templateresolver.ServletContextTemplateResolver")
//	public ServletContextTemplateResolver getTemplateResolver(){
//		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//		resolver.setPrefix("/WEB-INF/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode("HTML5");
//		return resolver;
//	}
}
