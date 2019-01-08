package 泛型.泛型练习;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericsTest05_1<T> 
{
	private T t;

	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public void setT(ArrayList<String> list) {
		this.t = (T) list;
	}
	public void setT(LinkedList<String> list2) {
		this.t = (T) list2;
	}
	
	public static void main(String[] args) {
		//GenericsTest05_1<ArrayList<String>> gene=new GenericsTest05_1<ArrayList<String>>();//yes正确
		GenericsTest05_1<? extends List<String>> gene=null;
		gene=new GenericsTest05_1<ArrayList<String>>();
		ArrayList<String> list=new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		gene.setT(list);
		System.out.println(gene.getT());
		gene=new GenericsTest05_1<LinkedList<String>>();
		LinkedList<String> list2=new LinkedList<String>();
		list2.add("b");
		list2.add("b");
		list2.add("b");
		gene.setT(list2);
		System.out.println(gene.getT());
		System.out.println("--------------------------");
		GenericsTest05_1<? super List> gene3=null;
		//gene3=new GenericsTest05_1<String>();//error
		gene3=new GenericsTest05_1<Object>();
		gene3.setT(list);
		System.out.println(gene3.getT());
		System.out.println("--------------------------");
		GenericsTest05_1<? extends Object> gene4=null;
		gene4=new GenericsTest05_1<Object>();
		//gene4.setT(null);  //error
		System.out.println("--------------------------");
		GenericsTest05_1<String> gene5=new GenericsTest05_1<String>();
	 	gene5.setT("hello");
		//gene5.setT(null); //error
		
		
		
		
		
		
	}

	
}
