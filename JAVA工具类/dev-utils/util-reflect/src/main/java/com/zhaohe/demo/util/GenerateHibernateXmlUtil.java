package com.zhaohe.demo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

/**
 * 持久化对象，修饰类、接口、枚举等类型
 *
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Persistent {
	String table();
}

/**
 * 标识属性
 *
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Id {
	String column();

	String type();

	String generator();
}

/**
 * 普通成员变量
 *
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Property {
	String column();

	String type();
}

@Persistent(table = "person")
class Person {
	@Id(column = "person_id", generator = "identity", type = "integer")
	private int id;
	@Property(column = "person_name", type = "string")
	private String name;
	@Property(column = "person_age", type = "integer")
	private int age;

	public Person() {
	}

	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}

/**
 * 编译时提取处理文件
 * 
 * @author zhaohe
 *
 */
public class GenerateHibernateXmlUtil extends AbstractProcessor {
	public static void main(String[] args) {
		//执行以下命令即可：javac -processor GenerateHibernateXmlUtil Person.java
	}
	// 循环处理每个需要处理的程序对象
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		// 定义文件输出流，生成额外文件
		PrintStream ps = null;
		try {
			for (Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
				Name clzName = t.getSimpleName();
				Persistent per = t.getAnnotation(Persistent.class);
				ps = new PrintStream(new FileOutputStream(clzName + ".hbm.xml"));
				ps.println("<?xml version=\"1.0\"?>");
				ps.println("<!DOCTYPE hibernate-mapping PUBLIC>");
				ps.println("	\"-//Hibernate/Hibernate " + "Mapping DTD 3.0//EN\"");
				ps.println("	\"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
				ps.println("<hibernate-mapping>");
				ps.println("	<class name=\"" + t);
				ps.println("\" table=\"" + per.table() + "\"");
				for (Element f : t.getEnclosedElements()) {
					// 只处理成员变量定义钱的注解
					if (f.getKind() == ElementKind.FIELD) {
						Id id = f.getAnnotation(Id.class);
						// 处理Id注解
						if (id != null) {
							ps.println("		<id name=\"" + f.getSimpleName() + "\"" + " column=\"" + id.column()
									+ "\"" + " type=\"" + id.type() + "\"" + ">");
							ps.println("		<generator class=\"" + id.generator() + "\"" + ">");
							ps.println("		</id>");
						}
						// 处理Property注解
						Property p = f.getAnnotation(Property.class);
						if (p != null) {
							ps.println("		<property name=\"" + f.getSimpleName() + "\"" + " column=\""
									+ p.column() + "\"" + " type=\"" + p.type() + "\"" + ">");
						}
					}
				}
				ps.println("	</class>");
				ps.println("</hibernate-mapping>");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return true;
	}

}
