package com.thilinam.applicationservice.rest.api.v1;

import com.thilinam.applicationservice.rest.dto.AnalyticDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1")
public interface SensorAPI {


    @ApiOperation(value = "This is an analytic api which provide analytic data for specific sensor or set of sensors",response = AnalyticDto.class)
    @GetMapping("/sensor-data")
    ResponseEntity<List<AnalyticDto>> getSensorReadings(@RequestHeader("Authorization")String token,
                                        @RequestParam("sensors") List<String> sensorId,
                                        @RequestParam("query_start_date") String startUTCTime,
                                        @RequestParam("query_end_date") String endUTCTime);
}
