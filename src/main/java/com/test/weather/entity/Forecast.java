package com.test.weather.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {

    private String forecastDate;

    private String nextUpdate;

    private String source;

    private String point;

    private List<ForecastItem> items;
}
