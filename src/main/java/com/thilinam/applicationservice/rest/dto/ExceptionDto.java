package com.thilinam.applicationservice.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ExceptionDto implements Serializable {
    private String errorCode;
    private String developerErrorMessage;
    private Date occurredTime;
}
