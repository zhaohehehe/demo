package clone.java µœ÷;

import java.io.Serializable;

public class DeepCloneUser implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	public ShallowCloneUser getUser() {
		return user;
	}
	public void setUser(ShallowCloneUser user) {
		this.user = user;
	}
	private String name;
	public ShallowCloneUser user;
	public DeepCloneUser(ShallowCloneUser user,String id,String name){
		this.user=user;
		this.id=id;
		this.name=name;
	}
	protected Object clone() throws CloneNotSupportedException{
	//	Object dc=super.clone();// error
		DeepCloneUser dc=(DeepCloneUser) super.clone();
		dc.user=(ShallowCloneUser) dc.user.clone();
		return dc;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DeepCloneUser other = (DeepCloneUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		DeepCloneUser user1=new DeepCloneUser(new ShallowCloneUser("000","A"),"000","A");
		DeepCloneUser user2=user1;
		DeepCloneUser user3=(DeepCloneUser) user1.clone();
		System.out.println(user3==user1);//F
		System.out.println(user3.equals(user1));//T
	}

}
