package test.design_pattern.singleton_pattern;
/**
 * 多线程安全;
 * final成员变量 当你在类中定义变量时，在其前面加上final关键字，那便是说，这个变量一旦被初始化便不可改变，
 * 这里不可改变的意思对基本类型来说是其值不可变，
 * 而对于对象变量来说其引用不可再变，但引用变量所指向的对象中的内容还是可以改变的;
 * @author zhaohe
 *
 *static不能修饰普通Java类，但是可以修饰内部类;
 *
 *final修饰的方法不能被子类覆盖，不允许修改
 */
public class TwoStaticInnerClassLazy {
	public static class SingletonHolder{
		//同时由于instance是static final类型，保证了内存中只有这样一个实例存在，而且只能被赋值一次，从而保证了线程安全性
		//即使在高并发的情况下多个线程同时访问getInstance()方法 也能够保证实例的唯一性
		private static final TwoStaticInnerClassLazy INSTANCE=new TwoStaticInnerClassLazy();
	}
	private TwoStaticInnerClassLazy(){}
	//内部类只在需要的时候才会被类加载器加载，实现了懒加载，即在需要的时候才实例化出一个Manager,而且是唯一的一个
	public static final TwoStaticInnerClassLazy getInstance(){
		
		return SingletonHolder.INSTANCE;
	}
	public static void main(String[] args) {
		getInstance();
	}
}