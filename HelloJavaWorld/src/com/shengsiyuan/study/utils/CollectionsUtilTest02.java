package com.shengsiyuan.study.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionsUtilTest02 {

	public static void main(String[] args) {
		TreeSet<String> set=new TreeSet();
		set.add("A");
		set.add("a");
		set.add("B");
		set.add("ab");
		System.out.println(set);
		TreeSet<Double> set0=new TreeSet();
		set0.add(3.9);
		set0.add(3.8);
		//set0.add(3); //error
		set0.add(3.0);
		set0.add(-3.8);
		System.out.println(set0);
		TreeSet<Integer> set1=new TreeSet();
		set1.add(new Integer(3));
		set1.add(new Integer(2));
		set1.add(new Integer(8));
		System.out.println(set1);
		/*error 未指定排序规则
		 * TreeSet<Person> set2=new TreeSet();
		set2.add(new Person("zhaohe"));
		set2.add(new Person("zhangsan"));
		set2.add(new Person("lisi"));
		System.out.println(set2);*/
		TreeSet<Person> set2=new TreeSet<Person>(new MyComparator2());
		set2.add(new Person("zhaohe",90.40));
		set2.add(new Person("zhangsan",45.78));
		set2.add(new Person("lisi",89));
		System.out.println(set2);
		System.out.println("分数最多："+Collections.min(set2,new MyComparator2()));
		
	}

}
class Person{
	String name;
	double score;
	public Person(String name,double score){
		this.name=name;
		this.score=score;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+this.name+" score:"+String.valueOf(score);
	}
}
class MyComparator implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Person p1=(Person)o1;
		Person p2=(Person)o2;
		
		return p1.name.compareTo(p2.name);
	}
	
}
class MyComparator2 implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Person p1=(Person)o1;
		Person p2=(Person)o2;
		
		return (int) (p2.score-p1.score);
	}
	
}
