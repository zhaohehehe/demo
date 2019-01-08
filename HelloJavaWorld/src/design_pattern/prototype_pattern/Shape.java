package test.design_pattern.prototype_pattern;

public abstract class Shape implements Cloneable{
	private String id;
	protected String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	abstract void draw();
	public Object colne(){
		Object clone=null;
		try{
			clone=super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return clone;
	}
}
