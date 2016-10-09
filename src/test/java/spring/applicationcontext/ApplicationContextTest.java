package spring.applicationcontext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * root
 * 2016年10月8日 下午10:36:12
 */
public class ApplicationContextTest {
    @Test  
    public void test1() {  
    BeanFactory beanFactory =  
    new ClassPathXmlApplicationContext("spring/applicationcontext/namebean.xml");  
        //根据id获取bean  
        ResourceBundleMessageSource source = beanFactory.getBean("messageSource", ResourceBundleMessageSource.class);  
        String message = source.getMessage("message", null, "Default",null);
        System.out.println(message);
        String[] beanAlias  = beanFactory.getAliases("messageSource");  
        Assert.assertEquals(0, beanAlias.length);  
        ResourceBundleMessageSource source2 = beanFactory.getBean("messageSource2", ResourceBundleMessageSource.class);  
        String message2 = source2.getMessage("message", null, "Default",null);
        System.out.println(message2);
    }  
}
