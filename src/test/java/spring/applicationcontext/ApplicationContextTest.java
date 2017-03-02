package spring.applicationcontext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.SystemEnvironmentPropertySource;

/**
 * root
 * 2016年10月8日 下午10:36:12
 */
public class ApplicationContextTest {
    @Test  
    public void test1() {  
    	Log log = LogFactory.getLog(ApplicationContextTest.class);
    BeanFactory beanFactory =  
    new ClassPathXmlApplicationContext("spring/applicationcontext/namebean.xml");  
    log.info(beanFactory.containsBean("systemProperties"));
    	SystemEnvironmentPropertySource environmentPropertySource =  (SystemEnvironmentPropertySource) beanFactory.getBean("systemProperties");
    	Object object = beanFactory.getBean("systemProperties");
        //根据id获取bean  
        ResourceBundleMessageSource source = beanFactory.getBean("messageSource", ResourceBundleMessageSource.class);  
        String message = source.getMessage("message", null, "Default",null);
        System.out.println(message);
        String[] beanAlias  = beanFactory.getAliases("messageSourceAlias1");  
        Assert.assertEquals(0, beanAlias.length);  
        ResourceBundleMessageSource source2 = beanFactory.getBean("messageSource2", ResourceBundleMessageSource.class);  
        String message2 = source2.getMessage("message", null, "Default",null);
        System.out.println(message2);
    }  
}
