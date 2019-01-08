package com.olg;

import java.util.List;
import java.util.Map;

public class OrthogonalListGraph {
	private VexNode[] nodeList;// 顶点数组
	private int nodeNum;// 定点数量
	private int arcNum;// 弧的数量

	/**
	 * 创建十字链表
	 * 
	 * @param nodes
	 * @param arcs
	 */
	public OrthogonalListGraph(List<NodeInfo> nodes, List<Map<String, String>> arcs) {
		nodeNum = nodes.size();
		arcNum = arcs.size();
		if (nodeList == null) {
			nodeList = new VexNode[nodeNum];
		}
		for (int i = 0; i < nodeNum; i++) {
			nodeList[i] = new VexNode(null, null, nodes.get(i));
		}
		// 头插法创建十字链表
		for (int i = 0; i < arcNum; i++) {
			int iIndex = locateNode(nodes, arcs.get(i).get("startId"));
			int jIndex = locateNode(nodes, arcs.get(i).get("endId"));
			// 设置iIndex的出弧链表
			ArcBox arcBox0 = new ArcBox();
			arcBox0.setArcInfo(new ArcInfo(1,arcs.get(i).get("deleteFlag")));
			arcBox0.setFromIndex(iIndex);
			arcBox0.setToIndex(jIndex);
			arcBox0.setToLink(nodeList[iIndex].getFirstOut());
			nodeList[iIndex].setFirstOut(arcBox0);
			// 更新iIndex出度+1
			nodeList[iIndex].getNodeInfo().setOutDegree(nodeList[iIndex].getNodeInfo().getOutDegree() + 1);
			// 设置jIndex的入弧链表
			ArcBox arcBox1 = new ArcBox();
			arcBox1.setArcInfo(new ArcInfo(1,arcs.get(i).get("deleteFlag")));
			arcBox1.setFromIndex(iIndex);
			arcBox1.setToIndex(jIndex);
			arcBox1.setFromLink(nodeList[jIndex].getFirstIn());
			nodeList[jIndex].setFirstIn(arcBox1);
			// 更新jIndex入度+1
			nodeList[jIndex].getNodeInfo().setInDegree(nodeList[jIndex].getNodeInfo().getInDegree() + 1);
		}
	}

	/**
	 * 打印邻接表
	 */
	public void printAdj() {
		System.out.println("========邻接表================");
		for (int i = 0; i < nodeNum; i++) {
			System.out.print("头节点： " + nodeList[i].getNodeInfo().getNodeId() + "("
					+ nodeList[i].getNodeInfo().getDeleteFlag() + ")");
			ArcBox firstOut = nodeList[i].getFirstOut();
			if (firstOut != null) {
				System.out
						.print("第一个邻接点： " + firstOut.getToIndex() + "(" + firstOut.getArcInfo().getDeleteFlag() + ")");
				ArcBox toLink = firstOut.getToLink();
				while (toLink != null) {
					System.out.print(" --> " + toLink.getToIndex() + "(" + toLink.getArcInfo().getDeleteFlag() + ")");
					toLink = toLink.getToLink();
				}
			}
			System.out.print("\n");
		}
	}

	/**
	 * 打印逆邻接表
	 */
	public void printReverseAdj() {
		System.out.println("========逆邻接表================");
		for (int i = 0; i < nodeNum; i++) {
			System.out.print("头节点： " + nodeList[i].getNodeInfo().getNodeId() + "("
					+ nodeList[i].getNodeInfo().getDeleteFlag() + ")");
			ArcBox firstIn = nodeList[i].getFirstIn();
			if (firstIn != null) {
				System.out
						.print("第一个逆邻接点： " + firstIn.getFromIndex() + "(" + firstIn.getArcInfo().getDeleteFlag() + ")");
				ArcBox fromLink = firstIn.getFromLink();
				while (fromLink != null) {
					System.out.print(
							" --> " + fromLink.getFromIndex() + "(" + fromLink.getArcInfo().getDeleteFlag() + ")");
					fromLink = fromLink.getFromLink();
				}
			}
			System.out.print("\n");
		}
	}

	public void printVexNodeDegree() {
		System.out.println("========节点的出度和入度================");
		for (int i = 0; i < nodeNum; i++) {
			System.out.println(
					"头节点： " + nodeList[i].getNodeInfo().getNodeId() + "(入度= " + nodeList[i].getNodeInfo().getInDegree()
							+ " ,出度= " + nodeList[i].getNodeInfo().getOutDegree() + ")");
		}
	}

	/**
	 * 删除节点的某个出边
	 * 
	 * @param fromFirst
	 * @param fromIndex
	 * @param targetIndex
	 * @return
	 */
	private boolean deleteLinkArc(VexNode vexNode, int fromIndex, int targetIndex) {
		if (vexNode == null) {
			return false;
		}
		ArcBox firstOut = vexNode.getFirstOut();
		if (firstOut == null) {
			return false;
		}
		if (firstOut.getFromIndex() == fromIndex && firstOut.getToIndex() == targetIndex) {
			vexNode.setFirstOut(firstOut.getToLink());
			return true;
		} else {
			ArcBox q = firstOut.getToLink();
			while (q != null) {
				if (q.getFromIndex() == fromIndex && q.getToIndex() == targetIndex) {
					firstOut.setToLink(q.getToLink());
					return true;
				} else {
					firstOut = firstOut.getToLink();
					q = firstOut.getToLink();
				}
			}
		}
		return false;

	}

