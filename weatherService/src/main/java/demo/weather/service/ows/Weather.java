package demo.weather.service.ows;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Weather extends WeatherEntry {

	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
