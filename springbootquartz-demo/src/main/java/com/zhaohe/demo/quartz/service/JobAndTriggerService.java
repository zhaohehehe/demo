package com.zhaohe.demo.quartz.service;

import com.github.pagehelper.PageInfo;
import com.zhaohe.demo.quartz.entity.JobAndTrigger;

public interface JobAndTriggerService {
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
