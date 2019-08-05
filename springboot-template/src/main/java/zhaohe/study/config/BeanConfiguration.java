package zhaohe.study.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class BeanConfiguration {
	@Bean(name = "MyTestBean1")
	TestBean myTestBean1() {
		TestBean bean = new TestBean();
		bean.setName("MyTestBean1");
		return bean;
	}

	@Bean(name = "MyTestBean2")
	TestBean myTestBean2() {
		TestBean bean = new TestBean();
		bean.setName("MyTestBean2");
		return bean;
	}

	@Bean
	SpringContextUtil springContextUtil() {
		return new SpringContextUtil();
	}
	
	/*
	@Bean("MyPropertyPlaceholderConfigurer")
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		final MyPropertyPlaceholderConfigurer ppc = new MyPropertyPlaceholderConfigurer();
		final List<Resource> resourceList = new ArrayList<>();
		final String configPath = "config";

		try {
			File file = new ClassPathResource(configPath).getFile();
			String[] resources = file.list();
			for (String resource : resources) {
				if (resource.startsWith("b") || "database.properties".equalsIgnoreCase(resource)) {
					resource = configPath + "/" + resource;
					resourceList.add(new ClassPathResource(resource));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ppc.setLocations(resourceList.toArray(new Resource[] {}));
		ppc.setFileEncoding("UTF-8");
		return ppc;
	}*/
	/**
	 * <pre>
	 * 上述Bean中的方法在Eclipse中测试没问题，但是部署JAR包之后会找不到配置文件，因为getFile()不能从JAR包中读取文件：
	 * java.io.FileNotFoundException: 
	 * class path resource [config] cannot be resolved to absolute file path because it does not reside in the file 
	 * system: jar:file:/*.jar!/BOOT-INF/classes!/config
	 * 所以使用PathMatchingResourcePatternResolver读取</pre>
	 * @return
	 */
	@Bean("MyPropertyPlaceholderConfigurer")
	public PropertyPlaceholderConfigurer customPropertyPlaceholderConfigurer() {
		final MyPropertyPlaceholderConfigurer ppc = new MyPropertyPlaceholderConfigurer();
		final List<Resource> resourceList = new ArrayList<>();

		PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		Resource dbResource = resourceLoader.getResource("classpath:/config/database.properties");
		resourceList.add(dbResource);
		try {
			Resource[] resources = resourceLoader.getResources("classpath:/config/test/*.properties");
			for (Resource resource : resources) {
				resourceList.add(resource);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ppc.setLocations(resourceList.toArray(new Resource[] {}));
		ppc.setFileEncoding("UTF-8");
		return ppc;

	}
}
