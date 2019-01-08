package ∑∫–Õ.∑∫–Õ¡∑œ∞;

public class WrapperGenerics<T>{
	private Generics<T> generics;

	public Generics<T> getGenerics() {
		return generics;
	}

	public void setGenerics(Generics<T> generics) {
		this.generics = generics;
	}
	public static void main(String[] args) {
		Generics<Integer> gene=new Generics<Integer>();
		gene.setT(100);
		WrapperGenerics<Integer> wra=new WrapperGenerics<Integer>();
		wra.setGenerics(gene);
		System.out.println(wra.getGenerics().getT());
	}
	

}
class Generics<T>{
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
