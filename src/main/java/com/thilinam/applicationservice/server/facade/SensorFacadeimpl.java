package com.thilinam.applicationservice.server.facade;

import com.thilinam.applicationservice.server.constant.ExceptionMessage;
import com.thilinam.applicationservice.server.constant.ExceptionType;
import com.thilinam.applicationservice.server.entity.DeviceData;
import com.thilinam.applicationservice.server.exception.ApplicationServiceException;
import com.thilinam.applicationservice.server.repository.DeviceDataRepository;
import com.thilinam.applicationservice.server.service.CalculationService;
import com.thilinam.applicationservice.server.wrapper.AnalyticDetailWrapper;
import com.thilinam.applicationservice.server.wrapper.AnalyticWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 *     This contains the facade level implementation or the business logic for the related data and related services
 *
 * </p>
 */
@Component
public class SensorFacadeimpl implements SensorFacade {

    @Autowired private DeviceDataRepository deviceDataRepository;
    @Autowired private CalculationService calculationService;

    @Override
    public List<AnalyticWrapper>  fetchSensorAnalyticData(List<String> sensorIdList, String startDateTime, String endDateTime) {

        List<AnalyticWrapper> analyticWrapperList = new ArrayList<>();
        for (String sensorId : sensorIdList) {
            analyticWrapperList.add(getAnalyticWrapper(sensorId, endDateTime, startDateTime));
        }
        return analyticWrapperList;
    }


    /********************************* Private Methods ************************************************************/

    private AnalyticWrapper getAnalyticWrapper(String sensorId,String startDateTime, String endDateTime) {
        List<DeviceData> deviceDataList = deviceDataRepository.
                findBySensorIdAndEventTimeStampGreaterThanEqualAndEventTimeStampLessThanEqual(sensorId, endDateTime, startDateTime);

        if (Objects.isNull(deviceDataList) || deviceDataList.isEmpty()) {
            throw new ApplicationServiceException(ExceptionMessage.NOT_FOUND.getErrorCode(),
                    ExceptionMessage.NOT_FOUND.getErrorMessage(), ExceptionType.NOT_FOUND);
        }

        AnalyticWrapper analyticDetails = new AnalyticWrapper();
        AnalyticDetailWrapper detail = new AnalyticDetailWrapper();

        analyticDetails.setSensorId(sensorId);
        analyticDetails.setStartTime(startDateTime);
        analyticDetails.setEndTime(endDateTime);
        detail.setMaxValue(calculationService.findMaximumValue(deviceDataList));
        detail.setMinValue(calculationService.findMinimumValue(deviceDataList));
        detail.setAverageValue(calculationService.findAverageValue(deviceDataList));
        detail.setMedianValue(calculationService.findMedianValue(deviceDataList));
        analyticDetails.setDetail(detail);

        return analyticDetails;
    }
}
