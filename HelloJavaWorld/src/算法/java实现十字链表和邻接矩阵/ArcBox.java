package com.olg;

public class ArcBox {
	private int fromIndex;// 弧尾位置索引
	private int toIndex;// 弧头位置索引
	private ArcBox fromLink;// 弧头相同的链表
	private ArcBox toLink;// 弧尾相同的链表
	private ArcInfo arcInfo;//// 弧的信息

	public ArcBox() {
		super();
	}

	public ArcBox(int fromIndex, int toIndex, ArcBox fromLink, ArcBox toLink, ArcInfo arcInfo) {
		super();
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
		this.fromLink = fromLink;
		this.toLink = toLink;
		this.arcInfo = arcInfo;
	}

	public int getFromIndex() {
		return fromIndex;
	}

	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}

	public int getToIndex() {
		return toIndex;
	}

	public void setToIndex(int toIndex) {
		this.toIndex = toIndex;
	}

	public ArcBox getFromLink() {
		return fromLink;
	}

	public void setFromLink(ArcBox fromLink) {
		this.fromLink = fromLink;
	}

	public ArcBox getToLink() {
		return toLink;
	}

	public void setToLink(ArcBox toLink) {
		this.toLink = toLink;
	}

	public ArcInfo getArcInfo() {
		return arcInfo;
	}

	public void setArcInfo(ArcInfo arcInfo) {
		this.arcInfo = arcInfo;
	}

}
