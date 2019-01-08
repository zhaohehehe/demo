package ∑∫–Õ.∑∫–Õ¡∑œ∞;

public class GenericsTest02<T> 
{
	
	private T typeName;

	public T getTypeName() {
		return typeName;
	}

	public void setTypeName(T typeName) {
		this.typeName = typeName;
	}
	public static void main(String[] args) {
		GenericsTest02<Boolean> gene=new GenericsTest02<Boolean>();
		gene.setTypeName(false);
		System.out.println(gene.getTypeName());
		
		GenericsTest02 gene2=new GenericsTest02();
		gene2.setTypeName("boolean");
		System.out.println(gene2.getTypeName());
	}
	
}
