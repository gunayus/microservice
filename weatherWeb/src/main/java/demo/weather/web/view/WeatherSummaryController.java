package demo.weather.web.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import demo.weather.web.WeatherAppProperties;
import demo.weather.web.ows.Weather;

@Controller
@RequestMapping("/")
public class WeatherSummaryController {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://WEATHER-SERVICE";
	
	@Autowired
	protected WeatherAppProperties properties;

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView conferenceWeather() {
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("summary", getSummary());
		return new ModelAndView("summary", model);
	}

	private Object getSummary() {
		List<WeatherSummary> summary = new ArrayList<>();
		for (String location : this.properties.getLocations()) {
			String country = location.split("/")[0];
			String city = location.split("/")[1];
			String url = ACCOUNTS_SERVICE_URL + String.format("/api/weather/now/%s/%s", country, city);
			
			Weather weather = restTemplate.getForEntity(url, Weather.class).getBody();
			summary.add(createWeatherSummary(country, city, weather));
		}
		return summary;
	}



	private WeatherSummary createWeatherSummary(String country, String city,
			Weather weather) {
		// cough cough
		if ("Las Vegas".equals(city)) {
			weather.setWeatherId(666);
		}
		return new WeatherSummary(country, city, weather);
	}

}
