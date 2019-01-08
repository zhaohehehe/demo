package test.design_pattern.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个Employee手下还可能有多个Employee
 * @author zhaohe
 *
 */
public class Employee {
	private String name;
	private String dept;
	private List<Employee> subordinates;
	public Employee(String name,String dept){
		this.name=name;
		this.dept=dept;
		this.subordinates=new ArrayList<Employee>();
	}
	public void add(Employee e){
		subordinates.add(e);
	}
	public void remove(Employee e){
		subordinates.remove(e);
	}
	public List<Employee> getSubordinates(){
		return subordinates;
	}
	public String toString(){
		return ("Employee:[name:"+name+",dept:"+dept+"]");
	}
}
