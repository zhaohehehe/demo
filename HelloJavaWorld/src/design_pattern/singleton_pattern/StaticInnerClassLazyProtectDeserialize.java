package zhaohe.study.design;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StaticInnerClassLazyProtectDeserialize {

	public static void main(String[] args) {
		try {
			SingletonStaticInnerSerialize serialize = SingletonStaticInnerSerialize.getInstance();
			System.out.println(serialize.hashCode());
			// 序列化
			FileOutputStream fo = new FileOutputStream("test");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(serialize);
			oo.close();
			fo.close();
			// 反序列化
			FileInputStream fi = new FileInputStream("test");
			ObjectInputStream oi = new ObjectInputStream(fi);
			SingletonStaticInnerSerialize serialize2 = (SingletonStaticInnerSerialize) oi.readObject();
			oi.close();
			fi.close();
			System.out.println(serialize2.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

// 使用内部类实现单例模式，在遇见序列化和反序列化的场景，得到的不是同一个实例
// 解决这个问题是在序列化的时候使用readResolve方法，即去掉注释的部分
class SingletonStaticInnerSerialize implements Serializable {

	private static final long serialVersionUID = 1L;

	private static class InnerClass {
		private static final SingletonStaticInnerSerialize singletonStaticInnerSerialize = new SingletonStaticInnerSerialize();
	}

	private SingletonStaticInnerSerialize() {
	}

	public static SingletonStaticInnerSerialize getInstance() {
		return InnerClass.singletonStaticInnerSerialize;
	}

	// 反序列化机制在恢复Java对象时无需调用构造器来初始化JAVA对象.对于单例类、枚举在实现序列化时都应该提供readResolve()方法，这样才能保证反序列的对象依然正常。
	// 建议：对于final类重写readResolve()不会有任何问题，否则，重写readResolve方法时应尽量使用private修饰
	// 保护性复制,在readObject之后调用
	private Object readResolve() {
		System.out.println("调用了readResolve方法");
		return InnerClass.singletonStaticInnerSerialize;
	}
}
