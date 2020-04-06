package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;

/**
 * @Date 2019-11-08
 * @author zhaohe
 * @Description 检测是否有环
 *
 */
public class CheckRing {

	public static void main(String[] args) {
		int[][] edges = new int[6][2];
		edges[0] = new int[] { 1, 0 };
		edges[1] = new int[] { 2, 1 };
		edges[2] = new int[] { 4, 3 };
		edges[3] = new int[] { 5, 4 };
		edges[4] = new int[] { 3, 2 };
		edges[5] = new int[] { 3, 5 };
		// edges[6] = new int[] { 1, 1 };
		List<Integer> path = new ArrayList<>();
		Set<Integer> ringSet = new HashSet<>();
		boolean isExistsRing = isExistsRing(6, edges, ringSet, path);
		System.out.println(isExistsRing);
		System.out.println("环上节点：");
		for (Iterator<Integer> it = ringSet.iterator(); it.hasNext();) {
			System.out.print(it.next() + ",");
		}
		System.out.println();
		System.out.println("路径：");
		for (Integer index : path) {
			System.out.print(index + ",");
		}
	}

	/**
	 * <p>
	 * 计算依赖图的入度，判断是否存在环
	 * </p>
	 * 
	 * @param verticesCount 图的顶点数
	 * @param edges         所有边
	 * @param ringSet       环上的所有顶点
	 * @param path
	 * @return
	 */
	public static boolean isExistsRing(int verticesCount, int[][] edges, Set<Integer> ringSet, List<Integer> path) {
		Assert.isTrue(verticesCount > 0, "图的顶点数目要大于0，请检查");
		Assert.isTrue(edges != null && edges.length > 0, "图的边数目要大于0，请检查");
		int[] indegrees = new int[verticesCount];
		// 初始化入度：依赖图中关系为from->to,edges存储方式为[to,from]
		for (int[] edge : edges)
			indegrees[edge[0]]++;
		// 模拟队列存储遍历过程中入度为0的顶点。入度为0，一定不在ring上
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < verticesCount; i++) {
			if (indegrees[i] == 0) {
				queue.addLast(i);
				if (path != null)
					path.add(i);
			} else {
				if (ringSet != null)
					ringSet.add(i);
			}
		}
		while (!queue.isEmpty()) {
			Integer pre = queue.removeFirst();
			verticesCount--;
			for (int[] edge : edges) {
				if (edge[1] != pre)
					continue;
				if (--indegrees[edge[0]] == 0) {
					queue.add(edge[0]);
					if (path != null)
						path.add(edge[0]);
					if (ringSet != null)
						ringSet.remove(edge[0]);
				} else {
					if (ringSet != null)
						ringSet.add(edge[0]);
				}
			}
		}
		return verticesCount != 0;
	}

}
