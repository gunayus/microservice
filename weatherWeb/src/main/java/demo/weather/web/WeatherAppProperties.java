package demo.weather.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.weather")
public class WeatherAppProperties {

	/**
	 * Comma-separated list of locations to display. Each entry should have the
	 * form "Country/City".
	 */
	private List<String> locations = Arrays.asList("UK/London", "Russia/Moscow");

	public List<String> getLocations() {
		return this.locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

}
