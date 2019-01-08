package com.shengsiyuan.study.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectObjectCopy {
	
	public static void main(String[] args) throws Exception {
		ReflectObjectCopy rc=new ReflectObjectCopy();
		Person person=new Person("tom",9);
		person.setId(1L);
		Person copyPerson=(Person) rc.copy(person);
		System.out.println(copyPerson.getId()+","+copyPerson.getName()+","+copyPerson.getAge());
	}

	//该方法实现对Person类的拷贝操作
	public Object copy(Object obj) throws Exception{
		Class<?> classType=obj.getClass();
		System.out.println(classType.getName());
		Object objCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		//获得对象的所有成员变量
		Field[] fields=classType.getDeclaredFields();
		for(Field field:fields){
			String fieldName=field.getName();//name,id,age
			String firstLetter=fieldName.substring(0, 1).toUpperCase();
			
			String getMethodName="get"+firstLetter+fieldName.substring(1);
			String setMethodName="set"+firstLetter+fieldName.substring(1);
			
			Method getMethod=classType.getMethod(getMethodName, new Class[]{});
			Method setMethod=classType.getMethod(setMethodName, new Class[]{field.getType()});
			
			Object value=getMethod.invoke(obj, new Object[]{});
			setMethod.invoke(objCopy, new Object[]{value});
			
		}
		return objCopy;
		/**
		 * java.lang.reflect.Constructor.newInstance()方法
		 * 前提条件：构造函数必须是public的；而且适用于代参构造方法
		 */
		//Constructor<?> cons1=classType.getConstructor(new Class[]{});
		//Object obj1=cons1.newInstance(new Object[]{});
		/**
		 * 以上两行代码等价于下行代码
		 * java.lang.Class.newInstance()方法
		 * 前提条件：构造函数是无参的；
		 */
		//Object obj0=classType.newInstance();
		/**
		 * 带参构造
		 */
		//Constructor<?> cons2=classType.getConstructor(new Class[]{String.class,int.class});
		//Object obj2=cons2.newInstance(new Object[]{"zhaohe",24});
		
	}

}
class Person{
	private Long id;
	private String name;
	private int age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Person(){}
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
}