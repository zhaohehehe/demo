package test.design_pattern.observer_pattern.demo2;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionDisplay implements Observer,DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	Observable observable;
	
	public CurrentConditionDisplay(Observable observable) {
		super();
		// TODO Auto-generated constructor stub
		this.observable=observable;
		this.observable.addObserver(this);
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("温度:"+temperature+"湿度:"+humidity+"压力:"+pressure);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof WeatherData){
			this.temperature=((WeatherData) o).getTemperature();
			this.humidity=((WeatherData) o).getHumidity();
			this.pressure=((WeatherData) o).getPressure();
			this.display();
		}
		
	}

}
