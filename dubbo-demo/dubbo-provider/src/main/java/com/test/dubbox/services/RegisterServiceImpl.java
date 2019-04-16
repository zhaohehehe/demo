package com.test.dubbox.services;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.test.dubbox.api.RegisterService;
import com.test.dubbox.entity.User;

/**
 * javax.ws.rs-api
 * 常用注解：https://blog.csdn.net/qq_22177809/article/details/86592660
 * 注意：如果消费端想要访问提供者的rest服务，这里需要加上JAX-RS的Annotation
 * <p>
 * 注解建议写在实现类上，避免污染接口，保持接口的纯净和广泛使用性。如果接口和实现类都写了注解，则会忽略接口上的注解
 */

@Service("registerService")
@Path("user")
@Produces({ "application/json; charset=UTF-8", "text/xml; charset=UTF-8" })
public class RegisterServiceImpl implements RegisterService {

	/**
	 * 在Bean上面配置JAX-RS的Annotation,访问:http://ip:port/user/register/12
	 */
	@GET
	@Path("/register/{id}")
	@Override
	public User register(@BeanParam User user) {
		return user;
	}

}
