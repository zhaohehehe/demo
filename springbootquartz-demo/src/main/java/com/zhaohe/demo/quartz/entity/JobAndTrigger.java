package com.zhaohe.demo.quartz.entity;

public class JobAndTrigger {
	private String job_oid;
	private String job_name;
	private String job_group;
	private String job_class_name;
	private String trigger_name;
	private String cron_expression;
	public String getJob_oid() {
		return job_oid;
	}
	public void setJob_oid(String job_oid) {
		this.job_oid = job_oid;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getJob_group() {
		return job_group;
	}
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}
	public String getJob_class_name() {
		return job_class_name;
	}
	public void setJob_class_name(String job_class_name) {
		this.job_class_name = job_class_name;
	}
	public String getTrigger_name() {
		return trigger_name;
	}
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}
	public String getCron_expression() {
		return cron_expression;
	}
	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}
	
}
