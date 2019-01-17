package com.zhaohe.demo.quartzSpring;

import static org.quartz.JobBuilder.newJob;

import java.io.IOException;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhaohe.demo.quartz.job.YourJob;

/**
 * 使用quartz提供的方法实现定时任务
 */
@Component
@Order(value = 2)
@PropertySource(value = { "classpath:udfquartz.properties" })
public class DefaultRunJobIfAppStart1 implements ApplicationRunner {

	@Value("${udfquartz.cronExpress}")
	private String cronExpress;
	@Autowired
	private SchedulerConfig config;
	@Autowired
	private UDFJobFactory udfJobFactory;
	
	@Override
	// 应用启动后，任务执行入口
	public void run(ApplicationArguments args) throws Exception {

		Scheduler scheduler;
		try {
			scheduler = config.schedulerFactoryBean(udfJobFactory).getScheduler();
			JobDetail job = newJob(YourJob.class).withIdentity("udfJobName1", "udfJobGroup1").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("udfTriggerName1", "udfTriggerGroup1").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpress)).build();
			scheduler.scheduleJob(job, trigger);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
