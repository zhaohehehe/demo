package test.design_pattern.strategy_pattern.demo3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByNameAsc implements SortStrategy,Comparator<Person> {

	@Override
	public void sort(List<Person> list) {
		Collections.sort(list, this);
		//Collections.sort(list,new SortByIdAsc());
	}

	@Override
	public int compare(Person o1, Person o2) {

		/*String name1=o1.getName();
		String name2=o2.getName();
		if(name1.equals(name2)){
			return o1.getId()-o2.getId();
		}
		return name1.compareTo(name2);*/
		int result=o1.getName().compareTo(o2.getName());
		if(0==result){
			return o1.getId()-o2.getId();
		}
		return result;
	}

}
