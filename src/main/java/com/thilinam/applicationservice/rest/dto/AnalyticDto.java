package com.thilinam.applicationservice.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AnalyticDto implements Serializable {
    private String sensorId;
    private String startTime;
    private String endTime;
    private AnalyticInfoDto detail;
}
