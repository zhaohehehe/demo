package zhaohe.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zhaohe.study.domin.User;
import zhaohe.study.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	RedisService redisService;
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@RequestMapping("/")
	public String indexThymeleaf() {
		redisService.saveKeys("test1", "test1");
		redisService.getKeys("test1");
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("key2", "value2");
		redisTemplate.opsForValue().set("key3", users);

		String result1 = redisTemplate.opsForValue().get("key1").toString();
		String result2 = redisTemplate.opsForValue().get("key2").toString();
		String result3 = redisTemplate.opsForValue().get("key3").toString();
		System.out.println("缓存result：" + result1 + "  " + result2 + "   " + result3);
		return "index";
	}

}
