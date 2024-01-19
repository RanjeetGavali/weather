package com.test.weather.controller;

import com.test.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/get/by/location/name")
    public ResponseEntity<?> getWeatherInfoByLocationName(@RequestParam String locationName) {

        return ResponseEntity.ok(weatherService.getWeatherInfoByLocationName(locationName));
    }

    @GetMapping("/get/hourly/by/location/name")
    public ResponseEntity<?> getHourlyWeatherInfoByLocationName(@RequestParam String locationName) {

        return ResponseEntity.ok(weatherService.getHourlyWeatherInfoByLocationName(locationName));

    }
}
