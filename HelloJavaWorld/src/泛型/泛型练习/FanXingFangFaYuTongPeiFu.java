package 泛型.泛型练习;

import java.util.Collection;
import java.util.List;

/**
 * 大多数情况下，泛型方法可以和通配符进行替换，但是如果没有以来关系，使用通配符即可。如果类型依赖比较强，推荐使用泛型方法
 * @author zhaohe
 *
 */
public class FanXingFangFaYuTongPeiFu<E> {
	//泛型构造器
	public <T> FanXingFangFaYuTongPeiFu(T t){}
	static <T, S extends T> void fill(Collection<T> des,Collection<S> src){
		
	}
	//通配符下限
	static <T> void fill2(Collection<T> des,Collection<? extends T> src){
		
	}
	static <T,S extends T> List<S> fill3(Collection<T> des,Collection<S> src){
		return null;
		
	}
	//通配符上线
	static <T,S extends T> T copy(Collection<T> des,Collection<S> src){
		T last=null;
		for(S a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	/**
	 * copy和copy2方法返回的类型是T，但是实际的参数类型是S 或者 ? extends T，所以失去了参数的类型 
	 * @param des
	 * @param src
	 * @return
	 */
	static <T> T copy2(Collection<T> des,Collection<? extends T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	/**
	 * copy3方法返回的类型是T，实际的参数类型也是T，没有失去参数的类型
	 * @param des
	 * @param src
	 * @return
	 */
	static <T> T copy3(Collection<? super T> des,Collection<T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	//copy4和copy3不能同时存在，否则不知道用哪个，因为通配符上限和下限都符合
	static <T> T copy4(Collection<T> des,Collection<? extends T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
}
