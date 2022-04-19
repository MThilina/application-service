package com.thilinam.applicationservice.server.facade;

import com.thilinam.applicationservice.server.wrapper.AnalyticWrapper;

import java.util.List;


public interface SensorFacade {
    List<AnalyticWrapper>  fetchSensorAnalyticData(List<String> sensorIdList, String startDateTime, String endDateTime);
}
