package com.zhaohe.demo.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhaohe.demo.quartz.entity.JobAndTrigger;

@Mapper
public interface JobAndTriggerMapper {

	public List<JobAndTrigger> getJobAndTriggerDetails();

}
