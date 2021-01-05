package myapp.designpattern.observe;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class CurrentConditionsDisplay implements Observer, DisplayElement {

	private int id;
	private float temperature;
	private float humidity;

	private Subject weatherData;

	public CurrentConditionsDisplay(Subject weatherData, int id) {
		this.id = id;
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		display(); // 편의상 여기
	}

	@Override
	public void display() {
		log.debug("장비 ID={}, 현재 기온={}도, 습도={}%", id, temperature, humidity);
	}

}
