package com.zhaohe.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Testable{
	
}
class Test{
	@Testable
	public static void test1(){
	} 
	@Testable
	public static void test2(){
		throw new IllegalArgumentException("测试失败");
	}
}
public class ReflectAnnotation {
	private ReflectAnnotation(){}
	public static void process(Class<?> clz) throws NoSuchMethodException, SecurityException, ClassNotFoundException{
		//Annotation[] arr=Class.forName(className).getMethod("").getAnnotations();
		int passed=0;
		int failed=0;
		for(Method method:clz.getMethods()){
			/*if(arr[0] instanceof Deprecated){
			System.out.println(((Deprecated)arr[0]).annotationType());
			}*/
			if(method.isAnnotationPresent(Testable.class)){
				try{
					method.invoke(null);
					//测试成功
					passed++;
				}catch(Exception e){
					System.out.println("方法"+method+"运行失败："+e.getCause());
					failed++;
				}
			}
		}
		System.out.println("共运行"+(passed+failed)+"个方法，"+"其中成功"+passed+"个，"+"失败"+failed+"个");
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		ReflectAnnotation.process(com.zhaohe.demo.util.annotation.Test.class);
	}
}
