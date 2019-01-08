package test.design_pattern.observer_pattern.demo2;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements DisplayElement, Observer {
	private float currentPressure=30.4f;
	private float lastPressure;
	
	public ForecastDisplay(Observable observable) {
		super();
		// TODO Auto-generated constructor stub
		observable.addObserver(this);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("lastPressure:"+lastPressure);
		System.out.println("currentPressure:"+currentPressure);
		System.out.println("根据以上预测");

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof WeatherData){
			this.lastPressure=this.currentPressure;
			this.currentPressure=((WeatherData) o).getPressure();
			display();
		}
		
	}

}
