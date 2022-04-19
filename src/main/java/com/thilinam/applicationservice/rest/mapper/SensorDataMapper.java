package com.thilinam.applicationservice.rest.mapper;

import com.thilinam.applicationservice.rest.dto.AnalyticDto;
import com.thilinam.applicationservice.rest.dto.AnalyticInfoDto;
import com.thilinam.applicationservice.server.wrapper.AnalyticDetailWrapper;
import com.thilinam.applicationservice.server.wrapper.AnalyticWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *     This mapper used for map server data in to client rest data
 * </p>
 */
@Component
@RequiredArgsConstructor
public class SensorDataMapper implements RestMapper<AnalyticWrapper, AnalyticDto>{
    @Override
    public AnalyticDto convertToRest(AnalyticWrapper serverObject) {
        if(!Objects.isNull(serverObject)){
            AnalyticDto dto = new AnalyticDto();
            dto.setSensorId(serverObject.getSensorId());
            dto.setStartTime(serverObject.getStartTime());
            dto.setEndTime(serverObject.getEndTime());
            dto.setDetail(convertDetails(serverObject.getDetail()));
            return dto;
        }
        return null;
    }

    @Override
    public List<AnalyticDto> convertToRest(List<AnalyticWrapper> objects) {
        return objects.stream().map(this::convertToRest).collect(Collectors.toList());
    }

    private AnalyticInfoDto convertDetails(AnalyticDetailWrapper wrapper){
        AnalyticInfoDto info = new AnalyticInfoDto();
        info.setAverageValue(wrapper.getAverageValue());
        info.setMaxValue(wrapper.getMaxValue());
        info.setMinValue(wrapper.getMinValue());
        info.setMedianValue(wrapper.getMedianValue());
        return info;
    }
}
