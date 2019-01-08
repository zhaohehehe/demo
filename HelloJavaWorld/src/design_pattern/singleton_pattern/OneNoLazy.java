package test.design_pattern.singleton_pattern;
/**
 * 线程同步
 * @author zhaohe
 *
 */
public class OneNoLazy {
	private static OneNoLazy instance=new OneNoLazy();
	private OneNoLazy(){}
	public static OneNoLazy getInstance(){
		return instance;
	}
	

}
