package com.test.weather.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastItem {

    private String date;

    private int period;

    private double freshSnow;

    private Weather weather;

    private double sunHours;

    private double rainHours;

    private Precipitation prec;

    private Temperature temperature;

    private double pressure;

    private int relativeHumidity;

    private Clouds clouds;

    private Wind wind;

    private Windchill windchill;

    private SnowLine snowLine;

    private boolean isNight;
}
