package test.design_pattern.observer_pattern.demo2;

public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData=new WeatherData();
		CurrentConditionDisplay currentConditionDisplay=new CurrentConditionDisplay(weatherData);
		StatisticsDisplay statisticsDisplay=new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay=new ForecastDisplay(weatherData);
		
		weatherData.serMeasurements(25, 80, 28.0f);
		weatherData.serMeasurements(26, 80, 30.0f);
		weatherData.serMeasurements(27, 80, 32.0f);
		
	}
}
