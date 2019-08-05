package zhaohe.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zhaohe.study.config.MyPropertyPlaceholderConfigurer;
import zhaohe.study.config.SpringContextUtil;
import zhaohe.study.config.TestBean;
import zhaohe.study.service.CityService;
import zhaohe.study.service.impl.CityServiceImpl2;

@RestController
public class CityController {
	
	@Autowired
	CityService cityService;

	@Autowired
	@Qualifier(value = "MyTestBean1")
	TestBean testBean1;

	@Autowired
	@Qualifier(value = "MyTestBean2")
	TestBean testBean2;

	@Autowired
	SpringContextUtil context;

	@RequestMapping("/")
	public String indexThymeleaf() {
		int chinaCountryCount = cityService.selectCount("CHN");
		String result = "中国城市数量：" + chinaCountryCount;
		System.out.println(result);
		System.out.println("=====================");
		System.out.println(testBean1.hello());
		System.out.println(testBean2.hello());
		System.out.println(((TestBean) context.getBean("MyTestBean2")).hello());
		
		MyPropertyPlaceholderConfigurer ppc = (MyPropertyPlaceholderConfigurer) context.getBean("MyPropertyPlaceholderConfigurer");
		
		System.out.println(context.getBean("dataSource"));
		
		CityService cityService1 = new CityServiceImpl2();
		
		System.out.println("----"+cityService1.selectCount("CHN"));
		
		return result;
	}

}
