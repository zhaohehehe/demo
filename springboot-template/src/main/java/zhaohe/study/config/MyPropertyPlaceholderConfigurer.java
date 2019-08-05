package zhaohe.study.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		System.out.println(props.getProperty("test1.driver"));
		System.out.println(props.getProperty("test2.driver"));
		System.out.println(props.getProperty("server.context-path"));
		super.processProperties(beanFactoryToProcess, props);
	}
}
