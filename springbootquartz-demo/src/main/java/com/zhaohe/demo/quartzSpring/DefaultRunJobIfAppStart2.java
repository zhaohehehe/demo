package com.zhaohe.demo.quartzSpring;

import static org.quartz.JobBuilder.newJob;

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
@Order(value = 1)
@PropertySource(value = { "classpath:udfquartz.properties" })
public class DefaultRunJobIfAppStart2 implements ApplicationRunner {

	@Value("${udfquartz.cronExpress}")
	private String cronExpress;
	@Autowired
    private Scheduler scheduler;

	@Override
	// 应用启动后，任务执行入口
	public void run(ApplicationArguments args) throws Exception {

		JobDetail job = newJob(YourJob.class).withIdentity("udfJobName2", "udfJobGroup2").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("udfTriggerName2", "udfTriggerGroup2").startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpress)).build();
		scheduler.scheduleJob(job, trigger);
	}

}
