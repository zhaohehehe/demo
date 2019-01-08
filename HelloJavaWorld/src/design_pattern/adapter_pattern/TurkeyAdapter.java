package test.design_pattern.adapter_pattern;

public class TurkeyAdapter implements Duck {
	Turkey turkey;
	public TurkeyAdapter(Turkey turkey){
		this.turkey=turkey;
	}
	@Override
	public void quack() {
		turkey.gobble();
		
	}

	@Override
	public void fly() {
		//火机飞得距离短，多飞几次
		for(int i=0;i<10;i++){
			turkey.fly();
		}
		
	}

}
