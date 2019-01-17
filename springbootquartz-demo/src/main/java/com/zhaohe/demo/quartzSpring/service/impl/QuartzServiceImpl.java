package com.zhaohe.demo.quartzSpring.service.impl;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaohe.demo.quartzSpring.service.QuartzService;

public class QuartzServiceImpl implements QuartzService{
	
	@Autowired
    private Scheduler scheduler;

	@Override
	public void service() {
		System.out.println("quartz service impl");
		
	}

}
