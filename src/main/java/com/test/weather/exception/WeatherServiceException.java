package com.test.weather.exception;

import lombok.Getter;

@Getter
public class WeatherServiceException extends RuntimeException {

    private int status;

    public WeatherServiceException(String message, int status) {
        super(message);
        this.status = status;

    }
}
