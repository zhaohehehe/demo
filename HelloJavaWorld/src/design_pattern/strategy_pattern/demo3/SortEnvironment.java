package test.design_pattern.strategy_pattern.demo3;

import java.util.List;

public class SortEnvironment {

	private SortStrategy sortStrategy;
	
	public SortEnvironment(SortStrategy sortStrategy){
		
		this.sortStrategy=sortStrategy;
		
	}

	public SortStrategy getSortStrategy() {
		return sortStrategy;
	}

	public void setSortStrategy(SortStrategy sortStrategy) {
		this.sortStrategy = sortStrategy;
	}
	public void sort(List<Person> list){
		this.sortStrategy.sort(list);
	}

}
