package com.thilinam.applicationservice.rest.mapper;

import com.thilinam.applicationservice.rest.dto.ExceptionDto;
import com.thilinam.applicationservice.server.exception.ApplicationServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ExceptionMapper implements RestMapper<ApplicationServiceException, ExceptionDto> {

    @Override
    public ExceptionDto convertToRest(ApplicationServiceException serverObject) {
        final ExceptionDto restObj = new ExceptionDto();
        restObj.setErrorCode(serverObject.getErrorCode());
        restObj.setDeveloperErrorMessage(serverObject.getMessage());
        restObj.setOccurredTime(new Date());
        return restObj;
    }
}
