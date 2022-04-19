package com.thilinam.applicationservice.rest.controller;

import com.thilinam.applicationservice.rest.api.v1.SensorAPI;
import com.thilinam.applicationservice.rest.dto.AnalyticDto;
import com.thilinam.applicationservice.rest.mapper.SensorDataMapper;
import com.thilinam.applicationservice.server.facade.SensorFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin(origins = "*")
public class SensorController implements SensorAPI {

    @Autowired SensorFacade sensorFacade;
    @Autowired SensorDataMapper mapper;

    @Override
    public ResponseEntity<List<AnalyticDto>> getSensorReadings(String token, List<String> sensorId,
                                                               String startUTCTime,
                                                               String endUTCTime) {

        List<AnalyticDto> dataList = mapper.convertToRest(sensorFacade.fetchSensorAnalyticData(sensorId,startUTCTime,endUTCTime));
        return ResponseEntity.ok(dataList);
    }
}
