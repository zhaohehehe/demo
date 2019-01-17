package com.zhaohe.demo.quartz.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class MyJob implements BaseJob {

	private static Logger _log = LoggerFactory.getLogger(MyJob.class);
	
	@Autowired
    private RedisTemplate<String, ?> redisTemplate;

	public MyJob() {

	}

	public void execute(JobExecutionContext context) {
		String info = null;
		info = context.getTrigger().getJobKey().getName();
		_log.error("Hello My Job执行时刻: " + new Date() +" "+info);

	}
}