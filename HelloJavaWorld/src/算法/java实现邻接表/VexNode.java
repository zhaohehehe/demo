package test;

import java.io.Serializable;

/**
 * @description 顶点节点
 * @author zhaohe
 * @date 2020-02-24
 *
 */
public class VexNode implements Serializable {
	private static final long serialVersionUID = -3212248660061482246L;
	private String id;
	private VexInfo vexInfo;// 顶点信息
	private ArcNode outHead;// 出边链表的表头节点
	private ArcNode inHead;// 入边链表的表头节点

	public VexNode() {
		super();
	}

	public VexNode(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VexInfo getVexInfo() {
		return vexInfo;
	}

	public void setVexInfo(VexInfo vexInfo) {
		this.vexInfo = vexInfo;
	}

	public ArcNode getOutHead() {
		return outHead;
	}

	public void setOutHead(ArcNode outHead) {
		this.outHead = outHead;
	}

	public ArcNode getInHead() {
		return inHead;
	}

	public void setInHead(ArcNode inHead) {
		this.inHead = inHead;
	}

}
