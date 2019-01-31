package zhaohe.study.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SaveFruitsToWarehouseMainThread {
	/*
	 * 假设树林中一共有50个苹果
	 */
	private static final List<Map<String, Object>> records = new ArrayList<>();
	static {
		for (int i = 0; i < 5000; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("水果" + i, i);
			records.add(map);
		}
	}
	/*
	 * 假设默认分配5个小朋友(默认其他线程数5个，可以按照实际情况扩容),5个小朋友同步向篮子中添加水果
	 */
	private static final int LittleKinNumber = 5;

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		SaveFruitsToWarehouseMainThread.mulThread();
		long b = System.currentTimeMillis();
		System.out.println("使用多线程耗时：" + (b - a));
		a = System.currentTimeMillis();
		SaveFruitsToWarehouseMainThread.noMulThread();
		b = System.currentTimeMillis();
		System.out.println("不使用多线程耗时：" + (b - a));

	}

	public static void noMulThread() {
		for (int i = 0; i < LittleKinNumber; i++) {
			try {
				// 模拟摘果子耗时
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 模拟单个入库耗时
		for (int i = 0; i < records.size(); i++) {

		}

	}

	@SuppressWarnings("unchecked")
	public static void mulThread() {
		// 初始值
		int BATCHES = LittleKinNumber;
		// 平均每个小朋友应该摘得苹果数量
		int batchSize = (int) Math.ceil((double) records.size() / LittleKinNumber);
		// 小朋友可能不够，实际可能会额外增加小朋友帮忙
		BATCHES = (int) Math.ceil((double) records.size() / batchSize);
		// FruitsBasketCallable fruitsBasket = (FruitsBasketCallable)
		// ApplicationContextUtil.getBean("fruitsBasketCallable");
		FruitsBasketCallable fruitsBasket = new FruitsBasketCallable();
		fruitsBasket.init(BATCHES);
		ExecutorService execService = Executors.newCachedThreadPool();
		// count.await(),主线程暂停，等待其他线程填充水果篮子，直到countDown()减为0时，通知入库
		Future<Object> future = execService.submit(fruitsBasket);
		for (int batchNo = 0; batchNo < BATCHES; batchNo++) {
			int fromIndex = batchNo * batchSize;
			int toIndex = (batchNo + 1) * batchSize;
			while (toIndex > records.size()) {
				toIndex--;
			}
			// LittleKidFillFruitsToBasketRunnable
			// littleKidFillFruitsToBasketRunnable =
			// (LittleKidFillFruitsToBasketRunnable)
			// ApplicationContextUtil.getBean("littleKidFillFruitsToBasketRunnable");
			LittleKidFillFruitsToBasketRunnable littleKidFillFruitsToBasketRunnable = new LittleKidFillFruitsToBasketRunnable();
			littleKidFillFruitsToBasketRunnable.setFruitsBasketCallable(fruitsBasket);
			littleKidFillFruitsToBasketRunnable.setKidName("kid-" + fromIndex + "-" + toIndex);
			littleKidFillFruitsToBasketRunnable.setFruitsBelongtoKid(records.subList(fromIndex, toIndex));
			// 其他线程向篮子中装水果，countDown()
			new Thread(littleKidFillFruitsToBasketRunnable).start();

		}
		while (!future.isDone()) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
				System.out.println("......大家在喜悦中忙碌着...........");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Map<String, Object>> warehouseAllFruits = new ArrayList<>();
		try {
			warehouseAllFruits = (List<Map<String, Object>>) future.get();
			System.out.println("===============劳动成果展示======================");
			for (int i = 0; i < warehouseAllFruits.size(); i++) {
				System.out.print(warehouseAllFruits.get(i).toString());
			}
			System.out.println("\n=============================================");
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
