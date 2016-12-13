package lol.resource;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 
 * 2016年8月9日 下午3:21:54
 */
public class ResourceTest {

	@Test
	public void test() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/lol/controller/beans.xml");
//		context.getResource()
		Resource resource = context.getResource("classpath:/");
		try {
			if(resource.exists()){
				System.out.println(resource.getDescription());
				System.out.println(resource.getFilename());
				System.out.println(resource.getURI().toString());
				System.out.println(resource.getURL());
				System.out.println(resource.contentLength());
				System.out.println(resource.getFile().getFreeSpace());
				System.out.println(resource.getFile().getTotalSpace());
				System.out.println(resource.getFile().getUsableSpace());
				System.out.println(resource.getFile().getCanonicalPath());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		fail("Not yet implemented");
	}

}
