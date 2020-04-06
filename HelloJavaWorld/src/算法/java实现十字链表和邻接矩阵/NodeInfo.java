package com.olg;

public class NodeInfo {
	String nodeId;
	String accoutType;
	String label;
	String objType;
	String type;
	String statusCode;
	// description
	String owner;
	String clusterName;
	String code;
	String endTime;
	String standardStartTime;
	String schedulingTool;
	String importantType;
	String isOnTime;
	String monthId;
	String standardEndTime;
	String frequency;
	String businessSystemName;
	String duration;
	String startTime;
	String problemMark;
	String expand;
	String name;
	String style;
	String statusName;

	String deleteFlag = "0";
	int inDegree;
	int outDegree;

	boolean isVisited;

	public NodeInfo() {
		super();
	}

	public NodeInfo(String nodeId, String accoutType, String label, String statusCode, String monthId, String frequency,
			String clusterName, String startTime, String endTime) {
		super();
		this.nodeId = nodeId;
		this.accoutType = accoutType;
		this.label = label;
		this.statusCode = statusCode;
		this.monthId = monthId;
		this.frequency = frequency;
		this.clusterName = clusterName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public NodeInfo(String nodeId) {
		super();
		this.nodeId = nodeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getAccoutType() {
		return accoutType;
	}

	public void setAccoutType(String accoutType) {
		this.accoutType = accoutType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStandardStartTime() {
		return standardStartTime;
	}

	public void setStandardStartTime(String standardStartTime) {
		this.standardStartTime = standardStartTime;
	}

	public String getSchedulingTool() {
		return schedulingTool;
	}

	public void setSchedulingTool(String schedulingTool) {
		this.schedulingTool = schedulingTool;
	}

	public String getImportantType() {
		return importantType;
	}

	public void setImportantType(String importantType) {
		this.importantType = importantType;
	}

	public String getIsOnTime() {
		return isOnTime;
	}

	public void setIsOnTime(String isOnTime) {
		this.isOnTime = isOnTime;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public String getStandardEndTime() {
		return standardEndTime;
	}

	public void setStandardEndTime(String standardEndTime) {
		this.standardEndTime = standardEndTime;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getBusinessSystemName() {
		return businessSystemName;
	}

	public void setBusinessSystemName(String businessSystemName) {
		this.businessSystemName = businessSystemName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getProblemMark() {
		return problemMark;
	}

	public void setProblemMark(String problemMark) {
		this.problemMark = problemMark;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeInfo other = (NodeInfo) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

}
