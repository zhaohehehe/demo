package zhaohe.study.redis.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	/**
	 * CacheManager配置
	 * @param redisConnectionFactory
	 * @return
	 */
	//1.x配置方式
	/*@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager= new RedisCacheManager(redisTemplate);
	    cacheManager.setDefaultExpiration(60);
	    Map<String,Long> expiresMap=new HashMap<>();
	    expiresMap.put("Product",5L);
	    cacheManager.setExpires(expiresMap);
	    return cacheManager;
	}*/
	//2.x配置方式
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofHours(1)); // 设置默认缓存有效期一小时
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	/**
	 * 配置redistemplate
	 * springboot2.0之后，spring容器可以自动生成StringRedisTemplate和RedisTemplate
	 * <Object,Object>，可以直接注入使用。 但是在实际使用中，我们大多不会直接使用RedisTemplate
	 * <Object,Object>，而是会对key,value进行序列化，所以还需要新增一个配置类。
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		//
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JdkSerializationRedisSerializer的序列化方式）
		template.setValueSerializer(serializer);
		template.setHashValueSerializer(serializer);
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());

		template.setConnectionFactory(redisConnectionFactory);
		//如果没有指定任何序列化方法，使用默认序列化方法
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * 也可以对默认的StringRedisTemplate进行处理（springboot2.0之后可以直接使用）
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(factory);
		return stringRedisTemplate;
	}
	
	/**
	 * spring cache缓存的key默认是通过KeyGenerator生成的，其默认生成策略如下：
	 * 1.如果方法没有参数，则使用0作为key。
	 * 2.如果只有一个参数的话则使用该参数作为key。
	 * 3.如果参数多于一个的话则使用所有参数的hashCode作为key。
	 * 可以看出默认的key生成策略中并没有涉及方法名称和类，这就意味着如果我们有两个参数列表相同的方法，我们用相同的参数分别调用两个方法，当调用第二个方法的时候，
	 * spring cache将会返回缓存中的第一个方法的缓存值，因为他们的key是一样的。
	 * 所以我们需要自定义key策略来解决这个问题，将类名和方法名和参数列表一起来生成key。
	 * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
	 * 如: @Cacheable(value = "key", keyGenerator = "cacheKeyGenerator"
	 */
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, objects) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append("::" + method.getName() + ":");
			for (Object obj : objects) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}

}
