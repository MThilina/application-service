package com.thilinam.applicationservice.server.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AnalyticWrapper implements Serializable {
    private String sensorId;
    private String startTime;
    private String endTime;
    private AnalyticDetailWrapper detail;
}
