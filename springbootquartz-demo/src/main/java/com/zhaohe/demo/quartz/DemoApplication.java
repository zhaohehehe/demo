package com.zhaohe.demo.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@MapperScan("com.zhaohe.demo.quartz.mapper")
@ComponentScan(basePackages = "com.zhaohe.demo")
@ServletComponentScan(basePackages = "com.zhaohe.demo.quartz.**.controller")
@SpringBootApplication
public class DemoApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8080);

	}
}
