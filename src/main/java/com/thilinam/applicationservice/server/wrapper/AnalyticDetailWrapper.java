package com.thilinam.applicationservice.server.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnalyticDetailWrapper implements Serializable {
    private double minValue;
    private double maxValue;
    private double averageValue;
    private double medianValue;
}
