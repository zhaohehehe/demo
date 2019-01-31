package zhaohe.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 主线程： 果园丰收啦，小朋友们纷纷到树林里摘果子存入仓库（主线程）！
 * 可惜只有一个水果篮子，所以大家（其他各个生产水果的线程）都向该篮子中装水果，直到将其装满后统一存入仓库
 *
 */
// @Component @Scope("prototype")//将该Bean托管给spring框架时使用
public class FruitsBasketCallable implements Callable<Object> {

	private CountDownLatch count;
	// @Autowired//将该Bean托管给spring框架时使用
	WarehouseMapper warehouseMapper;

	// 数据果实
	List<Map<String, Object>> fruits;

	public void init(int number) {
		// 这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值
		count = new CountDownLatch(number);
	}

	@Override
	public Object call() {
		try {
			// 与CountDownLatch的第一次交互是主线程等待其他线程，等待所有线程结束再执行主线程
			// 其他线程启动后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
			count.await();
			warehouseMapper = new WarehouseMapper();
			warehouseMapper.saveFruitstoWarehouse(fruits);
			System.out.println("CountDownLatch的count最终值=" + count.getCount());
			return fruits;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void fillBasket(String littleKidName, List<Map<String, Object>> fruit) {
		System.out.println("小朋友" + littleKidName + "摘取" + fruit.size() + "个水果到篮子");
		System.out.println("预计还差" + (count.getCount() - 1) + "个小朋友的水果，篮子才能填满送到仓库");
		if (this.fruits == null) {
			this.fruits = new ArrayList<>();
		}
		this.fruits.addAll(fruit);
		count.countDown();
	}

	public CountDownLatch getCount() {
		return count;
	}

	public void setCount(CountDownLatch count) {
		this.count = count;
	}

}
