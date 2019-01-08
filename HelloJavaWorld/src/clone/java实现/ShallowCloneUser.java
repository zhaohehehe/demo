package clone.java µœ÷;

import java.io.Serializable;

public class ShallowCloneUser implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	public ShallowCloneUser(String id,String name){
		this.id=id;
		this.name=name;
	}
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
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
		ShallowCloneUser other = (ShallowCloneUser) obj;
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
		ShallowCloneUser user1=new ShallowCloneUser("000","A");
		ShallowCloneUser user2=user1;
		ShallowCloneUser user4=new ShallowCloneUser("000","A");
		ShallowCloneUser user3=(ShallowCloneUser) user1.clone();
		System.out.println(user2==user1);//T
		System.out.println(user3==user1);//F
		System.out.println(user3.equals(user1));//T
		System.out.println(user4==user1);//F
		System.out.println(user4.equals(user1));//T
	}

}
