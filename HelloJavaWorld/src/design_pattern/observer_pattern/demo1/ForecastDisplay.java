package test.design_pattern.observer_pattern.demo1;

import java.util.List;

public class ForecastDisplay implements DisplayElement, Observer {
	private float temperature;
	private float humidity;
	private float pressure;
	WeatherData weatherData;
	
	public ForecastDisplay(WeatherData weatherData){
		this.weatherData=weatherData;
		this.weatherData.registerObserver(this);
		
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		// TODO Auto-generated method stub
		this.temperature=temperature;
		this.humidity=humidity;
		this.pressure=pressure;
		this.display();

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		List<Observer> list=weatherData.getObservers();
		System.out.println("遍历List<Observer>，根据值进行天气预测");

	}

}
