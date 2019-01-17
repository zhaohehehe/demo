package com.zhaohe.demo.quartz.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class YourJob implements BaseJob {

	private static Logger _log = LoggerFactory.getLogger(YourJob.class);
	
	@Autowired
    private RedisTemplate<String, ?> redisTemplate;

	public YourJob() {

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		String info = null;
		info = context.getTrigger().getJobKey().getName();
		_log.error("Hello Your Job执行时刻: " + new Date() +" "+info);

	}
}
