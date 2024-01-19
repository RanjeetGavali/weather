package com.test.weather.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowLine {

    private double avg;

    private double min;

    private double max;

    private String unit;

}
