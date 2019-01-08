package com.shengsiyuan.study.set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest02 {

	public static void main(String[] args) {
		HashSet<Student> set=new HashSet<Student>();
		set.add(new Student("a"));
		set.add(new Student("a"));
		set.add(new Student("b"));
		//System.out.println(set);
		for(Iterator it=set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
	}

}
class Student{
	String name;
	public Student(String name){
		this.name=name;
	}
	//向集合中添加元素是，主要判断name是否相等，所以只要返回name的哈希值即可
	public int hashCode(){
		return this.name.hashCode();
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(null!=obj&&obj instanceof Student){
			if(this.name.equals(((Student)obj).name)){
				return true;
			}
		}
		return false;
	}
}
