package test.design_pattern.singleton_pattern;
/**
 * 多线程安全；
 * 
 * @author zhaohe
 *
 */
public class FourDoubleCheckLocking {
	private volatile static FourDoubleCheckLocking singleton;
	private FourDoubleCheckLocking(){}
	public static FourDoubleCheckLocking getSingleton(){
		if(singleton==null){
			//对整个类加锁
			synchronized(FourDoubleCheckLocking.class){
				if(singleton==null){
					singleton=new FourDoubleCheckLocking();
				}
			}
		}
		return singleton;
	}

}