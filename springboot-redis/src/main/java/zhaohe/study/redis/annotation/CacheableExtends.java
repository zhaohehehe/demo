package zhaohe.study.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @org.springframework.cache.annotation.Cacheable默认是不支持在@Cacheable上添加过期时间的,
 * 如果想要在注解上指定缓存失效时间，请自行百度方法。
 * 参考例子：https://blog.csdn.net/huanghongfei1/article/details/61195650
 * https://blog.csdn.net/qq_40591332/article/details/82352039
 * 这里只是简单自定义了一个Cacheable注解
 * @author ZH
 *
 */
@Documented
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CacheableExtends {
	
	/**
	 * 可以在注解中指定，也可以不使用
	 * @return
	 */
	String key() default "";
	
	/**
	 * 过期时间，单位为ms，默认一个小时
	 * @return
	 */
	long expired() default 3600_000;

}
