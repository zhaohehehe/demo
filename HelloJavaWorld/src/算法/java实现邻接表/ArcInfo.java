package test;

import java.io.Serializable;

/**
 * @description 边信息
 * @author zhaohe
 * @date 2020-02-24
 *
 */
public class ArcInfo implements Serializable {
	private static final long serialVersionUID = 1405153977150401674L;
	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
