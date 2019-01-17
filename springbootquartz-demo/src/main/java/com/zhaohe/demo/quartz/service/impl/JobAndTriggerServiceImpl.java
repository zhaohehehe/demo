package com.zhaohe.demo.quartz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaohe.demo.quartz.entity.JobAndTrigger;
import com.zhaohe.demo.quartz.mapper.JobAndTriggerMapper;
import com.zhaohe.demo.quartz.service.JobAndTriggerService;

@Service(value = "JobAndTriggerService")
public class JobAndTriggerServiceImpl implements JobAndTriggerService {

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Override
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}
}
