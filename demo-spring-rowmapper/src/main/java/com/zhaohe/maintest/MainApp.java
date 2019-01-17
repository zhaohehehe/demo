package com.zhaohe.maintest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhaohe.dao.StudentDAO;
import com.zhaohe.po.Student;

public class MainApp {
	public static ApplicationContext context = null;
	/*
	 * 错误1：java.lang.ClassCastException: com.sun.proxy.$Proxy* cannot be cast to***问题解决方案
	 * 对于Spring AOP 采用两种代理方法，一种是常规JDK，一种是CGLIB:
	 * StudentJDBCTemplate了一个接口StudentDAO，当代理对象实现了至少一个接口时，默认使用JDK动态创建代理对象;
	 * 当代理对象没有实现任何接口时，就会使用CGLIB方法。由于StudentJDBCTemplate实现了StudentDAO接口，所以强制转换必须用父类StudentDAO来定
	*/
	public static StudentDAO studentJDBCTemplate = null;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application-beans.xml");
		studentJDBCTemplate = (StudentDAO) context.getBean("studentJDBCTemplate");
	}
	@Test
	public void test() {

		System.out.println("------Records Creation--------");
		studentJDBCTemplate.create("Maxsu", 21);
		studentJDBCTemplate.create("Curry", 22);
		studentJDBCTemplate.create("Suzend", 23);

		System.out.println("------Listing Multiple Records--------");
		List<Student> students = studentJDBCTemplate.listStudents();
		for (Student record : students) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}
		System.out.println("------单一查询--------");
		Student stu = studentJDBCTemplate.getStudent(1);
		System.out.println(stu.toString());
		System.out.println("------查询返回主键ID--------");
		int id = studentJDBCTemplate.insertSql(new String[] { "90", "Aaa", "20" });
		System.out.println(id);
	}
}