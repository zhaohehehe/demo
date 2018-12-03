package zhaohe.study.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Pointcut("execution( * zhaohe.study.mapper.*.*(..))")
	public void mapperAspect() {

	}

	@Before("mapperAspect()")
	public void switchDataSource(JoinPoint point) throws NoSuchMethodException, SecurityException {
		// 获取Mapper接口的Type
		Class<?> clazz = point.getSignature().getDeclaringType();
		//得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
		// Class<?> clazz =
		// point.getTarget().getClass();这样是取不到代理类的，因为使用CGLIB代理针对的是实现。
		// Spring AOP can also use CGLIB proxies.
		// This is necessary to proxy classes, rather than interfaces.
		// CGLIB is used by default if a business object does not implement an
		// interface.
		// As it is good practice to program to interfaces rather than classes,
		// business classes normally will implement one or more business
		// interfaces.
		String methodName = point.getSignature().getName();
		Method method = clazz.getMethod(methodName,argClass);
		// 判断是否存在@DataSourceAnno注解
		if (method.isAnnotationPresent(DataSourceAnno.class)) {
			DataSourceAnno annotation = method.getAnnotation(DataSourceAnno.class);
			String dataSourceKey = annotation.value();
			if (DynamicDataSourceContextHolder.containDataSourceKey(dataSourceKey)) {
				// 负载数据源
				// DynamicDataSourceContextHolder.useSlaveDataSource();
				DynamicDataSourceContextHolder.setDataSourceKey(dataSourceKey);
				logger.info("Switch DataSource to [{}] in Method [{}]",
						DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
			} else {
				logger.info(
						"DataSource = " + dataSourceKey
								+ " is missing,switch DataSource to DEFAULT = [{}] in Method [{}]",
						DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
			}

		} else {
			logger.info("DEFAULT DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
					point.getSignature());
		}
	}

	@After("mapperAspect())")
	public void restoreDataSource(JoinPoint point) {
		DynamicDataSourceContextHolder.clearDataSourceKey();
		logger.info("Restore DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
				point.getSignature());
	}

}
