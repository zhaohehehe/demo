package test.reflect;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenericHelp {
	public static void main(String[] args) {
		//GenericHelp.testType();
		GenericHelp.testGenericSuperclass();
	}

	public static void testGenericSuperclass() {
		// 获取泛型父类
		Type genSupperClass = TestGenericChild.class.getGenericSuperclass();
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genSupperClass instanceof ParameterizedType)) {
			System.out.println("genSupperClass" + genSupperClass.getClass() + Object.class);
		} else {
			Type[] params = ((ParameterizedType) genSupperClass).getActualTypeArguments();
			System.out.println("getRawType：" + ((ParameterizedType) genSupperClass).getRawType());
			System.out.println("getOwnerType：" + ((ParameterizedType) genSupperClass).getOwnerType());
			for (int i = 0; i < params.length; i++) {
				System.out.println("第" + i + "个: " + params[i]);
				if (!(params[i] instanceof Class)) {
					System.out.println(Object.class);
				}
			}
		}
	}

	public static void testType() {
		// 参考：http://blog.csdn.net/qq_29227939/article/details/53465128
		/*
		 * 0.java.lang.reflect.Type
		 */
		Type type = Student.class.getGenericSuperclass();
		System.out.println("*.class.getGenericSuperclass()" + type);
		Type[] type2 = Student.class.getGenericInterfaces();
		for (int index = 0; index < type2.length; index++) {
			System.out.println("*.class.getGenericInterfaces()" + type2[index]);
		}
		/*
		 * 1.public interface TypeVariable<D extends GenericDeclaration> extends
		 * Type Class、Method、Constructor 都实现了 GenericDeclaration接口， 该接口含有
		 * TypeVariable<?>[] getTypeParameters()方法；
		 */
		// TypeVariable<?>[] typeVariable=Person.class.getTypeParameters();
		TypeVariable<?>[] typeVariable = Teacher.class.getTypeParameters();
		for (int index = 0; index < typeVariable.length; index++) {
			System.out.println("typeVariable" + "[" + index + "]= " + typeVariable[index]);
			// typeVariable[0]= T
			// typeVariable[1]= V
		}
		for (int index = 0; index < typeVariable.length; index++) {
			Type[] bounds = typeVariable[index].getBounds();
			for (int i = 0; i < bounds.length; i++) {
				System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]= " + bounds[i]);
			}
		}
		// 声明此参数化类型的类
		/*
		 * 2.public interface ParameterizedType extends Type
		 */
		for (int index = 0; index < typeVariable.length; index++) {
			Type[] bounds = typeVariable[index].getBounds();
			for (int i = 0; i < bounds.length; i++) {
				if (bounds[i] instanceof ParameterizedType) {
					// 获取声明此参数化类型的类(java.lang.Comparable)
					Type rawType = ((ParameterizedType) bounds[i]).getRawType();
					System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "].getRawType()= " + rawType);
					// 获取实际的类型参数数组([? super T])：该? super T为WildcardType类型
					Type[] actualTypeArguments = ((ParameterizedType) bounds[i]).getActualTypeArguments();
					System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "].getActualTypeArguments()= "
							+ actualTypeArguments);
				} else if (bounds[i] instanceof Class) {
					System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]为Class类型！");
				}
			}
		}
		/*
		 * 3.public interface WildcardType extends Type
		 */
		for (int index = 0; index < typeVariable.length; index++) {
			Type[] bounds = typeVariable[index].getBounds();
			for (int i = 0; i < bounds.length; i++) {
				if (bounds[i] instanceof ParameterizedType) {
					// 获取声明此参数化类型的类(java.lang.Comparable)
					Type rawType = ((ParameterizedType) bounds[i]).getRawType();
					if (rawType instanceof WildcardType) {
						Type[] upperBounds = ((WildcardType) rawType).getUpperBounds();
						Type[] lowerBounds = ((WildcardType) rawType).getLowerBounds();
					} else {
						System.out.println(
								"typeVariable[" + index + "]" + "bounds[" + i + "].getRawType()的类型不是WildcardType");
					}
					// 获取实际的类型参数数组([? super T])：该? super T为WildcardType类型
					Type[] actualTypeArguments = ((ParameterizedType) bounds[i]).getActualTypeArguments();
					for (int j = 0; j < actualTypeArguments.length; j++) {
						System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]" + "actualTypeArguments["
								+ j + "]= " + actualTypeArguments[j]);
						if (actualTypeArguments[j] instanceof WildcardType) {
							Type[] upperBounds = ((WildcardType) actualTypeArguments[j]).getUpperBounds();
							System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]"
									+ "actualTypeArguments[" + j + "]" + "upperBounds[0]= " + upperBounds[0]);
							Type[] lowerBounds = ((WildcardType) actualTypeArguments[j]).getLowerBounds();
							System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]"
									+ "actualTypeArguments[" + j + "]" + "lowerBounds[0]= " + lowerBounds[0]);
						} else {
							System.out.println("typeVariable[" + index + "]" + "bounds[" + i + "]"
									+ "actualTypeArguments[" + j + "]" + "的类型不是WildcardType");
						}
					}
				} else if (bounds[i] instanceof Class) {
					System.out.println(bounds[i] + "为Class类型！");
				}
			}
		}
		/*
		 * 4.public interface GenericArrayType extends Type
		 */
	}
}

class Person<T> extends Student<Key, Value> implements Comparable<T>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Student<T, V> implements Serializable, Comparator<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

class Teacher<T, K extends Comparable<? super T>> {
}

class Key {
}

class Value {
}

class TestGenericSuper<T, X, Z, R, M> {
	public T f1;
	public X f2;
	public Z f3;
	List<T> list = new ArrayList<T>();

	public TestGenericSuper() {
	}
}

@SuppressWarnings("rawtypes")
class TestGenericChild<T> extends TestGenericSuper<Test, String, List, Class, T> {
	public T t1;
}
