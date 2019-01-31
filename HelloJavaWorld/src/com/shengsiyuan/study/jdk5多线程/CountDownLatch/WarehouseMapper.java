package zhaohe.study.thread;

import java.util.List;
import java.util.Map;

/**
 * 用于将水果存入仓库
 * 
 * @author ZH
 *
 */
public class WarehouseMapper {

	public void saveFruitstoWarehouse(List<Map<String, Object>> fruits) {
		System.out.println(fruits.size() + "个水果入库完毕，丰收啦！！");

	}

}
