package com.zhaohe.demo.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {
	
	/**
	 * 获取泛型父类的实际Class类型
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index){
		//获取泛型父类
		Type genSupperClass=clazz.getGenericSuperclass();
		//如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if(!(genSupperClass instanceof ParameterizedType)){
			return Object.class;
		}
		//返回表示此类型 实际类型参数的Type对象数组,数组里放的都是对应类型的Class, 
		//如BuyerServiceBean extends DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
		Type[] params=((ParameterizedType)genSupperClass).getActualTypeArguments();
		if(index>=params.length||index<0){
			throw new RuntimeException("索引值"+index+"超出泛型个数的范围");
		}
		if(!(params[index] instanceof Class)){
			return Object.class;
		}
		return  (Class) params[index];
	}
}
