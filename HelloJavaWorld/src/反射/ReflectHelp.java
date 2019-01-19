package test.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectHelp {
	public static void main(String[] args) {
		ReflectHelp.testField();
	}


	public static void testField() {
		Field[] fs = Test.class.getDeclaredFields();
		Field[] fs2 = Test.class.getFields();
		List<?> list = new ArrayList<Object>();
		String str = "";
		System.out.println(list.getClass().getName() + ";" + list.getClass().getModifiers());
		System.out.println(str.getClass().getName() + ";" + str.getClass().getModifiers());
		for (Field field : fs) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}
		for (Field field : fs2) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}
		TestGeneric1<Test> testGeneric1 = new TestGeneric1<Test>();
		TestGeneric1<String> testGeneric2 = new TestGeneric1<String>();
		Field[] fs3 = testGeneric1.getClass().getDeclaredFields();
		Field[] fs4 = testGeneric2.getClass().getFields();
		for (Field field : fs3) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}
		for (Field field : fs4) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}
		TestGeneric2 testGeneric3 = new TestGeneric2();
		TestGeneric3<String> testGeneric4 = new TestGeneric3<String>();
		Field[] fs5 = testGeneric3.getClass().getDeclaredFields();
		Field[] fs6 = testGeneric3.getClass().getFields();
		for (Field field : fs5) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}
		for (Field field : fs6) {
			System.out.println(field.getName() + ";" + field.getModifiers() + ";" + field.getType() + ";"
					+ field.getGenericType());
		}

	}

}

class SuperTest {
	String f1;
	private String f2;
	public String f3;

	public void m1() {
	}

	private String m2() {
		return f1;
	}

	private void m3() {
	}
}

class Test extends SuperTest {
	private String f1;

	public Test() {
		super();
		super.f1 = this.f1;
	}

	private void m4() {
	}
}

class TestGeneric1<T> {
	public T f1;
	List<T> list = new ArrayList<T>();
	public TestGeneric1() {
	}
}

class TestGeneric11<T, X, Z, R, M> {
	public T f1;
	public X f2;
	public Z f3;
	List<T> list = new ArrayList<T>();

	public TestGeneric11() {
	}
}

class TestGeneric2 extends TestGeneric1<Test> {

}

class TestGeneric3<T> extends TestGeneric1<Test> {
	public T t1;
}

class TestGeneric4<T> extends TestGeneric1<String> {
	public T t1;
}

@SuppressWarnings("rawtypes")
class TestGeneric5<T> extends TestGeneric11<Test, String, List, Class, T> {
	public T t1;
}