	/**
	 * 标记节点的某个出边
	 * 
	 * @param fromFirst
	 * @param fromIndex
	 * @param targetIndex
	 * @return
	 */
	private boolean tagLinkArc(VexNode vexNode, int fromIndex, int targetIndex) {
		if (vexNode == null) {
			return false;
		}
		ArcBox firstOut = vexNode.getFirstOut();
		while (firstOut != null) {
			if (firstOut.getFromIndex() == fromIndex && firstOut.getToIndex() == targetIndex) {
				firstOut.getArcInfo().setDeleteFlag("1");
				return true;
			} else {
				firstOut = firstOut.getToLink();
			}
		}
		return false;

	}

	private int locateNode(List<NodeInfo> nodes, String nodeId) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getNodeId().equals(nodeId)) {
				return i;
			}
		}
		return 0;
	}
	/*
	 * private int locateNode(VexNode[] nodes, String nodeId) { for (int i = 0;
	 * i < nodes.length; i++) { if
	 * (nodes[i].getNodeInfo().getNodeId().equals(nodeId)) { return i; } }
	 * return 0; }
	 */

	/*
	 * private int locateNode(OrthogonalListGraph graph, NodeInfo nodeInfo) { if
	 * (graph == null || graph.getNodeList() == null) { return -1; } for (int i
	 * = 0; i < graph.getNodeList().length; i++) { if (graph.getNodeList()[i] !=
	 * null && graph.getNodeList()[i].getNodeInfo().equals(nodeInfo)) { return
	 * i; }
	 * 
	 * } return -1; }
	 */

	public VexNode[] getNodeList() {
		return nodeList;
	}

	public void setNodeList(VexNode[] nodeList) {
		this.nodeList = nodeList;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public int getArcNum() {
		return arcNum;
	}

	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}

	// ===================================================================================================
	/**
	 * 删除节点的所有入边
	 * 
	 * @param graph
	 * @param targetIndex
	 *            节点位置索引
	 */
	public static void deleteVexNodeInArcs(OrthogonalListGraph graph, int targetIndex) {
		VexNode targetVexNode = graph.getNodeList()[targetIndex];
		System.out.println("目标删除节点：" + targetVexNode.getNodeInfo().getLabel());
		ArcBox firstIn = targetVexNode.getFirstIn();
		if (firstIn != null) {
			int firstFromIndex = firstIn.getFromIndex();
			// 修改弧头指向该节点的第一个邻接点的出弧链表
			VexNode firstAdjNode = graph.getNodeList()[firstFromIndex];
			graph.deleteLinkArc(firstAdjNode, firstFromIndex, targetIndex);
			ArcBox nextIn = firstIn.getFromLink();
			while (nextIn != null) {
				int nextFromIndex = nextIn.getFromIndex();
				// 修改弧头指向该节点的其他邻接点的出弧链表
				VexNode nextAdjNode = graph.getNodeList()[nextFromIndex];
				graph.deleteLinkArc(nextAdjNode, nextFromIndex, targetIndex);
				nextIn = nextIn.getFromLink();
			}
			// 修改该节点 的入弧链表
			targetVexNode.setFirstIn(null);
		}
	}

	/**
	 * 标记节点的所有入边
	 * 
	 * @param graph
	 * @param targetIndex
	 *            节点位置索引
	 */
	public static void tagVexNodeInArcs(OrthogonalListGraph graph, int targetIndex) {
		VexNode targetVexNode = graph.getNodeList()[targetIndex];
		targetVexNode.getNodeInfo().setDeleteFlag("1");
		System.out.println("目标标记节点：" + targetVexNode.getNodeInfo().getLabel());
		ArcBox firstIn = targetVexNode.getFirstIn();
		if (firstIn != null) {
			// 修改第一条入弧
			firstIn.getArcInfo().setDeleteFlag("1");
			int firstFromIndex = firstIn.getFromIndex();
			// 修改弧头指向该节点的第一个邻接点的出弧链表
			VexNode firstAdjNode = graph.getNodeList()[firstFromIndex];
			graph.tagLinkArc(firstAdjNode, firstFromIndex, targetIndex);
			ArcBox nextIn = firstIn.getFromLink();
			while (nextIn != null) {
				// 修改其他入弧
				nextIn.getArcInfo().setDeleteFlag("1");
				int nextFromIndex = nextIn.getFromIndex();
				// 修改弧头指向该节点的其他邻接点的出弧链表
				VexNode nextAdjNode = graph.getNodeList()[nextFromIndex];
				graph.tagLinkArc(nextAdjNode, nextFromIndex, targetIndex);
				nextIn = nextIn.getFromLink();
			}
		}
	}
}
