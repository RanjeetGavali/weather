package com.test.weather.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wind {

    private String unit;

    private String direction;

    private double avg;

    private double min;

    private double max;

    private String text;

    private boolean significationWind;

    private Gusts gusts;
}
