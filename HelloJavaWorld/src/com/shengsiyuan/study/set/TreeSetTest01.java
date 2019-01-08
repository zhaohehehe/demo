package com.shengsiyuan.study.set;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest01 {

	public static void main(String[] args) {
		TreeSet<Person> set2=new TreeSet<Person>(new MyComparator2());
		set2.add(new Person("zhaohe",90.40));
		set2.add(new Person("zhangsan",45.78));
		set2.add(new Person("lisi",89));
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
