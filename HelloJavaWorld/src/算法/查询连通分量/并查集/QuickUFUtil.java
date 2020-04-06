
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 并查集计算连通分量个数（顺带进行分组）
 */
public class QuickUFUtil {
	public static void main(String[] args) {
		QuickUFUtil.groupBy();
		System.out.println("=============");
		QuickUFUtil.initGroupAndBy();
	}

	public static void initGroupAndBy() {
		int[][] edges = new int[5][2];
		edges[0] = new int[] { 0, 1 };
		edges[1] = new int[] { 1, 2 };
		edges[2] = new int[] { 3, 4 };
		edges[3] = new int[] { 4, 5 };
		// edges[4] = new int[] { 2, 3 };
		JSONObject address = new JSONObject();
		address.put("000", 0);
		address.put("001", 1);
		address.put("002", 2);
		address.put("003", 2);// new int[] { 2, 3 }之间沒有边，但是3属于组2
		address.put("004", 4);
		address.put("005", 5);
		QuickUFUtil uf = new QuickUFUtil(address);
		for (int[] edge : edges) {
			if (!uf.find(edge[0], edge[1])) {
				uf.union(edge[0], edge[1]);
			}
		}
		for (int index : uf.parent) {
			System.out.print(index + ",");
		}
		System.out.println();
		System.out.println(uf.getCount());

	}

	public static void groupBy() {
		int[][] edges = new int[5][2];
		edges[0] = new int[] { 0, 1 };
		edges[1] = new int[] { 1, 2 };
		edges[2] = new int[] { 3, 4 };
		edges[3] = new int[] { 4, 5 };
		edges[4] = new int[] { 2, 3 };
		QuickUFUtil uf = new QuickUFUtil(6);
		for (int[] edge : edges) {
			if (!uf.find(edge[0], edge[1])) {
				uf.union(edge[0], edge[1]);
			}
		}
		for (int index : uf.parent) {
			System.out.print(index + ",");
		}
		System.out.println();
		System.out.println(uf.getCount());
	}

	private int count;
	private int[] parent;
	private int[] height;
	private JSONArray[] children;

	public QuickUFUtil(int count) {
		this.count = count;
		parent = new int[count];
		height = new int[count];
		children = new JSONArray[count];
		for (int i = 0; i < count; i++) {
			parent[i] = i;
			height[i] = 1;
			JSONArray arr = new JSONArray();
			arr.add(i);
			children[i] = arr;

		}
	}

	/**
	 * 使用city初始化分组
	 * 
	 * @param address
	 */
	public QuickUFUtil(JSONObject address) {
		this(address.size());
		String[] ids = new String[count];
		@SuppressWarnings("unchecked")
		Set<String> set = address.keySet();
		set.toArray(ids);
		JSONObject cities = new JSONObject();
		for (int i = 0; i < address.size(); i++) {
			String id = ids[i];
			String city = address.getString(id);
			if (!cities.containsKey(city)) {
				cities.put(city, i);
			} else {
				int parentI = cities.getInt(city);
				union(parentI, i);
				cities.put(city, parent[i]);
			}

		}

	}

	public boolean find(int i, int j) {
		return root(i) == root(j);
	}

	private int root(int i) {
		while (i != parent[i]) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}

		return parent[i];
	}

	public void union(int i, int j) {
		int rootI = root(i);
		int rootJ = root(j);
		if (height[rootI] > height[rootJ]) {
			unionChildren(parent[rootJ], rootI);
		} else {
			unionChildren(parent[rootI], rootJ);
			if (height[rootI] == height[rootJ]) {
				height[rootJ]++;
			}
		}

		this.count--;
	}

	public void unionChildren(int oldParent, int newParent) {
		JSONArray childrenInOldParent = children[oldParent];
		JSONArray childrenInNewParent = children[newParent];
		for (int k = 0; k < childrenInOldParent.size(); k++) {
			int index = (int) childrenInOldParent.get(k);
			parent[index] = newParent;
			childrenInNewParent.add(index);
		}
		childrenInOldParent.clear();
	}

	public int getCount() {
		return count;
	}

	public int[] getParent() {
		return parent;
	}

	public JSONArray[] getChildren() {
		return children;
	}

}
