package test.design_pattern.strategy_pattern.demo1;

public class Client {

	public static void main(String[] args) {
		AddStrategy_SpecificStrategyRole addstrategy=new AddStrategy_SpecificStrategyRole();
		MultiplyStrategy_SpecificStrategyRole mulstrategy=new MultiplyStrategy_SpecificStrategyRole();
		EnvironmentRole environment=new EnvironmentRole(addstrategy);
		System.out.println(environment.calculate(3, 6));
		
		environment.setStrategy(mulstrategy);
		System.out.println(environment.calculate(3, 6));
		
	}

}

