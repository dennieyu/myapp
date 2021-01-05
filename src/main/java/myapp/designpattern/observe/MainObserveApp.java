package myapp.designpattern.observe;

/**
 * @author dennieyu
 *
 */
public class MainObserveApp {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		WeatherData weather = new WeatherData(); // subject

		CurrentConditionsDisplay current1 = new CurrentConditionsDisplay(weather, 1); // register observer
		CurrentConditionsDisplay current2 = new CurrentConditionsDisplay(weather, 2); // register observer
		CurrentConditionsDisplay current3 = new CurrentConditionsDisplay(weather, 3); // register observer

		weather.setMeasurements(30, 65, 30.4f); // notify, update
		weather.setMeasurements(29, 64, 30.5f); // notify, update
		weather.setMeasurements(30, 64, 30.6f); // notify, update
	}

}
