package test.design_pattern.observer_pattern.demo1;

public class CurrentConditionDisplay implements Observer,DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	WeatherData weatherData;
	
	public CurrentConditionDisplay(WeatherData weatherData) {
		super();
		// TODO Auto-generated constructor stub
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
		System.out.println("温度:"+temperature+"湿度:"+humidity+"压力:"+pressure);
	}

}
