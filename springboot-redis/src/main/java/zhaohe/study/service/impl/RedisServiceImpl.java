package zhaohe.study.service.impl;

import org.springframework.stereotype.Service;

import zhaohe.study.domin.User;
import zhaohe.study.redis.annotation.CacheableExtends;
import zhaohe.study.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Override
	@CacheableExtends(expired = 10)
	public User saveKeys(String key, String value) {
		User user = new User();
		user.setName("test1");
		// TODO Auto-generated method stub
		return user;
	}

	@CacheableExtends(expired = 10000)
	@Override
	public User getKeys(String key) {
		User user = new User();
		user.setName("test2");
		// TODO Auto-generated method stub
		return user;
	}

}
