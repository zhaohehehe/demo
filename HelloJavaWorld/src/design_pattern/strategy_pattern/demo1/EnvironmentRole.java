package test.design_pattern.strategy_pattern.demo1;
//������ɫ������TreeSet
public class EnvironmentRole {
	//ά��һ���ӿڵ�����
	private Strategy_AbstractStrategyRole strategy;
	//Strategy_AbstractStrategyRole����Comparator�ӿ�
	public EnvironmentRole(Strategy_AbstractStrategyRole strategy) {

		this.strategy=strategy;
	}

	public Strategy_AbstractStrategyRole getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy_AbstractStrategyRole strategy) {
		this.strategy = strategy;
	}
	public int calculate(int a,int b){
		return this.strategy.calculate(a, b);
	}

}
