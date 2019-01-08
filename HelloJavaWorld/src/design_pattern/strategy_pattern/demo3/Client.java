package test.design_pattern.strategy_pattern.demo3;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		List<Person> list=new ArrayList<Person>();
		list.add(new Person(001,"zhaohe",23));
		list.add(new Person(002,"zhaohe",23));
		list.add(new Person(003,"zhaohe",22));
		list.add(new Person(004,"zhaohe",25));
		list.add(new Person(005,"lisi",25));
		list.add(new Person(005,"zhaohe",25));
		list.add(new Person(005,"zhaohe",24));
		
		SortEnvironment sortEnv1=new SortEnvironment(new SortByNameDesc());
		sortEnv1.sort(list);
		System.out.println(list);
		
		SortEnvironment sortEnv2=new SortEnvironment(new SortByAgeDesc());
		sortEnv2.sort(list);
		System.out.println(list);
		
		
	}

}
