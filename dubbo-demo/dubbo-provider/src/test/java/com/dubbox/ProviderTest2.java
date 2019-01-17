package com.dubbox;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

public class ProviderTest2 {
	private final static Logger logger = LoggerFactory.getLogger(ProviderTest2.class);

	public static void main(String[] args) throws Exception {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
			context.start();
			logger.info("Provider Context start success");
		} catch (Exception e) {
			logger.error("Provider Context start error\n" + e.getMessage());
		}
		synchronized (ProviderTest2.class) {
			while (true) {
				try {
					ProviderTest2.class.wait();
				} catch (InterruptedException e) {
					logger.error("synchronized error\n" + e.getMessage());
				}
			}
		}
	}
}
