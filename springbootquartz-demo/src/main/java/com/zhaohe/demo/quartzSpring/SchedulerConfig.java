package com.zhaohe.demo.quartzSpring;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
// @EnableScheduling
public class SchedulerConfig {

	@Autowired
	private UDFJobFactory udfJobFactory;

	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setQuartzProperties(quartzProperties());
		return factory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(udfJobFactory);
		// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setStartupDelay(1);
		return schedulerFactoryBean;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("udfJobFactory") UDFJobFactory udfJobFactory)
			throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
		factory.setOverwriteExistingJobs(true);
		// 延时启动
		// factory.setStartupDelay(20);
		// 加载quartz数据源配置
		factory.setQuartzProperties(quartzProperties());
		// 自定义Job Factory，用于Spring注入
		factory.setJobFactory(udfJobFactory);
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		return propertiesFactoryBean.getObject();
	}

	/**
	 * spring MVC web.xml中配置：
	 * <listener>org.quartz.ee.servlet.QuartzInitializerListener</listener>
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		return new QuartzInitializerListener();
	}

}
