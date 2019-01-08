package test.design_pattern.strategy_pattern.demo3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByNameDesc implements SortStrategy,Comparator<Person> {

	@Override
	public void sort(List<Person> list) {
		Collections.sort(list, this);
	}

	@Override
	public int compare(Person o1, Person o2) {

		String name1=o1.getName();
		String name2=o2.getName();
		if(name1.equals(name2)){
			return o2.getId()-o1.getId();
		}
		return name2.compareTo(name1);
	}

}
