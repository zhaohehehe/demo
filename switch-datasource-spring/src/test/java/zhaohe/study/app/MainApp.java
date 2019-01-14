package zhaohe.study.app;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhaohe.study.service.TestService;

/**
 * 错误：java.lang.ClassCastException: com.sun.proxy.$Proxy* cannot be cast
 * to***问题解决方案 对于Spring AOP 采用两种代理方法，一种是常规JDK，一种是CGLIB:
 * StudentJDBCTemplate了一个接口StudentDAO，当代理对象实现了至少一个接口时，默认使用JDK动态创建代理对象;
 * 当代理对象没有实现任何接口时，就会使用CGLIB方法。由于StudentJDBCTemplate实现了StudentDAO接口，
 * 所以强制转换必须用父类StudentDAO来定
 */
public class MainApp {
	public static ApplicationContext context = null;

	/*
	 * application-datasource2.xml和application-datasource.xml配置都是一样的，除了以下切面配置部分。
	 * 配置方法一：application-datasource.xml中使用<aop:advisor>方式配置，
	 * 切面方法需要实现org.aopalliance.intercept.MethodInterceptor，
	 * 可以参见zhaohe.study.datasource.aop.DataSourceAdvisor;
	 * 配置方法二：application-datasource2.xml中使用<aop:aspect>方式配置，
	 * <aop:aspect>中指定切点，可以参见zhaohe.study.datasource.aop.DataSourceAspect;
	 * 
	 */
	@Before
	public void init() {
		//context = new ClassPathXmlApplicationContext("application-datasource.xml");
		context = new ClassPathXmlApplicationContext("application-datasource2.xml");
	}

	@Test
	public void test() {
		TestService testService = (TestService) context.getBean("testService");
		testService.save();
	}
}