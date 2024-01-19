package com.test.weather.service;

import com.test.weather.entity.WeatherResponse;


public interface WeatherService {

    WeatherResponse getWeatherInfoByLocationName(String locationName);

    WeatherResponse getHourlyWeatherInfoByLocationName(String locationName);
}
