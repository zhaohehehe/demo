package test.design_pattern.strategy_pattern.demo3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByIdAsc implements SortStrategy,Comparator<Person> {

	@Override
	public void sort(List<Person> list) {
		Collections.sort(list, this);
	}

	@Override
	public int compare(Person o1, Person o2) {

		int id1=o1.getId();
		int id2=o2.getId();
		return id1-id2;
	}

}
