package test.design_pattern.observer_pattern.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class WeatherData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	

	public WeatherData() {
	}

	public void measurementsChanged(){
		this.setChanged();
		this.notifyObservers();
	}
	/*
	 * 观测到新值，由外部传感系统提供
	 */
	public void serMeasurements(float temperature, float humidity, float pressure){
		this.temperature=temperature;
		this.humidity=humidity;
		this.pressure=pressure;
		//this.notifyObservers();
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}
	

}
