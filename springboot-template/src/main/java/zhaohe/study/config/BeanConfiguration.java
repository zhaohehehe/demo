package zhaohe.study.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
	
	@Bean("MyPropertyPlaceholderConfigurer")
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		final MyPropertyPlaceholderConfigurer ppc = new MyPropertyPlaceholderConfigurer();
		final List<Resource> resourceList = new ArrayList<>();
		final String configPath = "config";

		try {
			File file = new ClassPathResource(configPath).getFile();
			String[] resources = file.list();
			for (String resource : resources) {
				if (true) {//resource.startsWith("b") || "database.properties".equalsIgnoreCase(resource)) {
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
	}
}
