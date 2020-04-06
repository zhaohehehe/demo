

/**
 * 并查集计算连通分量个数（不能进行分组）
 */
public class PureQuickUFUtil {
	public static void main(String[] args) {
		int[][] edges = new int[5][2];
		edges[0] = new int[] { 0, 1 };
		edges[1] = new int[] { 1, 2 };
		edges[2] = new int[] { 3, 4 };
		edges[3] = new int[] { 4, 5 };
		edges[4] = new int[] { 2, 3 };
		PureQuickUFUtil uf = new PureQuickUFUtil(6);
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

	public PureQuickUFUtil(int n) {
		this.count = n;
		parent = new int[n];
		height = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			height[i] = 1;
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
			parent[rootJ] = rootI;
		} else {
			parent[rootI] = rootJ;
			if (height[rootI] == height[rootJ]) {
				height[rootJ]++;
			}
		}
		this.count--;
	}

	public int getCount() {
		return count;
	}

	public int[] getParent() {
		return parent;
	}
}
