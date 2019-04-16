package com.dubbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.test.dubbox.api.RegisterService;
import com.test.dubbox.api.UserService;
import com.test.dubbox.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ConsumerTest {

	private final Logger log = LoggerFactory.getLogger(ConsumerTest.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterService registerService;

	@Test
	public void test1() {
		try {
			User user = userService.getUserByPhone("1233523452");
			System.out.println("============================================================");
			log.info(JSONObject.toJSONString(user));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	@Test
	public void test2() {
		try {
			User user = userService.getUserById(10l);
			System.out.println("============================================================");
			log.info(JSONObject.toJSONString(user));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	@Test
	public void test3() {
		try {
			User user = registerService.register(new User(10l,"alice","1234567890"));;
			System.out.println("============================================================");
			log.info(JSONObject.toJSONString(user));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
