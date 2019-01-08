package zhaohe.study.service;

import zhaohe.study.domin.User;

public interface RedisService {
	User saveKeys(String key, String value);

	User getKeys(String key);

}
