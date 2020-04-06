package test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description 邻接表
 * @date 2020-03-16
 * @author zhaohe
 *
 */
public class AdjacencyListGraph {
	public static void main(String[] args) {
		JSONArray nodes = new JSONArray();
		nodes.add(new JSONObject().accumulate("id", "0"));
		nodes.add(new JSONObject().accumulate("id", "1"));
		nodes.add(new JSONObject().accumulate("id", "2"));
		nodes.add(new JSONObject().accumulate("id", "3"));
		nodes.add(new JSONObject().accumulate("id", "4"));
		JSONArray arcs = new JSONArray();
		arcs.add(new JSONObject().accumulate("from", "0").accumulate("to", "0"));
		arcs.add(new JSONObject().accumulate("from", "1").accumulate("to", "2"));
		arcs.add(new JSONObject().accumulate("from", "2").accumulate("to", "1"));
		arcs.add(new JSONObject().accumulate("from", "3").accumulate("to", "4"));
		arcs.add(new JSONObject().accumulate("from", "1").accumulate("to", "4"));
		arcs.add(new JSONObject().accumulate("from", "2").accumulate("to", "0"));
		AdjacencyListGraph graph = new AdjacencyListGraph(nodes, arcs);
		VexNode[] vertice = graph.getVertexes();
		for (VexNode node : vertice) {
			System.out.print(node.getId() + " descendants:");
			ArcNode out = node.getOutHead();
			while (out != null) {
				System.out.print(vertice[out.getAdjVex()].getId() + ",");
				out = out.getNextArc();
			}
			System.out.println();
			System.out.println("---------------------------");
		}

	}

	private VexNode[] vertexes;// 顶点数组
	private int vexNum;// 顶点数
	private int arcNum;// 边数
	private Map<String, Integer> index = new HashMap<>();// 顶点的位置索引

	public AdjacencyListGraph(JSONArray nodes, JSONArray arcs) {
		vexNum = nodes.size();
		arcNum = arcs.size();
		vertexes = new VexNode[vexNum];
		for (int i = 0; i < vexNum; i++) {
			JSONObject node = nodes.getJSONObject(i);
			String id = node.getString("id");
			index.put(id, i);
			vertexes[i] = new VexNode(id);
			vertexes[i].setVexInfo(new VexInfo(id));
		}
		for (int i = 0; i < arcNum; i++) {
			JSONObject arc = arcs.getJSONObject(i);
			int fromVex = index.get(arc.get("from"));
			int toVex = index.get(arc.get("to"));
			createAdjacencyArc(fromVex, toVex);
		}
	}

	private void createAdjacencyArc(int fromVex, int toVex) {
		// 头插法创建邻接表
		ArcNode arcNode = new ArcNode();
		arcNode.setAdjVex(toVex);
		arcNode.setNextArc(vertexes[fromVex].getOutHead());
		vertexes[fromVex].setOutHead(arcNode);
	}

	@SuppressWarnings("unused")
	private void createReverseAdjacencyArc(int fromVex, int toVex) {
		// create reverse adjacency list
	}

	public VexNode[] getVertexes() {
		return vertexes;
	}

	public void setVertexes(VexNode[] vertexes) {
		this.vertexes = vertexes;
	}

	public int getVexNum() {
		return vexNum;
	}

	public void setVexNum(int vexNum) {
		this.vexNum = vexNum;
	}

	public int getArcNum() {
		return arcNum;
	}

	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}

	public Map<String, Integer> getIndex() {
		return index;
	}

	public void setIndex(Map<String, Integer> index) {
		this.index = index;
	}

}
