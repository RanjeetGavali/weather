package com.test.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class WeatherControllerException {


    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> handleWebClientResponseException(WebClientResponseException ex) {
        return new ResponseEntity<>(ex.getStatusText(), ex.getStatusCode());
    }

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<?> handleWebClientResponseException(WeatherServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleWebClientResponseException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
