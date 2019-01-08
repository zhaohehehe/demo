package test.design_pattern.observer_pattern.demo1;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
	private List<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	

	public WeatherData() {
		observers=new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);

	}

	@Override
	public void removeObserer(Observer o) {
		// TODO Auto-generated method stub
		int index=observers.indexOf(o);
		if(index>=0){
			observers.remove(index);
		}

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(int i=0;i<observers.size();i++){
			Observer o= observers.get(i);
			o.update(temperature, humidity, pressure);
		}
	}
	public void measurementsChanged(){
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

	public List<Observer> getObservers() {
		return observers;
	}
	

}
