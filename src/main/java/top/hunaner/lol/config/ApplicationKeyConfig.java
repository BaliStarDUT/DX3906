package top.hunaner.lol.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 配置Google或其他应用的授权key
 * 2017年1月3日 下午4:34:36
 */
@Component(value="applicationKeyConfig")
@PropertySource("classpath:spring/application-key.properties")
public class ApplicationKeyConfig {
	
	@Autowired
	private  Environment environment;
	public String googleMapAppKey;
	public String juheDataAppKey;
	
	@Bean(name="ApplicationKey")
	public ApplicationKeyConfig applicationKeyConfig(){
		ApplicationKeyConfig applicationKeyConfig = new ApplicationKeyConfig();
		applicationKeyConfig.googleMapAppKey = environment.getProperty("google.map.API.secretKey");
		applicationKeyConfig.juheDataAppKey =environment.getProperty("juhe.cn.API.key");
		return applicationKeyConfig;
	}
}
