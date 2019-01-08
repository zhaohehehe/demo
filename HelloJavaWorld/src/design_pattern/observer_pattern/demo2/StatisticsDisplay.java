package test.design_pattern.observer_pattern.demo2;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float pressure;
	private float avgPressure=0;
	Observable observable;
	private int changeCount=0;
	

	public StatisticsDisplay(Observable observable) {
		super();
		// TODO Auto-generated constructor stub
		this.observable=observable;
		this.observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("平均气压："+avgPressure);

	}

	@Override
	public void update(Observable o, Object arg) {
		changeCount++;
		// TODO Auto-generated method stub
		if(o instanceof WeatherData){
			this.pressure=((WeatherData) o).getPressure();
			int count=this.changeCount-1;
			this.avgPressure=(avgPressure * count+this.pressure)/(count+1);
			display();
		}
		
	}

}
