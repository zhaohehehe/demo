package com.olg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjMatrixGraph {
	private static final int MAX_NODE_NUM = 5000;
	private static final int MAX_WEIGHT = Integer.MAX_VALUE / 2;
	private NodeInfo[] nodes;
	private ArcInfo[][] arcs;// 邻接矩阵
	private int nodeNum;
	private int arcNum;

	public static void main(String[] args) {
		List<NodeInfo> nodes = new ArrayList<>();
		nodes.add(new NodeInfo("A"));
		nodes.add(new NodeInfo("B"));
		nodes.add(new NodeInfo("C"));
		nodes.add(new NodeInfo("D"));
		List<Map<String, NodeInfo>> arcs = new ArrayList<>(5);
		Map<String, NodeInfo> map1 = new HashMap<>();
		map1.put("from", new NodeInfo("A"));
		map1.put("to", new NodeInfo("B"));
		arcs.add(map1);
		Map<String, NodeInfo> map2 = new HashMap<>();
		map2.put("from", new NodeInfo("A"));
		map2.put("to", new NodeInfo("C"));
		arcs.add(map2);
		Map<String, NodeInfo> map3 = new HashMap<>();
		map3.put("from", new NodeInfo("A"));
		map3.put("to", new NodeInfo("D"));
		arcs.add(map3);
		Map<String, NodeInfo> map4 = new HashMap<>();
		map4.put("from", new NodeInfo("B"));
		map4.put("to", new NodeInfo("D"));
		arcs.add(map4);
		Map<String, NodeInfo> map5 = new HashMap<>();
		map5.put("from", new NodeInfo("C"));
		map5.put("to", new NodeInfo("D"));
		arcs.add(map5);
		AdjMatrixGraph graph = new AdjMatrixGraph(nodes, arcs);
		System.out.println("邻接矩阵：");
		graph.printAdj();

	}

	public AdjMatrixGraph(List<NodeInfo> nodes, List<Map<String, NodeInfo>> arcs) {
		nodeNum = nodes.size();
		arcNum = arcs.size();
		if (this.nodes == null) {
			this.nodes = new NodeInfo[nodeNum];
		}
		if (this.arcs == null) {
			this.arcs = new ArcInfo[nodeNum][nodeNum];
		}
		for (int i = 0; i < nodeNum; i++) {
			this.nodes[i] = nodes.get(i);
		}
		// 初始化邻接矩阵
		for (int i = 0; i < nodeNum; i++) {
			for (int j = 0; j < nodeNum; j++) {
				this.arcs[i][j] = (i == j) ? null : new ArcInfo(MAX_WEIGHT);
			}
		}
		// 构造邻接矩阵
		for (int i = 0; i < arcNum; i++) {
			int iIndex = this.locateNode(nodes, arcs.get(i).get("from"));
			int jIndex = this.locateNode(nodes, arcs.get(i).get("to"));
			if (this.arcs[iIndex][jIndex] != null) {
				this.arcs[iIndex][jIndex].setWeight(1);
			}
		}
	}

	private int locateNode(List<NodeInfo> nodes, NodeInfo node) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).equals(node)) {
				return i;
			}
		}
		return 0;
	}

	public void printAdj() {
		for (int i = 0; i < nodeNum; i++) {
			for (int j = 0; j < nodeNum; j++) {
				if (arcs[i][j] != null && arcs[i][j].getWeight() != MAX_WEIGHT) {
					System.out.println(i + "->" + j);
				}
			}
		}
	}

}
