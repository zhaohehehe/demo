package ∑∫–Õ.∑∫–Õ¡∑œ∞;

public class SimpleCollectionImitate<T>
{
	private T[] objArr;
	private int index=0;
	public SimpleCollectionImitate(){
		objArr=(T[]) new Object[10];
	}
	public SimpleCollectionImitate(int capacity){
		objArr=(T[]) new Object[capacity];
	}
	public void add(T t){
	
		objArr[index++]=t;
	}
	public T get(int i){
		return objArr[i];
	}
	public int getLength(){
		return index;
	}
	public static void main(String[] args) {
		SimpleCollectionImitate<Integer> sc=new SimpleCollectionImitate<Integer>();
		for(int i=0;i<10;i++){
			sc.add(new Integer(i));
		}
		for(int i=0;i<sc.getLength();i++){
			System.out.println(sc.get(i));
		}
	}
	
	

}
