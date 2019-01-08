package zhaohe.study.redis.aop;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import zhaohe.study.redis.annotation.CacheableExtends;

@Component
@Aspect
public class RedisCacheAspect {
	private final static Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	@Autowired
	private KeyGenerator keyGenerator;

	@Pointcut("@annotation(zhaohe.study.redis.annotation.CacheableExtends)")
	// @Pointcut(value = "(execution(* zhaohe.study..controller.*.*(..))&&
	// @annotation(zhaohe.study.redis.annotation.RedisCache)))")
	public void redisServiceAspect() {

	}

	@Around("redisServiceAspect()")
	public Object redisOperations(ProceedingJoinPoint point) throws NoSuchMethodException, SecurityException {
		Class<?>[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
		String methodName = point.getSignature().getName();
		Method method = point.getTarget().getClass().getMethod(methodName, argClass);

		String key = (String) keyGenerator.generate(point.getTarget(), method, (Object[]) method.getParameters());
		logger.info("======进入redis缓存切面====== key = "+key);
		// 获取RedisCache注解
		CacheableExtends cacheableExtends = ((MethodSignature) point.getSignature()).getMethod()
				.getAnnotation(CacheableExtends.class);
		Object obj = null;
		if (cacheableExtends != null) {

			if (redisTemplate.hasKey(key)) {
				obj = redisTemplate.opsForValue().get(key);
				logger.info("======从redis缓存中读取数据======:"+obj);
			} else {
				try {
					obj = point.proceed();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				logger.info("======redis中不存在该记录，从数据库查找 ======:"+obj);
				redisTemplate.opsForValue().set(key, obj, 100000, TimeUnit.MILLISECONDS);
				logger.info("======存入redis缓存======:"+obj);
			}
		}
		return obj;
	}
}