package com.olg;

public class ArcInfo {
	int weight;
	String deleteFlag = "0";

	boolean isVisited = false;

	public ArcInfo() {
		super();
	}
	
	public ArcInfo(int weight) {
		super();
		this.weight = weight;
	}

	public ArcInfo(int weight,String deleteFlag) {
		super();
		this.weight = weight;
		this.deleteFlag = deleteFlag;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
