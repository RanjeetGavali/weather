package com.test.weather.service;

import com.test.weather.entity.WeatherResponse;
import com.test.weather.exception.WeatherServiceException;
import com.test.weather.webClient.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherClient weatherClient;


    @Override
    public WeatherResponse getWeatherInfoByLocationName(String locationName) throws WebClientResponseException {
        if (locationName.trim().isEmpty()) {
            throw new WeatherServiceException("Location name can not be empty", HttpStatus.BAD_REQUEST.value());
        }
        return weatherClient.getWeatherInfoByLocation(locationName);
    }

    @Override
    public WeatherResponse getHourlyWeatherInfoByLocationName(String locationName) {
        if (locationName.trim().isEmpty()) {
            throw new WeatherServiceException("Location name can not be empty", HttpStatus.BAD_REQUEST.value());
        }
        return weatherClient.getHourlyWeatherInfoByLocation(locationName);
    }

}
