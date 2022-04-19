package com.thilinam.applicationservice.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnalyticInfoDto implements Serializable {
    private double minValue;
    private double maxValue;
    private double averageValue;
    private double medianValue;
}
