package zhaohe.study.datasource.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import zhaohe.study.datasource.DataSourceHolder;
import zhaohe.study.datasource.annotation.DataSource;

public class DataSourceAdvisor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		resolveDataSource(invocation.getThis().getClass(), invocation.getMethod());
		System.out.println("===========选定数据源=============" + DataSourceHolder.getDataSource());
		return null;
	}

	/**
	 * 提取目标对象方法注解和类型注解中的数据源标识
	 * 
	 * @param clazz
	 * @param method
	 */
	private void resolveDataSource(Class<?> clazz, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			// 默认使用类型注解
			if (clazz.isAnnotationPresent(DataSource.class)) {
				DataSource source = clazz.getAnnotation(DataSource.class);
				DataSourceHolder.setDataSource(source.name());
			}
			// 方法注解可以覆盖类型注解
			Method m = clazz.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource source = m.getAnnotation(DataSource.class);
				DataSourceHolder.setDataSource(source.name());
			}
		} catch (Exception e) {
			System.out.println(clazz + ":" + e.getMessage());
		}
	}

}
