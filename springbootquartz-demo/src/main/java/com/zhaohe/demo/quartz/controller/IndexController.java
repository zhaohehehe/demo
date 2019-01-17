package com.zhaohe.demo.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 不能使用RestController,不希望返回json，而是html页面
public class IndexController {

	@RequestMapping("/")
	public String index() throws Exception {
		System.out.println("=====================================");
		return "index";
	}

}
