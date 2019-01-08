package ∑∫–Õ.∑∫–Õ¡∑œ∞;

public class GenericsTest03<T> 
{
	
	private T[] typeName;
	
	
	public T[] getTypeName() {
		return typeName;
	}


	public void setTypeName(T[] typeName) {
		this.typeName = typeName;
	}

	public static void main(String[] args) {
		GenericsTest03<Integer> gene=new GenericsTest03<Integer>();
		Integer[] in=new Integer[]{2,5,7,9};
		gene.setTypeName(in);
		System.out.println(gene.getTypeName());
		for(Integer i:in){
			System.out.println(i);
		}
		
	}
	
}
