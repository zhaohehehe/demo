package com.zhaohe.demo.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(value = FKTags.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@interface FKTag{
	String name() default "测试";
	int value();
}
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@interface FKTags{
	FKTag[] value();
}
public class RepeatAnnotation {
	/**
	 * 传统重复注解,不加@Repeatable(value = FKTags.class)
	 */
	@FKTag(value = 1)
	@FKTags(value = { @FKTag(value = 0),@FKTag(value = 0,name="test") })
	private int test01;
	
	@FKTag(value = 1)
	@FKTag(value = 2)
	private int test02;
	public void test(){
		//传统
		FKTags ftags=this.getClass().getDeclaredAnnotation(FKTags.class);
		FKTag[] tags=this.getClass().getDeclaredAnnotationsByType(FKTag.class);
		for(FKTag tag:tags){
			System.out.println(tag.value()+tag.name());
		}
	}
}
