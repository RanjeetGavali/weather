package com.test.weather.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Precipitation {

    private double sum;

    private double sumAsRain;

    private int probability;

    private int precipitationClass;
}
