package com.test.weather.webClient;


import com.test.weather.entity.WeatherResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;


public interface WeatherClient {

    String RAPID_API_HOST_VALUE = "forecast9.p.rapidapi.com";

    String RAPID_API_KEY_HEADER = "X-RapidAPI-Key";

    String RAPID_API_HOST_HEADER = "X-RapidAPI-Host";

    WeatherResponse getWeatherInfoByLocation(String city) throws WebClientResponseException;

    WeatherResponse getHourlyWeatherInfoByLocation(String city) throws WebClientResponseException;
}
