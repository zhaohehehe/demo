package test.design_pattern.composite_pattern;

public class CompositePatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee CEO = new Employee("zhaohe","CEO");
		
		Employee headMarketing = new Employee("Alice","Head Marketing");
		Employee headSales = new Employee("Joe","Head Sales");
		
		Employee clerkMarketing1 = new Employee("Bob","Marketing");
		Employee clerkMarketing2 = new Employee("Rob","Marketing");
		
		Employee clerkSales1 = new Employee("Rob","Sales");
		Employee clerkSales2 = new Employee("John","Sales");
		
		CEO.add(headMarketing);CEO.add(headSales);
		
		headMarketing.add(clerkMarketing1);headMarketing.add(clerkMarketing2);
		
		headSales.add(clerkSales1);headSales.add(clerkSales2);
		//¥Ú”°
		System.out.println(CEO);
		for(Employee headEmployee:CEO.getSubordinates()){
			System.out.println(headEmployee);
			for(Employee employee:headEmployee.getSubordinates()){
				System.out.println(employee);
			}
		}
	}

}
