package zhaohe.study.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 其他摘水果线程
 * 
 * @author ZH
 *
 */
// @Component @Scope("prototype")//将该Bean托管给spring框架时使用
public class LittleKidFillFruitsToBasketRunnable implements Runnable {

	private FruitsBasketCallable fruitsBasketCallable;
	private String kidName;
	private List<Map<String, Object>> fruitsBelongtoKid;

	@Override
	public void run() {
		try {
			// 模拟摘果子耗时
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fruitsBasketCallable.fillBasket(kidName, fruitsBelongtoKid);
	}

	public FruitsBasketCallable getFruitsBasketCallable() {
		return fruitsBasketCallable;
	}

	public void setFruitsBasketCallable(FruitsBasketCallable fruitsBasketCallable) {
		this.fruitsBasketCallable = fruitsBasketCallable;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public List<Map<String, Object>> getFruitsBelongtoKid() {
		return fruitsBelongtoKid;
	}

	public void setFruitsBelongtoKid(List<Map<String, Object>> fruitsBelongtoKid) {
		this.fruitsBelongtoKid = fruitsBelongtoKid;
	}

}
