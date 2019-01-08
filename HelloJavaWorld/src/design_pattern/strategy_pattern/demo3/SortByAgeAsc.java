package test.design_pattern.strategy_pattern.demo3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByAgeAsc implements SortStrategy,Comparator<Person> {

	@Override
	public void sort(List<Person> list) {
		Collections.sort(list, this);
	}

	@Override
	public int compare(Person o1, Person o2) {

		int age1=o1.getAge();
		int age2=o2.getAge();
		if(age1==age2){
			return o1.getId()-o2.getId();
		}
		return age1-age2;
	}

}
