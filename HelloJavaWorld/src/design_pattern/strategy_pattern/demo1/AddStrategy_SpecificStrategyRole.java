package test.design_pattern.strategy_pattern.demo1;

public class AddStrategy_SpecificStrategyRole implements Strategy_AbstractStrategyRole {

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

}
