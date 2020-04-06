package test;

import java.io.Serializable;

/**
 * @description 顶点信息
 * @author zhaohe
 * @date 2020-02-24
 *
 */
public class VexInfo implements Serializable {
	private static final long serialVersionUID = -5147970701769533704L;
	private String id;
	private String code;
	private String name;

	public VexInfo(String id) {
		super();
		this.id = id;
	}

	public VexInfo(String id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
