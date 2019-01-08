package test.design_pattern.observer_pattern.demo1;

public interface Subject {
	void registerObserver(Observer o);
	void removeObserer(Observer o);
	void notifyObservers();
	
}
