package zhaohe.study.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

	/**
	 * 用于在切换数据源时保证不会被其他线程修改(可重入，可中断，不会阻塞等待)
	 */
	private static Lock lock = new ReentrantLock();

	/**
	 * 用于轮循slave数据源的计数器
	 */
	private static int counter = 0;

	/**
	 * Maintain variable for every thread(每个线程都有自己的变量副本), to avoid effect other
	 * thread
	 */
	private static final ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial((Supplier<String>) () -> {
		return DataSourceKey.MASTER_ASSET;
	});

	/**
	 * All DataSource List
	 */
	public static List<Object> dataSourceKeys = new ArrayList<>();

	/**
	 * The constant slaveDataSourceKeys.
	 */
	public static List<Object> slaveDataSourceKeys = new ArrayList<>();

	/**
	 * To switch DataSource
	 *
	 * @param key
	 *            the key
	 */
	public static void setDataSourceKey(String key) {
		CONTEXT_HOLDER.set(key);
	}
	
	/**
	 * Use master data source.
	 */
	public static void useMasterDataSource() {
		CONTEXT_HOLDER.set(DataSourceKey.MASTER_ASSET);
	}

	/**
	 * 当使用只读数据源时通过轮循方式选择要使用的数据源
	 */
	public static void useSlaveDataSource() {
		lock.lock();

		try {
			int datasourceKeyIndex = counter % slaveDataSourceKeys.size();
			CONTEXT_HOLDER.set(String.valueOf(slaveDataSourceKeys.get(datasourceKeyIndex)));
			counter++;
		} catch (Exception e) {
			logger.error("Switch slave datasource failed, error message is {}", e.getMessage());
			useMasterDataSource();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Get current DataSource
	 *
	 * @return data source key
	 */
	public static String getDataSourceKey() {
		return (String) CONTEXT_HOLDER.get();
	}

	/**
	 * To set DataSource as default
	 */
	public static void clearDataSourceKey() {
		CONTEXT_HOLDER.remove();
	}

	/**
	 * Check if give DataSource is in current DataSource list
	 *
	 * @param key
	 *            the key
	 * @return boolean boolean
	 */
	public static boolean containDataSourceKey(String key) {
		return dataSourceKeys.contains(key);
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		DynamicDataSourceContextHolder.counter = counter;
	}
	
}
