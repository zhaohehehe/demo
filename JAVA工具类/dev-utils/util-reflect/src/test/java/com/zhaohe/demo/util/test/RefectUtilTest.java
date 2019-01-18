package com.zhaohe.demo.util.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.zhaohe.demo.util.ReflectUtil;
import com.zhaohe.demo.util.ReflectUtil.ClassReflect;
import com.zhaohe.demo.util.ReflectUtil.ConstructorReflect;
import com.zhaohe.demo.util.ReflectUtil.FieldReflect;
import com.zhaohe.demo.util.ReflectUtil.MethodReflect;
import com.zhaohe.demo.util.ReflectUtil.ObjectReflect;

public class RefectUtilTest {
	@Test
	public void createReflect() {
		//包装类
		ClassReflect cr = ReflectUtil.on(Person.class);
		ReflectUtil op = ReflectUtil.on(Person.class).create();
		//精确查找
		op.method("say",String.class).call((Object)"你好世界");
		op.method("selfIntroduction");
		//根据提供的参数类型查找
		op.method("say","你好");
		//ClassReflect cr = ReflectUtil.on("class name here");
		//ClassReflect cr = ReflectUtil.on("class name here ",ClassLoader.getSystemClassLoader());//指定类加载器
		/*//包装对象
		ObjectReflect or = ReflectUtil.on(new Person());
		//包装字段
		Field f = Person.class.getDeclaredField("name");
		FieldReflect fr = ReflectUtil.on(f);
		FieldReflect fr = ReflectUtil.on(f,new Person("mario"));//绑定字段默认作用对象
		//包装方法
		Method m = Person.class.getDeclaredMethod("say",String.class);
		MethodReflect mr = ReflectUtil.on(m);
		MethodReflect mr = ReflectUtil.on(m,new Person());//绑定方法默认作用对象
		MethodReflect mr = ReflectUtil.on(m,new Person(),new Object[]{"hello world"});//绑定方法默认作用对象和参数
		//包装构造器
		Constructor<?> constr = Person.class.getDeclaredConstructor(String.class,int.class);
		ConstructorReflect cr = ReflectUtil.on(constr);
		ConstructorReflect cr = ReflectUtil.on(constr,"john",26);//绑定默认构造参数
*/		// off() 获取被包装的对象
		// cr.off() == constr; //==> true
	}
}
class Person{
	private String name;
	private int age = 0;
	Person(){
		name = "default name";
	}
	Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	Person(String name){
		this.name = name;
	}
	String selfIntroduction(){
		return "Hello!My name is '"+name+"'";
	}
	void say(String something){
		System.out.println(name+"说："+something);
	}
	boolean wasDead(){
		return age > 200; 
	}
	public String getName(){
		return name;
	}
}
