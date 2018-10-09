package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 上午10:52:42
 */
public class BaseTest {
	
	public static ApplicationContext getContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "test.xml" });
		context.start();
		return context;
	}
}
