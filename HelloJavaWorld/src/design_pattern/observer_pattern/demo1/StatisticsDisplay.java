package test.design_pattern.observer_pattern.demo1;

import java.util.List;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	WeatherData weatherData;
	

	public StatisticsDisplay(WeatherData weatherData) {
		super();
		// TODO Auto-generated constructor stub
		this.weatherData=weatherData;
		this.weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		List<Observer> list=weatherData.getObservers();
		System.out.println("遍历List<Observer>求得最大值、最小值、平均值");

	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		// TODO Auto-generated method stub
		this.temperature=temperature;
		this.humidity=humidity;
		this.pressure=pressure;
		this.display();

	}

}
