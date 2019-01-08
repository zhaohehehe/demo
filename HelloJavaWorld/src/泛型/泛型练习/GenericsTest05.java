package ·ºĞÍ.·ºĞÍÁ·Ï°;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest05<T extends List<String>> 
{
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public static void main(String[] args) {
		GenericsTest05<ArrayList<String>> gene=new GenericsTest05<ArrayList<String>>();
		ArrayList<String> list=new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		gene.setT(list);
		System.out.println(gene.getT());
		
		
		
		
	}
	
}
