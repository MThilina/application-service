package com.thilinam.applicationservice.server.service;

import com.thilinam.applicationservice.server.entity.DeviceData;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *     This is the service class which facilitate the method implementations for calculations
 * </p>
 */
@Log4j2
@Service
public class CalculationService {

    /**
     * <p>
     *     This method will return the maximum value for the sensors which only provide one reading
     *     Sensors which provide multiple readings will be filter out since values are not co-related
     *     Null safe method - if error occurred it will return 0 else return the maximum value
     * </p>
     * @param deviceDataList
     * @return
     */
    public double findMaximumValue(List<DeviceData> deviceDataList) {

        OptionalDouble max = deviceDataList.stream()
                .filter(deviceData -> !deviceData.getSensorReading().contains(","))
                .mapToDouble(record-> Double.parseDouble(record.getSensorReading()))
                .max();

        return max.isPresent() ? max.getAsDouble():0;
    }

    /**
     <p>
     *     This method will return the minimum value for the sensors which only provide one reading
     *     Sensors which provide multiple readings will be filter out since values are not co-related
     *     Null safe method - if error occurred it will return 0 else return the minimum value
     </p>
     * @param deviceDataList
     * @return
     */
    public double findMinimumValue(List<DeviceData> deviceDataList) {

        OptionalDouble min = deviceDataList.stream()
                .filter(deviceData -> !deviceData.getSensorReading().contains(","))
                .mapToDouble(record-> Double.parseDouble(record.getSensorReading()))
                .min();

        return min.isPresent() ? min.getAsDouble():0;

    }

    /**
     * <p>
     *     This method will calculate the average for the given sensor readings
     *     It will filter out the sensor which have more than multiple reading since those not co-related to each other
     *     Null safe method - if error occurred it will return 0 else return the average value
     * </p>
     * @param deviceDataList
     * @return
     */
    public double findAverageValue(List<DeviceData> deviceDataList){
        OptionalDouble average = deviceDataList.stream()
                .filter(deviceData -> !deviceData.getSensorReading().contains(","))
                .mapToDouble(record-> Double.parseDouble(record.getSensorReading()))
                .average();

        return average.isPresent() ? average.getAsDouble():0;
    }


    /**
     * <p>
     *     This method will calculate the median for the given sensor readings
     *     It will filter out the sensor which have more than multiple reading since those not co-related to each other
     *     Null safe method - if error occurred it will return 0 else return the median value
     * </p>
     * @param deviceDataList
     * @return
     */
    public double findMedianValue(List<DeviceData> deviceDataList){
        List<DeviceData> dataList =  deviceDataList.stream()
                .filter(deviceData -> !deviceData.getSensorReading().contains(","))
                .sorted(Comparator.comparing(DeviceData::getSensorReading))
                .collect(Collectors.toList());


        if(!dataList.isEmpty()){
            int middle = dataList.size()/2; // middle of array
            middle = middle >0 && middle %2 ==0 ? middle-1:middle; // median position
            return Double.parseDouble(dataList.get(middle).getSensorReading());
        }else{
            return 0;
        }
    }
}
