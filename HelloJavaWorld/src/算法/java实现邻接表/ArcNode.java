package test;

import java.io.Serializable;

/**
 * @description 边节点
 * @author zhaohe
 * @date 2020-02-24
 *
 */
public class ArcNode implements Serializable {
	private static final long serialVersionUID = -7445640863727141714L;
	private int adjVex;// 邻接点的位置
	private ArcNode nextArc;// 下一个边节点
	private ArcInfo arcInfo;// 边信息

	public int getAdjVex() {
		return adjVex;
	}

	public void setAdjVex(int adjVex) {
		this.adjVex = adjVex;
	}

	public ArcNode getNextArc() {
		return nextArc;
	}

	public void setNextArc(ArcNode nextArc) {
		this.nextArc = nextArc;
	}

	public ArcInfo getArcInfo() {
		return arcInfo;
	}

	public void setArcInfo(ArcInfo arcInfo) {
		this.arcInfo = arcInfo;
	}

}
